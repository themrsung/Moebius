package civitas.celestis.graphics.util;

import civitas.celestis.graphics.face.Face;
import civitas.celestis.graphics.ray.Ray;
import civitas.celestis.math.vector.Vector2;
import civitas.celestis.math.vector.Vector3;
import de.javagl.obj.FloatTuple;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * A utility class containing operations related to graphics processing.
 */
public final class GraphicsUtils {
    //
    // Conversions
    //

    /**
     * Converts a Wavefront {@link FloatTuple} to a {@link Vertex}.
     *
     * @param in Input tuple
     * @return Converted {@link Vertex}
     */
    @Nonnull
    public static Vertex wavefrontTupleToVertex(@Nonnull FloatTuple in) {
        return new Vertex(in.getZ(), in.getY(), in.getX());
    }

    /**
     * Inverts the Y component of a {@link Vector2}.
     *
     * @param in Input vector to invert Y component of
     * @return Inverted vector
     */
    @Nonnull
    public static Vector2 invertY(@Nonnull Vector2 in) {
        return new Vector2(in.x(), -in.y());
    }

    //
    // Raytracing
    //

    /**
     * Computes the intersection point between a ray and a face (triangle) using the Möller–Trumbore algorithm.
     *
     * @param ray  The ray for intersection testing
     * @param face The face (triangle) for intersection testing
     * @return The intersection point between the ray and the face if derivable, {@code null} if no intersection
     */
    @Nullable
    public static Vector3 intersection(@Nonnull Ray ray, @Nonnull Face face) {
        final Vector3 vertexA = face.getA();
        final Vector3 vertexB = face.getB();
        final Vector3 vertexC = face.getC();

        final Vector3 edge1 = vertexB.subtract(vertexA);
        final Vector3 edge2 = vertexC.subtract(vertexA);

        final Vector3 h = ray.getDirection().cross(edge2);
        double a = edge1.dot(h);

        if (a > -1e-6 && a < 1e-6) {
            return null;  // Ray and triangle are parallel or nearly parallel
        }

        double f = 1.0 / a;
        final Vector3 s = ray.getOrigin().subtract(vertexA);
        double u = f * s.dot(h);

        if (u < 0.0 || u > 1.0) {
            return null;  // Intersection point is outside the triangle
        }

        final Vector3 q = s.cross(edge1);
        double v = f * ray.getDirection().dot(q);

        if (v < 0.0 || u + v > 1.0) {
            return null;  // Intersection point is outside the triangle
        }

        double t = f * edge2.dot(q);
        if (t > 1e-6) {
            return ray.getDestination(t);  // Intersection point found
        }

        return null;  // Intersection point is behind the ray's origin
    }

    /**
     * Given an incident directional vector and a surface normal, this returns the reflection vector.
     *
     * @param incidentVector Incident vector hitting the surface
     * @param surfaceNormal  The surface normal of the face being hit
     * @return The reflection vector
     */
    @Nonnull
    public static Vector3 reflection(@Nonnull Vector3 incidentVector, @Nonnull Vector3 surfaceNormal) {
        return incidentVector.subtract(surfaceNormal.multiply(2.0 * incidentVector.dot(surfaceNormal)));
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
