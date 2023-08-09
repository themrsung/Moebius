package civitas.celestis.graphics.viewport;

import civitas.celestis.graphics.face.ColoredFace;
import civitas.celestis.graphics.face.Face;
import civitas.celestis.graphics.scene.Scene;
import civitas.celestis.graphics.shape.PolygonX;
import civitas.celestis.graphics.util.GraphicsUtils;
import civitas.celestis.math.quaternion.Quaternion;
import civitas.celestis.math.vector.Vector3;
import jakarta.annotation.Nonnull;

import javax.swing.*;
import java.awt.*;

/**
 * A viewport is responsible for visualizing a scene to the screen.
 */
public class Viewport extends JPanel {
    /**
     * Creates a new viewport.
     *
     * @param scene Scene to render in this viewport
     */
    public Viewport(@Nonnull Scene scene) {
        super(true);
        this.scene = scene;
        this.context = ViewportContext.getDefaultContext();
    }

    /**
     * Creates a new viewport.
     *
     * @param scene   Scene to render in this viewport
     * @param context Context of this viewport
     */
    public Viewport(@Nonnull Scene scene, @Nonnull ViewportContext context) {
        super(true);
        this.scene = scene;
        this.context = context;
    }

    @Nonnull
    private final Scene scene;
    @Nonnull
    private ViewportContext context;

    /**
     * Gets the scene of this viewport.
     *
     * @return The scene of this viewport
     */
    @Nonnull
    public Scene getScene() {
        return scene;
    }

    /**
     * Gets the current context of this viewport.
     *
     * @return The context of this viewport
     */
    @Nonnull
    public ViewportContext getContext() {
        return context;
    }

    /**
     * Sets the context of this viewport.
     *
     * @param context The context of this viewport
     */
    public void setContext(@Nonnull ViewportContext context) {
        this.context = context;
    }

    /**
     * Renders and paints the scene to the screen.
     *
     * @param g the <code>Graphics</code> context in which to paint
     */
    @Override
    public void paint(@Nonnull Graphics g) {
        if (painting) return;

        painting = true;
        // Clear screen
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Translate to relative coordinate system
        g.translate(getWidth() / 2, getHeight() / 2);

        // Clean and render scene
        scene.clean();
        scene.render();

        // Define contextual properties
        final Vector3 location = context.location();
        final Quaternion rotation = context.rotation();
        final double inflation = context.inflation();
        final double focalLength = context.focalLength();

        // Draw faces
        for (final Face face : scene.getFaces()) {
            final Face transformed = face.transform(location, rotation, inflation);
            final PolygonX polygon = new PolygonX();

            // Filter out faces behind the camera
            if (transformed.getCentroid().z() < 0) continue;

            for (final Vector3 point : transformed.getPoints()) {
                polygon.addPoint(GraphicsUtils.translate3Dto2D(point, focalLength));
            }

            g.setColor((face instanceof ColoredFace cf) ? cf.getColor() : Color.BLACK); // Fallback color
            g.fillPolygon(polygon);
        }

        painting = false;
    }

    private boolean painting = false;
}
