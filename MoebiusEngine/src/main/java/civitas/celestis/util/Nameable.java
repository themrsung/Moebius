package civitas.celestis.util;

import jakarta.annotation.Nonnull;

/**
 * An object which has a unique, unmodifiable name.
 * @param <N> The type of name this object has
 */
public interface Nameable<N> {
    /**
     * Returns the name of this object.
     * @return The name of this object
     */
    @Nonnull
    N getName();
}
