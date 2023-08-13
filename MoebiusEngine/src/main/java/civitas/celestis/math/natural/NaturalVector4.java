package civitas.celestis.math.natural;

import civitas.celestis.math.Numbers;
import civitas.celestis.math.integer.IntVector;
import civitas.celestis.math.integer.IntVector4;
import civitas.celestis.math.vector.Vector;
import civitas.celestis.math.vector.Vector4;
import jakarta.annotation.Nonnull;

import java.util.function.UnaryOperator;

/**
 * A four-dimensional vector of non-negative numbers.
 */
public class NaturalVector4 extends IntVector4 implements NaturalVector {
    //
    // Constants
    //

    /**
     * A vector with no direction or magnitude.
     * This is also the smallest possible 2D natural vector.
     */
    public static final NaturalVector4 ZERO = new NaturalVector4(IntVector4.ZERO);

    /**
     * The minimum possible value a vector can have without being zero.
     */
    public static final NaturalVector4 MIN_VALUE = new NaturalVector4(IntVector4.MIN_VALUE);

    /**
     * The maximum possible value a vector can have before overflowing.
     */
    public static final NaturalVector4 MAX_VALUE = new NaturalVector4(IntVector4.MAX_VALUE);

    /**
     * The positive W unit vector.
     */
    public static final NaturalVector4 POSITIVE_W = new NaturalVector4(IntVector4.POSITIVE_W);

    /**
     * The positive X unit vector.
     */
    public static final NaturalVector4 POSITIVE_X = new NaturalVector4(IntVector4.POSITIVE_X);

    /**
     * The positive Y unit vector.
     */
    public static final NaturalVector4 POSITIVE_Y = new NaturalVector4(IntVector4.POSITIVE_Y);

    /**
     * The positive Z unit vector.
     */
    public static final NaturalVector4 POSITIVE_Z = new NaturalVector4(IntVector4.POSITIVE_Z);

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
    public NaturalVector4(int w, int x, int y, int z) {
        super(w, x, y, z);
        enforceNatural();
    }

    /**
     * Creates a new vector.
     *
     * @param values The values to use
     */
    public NaturalVector4(@Nonnull int[] values) {
        super(values);
        enforceNatural();
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector to copy values from
     */
    public NaturalVector4(@Nonnull Vector v) {
        super(v);
        enforceNatural();
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector to copy values from
     */
    public NaturalVector4(@Nonnull Vector4 v) {
        super(v);
        enforceNatural();
    }

    /**
     * Creates a new vector.
     *
     * @param other The vector to copy values from
     */
    public NaturalVector4(@Nonnull IntVector other) {
        super(other);
        enforceNatural();
    }

    /**
     * Creates a new vector.
     *
     * @param other The vector to copy values from
     */
    public NaturalVector4(@Nonnull IntVector4 other) {
        super(other);
        enforceNatural();
    }

    /**
     * Enforces the non-negative constraint to all components.
     */
    private void enforceNatural() {
        Numbers.requireNonNegative(w);
        Numbers.requireNonNegative(x);
        Numbers.requireNonNegative(y);
        Numbers.requireNonNegative(z);
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
    public NaturalVector4 add(int s) {
        return new NaturalVector4(super.add(s));
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public NaturalVector4 subtract(int s) {
        return new NaturalVector4(super.subtract(s));
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to multiply this vector by
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public NaturalVector4 multiply(int s) {
        return new NaturalVector4(super.multiply(s));
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to divide this vector by
     * @return The resulting vector
     * @throws ArithmeticException When the denominator {@code s} is {@code 0}
     */
    @Nonnull
    @Override
    public NaturalVector4 divide(int s) throws ArithmeticException {
        return new NaturalVector4(super.divide(s));
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
    public NaturalVector4 add(@Nonnull IntVector v) throws IllegalArgumentException {
        return new NaturalVector4(super.add(v));
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
    public NaturalVector4 subtract(@Nonnull IntVector v) throws IllegalArgumentException {
        return new NaturalVector4(super.subtract(v));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to multiply this vector by
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 4}
     */
    @Nonnull
    @Override
    public NaturalVector4 multiply(@Nonnull IntVector v) throws IllegalArgumentException {
        return new NaturalVector4(super.multiply(v));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to add to this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public NaturalVector4 add(@Nonnull IntVector4 v) {
        return new NaturalVector4(super.add(v));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public NaturalVector4 subtract(@Nonnull IntVector4 v) {
        return new NaturalVector4(super.subtract(v));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to multiply this vector by
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public NaturalVector4 multiply(@Nonnull IntVector4 v) {
        return new NaturalVector4(super.multiply(v));
    }

    //
    // Clamping
    //

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the minimum vector of
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 4}
     */
    @Nonnull
    @Override
    public NaturalVector4 min(@Nonnull IntVector v) throws IllegalArgumentException {
        return new NaturalVector4(super.min(v));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the maximum vector of
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 4}
     */
    @Nonnull
    @Override
    public NaturalVector4 max(@Nonnull IntVector v) throws IllegalArgumentException {
        return new NaturalVector4(super.max(v));
    }

    /**
     * {@inheritDoc}
     *
     * @param min The minimum allowed values
     * @param max The maximum allowed values
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code min} or {@code max}'s length is not {@code 4}
     */
    @Nonnull
    @Override
    public NaturalVector4 clamp(@Nonnull IntVector min, @Nonnull IntVector max) throws IllegalArgumentException {
        return new NaturalVector4(super.clamp(min, max));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the minimum vector of
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public NaturalVector4 min(@Nonnull IntVector4 v) {
        return new NaturalVector4(super.min(v));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the maximum vector of
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public NaturalVector4 max(@Nonnull IntVector4 v) {
        return new NaturalVector4(super.max(v));
    }

    /**
     * {@inheritDoc}
     *
     * @param min The minimum allowed values
     * @param max The maximum allowed values
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public NaturalVector4 clamp(@Nonnull IntVector4 min, @Nonnull IntVector4 max) {
        return new NaturalVector4(super.clamp(min, max));
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
    public NaturalVector4 apply(@Nonnull UnaryOperator<Integer> operator) {
        return new NaturalVector4(super.apply(operator));
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
    public static NaturalVector4 parseVector(@Nonnull String input) throws NumberFormatException {
        if (!input.startsWith("NaturalVector4{")) {
            throw new NumberFormatException("The provided string does not represent a NaturalVector4.");
        }

        final String cleanInput = input.replaceAll("NaturalVector4\\{|}", "");
        final String[] valueStrings = cleanInput.split(", ");
        final int[] values = new int[4];

        for (final String valueString : valueStrings) {
            final String[] split = valueString.split("=");
            values[switch (split[0]) {
                case "w" -> 0;
                case "x" -> 1;
                case "y" -> 2;
                case "z" -> 3;
                default -> throw new NumberFormatException("The provided string does not represent a NaturalVector3.");
            }] = Integer.parseInt(split[1]);
        }

        return new NaturalVector4(values);
    }

    /**
     * {@inheritDoc}
     *
     * @return The string representation of this vector
     */
    @Override
    @Nonnull
    public String toString() {
        return "NaturalVector4{" +
                "w=" + w +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
