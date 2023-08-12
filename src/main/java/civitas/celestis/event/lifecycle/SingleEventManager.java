package civitas.celestis.event.lifecycle;

import civitas.celestis.event.Event;
import civitas.celestis.event.Listener;
import jakarta.annotation.Nonnull;

import java.util.*;

/**
 * An event manager implementation with a single event queue.
 */
public final class SingleEventManager implements EventManager {
    @Override
    public void start() {
        thread.start();
    }

    @Override
    public void stop() {
        thread.interrupt();
    }

    @Override
    public <E extends Event> void call(@Nonnull E event) {
        queue.add(event);
    }

    @Override
    public void register(@Nonnull Listener listener) {
        handlers.addAll(listener.getHandlerReferences());

        // Preemptively sort handlers
        handlers.sort(Comparator.comparing(HandlerReference::priority));
    }

    @Override
    public void register(@Nonnull Collection<Listener> listeners) {
        listeners.forEach(this::register);
    }

    @Override
    public void unregister(@Nonnull Listener listener) {
        handlers.removeAll(listener.getHandlerReferences());
    }

    @Override
    public void unregister(@Nonnull Collection<Listener> listeners) {
        listeners.forEach(this::unregister);
    }

    private final Queue<Event> queue = new LinkedList<>();
    private final List<HandlerReference> handlers = new ArrayList<>();
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
