package civitas.celestis.world;

import civitas.celestis.math.complex.Quaternion;
import civitas.celestis.math.vector.Double3;
import civitas.celestis.object.Movable3;
import civitas.celestis.util.Tickable;

/**
 * A three-dimensional world.
 *
 * @param <O> The type of object this world contains
 */
public interface World3<O extends Movable3 & Tickable> extends World<Double3, Quaternion, O> {
}
