package civitas.celestis.util.tuple;

import civitas.celestis.util.group.Group;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.UnaryOperator;

/**
 * A pair of elements.
 *
 * @param <E> The type of element to contain
 */
public class Pair<E> implements Tuple<E, Pair<E>> {
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
     * @param i The iterable object of which to copy values from
     */
    public Pair(@Nonnull Iterable<E> i) {
        final Iterator<E> it = i.iterator();

        try {
            this.a = it.next();
            this.b = it.next();
        } catch (final NoSuchElementException e) {
            throw new IllegalArgumentException("The iterable object is too small.");
        }
    }

    /**
     * Creates a new pair.
     *
     * @param g The group of which to copy values from
     */
    public Pair(@Nonnull Group<E> g) {
        final List<E> list = g.list();
        if (list.size() != 2) {
            throw new IllegalArgumentException("The provided group's size is not 2.");
        }

        this.a = list.get(0);
        this.b = list.get(1);
    }

    /**
     * Creates a new pair.
     *
     * @param t The tuple of which to copy values from
     */
    public Pair(@Nonnull Tuple<E, ?> t) {
        if (t.length() != 2) throw new IllegalArgumentException("The provided tuple's length is not 2.");

        this.a = t.get(0);
        this.b = t.get(1);
    }

    /**
     * Creates a new pair.
     *
     * @param p The pair of which to copy values from
     */
    public Pair(@Nonnull Pair<E> p) {
        this.a = p.a;
        this.b = p.b;
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
        return Objects.equals(a, obj) || Objects.equals(b, obj);
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
     * @return {@code 2}
     */
    @Override
    public int length() {
        return 2;
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
        return List.of(a, b);
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
    public Pair<E> transform(@Nonnull UnaryOperator<E> f) {
        return new Pair<>(f.apply(a), f.apply(b));
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
        return List.of(a, b).iterator();
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
        if (t.length() != 2) return false;
        return Objects.equals(a, t.get(0)) && Objects.equals(b, t.get(1));
    }

    /**
     * {@inheritDoc}
     *
     * @param p The tuple to compare to
     * @return {@inheritDoc}
     */
    @Override
    public boolean equals(@Nullable Pair<E> p) {
        if (p == null) return false;
        return Objects.equals(a, p.a) && Objects.equals(b, p.b);
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
