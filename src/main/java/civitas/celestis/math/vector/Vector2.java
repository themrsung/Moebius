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
     * The maximum possible value a vector can have.
     */
    public static final Vector2 POSITIVE_MAX = new Vector2(Double.MAX_VALUE, Double.MAX_VALUE);

    /**
     * The minimum possible value a vector can have. (the negation of POSITIVE_MAX)
     */
    public static final Vector2 NEGATIVE_MAX = POSITIVE_MAX.negate();

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
    // Randomization
    //

    /**
     * Returns a randomized normal vector.
     *
     * @return A normal vector with a random direction
     */
    @Nonnull
    public static Vector2 random() {
        return new Vector2(Numbers.random(-1, 1), Numbers.random(-1, 1)).normalize();
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
    public final double x() {
        return x;
    }

    /**
     * Gets the Y component of this vector.
     *
     * @return The Y component of this vector
     */
    public final double y() {
        return y;
    }

    /**
     * {@inheritDoc}
     *
     * @return An array containing the values {@code {x, y}}
     */
    @Nonnull
    @Override
    public final double[] values() {
        return new double[]{x, y};
    }

    /**
     * {@inheritDoc}
     * Since a two-dimensional vector always has two components, this always returns {@code 2}.
     *
     * @return {@code 2}
     */
    @Override
    public final int length() {
        return 2;
    }

    /**
     * {@inheritDoc}
     *
     * @return The square root of {@code x * x + y * y}
     */
    @Override
    public final double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@code x * x + y * y}
     */
    @Override
    public final double magnitude2() {
        return x * x + y * y;
    }

    //
    // Setters
    //

    /**
     * Returns a new vector where only the X component is changed.
     *
     * @param x The new value to set the X component to
     * @return The resulting vector
     */
    @Nonnull
    public Vector2 x(double x) {
        return new Vector2(x, y);
    }

    /**
     * Returns a new vector where only the Y component is changed.
     *
     * @param y The new value to set the Y component to
     * @return The resulting vector
     */
    @Nonnull
    public Vector2 y(double y) {
        return new Vector2(x, y);
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
     * Given an array of vectors, this returns the sum of the vectors.
     * This is faster than chaining {@link Vector2#add(Vector2)} as it does not create a new instance every iteration.
     *
     * @param vectors Vectors to sum
     * @return The sum of the given vectors
     */
    @Nonnull
    public static Vector2 sum(@Nonnull Vector2... vectors) {
        double x = 0;
        double y = 0;

        for (final Vector2 vector : vectors) {
            x += vector.x;
            y += vector.y;
        }

        return new Vector2(x, y);
    }

    /**
     * Given an array of vectors, this returns the average of the vectors.
     * This is faster than chaining {@link Vector2#add(Vector2)} then dividing it by the total vector count
     * as it does not create a new instance every iteration.
     *
     * @param vectors Vectors to average
     * @return The average of the given vectors
     */
    @Nonnull
    public static Vector2 avg(@Nonnull Vector2... vectors) {
        return sum(vectors).divide(vectors.length);
    }

    /**
     * Given an array of vectors, this returns the maximum vector.
     * This is faster than chaining {@link Vector2#max(Vector2)} as it does not create a new instance every iteration.
     *
     * @param vectors Vectors to max
     * @return The maximum of the given vectors
     */
    @Nonnull
    public static Vector2 max(@Nonnull Vector2... vectors) {
        double x = -Double.MAX_VALUE;
        double y = -Double.MAX_VALUE;

        for (final Vector2 vector : vectors) {
            x = Math.max(x, vector.x);
            y = Math.max(y, vector.y);
        }

        return new Vector2(x, y);
    }

    /**
     * Given an array of vectors, this returns the minimum vector.
     * This is faster than chaining {@link Vector2#min(Vector2)} as it does not create a new instance every iteration.
     *
     * @param vectors Vectors to min
     * @return The minimum of the given vectors
     */
    @Nonnull
    public static Vector2 min(@Nonnull Vector2... vectors) {
        double x = Double.MAX_VALUE;
        double y = Double.MAX_VALUE;

        for (final Vector2 vector : vectors) {
            x = Math.min(x, vector.x);
            y = Math.min(y, vector.y);
        }

        return new Vector2(x, y);
    }

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
     * Negates the X component of this vector.
     *
     * @return The negated vector
     */
    @Nonnull
    public Vector2 negateX() {
        return new Vector2(-x, y);
    }

    /**
     * Negates the Y component of this vector.
     *
     * @return The negated vector
     */
    @Nonnull
    public Vector2 negateY() {
        return new Vector2(x, -y);
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
     * Returns the maximum vector between this vector and the provided vector {@code v}.
     * This is calculated by applying {@link Math#max(double, double)} to all components.
     *
     * @param v Vector to get the maximum between
     * @return Maximum vector of the two vectors
     */
    @Nonnull
    public Vector2 max(@Nonnull Vector2 v) {
        return new Vector2(
                Math.max(x, v.x),
                Math.max(y, v.y)
        );
    }

    /**
     * Returns the minimum vector between this vector and the provided vector {@code v}.
     * This is calculated by applying {@link Math#min(double, double)} to all components.
     *
     * @param v Vector to get the minimum between
     * @return Minimum vector of the two vectors
     */
    @Nonnull
    public Vector2 min(@Nonnull Vector2 v) {
        return new Vector2(
                Math.min(x, v.x),
                Math.min(y, v.y)
        );
    }

    /**
     * Given a minimum and maximum allowed range, this returns a vector which respects the given bounds.
     *
     * @param min Minimum allowed boundaries
     * @param max Maximum allowed boundaries
     * @return The clamped vector
     */
    @Nonnull
    public Vector2 clamp(@Nonnull Vector2 min, @Nonnull Vector2 max) {
        return new Vector2(
                Math.max(Math.min(x, max.x), min.x),
                Math.max(Math.min(y, max.y), min.y)
        );
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

        return multiply(new Vector2(cos, sin));
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
