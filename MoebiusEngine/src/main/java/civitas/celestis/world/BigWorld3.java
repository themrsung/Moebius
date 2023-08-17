package civitas.celestis.world;

import civitas.celestis.math.complex.DecimalQuaternion;
import civitas.celestis.math.complex.Quaternion;
import civitas.celestis.math.vector.Decimal3;
import civitas.celestis.math.vector.Double3;
import civitas.celestis.object.BigMovable3;
import civitas.celestis.object.Movable3;
import civitas.celestis.util.Tickable;

/**
 * A three-dimensional world which uses {@link java.math.BigDecimal BigDecimal} as its coordinates.
 *
 * @param <O> The type of object this world contains
 */
public interface BigWorld3<O extends BigMovable3 & Tickable> extends World<Decimal3, DecimalQuaternion, O> {
}
