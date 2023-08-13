package civitas.celestis.math.decimal;

import civitas.celestis.math.Numeric;
import jakarta.annotation.Nonnull;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * A decimal number class used in Moebius.
 * This implements the {@link Numeric} interface to add functionality.
 */
public class Decimal extends BigDecimal implements Numeric {
    //
    //
    //
    //
    //
    //
    //
    //           UNFINISHED
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    public static final Decimal ZERO = valueOf(0);

    @Nonnull
    public static Decimal valueOf(long v) {
        return new Decimal(Double.toString(v));
    }

    @Nonnull
    public static Decimal valueOf(double v) {
        return new Decimal(Double.toString(v));
    }

    public Decimal(@Nonnull String val) {
        super(val);
    }

    public Decimal(@Nonnull BigDecimal d) {
        super(d.toString());
    }

    @Override
    @Nonnull
    public String toString() {
        return super.toString();
    }

    @Override
    @Nonnull
    public Decimal divide(@Nonnull BigDecimal divisor, @Nonnull MathContext mc) {
        return new Decimal(super.divide(divisor, mc));
    }
}
