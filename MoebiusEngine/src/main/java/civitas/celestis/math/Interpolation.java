package civitas.celestis.math;

import civitas.celestis.graphics.color.Color8;
import civitas.celestis.graphics.color.LinearColor;
import civitas.celestis.math.complex.FloatQuaternion;
import civitas.celestis.math.complex.Quaternion;
import civitas.celestis.math.vector.*;
import jakarta.annotation.Nonnull;

/**
 * Contains interpolation utility methods.
 */
public final class Interpolation {
    //
    // Linear Interpolation (LERP)
    //

    /**
     * Performs linear interpolation (LERP) between the starting value {@code s}
     * and the ending value {@code e}.
     * The interpolation parameter {@code t} determines the amount of interpolation.
     *
     * @param s The starting value of this LERP
     * @param e The ending value of this LERP
     * @param t The interpolation parameter between a range of {@code 0-1}
     * @return The interpolated value
     */
    public static double lerp(double s, double e, double t) {
        return s + (e - s) * t;
    }

    /**
     * Performs linear interpolation (LERP) between the starting value {@code s}
     * and the ending value {@code e}.
     * The interpolation parameter {@code t} determines the amount of interpolation.
     *
     * @param s The starting value of this LERP
     * @param e The ending value of this LERP
     * @param t The interpolation parameter between a range of {@code 0-1}
     * @return The interpolated value
     */
    @Nonnull
    public static Double2 lerp(@Nonnull Double2 s, @Nonnull Double2 e, double t) {
        return s.add(e.subtract(s).multiply(t));
    }

    /**
     * Performs linear interpolation (LERP) between the starting value {@code s}
     * and the ending value {@code e}.
     * The interpolation parameter {@code t} determines the amount of interpolation.
     *
     * @param s The starting value of this LERP
     * @param e The ending value of this LERP
     * @param t The interpolation parameter between a range of {@code 0-1}
     * @return The interpolated value
     */
    @Nonnull
    public static Double3 lerp(@Nonnull Double3 s, @Nonnull Double3 e, double t) {
        return s.add(e.subtract(s).multiply(t));
    }

    /**
     * Performs linear interpolation (LERP) between the starting value {@code s}
     * and the ending value {@code e}.
     * The interpolation parameter {@code t} determines the amount of interpolation.
     *
     * @param s The starting value of this LERP
     * @param e The ending value of this LERP
     * @param t The interpolation parameter between a range of {@code 0-1}
     * @return The interpolated value
     */
    @Nonnull
    public static Double4 lerp(@Nonnull Double4 s, @Nonnull Double4 e, double t) {
        return s.add(e.subtract(s).multiply(t));
    }

    /**
     * Performs linear interpolation (LERP) between the starting value {@code s}
     * and the ending value {@code e}.
     * The interpolation parameter {@code t} determines the amount of interpolation.
     *
     * @param s The starting value of this LERP
     * @param e The ending value of this LERP
     * @param t The interpolation parameter between a range of {@code 0-1}
     * @return The interpolated value
     */
    @Nonnull
    public static Quaternion lerp(@Nonnull Quaternion s, @Nonnull Quaternion e, double t) {
        return new Quaternion(s.add(e.subtract(s).multiply(t)));
    }

    /**
     * Performs linear interpolation (LERP) between the starting value {@code s}
     * and the ending value {@code e}.
     * The interpolation parameter {@code t} determines the amount of interpolation.
     *
     * @param s The starting value of this LERP
     * @param e The ending value of this LERP
     * @param t The interpolation parameter between a range of {@code 0-1}
     * @return The interpolated value
     */
    @Nonnull
    public static Float2 lerp(@Nonnull Float2 s, @Nonnull Float2 e, float t) {
        return s.add(e.subtract(s).multiply(t));
    }

    /**
     * Performs linear interpolation (LERP) between the starting value {@code s}
     * and the ending value {@code e}.
     * The interpolation parameter {@code t} determines the amount of interpolation.
     *
     * @param s The starting value of this LERP
     * @param e The ending value of this LERP
     * @param t The interpolation parameter between a range of {@code 0-1}
     * @return The interpolated value
     */
    @Nonnull
    public static Float3 lerp(@Nonnull Float3 s, @Nonnull Float3 e, float t) {
        return s.add(e.subtract(s).multiply(t));
    }

    /**
     * Performs linear interpolation (LERP) between the starting value {@code s}
     * and the ending value {@code e}.
     * The interpolation parameter {@code t} determines the amount of interpolation.
     *
     * @param s The starting value of this LERP
     * @param e The ending value of this LERP
     * @param t The interpolation parameter between a range of {@code 0-1}
     * @return The interpolated value
     */
    @Nonnull
    public static Float4 lerp(@Nonnull Float4 s, @Nonnull Float4 e, float t) {
        return s.add(e.subtract(s).multiply(t));
    }

    /**
     * Performs linear interpolation (LERP) between the starting value {@code s}
     * and the ending value {@code e}.
     * The interpolation parameter {@code t} determines the amount of interpolation.
     *
     * @param s The starting value of this LERP
     * @param e The ending value of this LERP
     * @param t The interpolation parameter between a range of {@code 0-1}
     * @return The interpolated value
     */
    @Nonnull
    public static FloatQuaternion lerp(@Nonnull FloatQuaternion s, @Nonnull FloatQuaternion e, float t) {
        return new FloatQuaternion(s.add(e.subtract(s).multiply(t)));
    }

    /**
     * Performs linear interpolation (LERP) between the starting value {@code s}
     * and the ending value {@code e}.
     * The interpolation parameter {@code t} determines the amount of interpolation.
     *
     * @param s The starting value of this LERP
     * @param e The ending value of this LERP
     * @param t The interpolation parameter between a range of {@code 0-1}
     * @return The interpolated value
     */
    @Nonnull
    public static LinearColor lerp(@Nonnull Color8 s, @Nonnull Color8 e, float t) {
        // Convert to linear if not already linear
        final LinearColor c1 = s instanceof LinearColor lc ? lc : new LinearColor(s);
        final LinearColor c2 = e instanceof LinearColor lc ? lc : new LinearColor(e);

        return new LinearColor(lerp((Float4) c1, c2, t));
    }

    //
    // Spherical Linear Interpolation (SLERP)
    //

    /**
     * Performs spherical linear interpolation (SLERP) between two quaternions.
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

    /**
     * Performs spherical linear interpolation (SLERP) between two quaternions.
     * This assumes that the input quaternions are already normalized.
     *
     * @param start The starting quaternion
     * @param end   The end quaternion
     * @param t     The interpolation parameter {@code t} ({@code 0-1})
     * @return The interpolated quaternion
     */
    @Nonnull
    public static FloatQuaternion slerp(@Nonnull FloatQuaternion start, @Nonnull FloatQuaternion end, float t) {
        // Get the dot product of the two quaternions
        float dot = start.dot(end);

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
        final float s0 = (float) (Math.cos(theta1) - dot * Math.sin(theta1) / Math.sin(theta0));
        final float s1 = (float) (Math.sin(theta1) / Math.sin(theta0));

        // Perform spherical linear interpolation
        return new FloatQuaternion(start.multiply(s0).add(end.multiply(s1)));
    }
}
