package civitas.celestis.util;

import jakarta.annotation.Nonnull;

import java.util.List;

/**
 * A group of elements.
 *
 * @param <E> The type of element to contain in this group
 */
public interface Group<E> {
    //
    // List Conversion
    //

    /**
     * Converts this group into a list.
     *
     * @return The list representation of this group
     */
    @Nonnull
    List<E> list();
}
