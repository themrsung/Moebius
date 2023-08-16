package civitas.celestis.math.physics;

import civitas.celestis.math.vector.Double3;
import jakarta.annotation.Nonnull;

/**
 * An un-rotatable perfect sphere.
 */
public class Sphere implements Solid {
    //
    // Constructors
    //

    /**
     * Creates a new sphere.
     *
     * @param center The center of this sphere
     * @param radius The radius of this sphere
     */
    public Sphere(@Nonnull Double3 center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    /**
     * Creates a new sphere.
     *
     * @param other The sphere of which to copy properties from
     */
    public Sphere(@Nonnull Sphere other) {
        this.center = other.center;
        this.radius = other.radius;
    }

    //
    // Variables
    //

    /**
     * The center of this sphere.
     */
    @Nonnull
    protected final Double3 center;

    /**
     * The radius of this sphere.
     */
    protected final double radius;

    //
    // Getters
    //

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    @Nonnull
    public Double3 getCenter() {
        return center;
    }

    /**
     * Returns the radius of this sphere.
     *
     * @return The radius of this sphere
     */
    public double getRadius() {
        return radius;
    }

    /**
     * {@inheritDoc}
     *
     * @return An empty array
     */
    @Nonnull
    @Override
    public Double3[] getCorners() {
        return new Double3[0];
    }

    /**
     * {@inheritDoc}
     *
     * @return {@code 0}
     */
    @Override
    public int getNumCorners() {
        return 0;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public double getVolume() {
        return (4d / 3d) * Math.PI * radius * radius * radius;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public BoundingBox getBoundingBox() {
        return new BoundingBox(center.subtract(radius), center.add(radius));
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
        return center.distance2(v) <= (radius * radius);
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
            return center.distance2(sphere.center) <= Math.pow(radius + sphere.radius, 2);
        }

        // Use bounding box as fallback
        return getBoundingBox().overlaps(s);
    }
}
