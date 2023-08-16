package civitas.celestis.world;

import civitas.celestis.math.vector.Double2;
import civitas.celestis.object.Movable2;
import civitas.celestis.util.Tickable;

/**
 * A two-dimensional world.
 *
 * @param <O> The type of object this world contains
 */
public interface World2<O extends Movable2 & Tickable> extends World<Double2, Double, O> {
}
