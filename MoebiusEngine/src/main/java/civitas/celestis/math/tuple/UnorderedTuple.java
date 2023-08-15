package civitas.celestis.math.tuple;

import jakarta.annotation.Nonnull;

/**
 * An unordered tuple.
 * Unordered tuples are considered equal without regard to their elements' order.
 *
 * @param <E> The type of element to contain
 */
public interface UnorderedTuple<E> extends Tuple<E> {
    /**
     * Checks for equality with regard to order.
     *
     * @param t The tuple to compare to
     * @return {@code true} if the lengths are equal, and the same components are in the same order
     */
    boolean strictEquals(@Nonnull Tuple<E> t);
}
