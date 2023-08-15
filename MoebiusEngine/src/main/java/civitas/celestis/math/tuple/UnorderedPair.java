package civitas.celestis.math.tuple;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.Objects;

/**
 * An unordered pair of objects.
 *
 * @param <E> The element to contain in this pair
 * @see Pair
 * @see UnorderedTuple
 */
public class UnorderedPair<E> extends Pair<E> implements UnorderedTuple<E> {
    //
    // Constructors
    //

    /**
     * Creates a new pair.
     *
     * @param a The first element of this pair
     * @param b The second element of this pair
     */
    public UnorderedPair(@Nonnull E a, @Nonnull E b) {
        super(a, b);
    }

    /**
     * Creates a new pair.
     *
     * @param t The tuple of which to copy values from
     */
    public UnorderedPair(@Nonnull Tuple<E> t) {
        super(t);
    }

    /**
     * Creates a new pair.
     *
     * @param p The pair of which to copy values from
     */
    public UnorderedPair(@Nonnull Pair<E> p) {
        super(p);
    }

    //
    // Equality
    //

    /**
     * Checks for equality without regard to order.
     *
     * @param obj The object to compare to
     * @return {@code true} if the other object is a tuple of length {@code 2}, and the composition is equal
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null) return false;

        if (obj instanceof Pair<?> p) {
            return (Objects.equals(a, p.a) && Objects.equals(b, p.b)) ||
                    (Objects.equals(a, p.b) && Objects.equals(b, p.a));
        }

        if (obj instanceof Tuple<?> t) {
            if (t.length() != 2) return false;
            return (Objects.equals(a, t.get(0)) && Objects.equals(b, t.get(1))) ||
                    (Objects.equals(a, t.get(1)) && Objects.equals(b, t.get(0)));
        }

        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @param t The tuple to compare to
     * @return {@code true} if the composition and order are both equal
     */
    @Override
    public boolean strictEquals(@Nonnull Tuple<E> t) {
        return super.equals(t);
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
        return "UnorderedPair{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }
}
