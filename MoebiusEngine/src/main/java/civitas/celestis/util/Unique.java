package civitas.celestis.util;

import jakarta.annotation.Nonnull;

/**
 * A unique object can be identified by a certain key.
 * This key must be unique within the scope of this object's lifecycle.
 *
 * @param <K> The key of which to identify this object by
 */
public interface Unique<K> {
    /**
     * Returns the unique identifier of this object.
     *
     * @return The unique identifier of this object
     */
    @Nonnull
    K getUniqueId();
}
