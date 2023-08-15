package civitas.celestis.math.vector;

import civitas.celestis.math.Numbers;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.Iterator;
import java.util.function.UnaryOperator;

/**
 * An immutable two-dimensional vector which uses {@code float}s to represent its components.
 */
public class Int2 extends Number implements IntVector<Int2> {
    //
    // Constants
    //

    /**
     * Absolute zero. This vector represents origin.
     */
    public static final Int2 ZERO = new Int2(0, 0);

    /**
     * The positive X unit vector.
     */
    public static final Int2 POSITIVE_X = new Int2(1, 0);

    /**
     * The positive Y unit vector.
     */
    public static final Int2 POSITIVE_Y = new Int2(0, 1);

    /**
     * The positive Z unit vector.
     */
    public static final Int2 POSITIVE_Z = new Int2(0, 0);

    /**
     * The negative X unit vector.
     */
    public static final Int2 NEGATIVE_X = new Int2(-1, 0);

    /**
     * The negative Y unit vector.
     */
    public static final Int2 NEGATIVE_Y = new Int2(0, -1);

    //
    // Constructors
    //

    /**
     * Creates a new vector.
     *
     * @param x The X component of this vector
     * @param y The Y component of this vector
     */
    public Int2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Creates a new vector.
     *
     * @param values An array of length {@code 2} which contains the component os this vector in XY order
     */
    public Int2(@Nonnull int[] values) {
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
    public Int2(@Nonnull Int2 v) {
        this.x = v.x;
        this.y = v.y;
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     */
    public Int2(@Nonnull Float2 v) {
        this.x = (int) v.x;
        this.y = (int) v.y;
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     */
    public Int2(@Nonnull Vector2 v) {
        this.x = (int) v.x;
        this.y = (int) v.y;
    }

    //
    // Variables
    //

    /**
     * The X component of this vector.
     */
    protected final int x;

    /**
     * The Y component of this vector.
     */
    protected final int y;

    //
    // Getters
    //

    /**
     * Returns the X component of this vector.
     *
     * @return The X component of this vector
     */
    public final int x() {
        return x;
    }

    /**
     * Returns the Y component of this vector.
     *
     * @return The Y component of this vector
     */
    public final int y() {
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
    public final int[] values() {
        return new int[]{x, y};
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
    public Int2 add(int s) {
        return new Int2(x + s, y + s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public Int2 subtract(int s) {
        return new Int2(x - s, y - s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to multiply to this scalar
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public Int2 multiply(int s) {
        return new Int2(x * s, y * s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to divide this vector by
     * @return The resulting vector
     * @throws ArithmeticException When the denominator {@code s} is zero
     */
    @Nonnull
    @Override
    public Int2 divide(int s) {
        return new Int2(x - s, y - s);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to add to this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public Int2 add(@Nonnull Int2 v) {
        return new Int2(x + v.x, y + v.y);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public Int2 subtract(@Nonnull Int2 v) {
        return new Int2(x - v.x, y - v.y);
    }

    /**
     * Multiplies this vector by another vector using complex number multiplication.
     *
     * @param v The vector to multiply this vector with
     * @return The resulting vector
     */
    @Nonnull
    public Int2 multiply(@Nonnull Int2 v) {
        return new Int2(x * v.x - y * v.y, x * v.y + y * v.x);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the dot product between
     * @return The dot product of the two vectors
     */
    @Override
    public final int dot(@Nonnull Int2 v) {
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
    public float distance(@Nonnull Int2 v) {
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
    public float distance2(@Nonnull Int2 v) {
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
    public float distanceManhattan(@Nonnull Int2 v) {
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
    public Int2 negate() {
        return new Int2(-x, -y);
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
    public Int2 transform(@Nonnull UnaryOperator<Integer> transformer) {
        return new Int2(transformer.apply(x), transformer.apply(y));
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
    public Int2 min(@Nonnull Int2 v) {
        return new Int2(Math.min(x, v.x), Math.min(y, v.y));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to compare to
     * @return The maximum vector
     */
    @Nonnull
    @Override
    public Int2 max(@Nonnull Int2 v) {
        return new Int2(Math.max(x, v.x), Math.max(y, v.y));
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
    public Int2 clamp(@Nonnull Int2 min, @Nonnull Int2 max) {
        return new Int2(
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
    public Int2 rotate(float angRads) {
        final float cos = (int) Math.cos(angRads);
        final float sin = (int) Math.sin(angRads);

        return new Int2((int) (x * cos - y * sin), (int) (x * sin + y * cos));
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
    public Int2 copy() {
        return new Int2(this);
    }

    //
    // Equality
    //

    /**
     * {@inheritDoc}
     *
     * @param obj The object to compare to
     * @return {@code true} if the other object is a {@link Int2}, and the component values are equal
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof Int2 v)) return false;
        return x == v.x && y == v.y;
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to compare to
     * @return {@code true} if all components are equal
     */
    @Override
    public boolean equals(@Nonnull Int2 v) {
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
    public int compareTo(@Nonnull Int2 v) {
        return Double.compare(norm2(), v.norm2());
    }

    //
    // Serialization
    //

    /**
     * Deserializes a string into a {@link Int2}.
     *
     * @param input The input to deserialize
     * @return The parsed vector
     * @throws NumberFormatException    When the format is invalid
     * @throws IllegalArgumentException When at least one of the component scalars is non-finite
     */
    @Nonnull
    public static Int2 parseVector(@Nonnull String input) throws IllegalArgumentException {
        if (!input.startsWith("Int2{")) {
            throw new NumberFormatException("The provided string does not represent a Int2.");
        }

        final String cleanInput = input.replaceAll("Int2\\{|}", "");
        final String[] valueStrings = cleanInput.split(", ");
        final int[] values = new int[2];

        for (final String valueString : valueStrings) {
            final String[] split = valueString.split("=");
            values[switch (split[0]) {
                case "x" -> 0;
                case "y" -> 1;
                default -> throw new NumberFormatException("The provided string does not represent a Int2.");
            }] = Integer.parseInt(split[1]);
        }

        return new Int2(values);
    }

    /**
     * {@inheritDoc}
     *
     * @return The string representation of this vector
     */
    @Override
    @Nonnull
    public String toString() {
        return "Int2{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
