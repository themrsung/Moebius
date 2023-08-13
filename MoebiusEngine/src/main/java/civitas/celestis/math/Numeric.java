package civitas.celestis.math;

import civitas.celestis.graphics.color.DeepColor;
import civitas.celestis.graphics.color.RGBColor;
import civitas.celestis.graphics.color.RichColor;
import civitas.celestis.math.complex.Complex;
import civitas.celestis.math.complex.Quaternion;
import civitas.celestis.math.decimal.Decimal;
import civitas.celestis.math.fraction.Fraction;
import civitas.celestis.math.integer.IntMatrix;
import civitas.celestis.math.integer.IntVector;
import civitas.celestis.math.matrix.Matrix;
import civitas.celestis.math.natural.NaturalVector;
import civitas.celestis.math.real.RealNumber;
import civitas.celestis.math.vector.Vector;
import civitas.celestis.math.vertex.Vertex;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * A mathematical number.
 */
public interface Numeric {
    //
    // Factory
    //

    /**
     * Creates a new two-dimensional number.
     *
     * @param values The values to use to create the number
     * @return The constructed number
     */
    @Nonnull
    static Numeric of(@Nonnull double... values) {
        try {return Vector.of(values);} catch (final Exception ignored) {}
        try {return new Complex(values);} catch (final Exception ignored) {}
        try {return new Fraction(values);} catch (final Exception ignored) {}
        try {return new Vertex(values);} catch (final Exception ignored) {}
        try {return new DeepColor(values);} catch (final Exception ignored) {}
        try {return new Quaternion(values);} catch (final Exception ignored) {}
        try {return new RichColor(values);} catch (final Exception ignored) {}
        try {return new RealNumber(values);} catch (final Exception ignored) {}

        if (values.length > 0) {
            return Decimal.valueOf(values[0]);
        } else {
            return Decimal.ZERO;
        }
    }

    /**
     * Creates a new matrix.
     *
     * @param values The values to use
     * @return The constructed matrix
     */
    @Nonnull
    static Matrix of(@Nonnull double[][] values) {
        return new Matrix(values);
    }

    /**
     * Creates a new integer vector.
     *
     * @param values The values to use
     * @return The constructed vector
     */
    @Nonnull
    static Numeric of(@Nonnull int... values) {
        try {return IntVector.of(values);} catch (final Exception ignored) {}
        try {return NaturalVector.of(values);} catch (final Exception ignored) {}
        try {return new RGBColor(values);} catch (final Exception ignored) {}

        if (values.length > 0) {
            return Decimal.valueOf(values[0]);
        } else {
            return Decimal.ZERO;
        }
    }

    /**
     * Creates a new matrix.
     *
     * @param values The values to use
     * @return The constructed matrix
     */
    @Nonnull
    static IntMatrix of(@Nonnull int[][] values) {
        return new IntMatrix(values);
    }

    //
    // Parser
    //

    /**
     * Deserializes a string into a number.
     *
     * @param input The input string to parse
     * @return The parsed number
     * @throws NumberFormatException When the format is invalid
     */
    @Nonnull
    static Numeric parseNumber(@Nonnull String input) throws NumberFormatException {
        try {return Vector.parseVector(input);} catch (final Exception ignored) {}
        try {return IntVector.parseVector(input);} catch (final Exception ignored) {}
        try {return RGBColor.parseColor(input);} catch (final Exception ignored) {}
        try {return Complex.parseComplex(input);} catch (final Exception ignored) {}
        try {return Fraction.parseFraction(input);} catch (final Exception ignored) {}
        try {return DeepColor.parseColor(input);} catch (final Exception ignored) {}
        try {return Quaternion.parseQuaternion(input);} catch (final Exception ignored) {}
        try {return RichColor.parseColor(input);} catch (final Exception ignored) {}
        try {return RealNumber.parseNumber(input);} catch (final Exception ignored) {}
        try {return new Decimal(input);} catch (final Exception ignored) {}

        throw new NumberFormatException("The given string is not a Numeric.");
    }

    //
    // Comparison
    //

    /**
     * Attempts to compare two numbers as good as possible.
     * This uses the signed squares of each number's {@code double} representation for efficiency.
     * <p>
     * Even though this is faster than {@link Numeric#slowCompare(Numeric, Numeric)}, this is still relatively
     * slow compared to using {@link Numeric#equals(Object)} or {@link Numbers#equals(double, double)} (and its overloads).
     * </p>
     *
     * @param n1 The first number to compare
     * @param n2 The second number to compare
     * @return {@code 0} if the values are equal, {@code 1} if the first number's value is larger, {@code -1} otherwise
     * @throws UnsupportedOperationException When at least one of ths numbers does not have a single scalar representation
     * @see Numeric#doubleValue2(Numeric)
     */
    static int compare(@Nonnull Numeric n1, @Nonnull Numeric n2) throws UnsupportedOperationException {
        // Get first number's representation
        final double v1 = doubleValue2(n1);

        // Get second number's representation
        final double v2 = doubleValue2(n2);

        return Double.compare(v1, v2);
    }

    /**
     * Attempts to compare two numbers as good as possible.
     * This uses each number's {@code double} representation for accuracy.
     *
     * @param n1 The first number to compare
     * @param n2 The second number to compare
     * @return {@code 0} if the values are equal, {@code 1} if the first number's value is larger, {@code -1} otherwise
     * @throws UnsupportedOperationException When at least one of ths numbers does not have a single scalar representation
     * @see Numeric#doubleValue2(Numeric)
     */
    static int slowCompare(@Nonnull Numeric n1, @Nonnull Numeric n2) throws UnsupportedOperationException {
        // Get first number's representation
        final double v1 = doubleValue(n1);

        // Get second number's representation
        final double v2 = doubleValue(n2);

        return Double.compare(v1, v2);
    }

    /**
     * Returns the {@code double} representation of a number.
     *
     * @param n The number to represent
     * @return The {@code double} representation
     * @throws UnsupportedOperationException When the number cannot be represented by a single {@code double}
     */
    static double doubleValue(@Nonnull Numeric n) throws UnsupportedOperationException {
        // Special case for fractions
        if (n instanceof Fraction f) return f.doubleValue();

        // Magnitudes for vectors
        if (n instanceof Vector v) return v.magnitude();
        if (n instanceof IntVector v) return v.magnitude();

        // Decimal's double value
        if (n instanceof Decimal d) return d.doubleValue();

        throw new UnsupportedOperationException("The given number cannot be represented by a single double.");
    }

    /**
     * Returns the signed squared value of a number.
     *
     * @param n The number to represent
     * @return The signed squared {@code double} representation
     * @throws UnsupportedOperationException When the number cannot be represented by a single squared {@code double}
     */
    static double doubleValue2(@Nonnull Numeric n) throws UnsupportedOperationException {
        // Special case for fractions
        if (n instanceof Fraction f) return Numbers.pows(f.doubleValue(), 2);

        // Squared magnitudes for vectors
        if (n instanceof Vector v) return v.magnitude2();
        if (n instanceof IntVector v) return v.magnitude2();

        // Decimal's double value squared
        if (n instanceof Decimal d) return Numbers.pows(d.doubleValue(), 2);

        throw new UnsupportedOperationException("The given number cannot be represented by a single double.");
    }

    //
    // Equality
    //

    /**
     * Checks for equality between this number and the given object.
     *
     * @param obj The object to compare to
     * @return {@code true} if the object can be considered equal to this number
     */
    boolean equals(@Nullable Object obj);

    //
    // Serialization
    //

    /**
     * Serializes this number into a string.
     *
     * @return The string representation of this number
     */
    @Nonnull
    String toString();
}
