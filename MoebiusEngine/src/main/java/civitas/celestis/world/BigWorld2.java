package civitas.celestis.world;

import civitas.celestis.math.vector.Decimal2;
import civitas.celestis.object.BigMovable2;
import civitas.celestis.util.Tickable;

/**
 * A two-dimensional world which uses {@link java.math.BigDecimal BigDecimal} as its coordinates.
 * Rotation is represented as a single {@code double}.
 *
 * @param <O> The type of object this world contains
 */
public interface BigWorld2<O extends BigMovable2 & Tickable> extends World<Decimal2, Double, O> {
}
