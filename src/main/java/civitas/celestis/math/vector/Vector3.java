package civitas.celestis.math.vector;

import civitas.celestis.math.quaternion.Quaternion;
import civitas.celestis.math.util.Numbers;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.function.UnaryOperator;

/**
 * A three-dimensional vector.
 * {@code Vector3} has three scalar components.
 */
public class Vector3 implements Vector {
    //
    // Constants
    //

    /**
     * A vector with no direction or magnitude. Represents origin.
     */
    public static final Vector3 ZERO = new Vector3(0, 0, 0);

    /**
     * A unit vector representing positive X.
     */
    public static final Vector3 POSITIVE_X = new Vector3(1, 0, 0);

    /**
     * A unit vector representing positive Y.
     */
    public static final Vector3 POSITIVE_Y = new Vector3(0, 1, 0);

    /**
     * A unit vector representing positive Z.
     */
    public static final Vector3 POSITIVE_Z = new Vector3(0, 0, 1);

    /**
     * A unit vector representing negative X.
     */
    public static final Vector3 NEGATIVE_X = new Vector3(-1, 0, 0);

    /**
     * A unit vector representing negative Y.
     */
    public static final Vector3 NEGATIVE_Y = new Vector3(0, -1, 0);

    /**
     * A unit vector representing negative Z.
     */
    public static final Vector3 NEGATIVE_Z = new Vector3(0, 0, -1);

    //
    // Constructors
    //

    /**
     * Creates a new vector from three component scalars.
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
     * Creates a new vector from an array of component scalars.
     * The length of the array must be equal to {@code 3}.
     *
     * @param values Array of values to use to construct this vector
     */
    public Vector3(@Nonnull double[] values) {
        if (values.length != 3) {
            throw new IllegalArgumentException("The provided array does not represent a Vector3.");
        }

        this.x = Numbers.requireFinite(values[0]);
        this.y = Numbers.requireFinite(values[1]);
        this.z = Numbers.requireFinite(values[2]);
    }

    /**
     * Creates a new vector by copying the values of another vector.
     *
     * @param other Vector to copy the values of
     */
    public Vector3(@Nonnull Vector3 other) {
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
    private final double x, y, z;

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
     * @return An array containing the values {@code {x, y, z}}
     */
    @Nonnull
    @Override
    public final double[] values() {
        return new double[]{x, y, z};
    }

    /**
     * {@inheritDoc}
     * Since a three-dimensional vector always has three components, this always returns {@code 3}.
     *
     * @return {@code 3}
     */
    @Override
    public final int length() {
        return 3;
    }

    /**
     * {@inheritDoc}
     *
     * @return The square root of {@code x * x + y * y + z * z}
     */
    @Override
    public final double magnitude() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@code x * x + y * y + z * z}
     */
    @Override
    public final double magnitude2() {
        return x * x + y * y + z * z;
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
    public Vector3 x(double x) {
        return new Vector3(x, y, z);
    }

    /**
     * Returns a new vector where only the Y component is changed.
     *
     * @param y The new value to set the Y component to
     * @return The resulting vector
     */
    @Nonnull
    public Vector3 y(double y) {
        return new Vector3(x, y, z);
    }

    /**
     * Returns a new vector where only the Z component is changed.
     *
     * @param z The new value to set the Z component to
     * @return The resulting vector
     */
    @Nonnull
    public Vector3 z(double z) {
        return new Vector3(x, y, z);
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
    public Vector3 add(double s) {
        return new Vector3(x + s, y + s, z + s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s Scalar to subtract from this vector
     * @return A new vector where the scalar is subtracted from all the components
     */
    @Nonnull
    @Override
    public Vector3 subtract(double s) {
        return new Vector3(x - s, y - s, z - s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s Scalar to multiply with this vector
     * @return A new vector where the scalar is multiplied to all the components
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
     * @return A new vector where all the components are divided by the scalar
     * @throws ArithmeticException When the denominator {@code s == 0}
     */
    @Nonnull
    @Override
    public Vector3 divide(double s) throws ArithmeticException {
        if (s == 0) throw new ArithmeticException("Cannot divide by zero");

        return new Vector3(x / s, y / s, z / s);
    }

    /**
     * Adds another vector to this vector.
     *
     * @param v Vector to add to this vector
     * @return The resulting vector
     */
    @Nonnull
    public Vector3 add(@Nonnull Vector3 v) {
        return new Vector3(x + v.x, y + v.y, z + v.z);
    }

    /**
     * Subtracts another vector from this vector.
     *
     * @param v Vector to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    public Vector3 subtract(@Nonnull Vector3 v) {
        return new Vector3(x - v.x, y - v.y, z - v.z);
    }

    /**
     * Returns the cross-product between {@code this} and the provided vector {@code v}.
     *
     * @param v Vector to get the cross-product of
     * @return The cross-product of the two vectors
     */
    @Nonnull
    public Vector3 cross(@Nonnull Vector3 v) {
        return new Vector3(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x);
    }

    /**
     * Returns the dot-product between {@code this} and the provided vector {@code v}.
     *
     * @param v Vector to get the dot-product of
     * @return The dot-product of the two vectors
     */
    public double dot(@Nonnull Vector3 v) {
        return x * v.x + y * v.y + z * v.z;
    }

    //
    // Utility
    //

    /**
     * Given an array of vectors, this returns the sum of the vectors.
     * This is faster than chaining {@link Vector3#add(Vector3)} as it does not create a new instance every iteration.
     *
     * @param vectors Vectors to sum
     * @return The sum of the given vectors
     */
    @Nonnull
    public static Vector3 sum(@Nonnull Vector3... vectors) {
        double x = 0;
        double y = 0;
        double z = 0;

        for (final Vector3 vector : vectors) {
            x += vector.x;
            y += vector.y;
            z += vector.z;
        }

        return new Vector3(x, y, z);
    }

    /**
     * Given an array of vectors, this returns the average of the vectors.
     * This is faster than chaining {@link Vector3#add(Vector3)} then dividing it by the total vector count
     * as it does not create a new instance every iteration.
     *
     * @param vectors Vectors to average
     * @return The average of the given vectors
     */
    @Nonnull
    public static Vector3 avg(@Nonnull Vector3... vectors) {
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
    public Vector3 apply(@Nonnull UnaryOperator<Double> operator) {
        return new Vector3(operator.apply(x), operator.apply(y), operator.apply(z));
    }

    /**
     * {@inheritDoc}
     *
     * @return A new vector where all the components are negated
     */
    @Nonnull
    @Override
    public Vector3 negate() {
        return new Vector3(-x, -y, -z);
    }

    /**
     * Negates the X component of this vector.
     *
     * @return The negated vector
     */
    @Nonnull
    public Vector3 negateX() {
        return new Vector3(-x, y, z);
    }

    /**
     * Negates the Y component of this vector.
     *
     * @return The negated vector
     */
    @Nonnull
    public Vector3 negateY() {
        return new Vector3(x, -y, z);
    }

    /**
     * Negates the Z component of this vector.
     *
     * @return The negated vector
     */
    @Nonnull
    public Vector3 negateZ() {
        return new Vector3(x, y, -z);
    }

    /**
     * {@inheritDoc}
     *
     * @return A new vector where all the components are normalized
     */
    @Nonnull
    @Override
    public Vector3 normalize() {
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
    public Vector3 max(@Nonnull Vector3 v) {
        return new Vector3(
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
    public Vector3 min(@Nonnull Vector3 v) {
        return new Vector3(
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
    public Vector3 clamp(@Nonnull Vector3 min, @Nonnull Vector3 max) {
        return new Vector3(
                Math.max(Math.min(x, max.x), min.x),
                Math.max(Math.min(y, max.y), min.y),
                Math.max(Math.min(z, max.z), min.z)
        );
    }

    /**
     * Returns the distance between {@code this} and the provided vector {@code v}.
     *
     * @param v The vector to get the distance to
     * @return The distance between the two vectors
     */
    public double distance(@Nonnull Vector3 v) {
        return v.subtract(this).magnitude();
    }

    /**
     * Returns the squared distance between {@code this} and the provided vector {@code v}.
     *
     * @param v The vector to get the squared distance to
     * @return The squared distance between the two vectors
     */
    public double distance2(@Nonnull Vector3 v) {
        return v.subtract(this).magnitude2();
    }

    /**
     * Converts this {@link Vector3} into a pure {@link Quaternion}.
     *
     * @return Pure quaternion of this vector
     */
    @Nonnull
    public Quaternion quaternion() {
        return new Quaternion(0, x, y, z);
    }

    /**
     * Given a rotation quaternion {@code rq}, this returns a rotated vector of {@code this}.
     *
     * @param rq The rotation quaternion to apply to this vector
     * @return The rotated vector
     */
    @Nonnull
    public Vector3 rotate(@Nonnull Quaternion rq) {
        return rq.multiply(quaternion()).multiply(rq.conjugate()).vector();
    }

    //
    // Equality
    //

    /**
     * Checks for equality between {@code this} and the provided object {@code obj}.
     *
     * @param obj Object to compare this vector to
     * @return {@code true} if the object is an instance of {@link Vector3}, and all the components are equal
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Vector3 v)) return false;
        return x == v.x && y == v.y && z == v.z;
    }

    //
    // Serialization
    //

    /**
     * Parses a string into a {@link Vector3}.
     *
     * @param s String to parse into a vector
     * @return The parsed string
     * @throws NumberFormatException    When the format is invalid
     * @throws IllegalArgumentException When at least one of the components is non-finite
     */
    @Nonnull
    public static Vector3 parseVector(@Nonnull String s) throws IllegalArgumentException {
        final String[] components = s.replaceAll("Vector3\\{|}", "").split(", ");
        final double[] values = new double[3];

        for (final String component : components) {
            final String[] split = component.split("=");
            if (split.length != 2) throw new NumberFormatException("The provided string does not represent a Vector3.");

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
     * Serializes this {@link Vector3} into a string.
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
