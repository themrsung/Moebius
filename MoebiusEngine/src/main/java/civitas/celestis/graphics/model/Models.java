package civitas.celestis.graphics.model;

import civitas.celestis.math.vector.Double3;
import civitas.celestis.util.tuple.ArrayTuple;
import jakarta.annotation.Nonnull;

/**
 * A utility class related to {@link Model} operations.
 */
public final class Models {
    //
    // Normal Computation
    //

    /**
     * Given an array of vertices, this calculates the normal vertices of the face,
     * then returns them packaged into an {@link ArrayTuple}.
     * The normal vertices are normalized.
     *
     * @param vertices The vertices to get the normal vertex of
     * @return The normal vertices of the face
     */
    @Nonnull
    public static ArrayTuple<Double3> calculateNormalVertices(@Nonnull Double3... vertices) {
        final Double3[] edges = new Double3[vertices.length];

        for (int i = 0; i < vertices.length; i++) {
            edges[i] = vertices[(i + 1) % vertices.length].subtract(vertices[i]);
        }

        final Double3[] normals = new Double3[edges.length];

        for (int i = 0; i < normals.length; i++) {
            try {
                normals[i] = edges[i].cross(edges[(i + 1) % normals.length]).normalize();
            } catch (final ArithmeticException e) {
                normals[i] = Double3.ZERO; // Special case for zero vectors
            }
        }

        return new ArrayTuple<>(normals);
    }
}
