package civitas.celestis.util.tuple;

import jakarta.annotation.Nonnull;

/**
 * A pair of two elements of a different type.
 *
 * @param <A> The type of the first element
 * @param <B> The type of the second element
 */
public class BinaryPair<A, B> extends Pair<Object> {
    //
    // Constructors
    //

    /**
     * Creates a new binary pair.
     *
     * @param a The first element of this pair
     * @param b The second element of this pair
     */
    public BinaryPair(@Nonnull A a, @Nonnull B b) {
        super(a, b);
    }

    /**
     * Creates a new binary pair.
     *
     * @param p The pair of which to copy object references from
     */
    public BinaryPair(@Nonnull BinaryPair<A, B> p) {
        super(p);
    }

    //
    // Getters
    //

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    @SuppressWarnings("unchecked")
    public A getA() {
        return (A) super.getA();
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    @SuppressWarnings("unchecked")
    public B getB() {
        return (B) super.getB();
    }
}
