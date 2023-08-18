package civitas.celestis.util.group;

import civitas.celestis.util.array.SafeArray;
import civitas.celestis.util.array.TypeSafe;
import jakarta.annotation.Nonnull;

/**
 * An interface for objects which are not groups, but can be converted to a group.
 *
 * @param <E> The type of element this object is holding
 * @see Group
 */
public interface Groupable<E> extends TypeSafe<E> {
    /**
     * Converts this object into a group.
     *
     * @return The group representation of this object
     * @see Group
     */
    @Nonnull
    Group<E> group();

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    default SafeArray<E> safeArray() {
        return SafeArray.fromGroup(group());
    }
}
