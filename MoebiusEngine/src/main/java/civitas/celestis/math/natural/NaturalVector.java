package civitas.celestis.math.natural;

import civitas.celestis.math.integer.IntVector;
import civitas.celestis.math.vector.Vector;
import jakarta.annotation.Nonnull;

import java.util.function.UnaryOperator;

/**
 * <p>
 * A multidimensional natural number which has a direction and magnitude.
 * This is essentially an {@link IntVector} with non-negative constraints enforced.
 * </p>
 * <p>
 * While mathematically incorrect, {@code 0} is still allowed in natural vectors
 * due to their importance in a variety of calculations.
 * </p>
 */
public interface NaturalVector extends IntVector {
    //
    // Factory
    //

    /**
     * Creates a new vector.
     *
     * @param values Value to create the vector from
     * @return The constructed vector
     */
    @Nonnull
    static NaturalVector of(@Nonnull int... values) {
        return switch (values.length) {
            case 2 -> new NaturalVector2(values);
            case 3 -> new NaturalVector3(values);
            case 4 -> new NaturalVector4(values);
            default -> new MutableNaturalVector(values);
        };
    }

    /**
     * Creates a new vector from a {@code double} vector.
     *
     * @param v {@link Vector} object to use
     * @return The constructed vector
     */
    @Nonnull
    static NaturalVector fromDouble(@Nonnull Vector v) {
        return switch (v.length()) {
            case 2 -> new NaturalVector2(v);
            case 3 -> new NaturalVector3(v);
            case 4 -> new NaturalVector4(v);
            default -> new MutableNaturalVector(v);
        };
    }

    /**
     * Deserializes a string into a vector.
     *
     * @param input Input string to parse
     * @return The parsed vector
     * @throws NumberFormatException When the string is not a vector
     */
    @Nonnull
    static NaturalVector parseVector(@Nonnull String input) throws NumberFormatException {
        try {return NaturalVector2.parseVector(input);} catch (final NumberFormatException ignored) {}
        try {return NaturalVector3.parseVector(input);} catch (final NumberFormatException ignored) {}
        try {return NaturalVector4.parseVector(input);} catch (final NumberFormatException ignored) {}
        try {return MutableNaturalVector.parseVector(input);} catch (final NumberFormatException ignored) {}
        throw new NumberFormatException("Given string is not a vector.");
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
    NaturalVector add(int s);

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    NaturalVector subtract(int s);

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to multiply this vector by
     * @return The resulting vector
     */
    @Nonnull
    @Override
    NaturalVector multiply(int s);

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to divide this vector by
     * @return The resulting vector
     * @throws ArithmeticException When the denominator {@code s} is zero
     */
    @Nonnull
    @Override
    NaturalVector divide(int s) throws ArithmeticException;

    /**
     * {@inheritDoc}
     *
     * @param v The vector to add to this vector
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is different from this vector's length
     */
    @Nonnull
    @Override
    NaturalVector add(@Nonnull IntVector v) throws IllegalArgumentException;

    /**
     * {@inheritDoc}
     *
     * @param v The vector to subtract from this vector
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is different from this vector's length
     */
    @Nonnull
    @Override
    NaturalVector subtract(@Nonnull IntVector v) throws IllegalArgumentException;

    /**
     * {@inheritDoc}
     *
     * @param v The vector to multiply this vector by
     * @return The resulting vector
     * @throws IllegalArgumentException      When the provided vector {@code v}'s length is different from this vector's length
     * @throws UnsupportedOperationException When there is no default multiplication operation defined for this vector
     */
    @Nonnull
    @Override
    NaturalVector multiply(@Nonnull IntVector v) throws IllegalArgumentException, UnsupportedOperationException;

    //
    // Clamping
    //

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the minimum vector of
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is different from this vector's length
     */
    @Nonnull
    @Override
    NaturalVector min(@Nonnull IntVector v) throws IllegalArgumentException;

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the maximum vector of
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is different from this vector's length
     */
    @Nonnull
    @Override
    NaturalVector max(@Nonnull IntVector v) throws IllegalArgumentException;

    /**
     * {@inheritDoc}
     *
     * @param min The minimum allowed values
     * @param max The maximum allowed values
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code min} or {@code max}'s length
     *                                  is different from this vector's length
     */
    @Nonnull
    @Override
    NaturalVector clamp(@Nonnull IntVector min, @Nonnull IntVector max) throws IllegalArgumentException;

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
    NaturalVector apply(@Nonnull UnaryOperator<Integer> operator);
}
