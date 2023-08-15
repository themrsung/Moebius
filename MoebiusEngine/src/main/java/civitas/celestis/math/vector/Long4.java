package civitas.celestis.math.vector;

import civitas.celestis.math.Numbers;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.function.UnaryOperator;

/**
 * An immutable four-dimensional vector which uses {@code long}s to represent its components.
 */
public class Long4 extends Number implements LongVector<Long4> {
    //
    // Constants
    //

    /**
     * Absolute zero. This vector represents origin.
     */
    public static final Long4 ZERO = new Long4(0, 0, 0, 0);

    /**
     * The positive W unit vector.
     */
    public static final Long4 POSITIVE_W = new Long4(1, 0, 0, 0);

    /**
     * The positive X unit vector.
     */
    public static final Long4 POSITIVE_X = new Long4(0, 1, 0, 0);

    /**
     * The positive Y unit vector.
     */
    public static final Long4 POSITIVE_Y = new Long4(0, 0, 1, 0);

    /**
     * The positive Z unit vector.
     */
    public static final Long4 POSITIVE_Z = new Long4(0, 0, 0, 1);

    /**
     * The negative W unit vector.
     */
    public static final Long4 NEGATIVE_W = new Long4(-1, 0, 0, 0);

    /**
     * The negative X unit vector.
     */
    public static final Long4 NEGATIVE_X = new Long4(0, -1, 0, 0);

    /**
     * The negative Y unit vector.
     */
    public static final Long4 NEGATIVE_Y = new Long4(0, 0, -1, 0);

    /**
     * The negative Z unit vector.
     */
    public static final Long4 NEGATIVE_Z = new Long4(0, 0, 0, -1);

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
    public Long4(long w, long x, long y, long z) {
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Creates a new vector.
     *
     * @param values An array of length {@code 3} which contains the component os this vector in WXYZ order
     */
    public Long4(@Nonnull long[] values) {
        if (values.length != 4) {
            throw new IllegalArgumentException("The provided array does not have a length of 4.");
        }

        this.w = values[0];
        this.x = values[1];
        this.y = values[2];
        this.z = values[3];
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     */
    public Long4(@Nonnull Long4 v) {
        this.w = v.w;
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     */
    public Long4(@Nonnull Vector4 v) {
        this.w = (long) v.w;
        this.x = (long) v.x;
        this.y = (long) v.y;
        this.z = (long) v.z;
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     */
    public Long4(@Nonnull Float4 v) {
        this.w = (long) v.w;
        this.x = (long) v.x;
        this.y = (long) v.y;
        this.z = (long) v.z;
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     */
    public Long4(@Nonnull Int4 v) {
        this.w = v.w;
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
    protected final long w;

    /**
     * The X component of this vector.
     */
    protected final long x;

    /**
     * The Y component of this vector.
     */
    protected final long y;

    /**
     * The Z component of this vector
     */
    protected final long z;

    //
    // Getters
    //

    /**
     * Returns the W component of this vector.
     *
     * @return The W component of this vector
     */
    public final long w() {
        return w;
    }

    /**
     * Returns the X component of this vector.
     *
     * @return The X component of this vector
     */
    public final long x() {
        return x;
    }

    /**
     * Returns the Y component of this vector.
     *
     * @return The Y component of this vector
     */
    public final long y() {
        return y;
    }

    /**
     * Returns the Z component of this vector.
     *
     * @return The Z component of this vector
     */
    public final long z() {
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
        return w == 0 && x == 0 && y == 0 && z == 0;
    }

    /**
     * {@inheritDoc}
     *
     * @return The array representation of this vector
     */
    @Nonnull
    @Override
    public final long[] values() {
        return new long[]{w, x, y, z};
    }

    /**
     * {@inheritDoc}
     *
     * @return The magnitude of this vector
     */
    @Override
    public final double norm() {
        return Math.sqrt(w * w + x * x + y * y + z * z);
    }

    /**
     * {@inheritDoc}
     *
     * @return The squared magnitude of this vector
     */
    @Override
    public final double norm2() {
        return w * w + x * x + y * y + z * z;
    }

    /**
     * {@inheritDoc}
     *
     * @return The Manhattan magnitude of this vector
     */
    @Override
    public final long normManhattan() {
        return Math.abs(w) + Math.abs(x) + Math.abs(y) + Math.abs(z);
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
    public Long4 add(long s) {
        return new Long4(w + s, x + s, y + s, z + s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public Long4 subtract(long s) {
        return new Long4(w - s, x - s, y - s, z - s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to multiply to this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public Long4 multiply(long s) {
        return new Long4(w * s, x * s, y * s, z * s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to divide this vector by
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public Long4 divide(long s) {
        return new Long4(w - s, x - s, y - s, z - s);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to add to this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public Long4 add(@Nonnull Long4 v) {
        return new Long4(w + v.w, x + v.x, y + v.y, z + v.z);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public Long4 subtract(@Nonnull Long4 v) {
        return new Long4(w - v.w, x - v.x, y - v.y, z - v.z);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the dot product between
     * @return The dot product of the two vectors
     */
    @Override
    public long dot(@Nonnull Long4 v) {
        return w * v.w + x * v.x + y * v.y + z * v.z;
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
    public double distance(@Nonnull Long4 v) {
        final double dw = w - v.w;
        final double dx = x - v.x;
        final double dy = y - v.y;
        final double dz = z - v.z;

        return Math.sqrt(dw * dw + dx * dx + dy * dy + dz * dz);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the squared distance between
     * @return The squared Euclidean distance between this vector and the provided vector {@code v}
     */
    @Override
    public double distance2(@Nonnull Long4 v) {
        final double dw = w - v.w;
        final double dx = x - v.x;
        final double dy = y - v.y;
        final double dz = z - v.z;

        return dw * dw + dx * dx + dy * dy + dz * dz;
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the Manhattan distance between
     * @return The Manhattan distance between this vector and the provided vector {@code v}
     */
    @Override
    public long distanceManhattan(@Nonnull Long4 v) {
        final long dw = Math.abs(w - v.w);
        final long dx = Math.abs(x - v.x);
        final long dy = Math.abs(y - v.y);
        final long dz = Math.abs(z - v.z);

        return dw + dx + dy + dz;
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
    public Vector4 normalize() {
        final double n = norm();
        return new Vector4(w / n, x / n, y / n, z / n);
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
    public Long4 negate() {
        return new Long4(-w, -x, -y, -z);
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
    public Long4 transform(@Nonnull UnaryOperator<Long> transformer) {
        return new Long4(transformer.apply(w), transformer.apply(x), transformer.apply(y), transformer.apply(z));
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
    public Long4 min(@Nonnull Long4 v) {
        return new Long4(Math.min(w, v.w), Math.min(x, v.x), Math.min(y, v.y), Math.min(z, v.z));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to compare to
     * @return The maximum vector
     */
    @Nonnull
    @Override
    public Long4 max(@Nonnull Long4 v) {
        return new Long4(Math.max(w, v.w), Math.max(x, v.x), Math.max(y, v.y), Math.max(z, v.z));
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
    public Long4 clamp(@Nonnull Long4 min, @Nonnull Long4 max) {
        return new Long4(
                Numbers.clamp(w, min.w, max.w),
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
    // Miscellaneous
    //

    /**
     * {@inheritDoc}
     *
     * @return The deep copy of this vector
     */
    @Nonnull
    @Override
    public Long4 copy() {
        return new Long4(this);
    }

    //
    // Equality
    //

    /**
     * {@inheritDoc}
     *
     * @param obj The object to compare to
     * @return {@code true} if the other object is a {@link Long4}, and the component values are equal
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof Long4 v)) return false;
        return w == v.w && x == v.x && y == v.y && z == v.z;
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to compare to
     * @return {@code true} if all components are equal
     */
    @Override
    public boolean equals(@Nonnull Long4 v) {
        return w == v.w && x == v.x && y == v.y && z == v.z;
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
    public int compareTo(@Nonnull Long4 v) {
        return Double.compare(norm2(), v.norm2());
    }

    //
    // Serialization
    //

    /**
     * Deserializes a string into a {@link Long4}.
     *
     * @param input The input to deserialize
     * @return The parsed vector
     * @throws NumberFormatException When the format is invalid
     */
    @Nonnull
    public static Long4 parseVector(@Nonnull String input) throws NumberFormatException {
        if (!input.startsWith("Long4{")) {
            throw new NumberFormatException("The provided string does not represent a Long4.");
        }

        final String cleanInput = input.replaceAll("Long4\\{|}", "");
        final String[] valueStrings = cleanInput.split(", ");
        final long[] values = new long[4];

        for (final String valueString : valueStrings) {
            final String[] split = valueString.split("=");
            values[switch (split[0]) {
                case "w" -> 0;
                case "x" -> 1;
                case "y" -> 2;
                case "z" -> 3;
                default -> throw new NumberFormatException("The provided string does not represent a Long4.");
            }] = Long.parseLong(split[1]);
        }

        return new Long4(values);
    }

    /**
     * {@inheritDoc}
     *
     * @return The string representation of this vector
     */
    @Override
    @Nonnull
    public String toString() {
        return "Long4{" +
                "w=" + w +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
