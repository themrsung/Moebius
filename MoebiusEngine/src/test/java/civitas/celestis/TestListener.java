package civitas.celestis;

import civitas.celestis.event.Event;
import civitas.celestis.event.EventHandler;
import civitas.celestis.event.Listener;

public class TestListener implements Listener {
    @EventHandler
    public void a(Event event) {
        System.out.println("Hello world");
    }
}
