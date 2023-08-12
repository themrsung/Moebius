package civitas.celestis.graphics;

import civitas.celestis.graphics.color.RichColor;
import civitas.celestis.math.quaternion.Quaternion;
import civitas.celestis.math.vector.Vector;
import civitas.celestis.math.vector.Vector2;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.math.vector.Vector4;
import civitas.celestis.math.vertex.Vertex;
import jakarta.annotation.Nonnull;

/**
 * Contains interpolation utility methods.
 */
public final class Interpolation {
    //
    // Linear Interpolation
    //

    /**
     * Performs linear interpolation between two scalars.
     *
     * @param start The starting scalar
     * @param end   The end scalar
     * @param t     The interpolation parameter {@code t} ({@code 0-1})
     * @return The interpolated scalar
     */
    public static double lerp(double start, double end, double t) {
        return start + (end - start) * t;
    }

    /**
     * Performs linear interpolation between two vectors.
     *
     * @param start The starting vector
     * @param end   The end vector
     * @param t     The interpolation parameter {@code t} ({@code 0-1})
     * @return The interpolated vector
     */
    @Nonnull
    public static Vector lerp(@Nonnull Vector start, @Nonnull Vector end, double t) {
        if (start.length() != end.length()) {
            throw new IllegalArgumentException("Cannot interpolate two vectors of different length.");
        }

        return start.add(end.subtract(start).multiply(t));
    }

    /**
     * Performs linear interpolation between two vectors.
     *
     * @param start The starting vector
     * @param end   The end vector
     * @param t     The interpolation parameter {@code t} ({@code 0-1})
     * @return The interpolated vector
     */
    @Nonnull
    public static Vector2 lerp(@Nonnull Vector2 start, @Nonnull Vector2 end, double t) {
        return start.add(end.subtract(start).multiply(t));
    }

    /**
     * Performs linear interpolation between two vectors.
     *
     * @param start The starting vector
     * @param end   The end vector
     * @param t     The interpolation parameter {@code t} ({@code 0-1})
     * @return The interpolated vector
     */
    @Nonnull
    public static Vector3 lerp(@Nonnull Vector3 start, @Nonnull Vector3 end, double t) {
        return start.add(end.subtract(start).multiply(t));
    }

    /**
     * Performs linear interpolation between two vertices.
     *
     * @param start The starting vertex
     * @param end   The end vertex
     * @param t     The interpolation parameter {@code t} ({@code 0-1})
     * @return The interpolated vertex
     */
    @Nonnull
    public static Vertex lerp(@Nonnull Vertex start, @Nonnull Vertex end, double t) {
        return start.add(end.subtract(start).multiply(t));
    }

    /**
     * Performs linear interpolation between two vectors.
     *
     * @param start The starting vector
     * @param end   The end vector
     * @param t     The interpolation parameter {@code t} ({@code 0-1})
     * @return The interpolated vector
     */
    @Nonnull
    public static Vector4 lerp(@Nonnull Vector4 start, @Nonnull Vector4 end, double t) {
        return start.add(end.subtract(start).multiply(t));
    }

    /**
     * Performs linear interpolation between two quaternions.
     *
     * @param start The starting quaternion
     * @param end   The end quaternion
     * @param t     The interpolation parameter {@code t} ({@code 0-1})
     * @return The interpolated quaternion
     */
    @Nonnull
    public static Quaternion lerp(@Nonnull Quaternion start, @Nonnull Quaternion end, double t) {
        return start.add(end.subtract(start).multiply(t));
    }

    /**
     * Performs linear interpolation between two colors.
     * Alpha components are interpolated separately.
     *
     * @param start The starting color
     * @param end   The end color
     * @param t     The interpolation parameter {@code t} ({@code 0-1})
     * @return The interpolated color
     */
    @Nonnull
    public static RichColor lerp(@Nonnull RichColor start, @Nonnull RichColor end, double t) {
        return new RichColor(lerp(start.rgb(), end.rgb(), t), lerp(start.alpha(), end.alpha(), t));
    }
}
