package civitas.celestis.util;

import jakarta.annotation.Nonnull;

import java.util.UUID;

/**
 * A unique object can be identified using a {@link UUID}.
 */
public interface Unique {
    /**
     * Gets the unique identifier of this object.
     *
     * @return The unique identifier of this object
     */
    @Nonnull
    UUID getUniqueId();
}
