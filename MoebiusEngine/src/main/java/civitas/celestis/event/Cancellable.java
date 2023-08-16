package civitas.celestis.event;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.UUID;

/**
 * A cancellable event. Cancellable events have a flag variable {@link Cancellable#cancelled},
 * which when set to {@code true} marks this event as cancelled.
 * <p>
 * Event listeners should respect this flag and not process the event.
 * Although most listeners obey this conventions, certain listeners may ignore the
 * cancellation flag and process the event regardless.
 * </p>
 *
 * @see Event
 */
public class Cancellable extends Event {
    //
    // Constructors
    //

    /**
     * Creates a new event.
     */
    public Cancellable() {
        this.cancelled = false;
    }

    /**
     * Creates a new event.
     *
     * @param uniqueId The unique identifier of this event
     */
    public Cancellable(@Nonnull UUID uniqueId) {
        super(uniqueId);
        this.cancelled = false;
    }

    /**
     * Creates a new event.
     *
     * @param cause The cause of this event
     * @param <E>   The type of cause of this event
     */
    public <E extends Event> Cancellable(@Nullable E cause) {
        super(cause);
        this.cancelled = false;
    }

    /**
     * Creates a new event.
     *
     * @param uniqueId The unique identifier of this event
     * @param cause    The cause of this event
     */
    public Cancellable(@Nonnull UUID uniqueId, @Nullable Event cause) {
        super(uniqueId, cause);
        this.cancelled = false;
    }

    //
    // Variables
    //

    /**
     * The cancellation flag of this event.
     */
    protected boolean cancelled;

    //
    // Getters
    //

    /**
     * Returns whether this event has been flagged as cancelled.
     *
     * @return {@code true} if this event has been cancelled
     */
    public final boolean isCancelled() {
        return cancelled;
    }

    //
    // Setters
    //

    /**
     * Sets the cancellation status of this event.
     *
     * @param cancelled The cancellation status to assign to this event
     */
    public final void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
