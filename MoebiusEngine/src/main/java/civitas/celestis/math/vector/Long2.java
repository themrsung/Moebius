package civitas.celestis.math.vector;

import civitas.celestis.math.Numbers;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;

/**
 * A two-dimensional vector which uses the type {@code long}.
 */
public class Long2 implements LongVector<Long2, Double2> {
    //
    // Constants
    //

    /**
     * A vector with no direction or magnitude. Represents origin.
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
     * @param values The array of values to use
     * @throws IndexOutOfBoundsException When the provided array's length is too small
     */
    public Long2(@Nonnull long[] values) {
        this.x = values[0];
        this.y = values[1];
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     * @throws IndexOutOfBoundsException When the provided vector's length is too small
     */
    public Long2(@Nonnull LongVector<?, ?> v) {
        final long[] array = v.array();

        this.x = array[0];
        this.y = array[1];
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
    public Long2(@Nonnull Double2 v) {
        this.x = (long) v.x;
        this.y = (long) v.y;
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
     * @param v The vector of which to copy component values from
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

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public List<Long> list() {
        return List.of(x, y);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public long[] array() {
        return new long[]{x, y};
    }

    //
    // Properties
    //

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public boolean isZero() {
        return x == 0 && y == 0;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public double norm() {
        return Math.sqrt(x * x + y * y);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public long norm2() {
        return x * x + y * y;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public long normManhattan() {
        return Math.abs(x) + Math.abs(y);
    }

    //
    // Arithmetic
    //

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to add to this vector
     * @return {@inheritDoc}
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
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Long2 subtract(long s) {
        return new Long2(x - s, y - s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to multiply this vector by
     * @return {@inheritDoc}
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
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Long2 divide(long s) {
        return new Long2(x / s, y / s);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to add to this vector
     * @return {@inheritDoc}
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
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Long2 subtract(@Nonnull Long2 v) {
        return new Long2(x - v.x, y - v.y);
    }

    /**
     * Multiplies this vector by another vector using complex number multiplication.
     *
     * @param v The vector to multiply this vector by
     * @return The complex number product between the two vectors
     */
    @Nonnull
    public Long2 multiply(@Nonnull Long2 v) {
        return new Long2(x * v.x - y * v.y, x * v.y + y * v.x);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the dot product between
     * @return {@inheritDoc}
     */
    @Override
    public long dot(@Nonnull Long2 v) {
        return x * v.x + y * v.y;
    }

    //
    // Negation
    //

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Long2 negate() {
        return new Long2(-x, -y);
    }

    //
    // Distance
    //

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the distance to
     * @return {@inheritDoc}
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
     * @param v The vector of which to get the squared distance to
     * @return {@inheritDoc}
     */
    @Override
    public long distance2(@Nonnull Long2 v) {
        final long dx = x - v.x;
        final long dy = y - v.y;

        return dx * dx + dy * dy;
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the distance to
     * @return {@inheritDoc}
     */
    @Override
    public long distanceManhattan(@Nonnull Long2 v) {
        final long dx = x - v.x;
        final long dy = y - v.y;

        return Math.abs(dx) + Math.abs(dy);
    }

    //
    // Normalization
    //

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Double2 normalize() {
        final double n = norm();

        if (n == 0) {
            throw new ArithmeticException("Cannot normalize a vector whose magnitude is zero.");
        }

        return new Double2(x / n, y / n);
    }


    //
    // Transformation
    //

    /**
     * {@inheritDoc}
     *
     * @param f The function to apply to each component of this vector
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Long2 transform(@Nonnull UnaryOperator<Long> f) {
        return new Long2(f.apply(x), f.apply(y));
    }

    //
    // Clamping
    //

    /**
     * {@inheritDoc}
     *
     * @param v The boundary vector of which to compare to
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Long2 min(@Nonnull Long2 v) {
        return new Long2(Math.min(x, v.x), Math.min(y, v.y));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The boundary vector of which to compare to
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Long2 max(@Nonnull Long2 v) {
        return new Long2(Math.max(x, v.x), Math.max(y, v.y));
    }

    /**
     * {@inheritDoc}
     *
     * @param min The minimum boundary vector
     * @param max The maximum boundary vector
     * @return {@inheritDoc}
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
    // Rotation
    //

    /**
     * Rotates this vector counter-clockwise by given angle.
     *
     * @param angRads The angle of rotation to apply in radians
     */
    @Nonnull
    public Long2 rotate(long angRads) {
        double cos = Math.cos(angRads);
        double sin = Math.sin(angRads);

        return new Long2(
                (long) (cos * x - sin * y),
                (long) (sin * x + cos * y)
        );
    }

    //
    // Equality
    //

    /**
     * {@inheritDoc}
     *
     * @param obj The object to compare to
     * @return {@inheritDoc}
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Long2 lv2) {
            return x == lv2.x && y == lv2.y;
        }

        if (obj instanceof LongVector<?, ?> lv) {
            final long[] array = lv.array();
            return Arrays.equals(array(), array);
        }

        if (obj instanceof Vector<?, ?> v) {
            final List<Long> l1 = list();
            final List<? extends Number> l2 = v.list();

            if (l1.size() != l2.size()) return false;

            for (int i = 0; i < l2.size(); i++) {
                if (l1.get(i).equals(l2.get(i))) return false;
            }

            return true;
        }

        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to compare to
     * @return {@inheritDoc}
     */
    @Override
    public boolean equals(@Nonnull Long2 v) {
        return x == v.x && y == v.y;
    }

    //
    // Serialization
    //

    /**
     * Deserializes a string into a vector.
     *
     * @param input The input string to parse
     * @return The parsed vector
     * @throws NumberFormatException When the format is invalid
     */
    @Nonnull
    public static Long2 parseVector(@Nonnull String input) throws NumberFormatException {
        if (!input.startsWith("Vector{")) {
            throw new NumberFormatException("The provided string is not a vector.");
        }

        final String[] valueStrings = input.replaceAll("Vector\\{|}", "").split(", ");
        if (valueStrings.length != 2) {
            throw new NumberFormatException("The provided string does not have two components.");
        }

        final long[] values = new long[2];

        for (final String valueString : valueStrings) {
            final String[] split = valueString.split("=");
            if (split.length != 2) {
                throw new NumberFormatException("The format is invalid.");
            }

            values[switch (split[0]) {
                case "x" -> 0;
                case "y" -> 1;
                default -> throw new NumberFormatException("The provided string has a non-XY component.");
            }] = Long.parseLong(split[1]);
        }

        return new Long2(values);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    @Nonnull
    public String toString() {
        return "Vector{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
