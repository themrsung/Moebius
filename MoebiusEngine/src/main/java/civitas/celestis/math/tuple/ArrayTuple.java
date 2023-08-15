package civitas.celestis.math.tuple;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * A tuple which is constructed from an array.
 * This is effectively a shallowly immutable array.
 *
 * @param <E> The type of element to hold
 */
public class ArrayTuple<E> implements Tuple<E> {
    //
    // Constructors
    //

    /**
     * Creates a new tuple.
     *
     * @param values The values to contain in this tuple
     */
    @SafeVarargs
    public ArrayTuple(@Nonnull E... values) {
        this.values = values;
    }

    /**
     * Creates a new tuple.
     *
     * @param other The tuple of which to copy values from
     */
    public ArrayTuple(@Nonnull ArrayTuple<E> other) {
        this.values = other.values;
    }

    //
    // Variables
    //

    /**
     * The array of values of this tuple.
     * This must be private in order to maintain the immutability of tuples.
     */
    @Nonnull
    private final E[] values;

    //
    // Getters
    //

    /**
     * {@inheritDoc}
     *
     * @param i The index of element to get
     * @return The {@code i}th element of this tuple
     * @throws IndexOutOfBoundsException When the index {@code i} is out of bounds
     */
    @Nonnull
    @Override
    public final E get(int i) throws IndexOutOfBoundsException {
        return values[i];
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
        for (final E value : values) {
            if (Objects.equals(value, e)) return true;
        }

        return false;
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
     * @return The number of elements this tuple contains
     */
    @Override
    public final int length() {
        return values.length;
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
        return Arrays.stream(values).toList();
    }

    /**
     * {@inheritDoc}
     *
     * @return The iterator of this tuple
     */
    @Nonnull
    @Override
    public final Iterator<E> iterator() {
        return Arrays.stream(values).iterator();
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
        if (values.length != t.length()) return false;

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
     * @return The string representation of this tuple
     */
    @Override
    @Nonnull
    public String toString() {
        return "ArrayTuple{" +
                "values=" + Arrays.toString(values) +
                '}';
    }
}
