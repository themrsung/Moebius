package civitas.celestis.math.vector;

import jakarta.annotation.Nonnull;

/**
 * A vector which uses the type {@code float}.
 *
 * @param <V> Itself (the parameter and result of various operations)
 * @see Float2
 * @see Float3
 * @see Float4
 */
public interface FloatVector<V extends FloatVector<V>> extends Vector<Float, V> {
    //
    // Getters
    //

    /**
     * Returns an array containing the components of this vector in the proper order.
     *
     * @return The array representation of this vector
     */
    @Nonnull
    float[] array();

    //
    // Properties
    //

    /**
     * Returns whether this vector contains {@link Double#NaN}.
     *
     * @return {@code true} if this vector is not a number
     */
    boolean isNaN();

    /**
     * Returns whether every component of this vector is finite.
     *
     * @return {@code true} if this vector is finite
     */
    boolean isFinite();

    /**
     * Returns whether at least one component of this vector is infinite.
     *
     * @return {@code true} if this vector is infinite
     */
    boolean isInfinite();

    /**
     * Returns the Euclidean norm (the magnitude) of this vector.
     *
     * @return The Euclidean norm of this vector
     */
    float norm();

    /**
     * Returns the squared Euclidean norm (the squared magnitude) of this vector.
     *
     * @return The squared Euclidean norm of this vector
     */
    float norm2();

    /**
     * Returns the Manhattan norm of this vector.
     *
     * @return The Manhattan norm of this vector
     */
    float normManhattan();

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
    V add(float s);

    /**
     * Subtracts a scalar from this vector.
     *
     * @param s The scalar to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    V subtract(float s);

    /**
     * Multiplies this vector by a scalar.
     *
     * @param s The scalar to multiply this vector by
     * @return The resulting vector
     */
    @Nonnull
    V multiply(float s);

    /**
     * Divides this vector by a scalar.
     *
     * @param s The scalar to divide this vector by
     * @return The resulting vector
     * @throws ArithmeticException When this vector does not support division by zero,
     *                             and the denominator {@code s} is zero
     */
    @Nonnull
    V divide(float s);

    /**
     * Returns the dot product between this vector and the provided vector {@code v}.
     *
     * @param v The vector of which to get the dot product between
     * @return The dot product of the two vectors
     */
    float dot(@Nonnull V v);

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
    float distance(@Nonnull V v);

    /**
     * Returns the squared Euclidean distance between this vector and the provided vector {@code v}.
     * This does not create an intermediate vector instance, but inlines the calculations.
     *
     * @param v The vector of which to get the squared distance to
     * @return The squared Euclidean distance to the provided vector {@code v}
     */
    float distance2(@Nonnull V v);

    /**
     * Returns the Manhattan distance between this vector and the provided vector {@code v}.
     * This does not create an intermediate vector instance, but inlines the calculations.
     *
     * @param v The vector of which to get the distance to
     * @return The Manhattan distance to the provided vector {@code v}
     */
    float distanceManhattan(@Nonnull V v);

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
    V normalize();
}
