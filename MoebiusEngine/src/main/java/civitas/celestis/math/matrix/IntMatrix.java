package civitas.celestis.math.matrix;

import civitas.celestis.math.Numeric;
import civitas.celestis.math.integer.IntVector;
import civitas.celestis.math.integer.IntVector2;
import civitas.celestis.math.integer.IntVector3;
import civitas.celestis.math.integer.IntVector4;
import civitas.celestis.math.vector.Vector;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.UnaryOperator;

/**
 * A two-dimensional array of scalars. Matrices can be used for various purposes.
 * A matrix cannot be resized after creation. Resizing requires re-instantiation.
 */
public class IntMatrix implements Numeric, Iterable<Integer> {
    //
    // Constructors
    //

    /**
     * Creates a new matrix.
     *
     * @param rows    The number of rows
     * @param columns The number of columns
     */
    public IntMatrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.values = new int[rows][columns];
    }

    /**
     * Creates a new matrix.
     *
     * @param values The values to contain in this matrix.
     */
    public IntMatrix(@Nonnull int[][] values) {
        if (values.length < 1) {
            this.rows = 0;
            this.columns = 0;
        } else {
            this.rows = values.length;
            this.columns = values[0].length;
        }

        this.values = new int[rows][columns];

        for (int r = 0; r < rows; r++) {
            System.arraycopy(values[r], 0, this.values[r], 0, columns);
        }
    }

    //
    // Variables
    //

    @Nonnull
    private final int[][] values;
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
    public int[] values() {
        final int[] valueArray = new int[length()];

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
    public int get(int r, int c) throws IndexOutOfBoundsException {
        return values[r][c];
    }

    /**
     * Gets a value at the specified position.
     *
     * @param i The index of the value
     * @return The value at the position
     * @throws IndexOutOfBoundsException When the index is out of bounds
     */
    public int get(@Nonnull IntVector2 i) throws IndexOutOfBoundsException {
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
    public void set(int r, int c, int v) throws IndexOutOfBoundsException {
        values[r][c] = v;
    }

    /**
     * Assigns a value to the specified position.
     *
     * @param i The index of the position
     * @param v The value to assign
     * @throws IndexOutOfBoundsException When the index is out of bounds
     */
    public void set(@Nonnull IntVector2 i, int v) throws IndexOutOfBoundsException {
        set(i.x(), i.y(), v);
    }

    //
    // Properties
    //

    /**
     * Returns the number of rows of this matrix.
     *
     * @return The number of rows of this matrix
     */
    public int rows() {
        return rows;
    }

    /**
     * Returns the number of columns of this matrix.
     *
     * @return The number of columns of this matrix
     */
    public int columns() {
        return columns;
    }

    /**
     * Returns the length ({@code rows * columns}) of this matrix.
     *
     * @return The length of this matrix
     */
    public int length() {
        return rows * columns;
    }

    /**
     * Returns the dimensions of this matrix, where {@code x} is the number of rows
     * and {@code y} is the number of columns.
     *
     * @return The dimensions of this matrix
     */
    @Nonnull
    public IntVector2 size() {
        return new IntVector2(rows, columns);
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
    @Nonnull
    public Iterator<Integer> iterator() {
        return Arrays.stream(values()).iterator();
    }


    //
    // Equality
    //

    /**
     * Checks for equality between this matrix and the specified object {@code obj}.
     *
     * @param obj The object to compare to
     * @return {@code true} if the object is an instance of {@link IntMatrix} and the dimensions and values are equal
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof IntMatrix m)) return false;
        if (rows != m.rows || columns != m.columns) return false;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                if (values[r][c] != m.values[r][c]) return false;
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
    public IntMatrix add(int s) {
        final IntMatrix result = new IntMatrix(rows, columns);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                result.values[r][c] = values[r][c] + s;
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
    public IntMatrix subtract(int s) {
        final IntMatrix result = new IntMatrix(rows, columns);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                result.values[r][c] = values[r][c] - s;
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
    public IntMatrix multiply(int s) {
        final IntMatrix result = new IntMatrix(rows, columns);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                result.values[r][c] = values[r][c] * s;
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
    public IntMatrix divide(int s) throws ArithmeticException {
        final IntMatrix result = new IntMatrix(rows, columns);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                result.values[r][c] = values[r][c] / s;
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
    public Vector multiply(@Nonnull IntVector v) throws ArithmeticException {
        if (columns != v.length()) {
            throw new ArithmeticException("Cannot perform vector-matrix multiplication when the length of the vector != number of columns.");
        }

        if (rows != columns) {
            throw new ArithmeticException("This matrix is not a square matrix.");
        }

        final double[] result = new double[v.length()];

        for (int r = 0; r < rows; r++) {
            double sum = 0;

            for (int c = 0; c < columns; c++) {
                sum += values[r][c] * v.valueAt(c);
            }

            result[r] = sum;
        }

        return Vector.of(result);
    }

    /**
     * Performs vector-matrix multiplication.
     *
     * @param v The vector to multiply with this matrix
     * @return The product of the operation
     * @throws ArithmeticException When the length of the vector is not equal to the number of columns of this matrix
     */
    @Nonnull
    public IntVector2 multiply(@Nonnull IntVector2 v) throws ArithmeticException {
        return (IntVector2) multiply((IntVector) v);
    }

    /**
     * Performs vector-matrix multiplication.
     *
     * @param v The vector to multiply with this matrix
     * @return The product of the operation
     * @throws ArithmeticException When the length of the vector is not equal to the number of columns of this matrix
     */
    @Nonnull
    public IntVector3 multiply(@Nonnull IntVector3 v) throws ArithmeticException {
        return (IntVector3) multiply((IntVector) v);
    }

    /**
     * Performs vector-matrix multiplication.
     *
     * @param v The vector to multiply with this matrix
     * @return The product of the operation
     * @throws ArithmeticException When the length of the vector is not equal to the number of columns of this matrix
     */
    @Nonnull
    public IntVector4 multiply(@Nonnull IntVector4 v) throws ArithmeticException {
        return (IntVector4) multiply((IntVector) v);
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
    public IntMatrix add(@Nonnull IntMatrix m) throws ArithmeticException {
        if (!size().equals(m.size())) {
            throw new ArithmeticException("Matrix dimensions must match for this operation.");
        }

        final IntMatrix result = new IntMatrix(rows, columns);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                result.values[r][c] = values[r][c] + m.values[r][c];
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
    public IntMatrix subtract(@Nonnull IntMatrix m) throws ArithmeticException {
        if (!size().equals(m.size())) {
            throw new ArithmeticException("Matrix dimensions must match for this operation.");
        }

        final IntMatrix result = new IntMatrix(rows, columns);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                result.values[r][c] = values[r][c] - m.values[r][c];
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
    public IntMatrix multiply(@Nonnull IntMatrix m) throws ArithmeticException {
        if (columns != m.rows) {
            throw new ArithmeticException("Matrix dimensions are incompatible for multiplication.");
        }

        final IntMatrix result = new IntMatrix(rows, columns);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < m.columns; c++) {
                int sum = 0;

                for (int i = 0; i < columns; i++) {
                    sum += values[r][i] * m.values[i][c];
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
    public IntMatrix apply(@Nonnull UnaryOperator<Integer> operator) {
        final IntMatrix result = new IntMatrix(rows, columns);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                result.values[r][c] = operator.apply(values[r][c]);
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
    public IntMatrix negate() {
        return multiply(-1);
    }

    /**
     * Transposes this matrix. (swaps the rows and columns)
     *
     * @return The transpose of this matrix
     */
    @Nonnull
    public IntMatrix transpose() {
        final IntMatrix result = new IntMatrix(columns, rows);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                result.values[c][r] = values[r][c];
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
    public static IntMatrix parseMatrix(@Nonnull String s) throws IllegalArgumentException {
        final String[] lines = s.replaceAll("IntMatrix\\{\n|}", "").split("\n");

        final int rows = lines.length;
        final int columns;
        try {
            columns = lines[0].replaceAll("\\[|]", "").trim().split(", ").length;
        } catch (IndexOutOfBoundsException e) {
            throw new NumberFormatException("String is not a matrix.");
        }

        final int[][] values = new int[rows][columns];

        for (int r = 0; r < lines.length; r++) {
            final String cleanLine = lines[r].replaceAll("\\[|]", "").trim();
            final String[] elements = cleanLine.split(", ");

            for (int c = 0; c < columns; c++) {
                values[r][c] = Integer.parseInt(elements[c]);
            }
        }

        return new IntMatrix(values);
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

        out.append("IntMatrix{\n");

        for (int r = 0; r < rows; r++) {
            out.append("  ").append(Arrays.toString(values[r])).append("\n");
        }

        out.append("}");

        return out.toString();
    }
}
