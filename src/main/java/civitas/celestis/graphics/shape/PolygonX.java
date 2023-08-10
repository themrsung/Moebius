package civitas.celestis.graphics.shape;

import civitas.celestis.graphics.util.Position;
import jakarta.annotation.Nonnull;

import java.awt.*;
import java.util.function.UnaryOperator;

/**
 * An improved {@link Polygon} class which supports the use of {@link Position} objects.
 */
public class PolygonX extends Polygon {
    //
    // Constructors
    //

    /**
     * Creates a new polygon object.
     */
    public PolygonX() {
    }

    /**
     * Creates a new polygon with a predefined array of points.
     *
     * @param points Array of points of this polygon
     */
    public PolygonX(@Nonnull Position... points) {
        for (final Position point : points) {
            addPoint(point);
        }
    }

    //
    // Methods
    //

    /**
     * Adds a point to this polygon.
     *
     * @param p Point to add
     */
    public void addPoint(@Nonnull Position p) {
        addPoint(p.xInt(), p.yInt());
    }

    /**
     * Gets the {@code i}th point of this polygon.
     *
     * @param i The index of point to get
     * @return The point of given index
     * @throws IndexOutOfBoundsException When the index {@code i} is out of bounds
     */
    @Nonnull
    public Position getPoint(int i) throws IndexOutOfBoundsException {
        if (i >= npoints) {
            throw new IndexOutOfBoundsException("Index " + i + " is out of bounds for this polygon.");
        }

        return new Position(xpoints[i], ypoints[i]);
    }

    /**
     * Gets all points of this polygon.
     *
     * @return Array containing every point of this polygon
     */
    @Nonnull
    public Position[] getPoints() {
        final Position[] points = new Position[npoints];

        for (int i = 0; i < npoints; i++) {
            points[i] = getPoint(i);
        }

        return points;
    }

    /**
     * Sets the {@code i}th point of this polygon.
     *
     * @param i Index of point to set
     * @param p Point to set to
     * @throws IndexOutOfBoundsException When the index {@code i} is out of bounds
     */
    public void setPoint(int i, @Nonnull Position p) throws IndexOutOfBoundsException {
        if (i >= npoints) {
            throw new IndexOutOfBoundsException("Index " + i + " is out of bounds for this polygon.");
        }

        xpoints[i] = p.xInt();
        ypoints[i] = p.yInt();
    }

    //
    // Utility
    //

    /**
     * Inverts all X coordinates of this polygon.
     */
    public void invertX() {
        for (int i = 0; i < npoints; i++) {
            xpoints[i] = -xpoints[i];
        }
    }

    /**
     * Inverts all Y coordinates of this polygon.
     */
    public void invertY() {
        for (int i = 0; i < npoints; i++) {
            ypoints[i] = -ypoints[i];
        }
    }

    /**
     * Applies given operator to all points of this polygon.
     *
     * @param operator Operator to apply to each point of this polygon
     */
    public void modify(@Nonnull UnaryOperator<Position> operator) {
        for (int i = 0; i < npoints; i++) {
            setPoint(i, operator.apply(getPoint(i)));
        }
    }

    /**
     * Serializes this polygon into a string.
     *
     * @return The string representation of this polygon
     */
    @Override
    public String toString() {
        return super.toString();
    }
}