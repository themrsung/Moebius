package civitas.celestis.util.group;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.function.UnaryOperator;

/**
 * A group of one element.
 * The usefulness of this class is questionable.
 *
 * @param <E> Type of element to hold
 */
public class Singleton<E> implements Group<E> {
    /**
     * Creates a new singleton.
     *
     * @param value Value of this singleton
     */
    public Singleton(@Nonnull E value) {
        this.value = value;
    }

    /**
     * Creates a new singleton from another.
     *
     * @param other Singleton to copy object reference from
     */
    public Singleton(@Nonnull Singleton<E> other) {
        this.value = other.value;
    }

    @Nonnull
    private final E value;

    /**
     * Gets the element contained in this singleton.
     *
     * @return The element of this singleton
     */
    @Nonnull
    public E value() {
        return value;
    }

    @Nonnull
    @Override
    public E get(int i) throws IndexOutOfBoundsException {
        if (i == 0) return value;
        throw new IndexOutOfBoundsException("Index " + i + " is out of bounds for singleton.");
    }

    @Override
    public int size() {
        return 1;
    }

    @Nonnull
    @Override
    public List<E> list() {
        return List.of(value);
    }

    @Override
    public boolean contains(@Nullable Object obj) {
        return Objects.equals(value, obj);
    }

    @Nonnull
    @Override
    public Singleton<E> apply(@Nonnull UnaryOperator<E> operator) {
        return new Singleton<>(operator.apply(value));
    }

    /**
     * Checks for equality with the given object.
     *
     * @param obj Object to compare to
     * @return {@code true} if the other object is a singleton, and the values are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Singleton<?> s)) return false;
        return Objects.equals(value, s.value);
    }
}
