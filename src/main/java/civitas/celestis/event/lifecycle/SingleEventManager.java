package civitas.celestis.event.lifecycle;

import civitas.celestis.event.Event;
import civitas.celestis.event.Listener;
import jakarta.annotation.Nonnull;

import java.util.*;

/**
 * An event manager implementation with a single event queue.
 */
public final class SingleEventManager implements EventManager {
    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        thread.start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop() {
        thread.interrupt();
    }

    /**
     * {@inheritDoc}
     * @param event The event to be called
     * @param <E> THe type of event to be called
     */
    @Override
    public <E extends Event> void call(@Nonnull E event) {
        queue.add(event);
    }

    /**
     * {@inheritDoc}
     * @param listener The listener to register to this event manager
     */
    @Override
    public void register(@Nonnull Listener listener) {
        handlers.addAll(listener.getHandlerReferences());

        // Preemptively sort handlers
        handlers.sort(Comparator.comparing(HandlerReference::priority));
    }

    /**
     * {@inheritDoc}
     * @param listeners The collection of listeners to register to this event manager
     */
    @Override
    public void register(@Nonnull Collection<Listener> listeners) {
        listeners.forEach(this::register);
    }

    /**
     * {@inheritDoc}
     * @param listener The listener to unregister from this event manager
     */
    @Override
    public void unregister(@Nonnull Listener listener) {
        handlers.removeAll(listener.getHandlerReferences());
    }

    /**
     * {@inheritDoc}
     * @param listeners The collection of listeners to unregister from this event manager
     */
    @Override
    public void unregister(@Nonnull Collection<Listener> listeners) {
        listeners.forEach(this::unregister);
    }

    /**
     * The queue of events.
     */
    private final Queue<Event> queue = new LinkedList<>();

    /**
     * The list of event handlers.
     */
    private final List<HandlerReference> handlers = new ArrayList<>();

    /**
     * The thread which handles the events.
     */
    private final Thread thread = new Thread(() -> {
        while (!Thread.interrupted()) {
            // Poll next event
            final Event event = queue.poll();
            if (event == null) continue;

            // Iterate through handlers and call if possible
            for (final HandlerReference handler : handlers) {
                if (!handler.isCallable(event)) continue;
                handler.call(event);
            }
        }
    }, "SingleEventManager");
}
