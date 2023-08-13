package civitas.celestis.util.alphabetical;

import jakarta.annotation.Nonnull;

/**
 * A shallowly immutable tuple of objects.
 *
 * @param <E> The type of object to hold
 */
public abstract class AlphabeticalTuple<E> implements AlphabeticalGroup<E> {
    //
    // Constructors
    //

    /**
     * Creates a new alphabetical tuple.
     *
     * @param a The first element of this tuple
     * @param b The second element of this tuple
     * @param c The third element of this tuple
     */
    public AlphabeticalTuple(@Nonnull E a, @Nonnull E b, @Nonnull E c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Creates a new alphabetical tuple.
     *
     * @param other The tuple to copy element references from
     */
    public AlphabeticalTuple(@Nonnull AlphabeticalTuple<E> other) {
        this.a = other.a;
        this.b = other.b;
        this.c = other.c;
    }

    //
    // Variables
    //
    @Nonnull
    protected final E a, b, c;

    //
    // Getters
    //

    /**
     * Returns the first element of this tuple.
     *
     * @return The first element of this tuple
     */
    @Nonnull
    public final E getA() {
        return a;
    }

    /**
     * Returns the second element of this tuple.
     *
     * @return The second element of this tuple
     */
    @Nonnull
    public final E getB() {
        return b;
    }

    /**
     * Returns the third element of this tuple.
     *
     * @return The third element of this tuple
     */
    @Nonnull
    public final E getC() {
        return c;
    }

    /**
     * Returns the {@code i}th element of this tuple.
     *
     * @param i The index of element to get
     * @return The element of given index
     * @throws IndexOutOfBoundsException When the index {@code i} is out of bounds ({@code i > 0 || i > 2})
     */
    @Nonnull
    @Override
    public final E get(int i) throws IndexOutOfBoundsException {
        switch (i) {
            case 0 -> {return a;}
            case 1 -> {return b;}
            case 2 -> {return c;}
            default -> throw new IndexOutOfBoundsException("Index " + i + " is out of bounds for this tuple.");
        }
    }

    /**
     * Returns the size of this tuple.
     *
     * @return {@code 3}
     */
    @Override
    public final int size() {
        return 3;
    }
}
