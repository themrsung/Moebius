package civitas.celestis.util;

import jakarta.annotation.Nonnull;

/**
 * A nameable object has a name of an arbitrary type {@link N}.
 *
 * @param <N> The type of name this object has
 */
public interface Nameable<N> {
    /**
     * Returns the name of this object.
     *
     * @return The name of this object
     */
    @Nonnull
    N getName();
}
