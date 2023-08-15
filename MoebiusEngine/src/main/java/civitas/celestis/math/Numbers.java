package civitas.celestis.math;

import civitas.celestis.math.vector.*;
import jakarta.annotation.Nonnull;

/**
 * Contains numerical utility methods.
 */
public final class Numbers {
    //
    // Constants
    //

    public static final double EPSILON = 1e-6;

    //
    // Constraints
    //

    /**
     * Explicitly denotes that a given {@code double} must be finite.
     * This is determined by the static method {@link Double#isFinite(double)}.
     * If the value is finite, this will simply pass through the value to the return value.
     * If not, this will throw an {@link IllegalArgumentException}.
     *
     * @param value The value to validate
     * @return The value given as the parameter
     * @throws IllegalArgumentException When the given field is non-finite
     */
    public static double requireFinite(double value) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("This field requires a finite value as an input. The provided value was " + value + ".");
        }

        return value;
    }

    /**
     * Explicitly denotes that a given {@code double} must be finite.
     * This is determined by the static method {@link Float#isFinite(float)}.
     * If the value is finite, this will simply pass through the value to the return value.
     * If not, this will throw an {@link IllegalArgumentException}.
     *
     * @param value The value to validate
     * @return The value given as the parameter
     * @throws IllegalArgumentException When the given field is non-finite
     */
    public static float requireFinite(float value) {
        if (!Float.isFinite(value)) {
            throw new IllegalArgumentException("This field requires a finite value as an input. The provided value was " + value + ".");
        }

        return value;
    }

    /**
     * Explicitly denotes that the provided value requires to be within the given boundaries.
     *
     * @param value The value to validate
     * @param min   The minimum allowed value
     * @param max   The maximum allowed value
     * @return The value provided as the {@code value} parameter
     * @throws IllegalArgumentException When the value is out of range
     */
    public static double requireRange(double value, double min, double max) {
        if (value < min || value > max) {
            throw new IllegalArgumentException("The provided value requires to be within the range of " + min + "-" + max + ".");
        }

        return value;
    }

    /**
     * Explicitly denotes that a given {@code double} must be either zero or positive.
     * Non-finite values are also not allowed.
     *
     * @param value The value to validate
     * @return The value given as the parameter
     * @throws IllegalArgumentException When {@code value < 0}
     */
    public static double requireNonNegative(double value) {
        return requireRange(value, 0, Double.MAX_VALUE);
    }

    /**
     * Explicitly denotes that a given {@code double} must have a positive sign.
     * Non-finite values are also not allowed.
     *
     * @param value The value to validate
     * @return The value given as the parameter
     * @throws IllegalArgumentException When {@code value < Double.MIN_VALUE}
     */
    public static double requirePositive(double value) {
        return requireRange(value, Double.MIN_VALUE, Double.MAX_VALUE);
    }

    /**
     * Explicitly denotes that a given {@code double} must not be zero.
     * This only checks for {@code value != 0} and does not filter out non-finite values.
     *
     * @param value The value to validate
     * @return The value given as the parameter
     * @throws IllegalArgumentException When {@code value == 0}
     */
    public static double requireNonZero(double value) {
        if (value == 0) {
            throw new IllegalArgumentException("This value cannot be zero.");
        }

        return value;
    }

    /**
     * Explicitly denotes that the provided value requires to be within the given boundaries.
     *
     * @param value The value to validate
     * @param min   The minimum allowed value
     * @param max   The maximum allowed value
     * @return The value provided as the {@code value} parameter
     * @throws IllegalArgumentException When the value is out of range
     */
    public static float requireRange(float value, float min, float max) {
        if (value < min || value > max) {
            throw new IllegalArgumentException("The provided value requires to be within the range of " + min + "-" + max + ".");
        }

        return value;
    }

    /**
     * Explicitly denotes that a given {@code double} must be either zero or positive.
     * Non-finite values are also not allowed.
     *
     * @param value The value to validate
     * @return The value given as the parameter
     * @throws IllegalArgumentException When {@code value < 0}
     */
    public static float requireNonNegative(float value) {
        return requireRange(value, 0, Float.MAX_VALUE);
    }

    /**
     * Explicitly denotes that a given {@code double} must have a positive sign.
     * Non-finite values are also not allowed.
     *
     * @param value The value to validate
     * @return The value given as the parameter
     * @throws IllegalArgumentException When {@code value < Double.MIN_VALUE}
     */
    public static float requirePositive(float value) {
        return requireRange(value, Float.MIN_VALUE, Float.MAX_VALUE);
    }

    /**
     * Explicitly denotes that a given {@code double} must not be zero.
     * This only checks for {@code value != 0} and does not filter out non-finite values.
     *
     * @param value The value to validate
     * @return The value given as the parameter
     * @throws IllegalArgumentException When {@code value == 0}
     */
    public static float requireNonZero(float value) {
        if (value == 0) {
            throw new IllegalArgumentException("This value cannot be zero.");
        }

        return value;
    }

    /**
     * Explicitly denotes that the provided value requires to be within the given boundaries.
     *
     * @param value The value to validate
     * @param min   The minimum allowed value
     * @param max   The maximum allowed value
     * @return The value provided as the {@code value} parameter
     * @throws IllegalArgumentException When the value is out of range
     */
    public static long requireRange(long value, long min, long max) {
        if (value < min || value > max) {
            throw new IllegalArgumentException("The provided value requires to be within the range of " + min + "-" + max + ".");
        }

        return value;
    }

    /**
     * Explicitly denotes that a given {@code double} must be either zero or positive.
     * Non-finite values are also not allowed.
     *
     * @param value The value to validate
     * @return The value given as the parameter
     * @throws IllegalArgumentException When {@code value < 0}
     */
    public static long requireNonNegative(long value) {
        if (value < 0) {
            throw new IllegalArgumentException("The provided value requires to be non-negative.");
        }

        return value;
    }

    /**
     * Explicitly denotes that a given {@code double} must have a positive sign.
     * Non-finite values are also not allowed.
     *
     * @param value The value to validate
     * @return The value given as the parameter
     * @throws IllegalArgumentException When {@code value < Double.MIN_VALUE}
     */
    public static long requirePositive(long value) {
        if (value <= 0) {
            throw new IllegalArgumentException("The provided value requires to be positive.");
        }

        return value;
    }

    /**
     * Explicitly denotes that a given {@code double} must not be zero.
     * This only checks for {@code value != 0} and does not filter out non-finite values.
     *
     * @param value The value to validate
     * @return The value given as the parameter
     * @throws IllegalArgumentException When {@code value == 0}
     */
    public static long requireNonZero(long value) {
        if (value == 0) {
            throw new IllegalArgumentException("This value cannot be zero.");
        }

        return value;
    }

    /**
     * Explicitly denotes that the provided value requires to be within the given boundaries.
     *
     * @param value The value to validate
     * @param min   The minimum allowed value
     * @param max   The maximum allowed value
     * @return The value provided as the {@code value} parameter
     * @throws IllegalArgumentException When the value is out of range
     */
    public static int requireRange(int value, int min, int max) {
        if (value < min || value > max) {
            throw new IllegalArgumentException("The provided value requires to be within the range of " + min + "-" + max + ".");
        }

        return value;
    }

    /**
     * Explicitly denotes that a given {@code double} must be either zero or positive.
     * Non-finite values are also not allowed.
     *
     * @param value The value to validate
     * @return The value given as the parameter
     * @throws IllegalArgumentException When {@code value < 0}
     */
    public static int requireNonNegative(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("The provided value requires to be non-negative.");
        }

        return value;
    }

    /**
     * Explicitly denotes that a given {@code double} must have a positive sign.
     * Non-finite values are also not allowed.
     *
     * @param value The value to validate
     * @return The value given as the parameter
     * @throws IllegalArgumentException When {@code value < Double.MIN_VALUE}
     */
    public static int requirePositive(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("The provided value requires to be positive.");
        }

        return value;
    }

    /**
     * Explicitly denotes that a given {@code double} must not be zero.
     * This only checks for {@code value != 0} and does not filter out non-finite values.
     *
     * @param value The value to validate
     * @return The value given as the parameter
     * @throws IllegalArgumentException When {@code value == 0}
     */
    public static int requireNonZero(int value) {
        if (value == 0) {
            throw new IllegalArgumentException("This value cannot be zero.");
        }

        return value;
    }

    //
    // Range Check
    //

    /**
     * Checks if the provided value respects the given boundaries
     *
     * @param value The value to check
     * @param min   The minimum allowed value
     * @param max   The maximum allowed value
     * @return {@code true} if the value is within range
     */
    public static boolean isInRange(double value, double min, double max) {
        return value >= min && value <= max;
    }


    /**
     * Checks if a vector is within the specified range.
     *
     * @param value The value to check
     * @param min   The minimum acceptable value
     * @param max   The maximum acceptable value
     * @return {@code true} if the value obeys the bounds
     */
    public static boolean isInRange(@Nonnull Vector2 value, @Nonnull Vector2 min, @Nonnull Vector2 max) {
        return isInRange(value.x(), min.x(), max.x()) && isInRange(value.y(), min.y(), max.y());
    }

    /**
     * Checks if a vector is within the specified range.
     *
     * @param value The value to check
     * @param min   The minimum acceptable value
     * @param max   The maximum acceptable value
     * @return {@code true} if the value obeys the bounds
     */
    public static boolean isInRange(@Nonnull Vector3 value, @Nonnull Vector3 min, @Nonnull Vector3 max) {
        return isInRange(value.x(), min.x(), max.x()) &&
                isInRange(value.y(), min.y(), max.y()) &&
                isInRange(value.z(), min.z(), max.z());
    }

    /**
     * Checks if a vector is within the specified range.
     *
     * @param value The value to check
     * @param min   The minimum acceptable value
     * @param max   The maximum acceptable value
     * @return {@code true} if the value obeys the bounds
     */
    public static boolean isInRange(@Nonnull Vector4 value, @Nonnull Vector4 min, @Nonnull Vector4 max) {
        return isInRange(value.w(), min.w(), max.w()) &&
                isInRange(value.x(), min.x(), max.x()) &&
                isInRange(value.y(), min.y(), max.y()) &&
                isInRange(value.z(), min.z(), max.z());
    }

    //
    // Range Adjustment
    //

    /**
     * Scales a scalar from one scale to another.
     *
     * @param value The value to scale
     * @param imin  The initial minimum value
     * @param imax  The initial maximum value
     * @param fmin  The final minimum value
     * @param fmax  The final maximum value
     * @return The scaled value
     */
    public static double scale(double value, double imin, double imax, double fmin, double fmax) {
        final double irange = imax - imin;
        if (irange == 0) return 0;

        return fmin + (value - imin) * (fmax - fmin) / irange;
    }

    /**
     * Scales a scalar from one scale to another.
     *
     * @param value The value to scale
     * @param imin  The initial minimum value
     * @param imax  The initial maximum value
     * @param fmin  The final minimum value
     * @param fmax  The final maximum value
     * @return The scaled value
     */
    public static float scale(float value, float imin, float imax, float fmin, float fmax) {
        final float irange = imax - imin;
        if (irange == 0) return 0;

        return fmin + (value - imin) * (fmax - fmin) / irange;
    }

    /**
     * Scales a scalar from one scale to another.
     *
     * @param value The value to scale
     * @param imin  The initial minimum value
     * @param imax  The initial maximum value
     * @param fmin  The final minimum value
     * @param fmax  The final maximum value
     * @return The scaled value
     */
    public static long scale(long value, long imin, long imax, long fmin, long fmax) {
        final long irange = imax - imin;
        if (irange == 0) return 0;

        return fmin + (value - imin) * (fmax - fmin) / irange;
    }

    /**
     * Scales a scalar from one scale to another.
     *
     * @param value The value to scale
     * @param imin  The initial minimum value
     * @param imax  The initial maximum value
     * @param fmin  The final minimum value
     * @param fmax  The final maximum value
     * @return The scaled value
     */
    public static int scale(int value, int imin, int imax, int fmin, int fmax) {
        final int irange = imax - imin;
        if (irange == 0) return 0;

        return fmin + (value - imin) * (fmax - fmin) / irange;
    }

    /**
     * Scales a vector from one scale to another.
     *
     * @param value The value to scale
     * @param imin  The initial minimum value
     * @param imax  The initial maximum value
     * @param fmin  The final minimum value
     * @param fmax  The final maximum value
     * @return The scaled value
     */
    @Nonnull
    public static Vector2 scale(
            @Nonnull Vector2 value,
            @Nonnull Vector2 imin,
            @Nonnull Vector2 imax,
            @Nonnull Vector2 fmin,
            @Nonnull Vector2 fmax
    ) {
        return new Vector2(
                scale(value.x(), imin.x(), imax.x(), fmin.x(), fmax.x()),
                scale(value.y(), imin.y(), imax.y(), fmin.y(), fmax.y())
        );
    }

    /**
     * Scales a vector from one scale to another.
     *
     * @param value The value to scale
     * @param imin  The initial minimum value
     * @param imax  The initial maximum value
     * @param fmin  The final minimum value
     * @param fmax  The final maximum value
     * @return The scaled value
     */
    @Nonnull
    public static Vector3 scale(
            @Nonnull Vector3 value,
            @Nonnull Vector3 imin,
            @Nonnull Vector3 imax,
            @Nonnull Vector3 fmin,
            @Nonnull Vector3 fmax
    ) {
        return new Vector3(
                scale(value.x(), imin.x(), imax.x(), fmin.x(), fmax.x()),
                scale(value.y(), imin.y(), imax.y(), fmin.y(), fmax.y()),
                scale(value.z(), imin.z(), imax.z(), fmin.z(), fmax.z())
        );
    }

    /**
     * Scales a vector from one scale to another.
     *
     * @param value The value to scale
     * @param imin  The initial minimum value
     * @param imax  The initial maximum value
     * @param fmin  The final minimum value
     * @param fmax  The final maximum value
     * @return The scaled value
     */
    @Nonnull
    public static Vector4 scale(
            @Nonnull Vector4 value,
            @Nonnull Vector4 imin,
            @Nonnull Vector4 imax,
            @Nonnull Vector4 fmin,
            @Nonnull Vector4 fmax
    ) {
        return new Vector4(
                scale(value.w(), imin.w(), imax.w(), fmin.w(), fmax.w()),
                scale(value.x(), imin.x(), imax.x(), fmin.x(), fmax.x()),
                scale(value.y(), imin.y(), imax.y(), fmin.y(), fmax.y()),
                scale(value.z(), imin.z(), imax.z(), fmin.z(), fmax.z())
        );
    }

    /**
     * Scales a vector from one scale to another.
     *
     * @param value The value to scale
     * @param imin  The initial minimum value
     * @param imax  The initial maximum value
     * @param fmin  The final minimum value
     * @param fmax  The final maximum value
     * @return The scaled value
     */
    @Nonnull
    public static Float2 scale(
            @Nonnull Float2 value,
            @Nonnull Float2 imin,
            @Nonnull Float2 imax,
            @Nonnull Float2 fmin,
            @Nonnull Float2 fmax
    ) {
        return new Float2(
                scale(value.x(), imin.x(), imax.x(), fmin.x(), fmax.x()),
                scale(value.y(), imin.y(), imax.y(), fmin.y(), fmax.y())
        );
    }

    /**
     * Scales a vector from one scale to another.
     *
     * @param value The value to scale
     * @param imin  The initial minimum value
     * @param imax  The initial maximum value
     * @param fmin  The final minimum value
     * @param fmax  The final maximum value
     * @return The scaled value
     */
    @Nonnull
    public static Float3 scale(
            @Nonnull Float3 value,
            @Nonnull Float3 imin,
            @Nonnull Float3 imax,
            @Nonnull Float3 fmin,
            @Nonnull Float3 fmax
    ) {
        return new Float3(
                scale(value.x(), imin.x(), imax.x(), fmin.x(), fmax.x()),
                scale(value.y(), imin.y(), imax.y(), fmin.y(), fmax.y()),
                scale(value.z(), imin.z(), imax.z(), fmin.z(), fmax.z())
        );
    }

    /**
     * Scales a vector from one scale to another.
     *
     * @param value The value to scale
     * @param imin  The initial minimum value
     * @param imax  The initial maximum value
     * @param fmin  The final minimum value
     * @param fmax  The final maximum value
     * @return The scaled value
     */
    @Nonnull
    public static Float4 scale(
            @Nonnull Float4 value,
            @Nonnull Float4 imin,
            @Nonnull Float4 imax,
            @Nonnull Float4 fmin,
            @Nonnull Float4 fmax
    ) {
        return new Float4(
                scale(value.w(), imin.w(), imax.w(), fmin.w(), fmax.w()),
                scale(value.x(), imin.x(), imax.x(), fmin.x(), fmax.x()),
                scale(value.y(), imin.y(), imax.y(), fmin.y(), fmax.y()),
                scale(value.z(), imin.z(), imax.z(), fmin.z(), fmax.z())
        );
    }

    /**
     * Scales a vector from one scale to another.
     *
     * @param value The value to scale
     * @param imin  The initial minimum value
     * @param imax  The initial maximum value
     * @param fmin  The final minimum value
     * @param fmax  The final maximum value
     * @return The scaled value
     */
    @Nonnull
    public static Int2 scale(
            @Nonnull Int2 value,
            @Nonnull Int2 imin,
            @Nonnull Int2 imax,
            @Nonnull Int2 fmin,
            @Nonnull Int2 fmax
    ) {
        return new Int2(
                scale(value.x(), imin.x(), imax.x(), fmin.x(), fmax.x()),
                scale(value.y(), imin.y(), imax.y(), fmin.y(), fmax.y())
        );
    }

    /**
     * Scales a vector from one scale to another.
     *
     * @param value The value to scale
     * @param imin  The initial minimum value
     * @param imax  The initial maximum value
     * @param fmin  The final minimum value
     * @param fmax  The final maximum value
     * @return The scaled value
     */
    @Nonnull
    public static Int3 scale(
            @Nonnull Int3 value,
            @Nonnull Int3 imin,
            @Nonnull Int3 imax,
            @Nonnull Int3 fmin,
            @Nonnull Int3 fmax
    ) {
        return new Int3(
                scale(value.x(), imin.x(), imax.x(), fmin.x(), fmax.x()),
                scale(value.y(), imin.y(), imax.y(), fmin.y(), fmax.y()),
                scale(value.z(), imin.z(), imax.z(), fmin.z(), fmax.z())
        );
    }

    /**
     * Scales a vector from one scale to another.
     *
     * @param value The value to scale
     * @param imin  The initial minimum value
     * @param imax  The initial maximum value
     * @param fmin  The final minimum value
     * @param fmax  The final maximum value
     * @return The scaled value
     */
    @Nonnull
    public static Int4 scale(
            @Nonnull Int4 value,
            @Nonnull Int4 imin,
            @Nonnull Int4 imax,
            @Nonnull Int4 fmin,
            @Nonnull Int4 fmax
    ) {
        return new Int4(
                scale(value.w(), imin.w(), imax.w(), fmin.w(), fmax.w()),
                scale(value.x(), imin.x(), imax.x(), fmin.x(), fmax.x()),
                scale(value.y(), imin.y(), imax.y(), fmin.y(), fmax.y()),
                scale(value.z(), imin.z(), imax.z(), fmin.z(), fmax.z())
        );
    }

    //
    // Clamping
    //

    /**
     * Clamps a value to fit between the provided boundaries of {@code min} and {@code max}.
     *
     * @param value The value to clamp
     * @param min   The minimum allowed value
     * @param max   The maximum allowed value
     * @return The clamped value
     */
    public static int clamp(int value, int min, int max) {
        return Math.min(Math.max(value, min), max);
    }

    /**
     * Clamps a value to fit between the provided boundaries of {@code min} and {@code max}.
     *
     * @param value The value to clamp
     * @param min   The minimum allowed value
     * @param max   The maximum allowed value
     * @return The clamped value
     */
    public static long clamp(long value, long min, long max) {
        return Math.min(Math.max(value, min), max);
    }

    /**
     * Clamps a value to fit between the provided boundaries of {@code min} and {@code max}.
     *
     * @param value The value to clamp
     * @param min   The minimum allowed value
     * @param max   The maximum allowed value
     * @return The clamped value
     */
    public static float clamp(float value, float min, float max) {
        return Math.min(Math.max(value, min), max);
    }

    /**
     * Clamps a value to fit between the provided boundaries of {@code min} and {@code max}.
     *
     * @param value The value to clamp
     * @param min   The minimum allowed value
     * @param max   The maximum allowed value
     * @return The clamped value
     */
    public static double clamp(double value, double min, double max) {
        return Math.min(Math.max(value, min), max);
    }

    //
    // Random
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
     * Returns the total of the provided values.
     *
     * @param values The values to sum
     * @return The sum of the provided values
     */
    public static float sum(@Nonnull float... values) {
        float sum = 0;

        for (final float value : values) {
            sum += value;
        }

        return sum;
    }

    /**
     * Returns the total of the provided values.
     *
     * @param values The values to sum
     * @return The sum of the provided values
     */
    public static long sum(@Nonnull long... values) {
        long sum = 0;

        for (final long value : values) {
            sum += value;
        }

        return sum;
    }

    /**
     * Returns the total of the provided values.
     *
     * @param values The values to sum
     * @return The sum of the provided values
     */
    public static int sum(@Nonnull int... values) {
        int sum = 0;

        for (final long value : values) {
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

        return new Vector4(w, x, y, z);
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
    public static Float2 sum(@Nonnull Float2... values) {
        float x = 0;
        float y = 0;

        for (final Float2 value : values) {
            x += value.x();
            y += value.y();
        }

        return new Float2(x, y);
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
    public static Float3 sum(@Nonnull Float3... values) {
        float x = 0;
        float y = 0;
        float z = 0;

        for (final Float3 value : values) {
            x += value.x();
            y += value.y();
            z += value.z();
        }

        return new Float3(x, y, z);
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
    public static Float4 sum(@Nonnull Float4... values) {
        float w = 0;
        float x = 0;
        float y = 0;
        float z = 0;

        for (final Float4 value : values) {
            w += value.w();
            x += value.x();
            y += value.y();
            z += value.z();
        }

        return new Float4(w, x, y, z);
    }

    /**
     * Returns the sum of the provided vectors.
     * This is the equivalent of chaining {@link Int2#add(Int2)}, but is faster due to the lack of
     * re-instantiation every iteration.
     *
     * @param values The vectors to sum
     * @return The sum of the provided vectors
     */
    @Nonnull
    public static Int2 sum(@Nonnull Int2... values) {
        int x = 0;
        int y = 0;

        for (final Int2 value : values) {
            x += value.x();
            y += value.y();
        }

        return new Int2(x, y);
    }

    /**
     * Returns the sum of the provided vectors.
     * This is the equivalent of chaining {@link Int3#add(Int3)}, but is faster due to the lack of
     * re-instantiation every iteration.
     *
     * @param values The vectors to sum
     * @return The sum of the provided vectors
     */
    @Nonnull
    public static Int3 sum(@Nonnull Int3... values) {
        int x = 0;
        int y = 0;
        int z = 0;

        for (final Int3 value : values) {
            x += value.x();
            y += value.y();
            z += value.z();
        }

        return new Int3(x, y, z);
    }

    /**
     * Returns the sum of the provided vectors.
     * This is the equivalent of chaining {@link Int4#add(Int4)}, but is faster due to the lack of
     * re-instantiation every iteration.
     *
     * @param values The vectors to sum
     * @return The sum of the provided vectors
     */
    @Nonnull
    public static Int4 sum(@Nonnull Int4... values) {
        int w = 0;
        int x = 0;
        int y = 0;
        int z = 0;

        for (final Int4 value : values) {
            w += value.w();
            x += value.x();
            y += value.y();
            z += value.z();
        }

        return new Int4(w, x, y, z);
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
     * Returns the average of the provided values.
     *
     * @param values The values to average
     * @return The average of the values
     */
    public static float avg(@Nonnull float... values) {
        if (values.length == 0) {
            return 0;
        }

        return sum(values) / values.length;
    }

    /**
     * Returns the average of the provided values.
     *
     * @param values The values to average
     * @return The average of the values
     */
    public static double avg(@Nonnull long... values) {
        if (values.length == 0) {
            return 0;
        }

        return (double) sum(values) / values.length;
    }

    /**
     * Returns the average of the provided values.
     *
     * @param values The values to average
     * @return The average of the values
     */
    public static float avg(@Nonnull int... values) {
        if (values.length == 0) {
            return 0;
        }

        return (float) sum(values) / values.length;
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

    /**
     * Returns the average of the provided vectors.
     * This is equivalent to the geometric centroid of the vectors.
     *
     * @param values The vectors to average
     * @return The average (geometric centroid) of ths provided vectors
     */
    @Nonnull
    public static Float2 avg(@Nonnull Float2... values) {
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
    public static Float3 avg(@Nonnull Float3... values) {
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
    public static Float4 avg(@Nonnull Float4... values) {
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
    public static Int2 avg(@Nonnull Int2... values) {
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
    public static Int3 avg(@Nonnull Int3... values) {
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
    public static Int4 avg(@Nonnull Int4... values) {
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

    /**
     * Returns the minimum vector of the provided vectors.
     *
     * @param values The vectors to get the minimum of
     * @return The minimum of the provided vectors
     */
    @Nonnull
    public static Float2 min(@Nonnull Float2... values) {
        float x = Float.MAX_VALUE;
        float y = Float.MAX_VALUE;

        for (final Float2 value : values) {
            x = Math.min(x, value.x());
            y = Math.min(y, value.y());
        }

        return new Float2(x, y);
    }

    /**
     * Returns the minimum vector of the provided vectors.
     *
     * @param values The vectors to get the minimum of
     * @return The minimum of the provided vectors
     */
    @Nonnull
    public static Float3 min(@Nonnull Float3... values) {
        float x = Float.MAX_VALUE;
        float y = Float.MAX_VALUE;
        float z = Float.MAX_VALUE;

        for (final Float3 value : values) {
            x = Math.min(x, value.x());
            y = Math.min(y, value.y());
            z = Math.min(z, value.z());
        }

        return new Float3(x, y, z);
    }

    /**
     * Returns the minimum vector of the provided vectors.
     *
     * @param values The vectors to get the minimum of
     * @return The minimum of the provided vectors
     */
    @Nonnull
    public static Float4 min(@Nonnull Float4... values) {
        float w = Float.MAX_VALUE;
        float x = Float.MAX_VALUE;
        float y = Float.MAX_VALUE;
        float z = Float.MAX_VALUE;

        for (final Float4 value : values) {
            w = Math.min(w, value.w());
            x = Math.min(x, value.x());
            y = Math.min(y, value.y());
            z = Math.min(z, value.z());
        }

        return new Float4(w, x, y, z);
    }

    /**
     * Returns the minimum vector of the provided vectors.
     *
     * @param values The vectors to get the minimum of
     * @return The minimum of the provided vectors
     */
    @Nonnull
    public static Int2 min(@Nonnull Int2... values) {
        int x = Integer.MAX_VALUE;
        int y = Integer.MAX_VALUE;

        for (final Int2 value : values) {
            x = Math.min(x, value.x());
            y = Math.min(y, value.y());
        }

        return new Int2(x, y);
    }

    /**
     * Returns the minimum vector of the provided vectors.
     *
     * @param values The vectors to get the minimum of
     * @return The minimum of the provided vectors
     */
    @Nonnull
    public static Int3 min(@Nonnull Int3... values) {
        int x = Integer.MAX_VALUE;
        int y = Integer.MAX_VALUE;
        int z = Integer.MAX_VALUE;

        for (final Int3 value : values) {
            x = Math.min(x, value.x());
            y = Math.min(y, value.y());
            z = Math.min(z, value.z());
        }

        return new Int3(x, y, z);
    }

    /**
     * Returns the minimum vector of the provided vectors.
     *
     * @param values The vectors to get the minimum of
     * @return The minimum of the provided vectors
     */
    @Nonnull
    public static Int4 min(@Nonnull Int4... values) {
        int w = Integer.MAX_VALUE;
        int x = Integer.MAX_VALUE;
        int y = Integer.MAX_VALUE;
        int z = Integer.MAX_VALUE;

        for (final Int4 value : values) {
            w = Math.min(w, value.w());
            x = Math.min(x, value.x());
            y = Math.min(y, value.y());
            z = Math.min(z, value.z());
        }

        return new Int4(w, x, y, z);
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

    /**
     * Returns the maximum vector of the provided vectors.
     *
     * @param values The vectors to get the maximum of
     * @return The maximum of the provided vectors
     */
    @Nonnull
    public static Float2 max(@Nonnull Float2... values) {
        float x = -Float.MAX_VALUE;
        float y = -Float.MAX_VALUE;

        for (final Float2 value : values) {
            x = Math.max(x, value.x());
            y = Math.max(y, value.y());
        }

        return new Float2(x, y);
    }

    /**
     * Returns the maximum vector of the provided vectors.
     *
     * @param values The vectors to get the maximum of
     * @return The maximum of the provided vectors
     */
    @Nonnull
    public static Float3 max(@Nonnull Float3... values) {
        float x = -Float.MAX_VALUE;
        float y = -Float.MAX_VALUE;
        float z = -Float.MAX_VALUE;

        for (final Float3 value : values) {
            x = Math.max(x, value.x());
            y = Math.max(y, value.y());
            z = Math.max(z, value.z());
        }

        return new Float3(x, y, z);
    }

    /**
     * Returns the maximum vector of the provided vectors.
     *
     * @param values The vectors to get the maximum of
     * @return The maximum of the provided vectors
     */
    @Nonnull
    public static Float4 max(@Nonnull Float4... values) {
        float w = -Float.MAX_VALUE;
        float x = -Float.MAX_VALUE;
        float y = -Float.MAX_VALUE;
        float z = -Float.MAX_VALUE;

        for (final Float4 value : values) {
            w = Math.max(w, value.w());
            x = Math.max(x, value.x());
            y = Math.max(y, value.y());
            z = Math.max(z, value.z());
        }

        return new Float4(w, x, y, z);
    }

    /**
     * Returns the maximum vector of the provided vectors.
     *
     * @param values The vectors to get the minimum of
     * @return The maximum of the provided vectors
     */
    @Nonnull
    public static Int2 max(@Nonnull Int2... values) {
        int x = -Integer.MAX_VALUE;
        int y = -Integer.MAX_VALUE;

        for (final Int2 value : values) {
            x = Math.max(x, value.x());
            y = Math.max(y, value.y());
        }

        return new Int2(x, y);
    }

    /**
     * Returns the maximum vector of the provided vectors.
     *
     * @param values The vectors to get the minimum of
     * @return The maximum of the provided vectors
     */
    @Nonnull
    public static Int3 max(@Nonnull Int3... values) {
        int x = -Integer.MAX_VALUE;
        int y = -Integer.MAX_VALUE;
        int z = -Integer.MAX_VALUE;

        for (final Int3 value : values) {
            x = Math.max(x, value.x());
            y = Math.max(y, value.y());
            z = Math.max(z, value.z());
        }

        return new Int3(x, y, z);
    }

    /**
     * Returns the maximum vector of the provided vectors.
     *
     * @param values The vectors to get the minimum of
     * @return The maximum of the provided vectors
     */
    @Nonnull
    public static Int4 max(@Nonnull Int4... values) {
        int w = -Integer.MAX_VALUE;
        int x = -Integer.MAX_VALUE;
        int y = -Integer.MAX_VALUE;
        int z = -Integer.MAX_VALUE;

        for (final Int4 value : values) {
            w = Math.max(w, value.w());
            x = Math.max(x, value.x());
            y = Math.max(y, value.y());
            z = Math.max(z, value.z());
        }

        return new Int4(w, x, y, z);
    }

    //
    // Weighted Average
    //

    /**
     * Returns the weighted average of two numbers.
     *
     * @param v1 The first number
     * @param v2 The second number
     * @param w1 The weight of the first number
     * @param w2 The weight of the second number
     * @return The weighted average
     */
    public static double avgw(double v1, double v2, double w1, double w2) {
        final double denominator = w1 + w2;
        if (denominator == 0) {
            throw new ArithmeticException("Cannot divide by zero.");
        }

        return (v1 * w1 + v2 * w2) / denominator;
    }

    /**
     * Returns the weighted average of three numbers.
     *
     * @param v1 The first number
     * @param v2 The second number
     * @param v3 The third number
     * @param w1 The weight of the first number
     * @param w2 The weight of the second number
     * @param w3 The weight of the third number
     * @return The weighted average
     */
    public static double avgw(double v1, double v2, double v3, double w1, double w2, double w3) {
        final double denominator = w1 + w2 + w3;
        if (denominator == 0) {
            throw new ArithmeticException("Cannot divide by zero.");
        }

        return (v1 * w1 + v2 * w2 + v3 * w3) / denominator;
    }

    /**
     * Returns the weighted average of the given numbers.
     *
     * @param values  The values to average
     * @param weights The weights of the values to average
     * @return The weighted average
     */
    public static double avgw(@Nonnull double[] values, @Nonnull double[] weights) {
        if (values.length != weights.length) {
            throw new IllegalArgumentException("The values and weights have a different length.");
        }

        final double denominator = Numbers.sum(weights);
        if (denominator == 0) {
            throw new ArithmeticException("Cannot divide by zero.");
        }

        double sum = 0;

        for (int i = 0; i < values.length; i++) {
            sum += (values[i] * weights[i]);
        }

        return sum / denominator;
    }

    /**
     * Returns the weighted average of two vectors.
     *
     * @param v1 The first vector
     * @param v2 The second vector
     * @param w1 The weight of the first vector
     * @param w2 The weight of the second vector
     * @return The weighted average of the two vectors
     */
    @Nonnull
    public static Vector2 avgw(@Nonnull Vector2 v1, @Nonnull Vector2 v2, double w1, double w2) {
        return v1.multiply(w1).add(v2.multiply(w2)).divide(w1 + w2);
    }

    /**
     * Returns the weighted average of three vectors.
     *
     * @param v1 The first vector
     * @param v2 The second vector
     * @param v3 The third vector
     * @param w1 The weight of the first vector
     * @param w2 The weight of the second vector
     * @param w3 The weight of the third vector
     * @return The weighted average of the three vectors
     */
    @Nonnull
    public static Vector2 avgw(@Nonnull Vector2 v1, @Nonnull Vector2 v2, @Nonnull Vector2 v3, double w1, double w2, double w3) {
        return v1.multiply(w1).add(v2.multiply(w2)).add(v3.multiply(w3)).divide(w1 + w2 + w3);
    }

    /**
     * Returns the weighted average of the given vectors.
     *
     * @param values  The vectors to average
     * @param weights The weights of the vectors to average
     * @return The weighted average
     */
    @Nonnull
    public static Vector2 avgw(@Nonnull Vector2[] values, @Nonnull double[] weights) {
        if (values.length != weights.length) {
            throw new IllegalArgumentException("The length of the values and weights are different.");
        }

        final Vector2[] weighted = new Vector2[values.length];
        for (int i = 0; i < values.length; i++) {
            weighted[i] = values[i].multiply(weights[i]);
        }

        return sum(weighted).divide(sum(weights));
    }

    /**
     * Returns the weighted average of two vectors.
     *
     * @param v1 The first vector
     * @param v2 The second vector
     * @param w1 The weight of the first vector
     * @param w2 The weight of the second vector
     * @return The weighted average of the two vectors
     */
    @Nonnull
    public static Vector3 avgw(@Nonnull Vector3 v1, @Nonnull Vector3 v2, double w1, double w2) {
        return v1.multiply(w1).add(v2.multiply(w2)).divide(w1 + w2);
    }

    /**
     * Returns the weighted average of three vectors.
     *
     * @param v1 The first vector
     * @param v2 The second vector
     * @param v3 The third vector
     * @param w1 The weight of the first vector
     * @param w2 The weight of the second vector
     * @param w3 The weight of the third vector
     * @return The weighted average of the three vectors
     */
    @Nonnull
    public static Vector3 avgw(@Nonnull Vector3 v1, @Nonnull Vector3 v2, @Nonnull Vector3 v3, double w1, double w2, double w3) {
        return v1.multiply(w1).add(v2.multiply(w2)).add(v3.multiply(w3)).divide(w1 + w2 + w3);
    }

    /**
     * Returns the weighted average of the given vectors.
     *
     * @param values  The vectors to average
     * @param weights The weights of the vectors to average
     * @return The weighted average
     */
    @Nonnull
    public static Vector3 avgw(@Nonnull Vector3[] values, @Nonnull double[] weights) {
        if (values.length != weights.length) {
            throw new IllegalArgumentException("The length of the values and weights are different.");
        }

        final Vector3[] weighted = new Vector3[values.length];
        for (int i = 0; i < values.length; i++) {
            weighted[i] = values[i].multiply(weights[i]);
        }

        return sum(weighted).divide(sum(weights));
    }

    /**
     * Returns the weighted average of two vectors.
     *
     * @param v1 The first vector
     * @param v2 The second vector
     * @param w1 The weight of the first vector
     * @param w2 The weight of the second vector
     * @return The weighted average of the two vectors
     */
    @Nonnull
    public static Vector4 avgw(@Nonnull Vector4 v1, @Nonnull Vector4 v2, double w1, double w2) {
        return v1.multiply(w1).add(v2.multiply(w2)).divide(w1 + w2);
    }

    /**
     * Returns the weighted average of three vectors.
     *
     * @param v1 The first vector
     * @param v2 The second vector
     * @param v3 The third vector
     * @param w1 The weight of the first vector
     * @param w2 The weight of the second vector
     * @param w3 The weight of the third vector
     * @return The weighted average of the three vectors
     */
    @Nonnull
    public static Vector4 avgw(@Nonnull Vector4 v1, @Nonnull Vector4 v2, @Nonnull Vector4 v3, double w1, double w2, double w3) {
        return v1.multiply(w1).add(v2.multiply(w2)).add(v3.multiply(w3)).divide(w1 + w2 + w3);
    }

    /**
     * Returns the weighted average of the given vectors.
     *
     * @param values  The vectors to average
     * @param weights The weights of the vectors to average
     * @return The weighted average
     */
    @Nonnull
    public static Vector4 avgw(@Nonnull Vector4[] values, @Nonnull double[] weights) {
        if (values.length != weights.length) {
            throw new IllegalArgumentException("The length of the values and weights are different.");
        }

        final Vector4[] weighted = new Vector4[values.length];
        for (int i = 0; i < values.length; i++) {
            weighted[i] = values[i].multiply(weights[i]);
        }

        return sum(weighted).divide(sum(weights));
    }

    /**
     * Returns the weighted average of two vectors.
     *
     * @param v1 The first vector
     * @param v2 The second vector
     * @param w1 The weight of the first vector
     * @param w2 The weight of the second vector
     * @return The weighted average of the two vectors
     */
    @Nonnull
    public static Float2 avgw(@Nonnull Float2 v1, @Nonnull Float2 v2, float w1, float w2) {
        return v1.multiply(w1).add(v2.multiply(w2)).divide(w1 + w2);
    }

    /**
     * Returns the weighted average of three vectors.
     *
     * @param v1 The first vector
     * @param v2 The second vector
     * @param v3 The third vector
     * @param w1 The weight of the first vector
     * @param w2 The weight of the second vector
     * @param w3 The weight of the third vector
     * @return The weighted average of the three vectors
     */
    @Nonnull
    public static Float2 avgw(@Nonnull Float2 v1, @Nonnull Float2 v2, @Nonnull Float2 v3, float w1, float w2, float w3) {
        return v1.multiply(w1).add(v2.multiply(w2)).add(v3.multiply(w3)).divide(w1 + w2 + w3);
    }

    /**
     * Returns the weighted average of the given vectors.
     *
     * @param values  The vectors to average
     * @param weights The weights of the vectors to average
     * @return The weighted average
     */
    @Nonnull
    public static Float2 avgw(@Nonnull Float2[] values, @Nonnull float[] weights) {
        if (values.length != weights.length) {
            throw new IllegalArgumentException("The length of the values and weights are different.");
        }

        final Float2[] weighted = new Float2[values.length];
        for (int i = 0; i < values.length; i++) {
            weighted[i] = values[i].multiply(weights[i]);
        }

        return sum(weighted).divide(sum(weights));
    }

    /**
     * Returns the weighted average of two vectors.
     *
     * @param v1 The first vector
     * @param v2 The second vector
     * @param w1 The weight of the first vector
     * @param w2 The weight of the second vector
     * @return The weighted average of the two vectors
     */
    @Nonnull
    public static Float3 avgw(@Nonnull Float3 v1, @Nonnull Float3 v2, float w1, float w2) {
        return v1.multiply(w1).add(v2.multiply(w2)).divide(w1 + w2);
    }

    /**
     * Returns the weighted average of three vectors.
     *
     * @param v1 The first vector
     * @param v2 The second vector
     * @param v3 The third vector
     * @param w1 The weight of the first vector
     * @param w2 The weight of the second vector
     * @param w3 The weight of the third vector
     * @return The weighted average of the three vectors
     */
    @Nonnull
    public static Float3 avgw(@Nonnull Float3 v1, @Nonnull Float3 v2, @Nonnull Float3 v3, float w1, float w2, float w3) {
        return v1.multiply(w1).add(v2.multiply(w2)).add(v3.multiply(w3)).divide(w1 + w2 + w3);
    }

    /**
     * Returns the weighted average of the given vectors.
     *
     * @param values  The vectors to average
     * @param weights The weights of the vectors to average
     * @return The weighted average
     */
    @Nonnull
    public static Float3 avgw(@Nonnull Float3[] values, @Nonnull float[] weights) {
        if (values.length != weights.length) {
            throw new IllegalArgumentException("The length of the values and weights are different.");
        }

        final Float3[] weighted = new Float3[values.length];
        for (int i = 0; i < values.length; i++) {
            weighted[i] = values[i].multiply(weights[i]);
        }

        return sum(weighted).divide(sum(weights));
    }

    /**
     * Returns the weighted average of two vectors.
     *
     * @param v1 The first vector
     * @param v2 The second vector
     * @param w1 The weight of the first vector
     * @param w2 The weight of the second vector
     * @return The weighted average of the two vectors
     */
    @Nonnull
    public static Float4 avgw(@Nonnull Float4 v1, @Nonnull Float4 v2, float w1, float w2) {
        return v1.multiply(w1).add(v2.multiply(w2)).divide(w1 + w2);
    }

    /**
     * Returns the weighted average of three vectors.
     *
     * @param v1 The first vector
     * @param v2 The second vector
     * @param v3 The third vector
     * @param w1 The weight of the first vector
     * @param w2 The weight of the second vector
     * @param w3 The weight of the third vector
     * @return The weighted average of the three vectors
     */
    @Nonnull
    public static Float4 avgw(@Nonnull Float4 v1, @Nonnull Float4 v2, @Nonnull Float4 v3, float w1, float w2, float w3) {
        return v1.multiply(w1).add(v2.multiply(w2)).add(v3.multiply(w3)).divide(w1 + w2 + w3);
    }

    /**
     * Returns the weighted average of the given vectors.
     *
     * @param values  The vectors to average
     * @param weights The weights of the vectors to average
     * @return The weighted average
     */
    @Nonnull
    public static Float4 avgw(@Nonnull Float4[] values, @Nonnull float[] weights) {
        if (values.length != weights.length) {
            throw new IllegalArgumentException("The length of the values and weights are different.");
        }

        final Float4[] weighted = new Float4[values.length];
        for (int i = 0; i < values.length; i++) {
            weighted[i] = values[i].multiply(weights[i]);
        }

        return sum(weighted).divide(sum(weights));
    }

    //
    // Fractional Math
    //

    /**
     * Returns the greatest common denominator of the two values.
     *
     * @param v1 The first value
     * @param v2 The second value
     * @return The greatest common denominator of the two values
     */
    public static double gcd(double v1, double v2) {
        return v2 == 0 ? v1 : gcd(v2, v1 % v2);
    }

    /**
     * Returns the least common multiple of the two values.
     *
     * @param v1 The first value
     * @param v2 The second value
     * @return The least common multiple of the two values
     */
    public static double lcm(double v1, double v2) {
        return (v1 * v2) / gcd(v1, v2);
    }

    /**
     * Returns the greatest common denominator of the two values.
     *
     * @param v1 The first value
     * @param v2 The second value
     * @return The greatest common denominator of the two values
     */
    public static long gcd(long v1, long v2) {
        return v2 == 0 ? v1 : gcd(v2, v1 % v2);
    }

    /**
     * Returns the least common multiple of the two values.
     *
     * @param v1 The first value
     * @param v2 The second value
     * @return The least common multiple of the two values
     */
    public static long lcm(long v1, long v2) {
        return (v1 * v2) / gcd(v1, v2);
    }

    //
    // Miscellaneous
    //

    /**
     * Calculates the distance between two scalars.
     *
     * @param v1 The first value
     * @param v2 The second value
     * @return The distance between the two scalars
     */
    public static double distance(double v1, double v2) {
        return Math.abs(v2 - v1);
    }

    /**
     * Calculates the squared distance between two scalars.
     *
     * @param v1 The first value
     * @param v2 The second value
     * @return The squared distance between the two scalars
     */
    public static double distance2(double v1, double v2) {
        final double diff = v2 - v1;
        return diff * diff;
    }
}
