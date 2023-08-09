package civitas.celestis.listener.object;

import civitas.celestis.event.EventHandler;
import civitas.celestis.event.HandlerPriority;
import civitas.celestis.event.Listener;
import civitas.celestis.event.object.ObjectsCollidedEvent;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.object.BaseObject;
import jakarta.annotation.Nonnull;

/**
 * This listener listens for {@link ObjectsCollidedEvent}s.
 */
public final class ObjectsCollidedListener implements Listener {
    @EventHandler(priority = HandlerPriority.PERMISSIVE)
    public void onObjectsCollided(@Nonnull ObjectsCollidedEvent event) {
        if (event.isCancelled()) return;

        // Obtain pointers to objects
        final BaseObject o1 = event.getObject1();
        final BaseObject o2 = event.getObject2();

        // Get velocities
        final Vector3 u1 = o1.getAcceleration();
        final Vector3 u2 = o2.getAcceleration();

        // Get masses
        final double m1 = o1.getMass();
        final double m2 = o2.getMass();

        // Check for potential arithmetic exception
        final double denominator = m1 + m2;
        if (denominator == 0) {
            // The collision of massless objects is not handled by default
            return;
        }

        // Final velocities
        final Vector3 v1 = u1.subtract(u2).multiply((m1 - m2) / denominator);
        final Vector3 v2 = u2.subtract(u1).multiply((m2 - m1) / denominator);

        // Set velocities
        o1.setAcceleration(v1);
        o2.setAcceleration(v2);
    }
}
