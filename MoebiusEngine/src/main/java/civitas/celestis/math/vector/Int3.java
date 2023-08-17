package civitas.celestis.math.vector;

import civitas.celestis.math.Numbers;
import civitas.celestis.math.complex.FloatQuaternion;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * A three-dimensional vector which uses the type {@code int}.
 */
public class Int3 implements IntVector<Int3, Float3> {
    //
    // Constants
    //

    /**
     * A vector with no direction or magnitude. Represents origin.
     */
    public static final Int3 ZERO = new Int3(0, 0, 0);

    /**
     * The positive X unit vector.
     */
    public static final Int3 POSITIVE_X = new Int3(1, 0, 0);

    /**
     * The positive Y unit vector.
     */
    public static final Int3 POSITIVE_Y = new Int3(0, 1, 0);

    /**
     * Ths positive Z unit vector.
     */
    public static final Int3 POSITIVE_Z = new Int3(0, 0, 1);

    /**
     * The negative X unit vector.
     */
    public static final Int3 NEGATIVE_X = new Int3(-1, 0, 0);

    /**
     * The negative Y unit vector.
     */
    public static final Int3 NEGATIVE_Y = new Int3(0, -1, 0);

    /**
     * The negative Z unit vector.
     */
    public static final Int3 NEGATIVE_Z = new Int3(0, 0, -1);

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
    public Int3(int x, int y, int z) {
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
    public Int3(@Nonnull int[] values) {
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
    public Int3(@Nonnull IntVector<?, ?> v) {
        final int[] array = v.array();

        this.x = array[0];
        this.y = array[1];
        this.z = array[2];
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     */
    public Int3(@Nonnull Int3 v) {
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     */
    public Int3(@Nonnull Double3 v) {
        this.x = (int) v.x;
        this.y = (int) v.y;
        this.z = (int) v.z;
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     */
    public Int3(@Nonnull Float3 v) {
        this.x = (int) v.x;
        this.y = (int) v.y;
        this.z = (int) v.z;
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     */
    public Int3(@Nonnull Long3 v) {
        this.x = (int) v.x;
        this.y = (int) v.y;
        this.z = (int) v.z;
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

    /**
     * The Z component of this vector.
     */
    protected final int z;

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

    /**
     * Returns the Z component of this vector.
     *
     * @return The Z component of this vector
     */
    public final int z() {
        return z;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public List<Integer> list() {
        return List.of(x, y, z);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public int[] array() {
        return new int[]{x, y, z};
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
    public float norm() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int norm2() {
        return x * x + y * y + z * z;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int normManhattan() {
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
    public Int3 add(int s) {
        return new Int3(x + s, y + s, z + s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to subtract from this vector
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Int3 subtract(int s) {
        return new Int3(x - s, y - s, z - s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to multiply this vector by
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Int3 multiply(int s) {
        return new Int3(x * s, y * s, z * s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to divide this vector by
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Int3 divide(int s) {
        return new Int3(x / s, y / s, z / s);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to add to this vector
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Int3 add(@Nonnull Int3 v) {
        return new Int3(x + v.x, y + v.y, z + v.z);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to subtract from this vector
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Int3 subtract(@Nonnull Int3 v) {
        return new Int3(x - v.x, y - v.y, z - v.z);
    }

    /**
     * Returns the cross product between this vector and the provided vector {@code v}.
     *
     * @param v The vector to multiply this vector by
     * @return The cross product between the two vectors
     */
    @Nonnull
    public Int3 cross(@Nonnull Int3 v) {
        return new Int3(
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
    public int dot(@Nonnull Int3 v) {
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
    public Int3 negate() {
        return new Int3(-x, -y, -z);
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
    public float distance(@Nonnull Int3 v) {
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
    public int distance2(@Nonnull Int3 v) {
        final int dx = x - v.x;
        final int dy = y - v.y;
        final int dz = z - v.z;

        return dx * dx + dy * dy + dz * dz;
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the distance to
     * @return {@inheritDoc}
     */
    @Override
    public int distanceManhattan(@Nonnull Int3 v) {
        final int dx = x - v.x;
        final int dy = y - v.y;
        final int dz = z - v.z;

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
    public Int3 transform(@Nonnull Function<? super Integer, Integer> f) {
        return new Int3(f.apply(x), f.apply(y), f.apply(z));
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
    public Int3 min(@Nonnull Int3 v) {
        return new Int3(Math.min(x, v.x), Math.min(y, v.y), Math.min(z, v.z));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The boundary vector of which to compare to
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Int3 max(@Nonnull Int3 v) {
        return new Int3(Math.max(x, v.x), Math.max(y, v.y), Math.max(z, v.z));
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
    public Int3 clamp(@Nonnull Int3 min, @Nonnull Int3 max) {
        return new Int3(
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
    public FloatQuaternion quaternion() {
        return new FloatQuaternion(0, x, y, z);
    }

    /**
     * Rotates this vector by the provided rotation quaternion.
     *
     * @param rq The quaternion to rotate this vector by
     * @return The resulting vector
     */
    @Nonnull
    public Int3 rotate(@Nonnull FloatQuaternion rq) {
        return new Int3(rq.multiply(quaternion()).multiply(rq.conjugate()).vector());
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
        if (obj instanceof Int3 iv3) {
            return x == iv3.x && y == iv3.y && z == iv3.z;
        }

        if (obj instanceof IntVector<?, ?> iv) {
            final int[] array = iv.array();
            return Arrays.equals(array(), array);
        }

        if (obj instanceof Vector<?, ?> v) {
            final List<Integer> l1 = list();
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
    public boolean equals(@Nonnull Int3 v) {
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
    public static Int3 parseVector(@Nonnull String input) throws NumberFormatException {
        if (!input.startsWith("Vector{")) {
            throw new NumberFormatException("The provided string is not a vector.");
        }

        final String[] valueStrings = input.replaceAll("Vector\\{|}", "").split(", ");
        if (valueStrings.length != 3) {
            throw new NumberFormatException("The provided string does not have three components.");
        }

        final int[] values = new int[3];

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
            }] = Integer.parseInt(split[1]);
        }

        return new Int3(values);
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
