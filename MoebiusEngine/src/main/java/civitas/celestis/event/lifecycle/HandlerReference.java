package civitas.celestis.event.lifecycle;

import civitas.celestis.event.Event;
import civitas.celestis.event.EventHandler;
import civitas.celestis.event.HandlerPriority;
import civitas.celestis.event.Listener;
import jakarta.annotation.Nonnull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * A reference to an event handler.
 *
 * @param listener The listener object
 * @param method   The method reference
 * @see Listener
 * @see EventHandler
 */
public record HandlerReference(
        @Nonnull Listener listener,
        @Nonnull Method method
) implements Comparable<HandlerReference> {
    /**
     * Returns the priority of this handler.
     *
     * @return The priority of this handler
     */
    @Nonnull
    public HandlerPriority priority() {
        return method.getAnnotation(EventHandler.class).priority();
    }

    /**
     * Returns whether this handler listens to the provided event {@code e}.
     *
     * @param e   The event to check
     * @param <E> The type of event to check
     * @return {@code true} if this handler handles the event {@code e}
     */
    public <E extends Event> boolean accepts(@Nonnull E e) {
        return method.getParameterTypes()[0].isAssignableFrom(e.getClass());
    }

    /**
     * Called to handle an event.
     *
     * @param e   The event to handle
     * @param <E> The type of event to handle
     * @throws IllegalAccessException    When the method cannot be accessed
     * @throws InvocationTargetException When the event handler throws an exception
     */
    public <E extends Event> void handle(@Nonnull E e) throws IllegalAccessException, InvocationTargetException {
        method.invoke(listener, e);
    }

    /**
     * Compares this handler reference to another by its priority.
     * Note that lower priorities are executed first.
     *
     * @param r The reference to compare to
     * @return {@code 0} if the priorities are equal, {@code 1} if this handler's priority is higher,
     * {@code -1} otherwise
     */
    @Override
    public int compareTo(@Nonnull HandlerReference r) {
        return priority().compareTo(r.priority());
    }
}
