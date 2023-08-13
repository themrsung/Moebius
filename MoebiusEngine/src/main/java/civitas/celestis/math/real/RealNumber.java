package civitas.celestis.math.real;

import civitas.celestis.math.Numbers;
import civitas.celestis.math.Sign;
import civitas.celestis.math.fraction.Fraction;
import civitas.celestis.math.vector.Vector;
import civitas.celestis.math.vector.Vector2;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * A scientific notation of a number using two {@code double}s.
 * The format is "{@code 2^x * y}".
 * <p>
 * The minimum possible value of a real number is<br>"{@code Math.pow(Double.MIN_VALUE, 2) * Double.MIN_VALUE}",<br>
 * and the maximum possible value of a real number is<br>"{@code Math.pow(Double.MAX_VALUE, 2) * Double.MAX_VALUE}".<br>
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
    public static final RealNumber MIN_VALUE = new RealNumber(Double.MIN_VALUE, Double.MIN_VALUE);

    /**
     * The maximum possible positive value a real number can safely represent.
     */
    public static final RealNumber MAX_VALUE = new RealNumber(Double.MAX_VALUE, Double.MAX_VALUE);

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

    @Nonnull
    @Override
    public RealNumber add(double s) {
        return add(new RealNumber(s));
    }

    @Nonnull
    @Override
    public RealNumber subtract(double s) {
        return subtract(new RealNumber(s));
    }

    @Nonnull
    @Override
    public RealNumber multiply(double s) {
        return multiply(new RealNumber(s));
    }

    @Nonnull
    @Override
    public RealNumber divide(double s) throws ArithmeticException {
        return divide(new RealNumber(s));
    }

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

    @Nonnull
    public RealNumber divide(@Nonnull RealNumber n) {
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
    public RealNumber pow2() {
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
    public RealNumber pow2(double n) {
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
        if (!input.startsWith("RealNumber{")) {
            throw new NumberFormatException("The provided string does not represent a RealNumber.");
        }

        final String cleanInput = input.replaceAll("RealNumber\\{|}", "");
        final String[] valueStrings = cleanInput.split(", ");
        final double[] values = {Double.NaN, Double.NaN};

        for (final String valueString : valueStrings) {
            final String[] split = valueString.split("=");
            values[switch (split[0]) {
                case "e" -> 0;
                case "m" -> 1;
                default -> throw new NumberFormatException("The provided string does not represent a RealNumber.");
            }] = Double.parseDouble(split[1]);
        }

        return new RealNumber(values);
    }

    /**
     * {@inheritDoc}
     *
     * @return The string representation of this vector
     */
    @Override
    @Nonnull
    public String toString() {
        return "RealNumber{" +
                "e=" + x +
                ", m=" + y +
                '}';
    }
}
