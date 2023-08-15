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
 * @param method   The handler method
 */
public record HandlerReference(@Nonnull Listener listener, @Nonnull Method method) {
    /**
     * Returns the priority of this handler.
     *
     * @return The priority of this handler
     * @see HandlerPriority
     */
    @Nonnull
    public HandlerPriority priority() {
        return method.getAnnotation(EventHandler.class).priority();
    }

    /**
     * Returns whether this handler handles the specified event.
     *
     * @param event The event of which to check for
     * @param <E>   The type of event to check
     * @return {@code true} if this handler handles the event
     */
    public <E extends Event> boolean handles(@Nonnull E event) {
        return method.getParameterTypes()[0].isAssignableFrom(event.getClass());
    }

    /**
     * Calls this handler to handle the specified event.
     *
     * @param event The event to handle
     * @param <E>   The type of event to handle
     */
    public <E extends Event> void handle(@Nonnull E event) {
        try {
            method.invoke(listener, event);
        } catch (final IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
