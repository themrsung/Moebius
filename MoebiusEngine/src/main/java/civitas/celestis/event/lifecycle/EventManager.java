package civitas.celestis.event.lifecycle;

import civitas.celestis.event.Event;
import civitas.celestis.event.Listener;
import jakarta.annotation.Nonnull;

/**
 * An event manager. Handles the lifecycle of events and event listeners.
 *
 * @see Event
 * @see Listener
 */
public interface EventManager {
    /**
     * Starts this event manager, instructing it to start processing queued events.
     */
    void start();

    /**
     * Interrupts this event manager, instructing it to terminate after the current iteration.
     */
    void interrupt();

    /**
     * Calls an event, adding it to the event queue.
     *
     * @param event The event to call
     * @param <E>   The type of event to call
     */
    <E extends Event> void call(@Nonnull E event);

    /**
     * Registers an event listener to this event manager.
     *
     * @param listener The listener to register to this event manager
     */
    void register(@Nonnull Listener listener);

    /**
     * Registers multiple listeners to this event manager.
     *
     * @param listeners The iterable object of listeners to register
     */
    void register(@Nonnull Iterable<Listener> listeners);

    /**
     * Unregisters an event listener from this event manager.
     *
     * @param listener The listener to unregister from this event manager
     */
    void unregister(@Nonnull Listener listener);

    /**
     * Unregisters multiple listeners from this event manager.
     *
     * @param listeners The iterable object of listeners to unregister
     */
    void unregister(@Nonnull Iterable<Listener> listeners);

}
