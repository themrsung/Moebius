package civitas.celestis.event.lifecycle;

import civitas.celestis.event.Event;
import civitas.celestis.event.Listener;
import civitas.celestis.util.ThreadedModule;
import jakarta.annotation.Nonnull;

/**
 * An event manager. Handles the lifecycle of events and event managers.
 *
 * @see Event
 * @see civitas.celestis.event.EventHandler EventHandler
 * @see Listener
 */
public interface EventManager extends ThreadedModule {
    //
    // Lifecycle
    //

    /**
     * Calls an event to this event manager, adding it to the event queue.
     *
     * @param e   The event being called
     * @param <E> The type of event being called
     */
    <E extends Event> void call(@Nonnull E e);

    //
    // Listener Registration
    //

    /**
     * Registers an event listener to this event manager.
     *
     * @param l The listener to register to this manager
     */
    void register(@Nonnull Listener l);

    /**
     * Registers multiple listeners to this event manager.
     *
     * @param i The iterable object containing the listeners to register
     */
    void register(@Nonnull Iterable<? extends Listener> i);

    /**
     * Unregisters an event listener from this event manager.
     *
     * @param l The listener to unregister from this manager
     */
    void unregister(@Nonnull Listener l);

    /**
     * Unregisters multiple listeners from this event manager.
     *
     * @param i The iterable object containing the listeners to register
     */
    void unregister(@Nonnull Iterable<? extends Listener> i);
}
