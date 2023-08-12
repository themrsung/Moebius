package civitas.celestis.util.group;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.function.UnaryOperator;

/**
 * A tuple of three elements.
 *
 * @param <E> Type of element to contain in this tuple
 */
public class Tuple<E> implements Group<E> {
    //
    // Constructors
    //

    /**
     * Creates a new tuple.
     *
     * @param first  The first element of this tuple
     * @param second The second element of this tuple
     * @param third  The third element of this tuple
     */
    public Tuple(@Nonnull E first, @Nonnull E second, @Nonnull E third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    /**
     * Creates a new tuple.
     *
     * @param g Group to copy values from
     * @throws IllegalArgumentException When the provided group {@code g} does not have a size of {@code 3}
     */
    public Tuple(@Nonnull Group<E> g) {
        if (g.size() != 3) {
            throw new IllegalArgumentException("The provided group does not have a size of 3.");
        }
        this.first = g.get(0);
        this.second = g.get(1);
        this.third = g.get(2);
    }

    //
    // Variables
    //

    @Nonnull
    protected final E first, second, third;

    //
    // Getters
    //

    /**
     * Returns the first element of this tuple.
     *
     * @return The first element of this tuple
     */
    @Nonnull
    public final E getFirst() {
        return first;
    }

    /**
     * Returns the second element of this tuple.
     *
     * @return The second element of this tuple
     */
    @Nonnull
    public final E getSecond() {
        return second;
    }

    /**
     * Returns the third element of this tuple.
     *
     * @return The third element of this tuple
     */
    @Nonnull
    public final E getThird() {
        return third;
    }

    /**
     * {@inheritDoc}
     *
     * @param i The index of element to get
     * @return The {@code i}th element of this tuple
     * @throws IndexOutOfBoundsException When the index is out of bounds
     */
    @Nonnull
    @Override
    public final E get(int i) throws IndexOutOfBoundsException {
        switch (i) {
            case 0 -> {return first;}
            case 1 -> {return second;}
            case 2 -> {return third;}
            default -> throw new IndexOutOfBoundsException("Index " + i + " is out of bounds for this tuple.");
        }
    }

    /**
     * Since a tuple always has three elements, this always returns {@code 3}.
     *
     * @return {@code 3}
     */
    @Override
    public final int size() {
        return 3;
    }

    //
    // Utility
    //

    /**
     * {@inheritDoc}
     *
     * @param obj Object to check
     * @return {@code true} if at least one of the elements is equal to {@code obj}
     */
    @Override
    public boolean contains(@Nullable Object obj) {
        return Objects.equals(first, obj) || Objects.equals(second, obj) || Objects.equals(third, obj);
    }

    /**
     * {@inheritDoc}
     *
     * @param operator The operator to apply to each element of this tuple
     * @return The resulting tuple
     */
    @Nonnull
    @Override
    public Tuple<E> apply(@Nonnull UnaryOperator<E> operator) {
        return new Tuple<>(operator.apply(first), operator.apply(second), operator.apply(third));
    }

    /**
     * {@inheritDoc}
     *
     * @return The list representation of this tuple
     */
    @Nonnull
    @Override
    public List<E> toList() {
        return List.of(first, second);
    }

    //
    // Equality
    //

    /**
     * {@inheritDoc}
     *
     * @param obj The object to compare to
     * @return {@code true} if the other object is an instance of {@link Group}, and the components and size are equal
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Group<?> g)) return false;
        if (g.size() != 3) return false;

        return Objects.equals(first, g.get(0)) && Objects.equals(second, g.get(1)) && Objects.equals(third, g.get(2));
    }

    /**
     * {@inheritDoc}
     *
     * @param other The group to compare to
     * @return {@code true} if the other group has a size of {@code 3}, and the components are equal (order does not matter)
     */
    @Override
    public boolean equalsIgnoreOrder(@Nonnull Group<E> other) {
        if (other.size() != 3) return false;

        for (final E e : other) {
            if (!contains(e)) return false;
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
        return "Tuple{" +
                "first=" + first +
                ", second=" + second +
                ", third=" + third +
                '}';
    }
}
