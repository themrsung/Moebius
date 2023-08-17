package civitas.celestis.util.collection;

import jakarta.annotation.Nonnull;

import java.util.Collection;
import java.util.List;

/**
 * An interface for objects which are not a list, but can be converted to a list.
 *
 * @param <E> The type of element this group is holding
 * @see List
 */
public interface Listable<E> extends Collectable<E> {
    /**
     * Converts this object into a list.
     *
     * @return The list representation of this object
     * @see List
     */
    @Nonnull
    List<E> list();

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    default Collection<E> collection() {
        return list();
    }
}