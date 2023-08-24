package civitas.celestis.math.vector;

import civitas.celestis.math.util.Numbers;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.function.UnaryOperator;

/**
 * A five-dimensional vector.
 * While five-dimensional vectors have no intuitive application for representing spacial data,
 * other useful applications still exist. (e.g. The representation of colors or financial models)
 */
public class Vector5 implements Vector {
    //
    // Constants
    //

    /**
     * A vector with no direction of magnitude. Represents origin.
     */
    public static final Vector5 ZERO = new Vector5(0, 0, 0, 0, 0);

    /**
     * The maximum possible value a vector can have.
     */
    public static final Vector5 POSITIVE_MAX = new Vector5(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);

    /**
     * The minimum possible value a vector can have. (the negation of POSITIVE_MAX)
     */
    public static final Vector5 NEGATIVE_MAX = POSITIVE_MAX.negate();

    /**
     * A unit vector representing positive V.
     */
    public static final Vector5 POSITIVE_V = new Vector5(1, 0, 0, 0, 0);

    /**
     * A unit vector representing positive W.
     */
    public static final Vector5 POSITIVE_W = new Vector5(0, 1, 0, 0, 0);

    /**
     * A unit vector representing positive X.
     */
    public static final Vector5 POSITIVE_X = new Vector5(0, 0, 1, 0, 0);

    /**
     * A unit vector representing positive Y.
     */
    public static final Vector5 POSITIVE_Y = new Vector5(0, 0, 0, 1, 0);

    /**
     * A unit vector representing positive Z.
     */
    public static final Vector5 POSITIVE_Z = new Vector5(0, 0, 0, 0, 1);

    /**
     * A unit vector representing negative V.
     */
    public static final Vector5 NEGATIVE_V = new Vector5(-1, 0, 0, 0, 0);

    /**
     * A unit vector representing negative W.
     */
    public static final Vector5 NEGATIVE_W = new Vector5(0, -1, 0, 0, 0);

    /**
     * A unit vector representing negative X.
     */
    public static final Vector5 NEGATIVE_X = new Vector5(0, 0, -1, 0, 0);

    /**
     * A unit vector representing negative Y.
     */
    public static final Vector5 NEGATIVE_Y = new Vector5(0, 0, 0, -1, 0);

    /**
     * A unit vector representing negative Z.
     */
    public static final Vector5 NEGATIVE_Z = new Vector5(0, 0, 0, 0, -1);

    //
    // Constructors
    //

    /**
     * Creates a new vector.
     *
     * @param v The V component of this vector
     * @param w The W component of this vector
     * @param x The X component of this vector
     * @param y The Y component of this vector
     * @param z The Z component of this vector
     */
    public Vector5(double v, double w, double x, double y, double z) {
        this.v = Numbers.requireFinite(v);
        this.w = Numbers.requireFinite(w);
        this.x = Numbers.requireFinite(x);
        this.y = Numbers.requireFinite(y);
        this.z = Numbers.requireFinite(z);
    }

    /**
     * Creates a new vector from an array of component scalars.
     * The length of the array must be equal to {@code 5}.
     *
     * @param values Array of values to use to construct this vector
     */
    public Vector5(@Nonnull double[] values) {
        if (values.length != 5) {
            throw new IllegalArgumentException("The provided array does not represent a Vector5.");
        }

        this.v = Numbers.requireFinite(values[0]);
        this.w = Numbers.requireFinite(values[1]);
        this.x = Numbers.requireFinite(values[2]);
        this.y = Numbers.requireFinite(values[3]);
        this.z = Numbers.requireFinite(values[4]);
    }

    /**
     * Creates a new vector by copying the values of another vector.
     *
     * @param other Vector to copy the values of
     */
    public Vector5(@Nonnull Vector5 other) {
        this.v = other.v;
        this.w = other.w;
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
    }

    //
    // Randomization
    //

    /**
     * Returns a randomized normal vector.
     * @return A normal vector with a random direction
     */
    @Nonnull
    public static Vector5 random() {
        return new Vector5(
                Numbers.random(-1, 1),
                Numbers.random(-1, 1),
                Numbers.random(-1, 1),
                Numbers.random(-1, 1),
                Numbers.random(-1, 1)
        ).normalize();
    }

    //
    // Variables
    //
    private final double v, w, x, y, z;

    //
    // Properties
    //

    /**
     * Gets the V component of this vector.
     *
     * @return The V component of this vector
     */
    public final double v() {
        return v;
    }

    /**
     * Gets the W component of this vector.
     *
     * @return The W component of this vector
     */
    public final double w() {
        return w;
    }

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
     * Gets the Z component of this vector.
     *
     * @return The Z component of this vector
     */
    public final double z() {
        return z;
    }

    /**
     * {@inheritDoc}
     *
     * @return An array containing the values {@code {v, w, x, y, z}}
     */
    @Nonnull
    @Override
    public final double[] values() {
        return new double[]{v, w, x, y, z};
    }

    /**
     * {@inheritDoc}
     * Since a five-dimensional vector always has five components, this always returns {@code 5}.
     *
     * @return {@code 5}
     */
    @Override
    public final int length() {
        return 5;
    }

    /**
     * {@inheritDoc}
     *
     * @return The collective square root of all the component's squares
     */
    @Override
    public final double magnitude() {
        return Math.sqrt(v * v + w * w + x * x + y * y + z * z);
    }

    /**
     * {@inheritDoc}
     *
     * @return The sum of all the components' squares
     */
    @Override
    public final double magnitude2() {
        return v * v + w * w + x * x + y * y + z * z;
    }

    //
    // Setters
    //

    /**
     * Returns a new vector where only the V component is changed.
     *
     * @param v The new value to set the V component to
     * @return The resulting vector
     */
    @Nonnull
    public Vector5 v(double v) {
        return new Vector5(v, w, x, y, z);
    }

    /**
     * Returns a new vector where only the W component is changed.
     *
     * @param w The new value to set the W component to
     * @return The resulting vector
     */
    @Nonnull
    public Vector5 w(double w) {
        return new Vector5(v, w, x, y, z);
    }

    /**
     * Returns a new vector where only the X component is changed.
     *
     * @param x The new value to set the X component to
     * @return The resulting vector
     */
    @Nonnull
    public Vector5 x(double x) {
        return new Vector5(v, w, x, y, z);
    }

    /**
     * Returns a new vector where only the Y component is changed.
     *
     * @param y The new value to set the Y component to
     * @return The resulting vector
     */
    @Nonnull
    public Vector5 y(double y) {
        return new Vector5(v, w, x, y, z);
    }

    /**
     * Returns a new vector where only the Z component is changed.
     *
     * @param z The new value to set the Z component to
     * @return The resulting vector
     */
    @Nonnull
    public Vector5 z(double z) {
        return new Vector5(v, w, x, y, z);
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
    public Vector5 add(double s) {
        return new Vector5(v + s, w + s, x + s, y + s, z + s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s Scalar to subtract from this vector
     * @return A new vector where the scalar is subtracted from all the components
     */
    @Nonnull
    @Override
    public Vector5 subtract(double s) {
        return new Vector5(v - s, w - s, x - s, y - s, z - s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s Scalar to multiply with this vector
     * @return A new vector where the scalar is multiplied to all the components
     */
    @Nonnull
    @Override
    public Vector5 multiply(double s) {
        return new Vector5(v * s, w * s, x * s, y * s, z * s);
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
    public Vector5 divide(double s) throws ArithmeticException {
        if (s == 0) throw new ArithmeticException("Cannot divide by zero.");

        return new Vector5(v / s, w / s, x / s, y / s, z / s);
    }

    /**
     * Adds another vector to this vector.
     *
     * @param other Vector to add to this vector
     * @return The resulting vector
     */
    @Nonnull
    public Vector5 add(@Nonnull Vector5 other) {
        return new Vector5(v + other.v, w + other.w, x + other.x, y + other.y, z + other.z);
    }

    /**
     * Subtracts another vector from this vector.
     *
     * @param other Vector to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    public Vector5 subtract(@Nonnull Vector5 other) {
        return new Vector5(v - other.v, w - other.w, x - other.x, y - other.y, z - other.z);
    }

    /**
     * Returns the dot-product between {@code this} and the provided vector {@code other}.
     *
     * @param other Vector to get the dot-product of
     * @return The dot-product of the two vectors
     */
    public double dot(@Nonnull Vector5 other) {
        return v * other.v + w * other.w + x * other.x + y * other.y + z * other.z;
    }

    /**
     * Returns the wedge-product between {@code this} and the provided vector {@code other}.
     *
     * @param other Vector to get the wedge-product of
     * @return The wedge-product of the two vectors
     */
    @Nonnull
    public Vector5 wedge(@Nonnull Vector5 other) {
        return new Vector5(
                w * other.v + v * other.w,
                x * other.v + v * other.x,
                y * other.v + v * other.y,
                z * other.v + v * other.z,
                y * other.x - x * other.y
        );
    }

    /**
     * Returns the geometric product between {@code this} and the provided vector {@code other}.
     *
     * @param other Vector to get the geometric product of
     * @return The geometric product of the two vectors
     */
    @Nonnull
    public Vector5 geometric(@Nonnull Vector5 other) {
        final Vector5 wedge = wedge(other);

        return new Vector5(
                dot(other) + wedge.v,
                wedge.w,
                wedge.x,
                wedge.y,
                wedge.z
        );
    }

    //
    // Utility
    //

    /**
     * Given an array of vectors, this returns the sum of the vectors.
     * This is faster than chaining {@link Vector5#add(Vector5)} as it does not create a new instance every iteration.
     *
     * @param vectors Vectors to sum
     * @return The sum of the given vectors
     */
    @Nonnull
    public static Vector5 sum(@Nonnull Vector5... vectors) {
        double v = 0;
        double w = 0;
        double x = 0;
        double y = 0;
        double z = 0;

        for (final Vector5 vector : vectors) {
            v += vector.v;
            w += vector.w;
            x += vector.x;
            y += vector.y;
            z += vector.z;
        }

        return new Vector5(v, w, x, y, z);
    }

    /**
     * Given an array of vectors, this returns the average of the vectors.
     * This is faster than chaining {@link Vector5#add(Vector5)} then dividing it by the total vector count
     * as it does not create a new instance every iteration.
     *
     * @param vectors Vectors to average
     * @return The average of the given vectors
     */
    @Nonnull
    public static Vector5 avg(@Nonnull Vector5... vectors) {
        return sum(vectors).divide(vectors.length);
    }

    /**
     * Given an array of vectors, this returns the maximum vector.
     * This is faster than chaining {@link Vector5#max(Vector5)} as it does not create a new instance every iteration.
     *
     * @param vectors Vectors to max
     * @return The maximum of the given vectors
     */
    @Nonnull
    public static Vector5 max(@Nonnull Vector5... vectors) {
        double v = -Double.MAX_VALUE;
        double w = -Double.MAX_VALUE;
        double x = -Double.MAX_VALUE;
        double y = -Double.MAX_VALUE;
        double z = -Double.MAX_VALUE;

        for (final Vector5 vector : vectors) {
            v = Math.max(v, vector.v);
            w = Math.max(w, vector.w);
            x = Math.max(x, vector.x);
            y = Math.max(y, vector.y);
            z = Math.max(z, vector.z);
        }

        return new Vector5(v, w, x, y, z);
    }

    /**
     * Given an array of vectors, this returns the minimum vector.
     * This is faster than chaining {@link Vector5#min(Vector5)} as it does not create a new instance every iteration.
     *
     * @param vectors Vectors to min
     * @return The minimum of the given vectors
     */
    @Nonnull
    public static Vector5 min(@Nonnull Vector5... vectors) {
        double v = Double.MAX_VALUE;
        double w = Double.MAX_VALUE;
        double x = Double.MAX_VALUE;
        double y = Double.MAX_VALUE;
        double z = Double.MAX_VALUE;

        for (final Vector5 vector : vectors) {
            v = Math.min(v, vector.v);
            w = Math.min(w, vector.w);
            x = Math.min(x, vector.x);
            y = Math.min(y, vector.y);
            z = Math.min(z, vector.z);
        }

        return new Vector5(v, w, x, y, z);
    }

    /**
     * {@inheritDoc}
     *
     * @param operator The operator to apply to all components of this vector
     * @return A new vector where the operator is applied to all of its components
     */
    @Nonnull
    @Override
    public Vector5 apply(@Nonnull UnaryOperator<Double> operator) {
        return new Vector5(operator.apply(v), operator.apply(w), operator.apply(x), operator.apply(y), operator.apply(z));
    }

    /**
     * {@inheritDoc}
     *
     * @return A new vector where all the components are negated
     */
    @Nonnull
    @Override
    public Vector5 negate() {
        return new Vector5(-v, -w, -x, -y, -z);
    }

    /**
     * Negates the V component of this vector.
     *
     * @return The negated vector
     */
    @Nonnull
    public Vector5 negateV() {
        return new Vector5(-v, w, x, y, z);
    }

    /**
     * Negates the W component of this vector.
     *
     * @return The negated vector
     */
    @Nonnull
    public Vector5 negateW() {
        return new Vector5(v, -w, x, y, z);
    }

    /**
     * Negates the X component of this vector.
     *
     * @return The negated vector
     */
    @Nonnull
    public Vector5 negateX() {
        return new Vector5(v, w, -x, y, z);
    }

    /**
     * Negates the Y component of this vector.
     *
     * @return The negated vector
     */
    @Nonnull
    public Vector5 negateY() {
        return new Vector5(v, w, x, -y, z);
    }

    /**
     * Negates the Z component of this vector.
     *
     * @return The negated vector
     */
    @Nonnull
    public Vector5 negateZ() {
        return new Vector5(v, w, x, y, -z);
    }

    /**
     * {@inheritDoc}
     *
     * @return A new vector where all the components are normalized
     */
    @Nonnull
    @Override
    public Vector5 normalize() {
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
    public double distance(@Nonnull Vector5 v) {
        return v.subtract(this).magnitude();
    }

    /**
     * Returns the squared distance between {@code this} and the provided vector {@code v}.
     *
     * @param v The vector to get the squared distance to
     * @return The squared distance between the two vectors
     */
    public double distance2(@Nonnull Vector5 v) {
        return v.subtract(this).magnitude2();
    }


    /**
     * Returns the maximum vector between this vector and the provided vector {@code v}.
     * This is calculated by applying {@link Math#max(double, double)} to all components.
     *
     * @param other Vector to get the maximum between
     * @return Maximum vector of the two vectors
     */
    @Nonnull
    public Vector5 max(@Nonnull Vector5 other) {
        return new Vector5(
                Math.max(v, other.v),
                Math.max(w, other.w),
                Math.max(x, other.x),
                Math.max(y, other.y),
                Math.max(z, other.z)
        );
    }

    /**
     * Returns the minimum vector between this vector and the provided vector {@code v}.
     * This is calculated by applying {@link Math#min(double, double)} to all components.
     *
     * @param other Vector to get the minimum between
     * @return Minimum vector of the two vectors
     */
    @Nonnull
    public Vector5 min(@Nonnull Vector5 other) {
        return new Vector5(
                Math.min(v, other.v),
                Math.min(w, other.w),
                Math.min(x, other.x),
                Math.min(y, other.y),
                Math.min(z, other.z)
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
    public Vector5 clamp(@Nonnull Vector5 min, @Nonnull Vector5 max) {
        return new Vector5(
                Math.max(Math.min(v, max.v), min.v),
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
     * @return {@code true} if ths object is an instance of {@link Vector5}, and all the components are equal
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Vector5 v5)) return false;
        return v == v5.v && w == v5.w && x == v5.x && y == v5.y && z == v5.z;
    }

    //
    // Serialization
    //

    /**
     * Parses a string into a {@link Vector5}.
     *
     * @param s String to parse into a vector
     * @return The parsed string
     * @throws NumberFormatException    When the format is invalid
     * @throws IllegalArgumentException When at least one of the components is non-finite
     */
    @Nonnull
    public static Vector5 parseVector(@Nonnull String s) throws IllegalArgumentException {
        final String[] components = s.replaceAll("Vector5\\{|}", "").split(", ");
        final double[] values = new double[5];

        for (final String component : components) {
            final String[] split = component.split("=");
            if (split.length != 2) throw new NumberFormatException("The provided string does not represent a Vector5");

            values[switch (split[0]) {
                case "v" -> 0;
                case "w" -> 1;
                case "x" -> 2;
                case "y" -> 3;
                case "z" -> 4;
                default -> throw new NumberFormatException("The provided string does not represent a Vector5.");
            }] = Double.parseDouble(split[1]);
        }

        return new Vector5(values);
    }

    /**
     * Serializes this {@link Vector5} into a string.
     *
     * @return The string representation of this vector
     */
    @Override
    @Nonnull
    public String toString() {
        return "Vector5{" +
                "v=" + v +
                ", w=" + w +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
