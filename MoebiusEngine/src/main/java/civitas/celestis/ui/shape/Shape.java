package civitas.celestis.ui.shape;

import civitas.celestis.math.integer.IntVector2;
import jakarta.annotation.Nonnull;

/**
 * A two-dimensional area.
 */
public interface Shape {
    /**
     * Returns an array of points which represent the corners of this shape.
     * @return The corners of this shape
     */
    @Nonnull
    IntVector2[] getPoints();

    /**
     * Returns the {@code i}th point of this shape.
     * @param i The index of point to get
     * @return The point at the specified index
     * @throws IndexOutOfBoundsException When the index {@code i} is out of bounds
     */
    @Nonnull
    IntVector2 getPoint(int i) throws IndexOutOfBoundsException;

    /**
     * Returns the number of points this shape has.
     * @return The number of points this shape has
     */
    int getPointCount();
}
