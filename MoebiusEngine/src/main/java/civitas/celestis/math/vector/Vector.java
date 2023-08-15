package civitas.celestis.math.vector;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.io.Serializable;

/**
 * A two-dimensional array of numbers.
 *
 * @param <V> This vector (the result of arithmetic operations)
 */
public interface Vector<V extends Vector<V>> extends Comparable<V>, Serializable {
    //
    // Arithmetic
    //

    /**
     * Adds another vector to this vector.
     *
     * @param v The vector to add to this vector
     * @return The resulting vector
     */
    @Nonnull
    V add(@Nonnull V v);

    /**
     * Subtracts another vector from this vector.
     *
     * @param v The vector to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    V subtract(@Nonnull V v);

    //
    // Properties
    //

    /**
     * Returns whether this vector is equal to the zero vector of its type.
     * This returns {@code true} if all components of this vector is equal to {@code 0}.
     *
     * @return {@code true} if all components of this vector is equal to {@code 0}
     */
    boolean isZero();

    //
    // Negation
    //

    /**
     * Returns the negated vector of this vector.
     *
     * @return The negation of this vector
     */
    @Nonnull
    V negate();

    //
    // Clamping
    //

    /**
     * Returns the minimum vector between this vector and the provided vector {@code v}.
     * The {@code min} operation is applied to each component of the two vectors.
     *
     * @param v The vector to compare to
     * @return The minimum vector between the two vectors
     */
    @Nonnull
    V min(@Nonnull V v);

    /**
     * Returns the maximum vector between this vector and the provided vector {@code v}.
     * The {@code max} operation is applied to each component of the two vectors.
     *
     * @param v The vector to compare to
     * @return The maximum vector between the two vectors
     */
    @Nonnull
    V max(@Nonnull V v);

    /**
     * Returns the clamped vector of this vector which respects the given boundaries of {@code min} and {@code max}.
     *
     * @param min The minimum allowed vector value
     * @param max The maximum allowed vector value
     * @return The clamped vector
     */
    @Nonnull
    V clamp(@Nonnull V min, @Nonnull V max);

    //
    // Numeric Conversion
    //

    /**
     * Returns the value of this vector as a {@code byte}.
     *
     * @return The {@code byte} representation of this vector
     */
    byte byteValue();

    /**
     * Returns the value of this vector as a {@code short}.
     *
     * @return The {@code short} representation of this vector
     */
    short shortValue();

    /**
     * Returns the value of this vector as an {@code int}.
     *
     * @return The {@code int} representation of this vector
     */
    int intValue();

    /**
     * Returns the value of this vector as a {@code long}.
     *
     * @return The {@code long} representation of this vector
     */
    long longValue();

    /**
     * Returns the value of this vector as a {@code float}.
     *
     * @return The {@code float} representation of this vector
     */
    float floatValue();

    /**
     * Returns the value of this vector as a {@code double}.
     *
     * @return The {@code double} representation of this vector
     */
    double doubleValue();

    //
    // Miscellaneous
    //

    /**
     * Returns the deep copy of this vector.
     * This is meaningless and only consumes additional resources if this vector is an immutable vector.
     *
     * @return The deep copy of this vector
     */
    @Nonnull
    V copy();

    //
    // Equality
    //

    /**
     * Checks for equality between this vector and the provided object {@code obj}.
     *
     * @param obj The object to compare to
     * @return {@code true} if this vector is considered equal to the object
     */
    boolean equals(@Nullable Object obj);

    /**
     * Checks for equality between this vector and another vector.
     *
     * @param v The vector to compare to
     * @return {@code true} if this vector is equal to the other vector {@code v}
     */
    boolean equals(@Nonnull V v);

    //
    // Comparison
    //

    /**
     * Compares this vector to the other vector.
     * The return value of this method depends on the implementation.
     *
     * @param v The vector to compare to
     * @return {@code 0} if the vectors are equal, {@code 1} if this vector
     * is considered larger, {@code -1} otherwise
     */
    int compareTo(@Nonnull V v);

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
