package civitas.celestis.util.grid;

import civitas.celestis.math.vector.Int2;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.UnaryOperator;

/**
 * A two-dimensional grid of objects.
 *
 * @param <V> The type of value to contain in this grid
 * @param <G> This grid (the result of various operations)
 */
public interface Grid<V, G extends Grid<V, G>> extends Iterable<V> {
    //
    // Dimensions
    //

    /**
     * Returns the number of rows this grid has.
     *
     * @return The number of rows of this grid
     */
    int rows();

    /**
     * Returns the number of columns this grid has.
     *
     * @return The number of columns of this grid
     */
    int columns();

    /**
     * Returns the area ({@code width * height}) of this grid.
     *
     * @return The area of this grid
     */
    int area();

    /**
     * Returns the size of this grid.
     * The X component represents the number of rows (the height),
     * and the Y component represents the number of columns.
     *
     * @return The size of this grid
     */
    @Nonnull
    Int2 size();

    //
    // Arrays
    //

    /**
     * Returns the {@code i}th row of this grid.
     * This is a direct reference, and changes will be reflected to this grid.
     *
     * @param i The index of row to get
     * @return The {@code i}th row of this grid
     * @throws IndexOutOfBoundsException When the index {@code i} is out of bounds
     */
    @Nonnull
    V[] row(int i) throws IndexOutOfBoundsException;

    /**
     * Returns the {@code i}th column of this grid.
     * This is a composite array, and changes will not be reflected to this grid.
     *
     * @param i The index of column to get
     * @return The {@code i}th column of this grid
     * @throws IndexOutOfBoundsException When the index {@code i} is out of bounds
     */
    @Nonnull
    V[] column(int i) throws IndexOutOfBoundsException;

    //
    // Values
    //

    /**
     * Returns the values of this grid.
     * The return value is a direct reference, and changes will be reflected to this grid.
     *
     * @return The values of this grid
     */
    @Nonnull
    V[][] values();

    /**
     * Returns a 1D array containing the values of this grid.
     *
     * @return The 1D array representation of this grid
     */
    @Nonnull
    V[] array();

    /**
     * Checks if this grid contains the given object.
     * This returns true if at least one of the components is equal to the provided value {@code v}.
     *
     * @param v The value to check for
     * @return {@code true} if this grid contains the value
     */
    boolean contains(@Nonnull V v);

    /**
     * Checks if this grid contains multiple objects.
     * This returns true if {@link Grid#contains(Object)} will return {@code true} for all elements
     * of the provided iterable object {@code i}.
     *
     * @param i The iterable to check for
     * @return {@code true} if this grid contains all elements of the iterable
     */
    boolean containsAll(@Nonnull Iterable<V> i);

    /**
     * Returns the value at the specified index of this grid.
     *
     * @param r The index of the row
     * @param c The index of the column
     * @return The value at the specified index
     * @throws IndexOutOfBoundsException When the index is out of bounds
     */
    @Nonnull
    V get(int r, int c) throws IndexOutOfBoundsException;

    /**
     * Returns the {@code p}th element of this grid.
     *
     * @param p The point of which to get the value of
     * @return The value at the specified point
     * @throws IndexOutOfBoundsException When the point is out of bounds
     */
    @Nonnull
    V get(@Nonnull Int2 p) throws IndexOutOfBoundsException;

    /**
     * Sets the value at the specified index of this grid.
     *
     * @param r The index of the row
     * @param c The index of the column
     * @param v The value to set to
     * @throws IndexOutOfBoundsException When the index is out of bounds
     */
    void set(int r, int c, @Nonnull V v) throws IndexOutOfBoundsException;

    /**
     * Sets the {@code p}th value to the provided value {@code v}.
     *
     * @param p The point of which to set the value of
     * @param v The value to set to
     * @throws IndexOutOfBoundsException When the point is out of bounds
     */
    void set(@Nonnull Int2 p, @Nonnull V v) throws IndexOutOfBoundsException;

    //
    // Bulk-operation
    //

    /**
     * Fills this grid with the provided value {@code v}.
     *
     * @param v The value to fill this grid with
     */
    void fill(@Nonnull V v);

    //
    // Sub-operation
    //

    /**
     * Returns a sub-grid of this grid.
     *
     * @param r1 The starting point's row index
     * @param c1 The starting point's column index
     * @param r2 The ending point's row index
     * @param c2 The ending point's column index
     * @return The sub-grid of the specified range
     * @throws IndexOutOfBoundsException When the range is out of bounds
     */
    @Nonnull
    G get(int r1, int r2, int c1, int c2) throws IndexOutOfBoundsException;

    /**
     * Returns a sub-grid of this grid.
     *
     * @param p1 The starting point
     * @param p2 The ending point
     * @return The sub-grid of the specified range
     * @throws IndexOutOfBoundsException When the range is out of bounds
     */
    @Nonnull
    G get(@Nonnull Int2 p1, @Nonnull Int2 p2) throws IndexOutOfBoundsException;

    /**
     * Sets multiple components of this grid.
     *
     * @param r1 The starting point's row index
     * @param c1 The starting point's column index
     * @param r2 The ending point's row index
     * @param c2 The ending point's column index
     * @param g  A grid containing the values to set to
     * @throws IndexOutOfBoundsException When the range is out of bounds
     */
    void set(int r1, int c1, int r2, int c2, @Nonnull G g) throws IndexOutOfBoundsException;

    /**
     * Sets multiple components of this grid.
     *
     * @param p1 The starting point
     * @param p2 The ending point
     * @param g  A grid containing the values to set to
     * @throws IndexOutOfBoundsException When the range is out of bounds
     */
    void set(@Nonnull Int2 p1, @Nonnull Int2 p2, @Nonnull G g) throws IndexOutOfBoundsException;

    //
    // Transposition
    //

    /**
     * Transposes this grid. This swaps the rows and columns, and maps the values by the function
     * {@code f(p(x, y)) -> q(y, x)}.
     *
     * @return The transpose of this grid
     */
    @Nonnull
    G transpose();

    //
    // Copy
    //

    /**
     * Returns a new grid with the same dimensions and components as this grid.
     *
     * @return The copied grid
     */
    @Nonnull
    G copy();

    /**
     * Returns a new grid with the provided dimensions.
     * The values of this grid are copied to the resulting matrix.
     *
     * @param r The number of rows
     * @param c The number of columns
     * @return The resized grid
     */
    @Nonnull
    G resize(int r, int c);

    /**
     * Returns a new grid with the provided dimensions.
     *
     * @param size The size of the new grid
     * @return The resized grid
     */
    @Nonnull
    G resize(@Nonnull Int2 size);

    //
    // Transformation
    //

    /**
     * Applies the given function to each element of this grid, then returns the resulting grid.
     *
     * @param transformer The function to apply to each element of this grid
     * @return The resulting grid
     */
    @Nonnull
    G transform(@Nonnull UnaryOperator<V> transformer);

    //
    // Iterator
    //

    /**
     * Returns an iterator of all values of this grid.
     * This is derived from the iterator of {@link Grid#array()}.
     *
     * @return The iterator of this grid's values
     */
    @Override
    @Nonnull
    default Iterator<V> iterator() {
        return Arrays.stream(array()).iterator();
    }

    //
    // Equality
    //

    /**
     * Checks for equality between this grid and the given object {@code obj}.
     *
     * @param obj The object to compare to
     * @return {@code true} if the object is also a grid, and the dimensions and components are equal
     */
    boolean equals(@Nullable Object obj);

    /**
     * Checks for equality with another grid.
     *
     * @param g The grid to compare to
     * @return {@code true} if the dimensions and components are equal
     */
    boolean equals(@Nonnull G g);

    //
    // Serialization
    //

    /**
     * Serializes this grid into a string.
     *
     * @return The string representation of this grid
     */
    @Nonnull
    String toString();

}
