package civitas.celestis.math.vector.boxed;

import java.math.BigDecimal;

/**
 * A vector which uses the type {@link BigDecimal}.
 * @param <V> Itself (the parameter and result of various operations)
 */
public interface DecimalVector<V extends DecimalVector<V>> extends BoxedVector<BigDecimal, V> {
}
