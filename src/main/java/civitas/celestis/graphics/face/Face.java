package civitas.celestis.graphics.face;

import civitas.celestis.graphics.raytracing.Ray;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.math.vertex.Vertex;
import civitas.celestis.util.alphabetical.AlphabeticalGroup;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.io.Serializable;
import java.util.function.UnaryOperator;

/**
 * A triangular face in 3D.
 * Faces are used to define the outer surface of an object.
 */
public interface Face extends AlphabeticalGroup<Vertex>, Serializable {
    //
    // Parser
    //

    /**
     * Deserializes a string into a face.
     *
     * @param input Input string to parse
     * @return The parsed face
     * @throws IllegalArgumentException When the format is invalid
     */
    @Nonnull
    static Face parseFace(@Nonnull String input) throws IllegalArgumentException {
        try {return ColoredFace.parseFace(input);} catch (final IllegalArgumentException ignored) {}

        throw new IllegalArgumentException("Given string is not a face.");
    }

    //
    // Geometry
    //

    /**
     * Returns the first vertex of this face.
     *
     * @return The first vertex of this face
     */
    @Nonnull
    Vertex getA();

    /**
     * Returns the second vertex of this face.
     *
     * @return The second vertex of this face
     */
    @Nonnull
    Vertex getB();

    /**
     * Returns the third vertex of this face.
     *
     * @return The third vertex of this face
     */
    @Nonnull
    Vertex getC();

    /**
     * Returns the surface normal vertex of this face.
     *
     * @return The surface normal vertex of this face
     */
    @Nonnull
    Vector3 getNormal();

    /**
     * Returns the geometric centroid of this face.
     *
     * @return The geometric centroid of this face
     */
    @Nonnull
    Vector3 getCentroid();

    //
    // Physics
    //

    /**
     * Given an incident ray, this returns the intersection between this face and the given ray.
     * If an intersection is not found, this returns {@code null}.
     *
     * @param incidentRay The incident ray
     * @return The point of intersection if found, {@code null} if not
     */
    @Nullable
    Vector3 getIntersection(@Nonnull Ray incidentRay);

    /**
     * Given an incident vector, this returns the reflection vector
     * if the vector were to collide with this face.
     *
     * @param incidentVector The incident directional vector
     * @return The reflection vector
     */
    @Nonnull
    Vector3 getReflectionVector(@Nonnull Vector3 incidentVector);

    //
    // Utility
    //

    /**
     * Applies given operator to each vertex of this face, then returns the resulting face.
     *
     * @param operator The operator to apply to each vertex of this face
     * @return The resulting face
     */
    @Nonnull
    Face apply(@Nonnull UnaryOperator<Vertex> operator);

    //
    // Serialization
    //

    /**
     * Serializes this face into a string.
     *
     * @return The string representation of this face
     */
    @Nonnull
    String toString();
}
