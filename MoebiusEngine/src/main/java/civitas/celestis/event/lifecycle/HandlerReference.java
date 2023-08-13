package civitas.celestis.event.lifecycle;

import civitas.celestis.event.Event;
import civitas.celestis.event.EventHandler;
import civitas.celestis.event.HandlerPriority;
import civitas.celestis.event.Listener;
import jakarta.annotation.Nonnull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * A transient reference object to an event handler method.
 *
 * @param listener The listener object
 * @param method   The handler method
 */
public record HandlerReference(@Nonnull Listener listener, @Nonnull Method method) {
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
     * Checks if an event can be safely called to this event handler.
     *
     * @param event The event to check for type-safety
     * @param <E>   The type of event to check for type-safety
     * @return {@code true} if the event can be safely called
     */
    public <E extends Event> boolean isCallable(@Nonnull E event) {
        return method.getParameterTypes()[0].isAssignableFrom(event.getClass());
    }

    /**
     * Calls an event to this event handler.
     *
     * @param event The event to call
     * @param <E>   The type of event to call
     */
    public <E extends Event> void call(@Nonnull E event) {
        try {
            method.invoke(listener, event);
        } catch (final IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
