package civitas.celestis.util.group;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.UnaryOperator;

/**
 * A group of elements.
 * Groups are similar to arrays in that they have a fixed size, but different
 * in that they do not support the usage of non-null values and that they are immutable.
 *
 * @param <E> The type of entry to contain
 */
public interface Group<E> extends Iterable<E> {
    //
    // Factory
    //

    /**
     * Given an array of elements, this returns a group constructed from the array of elements.
     *
     * @param elements The elements to contain
     * @param <E>      Type of element to contain
     * @return A group constructed from the provided elements
     * @throws UnsupportedOperationException When a group for the provided size is not defined
     */
    @Nonnull
    @SafeVarargs
    static <E> Group<E> of(@Nonnull E... elements) {
        switch (elements.length) {
            // Pair
            case 2 -> {
                return new Pair<>(elements[0], elements[1]);
            }

            // Tuple
            case 3 -> {
                return new Tuple<>(elements[0], elements[1], elements[2]);
            }

            default ->
                    throw new UnsupportedOperationException("There is no first-party group defined for size " + elements.length + ".");
        }
    }

    //
    // Static Utilities
    //

    /**
     * Given a list of elements, this returns a list of all possible pairs of the list.
     * Self pairs ({@code {A, A}} and duplicate pairs ({@code {A, B}, {B, A}}) are not counted.
     *
     * @param list The list to get permutations of
     * @param <E>  Type of element
     * @return All possible permutations of the list
     */
    @Nonnull
    static <E> List<Pair<E>> pairsOfList(@Nonnull List<E> list) {
        final List<Pair<E>> result = new ArrayList<>();

        for (final E e1 : list) {
            for (final E e2 : list) {
                if (Objects.equals(e1, e2)) continue;

                final Pair<E> pair = new Pair<>(e1, e2);
                if (result.stream().anyMatch(p -> p.equalsIgnoreOrder(pair))) continue;

                result.add(pair);
            }
        }

        return result;
    }

    //
    // Getters
    //

    /**
     * Gets the {@code i}th element of this group.
     *
     * @param i The index of element to get
     * @return The element of given index
     * @throws IndexOutOfBoundsException When the index is out of bounds
     */
    @Nonnull
    E get(int i) throws IndexOutOfBoundsException;

    /**
     * Returns the size of this group.
     *
     * @return The size of this group
     */
    int size();

    //
    // Utility
    //

    /**
     * Checks if given object is a member of this group.
     *
     * @param obj Object to check
     * @return {@code true} if this group contains the given object {@code obj}
     */
    boolean contains(@Nullable Object obj);

    /**
     * Applies given operator to all elements of this group,
     * then returns a new group containing the modified values.
     *
     * @param operator The operator to apply to each element of this group
     * @return The resulting group
     */
    @Nonnull
    Group<E> apply(@Nonnull UnaryOperator<E> operator);

    /**
     * Converts this group into a list.
     *
     * @return The list representation of this group
     */
    @Nonnull
    List<E> toList();

    /**
     * Returns the iterator of this group.
     *
     * @return The iterator of this group
     */
    @Override
    @Nonnull
    default Iterator<E> iterator() {
        return toList().iterator();
    }

    //
    // Equality
    //

    /**
     * Checks for equality between this group and given object.
     *
     * @param obj The object to compare to
     * @return {@code true} if the other object is an instance of {@link Group}, and the components and size are equal
     */
    boolean equals(@Nullable Object obj);

    /**
     * Checks for equality without regards to order.
     *
     * @param other The group to compare to
     * @return {@code true} if every element of the other group is a member of this group, and the sizes are equal
     */
    boolean equalsIgnoreOrder(@Nonnull Group<E> other);

    //
    // Serialization
    //

    /**
     * Serializes this group into a string.
     *
     * @return The string representation of this group
     */
    @Nonnull
    String toString();
}
