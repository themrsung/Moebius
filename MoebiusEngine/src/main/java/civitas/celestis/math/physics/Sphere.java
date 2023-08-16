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
}
