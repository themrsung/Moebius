package civitas.celestis.graphics.model;

import civitas.celestis.math.vector.Decimal3;
import civitas.celestis.math.vector.Double3;
import civitas.celestis.math.vector.Float3;
import civitas.celestis.util.tuple.ArrayTuple;
import de.javagl.obj.FloatTuple;
import de.javagl.obj.Obj;
import de.javagl.obj.ObjFace;
import jakarta.annotation.Nonnull;

import java.util.function.Function;

/**
 * A model with three-dimensional vertices.
 *
 * @see Model
 * @see Obj
 */
public class Model3 implements Model<Double3, ModelFace3> {
    //
    // Constants
    //

    /**
     * The mapper function to translate {@link Obj} vertices into Moebius vertices.
     * The X and Z coordinates are swapped to translate between coordinate systems.
     */
    private static final Function<FloatTuple, Double3> VERTEX_MAPPER =
            v -> new Double3(v.getZ(), v.getY(), v.getX());

    //
    // Constructors
    //

    /**
     * Creates a new model.
     *
     * @param obj The {@link Obj} object of which to copy data from
     */
    public Model3(@Nonnull Obj obj) {
        final Double3[] vertices = new Double3[obj.getNumVertices()];

        for (int i = 0; i < obj.getNumVertices(); i++) {
            vertices[i] = VERTEX_MAPPER.apply(obj.getVertex(i));
        }

        this.vertices = new ArrayTuple<>(vertices);
        this.normals = Models.calculateNormalVertices(vertices);

        final ModelFace3[] faces = new ModelFace3[obj.getNumFaces()];

        for (int i = 0; i < obj.getNumFaces(); i++) {
            final ObjFace face = obj.getFace(i);
            final int faceVertexCount = face.getNumVertices();

            final Double3[] faceVertices = new Double3[faceVertexCount];
            final Double3[] faceNormals = new Double3[faceVertexCount];

            for (int j = 0; j < faceVertexCount; j++) {
                // Use the same instances from the already constructed arrays to preserve memory
                faceVertices[j] = vertices[face.getVertexIndex(j)];
                faceNormals[j] = normals.get(face.getNormalIndex(j));
            }

            faces[i] = new ModelFace3(
                    new ArrayTuple<>(faceVertices),
                    new ArrayTuple<>(faceNormals)
            );
        }

        this.faces = new ArrayTuple<>(faces);
    }

    /**
     * Creates a new model.
     *
     * @param faces    The faces of this model
     * @param vertices The vertices of this model
     * @param normals  The normals of this model
     */
    public Model3(
            @Nonnull ArrayTuple<ModelFace3> faces,
            @Nonnull ArrayTuple<Double3> vertices,
            @Nonnull ArrayTuple<Double3> normals
    ) {
        if (vertices.length() != normals.length()) {
            throw new IllegalArgumentException("The number of vertices should match the number of faces.");
        }

        this.faces = faces;
        this.vertices = vertices;
        this.normals = normals;
    }

    //
    // Variables
    //

    /**
     * The faces of this model.
     */
    @Nonnull
    protected final ArrayTuple<ModelFace3> faces;

    /**
     * The vertices of this model.
     */
    @Nonnull
    protected final ArrayTuple<Double3> vertices;

    /**
     * The normals of this model.
     */
    @Nonnull
    protected final ArrayTuple<Double3> normals;

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
    public ArrayTuple<ModelFace3> getFaces() {
        return faces;
    }

    /**
     * {@inheritDoc}
     *
     * @param i The index of the face to get
     * @return {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Nonnull
    @Override
    public ModelFace3 getFace(int i) throws IndexOutOfBoundsException {
        return faces.get(i);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int getFaceCount() {
        return faces.length();
    }

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
    public ArrayTuple<Double3> getVertices() {
        return vertices;
    }

    /**
     * {@inheritDoc}
     *
     * @param i The index of the vertex to get
     * @return {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Nonnull
    @Override
    public Double3 getVertex(int i) throws IndexOutOfBoundsException {
        return vertices.get(i);
    }

    /**
     * Returns the {@code i}th vertex of this model, converted into
     * a 32-bit single precision floating point vertex.
     *
     * @param i The index of the vertex to get
     * @return The {@code i}th vertex of this model
     * @throws IndexOutOfBoundsException When the index {@code i} is out of bounds
     */
    @Nonnull
    public Float3 getVertex32(int i) throws IndexOutOfBoundsException {
        return new Float3(vertices.get(i));
    }

    /**
     * Returns the {@code i}th vertex of this model, converted into
     * an arbitrary prevision decimal vertex.
     *
     * @param i The index of the vertex to get
     * @return The {@code i}th vertex of this model
     * @throws IndexOutOfBoundsException When the index {@code i} is out of bounds
     */
    @Nonnull
    public Decimal3 getVertexDecimal(int i) throws IndexOutOfBoundsException {
        return new Decimal3(vertices.get(i));
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int getVertexCount() {
        return vertices.length();
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
    public ArrayTuple<Double3> getNormals() {
        return normals;
    }

    /**
     * {@inheritDoc}
     *
     * @param i The index of the normal vertex to get
     * @return {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Nonnull
    @Override
    public Double3 getNormal(int i) throws IndexOutOfBoundsException {
        return normals.get(i);
    }

    /**
     * Returns the {@code i}th normal vertex of this model, converted into
     * a 32-bit single precision floating point normal vertex.
     *
     * @param i The index of the normal vertex to get
     * @return The {@code i}th normal vertex of this model
     * @throws IndexOutOfBoundsException When the index {@code i} is out of bounds
     */
    @Nonnull
    public Float3 getNormal32(int i) throws IndexOutOfBoundsException {
        return new Float3(normals.get(i));
    }

    /**
     * Returns the {@code i}th normal vertex of this model, converted into
     * an arbitrary precision decimal normal vertex.
     *
     * @param i The index of the normal vertex to get
     * @return The {@code i}th normal vertex of this model
     * @throws IndexOutOfBoundsException When the index {@code i} is out of bounds
     */
    @Nonnull
    public Decimal3 getNormalDecimal(int i) throws IndexOutOfBoundsException {
        return new Decimal3(normals.get(i));
    }

    //
    // Serialization
    //

    /**
     * Serializes this model into a string for debugging purposes.
     *
     * @return The string representation of this model
     */
    @Override
    @Nonnull
    public String toString() {
        return "Model3{\n" +
                "  faces=" + faces + "\n" +
                "  vertices=" + vertices + "\n" +
                "  normals=" + normals + "\n" +
                '}';
    }
}
