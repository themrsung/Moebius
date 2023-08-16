package civitas.celestis.event;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.UUID;

/**
 * An event. Events can be called to an event manager in order to be processed.
 *
 * @see Handleable
 * @see Listener
 * @see EventHandler
 * @see HandlerPriority
 * @see Cancellable
 */
public abstract class Event implements Handleable {
    //
    // Constructors
    //

    /**
     * Creates a new event with a random unique identifier and no cause.
     */
    public Event() {
        this.uniqueId = UUID.randomUUID();
        this.cause = null;
    }

    /**
     * Creates a new event by specifying a unique identifier.
     *
     * @param uniqueId The unique identifier of this event
     */
    public Event(@Nonnull UUID uniqueId) {
        this.uniqueId = uniqueId;
        this.cause = null;
    }

    /**
     * Creates a new event with a random unique identifier.
     *
     * @param cause The cause of this event
     */
    public Event(@Nullable Handleable cause) {
        this.uniqueId = UUID.randomUUID();
        this.cause = cause;
    }

    /**
     * Creates a new event.
     *
     * @param uniqueId The unique identifier of this event
     * @param cause    The cause of this event
     */
    public Event(@Nonnull UUID uniqueId, @Nullable Handleable cause) {
        this.uniqueId = uniqueId;
        this.cause = cause;
    }

    //
    // Variables
    //

    /**
     * The unique identifier of this event.
     */
    @Nonnull
    protected final UUID uniqueId;

    /**
     * The cause of this event.
     */
    @Nullable
    protected final Handleable cause;

    //
    // Getters
    //

    /**
     * Returns the unique identifier of this event.
     *
     * @return The unique identifier of this event
     */
    @Override
    @Nonnull
    public final UUID getUniqueId() {
        return uniqueId;
    }

    /**
     * Returns the cause of this event.
     * If no cause was specified, this will return {@code null}.
     *
     * @return The cause of this event if specified, {@code null} if not
     */
    @Override
    @Nullable
    public final Handleable getCause() {
        return cause;
    }

    /**
     * Returns whether this event has a cause.
     *
     * @return {@code true} if the cause of this event has been specified
     */
    @Override
    public final boolean hasCause() {
        return cause != null;
    }

    //
    // Serialization
    //

    /**
     * Serializes this event into a string.
     * This method can be customized to represent the properties of a specific event.
     *
     * @return The string representation of this event
     */
    @Nonnull
    public String toString() {
        return getClass().getName() + "{" +
                "uniqueId=" + uniqueId + ", " +
                "cause=" + cause +
                +'}';
    }
}
