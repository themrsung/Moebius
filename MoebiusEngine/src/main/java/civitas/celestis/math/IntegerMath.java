package civitas.celestis.math;

import jakarta.annotation.Nonnull;

import java.math.BigInteger;

/**
 * Math utilities which use {@link BigInteger}.
 */
public final class IntegerMath {
    //
    // Clamping
    //

    /**
     * Clamps the provided value to respect the boundaries of {@code min} and {@code max}.
     *
     * @param value The value to clamp
     * @param min   The minimum allowed value
     * @param max   The maximum allowed value
     * @return The clamped value
     */
    @Nonnull
    public static BigInteger clamp(@Nonnull BigInteger value, @Nonnull BigInteger min, @Nonnull BigInteger max) {
        if (value.compareTo(min) < 0) return min;
        if (value.compareTo(max) > 0) return max;

        return value;
    }
}
