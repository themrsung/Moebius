package civitas.celestis.event.lifecycle;

import civitas.celestis.event.Handleable;
import civitas.celestis.event.Listener;
import jakarta.annotation.Nonnull;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Queue;

/**
 * An event processing thread. This can also be used as a single-core event manager.
 */
public class EventThread extends Thread implements EventManager {
    //
    // Thread Logic
    //

    /**
     * The event thread logic.
     *
     * @param queue    The queue of events this thread is polling from
     * @param handlers The list of handlers allocated to this thread
     * @return A new instance of {@link Runnable} containing
     * the logic of an event thread
     */
    @Nonnull
    private static Runnable getNewRunnable(@Nonnull Queue<Handleable> queue, @Nonnull List<HandlerReference> handlers) {
        return () -> {
            //
            // Event Thread
            // Last update: v0.3
            //

            // Infinitely loop until interrupted
            while (!Thread.interrupted()) {

                // Poll next event
                final Handleable e = queue.poll();

                // Check for null
                if (e == null) continue;

                // Iterate through copy of handlers to prevent concurrent modification from crashing this thread
                for (final HandlerReference h : List.copyOf(handlers)) {

                    // Continue if the handler does not accept the event
                    if (!h.accepts(e)) continue;

                    try {

                        // Call handler
                        h.handle(e);

                    } catch (final IllegalAccessException | InvocationTargetException exception) {

                        // Notify console
                        exception.printStackTrace();

                    }
                }
            }
        };
    }

    //
    // Constructors
    //

    /**
     * Creates a new event thread.
     *
     * @param name     The name of this thread
     * @param queue    The queue of events this thread should poll from
     * @param handlers The list of handlers allocated to this thread
     */
    public EventThread(@Nonnull String name, @Nonnull Queue<Handleable> queue, @Nonnull List<HandlerReference> handlers) {
        super(getNewRunnable(queue, handlers), name);
        this.queue = queue;
        this.handlers = handlers;
    }


    //
    // Variables
    //

    /**
     * The queue of events.
     */
    @Nonnull
    private final Queue<Handleable> queue;

    /**
     * The list of handlers.
     */
    @Nonnull
    private final List<HandlerReference> handlers;

    //
    // Methods
    //

    /**
     * {@inheritDoc}
     *
     * @param e   The event being called
     * @param <E> {@inheritDoc}
     */
    @Override
    public <E extends Handleable> void call(@Nonnull E e) {
        queue.add(e);
    }

    /**
     * {@inheritDoc}
     *
     * @param l The listener to register to this manager
     */
    @Override
    public void register(@Nonnull Listener l) {
        handlers.addAll(l.getHandlerReferences());

        // Preemptively sort the references by priority
        handlers.sort(HandlerReference::compareTo);
    }

    /**
     * {@inheritDoc}
     *
     * @param i The iterable object containing the listeners to register
     */
    @Override
    public void register(@Nonnull Iterable<? extends Listener> i) {
        i.forEach(this::register);
    }

    /**
     * {@inheritDoc}
     *
     * @param l The listener to unregister from this manager
     */
    @Override
    public void unregister(@Nonnull Listener l) {
        handlers.removeAll(l.getHandlerReferences());
    }

    /**
     * {@inheritDoc}
     *
     * @param i The iterable object containing the listeners to register
     */
    @Override
    public void unregister(@Nonnull Iterable<? extends Listener> i) {
        i.forEach(this::unregister);
    }
}
