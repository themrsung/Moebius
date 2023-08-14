package civitas.celestis.math.integer;

import civitas.celestis.math.vector.Vector;
import civitas.celestis.math.vector.Vector3;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.function.UnaryOperator;

/**
 * An immutable three-dimensional {@code int} vector.
 */
public class IntVector3 implements IntVector {
    //
    // Constants
    //

    /**
     * A vector with no direction or magnitude. Represents origin.
     */
    public static final IntVector3 ZERO = new IntVector3(0, 0, 0);

    /**
     * The minimum possible negative value a vector can have before overflowing.
     */
    public static final IntVector3 MIN_VALUE = new IntVector3(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);

    /**
     * The maximum possible positive value a vector can have before overflowing.
     */
    public static final IntVector3 MAX_VALUE = new IntVector3(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);

    /**
     * The positive X unit vector.
     */
    public static final IntVector3 POSITIVE_X = new IntVector3(1, 0, 0);

    /**
     * The positive Y unit vector.
     */
    public static final IntVector3 POSITIVE_Y = new IntVector3(0, 1, 0);

    /**
     * The positive Z unit vector.
     */
    public static final IntVector3 POSITIVE_Z = new IntVector3(0, 0, 1);

    /**
     * The negative X unit vector.
     */
    public static final IntVector3 NEGATIVE_X = new IntVector3(-1, 0, 0);

    /**
     * The negative Y unit vector.
     */
    public static final IntVector3 NEGATIVE_Y = new IntVector3(0, -1, 0);

    /**
     * The negative Z unit vector.
     */
    public static final IntVector3 NEGATIVE_Z = new IntVector3(0, 0, -1);

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
    public IntVector3(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Creates a new vector.
     *
     * @param values An array containing the values of this vector
     */
    public IntVector3(@Nonnull int[] values) {
        if (values.length != 3) {
            throw new IllegalArgumentException("The provided array does not have a length of 3.");
        }

        this.x = values[0];
        this.y = values[1];
        this.z = values[2];
    }

    /**
     * Creates a new vector.
     *
     * @param v The {@link Vector} to copy values from
     */
    public IntVector3(@Nonnull Vector v) {
        if (v.length() != 3) {
            throw new IllegalArgumentException("The provided array does not have a length of 3.");
        }

        this.x = (int) v.valueAt(0);
        this.y = (int) v.valueAt(1);
        this.z = (int) v.valueAt(2);
    }

    /**
     * Creates a new vector.
     *
     * @param v The {@link Vector3} to copy values from
     */
    public IntVector3(@Nonnull Vector3 v) {
        this.x = (int) v.x();
        this.y = (int) v.y();
        this.z = (int) v.z();
    }

    /**
     * Creates a new vector.
     *
     * @param other The vector to copy values from
     */
    public IntVector3(@Nonnull IntVector other) {
        if (other.length() != 3) {
            throw new IllegalArgumentException("The provided vector does not have a length of 3.");
        }

        this.x = other.valueAt(0);
        this.y = other.valueAt(1);
        this.z = other.valueAt(2);
    }

    /**
     * Creates a new vector.
     *
     * @param other The vector to copy values from
     */
    public IntVector3(@Nonnull IntVector3 other) {
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
    }

    //
    // Variables
    //

    protected final int x, y, z;

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
     * @param i Index of component to get
     * @return The value at the given index
     * @throws IndexOutOfBoundsException When the index is out of bounds
     */
    @Override
    public final int valueAt(int i) throws IndexOutOfBoundsException {
        switch (i) {
            case 0 -> {return x;}
            case 1 -> {return y;}
            case 2 -> {return z;}
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
        return new int[]{x, y, z};
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
        return Math.sqrt(x * x + y * y + z * z);
    }

    /**
     * {@inheritDoc}
     *
     * @return The squared magnitude of this vector
     */
    @Override
    public final double magnitude2() {
        return x * x + y * y + z * z;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@code 3}
     */
    public final int length() {
        return 3;
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
    public IntVector3 add(int s) {
        return new IntVector3(x + s, y + s, z + s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public IntVector3 subtract(int s) {
        return new IntVector3(x - s, y - s, z - s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to multiply this vector by
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public IntVector3 multiply(int s) {
        return new IntVector3(x * s, y * s, z * s);
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
    public IntVector3 divide(int s) throws ArithmeticException {
        return new IntVector3(x / s, y / s, z / s);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to add to this vector
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 3}
     */
    @Nonnull
    @Override
    public IntVector3 add(@Nonnull IntVector v) throws IllegalArgumentException {
        if (v.length() != 3) {
            throw new IllegalArgumentException("The provided vector does not have a length of 3.");
        }

        return new IntVector3(x + v.valueAt(0), y + v.valueAt(1), z + v.valueAt(2));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to subtract from this vector
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 3}
     */
    @Nonnull
    @Override
    public IntVector3 subtract(@Nonnull IntVector v) throws IllegalArgumentException {
        if (v.length() != 3) {
            throw new IllegalArgumentException("The provided vector does not have a length of 3.");
        }

        return new IntVector3(x - v.valueAt(0), y - v.valueAt(1), z - v.valueAt(2));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to multiply this vector by
     * @return This method cannot return a value
     * @throws UnsupportedOperationException Always throws an exception
     */
    @Nonnull
    @Override
    public IntVector3 multiply(@Nonnull IntVector v) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("There is no default multiplication operation defined for MutableVector.");
    }

    /**
     * Returns the cross product between this vector and the provided vector.
     *
     * @param v The vector on the right of this operation
     * @return The cross product of the two vectors
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 3}
     */
    @Nonnull
    public IntVector3 cross(@Nonnull IntVector v) throws IllegalArgumentException {
        if (v.length() != 3) {
            throw new IllegalArgumentException("The provided vector does not have a length of 3.");
        }

        return new IntVector3(
                y * v.valueAt(2) - z * v.valueAt(1),
                z * v.valueAt(0) - x * v.valueAt(2),
                x * v.valueAt(1) - y * v.valueAt(0)
        );
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the dot product between
     * @return The dot product of the two vectors
     * @throws IllegalArgumentException When the given vector {@code v}'s length is not {@code 3}
     */
    @Override
    public int dot(@Nonnull IntVector v) throws IllegalArgumentException {
        if (v.length() != 3) {
            throw new IllegalArgumentException("The given vector does not have a length of 3.");
        }

        return x * v.valueAt(0) + y * v.valueAt(1) + z * v.valueAt(2);
    }

    /**
     * Adds another vector to this vector.
     *
     * @param v The vector to add to this vector
     * @return The resulting vector
     */
    @Nonnull
    public IntVector3 add(@Nonnull IntVector3 v) {
        return new IntVector3(x + v.x, y + v.y, z + v.z);
    }

    /**
     * Subtracts another vector from this vector.
     *
     * @param v The vector to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    public IntVector3 subtract(@Nonnull IntVector3 v) {
        return new IntVector3(x - v.x, y - v.y, z - v.z);
    }

    /**
     * Returns the cross product between this vector and the provided vector {@code v}.
     *
     * @param v The vector on the right of this operation
     * @return The cross product of the two vectors
     */
    @Nonnull
    public IntVector3 cross(@Nonnull IntVector3 v) {
        return new IntVector3(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x);
    }

    /**
     * Returns the dot product between this vector and the provided vector {@code v}.
     *
     * @param v The vector to get the dot product between
     * @return The dot product of the two vectors
     */
    public int dot(@Nonnull IntVector3 v) {
        return x * v.x + y * v.y + z * v.z;
    }

    //
    // Clamping
    //

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the minimum vector of
     * @return The minimum vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 3}
     */
    @Nonnull
    @Override
    public IntVector3 min(@Nonnull IntVector v) throws IllegalArgumentException {
        if (v.length() != 3) {
            throw new IllegalArgumentException("The provided vector does not have a length of 3.");
        }

        return new IntVector3(Math.min(x, v.valueAt(0)), Math.min(y, v.valueAt(1)), Math.min(z, v.valueAt(2)));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the maximum vector of
     * @return The maximum vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 3}
     */
    @Nonnull
    @Override
    public IntVector3 max(@Nonnull IntVector v) throws IllegalArgumentException {
        if (v.length() != 3) {
            throw new IllegalArgumentException("The provided vector does not have a length of 3.");
        }

        return new IntVector3(Math.max(x, v.valueAt(0)), Math.max(y, v.valueAt(1)), Math.max(z, v.valueAt(2)));
    }

    /**
     * {@inheritDoc}
     *
     * @param min The minimum allowed values
     * @param max The maximum allowed values
     * @return The clamped vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 3}
     */
    @Nonnull
    @Override
    public IntVector3 clamp(@Nonnull IntVector min, @Nonnull IntVector max) throws IllegalArgumentException {
        if (min.length() != 3 || max.length() != 3) {
            throw new IllegalArgumentException("The provided vectors do not have a length of 3.");
        }

        return new IntVector3(
                Math.max(Math.min(x, max.valueAt(0)), min.valueAt(0)),
                Math.max(Math.min(y, max.valueAt(1)), min.valueAt(1)),
                Math.max(Math.min(z, max.valueAt(2)), min.valueAt(2))
        );
    }

    /**
     * Returns the minimum vector between this vector and the provided vector {@code v}.
     *
     * @param v The vector to get the minimum vector of
     * @return The minimum vector
     */
    @Nonnull
    public IntVector3 min(@Nonnull IntVector3 v) {
        return new IntVector3(Math.min(x, v.x), Math.min(y, v.y), Math.min(z, v.z));
    }

    /**
     * Returns the maximum vector between this vector and the provided vector {@code v}.
     *
     * @param v The vector to get the maximum vector of
     * @return The maximum vector
     */
    @Nonnull
    public IntVector3 max(@Nonnull IntVector3 v) {
        return new IntVector3(Math.max(x, v.x), Math.max(y, v.y), Math.max(z, v.z));
    }

    /**
     * Returns a clamped vector which respects the given boundaries of {@code min} and {@code max}.
     *
     * @param min The minimum allowed values
     * @param max The maximum allowed values
     * @return The clamped vector
     */
    @Nonnull
    public IntVector3 clamp(@Nonnull IntVector3 min, @Nonnull IntVector3 max) {
        return new IntVector3(
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
    public IntVector3 apply(@Nonnull UnaryOperator<Integer> operator) {
        return new IntVector3(operator.apply(x), operator.apply(y), operator.apply(z));
    }

    /**
     * {@inheritDoc}
     *
     * @return The negated vector
     */
    @Nonnull
    @Override
    public IntVector3 negate() {
        return new IntVector3(-x, -y, -z);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the distance to
     * @return The distance between this vector and {@code v}
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 3}
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
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 3}
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
    public double distance(@Nonnull IntVector3 v) {
        final int dx = x - v.x;
        final int dy = y - v.y;
        final int dz = z - v.z;

        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    /**
     * Returns the squared distance between this vector and the provided vector.
     *
     * @param v The vector to get the squared distance to
     * @return The squared distance between this vector and {@code v}
     */
    public double distance2(@Nonnull IntVector3 v) {
        final int dx = x - v.x;
        final int dy = y - v.y;
        final int dz = z - v.z;

        return dx * dx + dy * dy + dz * dz;
    }

    /**
     * {@inheritDoc}
     *
     * @return A {@link Vector3} derived from {@code this}
     */
    @Nonnull
    @Override
    public Vector3 doubleValue() {
        return new Vector3(x, y, z);
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
        if (v.length() != 3) return false;

        return x == v.valueAt(0) && y == v.valueAt(1) && z == v.valueAt(2);
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
    public static IntVector3 parseVector(@Nonnull String input) throws NumberFormatException {
        if (!input.startsWith("IntVector3{")) {
            throw new NumberFormatException("The provided string does not represent a IntVector3.");
        }

        final String cleanInput = input.replaceAll("IntVector3\\{|}", "");
        final String[] valueStrings = cleanInput.split(", ");
        final int[] values = new int[3];

        for (final String valueString : valueStrings) {
            final String[] split = valueString.split("=");
            values[switch (split[0]) {
                case "x" -> 0;
                case "y" -> 1;
                case "z" -> 2;
                default -> throw new NumberFormatException("The provided string does not represent a IntVector3.");
            }] = Integer.parseInt(split[1]);
        }

        return new IntVector3(values);
    }

    /**
     * {@inheritDoc}
     *
     * @return The string representation of this vector
     */
    @Override
    @Nonnull
    public String toString() {
        return "IntVector3{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
