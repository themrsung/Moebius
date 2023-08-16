package civitas.celestis.util.tuple;

import jakarta.annotation.Nonnull;

/**
 * A triple of three elements of different types.
 *
 * @param <A> The type of the first element
 * @param <B> The type of the second element
 * @param <C> The type of the third element
 */
public class TernaryTriple<A, B, C> extends Triple<Object> {
    //
    // Constructors
    //

    /**
     * Creates a new ternary triple.
     *
     * @param a The first element of this triple
     * @param b The second element of this triple
     * @param c The third element of this triple
     */
    public TernaryTriple(@Nonnull A a, @Nonnull B b, @Nonnull C c) {
        super(a, b, c);
    }

    /**
     * Creates a new ternary triple.
     *
     * @param t The triple of which to copy object references from
     */
    public TernaryTriple(@Nonnull TernaryTriple<A, B, C> t) {
        super(t);
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

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    @SuppressWarnings("unchecked")
    public C getC() {
        return (C) super.getC();
    }
}
