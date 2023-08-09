package civitas.celestis.event.lifecycle;

import civitas.celestis.event.Event;
import civitas.celestis.event.EventHandler;
import civitas.celestis.event.Listener;
import jakarta.annotation.Nonnull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * A synchronous event manager with a single thread.
 */
public final class SyncEventManager implements EventManager {
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
    public void register(@Nonnull Listener l) {
        listeners.add(l);
    }

    @Override
    public void register(@Nonnull Collection<Listener> l) {
        listeners.addAll(l);
    }

    @Override
    public void unregister(@Nonnull Listener l) {
        listeners.remove(l);
    }

    @Override
    public void unregister(@Nonnull Collection<Listener> l) {
        listeners.removeAll(l);
    }

    private final Queue<Event> queue = new LinkedList<>();
    private final List<Listener> listeners = new ArrayList<>();
    private final Thread thread = new Thread(() -> {
        while (true) {
            if (Thread.interrupted()) return;

            final Event event = queue.poll();
            if (event == null) continue;

            final List<HandlerReference> handlers = new ArrayList<>();

            for (final Listener listener : List.copyOf(listeners)) {
                for (final Method method : listener.getClass().getDeclaredMethods()) {
                    if (!method.isAnnotationPresent(EventHandler.class)) continue;
                    if (method.getParameterCount() != 1) continue;
                    if (!method.getParameterTypes()[0].isAssignableFrom(event.getClass())) continue;

                    handlers.add(new HandlerReference(listener, method));
                }
            }

            handlers.sort(Comparator.comparing(HandlerReference::priority));

            for (final HandlerReference handler : handlers) {
                try {
                    handler.method().invoke(handler.listener(), event);
                } catch (InvocationTargetException | IllegalAccessException ignored) {}
            }
        }
    }, "SyncEventManager");
}
