package civitas.celestis.math.vector;

import jakarta.annotation.Nonnull;

import java.util.function.UnaryOperator;

/**
 * A one-dimensional array of {@code float}s.
 *
 * @param <V> Itself (The return value of arithmetic results)
 */
public interface FloatVector<V extends FloatVector<V>> extends Vector<V> {
    //
    // Properties
    //

    /**
     * Returns whether this vector contains a {@link Double#NaN} value.
     *
     * @return {@code true} if at least one of the components is not a number
     */
    boolean isNaN();

    /**
     * Returns whether this vector is finite.
     * This returns {@code true} if all components of this vector are finite.
     *
     * @return {@code true} if all components of this vector are finite
     */
    boolean isFinite();

    /**
     * Returns whether this vector is infinite.
     * This returns {@code true} if at least one component of this vector is infinite.
     *
     * @return {@code true} if at least one component of this vector if infinite
     */
    boolean isInfinite();

    /**
     * Converts this vector into array form.
     *
     * @return The array representation of this vector
     */
    @Nonnull
    float[] values();

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
     * Adds a scalar to each component of this vector.
     *
     * @param s The scalar to add to this vector
     * @return The resulting vector
     */
    @Nonnull
    V add(float s);

    /**
     * Subtracts a scalar from each component of this vector.
     *
     * @param s The scalar to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    V subtract(float s);

    /**
     * Multiplies a scalar to each component of this vector.
     *
     * @param s The scalar to multiply to this vector
     * @return The resulting vector
     */
    @Nonnull
    V multiply(float s);

    /**
     * Divides each component of this vector by a scalar.
     *
     * @param s The scalar to divide this vector by
     * @return The resulting vector
     * @throws ArithmeticException If this vector does not allow division by zero, and
     *                             the denominator {@code s} is zero
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
     * Returns the distance between this vector and the provided vector {@code v}.
     *
     * @param v The vector of which to get the distance between
     * @return The distance between this vector and the provided vector {@code v}
     */
    float distance(@Nonnull V v);

    /**
     * Returns the squared distance between this vector and the provided vector {@code v}.
     *
     * @param v The vector of which to get the squared distance between
     * @return The squared distance between this vector and the provided vector {@code v}
     */
    float distance2(@Nonnull V v);

    /**
     * Returns the Manhattan distance between this vector and the provided vector {@code v}.
     *
     * @param v The vector of which to get the Manhattan distance between
     * @return The Manhattan distance between this vector and the provided vector {@code v}
     */
    float distanceManhattan(@Nonnull V v);

    //
    // Normalization
    //

    /**
     * Returns the normalized vector of this vector.
     * The special case for zero vectors depends on the implementation.
     *
     * @return The normalized vector of this vector
     * @throws ArithmeticException If this vector does not handle the case for zero
     *                             magnitude vectors, and the magnitude of this vector is zero
     */
    @Nonnull
    V normalize();

    //
    // Transformation
    //

    /**
     * Transforms this vector, then returns the resulting vector.
     * The provided transformer function is applied to all components of this vector
     *
     * @param transformer The function to apply to each component of this vector
     * @return The resulting vector
     */
    @Nonnull
    V transform(@Nonnull UnaryOperator<Float> transformer);
}
