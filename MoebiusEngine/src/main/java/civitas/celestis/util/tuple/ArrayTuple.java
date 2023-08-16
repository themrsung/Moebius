package civitas.celestis.util.tuple;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.*;
import java.util.function.Function;

/**
 * An immutable array of elements.
 *
 * @param <E> The type of element to contain
 */
public class ArrayTuple<E> implements Tuple<E, ArrayTuple<E>> {
    //
    // Constructors
    //

    /**
     * Creates a new tuple.
     *
     * @param values The values to contain
     */
    @SafeVarargs
    public ArrayTuple(@Nonnull E... values) {
        this.values = values;
    }

    /**
     * Creates a new tuple.
     *
     * @param t The tuple of which to copy values from
     * @param a An empty array of type {@link E}
     */
    public ArrayTuple(@Nonnull Tuple<E, ?> t, @Nonnull E[] a) {
        this.values = t.list().toArray(a);
    }

    /**
     * Creates a new tuple.
     *
     * @param t The array tuple of which to copy values from
     */
    public ArrayTuple(@Nonnull ArrayTuple<E> t) {
        this.values = t.values;
    }

    /**
     * Creates a new tuple.
     *
     * @param c The collection of which to copy values from
     */
    @SuppressWarnings("unchecked")
    public ArrayTuple(@Nonnull Collection<E> c) {
        final List<E> l = List.copyOf(c);
        this.values = (E[]) new Object[l.size()];

        for (int i = 0; i < l.size(); i++) {
            values[i] = l.get(i);
        }
    }

    //
    // Variables
    //

    /**
     * The array of values.
     * This is private in order to maintain immutability.
     */
    @Nonnull
    private final E[] values;

    //
    // Getters
    //

    /**
     * {@inheritDoc}
     *
     * @param i The index of component to get
     * @return {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Nonnull
    @Override
    public E get(int i) throws IndexOutOfBoundsException {
        return values[i];
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
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @param i The iterable object to check for containment
     * @return {@inheritDoc}
     */
    @Override
    public boolean containsAll(@Nonnull Iterable<E> i) {
        return false;
    }

    //
    // Properties
    //

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int length() {
        return values.length;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public List<E> list() {
        return Arrays.asList(values);
    }

    /**
     * {@inheritDoc}
     *
     * @param f The function to apple to each element of this tuple
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public ArrayTuple<E> transform(@Nonnull Function<? super E, E> f) {
        final E[] copy = Arrays.copyOf(values, values.length);

        for (int i = 0; i < values.length; i++) {
            copy[i] = f.apply(values[i]);
        }

        return new ArrayTuple<>(copy);
    }

    /**
     * {@inheritDoc}
     *
     * @param mapper The mapper function to apply to each element of this tuple
     * @param <F>    {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    @SuppressWarnings("unchecked")
    public <F> ArrayTuple<F> map(@Nonnull Function<? super E, ? extends F> mapper) {
        return new ArrayTuple<>((F[]) Arrays.stream(values).map(mapper).toArray(Object[]::new));
    }

    //
    // Iteration
    //

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    @Nonnull
    public Iterator<E> iterator() {
        return Arrays.stream(values).iterator();
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
        if (values.length != t.length()) return false;

        for (int i = 0; i < values.length; i++) {
            if (!Objects.equals(values[i], t.get(i))) return false;
        }

        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @param t The tuple to compare to
     * @return {@inheritDoc}
     */
    @Override
    public boolean equals(@Nullable ArrayTuple<E> t) {
        for (int i = 0; i < values.length; i++) {
            if (!Objects.equals(values[i], t.get(i))) return false;
        }

        return true;
    }

    //
    // Serialization
    //

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    @Nonnull
    public String toString() {
        return Arrays.toString(values);
    }
}
