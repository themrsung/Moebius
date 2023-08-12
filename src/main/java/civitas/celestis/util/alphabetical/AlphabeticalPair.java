package civitas.celestis.util.alphabetical;

import jakarta.annotation.Nonnull;

/**
 * A shallowly immutable pair of objects.
 *
 * @param <E> The type of object to hold
 */
public abstract class AlphabeticalPair<E> implements AlphabeticalGroup<E> {
    //
    // Constructors
    //

    /**
     * Creates a new alphabetical pair.
     *
     * @param a The first element of this pair
     * @param b The second element of this pair
     */
    public AlphabeticalPair(@Nonnull E a, @Nonnull E b) {
        this.a = a;
        this.b = b;
    }

    /**
     * Creates a new alphabetical pair.
     *
     * @param other The pair to copy element references from
     */
    public AlphabeticalPair(@Nonnull AlphabeticalPair<E> other) {
        this.a = other.a;
        this.b = other.b;
    }

    //
    // Variables
    //
    @Nonnull
    protected final E a, b;

    //
    // Getters
    //

    /**
     * Returns the first element of this pair.
     *
     * @return The first element of this pair
     */
    @Nonnull
    public final E getA() {
        return a;
    }

    /**
     * Returns the second element of this pair.
     *
     * @return The second element of this pair
     */
    @Nonnull
    public final E getB() {
        return b;
    }

    /**
     * Returns the {@code i}th element of this pair.
     *
     * @param i The index of element to get
     * @return The element of given index
     * @throws IndexOutOfBoundsException When the index {@code i} is out of bounds ({@code i < 0 || i > 1})
     */
    @Nonnull
    @Override
    public final E get(int i) throws IndexOutOfBoundsException {
        switch (i) {
            case 0 -> {return a;}
            case 1 -> {return b;}
            default -> throw new IndexOutOfBoundsException("Index " + i + " is out of bounds for this pair.");
        }
    }

    /**
     * Returns the size of this pair.
     *
     * @return {@code 2}
     */
    @Override
    public final int size() {
        return 2;
    }
}
