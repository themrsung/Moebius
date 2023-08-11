package civitas.celestis.graphics.ray;

import civitas.celestis.math.vector.Vector3;
import jakarta.annotation.Nonnull;

/**
 * A ray has an origin and a destination. Rays can represent physics phenomena or lighting.
 */
public interface Ray {
    /**
     * Gets the origin of this ray.
     *
     * @return Point of origin
     */
    @Nonnull
    Vector3 getOrigin();

    /**
     * Gets the direction of this ray.
     *
     * @return The directional vector of this ray
     */
    @Nonnull
    Vector3 getDirection();

    /**
     * Given a distance parameter {@code t}, this returns the destination of this ray.
     *
     * @param t The distance parameter
     * @return The destination of this ray
     */
    @Nonnull
    Vector3 getDestination(double t);
}
