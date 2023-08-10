package civitas.celestis.graphics.ray;

import civitas.celestis.graphics.util.Vertex;
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
    Vertex getOrigin();

    /**
     * Gets the direction of this ray.
     *
     * @return The directional vector of this ray
     */
    @Nonnull
    Vertex getDirection();

    /**
     * Given a distance parameter {@code t}, this returns the destination of this ray.
     *
     * @param t The distance parameter
     * @return The destination of this ray
     */
    @Nonnull
    Vertex getDestination(double t);
}
