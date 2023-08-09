package civitas.celestis.group;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.List;
import java.util.Objects;

/**
 * A group of two elements.
 */
public class Pair<E> implements Group<E> {
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
     * Creates a new pair from another pair.
     *
     * @param other Pair to copy object references from
     */
    public Pair(@Nonnull Pair<E> other) {
        this.a = other.a;
        this.b = other.b;
    }

    @Nonnull
    private final E a, b;

    /**
     * Gets the first element of this pair.
     *
     * @return The first element
     */
    @Nonnull
    public E a() {
        return a;
    }

    /**
     * Gets the second element of this pair.
     *
     * @return The second element
     */
    @Nonnull
    public E b() {
        return b;
    }

    @Nonnull
    @Override
    public E get(int i) throws IndexOutOfBoundsException {
        switch (i) {
            case 0 -> {return a;}
            case 1 -> {return b;}
            default -> {
                throw new IndexOutOfBoundsException("Index " + i + " is out of bounds for this tuple.");
            }
        }
    }

    @Override
    public int size() {
        return 2;
    }

    @Nonnull
    @Override
    public List<E> list() {
        return List.of(a, b);
    }

    @Override
    public boolean contains(@Nullable Object obj) {
        return Objects.equals(a, obj) || Objects.equals(b, obj);
    }

    /**
     * Given an element, this returns the other element.
     *
     * @param element The element to check for
     * @return The other element if found
     * @throws IllegalArgumentException When the given element if not a member of this pair
     */
    @Nonnull
    public E other(@Nonnull E element) throws IllegalArgumentException {
        if (a.equals(element)) return b;
        if (b.equals(element)) return a;

        throw new IllegalArgumentException("Given element is not a member of this pair.");
    }

    /**
     * Checks for equality without regard for the components' order.
     *
     * @param other Other pair to check equality for
     * @return {@code true} if both pairs have the same composition
     */
    public boolean equalsIgnoreOrder(@Nonnull Pair<E> other) {
        return (a.equals(other.a) && b.equals(other.b)) || (a.equals(other.b) && b.equals(other.a));
    }

    /**
     * Checks for equality with the given object.
     *
     * @param obj Object to compare to
     * @return {@code true} if the other object is a pair, and the components and order are equal
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Pair<?> p)) return false;
        return Objects.equals(a, p.a) && Objects.equals(b, p.b);
    }
}
