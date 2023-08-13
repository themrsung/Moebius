package civitas.celestis.math.natural;

import civitas.celestis.math.Numbers;
import civitas.celestis.math.integer.IntVector;
import civitas.celestis.math.integer.IntVector2;
import civitas.celestis.math.integer.IntVector3;
import civitas.celestis.math.vector.Vector;
import civitas.celestis.math.vector.Vector2;
import jakarta.annotation.Nonnull;

import java.util.function.UnaryOperator;

/**
 * A three-dimensional vector of non-negative numbers.
 */
public class NaturalVector3 extends IntVector3 implements NaturalVector {
    //
    // Constants
    //

    /**
     * A vector with no direction or magnitude.
     * This is also the smallest possible 2D natural vector.
     */
    public static final NaturalVector3 ZERO = new NaturalVector3(IntVector3.ZERO);

    /**
     * The minimum possible value a vector can have without being zero.
     */
    public static final NaturalVector3 MIN_VALUE = new NaturalVector3(IntVector3.MIN_VALUE);

    /**
     * The maximum possible value a vector can have before overflowing.
     */
    public static final NaturalVector3 MAX_VALUE = new NaturalVector3(IntVector3.MAX_VALUE);

    /**
     * The positive X unit vector.
     */
    public static final NaturalVector3 POSITIVE_X = new NaturalVector3(IntVector3.POSITIVE_X);

    /**
     * The positive Y unit vector.
     */
    public static final NaturalVector3 POSITIVE_Y = new NaturalVector3(IntVector3.POSITIVE_Y);

    /**
     * The positive Z unit vector.
     */
    public static final NaturalVector3 POSITIVE_Z = new NaturalVector3(IntVector3.POSITIVE_Z);

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
    public NaturalVector3(int x, int y, int z) {
        super(x, y, z);
        enforceNatural();
    }

    /**
     * Creates a new vector.
     *
     * @param values The values to use
     */
    public NaturalVector3(@Nonnull int[] values) {
        super(values);
        enforceNatural();
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector to copy values from
     */
    public NaturalVector3(@Nonnull Vector v) {
        super(v);
        enforceNatural();
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector to copy values from
     */
    public NaturalVector3(@Nonnull Vector2 v) {
        super(v);
        enforceNatural();
    }

    /**
     * Creates a new vector.
     *
     * @param other The vector to copy values from
     */
    public NaturalVector3(@Nonnull IntVector other) {
        super(other);
        enforceNatural();
    }

    /**
     * Creates a new vector.
     *
     * @param other The vector to copy values from
     */
    public NaturalVector3(@Nonnull IntVector2 other) {
        super(other);
        enforceNatural();
    }

    /**
     * Enforces the non-negative constraint to all components.
     */
    private void enforceNatural() {
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
    public NaturalVector3 add(int s) {
        return new NaturalVector3(super.add(s));
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public NaturalVector3 subtract(int s) {
        return new NaturalVector3(super.subtract(s));
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to multiply this vector by
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public NaturalVector3 multiply(int s) {
        return new NaturalVector3(super.multiply(s));
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
    public NaturalVector3 divide(int s) throws ArithmeticException {
        return new NaturalVector3(super.divide(s));
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
    public NaturalVector3 add(@Nonnull IntVector v) throws IllegalArgumentException {
        return new NaturalVector3(super.add(v));
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
    public NaturalVector3 subtract(@Nonnull IntVector v) throws IllegalArgumentException {
        return new NaturalVector3(super.subtract(v));
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
    public NaturalVector3 multiply(@Nonnull IntVector v) throws UnsupportedOperationException {
        return new NaturalVector3(super.multiply(v));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector on the right of this operation
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 3}
     */
    @Nonnull
    @Override
    public NaturalVector3 cross(@Nonnull IntVector v) throws IllegalArgumentException {
        return new NaturalVector3(super.cross(v));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to add to this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public NaturalVector3 add(@Nonnull IntVector3 v) {
        return new NaturalVector3(super.add(v));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public NaturalVector3 subtract(@Nonnull IntVector3 v) {
        return new NaturalVector3(super.subtract(v));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector on the right of this operation
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public NaturalVector3 cross(@Nonnull IntVector3 v) {
        return new NaturalVector3(super.cross(v));
    }

    //
    // Clamping
    //

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the minimum vector of
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 3}
     */
    @Nonnull
    @Override
    public NaturalVector3 min(@Nonnull IntVector v) throws IllegalArgumentException {
        return new NaturalVector3(super.min(v));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the maximum vector of
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 3}
     */
    @Nonnull
    @Override
    public NaturalVector3 max(@Nonnull IntVector v) throws IllegalArgumentException {
        return new NaturalVector3(super.max(v));
    }

    /**
     * {@inheritDoc}
     *
     * @param min The minimum allowed values
     * @param max The maximum allowed values
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code min} or {@code max}'s length is not {@code 3}
     */
    @Nonnull
    @Override
    public NaturalVector3 clamp(@Nonnull IntVector min, @Nonnull IntVector max) throws IllegalArgumentException {
        return new NaturalVector3(super.clamp(min, max));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the minimum vector of
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public NaturalVector3 min(@Nonnull IntVector3 v) {
        return new NaturalVector3(super.min(v));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the maximum vector of
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public NaturalVector3 max(@Nonnull IntVector3 v) {
        return new NaturalVector3(super.max(v));
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
    public NaturalVector3 clamp(@Nonnull IntVector3 min, @Nonnull IntVector3 max) {
        return new NaturalVector3(super.clamp(min, max));
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
    public NaturalVector3 apply(@Nonnull UnaryOperator<Integer> operator) {
        return new NaturalVector3(super.apply(operator));
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
    public static NaturalVector3 parseVector(@Nonnull String input) throws NumberFormatException {
        if (!input.startsWith("NaturalVector3{")) {
            throw new NumberFormatException("The provided string does not represent a NaturalVector3.");
        }

        final String cleanInput = input.replaceAll("NaturalVector3\\{|}", "");
        final String[] valueStrings = cleanInput.split(", ");
        final int[] values = new int[3];

        for (final String valueString : valueStrings) {
            final String[] split = valueString.split("=");
            values[switch (split[0]) {
                case "x" -> 0;
                case "y" -> 1;
                case "z" -> 2;
                default -> throw new NumberFormatException("The provided string does not represent a NaturalVector3.");
            }] = Integer.parseInt(split[1]);
        }

        return new NaturalVector3(values);
    }

    /**
     * {@inheritDoc}
     *
     * @return The string representation of this vector
     */
    @Override
    @Nonnull
    public String toString() {
        return "NaturalVector3{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

}
