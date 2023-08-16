package civitas.celestis.math.vector;

import civitas.celestis.math.Numbers;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;

/**
 * A four-dimensional vector which uses the type {@code double}.
 */
public class Double4 implements DoubleVector<Double4> {
    //
    // Constants
    //

    /**
     * A vector with no direction or magnitude. Represents origin.
     */
    public static final Double4 ZERO = new Double4(0, 0, 0, 0);

    /**
     * The positive W unit vector.
     */
    public static final Double4 POSITIVE_W = new Double4(1, 0, 0, 0);

    /**
     * The positive X unit vector.
     */
    public static final Double4 POSITIVE_X = new Double4(0, 1, 0, 0);

    /**
     * The positive Y unit vector.
     */
    public static final Double4 POSITIVE_Y = new Double4(0, 0, 1, 0);

    /**
     * Ths positive Z unit vector.
     */
    public static final Double4 POSITIVE_Z = new Double4(0, 0, 0, 1);

    /**
     * The negative W unit vector.
     */
    public static final Double4 NEGATIVE_W = new Double4(-1, 0, 0, 0);

    /**
     * The negative X unit vector.
     */
    public static final Double4 NEGATIVE_X = new Double4(0, -1, 0, 0);

    /**
     * The negative Y unit vector.
     */
    public static final Double4 NEGATIVE_Y = new Double4(0, 0, -1, 0);

    /**
     * The negative Z unit vector.
     */
    public static final Double4 NEGATIVE_Z = new Double4(0, 0, 0, -1);

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
    public Double4(double w, double x, double y, double z) {
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
    public Double4(@Nonnull double[] values) {
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
    public Double4(@Nonnull DoubleVector<?> v) {
        final double[] array = v.array();

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
    public Double4(@Nonnull Double4 v) {
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
    protected final double w;

    /**
     * The X component of this vector.
     */
    protected final double x;

    /**
     * The Y component of this vector.
     */
    protected final double y;

    /**
     * The Z component of this vector.
     */
    protected final double z;

    //
    // Getters
    //

    /**
     * Returns the W component of this vector.
     *
     * @return The W component of this vector
     */
    public final double w() {
        return w;
    }

    /**
     * Returns the X component of this vector.
     *
     * @return The X component of this vector
     */
    public final double x() {
        return x;
    }

    /**
     * Returns the Y component of this vector.
     *
     * @return The Y component of this vector
     */
    public final double y() {
        return y;
    }

    /**
     * Returns the Z component of this vector.
     *
     * @return The Z component of this vector
     */
    public final double z() {
        return z;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public List<Double> list() {
        return List.of(w, x, y, z);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public double[] array() {
        return new double[]{w, x, y, z};
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
        return Double.isNaN(w) || Double.isNaN(x) || Double.isNaN(y) || Double.isNaN(z);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public boolean isFinite() {
        return Double.isFinite(w) && Double.isFinite(x) && Double.isFinite(y) && Double.isFinite(z);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public boolean isInfinite() {
        return Double.isInfinite(w) || Double.isInfinite(x) || Double.isInfinite(y) && Double.isInfinite(z);
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public double norm() {
        return Math.sqrt(w * w + x * x + y * y + z * z);
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public double norm2() {
        return w * w + x * x + y * y + z * z;
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public double normManhattan() {
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
    public Double4 add(double s) {
        return new Double4(w + s, x + s, y + s, z + s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to subtract from this vector
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Double4 subtract(double s) {
        return new Double4(w - s, x - s, y - s, z - s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to multiply this vector by
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Double4 multiply(double s) {
        return new Double4(w * s, x * s, y * s, z * s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to divide this vector by
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Double4 divide(double s) {
        return new Double4(w / s, x / s, y / s, z / s);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to add to this vector
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Double4 add(@Nonnull Double4 v) {
        return new Double4(w + v.w, x + v.x, y + v.y, z + v.z);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to subtract from this vector
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Double4 subtract(@Nonnull Double4 v) {
        return new Double4(w - v.w, x - v.x, y - v.y, z - v.z);
    }

    /**
     * Multiplies this vector by another vector using quaternion multiplication.
     *
     * @param v The vector to multiply this vector by
     * @return The quaternion left-product of the two vectors
     */
    @Nonnull
    public Double4 multiply(@Nonnull Double4 v) {
        return new Double4(
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
    public double dot(@Nonnull Double4 v) {
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
    public Double4 negate() {
        return new Double4(-w, -x, -y, -z);
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
    public Double4 normalize() {
        final double n = norm();

        if (n == 0) {
            throw new ArithmeticException("Cannot normalize a vector whose magnitude is zero.");
        }

        return new Double4(w / n, x / n, y / n, z / n);
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
    public Double4 transform(@Nonnull UnaryOperator<Double> f) {
        return new Double4(f.apply(w), f.apply(x), f.apply(y), f.apply(z));
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
    public Double4 min(@Nonnull Double4 v) {
        return new Double4(Math.min(w, v.w), Math.min(x, v.x), Math.min(y, v.y), Math.min(z, v.z));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The boundary vector of which to compare to
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Double4 max(@Nonnull Double4 v) {
        return new Double4(Math.max(w, v.w), Math.max(x, v.x), Math.max(y, v.y), Math.max(z, v.z));
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
    public Double4 clamp(@Nonnull Double4 min, @Nonnull Double4 max) {
        return new Double4(
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
        if (obj instanceof Double4 dv4) {
            return w == dv4.w && x == dv4.x && y == dv4.y && z == dv4.z;
        }

        if (obj instanceof DoubleVector<?> dv) {
            final double[] array = dv.array();
            return Arrays.equals(array(), array);
        }

        if (obj instanceof Vector<?, ?> v) {
            final List<Double> l1 = list();
            final List<? extends Number> l2 = v.list();

            if (l1.size() != l2.size()) return false;

            for (int i = 0; i < l2.size(); i++) {
                if (l1.get(i) != l2.get(i)) return false;
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
    public boolean equals(@Nonnull Double4 v) {
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
    public static Double4 parseVector(@Nonnull String input) throws NumberFormatException {
        if (!input.startsWith("Vector{")) {
            throw new NumberFormatException("The provided string is not a vector.");
        }

        final String[] valueStrings = input.replaceAll("Vector\\{|}", "").split(", ");
        if (valueStrings.length != 4) {
            throw new NumberFormatException("The provided string does not have four components.");
        }

        final double[] values = new double[4];

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
            }] = Double.parseDouble(split[1]);
        }

        return new Double4(values);
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
