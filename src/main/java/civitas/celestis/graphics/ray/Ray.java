package civitas.celestis.graphics.ray;

import civitas.celestis.math.vector.Vector3;
import jakarta.annotation.Nonnull;

/**
 * A ray represents a directional line in 3D.
 */
public class Ray {
    /**
     * Creates a new ray. The direction should be a unit vector.
     * <b>Direction is not automatically normalized to conserve computation power.</b>
     *
     * @param origin    The origin of this ray
     * @param direction The direction of this ray
     */
    public Ray(@Nonnull Vector3 origin, @Nonnull Vector3 direction) {
        this.origin = origin;
        this.direction = direction;
    }

    /**
     * Creates a new ray from another ray.
     *
     * @param other The ray to copy properties from
     */
    public Ray(@Nonnull Ray other) {
        this.origin = other.origin;
        this.direction = other.direction;
    }

    @Nonnull
    private final Vector3 origin, direction;

    /**
     * Gets the origin of this ray.
     *
     * @return The origin of this ray
     */
    @Nonnull
    public Vector3 getOrigin() {
        return origin;
    }

    /**
     * Gets the direction of this ray.
     *
     * @return The direction of this ray
     */
    @Nonnull
    public Vector3 getDirection() {
        return direction;
    }

    /**
     * Given a distance parameter {@code t}, this calculates the destination of this ray.
     *
     * @param t The distance parameter
     * @return The destination of this ray
     */
    @Nonnull
    public Vector3 getDestination(double t) {
        return origin.add(direction.multiply(t));
    }
}
