package civitas.celestis.graphics.model;

import civitas.celestis.math.vector.Vector;
import jakarta.annotation.Nonnull;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * A three-dimensional graphical model.
 *
 * @param <V> The type of vertex this model uses
 */
public interface Model<V extends Vector<?, V>> {
    //
    // Vertices
    //

    /**
     * Returns an array containing every vertex of this model.
     * This is a copied array, and changes to the return value will not be reflected in this model.
     *
     * @return The array of every vertex of this model
     */
    @Nonnull
    V[] getVertices();

    /**
     * Returns the {@code i}th vertex of this model.
     *
     * @param i The index of vertex to get
     * @return The vertex at the specified index
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
     * Returns an array of the normal vertices of this model.
     * This is a copied array, and changes to the return value will not be reflected in this model.
     *
     * @return The array of every normal vertex of this model
     */
    @Nonnull
    V[] getNormals();

    /**
     * Returns the {@code i}th normal vertex of this model.
     *
     * @param i The index of normal vertex to get
     * @return The normal vertex at the specified index
     * @throws IndexOutOfBoundsException When the index {@code i} is out of bounds
     */
    @Nonnull
    V getNormal(int i) throws IndexOutOfBoundsException;

    /**
     * Returns the number of normal vertices this model has.
     *
     * @return The number of normal vertices this model has
     */
    int getNormalCount();

    //
    // Texture Coordinates
    //

    /**
     * Returns an array of the texture coordinates of this model.
     * This is a copied array, and changes to the return value will not be reflected in this model.
     *
     * @return The array of every texture coordinate of this model
     */
    @Nonnull
    V[] getTextureCoordinates();

    /**
     * Returns the {@code i}th texture coordinate of this model.
     *
     * @param i The index of texture coordinate to get
     * @return The texture coordinate at the specified index
     * @throws IndexOutOfBoundsException When the index {@code i} is out of bounds
     */
    @Nonnull
    V getTextureCoordinate(int i) throws IndexOutOfBoundsException;

    /**
     * Returns the number of texture coordinates this model has.
     *
     * @return The number of texture coordinates this model has
     */
    int getTextureCoordinateCount();

    //
    // Faces
    //

    /**
     * Returns an array containing every face of this model.
     * This is a copied array, and changes to the return value will not be reflected in this model.
     *
     * @return The array of every face of this model
     * @see ModelFace
     */
    @Nonnull
    ModelFace[] getFaces();

    /**
     * Returns the {@code i}th face of this model.
     *
     * @param i The index of face to get
     * @return The face at the specified index
     * @throws IndexOutOfBoundsException When the index {@code i} is out of bounds
     * @see ModelFace
     */
    @Nonnull
    ModelFace getFace(int i) throws IndexOutOfBoundsException;

    /**
     * Returns the number of faces this model has.
     *
     * @return The number of faces this model has
     */
    int getFaceCount();

    //
    // Groups
    //

    /**
     * Returns an array containing the groups of this model.
     * This is a copied array, and changes to the return value will not be reflected in this model.
     *
     * @return The array of every group of this model
     * @see ModelGroup
     */
    @Nonnull
    ModelGroup[] getGroups();

    /**
     * Returns the {@code i}th group of this model.
     *
     * @param i The index of group to get
     * @return The group at the specified index
     * @throws IndexOutOfBoundsException When the index {@code i} is out of bounds
     * @see ModelGroup
     */
    @Nonnull
    ModelGroup getGroup(int i) throws IndexOutOfBoundsException;

    /**
     * Returns the number of groups this model has.
     *
     * @return The number of groups this model has
     */
    int getGroupCount();

    //
    // Material Groups
    //

    /**
     * Returns a map containing the material groups of this model.
     * This is a copied map, and changes to the return value will not be reflected in this model.
     *
     * @return The map of every material group of this model
     */
    @Nonnull
    Map<String, ? extends ModelGroup> getMaterialGroups();

    /**
     * Gets a material group by name.
     *
     * @param name The name of the material group to set
     * @return The material group of the specified name
     * @throws NoSuchElementException When a material group of matching name cannot be found
     */
    @Nonnull
    ModelGroup getMaterialGroup(@Nonnull String name) throws NoSuchElementException;

    /**
     * Returns the number of material groups this model has.
     *
     * @return The number of material groups this model has
     */
    int getMaterialGroupCount();

    /**
     * Returns a set of strings containing the material group names of this model.
     *
     * @return A set of strings containing the material group names of this model
     */
    @Nonnull
    Set<String> getMaterialGroupNames();
}
