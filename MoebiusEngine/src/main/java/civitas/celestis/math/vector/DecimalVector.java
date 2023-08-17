package civitas.celestis.math.vector;

import jakarta.annotation.Nonnull;

import java.math.BigDecimal;

/**
 * A vector which uses the type {@link BigDecimal}.
 *
 * @param <V> Itself (the parameter and result of various operations)
 */
public interface DecimalVector<V extends DecimalVector<V>> extends BigVector<BigDecimal, V> {
    //
    // Integer Conversion
    //

    /**
     * Converts this decimal vector into an integer vector.
     *
     * @return The integer vector representation of this vector
     * @see BigDecimal#toBigInteger()
     */
    @Nonnull
    IntegerVector<?> toBigInteger();
}
