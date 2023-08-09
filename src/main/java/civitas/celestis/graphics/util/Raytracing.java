package civitas.celestis.graphics.util;

import civitas.celestis.graphics.face.Face;
import civitas.celestis.graphics.ray.Ray;
import civitas.celestis.math.vector.Vector3;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * A utility class containing raytracing functions.
 */
public final class Raytracing {
    /**
     * Gets the intersection point between a ray and a face.
     * If there is no intersection, this returns {@code null}.
     *
     * @param ray  Ray to check
     * @param face Face to check
     * @return Intersection point if found, {@code null} if not
     */
    @Nullable
    public static Vector3 getIntersectionPoint(@Nonnull Ray ray, @Nonnull Face face) {
        final Vector3 normal = face.getNormal();
        final double dot = ray.getDirection().dot(normal);

        // Check if ray is nearly parallel to the plane
        if (Math.abs(dot) < 1e-6) {
            return null; // No intersection
        }

        final double t = normal.dot(face.getA().subtract(ray.getOrigin())) / dot;

        if (t < 0) {
            return null; // Intersection behind ray origin
        }

        final Vector3 intersectionPoint = ray.getDestination(t);

        final Vector3 v0 = face.getA().subtract(intersectionPoint);
        final Vector3 v1 = face.getB().subtract(intersectionPoint);
        final Vector3 v2 = face.getC().subtract(intersectionPoint);

        final Vector3 cross1 = v0.cross(v1);
        final Vector3 cross2 = v1.cross(v2);

        if (cross1.dot(normal) >= 0 && cross2.dot(normal) >= 0) {
            return intersectionPoint; // Intersection point within the bounds of the face
        }

        return null; // Intersection point outside the bounds of the face
    }

    /**
     * Given an incident directional vector and a surface normal, this calculates the reflection vector.
     *
     * @param incidentDirection Incoming directional vector
     * @param surfaceNormal     The surface normal
     * @return Reflection vector
     */
    @Nonnull
    public static Vector3 getReflectionVector(@Nonnull Vector3 incidentDirection, @Nonnull Vector3 surfaceNormal) {
        return incidentDirection.subtract(surfaceNormal.multiply(2 * incidentDirection.dot(surfaceNormal)));
    }
}
