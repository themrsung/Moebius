package civitas.celestis.graphics.face;

import civitas.celestis.math.quaternion.Quaternion;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.util.group.Tuple;
import jakarta.annotation.Nonnull;

import java.util.function.UnaryOperator;

/**
 * A triangular face. Faces are used as the base building blocks for 3D models.
 * Vertices are assumed to be in counter-clockwise order.
 */
public class Face {
    //
    // Constructors
    //

    /**
     * Creates a new face from three vertices.
     *
     * @param a The first vertex of this face
     * @param b The second vertex of this face
     * @param c The third vertex of this face
     */
    public Face(@Nonnull Vector3 a, @Nonnull Vector3 b, @Nonnull Vector3 c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Creates a new face from a tuple of vertices.
     *
     * @param tuple The tuple containing the vertices of this face
     */
    public Face(@Nonnull Tuple<Vector3> tuple) {
        this(tuple.a(), tuple.b(), tuple.c());
    }

    /**
     * Creates a new face from another face.
     *
     * @param other Face to copy the properties from
     */
    public Face(@Nonnull Face other) {
        this.a = other.a;
        this.b = other.b;
        this.c = other.c;
    }

    //
    // Variables
    //

    @Nonnull
    private final Vector3 a, b, c;

    //
    // Properties
    //

    /**
     * Gets the first vertex of this face.
     *
     * @return THe first vertex
     */
    @Nonnull
    public Vector3 getA() {
        return a;
    }

    /**
     * Gets the second vertex of this face.
     *
     * @return The second vertex
     */
    @Nonnull
    public Vector3 getB() {
        return b;
    }

    /**
     * Gets the third vertex of this face.
     *
     * @return The third vertex
     */
    @Nonnull
    public Vector3 getC() {
        return c;
    }

    /**
     * Gets a tuple containing the points of this face.
     *
     * @return Tuple of points of this face
     */
    @Nonnull
    public Tuple<Vector3> getPoints() {
        return new Tuple<>(a, b, c);
    }

    /**
     * Gets the geometric centroid of this face.
     *
     * @return The geometric centroid
     */
    @Nonnull
    public Vector3 getCentroid() {
        return a.add(b).add(c).divide(3);
    }

    /**
     * Gets the surface normal of this face.
     *
     * @return The surface normal of this face
     */
    @Nonnull
    public Vector3 getNormal() {
        return b.subtract(a).cross(c.subtract(a));
    }

    //
    // Utility
    //

    /**
     * Applies given operator to all vertices of this face, then returns the resulting face.
     *
     * @param operator Operator to apply to each vertex of this face
     * @return The resulting face
     */
    @Nonnull
    public Face apply(@Nonnull UnaryOperator<Vector3> operator) {
        return new Face(getPoints().apply(operator));
    }

    /**
     * Inflates this face by given scale factor.
     *
     * @param factor Scale factor to inflate by
     * @return The resulting face
     */
    @Nonnull
    public Face inflate(double factor) {
        return new Face(getPoints().apply(v -> v.multiply(factor)));
    }

    /**
     * Translates this face into a relative coordinate system.
     *
     * @param origin New origin of this face
     * @return The resulting face
     */
    @Nonnull
    public Face translate(@Nonnull Vector3 origin) {
        return new Face(getPoints().apply(v -> v.subtract(origin)));
    }

    /**
     * Rotates this face by given rotation.
     *
     * @param rotation Rotation to apply to all vertices
     * @return The resulting face
     */
    @Nonnull
    public Face rotate(@Nonnull Quaternion rotation) {
        return new Face(getPoints().apply(v -> v.rotate(rotation)));
    }

    /**
     * Transforms this face.
     *
     * @param origin   New origin of this face
     * @param rotation Rotation to apply to all vertices
     * @return The resulting face
     */
    @Nonnull
    public Face transform(@Nonnull Vector3 origin, @Nonnull Quaternion rotation) {
        return new Face(getPoints().apply(v -> v.subtract(origin).rotate(rotation)));
    }

    /**
     * Transforms this face.
     *
     * @param origin      New origin of this face
     * @param rotation    Rotation to apply to all vertices
     * @param scaleFactor Scale factor to apply to all vertices
     * @return The resulting face
     */
    @Nonnull
    public Face transform(@Nonnull Vector3 origin, @Nonnull Quaternion rotation, double scaleFactor) {
        return new Face(getPoints().apply(v -> v.subtract(origin).rotate(rotation).multiply(scaleFactor)));
    }

    /**
     * Translates this face into an absolute coordinate system.
     *
     * @param location Location of this face relative to origin
     * @param rotation Rotation of this face
     * @return The resulting face
     */
    @Nonnull
    public Face absolute(@Nonnull Vector3 location, @Nonnull Quaternion rotation) {
        return new Face(getPoints().apply(v -> v.rotate(rotation).add(location)));
    }
}
