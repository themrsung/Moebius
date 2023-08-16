package civitas.celestis.math.physics;

import civitas.celestis.math.vector.Double3;
import civitas.celestis.math.vector.Float3;
import civitas.celestis.math.vector.Int3;
import civitas.celestis.math.vector.Long3;
import jakarta.annotation.Nonnull;

/**
 * A solid in a three-dimensional Euclidean space.
 */
public interface Solid {
    //
    // Properties
    //

    /**
     * Returns an array of corners this solid has.
     * If this solid has no corners, (the solid is spherical)
     * this will return an empty array.
     *
     * @return The array of this solid's corners
     */
    @Nonnull
    Double3[] getCorners();

    /**
     * Returns the number of corners this solid has.
     * If this solid has no corners, (the solid is spherical)
     * this will return {@code 0}.
     *
     * @return The number of corners this solid has
     */
    int getNumCorners();

    /**
     * Returns the geometric centroid of this solid.
     *
     * @return The centroid of this solid
     */
    @Nonnull
    Double3 getCenter();

    /**
     * Returns the volume of this solid.
     *
     * @return The volume of this solid
     */
    double getVolume();

    /**
     * Returns the bounding box of this solid.
     *
     * @return The bounding box of this solid
     */
    @Nonnull
    BoundingBox getBoundingBox();

    //
    // Containment
    //

    /**
     * Returns whether this solid contains the given vertex {@code v}.
     *
     * @param v The vertex to check for containment
     * @return {@code true} if the vertex is within the bounds of this solid
     */
    boolean contains(@Nonnull Double3 v);

    /**
     * Returns whether this solid contains the given vertex {@code v}.
     *
     * @param v The vertex to check for containment
     * @return {@code true} if the vertex is within the bounds of this solid
     */
    default boolean contains(@Nonnull Float3 v) {
        return contains(new Double3(v));
    }

    /**
     * Returns whether this solid contains the given vertex {@code v}.
     *
     * @param v The vertex to check for containment
     * @return {@code true} if the vertex is within the bounds of this solid
     */
    default boolean contains(@Nonnull Long3 v) {
        return contains(new Double3(v));
    }

    /**
     * Returns whether this solid contains the given vertex {@code v}.
     *
     * @param v The vertex to check for containment
     * @return {@code true} if the vertex is within the bounds of this solid
     */
    default boolean contains(@Nonnull Int3 v) {
        return contains(new Double3(v));
    }

    //
    // Overlapping
    //

    /**
     * Returns whether this solid overlaps the given solid {@code s}.
     *
     * @param s The solid to compare to
     * @return {@code true} if the two solids overlap
     */
    boolean overlaps(@Nonnull Solid s);
}
