package civitas.celestis.graphics.model;

import civitas.celestis.math.vector.Decimal3;
import civitas.celestis.math.vector.Double3;
import civitas.celestis.math.vector.Float3;
import civitas.celestis.util.tuple.ArrayTuple;
import jakarta.annotation.Nonnull;

/**
 * A model face with three-dimensional vertices.
 *
 * @see ModelFace
 */
public class ModelFace3 implements ModelFace<Double3> {
    //
    // Constructors
    //

    /**
     * Creates a new model face.
     *
     * @param vertices The vertices of this face
     */
    public ModelFace3(@Nonnull Double3... vertices) {
        this.vertices = new ArrayTuple<>(vertices);
        this.normals = Models.calculateNormalVertices(vertices);
    }

    /***
     * Creates a new model face.
     * @param vertices The tuple of vertices
     * @param normals The tuple of normal vertices
     */
    public ModelFace3(@Nonnull ArrayTuple<Double3> vertices, @Nonnull ArrayTuple<Double3> normals) {
        if (vertices.length() != normals.length()) {
            throw new IllegalArgumentException("The number of vertices should match the number of normals.");
        }

        this.vertices = vertices;
        this.normals = normals;
    }

    //
    // Variables
    //

    /**
     * The tuple of vertices.
     */
    @Nonnull
    protected final ArrayTuple<Double3> vertices;

    /**
     * The tuple of normals.
     */
    @Nonnull
    protected final ArrayTuple<Double3> normals;

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
     * Returns the {@code i}th vertex of this face, converted into
     * a 32-bit single precision floating point vertex.
     *
     * @param i The index of the vertex to get
     * @return The {@code i}th vertex of this face
     * @throws IndexOutOfBoundsException When the index {@code i} is out of bounds
     */
    @Nonnull
    public Float3 getVertex32(int i) throws IndexOutOfBoundsException {
        return new Float3(vertices.get(i));
    }

    /**
     * Returns the {@code i}th vertex of this face, converted into
     * an arbitrary prevision decimal vertex.
     *
     * @param i The index of the vertex to get
     * @return The {@code i}th vertex of this face
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
     * Returns the {@code i}th normal vertex of this face, converted into
     * a 32-bit single precision floating point normal vertex.
     *
     * @param i The index of the normal vertex to get
     * @return The {@code i}th normal vertex of this face
     * @throws IndexOutOfBoundsException When the index {@code i} is out of bounds
     */
    @Nonnull
    public Float3 getNormal32(int i) throws IndexOutOfBoundsException {
        return new Float3(normals.get(i));
    }

    /**
     * Returns the {@code i}th normal vertex of this face, converted into
     * an arbitrary precision decimal normal vertex.
     *
     * @param i The index of the normal vertex to get
     * @return The {@code i}th normal vertex of this face
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
     * Serializes this face into a string for debugging purposes.
     *
     * @return The string representation of this face
     */
    @Override
    @Nonnull
    public String toString() {
        return "ModelFace3{" +
                "vertices=" + vertices +
                ", normals=" + normals +
                '}';
    }
}
