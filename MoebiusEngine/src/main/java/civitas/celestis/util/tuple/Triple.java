package civitas.celestis.util.tuple;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.UnaryOperator;

/**
 * A triple of elements.
 *
 * @param <E> The type of element to contain
 */
public class Triple<E> implements Tuple<E, Triple<E>> {
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
    public Triple(@Nonnull Tuple<E, ?> t) {
        if (t.length() != 3) throw new IllegalArgumentException("The provided tuple's length is not 3.");

        this.a = t.get(0);
        this.b = t.get(1);
        this.c = t.get(2);
    }

    /**
     * Creates a new triple.
     *
     * @param t The triple of which to copy values from
     */
    public Triple(@Nonnull Triple<E> t) {
        this.a = t.a;
        this.b = t.b;
        this.c = t.c;
    }

    //
    // Variables
    //

    /**
     * The first element of this tuple.
     */
    @Nonnull
    protected final E a;

    /**
     * The second element of this tuple.
     */
    @Nonnull
    protected final E b;

    /**
     * The third element of this tuple.
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
    public E getA() {
        return a;
    }

    /**
     * Returns the second element of this tuple.
     *
     * @return The second element of this tuple
     */
    @Nonnull
    public E getB() {
        return b;
    }

    /**
     * Returns the third element of this tuple.
     *
     * @return The third element of this tuple
     */
    @Nonnull
    public E getC() {
        return c;
    }

    /**
     * {@inheritDoc}
     *
     * @param i The index of component to get
     * @return The {@code i}th element of this tuple
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public E get(int i) throws IndexOutOfBoundsException {
        return switch (i) {
            case 0 -> a;
            case 1 -> b;
            case 2 -> c;
            default -> throw new IndexOutOfBoundsException("The index " + i + " is out of bounds.");
        };
    }

    //
    // Containment
    //

    /**
     * {@inheritDoc}
     *
     * @param obj The object to check for containment
     * @return {@inheritDoc}
     */
    @Override
    public boolean contains(@Nullable Object obj) {
        return Objects.equals(a, obj) || Objects.equals(b, obj) || Objects.equals(c, obj);
    }

    /**
     * {@inheritDoc}
     *
     * @param i The iterable object to check for containment
     * @return {@inheritDoc}
     */
    @Override
    public boolean containsAll(@Nonnull Iterable<E> i) {
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
    public int length() {
        return 3;
    }

    //
    // List Conversion
    //

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public List<E> list() {
        return List.of(a, b, c);
    }

    //
    // Transformation
    //

    /**
     * {@inheritDoc}
     *
     * @param f The function to apple to each element of this tuple
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Triple<E> transform(@Nonnull UnaryOperator<E> f) {
        return new Triple<>(f.apply(a), f.apply(b), f.apply(c));
    }

    //
    // Iteration
    //

    /**
     * Returns an iterator of the elements of this tuple.
     *
     * @return An iterator of the elements of this tuple
     */
    @Override
    @Nonnull
    public Iterator<E> iterator() {
        return List.of(a, b, c).iterator();
    }


    //
    // Equality
    //

    /**
     * {@inheritDoc}
     *
     * @param obj The object to compare to
     * @return {@inheritDoc}
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof Tuple<?, ?> t)) return false;
        if (t.length() != 3) return false;
        return Objects.equals(a, t.get(0)) && Objects.equals(b, t.get(1)) && Objects.equals(c, t.get(2));
    }

    /**
     * {@inheritDoc}
     *
     * @param t The tuple to compare to
     * @return {@inheritDoc}
     */
    @Override
    public boolean equals(@Nullable Triple<E> t) {
        if (t == null) return false;
        return Objects.equals(a, t.a) && Objects.equals(b, t.b) && Objects.equals(c, t.c);
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
