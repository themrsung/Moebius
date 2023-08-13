package civitas.celestis.math.natural;

import civitas.celestis.math.Numbers;
import civitas.celestis.math.integer.IntVector;
import civitas.celestis.math.integer.MutableIntVector;
import civitas.celestis.math.vector.Vector;
import jakarta.annotation.Nonnull;

import java.util.Arrays;
import java.util.function.UnaryOperator;

/**
 * A mutable vector which only accepts natural numbers and {@code 0}.
 */
public class MutableNaturalVector extends MutableIntVector implements NaturalVector {
    //
    // Constructors
    //

    /**
     * Creates a new vector.
     *
     * @param initialLength The initial length of this vector
     */
    public MutableNaturalVector(int initialLength) {
        super(initialLength);
        enforceNatural();
    }

    /**
     * Creates a new vector.
     *
     * @param values The initial values of this vector
     */
    public MutableNaturalVector(@Nonnull int... values) {
        super(values);
        enforceNatural();
    }

    /**
     * Creates a new vector.
     *
     * @param v The {@link Vector} to copy values from
     */
    public MutableNaturalVector(@Nonnull Vector v) {
        super(v);
        enforceNatural();
    }

    /**
     * Creates a new vector.
     *
     * @param other The other vector to copy component values from
     */
    public MutableNaturalVector(@Nonnull IntVector other) {
        super(other);
        enforceNatural();
    }

    /**
     * Enforces the non-negative constraint on all components of this vector.
     */
    private void enforceNatural() {
        for (final int value : values()) {
            Numbers.requireNonNegative(value);
        }
    }

    //
    // Setters
    //

    /**
     * {@inheritDoc}
     *
     * @param i     Index to set
     * @param value Value to set to
     * @throws IndexOutOfBoundsException When the index {@code i} is out of bounds
     */
    @Override
    public void setValueAt(int i, int value) throws IndexOutOfBoundsException {
        super.setValueAt(i, (int) Numbers.requireNonNegative(value));
    }

    /**
     * {@inheritDoc}
     *
     * @param value Value to append
     */
    @Override
    public void append(int value) {
        super.append((int) Numbers.requireNonNegative(value));
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
    public MutableNaturalVector add(int s) {
        return new MutableNaturalVector(super.add(s));
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public MutableNaturalVector subtract(int s) {
        return new MutableNaturalVector(super.subtract(s));
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to multiply this vector by
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public MutableNaturalVector multiply(int s) {
        return new MutableNaturalVector(super.multiply(s));
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
    public MutableNaturalVector divide(int s) throws ArithmeticException {
        return new MutableNaturalVector(super.divide(s));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to add to this vector
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s
     *                                  length is different from this vector's length
     */
    @Nonnull
    @Override
    public MutableNaturalVector add(@Nonnull IntVector v) throws IllegalArgumentException {
        return new MutableNaturalVector(super.add(v));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to subtract from this vector
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s
     *                                  length is different from this vector's length
     */
    @Nonnull
    @Override
    public MutableNaturalVector subtract(@Nonnull IntVector v) throws IllegalArgumentException {
        return new MutableNaturalVector(super.subtract(v));
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
    public MutableNaturalVector multiply(@Nonnull IntVector v) throws UnsupportedOperationException {
        return new MutableNaturalVector(super.multiply(v));
    }

    //
    // Clamping
    //

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the minimum vector of
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s
     *                                  length is different from this vector's length
     */
    @Nonnull
    @Override
    public MutableNaturalVector min(@Nonnull IntVector v) throws IllegalArgumentException {
        return new MutableNaturalVector(super.min(v));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the maximum vector of
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s
     *                                  length is different from this vector's length
     */
    @Nonnull
    @Override
    public MutableNaturalVector max(@Nonnull IntVector v) throws IllegalArgumentException {
        return new MutableNaturalVector(super.max(v));
    }

    /**
     * {@inheritDoc}
     *
     * @param min The minimum allowed values
     * @param max The maximum allowed values
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code min} or {@code max}'s
     *                                  length is different from this vector's length
     */
    @Nonnull
    @Override
    public MutableNaturalVector clamp(@Nonnull IntVector min, @Nonnull IntVector max) throws IllegalArgumentException {
        return new MutableNaturalVector(super.clamp(min, max));
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
    public MutableNaturalVector apply(@Nonnull UnaryOperator<Integer> operator) {
        return new MutableNaturalVector(super.apply(operator));
    }

    //
    // Serialization
    //

    /**
     * Deserializes a string to parse a vector.
     *
     * @param input Input string to parse to a vector
     * @return The parsed vector
     * @throws NumberFormatException When the string's format is invalid
     */
    @Nonnull
    public static MutableNaturalVector parseVector(@Nonnull String input) throws NumberFormatException {
        if (!input.startsWith("MutableNaturalVector{")) {
            throw new NumberFormatException("Given string does not represent a MutableNaturalVector.");
        }

        final String cleanInput = input.replaceAll("MutableNaturalVector\\{values=|}|\\[|]", "");
        final String[] valueStrings = cleanInput.split(", ");

        final int[] values = new int[valueStrings.length];

        for (int i = 0; i < valueStrings.length; i++) {
            values[i] = Integer.parseInt(valueStrings[i]);
        }

        return new MutableNaturalVector(values);
    }

    /**
     * {@inheritDoc}
     *
     * @return The string representation of this vector
     */
    @Override
    @Nonnull
    public String toString() {
        return "MutableNaturalVector{" +
                "values=" + Arrays.toString(Arrays.copyOf(values(), length())) +
                '}';
    }
}
