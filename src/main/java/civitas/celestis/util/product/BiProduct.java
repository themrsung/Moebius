package civitas.celestis.util.product;

import jakarta.annotation.Nonnull;

/**
 * This class is used to return two variables from a single method.
 * {@code null} values are not supported.
 *
 * @param <A> The type of the first product
 * @param <B> The type of the second product
 */
public class BiProduct<A, B> {
    //
    // Constructors
    //

    /**
     * Creates a new bi-product.
     *
     * @param a The first product
     * @param b The second product
     */
    public BiProduct(@Nonnull A a, @Nonnull B b) {
        this.a = a;
        this.b = b;
    }

    /**
     * Creates a new bi-product from another.
     *
     * @param other Bi-product to copy object references from
     */
    public BiProduct(@Nonnull BiProduct<A, B> other) {
        this.a = other.a;
        this.b = other.b;
    }

    //
    // Variables
    //

    @Nonnull
    private final A a;
    @Nonnull
    private final B b;

    //
    // Getters
    //

    /**
     * Returns the first product.
     *
     * @return The first product
     */
    @Nonnull
    public A a() {
        return a;
    }

    /**
     * Returns the second product.
     *
     * @return The second product
     */
    @Nonnull
    public B b() {
        return b;
    }

    //
    // Utility
    //

    /**
     * Returns a flipped bi-product.
     *
     * @return The flipped bi-product of this bi-product
     */
    @Nonnull
    public BiProduct<B, A> flip() {
        return new BiProduct<>(b, a);
    }

    /**
     * Serializes this bi-product into a string.
     *
     * @return The string representation of this bi-product
     */
    @Override
    @Nonnull
    public String toString() {
        return "BiProduct{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }
}
