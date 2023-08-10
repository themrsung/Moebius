package civitas.celestis.graphics.util;

import civitas.celestis.math.vector.Vector3;
import jakarta.annotation.Nonnull;

/**
 * A box used to roughly estimate the bounds of a solid.
 */
public class BoundingBox {
    //
    // Constructors
    //

    /**
     * Creates a new bounding box.
     * If the array is empty, this will result in creating an effectively infinite box.
     *
     * @param vertices Array of vertices to use
     */
    public BoundingBox(@Nonnull Vertex... vertices) {
        this.min = Vertex.min(vertices);
        this.max = Vertex.max(vertices);
    }

    /**
     * Creates a new bounding box from another.
     *
     * @param other Bounding box to copy the values from
     */
    public BoundingBox(@Nonnull BoundingBox other) {
        this.min = other.min;
        this.max = other.max;
    }

    //
    // Variables
    //

    @Nonnull
    private final Vertex min, max;

    //
    // Getters
    //

    /**
     * Gets the minimum bound of this box.
     *
     * @return The minimum vertex
     */
    @Nonnull
    public Vertex min() {
        return min;
    }

    /**
     * Gets the maximum bound of this box.
     *
     * @return The maximum vertex
     */
    @Nonnull
    public Vertex max() {
        return max;
    }

    /**
     * Calculates the size of this bounding box.
     *
     * @return The size of this box
     */
    @Nonnull
    public Vertex size() {
        return max.subtract(min);
    }

    /**
     * Calculates the center of this bounding box.
     *
     * @return The center of this box
     */
    @Nonnull
    public Vertex center() {
        // Does not use Vertex.avg(Vertex...) as that would be slower for only 2 vertices
        return min.add(max).divide(2);
    }

    //
    // Methods
    //

    /**
     * Checks if this bounding box contains a given vertex.
     *
     * @param v Vertex to check
     * @return {@code true} if this box contains the given vertex
     */
    public boolean contains(@Nonnull Vertex v) {
        final double x = v.x();
        final double y = v.y();
        final double z = v.z();

        final double minX = min.x();
        final double minY = min.y();
        final double minZ = min.z();

        final double maxX = max.x();
        final double maxY = max.y();
        final double maxZ = max.z();

        final boolean containsX = x >= minX && x <= maxX;
        final boolean containsY = y >= minY && y <= maxY;
        final boolean containsZ = z >= minZ && z <= maxZ;

        return containsX && containsY && containsZ;
    }

    /**
     * Clamps a vector to respect the bounds of this bounding box.
     *
     * @param v The vector to clamp
     * @return The clamped vector
     */
    @Nonnull
    public Vector3 clamp(@Nonnull Vector3 v) {
        return v.clamp(min, max);
    }

    /**
     * Clamps a vertex to respect the bounds of this bounding box.
     *
     * @param v The vertex to clamp
     * @return The clamped vertex
     */
    @Nonnull
    public Vertex clamp(@Nonnull Vertex v) {
        return v.clamp(min, max);
    }

    //
    // Serialization
    //

    /**
     * Serializes this bounding box into a string.
     *
     * @return The string representation of this bounding box
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
