package civitas.celestis.math.natural;

import civitas.celestis.math.Numbers;
import civitas.celestis.math.integer.IntVector;
import civitas.celestis.math.integer.IntVector2;
import civitas.celestis.math.vector.Vector;
import civitas.celestis.math.vector.Vector2;
import jakarta.annotation.Nonnull;

import java.util.function.UnaryOperator;

/**
 * A two-dimensional vector of non-negative numbers.
 */
public class NaturalVector2 extends IntVector2 implements NaturalVector {
    //
    // Constants
    //

    /**
     * A vector with no direction or magnitude.
     * This is also the smallest possible 2D natural vector.
     */
    public static final NaturalVector2 ZERO = new NaturalVector2(IntVector2.ZERO);

    /**
     * The maximum possible value a vector can have before overflowing.
     */
    public static final NaturalVector2 MAX_VALUE = new NaturalVector2(IntVector2.MAX_VALUE);

    /**
     * The positive X unit vector.
     */
    public static final NaturalVector2 POSITIVE_X = new NaturalVector2(IntVector2.POSITIVE_X);

    /**
     * The positive Y unit vector.
     */
    public static final NaturalVector2 POSITIVE_Y = new NaturalVector2(IntVector2.POSITIVE_Y);

    //
    // Constructors
    //

    /**
     * Creates a new vector.
     *
     * @param x The X component of this vector
     * @param y The Y component of this vector
     */
    public NaturalVector2(int x, int y) {
        super(x, y);
        enforceNatural();
    }

    /**
     * Creates a new vector.
     *
     * @param values The values to use
     */
    public NaturalVector2(@Nonnull int[] values) {
        super(values);
        enforceNatural();
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector to copy values from
     */
    public NaturalVector2(@Nonnull Vector v) {
        super(v);
        enforceNatural();
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector to copy values from
     */
    public NaturalVector2(@Nonnull Vector2 v) {
        super(v);
        enforceNatural();
    }

    /**
     * Creates a new vector.
     *
     * @param other The vector to copy values from
     */
    public NaturalVector2(@Nonnull IntVector other) {
        super(other);
        enforceNatural();
    }

    /**
     * Creates a new vector.
     *
     * @param other The vector to copy values from
     */
    public NaturalVector2(@Nonnull IntVector2 other) {
        super(other);
        enforceNatural();
    }

    /**
     * Enforces the non-negative constraint to all components.
     */
    private void enforceNatural() {
        Numbers.requireNonNegative(x);
        Numbers.requireNonNegative(y);
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
    public NaturalVector2 add(int s) {
        return new NaturalVector2(super.add(s));
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public NaturalVector2 subtract(int s) {
        return new NaturalVector2(super.subtract(s));
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to multiply this vector by
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public NaturalVector2 multiply(int s) {
        return new NaturalVector2(super.multiply(s));
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
    public NaturalVector2 divide(int s) throws ArithmeticException {
        return new NaturalVector2(super.divide(s));
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
    public NaturalVector2 add(@Nonnull IntVector v) throws IllegalArgumentException {
        return new NaturalVector2(super.add(v));
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
    public NaturalVector2 subtract(@Nonnull IntVector v) throws IllegalArgumentException {
        return new NaturalVector2(super.subtract(v));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to multiply this vector by
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 2}
     */
    @Nonnull
    @Override
    public NaturalVector2 multiply(@Nonnull IntVector v) throws IllegalArgumentException {
        return new NaturalVector2(super.multiply(v));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to add to this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public NaturalVector2 add(@Nonnull IntVector2 v) {
        return new NaturalVector2(super.add(v));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public NaturalVector2 subtract(@Nonnull IntVector2 v) {
        return new NaturalVector2(super.subtract(v));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to multiply with vector by
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public NaturalVector2 multiply(@Nonnull IntVector2 v) {
        return new NaturalVector2(super.multiply(v));
    }

    //
    // Clamping
    //

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the minimum vector of
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 2}
     */
    @Nonnull
    @Override
    public NaturalVector2 min(@Nonnull IntVector v) throws IllegalArgumentException {
        return new NaturalVector2(super.min(v));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the maximum vector of
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 2}
     */
    @Nonnull
    @Override
    public NaturalVector2 max(@Nonnull IntVector v) throws IllegalArgumentException {
        return new NaturalVector2(super.max(v));
    }

    /**
     * {@inheritDoc}
     *
     * @param min The minimum allowed values
     * @param max The maximum allowed values
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code min} or {@code max}'s length is not {@code 2}
     */
    @Nonnull
    @Override
    public NaturalVector2 clamp(@Nonnull IntVector min, @Nonnull IntVector max) throws IllegalArgumentException {
        return new NaturalVector2(super.clamp(min, max));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the minimum vector of
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public NaturalVector2 min(@Nonnull IntVector2 v) {
        return new NaturalVector2(super.min(v));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the maximum vector of
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public NaturalVector2 max(@Nonnull IntVector2 v) {
        return new NaturalVector2(super.max(v));
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
    public NaturalVector2 clamp(@Nonnull IntVector2 min, @Nonnull IntVector2 max) {
        return new NaturalVector2(super.clamp(min, max));
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
    public NaturalVector2 apply(@Nonnull UnaryOperator<Integer> operator) {
        return new NaturalVector2(super.apply(operator));
    }

    /**
     * {@inheritDoc}
     *
     * @param rads The angle of rotation to apply in radians
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public NaturalVector2 rotate(double rads) {
        return new NaturalVector2(super.rotate(rads));
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
    public static NaturalVector2 parseVector(@Nonnull String input) throws NumberFormatException {
        if (!input.startsWith("NaturalVector2{")) {
            throw new NumberFormatException("The provided string does not represent a NaturalVector2.");
        }

        final String cleanInput = input.replaceAll("NaturalVector2\\{|}", "");
        final String[] valueStrings = cleanInput.split(", ");
        final int[] values = new int[2];

        for (final String valueString : valueStrings) {
            final String[] split = valueString.split("=");
            values[switch (split[0]) {
                case "x" -> 0;
                case "y" -> 1;
                default -> throw new NumberFormatException("The provided string does not represent a NaturalVector2.");
            }] = Integer.parseInt(split[1]);
        }

        return new NaturalVector2(values);
    }

    /**
     * {@inheritDoc}
     *
     * @return The string representation of this vector
     */
    @Override
    @Nonnull
    public String toString() {
        return "NaturalVector2{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
