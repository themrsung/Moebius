package civitas.celestis.math.decimal;

import civitas.celestis.math.Sign;
import jakarta.annotation.Nonnull;

import java.math.BigDecimal;

/**
 * A utility class used to handle {@link BigDecimal} numbers.
 */
public final class Decimals {
    //
    // Miscellaneous
    //

    /**
     * Returns the sign of the provided value.
     *
     * @param value The value of which to get the sign of
     * @return The sign of the value
     */
    @Nonnull
    public static Sign sign(@Nonnull Decimal value) {
        final int s = value.signum();
        return s == 0 ? Sign.ZERO : (s > 0 ? Sign.POSITIVE : Sign.NEGATIVE);
    }
}
