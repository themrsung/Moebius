package civitas.celestis.math.function;

import java.util.function.Function;

/**
 * A mathematical function.
 *
 * @param <I> The input parameter (any type)
 * @param <O> The output parameter (a {@link Number})
 */
public interface MathFunction<I, O extends Number> extends Function<I, O> {
}
