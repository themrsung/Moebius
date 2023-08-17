package civitas.celestis.object;

import civitas.celestis.math.complex.DecimalQuaternion;
import civitas.celestis.math.vector.Decimal3;

/**
 * A movable object which can be placed in a three-dimensional world.
 * This uses {@link java.math.BigDecimal BigDecimal} based vector types
 * to represent its location and rotation.
 */
public interface BigMovable3 extends Movable<Decimal3, DecimalQuaternion> {
}
