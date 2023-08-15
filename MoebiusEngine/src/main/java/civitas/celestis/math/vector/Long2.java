package civitas.celestis.math.vector;

import civitas.celestis.math.Numbers;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.function.UnaryOperator;

/**
 * An immutable two-dimensional vector which uses {@code double}s to represent its components.
 */
public class Long2 extends Number implements LongVector<Long2> {
    //
    // Constants
    //

    /**
     * Absolute zero. This vector represents origin.
     */
    public static final Long2 ZERO = new Long2(0, 0);

    /**
     * The positive X unit vector.
     */
    public static final Long2 POSITIVE_X = new Long2(1, 0);

    /**
     * The positive Y unit vector.
     */
    public static final Long2 POSITIVE_Y = new Long2(0, 1);

    /**
     * The positive Z unit vector.
     */
    public static final Long2 POSITIVE_Z = new Long2(0, 0);

    /**
     * The negative X unit vector.
     */
    public static final Long2 NEGATIVE_X = new Long2(-1, 0);

    /**
     * The negative Y unit vector.
     */
    public static final Long2 NEGATIVE_Y = new Long2(0, -1);

    //
    // Constructors
    //

    /**
     * Creates a new vector.
     *
     * @param x The X component of this vector
     * @param y The Y component of this vector
     */
    public Long2(long x, long y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Creates a new vector.
     *
     * @param values An array of length {@code 2} which contains the component os this vector in XY order
     */
    public Long2(@Nonnull long[] values) {
        if (values.length != 2) {
            throw new IllegalArgumentException("The provided array does not have a length of 2.");
        }

        this.x = values[0];
        this.y = values[1];
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     */
    public Long2(@Nonnull Vector2 v) {
        this.x = (long) v.x;
        this.y = (long) v.y;
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     */
    public Long2(@Nonnull Long2 v) {
        this.x = v.x;
        this.y = v.y;
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     */
    public Long2(@Nonnull Float2 v) {
        this.x = (long) v.x;
        this.y = (long) v.y;
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector from which to copy component values from
     */
    public Long2(@Nonnull Int2 v) {
        this.x = v.x;
        this.y = v.y;
    }

    //
    // Variables
    //

    /**
     * The X component of this vector.
     */
    protected final long x;

    /**
     * The Y component of this vector.
     */
    protected final long y;

    //
    // Getters
    //

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
        return x == 0 && y == 0;
    }

    /**
     * {@inheritDoc}
     *
     * @return The array representation of this vector
     */
    @Nonnull
    @Override
    public final long[] values() {
        return new long[]{x, y};
    }

    /**
     * {@inheritDoc}
     *
     * @return The magnitude of this vector
     */
    @Override
    public final double norm() {
        return Math.sqrt(x * x + y * y);
    }

    /**
     * {@inheritDoc}
     *
     * @return The squared magnitude of this vector
     */
    @Override
    public final double norm2() {
        return x * x + y * y;
    }

    /**
     * {@inheritDoc}
     *
     * @return The Manhattan magnitude of this vector
     */
    @Override
    public final long normManhattan() {
        return Math.abs(x) + Math.abs(y);
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
    public Long2 add(long s) {
        return new Long2(x + s, y + s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public Long2 subtract(long s) {
        return new Long2(x - s, y - s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to multiply to this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public Long2 multiply(long s) {
        return new Long2(x * s, y * s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to divide this vector by
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public Long2 divide(long s) {
        return new Long2(x - s, y - s);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to add to this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public Long2 add(@Nonnull Long2 v) {
        return new Long2(x + v.x, y + v.y);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public Long2 subtract(@Nonnull Long2 v) {
        return new Long2(x - v.x, y - v.y);
    }

    /**
     * Multiplies this vector by another vector using complex number multiplication.
     *
     * @param v The vector to multiply this vector with
     * @return The resulting vector
     */
    @Nonnull
    public Long2 multiply(@Nonnull Long2 v) {
        return new Long2(x * v.x - y * v.y, x * v.y + y * v.x);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the dot product between
     * @return The dot product of the two vectors
     */
    @Override
    public final long dot(@Nonnull Long2 v) {
        return x * v.x + y * v.y;
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
    public double distance(@Nonnull Long2 v) {
        final double dx = x - v.x;
        final double dy = y - v.y;

        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the squared distance between
     * @return The squared Euclidean distance between this vector and the provided vector {@code v}
     */
    @Override
    public double distance2(@Nonnull Long2 v) {
        final double dx = x - v.x;
        final double dy = y - v.y;

        return dx * dx + dy * dy;
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the Manhattan distance between
     * @return The Manhattan distance between this vector and the provided vector {@code v}
     */
    @Override
    public long distanceManhattan(@Nonnull Long2 v) {
        final long dx = Math.abs(x - v.x);
        final long dy = Math.abs(y - v.y);

        return dx + dy;
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
    public Vector2 normalize() {
        final double n = norm();
        return new Vector2(x / n, y / n);
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
    public Long2 negate() {
        return new Long2(-x, -y);
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
    public Long2 transform(@Nonnull UnaryOperator<Long> transformer) {
        return new Long2(transformer.apply(x), transformer.apply(y));
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
    public Long2 min(@Nonnull Long2 v) {
        return new Long2(Math.min(x, v.x), Math.min(y, v.y));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to compare to
     * @return The maximum vector
     */
    @Nonnull
    @Override
    public Long2 max(@Nonnull Long2 v) {
        return new Long2(Math.max(x, v.x), Math.max(y, v.y));
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
    public Long2 clamp(@Nonnull Long2 min, @Nonnull Long2 max) {
        return new Long2(
                Numbers.clamp(x, min.x, max.x),
                Numbers.clamp(y, min.y, max.y)
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
     * Rotates this vector counter-clockwise by given angle.
     *
     * @param angRads Angle in radians to rotate this vector by
     * @return The rotated vector
     */
    @Nonnull
    public Long2 rotate(double angRads) {
        final double cos = Math.cos(angRads);
        final double sin = Math.sin(angRads);

        return new Long2((long) (x * cos - y * sin), (long) (x * sin + y * cos));
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
    public Long2 copy() {
        return new Long2(this);
    }

    //
    // Equality
    //

    /**
     * {@inheritDoc}
     *
     * @param obj The object to compare to
     * @return {@code true} if the other object is a {@link Long2}, and the component values are equal
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof Long2 v)) return false;
        return x == v.x && y == v.y;
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to compare to
     * @return {@code true} if all components are equal
     */
    @Override
    public boolean equals(@Nonnull Long2 v) {
        return x == v.x && y == v.y;
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
    public int compareTo(@Nonnull Long2 v) {
        return Double.compare(norm2(), v.norm2());
    }

    //
    // Serialization
    //

    /**
     * Deserializes a string into a {@link Long2}.
     *
     * @param input The input to deserialize
     * @return The parsed vector
     * @throws NumberFormatException When the format is invalid
     */
    @Nonnull
    public static Long2 parseVector(@Nonnull String input) throws NumberFormatException {
        if (!input.startsWith("Long2{")) {
            throw new NumberFormatException("The provided string does not represent a Long2.");
        }

        final String cleanInput = input.replaceAll("Long2\\{|}", "");
        final String[] valueStrings = cleanInput.split(", ");
        final long[] values = new long[2];

        for (final String valueString : valueStrings) {
            final String[] split = valueString.split("=");
            values[switch (split[0]) {
                case "x" -> 0;
                case "y" -> 1;
                default -> throw new NumberFormatException("The provided string does not represent a Long2.");
            }] = Long.parseLong(split[1]);
        }

        return new Long2(values);
    }

    /**
     * {@inheritDoc}
     *
     * @return The string representation of this vector
     */
    @Override
    @Nonnull
    public String toString() {
        return "Long2{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
