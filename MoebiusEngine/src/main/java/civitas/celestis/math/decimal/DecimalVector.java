package civitas.celestis.math.decimal;

import civitas.celestis.math.vector.Vector;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.function.UnaryOperator;

/**
 * <p>
 * A two-dimensional number of {@link BigDecimal}.
 * </p>
 * <p>
 * <b>Decimal vectors are not designed to be fast or efficient.</b>
 * The usage of {@link BigDecimal} makes decimal vectors too slow for
 * real-time applications such as games.
 * </p>
 * <p>
 * These vectors are more suitable for physics simulators or financial modeling
 * due to their ability to have an arbitrary amount of precision.
 * </p>
 */
public interface DecimalVector {
    //
    // Getters
    //

    /**
     * Returns the component at the specified index.
     *
     * @param i Index of component to get
     * @return The component at the given index
     * @throws IndexOutOfBoundsException If the index is out of bounds ({@code i >= length() || i < 0})
     */
    @Nonnull
    BigDecimal valueAt(int i) throws IndexOutOfBoundsException;

    /**
     * Return a list which contains the values of this vector.
     * Changes in the return value of this method will not be reflected in the original vector.
     *
     * @return The list representation of this vector
     */
    @Nonnull
    List<BigDecimal> values();

    //
    // Properties
    //

    /**
     * Returns the magnitude of this vector.
     * @param context The mathematical context to use in the square root operation
     *
     * @return The magnitude of this vector
     */
    @Nonnull
    BigDecimal magnitude(@Nonnull MathContext context);

    /**
     * Returns the squared magnitude of this vector.
     *
     * @return The squared magnitude of this vector
     */
    @Nonnull
    BigDecimal magnitude2();

    /**
     * Returns the length of this vector.
     *
     * @return The number of components this vector contains
     */
    int length();

    //
    // Arithmetic
    //

    /**
     * Adds a scalar to this vector.
     *
     * @param s The scalar to add to this vector
     * @return The resulting vector
     */
    @Nonnull
    DecimalVector add(@Nonnull BigDecimal s);

    /**
     * Subtracts this vector by a scalar.
     *
     * @param s The scalar to subtract this vector by
     * @return The resulting vector
     */
    @Nonnull
    DecimalVector subtract(@Nonnull BigDecimal s);

    /**
     * Multiplies this vector by a scalar.
     *
     * @param s The scalar to multiply this vector by
     * @return The resulting vector
     */
    @Nonnull
    DecimalVector multiply(@Nonnull BigDecimal s);

    /**
     * Divides this vector by a scalar.
     *
     * @param s The scalar to divide this vector by
     * @param context The mathematical context to use in the division operation
     * @return The resulting vector
     * @throws ArithmeticException When the denominator {@code s} is zero
     */
    @Nonnull
    DecimalVector divide(@Nonnull BigDecimal s, @Nonnull MathContext context) throws ArithmeticException;

    /**
     * Adds another vector to this vector.
     *
     * @param v The vector to add to this vector
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length
     *                                  is different from this vector's length
     */
    @Nonnull
    DecimalVector add(@Nonnull DecimalVector v) throws IllegalArgumentException;

    /**
     * Subtracts another vector from this vector.
     *
     * @param v The vector to subtract from this vector
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length
     *                                  is different from this vector's length
     */
    @Nonnull
    DecimalVector subtract(@Nonnull DecimalVector v) throws IllegalArgumentException;

    /**
     * Multiplies this vector by another vector.
     *
     * @param v The vector to multiply this vector by
     * @return The resulting vector
     * @throws IllegalArgumentException      When the provided vector {@code v}'s length
     *                                       is different from this vector's length
     * @throws UnsupportedOperationException When there is no default definition of multiplication
     *                                       for this vector
     */
    @Nonnull
    DecimalVector multiply(@Nonnull DecimalVector v) throws IllegalArgumentException, UnsupportedOperationException;

    /**
     * Returns the dot product between this vector and the provided vector {@code v}.
     *
     * @param v The vector to get the dot product between
     * @return The dot product of the two vectors
     * @throws IllegalArgumentException When the provided vector {@code v}'s length
     *                                  is different from this vector's length
     */
    @Nonnull
    BigDecimal dot(@Nonnull DecimalVector v) throws IllegalArgumentException;

    //
    // Clamping
    //

    /**
     * Returns the minimum vector between this vector and the provided vector {@code v}.
     *
     * @param v The vector to get the minimum vector of
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length
     *                                  is different from this vector's length
     */
    @Nonnull
    DecimalVector min(@Nonnull DecimalVector v) throws IllegalArgumentException;

    /**
     * Returns the maximum vector between this vector and the provided vector {@code v}.
     *
     * @param v The vector to get the maximum vector of
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length
     *                                  is different from this vector's length
     */
    @Nonnull
    DecimalVector max(@Nonnull DecimalVector v) throws IllegalArgumentException;

    /**
     * Returns the clamped vector of this vector to respect the given boundaries of {@code min} and {@code max}.
     *
     * @param min The minimum allowed values
     * @param max The minimum allowed values
     * @return The resulting vector
     * @throws IllegalArgumentException When either of the given vectors have a different length from this vector
     */
    @Nonnull
    DecimalVector clamp(@Nonnull DecimalVector min, @Nonnull DecimalVector max) throws IllegalArgumentException;

    //
    // Utility
    //

    /**
     * Applies given operator to all components of this vector, then returns the resulting vector.
     *
     * @param operator The operator to apply to each component of this vector
     * @return The resulting vector
     */
    @Nonnull
    DecimalVector apply(@Nonnull UnaryOperator<BigDecimal> operator);

    /**
     * Normalizes this vector and returns the normalized vector.
     *
     * @param context The mathematical context to use for square root and division
     * @return The normalized vector of this vector
     * @throws UnsupportedOperationException When the magnitude of this vector is exactly zero
     */
    @Nonnull
    DecimalVector normalize(@Nonnull MathContext context) throws UnsupportedOperationException;

    /**
     * Returns the negated vector of this vector.
     *
     * @return The negation of this vector
     */
    @Nonnull
    DecimalVector negate();

    /**
     * Returns the distance between this vector and the provided vector.
     *
     * @param v The vector to get the distance to
     * @param context The mathematical context to use when calculating the magnitude of the difference vector
     * @return The distance between this vector and {@code v}
     * @throws IllegalArgumentException When the provided vector has a different length compared to this vector
     */
    @Nonnull
    BigDecimal distance(@Nonnull DecimalVector v, @Nonnull MathContext context) throws IllegalArgumentException;

    /**
     * Returns the squared distance between this vector and the provided vector.
     *
     * @param v The vector to get the squared distance to
     * @return The squared distance between this vector and {@code v}
     * @throws IllegalArgumentException When the provided vector has a different length compared to this vector
     */
    @Nonnull
    BigDecimal distance2(@Nonnull DecimalVector v) throws IllegalArgumentException;

    /**
     * Returns the {@code double} representation of this vector.
     *
     * @return A {@code double} {@link Vector} constructed from the values of this vector
     */
    @Nonnull
    Vector doubleValue();

    //
    // Equality
    //

    /**
     * Checks for equality between this vector and the given object.
     * This operation ensures that the equality operator will work reliably
     * between different instances of {@link DecimalVector}.
     *
     * @param obj The object to compare to
     * @return {@code true} if the given object is an instance of {@link DecimalVector},
     * and the components and length are both equal
     */
    boolean equals(@Nullable Object obj);

    //
    // Serialization
    //

    /**
     * Serializes this vector into a string.
     *
     * @return The string representation of this vector
     */
    @Nonnull
    String toString();
}
