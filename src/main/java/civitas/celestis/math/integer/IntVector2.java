package civitas.celestis.math.integer;

import civitas.celestis.math.vector.Vector;
import civitas.celestis.math.vector.Vector2;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.function.UnaryOperator;

/**
 * An immutable two-dimensional {@code int} vector.
 */
public class IntVector2 implements IntVector {
    //
    // Constants
    //

    /**
     * A vector with no direction or magnitude. Represents origin.
     */
    public static final IntVector2 ZERO = new IntVector2(0, 0);

    /**
     * The minimum possible positive value a vector can have without being zero.
     */
    public static final IntVector2 MIN_VALUE = new IntVector2(Integer.MIN_VALUE, Integer.MIN_VALUE);

    /**
     * The maximum possible positive value a vector can have before overflowing.
     */
    public static final IntVector2 MAX_VALUE = new IntVector2(Integer.MAX_VALUE, Integer.MAX_VALUE);

    /**
     * The positive X unit vector.
     */
    public static final IntVector2 POSITIVE_X = new IntVector2(1, 0);

    /**
     * The positive Y unit vector.
     */
    public static final IntVector2 POSITIVE_Y = new IntVector2(0, 1);

    /**
     * The negative X unit vector.
     */
    public static final IntVector2 NEGATIVE_X = new IntVector2(-1, 0);

    /**
     * The negative Y unit vector.
     */
    public static final IntVector2 NEGATIVE_Y = new IntVector2(0, -1);

    //
    // Constructors
    //

    /**
     * Creates a new vector.
     *
     * @param x The X component of this vector
     * @param y The Y component of this vector
     */
    public IntVector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Creates a new vector.
     *
     * @param values An array containing the values of this vector
     */
    public IntVector2(@Nonnull int[] values) {
        if (values.length != 2) {
            throw new IllegalArgumentException("The provided array does not have a length of 2.");
        }

        this.x = values[0];
        this.y = values[1];
    }

    /**
     * Creates a new vector.
     *
     * @param v The {@link Vector} to copy values from
     */
    public IntVector2(@Nonnull Vector v) {
        if (v.length() != 2) {
            throw new IllegalArgumentException("The provided vector does not have a length of 2.");
        }

        this.x = (int) v.valueAt(0);
        this.y = (int) v.valueAt(1);
    }

    /**
     * Creates a new vector.
     *
     * @param v The {@link Vector2} to copy values from
     */
    public IntVector2(@Nonnull Vector2 v) {
        this.x = (int) v.x();
        this.y = (int) v.y();
    }

    /**
     * Creates a new vector.
     *
     * @param other The vector to copy values from
     */
    public IntVector2(@Nonnull IntVector other) {
        if (other.length() != 2) {
            throw new IllegalArgumentException("The provided vector does not have a length of 2.");
        }

        this.x = other.valueAt(0);
        this.y = other.valueAt(1);
    }

    /**
     * Creates a new vector.
     *
     * @param other The vector to copy values from
     */
    public IntVector2(@Nonnull IntVector2 other) {
        this.x = other.x;
        this.y = other.y;
    }

    //
    // Variables
    //

    protected final int x, y;

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
        return new int[]{x, y};
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
        return x * x + y * y;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@code 2}
     */
    public final int length() {
        return 2;
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
    public IntVector2 add(int s) {
        return new IntVector2(x + s, y + s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public IntVector2 subtract(int s) {
        return new IntVector2(x - s, y - s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to multiply this vector by
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public IntVector2 multiply(int s) {
        return new IntVector2(x * s, y * s);
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
    public IntVector2 divide(int s) throws ArithmeticException {
        return new IntVector2(x / s, y / s);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to add to this vector
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 2}
     */
    @Nonnull
    @Override
    public IntVector2 add(@Nonnull IntVector v) throws IllegalArgumentException {
        if (v.length() != 2) {
            throw new IllegalArgumentException("The provided vector does not have a length of 2.");
        }

        return new IntVector2(x + v.valueAt(0), y + v.valueAt(1));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to subtract from this vector
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 2}
     */
    @Nonnull
    @Override
    public IntVector2 subtract(@Nonnull IntVector v) throws IllegalArgumentException {
        if (v.length() != 2) {
            throw new IllegalArgumentException("The provided vector does not have a length of 2.");
        }

        return new IntVector2(x - v.valueAt(0), y - v.valueAt(1));
    }

    /**
     * {@inheritDoc}
     * This operation uses complex number multiplication.
     *
     * @param v The vector to multiply this vector by
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 2}
     */
    @Nonnull
    @Override
    public IntVector2 multiply(@Nonnull IntVector v) throws IllegalArgumentException {
        if (v.length() != 2) {
            throw new IllegalArgumentException("The provided vector does not have a length of 2.");
        }

        return new IntVector2(x * v.valueAt(0) - y * v.valueAt(1), x * v.valueAt(1) + y * v.valueAt(0));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the dot product between
     * @return The dot product of the two vectors
     * @throws IllegalArgumentException When the given vector {@code v}'s length is not {@code 2}
     */
    @Override
    public int dot(@Nonnull IntVector v) throws IllegalArgumentException {
        if (v.length() != 2) {
            throw new IllegalArgumentException("The given vector does not have a length of 2.");
        }

        return x * v.valueAt(0) + y * v.valueAt(1);
    }

    /**
     * Adds another vector to this vector.
     *
     * @param v The vector to add to this vector
     * @return The resulting vector
     */
    @Nonnull
    public IntVector2 add(@Nonnull IntVector2 v) {
        return new IntVector2(x + v.x, y + v.y);
    }

    /**
     * Subtracts another vector from this vector.
     *
     * @param v The vector to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    public IntVector2 subtract(@Nonnull IntVector2 v) {
        return new IntVector2(x - v.x, y - v.y);
    }

    /**
     * Multiplies this vector by another vector using complex number multiplication.
     *
     * @param v The vector to multiply with vector by
     * @return The resulting vector
     */
    @Nonnull
    public IntVector2 multiply(@Nonnull IntVector2 v) {
        return new IntVector2(x * v.x - y * v.y, x * v.y + y * v.x);
    }

    /**
     * Returns the dot product between this vector and the provided vector {@code v}.
     *
     * @param v The vector to get the dot product between
     * @return The dot product of the two vectors
     */
    public int dot(@Nonnull IntVector2 v) {
        return x * v.x + y * v.y;
    }

    //
    // Clamping
    //

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the minimum vector of
     * @return The minimum vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 2}
     */
    @Nonnull
    @Override
    public IntVector2 min(@Nonnull IntVector v) throws IllegalArgumentException {
        if (v.length() != 2) {
            throw new IllegalArgumentException("The provided vector does not have a length of 2.");
        }

        return new IntVector2(Math.min(x, v.valueAt(0)), Math.min(y, v.valueAt(1)));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the maximum vector of
     * @return The maximum vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 2}
     */
    @Nonnull
    @Override
    public IntVector2 max(@Nonnull IntVector v) throws IllegalArgumentException {
        if (v.length() != 2) {
            throw new IllegalArgumentException("The provided vector does not have a length of 2.");
        }

        return new IntVector2(Math.max(x, v.valueAt(0)), Math.max(y, v.valueAt(1)));
    }

    /**
     * {@inheritDoc}
     *
     * @param min The minimum allowed values
     * @param max The maximum allowed values
     * @return The clamped vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 2}
     */
    @Nonnull
    @Override
    public IntVector2 clamp(@Nonnull IntVector min, @Nonnull IntVector max) throws IllegalArgumentException {
        if (min.length() != 2 || max.length() != 2) {
            throw new IllegalArgumentException("The provided vectors do not have a length of 2.");
        }

        return new IntVector2(
                Math.max(Math.min(x, max.valueAt(0)), min.valueAt(0)),
                Math.max(Math.min(y, max.valueAt(1)), min.valueAt(1))
        );
    }

    /**
     * Returns the minimum vector between this vector and the provided vector {@code v}.
     *
     * @param v The vector to get the minimum vector of
     * @return The minimum vector
     */
    @Nonnull
    public IntVector2 min(@Nonnull IntVector2 v) {
        return new IntVector2(Math.min(x, v.x), Math.min(y, v.y));
    }

    /**
     * Returns the maximum vector between this vector and the provided vector {@code v}.
     *
     * @param v The vector to get the maximum vector of
     * @return The maximum vector
     */
    @Nonnull
    public IntVector2 max(@Nonnull IntVector2 v) {
        return new IntVector2(Math.max(x, v.x), Math.max(y, v.y));
    }

    /**
     * Returns a clamped vector which respects the given boundaries of {@code min} and {@code max}.
     *
     * @param min The minimum allowed values
     * @param max The maximum allowed values
     * @return The clamped vector
     */
    @Nonnull
    public IntVector2 clamp(@Nonnull IntVector2 min, @Nonnull IntVector2 max) {
        return new IntVector2(Math.max(Math.min(x, max.x), min.x), Math.max(Math.min(y, max.y), min.y));
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
    public IntVector2 apply(@Nonnull UnaryOperator<Integer> operator) {
        return new IntVector2(operator.apply(x), operator.apply(y));
    }

    /**
     * {@inheritDoc}
     *
     * @return The negated vector
     */
    @Nonnull
    @Override
    public IntVector2 negate() {
        return new IntVector2(-x, -y);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the distance to
     * @return The distance between this vector and {@code v}
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 2}
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
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 2}
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
    public double distance(@Nonnull IntVector2 v) {
        return subtract(v).magnitude();
    }

    /**
     * Returns the squared distance between this vector and the provided vector.
     *
     * @param v The vector to get the squared distance to
     * @return The squared distance between this vector and {@code v}
     */
    public double distance2(@Nonnull IntVector2 v) {
        return subtract(v).magnitude2();
    }

    /**
     * {@inheritDoc}
     *
     * @return A {@link Vector2} derived from {@code this}
     */
    @Nonnull
    @Override
    public Vector2 toDouble() {
        return new Vector2(x, y);
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
        if (v.length() != 2) return false;

        return x == v.valueAt(0) && y == v.valueAt(1);
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
    public static IntVector2 parseVector(@Nonnull String input) throws NumberFormatException {
        if (!input.startsWith("IntVector2{")) {
            throw new NumberFormatException("The provided string does not represent a IntVector2.");
        }

        final String cleanInput = input.replaceAll("IntVector2\\{|}", "");
        final String[] valueStrings = cleanInput.split(", ");
        final int[] values = new int[2];

        for (final String valueString : valueStrings) {
            final String[] split = valueString.split("=");
            values[switch (split[0]) {
                case "x" -> 0;
                case "y" -> 1;
                default -> throw new NumberFormatException("The provided string does not represent a IntVector2.");
            }] = Integer.parseInt(split[1]);
        }

        return new IntVector2(values);
    }

    /**
     * {@inheritDoc}
     *
     * @return The string representation of this vector
     */
    @Override
    @Nonnull
    public String toString() {
        return "IntVector2{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
