package civitas.celestis.math.vector;

import java.math.BigInteger;

/**
 * A vector which uses the type {@link BigInteger}.
 *
 * @param <V> Itself (the parameter and result of various operations)
 */
public interface IntegerVector<V extends IntegerVector<V>> extends BigVector<BigInteger, V> {
}
