package civitas.celestis;

import civitas.celestis.event.EventHandler;
import civitas.celestis.event.Listener;
import civitas.celestis.event.object.ObjectsCollidedEvent;
import jakarta.annotation.Nonnull;

public class TestListener implements Listener {
    @EventHandler
    public void onCollision(@Nonnull ObjectsCollidedEvent e) {
        Moebius.getLogger().info("Objects collided.");
    }
}
