package civitas.celestis.graphics.model;

import civitas.celestis.graphics.face.Face;
import civitas.celestis.math.vertex.Vertex;
import jakarta.annotation.Nonnull;

import java.io.Serializable;
import java.util.List;

/**
 * A model object used to store data about an object's graphical profile.
 * Models have limited mutability.
 */
public interface Model extends Serializable {
    //
    // Parser
    //

    /**
     * Deserializes a string into a model.
     *
     * @param input The input string to parse
     * @return The parsed model
     * @throws IllegalArgumentException When the format is invalid
     */
    @Nonnull
    static Model parseModel(@Nonnull String input) throws IllegalArgumentException {
        try {return ColoredModel.parseModel(input);} catch (final IllegalArgumentException ignored) {}

        throw new IllegalArgumentException("Given string is not a model.");
    }

    //
    // Getters
    //

    /**
     * Returns all faces of this model.
     * This is not a copied list, and changes to the return value will be reflected in this model.
     *
     * @return A list containing every face of this model
     */
    @Nonnull
    List<? extends Face> getFaces();

    /**
     * Returns the {@code i}th face of this model.
     *
     * @param i The index of the face to get
     * @return The face at the specified index
     * @throws IndexOutOfBoundsException When the index {@code i} is out of bounds
     */
    @Nonnull
    Face getFace(int i) throws IndexOutOfBoundsException;

    /**
     * Returns the face count of this model.
     *
     * @return The number of faces this model has
     */
    int getFaceCount();

    /**
     * Returns a list of all vertices of this model.
     *
     * @return A list containing all vertices of this model
     */
    @Nonnull
    List<Vertex> getVertices();

    //
    // Serialization
    //

    /**
     * Serializes this model into a string.
     *
     * @return The string representation of this model
     */
    @Nonnull
    String toString();
}
