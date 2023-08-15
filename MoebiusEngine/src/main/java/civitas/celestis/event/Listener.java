package civitas.celestis.event;

import civitas.celestis.event.lifecycle.HandlerReference;
import jakarta.annotation.Nonnull;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * A marker interface which marks a class as an event listener.
 *
 * @see Event
 * @see EventHandler
 * @see HandlerPriority
 */
public interface Listener {
    /**
     * Returns a list of references to all event handlers in this listener.
     * <p><b>
     * Modifying this method will result in different behavior in the event manager.
     * Do not modify or override this method unless you are changing
     * the architecture of the event manager or optimizing it further.
     * </b></p>
     *
     * @return A list of references to all event handlers in this listener
     */
    @Nonnull
    default List<HandlerReference> getHandlerReferences() {
        final List<HandlerReference> refs = new ArrayList<>();

        for (final Method method : getClass().getDeclaredMethods()) {
            if (!method.isAnnotationPresent(EventHandler.class)) continue;
            if (method.getParameterCount() != 1) continue;
            if (!method.getParameterTypes()[0].isAssignableFrom(Event.class)) continue;

            refs.add(new HandlerReference(this, method));
        }

        return refs;
    }
}
