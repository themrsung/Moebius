package civitas.celestis.ui.shape;

import civitas.celestis.math.integer.IntVector2;
import civitas.celestis.util.alphabetical.AlphabeticalTuple;
import jakarta.annotation.Nonnull;

/**
 * A shape with three points.
 */
public class Triangle extends AlphabeticalTuple<IntVector2> implements Shape {
    //
    // Constructors
    //

    /**
     * Creates a new triangle.
     *
     * @param a The first point of this triangle
     * @param b The second point of this triangle
     * @param c The third point of this triangle
     */
    public Triangle(@Nonnull IntVector2 a, @Nonnull IntVector2 b, @Nonnull IntVector2 c) {
        super(a, b, c);
    }

    //
    // Methods
    //

    /**
     * {@inheritDoc}
     *
     * @return An array containing the points of this triangle.
     */
    @Nonnull
    @Override
    public IntVector2[] getPoints() {
        return new IntVector2[]{a, b, c};
    }

    /**
     * {@inheritDoc}
     *
     * @param i The index of point to get
     * @return The point at the specified index
     * @throws IndexOutOfBoundsException When the index is out of bounds
     */
    @Nonnull
    @Override
    public IntVector2 getPoint(int i) throws IndexOutOfBoundsException {
        return get(i);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@code 3}
     */
    @Override
    public int getPointCount() {
        return 3;
    }

    /**
     * {@inheritDoc}
     *
     * @return An array containing the points of this triangle.
     */
    @Nonnull
    @Override
    public IntVector2[] toArray() {
        return new IntVector2[]{a, b, c};
    }
}
