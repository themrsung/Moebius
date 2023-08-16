package civitas.celestis.world;

import civitas.celestis.math.complex.FloatQuaternion;
import civitas.celestis.math.vector.Float3;
import civitas.celestis.object.SingleMovable3;
import civitas.celestis.util.Tickable;

/**
 * A three-dimensional world which uses 32-bit single precision data types.
 *
 * @param <O> The type of object this world contains
 */
public interface SingleWorld3<O extends SingleMovable3 & Tickable> extends World<Float3, FloatQuaternion, O> {
}
