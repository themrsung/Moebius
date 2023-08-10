package civitas.celestis.graphics.collision;

import civitas.celestis.graphics.face.Face;
import civitas.celestis.graphics.ray.LightRay;
import civitas.celestis.graphics.ray.Ray;
import civitas.celestis.graphics.util.GraphicsUtils;
import civitas.celestis.math.vector.Vector3;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * A collision object.
 * This is used to handle collisions between rays and faces.
 */
public class Collision {
    /**
     * Creates a new collision.
     *
     * @param ray  Ray of the collision
     * @param face Face of the collision
     */
    public Collision(@Nonnull Ray ray, @Nonnull Face face) {
        this.ray = ray;
        this.face = face;
    }

    @Nonnull
    private final Ray ray;
    @Nonnull
    private final Face face;

    /**
     * Gets the ray of this collision.
     *
     * @return The ray of this collision
     */
    @Nonnull
    public Ray getRay() {
        return ray;
    }

    /**
     * Gets the face of this collision.
     *
     * @return The face of this collision
     */
    @Nonnull
    public Face getFace() {
        return face;
    }

    /**
     * Gets the result of this collision.
     *
     * @return The result of this collision
     */
    @Nonnull
    public CollisionResult getResult() {
        return new CollisionResult();
    }

    /**
     * Gets the reflection ray of this collision.
     *
     * @return Reflection ray if derivable, {@code null} if not
     */
    @Nullable
    private Ray getReflection() {
        if (ray instanceof LightRay lr) {
            final Vector3 intersection = GraphicsUtils.intersection(ray, face);
            if (intersection == null) return null;

            final Vector3 reflection = GraphicsUtils.reflection(ray.getDirection(), face.getNormal());

            final double reflectiveness = face.getReflectiveness();
            final double intensity = lr.getIntensity();

            final double resultingIntensity = intensity * reflectiveness;

            return new LightRay(intersection, reflection, lr.getColor(), resultingIntensity);
        }

        return null;
    }

    /**
     * Gets the pass-through ray of this collision.
     *
     * @return Pass-through ray if derivable, {@code null} if not
     */
    @Nullable
    private Ray getPassThrough() {
        if (!face.isTranslucent()) return null;

        if (ray instanceof LightRay lr) {
            final Vector3 intersection = GraphicsUtils.intersection(ray, face);
            if (intersection == null) return null;

            final double alpha = 1.0 - face.getReflectiveness();

            return new LightRay(intersection, ray.getDirection(), lr.getColor(), lr.getIntensity() * alpha);
        }

        return null;
    }

    /**
     * The result of a collision.
     */
    public static class CollisionResult {
        private boolean collides;

        // FIXME FIXME FIXME REDO THIS
    }
}
