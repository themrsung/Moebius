package civitas.celestis.math.vector;

import civitas.celestis.math.Numbers;
import civitas.celestis.math.quaternion.Quaternion;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.function.UnaryOperator;

/**
 * An immutable three-dimensional vector.
 */
public class Vector3 implements Vector {
    //
    // Constants
    //

    /**
     * Absolute zero. This vector represents origin.
     */
    public static final Vector3 ZERO = new Vector3(0, 0, 0);

    /**
     * The minimum positive value a vector can have without being zero.
     */
    public static final Vector3 MIN_VALUE = new Vector3(Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE);

    /**
     * The maximum positive value a vector can have without being infinite.
     */
    public static final Vector3 MAX_VALUE = new Vector3(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);

    /**
     * The positive X unit vector.
     */
    public static final Vector3 POSITIVE_X = new Vector3(1, 0, 0);

    /**
     * The positive Y unit vector.
     */
    public static final Vector3 POSITIVE_Y = new Vector3(0, 1, 0);

    /**
     * The positive Z unit vector.
     */
    public static final Vector3 POSITIVE_Z = new Vector3(0, 0, 1);

    /**
     * The negative X unit vector.
     */
    public static final Vector3 NEGATIVE_X = new Vector3(-1, 0, 0);

    /**
     * The negative Y unit vector.
     */
    public static final Vector3 NEGATIVE_Y = new Vector3(0, -1, 0);

    /**
     * The negative Z unit vector.
     */
    public static final Vector3 NEGATIVE_Z = new Vector3(0, 0, -1);

    //
    // Constructors
    //

    /**
     * Creates a new vector with no direction or magnitude.
     */
    public Vector3() {
        this(ZERO);
    }

    /**
     * Creates a new vector.
     *
     * @param x The X component of this vector
     * @param y The Y component of this vector
     * @param z The Z component of this vector
     */
    public Vector3(double x, double y, double z) {
        this.x = Numbers.requireFinite(x);
        this.y = Numbers.requireFinite(y);
        this.z = Numbers.requireFinite(z);
    }

    /**
     * Creates a new vector.
     *
     * @param values The array of component scalars to construct this vector from
     */
    public Vector3(@Nonnull double[] values) {
        if (values.length != 3) {
            throw new IllegalArgumentException("The provided array does not have a length of 3.");
        }

        this.x = Numbers.requireFinite(values[0]);
        this.y = Numbers.requireFinite(values[1]);
        this.z = Numbers.requireFinite(values[2]);
    }

    /**
     * Creates a new vector.
     *
     * @param other The vector to copy values from
     */
    public Vector3(@Nonnull Vector other) {
        if (other.length() != 3) {
            throw new IllegalArgumentException("The provided vector does not have a length of 3.");
        }

        this.x = other.valueAt(0);
        this.y = other.valueAt(1);
        this.z = other.valueAt(2);
    }

    /**
     * Creates a new vector.
     *
     * @param other The vector to copy values from
     */
    public Vector3(@Nonnull Vector3 other) {
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
    }

    //
    // Randomization
    //

    /**
     * Returns a random normalized unit vector.
     *
     * @return A random unit vector
     */
    @Nonnull
    public static Vector3 random() {
        return new Vector3(Numbers.random(-1, 1), Numbers.random(-1, 1), Numbers.random(-1, 1)).normalize();
    }

    //
    // Variables
    //

    protected final double x, y, z;

    //
    // Getters
    //

    /**
     * Returns the X component of this vector.
     *
     * @return The X component of this vector
     */
    public final double x() {
        return x;
    }

    /**
     * Returns the Y component of this vector.
     *
     * @return The Y component of this vector
     */
    public final double y() {
        return y;
    }

    /**
     * Returns the Z component of this vector.
     *
     * @return The Z component of this vector
     */
    public final double z() {
        return z;
    }

    /**
     * {@inheritDoc}
     *
     * @param i Index of component to get
     * @return The X, Y, Z component for {@code 0}, {@code 1}, {@code 2} respectively
     * @throws IndexOutOfBoundsException When the index is out of bounds {@code i < 0 || i > 2}
     */
    @Override
    public final double valueAt(int i) throws IndexOutOfBoundsException {
        switch (i) {
            case 0 -> {return x;}
            case 1 -> {return y;}
            case 2 -> {return z;}

            default -> throw new IndexOutOfBoundsException("Index " + i + " is out of bounds for this vector.");
        }
    }

    /**
     * {@inheritDoc}
     *
     * @return The array representation of this vector
     */
    @Override
    public final double[] values() {
        return new double[]{x, y, z};
    }

    //
    // Properties
    //

    /**
     * {@inheritDoc}
     *
     * @return The magnitude of this vector
     */
    @Override
    public final double magnitude() {
        return Math.sqrt(magnitude2());
    }

    /**
     * {@inheritDoc}
     *
     * @return The squared magnitude of this vector
     */
    @Override
    public final double magnitude2() {
        return x * x + y * y + z * z;
    }

    /**
     * Since a {@link Vector3} always has three components, this will return a constant value {@code 3}.
     *
     * @return {@code 3}
     */
    @Override
    public final int length() {
        return 3;
    }

    //
    // Arithmetic
    //

    /**
     * {@inheritDoc}
     *
     * @param s Scalar to add to this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public Vector3 add(double s) {
        return new Vector3(x + s, y + s, z + s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s Scalar to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public Vector3 subtract(double s) {
        return new Vector3(x - s, y - s, z - s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s Scalar to multiply this vector by
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public Vector3 multiply(double s) {
        return new Vector3(x * s, y * s, z * s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s Scalar to divide this vector by
     * @return The resulting vector
     * @throws ArithmeticException When the denominator {@code s} is zero
     */
    @Nonnull
    @Override
    public Vector3 divide(double s) throws ArithmeticException {
        if (s == 0) {
            throw new ArithmeticException("Cannot divide by zero.");
        }

        return new Vector3(x / s, y / s, z / s);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to add to this vector
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 3}
     */
    @Nonnull
    @Override
    public Vector3 add(@Nonnull Vector v) throws IllegalArgumentException {
        if (v.length() != 3) {
            throw new IllegalArgumentException("The provided vector does not have a length of 3.");
        }

        return new Vector3(x + v.valueAt(0), y + v.valueAt(1), z + v.valueAt(2));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to subtract from this vector
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 3}
     */
    @Nonnull
    @Override
    public Vector3 subtract(@Nonnull Vector v) throws IllegalArgumentException {
        if (v.length() != 3) {
            throw new IllegalArgumentException("The provided vector does not have a length of 3.");
        }

        return new Vector3(x - v.valueAt(0), y - v.valueAt(1), z - v.valueAt(2));
    }

    /**
     * Since there is no default multiplication defined for three-dimensional vectors,
     * this method will always throw an {@link UnsupportedOperationException}.
     * Use the cross product {@link Vector3#cross(Vector)} or dot product {@link Vector3#dot(Vector)} instead.
     *
     * @param v The vector to multiply this vector by
     * @return This method cannot return a value
     * @throws UnsupportedOperationException Always throws this exception
     */
    @Nonnull
    @Override
    public Vector3 multiply(@Nonnull Vector v) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("There is no default multiplication behavior defined for three-dimensional vectors.");
    }

    /**
     * Returns the cross product between this vector and the provided vector {@code v}.
     *
     * @param v The vector on the right of this operation
     * @return The cross product of the two vectors
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 3}
     */
    @Nonnull
    public Vector3 cross(@Nonnull Vector v) throws IllegalArgumentException {
        if (v.length() != 3) {
            throw new IllegalArgumentException("The provided vector does not have a length of 3.");
        }

        return new Vector3(
                y * v.valueAt(2) - z * v.valueAt(1),
                z * v.valueAt(0) - x * v.valueAt(2),
                x * v.valueAt(1) - y * v.valueAt(0)
        );
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the dot product between
     * @return The dot product of the two vectors
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 3}
     */
    @Override
    public double dot(@Nonnull Vector v) throws IllegalArgumentException {
        if (v.length() != 3) {
            throw new IllegalArgumentException("The provided vector does not have a length of 3.");
        }

        return x * v.valueAt(0) + y * v.valueAt(1) + z * v.valueAt(2);
    }

    /**
     * Adds another vector to this vector.
     *
     * @param v The vector to add to this vector
     * @return The resulting vector
     */
    @Nonnull
    public Vector3 add(@Nonnull Vector3 v) {
        return new Vector3(x + v.x, y + v.y, z + v.z);
    }

    /**
     * Subtracts another vector from this vector.
     *
     * @param v The vector to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    public Vector3 subtract(@Nonnull Vector3 v) {
        return new Vector3(x - v.x, y - v.y, z - v.z);
    }

    /**
     * Returns the cross product between this vector and the provided vector {@code v}.
     *
     * @param v The vector on the right of this operation
     * @return The cross product of the two vectors
     */
    @Nonnull
    public Vector3 cross(@Nonnull Vector3 v) {
        return new Vector3(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x);
    }

    /**
     * Returns the dot product between this vector and the provided vector {@code v}.
     *
     * @param v The vector to get the dot product between
     * @return The dot product of the two vectors
     */
    public double dot(@Nonnull Vector3 v) {
        return x * v.x + y * v.y + z * v.z;
    }

    //
    // Clamping
    //

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the minimum vector of
     * @return The minimum vector of the two vectors
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 3}
     */
    @Nonnull
    @Override
    public Vector3 min(@Nonnull Vector v) throws IllegalArgumentException {
        if (v.length() != 3) {
            throw new IllegalArgumentException("The provided vector does not have a length of 3.");
        }

        return new Vector3(
                Math.min(x, v.valueAt(0)),
                Math.min(y, v.valueAt(1)),
                Math.min(z, v.valueAt(2))
        );
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the maximum vector of
     * @return The maximum vector of the two vectors
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 3}
     */
    @Nonnull
    @Override
    public Vector3 max(@Nonnull Vector v) throws IllegalArgumentException {
        if (v.length() != 3) {
            throw new IllegalArgumentException("The provided vector does not have a length of 3.");
        }

        return new Vector3(
                Math.max(x, v.valueAt(0)),
                Math.max(y, v.valueAt(1)),
                Math.max(z, v.valueAt(2))
        );
    }

    /**
     * {@inheritDoc}
     *
     * @param min The minimum allowed values
     * @param max The maximum allowed values
     * @return The clamped vector
     * @throws IllegalArgumentException When either of the provided vectors {@code min} or {@code max} does not have a length of {@code 3}
     */
    @Nonnull
    @Override
    public Vector3 clamp(@Nonnull Vector min, @Nonnull Vector max) throws IllegalArgumentException {
        if (min.length() != 3 || max.length() != 3) {
            throw new IllegalArgumentException("The provided vectors do not have a length of 3.");
        }

        return new Vector3(
                Math.max(Math.min(x, max.valueAt(0)), min.valueAt(0)),
                Math.max(Math.min(y, max.valueAt(1)), min.valueAt(1)),
                Math.max(Math.min(z, max.valueAt(2)), min.valueAt(2))
        );
    }

    /**
     * Returns the minimum vector between this vector and the provided vector {@code v}.
     *
     * @param v The vector to get the minimum vector of
     * @return The resulting vector
     */
    @Nonnull
    public Vector3 min(@Nonnull Vector3 v) {
        return new Vector3(Math.min(x, v.x), Math.min(y, v.y), Math.min(z, v.z));
    }

    /**
     * Returns the maximum vector between this vector and the provided vector {@code v}.
     *
     * @param v The vector to get the maximum vector of
     * @return The resulting vector
     */
    @Nonnull
    public Vector3 max(@Nonnull Vector3 v) {
        return new Vector3(Math.max(x, v.x), Math.max(y, v.y), Math.max(z, v.z));
    }

    /**
     * Returns the clamped vector of this vector to respect the given boundaries of {@code min} and {@code max}.
     *
     * @param min The minimum allowed values
     * @param max The maximum allowed values
     * @return The resulting vector
     */
    @Nonnull
    public Vector3 clamp(@Nonnull Vector3 min, @Nonnull Vector3 max) {
        return new Vector3(
                Math.max(Math.min(x, max.x), min.x),
                Math.max(Math.min(y, max.y), min.y),
                Math.max(Math.min(z, max.z), min.z)
        );
    }

    //
    // Utility
    //

    /**
     * {@inheritDoc}
     *
     * @param operator The operator to apply to each component of this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public Vector3 apply(@Nonnull UnaryOperator<Double> operator) {
        return new Vector3(operator.apply(x), operator.apply(y), operator.apply(z));
    }

    /**
     * {@inheritDoc}
     *
     * @return The normalized vector
     * @throws UnsupportedOperationException When the magnitude of this vector is exactly zero
     */
    @Nonnull
    @Override
    public Vector3 normalize() throws UnsupportedOperationException {
        try {
            return divide(magnitude());
        } catch (final ArithmeticException e) {
            throw new UnsupportedOperationException("Cannot normalize a vector with no direction. (with a magnitude of zero)");
        }
    }

    /**
     * {@inheritDoc}
     *
     * @return The negation of this vector
     */
    @Nonnull
    @Override
    public Vector3 negate() {
        return new Vector3(-x, -y, -z);
    }

    /**
     * Rotates this vector by a rotation quaternion.
     *
     * @param rq The rotation quaternion to use
     * @return The rotated vector
     */
    @Nonnull
    public Vector3 rotate(@Nonnull Quaternion rq) {
        return rq.multiply(quaternion()).multiply(rq.conjugate()).vector();
    }

    /**
     * Converts this vector into a pure quaternion. (a quaternion with the scalar part being {@code 0})
     *
     * @return The pure quaternion of this vector
     */
    @Nonnull
    public Quaternion quaternion() {
        return new Quaternion(0, x, y, z);
    }

    //
    // Equality
    //

    /**
     * {@inheritDoc}
     *
     * @param obj The object to compare to
     * @return {@code true} if the given object is an instance of {@link Vector}, and the components and length are equal
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Vector v)) return false;
        if (v.length() != 3) return false;

        return x == v.valueAt(0) && y == v.valueAt(1) && z == v.valueAt(2);
    }

    //
    // Serialization
    //

    /**
     * Deserializes a string into a {@link Vector3}.
     *
     * @param input The input to deserialize
     * @return The parsed vector
     * @throws NumberFormatException    When the format is invalid
     * @throws IllegalArgumentException When at least one of the component scalars is non-finite
     */
    @Nonnull
    public static Vector3 parseVector(@Nonnull String input) throws IllegalArgumentException {
        if (!input.startsWith("Vector3{")) {
            throw new NumberFormatException("The provided string does not represent a Vector3.");
        }

        final String cleanInput = input.replaceAll("Vector3\\{|}", "");
        final String[] valueStrings = cleanInput.split(", ");
        final double[] values = {Double.NaN, Double.NaN, Double.NaN};

        for (final String valueString : valueStrings) {
            final String[] split = valueString.split("=");
            values[switch (split[0]) {
                case "x" -> 0;
                case "y" -> 1;
                case "z" -> 2;
                default -> throw new NumberFormatException("The provided string does not represent a Vector3.");
            }] = Double.parseDouble(split[1]);
        }

        return new Vector3(values);
    }

    /**
     * {@inheritDoc}
     *
     * @return The string representation of this vector
     */
    @Override
    @Nonnull
    public String toString() {
        return "Vector3{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
