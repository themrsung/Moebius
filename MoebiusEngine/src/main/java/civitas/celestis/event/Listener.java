package civitas.celestis.event;

import civitas.celestis.event.lifecycle.HandlerReference;
import jakarta.annotation.Nonnull;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * An event listener. This is a marker interface which marks a class as an event listener.
 *
 * @see Handleable
 * @see Event
 * @see EventHandler
 * @see HandlerPriority
 */
public interface Listener {
    /**
     * Returns a list of references to all valid event handlers of this listener.
     * <p>
     * Modifying or overriding this method will change the behavior of the event manager.
     * Change this at your own risk.
     * </p>
     *
     * @return The list of valid handler references of this listener
     */
    @Nonnull
    default List<HandlerReference> getHandlerReferences() {
        final List<HandlerReference> refs = new ArrayList<>();

        for (final Method m : getClass().getDeclaredMethods()) {
            if (!m.isAnnotationPresent(EventHandler.class)) continue;
            if (m.getParameterCount() != 1) continue;

            refs.add(new HandlerReference(this, m));
        }

        return refs;
    }
}
