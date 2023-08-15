package civitas.celestis.math;

import civitas.celestis.graphics.color.LinearColor;
import civitas.celestis.graphics.geometry.Vertex;
import civitas.celestis.math.complex.Complex;
import civitas.celestis.math.complex.Quaternion;
import civitas.celestis.math.matrix.DoubleMatrix;
import civitas.celestis.math.matrix.LongMatrix;
import civitas.celestis.math.vector.*;
import jakarta.annotation.Nonnull;

/**
 * A superinterface for numeric data types.
 * @see Vector
 * @see civitas.celestis.math.matrix.Matrix Matrix
 */
public interface Numeric {
    //
    // Factory
    //

    /**
     * Creates a new number from an array of {@code double}s.
     *
     * @param values The array of values
     * @return The constructed number
     */
    @Nonnull
    static Numeric of(@Nonnull double... values) {
        return switch (values.length) {
            case 2 -> new Vector2(values);
            case 3 -> new Vector3(values);
            case 4 -> new Vector4(values);

            // 1-row matrix
            default -> new DoubleMatrix(new double[][]{values});
        };
    }

    /**
     * Creates a new number from an array of {@code float}s.
     *
     * @param values The array of values
     * @return The constructed number
     */
    @Nonnull
    static Numeric of(@Nonnull float... values) {
        return switch (values.length) {
            case 2 -> new Float2(values);
            case 3 -> new Float3(values);
            case 4 -> new Float4(values);

            // 1-row matrix (converted to double)
            default -> {
                final double[] converted = new double[values.length];

                for (int i = 0; i < values.length; i++) {
                    converted[i] = values[i];
                }

                yield new DoubleMatrix(new double[][]{converted});
            }
        };
    }

    /**
     * Creates a new number from an array of {@code long}s.
     *
     * @param values The array of values
     * @return The constructed number
     */
    @Nonnull
    static Numeric of(@Nonnull long... values) {
        return switch (values.length) {
            case 2 -> new Long2(values);
            case 3 -> new Long3(values);
            case 4 -> new Long4(values);

            // 1-row matrix
            default -> new LongMatrix(new long[][]{values});
        };
    }

    /**
     * Creates a new number from an array of {@code int}s.
     *
     * @param values The array of values
     * @return The constructed number
     */
    @Nonnull
    static Numeric of(@Nonnull int... values) {
        return switch (values.length) {
            case 2 -> new Int2(values);
            case 3 -> new Int3(values);
            case 4 -> new Int4(values);

            // 1-row matrix (converted to long)
            default -> {
                final long[] converted = new long[values.length];

                for (int i = 0; i < values.length; i++) {
                    converted[i] = values[i];
                }

                yield new LongMatrix(new long[][]{converted});
            }
        };
    }

    /**
     * Creates a new matrix.
     *
     * @param values The values to contain
     * @return The constructed matrix
     * @see DoubleMatrix#DoubleMatrix(double[][]) DoubleMatrix
     */
    @Nonnull
    static DoubleMatrix of(@Nonnull double[][] values) {
        return new DoubleMatrix(values);
    }

    /**
     * Creates a new matrix.
     *
     * @param values The values to contain
     * @return The constructed matrix
     * @see LongMatrix#LongMatrix(long[][]) LongMatrix
     */
    @Nonnull
    static LongMatrix of(@Nonnull long[][] values) {
        return new LongMatrix(values);
    }

    //
    // Parsing
    //

    /**
     * Parses a string into a number.
     *
     * @param s The string to parse
     * @return The parsed number
     * @throws NumberFormatException When the string is not a number
     */
    @Nonnull
    static Numeric parseNumber(@Nonnull String s) throws NumberFormatException {
        // DoubleVector subtypes
        try {return Vector2.parseVector(s);} catch (final Exception ignored) {}
        try {return Vector3.parseVector(s);} catch (final Exception ignored) {}
        try {return Vector4.parseVector(s);} catch (final Exception ignored) {}
        try {return Complex.parseComplex(s);} catch (final Exception ignored) {}
        try {return Vertex.parseVertex(s);} catch (final Exception ignored) {}
        try {return Quaternion.parseQuaternion(s);} catch (final Exception ignored) {}

        // FloatVector subtypes
        try {return Float2.parseVector(s);} catch (final Exception ignored) {}
        try {return Float3.parseVector(s);} catch (final Exception ignored) {}
        try {return Float4.parseVector(s);} catch (final Exception ignored) {}
        try {return LinearColor.parseColor(s);} catch (final Exception ignored) {}

        // LongVector subtypes
        try {return Long2.parseVector(s);} catch (final Exception ignored) {}
        try {return Long3.parseVector(s);} catch (final Exception ignored) {}
        try {return Long4.parseVector(s);} catch (final Exception ignored) {}

        // IntVector subtypes
        try {return Int2.parseVector(s);} catch (final Exception ignored) {}
        try {return Int3.parseVector(s);} catch (final Exception ignored) {}
        try {return Int4.parseVector(s);} catch (final Exception ignored) {}

        // Matrix subtypes
        try {return DoubleMatrix.parseMatrix(s);} catch (final Exception ignored) {}
        try {return LongMatrix.parseMatrix(s);} catch (final Exception ignored) {}

        // Exception
        throw new NumberFormatException("Given string is not a numeric type.");
    }
}
