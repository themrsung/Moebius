package civitas.celestis.math.vector;

import civitas.celestis.math.Numbers;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.function.UnaryOperator;

/**
 * An immutable four-dimensional vector.
 */
public class Vector4 implements Vector {
    //
    // Constants
    //

    /**
     * Absolute zero. This vector represents origin.
     */
    public static final Vector4 ZERO = new Vector4(0, 0, 0, 0);

    /**
     * The minimum positive value a vector can have without being zero.
     */
    public static final Vector4 MIN_VALUE = new Vector4(Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE);

    /**
     * The maximum positive value a vector can have without being infinite.
     */
    public static final Vector4 MAX_VALUE = new Vector4(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);

    /**
     * The positive W unit vector.
     */
    public static final Vector4 POSITIVE_W = new Vector4(1, 0, 0, 0);

    /**
     * The positive X unit vector.
     */
    public static final Vector4 POSITIVE_X = new Vector4(0, 1, 0, 0);

    /**
     * The positive Y unit vector.
     */
    public static final Vector4 POSITIVE_Y = new Vector4(0, 0, 1, 0);

    /**
     * The positive Z unit vector.
     */
    public static final Vector4 POSITIVE_Z = new Vector4(0, 0, 0, 1);

    /**
     * The negative W unit vector.
     */
    public static final Vector4 NEGATIVE_W = new Vector4(-1, 0, 0, 0);

    /**
     * The negative X unit vector.
     */
    public static final Vector4 NEGATIVE_X = new Vector4(0, -1, 0, 0);

    /**
     * The negative Y unit vector.
     */
    public static final Vector4 NEGATIVE_Y = new Vector4(0, 0, -1, 0);

    /**
     * The negative Z unit vector.
     */
    public static final Vector4 NEGATIVE_Z = new Vector4(0, 0, 0, -1);

    //
    // Constructors
    //

    /**
     * Creates a new vector.
     *
     * @param w The W component of this vector
     * @param x The X component of this vector
     * @param y The Y component of this vector
     * @param z The Z component of this vector
     */
    public Vector4(double w, double x, double y, double z) {
        this.w = Numbers.requireFinite(w);
        this.x = Numbers.requireFinite(x);
        this.y = Numbers.requireFinite(y);
        this.z = Numbers.requireFinite(z);
    }

    /**
     * Creates a new vector.
     *
     * @param values The array of component scalars to construct this vector from
     */
    public Vector4(@Nonnull double[] values) {
        if (values.length != 4) {
            throw new IllegalArgumentException("The provided array does not have a length of 4.");
        }

        this.w = Numbers.requireFinite(values[0]);
        this.x = Numbers.requireFinite(values[1]);
        this.y = Numbers.requireFinite(values[2]);
        this.z = Numbers.requireFinite(values[3]);
    }

    /**
     * Creates a new vector.
     *
     * @param other The vector to copy values from
     */
    public Vector4(@Nonnull Vector other) {
        if (other.length() != 4) {
            throw new IllegalArgumentException("The provided vector does not have a length of 4.");
        }

        this.w = other.valueAt(0);
        this.x = other.valueAt(1);
        this.y = other.valueAt(2);
        this.z = other.valueAt(3);
    }

    /**
     * Creates a new vector.
     *
     * @param other The vector to copy values from
     */
    public Vector4(@Nonnull Vector4 other) {
        this.w = other.w;
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
    public static Vector4 random() {
        return new Vector4(
                Numbers.random(-1, 1),
                Numbers.random(-1, 1),
                Numbers.random(-1, 1),
                Numbers.random(-1, 1)
        ).normalize();
    }

    //
    // Variables
    //

    protected final double w, x, y, z;

    //
    // Getters
    //

    /**
     * Returns the W component of this vector.
     *
     * @return The W component of this vector
     */
    public final double w() {
        return w;
    }

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
     * @return The W, X, Y, Z component for {@code 0}, {@code 1}, {@code 2}, {@code 3} respectively
     * @throws IndexOutOfBoundsException When the index is out of bounds {@code i < 0 || i > 3}
     */
    @Override
    public final double valueAt(int i) throws IndexOutOfBoundsException {
        switch (i) {
            case 0 -> {return w;}
            case 1 -> {return x;}
            case 2 -> {return y;}
            case 3 -> {return z;}

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
        return new double[]{w, x, y, z};
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
        return w * w + x * x + y * y + z * z;
    }

    /**
     * Since a {@link Vector4} always has four components, this will return a constant value {@code 4}.
     *
     * @return {@code 4}
     */
    @Override
    public final int length() {
        return 4;
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
    public Vector4 add(double s) {
        return new Vector4(w + s, x + s, y + s, z + s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s Scalar to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public Vector4 subtract(double s) {
        return new Vector4(w - s, x - s, y - s, z - s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s Scalar to multiply this vector by
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public Vector4 multiply(double s) {
        return new Vector4(w * s, x * s, y * s, z * s);
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
    public Vector4 divide(double s) throws ArithmeticException {
        if (s == 0) {
            throw new ArithmeticException("Cannot divide by zero.");
        }

        return new Vector4(w / s, x / s, y / s, z / s);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to add to this vector
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 4}
     */
    @Nonnull
    @Override
    public Vector4 add(@Nonnull Vector v) throws IllegalArgumentException {
        if (v.length() != 4) {
            throw new IllegalArgumentException("The provided vector does not have a length of 4.");
        }

        return new Vector4(w + v.valueAt(0), x + v.valueAt(1), y + v.valueAt(2), z + v.valueAt(3));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to subtract from this vector
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 4}
     */
    @Nonnull
    @Override
    public Vector4 subtract(@Nonnull Vector v) throws IllegalArgumentException {
        if (v.length() != 4) {
            throw new IllegalArgumentException("The provided vector does not have a length of 4.");
        }

        return new Vector4(w - v.valueAt(0), x - v.valueAt(1), y - v.valueAt(2), z - v.valueAt(3));
    }

    /**
     * {@inheritDoc}
     * Multiplication is performed using quaternion left-multiplication.
     *
     * @param v The vector to multiply this vector by
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 4}
     */
    @Nonnull
    @Override
    public Vector4 multiply(@Nonnull Vector v) throws IllegalArgumentException {
        if (v.length() != 4) {
            throw new IllegalArgumentException("The provided vector's length is not 4");
        }

        return new Vector4(
                w * v.valueAt(0) - x * v.valueAt(1) - y * v.valueAt(2) - z * v.valueAt(3),
                w * v.valueAt(1) + x * v.valueAt(0) + y * v.valueAt(3) - z * v.valueAt(2),
                w * v.valueAt(2) - x * v.valueAt(3) + y * v.valueAt(0) + z * v.valueAt(1),
                w * v.valueAt(3) + x * v.valueAt(2) - y * v.valueAt(1) + z * v.valueAt(0)
        );
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the dot product between
     * @return The dot product of the two vectors
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 4}
     */
    @Override
    public double dot(@Nonnull Vector v) throws IllegalArgumentException {
        if (v.length() != 4) {
            throw new IllegalArgumentException("The provided vector does not have a length of 4.");
        }

        return w * v.valueAt(0) + x * v.valueAt(1) + y * v.valueAt(2) + z * v.valueAt(3);
    }

    /**
     * Adds another vector to this vector.
     *
     * @param v The vector to add to this vector
     * @return The resulting vector
     */
    @Nonnull
    public Vector4 add(@Nonnull Vector4 v) {
        return new Vector4(w + v.w, x + v.x, y + v.y, z + v.z);
    }

    /**
     * Subtracts another vector from this vector.
     *
     * @param v The vector to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    public Vector4 subtract(@Nonnull Vector4 v) {
        return new Vector4(w - v.w, x - v.x, y - v.y, z - v.z);
    }

    /**
     * Multiplies this vector by another vector using quaternion left-multiplication.
     *
     * @param v The vector to multiply this vector with
     * @return The resulting vector
     */
    @Nonnull
    public Vector4 multiply(@Nonnull Vector4 v) {
        return new Vector4(
                w * v.w - x * v.x - y * v.y - z * v.z,
                w * v.x + x * v.w + y * v.z - z * v.y,
                w * v.y - x * v.z + y * v.w + z * v.x,
                w * v.z + x * v.y - y * v.x + z * v.w
        );
    }

    /**
     * Returns the dot product between this vector and the provided vector {@code v}.
     *
     * @param v The vector to get the dot product between
     * @return The dot product of the two vectors
     */
    public double dot(@Nonnull Vector4 v) {
        return w * v.w + x * v.x + y * v.y + z * v.z;
    }

    //
    // Clamping
    //

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the minimum vector of
     * @return The minimum vector of the two vectors
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 4}
     */
    @Nonnull
    @Override
    public Vector4 min(@Nonnull Vector v) throws IllegalArgumentException {
        if (v.length() != 4) {
            throw new IllegalArgumentException("The provided vector does not have a length of 4.");
        }

        return new Vector4(
                Math.min(w, v.valueAt(0)),
                Math.min(x, v.valueAt(1)),
                Math.min(y, v.valueAt(2)),
                Math.min(z, v.valueAt(3))
        );
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the maximum vector of
     * @return The maximum vector of the two vectors
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 4}
     */
    @Nonnull
    @Override
    public Vector4 max(@Nonnull Vector v) throws IllegalArgumentException {
        if (v.length() != 4) {
            throw new IllegalArgumentException("The provided vector does not have a length of 4.");
        }

        return new Vector4(
                Math.max(w, v.valueAt(0)),
                Math.max(x, v.valueAt(1)),
                Math.max(y, v.valueAt(2)),
                Math.max(z, v.valueAt(3))
        );
    }

    /**
     * {@inheritDoc}
     *
     * @param min The minimum allowed values
     * @param max The maximum allowed values
     * @return The clamped vector
     * @throws IllegalArgumentException When either of the provided vectors {@code min} or {@code max} does not have a length of {@code 4}
     */
    @Nonnull
    @Override
    public Vector4 clamp(@Nonnull Vector min, @Nonnull Vector max) throws IllegalArgumentException {
        if (min.length() != 4 || max.length() != 4) {
            throw new IllegalArgumentException("The provided vectors do not have a length of 4.");
        }

        return new Vector4(
                Math.max(Math.min(w, max.valueAt(0)), min.valueAt(0)),
                Math.max(Math.min(x, max.valueAt(1)), min.valueAt(1)),
                Math.max(Math.min(y, max.valueAt(2)), min.valueAt(2)),
                Math.max(Math.min(z, max.valueAt(3)), min.valueAt(3))
        );
    }

    /**
     * Returns the minimum vector between this vector and the provided vector {@code v}.
     *
     * @param v The vector to get the minimum vector of
     * @return The resulting vector
     */
    @Nonnull
    public Vector4 min(@Nonnull Vector4 v) {
        return new Vector4(Math.min(w, v.w), Math.min(x, v.x), Math.min(y, v.y), Math.min(z, v.z));
    }

    /**
     * Returns the maximum vector between this vector and the provided vector {@code v}.
     *
     * @param v The vector to get the maximum vector of
     * @return The resulting vector
     */
    @Nonnull
    public Vector4 max(@Nonnull Vector4 v) {
        return new Vector4(Math.max(w, v.w), Math.max(x, v.x), Math.max(y, v.y), Math.max(z, v.z));
    }

    /**
     * Returns the clamped vector of this vector to respect the given boundaries of {@code min} and {@code max}.
     *
     * @param min The minimum allowed values
     * @param max The maximum allowed values
     * @return The resulting vector
     */
    @Nonnull
    public Vector4 clamp(@Nonnull Vector4 min, @Nonnull Vector4 max) {
        return new Vector4(
                Math.max(Math.min(w, max.w), min.w),
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
    public Vector4 apply(@Nonnull UnaryOperator<Double> operator) {
        return new Vector4(operator.apply(w), operator.apply(x), operator.apply(y), operator.apply(z));
    }

    /**
     * {@inheritDoc}
     *
     * @return The normalized vector
     * @throws UnsupportedOperationException When the magnitude of this vector is exactly zero
     */
    @Nonnull
    @Override
    public Vector4 normalize() throws UnsupportedOperationException {
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
    public Vector4 negate() {
        return new Vector4(-w, -x, -y, -z);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the distance to
     * @return The distance between this vector and {@code v}
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 4}
     */
    @Override
    public double distance(@Nonnull Vector v) throws IllegalArgumentException {
        return subtract(v).magnitude();
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the squared distance to
     * @return The squared distance between this vector and {@code v}
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 4}
     */
    @Override
    public double distance2(@Nonnull Vector v) throws IllegalArgumentException {
        return subtract(v).magnitude2();
    }

    /**
     * Returns the distance between this vector and the provided vector.
     *
     * @param v The vector to get the distance to
     * @return The distance between this vector and {@code v}
     */
    public double distance(@Nonnull Vector4 v) {
        return subtract(v).magnitude();
    }

    /**
     * Returns the squared distance between this vector and the provided vector.
     *
     * @param v The vector to get the squared distance to
     * @return The squared distance between this vector and {@code v}
     */
    public double distance2(@Nonnull Vector4 v) {
        return subtract(v).magnitude2();
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
        if (v.length() != 4) return false;

        return w == v.valueAt(0) && x == v.valueAt(1) && y == v.valueAt(2) && z == v.valueAt(3);
    }

    //
    // Serialization
    //

    /**
     * Deserializes a string into a {@link Vector4}.
     *
     * @param input The input to deserialize
     * @return The parsed vector
     * @throws NumberFormatException    When the format is invalid
     * @throws IllegalArgumentException When at least one of the component scalars is non-finite
     */
    @Nonnull
    public static Vector4 parseVector(@Nonnull String input) throws IllegalArgumentException {
        if (!input.startsWith("Vector4{")) {
            throw new NumberFormatException("The provided string does not represent a Vector4.");
        }

        final String cleanInput = input.replaceAll("Vector4\\{|}", "");
        final String[] valueStrings = cleanInput.split(", ");
        final double[] values = {Double.NaN, Double.NaN, Double.NaN, Double.NaN};

        for (final String valueString : valueStrings) {
            final String[] split = valueString.split("=");
            values[switch (split[0]) {
                case "w" -> 0;
                case "x" -> 1;
                case "y" -> 2;
                case "z" -> 3;
                default -> throw new NumberFormatException("The provided string does not represent a Vector4.");
            }] = Double.parseDouble(split[1]);
        }

        return new Vector4(values);
    }

    /**
     * {@inheritDoc}
     *
     * @return The string representation of this vector
     */
    @Override
    @Nonnull
    public String toString() {
        return "Vector4{" +
                "w=" + w +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
