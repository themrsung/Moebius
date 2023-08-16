package civitas.celestis.graphics.model;

import civitas.celestis.math.vector.Double3;
import jakarta.annotation.Nonnull;

/**
 * A three-dimensional graphical model.
 */
public interface Model {
    //
    // Vertices
    //

    /**
     * Returns an array containing every vertex of this model.
     * Since this is a direct reference, changes in the return value will be reflected to this model.
     *
     * @return The array of every vertex of this model
     */
    @Nonnull
    Double3[] getVertices();

    /**
     * Returns the {@code i}th vertex of this model.
     *
     * @param i The index of vertex to get
     * @return The vertex at the specified index
     * @throws IndexOutOfBoundsException When the index {@code i} is out of bounds
     */
    @Nonnull
    Double3 getVertex(int i) throws IndexOutOfBoundsException;

    /**
     * Returns the number of vertices this model has.
     *
     * @return The number of vertices this model has
     */
    int getNumVertices();

    //
    // Faces
    //

    /**
     * Returns an array containing every face of this model.
     * Since this is a direct reference, changes in the return value will be reflected to this model.
     *
     * @return The array of every face of this model
     */
    @Nonnull
    ModelFace[] getFaces();

    /**
     * Returns the {@code i}th face of this model.
     *
     * @param i The index of face to get
     * @return The face at the specified index
     * @throws IndexOutOfBoundsException When the index {@code i} is out of bounds
     */
    @Nonnull
    ModelFace getFace(int i) throws IndexOutOfBoundsException;

    /**
     * Returns the number of faces this model has.
     *
     * @return The number of faces this model has
     */
    int getNumFaces();
}
