package civitas.celestis.math.vector;

import civitas.celestis.math.util.Numbers;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.function.UnaryOperator;

/**
 * A four-dimensional vector.
 * While usage of a {@code Vector4} independently is scarce,
 * it is often used as a base class for objects with four component {@code double}s.
 */
public class Vector4 implements Vector {
    //
    // Constants
    //

    /**
     * A vector with no direction or magnitude. Represents origin.
     */
    public static final Vector4 ZERO = new Vector4(0, 0, 0, 0);

    /**
     * A unit vector representing positive W.
     */
    public static final Vector4 POSITIVE_W = new Vector4(1, 0, 0, 0);

    /**
     * A unit vector representing positive X.
     */
    public static final Vector4 POSITIVE_X = new Vector4(0, 1, 0, 0);

    /**
     * A unit vector representing positive Y.
     */
    public static final Vector4 POSITIVE_Y = new Vector4(0, 0, 1, 0);

    /**
     * A unit vector representing positive Z.
     */
    public static final Vector4 POSITIVE_Z = new Vector4(0, 0, 0, 1);

    /**
     * A unit vector representing negative W.
     */
    public static final Vector4 NEGATIVE_W = new Vector4(-1, 0, 0, 0);

    /**
     * A unit vector representing negative X.
     */
    public static final Vector4 NEGATIVE_X = new Vector4(0, -1, 0, 0);

    /**
     * A unit vector representing negative Y.
     */
    public static final Vector4 NEGATIVE_Y = new Vector4(0, 0, -1, 0);

    /**
     * A unit vector representing negative Z.
     */
    public static final Vector4 NEGATIVE_Z = new Vector4(0, 0, 0, -1);

    //
    // Constructors
    //

    /**
     * Creates a new vector from four component scalars.
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
     * Creates a new vector from an array of component scalars.
     * The length of the array must be equal to {@code 4}.
     *
     * @param values Array of values to use to construct this vector
     */
    public Vector4(@Nonnull double[] values) {
        if (values.length != 4) {
            throw new IllegalArgumentException("The provided array does not represent a Vector4.");
        }

        this.w = Numbers.requireFinite(values[0]);
        this.x = Numbers.requireFinite(values[1]);
        this.y = Numbers.requireFinite(values[2]);
        this.z = Numbers.requireFinite(values[3]);
    }

    /**
     * Creates a new vector by copying the values of another vector.
     *
     * @param other Vector to copy the values of
     */
    public Vector4(@Nonnull Vector4 other) {
        this.w = other.w;
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
    }

    //
    // Variables
    //

    /**
     * The component scalars of this vector.
     */
    private final double w, x, y, z;

    //
    // Properties
    //

    /**
     * Gets the W component of this vector.
     *
     * @return The W component of this vector
     */
    public double w() {
        return w;
    }

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
     * Gets the Z component of this vector.
     *
     * @return The Z component of this vector
     */
    public double z() {
        return z;
    }

    /**
     * {@inheritDoc}
     *
     * @return An array containing the values {@code {w, x, y, z}}
     */
    @Nonnull
    @Override
    public double[] values() {
        return new double[]{w, x, y, z};
    }

    /**
     * {@inheritDoc}
     * Since a four-dimensional vector always has four components, this always returns {@code 4}.
     *
     * @return {@code 4}
     */
    @Override
    public int length() {
        return 4;
    }

    /**
     * {@inheritDoc}
     *
     * @return The square root of {@code w * w + x * x + y * y + z * z}
     */
    @Override
    public double magnitude() {
        return Math.sqrt(w * w + x * x + y * y + z * z);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@code w * w + x * x + y * y + z * z}
     */
    @Override
    public double magnitude2() {
        return w * w + x * x + y * y + z * z;
    }

    //
    // Setters
    //

    /**
     * Returns a new vector where only the W component is changed.
     *
     * @param w The new value to set the W component to
     * @return The resulting vector
     */
    @Nonnull
    public Vector4 w(double w) {
        return new Vector4(w, x, y, z);
    }

    /**
     * Returns a new vector where only the X component is changed.
     *
     * @param x The new value to set the X component to
     * @return The resulting vector
     */
    @Nonnull
    public Vector4 x(double x) {
        return new Vector4(w, x, y, z);
    }

    /**
     * Returns a new vector where only the Y component is changed.
     *
     * @param y The new value to set the Y component to
     * @return The resulting vector
     */
    @Nonnull
    public Vector4 y(double y) {
        return new Vector4(w, x, y, z);
    }

    /**
     * Returns a new vector where only the Z component is changed.
     *
     * @param z The new value to set the Z component to
     * @return The resulting vector
     */
    @Nonnull
    public Vector4 z(double z) {
        return new Vector4(w, x, y, z);
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
    public Vector4 add(double s) {
        return new Vector4(w + s, x + s, y + s, z + s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s Scalar to subtract from this vector
     * @return A new vector where the scalar is subtracted from all the components
     */
    @Nonnull
    @Override
    public Vector4 subtract(double s) {
        return new Vector4(w - s, x - s, y - s, z - s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s Scalar to multiply with this vector
     * @return A new vector where the scalar is multiplied to all the components
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
     * @return A new vector where all the components are divided by the scalar
     * @throws ArithmeticException When the denominator {@code s == 0}
     */
    @Nonnull
    @Override
    public Vector4 divide(double s) throws ArithmeticException {
        if (s == 0) throw new ArithmeticException("Cannot divide by zero.");

        return new Vector4(w / s, x / s, y / s, z / s);
    }

    /**
     * Adds another vector to this vector.
     *
     * @param v Vector to add to this vector
     * @return The resulting vector
     */
    @Nonnull
    public Vector4 add(@Nonnull Vector4 v) {
        return new Vector4(w + v.w, x + v.x, y + v.y, z + v.z);
    }

    /**
     * Subtracts another vector from this vector.
     *
     * @param v Vector to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    public Vector4 subtract(@Nonnull Vector4 v) {
        return new Vector4(w - v.w, x - v.x, y - v.y, z - v.z);
    }

    /**
     * Returns the dot-product between {@code this} and the provided vector {@code v}.
     *
     * @param v Vector to get the dot-product of
     * @return The dot-product of the two vectors
     */
    public double dot(@Nonnull Vector4 v) {
        return w * v.w + x * v.x + y * v.y + z * v.z;
    }

    //
    // Utility
    //

    /**
     * Given an array of vectors, this returns the sum of the vectors.
     * This is faster than chaining {@link Vector4#add(Vector4)} as it does not create a new instance every iteration.
     *
     * @param vectors Vectors to sum
     * @return The sum of the given vectors
     */
    @Nonnull
    public static Vector4 sum(@Nonnull Vector4... vectors) {
        double w = 0;
        double x = 0;
        double y = 0;
        double z = 0;

        for (final Vector4 vector : vectors) {
            w += vector.w;
            x += vector.x;
            y += vector.y;
            z += vector.z;
        }

        return new Vector4(w, x, y, z);
    }

    /**
     * Given an array of vectors, this returns the average of the vectors.
     * This is faster than chaining {@link Vector4#add(Vector4)} then dividing it by the total vector count
     * as it does not create a new instance every iteration.
     *
     * @param vectors Vectors to average
     * @return The average of the given vectors
     */
    @Nonnull
    public static Vector4 avg(@Nonnull Vector4... vectors) {
        return sum(vectors).divide(vectors.length);
    }

    /**
     * {@inheritDoc}
     *
     * @param operator The operator to apply to all components of this vector
     * @return A new vector where the operator is applied to all of its components
     */
    @Nonnull
    @Override
    public Vector4 apply(@Nonnull UnaryOperator<Double> operator) {
        return new Vector4(operator.apply(w), operator.apply(x), operator.apply(y), operator.apply(z));
    }

    /**
     * {@inheritDoc}
     *
     * @return A new vector where all the components are negated
     */
    @Nonnull
    @Override
    public Vector4 negate() {
        return new Vector4(-w, -x, -y, -z);
    }

    /**
     * Negates the W component of this vector.
     *
     * @return The negated vector
     */
    @Nonnull
    public Vector4 negateW() {
        return new Vector4(-w, x, y, z);
    }

    /**
     * Negates the X component of this vector.
     *
     * @return The negated vector
     */
    @Nonnull
    public Vector4 negateX() {
        return new Vector4(w, -x, y, z);
    }

    /**
     * Negates the Y component of this vector.
     *
     * @return The negated vector
     */
    @Nonnull
    public Vector4 negateY() {
        return new Vector4(w, x, -y, z);
    }

    /**
     * Negates the Z component of this vector.
     *
     * @return The negated vector
     */
    @Nonnull
    public Vector4 negateZ() {
        return new Vector4(w, x, y, -z);
    }

    /**
     * {@inheritDoc}
     *
     * @return A new vector where all the components are normalized
     */
    @Nonnull
    @Override
    public Vector4 normalize() {
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
    public double distance(@Nonnull Vector4 v) {
        return v.subtract(this).magnitude();
    }

    /**
     * Returns the squared distance between {@code this} and the provided vector {@code v}.
     *
     * @param v The vector to get the squared distance to
     * @return The squared distance between the two vectors
     */
    public double distance2(@Nonnull Vector4 v) {
        return v.subtract(this).magnitude2();
    }

    /**
     * Returns the maximum vector between this vector and the provided vector {@code v}.
     * This is calculated by applying {@link Math#max(double, double)} to all components.
     *
     * @param v Vector to get the maximum between
     * @return Maximum vector of the two vectors
     */
    @Nonnull
    public Vector4 max(@Nonnull Vector4 v) {
        return new Vector4(
                Math.max(w, v.w),
                Math.max(x, v.x),
                Math.max(y, v.y),
                Math.max(z, v.z)
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
    public Vector4 min(@Nonnull Vector4 v) {
        return new Vector4(
                Math.min(w, v.w),
                Math.min(x, v.x),
                Math.min(y, v.y),
                Math.min(z, v.z)
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
    public Vector4 clamp(@Nonnull Vector4 min, @Nonnull Vector4 max) {
        return new Vector4(
                Math.max(Math.min(w, max.w), min.w),
                Math.max(Math.min(x, max.x), min.x),
                Math.max(Math.min(y, max.y), min.y),
                Math.max(Math.min(z, max.z), min.z)
        );
    }

    /**
     * Checks for equality between {@code this} and the provided object {@code obj}.
     *
     * @param obj Object to compare this vector to
     * @return {@code true} if ths object is an instance of {@link Vector4}, and all the components are equal
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Vector4 v)) return false;
        return w == v.w && x == v.x && y == v.y && z == v.z;
    }

    //
    // Serialization
    //

    /**
     * Parses a string into a {@link Vector4}.
     *
     * @param s String to parse into a vector
     * @return The parsed string
     * @throws NumberFormatException    When the format is invalid
     * @throws IllegalArgumentException When at least one of the components is non-finite
     */
    @Nonnull
    public static Vector4 parseVector(@Nonnull String s) throws IllegalArgumentException {
        final String[] components = s.replaceAll("Vector4\\{|}", "").split(", ");
        final double[] values = new double[4];

        for (final String component : components) {
            final String[] split = component.split("=");
            if (split.length != 2) throw new NumberFormatException("The provided string does not represent a Vector4");

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
     * Serializes this {@link Vector4} into a string.
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
