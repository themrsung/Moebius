package civitas.celestis.math.tuple;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * A shallowly immutable pair of elements.
 * Pairs are considered equal when both the order and composition are equal.
 *
 * @param <E> The type of element to contain in this pair
 * @see Tuple
 */
public class Pair<E> implements Tuple<E> {
    //
    // Constructors
    //

    /**
     * Creates a new pair.
     *
     * @param a The first element of this pair
     * @param b The second element of this pair
     */
    public Pair(@Nonnull E a, @Nonnull E b) {
        this.a = a;
        this.b = b;
    }

    /**
     * Creates a new pair.
     *
     * @param t The tuple of which to copy values from
     */
    public Pair(@Nonnull Tuple<E> t) {
        if (t.length() != 2) {
            throw new IllegalArgumentException("The provided tuple's length is not 2.");
        }

        this.a = t.get(0);
        this.b = t.get(1);
    }

    /**
     * Creates a new pair.
     *
     * @param p The pair to copy values from
     */
    public Pair(@Nonnull Pair<E> p) {
        this.a = p.a;
        this.b = p.b;
    }

    //
    // Variables
    //

    /**
     * The first element (element {@code 0}) of this tuple.
     */
    @Nonnull
    protected final E a;

    /**
     * The second element (element {@code 1}) of this tuple.
     */
    @Nonnull
    protected final E b;

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
     * {@inheritDoc}
     *
     * @param i The index of element to get
     * @return The {@code i}th value of this tuple
     * @throws IndexOutOfBoundsException When the index {@code i} is out of bounds
     */
    @Nonnull
    @Override
    public final E get(int i) throws IndexOutOfBoundsException {
        return switch (i) {
            case 0 -> a;
            case 1 -> b;
            default -> throw new IndexOutOfBoundsException("Index " + i + " is out of bounds.");
        };
    }

    //
    // Containment
    //

    /**
     * {@inheritDoc}
     *
     * @param e The element to check for containment
     * @return {@code true} if this tuple contains the given element
     */
    @Override
    public final boolean contains(@Nonnull E e) {
        return Objects.equals(a, e) || Objects.equals(b, e);
    }

    /**
     * {@inheritDoc}
     *
     * @param i The iterable object to check for containment
     * @return {@code true} if this tuple contains every given element
     */
    @Override
    public final boolean containsAll(@Nonnull Iterable<E> i) {
        for (final E e : i) {
            if (!contains(e)) return false;
        }
        return true;
    }

    //
    // Properties
    //

    /**
     * {@inheritDoc}
     *
     * @return {@code 2}
     */
    public final int length() {
        return 2;
    }

    //
    // Conversion
    //

    /**
     * {@inheritDoc}
     *
     * @return The list representation of this pair
     */
    @Nonnull
    @Override
    public final List<E> toList() {
        return List.of(a, b);
    }

    //
    // Iteration
    //

    /**
     * {@inheritDoc}
     *
     * @return An iterator of this pair
     */
    @Nonnull
    @Override
    public final Iterator<E> iterator() {
        return List.of(a, b).iterator();
    }


    //
    // Equality
    //

    /**
     * {@inheritDoc}
     *
     * @param obj The object to compare to
     * @return {@code true} if the object is also a tuple, and the length and elements are equal
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof Tuple<?> t)) return false;
        if (t.length() != 2) return false;
        return Objects.equals(a, t.get(0)) && Objects.equals(b, t.get(1));
    }

    //
    // Serialization
    //

    /**
     * {@inheritDoc}
     *
     * @return The string representation of this tuple
     */
    @Override
    @Nonnull
    public String toString() {
        return "Pair{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }
}
