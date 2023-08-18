package civitas.celestis.util.collection;

import civitas.celestis.util.array.SafeArray;
import civitas.celestis.util.array.TypeSafe;
import jakarta.annotation.Nonnull;

import java.util.Collection;

/**
 * An interface for objects which are not a collection, but can be converted to a collection.
 *
 * @param <E> The type of element this group is holding
 * @see Collection
 */
public interface Collectable<E> extends TypeSafe<E> {
    /**
     * Converts this object into a collection.
     *
     * @return The collection representation of this object
     * @see Collection
     */
    @Nonnull
    Collection<E> collection();

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    default SafeArray<E> safeArray() {
        return SafeArray.fromCollection(collection());
    }
}
