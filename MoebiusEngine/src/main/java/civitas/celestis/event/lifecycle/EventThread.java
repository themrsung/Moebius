package civitas.celestis.event.lifecycle;

import civitas.celestis.event.Event;
import civitas.celestis.event.Listener;
import jakarta.annotation.Nonnull;

import java.util.Comparator;
import java.util.List;
import java.util.Queue;

/**
 * An event processing thread responsible for handling events.
 * This can also be used as a single-queue event handler.
 */
public class EventThread extends Thread implements EventManager {
    //
    // Constructors
    //

    /**
     * Creates a new event thread.
     *
     * @param queue    The queue of events this thread is managing
     * @param handlers The list of handlers registered to this thread
     * @param name     The name of this thread
     */
    public EventThread(@Nonnull Queue<Event> queue, @Nonnull List<HandlerReference> handlers, @Nonnull String name) {
        super(() -> {
            // Enter infinite loop while thread is active
            while (!Thread.interrupted()) {
                // Poll event
                final Event event = queue.poll();

                // Continue if null
                if (event == null) continue;

                // Iterate through copy of handlers to prevent concurrent modification from crashing the thread
                for (final HandlerReference handler : List.copyOf(handlers)) {
                    // Call handler if the handler accepts this event
                    if (handler.handles(event)) handler.handle(event);
                }
            }
        }, name);

        // Store references for modification
        this.queue = queue;
        this.handlers = handlers;
    }

    //
    // Variables
    //

    /**
     * The queue of events allocated to this thread.
     */
    @Nonnull
    private final Queue<Event> queue;

    /**
     * The list of handlers registered to this thread.
     */
    @Nonnull
    private final List<HandlerReference> handlers;

    //
    // Methods
    //

    /**
     * {@inheritDoc}
     *
     * @param event The event to call
     * @param <E>   The type of event to call
     */
    @Override
    public <E extends Event> void call(@Nonnull E event) {
        queue.add(event);
    }

    /**
     * {@inheritDoc}
     *
     * @param listener The listener to register to this event manager
     */
    @Override
    public void register(@Nonnull Listener listener) {
        // Register the handler references
        handlers.addAll(listener.getHandlerReferences());

        // Preemptively sort the handler list by priority
        handlers.sort(Comparator.comparing(HandlerReference::priority));
    }

    /**
     * {@inheritDoc}
     *
     * @param listeners The iterable object of listeners to register
     */
    @Override
    public void register(@Nonnull Iterable<Listener> listeners) {
        listeners.forEach(this::register);
    }

    /**
     * {@inheritDoc}
     *
     * @param listener The listener to unregister from this event manager
     */
    @Override
    public void unregister(@Nonnull Listener listener) {
        handlers.removeAll(listener.getHandlerReferences());
    }

    /**
     * {@inheritDoc}
     *
     * @param listeners The iterable object of listeners to unregister
     */
    @Override
    public void unregister(@Nonnull Iterable<Listener> listeners) {
        listeners.forEach(this::unregister);
    }
}
