package civitas.celestis.physics.solid;

import civitas.celestis.math.vector.Vector3;
import jakarta.annotation.Nonnull;

/**
 * An object which represents a segment of three-dimensional space.
 */
public interface Solid {
    //
    // Properties
    //

    /**
     * Gets the geometric centroid of this solid.
     *
     * @return The geometric centroid of this solid
     */
    @Nonnull
    Vector3 getCentroid();

    /**
     * Gets an array of corners of this solid.
     *
     * @return Array of this solid's corners
     */
    @Nonnull
    Vector3[] getCorners();

    /**
     * Gets the volume of this solid.
     *
     * @return The volume of this solid
     */
    double getVolume();

    /**
     * Gets the surface area of this solid.
     *
     * @return The surface area of this solid
     */
    double getSurfaceArea();

    /**
     * Gets the coefficient of drag when viewed from given direction.
     *
     * @param incidentDirection The incident direction to view this solid from
     * @return The coefficient of drag of this solid
     */
    double getDragCoefficient(@Nonnull Vector3 incidentDirection);

    /**
     * Gets the cross-sectional area when viewed from given direction.
     *
     * @param incidentDirection The incident direction to view this solid from
     * @return The cross-sectional area of this solid
     */
    double getCrossSection(@Nonnull Vector3 incidentDirection);

    //
    // Methods
    //

    /**
     * Checks if this solid contains given point.
     *
     * @param point Point to check
     * @return {@code true} if this solid contains the point
     */
    boolean contains(@Nonnull Vector3 point);

    /**
     * Checks if this solid overlaps another solid.
     *
     * @param other Solid to check
     * @return {@code true} if the two solids have at least one point in common
     */
    boolean overlaps(@Nonnull Solid other);
}
