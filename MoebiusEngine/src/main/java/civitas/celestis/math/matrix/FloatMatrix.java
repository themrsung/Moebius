package civitas.celestis.math.matrix;

import civitas.celestis.math.vector.Int2;
import civitas.celestis.util.group.Grid;
import civitas.celestis.util.tuple.ArrayTuple;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

/**
 * A matrix of {@code float}s.
 */
public class FloatMatrix implements Matrix<Float, FloatMatrix> {
    //
    // Constructors
    //

    /**
     * Creates a new matrix.
     *
     * @param values The values to contain in this matrix
     */
    public FloatMatrix(@Nonnull float[][] values) {
        this.values = values;
        this.rows = values.length;
        this.columns = rows > 0 ? values[0].length : 0;
    }

    /**
     * Creates a new matrix.
     *
     * @param r The number of rows this matrix should have
     * @param c The number of columns this matrix should have
     */
    public FloatMatrix(int r, int c) {
        this.values = new float[r][c];
        this.rows = r;
        this.columns = c;
    }

    /**
     * Creates a new matrix.
     *
     * @param size The size of this matrix
     */
    public FloatMatrix(@Nonnull Int2 size) {
        this.rows = size.x();
        this.columns = size.y();
        this.values = new float[rows][columns];
    }

    /**
     * Creates a new matrix.
     *
     * @param g The grid of which to copy properties from
     */
    public FloatMatrix(@Nonnull Grid<? extends Number> g) {
        this.rows = g.rows();
        this.columns = g.columns();
        this.values = new float[rows][columns];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                values[r][c] = (float) g.get(r, c);
            }
        }
    }

    //
    // Variables
    //

    /**
     * The 2D array storing the values of this matrix.
     */
    @Nonnull
    protected final float[][] values;

    /**
     * The number of rows.
     */
    protected final int rows;

    /**
     * The number of columns.
     */
    protected final int columns;

    //
    // Dimensions
    //

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int rows() {
        return rows;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int columns() {
        return columns;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int area() {
        return rows * columns;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Int2 size() {
        return new Int2(rows, columns);
    }

    //
    // Rows / Columns
    //

    /**
     * {@inheritDoc}
     *
     * @param r The index of row to get
     * @return {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Nonnull
    @Override
    public ArrayTuple<Float> row(int r) throws IndexOutOfBoundsException {
        final Float[] row = new Float[columns];

        for (int c = 0; c < columns; c++) {
            row[c] = values[r][c];
        }

        return new ArrayTuple<>(row);
    }

    /**
     * {@inheritDoc}
     *
     * @param c The index of column to get
     * @return {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Nonnull
    @Override
    public ArrayTuple<Float> column(int c) throws IndexOutOfBoundsException {
        final Float[] col = new Float[rows];

        for (int r = 0; r < rows; r++) {
            col[r] = values[r][c];
        }

        return new ArrayTuple<>(col);
    }

    //
    // Containment
    //

    /**
     * {@inheritDoc}
     *
     * @param obj The object to check for containment
     * @return {@inheritDoc}
     */
    @Override
    public boolean contains(@Nullable Object obj) {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                if (Objects.equals(values[r][c], obj)) return true;
            }
        }

        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @param i The iterable object to check for containment
     * @return {@inheritDoc}
     */
    @Override
    public boolean containsAll(@Nonnull Iterable<Float> i) {
        for (final float v : i) {
            if (!contains(v)) return false;
        }

        return true;
    }

    //
    // Getters
    //

    /**
     * {@inheritDoc}
     *
     * @param r The index of row to get
     * @param c The index of column to get
     * @return {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public Float get(int r, int c) throws IndexOutOfBoundsException {
        return values[r][c];
    }

    /**
     * {@inheritDoc}
     *
     * @param i The index of element to get
     * @return {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public Float get(@Nonnull Int2 i) throws IndexOutOfBoundsException {
        return values[i.x()][i.y()];
    }

    //
    // Setters
    //

    /**
     * {@inheritDoc}
     *
     * @param r The index of row to set
     * @param c The index of column to set
     * @param v The value to set to
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public void set(int r, int c, Float v) throws IndexOutOfBoundsException {
        values[r][c] = v;
    }

    /**
     * {@inheritDoc}
     *
     * @param i The index of element to set
     * @param v The value to set to
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public void set(@Nonnull Int2 i, Float v) throws IndexOutOfBoundsException {
        values[i.x()][i.y()] = v;
    }

    /**
     * {@inheritDoc}
     *
     * @param v The value to fill this grid with
     */
    @Override
    public void fill(Float v) {
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
     * @param c1 The starting point's column index
     * @param r2 The ending point's row index
     * @param c2 The ending point's column index
     * @return {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Nonnull
    @Override
    public FloatMatrix get(int r1, int c1, int r2, int c2) throws IndexOutOfBoundsException {
        final FloatMatrix result = new FloatMatrix(r2 - r1, c2 - c1);

        for (int r = r1; r < r2; r++) {
            if (c2 - c1 >= 0) System.arraycopy(values[r], c1, result.values[r - r1], 0, c2 - c1);
        }

        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @param i1 The starting point's index
     * @param i2 The ending point's index
     * @return {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Nonnull
    @Override
    public FloatMatrix get(@Nonnull Int2 i1, @Nonnull Int2 i2) throws IndexOutOfBoundsException {
        final int r1 = i1.x();
        final int c1 = i1.y();
        final int r2 = i2.x();
        final int c2 = i2.y();

        final FloatMatrix result = new FloatMatrix(r2 - r1, c2 - c1);

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
     * @param g  The grid containing the values to assign
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public void set(int r1, int c1, int r2, int c2, @Nonnull Grid<Float> g) throws IndexOutOfBoundsException {
        for (int r = r1; r < r2; r++) {
            for (int c = c1; c < c2; c++) {
                values[r][c] = g.get(r - r1, c - c1);
            }
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param i1 The starting point's index
     * @param i2 The ending point's index
     * @param g  The grid containing the values to assign
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public void set(@Nonnull Int2 i1, @Nonnull Int2 i2, @Nonnull Grid<Float> g) throws IndexOutOfBoundsException {
        final int r1 = i1.x();
        final int c1 = i1.y();

        for (int r = r1; r < i2.x(); r++) {
            for (int c = c1; c < i2.y(); c++) {
                values[r][c] = g.get(r - r1, c - c1);
            }
        }
    }

    //
    // Arithmetic
    //

    /**
     * {@inheritDoc}
     *
     * @param m The matrix to add to this matrix
     * @return {@inheritDoc}
     * @throws ArithmeticException {@inheritDoc}
     */
    @Nonnull
    @Override
    public FloatMatrix add(@Nonnull FloatMatrix m) throws ArithmeticException {
        if (rows != m.rows || columns != m.columns) {
            throw new ArithmeticException("Matrix dimensions must match for addition.");
        }

        final FloatMatrix result = new FloatMatrix(rows, columns);

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
     * @return {@inheritDoc}
     * @throws ArithmeticException {@inheritDoc}
     */
    @Nonnull
    @Override
    public FloatMatrix subtract(@Nonnull FloatMatrix m) throws ArithmeticException {
        if (rows != m.rows || columns != m.columns) {
            throw new ArithmeticException("Matrix dimensions must match for subtraction.");
        }

        final FloatMatrix result = new FloatMatrix(rows, columns);

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
     * @return {@inheritDoc}
     * @throws ArithmeticException {@inheritDoc}
     */
    @Nonnull
    @Override
    public FloatMatrix multiply(@Nonnull FloatMatrix m) throws ArithmeticException {
        if (columns != m.rows) {
            throw new ArithmeticException("Number of columns in the first matrix must be equal to the number of rows in the second matrix.");
        }

        final FloatMatrix result = new FloatMatrix(rows, m.columns);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < m.columns; j++) {
                float sum = 0;
                for (int k = 0; k < columns; k++) {
                    sum += values[i][k] * m.values[k][j];
                }
                result.values[i][j] = sum;
            }
        }

        return result;
    }

    //
    // Negation
    //

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public FloatMatrix negate() {
        final FloatMatrix result = new FloatMatrix(rows, columns);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                result.values[r][c] = -values[r][c];
            }
        }

        return result;
    }

    //
    // Transformation
    //

    /**
     * {@inheritDoc}
     *
     * @param f The function to apply to each element of this grid
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public FloatMatrix transform(@Nonnull UnaryOperator<Float> f) {
        final FloatMatrix result = new FloatMatrix(rows, columns);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                result.values[r][c] = f.apply(values[r][c]);
            }
        }

        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public FloatMatrix transpose() {
        final FloatMatrix result = new FloatMatrix(columns, rows);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                result.values[c][r] = values[r][c];
            }
        }

        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @param r The number of rows the new grid should have
     * @param c The number of columns the new grid should have
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public FloatMatrix resize(int r, int c) {
        final FloatMatrix result = new FloatMatrix(r, c);

        final int rowCount = Math.min(rows, r);
        final int colCount = Math.min(columns, c);

        for (int i = 0; i < rowCount; i++) {
            System.arraycopy(values[i], 0, result.values[i], 0, colCount);
        }

        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @param size The size of the new grid
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public FloatMatrix resize(@Nonnull Int2 size) {
        final int r = size.x();
        final int c = size.y();
        final FloatMatrix result = new FloatMatrix(r, c);

        final int rowCount = Math.min(rows, r);
        final int colCount = Math.min(columns, c);

        for (int i = 0; i < rowCount; i++) {
            System.arraycopy(values[i], 0, result.values[i], 0, colCount);
        }

        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @param g The other grid to merge with
     * @param f The merger function to apply
     * @return {@inheritDoc}
     * @throws IllegalArgumentException {@inheritDoc}
     */
    @Nonnull
    @Override
    public FloatMatrix merge(@Nonnull Grid<Float> g, @Nonnull BinaryOperator<Float> f) throws IllegalArgumentException {
        if (!size().equals(g.size())) {
            throw new IllegalArgumentException("Grid dimensions must match for this operation.");
        }

        final FloatMatrix result = new FloatMatrix(rows, columns);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                result.values[r][c] = f.apply(values[r][c], g.get(r, c));
            }
        }

        return result;
    }

    //
    // List Conversion
    //

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public List<Float> list() {
        final List<Float> list = new ArrayList<>();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                list.add(values[r][c]);
            }
        }

        return list;
    }

    //
    // Iteration
    //

    /**
     * Converts this matrix into a one-dimensional array.
     *
     * @return The array representation of this matrix
     */
    @Nonnull
    public float[] array() {
        final float[] array = new float[area()];

        for (int r = 0; r < rows; r++) {
            System.arraycopy(values[r], 0, array, r * columns, columns);
        }

        return array;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    @Nonnull
    public Iterator<Float> iterator() {
        return list().iterator();
    }


    //
    // Equality
    //

    /**
     * {@inheritDoc}
     *
     * @param obj The object to compare to
     * @return {@inheritDoc}
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof Grid<?> g)) return false;
        if (!size().equals(g.size())) return false;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                if (!Objects.equals(values[r][c], g.get(r, c))) return false;
            }
        }

        return true;
    }

    //
    // Serialization
    //

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    @Nonnull
    public String toString() {
        final StringBuilder out = new StringBuilder();

        out.append("Grid{\n");

        for (int r = 0; r < rows; r++) {
            out.append("  ");
            out.append(Arrays.toString(values[r]));
            out.append(",\n");
        }

        out.append("}");

        return out.toString();
    }
}