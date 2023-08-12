package civitas.celestis.util.group;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.function.UnaryOperator;

/**
 * A pair of elements.
 *
 * @param <E> Type of element to contain in this pair
 */
public class Pair<E> implements Group<E> {
    //
    // Constructors
    //

    /**
     * Creates a new pair.
     *
     * @param first  The first element of this pair
     * @param second The second element of this pair
     */
    public Pair(@Nonnull E first, @Nonnull E second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Creates a new pair.
     *
     * @param g Group to copy values from
     * @throws IllegalArgumentException When the provided group {@code g} does not have a size of {@code 2}
     */
    public Pair(@Nonnull Group<E> g) {
        if (g.size() != 2) {
            throw new IllegalArgumentException("The provided group does not have a size of 2.");
        }
        this.first = g.get(0);
        this.second = g.get(1);
    }

    //
    // Variables
    //

    @Nonnull
    protected final E first, second;

    //
    // Getters
    //

    /**
     * Returns the first element of this pair.
     *
     * @return The first element of this pair
     */
    @Nonnull
    public final E getFirst() {
        return first;
    }

    /**
     * Returns the second element of this pair.
     *
     * @return The second element of this pair
     */
    @Nonnull
    public final E getSecond() {
        return second;
    }

    /**
     * {@inheritDoc}
     *
     * @param i The index of element to get
     * @return The {@code i}th element of this pair
     * @throws IndexOutOfBoundsException When the index is out of bounds
     */
    @Nonnull
    @Override
    public final E get(int i) throws IndexOutOfBoundsException {
        switch (i) {
            case 0 -> {return first;}
            case 1 -> {return second;}
            default -> throw new IndexOutOfBoundsException("Index " + i + " is out of bounds for this pair.");
        }
    }

    /**
     * Since a pair always has two elements, this always returns {@code 2}.
     *
     * @return {@code 2}
     */
    @Override
    public final int size() {
        return 2;
    }

    //
    // Utility
    //

    /**
     * {@inheritDoc}
     *
     * @param obj Object to check
     * @return {@code true} if either of the elements are equal to {@code obj}
     */
    @Override
    public boolean contains(@Nullable Object obj) {
        return Objects.equals(first, obj) || Objects.equals(second, obj);
    }

    /**
     * {@inheritDoc}
     *
     * @param operator The operator to apply to each element of this pair
     * @return The resulting pair
     */
    @Nonnull
    @Override
    public Pair<E> apply(@Nonnull UnaryOperator<E> operator) {
        return new Pair<>(operator.apply(first), operator.apply(second));
    }

    /**
     * {@inheritDoc}
     *
     * @return The list representation of this pair
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
        if (g.size() != 2) return false;

        return Objects.equals(first, g.get(0)) && Objects.equals(second, g.get(1));
    }

    /**
     * {@inheritDoc}
     *
     * @param other The group to compare to
     * @return {@code true} if the other group has a size of {@code 2}, and the components are equal (order does not matter)
     */
    @Override
    public boolean equalsIgnoreOrder(@Nonnull Group<E> other) {
        if (other.size() != 2) return false;
        return (Objects.equals(first, other.get(0)) && Objects.equals(second, other.get(1))) ||
                (Objects.equals(second, other.get(0)) && Objects.equals(first, other.get(1)));
    }

    //
    // Serialization
    //

    /**
     * {@inheritDoc}
     *
     * @return The string representation of this pair
     */
    @Override
    @Nonnull
    public String toString() {
        return "Pair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
