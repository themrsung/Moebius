package civitas.celestis.util.group;

import civitas.celestis.math.vector.Int2;
import civitas.celestis.util.tuple.ArrayTuple;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * A grid which uses a two-dimensional array to store its values.
 *
 * @param <E> The type of element to store in this grid
 */
public class ArrayGrid<E> implements Grid<E> {
    //
    // Constructors
    //

    /**
     * Creates a new grid.
     *
     * @param r The number of rows
     * @param c The number of columns
     */
    @SuppressWarnings("unchecked")
    public ArrayGrid(int r, int c) {
        this.values = (E[][]) new Object[r][c];
        this.rows = r;
        this.columns = c;
    }

    /**
     * Creates a new grid.
     *
     * @param size The dimensions of this grid
     */
    @SuppressWarnings("unchecked")
    public ArrayGrid(@Nonnull Int2 size) {
        this.rows = size.x();
        this.columns = size.y();
        this.values = (E[][]) new Object[rows][columns];
    }

    /**
     * Creates a new grid.
     *
     * @param values The values to contain in this grid
     */
    public ArrayGrid(@Nonnull E[][] values) {
        this.values = values;
        this.rows = values.length;
        this.columns = rows > 0 ? values[0].length : 0;
    }

    /**
     * Creates a new grid.
     *
     * @param g The grid of which to copy component values from
     */
    @SuppressWarnings("unchecked")
    public ArrayGrid(@Nonnull Grid<E> g) {
        this.rows = g.rows();
        this.columns = g.columns();
        this.values = (E[][]) new Object[rows][columns];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                this.values[r][c] = g.get(r, c);
            }
        }
    }


    //
    // Variables
    //

    /**
     * The two-dimensional array of values.
     */
    @Nonnull
    private final E[][] values;

    /**
     * The number of rows.
     */
    private final int rows;

    /**
     * The number of columns.
     */
    private final int columns;

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

    /**
     * {@inheritDoc}
     *
     * @param r The index of row to get
     * @return {@inheritDoc}
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Nonnull
    @Override
    public ArrayTuple<E> row(int r) throws IndexOutOfBoundsException {
        return new ArrayTuple<>(values[r]);
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
    @SuppressWarnings("unchecked")
    public ArrayTuple<E> column(int c) throws IndexOutOfBoundsException {
        final Object[] column = new Object[rows];

        for (int r = 0; r < rows; r++) {
            column[r] = values[r][c];
        }

        return new ArrayTuple<>((E[]) column);
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
    public boolean containsAll(@Nonnull Iterable<E> i) {
        for (final E e : i) {
            if (!contains(e)) return false;
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
    public E get(int r, int c) throws IndexOutOfBoundsException {
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
    public E get(@Nonnull Int2 i) throws IndexOutOfBoundsException {
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
    public void set(int r, int c, E v) throws IndexOutOfBoundsException {
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
    public void set(@Nonnull Int2 i, E v) throws IndexOutOfBoundsException {
        values[i.x()][i.y()] = v;
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
    public ArrayGrid<E> get(int r1, int c1, int r2, int c2) throws IndexOutOfBoundsException {
        final ArrayGrid<E> result = new ArrayGrid<>(r2 - r1, c2 - c1);

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
    public ArrayGrid<E> get(@Nonnull Int2 i1, @Nonnull Int2 i2) throws IndexOutOfBoundsException {
        final int r1 = i1.x();
        final int c1 = i1.y();
        final int r2 = i2.x();
        final int c2 = i2.y();

        final ArrayGrid<E> result = new ArrayGrid<>(r2 - r1, c2 - c1);

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
    public void set(int r1, int c1, int r2, int c2, @Nonnull Grid<E> g) throws IndexOutOfBoundsException {
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
    public void set(@Nonnull Int2 i1, @Nonnull Int2 i2, @Nonnull Grid<E> g) throws IndexOutOfBoundsException {
        final int r1 = i1.x();
        final int c1 = i1.y();

        for (int r = r1; r < i2.x(); r++) {
            for (int c = c1; c < i2.y(); c++) {
                values[r][c] = g.get(r - r1, c - c1);
            }
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param v The value to fill this grid with
     */
    @Override
    public void fill(E v) {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                values[r][c] = v;
            }
        }
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
    public ArrayGrid<E> transform(@Nonnull Function<? super E, E> f) {
        final ArrayGrid<E> result = new ArrayGrid<>(rows, columns);

        for (int r = 0; r < rows; r++) {
            System.arraycopy(values[r], 0, result.values[r], 0, columns);
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
    public ArrayGrid<E> transpose() {
        final ArrayGrid<E> result = new ArrayGrid<>(columns, rows);

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
    public ArrayGrid<E> resize(int r, int c) {
        final ArrayGrid<E> result = new ArrayGrid<>(r, c);

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
    public ArrayGrid<E> resize(@Nonnull Int2 size) {
        final int r = size.x();
        final int c = size.y();

        final ArrayGrid<E> result = new ArrayGrid<>(r, c);

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
    public ArrayGrid<E> merge(@Nonnull Grid<E> g, @Nonnull BinaryOperator<E> f) throws IllegalArgumentException {
        if (!size().equals(g.size())) {
            throw new IllegalArgumentException("Grid dimensions must match for this operation.");
        }

        final ArrayGrid<E> result = new ArrayGrid<>(rows, columns);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                result.values[r][c] = f.apply(values[r][c], g.get(r, c));
            }
        }

        return result;
    }

    //
    // Conversion
    //

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public List<E> list() {
        final List<E> list = new ArrayList<>();

        for (int r = 0; r < rows; r++) {
            list.addAll(Arrays.asList(values[r]).subList(0, columns));
        }

        return list;
    }

    /**
     * Converts this grid into a 1D array.
     *
     * @return The array representation of this array
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    public E[] array() {
        final E[] array = (E[]) new Object[area()];

        for (int r = 0; r < rows; r++) {
            System.arraycopy(values[r], 0, array, r * columns, columns);
        }

        return array;
    }

    //
    // Iteration
    //

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    @Nonnull
    public Iterator<E> iterator() {
        return Arrays.stream(array()).iterator();
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
