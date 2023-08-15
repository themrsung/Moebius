package civitas.celestis.math.tuple;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

/**
 * A shallowly immutable tuple of objects. This is similar to the Python tuples.
 * <p>
 * Similarly to arrays, tuples have a fixed size which cannot be changed after instantiation.
 * Unlike arrays, the value of a tuple's element cannot be changed.
 * </p>
 * <p>
 * Unlike other data types, tuples do not support the usage of {@code null}.
 * Although using {@code null} will not trigger an exception, keep in mind that
 * tuples were not designed to be null-safe.
 * </p>
 * <p>
 * While tuples can be natively serialized by {@link Tuple#toString()}, there is no
 * built in way of deserializing it. A custom implementation either by an external static method,
 * or a member method of an inheriting class is required for deserialization capability.
 * </p>
 *
 * @param <E> The type of element this tuple should hold
 * @see Pair
 * @see Triple
 * @see Quad
 * @see ArrayTuple
 */
public interface Tuple<E> extends Iterable<E>, Serializable {
    //
    // Factory
    //

    /**
     * Creates a new tuple from a variable number of values.
     *
     * @param values The values to contain
     * @param <E>    The type of element to contain
     * @return A tuple built from the given array of values
     */
    @Nonnull
    @SafeVarargs
    static <E> Tuple<E> of(@Nonnull E... values) {
        return switch (values.length) {
            case 2 -> new Pair<>(values[0], values[1]);
            case 3 -> new Triple<>(values[0], values[1], values[2]);
            case 4 -> new Quad<>(values[0], values[1], values[2], values[3]);
            default -> new ArrayTuple<>(values);
        };
    }

    //
    // Getters
    //

    /**
     * Returns the {@code i}th element of this tuple.
     *
     * @param i The index of element to get
     * @return The element at the specified position
     * @throws IndexOutOfBoundsException When the index {@code i} is out of bounds
     */
    @Nonnull
    E get(int i) throws IndexOutOfBoundsException;

    //
    // Containment
    //

    /**
     * Checks if this tuple contains given element.
     *
     * @param e The element to check for containment
     * @return {@code true} if at least one of this tuple's members is equal to the given element
     */
    boolean contains(@Nonnull E e);

    /**
     * Checks if this tuple contains multiple objects.
     *
     * @param i The iterable object to check for containment
     * @return {@code true} if this tuple contains every element of the iterable
     */
    boolean containsAll(@Nonnull Iterable<E> i);

    //
    // Properties
    //

    /**
     * Returns the length of this tuple.
     *
     * @return The number of elements this tuple contains
     */
    int length();

    //
    // Conversion
    //

    /**
     * Converts this tuple into a list.
     *
     * @return The list representation of this tuple
     */
    @Nonnull
    List<E> toList();

    //
    // Iteration
    //

    /**
     * Returns an iterator of all elements of this tuple.
     *
     * @return An iterator of all elements of this tuple
     */
    @Override
    @Nonnull
    Iterator<E> iterator();

    //
    // Equality
    //

    /**
     * Checks for equality between this tuple and the provided object {@code obj}.
     *
     * @param obj The object to compare to
     * @return {@code true} if the object is also a tuple, and the length and elements are equal
     */
    boolean equals(@Nullable Object obj);

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
