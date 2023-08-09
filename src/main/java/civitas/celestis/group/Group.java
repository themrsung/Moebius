package civitas.celestis.group;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.Iterator;
import java.util.List;

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
            case 2 -> {return new Pair<>(elements[0], elements[1]);}
            case 3 -> {return new Tuple<>(elements[0], elements[1], elements[2]);}
            default -> {
                throw new UnsupportedOperationException("There is no defined group of size " + elements.length + ".");
            }
        }
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
