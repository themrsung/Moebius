package civitas.celestis.util.group;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.UnaryOperator;

/**
 * A group of elements.
 *
 * @param <E> Type of element to hold
 */
public interface Group<E> extends Iterable<E> {
    /**
     * Creates a group of given elements.
     *
     * @param elements Elements to create a group from
     * @param <E>      Type of element to hold in the group
     * @return Group created from specified elements
     */
    @Nonnull
    @SafeVarargs
    static <E> Group<E> of(@Nonnull E... elements) {
        switch (elements.length) {
            case 1 -> {return new Singleton<>(elements[0]);}
            case 2 -> {return new Pair<>(elements[0], elements[1]);}
            case 3 -> {return new Tuple<>(elements[0], elements[1], elements[2]);}
            default -> {
                throw new UnsupportedOperationException("There is no defined group of size " + elements.length + ".");
            }
        }
    }

    /**
     * Gets a list of unique pairs of given list.
     *
     * @param list List to get pairs of
     * @param <E>  Type of element to contain
     * @return A list of unique pairs of the list
     */
    @Nonnull
    static <E> List<Pair<E>> pairsOf(@Nonnull List<E> list) {
        final List<Pair<E>> result = new ArrayList<>();

        for (final E e1 : list) {
            for (final E e2 : list) {
                if (e1.equals(e2)) continue;
                final Pair<E> pair = new Pair<>(e1, e2);

                if (result.stream().anyMatch(p -> p.equalsIgnoreOrder(pair))) continue;

                result.add(pair);
            }
        }

        return result;
    }

    /**
     * Gets the {@code i}th element of this group.
     *
     * @param i Index of element to get
     * @return The element in the specified position
     * @throws IndexOutOfBoundsException When index is out of bounds
     */
    @Nonnull
    E get(int i) throws IndexOutOfBoundsException;

    /**
     * Returns the size of this group.
     *
     * @return The number of elements this group is holding
     */
    int size();

    /**
     * Converts this group into a list.
     *
     * @return List containing the elements of this group
     */
    @Nonnull
    List<E> list();

    /**
     * Checks if this group contains given object.
     *
     * @param obj Object to check
     * @return {@code true} if this group contains given object
     */
    boolean contains(@Nullable Object obj);

    /**
     * Applies given operator to each element of this group, then returns the resulting group.
     *
     * @param operator Operator to apply to each element of this group
     * @return The resulting group
     */
    @Nonnull
    Group<E> apply(@Nonnull UnaryOperator<E> operator);

    /**
     * Returns an iterator of all the elements of this group.
     *
     * @return Iterator of all elements of this group
     */
    @Override
    @Nonnull
    default Iterator<E> iterator() {
        return list().iterator();
    }
}
