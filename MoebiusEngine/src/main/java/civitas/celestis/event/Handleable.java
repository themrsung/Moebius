package civitas.celestis.event;

import civitas.celestis.util.Unique;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.UUID;

/**
 * A marker interface which marks a class as handleable by an event manager.
 */
public interface Handleable extends Unique<UUID> {
    //
    // Properties
    //

    /**
     * Returns the cause of this handleable object.
     *
     * @return The cause of this handleable object
     */
    @Nullable
    Handleable getCause();

    /**
     * Returns whether this handleable object has a cause.
     *
     * @return {@code true} if the cause of this handleable has been specified
     */
    default boolean hasCause() {
        return getCause() != null;
    }

    //
    // Serialization
    //

    /**
     * Serializes this handleable object into a string.
     *
     * @return The string representation of this object
     */
    @Nonnull
    String toString();
}
