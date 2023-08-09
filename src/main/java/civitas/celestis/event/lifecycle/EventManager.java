package civitas.celestis.event.lifecycle;

import civitas.celestis.event.Event;
import civitas.celestis.event.Listener;
import jakarta.annotation.Nonnull;

import java.util.Collection;

/**
 * An event manager handles the lifecycle of events and event listeners.
 */
public interface EventManager {
    /**
     * Starts processing events.
     */
    void start();

    /**
     * Stops processing events.
     */
    void stop();

    /**
     * Calls an event to be handled by this manager.
     *
     * @param event Event to be handled
     * @param <E>   Type of event to be handled
     */
    <E extends Event> void call(@Nonnull E event);

    /**
     * Registers a listener to this event manager.
     *
     * @param listener Listener to register
     */
    void register(@Nonnull Listener listener);

    /**
     * Registers multiple listeners to this event manager.
     *
     * @param listeners Collection of listeners to register
     */
    void register(@Nonnull Collection<Listener> listeners);

    /**
     * Unregisters a listener from this event manager.
     *
     * @param listener Listener to unregister
     */
    void unregister(@Nonnull Listener listener);

    /**
     * Unregisters multiple listeners from this event manager.
     *
     * @param listeners Collection of listeners to unregister
     */
    void unregister(@Nonnull Collection<Listener> listeners);
}
