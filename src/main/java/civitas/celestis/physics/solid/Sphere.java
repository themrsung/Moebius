package civitas.celestis.physics.solid;

import civitas.celestis.math.vector.Vector3;
import jakarta.annotation.Nonnull;

/**
 * A solid with no corners.
 */
public class Sphere implements Solid {
    /**
     * Creates a new sphere.
     *
     * @param centroid The geometric centroid of this sphere
     * @param radius   The radius of this sphere
     */
    public Sphere(@Nonnull Vector3 centroid, double radius) {
        this.centroid = centroid;
        this.radius = radius;
    }

    @Nonnull
    private final Vector3 centroid;
    private final double radius;

    @Nonnull
    public Vector3 getCentroid() {
        return centroid;
    }

    /**
     * Gets the radius of this sphere.
     *
     * @return The radius of this sphere
     */
    public double getRadius() {
        return radius;
    }

    @Nonnull
    @Override
    public Vector3[] getCorners() {
        return new Vector3[0];
    }

    @Override
    public double getVolume() {
        return 4.0 * 3.0 * Math.PI * Math.pow(radius, 3);
    }

    @Override
    public double getSurfaceArea() {
        return 4.0 * Math.PI * radius * radius;
    }

    @Override
    public double getDragCoefficient(@Nonnull Vector3 incidentDirection) {
        return 0.5;
    }

    @Override
    public double getCrossSection(@Nonnull Vector3 incidentDirection) {
        return Math.PI * radius * radius;
    }

    @Override
    public boolean contains(@Nonnull Vector3 point) {
        return centroid.distance2(point) <= radius * radius;
    }

    @Override
    public boolean overlaps(@Nonnull Solid other) {
        if (other instanceof Sphere sphere) {
            return centroid.distance2(sphere.centroid) <= Math.pow(radius + sphere.radius, 2);
        } else {
            for (final Vector3 corner : other.getCorners()) {
                if (contains(corner)) return true;
            }

            return false;
        }
    }
}
