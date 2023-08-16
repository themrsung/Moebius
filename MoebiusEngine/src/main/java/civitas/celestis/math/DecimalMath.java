package civitas.celestis.math;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Math utilities which use {@link BigDecimal}.
 */
public class DecimalMath {
    //
    // Constants
    //

    /**
     * The mathematical context this class uses at runtime.
     */
    public static final MathContext RUNTIME_CONTEXT = new MathContext(32, RoundingMode.HALF_UP);

    /**
     * The value of pi.
     */
    public static final BigDecimal PI;

    /**
     * The value of Euler's constant.
     */
    public static final BigDecimal E;

    //
    // Static Operations
    //

    /*
     * Variables assignments
     */
    static {
        PI = new BigDecimal("3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679");
        E = new BigDecimal("2.7182818284590452353602874713526624977572470936999595749669676277240766303535475945713821785251664274");
    }
}
