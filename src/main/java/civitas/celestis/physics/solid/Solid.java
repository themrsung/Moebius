package civitas.celestis.physics.solid;

import civitas.celestis.graphics.face.Face;
import civitas.celestis.graphics.raytracing.Ray;
import civitas.celestis.math.vector.Vector3;
import jakarta.annotation.Nonnull;

/**
 * A solid in 3D space.
 * Solids are used for physics calculations.
 */
public interface Solid {
    //
    // Geometry
    //

    /**
     * Returns the geometric centroid of this solid.
     * @return The geometric centroid of this solid
     */
    @Nonnull
    Vector3 center();

    /**
     * Returns the volume of this solid.
     *
     * @return The volume of this solid
     */
    double volume();

    /**
     * Returns an array of corners of this solid.
     * If this solid is not a sphere, checking {@link Solid#contains(Vector3)} with each
     * element of this array will accurately determine if the two solids overlap.
     *
     * @return The array of corners of this solid
     */
    @Nonnull
    Vector3[] corners();

    /**
     * Checks if given point {@code v} is within the bounds of this solid.
     *
     * @param v The point to check
     * @return {@code true} if this solid contains the given point
     */
    boolean contains(@Nonnull Vector3 v);

    /**
     * Checks if given face {@code f} is within the bounds of this solid.
     * This only returns {@code true} if all vertices of this face are within the bounds of this solid.
     *
     * @param f The face to check
     * @return {@code true} if this solid contains the given face
     */
    boolean contains(@Nonnull Face f);

    /**
     * Checks if this solid completely encapsulates the other solid.
     *
     * @param s The solid to compare to
     * @return {@code true} if there is no point of the other solid which is not part of this solid
     */
    boolean contains(@Nonnull Solid s);

    /**
     * Checks if this solid overlaps the given face {@code f}.
     *
     * @param f The face to compare to
     * @return {@code true} if there is at least one point in common between the face and this solid
     */
    boolean overlaps(@Nonnull Face f);

    /**
     * Checks if this solid overlaps another solid.
     * This returns {@code true} if there is at least one point in common between the two solids.
     *
     * @param s The solid to compare to
     * @return {@code true} if the two solids overlap
     */
    boolean overlaps(@Nonnull Solid s);

    //
    // Raytracing
    //

    /**
     * Checks if the given ray intersects this solid.
     * This method favors speed over accuracy, and may not be entirely accurate.
     *
     * @param ray The ray to check for intersection
     * @return {@code true} if the ray intersects this solid
     */
    boolean intersects(@Nonnull Ray ray);

    //
    // Bounding Box
    //

    /**
     * Returns the bounding box of this solid for rough physics calculations.
     * Note that bounding boxes do not accurately represent the profile of this solid.
     *
     * @return The bounding box of this solid
     */
    @Nonnull
    BoundingBox boundingBox();
}
