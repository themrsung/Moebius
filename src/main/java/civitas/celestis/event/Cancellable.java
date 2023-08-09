package civitas.celestis.event;

/**
 * Cancellable events can be cancelled by lower-priority listeners.
 * Higher-priority listeners should respect this cancellation and not process the event.
 */
public interface Cancellable extends Event {
    /**
     * Gets whether this event is cancelled.
     * @return {@code true} if this event has been cancelled
     */
    boolean isCancelled();

    /**
     * Sets whether this event is cancelled.
     * @param cancelled {@code true} to cancel this event
     */
    void setCancelled(boolean cancelled);
}
