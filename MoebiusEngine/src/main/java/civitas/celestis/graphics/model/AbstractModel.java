package civitas.celestis.graphics.model;

import civitas.celestis.math.vector.Vector;
import de.javagl.obj.FloatTuple;
import de.javagl.obj.Obj;
import de.javagl.obj.ObjFace;
import de.javagl.obj.ObjGroup;
import jakarta.annotation.Nonnull;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;

/**
 * The base class for three-dimensional models.
 *
 * @param <V> The type of vertex to use
 */
public abstract class AbstractModel<V extends Vector<?, V>> implements Model<V> {
    //
    // Constructors
    //

    /**
     * Creates a new model.
     *
     * @param obj              The {@link Obj} object to copy data from
     * @param vertexMapper     The mapper function for vertices
     * @param faceMapper       The mapper function for faces
     * @param groupConstructor The constructor function for a {@link ModelGroup}
     */
    public AbstractModel(
            @Nonnull Obj obj,
            @Nonnull Function<? super FloatTuple, V> vertexMapper,
            @Nonnull Function<? super ObjFace, ? extends ModelFace> faceMapper,
            @Nonnull Function<ModelFace[], ? extends ModelGroup> groupConstructor
    ) {
        final Map<ObjFace, ModelFace> faceMap = new HashMap<>();

        this.vertices = unpackVertices(obj, vertexMapper);
        this.normals = unpackNormals(obj, vertexMapper);
        this.textureCoordinates = unpackTextureCoordinates(obj, vertexMapper);

        this.faces = new ModelFace[obj.getNumFaces()];
        for (int i = 0; i < obj.getNumFaces(); i++) {
            final ObjFace f1 = obj.getFace(i);
            final ModelFace f2 = faceMapper.apply(f1);

            faceMap.put(f1, f2);
        }

        this.groups = new ModelGroup[obj.getNumGroups()];
        for (int i = 0; i < obj.getNumGroups(); i++) {
            final ObjGroup group = obj.getGroup(i);
            final ModelFace[] faces = new ModelFace[group.getNumFaces()];

            for (int j = 0; j < group.getNumFaces(); j++) {
                faces[j] = faceMap.get(group.getFace(j));
            }

            groups[i] = groupConstructor.apply(faces);
        }

        this.materialGroups = new HashMap<>();
        for (int i = 0; i < obj.getNumMaterialGroups(); i++) {
            final ObjGroup group = obj.getMaterialGroup(i);
            final String name = group.getName();
            final ModelFace[] faces = new ModelFace[group.getNumFaces()];

            for (int j = 0; j < group.getNumFaces(); j++) {
                faces[j] = faceMap.get(group.getFace(j));
            }

            materialGroups.put(name, groupConstructor.apply(faces));
        }
    }

    /**
     * Creates a new model.
     *
     * @param vertices           The vertices of this model
     * @param normals            The normal vertices of this model
     * @param textureCoordinates The texture coordinates of this model
     * @param faces              The faces of this model
     * @param groups             The groups of this model
     * @param materialGroups     The map of material groups of this model
     */
    public AbstractModel(
            @Nonnull V[] vertices,
            @Nonnull V[] normals,
            @Nonnull V[] textureCoordinates,
            @Nonnull ModelFace[] faces,
            @Nonnull ModelGroup[] groups,
            @Nonnull Map<String, ModelGroup> materialGroups
    ) {
        this.vertices = vertices;
        this.normals = normals;
        this.textureCoordinates = textureCoordinates;
        this.faces = faces;
        this.groups = groups;
        this.materialGroups = materialGroups;
    }

    /**
     * Unpacks the vertices of an {@link Obj}.
     *
     * @param obj The object to unpack
     * @param f   The mapper function to use to translate each vertex
     * @param <V> The type of vertex to use
     * @return The array of all vertices of the object
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    protected static <V extends Vector<?, V>> V[] unpackVertices(
            @Nonnull Obj obj,
            @Nonnull Function<? super FloatTuple, V> f
    ) {
        final V[] unpacked = (V[]) Array.newInstance(f.apply(obj.getVertex(0)).getClass(), obj.getNumVertices());

        for (int i = 0; i < obj.getNumVertices(); i++) {
            unpacked[i] = f.apply(obj.getVertex(i));
        }

        return unpacked;
    }

    /**
     * Unpacks the normal vertices of an {@link Obj}.
     *
     * @param obj The object to unpack
     * @param f   The mapper function to use to translate each vertex
     * @param <V> The type of vertex to use
     * @return The array of all normal vertices of the object
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    protected static <V extends Vector<?, V>> V[] unpackNormals(
            @Nonnull Obj obj,
            @Nonnull Function<? super FloatTuple, V> f
    ) {
        final V[] unpacked = (V[]) Array.newInstance(f.apply(obj.getVertex(0)).getClass(), obj.getNumNormals());

        for (int i = 0; i < obj.getNumNormals(); i++) {
            unpacked[i] = f.apply(obj.getNormal(i));
        }

        return unpacked;
    }

    /**
     * Unpacks the texture coordinates of an {@link Obj}.
     *
     * @param obj The object to unpack
     * @param f   The mapper function to use to translate each coordinate
     * @param <V> The type of texture coordinate to use
     * @return The array of all texture coordinate of the object
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    protected static <V extends Vector<?, V>> V[] unpackTextureCoordinates(
            @Nonnull Obj obj,
            @Nonnull Function<? super FloatTuple, V> f
    ) {
        final V[] unpacked = (V[]) Array.newInstance(f.apply(obj.getVertex(0)).getClass(), obj.getNumTexCoords());

        for (int i = 0; i < obj.getNumTexCoords(); i++) {
            unpacked[i] = f.apply(obj.getTexCoord(i));
        }

        return unpacked;
    }

    //
    // Variables
    //

    /**
     * The array of vertices.
     */
    @Nonnull
    protected final V[] vertices;
    /**
     * The array of normal vertices.
     */
    @Nonnull
    protected final V[] normals;

    /**
     * The array of texture coordinates.
     */
    @Nonnull
    protected final V[] textureCoordinates;

    /**
     * The array of faces.
     */
    @Nonnull
    protected final ModelFace[] faces;

    /**
     * The array of groups.
     */
    @Nonnull
    protected final ModelGroup[] groups;

    /**
     * The map of material groups.
     */
    @Nonnull
    protected final Map<String, ModelGroup> materialGroups;

    //
    // Vertices
    //

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    @Nonnull
    @SuppressWarnings("unchecked")
    public V[] getVertices() {
        final V[] result = (V[]) Array.newInstance(vertices[0].getClass(), vertices.length);
        System.arraycopy(vertices, 0, result, 0, vertices.length);
        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @param i The index of vertex to get
     * @return {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Nonnull
    @Override
    public V getVertex(int i) throws IndexOutOfBoundsException {
        return vertices[i];
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int getVertexCount() {
        return vertices.length;
    }

    //
    // Normals
    //

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    @Nonnull
    @SuppressWarnings("unchecked")
    public V[] getNormals() {
        final V[] result = (V[]) Array.newInstance(normals[0].getClass(), normals.length);
        System.arraycopy(normals, 0, result, 0, normals.length);
        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @param i The index of normal vertex to get
     * @return {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Nonnull
    @Override
    public V getNormal(int i) throws IndexOutOfBoundsException {
        return normals[i];
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int getNormalCount() {
        return normals.length;
    }

    //
    // Texture Coordinates
    //

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    @Nonnull
    @SuppressWarnings("unchecked")
    public V[] getTextureCoordinates() {
        final V[] result = (V[]) Array.newInstance(textureCoordinates[0].getClass(), textureCoordinates.length);
        System.arraycopy(textureCoordinates, 0, result, 0, textureCoordinates.length);
        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @param i The index of texture coordinate to get
     * @return {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Nonnull
    @Override
    public V getTextureCoordinate(int i) throws IndexOutOfBoundsException {
        return textureCoordinates[i];
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int getTextureCoordinateCount() {
        return textureCoordinates.length;
    }

    //
    // Faces
    //

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    @Nonnull
    public ModelFace[] getFaces() {
        return Arrays.stream(faces).toArray(ModelFace[]::new);
    }

    /**
     * {@inheritDoc}
     *
     * @param i The index of face to get
     * @return {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Nonnull
    @Override
    public ModelFace getFace(int i) throws IndexOutOfBoundsException {
        return faces[i];
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int getFaceCount() {
        return faces.length;
    }

    //
    // Groups
    //

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    @Nonnull
    public ModelGroup[] getGroups() {
        return Arrays.stream(groups).toArray(ModelGroup[]::new);
    }

    /**
     * {@inheritDoc}
     *
     * @param i The index of group to get
     * @return {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Nonnull
    @Override
    public ModelGroup getGroup(int i) throws IndexOutOfBoundsException {
        return groups[i];
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int getGroupCount() {
        return groups.length;
    }

    //
    // Material Groups
    //

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    @Nonnull
    public Map<String, ModelGroup> getMaterialGroups() {
        return Map.copyOf(materialGroups);
    }

    /**
     * {@inheritDoc}
     *
     * @param name The name of the material group to set
     * @return {@inheritDoc}
     * @throws NoSuchElementException {@inheritDoc}
     */
    @Nonnull
    @Override
    public ModelGroup getMaterialGroup(@Nonnull String name) throws NoSuchElementException {
        try {
            return Objects.requireNonNull(materialGroups.get(name));
        } catch (final NullPointerException e) {
            throw new NoSuchElementException(e);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int getMaterialGroupCount() {
        return materialGroups.size();
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Set<String> getMaterialGroupNames() {
        return materialGroups.keySet();
    }

    //
    // Serialization
    //

    /**
     * Serializes this model into a string.
     * This should only be used for debugging purposes.
     *
     * @return The string representation of this model
     */
    @Override
    public String toString() {
        return "AbstractModel{" +
                "vertices=" + Arrays.toString(vertices) +
                ", normals=" + Arrays.toString(normals) +
                ", textureCoordinates=" + Arrays.toString(textureCoordinates) +
                ", faces=" + Arrays.toString(faces) +
                ", groups=" + Arrays.toString(groups) +
                ", materialGroups=" + materialGroups +
                '}';
    }
}
