package civitas.celestis.util.tuple;

import civitas.celestis.util.group.Group;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.function.Function;

/**
 * An immutable group of elements.
 * A tuple's length or component values cannot be changed after instantiation.
 * <p>
 * Moebius tuples are very similar to Python tuples in that they are shallowly immutable.
 * Tuples can be iterated by a for-each loop, or by a for-i loop. (using {@link Tuple#get(int)})
 * </p>
 * <p>
 * Since tuples cannot be resized or modified, nested tuples can effectively create an unmodifiable
 * n-dimensional matrix. (although this is not very efficient, and lacks the functionality of dedicated matrices)
 * </p>
 *
 * @param <E> The type of element to hold in this tuple
 * @param <T> Itself (the parameter or result of type-specific operations)
 * @see Pair
 * @see Triple
 * @see Quad
 * @see BinaryPair
 * @see TernaryTriple
 * @see QuaternaryQuad
 * @see ArrayTuple
 */
public interface Tuple<E, T extends Tuple<E, T>> extends Group<E>, Iterable<E> {
    //
    // Getters
    //

    /**
     * Returns the {@code i}th component of this tuple.
     *
     * @param i The index of component to get
     * @return The component at the specified index
     * @throws IndexOutOfBoundsException When the index {@code i} is out of bounds
     *                                   ({@code i < 0 || i >= length()})
     */
    @Nonnull
    E get(int i) throws IndexOutOfBoundsException;

    //
    // Containment
    //

    /**
     * Checks if this tuple contains the specified object {@code obj}.
     *
     * @param obj The object to check for containment
     * @return {@code true} if this tuple contains the given object
     */
    boolean contains(@Nullable Object obj);

    /**
     * Checks if this tuple contains multiple elements.
     *
     * @param i The iterable object to check for containment
     * @return {@code true} if this object contains every element of the iterable
     */
    boolean containsAll(@Nonnull Iterable<E> i);

    //
    // Properties
    //

    /**
     * Returns the length of this tuple.
     * This only counts the number of components of type {@link E}.
     *
     * @return The number of components this tuple has
     */
    int length();

    //
    // Transformation
    //

    /**
     * Applies the provided function {@code f} to every element of this tuple,
     * then returns the transformed tuple.
     *
     * @param f The function to apple to each element of this tuple
     * @return The resulting tuple
     */
    @Nonnull
    T transform(@Nonnull Function<? super E, E> f);

    /**
     * Maps this tuple into another tuple of a different type.
     *
     * @param mapper The mapper function to apply to each element of this tuple
     * @param <F>    The type of element to contain in the new tuple
     * @return The mapped tuple
     */
    @Nonnull
    <F> Tuple<F, ?> map(@Nonnull Function<? super E, ? extends F> mapper);

    //
    // Equality
    //

    /**
     * Checks for equality with another object.
     * Equality is compared differently across different type of tuples.
     *
     * @param obj The object to compare to
     * @return {@code true} if the other object is also a tuple,
     * and the length and component values match
     */
    boolean equals(@Nullable Object obj);

    /**
     * Checks for equality with another tuple of the same type.
     *
     * @param t The tuple to compare to
     * @return {@code true} if the length and component values are equal
     */
    boolean equals(@Nullable T t);

    //
    // Serialization
    //

    /**
     * Serializes this tuple into a string.
     *
     * @return The string representation of this tuple
     */
    @Nonnull
    String toString();
}
