package civitas.celestis.math.vector;

import civitas.celestis.math.vertex.Vertex;
import jakarta.annotation.Nonnull;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * A mapper class for rearranging vector components.
 * This is intended to be used as a function.
 * <p>
 * <code>
 * final Vector3 v = new Vector3(1, 2, 3);<br>
 * final Vector3 p = VectorMapper.V3_SWAP_X_Z.apply(v); // {3, 2, 1}
 * </code>
 * </p>
 *
 * @param <V> The type of vector to rearrange
 */
public final class VectorMapper<V extends Vector> implements UnaryOperator<V> {
    //
    // Constants
    //

    /**
     * A mapper which swaps the X and Y components of a two-dimensional vector.
     */
    public static final VectorMapper<Vector2> V2_SWAP_X_Y = new VectorMapper<>(new int[]{1, 0}, Vector2::new);

    /**
     * A mapper which swaps the X and Y components of a three-dimensional vector.
     */
    public static final VectorMapper<Vector3> V3_SWAP_X_Y = new VectorMapper<>(new int[]{1, 0, 2}, Vector3::new);

    /**
     * A mapper which swaps the X and Z components of a three-dimensional vector.
     */
    public static final VectorMapper<Vector3> V3_SWAP_X_Z = new VectorMapper<>(new int[]{2, 1, 0}, Vector3::new);

    /**
     * A mapper which swaps the Y and Z components of a three-dimensional vector.
     */
    public static final VectorMapper<Vector3> V3_SWAP_Y_Z = new VectorMapper<>(new int[]{0, 2, 1}, Vector3::new);

    /**
     * A mapper which swaps the X and Y components of a vertex.
     */
    public static final VectorMapper<Vertex> VERTEX_SWAP_X_Y = new VectorMapper<>(new int[]{1, 0, 2}, Vertex::new);

    /**
     * A mapper which swaps the X and Z components of a vertex.
     */
    public static final VectorMapper<Vertex> VERTEX_SWAP_X_Z = new VectorMapper<>(new int[]{2, 1, 0}, Vertex::new);

    /**
     * A mapper which swaps the Y and Z components of a vertex.
     */
    public static final VectorMapper<Vertex> VERTEX_SWAP_Y_Z = new VectorMapper<>(new int[]{0, 2, 1}, Vertex::new);

    /**
     * A mapper which swaps the W and X components of a four-dimensional vector.
     */
    public static final VectorMapper<Vector4> V4_SWAP_W_X = new VectorMapper<>(new int[]{1, 0, 2, 3}, Vector4::new);

    /**
     * A mapper which swaps the W and Y components of a four-dimensional vector.
     */
    public static final VectorMapper<Vector4> V4_SWAP_W_Y = new VectorMapper<>(new int[]{2, 1, 0, 3}, Vector4::new);

    /**
     * A mapper which swaps the W and Z components of a four-dimensional vector.
     */
    public static final VectorMapper<Vector4> V4_SWAP_W_Z = new VectorMapper<>(new int[]{3, 1, 2, 0}, Vector4::new);

    /**
     * A mapper which swaps the X and Y components of a four-dimensional vector.
     */
    public static final VectorMapper<Vector4> V4_SWAP_X_Y = new VectorMapper<>(new int[]{0, 2, 1, 3}, Vector4::new);

    /**
     * A mapper which swaps the X and Z components of a four-dimensional vector.
     */
    public static final VectorMapper<Vector4> V4_SWAP_X_Z = new VectorMapper<>(new int[]{0, 3, 2, 1}, Vector4::new);

    /**
     * A mapper which swaps the Y and Z components of a four-dimensional vector.
     */
    public static final VectorMapper<Vector4> V4_SWAP_Y_Z = new VectorMapper<>(new int[]{0, 1, 3, 2}, Vector4::new);

    /**
     * A mapper which swaps the V and W components of a five-dimensional vector.
     */
    public static final VectorMapper<Vector5> V5_SWAP_V_W = new VectorMapper<>(new int[]{1, 0, 2, 3, 4}, Vector5::new);

    /**
     * A mapper which swaps the V and X components of a five-dimensional vector.
     */
    public static final VectorMapper<Vector5> V5_SWAP_V_X = new VectorMapper<>(new int[]{2, 1, 0, 3, 4}, Vector5::new);

    /**
     * A mapper which swaps the V and X components of a five-dimensional vector.
     */
    public static final VectorMapper<Vector5> V5_SWAP_V_Y = new VectorMapper<>(new int[]{3, 1, 2, 0, 4}, Vector5::new);

    /**
     * A mapper which swaps the V and Y components of a five-dimensional vector.
     */
    public static final VectorMapper<Vector5> V5_SWAP_V_Z = new VectorMapper<>(new int[]{4, 1, 2, 3, 0}, Vector5::new);

    /**
     * A mapper which swaps the V and Z components of a five-dimensional vector.
     */
    public static final VectorMapper<Vector5> V5_SWAP_W_X = new VectorMapper<>(new int[]{0, 2, 1, 3, 4}, Vector5::new);

    /**
     * A mapper which swaps the W and Y components of a five-dimensional vector.
     */
    public static final VectorMapper<Vector5> V5_SWAP_W_Y = new VectorMapper<>(new int[]{0, 3, 2, 1, 4}, Vector5::new);

    /**
     * A mapper which swaps the W and Z components of a five-dimensional vector.
     */
    public static final VectorMapper<Vector5> V5_SWAP_W_Z = new VectorMapper<>(new int[]{0, 4, 2, 3, 1}, Vector5::new);

    /**
     * A mapper which swaps the X and Y components of a five-dimensional vector.
     */
    public static final VectorMapper<Vector5> V5_SWAP_X_Y = new VectorMapper<>(new int[]{0, 4, 2, 3, 1}, Vector5::new);

    /**
     * A mapper which swaps the X and Z components of a five-dimensional vector.
     */
    public static final VectorMapper<Vector5> V5_SWAP_X_Z = new VectorMapper<>(new int[]{0, 4, 2, 3, 1}, Vector5::new);

    /**
     * A mapper which swaps the Y and Z components of a five-dimensional vector.
     */
    public static final VectorMapper<Vector5> V5_SWAP_Y_Z = new VectorMapper<>(new int[]{0, 1, 2, 4, 3}, Vector5::new);

    //
    // Constructors
    //

    /**
     * Creates a new vector mapper.
     *
     * @param map         The map of indexes
     * @param constructor The constructor function to use to create the new vector instance
     */
    public VectorMapper(@Nonnull int[] map, @Nonnull Function<double[], V> constructor) {
        this.map = map;
        this.constructor = constructor;
    }

    //
    // Variables
    //
    @Nonnull
    private final int[] map;
    @Nonnull
    private final Function<double[], V> constructor;

    //
    // Methods
    //

    /**
     * Applies this vector mapper to the given mapper.
     *
     * @param t The original vector
     * @return The mapped vector
     */
    @Override
    public V apply(@Nonnull V t) {
        final double[] original = t.values();
        final double[] result = new double[t.length()];

        for (int i = 0; i < t.length(); i++) {
            result[map[i]] = original[i];
        }

        return constructor.apply(result);
    }
}
