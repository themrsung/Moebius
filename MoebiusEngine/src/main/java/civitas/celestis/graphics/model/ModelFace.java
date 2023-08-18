package civitas.celestis.graphics.model;

import civitas.celestis.math.vector.Vector;
import jakarta.annotation.Nonnull;

/**
 * Represents the polygonal shape of a model's surface.
 * Model faces are immutable.
 *
 * @param <V> The type of vertex this model face should use
 */
public interface ModelFace<V extends Vector<?, V>> {
    //
    // Vertices
    //

    /**
     * Returns an iterable object containing the vertices of this face.
     *
     * @return An iterable object which contains the vertices of this face
     */
    @Nonnull
    Iterable<V> getVertices();

    /**
     * Returns the {@code i}th vertex of this face.
     *
     * @param i The index of the vertex to get
     * @return The {@code i}th vertex of this face
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
     * @return An iterable object which contains the normal vertices of this face
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
