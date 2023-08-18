package civitas.celestis.graphics.model;

import civitas.celestis.math.vector.Double3;
import civitas.celestis.util.tuple.ArrayTuple;
import de.javagl.obj.ObjReader;
import jakarta.annotation.Nonnull;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * A utility class related to {@link Model} operations.
 */
public final class Models {
    //
    // IO
    //

    /**
     * Reads a Wavefront .OBJ file using the {@code de.javagl.obj} API.
     *
     * @param path The relative directory of the .OBJ file
     * @return A constructed model containing the file's data
     * @throws IOException When an exception occurs in the process of reading the file
     */
    @Nonnull
    public static Model3 readWavefrontObjFile(@Nonnull String path) throws IOException {
        return readWavefrontObjFile(new File(path));
    }

    /**
     * Reads a Wavefront .OBJ file using the {@code de.javagl.obj} API.
     *
     * @param file The {@link File} object containing the reference to the .OBJ file
     * @return A constructed model containing the file's data
     * @throws IOException When an exception occurs in the process of reading the file
     */
    @Nonnull
    public static Model3 readWavefrontObjFile(@Nonnull File file) throws IOException {
        try {
            return new Model3(ObjReader.read(new FileReader(file)));
        } catch (final Exception e) {
            throw new IOException(e);
        }
    }

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
