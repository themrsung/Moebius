package civitas.celestis.math;

import jakarta.annotation.Nonnull;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Math utilities which use {@link BigDecimal}.
 * <p>
 * All runtime calculations which involve rounding use the mathematical context
 * {@link DecimalMath#RUNTIME_CONTEXT}, while all constants precomputed at compile time
 * use the {@link DecimalMath#COMPILE_CONTEXT}.
 * </p>
 * <p>
 * Both mathematical contexts are determined by the constant {@link DecimalMath#RUNTIME_PRECISION}.
 * A margin of two more decimal places is added to this precision for compile time calculations.
 * </p>
 */
public class DecimalMath {
    //
    // Constants
    //

    /**
     * The number of decimal places to calculate.
     */
    public static final int RUNTIME_PRECISION = 32;

    /**
     * The mathematical context used at runtime.
     */
    public static final MathContext RUNTIME_CONTEXT = new MathContext(RUNTIME_PRECISION, RoundingMode.HALF_UP);

    /**
     * The mathematical context used at compile time.
     */
    public static final MathContext COMPILE_CONTEXT = new MathContext(RUNTIME_PRECISION + 2, RoundingMode.HALF_UP);

    /**
     * The value of pi.
     */
    public static final BigDecimal PI;

    /**
     * The value of tau. (2 * pi)
     */
    public static final BigDecimal TAU;

    /**
     * The value of Euler's constant.
     */
    public static final BigDecimal E;

    /**
     * The square root of two.
     */
    public static final BigDecimal SQRT_2;

    /**
     * The value of {@code 1 / 3}.
     */
    public static final BigDecimal THIRD;

    /**
     * The value of {@code 1 / 6}.
     */
    public static final BigDecimal SIXTH;

    /**
     * The value of {@code 1 / 7}.
     */
    public static final BigDecimal SEVENTH;

    /**
     * The value of {@code 1 / 8}.
     */
    public static final BigDecimal EIGHTH;

    /**
     * The value of {@code 1 / 9}.
     */
    public static final BigDecimal NINTH;

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
    public static BigDecimal clamp(@Nonnull BigDecimal value, @Nonnull BigDecimal min, @Nonnull BigDecimal max) {
        if (value.compareTo(min) < 0) return min;
        if (value.compareTo(max) > 0) return max;

        return value;
    }

    //
    // Static Operations
    //

    /*
     * Variable assignments
     */
    static {
        // The raw unscaled value of PI.
        final BigDecimal rawPi = new BigDecimal("3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679");

        PI = rawPi.setScale(COMPILE_CONTEXT.getPrecision(), COMPILE_CONTEXT.getRoundingMode());
        TAU = rawPi.multiply(BigDecimal.TWO).setScale(COMPILE_CONTEXT.getPrecision(), COMPILE_CONTEXT.getRoundingMode());

        E = new BigDecimal("2.7182818284590452353602874713526624977572470936999595749669676277240766303535475945713821785251664274", COMPILE_CONTEXT);

        SQRT_2 = BigDecimal.TWO.sqrt(COMPILE_CONTEXT);

        THIRD = BigDecimal.ONE.divide(BigDecimal.valueOf(3), COMPILE_CONTEXT);
        SIXTH = BigDecimal.ONE.divide(BigDecimal.valueOf(6), COMPILE_CONTEXT);
        SEVENTH = BigDecimal.ONE.divide(BigDecimal.valueOf(7), COMPILE_CONTEXT);
        EIGHTH = BigDecimal.ONE.divide(BigDecimal.valueOf(8), COMPILE_CONTEXT);
        NINTH = BigDecimal.ONE.divide(BigDecimal.valueOf(9), COMPILE_CONTEXT);
    }
}
