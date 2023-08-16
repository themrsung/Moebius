package civitas.celestis.world;

import civitas.celestis.math.complex.FloatQuaternion;
import civitas.celestis.math.vector.Float3;
import civitas.celestis.object.SingleMovable3;

/**
 * A three-dimensional world which uses 32-bit single precision data types.
 */
public interface SingleWorld3 extends World<Float3, FloatQuaternion, SingleMovable3> {
}
