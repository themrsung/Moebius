package civitas.celestis.math.fraction;

import civitas.celestis.math.Numbers;
import civitas.celestis.math.Numeric;
import civitas.celestis.math.Sign;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * A fractional number which can understand recursion.
 * <p>
 * Although non-finite values are not allowed as the individual numerator or
 * denominator components, certain combinations of finite pairs can create a non-finite fraction.
 * Examples of this include {@link Fraction#POSITIVE_INFINITY} and {@link Fraction#NEGATIVE_INFINITY}.
 * </p>
 * <p>
 * While numbers of higher precision compared to a {@code double} can be represented using a fraction,
 * those values are not useful due to the fact that comparison uses the {@code double} representation for efficiency.
 * A custom implementation which utilizes those higher precisions is feasible by extending this class.
 * </p>
 */
public class Fraction extends Number implements Numeric, Comparable<Number> {
    //
    // Constants
    //

    /**
     * The fraction {@code 0.0 / 1.0}.
     */
    public static final Fraction ZERO = new Fraction(0);

    /**
     * The fraction {@code 0.0 / 0.0}.
     */
    public static final Fraction NaN = new Fraction(0, 0);

    /**
     * The fraction {@code 1.0 / 0.0}.
     */
    public static final Fraction POSITIVE_INFINITY = new Fraction(1, 0);

    /**
     * The fraction {@code -1.0 / 0.0}.
     */
    public static final Fraction NEGATIVE_INFINITY = new Fraction(-1, 0);

    /**
     * The maximum representable value of a fraction.
     * Any values above this will equal to infinity.
     */
    public static final Fraction MAX_VALUE = new Fraction(Double.MAX_VALUE);

    /**
     * The minimum representable value of a fraction.
     * Any values below this will equal to zero.
     */
    public static final Fraction MIN_VALUE = new Fraction(Double.MIN_VALUE);

    /**
     * The number ONE. ({@code 1 / 1})
     */
    public static final Fraction ONE = new Fraction(1);

    /**
     * The number TWO. ({@code 2 / 1})
     */
    public static final Fraction TWO = new Fraction(2);

    /**
     * The number THREE. ({@code 3 / 1})
     */
    public static final Fraction THREE = new Fraction(3);

    /**
     * The number FOUR. ({@code 4 / 1})
     */
    public static final Fraction FOUR = new Fraction(4);

    /**
     * The number FIVE. ({@code 5 / 1})
     */
    public static final Fraction FIVE = new Fraction(5);

    /**
     * The number SIX. ({@code 6 / 1})
     */
    public static final Fraction SIX = new Fraction(6);

    /**
     * The number SEVEN. ({@code 7 / 1})
     */
    public static final Fraction SEVEN = new Fraction(7);

    /**
     * The number EIGHT. ({@code 8 / 1})
     */
    public static final Fraction EIGHT = new Fraction(8);

    /**
     * The number NINE. ({@code 9 / 1})
     */
    public static final Fraction NINE = new Fraction(9);

    /**
     * The number TEN. ({@code 10 / 1})
     */
    public static final Fraction TEN = new Fraction(10);

    /**
     * The number SIXTEEN. ({@code 16 / 1})
     */
    public static final Fraction SIXTEEN = new Fraction(16);

    /**
     * The number THIRTY_TWO. ({@code 32 / 1})
     */
    public static final Fraction THIRTY_TWO = new Fraction(32);

    /**
     * The number SIXTY_FOUR. ({@code 64 / 1})
     */
    public static final Fraction SIXTY_FOUR = new Fraction(64);

    /**
     * The number HUNDRED. ({@code 100 / 1})
     */
    public static final Fraction HUNDRED = new Fraction(100);

    /**
     * The number HALF. ({@code 1 / 2})
     */
    public static final Fraction HALF = new Fraction(1, 2);

    /**
     * The number THIRD. ({@code 1 / 3})
     */
    public static final Fraction THIRD = new Fraction(1, 3);

    /**
     * The number FOURTH. ({@code 1 / 4})
     */
    public static final Fraction FOURTH = new Fraction(1, 4);

    /**
     * The number FIFTH. ({@code 1 / 5})
     */
    public static final Fraction FIFTH = new Fraction(1, 5);

    /**
     * The number SIXTH. ({@code 1 / 6})
     */
    public static final Fraction SIXTH = new Fraction(1, 6);

    /**
     * The number SEVENTH. ({@code 1 / 7})
     */
    public static final Fraction SEVENTH = new Fraction(1, 7);

    /**
     * The number EIGHTH. ({@code 1 / 8})
     */
    public static final Fraction EIGHTH = new Fraction(1, 8);

    /**
     * The number NINTH. ({@code 1 / 9})
     */
    public static final Fraction NINTH = new Fraction(1, 9);

    /**
     * The number TENTH. ({@code 1 / 10})
     */
    public static final Fraction TENTH = new Fraction(1, 10);

    /**
     * The number SIXTEENTH. ({@code 1 / 16})
     */
    public static final Fraction SIXTEENTH = new Fraction(1, 16);

    /**
     * The number THIRTY_SECOND. ({@code 1 / 32})
     */
    public static final Fraction THIRTY_SECOND = new Fraction(1, 32);

    /**
     * The number SIXTY_FOURTH. ({@code 1 / 64})
     */
    public static final Fraction SIXTY_FOURTH = new Fraction(1, 64);

    /**
     * The number HUNDREDTH. ({@code 1 / 100})
     */
    public static final Fraction HUNDREDTH = new Fraction(1, 100);

    /**
     * The mathematical context where division terminates after {@code 10} decimal places.
     */
    public static final MathContext PRECISION_10 = new MathContext(10, RoundingMode.HALF_UP);

    /**
     * The mathematical context where division terminates after {@code 25} decimal places.
     */
    public static final MathContext PRECISION_25 = new MathContext(25, RoundingMode.HALF_UP);

    /**
     * The mathematical context where division terminates after {@code 50} decimal places.
     */
    public static final MathContext PRECISION_50 = new MathContext(50, RoundingMode.HALF_UP);

    /**
     * The mathematical context where division terminates after {@code 75} decimal places.
     */
    public static final MathContext PRECISION_75 = new MathContext(75, RoundingMode.HALF_UP);

    /**
     * The mathematical context where division terminates after {@code 100} decimal places.
     */
    public static final MathContext PRECISION_100 = new MathContext(100, RoundingMode.HALF_UP);

    /**
     * The mathematical context where division terminates after {@code 200} decimal places.
     */
    public static final MathContext PRECISION_200 = new MathContext(200, RoundingMode.HALF_UP);

    /**
     * The mathematical context where division terminates after {@code 300} decimal places.
     */
    public static final MathContext PRECISION_300 = new MathContext(300, RoundingMode.HALF_UP);

    /**
     * The mathematical context where division terminates after {@code 400} decimal places.
     */
    public static final MathContext PRECISION_400 = new MathContext(400, RoundingMode.HALF_UP);

    /**
     * The mathematical context where division terminates after {@code 500} decimal places.
     */
    public static final MathContext PRECISION_500 = new MathContext(500, RoundingMode.HALF_UP);

    //
    // Constructors
    //

    /**
     * Creates a new fraction.
     *
     * @param value The value this fraction should represent
     */
    public Fraction(double value) {
        if (Numbers.requireFinite(value) >= 1 || value == 0) {
            this.n = value;
            this.d = 1;
        } else {
            this.n = 1;
            this.d = 1 / value;
        }
    }

    /**
     * Creates a new fraction.
     *
     * @param n The numerator of this fraction
     * @param d The denominator of this fraction
     */
    public Fraction(double n, double d) {
        this.n = Numbers.requireFinite(n);
        this.d = Numbers.requireFinite(d);
    }

    /**
     * Creates a new fraction.
     *
     * @param other The fraction from which to copy values from
     */
    public Fraction(@Nonnull Fraction other) {
        this.n = other.n;
        this.d = other.d;
    }

    /**
     * Creates a new fraction.
     *
     * @param str The string representation of this fraction
     * @throws NumberFormatException When the format is invalid
     */
    public Fraction(@Nonnull String str) {
        this(parseFraction(str));
    }

    //
    // Variables
    //

    private final double n, d;

    //
    // Properties
    //

    /**
     * Returns the numerator of this fraction.
     *
     * @return The numerator of this fraction
     */
    public final double numerator() {
        return n;
    }

    /**
     * Returns the denominator of this fraction.
     *
     * @return The denominator of this fraction
     */
    public final double denominator() {
        return d;
    }

    /**
     * Returns the effective sign of this fraction.
     *
     * @return The sign of the {@code double} value of this fraction
     * @see Fraction#doubleValue()
     */
    @Nonnull
    public Sign sign() {
        return Numbers.sign(doubleValue());
    }

    /**
     * Checks if this number can be represented as a rational decimal without loss of precision.
     * In other words, this checks if the decimal representation of this fraction using the
     * {@link MathContext#UNLIMITED} context can terminate safely.
     *
     * @return {@code true} if this number can be safely represented as a rational
     */
    public boolean rational() {
        try {
            decimalValue(MathContext.UNLIMITED);
            return true;
        } catch (final ArithmeticException e) {
            return false;
        }
    }

    /**
     * Checks if the decimal representation of this fraction has is a repeating decimal.
     * @return {@code true} if this is a repeating decimal fraction
     */
    public boolean repeating() {
        final BigDecimal decimal = decimalValue(new MathContext(100, RoundingMode.HALF_EVEN)); // Adjust precision
        final String decimalString = decimal.toString();

        final int dotIndex = decimalString.indexOf('.');
        if (dotIndex < 0) {
            // The decimal is a whole number, no repetition
            return false;
        }

        final String fractionalPart = decimalString.substring(dotIndex + 1);
        final int length = fractionalPart.length();

        // Check for repeated patterns in the fractional part
        for (int len = 1; len <= length / 2; len++) {
            boolean repeated = true;
            for (int i = len; i < length; i++) {
                if (fractionalPart.charAt(i) != fractionalPart.charAt(i - len)) {
                    repeated = false;
                    break;
                }
            }
            if (repeated) {
                return true;
            }
        }

        return false;
    }

    //
    // Transformation
    //

    /**
     * Normalizes this fraction into the format of "{@code n / 1}".
     *
     * @return The normalized fraction
     * @throws ArithmeticException When the denominator is zero
     */
    @Nonnull
    public Fraction normalize() throws ArithmeticException {
        if (d == 0) throw new ArithmeticException("Cannot divide by zero.");

        return new Fraction(n / d, 1);
    }

    /**
     * Returns the reciprocal of this fraction in the format of "{@code 1 / d}".
     *
     * @return The reciprocal of this fraction
     * @throws ArithmeticException When the numerator is zero
     */
    @Nonnull
    public Fraction reciprocal() throws ArithmeticException {
        if (n == 0) throw new ArithmeticException("Cannot divide by zero.");

        return new Fraction(1, d / n);
    }

    /**
     * Rescales this fraction. Multiplies the given scalar {@code s} to both the
     * numerator and denominator of this fraction, then returns the result.
     *
     * @param s The scale to apply
     * @return The rescaled fraction
     */
    @Nonnull
    public Fraction rescale(double s) {
        return new Fraction(n * s, d * s);
    }

    /**
     * Simplifies this fraction by dividing by the greatest common denominator.
     *
     * @return The simplified fraction
     * @throws ArithmeticException When the GCD is zero
     */
    @Nonnull
    public Fraction simplify() throws ArithmeticException {
        final double gcd = Numbers.gcd(n, d);
        if (gcd == 0) {
            throw new ArithmeticException("Cannot simply this fraction.");
        }

        return new Fraction(n / gcd, d / gcd);
    }

    //
    // Arithmetic
    //

    /**
     * Multiplies this fraction by a scalar.
     *
     * @param s The scalar to multiply this fraction with
     * @return The resulting fraction
     */
    @Nonnull
    public Fraction multiply(double s) {
        return new Fraction(n * s, d);
    }

    /**
     * Divides this fraction by a scalar.
     *
     * @param s The scalar to divide this fraction by
     * @return The resulting fraction
     */
    @Nonnull
    public Fraction divide(double s) {
        return new Fraction(n, d * s);
    }

    /**
     * Adds another fraction to this fraction.
     *
     * @param f The fraction to add to this fraction
     * @return The resulting fraction
     * @throws ArithmeticException When the fractions are non-finite
     */
    @Nonnull
    public Fraction add(@Nonnull Fraction f) throws ArithmeticException {
        if (d == 0 || f.d == 0) {
            throw new ArithmeticException("Cannot perform arithmetic non-finite values.");
        }

        final double lcm = Numbers.lcm(d, f.d);

        final double n1 = n * (lcm / d);
        final double n2 = f.n * (lcm / f.d);

        return new Fraction(n1 + n2, lcm);
    }

    /**
     * Subtracts a fraction from this fraction.
     *
     * @param f The fraction to subtract from this fraction
     * @return The resulting fraction
     * @throws ArithmeticException When the fractions are non-finite
     */
    @Nonnull
    public Fraction subtract(@Nonnull Fraction f) throws ArithmeticException {
        if (d == 0 || f.d == 0) {
            throw new ArithmeticException("Cannot perform arithmetic non-finite values.");
        }

        final double lcm = Numbers.lcm(d, f.d);

        final double n1 = n * (lcm / d);
        final double n2 = f.n * (lcm / f.d);

        return new Fraction(n1 - n2, lcm);
    }

    /**
     * Multiplies this fraction by another fraction.
     *
     * @param f The fraction to multiply this fraction by
     * @return The resulting fraction
     * @throws ArithmeticException When the fractions are non-finite
     */
    @Nonnull
    public Fraction multiply(@Nonnull Fraction f) throws ArithmeticException {
        if (d == 0 || f.d == 0) {
            throw new ArithmeticException("Cannot perform arithmetic non-finite values.");
        }

        final double lcm = Numbers.lcm(d, f.d);

        final double n1 = n * (lcm / d);
        final double n2 = f.n * (lcm / f.d);

        return new Fraction(n1 * n2, lcm);
    }

    /**
     * Divides this fraction by another fraction.
     * By default, this delegates to {@link Fraction#multiply(Fraction)} by getting the inverse of {@code f}.
     *
     * @param f The fraction to divide this fraction by
     * @return The resulting fraction
     * @throws ArithmeticException When the fractions are non-finite or the denominator is zero
     */
    @Nonnull
    public Fraction divide(@Nonnull Fraction f) throws ArithmeticException {
        if (f.n == 0d) throw new ArithmeticException("Cannot divide by zero.");
        return multiply(f.inverse());
    }

    //
    // Utility
    //

    /**
     * Negates this fraction.
     *
     * @return The negation of this fraction
     */
    @Nonnull
    public Fraction negate() {
        return new Fraction(-n, d);
    }

    /**
     * Returns the inverse of this fraction.
     *
     * @return The inverse of this fraction
     */
    @Nonnull
    public Fraction inverse() {
        return new Fraction(d, n);
    }

    //
    // Conversion
    //

    /**
     * {@inheritDoc}
     *
     * @return The {@code int} value of this fraction
     */
    @Override
    public int intValue() {
        return (int) (n / d);
    }

    /**
     * {@inheritDoc}
     *
     * @return The {@code long} value of this fraction
     */
    @Override
    public long longValue() {
        return (long) (n / d);
    }

    /**
     * {@inheritDoc}
     *
     * @return The {@code float} value of this fraction
     */
    @Override
    public float floatValue() {
        return (float) (n / d);
    }

    /**
     * {@inheritDoc}
     *
     * @return The {@code double} value of this fraction
     */
    @Override
    public double doubleValue() {
        return n / d;
    }

    /**
     * Returns this number as a {@link BigDecimal}.
     *
     * @param context The mathematical context to use in division
     * @return The decimal representation of this fraction
     * @see MathContext
     * @see MathContext#DECIMAL32
     * @see MathContext#DECIMAL64
     * @see MathContext#DECIMAL128
     * @see MathContext#UNLIMITED
     * @see Fraction#PRECISION_10
     * @see Fraction#PRECISION_25
     * @see Fraction#PRECISION_50
     * @see Fraction#PRECISION_75
     * @see Fraction#PRECISION_100
     * @see Fraction#PRECISION_200
     * @see Fraction#PRECISION_300
     * @see Fraction#PRECISION_400
     * @see Fraction#PRECISION_500
     */
    @Nonnull
    public BigDecimal decimalValue(@Nonnull MathContext context) {
        return BigDecimal.valueOf(n).divide(BigDecimal.valueOf(d), context);
    }

    //
    // Comparison
    //

    /**
     * Compares this fraction to the provided number {@code n}.
     *
     * @param n The number to compare to
     * @return {@code 0} if the values are equal, {@code 1} if this number is larger, {@code -1} otherwise
     */
    @Override
    public int compareTo(@Nonnull Number n) {
        return Double.compare(doubleValue(), n.doubleValue());
    }

    /**
     * Checks for equality between this fraction and the provided object {@code obj}.
     *
     * @param obj The object to compare to
     * @return {@code true} if the object is an instance of {@link Number}, and the {@code double} values are equal
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Number num)) return false;
        return doubleValue() == num.doubleValue();
    }

    //
    // Serialization
    //

    /**
     * Deserializes a string into a fraction.
     *
     * @param input The string to parse
     * @return The parsed fraction
     * @throws NumberFormatException When the format is invalid
     */
    @Nonnull
    public static Fraction parseFraction(@Nonnull String input) throws NumberFormatException {
        final String[] split = input.trim().split("/");
        if (split.length != 2) {
            throw new NumberFormatException("The provided string does not represent a fraction.");
        }

        return new Fraction(Double.parseDouble(split[0]), Double.parseDouble(split[1]));
    }

    /**
     * Serializes this fraction into a string.
     *
     * @return The string representation of this fraction
     */
    @Override
    @Nonnull
    public String toString() {
        return n + "/" + d;
    }
}
