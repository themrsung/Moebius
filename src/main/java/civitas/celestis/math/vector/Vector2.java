package civitas.celestis.math.vector;

import civitas.celestis.math.util.Numbers;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.function.UnaryOperator;

/**
 * A two-dimensional vector.
 * {@code Vector2} has two scalar components.
 */
public class Vector2 implements Vector {
    //
    // Constants
    //

    /**
     * A vector with no direction of magnitude. Represent origin.
     */
    public static final Vector2 ZERO = new Vector2(0, 0);

    /**
     * A unit vector representing positive X.
     */
    public static final Vector2 POSITIVE_X = new Vector2(1, 0);

    /**
     * A unit vector representing positive Y.
     */
    public static final Vector2 POSITIVE_Y = new Vector2(0, 1);

    /**
     * A unit vector representing negative X.
     */
    public static final Vector2 NEGATIVE_X = new Vector2(-1, 0);

    /**
     * A unit vector representing negative Y.
     */
    public static final Vector2 NEGATIVE_Y = new Vector2(0, -1);

    //
    // Constructors
    //

    /**
     * Creates a new vector from two component scalars.
     *
     * @param x The X component of this vector
     * @param y The Y component of this vector
     */
    public Vector2(double x, double y) {
        this.x = Numbers.requireFinite(x);
        this.y = Numbers.requireFinite(y);
    }

    /**
     * Creates a new vector from an array of component scalars.
     * The length of the array must be equal to {@code 2}.
     *
     * @param values Array of values to use to construct this vector
     */
    public Vector2(@Nonnull double[] values) {
        if (values.length != 2) {
            throw new IllegalArgumentException("The provided array does not represent a Vector2.");
        }

        this.x = Numbers.requireFinite(values[0]);
        this.y = Numbers.requireFinite(values[1]);
    }

    /**
     * Creates a new vector by copying the values of another vector.
     *
     * @param other Vector to copy the values of
     */
    public Vector2(@Nonnull Vector2 other) {
        this.x = other.x;
        this.y = other.y;
    }

    //
    // Variables
    //

    /**
     * The component scalars of this vector.
     */
    private final double x, y;

    //
    // Properties
    //

    /**
     * Gets the X component of this vector.
     *
     * @return The X component of this vector
     */
    public double x() {
        return x;
    }

    /**
     * Gets the Y component of this vector.
     *
     * @return The Y component of this vector
     */
    public double y() {
        return y;
    }

    /**
     * {@inheritDoc}
     *
     * @return An array containing the values {@code {x, y}}
     */
    @Nonnull
    @Override
    public double[] values() {
        return new double[]{x, y};
    }

    /**
     * {@inheritDoc}
     * Since a two-dimensional vector always has two components, this always returns {@code 2}.
     *
     * @return {@code 2}
     */
    @Override
    public int length() {
        return 2;
    }

    /**
     * {@inheritDoc}
     *
     * @return The square root of {@code x * x + y * y}
     */
    @Override
    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@code x * x + y * y}
     */
    @Override
    public double magnitude2() {
        return x * x + y * y;
    }

    //
    // Arithmetic
    //

    /**
     * {@inheritDoc}
     *
     * @param s Scalar to add to this vector
     * @return A new vector where the scalar is added to all the components
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
     * @return A new vector where the scalar is subtracted from all the components
     */
    @Nonnull
    @Override
    public Vector2 subtract(double s) {
        return new Vector2(x - s, y - s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s Scalar to multiply with this vector
     * @return A new vector where the scalar is multiplied to all the components
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
     * @return A new vector where all the components are divided by the scalar
     * @throws ArithmeticException When the denominator {@code s == 0}
     */
    @Nonnull
    @Override
    public Vector2 divide(double s) throws ArithmeticException {
        if (s == 0) throw new ArithmeticException("Cannot divide by zero");

        return new Vector2(x / s, y / s);
    }


    /**
     * Adds another vector to this vector.
     *
     * @param v Vector to add to this vector
     * @return The resulting vector
     */
    @Nonnull
    public Vector2 add(@Nonnull Vector2 v) {
        return new Vector2(x + v.x, y + v.y);
    }

    /**
     * Subtracts another vector from this vector.
     *
     * @param v Vector to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    public Vector2 subtract(@Nonnull Vector2 v) {
        return new Vector2(x - v.x, y - v.y);
    }

    /**
     * Multiplies this vector by another vector.
     *
     * @param v Vector to multiply this vector by
     * @return The resulting vector
     */
    @Nonnull
    public Vector2 multiply(@Nonnull Vector2 v) {
        return new Vector2(x * v.x - y * v.y, x * v.y + y * v.x);
    }

    /**
     * Returns the dot-product between {@code this} and the provided vector {@code v}.
     *
     * @param v Vector to get the dot-product of
     * @return The dot-product of the two vectors
     */
    public double dot(@Nonnull Vector2 v) {
        return x * v.x + y * v.y;
    }

    //
    // Utility
    //


    /**
     * {@inheritDoc}
     *
     * @param operator The operator to apply to all components of this vector
     * @return A new vector where the operator is applied to all of its components
     */
    @Nonnull
    @Override
    public Vector2 apply(@Nonnull UnaryOperator<Double> operator) {
        return new Vector2(operator.apply(x), operator.apply(y));
    }

    /**
     * {@inheritDoc}
     *
     * @return A new vector where all the components are negated
     */
    @Nonnull
    @Override
    public Vector2 negate() {
        return new Vector2(-x, -y);
    }

    /**
     * {@inheritDoc}
     *
     * @return A new vector where all the components are normalized
     */
    @Nonnull
    @Override
    public Vector2 normalize() {
        final double m = magnitude();
        if (m == 0) return ZERO;

        return divide(m);
    }


    /**
     * Returns the distance between {@code this} and the provided vector {@code v}.
     *
     * @param v The vector to get the distance to
     * @return The distance between the two vectors
     */
    public double distance(@Nonnull Vector2 v) {
        return v.subtract(this).magnitude();
    }

    /**
     * Returns the squared distance between {@code this} and the provided vector {@code v}.
     *
     * @param v The vector to get the squared distance to
     * @return The squared distance between the two vectors
     */
    public double distance2(@Nonnull Vector2 v) {
        return v.subtract(this).magnitude2();
    }

    /**
     * Rotates this vector counter-clockwise by given angle.
     * Angle is denoted in radians.
     *
     * @param angle Angle to rotate this vector by in radians
     * @return The rotated vector
     */
    @Nonnull
    public Vector2 rotate(double angle) {
        final double cos = Math.cos(angle);
        final double sin = Math.sin(angle);

        return multiply(new Vector2(cos, -sin));
    }

    //
    // Equality
    //

    /**
     * Checks for equality between {@code this} and the provided object {@code obj}.
     *
     * @param obj Object to compare this vector to
     * @return {@code true} if the object is an instance of {@link Vector2}, and all the components are equal
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Vector2 v)) return false;
        return x == v.x && y == v.y;
    }

    //
    // Serialization
    //

    /**
     * Parses a string into a {@link Vector2}.
     *
     * @param s String to parse into a vector
     * @return The parsed string
     * @throws NumberFormatException    When the format is invalid
     * @throws IllegalArgumentException When at least one of the components is non-finite
     */
    @Nonnull
    public static Vector2 parseVector(@Nonnull String s) throws IllegalArgumentException {
        final String[] components = s.replaceAll("Vector2\\{|}", "").split(", ");
        final double[] values = new double[2];

        for (final String component : components) {
            final String[] split = component.split("=");
            if (split.length != 2) throw new NumberFormatException("The provided string does not represent a Vector2.");

            values[switch (split[0]) {
                case "x" -> 0;
                case "y" -> 1;
                default -> throw new NumberFormatException("The provided string does not represent a Vector2.");
            }] = Double.parseDouble(split[1]);
        }

        return new Vector2(values);
    }

    /**
     * Serializes this {@link Vector2} into a string.
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
