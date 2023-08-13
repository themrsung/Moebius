package civitas.celestis.math.complex;

import civitas.celestis.math.Numbers;
import civitas.celestis.math.vector.Vector;
import civitas.celestis.math.vector.Vector2;
import jakarta.annotation.Nonnull;

import java.util.function.UnaryOperator;

/**
 * A complex number: {@code x + yi}
 * <p>
 * The component {@code x} represents the real part of this number,
 * and the component {@code y} represents the imaginary part of this number.
 * </p>
 */
public class Complex extends Vector2 implements Comparable<Vector2> {
    //
    // Constructors
    //

    /**
     * Creates a new complex number.
     *
     * @param real      The real part of this number
     * @param imaginary The imaginary part of this number
     */
    public Complex(double real, double imaginary) {
        super(real, imaginary);
    }

    /**
     * Creates a new complex number.
     *
     * @param values An array with the real part at index {@code 0}
     *               and the imaginary part at index {@code 1}
     */
    public Complex(@Nonnull double[] values) {
        super(values);
    }

    /**
     * Creates a new complex number.
     *
     * @param other The vector of which to copy values from
     */
    public Complex(@Nonnull Vector other) {
        super(other);
    }

    /**
     * Creates a new complex number.
     *
     * @param other The vector of which to copy values from
     */
    public Complex(@Nonnull Vector2 other) {
        super(other);
    }

    //
    // Getters
    //

    /**
     * Returns the real part of this number.
     *
     * @return The real part of this number
     */
    public final double real() {
        return x;
    }

    /**
     * Returns the imaginary part of this number.
     *
     * @return The imaginary part of this number
     */
    public final double imaginary() {
        return y;
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
    public Complex add(double s) {
        return new Complex(x + s, y);
    }

    /**
     * Subtracts a scalar from this number.
     *
     * @param s Scalar to subtract from this number
     * @return The resulting number
     */
    @Nonnull
    @Override
    public Complex subtract(double s) {
        return new Complex(x - s, y);
    }

    /**
     * Multiplies this number by a scalar.
     *
     * @param s Scalar to multiply this number by
     * @return The resulting number
     */
    @Nonnull
    @Override
    public Complex multiply(double s) {
        return new Complex(super.multiply(s));
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
    public Complex divide(double s) throws ArithmeticException {
        return new Complex(super.divide(s));
    }

    /**
     * Adds a vector to this number.
     *
     * @param v The vector to add to this number
     * @return The resulting number
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 2}
     */
    @Nonnull
    @Override
    public Complex add(@Nonnull Vector v) throws IllegalArgumentException {
        return new Complex(super.add(v));
    }

    /**
     * Subtracts a vector from this number.
     *
     * @param v The vector to subtract from this number
     * @return The resulting number
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 2}
     */
    @Nonnull
    @Override
    public Complex subtract(@Nonnull Vector v) throws IllegalArgumentException {
        return new Complex(super.subtract(v));
    }

    /**
     * Multiplies this number by another vector using complex number multiplication.
     *
     * @param v The vector to multiply this number by
     * @return The resulting number
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 2}
     */
    @Nonnull
    @Override
    public Complex multiply(@Nonnull Vector v) throws IllegalArgumentException {
        return new Complex(super.multiply(v));
    }

    /**
     * Adds a vector to this number.
     *
     * @param v The vector to add to this number
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public Complex add(@Nonnull Vector2 v) {
        return new Complex(super.add(v));
    }

    /**
     * Subtracts a vector from this number.
     *
     * @param v The vector to subtract from this vector
     * @return The resulting number
     */
    @Nonnull
    @Override
    public Complex subtract(@Nonnull Vector2 v) {
        return new Complex(super.subtract(v));
    }

    /**
     * Multiplies this number by a vector.
     *
     * @param v The vector to multiply this number with
     * @return The resulting number
     */
    @Nonnull
    @Override
    public Complex multiply(@Nonnull Vector2 v) {
        return new Complex(super.multiply(v));
    }

    //
    // Clamping
    //

    /**
     * Returns the minimum number.
     *
     * @param v The vector to get the minimum number of
     * @return The minimum number
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 2}
     */
    @Nonnull
    @Override
    public Complex min(@Nonnull Vector v) throws IllegalArgumentException {
        return new Complex(super.min(v));
    }

    /**
     * Returns the maximum number.
     *
     * @param v The vector to get the maximum number of
     * @return The maximum number
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 2}
     */
    @Nonnull
    @Override
    public Complex max(@Nonnull Vector v) throws IllegalArgumentException {
        return new Complex(super.max(v));
    }

    /**
     * Clamps this number to respect the given bounds.
     *
     * @param min The minimum allowed values
     * @param max The maximum allowed values
     * @return The clamped number
     * @throws IllegalArgumentException When the provided vector {@code min} or {@code max}'s length is not {@code 2}
     */
    @Nonnull
    @Override
    public Complex clamp(@Nonnull Vector min, @Nonnull Vector max) throws IllegalArgumentException {
        return new Complex(super.clamp(min, max));
    }

    /**
     * Returns the minimum number.
     *
     * @param v The vector to get the minimum number of
     * @return The minimum number
     */
    @Nonnull
    @Override
    public Complex min(@Nonnull Vector2 v) {
        return new Complex(super.min(v));
    }

    /**
     * Returns the maximum number.
     *
     * @param v The vector to get the maximum number of
     * @return The maximum number
     */
    @Nonnull
    @Override
    public Complex max(@Nonnull Vector2 v) {
        return new Complex(super.max(v));
    }

    /**
     * Clamps this number to respect the given bounds.
     *
     * @param min The minimum allowed values
     * @param max The maximum allowed values
     * @return The clamped number
     */
    @Nonnull
    @Override
    public Complex clamp(@Nonnull Vector2 min, @Nonnull Vector2 max) {
        return new Complex(super.clamp(min, max));
    }

    //
    // Utility
    //

    /**
     * Applies the given operator to both components of this number.
     *
     * @param operator The operator to apply to each component of this number
     * @return The resulting number
     */
    @Nonnull
    @Override
    public Complex apply(@Nonnull UnaryOperator<Double> operator) {
        return new Complex(super.apply(operator));
    }

    /**
     * Normalizes this number.
     *
     * @return The normalized number
     * @throws UnsupportedOperationException When the magnitude of this number is exactly zero
     */
    @Nonnull
    @Override
    public Complex normalize() throws UnsupportedOperationException {
        return new Complex(super.normalize());
    }

    /**
     * Negates this number.
     *
     * @return The negation of this number
     */
    @Nonnull
    @Override
    public Complex negate() {
        return new Complex(super.negate());
    }

    /**
     * Rotates this number counter-clockwise.
     *
     * @param angRads Angle in radians to rotate this number by
     * @return The rotated number
     */
    @Nonnull
    @Override
    public Complex rotate(double angRads) {
        return new Complex(super.rotate(angRads));
    }

    //
    // Comparison
    //

    /**
     * Compares this object to a vector (or a complex number)
     *
     * @param v The object to compare to
     * @return {@code 0} if the magnitudes are effectively equal, {@code 1} if this number's
     * magnitude is greater, {@code -1} otherwise
     */
    @Override
    public int compareTo(@Nonnull Vector2 v) {
        return Numbers.compare(magnitude2(), v.magnitude2());
    }

    //
    // Serialization
    //

    /**
     * Deserializes a string into a {@link Complex}.
     *
     * @param input The input to deserialize
     * @return The parsed vector
     * @throws NumberFormatException    When the format is invalid
     * @throws IllegalArgumentException When at least one of the component scalars is non-finite
     */
    @Nonnull
    public static Complex parseComplex(@Nonnull String input) throws IllegalArgumentException {
        if (!input.startsWith("Complex{")) {
            throw new NumberFormatException("The provided string does not represent a Complex.");
        }

        final String cleanInput = input.replaceAll("Complex\\{|}", "");
        final String[] valueStrings = cleanInput.split(", ");
        final double[] values = {Double.NaN, Double.NaN};

        for (final String valueString : valueStrings) {
            final String[] split = valueString.split("=");
            values[switch (split[0]) {
                case "x" -> 0;
                case "y" -> 1;
                default -> throw new NumberFormatException("The provided string does not represent a Complex.");
            }] = Double.parseDouble(split[1]);
        }

        return new Complex(values);
    }

    /**
     * {@inheritDoc}
     *
     * @return The string representation of this vector
     */
    @Override
    @Nonnull
    public String toString() {
        return "Complex{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
