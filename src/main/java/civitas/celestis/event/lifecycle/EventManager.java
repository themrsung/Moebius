package civitas.celestis.event.lifecycle;

import civitas.celestis.event.Event;
import civitas.celestis.event.Listener;
import jakarta.annotation.Nonnull;

import java.util.Collection;

/**
 * An event manager.
 * Event managers handle the lifecycle of events.
 */
public interface EventManager {
    /**
     * Starts processing events in the event queue.
     */
    void start();

    /**
     * Stops processing events in the event queue.
     */
    void stop();

    /**
     * Calls an event to be handled by subscribing listeners.
     * The event will be added to the event queue for processing.
     *
     * @param event The event to be called
     * @param <E>   The type of event to be called
     */
    <E extends Event> void call(@Nonnull E event);

    /**
     * Registers a listener to this event manager.
     *
     * @param listener The listener to register to this event manager
     */
    void register(@Nonnull Listener listener);

    /**
     * Registers a collection of listeners to this event manager.
     *
     * @param listeners The collection of listeners to register to this event manager
     */
    void register(@Nonnull Collection<Listener> listeners);

    /**
     * Unregisters a listener from this event manager.
     *
     * @param listener The listener to unregister from this event manager
     */
    void unregister(@Nonnull Listener listener);

    /**
     * Unregisters a collection of listeners from this event manager.
     *
     * @param listeners The collection of listeners to unregister from this event manager
     */
    void unregister(@Nonnull Collection<Listener> listeners);
}
