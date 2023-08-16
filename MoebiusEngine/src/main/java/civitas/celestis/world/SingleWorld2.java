package civitas.celestis.world;

import civitas.celestis.math.vector.Float2;
import civitas.celestis.object.SingleMovable2;
import civitas.celestis.util.Tickable;

/**
 * A two-dimensional world which uses 32-bit single precision data types.
 *
 * @param <O> The type of object this world contains
 */
public interface SingleWorld2<O extends SingleMovable2 & Tickable> extends World<Float2, Float, O> {
}
