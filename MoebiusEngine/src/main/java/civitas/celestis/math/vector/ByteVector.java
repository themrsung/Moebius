package civitas.celestis.math.vector;

import jakarta.annotation.Nonnull;

/**
 * A vector which uses the type {@code short}.
 *
 * @param <V> Itself (the parameter and result of various operations)
 * @param <F> The {@code float} vector corresponding to the size of this vector
 */
public interface ByteVector<V extends ByteVector<V, F>, F extends FloatVector<F>> extends Vector<Byte, V> {
    //
    // Getters
    //

    /**
     * Returns an array containing the components of this vector in the proper order.
     *
     * @return The array representation of this vector
     */
    @Nonnull
    byte[] array();

    //
    // Properties
    //

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
    byte normManhattan();

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
    V add(byte s);

    /**
     * Subtracts a scalar from this vector.
     *
     * @param s The scalar to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    V subtract(byte s);

    /**
     * Multiplies this vector by a scalar.
     *
     * @param s The scalar to multiply this vector by
     * @return The resulting vector
     */
    @Nonnull
    V multiply(byte s);

    /**
     * Divides this vector by a scalar.
     *
     * @param s The scalar to divide this vector by
     * @return The resulting vector
     * @throws ArithmeticException When this vector does not support division by zero,
     *                             and the denominator {@code s} is zero
     */
    @Nonnull
    V divide(byte s);

    /**
     * Returns the dot product between this vector and the provided vector {@code v}.
     *
     * @param v The vector of which to get the dot product between
     * @return The dot product of the two vectors
     */
    byte dot(@Nonnull V v);

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
    F normalize();
}
