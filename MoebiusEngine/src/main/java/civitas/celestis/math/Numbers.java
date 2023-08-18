package civitas.celestis.math;

import civitas.celestis.math.vector.*;
import civitas.celestis.util.collection.CircularQueue;
import jakarta.annotation.Nonnull;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

/**
 * Contains utility methods related to numbers.
 */
public class Numbers {
    /**
     * A small value often used to counter the effects of floating point imprecision.
     */
    public static final double EPSILON = 1e-6;

    //
    // Equality
    //

    /**
     * Checks if two numbers are exactly equal.
     *
     * @param n1 The first number to compare
     * @param n2 The second number to compare
     * @return {@code true} if the values converted to {@code double} are equal
     */
    public static boolean equals(@Nonnull Number n1, @Nonnull Number n2) {
        // BigDecimal comparison
        if (n1 instanceof BigDecimal bd) return bd.equals(n2);
        if (n2 instanceof BigDecimal bd) return bd.equals(n1);

        // BigInteger comparison
        if (n1 instanceof BigInteger bi) return bi.equals(n2);
        if (n2 instanceof BigInteger bi) return bi.equals(n1);

        // double value comparison
        return n1.doubleValue() == n2.doubleValue();
    }

    //
    // Random
    //

    /**
     * A circular queue of four random number generators.
     */
    private static final CircularQueue<Random> randomGenerators = CircularQueue.of(
            new Random(),
            new Random(),
            new Random(),
            new Random()
    );

    /**
     * Returns a random value of {@code double} between the range of {@code [0-1)}.
     *
     * @return A random value of {@code double} between the range of {@code [0-1)}
     */
    @SuppressWarnings("ConstantConditions")
    public static double random() {
        return randomGenerators.next().nextDouble();
    }

    /**
     * Returns a random value of {@code float} between the range of {@code [0-1)}.
     *
     * @return A random value of {@code float} between the range of {@code [0-1)}
     */
    @SuppressWarnings("ConstantConditions")
    public static float randomFloat() {
        return (float) randomGenerators.next().nextDouble();
    }

    /**
     * Returns a random finite value of {@code double}.
     *
     * @return A random finite value
     */
    @SuppressWarnings("ConstantConditions")
    public static double randomFinite() {
        return (-1 + randomGenerators.next().nextDouble() * 2) * Double.MAX_VALUE;
    }

    /**
     * Returns a random finite value of {@code float}.
     *
     * @return A random finite value
     */
    @SuppressWarnings("ConstantConditions")
    public static float randomFiniteFloat() {
        return (float) (-1 + randomGenerators.next().nextDouble() * 2) * Float.MAX_VALUE;
    }

    /**
     * Returns a random value around zero which follows a Gaussian curve with a
     * standard deviation of {@code 1}.
     *
     * @return A random finite value following a bell curve with a mean of zero
     */
    @SuppressWarnings("ConstantConditions")
    public static double randomGaussian() {
        return randomGenerators.next().nextGaussian();
    }

    /**
     * Returns a random value around zero which follows a Gaussian curve with a
     * standard deviation of {@code std}.
     *
     * @param std The amount of standard deviation desired
     * @return A random finite value following a bell curve with a mean of zero
     */
    @SuppressWarnings("ConstantConditions")
    public static double randomGaussian(double std) {
        return randomGenerators.next().nextGaussian() * std;
    }

    /**
     * Returns a random value around the specified mean which follows a
     * Gaussian curve with a standard deviation of {@code std}.
     *
     * @param std  The amount of standard deviation desired
     * @param mean The desired mean of the curve
     * @return A random finite value following a bell curve with the provided mean
     */
    @SuppressWarnings("ConstantConditions")
    public static double randomGaussian(double std, double mean) {
        return randomGenerators.next().nextGaussian() * std + mean;
    }

    /**
     * Returns a random value between the provided boundaries of {@code min} and {@code max}.
     *
     * @param min The minimum allowed value
     * @param max The maximum allowed value
     * @return A random value between the two values
     */
    @SuppressWarnings("ConstantConditions")
    public static double random(double min, double max) {
        return min + randomGenerators.next().nextDouble() * (max - min);
    }

    /**
     * Returns a random value between the provided boundaries of {@code min} and {@code max}.
     *
     * @param min The minimum allowed value
     * @param max The maximum allowed value
     * @return A random value between the two values
     */
    @SuppressWarnings("ConstantConditions")
    public static float random(float min, float max) {
        return min + randomGenerators.next().nextFloat() * (max - min);
    }

    /**
     * Returns a random value between the provided boundaries of {@code min} and {@code max}.
     *
     * @param min The minimum allowed value
     * @param max The maximum allowed value
     * @return A random value between the two values
     */
    @SuppressWarnings("ConstantConditions")
    public static long random(long min, long max) {
        return min + (long) (randomGenerators.next().nextDouble() * (max - min));
    }

    /**
     * Returns a random value between the provided boundaries of {@code min} and {@code max}.
     *
     * @param min The minimum allowed value
     * @param max The maximum allowed value
     * @return A random value between the two values
     */
    @SuppressWarnings("ConstantConditions")
    public static int random(int min, int max) {
        return min + (int) (randomGenerators.next().nextFloat() * (max - min));
    }

    /**
     * Returns a new random unit vector.
     *
     * @return A random unit vector
     */
    @Nonnull
    public static Double2 randomDouble2() {
        try {
            return new Double2(random(), random()).normalize();
        } catch (final ArithmeticException e) {
            return Double2.POSITIVE_X; // Special case for zero vectors
        }
    }

    /**
     * Returns a new random unit vector.
     *
     * @return A random unit vector
     */
    @Nonnull
    public static Double3 randomDouble3() {
        try {
            return new Double3(random(), random(), random()).normalize();
        } catch (final ArithmeticException e) {
            return Double3.POSITIVE_X; // Special case for zero vectors
        }
    }

    /**
     * Returns a new random unit vector.
     *
     * @return A random unit vector
     */
    @Nonnull
    public static Double4 randomDouble4() {
        try {
            return new Double4(random(), random(), random(), random()).normalize();
        } catch (final ArithmeticException e) {
            return Double4.POSITIVE_W; // Special case for zero vectors
        }
    }

    /**
     * Returns a new random unit vector.
     *
     * @return A random unit vector
     */
    @Nonnull
    public static Float2 randomFloat2() {
        try {
            return new Float2(randomFloat(), randomFloat()).normalize();
        } catch (final ArithmeticException e) {
            return Float2.POSITIVE_X; // Special case for zero vectors
        }
    }

    /**
     * Returns a new random unit vector.
     *
     * @return A random unit vector
     */
    @Nonnull
    public static Float3 randomFloat3() {
        try {
            return new Float3(randomFloat(), randomFloat(), randomFloat()).normalize();
        } catch (final ArithmeticException e) {
            return Float3.POSITIVE_X; // Special case for zero vectors
        }
    }

    /**
     * Returns a new random unit vector.
     *
     * @return A random unit vector
     */
    @Nonnull
    public static Float4 randomFloat4() {
        try {
            return new Float4(randomFloat(), randomFloat(), randomFloat(), randomFloat()).normalize();
        } catch (final ArithmeticException e) {
            return Float4.POSITIVE_W;
        }
    }

    //
    // Clamping
    //

    /**
     * Clamps the provided value to respect the given boundaries of {@code min} and {@code max}.
     *
     * @param value The value to clamp
     * @param min   The minimum allowed value
     * @param max   The maximum allowed value
     * @return The clamped value
     */
    public static double clamp(double value, double min, double max) {
        return Math.min(Math.max(value, min), max);
    }

    /**
     * Clamps the provided value to respect the given boundaries of {@code min} and {@code max}.
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
     * Clamps the provided value to respect the given boundaries of {@code min} and {@code max}.
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
     * Clamps the provided value to respect the given boundaries of {@code min} and {@code max}.
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
     * Clamps the provided value to respect the given boundaries of {@code min} and {@code max}.
     *
     * @param value The value to clamp
     * @param min   The minimum allowed value
     * @param max   The maximum allowed value
     * @return The clamped value
     */
    public static short clamp(short value, short min, short max) {
        if (value < min) return min;
        if (value > max) return max;
        return value;
    }

    /**
     * Clamps the provided value to respect the given boundaries of {@code min} and {@code max}.
     *
     * @param value The value to clamp
     * @param min   The minimum allowed value
     * @param max   The maximum allowed value
     * @return The clamped value
     */
    public static byte clamp(byte value, byte min, byte max) {
        if (value < min) return min;
        if (value > max) return max;
        return value;
    }

    /**
     * Between the provided values, this returns the minimum value.
     *
     * @param v1 The first value to compare
     * @param v2 The second value to compare
     * @return The minimum of the two values
     */
    public static double min(double v1, double v2) {
        return Math.min(v1, v2);
    }

    /**
     * Between the provided values, this returns the minimum value.
     *
     * @param v1 The first value to compare
     * @param v2 The second value to compare
     * @param v3 The third value to compare
     * @return The minimum of the three values
     */
    public static double min(double v1, double v2, double v3) {
        if (v1 > v2) {
            return Math.min(v2, v3);
        } else {
            return Math.min(v1, v2);
        }
    }

    /**
     * Between the provided values, this returns the minimum value.
     *
     * @param values The values to compare
     * @return The minimum of the given values
     */
    public static double min(@Nonnull double... values) {
        double min = Double.MAX_VALUE;

        for (final double value : values) {
            min = Math.min(min, value);
        }

        return min;
    }

    /**
     * Between the provided vectors, this returns the minimum vector.
     *
     * @param values The values to compare
     * @return The minimum vector of the given vectors
     * @see civitas.celestis.math.vector.Vector#min(Vector)
     */
    @Nonnull
    public static Double2 min(@Nonnull Double2... values) {
        double x = Double.MAX_VALUE;
        double y = Double.MAX_VALUE;

        for (final Double2 value : values) {
            x = Math.min(x, value.x());
            y = Math.min(y, value.y());
        }

        return new Double2(x, y);
    }

    /**
     * Between the provided vectors, this returns the minimum vector.
     *
     * @param values The values to compare
     * @return The minimum vector of the given vectors
     * @see civitas.celestis.math.vector.Vector#min(Vector)
     */
    @Nonnull
    public static Double3 min(@Nonnull Double3... values) {
        double x = Double.MAX_VALUE;
        double y = Double.MAX_VALUE;
        double z = Double.MAX_VALUE;

        for (final Double3 value : values) {
            x = Math.min(x, value.x());
            y = Math.min(y, value.y());
            z = Math.min(z, value.z());
        }

        return new Double3(x, y, z);
    }

    /**
     * Between the provided vectors, this returns the minimum vector.
     *
     * @param values The values to compare
     * @return The minimum vector of the given vectors
     * @see civitas.celestis.math.vector.Vector#min(Vector)
     */
    @Nonnull
    public static Double4 min(@Nonnull Double4... values) {
        double w = Double.MAX_VALUE;
        double x = Double.MAX_VALUE;
        double y = Double.MAX_VALUE;
        double z = Double.MAX_VALUE;

        for (final Double4 value : values) {
            w = Math.min(w, value.w());
            x = Math.min(x, value.x());
            y = Math.min(y, value.y());
            z = Math.min(z, value.z());
        }

        return new Double4(w, x, y, z);
    }

    /**
     * Between the provided values, this returns the minimum value.
     *
     * @param v1 The first value to compare
     * @param v2 The second value to compare
     * @return The minimum of the two values
     */
    public static float min(float v1, float v2) {
        return Math.min(v1, v2);
    }

    /**
     * Between the provided values, this returns the minimum value.
     *
     * @param v1 The first value to compare
     * @param v2 The second value to compare
     * @param v3 The third value to compare
     * @return The minimum of the three values
     */
    public static float min(float v1, float v2, float v3) {
        if (v1 > v2) {
            return Math.min(v2, v3);
        } else {
            return Math.min(v1, v2);
        }
    }

    /**
     * Between the provided values, this returns the minimum value.
     *
     * @param values The values to compare
     * @return The minimum of the given values
     */
    public static float min(@Nonnull float... values) {
        float min = Float.MAX_VALUE;

        for (final float value : values) {
            min = Math.min(min, value);
        }

        return min;
    }

    /**
     * Between the provided vectors, this returns the minimum vector.
     *
     * @param values The values to compare
     * @return The minimum vector of the given vectors
     * @see civitas.celestis.math.vector.Vector#min(Vector)
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
     * Between the provided vectors, this returns the minimum vector.
     *
     * @param values The values to compare
     * @return The minimum vector of the given vectors
     * @see civitas.celestis.math.vector.Vector#min(Vector)
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
     * Between the provided vectors, this returns the minimum vector.
     *
     * @param values The values to compare
     * @return The minimum vector of the given vectors
     * @see civitas.celestis.math.vector.Vector#min(Vector)
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
     * Between the provided values, this returns the minimum value.
     *
     * @param v1 The first value to compare
     * @param v2 The second value to compare
     * @return The minimum of the two values
     */
    public static double max(double v1, double v2) {
        return Math.max(v1, v2);
    }

    /**
     * Between the provided values, this returns the minimum value.
     *
     * @param v1 The first value to compare
     * @param v2 The second value to compare
     * @param v3 The third value to compare
     * @return The minimum of the three values
     */
    public static double max(double v1, double v2, double v3) {
        if (v1 > v2) {
            return Math.max(v1, v3);
        } else {
            return Math.max(v2, v3);
        }
    }

    /**
     * Between the provided values, this returns the minimum value.
     *
     * @param values The values to compare
     * @return The minimum of the given values
     */
    public static double max(@Nonnull double... values) {
        double max = -Double.MAX_VALUE;

        for (final double value : values) {
            max = Math.max(max, value);
        }

        return max;
    }

    /**
     * Between the provided vectors, this returns the minimum vector.
     *
     * @param values The values to compare
     * @return The minimum vector of the given vectors
     * @see civitas.celestis.math.vector.Vector#max(Vector)
     */
    @Nonnull
    public static Double2 max(@Nonnull Double2... values) {
        double x = -Double.MAX_VALUE;
        double y = -Double.MAX_VALUE;

        for (final Double2 value : values) {
            x = Math.max(x, value.x());
            y = Math.max(y, value.y());
        }

        return new Double2(x, y);
    }

    /**
     * Between the provided vectors, this returns the minimum vector.
     *
     * @param values The values to compare
     * @return The minimum vector of the given vectors
     * @see civitas.celestis.math.vector.Vector#max(Vector)
     */
    @Nonnull
    public static Double3 max(@Nonnull Double3... values) {
        double x = -Double.MAX_VALUE;
        double y = -Double.MAX_VALUE;
        double z = -Double.MAX_VALUE;

        for (final Double3 value : values) {
            x = Math.max(x, value.x());
            y = Math.max(y, value.y());
            z = Math.max(z, value.z());
        }

        return new Double3(x, y, z);
    }

    /**
     * Between the provided vectors, this returns the minimum vector.
     *
     * @param values The values to compare
     * @return The minimum vector of the given vectors
     * @see civitas.celestis.math.vector.Vector#max(Vector)
     */
    @Nonnull
    public static Double4 max(@Nonnull Double4... values) {
        double w = -Double.MAX_VALUE;
        double x = -Double.MAX_VALUE;
        double y = -Double.MAX_VALUE;
        double z = -Double.MAX_VALUE;

        for (final Double4 value : values) {
            w = Math.max(w, value.w());
            x = Math.max(x, value.x());
            y = Math.max(y, value.y());
            z = Math.max(z, value.z());
        }

        return new Double4(w, x, y, z);
    }

    /**
     * Between the provided values, this returns the minimum value.
     *
     * @param v1 The first value to compare
     * @param v2 The second value to compare
     * @return The minimum of the two values
     */
    public static float max(float v1, float v2) {
        return Math.max(v1, v2);
    }

    /**
     * Between the provided values, this returns the minimum value.
     *
     * @param v1 The first value to compare
     * @param v2 The second value to compare
     * @param v3 The third value to compare
     * @return The minimum of the three values
     */
    public static double max(float v1, float v2, double v3) {
        if (v1 > v2) {
            return Math.max(v1, v3);
        } else {
            return Math.max(v2, v3);
        }
    }

    /**
     * Between the provided values, this returns the minimum value.
     *
     * @param values The values to compare
     * @return The minimum of the given values
     */
    public static float max(@Nonnull float... values) {
        float max = -Float.MAX_VALUE;

        for (final float value : values) {
            max = Math.max(max, value);
        }

        return max;
    }

    /**
     * Between the provided vectors, this returns the minimum vector.
     *
     * @param values The values to compare
     * @return The minimum vector of the given vectors
     * @see civitas.celestis.math.vector.Vector#max(Vector)
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
     * Between the provided vectors, this returns the minimum vector.
     *
     * @param values The values to compare
     * @return The minimum vector of the given vectors
     * @see civitas.celestis.math.vector.Vector#max(Vector)
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
     * Between the provided vectors, this returns the minimum vector.
     *
     * @param values The values to compare
     * @return The minimum vector of the given vectors
     * @see civitas.celestis.math.vector.Vector#max(Vector)
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

    //
    // Validation
    //

    /**
     * Explicitly denotes that the provided value must be within the boundaries of {@code min} and {@code max}.
     * If the value is not within the bounds, an {@link IllegalArgumentException} will be thrown
     *
     * @param value The value to validate
     * @param min   The minimum allowed value
     * @param max   The maximum allowed value
     * @return The value provided as the first parameter
     * @throws IllegalArgumentException When the value is outside the specified range
     */
    public static double requireRange(double value, double min, double max) {
        if (!isInRange(value, min, max)) {
            throw new IllegalArgumentException("This field requires the value to be within the range of " + min + "-" + max + ".");
        }

        return value;
    }

    /**
     * Explicitly denotes that the provided value must be within the boundaries of {@code min} and {@code max}.
     * If the value is not within the bounds, an {@link IllegalArgumentException} will be thrown
     *
     * @param value The value to validate
     * @param min   The minimum allowed value
     * @param max   The maximum allowed value
     * @return The value provided as the first parameter
     * @throws IllegalArgumentException When the value is outside the specified range
     */
    public static float requireRange(float value, float min, float max) {
        if (!isInRange(value, min, max)) {
            throw new IllegalArgumentException("This field requires the value to be within the range of " + min + "-" + max + ".");
        }

        return value;
    }

    /**
     * Explicitly denotes that the provided value must be within the boundaries of {@code min} and {@code max}.
     * If the value is not within the bounds, an {@link IllegalArgumentException} will be thrown
     *
     * @param value The value to validate
     * @param min   The minimum allowed value
     * @param max   The maximum allowed value
     * @return The value provided as the first parameter
     * @throws IllegalArgumentException When the value is outside the specified range
     */
    public static long requireRange(long value, long min, long max) {
        if (!isInRange(value, min, max)) {
            throw new IllegalArgumentException("This field requires the value to be within the range of " + min + "-" + max + ".");
        }

        return value;
    }

    /**
     * Explicitly denotes that the provided value must be within the boundaries of {@code min} and {@code max}.
     * If the value is not within the bounds, an {@link IllegalArgumentException} will be thrown
     *
     * @param value The value to validate
     * @param min   The minimum allowed value
     * @param max   The maximum allowed value
     * @return The value provided as the first parameter
     * @throws IllegalArgumentException When the value is outside the specified range
     */
    public static int requireRange(int value, long min, int max) {
        if (!isInRange(value, min, max)) {
            throw new IllegalArgumentException("This field requires the value to be within the range of " + min + "-" + max + ".");
        }

        return value;
    }

    /**
     * Explicitly denotes that the provided value must be within the boundaries of {@code min} and {@code max}.
     * If the value is not within the bounds, an {@link IllegalArgumentException} will be thrown
     *
     * @param value The value to validate
     * @param min   The minimum allowed value
     * @param max   The maximum allowed value
     * @return The value provided as the first parameter
     * @throws IllegalArgumentException When the value is outside the specified range
     */
    public static short requireRange(short value, short min, short max) {
        if (!isInRange(value, min, max)) {
            throw new IllegalArgumentException("This field requires the value to be within the range of " + min + "-" + max + ".");
        }

        return value;
    }

    /**
     * Explicitly denotes that the provided value must be within the boundaries of {@code min} and {@code max}.
     * If the value is not within the bounds, an {@link IllegalArgumentException} will be thrown
     *
     * @param value The value to validate
     * @param min   The minimum allowed value
     * @param max   The maximum allowed value
     * @return The value provided as the first parameter
     * @throws IllegalArgumentException When the value is outside the specified range
     */
    public static byte requireRange(byte value, byte min, byte max) {
        if (!isInRange(value, min, max)) {
            throw new IllegalArgumentException("This field requires the value to be within the range of " + min + "-" + max + ".");
        }

        return value;
    }

    /**
     * Checks if the provided value is within the boundaries of {@code min} and {@code max}.
     *
     * @param value The value to validate
     * @param min   The minimum allowed value
     * @param max   The maximum allowed value
     * @return {@code true} if the value within the specified range
     */
    public static boolean isInRange(double value, double min, double max) {
        return value >= min && value <= max;
    }

    /**
     * Checks if the provided value is within the boundaries of {@code min} and {@code max}.
     *
     * @param value The value to validate
     * @param min   The minimum allowed value
     * @param max   The maximum allowed value
     * @return {@code true} if the value within the specified range
     */
    public static boolean isInRange(float value, float min, float max) {
        return value >= min && value <= max;
    }

    /**
     * Checks if the provided value is within the boundaries of {@code min} and {@code max}.
     *
     * @param value The value to validate
     * @param min   The minimum allowed value
     * @param max   The maximum allowed value
     * @return {@code true} if the value within the specified range
     */
    public static boolean isInRange(long value, long min, long max) {
        return value >= min && value <= max;
    }

    /**
     * Checks if the provided value is within the boundaries of {@code min} and {@code max}.
     *
     * @param value The value to validate
     * @param min   The minimum allowed value
     * @param max   The maximum allowed value
     * @return {@code true} if the value within the specified range
     */
    public static boolean isInRange(int value, int min, int max) {
        return value >= min && value <= max;
    }

    /**
     * Checks if the provided value is within the boundaries of {@code min} and {@code max}.
     *
     * @param value The value to validate
     * @param min   The minimum allowed value
     * @param max   The maximum allowed value
     * @return {@code true} if the value within the specified range
     */
    public static boolean isInRange(short value, short min, short max) {
        return value >= min && value <= max;
    }

    /**
     * Checks if the provided value is within the boundaries of {@code min} and {@code max}.
     *
     * @param value The value to validate
     * @param min   The minimum allowed value
     * @param max   The maximum allowed value
     * @return {@code true} if the value within the specified range
     */
    public static boolean isInRange(byte value, byte min, byte max) {
        return value >= min && value <= max;
    }

    /**
     * Checks if the provided value is within the boundaries of {@code min} and {@code max}.
     *
     * @param value The value to validate
     * @param min   The minimum allowed value
     * @param max   The maximum allowed value
     * @return {@code true} if the value within the specified range
     */
    public static boolean isInRange(@Nonnull Double2 value, @Nonnull Double2 min, @Nonnull Double2 max) {
        return isInRange(value.x(), min.x(), max.x()) &&
                isInRange(value.y(), min.y(), max.y());
    }

    /**
     * Checks if the provided value is within the boundaries of {@code min} and {@code max}.
     *
     * @param value The value to validate
     * @param min   The minimum allowed value
     * @param max   The maximum allowed value
     * @return {@code true} if the value within the specified range
     */
    public static boolean isInRange(@Nonnull Double3 value, @Nonnull Double3 min, @Nonnull Double3 max) {
        return isInRange(value.x(), min.x(), max.x()) &&
                isInRange(value.y(), min.y(), max.y()) &&
                isInRange(value.z(), min.z(), max.z());
    }

    /**
     * Checks if the provided value is within the boundaries of {@code min} and {@code max}.
     *
     * @param value The value to validate
     * @param min   The minimum allowed value
     * @param max   The maximum allowed value
     * @return {@code true} if the value within the specified range
     */
    public static boolean isInRange(@Nonnull Double4 value, @Nonnull Double4 min, @Nonnull Double4 max) {
        return isInRange(value.w(), min.w(), max.w()) &&
                isInRange(value.x(), min.x(), max.x()) &&
                isInRange(value.y(), min.y(), max.y()) &&
                isInRange(value.z(), min.z(), max.z());
    }

    /**
     * Checks if the provided value is within the boundaries of {@code min} and {@code max}.
     *
     * @param value The value to validate
     * @param min   The minimum allowed value
     * @param max   The maximum allowed value
     * @return {@code true} if the value within the specified range
     */
    public static boolean isInRange(@Nonnull Float2 value, @Nonnull Float2 min, @Nonnull Float2 max) {
        return isInRange(value.x(), min.x(), max.x()) &&
                isInRange(value.y(), min.y(), max.y());
    }

    /**
     * Checks if the provided value is within the boundaries of {@code min} and {@code max}.
     *
     * @param value The value to validate
     * @param min   The minimum allowed value
     * @param max   The maximum allowed value
     * @return {@code true} if the value within the specified range
     */
    public static boolean isInRange(@Nonnull Float3 value, @Nonnull Float3 min, @Nonnull Float3 max) {
        return isInRange(value.x(), min.x(), max.x()) &&
                isInRange(value.y(), min.y(), max.y()) &&
                isInRange(value.z(), min.z(), max.z());
    }

    /**
     * Checks if the provided value is within the boundaries of {@code min} and {@code max}.
     *
     * @param value The value to validate
     * @param min   The minimum allowed value
     * @param max   The maximum allowed value
     * @return {@code true} if the value within the specified range
     */
    public static boolean isInRange(@Nonnull Float4 value, @Nonnull Double4 min, @Nonnull Float4 max) {
        return isInRange(value.w(), min.w(), max.w()) &&
                isInRange(value.x(), min.x(), max.x()) &&
                isInRange(value.y(), min.y(), max.y()) &&
                isInRange(value.z(), min.z(), max.z());
    }

    /**
     * Checks if the provided value is within the boundaries of {@code min} and {@code max}.
     *
     * @param value The value to validate
     * @param min   The minimum allowed value
     * @param max   The maximum allowed value
     * @return {@code true} if the value within the specified range
     */
    public static boolean isInRange(@Nonnull Long2 value, @Nonnull Long2 min, @Nonnull Long2 max) {
        return isInRange(value.x(), min.x(), max.x()) &&
                isInRange(value.y(), min.y(), max.y());
    }

    /**
     * Checks if the provided value is within the boundaries of {@code min} and {@code max}.
     *
     * @param value The value to validate
     * @param min   The minimum allowed value
     * @param max   The maximum allowed value
     * @return {@code true} if the value within the specified range
     */
    public static boolean isInRange(@Nonnull Long3 value, @Nonnull Long3 min, @Nonnull Long3 max) {
        return isInRange(value.x(), min.x(), max.x()) &&
                isInRange(value.y(), min.y(), max.y()) &&
                isInRange(value.z(), min.z(), max.z());
    }

    /**
     * Checks if the provided value is within the boundaries of {@code min} and {@code max}.
     *
     * @param value The value to validate
     * @param min   The minimum allowed value
     * @param max   The maximum allowed value
     * @return {@code true} if the value within the specified range
     */
    public static boolean isInRange(@Nonnull Long4 value, @Nonnull Long4 min, @Nonnull Long4 max) {
        return isInRange(value.w(), min.w(), max.w()) &&
                isInRange(value.x(), min.x(), max.x()) &&
                isInRange(value.y(), min.y(), max.y()) &&
                isInRange(value.z(), min.z(), max.z());
    }

    /**
     * Checks if the provided value is within the boundaries of {@code min} and {@code max}.
     *
     * @param value The value to validate
     * @param min   The minimum allowed value
     * @param max   The maximum allowed value
     * @return {@code true} if the value within the specified range
     */
    public static boolean isInRange(@Nonnull Int2 value, @Nonnull Int2 min, @Nonnull Int2 max) {
        return isInRange(value.x(), min.x(), max.x()) &&
                isInRange(value.y(), min.y(), max.y());
    }

    /**
     * Checks if the provided value is within the boundaries of {@code min} and {@code max}.
     *
     * @param value The value to validate
     * @param min   The minimum allowed value
     * @param max   The maximum allowed value
     * @return {@code true} if the value within the specified range
     */
    public static boolean isInRange(@Nonnull Int3 value, @Nonnull Int3 min, @Nonnull Int3 max) {
        return isInRange(value.x(), min.x(), max.x()) &&
                isInRange(value.y(), min.y(), max.y()) &&
                isInRange(value.z(), min.z(), max.z());
    }

    /**
     * Checks if the provided value is within the boundaries of {@code min} and {@code max}.
     *
     * @param value The value to validate
     * @param min   The minimum allowed value
     * @param max   The maximum allowed value
     * @return {@code true} if the value within the specified range
     */
    public static boolean isInRange(@Nonnull Int4 value, @Nonnull Int4 min, @Nonnull Int4 max) {
        return isInRange(value.w(), min.w(), max.w()) &&
                isInRange(value.x(), min.x(), max.x()) &&
                isInRange(value.y(), min.y(), max.y()) &&
                isInRange(value.z(), min.z(), max.z());
    }

    //
    // Factorial
    //


    /**
     * Calculates the factorial of the provided input {@code n}.
     * Note that this requires a non-negative input.
     *
     * @param n The number to get the factorial of
     * @return The factorial of {@code n}
     */
    public static double factorial(double n) {
        // Estimate factorial using the Gamma function
        return gamma(n + 1);
    }

    /**
     * Calculates the factorial of the provided input {@code n}.
     * Note that this requires a non-negative input.
     *
     * @param n The number to get the factorial of
     * @return The factorial of {@code n}
     */
    public static long factorial(long n) {
        // Use factorial table if possible
        if (requireRange(n, 0, Long.MAX_VALUE) < FACTORIALS.length) {
            return FACTORIALS[(int) n];
        }

        // Numbers above the threshold will result in overflows
        if (n > FACTORIAL_CALCULATION_THRESHOLD) {
            throw new ArithmeticException("Given input will trigger an integer overflow. Use factorial(double) instead.");
        }

        // This fallback algorithm will not be reached if the default settings are kept intact

        // Calculate factorial through recursion
        return n * factorial(n - 1);
    }

    /**
     * Calculates the factorial of the provided input {@code n}.
     * Note that this requires a non-negative input.
     *
     * @param n The number to get the factorial of
     * @return The factorial of {@code n}
     */
    public static long factorial(int n) {
        // Use factorial table if possible
        if (requireRange(n, 0, Long.MAX_VALUE) < FACTORIALS.length) {
            return FACTORIALS[n];
        }

        // Delegate to factorial(long)
        return factorial((long) n);
    }

    // Factorial table
    private static final long[] FACTORIALS = {
            1,
            1,
            2,
            6,
            24,
            120,
            720,
            5040,
            40320,
            362880,
            3628800,
            39916800,
            479001600,
            6227020800L,
            87178291200L,
            1307674368000L,
            20922789888000L,
            355687428096000L,
            6402373705728000L,
            121645100408832000L,
            2432902008176640000L
    };

    // The maximum input of the factorial function this class will attempt to calculate
    private static final int FACTORIAL_CALCULATION_THRESHOLD = FACTORIALS.length - 1;

    // Lanczos parameters
    private static final double LANCZOS_G = 7;
    private static final double[] LANCZOS_COEFFICIENTS = {
            0.99999999999980993,
            676.5203681218851,
            -1259.1392167224028,
            771.32342877765313,
            -176.61502916214059,
            12.507343278686905,
            -0.13857109526572012,
            9.9843695780195716e-6,
            1.5056327351493116e-7
    };

    /**
     * Returns the gamma of the given input {@code n}.
     *
     * @param n The value to get the gamma of
     * @return The gamma function value of {@code n}
     */
    public static double gamma(double n) {
        // Copy value for manipulation
        double x = n;

        if (x <= 0.5) {
            return Math.PI / (Math.sin(Math.PI * x) * gamma(1 - x));
        }

        x -= 1;
        double result = LANCZOS_COEFFICIENTS[0];
        for (int i = 1; i < LANCZOS_COEFFICIENTS.length; i++) {
            result += LANCZOS_COEFFICIENTS[i] / (x + i);
        }

        double t = x + LANCZOS_G + 0.5;
        return Math.sqrt(2 * Math.PI) * Math.pow(t, x + 0.5) * Math.exp(-t) * result;
    }
}
