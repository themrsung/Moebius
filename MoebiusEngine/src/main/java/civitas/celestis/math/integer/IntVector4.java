package civitas.celestis.math.integer;

import civitas.celestis.math.vector.Vector;
import civitas.celestis.math.vector.Vector4;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.function.UnaryOperator;

/**
 * An immutable four-dimensional {@code int} vector.
 */
public class IntVector4 implements IntVector {
    //
    // Constants
    //

    /**
     * A vector with no direction or magnitude. Represents origin.
     */
    public static final IntVector4 ZERO = new IntVector4(0, 0, 0, 0);

    /**
     * The minimum possible positive value a vector can have without being zero.
     */
    public static final IntVector4 MIN_VALUE = new IntVector4(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);

    /**
     * The maximum possible positive value a vector can have before overflowing.
     */
    public static final IntVector4 MAX_VALUE = new IntVector4(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);

    /**
     * The positive W unit vector.
     */
    public static final IntVector4 POSITIVE_W = new IntVector4(1, 0, 0, 0);

    /**
     * The positive X unit vector.
     */
    public static final IntVector4 POSITIVE_X = new IntVector4(0, 1, 0, 0);

    /**
     * The positive Y unit vector.
     */
    public static final IntVector4 POSITIVE_Y = new IntVector4(0, 0, 1, 0);

    /**
     * The positive Z unit vector.
     */
    public static final IntVector4 POSITIVE_Z = new IntVector4(0, 0, 0, 1);

    /**
     * The negative W unit vector.
     */
    public static final IntVector4 NEGATIVE_W = new IntVector4(-1, 0, 0, 0);

    /**
     * The negative X unit vector.
     */
    public static final IntVector4 NEGATIVE_X = new IntVector4(0, -1, 0, 0);

    /**
     * The negative Y unit vector.
     */
    public static final IntVector4 NEGATIVE_Y = new IntVector4(0, 0, -1, 0);

    /**
     * The negative Z unit vector.
     */
    public static final IntVector4 NEGATIVE_Z = new IntVector4(0, 0, 0, -1);

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
    public IntVector4(int w, int x, int y, int z) {
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Creates a new vector.
     *
     * @param values An array containing the values of this vector
     */
    public IntVector4(@Nonnull int[] values) {
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
     * @param v The {@link Vector} to copy values from
     */
    public IntVector4(@Nonnull Vector v) {
        if (v.length() != 4) {
            throw new IllegalArgumentException("The provided array does not have a length of 4.");
        }

        this.w = (int) v.valueAt(0);
        this.x = (int) v.valueAt(1);
        this.y = (int) v.valueAt(2);
        this.z = (int) v.valueAt(3);
    }

    /**
     * Creates a new vector.
     *
     * @param v The {@link Vector4} to copy values from
     */
    public IntVector4(@Nonnull Vector4 v) {
        this.w = (int) v.w();
        this.x = (int) v.x();
        this.y = (int) v.y();
        this.z = (int) v.z();
    }

    /**
     * Creates a new vector.
     *
     * @param other The vector to copy values from
     */
    public IntVector4(@Nonnull IntVector other) {
        if (other.length() != 4) {
            throw new IllegalArgumentException("The provided vector does not have a length of 4.");
        }

        this.w = other.valueAt(0);
        this.x = other.valueAt(1);
        this.y = other.valueAt(2);
        this.z = other.valueAt(3);
    }

    /**
     * Creates a new vector.
     *
     * @param other The vector to copy values from
     */
    public IntVector4(@Nonnull IntVector4 other) {
        this.w = other.w;
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
    }

    //
    // Variables
    //

    protected final int w, x, y, z;

    //
    // Getters
    //

    /**
     * Returns the W component of this vector.
     *
     * @return The W component of this vector
     */
    public final int w() {
        return w;
    }

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
     * @param i Index of component to get
     * @return The value at the given index
     * @throws IndexOutOfBoundsException When the index is out of bounds
     */
    @Override
    public final int valueAt(int i) throws IndexOutOfBoundsException {
        switch (i) {
            case 0 -> {return w;}
            case 1 -> {return x;}
            case 2 -> {return y;}
            case 3 -> {return z;}
            default -> throw new IndexOutOfBoundsException("Index " + i + " is out of bounds for this vector.");
        }
    }

    /**
     * {@inheritDoc}
     *
     * @return The array representation of this vector
     */
    @Override
    public final int[] values() {
        return new int[]{w, x, y, z};
    }

    //
    // Properties
    //

    /**
     * {@inheritDoc}
     *
     * @return The magnitude of this vector
     */
    @Override
    public final double magnitude() {
        return Math.sqrt(magnitude2());
    }

    /**
     * {@inheritDoc}
     *
     * @return The squared magnitude of this vector
     */
    @Override
    public final double magnitude2() {
        return w * w + x * x + y * y + z * z;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@code 4}
     */
    public final int length() {
        return 4;
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
    public IntVector4 add(int s) {
        return new IntVector4(w + s, x + s, y + s, z + s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public IntVector4 subtract(int s) {
        return new IntVector4(w - s, x - s, y - s, z - s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to multiply this vector by
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public IntVector4 multiply(int s) {
        return new IntVector4(w * s, x * s, y * s, z * s);
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
    public IntVector4 divide(int s) throws ArithmeticException {
        return new IntVector4(w / s, x / s, y / s, z / s);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to add to this vector
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 4}
     */
    @Nonnull
    @Override
    public IntVector4 add(@Nonnull IntVector v) throws IllegalArgumentException {
        if (v.length() != 4) {
            throw new IllegalArgumentException("The provided vector does not have a length of 4.");
        }

        return new IntVector4(w + v.valueAt(0), x + v.valueAt(1), y + v.valueAt(2), z + v.valueAt(3));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to subtract from this vector
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 4}
     */
    @Nonnull
    @Override
    public IntVector4 subtract(@Nonnull IntVector v) throws IllegalArgumentException {
        if (v.length() != 4) {
            throw new IllegalArgumentException("The provided vector does not have a length of 4.");
        }

        return new IntVector4(w - v.valueAt(0), x - v.valueAt(1), y - v.valueAt(2), z - v.valueAt(3));
    }

    /**
     * {@inheritDoc}
     * This operation uses quaternion left-multiplication.
     *
     * @param v The vector to multiply this vector by
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 4}
     */
    @Nonnull
    @Override
    public IntVector4 multiply(@Nonnull IntVector v) throws IllegalArgumentException {
        if (v.length() != 4) {
            throw new IllegalArgumentException("The provided vector does not have a length of 4.");
        }

        return new IntVector4(
                w * v.valueAt(0) - x * v.valueAt(1) - y * v.valueAt(2) - z * v.valueAt(3),
                w * v.valueAt(1) + x * v.valueAt(0) + y * v.valueAt(3) - z * v.valueAt(2),
                w * v.valueAt(2) - x * v.valueAt(3) + y * v.valueAt(0) + z * v.valueAt(1),
                w * v.valueAt(3) + x * v.valueAt(2) - y * v.valueAt(1) + z * v.valueAt(0)
        );
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the dot product between
     * @return The dot product of the two vectors
     * @throws IllegalArgumentException When the given vector {@code v}'s length is not {@code 4}
     */
    @Override
    public int dot(@Nonnull IntVector v) throws IllegalArgumentException {
        if (v.length() != 4) {
            throw new IllegalArgumentException("The given vector does not have a length of 4.");
        }

        return w * v.valueAt(0) + x * v.valueAt(1) + y * v.valueAt(2) + z * v.valueAt(3);
    }

    /**
     * Adds another vector to this vector.
     *
     * @param v The vector to add to this vector
     * @return The resulting vector
     */
    @Nonnull
    public IntVector4 add(@Nonnull IntVector4 v) {
        return new IntVector4(w + v.w, x + v.x, y + v.y, z + v.z);
    }

    /**
     * Subtracts another vector from this vector.
     *
     * @param v The vector to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    public IntVector4 subtract(@Nonnull IntVector4 v) {
        return new IntVector4(w - v.w, x - v.x, y - v.y, z - v.z);
    }

    /**
     * Multiplies this vector by another vector using quaternion left-multiplication.
     *
     * @param v The vector to multiply this vector by
     * @return The resulting vector
     */
    @Nonnull
    public IntVector4 multiply(@Nonnull IntVector4 v) {
        return new IntVector4(
                w * v.w - x * v.x - y * v.y - z * v.z,
                w * v.x + x * v.w + y * v.z - z * v.y,
                w * v.y - x * v.z + y * v.w + z * v.x,
                w * v.z + x * v.y - y * v.x + z * v.w
        );
    }

    /**
     * Returns the dot product between this vector and the provided vector {@code v}.
     *
     * @param v The vector to get the dot product between
     * @return The dot product of the two vectors
     */
    public int dot(@Nonnull IntVector4 v) {
        return w * v.w + x * v.x + y * v.y + z * v.z;
    }

    //
    // Clamping
    //

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the minimum vector of
     * @return The minimum vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 4}
     */
    @Nonnull
    @Override
    public IntVector4 min(@Nonnull IntVector v) throws IllegalArgumentException {
        if (v.length() != 4) {
            throw new IllegalArgumentException("The provided vector does not have a length of 4.");
        }

        return new IntVector4(
                Math.min(w, v.valueAt(0)),
                Math.min(x, v.valueAt(1)),
                Math.min(y, v.valueAt(2)),
                Math.min(z, v.valueAt(3))
        );
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the maximum vector of
     * @return The maximum vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 4}
     */
    @Nonnull
    @Override
    public IntVector4 max(@Nonnull IntVector v) throws IllegalArgumentException {
        if (v.length() != 4) {
            throw new IllegalArgumentException("The provided vector does not have a length of 4.");
        }

        return new IntVector4(
                Math.max(w, v.valueAt(0)),
                Math.max(x, v.valueAt(1)),
                Math.max(y, v.valueAt(2)),
                Math.max(z, v.valueAt(3))
        );
    }

    /**
     * {@inheritDoc}
     *
     * @param min The minimum allowed values
     * @param max The maximum allowed values
     * @return The clamped vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 4}
     */
    @Nonnull
    @Override
    public IntVector4 clamp(@Nonnull IntVector min, @Nonnull IntVector max) throws IllegalArgumentException {
        if (min.length() != 4 || max.length() != 4) {
            throw new IllegalArgumentException("The provided vectors do not have a length of 4.");
        }

        return new IntVector4(
                Math.max(Math.min(w, max.valueAt(0)), min.valueAt(0)),
                Math.max(Math.min(x, max.valueAt(1)), min.valueAt(1)),
                Math.max(Math.min(y, max.valueAt(2)), min.valueAt(2)),
                Math.max(Math.min(z, max.valueAt(3)), min.valueAt(3))
        );
    }

    /**
     * Returns the minimum vector between this vector and the provided vector {@code v}.
     *
     * @param v The vector to get the minimum vector of
     * @return The minimum vector
     */
    @Nonnull
    public IntVector4 min(@Nonnull IntVector4 v) {
        return new IntVector4(Math.min(w, v.w), Math.min(x, v.x), Math.min(y, v.y), Math.min(z, v.z));
    }

    /**
     * Returns the maximum vector between this vector and the provided vector {@code v}.
     *
     * @param v The vector to get the maximum vector of
     * @return The maximum vector
     */
    @Nonnull
    public IntVector4 max(@Nonnull IntVector4 v) {
        return new IntVector4(Math.max(w, v.w), Math.max(x, v.x), Math.max(y, v.y), Math.max(z, v.z));
    }

    /**
     * Returns a clamped vector which respects the given boundaries of {@code min} and {@code max}.
     *
     * @param min The minimum allowed values
     * @param max The maximum allowed values
     * @return The clamped vector
     */
    @Nonnull
    public IntVector4 clamp(@Nonnull IntVector4 min, @Nonnull IntVector4 max) {
        return new IntVector4(
                Math.max(Math.min(w, max.w), min.w),
                Math.max(Math.min(x, max.x), min.x),
                Math.max(Math.min(y, max.y), min.y),
                Math.max(Math.min(z, max.z), min.z)
        );
    }

    //
    // Utility
    //

    /**
     * {@inheritDoc}
     *
     * @param operator The operator to apply to each component of this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public IntVector4 apply(@Nonnull UnaryOperator<Integer> operator) {
        return new IntVector4(operator.apply(w), operator.apply(x), operator.apply(y), operator.apply(z));
    }

    /**
     * {@inheritDoc}
     *
     * @return The negated vector
     */
    @Nonnull
    @Override
    public IntVector4 negate() {
        return new IntVector4(-w, -x, -y, -z);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the distance to
     * @return The distance between this vector and {@code v}
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 4}
     */
    @Override
    public double distance(@Nonnull IntVector v) throws IllegalArgumentException {
        return subtract(v).magnitude();
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the squared distance to
     * @return The distance between this vector and {@code v}
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 4}
     */
    @Override
    public double distance2(@Nonnull IntVector v) throws IllegalArgumentException {
        return subtract(v).magnitude2();
    }

    /**
     * Returns the distance between this vector and the provided vector.
     *
     * @param v The vector to get the distance to
     * @return The distance between this vector and {@code v}
     */
    public double distance(@Nonnull IntVector4 v) {
        return subtract(v).magnitude();
    }

    /**
     * Returns the squared distance between this vector and the provided vector.
     *
     * @param v The vector to get the squared distance to
     * @return The squared distance between this vector and {@code v}
     */
    public double distance2(@Nonnull IntVector4 v) {
        return subtract(v).magnitude2();
    }

    /**
     * {@inheritDoc}
     *
     * @return A {@link Vector4} derived from {@code this}
     */
    @Nonnull
    @Override
    public Vector4 doubleValue() {
        return new Vector4(w, x, y, z);
    }

    //
    // Equality
    //

    /**
     * {@inheritDoc}
     *
     * @param obj The object to compare to
     * @return {@code true} if the object is an instance of {@link IntVector}, and the length and components are equal
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof IntVector v)) return false;
        if (v.length() != 4) return false;

        return w == v.valueAt(0) && x == v.valueAt(1) && y == v.valueAt(2) && z == v.valueAt(3);
    }

    //
    // Serialization
    //

    /**
     * Deserializes a string into a vector.
     *
     * @param input Input string to parse
     * @return The parsed vector
     * @throws NumberFormatException When the format is invalid
     */
    @Nonnull
    public static IntVector4 parseVector(@Nonnull String input) throws NumberFormatException {
        if (!input.startsWith("IntVector4{")) {
            throw new NumberFormatException("The provided string does not represent a IntVector4.");
        }

        final String cleanInput = input.replaceAll("IntVector4\\{|}", "");
        final String[] valueStrings = cleanInput.split(", ");
        final int[] values = new int[4];

        for (final String valueString : valueStrings) {
            final String[] split = valueString.split("=");
            values[switch (split[0]) {
                case "w" -> 0;
                case "x" -> 1;
                case "y" -> 2;
                case "z" -> 3;
                default -> throw new NumberFormatException("The provided string does not represent a IntVector4.");
            }] = Integer.parseInt(split[1]);
        }

        return new IntVector4(values);
    }

    /**
     * {@inheritDoc}
     *
     * @return The string representation of this vector
     */
    @Override
    @Nonnull
    public String toString() {
        return "IntVector4{" +
                "w=" + w +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
