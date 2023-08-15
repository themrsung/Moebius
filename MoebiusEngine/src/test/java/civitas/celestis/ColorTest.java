package civitas.celestis;

import civitas.celestis.event.Event;
import civitas.celestis.event.Listener;
import civitas.celestis.event.lifecycle.EventManager;
import civitas.celestis.event.lifecycle.SyncEventManager;
import civitas.celestis.graphics.color.Color8;
import civitas.celestis.graphics.color.LinearColor;
import civitas.celestis.graphics.color.SimpleColor;

public class ColorTest {
    public static void main(String[] args) {
        final Color8 red = SimpleColor.RED;
        final Color8 thisShouldBeRed = Color8.fromAWT(Color8.toAWT(red));
        System.out.println(new LinearColor(thisShouldBeRed));

        manager.start();

        manager.register(new TestListener());
        manager.call(new Event() {});
    }

    static EventManager manager = new SyncEventManager();
}
