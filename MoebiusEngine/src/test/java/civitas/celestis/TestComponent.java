package civitas.celestis;

import civitas.celestis.graphics.color.RichColor;
import civitas.celestis.ui.component.UIComponent;
import civitas.celestis.ui.event.component.ComponentAddedEvent;
import civitas.celestis.ui.image.RichImage;
import civitas.celestis.ui.shape.Polygon;
import civitas.celestis.ui.shape.Triangle;
import jakarta.annotation.Nonnull;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TestComponent implements UIComponent {
    @Override
    public void render(@Nonnull RichImage image) {
        final RichColor color = RichColor.random();

        image.fill(color);

        image.fill(new Polygon(
                image.getPoint(0.25, 0.25),
                image.getPoint(0.75, 0.25),
                image.getPoint(0.75, 0.75),
                image.getPoint(0.25, 0.75)
        ), color.reverse());

        image.fill(new Triangle(
                image.getPoint(0.33, 0.33),
                image.getPoint(0.66, 0.4),
                image.getPoint(0.4, 0.7)
        ), RichColor.random());
    }

    @Override
    public void onComponentAdded(@Nonnull ComponentAddedEvent event) {
        event.getFrame().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                Moebius.getScheduler().stop(); // Stops the engine without closing it
            }
        });
    }
}
