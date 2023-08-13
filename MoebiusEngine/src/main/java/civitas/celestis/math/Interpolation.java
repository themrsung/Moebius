package civitas.celestis.math;

import civitas.celestis.graphics.color.RichColor;
import civitas.celestis.math.complex.Quaternion;
import civitas.celestis.math.vector.*;
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
     *
     * @param start The starting color
     * @param end   The end color
     * @param t     The interpolation parameter {@code t} ({@code 0-1})
     * @return The interpolated color
     */
    @Nonnull
    public static RichColor lerp(@Nonnull RichColor start, @Nonnull RichColor end, double t) {
        return new RichColor(lerp((Vector4) start, end, t)); // Delegates to Vector4 lerp for optimization
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
    public static Vector5 lerp(@Nonnull Vector5 start, @Nonnull Vector5 end, double t) {
        return start.add(end.subtract(start).multiply(t));
    }

    //
    // Spherical Linear Interpolation
    //

    /**
     * Performs spherical linear interpolation between two quaternions.
     * This assumes that the input quaternions are already normalized.
     *
     * @param start The starting quaternion
     * @param end   The end quaternion
     * @param t     The interpolation parameter {@code t} ({@code 0-1})
     * @return The interpolated quaternion
     */
    @Nonnull
    public static Quaternion slerp(@Nonnull Quaternion start, @Nonnull Quaternion end, double t) {
        // Get the dot product of the two quaternions
        double dot = start.dot(end);

        // Determine direction and adjust end quaternion if required
        if (dot < 0) {
            end = end.negate();
            dot = -dot;
        }

        if (1 - dot < Numbers.EPSILON) {
            // Quaternions are very close, use linear interpolation
            return lerp(start, end, t);
        }

        // Calculate the angle between the quaternions
        final double theta0 = Math.acos(dot);
        final double theta1 = theta0 * t;

        // Calculate the interpolation coefficients
        final double s0 = Math.cos(theta1) - dot * Math.sin(theta1) / Math.sin(theta0);
        final double s1 = Math.sin(theta1) / Math.sin(theta0);

        // Perform spherical linear interpolation
        return start.multiply(s0).add(end.multiply(s1));
    }
}
