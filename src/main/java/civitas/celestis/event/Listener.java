package civitas.celestis.event;

import civitas.celestis.event.lifecycle.HandlerReference;
import jakarta.annotation.Nonnull;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * A marker interface which marks a class as an event listener.
 * <p>
 * While there are no required parameter or methods,
 * minimizing the amount of declared methods in a listener class is recommended.
 * </p>
 *
 * @see Event
 * @see EventHandler
 */
public interface Listener {
    /**
     * Returns an array of handler references of this listener instance.
     * <p>
     * <b>Do not override this method unless you are changing the architecture of the event manager.</b>
     * Modifications in this logic will result in different behavior in the event manager.
     * </p>
     *
     * @return An array containing the handler references
     */
    @Nonnull
    default List<HandlerReference> getHandlerReferences() {
        final List<HandlerReference> references = new ArrayList<>();

        for (final Method declaredMethod : getClass().getDeclaredMethods()) {
            if (!declaredMethod.isAnnotationPresent(EventHandler.class)) continue;
            if (declaredMethod.getParameterCount() != 1) continue;

            references.add(new HandlerReference(this, declaredMethod));
        }

        return references;
    }
}
