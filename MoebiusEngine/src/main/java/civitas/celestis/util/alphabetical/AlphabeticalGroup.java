package civitas.celestis.util.alphabetical;

import jakarta.annotation.Nonnull;

/**
 * A superinterface for classes which use alphabetical notation to store
 * multiple objects of the same type.
 *
 * @param <E> The type of element to hold
 */
public interface AlphabeticalGroup<E> {
    /**
     * Gets the {@code i}th element of this object.
     *
     * @param i The index of element to get
     * @return The element at given index
     * @throws IndexOutOfBoundsException When the index is out of bounds
     */
    @Nonnull
    E get(int i) throws IndexOutOfBoundsException;

    /**
     * Returns an array containing the elements of this object.
     *
     * @return The array representation of this object
     */
    @Nonnull
    E[] toArray();

    /**
     * Returns the size of this object.
     *
     * @return The number of components this object has
     */
    int size();
}
