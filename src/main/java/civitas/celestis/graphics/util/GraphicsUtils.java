package civitas.celestis.graphics.util;

import civitas.celestis.math.vector.Vector3;
import de.javagl.obj.FloatTuple;
import jakarta.annotation.Nonnull;

/**
 * Contains utility methods related to graphics.
 */
public final class GraphicsUtils {
    /**
     * Converts a {@link FloatTuple} into a {@link Vector3}.
     *
     * @param tuple Tuple to convert
     * @return Converted {@link Vector3}
     */
    @Nonnull
    public static Vector3 objTupleToVector3(@Nonnull FloatTuple tuple) {
        return new Vector3(tuple.getZ(), tuple.getY(), tuple.getX());
    }
}
