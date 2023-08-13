package civitas.celestis.graphics.raytracing;

import civitas.celestis.math.vector.Vector3;
import jakarta.annotation.Nonnull;

/**
 * A ray in 3D.
 * Rays are used as the base building blocks for raytracing implementations.
 */
public interface Ray {
    /**
     * Returns the origin of this ray.
     *
     * @return The origin of this ray
     */
    @Nonnull
    Vector3 origin();

    /**
     * Returns the direction of this ray
     *
     * @return The direction of this ray
     */
    @Nonnull
    Vector3 direction();

    /**
     * Given a distance parameter {@code t}, this calculates the destination of this ray.
     *
     * @param t The distance parameter
     * @return The destination of this ray
     */
    @Nonnull
    Vector3 destination(double t);
}
