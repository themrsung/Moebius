package civitas.celestis;

import civitas.celestis.event.EventHandler;
import civitas.celestis.event.Listener;
import jakarta.annotation.Nonnull;

public class TestListener implements Listener {
    @EventHandler
    public void onEvent(@Nonnull TestEvent event) {
        System.out.println("Event test succeeded.");
    }
}