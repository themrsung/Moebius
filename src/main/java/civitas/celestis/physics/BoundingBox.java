package civitas.celestis.physics;

import civitas.celestis.graphics.face.Face;
import civitas.celestis.graphics.raytracing.Ray;
import civitas.celestis.math.Numbers;
import civitas.celestis.math.vector.Vector3;
import jakarta.annotation.Nonnull;

/**
 * A non-rotatable cuboid in 3D space.
 * Bounding boxes are used for rough collision and intersection tests.
 */
public class BoundingBox {
    //
    // Constructors
    //

    /**
     * Creates a new bounding box.
     *
     * @param min The minimum bounds of this box
     * @param max The maximum bounds of this box
     */
    public BoundingBox(@Nonnull Vector3 min, @Nonnull Vector3 max) {
        this.min = min;
        this.max = max;
    }

    /**
     * Creates a new bounding box.
     * The minimum and maximum vectors are automatically derived from the provided array.
     *
     * @param points An array of points to use as bounds
     */
    public BoundingBox(@Nonnull Vector3... points) {
        this.min = Numbers.min(points);
        this.max = Numbers.max(points);
    }

    /**
     * Creates a new bounding box.
     *
     * @param face The face to use as the boundaries
     */
    public BoundingBox(@Nonnull Face face) {
        this(face.toArray());
    }

    //
    // Variables
    //

    @Nonnull
    protected final Vector3 min, max;

    //
    // Getters
    //

    /**
     * Returns the minimum bounds of this box.
     *
     * @return The minimum bounds of this box
     */
    @Nonnull
    public final Vector3 min() {
        return min;
    }

    /**
     * Returns the maximum bounds of this box.
     *
     * @return The maximum bounds of this box
     */
    @Nonnull
    public final Vector3 max() {
        return max;
    }

    /**
     * Returns the center of this bounding box.
     *
     * @return The center of this bounding box
     */
    @Nonnull
    public Vector3 center() {
        return min.add(max).divide(2);
    }

    /**
     * Checks if given point {@code v} is within the bounds of this box.
     *
     * @param v The point to check
     * @return {@code true} if this box contains the given point
     */
    public final boolean contains(@Nonnull Vector3 v) {
        return Numbers.isInRange(v, min, max);
    }

    /**
     * Checks if given face {@code f} is within the bounds of this box.
     *
     * @param f The face to check
     * @return {@code true} if this box contains the given face
     */
    public final boolean contains(@Nonnull Face f) {
        return Numbers.isInRange(f.getA(), min, max) ||
                Numbers.isInRange(f.getB(), min, max) ||
                Numbers.isInRange(f.getC(), min, max);
    }

    /**
     * Checks if the given ray intersects this box.
     *
     * @param ray The ray to check for intersections
     * @return {@code true} if the ray intersects this box
     */
    public boolean intersects(@Nonnull Ray ray) {
        double tmin = (min.x() - ray.origin().x()) / ray.direction().x();
        double tmax = (max.x() - ray.origin().x()) / ray.direction().x();

        if (tmin > tmax) {
            double temp = tmin;
            tmin = tmax;
            tmax = temp;
        }

        double tymin = (min.y() - ray.origin().y()) / ray.direction().y();
        double tymax = (max.y() - ray.origin().y()) / ray.direction().y();

        if (tymin > tymax) {
            double temp = tymin;
            tymin = tymax;
            tymax = temp;
        }

        if ((tmin > tymax) || (tymin > tmax)) {
            return false;
        }

        if (tymin > tmin) {
            tmin = tymin;
        }

        if (tymax < tmax) {
            tmax = tymax;
        }

        double tzmin = (min.z() - ray.origin().z()) / ray.direction().z();
        double tzmax = (max.z() - ray.origin().z()) / ray.direction().z();

        if (tzmin > tzmax) {
            double temp = tzmin;
            tzmin = tzmax;
            tzmax = temp;
        }

        return !(tmin > tzmax) && !(tzmin > tmax);
    }
}
