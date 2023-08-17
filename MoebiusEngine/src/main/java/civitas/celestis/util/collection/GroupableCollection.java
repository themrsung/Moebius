package civitas.celestis.util.collection;

import civitas.celestis.util.group.Group;
import civitas.celestis.util.group.Groupable;
import jakarta.annotation.Nonnull;

import java.util.Collection;

/**
 * A collection which implements the {@link Groupable} interface.
 *
 * @param <E> The type of element to contain in this collection
 */
public interface GroupableCollection<E> extends Collection<E>, Groupable<E> {
    //
    // Grouping
    //

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    @SuppressWarnings("unchecked")
    default Group<E> group() {
        return Group.of((E[]) toArray());
    }
}
