package civitas.celestis.math.tuple;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.Objects;

/**
 * An unordered triple of elements.
 *
 * @param <E> The type of element to contain
 * @see Triple
 * @see UnorderedTuple
 */
public class UnorderedTriple<E> extends Triple<E> implements UnorderedTuple<E> {
    //
    // Constructors
    //

    /**
     * Creates a new triple.
     *
     * @param a The first element of this triple
     * @param b The second element of this triple
     * @param c The third element of this triple
     */
    public UnorderedTriple(@Nonnull E a, @Nonnull E b, @Nonnull E c) {
        super(a, b, c);
    }

    /**
     * Creates a new triple.
     *
     * @param t The tuple of which to copy values from
     */
    public UnorderedTriple(@Nonnull Tuple<E> t) {
        super(t);
    }

    /**
     * Creates a new triple.
     *
     * @param other The triple of which to copy values from
     */
    public UnorderedTriple(@Nonnull Triple<E> other) {
        super(other);
    }

    //
    // Equality
    //

    /**
     * Checks for equality without regard to order.
     *
     * @param obj The object to compare to
     * @return {@code true} if the other object is a tuple of length {@code 3}, and the composition is equal
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null) return false;

        if (obj instanceof Triple<?> t) {
            if (!(Objects.equals(a, t.a) || Objects.equals(b, t.a) || Objects.equals(c, t.a))) return false;
            if (!(Objects.equals(a, t.b) || Objects.equals(b, t.b) || Objects.equals(c, t.b))) return false;
            return Objects.equals(a, t.c) || Objects.equals(b, t.c) || Objects.equals(c, t.c);
        } else if (obj instanceof Tuple<?> t) {
            if (t.length() != 3) return false;
            if (!(Objects.equals(a, t.get(0)) || Objects.equals(b, t.get(0)) || Objects.equals(c, t.get(0))))
                return false;
            if (!(Objects.equals(a, t.get(1)) || Objects.equals(b, t.get(1)) || Objects.equals(c, t.get(1))))
                return false;
            return (!(Objects.equals(a, t.get(2)) || Objects.equals(b, t.get(2)) || Objects.equals(c, t.get(2))));
        }

        return false;
    }

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
        return "UnorderedTriple{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
    }
}
