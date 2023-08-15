package civitas.celestis.math.matrix;

import civitas.celestis.math.vector.Int2;
import civitas.celestis.util.grid.Grid;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.UnaryOperator;

/**
 * A matrix of {@code double}s.
 */
public class DoubleMatrix implements Matrix<Double, DoubleMatrix> {
    //
    // Constructors
    //

    /**
     * Creates a new matrix.
     *
     * @param rows    The number of rows to hold
     * @param columns The number of columns to hold
     */
    public DoubleMatrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        this.values = new double[rows][columns];
    }

    /**
     * Creates a new matrix.
     *
     * @param size The size of this matrix
     */
    public DoubleMatrix(@Nonnull Int2 size) {
        this.rows = size.x();
        this.columns = size.y();

        this.values = new double[rows][columns];
    }

    /**
     * Creates a new matrix.
     *
     * @param values The values to hold
     */
    public DoubleMatrix(@Nonnull double[][] values) {
        this.rows = values.length;
        this.columns = rows > 0 ? values[0].length : 0;

        this.values = values;
    }

    /**
     * Creates a new matrix.
     *
     * @param m The grid to copy values from
     */
    public DoubleMatrix(@Nonnull Grid<Double, ?> m) {
        this.rows = m.rows();
        this.columns = m.columns();

        this.values = new double[rows][columns];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                this.values[r][c] = m.get(r, c);
            }
        }
    }

    //
    // Variables
    //

    /**
     * The array of this matrix's values.
     */
    @Nonnull
    protected final double[][] values;

    /**
     * The number of rows
     */
    protected final int rows;

    /**
     * The number of columns
     */
    protected final int columns;

    //
    // Dimensions
    //

    /**
     * {@inheritDoc}
     *
     * @return The height of this matrix
     */
    @Override
    public final int rows() {
        return rows;
    }

    /**
     * {@inheritDoc}
     *
     * @return The width of this matrix
     */
    @Override
    public final int columns() {
        return columns;
    }

    /**
     * {@inheritDoc}
     *
     * @return The area of this matrix
     */
    @Override
    public int area() {
        return rows * columns;
    }

    /**
     * {@inheritDoc}
     *
     * @return The size of this matrix
     */
    @Nonnull
    @Override
    public Int2 size() {
        return new Int2(rows, columns);
    }

    //
    // Arrays
    //

    /**
     * {@inheritDoc}
     *
     * @param i The index of row to get
     * @return The {@code i}th row of this matrix
     * @throws IndexOutOfBoundsException When the index {@code i} is out of bounds
     */
    @Nonnull
    @Override
    public Double[] row(int i) throws IndexOutOfBoundsException {
        return Arrays.stream(values[i]).boxed().toArray(Double[]::new);
    }

    /**
     * {@inheritDoc}
     *
     * @param i The index of column to get
     * @return The {@code i}th column of this matrix
     * @throws IndexOutOfBoundsException When the index {@code i} is out of bounds
     */
    @Nonnull
    @Override
    public Double[] column(int i) throws IndexOutOfBoundsException {
        final Double[] col = new Double[rows];

        for (int r = 0; r < rows; r++) {
            col[r] = values[r][i];
        }

        return col;
    }

    //
    // Values
    //

    /**
     * {@inheritDoc}
     *
     * @param v The value to check for
     * @return {@code true} if this matrix contains given value
     */
    @Override
    public boolean contains(@Nonnull Double v) {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                if (values[r][c] == v) return true;
            }
        }

        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @param i The iterable to check for
     * @return {@code true} if this matrix contains the iterable
     */
    @Override
    public boolean containsAll(@Nonnull Iterable<Double> i) {
        for (final double v : i) {
            if (!contains(v)) return false;
        }

        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @param r The index of the row
     * @param c The index of the column
     * @return The value at the specified position
     * @throws IndexOutOfBoundsException When the index is out of bounds
     */
    @Nonnull
    @Override
    public Double get(int r, int c) throws IndexOutOfBoundsException {
        return values[r][c];
    }

    /**
     * {@inheritDoc}
     *
     * @param p The point of which to get the value of
     * @return The value at the specified position
     * @throws IndexOutOfBoundsException When the point is out of bounds
     */
    @Nonnull
    @Override
    public Double get(@Nonnull Int2 p) throws IndexOutOfBoundsException {
        return values[p.x()][p.y()];
    }

    /**
     * {@inheritDoc}
     *
     * @param r The index of the row
     * @param c The index of the column
     * @param v The value to set to
     * @throws IndexOutOfBoundsException When the index is out of bounds
     */
    @Override
    public void set(int r, int c, @Nonnull Double v) throws IndexOutOfBoundsException {
        values[r][c] = v;
    }

    /**
     * {@inheritDoc}
     *
     * @param p The point of which to set the value of
     * @param v The value to set to
     * @throws IndexOutOfBoundsException When the point is out of bounds
     */
    @Override
    public void set(@Nonnull Int2 p, @Nonnull Double v) throws IndexOutOfBoundsException {
        values[p.x()][p.y()] = v;
    }

    /**
     * Returns a copied 2D array of the value of this matrix
     *
     * @return The values of this matrix
     */
    @Nonnull
    @Override
    public Double[][] values() {
        return Arrays.stream(values).map(r -> Arrays.stream(r).boxed().toArray(Double[]::new)).toArray(Double[][]::new);
    }

    /**
     * Returns a copied array of the values of this matrix
     *
     * @return The array representation of this matrix
     */
    @Nonnull
    @Override
    public Double[] array() {
        final Double[] array = new Double[rows * columns];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                array[r * columns + c] = values[r][c];
            }
        }

        return array;
    }

    //
    // Arithmetic
    //

    /**
     * {@inheritDoc}
     *
     * @param m The matrix to add to this matrix
     * @return The resulting matrix
     */
    @Nonnull
    @Override
    public DoubleMatrix add(@Nonnull DoubleMatrix m) {
        if (rows != m.rows || columns != m.columns) {
            throw new ArithmeticException("Matrix dimensions must match for this operation.");
        }

        final DoubleMatrix result = new DoubleMatrix(rows, columns);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                result.values[r][c] = values[r][c] + m.values[r][c];
            }
        }

        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @param m The matrix to subtract from this matrix
     * @return The resulting matrix
     */
    @Nonnull
    @Override
    public DoubleMatrix subtract(@Nonnull DoubleMatrix m) {
        if (rows != m.rows || columns != m.columns) {
            throw new ArithmeticException("Matrix dimensions must match for this operation.");
        }

        final DoubleMatrix result = new DoubleMatrix(rows, columns);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                result.values[r][c] = values[r][c] - m.values[r][c];
            }
        }

        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @param m The matrix to multiply this matrix by
     * @return The resulting matrix
     */
    @Nonnull
    @Override
    public DoubleMatrix multiply(@Nonnull DoubleMatrix m) {
        if (columns != m.rows) {
            throw new ArithmeticException("Matrix dimensions are incompatible for multiplication.");
        }

        final DoubleMatrix result = new DoubleMatrix(rows, m.columns);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < m.columns; c++) {
                double sum = 0;

                for (int k = 0; k < columns; k++) {
                    sum += values[r][k] * m.values[k][c];
                }

                result.values[r][c] = sum;
            }
        }

        return result;
    }

    //
    // Negation
    //

    /**
     * Negates this matrix.
     *
     * @return The negation of this matrix
     */
    @Nonnull
    @Override
    public DoubleMatrix negate() {
        final DoubleMatrix result = new DoubleMatrix(rows, columns);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                result.values[r][c] = -values[r][c];
            }
        }

        return result;
    }

    //
    // Bulk-operation
    //

    /**
     * {@inheritDoc}
     *
     * @param v The value to fill this array with
     */
    @Override
    public void fill(@Nonnull Double v) {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                values[r][c] = v;
            }
        }
    }


    //
    // Sub-operation
    //

    /**
     * {@inheritDoc}
     *
     * @param r1 The starting point's row index
     * @param r2 The ending point's row index
     * @param c1 The starting point's column index
     * @param c2 The ending point's column index
     * @return The sub-matrix of this matrix
     * @throws IndexOutOfBoundsException When the range is out of bounds
     */
    @Nonnull
    @Override
    public DoubleMatrix get(int r1, int r2, int c1, int c2) throws IndexOutOfBoundsException {
        final DoubleMatrix result = new DoubleMatrix(r2 - r1, c2 - c1);

        for (int r = r1; r < r2; r++) {
            if (c2 - c1 >= 0) System.arraycopy(values[r], c1, result.values[r - r1], 0, c2 - c1);
        }

        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @param p1 The starting point
     * @param p2 The ending point
     * @return The sub-matrix of this matrix
     * @throws IndexOutOfBoundsException When the range is out of bounds
     */
    @Nonnull
    @Override
    public DoubleMatrix get(@Nonnull Int2 p1, @Nonnull Int2 p2) throws IndexOutOfBoundsException {
        final int r1 = p1.x();
        final int r2 = p1.y();
        final int c1 = p2.x();
        final int c2 = p2.y();

        final DoubleMatrix result = new DoubleMatrix(r2 - r1, c2 - c1);

        for (int r = r1; r < r2; r++) {
            if (c2 - c1 >= 0) System.arraycopy(values[r], c1, result.values[r - r1], 0, c2 - c1);
        }

        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @param r1 The starting point's row index
     * @param c1 The starting point's column index
     * @param r2 The ending point's row index
     * @param c2 The ending point's column index
     * @param m  An array containing the values to set to
     * @throws IndexOutOfBoundsException When the range is out of bounds
     */
    @Override
    public void set(int r1, int c1, int r2, int c2, @Nonnull DoubleMatrix m) throws IndexOutOfBoundsException {
        for (int r = r1; r < r2; r++) {
            if (c2 - c1 >= 0) System.arraycopy(m.values[r - r1], 0, values[r], c1, c2 - c1);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param p1 The starting point
     * @param p2 The ending point
     * @param m  An array containing the values to set to
     * @throws IndexOutOfBoundsException When the range is out of bounds
     */
    @Override
    public void set(@Nonnull Int2 p1, @Nonnull Int2 p2, @Nonnull DoubleMatrix m) throws IndexOutOfBoundsException {
        final int r1 = p1.x();
        final int c1 = p1.y();
        final int c2 = p2.y();

        for (int r = r1; r < p2.x(); r++) {
            if (c2 - c1 >= 0) System.arraycopy(m.values[r - r1], 0, values[r], c1, c2 - c1);
        }
    }

    //
    // Transposition
    //

    /**
     * {@inheritDoc}
     *
     * @return The transpose of this matrix
     */
    @Nonnull
    @Override
    public DoubleMatrix transpose() {
        final DoubleMatrix result = new DoubleMatrix(columns, rows);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                result.values[c][r] = values[r][c];
            }
        }

        return result;
    }

    //
    // Copy
    //

    /**
     * {@inheritDoc}
     *
     * @return The copied matrix
     */
    @Nonnull
    @Override
    public DoubleMatrix copy() {
        final DoubleMatrix result = new DoubleMatrix(rows, columns);

        for (int r = 0; r < rows; r++) {
            if (columns >= 0) System.arraycopy(values[r], 0, result.values[r], 0, columns);
        }

        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @param r The number of rows
     * @param c The number of columns
     * @return The resized matrix
     */
    @Nonnull
    @Override
    public DoubleMatrix resize(int r, int c) {
        final DoubleMatrix result = new DoubleMatrix(r, c);

        final int rowCount = Math.min(rows, r);
        final int colCount = Math.min(columns, c);

        for (int i = 0; i < rowCount; i++) {
            if (colCount >= 0) System.arraycopy(values[i], 0, result.values[i], 0, colCount);
        }

        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @param size The size of the new matrix
     * @return The resized matrix
     */
    @Nonnull
    @Override
    public DoubleMatrix resize(@Nonnull Int2 size) {
        final int r = size.x();
        final int c = size.y();

        final DoubleMatrix result = new DoubleMatrix(r, c);

        final int rowCount = Math.min(rows, r);
        final int colCount = Math.min(columns, c);

        for (int i = 0; i < rowCount; i++) {
            if (colCount >= 0) System.arraycopy(values[i], 0, result.values[i], 0, colCount);
        }

        return result;
    }

    //
    // Transformation
    //

    /**
     * {@inheritDoc}
     *
     * @param transformer The function to apply to each element of this matrix
     * @return The resulting matrix
     */
    @Nonnull
    @Override
    public DoubleMatrix transform(@Nonnull UnaryOperator<Double> transformer) {
        final DoubleMatrix result = new DoubleMatrix(rows, columns);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                result.values[r][c] = transformer.apply(values[r][c]);
            }
        }

        return result;
    }


    //
    // Equality
    //

    /**
     * {@inheritDoc}
     *
     * @param obj The object to compare to
     * @return {@code true} if the other object is an {@link Grid} which contains {@code double}s,
     * and the dimensions and components are equal
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof Grid<?, ?> a)) return false;
        if (rows != a.rows() || columns != a.columns()) return false;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                if (!Objects.equals(values[r][c], a.get(r, c))) return false;
            }
        }

        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @param m The array to compare to
     * @return {@code true} if the dimensions and components are equal
     */
    @Override
    public boolean equals(@Nonnull DoubleMatrix m) {
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
     */
    @Nonnull
    public static DoubleMatrix parseMatrix(@Nonnull String s) throws IllegalArgumentException {
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

        return new DoubleMatrix(values);
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
