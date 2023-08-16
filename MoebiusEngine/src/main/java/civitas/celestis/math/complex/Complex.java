package civitas.celestis.math.complex;

import civitas.celestis.math.Numbers;
import civitas.celestis.math.vector.*;
import civitas.celestis.util.data.Packable64;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * A complex number with the notation {@code x + yi}.
 */
public class Complex extends Double2 implements Comparable<Complex>, Packable64 {
    //
    // Constants
    //

    /**
     * The identity number. This is the real number equivalent of {@code 1}.
     */
    public static final Complex IDENTITY = new Complex(1, 0);

    /**
     * The square root of {@code -1}.
     * <p>
     * Multiplying a complex number by {@code i} will rotate the number 90 degrees counter-clockwise
     * along the real-complex number plane.
     * </p>
     */
    public static final Complex I = new Complex(0, 1);

    //
    // Static Initializers
    //

    /**
     * Returns the complex number counterpart of the provided real number.
     *
     * @param real The real number to represent as a complex number
     * @return The complex number representation of the provided number
     */
    @Nonnull
    public static Complex of(double real) {
        return new Complex(real, 0);
    }

    //
    // Constructors
    //

    /**
     * Creates a new complex number.
     *
     * @param r The real part of this complex number
     * @param i The imaginary part of this complex number
     */
    public Complex(double r, double i) {
        super(r, i);
    }

    /**
     * Creates a new complex number.
     *
     * @param values An array containing the components of
     *               this number in real-imaginary order
     */
    public Complex(@Nonnull double[] values) {
        super(values);
    }

    /**
     * Creates a new complex number.
     *
     * @param v The vector of which to copy component values from
     */
    public Complex(@Nonnull DoubleVector<?> v) {
        super(v);
    }

    /**
     * Creates a new complex number.
     *
     * @param v The vector of which to copy component values from
     */
    public Complex(@Nonnull Double2 v) {
        super(v);
    }

    /**
     * Creates a new complex number.
     *
     * @param v The vector of which to copy component values from
     */
    public Complex(@Nonnull Float2 v) {
        super(v);
    }

    /**
     * Creates a new complex number.
     *
     * @param v The vector of which to copy component values from
     */
    public Complex(@Nonnull Long2 v) {
        super(v);
    }

    /**
     * Creates a new complex number.
     *
     * @param v The vector of which to copy component values from
     */
    public Complex(@Nonnull Int2 v) {
        super(v);
    }

    //
    // Packing
    //

    /**
     * Unpacks a 64-bit representation of a complex number.
     *
     * @param c64 The packed 64-bit representation of a complex number
     * @return The unpacked complex number
     */
    @Nonnull
    public static Complex unpack64(long c64) {
        // Extract the packed values using bit shifting
        int packedR = (int) (c64 >> 32);
        int packedI = (int) c64;

        // Convert the packed integers back to floats
        float r = Float.intBitsToFloat(packedR);
        float i = Float.intBitsToFloat(packedI);

        // Create and return the unpacked Double2
        return new Complex(r, i);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public long pack64() {
        final float r = (float) x;
        final float i = (float) y;

        // Convert the floats to integers by using their bitwise representation
        int packedR = Float.floatToIntBits(r);
        int packedI = Float.floatToIntBits(i);

        // Pack the two integers into a single long
        return ((long) packedR << 32) | (packedI & 0xFFFFFFFFL);
    }

    //
    // Getters
    //

    /**
     * Returns the real part of this complex number.
     *
     * @return The real part of this complex number
     */
    public final double real() {
        return x;
    }

    /**
     * Returns the imaginary part of this complex number.
     *
     * @return The imaginary part of this complex number
     */
    public final double imaginary() {
        return y;
    }

    //
    // Arithmetic
    //

    /**
     * Adds a scalar to this complex number.
     *
     * @param s The scalar to add to this number
     * @return The resulting number
     */
    @Nonnull
    @Override
    public Complex add(double s) {
        return new Complex(x + s, y);
    }

    /**
     * Subtracts a scalar from this complex number.
     *
     * @param s The scalar to subtract from this number
     * @return The resulting number
     */
    @Nonnull
    @Override
    public Complex subtract(double s) {
        return new Complex(x - s, y);
    }

    /**
     * Multiplies this complex number by a scalar.
     *
     * @param s The scalar to multiply this number by
     * @return The resulting number
     */
    @Nonnull
    @Override
    public Complex multiply(double s) {
        return new Complex(x * s, y * s);
    }

    /**
     * Divides this complex number by a scalar.
     *
     * @param s The scalar to divide this number by
     * @return THe resulting number
     * @throws ArithmeticException When the denominator {@code s} is zero
     */
    @Nonnull
    @Override
    public Complex divide(double s) throws ArithmeticException {
        if (s == 0) {
            throw new ArithmeticException("Cannot divide by zero.");
        }

        return new Complex(x / s, y / s);
    }

    /**
     * Adds another complex number to this number.
     *
     * @param c The complex number to add to this number
     * @return The resulting number
     */
    @Nonnull
    public Complex add(@Nonnull Complex c) {
        return new Complex(x + c.x, y + c.y);
    }

    /**
     * Subtracts this number by another complex number.
     *
     * @param c The complex number to subtract from this number
     * @return The resulting number
     */
    @Nonnull
    public Complex subtract(@Nonnull Complex c) {
        return new Complex(x - c.x, y - c.y);
    }

    /**
     * Multiplies this number by another complex number.
     * The calculations are identical to {@link Double2#multiply(Double2)}.
     *
     * @param c The complex number to multiply this number by
     * @return The product of the two numbers
     */
    @Nonnull
    public Complex multiply(@Nonnull Complex c) {
        return new Complex(x * c.x - y * c.y, x * c.y + y * c.x);
    }

    /**
     * Divides this number by another complex number.
     * This is identical to the reciprocal of the product.
     *
     * @param c The complex number to divide this number by
     * @return The quotient of the two numbers
     * @throws ArithmeticException When the squared Euclidean norm of the provided
     *                             complex number {@code c} is zero
     */
    @Nonnull
    public Complex divide(@Nonnull Complex c) throws ArithmeticException {
        final double n2 = c.norm2();
        if (n2 == 0) {
            throw new ArithmeticException("Cannot divide by zero.");
        }

        return new Complex(
                (x * c.x + y * c.y) / n2,
                (y * c.x - x * c.y) / n2
        );

    }

    //
    // Reciprocal
    //

    /**
     * Returns the multiplicative inverse (the reciprocal) of this number.
     *
     * @return The reciprocal of this number
     * @throws ArithmeticException When the squared Euclidean norm of this number is zero
     */
    @Nonnull
    public Complex reciprocal() throws ArithmeticException {
        final double n2 = norm2();
        if (n2 == 0) {
            throw new ArithmeticException("Cannot divide by zero.");
        }

        return new Complex(x / n2, -y / n2);
    }

    //
    // Rotation
    //

    /**
     * Rotates this complex number along the complex plane counter-clockwise
     * by the provided angle of rotation.
     *
     * @param angRads The angle of rotation to apply in radians
     * @return The rotated number
     */
    @Nonnull
    @Override
    public Complex rotate(double angRads) {
        double cos = Math.cos(angRads);
        double sin = Math.sin(angRads);

        return new Complex(
                cos * x - sin * y,
                sin * x + cos * y
        );
    }


    //
    // Comparison
    //

    /**
     * Compares this number to another complex number.
     *
     * @param c The complex number to compare to
     * @return {@code 0} if the values are equal, {@code 1} if this number is greater, {@code -1} otherwise
     */
    @Override
    public int compareTo(@Nonnull Complex c) {
        final int r = Double.compare(x, c.x);
        if (r != 0) return r;

        return Double.compare(y, c.y);
    }


    //
    // Equality
    //

    /**
     * Checks for equality between this number and the provided object {@code obj}.
     * <p>
     * This method respects the mathematical concept of complex numbers,
     * and only treats complex numbers which have the same components as equal.
     * </p>
     * <p>
     * Scalar values (real numbers) of the same representation will not be considered equal.
     * </p>
     *
     * @param obj The object to compare to
     * @return {@code true} if the other object is also a complex number, and both the real
     * and imaginary parts are equal
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof Complex c)) return false;
        return x == c.x && y == c.y;
    }

    /**
     * Checks for loose equality between this number and the provided object {@code obj}.
     * <p>
     * This method will return {@code true} if either the other object is a {@link Number}
     * and the value it represents is equal to that of this complex number's value,
     * or if the other object is equal to this number in the context of {@link Double2#equals(Object)}.
     * </p>
     * <p>
     * If the imaginary part (the Y component) of this number is exactly zero,
     * it will allow comparison with real numbers.
     * </p>
     * <p>
     * If the other object is not an instance of {@link Number}, it will follow the equality
     * logic of {@link Double2#equals(Object)}.
     * </p>
     *
     * @param obj The object to compare to
     * @return {@code true} if the object is considered equal to this number as the description above
     */
    public boolean looseEquals(@Nullable Object obj) {
        if (obj instanceof Number n && y == 0) {
            return Numbers.equals(x, n);
        }

        return super.equals(obj);
    }

    //
    // Serialization
    //

    /**
     * Deserializes a string into a complex number.
     *
     * @param input The input string to parse
     * @return The parsed complex number
     * @throws NumberFormatException When the format is invalid
     */
    @Nonnull
    public static Complex parseComplex(@Nonnull String input) throws NumberFormatException {
        if (!input.startsWith("Complex{")) {
            throw new NumberFormatException("The provided string is not a complex number.");
        }

        final String[] valueStrings = input.replaceAll("Complex\\{|}", "").split(", ");
        if (valueStrings.length != 2) {
            throw new NumberFormatException("The provided string does not have two components.");
        }

        final double[] values = new double[2];

        for (final String valueString : valueStrings) {
            final String[] split = valueString.split("=");
            if (split.length != 2) {
                throw new NumberFormatException("The format is invalid.");
            }

            values[switch (split[0]) {
                case "x" -> 0;
                case "y" -> 1;
                default -> throw new NumberFormatException("The provided string has a non-XY component.");
            }] = Double.parseDouble(split[1]);
        }

        return new Complex(values);
    }

    /**
     * Serializes this complex number into a string.
     *
     * @return The string representation of this number
     */
    @Nonnull
    @Override
    public String toString() {
        return "Complex{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
