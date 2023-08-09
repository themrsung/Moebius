package civitas.celestis.graphics.face;

import civitas.celestis.math.quaternion.Quaternion;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.util.Copyable;
import civitas.celestis.util.group.Tuple;
import jakarta.annotation.Nonnull;

import java.util.function.UnaryOperator;

/**
 * A triangular face.
 * Coordinates should be defined in counter-clockwise order.
 */
public interface Face extends Copyable<Face> {
    //
    // Properties
    //

    /**
     * Gets the first point of this face.
     *
     * @return The first point of this face
     */
    @Nonnull
    Vector3 getA();

    /**
     * Gets the second point of this face.
     *
     * @return The second point of this face
     */
    @Nonnull
    Vector3 getB();

    /**
     * Gets the third point of this face.
     *
     * @return The third point of this face
     */
    @Nonnull
    Vector3 getC();

    /**
     * Gets a tuple object containing the three points of this face.
     *
     * @return A tuple of all points of this face
     */
    @Nonnull
    Tuple<Vector3> getPoints();

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
     * @return The centroid of this face
     */
    @Nonnull
    Vector3 getCentroid();

    /**
     * On a scale of {@code 0-1}, this returns the reflectiveness of this face.
     *
     * @return The reflectiveness of this face
     */
    double getReflectiveness();

    /**
     * Whether this face allows rays to pass through.
     *
     * @return {@code true} if this face allows the pass-through of rays
     */
    boolean isTranslucent();

    /**
     * Sets the reflectiveness of this face on a scale of {@code 0-1}.
     *
     * @param reflectiveness The reflectiveness of this face
     */
    void setReflectiveness(double reflectiveness);

    /**
     * Sets whether this face allows the pass-through of rays.
     *
     * @param translucent {@code true} to allow the pass-through of rays
     */
    void setTranslucent(boolean translucent);

    //
    // Utility
    //

    /**
     * Applies given operator to all vertices of this face, then returns a new instance.
     *
     * @param operator Operator to apply to each vertex of this face
     * @return A new face containing the modified vertices
     */
    @Nonnull
    Face apply(@Nonnull UnaryOperator<Vector3> operator);

    /**
     * Inflates this face by given scalar.
     *
     * @param scale Scale factor to apply to all vertices.
     * @return The resulting face
     */
    @Nonnull
    Face inflate(double scale);

    /**
     * Translates the origin of this face.
     *
     * @param origin The new origin of this face
     * @return The resulting face
     */
    @Nonnull
    Face translate(@Nonnull Vector3 origin);

    /**
     * Rotates every vertex of this face.
     *
     * @param rotation Rotation to apply to all vertices of this face
     * @return The resulting face
     */
    @Nonnull
    Face rotate(@Nonnull Quaternion rotation);

    /**
     * Transforms this face into a relative coordinate system.
     *
     * @param origin   New origin of this face
     * @param rotation Rotation to apply to this face
     * @return The resulting face
     */
    @Nonnull
    Face transform(@Nonnull Vector3 origin, @Nonnull Quaternion rotation);

    /**
     * Transforms this face into a relative coordinate system.
     *
     * @param origin   New origin of this face
     * @param rotation Rotation to apply to this face
     * @param scale    Scale factor to apply to this face
     * @return The resulting face
     */
    @Nonnull
    Face transform(@Nonnull Vector3 origin, @Nonnull Quaternion rotation, double scale);

    /**
     * Offsets this face into a new coordinate system.
     *
     * @param offset   Offset to apply to each vertex of this face
     * @param rotation Rotation to apply to this face
     * @return The resulting face
     */
    @Nonnull
    Face offset(@Nonnull Vector3 offset, @Nonnull Quaternion rotation);
}
