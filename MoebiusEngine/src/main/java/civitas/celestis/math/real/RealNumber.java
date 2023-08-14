package civitas.celestis.math.real;

import civitas.celestis.math.Numbers;
import civitas.celestis.math.Sign;
import civitas.celestis.math.fraction.Fraction;
import civitas.celestis.math.vector.Vector;
import civitas.celestis.math.vector.Vector2;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * A scientific notation of a number using two {@code double}s.
 * The format is "{@code 2^x * y}".
 * <p>
 * The minimum possible value of a real number is<br>"{@code 4.9E-324e-1.7976931348623157E308}",<br>
 * and the maximum possible value of a real number is<br>"{@code 1.7976931348623157E308e1.7976931348623157E308}".<br>
 * </p>
 * <p>
 * A real number can be used to represent numbers beyond the limits of {@code double}.
 * </p>
 *
 * @see RealNumber#MIN_VALUE
 * @see RealNumber#MAX_VALUE
 */
public class RealNumber extends Vector2 implements Comparable<RealNumber> {
    //
    // Constants
    //

    /**
     * Absolute zero.
     */
    public static final RealNumber ZERO = new RealNumber(0);

    /**
     * The minimum possible positive value a real number can have without being zero.
     */
    public static final RealNumber MIN_VALUE = new RealNumber(-1.7976931348623157E308, 4.9E-324);

    /**
     * The minimum positive value which when added to a number, will change the value of that number.
     */
    public static final RealNumber MIN_INCREMENT = new RealNumber(Double.MIN_VALUE);

    /**
     * The {@link RealNumber} equivalent of {@link Double#MAX_VALUE}.
     */
    public static final RealNumber DOUBLE_MAX_VALUE = new RealNumber(Double.MAX_VALUE);

    /**
     * The maximum possible positive value a real number can safely represent.
     */
    public static final RealNumber MAX_VALUE = new RealNumber(1.7976931348623157E308, 1.7976931348623157E308);

    /**
     * The number {@code 1}.
     */
    public static final RealNumber ONE = new RealNumber(1);

    /**
     * The number {@code 2}.
     */
    public static final RealNumber TWO = new RealNumber(2);

    /**
     * The number {@code 3}.
     */
    public static final RealNumber THREE = new RealNumber(3);

    /**
     * The number {@code 4}.
     */
    public static final RealNumber FOUR = new RealNumber(4);

    /**
     * The number {@code 5}.
     */
    public static final RealNumber FIVE = new RealNumber(5);

    /**
     * The number {@code 6}.
     */
    public static final RealNumber SIX = new RealNumber(6);

    /**
     * The number {@code 7}.
     */
    public static final RealNumber SEVEN = new RealNumber(7);

    /**
     * The number {@code 8}.
     */
    public static final RealNumber EIGHT = new RealNumber(8);

    /**
     * The number {@code 9}.
     */
    public static final RealNumber NINE = new RealNumber(9);

    /**
     * The number {@code 10}.
     */
    public static final RealNumber TEN = new RealNumber(10);
    /**
     * A mathematical context object with 100 digits of precision.
     */
    public static final MathContext PRECISION_100 = new MathContext(100, RoundingMode.HALF_UP);

    /**
     * A mathematical context object with 500 digits of precision.
     */
    public static final MathContext PRECISION_500 = new MathContext(500, RoundingMode.HALF_UP);

    /**
     * A mathematical context object with 1,000 digits of precision.
     */
    public static final MathContext PRECISION_1000 = new MathContext(1000, RoundingMode.HALF_UP);

    //
    // Static Utilities
    //

    /**
     * Converts a base 10 notation to a base 2 number.
     * Note that this is limited to the range of {@code double}.
     *
     * @param exponent The exponent of the notation
     * @param mantissa The mantissa of the notation
     * @return The converted {@link RealNumber}
     */
    @Nonnull
    public static RealNumber fromBase10(double exponent, double mantissa) {
        return new RealNumber(Math.pow(10, exponent) * mantissa);
    }

    //
    // Constructors
    //

    /**
     * Creates a new number.
     *
     * @param value The value to contain
     */
    public RealNumber(double value) {
        super(Math.getExponent(value), value / Math.scalb(1.0, Math.getExponent(value)));
    }

    /**
     * Creates a new number.
     *
     * @param exp      The exponent of this number
     * @param mantissa The mantissa of this number
     */
    public RealNumber(double exp, double mantissa) {
        super(exp, mantissa);
    }

    /**
     * Creates a new real number.
     *
     * @param f The fraction of which to get the value from
     */
    public RealNumber(@Nonnull Fraction f) {
        this(f.doubleValue());
    }

    /**
     * Creates a new number.
     *
     * @param values An array where the element at index {@code 0} is the exponent,
     *               and the element at index {@code 1} is the mantissa
     */
    public RealNumber(@Nonnull double[] values) {
        super(values);
    }

    /**
     * Creates a new number.
     *
     * @param other The vector to copy values from
     */
    public RealNumber(@Nonnull Vector other) {
        super(other);
    }

    /**
     * Creates a new number.
     *
     * @param other The vector to copy values from
     */
    public RealNumber(@Nonnull Vector2 other) {
        super(other);
    }

    //
    // Getters
    //

    /**
     * Returns the exponent of this number.
     *
     * @return The exponent of this number
     */
    public final double exponent() {
        return x;
    }

    /**
     * Returns the mantissa of this number.
     *
     * @return The mantissa of this number
     */
    public final double mantissa() {
        return y;
    }

    /**
     * Returns the sign of this number.
     *
     * @return The sign of this number
     * @see Sign
     */
    @Nonnull
    public final Sign sign() {
        return Numbers.sign(x).multiply(Numbers.sign(y));
    }

    /**
     * Returns the {@code double} value of this number.
     *
     * @return The {@code double} representation of this number
     */
    public final double doubleValue() {
        return Math.pow(2, x) * y;
    }

    //
    // Arithmetic
    //

    /**
     * Adds a scalar to this number.
     *
     * @param s Scalar to add to this number
     * @return The resulting number
     */
    @Nonnull
    @Override
    public RealNumber add(double s) {
        return add(new RealNumber(s));
    }

    /**
     * Subtracts a scalar from this number.
     *
     * @param s Scalar to subtract from this number
     * @return The resulting number
     */
    @Nonnull
    @Override
    public RealNumber subtract(double s) {
        return subtract(new RealNumber(s));
    }

    /**
     * Multiplies this number by a scalar.
     *
     * @param s Scalar to multiply this number by
     * @return The resulting number
     */
    @Nonnull
    @Override
    public RealNumber multiply(double s) {
        return multiply(new RealNumber(s));
    }

    /**
     * Divides this number by a scalar.
     *
     * @param s Scalar to divide this number by
     * @return The resulting number
     * @throws ArithmeticException When the denominator {@code s} is zero
     */
    @Nonnull
    @Override
    public RealNumber divide(double s) throws ArithmeticException {
        return divide(new RealNumber(s));
    }

    /**
     * Adds a number to this number.
     *
     * @param n The number to add to this number
     * @return The resulting number
     */
    @Nonnull
    public RealNumber add(@Nonnull RealNumber n) {
        // Determine the larger exponent
        double maxExp = Math.max(x, n.x);

        // Scale both numbers to have the same exponent
        final double m1 = y * Math.pow(2, x - maxExp);
        final double m2 = n.y * Math.pow(2, n.x - maxExp);

        // Add the scaled mantissas
        final double m = m1 + m2;

        // Define the result
        double exponent = maxExp;
        double mantissa = m;

        // Normalize if necessary
        if (mantissa >= 2) {
            mantissa /= 2;
            exponent++;
        }

        // Return the calculated value
        return new RealNumber(exponent, mantissa);
    }

    /**
     * Subtracts a number from this number.
     *
     * @param n The number to subtract from this number
     * @return The resulting number
     */
    @Nonnull
    public RealNumber subtract(@Nonnull RealNumber n) {
        // Determine the larger exponent
        double maxExp = Math.max(x, n.x);

        // Scale both numbers to have the same exponent
        final double m1 = y * Math.pow(2, x - maxExp);
        final double m2 = n.y * Math.pow(2, n.x - maxExp);

        // Add the scaled mantissas
        final double m = m1 - m2;

        // Define the result
        double exponent = maxExp;
        double mantissa = m;

        // Normalize if necessary
        if (mantissa >= 2) {
            mantissa /= 2;
            exponent++;
        }

        // Return the calculated value
        return new RealNumber(exponent, mantissa);
    }

    /**
     * Multiplies this number by a number.
     *
     * @param n The number to multiply this number by
     * @return The resulting number
     */
    @Nonnull
    public RealNumber multiply(@Nonnull RealNumber n) {
        // Product of mantissas
        double mantissa = y * n.y;

        // Sum the exponents
        double exp = x + n.x;

        // Normalize if necessary
        if (mantissa >= 2) {
            mantissa /= 2;
            exp++;
        }

        // Return the calculated value
        return new RealNumber(exp, mantissa);
    }

    /**
     * Divides this number by a number.
     *
     * @param n The number to divide this number by
     * @return The resulting number
     * @throws ArithmeticException When the denominator {@code n} is zero
     */
    @Nonnull
    public RealNumber divide(@Nonnull RealNumber n) throws ArithmeticException {
        // Check for division by zero
        if (n.y == 0) {
            throw new ArithmeticException("Cannot divide by zero.");
        }

        // Divide the mantissas
        double mantissa = y / n.y;

        // Subtract the exponents
        double exp = x - n.x;

        // Normalize if necessary
        if (mantissa >= 2) {
            mantissa /= 2;
            exp++;
        }

        // Return the calculated value
        return new RealNumber(exp, mantissa);
    }

    /**
     * Doubles this number.
     *
     * @return {@code this * 2}
     */
    @Nonnull
    public RealNumber twoFold() {
        return new RealNumber(x + 1, y);
    }

    /**
     * Doubles this number {@code n} times.
     * This effectively multiplies this number by {@code 2^n}.
     *
     * @param n The number of times to double this number
     * @return {@code this * 2^n}
     */
    @Nonnull
    public RealNumber twoFold(double n) {
        return new RealNumber(x + n, y);
    }

    /**
     * Halves this number.
     *
     * @return {@code this  / 2}
     */
    @Nonnull
    public RealNumber halve() {
        return new RealNumber(x - 1, y);
    }

    /**
     * Halves this number {@code n} times.
     * This effectively divides this number by {@code 2^n}.
     *
     * @param n THe number of times to halve this number
     * @return {@code this / 2^n}
     */
    @Nonnull
    public RealNumber halve(double n) {
        return new RealNumber(x - n, y);
    }

    /**
     * Raises this number to the power of {@code 2}.
     * @return The squared number of this number
     */
    @Nonnull
    public RealNumber pow() {
        return multiply(this);
    }

    /**
     * Raises this number to the power of {@code exp}.
     * @param exp The exponent to raise to
     * @return The {@code exp}th power of {@code this}
     */
    @Nonnull
    public RealNumber pow(long exp) {
        RealNumber result = ONE;

        for (long i = 0; i < exp; i++) {
            result = result.multiply(this);
        }

        return result;
    }

    /**
     * Returns the exponential function of this number.
     * @return The exponential function of this number
     */
    @Nonnull
    public RealNumber exp() {
        return new RealNumber(x, Math.exp(y));
    }

    /**
     * Returns the square root of this number.
     * This is an estimation derived by manipulating the exponent of this number.
     *
     * @return The approximate square root of this number
     */
    @Nonnull
    public RealNumber sqrt() {
        RealNumber guess = new RealNumber(x / 2, y);

        // Three iterations of Newton's method to improve accuracy
        guess = (guess.add(this.divide(guess))).divide(2);
        guess = (guess.add(this.divide(guess))).divide(2);
        guess = (guess.add(this.divide(guess))).divide(2);

        return guess;
    }

    /**
     * Applies the {@link RealNumber#sqrt()} function {@code n} times.
     *
     * @param n The number of times to square root
     * @return The cumulative square root of this number
     */
    @Nonnull
    public RealNumber sqrt(long n) {
        RealNumber root = this;

        for (long i = 0; i < n; i++) {
            root = root.sqrt();
        }

        return root;
    }

    /**
     * Returns the logarithm of this number.
     * @return The logarithm of this number
     */
    @Nonnull
    public RealNumber log() {
        return new RealNumber(x, Math.log(y));
    }

    //
    // Clamping
    //

    /**
     * Returns the minimum number between this number and {@code n}.
     *
     * @param n The number to compare to
     * @return The smaller number
     */
    @Nonnull
    public RealNumber min(@Nonnull RealNumber n) {
        return compareTo(n) >= 0 ? this : n;
    }

    /**
     * Returns the maximum number between this number and {@code n}.
     *
     * @param n The number to compare to
     * @return The smaller number
     */
    @Nonnull
    public RealNumber max(@Nonnull RealNumber n) {
        return compareTo(n) <= 0 ? this : n;
    }

    /**
     * Clamps this number to respect the given boundaries.
     *
     * @param min The minimum allowed value
     * @param max The maximum allowed value
     * @return The clamped number
     */
    @Nonnull
    public RealNumber clamp(@Nonnull RealNumber min, @Nonnull RealNumber max) {
        if (compareTo(min) < 0) {
            return min;
        }

        if (compareTo(max) > 0) {
            return max;
        }

        return this;
    }

    //
    // Utility
    //

    /**
     * Negates this number.
     *
     * @return The negation of this number
     */
    @Nonnull
    @Override
    public RealNumber negate() {
        return new RealNumber(x, -y);
    }


    //
    // Comparison
    //

    /**
     * Compares this number to another number.
     *
     * @param n The number to compare to
     * @return {@code 0} if the numbers are equal, {@code 1} if this number is larger, {@code -1} otherwise
     */
    @Override
    public int compareTo(@Nonnull RealNumber n) {
        // Compare signs
        final Sign s1 = sign();
        final Sign s2 = n.sign();

        // Use Sign.compareTo(int) for non-finite values
        if (!s1.finite() || !s2.finite()) {
            return s1.compareTo(s2);
        }

        // Compare exponents
        if (x != n.x) {
            return Double.compare(x, n.x);
        }

        // Compare mantissas
        return Double.compare(y, n.y);
    }

    /**
     * Compares this number to a {@link Fraction}.
     *
     * @param f The fraction to compare to
     * @return {@code 0} if the numbers are equal, {@code 1} if this number is larger, {@code -1} otherwise
     */
    public int compareTo(@Nonnull Fraction f) {
        return Double.compare(doubleValue(), f.doubleValue());
    }

    /**
     * Compares this number to a {@link Number}.
     *
     * @param n The number to compare to
     * @return {@code 0} if the numbers are equal, {@code 1} if this number is larger, {@code -1} otherwise
     */
    public int compareTo(@Nonnull Number n) {
        return Double.compare(doubleValue(), n.doubleValue());
    }

    //
    // Equality
    //

    /**
     * Checks for equality between this number and the given object.
     *
     * @param obj The object to compare to
     * @return {@code true} if the object if a number, and the values are equal
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null) return false;

        // Check for real numbers
        if (obj instanceof RealNumber n) {
            return x == n.x && y == n.y;
        }

        // Check for fractions
        if (obj instanceof Fraction f) {
            return doubleValue() == f.doubleValue();
        }

        // Check for numbers
        if (obj instanceof Number n) {
            return doubleValue() == n.doubleValue();
        }

        return false;
    }

    /**
     * Checks for equality with a {@link Fraction}.
     *
     * @param f The fraction to compare to
     * @return {@code true} if the values are equal
     */
    public boolean equals(@Nonnull Fraction f) {
        return doubleValue() == f.doubleValue();
    }

    /**
     * Checks for equality with a {@link Number}.
     *
     * @param n The number to compare to
     * @return {@code true} if the values are equal
     */
    public boolean equals(@Nonnull Number n) {
        return doubleValue() == n.doubleValue();
    }

    //
    // Serialization
    //

    /**
     * Deserializes a string into a {@link Vector2}.
     *
     * @param input The input to deserialize
     * @return The parsed vector
     * @throws NumberFormatException    When the format is invalid
     * @throws IllegalArgumentException When at least one of the component scalars is non-finite
     */
    @Nonnull
    public static RealNumber parseNumber(@Nonnull String input) throws IllegalArgumentException {
        if (!input.contains("e")) {
            throw new NumberFormatException("The provided string does not represent a RealNumber.");
        }

        final String[] valueStrings = input.split("e");
        if (valueStrings.length != 2) {
            throw new NumberFormatException("The provided string does not represent a RealNumber.");
        }

        return new RealNumber(Double.parseDouble(valueStrings[1]), Double.parseDouble(valueStrings[0]));
    }

    /**
     * {@inheritDoc}
     *
     * @return The string representation of this number
     */
    @Override
    @Nonnull
    public String toString() {
        return y + "e" + x;
    }

    /**
     * Converts this number into a format "{@code 0.####...}".
     * This uses the math context {@link MathContext#DECIMAL128}.
     *
     * @return The human-readable string representation of this number
     */
    @Nonnull
    public String toReadableString() {return toReadableString(MathContext.DECIMAL128);}

    /**
     * Converts this number into a format "{@code 0.####...}".
     *
     * @param context The mathematical context to use when calculating the value
     * @return The human-readable string representation of this number
     * @see RealNumber#PRECISION_100
     * @see RealNumber#PRECISION_500
     * @see RealNumber#PRECISION_1000
     */
    @Nonnull
    public String toReadableString(@Nonnull MathContext context) {
        try {
            final BigDecimal mantissa = BigDecimal.valueOf(y);
            final BigDecimal exponentiation = BigDecimal.TWO.pow((int) x, context);

            final BigDecimal result = mantissa.multiply(exponentiation);

            return result.toPlainString();
        } catch (final ArithmeticException e) {
            return "OUT_OF_DISPLAYABLE_RANGE";
        }
    }

}
