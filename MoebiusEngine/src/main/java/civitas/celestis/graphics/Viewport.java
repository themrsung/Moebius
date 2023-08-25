package civitas.celestis.graphics;

import civitas.celestis.math.complex.Quaternion;
import civitas.celestis.math.vector.Vector2;
import civitas.celestis.math.vector.Vector3;
import jakarta.annotation.Nonnull;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Viewport extends JComponent {
    public Viewport() {
    }

    public Vector3 origin = Vector3.ZERO;
    public Quaternion angle = Quaternion.IDENTITY;

    public final List<Face> faces = new ArrayList<>();

    public double focalLength = 350;

    @Override
    public void paint(@Nonnull Graphics g) {

        if (painting) return;

        painting = true;
        // Clear screen
        g.setColor(new Color(15, 15, 15));
        g.fillRect(0, 0, getWidth(), getHeight());

        g.translate(getWidth() / 2, getHeight() / 2);

        // Render faces
        for (final Face face : List.copyOf(faces)) {
            final Polygon polygon = new Polygon();

            for (final Vector3 vertex : face.getVertices()) {
                final Vector3 relative = vertex.subtract(origin).rotate(angle);
                final Vector2 mapped = Geometry.translate3Dto2D(relative, focalLength);
                polygon.addPoint((int) mapped.x(), (int) mapped.y());
            }

            g.setColor(face.getColor());
            g.fillPolygon(polygon);
        }


        g.dispose();
        painting = false;
    }

    private boolean painting = false;
}
