package civitas.celestis.physics.solid;

import civitas.celestis.graphics.face.Face;
import civitas.celestis.graphics.model.Model;
import civitas.celestis.graphics.raytracing.Ray;
import civitas.celestis.math.Numbers;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.math.vertex.Vertex;
import jakarta.annotation.Nonnull;

/**
 * A non-rotatable cuboid in 3D space.
 * Bounding boxes are used for rough collision and intersection tests.
 */
public class BoundingBox implements Solid {
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
     * Creates a new bounding box from a face.
     * Note that this constructor may be very slow if the model's vertex count is high.
     * Precomputing this at compile time or caching the created box is recommended.
     *
     * @param model The model to create the bounding box from
     */
    public BoundingBox(@Nonnull Model model) {
        this(model.getVertices().toArray(new Vector3[0]));
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
     * Returns the dimensions of this box.
     *
     * @return The dimensions of this box
     */
    @Nonnull
    public final Vector3 dimensions() {
        return max.subtract(min);
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
     * {@inheritDoc}
     *
     * @return The volume of this box
     */
    @Override
    public double volume() {
        final Vector3 dimensions = dimensions();
        return dimensions.x() * dimensions.y() * dimensions.z();
    }

    /**
     * {@inheritDoc}
     *
     * @return The array of corners of this box
     */
    @Nonnull
    @Override
    public Vector3[] corners() {
        return new Vector3[]{
                min, // (minX, minY, minZ)
                new Vector3(min.x(), min.y(), max.z()), // (minX, minY, maxZ)
                new Vector3(min.x(), max.y(), min.z()), // (minX, maxY, minZ)
                new Vector3(min.x(), max.y(), max.z()), // (minX, maxY, maxZ)
                new Vector3(max.x(), min.y(), min.z()), // (maxX, minY, minZ)
                new Vector3(max.x(), min.y(), max.z()), // (maxX, minY, maxZ)
                new Vector3(max.x(), max.y(), min.z()), // (maxX, maxY, minZ)
                max // (maxX, maxY, maxZ)
        };
    }

    //
    // Methods
    //

    /**
     * {@inheritDoc}
     *
     * @param v The point to check
     * @return {@code true} if this box contains the given point
     */
    @Override
    public final boolean contains(@Nonnull Vector3 v) {
        return Numbers.isInRange(v, min, max);
    }

    /**
     * {@inheritDoc}
     *
     * @param f The face to check
     * @return {@code true} if this box contains the given face
     */
    @Override
    public final boolean contains(@Nonnull Face f) {
        return Numbers.isInRange(f.getA(), min, max) &&
                Numbers.isInRange(f.getB(), min, max) &&
                Numbers.isInRange(f.getC(), min, max);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The solid to compare to
     * @return {@code true} if this box completely encapsulates the other solid
     */
    @Override
    public boolean contains(@Nonnull Solid s) {
        if (s instanceof BoundingBox box) {
            return contains(box.min) && contains(box.max);
        } else if (s instanceof Sphere sphere) {
            return contains(sphere.boundingBox());
        }

        // Fallback to comparing corners
        for (final Vector3 corner : s.corners()) {
            if (!contains(corner)) return false;
        }

        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @param f The face to compare to
     * @return {@code true} if this box overlaps the given face {@code f}
     */
    @Override
    public boolean overlaps(@Nonnull Face f) {
        for (final Vertex vertex : f.toArray()) {
            if (contains(vertex)) return true;
        }

        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @param s The solid to compare to
     * @return {@code true} if the two solids overlap
     */
    @Override
    public boolean overlaps(@Nonnull Solid s) {
        if (s instanceof Sphere sphere) {
            return overlaps(sphere.boundingBox());
        }

        // Fallback to comparing corners
        for (final Vector3 corner : s.corners()) {
            if (contains(corner)) return true;
        }

        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @param ray The ray to check for intersections
     * @return {@code true} if the ray intersects this box
     */
    @Override
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

    /**
     * Since this is already a bounding box, this method returns a reference to this instance.
     *
     * @return {@code this}
     */
    @Nonnull
    @Override
    public BoundingBox boundingBox() {
        return this;
    }

    //
    // Serialization
    //

    /**
     * Serializes this box into a string.
     *
     * @return The string representation of this box
     */
    @Override
    @Nonnull
    public String toString() {
        return "BoundingBox{" +
                "min=" + min +
                ", max=" + max +
                '}';
    }
}
