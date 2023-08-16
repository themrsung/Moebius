package civitas.celestis.object;

import civitas.celestis.math.complex.FloatQuaternion;
import civitas.celestis.math.vector.Float3;

/**
 * A movable object which can be placed in a three-dimensional world.
 * Unlike {@link Movable3}, this interface uses 32-bit single precision data types.
 */
public interface SingleMovable3 extends Movable<Float3, FloatQuaternion> {
}
