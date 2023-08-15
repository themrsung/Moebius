package civitas.celestis.math.tuple;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * A shallowly immutable triple of elements.
 * Triples are considered equal when both the order and composition are equal.
 *
 * @param <E> The type of element to contain in this triple
 * @see Tuple
 */
public class Triple<E> implements Tuple<E> {
    //
    // Constructors
    //

    /**
     * Creates a new triple.
     *
     * @param a The first element of this triple
     * @param b The second element of this triple
     * @param c The third element of this triple
     */
    public Triple(@Nonnull E a, @Nonnull E b, @Nonnull E c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Creates a new triple.
     *
     * @param t The tuple of which to copy values from
     */
    public Triple(@Nonnull Tuple<E> t) {
        if (t.length() != 3) {
            throw new IllegalArgumentException("The provided tuple's length is not 3.");
        }

        this.a = t.get(0);
        this.b = t.get(1);
        this.c = t.get(2);
    }

    /**
     * Creates a new triple.
     *
     * @param other The triple of which to copy values from
     */
    public Triple(@Nonnull Triple<E> other) {
        this.a = other.a;
        this.b = other.b;
        this.c = other.c;
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

    /**
     * The third element (element {@code 2}) of this tuple.
     */
    @Nonnull
    protected final E c;

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
            case 2 -> c;
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
        return Objects.equals(a, e) || Objects.equals(b, e) || Objects.equals(c, e);
    }

    /**
     * {@inheritDoc}
     *
     * @param i The iterable object to check for containment
     * @return {@code true} if this tuple contains every element of the iterable
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
     * @return {@code 3}
     */
    @Override
    public final int length() {
        return 3;
    }

    //
    // Conversion
    //

    /**
     * {@inheritDoc}
     *
     * @return The list representation of this tuple
     */
    @Nonnull
    @Override
    public final List<E> toList() {
        return List.of(a, b, c);
    }

    //
    // Iteration
    //

    /**
     * {@inheritDoc}
     *
     * @return An iterator of this triple
     */
    @Nonnull
    @Override
    public final Iterator<E> iterator() {
        return List.of(a, b, c).iterator();
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
        if (t.length() != 3) return false;
        return Objects.equals(a, t.get(0)) && Objects.equals(b, t.get(1)) && Objects.equals(c, t.get(2));
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
        return "Triple{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
    }
}
