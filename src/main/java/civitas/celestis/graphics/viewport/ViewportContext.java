package civitas.celestis.graphics.viewport;

import civitas.celestis.math.quaternion.Quaternion;
import civitas.celestis.math.vector.Vector3;
import jakarta.annotation.Nonnull;

/**
 * The contextual data required to render a scene.
 *
 * @param location    Location of the viewport
 * @param rotation    Rotation of the viewport
 * @param inflation   Inflation of objects (to better represent their size)
 * @param focalLength Focal length of the viewport
 */
public record ViewportContext(
        @Nonnull Vector3 location,
        @Nonnull Quaternion rotation,
        double inflation,
        double focalLength
) {
    /**
     * Gets the default configuration of a viewport.
     *
     * @return The default configuration of a viewport
     */
    @Nonnull
    public static ViewportContext getDefaultContext() {
        return new ViewportContext(Vector3.ZERO, Quaternion.IDENTITY, 10, 250);
    }
}
