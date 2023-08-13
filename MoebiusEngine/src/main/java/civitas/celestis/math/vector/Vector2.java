package civitas.celestis.math.vector;

import civitas.celestis.math.Numbers;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.function.UnaryOperator;

/**
 * An immutable two-dimensional vector.
 */
public class Vector2 implements Vector {
    //
    // Constants
    //

    /**
     * Absolute zero. This vector represents origin.
     */
    public static final Vector2 ZERO = new Vector2(0, 0);

    /**
     * The minimum positive value a vector can have without being zero.
     */
    public static final Vector2 MIN_VALUE = new Vector2(Double.MIN_VALUE, Double.MIN_VALUE);

    /**
     * The maximum positive value a vector can have without being infinite.
     */
    public static final Vector2 MAX_VALUE = new Vector2(Double.MAX_VALUE, Double.MAX_VALUE);

    /**
     * The positive X unit vector.
     */
    public static final Vector2 POSITIVE_X = new Vector2(1, 0);

    /**
     * The positive Y unit vector.
     */
    public static final Vector2 POSITIVE_Y = new Vector2(0, 1);

    /**
     * The negative X unit vector.
     */
    public static final Vector2 NEGATIVE_X = new Vector2(-1, 0);

    /**
     * The negative Y unit vector.
     */
    public static final Vector2 NEGATIVE_Y = new Vector2(0, -1);

    //
    // Constructors
    //

    /**
     * Creates a new vector.
     *
     * @param x The X component of this vector
     * @param y The Y component of this vector
     */
    public Vector2(double x, double y) {
        this.x = Numbers.requireFinite(x);
        this.y = Numbers.requireFinite(y);
    }

    /**
     * Creates a new vector.
     *
     * @param values The array of component scalars to construct this vector from
     */
    public Vector2(@Nonnull double[] values) {
        if (values.length != 2) {
            throw new IllegalArgumentException("The provided array does not have a length of 2.");
        }

        this.x = Numbers.requireFinite(values[0]);
        this.y = Numbers.requireFinite(values[1]);
    }

    /**
     * Creates a new vector.
     *
     * @param other The vector to copy values from
     */
    public Vector2(@Nonnull Vector other) {
        if (other.length() != 2) {
            throw new IllegalArgumentException("The provided vector does not have a length of 2.");
        }

        this.x = other.valueAt(0);
        this.y = other.valueAt(1);
    }

    /**
     * Creates a new vector.
     *
     * @param other The vector to copy values from
     */
    public Vector2(@Nonnull Vector2 other) {
        this.x = other.x;
        this.y = other.y;
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
    public static Vector2 random() {
        return new Vector2(Numbers.random(-1, 1), Numbers.random(-1, 1)).normalize();
    }

    //
    // Variables
    //

    protected final double x, y;

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
     * {@inheritDoc}
     *
     * @param i Index of component to get
     * @return The X, Y component for {@code 0}, {@code 1} respectively
     * @throws IndexOutOfBoundsException When the index is out of bounds {@code i < 0 || i > 1}
     */
    @Override
    public final double valueAt(int i) throws IndexOutOfBoundsException {
        switch (i) {
            case 0 -> {return x;}
            case 1 -> {return y;}

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
        return new double[]{x, y};
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
        return x * x + y * y;
    }

    /**
     * Since a {@link Vector2} always has two components, this will return a constant value {@code 2}.
     *
     * @return {@code 2}
     */
    @Override
    public final int length() {
        return 2;
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
    public Vector2 add(double s) {
        return new Vector2(x + s, y + s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s Scalar to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public Vector2 subtract(double s) {
        return new Vector2(x - s, y - s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s Scalar to multiply this vector by
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public Vector2 multiply(double s) {
        return new Vector2(x * s, y * s);
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
    public Vector2 divide(double s) throws ArithmeticException {
        if (s == 0) {
            throw new ArithmeticException("Cannot divide by zero.");
        }

        return new Vector2(x / s, y / s);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to add to this vector
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 2}
     */
    @Nonnull
    @Override
    public Vector2 add(@Nonnull Vector v) throws IllegalArgumentException {
        if (v.length() != 3) {
            throw new IllegalArgumentException("The provided vector does not have a length of 2.");
        }

        return new Vector2(x + v.valueAt(0), y + v.valueAt(1));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to subtract from this vector
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 2}
     */
    @Nonnull
    @Override
    public Vector2 subtract(@Nonnull Vector v) throws IllegalArgumentException {
        if (v.length() != 3) {
            throw new IllegalArgumentException("The provided vector does not have a length of 2.");
        }

        return new Vector2(x - v.valueAt(0), y - v.valueAt(1));
    }

    /**
     * {@inheritDoc}
     * Multiplication is performed using complex number multiplication.
     *
     * @param v The vector to multiply this vector by
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 2}
     */
    @Nonnull
    @Override
    public Vector2 multiply(@Nonnull Vector v) throws IllegalArgumentException {
        if (v.length() != 2) {
            throw new IllegalArgumentException("The provided vector's length is not 2");
        }

        return new Vector2(
                x * v.valueAt(0) - y * v.valueAt(1),
                x * v.valueAt(1) + y * v.valueAt(0)
        );
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the dot product between
     * @return The dot product of the two vectors
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 2}
     */
    @Override
    public double dot(@Nonnull Vector v) throws IllegalArgumentException {
        if (v.length() != 2) {
            throw new IllegalArgumentException("The provided vector does not have a length of 2.");
        }

        return x * v.valueAt(0) + y * v.valueAt(1);
    }

    /**
     * Adds another vector to this vector.
     *
     * @param v The vector to add to this vector
     * @return The resulting vector
     */
    @Nonnull
    public Vector2 add(@Nonnull Vector2 v) {
        return new Vector2(x + v.x, y + v.y);
    }

    /**
     * Subtracts another vector from this vector.
     *
     * @param v The vector to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    public Vector2 subtract(@Nonnull Vector2 v) {
        return new Vector2(x - v.x, y - v.y);
    }

    /**
     * Multiplies this vector by another vector using complex number multiplication.
     *
     * @param v The vector to multiply this vector with
     * @return The resulting vector
     */
    @Nonnull
    public Vector2 multiply(@Nonnull Vector2 v) {
        return new Vector2(x * v.x - y * v.y, x * v.y + y * v.x);
    }

    /**
     * Returns the dot product between this vector and the provided vector {@code v}.
     *
     * @param v The vector to get the dot product between
     * @return The dot product of the two vectors
     */
    public double dot(@Nonnull Vector2 v) {
        return x * v.x + y * v.y;
    }

    //
    // Clamping
    //

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the minimum vector of
     * @return The minimum vector of the two vectors
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 2}
     */
    @Nonnull
    @Override
    public Vector2 min(@Nonnull Vector v) throws IllegalArgumentException {
        if (v.length() != 2) {
            throw new IllegalArgumentException("The provided vector does not have a length of 2.");
        }

        return new Vector2(
                Math.min(x, v.valueAt(0)),
                Math.min(y, v.valueAt(1))
        );
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the maximum vector of
     * @return The maximum vector of the two vectors
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 2}
     */
    @Nonnull
    @Override
    public Vector2 max(@Nonnull Vector v) throws IllegalArgumentException {
        if (v.length() != 2) {
            throw new IllegalArgumentException("The provided vector does not have a length of 2.");
        }

        return new Vector2(
                Math.max(x, v.valueAt(0)),
                Math.max(y, v.valueAt(1))
        );
    }

    /**
     * {@inheritDoc}
     *
     * @param min The minimum allowed values
     * @param max The maximum allowed values
     * @return The clamped vector
     * @throws IllegalArgumentException When either of the provided vectors {@code min} or {@code max} does not have a length of {@code 2}
     */
    @Nonnull
    @Override
    public Vector2 clamp(@Nonnull Vector min, @Nonnull Vector max) throws IllegalArgumentException {
        if (min.length() != 2 || max.length() != 2) {
            throw new IllegalArgumentException("The provided vectors do not have a length of 2.");
        }

        return new Vector2(
                Math.max(Math.min(x, max.valueAt(0)), min.valueAt(0)),
                Math.max(Math.min(y, max.valueAt(1)), min.valueAt(1))
        );
    }

    /**
     * Returns the minimum vector between this vector and the provided vector {@code v}.
     *
     * @param v The vector to get the minimum vector of
     * @return The resulting vector
     */
    @Nonnull
    public Vector2 min(@Nonnull Vector2 v) {
        return new Vector2(Math.min(x, v.x), Math.min(y, v.y));
    }

    /**
     * Returns the maximum vector between this vector and the provided vector {@code v}.
     *
     * @param v The vector to get the maximum vector of
     * @return The resulting vector
     */
    @Nonnull
    public Vector2 max(@Nonnull Vector2 v) {
        return new Vector2(Math.max(x, v.x), Math.max(y, v.y));
    }

    /**
     * Returns the clamped vector of this vector to respect the given boundaries of {@code min} and {@code max}.
     *
     * @param min The minimum allowed values
     * @param max The maximum allowed values
     * @return The resulting vector
     */
    @Nonnull
    public Vector2 clamp(@Nonnull Vector2 min, @Nonnull Vector2 max) {
        return new Vector2(
                Math.max(Math.min(x, max.x), min.x),
                Math.max(Math.min(y, max.y), min.y)
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
    public Vector2 apply(@Nonnull UnaryOperator<Double> operator) {
        return new Vector2(operator.apply(x), operator.apply(y));
    }

    /**
     * {@inheritDoc}
     *
     * @return The normalized vector
     * @throws UnsupportedOperationException When the magnitude of this vector is exactly zero
     */
    @Nonnull
    @Override
    public Vector2 normalize() throws UnsupportedOperationException {
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
    public Vector2 negate() {
        return new Vector2(-x, -y);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the distance to
     * @return The distance between this vector and {@code v}
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 2}
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
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 2}
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
    public double distance(@Nonnull Vector2 v) {
        return subtract(v).magnitude();
    }

    /**
     * Returns the squared distance between this vector and the provided vector.
     *
     * @param v The vector to get the squared distance to
     * @return The squared distance between this vector and {@code v}
     */
    public double distance2(@Nonnull Vector2 v) {
        return subtract(v).magnitude2();
    }

    /**
     * Rotates this vector counter-clockwise by given angle.
     *
     * @param angRads Angle in radians to rotate this vector by
     * @return The rotated vector
     */
    @Nonnull
    public Vector2 rotate(double angRads) {
        return multiply(new Vector2(Math.cos(angRads), Math.sin(angRads)));
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
        if (v.length() != 2) return false;

        return x == v.valueAt(0) && y == v.valueAt(1);
    }

    //
    // Serialization
    //

    /**
     * Deserializes a string into a {@link Vector2}.
     *
     * @param input The input to deserialize
     * @return The parsed vector
     * @throws NumberFormatException    When the format is invalid
     * @throws IllegalArgumentException When at least one of the component scalars is non-finite
     */
    @Nonnull
    public static Vector2 parseVector(@Nonnull String input) throws IllegalArgumentException {
        if (!input.startsWith("Vector2{")) {
            throw new NumberFormatException("The provided string does not represent a Vector2.");
        }

        final String cleanInput = input.replaceAll("Vector2\\{|}", "");
        final String[] valueStrings = cleanInput.split(", ");
        final double[] values = {Double.NaN, Double.NaN};

        for (final String valueString : valueStrings) {
            final String[] split = valueString.split("=");
            values[switch (split[0]) {
                case "x" -> 0;
                case "y" -> 1;
                default -> throw new NumberFormatException("The provided string does not represent a Vector2.");
            }] = Double.parseDouble(split[1]);
        }

        return new Vector2(values);
    }

    /**
     * {@inheritDoc}
     *
     * @return The string representation of this vector
     */
    @Override
    @Nonnull
    public String toString() {
        return "Vector2{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
