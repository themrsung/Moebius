package civitas.celestis.util;

import jakarta.annotation.Nonnull;

/**
 * Represents a copyable object.
 *
 * @param <O> Type of object {@link Copyable#copy()} returns
 */
public interface Copyable<O> {
    /**
     * Returns a deep copy of this object.
     * Any changes made to the return value of this method will not be reflected in this instance.
     *
     * @return A deep copy of this object
     */
    @Nonnull
    O copy();
}
