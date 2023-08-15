package civitas.celestis.math.vector;

import civitas.celestis.math.Numbers;
import civitas.celestis.util.packing.Packable;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.function.UnaryOperator;

/**
 * An immutable two-dimensional vector which uses {@code float}s to represent its components.
 * <p>
 * 2D {@code float} vectors can be packed into a single {@code long} for easier storage.
 * No precision is lost in the process.
 * </p>
 */
public class Float2 extends Number implements FloatVector<Float2>, Packable {
    //
    // Constants
    //

    /**
     * Absolute zero. This vector represents origin.
     */
    public static final Float2 ZERO = new Float2(0, 0);

    /**
     * The positive X unit vector.
     */
    public static final Float2 POSITIVE_X = new Float2(1, 0);

    /**
     * The positive Y unit vector.
     */
    public static final Float2 POSITIVE_Y = new Float2(0, 1);

    /**
     * The positive Z unit vector.
     */
    public static final Float2 POSITIVE_Z = new Float2(0, 0);

    /**
     * The negative X unit vector.
     */
    public static final Float2 NEGATIVE_X = new Float2(-1, 0);

    /**
     * The negative Y unit vector.
     */
    public static final Float2 NEGATIVE_Y = new Float2(0, -1);

    //
    // Constructors
    //

    /**
     * Creates a new vector.
     *
     * @param x The X component of this vector
     * @param y The Y component of this vector
     */
    public Float2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Creates a new vector.
     *
     * @param values An array of length {@code 2} which contains the component os this vector in XY order
     */
    public Float2(@Nonnull float[] values) {
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
    public Float2(@Nonnull Float2 v) {
        this.x = v.x;
        this.y = v.y;
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     */
    public Float2(@Nonnull Vector2 v) {
        this.x = (float) v.x;
        this.y = (float) v.y;
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     */
    public Float2(@Nonnull Int2 v) {
        this.x = v.x;
        this.y = v.y;
    }

    //
    // Packing
    //

    /**
     * Unpacks a packed value of a {@link Float2}.
     * @param packed The packed {@code long}
     * @return The unpacked vector
     * @see Float2#pack()
     */
    @Nonnull
    public static Float2 unpack(long packed) {
        final int xBits = (int) (packed >> 32);
        final int yBits = (int) packed;

        return new Float2(Float.intBitsToFloat(xBits), Float.intBitsToFloat(yBits));
    }

    /**
     * Packs this vector into a single {@code long} for easier storage.
     * Since both formats have 64 bits, no precision is lost.
     *
     * @return The packed {@code long}
     * @see Float2#unpack(long) 
     */
    @Override
    public long pack() {
        final int xBits = Float.floatToIntBits(x);
        final int yBits = Float.floatToIntBits(y);

        return ((long) xBits << 32) | (yBits & 0xFFFFFFFFL);
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
     * @return {@code true} if this vector is not a number
     */
    @Override
    public final boolean isNaN() {
        return Double.isNaN(x) || Double.isNaN(y);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@code true} if this vector is finite
     */
    @Override
    public final boolean isFinite() {
        return Double.isFinite(x) && Double.isFinite(y);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@code true} if this vector is infinite
     */
    @Override
    public final boolean isInfinite() {
        return Double.isInfinite(x) || Double.isInfinite(y);
    }

    /**
     * {@inheritDoc}
     *
     * @return The array representation of this vector
     */
    @Nonnull
    @Override
    public final float[] values() {
        return new float[]{x, y};
    }

    /**
     * {@inheritDoc}
     *
     * @return The magnitude of this vector
     */
    @Override
    public final float norm() {
        return (float) Math.sqrt(x * x + y * y);
    }

    /**
     * {@inheritDoc}
     *
     * @return The squared magnitude of this vector
     */
    @Override
    public final float norm2() {
        return x * x + y * y;
    }

    /**
     * {@inheritDoc}
     *
     * @return The Manhattan magnitude of this vector
     */
    @Override
    public final float normManhattan() {
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
    public Float2 add(float s) {
        return new Float2(x + s, y + s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public Float2 subtract(float s) {
        return new Float2(x - s, y - s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to multiply to this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public Float2 multiply(float s) {
        return new Float2(x * s, y * s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to divide this vector by
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public Float2 divide(float s) {
        return new Float2(x - s, y - s);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to add to this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public Float2 add(@Nonnull Float2 v) {
        return new Float2(x + v.x, y + v.y);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public Float2 subtract(@Nonnull Float2 v) {
        return new Float2(x - v.x, y - v.y);
    }

    /**
     * Multiplies this vector by another vector using complex number multiplication.
     *
     * @param v The vector to multiply this vector with
     * @return The resulting vector
     */
    @Nonnull
    public Float2 multiply(@Nonnull Float2 v) {
        return new Float2(x * v.x - y * v.y, x * v.y + y * v.x);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the dot product between
     * @return The dot product of the two vectors
     */
    @Override
    public final float dot(@Nonnull Float2 v) {
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
    public float distance(@Nonnull Float2 v) {
        final float dx = x - v.x;
        final float dy = y - v.y;

        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the squared distance between
     * @return The squared Euclidean distance between this vector and the provided vector {@code v}
     */
    @Override
    public float distance2(@Nonnull Float2 v) {
        final float dx = x - v.x;
        final float dy = y - v.y;

        return dx * dx + dy * dy;
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the Manhattan distance between
     * @return The Manhattan distance between this vector and the provided vector {@code v}
     */
    @Override
    public float distanceManhattan(@Nonnull Float2 v) {
        final float dx = Math.abs(x - v.x);
        final float dy = Math.abs(y - v.y);

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
    public Float2 normalize() {
        final float n = norm();
        return new Float2(x / n, y / n);
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
    public Float2 negate() {
        return new Float2(-x, -y);
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
    public Float2 transform(@Nonnull UnaryOperator<Float> transformer) {
        return new Float2(transformer.apply(x), transformer.apply(y));
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
    public Float2 min(@Nonnull Float2 v) {
        return new Float2(Math.min(x, v.x), Math.min(y, v.y));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to compare to
     * @return The maximum vector
     */
    @Nonnull
    @Override
    public Float2 max(@Nonnull Float2 v) {
        return new Float2(Math.max(x, v.x), Math.max(y, v.y));
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
    public Float2 clamp(@Nonnull Float2 min, @Nonnull Float2 max) {
        return new Float2(
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
    public Float2 rotate(float angRads) {
        final float cos = (float) Math.cos(angRads);
        final float sin = (float) Math.sin(angRads);

        return new Float2(x * cos - y * sin, x * sin + y * cos);
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
    public Float2 copy() {
        return new Float2(this);
    }

    //
    // Equality
    //

    /**
     * {@inheritDoc}
     *
     * @param obj The object to compare to
     * @return {@code true} if the other object is a {@link Float2}, and the component values are equal
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof Float2 v)) return false;
        return x == v.x && y == v.y;
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to compare to
     * @return {@code true} if all components are equal
     */
    @Override
    public boolean equals(@Nonnull Float2 v) {
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
    public int compareTo(@Nonnull Float2 v) {
        return Double.compare(norm2(), v.norm2());
    }

    //
    // Serialization
    //

    /**
     * Deserializes a string into a {@link Float2}.
     *
     * @param input The input to deserialize
     * @return The parsed vector
     * @throws NumberFormatException    When the format is invalid
     * @throws IllegalArgumentException When at least one of the component scalars is non-finite
     */
    @Nonnull
    public static Float2 parseVector(@Nonnull String input) throws IllegalArgumentException {
        if (!input.startsWith("Float2{")) {
            throw new NumberFormatException("The provided string does not represent a Float2.");
        }

        final String cleanInput = input.replaceAll("Float2\\{|}", "");
        final String[] valueStrings = cleanInput.split(", ");
        final float[] values = new float[2];

        for (final String valueString : valueStrings) {
            final String[] split = valueString.split("=");
            values[switch (split[0]) {
                case "x" -> 0;
                case "y" -> 1;
                default -> throw new NumberFormatException("The provided string does not represent a Float2.");
            }] = Float.parseFloat(split[1]);
        }

        return new Float2(values);
    }

    /**
     * {@inheritDoc}
     *
     * @return The string representation of this vector
     */
    @Override
    @Nonnull
    public String toString() {
        return "Float2{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
