package civitas.celestis.math.fraction;

import civitas.celestis.math.Numbers;
import civitas.celestis.math.Sign;
import civitas.celestis.math.real.RealNumber;
import civitas.celestis.math.vector.Vector;
import civitas.celestis.math.vector.Vector2;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;

/**
 * A number which can accurately represent recursion.
 * Non-finite values are not supported as the numerator or denominator.
 * <p>
 * Fractions are useful for accurately handling the division of two numbers.
 * Unlike other implementations within this project, division by fractions will not
 * immediately throw an {@link ArithmeticException}. Instead, the pure form of the
 * numerator and denominator will both be stored as-is.
 * </p>
 * <p>
 * This is an example on how to perform division using a fraction.<br>
 * <code>
 * // 1 on 3 division<br>
 * final Fraction result = new Fraction(1, 3);<br><br>
 * // You can either keep this as-is, or convert it to a double<br>
 * final double value = result.doubleValue();
 * </code>
 * </p>
 */
public class Fraction extends Vector2 implements Comparable<Fraction> {
    //
    // Constants
    //

    /**
     * The value for zero.
     */
    public static final Fraction ZERO = new Fraction(0);

    /**
     * The value equivalent to {@link Double#NaN}.
     */
    public static final Fraction NaN = new Fraction(0, 0);

    /**
     * The value equivalent to {@link Double#POSITIVE_INFINITY}.
     */
    public static final Fraction POSITIVE_INFINITY = new Fraction(1, 0);

    /**
     * The value equivalent to {@link Double#NEGATIVE_INFINITY}.
     */
    public static final Fraction NEGATIVE_INFINITY = new Fraction(-1, 0);

    /**
     * The minimum possible positive value a fraction can have without being zero.
     */
    public static final Fraction MAX_VALUE = new Fraction(Double.MAX_VALUE, Double.MIN_VALUE);

    /**
     * The maximum possible positive value a fraction can have without being infinite.
     */

    public static final Fraction MIN_VALUE = new Fraction(Double.MIN_VALUE, Double.MAX_VALUE);

    /**
     * The inverse of {@link Math#PI}.
     */
    public static final Fraction INVERSE_PI = new Fraction(1, Math.PI);

    /**
     * The inverse of {@link Math#E}.
     */
    public static final Fraction INVERSE_E = new Fraction(1, Math.E);

    /**
     * {@code 1.0 / 10.0}
     */
    public static final Fraction TENTH = new Fraction(1, 10);

    /**
     * {@code 1.0 / 9.0}
     */
    public static final Fraction NINTH = new Fraction(1, 9);

    /**
     * {@code 1.0 / 8.0}
     */
    public static final Fraction EIGHTH = new Fraction(1, 8);

    /**
     * {@code 1.0 / 7.0}
     */
    public static final Fraction SEVENTH = new Fraction(1, 7);

    /**
     * {@code 1.0 / 6.0}
     */
    public static final Fraction SIXTH = new Fraction(1, 6);

    /**
     * {@code 1.0 / 5.0}
     */
    public static final Fraction FIFTH = new Fraction(1, 5);

    /**
     * {@code 1.0 / 4.0}
     */
    public static final Fraction FOURTH = new Fraction(1, 4);

    /**
     * {@code 1.0 / 3.0}
     */
    public static final Fraction THIRD = new Fraction(1, 3);

    /**
     * {@code 1.0 / 2.0}
     */
    public static final Fraction HALF = new Fraction(1, 2);

    /**
     * {@code 1.0 / 1.0}
     */
    public static final Fraction ONE = new Fraction(1);

    /**
     * {@code 2.0 / 1.0}
     */
    public static final Fraction TWO = new Fraction(2);

    /**
     * {@code 3.0 / 1.0}
     */
    public static final Fraction THREE = new Fraction(3);

    /**
     * {@code 4.0 / 1.0}
     */
    public static final Fraction FOUR = new Fraction(4);

    /**
     * {@code 5.0 / 1.0}
     */
    public static final Fraction FIVE = new Fraction(5);

    /**
     * {@code 6.0 / 1.0}
     */
    public static final Fraction SIX = new Fraction(6);

    /**
     * {@code 7.0 / 1.0}
     */
    public static final Fraction SEVEN = new Fraction(7);

    /**
     * {@code 8.0 / 1.0}
     */
    public static final Fraction EIGHT = new Fraction(8);

    /**
     * {@code 9.0 / 1.0}
     */
    public static final Fraction NINE = new Fraction(9);

    /**
     * {@code 10.0 / 1.0}
     */
    public static final Fraction TEN = new Fraction(10);

    //
    // Constructors
    //

    /**
     * Creates a new fraction.
     *
     * @param numerator   The numerator of this fraction
     * @param denominator The denominator of this fraction
     */
    public Fraction(double numerator, double denominator) {
        super(numerator, denominator);
    }

    /**
     * Creates a new fraction.
     *
     * @param value The value to represent
     */
    public Fraction(double value) {
        super(value, 1);
    }

    /**
     * Creates a new fraction.
     *
     * @param values An array where element {@code 0} is the numerator, and {@code 1} is the denominator
     */
    public Fraction(@Nonnull double[] values) {
        super(values);
    }

    /**
     * Creates a new fraction.
     *
     * @param n The number of which to get the value of
     */
    public Fraction(@Nonnull RealNumber n) {
        this(n.divide(Double.MAX_VALUE).doubleValue(), 1 / Double.MAX_VALUE);
    }

    /**
     * Creates a new fraction.
     *
     * @param other The vector of which to copy values from
     */
    public Fraction(@Nonnull Vector other) {
        super(other);
    }

    /**
     * Creates a new fraction.
     *
     * @param other The vector of which to copy values from
     */
    public Fraction(@Nonnull Vector2 other) {
        super(other);
    }

    /**
     * Creates a new fraction.
     *
     * @param str The string representation of this fraction
     */
    public Fraction(@Nonnull String str) {
        super(parseFraction(str));
    }

    //
    // Getters
    //

    /**
     * Returns the numerator of this fraction.
     *
     * @return The numerator of this fraction
     */
    public final double numerator() {
        return x;
    }

    /**
     * Returns the denominator of this fraction.
     *
     * @return The denominator of this fraction
     */
    public final double denominator() {
        return y;
    }

    /**
     * Returns the sign of this fraction.
     *
     * @return The sign of this fraction
     */
    @Nonnull
    public final Sign sign() {
        final Sign n = Numbers.sign(x);
        final Sign d = Numbers.sign(y);

        return n.multiply(d);
    }

    //
    // Representation
    //

    /**
     * Checks if this fraction is finite. (the denominator is non-zero)
     *
     * @return {@code true} if the denominator is not zero
     */
    public final boolean isFinite() {
        return y != 0;
    }

    /**
     * Checks if this fraction has a rational decimal representation.
     *
     * @return {@code true} if this fraction can be represented as a decimal without loss of precision
     */
    public final boolean isRational() {
        if (y == 0) return false;

        int dividend = (int) x;
        final int divisor = (int) y;

        // Keep track of remainders
        final Set<Integer> remainders = new HashSet<>();

        while (dividend != 0) {
            if (remainders.contains(dividend)) {
                return false; // Repeating pattern detected
            }
            remainders.add(dividend);

            dividend = (dividend % divisor) * 10; // Multiply by 10 for decimal division
        }

        return true; // Decimal terminates
    }

    /**
     * Returns the decimal value of this fraction.
     * This uses the math context {@link MathContext#UNLIMITED},
     * and may result in throwing an {@link ArithmeticException}.
     *
     * @return The {@link BigDecimal} value of this fraction
     * @throws ArithmeticException When this value does not have an accurate decimal representation
     */
    @Nonnull
    public final BigDecimal decimalValue() throws ArithmeticException {
        return BigDecimal.valueOf(x).divide(BigDecimal.valueOf(y), MathContext.UNLIMITED);
    }

    /**
     * Returns the decimal value of this fraction.
     *
     * @param digits The number of digits at which to forcefully terminate the calculation.
     * @return The {@link BigDecimal} value of this fraction
     */
    @Nonnull
    public final BigDecimal decimalValue(int digits) {
        return decimalValue(new MathContext(digits, RoundingMode.HALF_UP));
    }

    /**
     * Returns the decimal value of this fraction.
     *
     * @param context The mathematical context to use when calculating the value
     * @return The {@link BigDecimal} value of this fraction
     */
    @Nonnull
    public final BigDecimal decimalValue(@Nonnull MathContext context) {
        return BigDecimal.valueOf(x).divide(BigDecimal.valueOf(y), context);
    }

    /**
     * Returns the real number value of this fraction.
     *
     * @return The {@link RealNumber} value of this fraction
     */
    @Nonnull
    public final RealNumber realValue() {
        final RealNumber n = new RealNumber(x);
        final RealNumber d = new RealNumber(y);

        return n.divide(d);
    }

    /**
     * Returns the {@code double} value of this fraction.
     *
     * @return The {@code double} value of this fraction
     */
    public final double doubleValue() {
        return x / y;
    }

    /**
     * Returns the {@code float} value of this fraction.
     *
     * @return The {@code float} value of this fraction
     */
    public final float floatValue() {
        return (float) (x / y);
    }

    //
    // Arithmetic
    //

    /**
     * Adds a scalar to this fraction.
     *
     * @param s Scalar to add to this fraction
     * @return The resulting fraction
     */
    @Nonnull
    @Override
    public Fraction add(double s) {
        return new Fraction(x + (s / y), y);
    }

    /**
     * Subtracts a scalar from this fraction.
     *
     * @param s Scalar to subtract from this fraction
     * @return The resulting fraction
     */
    @Nonnull
    @Override
    public Fraction subtract(double s) {
        return new Fraction(x - (s / y), y);
    }

    /**
     * Multiplies this fraction by a scalar.
     *
     * @param s Scalar to multiply this fraction by
     * @return The resulting fraction
     */
    @Nonnull
    @Override
    public Fraction multiply(double s) {
        return new Fraction(x * s, y);
    }

    /**
     * Divides this fraction by a scalar.
     *
     * @param s Scalar to divide this fraction by
     * @return The resulting fraction
     */
    @Nonnull
    @Override
    public Fraction divide(double s) {
        return new Fraction(x, y * s);
    }

    /**
     * Adds another fraction to this fraction.
     *
     * @param f The fraction to add to this fraction
     * @return The resulting fraction
     * @throws ArithmeticException When at least one of the fraction's denominator is zero
     */
    @Nonnull
    public Fraction add(@Nonnull Fraction f) throws ArithmeticException {
        final double lcm = Numbers.lcm(y, f.y);

        final double n1 = x * (lcm / y);
        final double n2 = f.x * (lcm / f.y);

        return new Fraction(n1 + n2, lcm);
    }

    /**
     * Subtracts another fraction from this fraction.
     *
     * @param f The fraction to subtract from this fraction
     * @return The resulting fraction
     * @throws ArithmeticException When at least one of the fraction's denominator is zero
     */
    @Nonnull
    public Fraction subtract(@Nonnull Fraction f) throws ArithmeticException {
        final double lcm = Numbers.lcm(y, f.y);

        final double n1 = x * (lcm / y);
        final double n2 = f.x * (lcm / f.y);

        return new Fraction(n1 - n2, lcm);
    }

    /**
     * Multiplies this fraction by another fraction.
     *
     * @param f The fraction to multiply this fraction with
     * @return The resulting fraction
     */
    @Nonnull
    public Fraction multiply(@Nonnull Fraction f) {
        return new Fraction(x * f.x, y * f.y);
    }

    /**
     * Divides this fraction by another fraction.
     *
     * @param f The fraction to divide this fraction by
     * @return The resulting fraction
     */
    @Nonnull
    public Fraction divide(@Nonnull Fraction f) {
        return new Fraction(x / f.x, y / f.y);
    }

    /**
     * Scales this fraction by multiplying the given scalar to both the numerator and denominator.
     *
     * @param s The scale factor to apply
     * @return The scaled fraction
     */
    @Nonnull
    public Fraction scale(double s) {
        return new Fraction(super.multiply(s));
    }

    /**
     * Returns the {@code e}th power of this fraction.
     *
     * @param e The exponent to raise to
     * @return The {@code e}th power of this fraction
     */
    @Nonnull
    public Fraction pow(double e) {
        return new Fraction(Math.pow(x, e), Math.pow(y, e));
    }

    /**
     * Returns the estimated square root of this fraction.
     *
     * @return The estimate of the square root of this fraction
     */
    @Nonnull
    public Fraction sqrt() {
        // Cannot invoke doubleValue() as this fraction could be non-finite
        return new Fraction(Math.sqrt(x), Math.sqrt(y));
    }

    /**
     * Returns the sin of this fraction.
     *
     * @return The sin of this fraction
     */
    @Nonnull
    public Fraction sin() {
        return new Fraction(Math.sin(x), Math.sin(y));
    }

    /**
     * Returns the cosine of this fraction.
     *
     * @return The cosine of this fraction
     */
    @Nonnull
    public Fraction cos() {
        return new Fraction(Math.cos(x), Math.cos(y));
    }

    /**
     * Returns the tangent of this fraction.
     *
     * @return The tangent of this fraction
     */
    @Nonnull
    public Fraction tan() {
        return new Fraction(Math.tan(x), Math.tan(y));
    }

    /**
     * Returns the arc sine of this fraction.
     *
     * @return The arc sine of this fraction
     */
    @Nonnull
    public Fraction asin() {
        return new Fraction(Math.asin(x), Math.asin(y));
    }

    /**
     * Returns the arc cosine of this fraction.
     *
     * @return The arc cosine of this fraction
     */
    @Nonnull
    public Fraction acos() {
        return new Fraction(Math.acos(x), Math.acos(y));
    }

    /**
     * Returns the arc tangent of this fraction.
     *
     * @return The arc tangent of this fraction
     */
    @Nonnull
    public Fraction atan() {
        return new Fraction(Math.atan(x), Math.atan(y));
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
    @Override
    public Fraction negate() {
        return new Fraction(-x, y);
    }

    /**
     * Returns the inverse of this fraction.
     *
     * @return The inverse of this fraction
     */
    @Nonnull
    public Fraction inverse() {
        return new Fraction(y, x);
    }

    /**
     * Normalizes this fraction to have a denominator of {@code 1}.
     *
     * @return The normalized fraction
     * @throws ArithmeticException When normalization is impossible (the denominator is {@code 0})
     */
    @Nonnull
    @Override
    public Fraction normalize() throws ArithmeticException {
        return new Fraction(super.divide(y));
    }

    /**
     * Calculates the reciprocal fraction of this fraction
     *
     * @return The unit fraction representation of this fraction
     * @throws ArithmeticException When the numerator is zero
     */
    @Nonnull
    public Fraction reciprocal() throws ArithmeticException {
        return new Fraction(super.divide(x));
    }

    //
    // Equality
    //

    /**
     * Checks for equality with the given object.
     *
     * @param obj The object to compare to
     * @return {@code true} if the object is a fraction, and it represents the same number as this fraction
     * or the object is a {@link Number}, and its value is equal to the value this fraction represents
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null) return false;

        // Check for fractions
        if (obj instanceof Fraction f) {
            // Fractions are exactly equal
            if (x == f.x && y == f.y) return true;

            // Check for different notations of the same value

            // Check for non-finite values and zero
            final Sign s1 = sign();
            final Sign s2 = f.sign();

            switch (s1) {
                case NOT_A_NUMBER -> {
                    if (s2 == Sign.NOT_A_NUMBER) return true;
                }

                case POSITIVE_INFINITY -> {
                    if (s2 == Sign.POSITIVE_INFINITY) return true;
                }

                case NEGATIVE_INFINITY -> {
                    if (s2 == Sign.NEGATIVE_INFINITY) return true;
                }

                case ZERO -> {
                    if (s2 == Sign.ZERO) return true;
                }
            }

            // Compare double representation while accounting for floating point imprecision
            return Numbers.equals(doubleValue(), f.doubleValue());
        }

        // Check for real numbers
        if (obj instanceof RealNumber n) {
            return doubleValue() == n.doubleValue();
        }

        // Check for numbers
        if (obj instanceof Number n) {
            // Check for the value of the number
            return n.doubleValue() == doubleValue();
        }

        return false;
    }

    /**
     * Checks for equality with a {@link RealNumber}.
     *
     * @param n The number to compare to
     * @return {@code true} if the values are equal
     */
    public boolean equals(@Nonnull RealNumber n) {
        return doubleValue() == n.doubleValue();
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

    /**
     * Checks for loose equality between this fraction and the given object.
     * This will return {@code true} if either {@link Vector2#equals(Object)}
     * or {@link Fraction#equals(Object)} return {@code true}.
     *
     * @param obj The object to compare to
     * @return {@code true} if the object is equal by {@link Vector} conventions or by {@link Fraction} conventions
     */
    public final boolean looseEquals(@Nullable Object obj) {
        if (super.equals(obj)) return true;
        return equals(obj);
    }

    /**
     * Check for strict equality between this fraction and another.
     * This will only return {@code true} if both the numerator and denominator are exactly equal.
     *
     * @param f The fraction to compare to
     * @return {@code true} if both the numerator and denominator are exactly equal
     */
    public final boolean strictEquals(@Nonnull Fraction f) {
        return x == f.x && y == f.y;
    }

    //
    // Comparison
    //

    /**
     * Compares this fraction to the given fraction.
     * The assumption for special values are as follows.<br>
     * {@code NOT_A_NUMBER > POSITIVE_INFINITY},<br> {@code NOT_A_NUMBER > NEGATIVE_INFINITY},<br>
     * {@code POSITIVE_INFINITY > NEGATIVE_INFINITY}
     *
     * @param f The fraction to compare to
     * @return {@code 0} if the values are equal, {@code 1} if this fraction is larger, {@code -1} if this fraction is smaller
     * @see Double#compare(double, double)
     */
    @Override
    public int compareTo(@Nonnull Fraction f) {
        return Double.compare(doubleValue(), f.doubleValue());
    }

    /**
     * Compares this fraction to a {@link RealNumber}.
     *
     * @param n The number to compare to
     * @return {@code 0} if the values are equal, {@code 1} if this fraction is larger, {@code -1} otherwise
     */
    public int compareTo(@Nonnull RealNumber n) {
        return Double.compare(doubleValue(), n.doubleValue());
    }

    /**
     * Compares this fraction to a {@link Number}.
     *
     * @param n The number to compare to
     * @return {@code 0} if the values are equal, {@code 1} if this fraction is larger, {@code -1} otherwise
     */
    public int compareTo(@Nonnull Number n) {
        return Double.compare(doubleValue(), n.doubleValue());
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
    public static Fraction parseFraction(@Nonnull String input) throws NumberFormatException {
        if (!input.contains("/")) {
            throw new NumberFormatException("The provided string does not represent a fraction.");
        }

        final String cleanInput = input.replaceAll(" ", "");
        final String[] valueStrings = cleanInput.split("/");

        if (valueStrings.length != 2) {
            throw new NumberFormatException("The provided string does not represent a fraction.");
        }

        final double numerator = Double.parseDouble(valueStrings[0]);
        final double denominator = Double.parseDouble(valueStrings[1]);

        return new Fraction(numerator, denominator);
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
