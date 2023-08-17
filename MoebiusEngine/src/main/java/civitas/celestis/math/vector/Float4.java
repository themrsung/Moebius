package civitas.celestis.math.vector;

import civitas.celestis.math.Numbers;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * A four-dimensional vector which uses the type {@code float}.
 */
public class Float4 implements FloatVector<Float4> {
    //
    // Constants
    //

    /**
     * A vector with no direction or magnitude. Represents origin.
     */
    public static final Float4 ZERO = new Float4(0, 0, 0, 0);

    /**
     * The positive W unit vector.
     */
    public static final Float4 POSITIVE_W = new Float4(1, 0, 0, 0);

    /**
     * The positive X unit vector.
     */
    public static final Float4 POSITIVE_X = new Float4(0, 1, 0, 0);

    /**
     * The positive Y unit vector.
     */
    public static final Float4 POSITIVE_Y = new Float4(0, 0, 1, 0);

    /**
     * Ths positive Z unit vector.
     */
    public static final Float4 POSITIVE_Z = new Float4(0, 0, 0, 1);

    /**
     * The negative W unit vector.
     */
    public static final Float4 NEGATIVE_W = new Float4(-1, 0, 0, 0);

    /**
     * The negative X unit vector.
     */
    public static final Float4 NEGATIVE_X = new Float4(0, -1, 0, 0);

    /**
     * The negative Y unit vector.
     */
    public static final Float4 NEGATIVE_Y = new Float4(0, 0, -1, 0);

    /**
     * The negative Z unit vector.
     */
    public static final Float4 NEGATIVE_Z = new Float4(0, 0, 0, -1);

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
    public Float4(float w, float x, float y, float z) {
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Creates a new vector.
     *
     * @param values The array of values to use
     * @throws IndexOutOfBoundsException When the provided array's length is too small
     */
    public Float4(@Nonnull float[] values) {
        this.w = values[0];
        this.x = values[1];
        this.y = values[2];
        this.z = values[3];
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     * @throws IndexOutOfBoundsException When the provided vector's length is too small
     */
    public Float4(@Nonnull FloatVector<?> v) {
        final float[] array = v.array();

        this.w = array[0];
        this.x = array[1];
        this.y = array[2];
        this.z = array[3];
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     */
    public Float4(@Nonnull Float4 v) {
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
    public Float4(@Nonnull Double4 v) {
        this.w = (float) v.w;
        this.x = (float) v.x;
        this.y = (float) v.y;
        this.z = (float) v.z;
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     */
    public Float4(@Nonnull Long4 v) {
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
    public Float4(@Nonnull Int4 v) {
        this.w = v.w;
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
    }

    //
    // Variables
    //

    /**
     * The W component of this vector.
     */
    protected final float w;

    /**
     * The X component of this vector.
     */
    protected final float x;

    /**
     * The Y component of this vector.
     */
    protected final float y;

    /**
     * The Z component of this vector.
     */
    protected final float z;

    //
    // Getters
    //

    /**
     * Returns the W component of this vector.
     *
     * @return The W component of this vector
     */
    public final float w() {
        return w;
    }

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

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public List<Float> list() {
        return List.of(w, x, y, z);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public float[] array() {
        return new float[]{w, x, y, z};
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
        return w == 0 && x == 0 && y == 0 & z == 0;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public boolean isNaN() {
        return Float.isNaN(w) || Float.isNaN(x) || Float.isNaN(y) || Float.isNaN(z);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public boolean isFinite() {
        return Float.isFinite(w) && Float.isFinite(x) && Float.isFinite(y) && Float.isFinite(z);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public boolean isInfinite() {
        return Float.isInfinite(w) || Float.isInfinite(x) || Float.isInfinite(y) && Float.isInfinite(z);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public float norm() {
        return (float) Math.sqrt(w * w + x * x + y * y + z * z);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public float norm2() {
        return w * w + x * x + y * y + z * z;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public float normManhattan() {
        return Math.abs(w) + Math.abs(x) + Math.abs(y) + Math.abs(z);
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
    public Float4 add(float s) {
        return new Float4(w + s, x + s, y + s, z + s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to subtract from this vector
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Float4 subtract(float s) {
        return new Float4(w - s, x - s, y - s, z - s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to multiply this vector by
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Float4 multiply(float s) {
        return new Float4(w * s, x * s, y * s, z * s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to divide this vector by
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Float4 divide(float s) {
        return new Float4(w / s, x / s, y / s, z / s);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to add to this vector
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Float4 add(@Nonnull Float4 v) {
        return new Float4(w + v.w, x + v.x, y + v.y, z + v.z);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to subtract from this vector
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Float4 subtract(@Nonnull Float4 v) {
        return new Float4(w - v.w, x - v.x, y - v.y, z - v.z);
    }

    /**
     * Multiplies this vector by another vector using quaternion multiplication.
     *
     * @param v The vector to multiply this vector by
     * @return The quaternion left-product of the two vectors
     */
    @Nonnull
    public Float4 multiply(@Nonnull Float4 v) {
        return new Float4(
                w * v.w - x * v.x - y * v.y - z * v.z,
                w * v.x + x * v.w + y * v.z - z * v.y,
                w * v.y - x * v.z + y * v.w + z * v.x,
                w * v.z + x * v.y - y * v.x + z * v.w
        );
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the dot product between
     * @return {@inheritDoc}
     */
    @Override
    public float dot(@Nonnull Float4 v) {
        return w * v.w + x * v.x + y * v.y + z * v.z;
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
    public Float4 negate() {
        return new Float4(-w, -x, -y, -z);
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
    public float distance(@Nonnull Float4 v) {
        final float dw = w - v.w;
        final float dx = x - v.x;
        final float dy = y - v.y;
        final float dz = z - v.z;

        return (float) Math.sqrt(dw * dw + dx * dx + dy * dy + dz * dz);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the squared distance to
     * @return {@inheritDoc}
     */
    @Override
    public float distance2(@Nonnull Float4 v) {
        final float dw = w - v.w;
        final float dx = x - v.x;
        final float dy = y - v.y;
        final float dz = z - v.z;

        return dw * dw + dx * dx + dy * dy + dz * dz;
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the distance to
     * @return {@inheritDoc}
     */
    @Override
    public float distanceManhattan(@Nonnull Float4 v) {
        final float dw = w - v.w;
        final float dx = x - v.x;
        final float dy = y - v.y;
        final float dz = z - v.z;

        return Math.abs(dw) + Math.abs(dx) + Math.abs(dy) + Math.abs(dz);
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
    public Float4 normalize() {
        final float n = norm();

        if (n == 0) {
            throw new ArithmeticException("Cannot normalize a vector whose magnitude is zero.");
        }

        return new Float4(w / n, x / n, y / n, z / n);
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
    public Float4 transform(@Nonnull Function<? super Float, Float> f) {
        return new Float4(f.apply(w), f.apply(x), f.apply(y), f.apply(z));
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
    public Float4 min(@Nonnull Float4 v) {
        return new Float4(Math.min(w, v.w), Math.min(x, v.x), Math.min(y, v.y), Math.min(z, v.z));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The boundary vector of which to compare to
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Float4 max(@Nonnull Float4 v) {
        return new Float4(Math.max(w, v.w), Math.max(x, v.x), Math.max(y, v.y), Math.max(z, v.z));
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
    public Float4 clamp(@Nonnull Float4 min, @Nonnull Float4 max) {
        return new Float4(
                Numbers.clamp(w, min.w, max.w),
                Numbers.clamp(x, min.x, max.x),
                Numbers.clamp(y, min.y, max.y),
                Numbers.clamp(z, min.z, max.z)
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
        if (obj instanceof Float4 fv4) {
            return w == fv4.w && x == fv4.x && y == fv4.y && z == fv4.z;
        }

        if (obj instanceof FloatVector<?> fv) {
            final float[] array = fv.array();
            return Arrays.equals(array(), array);
        }

        if (obj instanceof Vector<?, ?> v) {
            final List<Float> l1 = list();
            final List<? extends Number> l2 = v.list();

            if (l2.size() != 4) return false;

            for (int i = 0; i < 4; i++) {
                if (!Numbers.equals(l1.get(i), l2.get(i))) return false;
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
    public boolean equals(@Nonnull Float4 v) {
        return w == v.w && x == v.x && y == v.y && z == v.z;
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
    public static Float4 parseVector(@Nonnull String input) throws NumberFormatException {
        if (!input.startsWith("Vector{")) {
            throw new NumberFormatException("The provided string is not a vector.");
        }

        final String[] valueStrings = input.replaceAll("Vector\\{|}", "").split(", ");
        if (valueStrings.length != 4) {
            throw new NumberFormatException("The provided string does not have four components.");
        }

        final float[] values = new float[4];

        for (final String valueString : valueStrings) {
            final String[] split = valueString.split("=");
            if (split.length != 2) {
                throw new NumberFormatException("The format is invalid.");
            }

            values[switch (split[0]) {
                case "w" -> 0;
                case "x" -> 1;
                case "y" -> 2;
                case "z" -> 3;
                default -> throw new NumberFormatException("The provided string has a non-WXYZ component.");
            }] = Float.parseFloat(split[1]);
        }

        return new Float4(values);
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
                "w=" + w +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
