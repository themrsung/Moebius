package civitas.celestis.math.vector;

import jakarta.annotation.Nonnull;

/**
 * A vector which uses the type {@code double}.
 *
 * @param <V> Itself (the parameter and result of various operations)
 * @see Double2
 * @see Double3
 * @see Double4
 */
public interface DoubleVector<V extends DoubleVector<V>> extends Vector<Double, V> {
    //
    // Getters
    //

    /**
     * Returns an array containing the components of this vector in the proper order.
     *
     * @return The array representation of this vector
     */
    @Nonnull
    double[] array();

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
    double normManhattan();

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
    V add(double s);

    /**
     * Subtracts a scalar from this vector.
     *
     * @param s The scalar to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    V subtract(double s);

    /**
     * Multiplies this vector by a scalar.
     *
     * @param s The scalar to multiply this vector by
     * @return The resulting vector
     */
    @Nonnull
    V multiply(double s);

    /**
     * Divides this vector by a scalar.
     *
     * @param s The scalar to divide this vector by
     * @return The resulting vector
     * @throws ArithmeticException When this vector does not support division by zero,
     *                             and the denominator {@code s} is zero
     */
    @Nonnull
    V divide(double s);

    /**
     * Returns the dot product between this vector and the provided vector {@code v}.
     *
     * @param v The vector of which to get the dot product between
     * @return The dot product of the two vectors
     */
    double dot(@Nonnull V v);

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
