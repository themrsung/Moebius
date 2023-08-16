package civitas.celestis.math.vector;

import jakarta.annotation.Nonnull;

/**
 * A vector which uses the type {@code float}.
 *
 * @param <V> Itself (the parameter and result of various operations)
 * @param <D> The {@code double} vector corresponding to this vector's size
 * @see Long2
 * @see Long3
 * @see Long4
 */
public interface LongVector<V extends LongVector<V, D>, D extends DoubleVector<D>> extends Vector<Long, V> {
    //
    // Getters
    //

    /**
     * Returns an array containing the components of this vector in the proper order.
     *
     * @return The array representation of this vector
     */
    @Nonnull
    long[] array();

    //
    // Properties
    //

    /**
     * Returns the Euclidean norm (the magnitude) of this vector.
     *
     * @return The Euclidean norm of this vector
     */
    double norm();

    /**
     * Returns the squared Euclidean norm (the squared magnitude) of this vector.
     *
     * @return The squared Euclidean norm of this vector
     */
    long norm2();

    /**
     * Returns the Manhattan norm of this vector.
     *
     * @return The Manhattan norm of this vector
     */
    long normManhattan();

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
    V add(long s);

    /**
     * Subtracts a scalar from this vector.
     *
     * @param s The scalar to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    V subtract(long s);

    /**
     * Multiplies this vector by a scalar.
     *
     * @param s The scalar to multiply this vector by
     * @return The resulting vector
     */
    @Nonnull
    V multiply(long s);

    /**
     * Divides this vector by a scalar.
     *
     * @param s The scalar to divide this vector by
     * @return The resulting vector
     * @throws ArithmeticException When this vector does not support division by zero,
     *                             and the denominator {@code s} is zero
     */
    @Nonnull
    V divide(long s);

    /**
     * Returns the dot product between this vector and the provided vector {@code v}.
     *
     * @param v The vector of which to get the dot product between
     * @return The dot product of the two vectors
     */
    long dot(@Nonnull V v);

    //
    // Distance
    //

    /**
     * Returns the Euclidean distance between this vector and the provided vector {@code v}.
     * This does not create an intermediate vector instance, but inlines the calculations.
     *
     * @param v The vector of which to get the distance to
     * @return The Euclidean distance to the provided vector {@code v}
     */
    double distance(@Nonnull V v);

    /**
     * Returns the squared Euclidean distance between this vector and the provided vector {@code v}.
     * This does not create an intermediate vector instance, but inlines the calculations.
     *
     * @param v The vector of which to get the squared distance to
     * @return The squared Euclidean distance to the provided vector {@code v}
     */
    long distance2(@Nonnull V v);

    /**
     * Returns the Manhattan distance between this vector and the provided vector {@code v}.
     * This does not create an intermediate vector instance, but inlines the calculations.
     *
     * @param v The vector of which to get the distance to
     * @return The Manhattan distance to the provided vector {@code v}
     */
    long distanceManhattan(@Nonnull V v);

    //
    // Normalization
    //

    /**
     * Normalizes this vector to have a Euclidean norm (magnitude) of {@code 1}.
     *
     * @return The normalized vector of this vector
     * @throws ArithmeticException When the Euclidean norm (magnitude) is zero
     */
    @Nonnull
    D normalize();
}
