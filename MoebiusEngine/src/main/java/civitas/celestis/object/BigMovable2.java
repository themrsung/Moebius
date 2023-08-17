package civitas.celestis.object;

import civitas.celestis.math.vector.Decimal2;

/**
 * A movable object which can be placed in a two-dimensional world.
 * This uses {@link java.math.BigDecimal BigDecimal} based vector types
 * to represent its location, and a single {@code double} to represent its rotation.
 */
public interface BigMovable2 extends Movable<Decimal2, Double> {
}
