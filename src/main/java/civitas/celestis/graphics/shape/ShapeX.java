package civitas.celestis.graphics.shape;

import civitas.celestis.math.vector.Vector2;
import jakarta.annotation.Nonnull;

import java.awt.*;
import java.util.function.UnaryOperator;

/**
 * An improved shape interface.
 */
public interface ShapeX extends Shape {
    /**
     * Applies given operator to all points of this shape.
     *
     * @param operator Operator to apply
     */
    void modify(@Nonnull UnaryOperator<Vector2> operator);

    /**
     * Inverts all X coordinates of this shape.
     */
    void invertX();

    /**
     * Inverts all Y coordinates of this shape.
     */
    void invertY();
}
