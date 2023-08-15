package civitas.celestis.math.complex;

import civitas.celestis.math.vector.Float2;
import civitas.celestis.math.vector.Int2;
import civitas.celestis.math.vector.Vector2;
import jakarta.annotation.Nonnull;

/**
 * A complex number: {@code x + yi}.
 */
public class Complex extends Vector2 {
    //
    // Constructors
    //

    /**
     * Creates a new complex number.
     * @param r The real part of this number
     * @param i The imaginary part of this number
     */
    public Complex(double r, double i) {
        super(r, i);
    }

    /**
     * Creates a new complex number.
     * @param values A 2-element array with the first element being the real part,
     *               and the second element being the imaginary part
     */
    public Complex(@Nonnull double[] values) {
        super(values);
    }

    /**
     * Creates a new complex number.
     * @param v The vector to copy values from
     */
    public Complex(@Nonnull Vector2 v) {
        super(v);
    }

    /**
     * Creates a new complex number.
     * @param v The vector to copy values from
     */
    public Complex(@Nonnull Float2 v) {
        super(v);
    }

    /**
     * Creates a new complex number.
     * @param v The vector to copy values from
     */
    public Complex(@Nonnull Int2 v) {
        super(v);
    }

    //
    // Arithmetic
    //

    /**
     * Adds a scalar to this number.
     * @param s The scalar to add to this number
     * @return The resulting number
     */
    @Nonnull
    @Override
    public Complex add(double s) {
        return new Complex(x + s, y);
    }

    /**
     * Subtracts a scalar from this number.
     * @param s The scalar to subtract from this number
     * @return The resulting number
     */
    @Nonnull
    @Override
    public Complex subtract(double s) {
        return new Complex(x - s, y);
    }

    /**
     * Multiplies this number by a scalar.
     * @param s The scalar to multiply to this number
     * @return The resulting number
     */
    @Nonnull
    @Override
    public Complex multiply(double s) {
        return new Complex(x * s, y * s);
    }

    /**
     * Divides this number by a scalar.
     * @param s The scalar to divide this number by
     * @return The resulting number
     */
    @Nonnull
    @Override
    public Complex divide(double s) {
        return new Complex(x / s, y / s);
    }

    /**
     * Adds another complex number to this number.
     * @param c The complex number to add to this number
     * @return The sum of the two numbers
     */
    @Nonnull
    public Complex add(@Nonnull Complex c) {
        return new Complex(x + c.x, y + c.y);
    }

    /**
     * Subtracts another complex number from this number.
     * @param c The complex number to subtract from this number
     * @return The resulting number
     */
    @Nonnull
    public Complex subtract(@Nonnull Complex c) {
        return new Complex(x - c.x, y - c.y);
    }

    /**
     * Multiplies this number by another complex number.
     * This operation is identical to that of {@link Vector2#multiply(Vector2)}.
     *
     * @param c The complex number to multiply this number with
     * @return The product of the two numbers
     */
    @Nonnull
    public Complex multiply(@Nonnull Complex c) {
        return new Complex(x * c.x - y * c.y, x * c.y + y * c.x);
    }

    /**
     * Divides this number by another complex number.
     * @param c The complex number to divide this complex number by
     * @return The quotient of the two numbers
     * @throws ArithmeticException When the squared norm of the provided number {@code c} is zero
     */
    @Nonnull
    public Complex divide(@Nonnull Complex c) {
        final double n2 = c.norm2();

        if (n2 == 0) {
            throw new ArithmeticException("Cannot divide by a complex number with zero magnitude.");
        }

        final double r = (x * c.x + y * c.y) / n2;
        final double i = (x * c.x - y * c.y) / n2;
        return new Complex(r, i);
    }

    //
    // Inverse
    //

    /**
     * Returns the inverse of this number.
     * @return The inverse of this number
     * @throws ArithmeticException When the squared norm of this number is zero
     */
    @Nonnull
    public Complex inverse() {
        final double n2 = norm2();
        if (n2 == 0) throw new ArithmeticException("Cannot find the inverse of this number.");
        return new Complex(x / n2, y / n2);
    }

    //
    // Rotation
    //

    /**
     * Rotates this number counter-clockwise by given angle.
     * @param angRads Angle in radians to rotate this vector by
     * @return The rotated number
     */
    @Nonnull
    @Override
    public Complex rotate(double angRads) {
        final double cos = Math.cos(angRads);
        final double sin = Math.sin(angRads);

        return new Complex(x * cos - y * sin, x * sin + y * cos);
    }

    //
    // Comparison
    //

    /**
     * Compares this number to another number.
     * If the number is an ordinary {@link Vector2}, the X component will be treated
     * as the real part, and the Y component will be treated as the imaginary part.
     *
     * @param v The number to compare to, in the form of a vector
     * @return {@code 0} if the values are equal, {@code 1} if this value is greater, {@code -1} otherwise
     */
    @Override
    public int compareTo(@Nonnull Vector2 v) {
        final int real = Double.compare(x, v.x());
        if (real != 0) return real;

        return Double.compare(y, v.y());
    }

    //
    // Numeric Conversion
    //

    /**
     * This operation is not supported.
     * @return This method cannot return a value
     * @throws UnsupportedOperationException Always throws an exception
     */
    @Override
    public byte byteValue() {
        throw new UnsupportedOperationException("A complex number cannot be represented as a single scalar.");
    }

    /**
     * This operation is not supported.
     * @return This method cannot return a value
     * @throws UnsupportedOperationException Always throws an exception
     */
    @Override
    public short shortValue() {
        throw new UnsupportedOperationException("A complex number cannot be represented as a single scalar.");
    }

    /**
     * This operation is not supported.
     * @return This method cannot return a value
     * @throws UnsupportedOperationException Always throws an exception
     */
    @Override
    public int intValue() {
        throw new UnsupportedOperationException("A complex number cannot be represented as a single scalar.");
    }

    /**
     * Returns the packed value of this complex number.
     * @return The packed value
     * @see Complex#pack()
     */
    @Override
    public long longValue() {
        return pack();
    }

    /**
     * This operation is not supported.
     * @return This method cannot return a value
     * @throws UnsupportedOperationException Always throws an exception
     */
    @Override
    public float floatValue() {
        throw new UnsupportedOperationException("A complex number cannot be represented as a single scalar.");
    }

    /**
     * Returns the packed value of this complex number in the format of {@code double}.
     * @return The packed value as a {@code double}
     * @see Complex#pack()
     */
    @Override
    public double doubleValue() {
        return Double.longBitsToDouble(pack());
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
    public static Complex parseVector(@Nonnull String input) throws IllegalArgumentException {
        if (!input.startsWith("Vector2{")) {
            throw new NumberFormatException("The provided string does not represent a Complex.");
        }

        final String cleanInput = input.replaceAll("Vector2\\{|}", "");
        final String[] valueStrings = cleanInput.split(", ");
        final double[] values = new double[2];

        for (final String valueString : valueStrings) {
            final String[] split = valueString.split("=");
            values[switch (split[0]) {
                case "r" -> 0;
                case "i" -> 1;
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
                "r=" + x +
                ", i=" + y +
                '}';
    }

}
