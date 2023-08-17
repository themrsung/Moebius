package civitas.celestis.util.collection;

import jakarta.annotation.Nonnull;

import java.io.Serializable;
import java.util.Collection;

/**
 * An interface for objects which are not a collection, but can be converted to a collection.
 *
 * @param <E> The type of element this group is holding
 * @see Collection
 */
public interface Collectable<E> extends Serializable {
    /**
     * Converts this object into a collection.
     *
     * @return The collection representation of this object
     * @see Collection
     */
    @Nonnull
    Collection<E> collection();
}
