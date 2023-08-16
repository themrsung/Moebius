package civitas.celestis.util.tuple;

import jakarta.annotation.Nonnull;

/**
 * A quad of four elements of different types.
 *
 * @param <A> The type of the first element
 * @param <B> The type of the second element
 * @param <C> The type of the third element
 * @param <D> The type of the fourth element
 */
public class QuaternaryQuad<A, B, C, D> extends Quad<Object> {
    //
    // Constructors
    //

    /**
     * Creates a new quaternary quad.
     *
     * @param a The first element of this quad
     * @param b The second element of this quad
     * @param c The third element of this quad
     * @param d The fourth element of this quad
     */
    public QuaternaryQuad(@Nonnull A a, @Nonnull B b, @Nonnull C c, @Nonnull D d) {
        super(a, b, c, d);
    }

    /**
     * Creates a new quaternary quad.
     *
     * @param q The quad of which to copy object references from
     */
    public QuaternaryQuad(@Nonnull QuaternaryQuad<A, B, C, D> q) {
        super(q);
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

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    @SuppressWarnings("unchecked")
    public D getD() {
        return (D) super.getD();
    }
}
