package civitas.celestis.math.matrix;

import civitas.celestis.math.Numeric;
import civitas.celestis.math.integer.IntVector2;
import civitas.celestis.math.natural.NaturalVector2;
import civitas.celestis.math.real.*;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.UnaryOperator;

/**
 * A two-dimensional array of {@link RealNumber} scalars. Matrices can be used for various purposes.
 * A matrix cannot be resized after creation. Resizing requires re-instantiation.
 */
public class RealMatrix implements Numeric, Iterable<RealNumber> {
    //
    // Constructors
    //

    /**
     * Creates a new matrix.
     *
     * @param rows    The number of rows
     * @param columns The number of columns
     */
    public RealMatrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.values = new RealNumber[rows][columns];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                values[r][c] = RealNumber.ZERO; // Ensures that the array is non-null
            }
        }
    }

    /**
     * Creates a new matrix.
     *
     * @param values The values to contain in this matrix
     */
    public RealMatrix(@Nonnull RealNumber[][] values) {
        if (values.length < 1) {
            this.rows = 0;
            this.columns = 0;
        } else {
            this.rows = values.length;
            this.columns = values[0].length;
        }

        this.values = new RealNumber[rows][columns];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                this.values[r][c] = Objects.requireNonNull(values[r][c]);
            }
        }
    }

    //
    // Variables
    //

    @Nonnull
    private final RealNumber[][] values;
    private final int rows, columns;

    //
    // Getters
    //

    /**
     * Returns an array containing all values of this matrix.
     * Changes in the return value of this method will not be reflected in the matrix.
     *
     * @return An array of values of this matrix
     */
    @Nonnull
    public final RealNumber[] values() {
        final RealNumber[] valueArray = new RealNumber[length()];

        for (int r = 0; r < rows; r++) {
            if (columns >= 0) System.arraycopy(values[r], 0, valueArray, r * columns, columns);
        }

        return valueArray;
    }

    /**
     * Gets a value at the specified position.
     *
     * @param r The index of the row
     * @param c The column of the row
     * @return The value at the position
     * @throws IndexOutOfBoundsException When either of the indexes are out of bounds
     */
    @Nonnull
    public final RealNumber get(int r, int c) throws IndexOutOfBoundsException {
        return values[r][c];
    }

    /**
     * Gets a value at the specified position.
     *
     * @param i The index of the value
     * @return The value at the position
     * @throws IndexOutOfBoundsException When the index is out of bounds
     */
    public final RealNumber get(@Nonnull IntVector2 i) throws IndexOutOfBoundsException {
        return get(i.x(), i.y());
    }

    /**
     * Assigns a value to the specified position.
     *
     * @param r The index of the row
     * @param c The index of the column
     * @param v The value to assign
     * @throws IndexOutOfBoundsException When either of the indexes are out of bounds
     */
    public final void set(int r, int c, @Nonnull RealNumber v) throws IndexOutOfBoundsException {
        values[r][c] = Objects.requireNonNull(v);
    }

    /**
     * Assigns a value to the specified position.
     *
     * @param i The index of the position
     * @param v The value to assign
     * @throws IndexOutOfBoundsException When the index is out of bounds
     */
    public final void set(@Nonnull IntVector2 i, @Nonnull RealNumber v) throws IndexOutOfBoundsException {
        set(i.x(), i.y(), Objects.requireNonNull(v));
    }

    //
    // Properties
    //

    /**
     * Returns the number of rows of this matrix.
     *
     * @return The number of rows of this matrix
     */
    public final int rows() {
        return rows;
    }

    /**
     * Returns the number of columns of this matrix.
     *
     * @return The number of columns of this matrix
     */
    public final int columns() {
        return columns;
    }

    /**
     * Returns the length ({@code rows * columns}) of this matrix.
     *
     * @return The length of this matrix
     */
    public final int length() {
        return rows * columns;
    }

    /**
     * Returns the dimensions of this matrix, where {@code x} is the number of rows
     * and {@code y} is the number of columns.
     *
     * @return The dimensions of this matrix
     */
    @Nonnull
    public final NaturalVector2 size() {
        return new NaturalVector2(rows, columns);
    }

    //
    // Utility
    //

    /**
     * Returns an iterator of all values of this matrix.
     *
     * @return An iterator of all values of this matrix
     */
    @Override
    public Iterator<RealNumber> iterator() {
        return Arrays.stream(values()).iterator();
    }

    //
    // Equality
    //

    /**
     * Checks for equality between this matrix and the specified object {@code obj}.
     *
     * @param obj The object to compare to
     * @return {@code ture} if ths object is an instance of {@link RealMatrix} and the dimensions and values are equal
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof RealMatrix m)) return false;
        if (rows != m.rows || columns != m.columns) return false;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                if (!values[r][c].equals(m.values[r][c])) return false;
            }
        }

        return true;
    }

    //
    // Scalar Arithmetic
    //

    /**
     * Adds a scalar to this matrix.
     *
     * @param s The scalar to add to this matrix
     * @return The resulting matrix
     */
    @Nonnull
    public RealMatrix add(@Nonnull RealNumber s) {
        final RealMatrix result = new RealMatrix(rows, columns);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                result.values[r][c] = values[r][c].add(s);
            }
        }

        return result;
    }

    /**
     * Subtracts a scalar to this matrix.
     *
     * @param s The scalar to subtract to this matrix
     * @return The resulting matrix
     */
    @Nonnull
    public RealMatrix subtract(@Nonnull RealNumber s) {
        final RealMatrix result = new RealMatrix(rows, columns);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                result.values[r][c] = values[r][c].subtract(s);
            }
        }

        return result;
    }

    /**
     * Multiplies this matrix by a scalar.
     *
     * @param s The scalar to multiply this matrix by
     * @return The resulting matrix
     */
    @Nonnull
    public RealMatrix multiply(@Nonnull RealNumber s) {
        final RealMatrix result = new RealMatrix(rows, columns);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                result.values[r][c] = values[r][c].multiply(s);
            }
        }

        return result;
    }

    /**
     * Divides this matrix by a scalar.
     *
     * @param s The scalar to divide this matrix by
     * @return The resulting matrix
     * @throws ArithmeticException When the denominator {@code s} is zero
     */
    @Nonnull
    public RealMatrix divide(@Nonnull RealNumber s) throws ArithmeticException {
        final RealMatrix result = new RealMatrix(rows, columns);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                result.values[r][c] = values[r][c].divide(s);
            }
        }

        return result;
    }

    //
    // Vector Arithmetic
    //

    /**
     * Performs vector-matrix multiplication.
     *
     * @param v The vector to multiply with this matrix
     * @return The product of the operation
     * @throws ArithmeticException When the length of the vector is not equal to the number of columns of this matrix
     */
    @Nonnull
    public RealVector multiply(@Nonnull RealVector v) throws ArithmeticException {
        if (columns != v.length()) {
            throw new ArithmeticException("Cannot perform vector-matrix multiplication when the length of the vector != number of columns.");
        }

        if (rows != columns) {
            throw new ArithmeticException("This matrix is not a square matrix.");
        }

        final RealNumber[] result = new RealNumber[v.length()];

        for (int r = 0; r < rows; r++) {
            RealNumber sum = RealNumber.ZERO;

            for (int c = 0; c < columns; c++) {
                sum = sum.add(values[r][c].multiply(v.valueAt(c)));
            }

            result[r] = sum;
        }

        return RealVector.of(result);
    }

    /**
     * Performs vector-matrix multiplication.
     *
     * @param v The vector to multiply with this matrix
     * @return The product of the operation
     * @throws ArithmeticException When the length of the vector is not equal to the number of columns of this matrix
     */
    @Nonnull
    public RealVector2 multiply(@Nonnull RealVector2 v) throws ArithmeticException {
        return (RealVector2) multiply((RealVector) v);
    }

    /**
     * Performs vector-matrix multiplication.
     *
     * @param v The vector to multiply with this matrix
     * @return The product of the operation
     * @throws ArithmeticException When the length of the vector is not equal to the number of columns of this matrix
     */
    @Nonnull
    public RealVector3 multiply(@Nonnull RealVector3 v) throws ArithmeticException {
        return (RealVector3) multiply((RealVector) v);
    }

    /**
     * Performs vector-matrix multiplication.
     *
     * @param v The vector to multiply with this matrix
     * @return The product of the operation
     * @throws ArithmeticException When the length of the vector is not equal to the number of columns of this matrix
     */
    @Nonnull
    public RealVector4 multiply(@Nonnull RealVector4 v) throws ArithmeticException {
        return (RealVector4) multiply((RealVector) v);
    }

    //
    // Matrix Arithmetic
    //


    /**
     * Adds another matrix to this matrix.
     *
     * @param m The matrix to add to this matrix
     * @return The resulting matrix
     * @throws ArithmeticException When the matrices' dimensions do not match
     */
    @Nonnull
    public RealMatrix add(@Nonnull RealMatrix m) throws ArithmeticException {
        if (!size().equals(m.size())) {
            throw new ArithmeticException("Matrix dimensions must match for this operation.");
        }

        final RealMatrix result = new RealMatrix(rows, columns);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                result.values[r][c] = values[r][c].add(m.values[r][c]);
            }
        }

        return result;
    }

    /**
     * Subtracts another matrix from this matrix.
     *
     * @param m The matrix to subtract from this matrix
     * @return The resulting matrix
     * @throws ArithmeticException When the matrices' dimensions do not match
     */
    @Nonnull
    public RealMatrix subtract(@Nonnull RealMatrix m) throws ArithmeticException {
        if (!size().equals(m.size())) {
            throw new ArithmeticException("Matrix dimensions must match for this operation.");
        }

        final RealMatrix result = new RealMatrix(rows, columns);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                result.values[r][c] = values[r][c].subtract(m.values[r][c]);
            }
        }

        return result;
    }

    /**
     * Multiplies this matrix by another matrix.
     *
     * @param m The matrix to multiply to this matrix
     * @return The resulting matrix
     * @throws ArithmeticException When the matrices' dimensions are incompatible
     */
    @Nonnull
    public RealMatrix multiply(@Nonnull RealMatrix m) throws ArithmeticException {
        if (columns != m.rows) {
            throw new ArithmeticException("Matrix dimensions are incompatible for multiplication.");
        }

        final RealMatrix result = new RealMatrix(rows, columns);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < m.columns; c++) {
                RealNumber sum = RealNumber.ZERO;

                for (int i = 0; i < columns; i++) {
                    sum = sum.add(values[r][i].multiply(m.values[i][c]));
                }
                result.values[r][c] = sum;
            }
        }

        return result;
    }

    //
    // Utility
    //

    /**
     * Applies given operator to each element of this matrix, then returns the resulting matrix.
     *
     * @param operator The operator to apply to each element of this matrix
     * @return The resulting matrix
     */
    @Nonnull
    public RealMatrix apply(@Nonnull UnaryOperator<RealNumber> operator) {
        final RealMatrix result = new RealMatrix(rows, columns);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                result.values[r][c] = Objects.requireNonNull(operator.apply(values[r][c]));
            }
        }

        return result;
    }

    /**
     * Returns the negated matrix of this matrix.
     *
     * @return The negation of this matrix
     */
    @Nonnull
    public RealMatrix negate() {
        return multiply(new RealNumber(-1));
    }

    /**
     * Transposes this matrix. (swaps the rows and columns)
     *
     * @return The transpose of this matrix
     */
    @Nonnull
    public RealMatrix transpose() {
        final RealMatrix result = new RealMatrix(columns, rows);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                result.values[c][r] = values[r][c];
            }
        }

        return result;
    }

    /**
     * Converts this matrix into a {@code double} matrix.
     *
     * @return The {@link Matrix} representation of this matrix
     */
    @Nonnull
    public Matrix doubleValue() {
        final Matrix result = new Matrix(rows, columns);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                result.set(r, c, values[r][c].doubleValue());
            }
        }

        return result;
    }

    //
    // Serialization
    //

    /**
     * Deserializes a string into a matrix.
     *
     * @param s String to parse
     * @return Matrix parsed from given string
     * @throws NumberFormatException    When the string is not parsable to a matrix
     * @throws IllegalArgumentException When the string contains non-finite values
     */
    @Nonnull
    public static RealMatrix parseMatrix(@Nonnull String s) throws IllegalArgumentException {
        final String[] lines = s.replaceAll("RealMatrix\\{\n|}", "").split("\n");

        final int rows = lines.length;
        final int columns;
        try {
            columns = lines[0].replaceAll("\\[|]", "").trim().split(", ").length;
        } catch (IndexOutOfBoundsException e) {
            throw new NumberFormatException("String is not a matrix.");
        }

        final RealNumber[][] values = new RealNumber[rows][columns];

        for (int r = 0; r < lines.length; r++) {
            final String cleanLine = lines[r].replaceAll("\\[|]", "").trim();
            final String[] elements = cleanLine.split(", ");

            for (int c = 0; c < columns; c++) {
                values[r][c] = RealNumber.parseNumber(elements[c]);
            }
        }

        return new RealMatrix(values);
    }

    /**
     * Serializes this matrix into a string.
     *
     * @return The string representation of this matrix
     */
    @Override
    @Nonnull
    public String toString() {
        final StringBuilder out = new StringBuilder();

        out.append("RealMatrix{\n");

        for (int r = 0; r < rows; r++) {
            out.append("  ").append(Arrays.toString(values[r])).append("\n");
        }

        out.append("}");

        return out.toString();
    }
}
