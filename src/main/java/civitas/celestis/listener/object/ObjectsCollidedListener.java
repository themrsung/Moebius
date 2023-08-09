package civitas.celestis.listener.object;

import civitas.celestis.event.EventHandler;
import civitas.celestis.event.HandlerPriority;
import civitas.celestis.event.Listener;
import civitas.celestis.event.object.ObjectsCollidedEvent;
import jakarta.annotation.Nonnull;

/**
 * Listens for object collisions and handles the collision event.
 */
public final class ObjectsCollidedListener implements Listener {
    @EventHandler(priority = HandlerPriority.PERMISSIVE)
    public void onObjectsCollided(@Nonnull ObjectsCollidedEvent event) {
        if (event.isCancelled()) return;


    }
}
