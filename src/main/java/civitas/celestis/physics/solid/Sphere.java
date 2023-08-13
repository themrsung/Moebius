package civitas.celestis.physics.solid;

import civitas.celestis.graphics.face.Face;
import civitas.celestis.graphics.raytracing.Ray;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.math.vertex.Vertex;
import jakarta.annotation.Nonnull;

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
    public Sphere(@Nonnull Vector3 center, double radius) {
        this.center = center;
        this.radius = radius;
        this.box = calculateBoundingBox(center, radius);
    }

    /**
     * Given a center and a radius, this calculates a sphere's bounding box.
     *
     * @param center The center of the sphere
     * @param radius The radius of the sphere
     * @return The bounding box of the sphere
     */
    @Nonnull
    private static BoundingBox calculateBoundingBox(@Nonnull Vector3 center, double radius) {
        return new BoundingBox(center.subtract(radius), center.add(radius));
    }


    //
    // Variables
    //

    @Nonnull
    private final Vector3 center;
    private final double radius;
    @Nonnull
    private transient final BoundingBox box;

    //
    // Geometry
    //

    /**
     * Returns the geometric centroid of this sphere.
     *
     * @return The geometric centroid of this sphere
     */
    @Nonnull
    @Override
    public Vector3 center() {
        return center;
    }

    /**
     * Returns the radius of this sphere.
     *
     * @return The radius of this sphere
     */
    public double radius() {
        return radius;
    }

    /**
     * Returns the volume of this sphere
     *
     * @return The volume of this sphere
     */
    @Override
    public double volume() {
        return 4.0 / 3.0 * Math.PI * Math.pow(radius, 3);
    }

    /**
     * Since a sphere has no corners, this returns an empty array.
     *
     * @return An empty array of {@link Vector3}
     */
    @Nonnull
    @Override
    public Vector3[] corners() {
        return new Vector3[0];
    }

    /**
     * {@inheritDoc}
     *
     * @param v The point to check
     * @return {@code true} if the distance between the center of this sphere and {@code v}
     * is equal to or less than this sphere's radius
     */
    @Override
    public boolean contains(@Nonnull Vector3 v) {
        return center.distance2(v) <= (radius * radius);
    }

    /**
     * {@inheritDoc}
     *
     * @param f The face to check
     * @return {@code true} if all vertices of the face are within the bounds of this sphere
     */
    @Override
    public boolean contains(@Nonnull Face f) {
        for (final Vertex vertex : f.toArray()) {
            if (contains(vertex)) return true;
        }

        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @param s The solid to compare to
     * @return {@code true} if this sphere contains the given solid {@code s}
     */
    @Override
    public boolean contains(@Nonnull Solid s) {
        if (s instanceof Sphere sphere) {
            return (center.distance(sphere.center) + sphere.radius) <= radius;
        } else if (s instanceof BoundingBox box) {
            return contains(box.min) && contains(box.max);
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
     * @return {@code true} if this sphere overlaps with the given face {@code f}
     */
    @Override
    public boolean overlaps(@Nonnull Face f) {
        for (final Vertex vertex : f.toArray()) {
            if (contains(vertex)) return true;
        }

        return true;
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
            return center.distance2(sphere.center) <= Math.pow(radius + sphere.radius, 2);
        }

        // Fallback to using corners
        for (final Vector3 corner : s.corners()) {
            if (contains(corner)) return true;
        }

        return false;
    }

    //
    // Raytracing
    //

    /**
     * {@inheritDoc}
     *
     * @param ray The ray to check for intersection
     * @return {@code true} if this sphere intersect the given ray
     */
    @Override
    public boolean intersects(@Nonnull Ray ray) {
        return box.intersects(ray);
    }

    //
    // Bounding Box
    //

    /**
     * {@inheritDoc}
     *
     * @return The bounding box of this sphere
     */
    @Nonnull
    @Override
    public BoundingBox boundingBox() {
        return box;
    }

    //
    // Serialization
    //

    /**
     * Serializes this sphere into a string.
     *
     * @return The string representation of this sphere
     */
    @Override
    @Nonnull
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                ", box=" + box +
                '}';
    }
}
