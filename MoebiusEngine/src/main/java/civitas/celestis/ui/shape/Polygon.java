package civitas.celestis.ui.shape;

import civitas.celestis.math.integer.IntVector2;
import jakarta.annotation.Nonnull;

/**
 * A shape with a variable number of points.
 */
public class Polygon implements Shape {
    /**
     * Creates a new polygon.
     * @param points The points to create this polygon from
     */
    public Polygon(@Nonnull IntVector2... points) {
        this.points = points;
    }

    //
    // Variables
    //
    @Nonnull
    private final IntVector2[] points;

    //
    // Methods
    //

    /**
     * {@inheritDoc}
     * @return An array containing the points of this polygon
     */
    @Override
    @Nonnull
    public IntVector2[] getPoints() {
        return points;
    }

    /**
     * {@inheritDoc}
     * @param i The index of point to get
     * @return The point at the specified index
     * @throws IndexOutOfBoundsException When the index is out of bounds
     */
    @Nonnull
    @Override
    public IntVector2 getPoint(int i) throws IndexOutOfBoundsException {
        return points[i];
    }

    /**
     * {@inheritDoc}
     * @return The number of points this polygon has
     */
    @Override
    public int getPointCount() {
        return points.length;
    }
}
