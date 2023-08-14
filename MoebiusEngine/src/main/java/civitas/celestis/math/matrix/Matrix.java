package civitas.celestis.math.matrix;

import civitas.celestis.math.Numeric;
import civitas.celestis.math.natural.NaturalVector2;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.UnaryOperator;

/**
 * A two-dimensional array of {@code double} components.
 * The dimensions of a matrix cannot be changed after its inception.
 */
public class Matrix implements Numeric, Iterable<Double> {
    //
    // Factory
    //

    /**
     * Creates a new matrix from ths given array of values.
     *
     * @param values The values to contain
     * @return The constructed matrix
     */
    @Nonnull
    public static Matrix of(@Nonnull double[][] values) {
        final int rows = values.length;
        final int columns = rows > 0 ? values[0].length : 0;

        final double[][] copied = new double[rows][columns];

        for (int r = 0; r < rows; r++) {
            System.arraycopy(values[r], 0, copied[r], 0, columns);
        }

        return new Matrix(copied);
    }

    /**
     * Performs a deep copy of the given matrix {@code m}.
     *
     * @param m The matrix to copy
     * @return A deep copy of {@code m}
     */
    @Nonnull
    public static Matrix copyOf(@Nonnull Matrix m) {
        final Matrix copy = new Matrix(m.rows, m.columns);

        for (int r = 0; r < m.rows; r++) {
            if (m.columns >= 0) System.arraycopy(m.values[r], 0, copy.values[r], 0, m.columns);
        }

        return copy;
    }

    //
    // Constructors
    //

    /**
     * Creates a new matrix.
     *
     * @param size The size of this matrix
     */
    public Matrix(@Nonnull NaturalVector2 size) {
        final int r = size.x();
        final int c = size.y();

        this.rows = r;
        this.columns = c;
        this.values = new double[r][c];
    }

    /**
     * Creates a new matrix.
     *
     * @param rows    The number of rows
     * @param columns The number of columns
     */
    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.values = new double[rows][columns];
    }

    /**
     * Creates a new matrix.
     *
     * @param size  The size of this matrix
     * @param value The initial value to fill this matrix with
     */
    public Matrix(@Nonnull NaturalVector2 size, double value) {
        final int height = size.x();
        final int width = size.y();

        this.rows = height;
        this.columns = width;
        this.values = new double[height][width];

        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                values[r][c] = value;
            }
        }
    }

    /**
     * Creates a new matrix.
     *
     * @param rows    The number of rows
     * @param columns The number of columns
     * @param value   The initial value to fill this matrix with
     */
    public Matrix(int rows, int columns, double value) {
        this.rows = rows;
        this.columns = columns;
        this.values = new double[rows][columns];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                values[r][c] = value;
            }
        }
    }

    /**
     * Creates a new matrix.
     *
     * @param values The values to contain
     */
    public Matrix(@Nonnull double[][] values) {
        this.rows = values.length;
        this.columns = rows > 0 ? values[0].length : 0;
        this.values = values;
    }

    /**
     * Creates a new matrix.
     *
     * @param values  The values to contain
     * @param rows    The number of rows
     * @param columns The number of columns
     */
    public Matrix(@Nonnull double[][] values, int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.values = values;
    }

    /**
     * Creates a new matrix.
     *
     * @param array The 1D array from which to copy values from
     * @param rows  The number of rows
     * @throws IllegalArgumentException When the array's length is not mappable into a 2D array
     */
    public Matrix(@Nonnull double[] array, int rows) {
        this.rows = rows;
        this.columns = rows > 0 ? array.length / rows : 0;

        if (rows * columns != array.length) {
            throw new IllegalArgumentException("The length of the array if not a multiple of the provided row count.");
        }

        this.values = new double[rows][columns];

        for (int r = 0; r < rows; r++) {
            System.arraycopy(array, r * columns, values[r], 0, columns);
        }
    }

    //
    // Variables
    //
    @Nonnull
    protected final double[][] values;
    protected final int rows;
    protected final int columns;

    //
    // Getters
    //

    /**
     * Returns the component at the specified index.
     *
     * @param r The index of row to get
     * @param c The index of column to get
     * @return The component at the specified index
     * @throws IndexOutOfBoundsException When either the index {@code r} ir {@code c} is out of bounds
     */
    public final double get(int r, int c) throws IndexOutOfBoundsException {
        return values[r][c];
    }

    /**
     * Returns the component at the specified point.
     *
     * @param p The point of which to get the component value of
     * @return The component at the specified index
     * @throws IndexOutOfBoundsException When the index {@code p} is out of bounds
     */
    public final double get(@Nonnull NaturalVector2 p) throws IndexOutOfBoundsException {
        return values[p.x()][p.y()];
    }

    /**
     * Returns a direct reference to this array's values.
     * Changes in the return value of this method will be reflected in this matrix
     *
     * @return A direct reference to this array's values
     */
    @Nonnull
    public double[][] values() {
        return values;
    }

    /**
     * Returns a 1D array of this matrix's values.
     * Values are mapped from top-to-bottom, then left-to-right.
     *
     * @return A 1D array representation of this matrix
     */
    @Nonnull
    public final double[] values1() {
        final double[] array = new double[area()];

        for (int r = 0; r < rows; r++) {
            if (columns >= 0) System.arraycopy(values[r], 0, array, r * columns, columns);
        }

        return array;
    }

    /**
     * Returns a direct reference to the {@code i}th row of this matrix.
     *
     * @param i The index of row to get
     * @return A direct reference to the {@code i}th row of this matrix
     * @throws IndexOutOfBoundsException When the index {@code i} is out of bounds
     */
    @Nonnull
    public double[] row(int i) throws IndexOutOfBoundsException {
        return values[i];
    }

    /**
     * Returns a copied array representing the {@code i}th column of this matrix.
     * Changes in the return value of this method will not be reflected in this matrix.
     *
     * @param i The index of column to get
     * @return The {@code i}th column of this matrix
     * @throws IndexOutOfBoundsException When the index {@code i} if out of bounds
     */
    @Nonnull
    public final double[] column(int i) throws IndexOutOfBoundsException {
        if (i >= columns) {
            throw new IndexOutOfBoundsException("The index " + i + " is out of bounds for this matrix.");
        }

        final double[] column = new double[rows];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                if (c == i) {
                    column[r] = values[r][c];
                    break;
                }
            }
        }

        return column;
    }

    /**
     * Returns a sub-array of this matrix.
     * Changes in the return value of this method will not be reflected in this matrix.
     *
     * @param r1 The starting point's row index
     * @param c1 The starting point's column index
     * @param r2 The ending point's row index
     * @param c2 The ending point's column index
     * @return The sub-array of the specified range
     * @throws IndexOutOfBoundsException When the indexes are out of bounds
     */
    @Nonnull
    public final double[][] subArray(int r1, int c1, int r2, int c2) throws IndexOutOfBoundsException {
        final double[][] result = new double[r2 - r1][c2 - c1];

        for (int r = r1; r < r2; r++) {
            if (c2 - c1 >= 0) System.arraycopy(values[r], c1, result[r - r1], 0, c2 - c1);
        }

        return result;
    }

    /**
     * Returns a sub-array of this matrix.
     * Changes in the return value of this method will not be reflected in this matrix.
     *
     * @param p1 The starting point
     * @param p2 The ending point
     * @return The sub-array of the specified range
     * @throws IndexOutOfBoundsException WHen the points are out of bounds
     */
    @Nonnull
    public final double[][] subArray(@Nonnull NaturalVector2 p1, @Nonnull NaturalVector2 p2) throws IndexOutOfBoundsException {
        final int r1 = p1.x();
        final int c1 = p1.y();
        final int r2 = p2.x();
        final int c2 = p2.y();

        final double[][] result = new double[r2 - r1][c2 - c1];

        for (int r = r1; r < r2; r++) {
            if (c2 - c1 >= 0) System.arraycopy(values[r], c1, result[r - r1], 0, c2 - c1);
        }

        return result;
    }

    /**
     * Returns a sub-matrix of this matrix.
     *
     * @param r1 The starting point's row index
     * @param c1 The starting point's column index
     * @param r2 The ending point's row index
     * @param c2 The ending point's column index
     * @return The sub-matrix of the specified range
     * @throws IndexOutOfBoundsException When the indexes are out of bounds
     */
    @Nonnull
    public Matrix subMatrix(int r1, int c1, int r2, int c2) throws IndexOutOfBoundsException {
        return new Matrix(subArray(r1, c1, r2, c2));
    }

    /**
     * Returns a sub-matrix of this matrix.
     *
     * @param p1 The starting point
     * @param p2 The ending point
     * @return The sub-matrix of the specified range
     * @throws IndexOutOfBoundsException When the points are out of bounds
     */
    @Nonnull
    public Matrix subMatrix(@Nonnull NaturalVector2 p1, @Nonnull NaturalVector2 p2) throws IndexOutOfBoundsException {
        return new Matrix(subArray(p1, p2));
    }

    //
    // Properties
    //

    /**
     * Returns the number of rows this matrix has.
     *
     * @return The number of rows (the height)
     */
    public final int rows() {
        return rows;
    }

    /**
     * Returns the number of columns this matrix has.
     *
     * @return The number of columns (the width)
     */
    public final int columns() {
        return columns;
    }

    /**
     * Returns the size of this matrix, mapped as a vector.
     * The X component of the vector represents the row count,
     * while the Y component of ths vector represents the column count.
     *
     * @return The size of this matrix
     */
    @Nonnull
    public final NaturalVector2 size() {
        return new NaturalVector2(rows, columns);
    }

    /**
     * Returns the area of this matrix. ({@code rows * columns})
     *
     * @return The area of this matrix
     */
    public final int area() {
        return rows * columns;
    }

    //
    // Setters
    //

    /**
     * Sets the value of the specified index.
     *
     * @param r The index of row to set
     * @param c The index of column to set
     * @param v The value to set to
     * @throws IndexOutOfBoundsException When either the index {@code r} or {@code c} is out of bounds
     */
    public void set(int r, int c, double v) throws IndexOutOfBoundsException {
        values[r][c] = v;
    }

    /**
     * Sets the value of the specified point.
     *
     * @param p The point to set
     * @param v The value to set to
     * @throws IndexOutOfBoundsException When the point {@code p} is out of bounds
     */
    public void set(@Nonnull NaturalVector2 p, double v) throws IndexOutOfBoundsException {
        values[p.x()][p.y()] = v;
    }

    /**
     * Sets a range of this matrix's values.
     *
     * @param r1     The starting point's row index
     * @param c1     The starting point's column index
     * @param r2     The ending point's row index
     * @param c2     The ending point's column index
     * @param values The values of which to set to
     * @throws IndexOutOfBoundsException When the indexes are out of bounds
     */
    public void set(int r1, int c1, int r2, int c2, @Nonnull double[][] values) throws IndexOutOfBoundsException {
        for (int r = r1; r < r2; r++) {
            if (c2 - c1 >= 0) System.arraycopy(values[r - r1], 0, this.values[r], c1, c2 - c1);
        }
    }

    /**
     * Sets a range of this matrix's values.
     *
     * @param p1     The starting point
     * @param p2     The ending point
     * @param values The values of which to set to
     * @throws IndexOutOfBoundsException When the points are out of bounds
     */
    public void set(@Nonnull NaturalVector2 p1, @Nonnull NaturalVector2 p2, @Nonnull double[][] values) throws IndexOutOfBoundsException {
        final int r1 = p1.x();
        final int c1 = p1.y();
        final int c2 = p2.y();

        for (int r = r1; r < p2.x(); r++) {
            if (c2 - c1 >= 0) System.arraycopy(values[r - r1], 0, this.values[r], c1, c2 - c1);
        }
    }

    /**
     * Sets a range of this matrix's values.
     *
     * @param r1 The starting point's row index
     * @param c1 The starting point's column index
     * @param r2 The ending point's row index
     * @param c2 The ending point's column index
     * @param m  The matrix to copy component values from
     * @throws IndexOutOfBoundsException When the indexes are out of bounds
     */
    public void set(int r1, int c1, int r2, int c2, @Nonnull Matrix m) throws IndexOutOfBoundsException {
        for (int r = r1; r < r2; r++) {
            if (c2 - c1 >= 0) System.arraycopy(m.values[r - r1], 0, this.values[r], c1, c2 - c1);
        }
    }

    /**
     * Sets a range of this matrix's values.
     *
     * @param p1 The starting point
     * @param p2 The ending point
     * @param m  The matrix to copy component values from
     * @throws IndexOutOfBoundsException When the points are out of bounds
     */
    public void set(@Nonnull NaturalVector2 p1, @Nonnull NaturalVector2 p2, @Nonnull Matrix m) throws IndexOutOfBoundsException {
        final int r1 = p1.x();
        final int c1 = p1.y();
        final int c2 = p2.y();

        for (int r = r1; r < p2.x(); r++) {
            if (c2 - c1 >= 0) System.arraycopy(m.values[r - r1], 0, this.values[r], c1, c2 - c1);
        }
    }

    //
    // Arithmetic
    //

    /**
     * Adds a scalar to this matrix.
     *
     * @param s The scalar to add to this matrix
     * @return The resulting matrix
     */
    @Nonnull
    public Matrix add(double s) {
        final Matrix result = new Matrix(rows, columns);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                result.values[r][c] = values[r][c] + s;
            }
        }

        return result;
    }

    /**
     * Subtracts a scalar from this matrix.
     *
     * @param s The scalar to subtract from this matrix
     * @return The resulting matrix
     */
    @Nonnull
    public Matrix subtract(double s) {
        final Matrix result = new Matrix(rows, columns);

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
    public Matrix multiply(double s) {
        final Matrix result = new Matrix(rows, columns);

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
     */
    @Nonnull
    public Matrix divide(double s) {
        final Matrix result = new Matrix(rows, columns);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                result.values[r][c] = values[r][c] / s;
            }
        }

        return result;
    }

    /**
     * Adds another matrix to this matrix.
     *
     * @param m The matrix to add to this matrix
     * @return The resulting matrix
     * @throws IllegalArgumentException When the dimensions are not equal
     */
    @Nonnull
    public Matrix add(@Nonnull Matrix m) throws IllegalArgumentException {
        if (rows != m.rows || columns != m.columns) {
            throw new IllegalArgumentException("The matrices dimensions must match to perform this operation.");
        }

        final Matrix result = new Matrix(rows, columns);

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
     * @throws IllegalArgumentException When the dimensions are not equal
     */
    @Nonnull
    public Matrix subtract(@Nonnull Matrix m) throws IllegalArgumentException {
        if (rows != m.rows || columns != m.columns) {
            throw new IllegalArgumentException("The matrices dimensions must match to perform this operation.");
        }

        final Matrix result = new Matrix(rows, columns);

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
     * @param m The matrix to multiply this matrix by
     * @return The resulting matrix
     * @throws IllegalArgumentException When the dimensions are incompatible
     */
    @Nonnull
    public Matrix multiply(@Nonnull Matrix m) throws IllegalArgumentException {
        if (columns != m.rows) {
            throw new IllegalArgumentException("Matrix dimensions are incompatible for multiplication.");
        }

        final Matrix result = new Matrix(rows, columns);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < m.columns; c++) {
                double sum = 0;

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
     * Returns a matrix where the given operator is applied to every component of this matrix.
     *
     * @param operator The operator to apply to each component of this matrix
     * @return The resulting matrix
     */
    @Nonnull
    public Matrix apply(@Nonnull UnaryOperator<Double> operator) {
        final Matrix result = new Matrix(rows, columns);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                result.values[r][c] = operator.apply(values[r][c]);
            }
        }

        return result;
    }

    /**
     * Negates this matrix.
     *
     * @return The negation of this matrix
     */
    @Nonnull
    public Matrix negate() {
        final Matrix result = new Matrix(rows, columns);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                result.values[r][c] = -values[r][c];
            }
        }

        return result;
    }

    /**
     * Transposes this matrix. This swaps the rows and columns.
     * When this matrix is a {@code m * n} matrix, this method will return a {@code n * m}
     * matrix with the values mapped to {@code f(p{x, y}) -> q{y, x}}.
     *
     * @return The transpose of this matrix
     */
    @Nonnull
    public Matrix transpose() {
        final Matrix result = new Matrix(columns, rows);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                result.values[c][r] = values[r][c];
            }
        }

        return result;
    }

    /**
     * Returns the maximum column sum norm (the 1-norm) of this matrix.
     *
     * @return The 1-norm of this matrix
     */
    public final double norm1() {
        double v = 0;

        for (int r = 0; r < rows; r++) {
            double sum = 0;

            for (int c = 0; c < columns; c++) {
                sum += Math.abs(values[c][r]);
            }

            v = Math.max(v, sum);
        }

        return v;
    }

    /**
     * Returns the infinity-norm (the absolute row sum) of this matrix.
     *
     * @return The infinity-norm of this matrix
     */
    public final double normInf() {
        double v = 0;

        for (int r = 0; r < rows; r++) {
            double sum = 0;

            for (int c = 0; c < columns; c++) {
                sum += Math.abs(values[r][c]);
            }

            v = Math.max(v, sum);
        }

        return v;
    }

    /**
     * Returns a resized matrix with the components mapped by matrix cropping.
     *
     * @param rows    The number of rows to resize to
     * @param columns The number of columns to resize to
     * @return The resized matrix
     */
    @Nonnull
    public Matrix resize(int rows, int columns) {
        final Matrix result = new Matrix(rows, columns);

        final int rowCount = Math.min(this.rows, rows);
        final int colCount = Math.min(this.columns, columns);

        for (int r = 0; r < rowCount; r++) {
            if (colCount >= 0) System.arraycopy(values[r], 0, result.values[r], 0, colCount);
        }

        return result;
    }

    /**
     * Returns a resized matrix with the components mapped by matrix cropping.
     *
     * @param size The new size of this matrix
     * @return The resized matrix
     */
    @Nonnull
    public Matrix resize(@Nonnull NaturalVector2 size) {
        final int height = size.x();
        final int width = size.y();

        final Matrix result = new Matrix(height, width);

        final int rowCount = Math.min(this.rows, height);
        final int colCount = Math.min(this.columns, width);

        for (int r = 0; r < rowCount; r++) {
            if (colCount >= 0) System.arraycopy(values[r], 0, result.values[r], 0, colCount);
        }

        return result;
    }

    /**
     * Returns an iterator of all components of this matrix.
     *
     * @return An iterator of all components of this matrix
     */
    @Override
    @Nonnull
    public Iterator<Double> iterator() {
        return Arrays.stream(values1()).iterator();
    }

    //
    // Equality
    //

    /**
     * Checks for equality between this matrix and the given object {@code obj}.
     *
     * @param obj The object to compare to
     * @return {@code true} if the other object is a matrix, and the dimensions and components are equal
     */
    @Override
    public boolean equals(@Nullable Object obj) {
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
     * Deserializes a string into a matrix.
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
