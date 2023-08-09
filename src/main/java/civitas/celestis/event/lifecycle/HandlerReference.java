package civitas.celestis.event.lifecycle;

import civitas.celestis.event.EventHandler;
import civitas.celestis.event.HandlerPriority;
import civitas.celestis.event.Listener;
import jakarta.annotation.Nonnull;

import java.lang.reflect.Method;

/**
 * A reference object to an event handler.
 *
 * @param listener Listener object containing the handler
 * @param method   Handler method to be called
 */
public record HandlerReference(@Nonnull Listener listener, @Nonnull Method method) {
    /**
     * Gets the priority of this event handler.
     *
     * @return The event handler priority
     */
    @Nonnull
    public HandlerPriority priority() {
        return method.getAnnotation(EventHandler.class).priority();
    }
}
