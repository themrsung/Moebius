package civitas.celestis.ui.shape;

import civitas.celestis.math.integer.IntVector2;
import civitas.celestis.util.alphabetical.AlphabeticalPair;
import jakarta.annotation.Nonnull;

/**
 * A shape with two points.
 */
public class Line extends AlphabeticalPair<IntVector2> implements Shape {
    //
    // Constructors
    //

    /**
     * Creates a new line.
     * @param a The first point of this line
     * @param b The second point of this line
     */
    public Line(@Nonnull IntVector2 a, @Nonnull IntVector2 b) {
        super(a, b);
    }

    //
    // Methods
    //

    /**
     * {@inheritDoc}
     * @return An array containing the points of this line
     */
    @Nonnull
    @Override
    public IntVector2[] getPoints() {
        return new IntVector2[] {a, b};
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
        return get(i);
    }

    /**
     * {@inheritDoc}
     * @return {@code 2}
     */
    @Override
    public int getPointCount() {
        return 2;
    }

    /**
     * {@inheritDoc}
     * @return An array containing the points of this line
     */
    @Nonnull
    @Override
    public IntVector2[] toArray() {
        return new IntVector2[] {a, b};
    }
}
