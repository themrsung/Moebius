package civitas.celestis.object;

import jakarta.annotation.Nonnull;

import java.util.UUID;

/**
 * A unique object is identifiable by a unique identifier.
 */
public interface Unique {
    /**
     * Gets the unique identifier of this object.
     *
     * @return The unique ID of this object
     */
    @Nonnull
    UUID getUniqueId();
}
