package civitas.celestis.graphics.model;

import civitas.celestis.math.matrix.IntMatrix;
import de.javagl.obj.ObjFace;
import jakarta.annotation.Nonnull;

/**
 * The triangular face of a model.
 * Vertices are stored as reference indices instead of their raw values.
 *
 * @see ObjFace
 */
public class ModelFace extends IntMatrix {
    //
    // Constructors
    //

    /**
     * Creates a new model face. The values should be mapped as follows.
     * <p>
     * The first row should contain the vertices of this face
     * according to a right-handed coordinate system.<br>
     * The second row should contain the normal vertices of this face
     * according to a right-handed coordinate system.<br>
     * The third row should contain the texture coordinates of this face
     * according to a right-handed coordinate system.
     * </p>
     *
     * @param values The vertex indices of this face
     */
    public ModelFace(@Nonnull int[][] values) {
        super(values);
    }

    /**
     * Creates a new model face.
     *
     * @param f The {@link ObjFace} of which to copy indices from
     */
    public ModelFace(@Nonnull ObjFace f) {
        super(unpackObjFace(f));
    }

    /**
     * Unpacks ths indices of an {@link ObjFace} into a 3x3 grid of {@code int}s.
     *
     * @param f The face to unpack
     * @return The unpacked 2D array of indices
     */
    @Nonnull
    private static int[][] unpackObjFace(@Nonnull ObjFace f) {
        final int[][] unpacked = new int[3][3];

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                unpacked[r][c] = switch (r) {
                    case 0 -> f.getVertexIndex(c);
                    case 1 -> f.getNormalIndex(c);
                    case 2 -> f.getTexCoordIndex(c);
                    default ->
                            throw new UnknownError("An unknown error occurred while unpacking an ObjFace into a ModelFace.");
                };
            }
        }

        return unpacked;
    }

    //
    // Getters
    //

    /**
     * Returns the index of the {@code i}th vertex of this face.
     *
     * @param i The index of the vertex to get ({@code 0-2})
     * @return The index of the vertex of the specified index
     * @throws IndexOutOfBoundsException When the index {@code i} is out of bounds
     */
    public int getVertexIndex(int i) throws IndexOutOfBoundsException {
        return values[0][i];
    }

    /**
     * Returns the index of the {@code i}th normal vertex of this face.
     *
     * @param i The index of the normal vertex to get ({@code 0-2})
     * @return The index of the normal vertex of the specified index
     * @throws IndexOutOfBoundsException When the index {@code i} is out of bounds
     */
    public int getNormalIndex(int i) throws IndexOutOfBoundsException {
        return values[1][i];
    }

    /**
     * Returns the index of the {@code i}th texture coordinate of this face.
     *
     * @param i The index of the texture coordinate to get ({@code 0-2})
     * @return The index of ths texture coordinate of the specified index
     * @throws IndexOutOfBoundsException When the index {@code i} is out of bounds
     */
    public int getTextureCoordinateIndex(int i) throws IndexOutOfBoundsException {
        return values[2][i];
    }
}
