package civitas.celestis.graphics;

import civitas.celestis.math.vector.Vector2;
import civitas.celestis.math.vector.Vector3;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

public class Geometry {

    //
    // Raytracing
    //

    /**
     * Given a tetrahedron in the form of its four corners,
     * this returns the signed volume of the tetrahedron.
     *
     * @param a The first corner of the tetrahedron
     * @param b The second corner of the tetrahedron
     * @param c The third corner of the tetrahedron
     * @param d The fourth corner of the tetrahedron
     * @return The singed volume of the tetrahedron
     */
    public static double signedVolume(@Nonnull Vector3 a, @Nonnull Vector3 b, @Nonnull Vector3 c, @Nonnull Vector3 d) {
        final Vector3 ab = b.subtract(a);
        final Vector3 ac = c.subtract(a);
        final Vector3 ad = d.subtract(a);

        return (1d / 6d) * ab.cross(ac).dot(ad);
    }

    /**
     * Checks if the face and ray have an intersection.
     *
     * @param face The face to check
     * @param ray  The ray to check
     * @return {@code true} if the face and ray have an intersection
     */
    public static boolean intersects(@Nonnull Face face, @Nonnull Ray ray) {
        final Vector3 p1 = face.a;
        final Vector3 p2 = face.b;
        final Vector3 p3 = face.c;

        final Vector3 q1 = ray.origin;
        final Vector3 q2 = ray.getDestination(face.getCentroid().distance2(q1));

        // These must have different signs
        final double v1 = signedVolume(q1, p1, p2, p3);
        final double v2 = signedVolume(q2, p1, p2, p3);

        // These must have the same sign
        final double v3 = signedVolume(q1, q2, p1, p2);
        final double v4 = signedVolume(q1, q2, p2, p3);
        final double v5 = signedVolume(q1, q2, p3, p1);

        return (v1 * v2) < 0 && (v3 * v4 * v5) >= 0;
    }

    /**
     * Calculates the point of intersection between a face and ray.
     *
     * @param face The face
     * @param ray  The ray
     * @return The intersection if found, {@code null} if not
     */
    @Nullable
    public static Vector3 intersection(@Nonnull Face face, @Nonnull Ray ray) {
        // Obtain references to face's vertices
        final Vector3 p1 = face.getA();
        final Vector3 p2 = face.getB();
        final Vector3 p3 = face.getC();

        // Declare local variables
        final Vector3 normal = face.getNormal().normalize();
        final Vector3 rayOrigin = ray.origin;
        final Vector3 rayDirection = ray.direction;

        // Calculate determinant to check if the face and ray are parallel
        final double determinant = rayDirection.dot(normal);
        if (Math.abs(determinant) < 1e-6) {
            return null; // Ray is parallel to face
        }

        // Calculate the intersection point
        final double t = p1.subtract(rayOrigin).dot(normal) / determinant;
        final Vector3 intersectionPoint = ray.getDestination(t);

        // Calculate the barycentric coordinates of the intersection point
        final Vector3 v0 = p2.subtract(p1);
        final Vector3 v1 = p3.subtract(p1);
        final Vector3 v2 = intersectionPoint.subtract(p1);

        final double dot00 = v0.dot(v0);
        final double dot01 = v0.dot(v1);
        final double dot11 = v1.dot(v1);
        final double dot20 = v2.dot(v0);
        final double dot21 = v2.dot(v1);

        final double invDenom = 1.0 / (dot00 * dot11 - dot01 * dot01);
        final double u = (dot11 * dot20 - dot01 * dot21) * invDenom;
        final double v = (dot00 * dot21 - dot01 * dot20) * invDenom;

        if (u >= 0 && v >= 0 && u + v <= 1) {
            return intersectionPoint; // Intersection point lies inside the triangle
        }

        return null; // Intersection point is outside the triangle
    }

    /**
     * Given an incident vector and the surface normal, this calculates the reflection vector
     * if the incident vector were to collide with the surface.
     * The surface normal is automatically normalized.
     *
     * @param incidentVector The incident vector
     * @param surfaceNormal  The surface normal
     * @return The reflection vector
     */
    @Nonnull
    public static Vector3 reflect(@Nonnull Vector3 incidentVector, @Nonnull Vector3 surfaceNormal) {
        final Vector3 normalizedNorm = surfaceNormal.normalize();
        final double projection = incidentVector.dot(normalizedNorm);
        final Vector3 projectionVector = normalizedNorm.multiply(projection);

        return incidentVector.subtract(projectionVector.multiply(2));
    }

    //
    // Rendering
    //

    /**
     * Translates a 3D vector into a 2D vector with respect to focal length.
     * This also inverts the Y coordinate to be compatible with the coordinate system of AWT.
     *
     * @param in          Input vector
     * @param focalLength Focal length to use in conversion
     * @return Converted {@link Vector2}
     */
    @Nonnull
    public static Vector2 translate3Dto2D(@Nonnull Vector3 in, double focalLength) {
        return new Vector2(
                (focalLength / (focalLength + in.z())) * in.x(),
                (focalLength / (focalLength + in.z())) * -in.y()
        );
    }
}
