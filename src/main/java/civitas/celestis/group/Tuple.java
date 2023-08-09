package civitas.celestis.group;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.List;
import java.util.Objects;

/**
 * A group of three elements.
 */
public class Tuple<E> implements Group<E> {
    /**
     * Creates a new tuple.
     *
     * @param a The first element of this tuple
     * @param b The second element of this tuple
     * @param c The third element of this tuple
     */
    public Tuple(@Nonnull E a, @Nonnull E b, @Nonnull E c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Creates a new tuple from another tuple.
     *
     * @param other Tuple to copy object references from
     */
    public Tuple(@Nonnull Tuple<E> other) {
        this.a = other.a;
        this.b = other.b;
        this.c = other.c;
    }

    @Nonnull
    private final E a, b, c;

    /**
     * Gets the first element of this tuple.
     *
     * @return The first element
     */
    @Nonnull
    public E a() {
        return a;
    }

    /**
     * Gets the second element of this tuple.
     *
     * @return The second element
     */
    @Nonnull
    public E b() {
        return b;
    }

    /**
     * Gets the third element of this tuple.
     *
     * @return The third element
     */
    @Nonnull
    public E c() {
        return c;
    }

    @Nonnull
    @Override
    public E get(int i) throws IndexOutOfBoundsException {
        switch (i) {
            case 0 -> {return a;}
            case 1 -> {return b;}
            case 2 -> {return c;}
            default -> {
                throw new IndexOutOfBoundsException("Index " + i + " is out of bounds for this tuple.");
            }
        }
    }

    @Override
    public int size() {
        return 3;
    }

    @Nonnull
    @Override
    public List<E> list() {
        return List.of(a, b, c);
    }

    @Override
    public boolean contains(@Nullable Object obj) {
        return Objects.equals(a, obj) || Objects.equals(b, obj) || Objects.equals(c, obj);
    }

    /**
     * Checks for equality without regard for the components' order.
     *
     * @param other Other tuple to check equality for
     * @return {@code true} if both tuples have the same composition
     */
    public boolean equalsIgnoreOrder(@Nonnull Tuple<E> other) {
        return contains(other.a) && contains(other.b) && contains(other.c);
    }

    /**
     * Checks for equality with the given object.
     *
     * @param obj Object to compare to
     * @return {@code true} if the other object is a tuple, and the components and order are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Tuple<?> t)) return false;
        return Objects.equals(a, t.a) && Objects.equals(b, t.b) && Objects.equals(c, t.c);
    }
}
