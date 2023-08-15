package civitas.celestis.math;

import civitas.celestis.math.complex.Quaternion;
import civitas.celestis.math.vector.Vector2;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.math.vector.Vector4;
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
        return new Quaternion(start.add(end.subtract(start).multiply(t)));
    }

    /**
     * Performs linear interpolation between two colors.
     *
     * @param start The starting color
     * @param end   The end color
     * @param t     The interpolation parameter {@code t} ({@code 0-1})
     * @return The interpolated color
     */
//    @Nonnull
//    public static RichColor lerp(@Nonnull RichColor start, @Nonnull RichColor end, double t) {
//        return new RichColor(lerp((Vector4) start, end, t)); // Delegates to Vector4 lerp for optimization
//    }

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
        return new Quaternion(start.multiply(s0).add(end.multiply(s1)));
    }
}
