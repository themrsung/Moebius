package civitas.celestis.math.vector;

import civitas.celestis.util.group.Group;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.io.Serializable;
import java.util.function.UnaryOperator;

/**
 * A mathematical tuple of numbers.
 *
 * @param <N> The type of number this vector uses
 * @param <V> Itself (the parameter and result of various operations)
 * @see DoubleVector
 * @see FloatVector
 * @see LongVector
 * @see IntVector
 */
public interface Vector<N extends Number, V extends Vector<N, V>> extends Group<N>, Serializable {
    //
    // Properties
    //

    /**
     * Returns whether this vector is zero.
     *
     * @return {@code true} if every component of this vector is equal to zero
     */
    boolean isZero();

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
    // Negation
    //

    /**
     * Negates this vector, then returns the resulting vector.
     *
     * @return The negation of this vector
     */
    @Nonnull
    V negate();

    //
    // Transformation
    //

    /**
     * Applies the provided function {@code f} to all components of this vector,
     * then returns the resulting vector.
     *
     * @param f The function to apply to each component of this vector
     * @return The resulting vector
     */
    @Nonnull
    V transform(@Nonnull UnaryOperator<N> f);

    //
    // Clamping
    //

    /**
     * Given a boundary vector {@code v}, this returns the minimum vector between this vector and {@code v}.
     * This is achieved by taking the minimum of each corresponding component, then packaging those
     * minimum components into a new vector.
     *
     * @param v The boundary vector of which to compare to
     * @return The minimum vector between the two vectors
     */
    @Nonnull
    V min(@Nonnull V v);

    /**
     * Given a boundary vector {@code v}, this returns the maximum vector between this vector and {@code v}.
     * This is achieved by taking the maximum of each corresponding component, then packaging those
     * minimum components into a new vector.
     *
     * @param v The boundary vector of which to compare to
     * @return The maximum vector between the two vectors
     */
    @Nonnull
    V max(@Nonnull V v);

    /**
     * Given two boundary vectors {@code min} and {@code max}, this returns a clamped vector of this vector
     * which respects the given boundaries.
     *
     * @param min The minimum boundary vector
     * @param max The maximum boundary vector
     * @return The clamped vector which respects the given boundaries
     */
    @Nonnull
    V clamp(@Nonnull V min, @Nonnull V max);

    //
    // Equality
    //

    /**
     * Checks for equality between this vector and the provided object {@code obj}.
     *
     * @param obj The object to compare to
     * @return {@code true} if the object is also a vector, and the length
     * and component values are equal
     */
    boolean equals(@Nullable Object obj);

    /**
     * Checks for equality between this vector and the provided vector {@code v}.
     *
     * @param v The vector to compare to
     * @return {@code true} if the component values are equal
     */
    boolean equals(@Nonnull V v);

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
