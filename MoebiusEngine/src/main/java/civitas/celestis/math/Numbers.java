package civitas.celestis.math;

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
     * Explicitly denotes that the provided value requires to be within the given boundaries.
     * @param value The value to validate
     * @param min The minimum allowed value
     * @param max The maximum allowed value
     * @return The value provided as the {@code value} parameter
     * @throws IllegalArgumentException When the value is out of range
     */
    public static double requireRange(double value, double min, double max) {
        if (value < min || value > max) {
            throw new IllegalArgumentException("The provided value required to be within the range of " + min + "-" + max + ".");
        }

        return value;
    }

    //
    // Range Check
    //

    /**
     * Checks if the provided value respects the given boundaries
     * @param value The value to check
     * @param min The minimum allowed value
     * @param max The maximum allowed value
     * @return {@code true} if the value is within range
     */
    public static boolean isInRange(double value, double min, double max) {
        return value >= min && value <= max;
    }

    //
    // Clamping
    //

    /**
     * Clamps a value to fit between the provided boundaries of {@code min} and {@code max}.
     * @param value The value to clamp
     * @param min The minimum allowed value
     * @param max The maximum allowed value
     * @return The clamped value
     */
    public static int clamp(int value, int min, int max) {
        return Math.min(Math.max(value, min), max);
    }

    /**
     * Clamps a value to fit between the provided boundaries of {@code min} and {@code max}.
     * @param value The value to clamp
     * @param min The minimum allowed value
     * @param max The maximum allowed value
     * @return The clamped value
     */
    public static long clamp(long value, long min, long max) {
        return Math.min(Math.max(value, min), max);
    }

    /**
     * Clamps a value to fit between the provided boundaries of {@code min} and {@code max}.
     * @param value The value to clamp
     * @param min The minimum allowed value
     * @param max The maximum allowed value
     * @return The clamped value
     */
    public static float clamp(float value, float min, float max) {
        return Math.min(Math.max(value, min), max);
    }

    /**
     * Clamps a value to fit between the provided boundaries of {@code min} and {@code max}.
     * @param value The value to clamp
     * @param min The minimum allowed value
     * @param max The maximum allowed value
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
}
