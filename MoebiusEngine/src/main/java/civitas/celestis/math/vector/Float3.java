package civitas.celestis.math.vector;

import civitas.celestis.math.Numbers;
import civitas.celestis.math.complex.Quaternion;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * A three-dimensional vector which uses the type {@code float}.
 */
public class Float3 implements FloatVector<Float3> {
    //
    // Constants
    //

    /**
     * A vector with no direction or magnitude. Represents origin.
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
     * Ths positive Z unit vector.
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
     * @param values The array of values to use
     * @throws IndexOutOfBoundsException When the provided array's length is too small
     */
    public Float3(@Nonnull float[] values) {
        this.x = values[0];
        this.y = values[1];
        this.z = values[2];
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     * @throws IndexOutOfBoundsException When the provided vector's length is too small
     */
    public Float3(@Nonnull FloatVector<?> v) {
        final float[] array = v.array();

        this.x = array[0];
        this.y = array[1];
        this.z = array[2];
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
    public Float3(@Nonnull Double3 v) {
        this.x = (float) v.x();
        this.y = (float) v.y();
        this.z = (float) v.z();
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     */
    public Float3(@Nonnull Long3 v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
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
     * The Z component of this vector.
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

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public List<Float> list() {
        return List.of(x, y, z);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public float[] array() {
        return new float[]{x, y, z};
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
        return x == 0 && y == 0 & z == 0;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public boolean isNaN() {
        return Float.isNaN(x) || Float.isNaN(y) || Float.isNaN(z);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public boolean isFinite() {
        return Float.isFinite(x) && Float.isFinite(y) && Float.isFinite(z);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public boolean isInfinite() {
        return Float.isInfinite(x) || Float.isInfinite(y) && Float.isInfinite(z);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public float norm() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public float norm2() {
        return x * x + y * y + z * z;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public float normManhattan() {
        return Math.abs(x) + Math.abs(y) + Math.abs(z);
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
    public Float3 add(float s) {
        return new Float3(x + s, y + s, z + s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to subtract from this vector
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Float3 subtract(float s) {
        return new Float3(x - s, y - s, z - s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to multiply this vector by
     * @return {@inheritDoc}
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
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Float3 divide(float s) {
        return new Float3(x / s, y / s, z / s);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to add to this vector
     * @return {@inheritDoc}
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
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Float3 subtract(@Nonnull Float3 v) {
        return new Float3(x - v.x, y - v.y, z - v.z);
    }

    /**
     * Returns the cross product between this vector and the provided vector {@code v}.
     *
     * @param v The vector to multiply this vector by
     * @return The cross product between the two vectors
     */
    @Nonnull
    public Float3 cross(@Nonnull Float3 v) {
        return new Float3(
                y * v.z - z * v.y,
                z * v.x - x * v.z,
                x * v.y - y * v.x
        );
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the dot product between
     * @return {@inheritDoc}
     */
    @Override
    public float dot(@Nonnull Float3 v) {
        return x * v.x + y * v.y + z * v.z;
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
    public Float3 negate() {
        return new Float3(-x, -y, -z);
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
    public float distance(@Nonnull Float3 v) {
        final float dx = x - v.x;
        final float dy = y - v.y;
        final float dz = z - v.z;

        return (float) Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the squared distance to
     * @return {@inheritDoc}
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
     * @param v The vector of which to get the distance to
     * @return {@inheritDoc}
     */
    @Override
    public float distanceManhattan(@Nonnull Float3 v) {
        final float dx = x - v.x;
        final float dy = y - v.y;
        final float dz = z - v.z;

        return Math.abs(dx) + Math.abs(dy) + Math.abs(dz);
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
    public Float3 normalize() {
        final float n = norm();

        if (n == 0) {
            throw new ArithmeticException("Cannot normalize a vector whose magnitude is zero.");
        }

        return new Float3(x / n, y / n, z / n);
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
    public Float3 transform(@Nonnull Function<? super Float, Float> f) {
        return new Float3(f.apply(x), f.apply(y), f.apply(z));
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
    public Float3 min(@Nonnull Float3 v) {
        return new Float3(Math.min(x, v.x), Math.min(y, v.y), Math.min(z, v.z));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The boundary vector of which to compare to
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Float3 max(@Nonnull Float3 v) {
        return new Float3(Math.max(x, v.x), Math.max(y, v.y), Math.max(z, v.z));
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
    public Float3 clamp(@Nonnull Float3 min, @Nonnull Float3 max) {
        return new Float3(
                Numbers.clamp(x, min.x, max.x),
                Numbers.clamp(y, min.y, max.y),
                Numbers.clamp(z, min.z, max.z)
        );
    }

    //
    // Rotation
    //

    /**
     * Converts this vector into a pure quaternion.
     * (a quaternion where the scalar part is {@code 0}, and the
     * vector part is populated with this vector).
     *
     * @return The pure quaternion representation of this vector
     */
    @Nonnull
    public Quaternion quaternion() {
        return new Quaternion(0, x, y, z);
    }

    /**
     * Rotates this vector by the provided rotation quaternion.
     *
     * @param rq The quaternion to rotate this vector by
     * @return The resulting vector
     */
    @Nonnull
    public Float3 rotate(@Nonnull Quaternion rq) {
        return new Float3(rq.multiply(quaternion()).multiply(rq.conjugate()).vector());
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
        if (obj instanceof Float3 dv3) {
            return x == dv3.x && y == dv3.y && z == dv3.z;
        }

        if (obj instanceof FloatVector<?> fv) {
            final float[] array = fv.array();
            return Arrays.equals(array(), array);
        }

        if (obj instanceof Vector<?, ?> v) {
            final List<Float> l1 = list();
            final List<? extends Number> l2 = v.list();

            if (l2.size() != 3) return false;

            for (int i = 0; i < 3; i++) {
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
    public boolean equals(@Nonnull Float3 v) {
        return x == v.x && y == v.y && z == v.z;
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
    public static Float3 parseVector(@Nonnull String input) throws NumberFormatException {
        if (!input.startsWith("Vector{")) {
            throw new NumberFormatException("The provided string is not a vector.");
        }

        final String[] valueStrings = input.replaceAll("Vector\\{|}", "").split(", ");
        if (valueStrings.length != 3) {
            throw new NumberFormatException("The provided string does not have three components.");
        }

        final float[] values = new float[3];

        for (final String valueString : valueStrings) {
            final String[] split = valueString.split("=");
            if (split.length != 2) {
                throw new NumberFormatException("The format is invalid.");
            }

            values[switch (split[0]) {
                case "x" -> 0;
                case "y" -> 1;
                case "z" -> 2;
                default -> throw new NumberFormatException("The provided string has a non-XYZ component.");
            }] = Float.parseFloat(split[1]);
        }

        return new Float3(values);
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
                ", z=" + z +
                '}';
    }
}
