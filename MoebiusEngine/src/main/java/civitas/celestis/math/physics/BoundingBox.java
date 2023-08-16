package civitas.celestis.math.physics;

import civitas.celestis.math.Numbers;
import civitas.celestis.math.vector.Double3;
import jakarta.annotation.Nonnull;

/**
 * An un-rotatable box in 3D space.
 * Bounding boxes are used for rough comparison between solids.
 */
public class BoundingBox implements Solid {
    //
    // Static Initialization
    //

    /**
     * Returns a new bounding box constructed from the provided array of vertices.
     *
     * @param vertices The array of vertices to construct the box from
     * @return The constructed bounding box
     * @see Numbers#min(Double3...)
     * @see Numbers#max(Double3...)
     */
    @Nonnull
    public static BoundingBox of(@Nonnull Double3... vertices) {
        return new BoundingBox(
                Numbers.min(vertices),
                Numbers.max(vertices)
        );
    }

    //
    // Constructors
    //

    /**
     * Creates a new bounding box.
     *
     * @param min The minimum vertex of this bounding box
     * @param max The maximum vertex of this bounding box
     */
    public BoundingBox(@Nonnull Double3 min, @Nonnull Double3 max) {
        this.min = min;
        this.max = max;
    }

    //
    // Variables
    //

    /**
     * The minimum vertex of this bounding box.
     */
    @Nonnull
    protected final Double3 min;

    /**
     * The maximum vertex of this bounding box.
     */
    @Nonnull
    protected final Double3 max;

    //
    // Getters
    //

    /**
     * Returns the minimum vertex of this bounding box.
     *
     * @return The minimum vertex of this bounding box
     */
    @Nonnull
    public Double3 getMin() {
        return min;
    }

    /**
     * Returns the maximum vertex of this bounding box.
     *
     * @return The maximum vertex of this bounding box
     */
    @Nonnull
    public Double3 getMax() {
        return max;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Double3[] getCorners() {
        return new Double3[]{
                min,
                new Double3(min.x(), min.y(), max.z()),
                new Double3(min.x(), max.y(), min.z()),
                new Double3(min.x(), max.y(), max.z()),
                new Double3(max.x(), min.y(), min.z()),
                new Double3(max.x(), min.y(), max.z()),
                new Double3(max.x(), max.y(), min.z()),
                max
        };
    }

    /**
     * {@inheritDoc}
     *
     * @return {@code 8}
     */
    @Override
    public int getNumCorners() {
        return 8;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Double3 getCenter() {
        final double x1 = min.x();
        final double y1 = min.y();
        final double z1 = min.z();

        final double x2 = max.x();
        final double y2 = max.y();
        final double z2 = max.z();

        return new Double3((x1 + x2) / 2, (y1 + y2) / 2, (z1 + z2) / 2);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public double getVolume() {
        final Double3 size = getSize();
        return size.x() * size.y() * size.z();
    }

    /**
     * Returns the size of this bounding box.
     *
     * @return The size of this bounding box
     */
    @Nonnull
    public Double3 getSize() {
        return max.subtract(min);
    }

    /**
     * {@inheritDoc}
     * Since this is already a bounding box and bounding boxes are immutable,
     * this method simply returns a reference to the bounding box itself.
     *
     * @return {@code this}
     */
    @Nonnull
    @Override
    public BoundingBox getBoundingBox() {
        return this;
    }

    //
    // Containment
    //

    /**
     * {@inheritDoc}
     *
     * @param v The vertex to check for containment
     * @return {@inheritDoc}
     */
    @Override
    public boolean contains(@Nonnull Double3 v) {
        return Numbers.isInRange(v, min, max);
    }

    //
    // Overlapping
    //

    /**
     * {@inheritDoc}
     *
     * @param s The solid to compare to
     * @return {@inheritDoc}
     */
    @Override
    public boolean overlaps(@Nonnull Solid s) {
        // Special case for spheres
        if (s instanceof Sphere sphere) {
            // This is a rough comparison, and is not geometrically accurate
            final double diagonal = getSize().norm();
            final double radius = sphere.radius;

            return getCenter().distance2(sphere.center) <= Math.pow(diagonal + radius, 2);
        }

        // Fallback to bounding box if the solid has no corners
        if (s.getNumCorners() <= 0) {
            return overlaps(s.getBoundingBox());
        }

        // Compare all corners of the other solid
        for (final Double3 corner : s.getCorners()) {
            if (contains(corner)) return true;
        }

        return false;
    }
}
