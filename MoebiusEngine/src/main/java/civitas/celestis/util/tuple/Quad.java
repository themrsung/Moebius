package civitas.celestis.util.tuple;

import civitas.celestis.util.Group;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.UnaryOperator;

/**
 * A quad of elements.
 *
 * @param <E> The type of element to contain
 */
public class Quad<E> implements Tuple<E, Quad<E>> {
    //
    // Constructors
    //

    /**
     * Creates a new triple.
     *
     * @param a The first element of this triple
     * @param b The second element of this triple
     * @param c The third element of this triple
     * @param d The fourth element of this triple
     */
    public Quad(@Nonnull E a, @Nonnull E b, @Nonnull E c, @Nonnull E d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    /**
     * Creates a new quad.
     *
     * @param i The iterable object of which to copy values from
     */
    public Quad(@Nonnull Iterable<E> i) {
        final Iterator<E> it = i.iterator();

        try {
            this.a = it.next();
            this.b = it.next();
            this.c = it.next();
            this.d = it.next();
        } catch (final NoSuchElementException e) {
            throw new IllegalArgumentException("The iterable object is too small.");
        }
    }

    /**
     * Creates a new quad.
     *
     * @param g The group of which to copy values from
     */
    public Quad(@Nonnull Group<E> g) {
        final List<E> list = g.list();
        if (list.size() != 4) {
            throw new IllegalArgumentException("The provided group's size is not 4.");
        }

        this.a = list.get(0);
        this.b = list.get(1);
        this.c = list.get(2);
        this.d = list.get(3);
    }

    /**
     * Creates a new triple.
     *
     * @param t The tuple of which to copy values from
     */
    public Quad(@Nonnull Tuple<E, ?> t) {
        if (t.length() != 4) throw new IllegalArgumentException("The provided tuple's length is not 4.");

        this.a = t.get(0);
        this.b = t.get(1);
        this.c = t.get(2);
        this.d = t.get(3);
    }

    /**
     * Creates a new triple.
     *
     * @param t The triple of which to copy values from
     */
    public Quad(@Nonnull Quad<E> t) {
        this.a = t.a;
        this.b = t.b;
        this.c = t.c;
        this.d = t.d;
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

    /**
     * The third element of this tuple.
     */
    @Nonnull
    protected final E d;

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
     * Returns the fourth element of this tuple.
     *
     * @return The fourth element of this tuple
     */
    @Nonnull
    public E getD() {
        return d;
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
            case 3 -> d;
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
        return List.of(a, b, c, d);
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
    public Quad<E> transform(@Nonnull UnaryOperator<E> f) {
        return new Quad<>(f.apply(a), f.apply(b), f.apply(c), f.apply(d));
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
        return List.of(a, b, c, d).iterator();
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
        if (t.length() != 4) return false;
        return Objects.equals(a, t.get(0)) &&
                Objects.equals(b, t.get(1)) &&
                Objects.equals(c, t.get(2)) &&
                Objects.equals(d, t.get(3));
    }

    /**
     * {@inheritDoc}
     *
     * @param q The tuple to compare to
     * @return {@inheritDoc}
     */
    @Override
    public boolean equals(@Nullable Quad<E> q) {
        if (q == null) return false;
        return Objects.equals(a, q.a) &&
                Objects.equals(b, q.b) &&
                Objects.equals(c, q.c) &&
                Objects.equals(c, q.d);
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
        return "Quad{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", d=" + d +
                '}';
    }
}
