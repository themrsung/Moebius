package civitas.celestis.graphics;

import civitas.celestis.math.vector.Vector3;
import jakarta.annotation.Nonnull;

public class Ray {

    /**
     * Creates a new ray.
     * @param origin The origin of this ray
     * @param direction The direction of this ray
     */
    public Ray(@Nonnull Vector3 origin, @Nonnull Vector3 direction) {
        this.origin = origin;
        this.direction = direction;
    }

    @Nonnull
    protected final Vector3 origin, direction;

    @Nonnull
    public Vector3 getOrigin() {
        return origin;
    }

    @Nonnull
    public Vector3 getDirection() {
        return direction;
    }

    @Nonnull
    public Vector3 getDestination(double t) {
        final double x = origin.x() + direction.x() * t;
        final double y = origin.y() + direction.y() * t;
        final double z = origin.z() + direction.z() * t;

        return new Vector3(x, y, z);
    }
}
