package civitas.celestis.math.vector;

import civitas.celestis.math.Numbers;
import civitas.celestis.math.complex.Quaternion;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.function.UnaryOperator;

/**
 * An immutable three-dimensional vector which uses {@code float}s to represent its components.
 */
public class Float3 extends Number implements FloatVector<Float3> {
    //
    // Constants
    //

    /**
     * Absolute zero. This vector represents origin.
     */
    public static final Float3 ZERO = new Float3(0, 0, 0);

    /**
     * The positive X unit vector.
     */
    public static final Float3 POSITIVE_X = new Float3(1, 0, 0);

    /**
     * The positive Y unit vector.
     */
    public static final Float3 POSITIVE_Y = new Float3(0, 1, 0);

    /**
     * The positive Z unit vector.
     */
    public static final Float3 POSITIVE_Z = new Float3(0, 0, 1);

    /**
     * The negative X unit vector.
     */
    public static final Float3 NEGATIVE_X = new Float3(-1, 0, 0);

    /**
     * The negative Y unit vector.
     */
    public static final Float3 NEGATIVE_Y = new Float3(0, -1, 0);

    /**
     * The negative Z unit vector.
     */
    public static final Float3 NEGATIVE_Z = new Float3(0, 0, -1);

    //
    // Constructors
    //

    /**
     * Creates a new vector.
     *
     * @param x The X component of this vector
     * @param y The Y component of this vector
     * @param z The Z component of this vector
     */
    public Float3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Creates a new vector.
     *
     * @param values An array of length {@code 3} which contains the component os this vector in XYZ order
     */
    public Float3(@Nonnull float[] values) {
        if (values.length != 3) {
            throw new IllegalArgumentException("The provided array does not have a length of 3.");
        }

        this.x = values[0];
        this.y = values[1];
        this.z = values[2];
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     */
    public Float3(@Nonnull Float3 v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     */
    public Float3(@Nonnull Vector3 v) {
        this.x = (float) v.x;
        this.y = (float) v.y;
        this.z = (float) v.z;
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     */
    public Float3(@Nonnull Int3 v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
    }

    //
    // Variables
    //

    /**
     * The X component of this vector.
     */
    protected final float x;

    /**
     * The Y component of this vector.
     */
    protected final float y;

    /**
     * The Z component of this vector
     */
    protected final float z;

    //
    // Getters
    //

    /**
     * Returns the X component of this vector.
     *
     * @return The X component of this vector
     */
    public final float x() {
        return x;
    }

    /**
     * Returns the Y component of this vector.
     *
     * @return The Y component of this vector
     */
    public final float y() {
        return y;
    }

    /**
     * Returns the Z component of this vector.
     *
     * @return The Z component of this vector
     */
    public final float z() {
        return z;
    }

    //
    // Properties
    //

    /**
     * {@inheritDoc}
     *
     * @return {@code true} if this vector is zero
     */
    @Override
    public final boolean isZero() {
        return x == 0 && y == 0 && z == 0;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@code true} if this vector is not a number
     */
    @Override
    public final boolean isNaN() {
        return Float.isNaN(x) || Float.isNaN(y) || Float.isNaN(z);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@code true} if this vector is finite
     */
    @Override
    public final boolean isFinite() {
        return Float.isFinite(x) && Float.isFinite(y) && Float.isFinite(z);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@code true} if this vector is infinite
     */
    @Override
    public final boolean isInfinite() {
        return Float.isInfinite(x) || Float.isInfinite(y) || Float.isInfinite(z);
    }

    /**
     * {@inheritDoc}
     *
     * @return The array representation of this vector
     */
    @Nonnull
    @Override
    public final float[] values() {
        return new float[]{x, y, z};
    }

    /**
     * {@inheritDoc}
     *
     * @return The magnitude of this vector
     */
    @Override
    public final float norm() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    /**
     * {@inheritDoc}
     *
     * @return The squared magnitude of this vector
     */
    @Override
    public final float norm2() {
        return x * x + y * y + z * z;
    }

    /**
     * {@inheritDoc}
     *
     * @return The Manhattan magnitude of this vector
     */
    @Override
    public final float normManhattan() {
        return Math.abs(x) + Math.abs(y) + Math.abs(z);
    }

    //
    // Arithmetic
    //

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to add to this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public Float3 add(float s) {
        return new Float3(x + s, y + s, z + s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public Float3 subtract(float s) {
        return new Float3(x - s, y - s, z - s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to multiply to this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public Float3 multiply(float s) {
        return new Float3(x * s, y * s, z * s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to divide this vector by
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public Float3 divide(float s) {
        return new Float3(x - s, y - s, z - s);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to add to this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public Float3 add(@Nonnull Float3 v) {
        return new Float3(x + v.x, y + v.y, z + v.z);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public Float3 subtract(@Nonnull Float3 v) {
        return new Float3(x - v.x, y - v.y, z - v.z);
    }

    /**
     * Returns the cross product of this vector and the provided vector {@code v}.
     *
     * @param v The vector of which to get the cross product between
     * @return The cross product of the two vectors
     */
    @Nonnull
    public Float3 cross(@Nonnull Float3 v) {
        return new Float3(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the dot product between
     * @return The dot product of the two vectors
     */
    @Override
    public final float dot(@Nonnull Float3 v) {
        return x * v.x + y * v.y + z * v.z;
    }

    //
    // Distance
    //

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the distance between
     * @return The Euclidean distance between this vector and the provided vector {@code v}
     */
    @Override
    public float distance(@Nonnull Float3 v) {
        final float dx = x - v.x;
        final float dy = y - v.y;
        final float dz = z - v.z;

        return (float) Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the squared distance between
     * @return The squared Euclidean distance between this vector and the provided vector {@code v}
     */
    @Override
    public float distance2(@Nonnull Float3 v) {
        final float dx = x - v.x;
        final float dy = y - v.y;
        final float dz = z - v.z;

        return dx * dx + dy * dy + dz * dz;
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the Manhattan distance between
     * @return The Manhattan distance between this vector and the provided vector {@code v}
     */
    @Override
    public float distanceManhattan(@Nonnull Float3 v) {
        final float dx = Math.abs(x - v.x);
        final float dy = Math.abs(y - v.y);
        final float dz = Math.abs(z - v.z);

        return dx + dy + dz;
    }

    //
    // Normalization
    //

    /**
     * {@inheritDoc}
     *
     * @return The normalized vector of this vector
     */
    @Nonnull
    @Override
    public Float3 normalize() {
        final float n = norm();
        return new Float3(x / n, y / n, z / n);
    }

    //
    // Negation
    //

    /**
     * {@inheritDoc}
     *
     * @return The negation of this vector
     */
    @Nonnull
    @Override
    public Float3 negate() {
        return new Float3(-x, -y, -z);
    }

    //
    // Transformation
    //

    /**
     * {@inheritDoc}
     *
     * @param transformer The function to apply to each component of this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public Float3 transform(@Nonnull UnaryOperator<Float> transformer) {
        return new Float3(transformer.apply(x), transformer.apply(y), transformer.apply(z));
    }

    //
    // Clamping
    //

    /**
     * {@inheritDoc}
     *
     * @param v The vector to compare to
     * @return The minimum vector
     */
    @Nonnull
    @Override
    public Float3 min(@Nonnull Float3 v) {
        return new Float3(Math.min(x, v.x), Math.min(y, v.y), Math.min(z, v.z));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to compare to
     * @return The maximum vector
     */
    @Nonnull
    @Override
    public Float3 max(@Nonnull Float3 v) {
        return new Float3(Math.max(x, v.x), Math.max(y, v.y), Math.max(z, v.z));
    }

    /**
     * {@inheritDoc}
     *
     * @param min The minimum allowed vector value
     * @param max The maximum allowed vector value
     * @return The clamped vector
     */
    @Nonnull
    @Override
    public Float3 clamp(@Nonnull Float3 min, @Nonnull Float3 max) {
        return new Float3(
                Numbers.clamp(x, min.x, max.x),
                Numbers.clamp(y, min.y, max.y),
                Numbers.clamp(z, min.z, max.z)
        );
    }

    //
    // Numeric Conversion
    //

    /**
     * {@inheritDoc}
     *
     * @return The {@code long} value of this vector
     */
    @Override
    public int intValue() {
        return (int) longValue();
    }

    /**
     * {@inheritDoc}
     *
     * @return The {@code long} value of this vector
     */
    @Override
    public long longValue() {
        return (long) norm();
    }

    /**
     * {@inheritDoc}
     *
     * @return The {@code float} value of this vector
     */
    @Override
    public float floatValue() {
        return (float) doubleValue();
    }

    /**
     * {@inheritDoc}
     *
     * @return The {@code double} value of this vector
     */
    @Override
    public double doubleValue() {
        return norm();
    }

    //
    // Rotation
    //

    /**
     * Converts this vector into a pure quaternion.
     *
     * @return A quaternion with the values {@code {0, x, y, z}}
     */
    @Nonnull
    public Quaternion quaternion() {
        return new Quaternion(0, x, y, z);
    }

    /**
     * Rotates this vector by a rotation quaternion.
     *
     * @param rq The rotation quaternion to apply
     * @return The rotated vector
     */
    @Nonnull
    public Float3 rotate(@Nonnull Quaternion rq) {
        return new Float3(rq.multiply(quaternion()).multiply(rq.conjugate()).vector());
    }

    //
    // Miscellaneous
    //

    /**
     * {@inheritDoc}
     *
     * @return The deep copy of this vector
     */
    @Nonnull
    @Override
    public Float3 copy() {
        return new Float3(this);
    }

    //
    // Equality
    //

    /**
     * {@inheritDoc}
     *
     * @param obj The object to compare to
     * @return {@code true} if the other object is a {@link Float3}, and the component values are equal
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof Float3 v)) return false;
        return x == v.x && y == v.y && z == v.z;
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to compare to
     * @return {@code true} if all components are equal
     */
    @Override
    public boolean equals(@Nonnull Float3 v) {
        return x == v.x && y == v.y && z == v.z;
    }

    //
    // Comparison
    //

    /**
     * {@inheritDoc}
     *
     * @param v the object to be compared.
     * @return {@code 0} if the Euclidean magnitudes are equal, {@code 1} if this vector's is larger,
     * {@code -1} otherwise
     */
    @Override
    public int compareTo(@Nonnull Float3 v) {
        return Double.compare(norm2(), v.norm2());
    }

    //
    // Serialization
    //

    /**
     * Deserializes a string into a {@link Float3}.
     *
     * @param input The input to deserialize
     * @return The parsed vector
     * @throws NumberFormatException    When the format is invalid
     * @throws IllegalArgumentException When at least one of the component scalars is non-finite
     */
    @Nonnull
    public static Float3 parseVector(@Nonnull String input) throws IllegalArgumentException {
        if (!input.startsWith("Float3{")) {
            throw new NumberFormatException("The provided string does not represent a Float3.");
        }

        final String cleanInput = input.replaceAll("Float3\\{|}", "");
        final String[] valueStrings = cleanInput.split(", ");
        final float[] values = new float[3];

        for (final String valueString : valueStrings) {
            final String[] split = valueString.split("=");
            values[switch (split[0]) {
                case "x" -> 0;
                case "y" -> 1;
                case "z" -> 2;
                default -> throw new NumberFormatException("The provided string does not represent a Float3.");
            }] = Float.parseFloat(split[1]);
        }

        return new Float3(values);
    }

    /**
     * {@inheritDoc}
     *
     * @return The string representation of this vector
     */
    @Override
    @Nonnull
    public String toString() {
        return "Float3{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
