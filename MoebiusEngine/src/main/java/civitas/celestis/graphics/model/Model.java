package civitas.celestis.graphics.model;

import civitas.celestis.math.vector.Vector;
import jakarta.annotation.Nonnull;

/**
 * Represents a graphical model of an object.
 *
 * @param <V> The type of vertex this model should use
 * @param <F> The type of face this model should use
 */
public interface Model<V extends Vector<?, V>, F extends ModelFace<V>> {
    //
    // Faces
    //

    /**
     * Returns an iterable object containing the faces of this model.
     *
     * @return An iterable object containing the faces of this model
     */
    @Nonnull
    Iterable<F> getFaces();

    /**
     * Returns the {@code i}th face of this model.
     *
     * @param i The index of the face to get
     * @return The {@code i}th face of this model
     * @throws IndexOutOfBoundsException When the index {@code i} is out of bounds
     */
    @Nonnull
    F getFace(int i) throws IndexOutOfBoundsException;

    /**
     * Returns the number of faces this model has.
     *
     * @return The number of faces this model has
     */
    int getFaceCount();

    //
    // Vertices
    //

    /**
     * Returns an iterable object containing the vertices of this model.
     *
     * @return An iterable object containing the vertices of this model
     */
    @Nonnull
    Iterable<V> getVertices();

    /**
     * Returns the {@code i}th vertex of this model.
     *
     * @param i The index of the vertex to get
     * @return The {@code i}th vertex of this model
     * @throws IndexOutOfBoundsException When the index {@code i} is out of bounds
     */
    @Nonnull
    V getVertex(int i) throws IndexOutOfBoundsException;

    /**
     * Returns the number of vertices this model has.
     *
     * @return The number of vertices this model has
     */
    int getVertexCount();

    //
    // Normals
    //

    /**
     * Returns an iterable object containing the normal vertices of this face.
     *
     * @return An iterable object containing the normal vertices of this face
     */
    @Nonnull
    Iterable<V> getNormals();

    /**
     * Returns the {@code i}th normal vertex of this face.
     *
     * @param i The index of the normal vertex to get
     * @return The {@code i}th normal vertex of this face
     * @throws IndexOutOfBoundsException When the index {@code i} is out of bounds
     */
    @Nonnull
    V getNormal(int i) throws IndexOutOfBoundsException;
}
