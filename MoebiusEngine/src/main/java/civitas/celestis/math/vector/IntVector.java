package civitas.celestis.math.vector;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.io.Serializable;
import java.util.function.UnaryOperator;

/**
 * A two-dimensional array of {@code int}s.
 *
 * @param <V> Itself (The return value of arithmetic results)
 */
public interface IntVector<V extends IntVector<V>> extends Vector<V> {
    //
    // Properties
    //

    /**
     * Converts this vector into array form.
     *
     * @return The array representation of this vector
     */
    @Nonnull
    int[] values();

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
    V add(int s);

    /**
     * Subtracts a scalar from each component of this vector.
     *
     * @param s The scalar to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    V subtract(int s);

    /**
     * Multiplies a scalar to each component of this vector.
     *
     * @param s The scalar to multiply to this vector
     * @return The resulting vector
     */
    @Nonnull
    V multiply(int s);

    /**
     * Divides each component of this vector by a scalar.
     *
     * @param s The scalar to divide this vector by
     * @return The resulting vector
     * @throws ArithmeticException When the denominator {@code s} is zero
     */
    @Nonnull
    V divide(int s);

    /**
     * Returns the dot product between this vector and the provided vector {@code v}.
     *
     * @param v The vector of which to get the dot product between
     * @return The dot product of the two vectors
     */
    int dot(@Nonnull V v);

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
    FloatVector<?> normalize();

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
    V transform(@Nonnull UnaryOperator<Integer> transformer);

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
