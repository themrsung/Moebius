package civitas.celestis.math;

import civitas.celestis.math.quaternion.Quaternion;
import civitas.celestis.math.vector.Vector;
import civitas.celestis.math.vector.Vector2;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.math.vector.Vector4;
import jakarta.annotation.Nonnull;

/**
 * Contains utility methods for numbers.
 */
public final class Numbers {
    //
    // Constraints
    //

    /**
     * Explicitly denotes that a given {@code double} must be finite.
     * This is determined by the static method {@link Double#isFinite(double)}.
     * If the value is finite, this will simply pass through the value to the return value.
     * If not, this will throw an {@link IllegalArgumentException}.
     *
     * @param value Value to validate
     * @return The value given as the parameter
     * @throws IllegalArgumentException When the given field is non-finite
     */
    public static double requireFinite(double value) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("This field requires a finite value as an input. The provided value was " + value + ".");
        }

        return value;
    }

    //
    // Randomization
    //

    /**
     * Returns a random {@code double} between the range of {@code min} and {@code max}
     *
     * @param min The minimum acceptable value
     * @param max The maximum acceptable value
     * @return A random value between {@code min} and {@code max}
     */
    public static double random(double min, double max) {
        return Math.random() * max - min;
    }

    //
    // Type Casting
    //

    /**
     * Checks if a {@code double} can be safely cast to an {@code int} without any overflows.
     * @param value Value to check
     * @return {@code true} if {@code (int) value} will not result in an overflow
     */
    public static boolean isIntSafe(double value) {
        // Adds margin of 1 for additional safety
        return value > -Integer.MAX_VALUE && value < Integer.MAX_VALUE;
    }

    /**
     * Checks if a {@link Vector} can be safely cast to an {@link IntVector} without any overflows.
     * @param v Value to check
     * @return {@code true} if the vector is int-safe
     */
    public static boolean isIntSafe(@Nonnull Vector v) {
        for (int i = 0; i < v.length(); i++) {
            if (!isIntSafe(v.valueAt(i))) return false;
        }

        return true;
    }

    //
    // Sum
    //

    /**
     * Returns the total of the provided values.
     *
     * @param values The values to sum
     * @return The sum of the provided values
     */
    public static double sum(@Nonnull double... values) {
        double sum = 0;

        for (final double value : values) {
            sum += value;
        }

        return sum;
    }

    /**
     * Returns the sum of the provided vectors.
     * This is the equivalent of chaining {@link Vector2#add(Vector2)}, but is faster due to the lack of
     * re-instantiation every iteration.
     *
     * @param values The vectors to sum
     * @return The sum of the provided vectors
     */
    @Nonnull
    public static Vector2 sum(@Nonnull Vector2... values) {
        double x = 0;
        double y = 0;

        for (final Vector2 value : values) {
            x += value.x();
            y += value.y();
        }

        return new Vector2(x, y);
    }

    /**
     * Returns the sum of the provided vectors.
     * This is the equivalent of chaining {@link Vector3#add(Vector3)}, but is faster due to the lack of
     * re-instantiation every iteration.
     *
     * @param values The vectors to sum
     * @return The sum of the provided vectors
     */
    @Nonnull
    public static Vector3 sum(@Nonnull Vector3... values) {
        double x = 0;
        double y = 0;
        double z = 0;

        for (final Vector3 value : values) {
            x += value.x();
            y += value.y();
            z += value.z();
        }

        return new Vector3(x, y, z);
    }

    /**
     * Returns the sum of the provided vectors.
     * This is the equivalent of chaining {@link Vector4#add(Vector4)}, but is faster due to the lack of
     * re-instantiation every iteration.
     *
     * @param values The vectors to sum
     * @return The sum of the provided vectors
     */
    @Nonnull
    public static Vector4 sum(@Nonnull Vector4... values) {
        double w = 0;
        double x = 0;
        double y = 0;
        double z = 0;

        for (final Vector4 value : values) {
            w += value.w();
            x += value.x();
            y += value.y();
            z += value.z();
        }

        return new Quaternion(w, x, y, z);
    }

    //
    // Average
    //

    /**
     * Returns the average of the provided values.
     *
     * @param values The values to average
     * @return The average of the values
     */
    public static double avg(@Nonnull double... values) {
        if (values.length == 0) {
            return 0;
        }

        return sum(values) / values.length;
    }

    /**
     * Returns the average of the provided vectors.
     * This is equivalent to the geometric centroid of the vectors.
     *
     * @param values The vectors to average
     * @return The average (geometric centroid) of ths provided vectors
     */
    @Nonnull
    public static Vector2 avg(@Nonnull Vector2... values) {
        return sum(values).divide(values.length);
    }

    /**
     * Returns the average of the provided vectors.
     * This is equivalent to the geometric centroid of the vectors.
     *
     * @param values The vectors to average
     * @return The average (geometric centroid) of ths provided vectors
     */
    @Nonnull
    public static Vector3 avg(@Nonnull Vector3... values) {
        return sum(values).divide(values.length);
    }

    /**
     * Returns the average of the provided vectors.
     * This is equivalent to the geometric centroid of the vectors.
     *
     * @param values The vectors to average
     * @return The average (geometric centroid) of ths provided vectors
     */
    @Nonnull
    public static Vector4 avg(@Nonnull Vector4... values) {
        return sum(values).divide(values.length);
    }

    //
    // Min
    //

    /**
     * Returns the minimum of the provided values.
     *
     * @param values The values to get the minimum of
     * @return The minimum of the provided values
     */
    public static double min(@Nonnull double... values) {
        double min = Double.MAX_VALUE;

        for (final double value : values) {
            min = Math.min(min, value);
        }

        return min;
    }

    /**
     * Returns the minimum vector of the provided vectors.
     *
     * @param values The vectors to get the minimum of
     * @return The minimum of the provided vectors
     */
    @Nonnull
    public static Vector2 min(@Nonnull Vector2... values) {
        double x = Double.MAX_VALUE;
        double y = Double.MAX_VALUE;

        for (final Vector2 value : values) {
            x = Math.min(x, value.x());
            y = Math.min(y, value.y());
        }

        return new Vector2(x, y);
    }

    /**
     * Returns the minimum vector of the provided vectors.
     *
     * @param values The vectors to get the minimum of
     * @return The minimum of the provided vectors
     */
    @Nonnull
    public static Vector3 min(@Nonnull Vector3... values) {
        double x = Double.MAX_VALUE;
        double y = Double.MAX_VALUE;
        double z = Double.MAX_VALUE;

        for (final Vector3 value : values) {
            x = Math.min(x, value.x());
            y = Math.min(y, value.y());
            z = Math.min(z, value.z());
        }

        return new Vector3(x, y, z);
    }

    /**
     * Returns the minimum vector of the provided vectors.
     *
     * @param values The vectors to get the minimum of
     * @return The minimum of the provided vectors
     */
    @Nonnull
    public static Vector4 min(@Nonnull Vector4... values) {
        double w = Double.MAX_VALUE;
        double x = Double.MAX_VALUE;
        double y = Double.MAX_VALUE;
        double z = Double.MAX_VALUE;

        for (final Vector4 value : values) {
            w = Math.min(w, value.w());
            x = Math.min(x, value.x());
            y = Math.min(y, value.y());
            z = Math.min(z, value.z());
        }

        return new Vector4(w, x, y, z);
    }

    //
    // Max
    //

    /**
     * Returns the maximum of the provided values.
     *
     * @param values The values to get the maximum of
     * @return The maximum of the provided values
     */
    public static double max(@Nonnull double... values) {
        double max = -Double.MAX_VALUE;

        for (final double value : values) {
            max = Math.max(max, value);
        }

        return max;
    }

    /**
     * Returns the maximum vector of the provided vectors.
     *
     * @param values The vectors to get the maximum of
     * @return The maximum of the provided vectors
     */
    @Nonnull
    public static Vector2 max(@Nonnull Vector2... values) {
        double x = -Double.MAX_VALUE;
        double y = -Double.MAX_VALUE;

        for (final Vector2 value : values) {
            x = Math.max(x, value.x());
            y = Math.max(y, value.y());
        }

        return new Vector2(x, y);
    }

    /**
     * Returns the maximum vector of the provided vectors.
     *
     * @param values The vectors to get the maximum of
     * @return The maximum of the provided vectors
     */
    @Nonnull
    public static Vector3 max(@Nonnull Vector3... values) {
        double x = -Double.MAX_VALUE;
        double y = -Double.MAX_VALUE;
        double z = -Double.MAX_VALUE;

        for (final Vector3 value : values) {
            x = Math.max(x, value.x());
            y = Math.max(y, value.y());
            z = Math.max(z, value.z());
        }

        return new Vector3(x, y, z);
    }

    /**
     * Returns the maximum vector of the provided vectors.
     *
     * @param values The vectors to get the maximum of
     * @return The maximum of the provided vectors
     */
    @Nonnull
    public static Vector4 max(@Nonnull Vector4... values) {
        double w = -Double.MAX_VALUE;
        double x = -Double.MAX_VALUE;
        double y = -Double.MAX_VALUE;
        double z = -Double.MAX_VALUE;

        for (final Vector4 value : values) {
            w = Math.max(w, value.w());
            x = Math.max(x, value.x());
            y = Math.max(y, value.y());
            z = Math.max(z, value.z());
        }

        return new Vector4(w, x, y, z);
    }

    //
    // Clamping
    //

    /**
     * Clamps the value {@code v} to respect the boundaries of {@code min} and {@code max}.
     *
     * @param v   The value to clamp
     * @param min The minimum acceptable value
     * @param max The maximum acceptable value
     * @return The clamped value
     */
    public static double clamp(double v, double min, double max) {
        return Math.max(Math.min(v, max), min);
    }
}
