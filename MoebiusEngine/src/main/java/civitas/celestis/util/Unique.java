package civitas.celestis.util;

import jakarta.annotation.Nonnull;

import java.util.UUID;

/**
 * A unique object can be identified by a unique identifier.
 * This is implemented using {@link UUID} for this interface.
 */
public interface Unique {
    /**
     * Returns the unique identifier of this object.
     *
     * @return The unique identifier of this object
     */
    @Nonnull
    UUID getUniqueId();
}
