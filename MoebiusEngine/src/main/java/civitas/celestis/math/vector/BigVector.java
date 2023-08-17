package civitas.celestis.math.vector;

import jakarta.annotation.Nonnull;

/**
 * A vector which uses non-primitive number types.
 *
 * @param <N> The type of number to use
 * @param <V> Itself (the parameter and result of various operations)
 */
public interface BigVector<N extends Number, V extends BigVector<N, V>> extends Vector<N, V> {
    //
    // Getters
    //

    /**
     * Returns an array containing the components of this vector in the proper order.
     *
     * @return The array representation of this vector
     */
    @Nonnull
    N[] array();

    //
    // Properties
    //

    /**
     * Returns the Euclidean norm (the magnitude) of this vector.
     *
     * @return The Euclidean norm of this vector
     */
    @Nonnull
    Number norm();

    /**
     * Returns the squared Euclidean norm (the squared magnitude) of this vector.
     *
     * @return The squared Euclidean norm of this vector
     */
    @Nonnull
    N norm2();

    /**
     * Returns the Manhattan norm of this vector.
     *
     * @return The Manhattan norm of this vector
     */
    @Nonnull
    N normManhattan();

    //
    // Arithmetic
    //

    /**
     * Adds a scalar to this vector.
     *
     * @param n The scalar to add to this vector
     * @return The resulting vector
     */
    @Nonnull
    V add(@Nonnull N n);

    /**
     * Subtracts a scalar from this vector.
     *
     * @param n The scalar to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    V subtract(@Nonnull N n);

    /**
     * Multiplies this vector by a scalar.
     *
     * @param n The scalar to multiply this vector by
     * @return The resulting vector
     */
    @Nonnull
    V multiply(@Nonnull N n);

    /**
     * Divides this vector by a scalar.
     *
     * @param n The scalar to divide this vector by
     * @return The resulting vector
     * @throws ArithmeticException When this vector does not support division by zero,
     *                             and the denominator {@code s} is zero
     */
    @Nonnull
    V divide(@Nonnull N n);

    /**
     * Returns the dot product between this vector and the provided vector {@code v}.
     *
     * @param v The vector of which to get the dot product between
     * @return The dot product of the two vectors
     */
    @Nonnull
    N dot(@Nonnull V v);

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
    @Nonnull
    Number distance(@Nonnull V v);

    /**
     * Returns the squared Euclidean distance between this vector and the provided vector {@code v}.
     * This does not create an intermediate vector instance, but inlines the calculations.
     *
     * @param v The vector of which to get the squared distance to
     * @return The squared Euclidean distance to the provided vector {@code v}
     */
    @Nonnull
    N distance2(@Nonnull V v);

    /**
     * Returns the Manhattan distance between this vector and the provided vector {@code v}.
     * This does not create an intermediate vector instance, but inlines the calculations.
     *
     * @param v The vector of which to get the distance to
     * @return The Manhattan distance to the provided vector {@code v}
     */
    @Nonnull
    N distanceManhattan(@Nonnull V v);

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
    Vector<?, ?> normalize();
}
