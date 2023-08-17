package civitas.celestis.util.collection;

import civitas.celestis.util.group.Group;
import civitas.celestis.util.group.Groupable;
import jakarta.annotation.Nonnull;

import java.util.ArrayList;
import java.util.Collection;

/**
 * An array list which implements the {@link Groupable} interface.
 *
 * @param <E> The type of element to contain in this list
 */
public class GroupableList<E> extends ArrayList<E> implements Groupable<E> {
    //
    // Constructors
    //

    /**
     * Creates a new groupable list.
     *
     * @param initialCapacity The initial capacity of this list
     */
    public GroupableList(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * Creates a new groupable list.
     */
    public GroupableList() {
    }

    /**
     * Creates a new groupable list.
     *
     * @param c The collection of which to copy element values from
     */
    public GroupableList(@Nonnull Collection<? extends E> c) {
        super(c);
    }

    /**
     * Creates a new groupable list.
     *
     * @param l The listable object of which to copy element values from
     */
    public GroupableList(@Nonnull Listable<E> l) {
        super(l.list());
    }

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
    public Group<E> group() {
        return Group.of((E[]) toArray());
    }
}
