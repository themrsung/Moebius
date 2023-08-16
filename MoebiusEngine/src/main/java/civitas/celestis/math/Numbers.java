package civitas.celestis.math;

import jakarta.annotation.Nonnull;

public class Numbers {
    public static final double EPSILON = 1e-6;

    public static boolean equals(@Nonnull Number n1, @Nonnull Number n2) {
        return n1.doubleValue() == n2.doubleValue();
    }

    public static double clamp(double value, double min, double max) {
        return Math.min(Math.max(value, min), max);
    }

    public static float clamp(float value, float min, float max) {
        return Math.min(Math.max(value, min), max);
    }

    public static long clamp(long value, long min, long max) {
        return Math.min(Math.max(value, min), max);
    }

    public static int clamp(int value, int min, int max) {
        return Math.min(Math.max(value, min), max);
    }

    public static short clamp(short value, short min, short max) {
        if (value < min) return min;
        if (value > max) return max;
        return value;
    }

    public static byte clamp(byte value, byte min, byte max) {
        if (value < min) return min;
        if (value > max) return max;
        return value;
    }
}
