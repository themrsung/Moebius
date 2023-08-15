package civitas.celestis.math.vector;

import jakarta.annotation.Nonnull;

import java.util.function.UnaryOperator;

/**
 * A two-dimensional array of {@code long}s.
 *
 * @param <V> Itself (The return value of arithmetic results)
 */
public interface LongVector<V extends LongVector<V>> extends Vector<V> {
    //
    // Properties
    //

    /**
     * Converts this vector into array form.
     *
     * @return The array representation of this vector
     */
    @Nonnull
    long[] values();

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
    double norm2();

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
     * Adds a scalar to each component of this vector.
     *
     * @param s The scalar to add to this vector
     * @return The resulting vector
     */
    @Nonnull
    V add(long s);

    /**
     * Subtracts a scalar from each component of this vector.
     *
     * @param s The scalar to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    V subtract(long s);

    /**
     * Multiplies a scalar to each component of this vector.
     *
     * @param s The scalar to multiply to this vector
     * @return The resulting vector
     */
    @Nonnull
    V multiply(long s);

    /**
     * Divides each component of this vector by a scalar.
     *
     * @param s The scalar to divide this vector by
     * @return The resulting vector
     * @throws ArithmeticException When the denominator {@code s} is zero
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
     *
     * @param v The vector of which to get the distance between
     * @return The Euclidean distance between this vector and the provided vector {@code v}
     */
    double distance(@Nonnull V v);

    /**
     * Returns the squared Euclidean distance between this vector and the provided vector {@code v}.
     *
     * @param v The vector of which to get the squared distance between
     * @return The squared Euclidean distance between this vector and the provided vector {@code v}
     */
    double distance2(@Nonnull V v);

    /**
     * Returns the Manhattan distance between this vector and the provided vector {@code v}.
     *
     * @param v The vector of which to get the Manhattan distance between
     * @return The Manhattan distance between this vector and the provided vector {@code v}
     */
    long distanceManhattan(@Nonnull V v);

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
    Vector<?> normalize();

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
    V transform(@Nonnull UnaryOperator<Long> transformer);
}
