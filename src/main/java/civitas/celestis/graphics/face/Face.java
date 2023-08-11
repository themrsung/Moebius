package civitas.celestis.graphics.face;

import civitas.celestis.math.quaternion.Quaternion;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.util.Copyable;
import civitas.celestis.util.group.Tuple;
import jakarta.annotation.Nonnull;

import java.awt.*;
import java.util.function.UnaryOperator;

/**
 * A triangular face.
 * Coordinates should be defined in counter-clockwise order.
 */
public interface Face extends Copyable<Face> {
    //
    // Geometry
    //

    /**
     * Gets the first vertex of this face.
     *
     * @return The first vertex of this face
     */
    @Nonnull
    Vector3 getA();

    /**
     * Gets the second vertex of this face.
     *
     * @return The second vertex of this face
     */
    @Nonnull
    Vector3 getB();

    /**
     * Gets the third vertex of this face.
     *
     * @return The third vertex of this face
     */
    @Nonnull
    Vector3 getC();

    /**
     * Gets a tuple of vertices of this face.
     *
     * @return A tuple containing the vertices of this face
     */
    @Nonnull
    Tuple<Vector3> getVertices();

    /**
     * Gets the surface normal of this face.
     *
     * @return The surface normal of this face
     */
    @Nonnull
    Vector3 getNormal();

    /**
     * Gets the geometric centroid of this face.
     *
     * @return The geometric centroid of this face
     */
    @Nonnull
    Vector3 getCentroid();

    //
    // Graphics
    //

    /**
     * Gets the color of this face.
     *
     * @return The color of this face
     */
    @Nonnull
    Color getColor();

    /**
     * Gets the alpha of this face between a range of {@code 0-1}.
     *
     * @return The alpha of this face
     */
    double getAlpha();

    /**
     * Gets whether this face allows the pass-through of rays.
     *
     * @return {@code true} if this face allows the pass-through of rays
     */
    boolean isTranslucent();

    //
    // Utility
    //

    /**
     * Applies given operator to all vertices of this face.
     *
     * @param operator The operator to apply to each vertex of this face
     * @return A new face containing the modified vertices
     */
    @Nonnull
    Face apply(@Nonnull UnaryOperator<Vector3> operator);

    /**
     * Inflates this face by given scalar.
     *
     * @param scale Scale factor to apply to each vertex of this face
     * @return The inflated face
     */
    @Nonnull
    Face inflate(double scale);

    /**
     * Translates this face into a relative coordinate system.
     *
     * @param origin The new origin of this face
     * @return The translated face
     */
    @Nonnull
    Face translate(@Nonnull Vector3 origin);

    /**
     * Rotates this face by a given rotation.
     *
     * @param rotation The rotation to apply to each vertex of this face
     * @return The rotated face
     */
    @Nonnull
    Face rotate(@Nonnull Quaternion rotation);

    /**
     * Transforms this face.
     *
     * @param origin   The new origin of this face
     * @param rotation The rotation to apply to this face
     * @param scale    The scale factor to apply to this face
     * @return The transformed face
     */
    @Nonnull
    Face transform(@Nonnull Vector3 origin, @Nonnull Quaternion rotation, double scale);
}
