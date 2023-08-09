package civitas.celestis.graphics.shape;

import civitas.celestis.math.vector.Vector2;
import jakarta.annotation.Nonnull;

import java.awt.*;
import java.util.function.UnaryOperator;

/**
 * An improved polygon class which supports {@code double} and {@link Vector2} input.
 */
public class PolygonX extends Polygon implements ShapeX {
    /**
     * Creates a new empty polygon.
     */
    public PolygonX() {
    }

    /**
     * Creates a polygon from an array of predefined points.
     *
     * @param points Array of points
     */
    public PolygonX(@Nonnull Vector2... points) {
        for (final Vector2 point : points) {
            addPoint(point);
        }
    }

    /**
     * Adds a point to this polygon. Decimal places are dropped.
     *
     * @param x X coordinate of the point
     * @param y Y coordinate of the point
     */
    public void addPoint(double x, double y) {
        addPoint((int) x, (int) y);
    }

    /**
     * Adds a point to this polygon. Decimal places are dropped.
     *
     * @param point Point to add
     */
    public void addPoint(@Nonnull Vector2 point) {
        addPoint((int) point.x(), (int) point.y());
    }

    @Override
    public void modify(@Nonnull UnaryOperator<Vector2> operator) {
        for (int i = 0; i < npoints; i++) {
            final double x = xpoints[i];
            final double y = ypoints[i];

            final Vector2 result = operator.apply(new Vector2(x, y));

            xpoints[i] = (int) result.x();
            ypoints[i] = (int) result.y();
        }
    }

    @Override
    public void invertX() {
        for (int i = 0; i < npoints; i++) {
            xpoints[i] = -xpoints[i];
        }
    }

    @Override
    public void invertY() {
        for (int i = 0; i < npoints; i++) {
            ypoints[i] = -ypoints[i];
        }
    }
}
