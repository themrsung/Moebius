package civitas.celestis.math.fraction;

import civitas.celestis.graphics.color.RGBColor;
import civitas.celestis.math.real.RealNumber;
import civitas.celestis.math.real.RealVector2;
import jakarta.annotation.Nonnull;

/**
 * A number which can accurately represent recursion.
 *
 * <p>
 * Fractions are useful for accurately handling the division of two real numbers.
 * Unlike other implementations within this project, division by fractions will not
 * immediately throw an {@link ArithmeticException}. Instead, the pure form of the
 * numerator and denominator will both be stored as-is.
 * </p>
 */
public class RealFraction extends RealVector2 implements Comparable<RealFraction> {
    //
    // Constants
    //

    public static final RealFraction ZERO = new RealFraction(RealNumber.ZERO);
    public static final RealFraction NaN = new RealFraction(RealNumber.ZERO, RealNumber.ZERO);
    public static final RealFraction POSITIVE_INFINITY = new RealFraction(RealNumber.ONE, RealNumber.ZERO);
    public static final RealFraction NEGATIVE_INFINITY = new RealFraction(RealNumber.ONE.negate(), RealNumber.ZERO);
    public static final RealFraction MAX_VALUE = new RealFraction(RealNumber.MAX_VALUE, RealNumber.MIN_VALUE);
    public static final RealFraction MIN_VALUE = new RealFraction(RealNumber.MIN_VALUE, RealNumber.MAX_VALUE);

    //
    // Constructors
    //

    /**
     * Creates a new fraction.
     * @param value The value to represent
     */
    public RealFraction(@Nonnull RealNumber value) {
        super(value, RealNumber.ONE);
    }

    /**
     * Creates a new fraction.
     * @param numerator The numerator of this fraction
     * @param denominator The denominator of this fraction
     */
    public RealFraction(double numerator, double denominator) {
        super(numerator, denominator);
    }

    /**
     * Creates a new fraction.
     * @param numerator The numerator of this fraction
     * @param denominator The denominator of this fraction
     */
    public RealFraction(@Nonnull RealNumber numerator, @Nonnull RealNumber denominator) {
        super(numerator, denominator);
    }

    //
    // Getters
    //

    /**
     * Returns the numerator of this fraction.
     * @return The numerator of this fraction
     */
    @Nonnull
    public final RealNumber numerator() {
        return x;
    }

    /**
     * Returns the denominator of this fraction.
     * @return The denominator of this fraction
     */
    @Nonnull
    public final RealNumber denominator() {
        return y;
    }

    /**
     * Returns the real number value of this fraction.
     * @return The {@link RealNumber} representation of this fraction
     */
    @Nonnull
    public final RealNumber realValue() {
        return x.divide(y);
    }

    //
    // Comparison
    //

    /**
     * Compares this fraction to another fraction
     * @param f The fraction to compare to
     * @return {@code 0} if the values are equal, {@code 1} if this fraction's value is larger,
     * {@code -1} otherwise
     */
    @Override
    public int compareTo(@Nonnull RealFraction f) {
        return realValue().compareTo(f.realValue());
    }

    //
    // Serialization
    //

    /**
     * Deserializes a string into a fraction.
     *
     * @param input The input string to parse
     * @return The parsed string
     * @throws NumberFormatException When the format is invalid
     */
    @Nonnull
    public static RealFraction parseFraction(@Nonnull String input) throws NumberFormatException {
        if (!input.contains("/")) {
            throw new NumberFormatException("The provided string does not represent a fraction.");
        }

        final String cleanInput = input.replaceAll(" ", "");
        final String[] valueStrings = cleanInput.split("/");

        if (valueStrings.length != 2) {
            throw new NumberFormatException("The provided string does not represent a fraction.");
        }

        final RealNumber numerator = RealNumber.parseNumber(valueStrings[0]);
        final RealNumber denominator = RealNumber.parseNumber(valueStrings[1]);

        return new RealFraction(numerator, denominator);
    }

    /**
     * Serializes this fraction into a string.
     *
     * @return The string representation of this fraction
     */
    @Override
    @Nonnull
    public String toString() {
        return x + "/" + y;
    }
}
