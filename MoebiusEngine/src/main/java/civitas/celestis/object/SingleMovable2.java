package civitas.celestis.object;

import civitas.celestis.math.vector.Float2;

/**
 * A movable object which can be placed in a two-dimensional world.
 * Unlike {@link Movable2}, this interface uses 32-bit single precision data types.
 */
public interface SingleMovable2 extends Movable<Float2, Float> {
}
