package civitas.celestis.math.matrix;

import civitas.celestis.math.util.Numbers;
import civitas.celestis.math.vector.Vector;
import jakarta.annotation.Nonnull;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.UnaryOperator;

/**
 * A rectangular array of {@code double}s.
 */
public class Matrix implements Iterable<Double> {
    //
    // Constructors
    //

    /**
     * Creates an empty matrix with the specified dimensions.
     *
     * @param rows    Number of rows
     * @param columns Number of columns
     */
    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.values = new double[rows][columns];
    }

    /**
     * Creates a new matrix from a rectangular array of values.
     *
     * @param values Values to use
     */
    public Matrix(@Nonnull double[][] values) {
        if (values.length == 0) {
            throw new IllegalArgumentException("A matrix cannot have a size of 0*n.");
        }

        this.rows = values.length;
        this.columns = values[0].length;
        this.values = new double[rows][columns];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                this.values[r][c] = Numbers.requireFinite(values[r][c]);
            }
        }
    }

    //
    // Variables
    //

    /**
     * The values of this matrix.
     */
    @Nonnull
    private final double[][] values;

    /**
     * The dimensions of this matrix.
     */
    private final int rows, columns;

    //
    // Accessors
    //

    /**
     * Gets the value in the specified position.
     *
     * @param row    Index of row to get
     * @param column Index of column to get
     * @return Value in specified position
     * @throws IndexOutOfBoundsException When the index is out of bounds
     */
    public double get(int row, int column) throws IndexOutOfBoundsException {
        return values[row][column];
    }

    /**
     * Sets the value in the specified position.
     *
     * @param row    Index of row to set
     * @param column Index of column to set
     * @param value  Value to set to
     * @throws IndexOutOfBoundsException When the index is out of bounds
     */
    public void set(int row, int column, double value) throws IndexOutOfBoundsException {
        values[row][column] = Numbers.requireFinite(value);
    }

    /**
     * Returns the values of this matrix as a single-dimensional array.
     *
     * @return Array of all values in this matrix
     */
    @Nonnull
    public double[] values() {
        final int size = size();
        final double[] result = new double[size];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                result[r * columns * c] = values[r][c];
            }
        }

        return result;
    }

    /**
     * Gets the number of rows of this matrix.
     *
     * @return The number of rows
     */
    public int rows() {
        return rows;
    }

    /**
     * Gets the number of columns of this matrix.
     *
     * @return The number of columns
     */
    public int columns() {
        return columns;
    }

    /**
     * Gets the size of this matrix.
     *
     * @return {@code rows * columns}
     */
    public int size() {
        return rows * columns;
    }

    //
    // Arithmetic
    //

    /**
     * Adds a scalar to this matrix.
     *
     * @param s Scalar to add to this matrix
     * @return The resulting matrix
     */
    @Nonnull
    public Matrix add(double s) {
        final double[][] result = new double[rows][columns];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                result[r][c] = Numbers.requireFinite(values[r][c] + s);
            }
        }

        return new Matrix(result);
    }

    /**
     * Subtracts a scalar from this matrix.
     *
     * @param s Scalar to subtract from this matrix
     * @return The resulting matrix
     */
    @Nonnull
    public Matrix subtract(double s) {
        final double[][] result = new double[rows][columns];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                result[r][c] = Numbers.requireFinite(values[r][c] - s);
            }
        }

        return new Matrix(result);
    }

    /**
     * Multiplies this matrix by a scalar.
     *
     * @param s Scalar to multiply this matrix with
     * @return The resulting matrix
     */
    @Nonnull
    public Matrix multiply(double s) {
        final double[][] result = new double[rows][columns];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                result[r][c] = Numbers.requireFinite(values[r][c] * s);
            }
        }

        return new Matrix(result);
    }

    /**
     * Divides this matrix by a scalar.
     *
     * @param s Scalar to divide this matrix by
     * @return The resulting matrix
     * @throws ArithmeticException When the denominator {@code s == 0}
     */
    @Nonnull
    public Matrix divide(double s) throws ArithmeticException {
        if (s == 0) {
            throw new ArithmeticException("Cannot divide by zero.");
        }

        final double[][] result = new double[rows][columns];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                result[r][c] = Numbers.requireFinite(values[r][c] / s);
            }
        }

        return new Matrix(result);
    }

    /**
     * Multiplies a vector by this matrix.
     *
     * @param v Vector to multiply with this matrix
     * @return The resulting vector
     * @throws UnsupportedOperationException When the operation is impossible
     */
    @Nonnull
    public Vector multiply(@Nonnull Vector v) throws UnsupportedOperationException {
        if (v.length() != columns) {
            throw new UnsupportedOperationException("Cannot perform multiplication when the column length != vector's length.");
        }

        if (rows != columns) {
            throw new UnsupportedOperationException("The matrix is not a square matrix.");
        }

        final double[] vector = v.values();
        final double[] result = new double[v.length()];

        for (int r = 0; r < rows; r++) {
            double sum = 0;

            for (int c = 0; c < columns; c++) {
                sum += values[r][c] * vector[c];
            }

            result[r] = sum;
        }

        return Vector.of(result);
    }

    /**
     * Adds another matrix to this matrix.
     *
     * @param m Matrix to add to this matrix
     * @return The resulting matrix
     * @throws UnsupportedOperationException When the dimensions do not match
     */
    @Nonnull
    public Matrix add(@Nonnull Matrix m) throws UnsupportedOperationException {
        if (rows != m.rows || columns != m.columns) {
            throw new UnsupportedOperationException("Matrix dimensions must match for addition.");
        }

        final double[][] result = new double[rows][columns];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                result[r][c] = values[r][c] + m.values[r][c];
            }
        }

        return new Matrix(result);
    }

    /**
     * Subtracts another matrix from this matrix.
     *
     * @param m Matrix to subtract from this matrix
     * @return The resulting matrix
     * @throws UnsupportedOperationException When the dimensions do not match
     */
    @Nonnull
    public Matrix subtract(@Nonnull Matrix m) throws UnsupportedOperationException {
        if (rows != m.rows || columns != m.columns) {
            throw new UnsupportedOperationException("Matrix dimensions must match for subtraction.");
        }

        final double[][] result = new double[rows][columns];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                result[r][c] = values[r][c] - m.values[r][c];
            }
        }

        return new Matrix(result);
    }

    /**
     * Multiplies this matrix by another matrix.
     *
     * @param m Matrix to multiply with
     * @return The resulting matrix
     * @throws UnsupportedOperationException When the matrices' dimensions are incompatible
     */
    @Nonnull
    public Matrix multiply(@Nonnull Matrix m) throws UnsupportedOperationException {
        if (columns != m.rows) {
            throw new UnsupportedOperationException("Matrix dimensions must match for multiplication.");
        }

        final double[][] result = new double[rows][columns];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < m.columns; c++) {
                double sum = 0;

                for (int i = 0; i < columns; i++) {
                    sum += values[r][i] * m.values[i][c];
                }

                result[r][c] = sum;
            }
        }

        return new Matrix(result);
    }

    //
    // Utility
    //

    /**
     * Fills this matrix with given value.
     *
     * @param value Value to fill this matrix with
     */
    public void fill(double value) {
        Numbers.requireFinite(value);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                values[r][c] = value;
            }
        }
    }

    /**
     * Applies given operator to all components of this matrix,
     * then returns a new instance with the resulting values.
     *
     * @param operator Operator to apply to all components of this matrix
     * @return The resulting matrix
     */
    @Nonnull
    public Matrix apply(@Nonnull UnaryOperator<Double> operator) {
        final double[][] result = new double[rows][columns];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                result[r][c] = operator.apply(values[r][c]);
            }
        }

        return new Matrix(result);
    }

    /**
     * Applies given operator to all components of this matrix.
     *
     * @param operator Operator to apply to all components of this matrix
     */
    public void modify(@Nonnull UnaryOperator<Double> operator) {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                values[r][c] = Numbers.requireFinite(operator.apply(values[r][c]));
            }
        }
    }

    /**
     * Returns a resized matrix derived from this matrix.
     *
     * @param r New number of rows
     * @param c New number of columns
     * @return The resized matrix
     */
    @Nonnull
    public Matrix resize(int r, int c) {
        final double[][] result = new double[r][c];

        final int rowCount = Math.min(rows, r);
        final int columnCount = Math.min(columns, c);

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                result[i][j] = values[i][j];
            }
        }

        return new Matrix(result);
    }

    /**
     * Returns a deep copy of this matrix.
     *
     * @return Deep copy of this matrix
     */
    @Nonnull
    public Matrix copy() {
        return new Matrix(values);
    }

    /**
     * Returns an iterator of every component in this matrix.
     *
     * @return Iterator of all components
     */
    @Override
    @Nonnull
    public Iterator<Double> iterator() {
        return Arrays.stream(values()).iterator();
    }

    //
    // Equality
    //

    /**
     * Checks for equality between this matrix and given object.
     *
     * @param obj Object to compare to
     * @return {@code true} if given object is a matrix, and the components are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Matrix m)) return false;
        if (rows != m.rows || columns != m.columns) return false;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                if (values[r][c] != m.values[r][c]) return false;
            }
        }

        return true;
    }

    //
    // Serialization
    //

    /**
     * Parses a string into a matrix.
     *
     * @param s String to parse
     * @return Matrix parsed from given string
     * @throws NumberFormatException    When the string is not parsable to a matrix
     * @throws IllegalArgumentException When the string contains non-finite values
     */
    @Nonnull
    public static Matrix parseMatrix(@Nonnull String s) throws IllegalArgumentException {
        final String[] lines = s.replaceAll("Matrix\\{\n|}", "").split("\n");

        final int rows = lines.length;
        final int columns;
        try {
            columns = lines[0].replaceAll("\\[|]", "").trim().split(", ").length;
        } catch (IndexOutOfBoundsException e) {
            throw new NumberFormatException("String is not a matrix.");
        }

        final double[][] values = new double[rows][columns];

        for (int r = 0; r < lines.length; r++) {
            final String cleanLine = lines[r].replaceAll("\\[|]", "").trim();
            final String[] elements = cleanLine.split(", ");

            for (int c = 0; c < columns; c++) {
                values[r][c] = Double.parseDouble(elements[c]);
            }
        }

        return new Matrix(values);
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

        out.append("Matrix{\n");

        for (int r = 0; r < rows; r++) {
            out.append("  ").append(Arrays.toString(values[r])).append("\n");
        }

        out.append("}");

        return out.toString();
    }
}
