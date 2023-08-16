package civitas.celestis.util.group;

import civitas.celestis.math.vector.Int2;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.io.Serializable;
import java.util.Iterator;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * A two-dimensional grid of elements.
 * While the usage of {@code null} is not officially supported,
 * there are no exceptions or null-checks. Use at your own risk.
 *
 * @param <E> The type of element to contain in this grid
 * @see ArrayGrid
 * @see civitas.celestis.math.matrix.Matrix Matrix
 */
public interface Grid<E> extends Group<E>, Iterable<E>, Serializable {
    //
    // Dimensions
    //

    /**
     * Returns the number of rows this grid has.
     *
     * @return The number of rows
     */
    int rows();

    /**
     * Returns the number of columns this grid has.
     *
     * @return The number of columns
     */
    int columns();

    /**
     * Returns the area ({@code rows * columns}) if this grid.
     *
     * @return The area of this grid
     */
    int area();

    /**
     * Returns the dimensions of this grid.
     * The X component represents the number of rows (the height),
     * and the Y component represents the number of columns (the width).
     *
     * @return The dimensions of this grid
     */
    @Nonnull
    Int2 size();

    //
    // Rows / Columns
    //

    /**
     * Returns the {@code r}th row of this grid.
     * Changes in the return value will not be reflected to this grid.
     *
     * @param r The index of row to get
     * @return The {@code r}th row of this grid
     * @throws IndexOutOfBoundsException When the index {@code r} is out of bounds
     */
    @Nonnull
    Group<E> row(int r) throws IndexOutOfBoundsException;

    /**
     * Returns the {@code c}th column of this grid.
     * Changes in the return value will not be reflected to this grid.
     *
     * @param c The index of column to get
     * @return The {@code c} th column of this grid
     * @throws IndexOutOfBoundsException When the index {@code c} is out of bounds
     */
    @Nonnull
    Group<E> column(int c) throws IndexOutOfBoundsException;

    //
    // Containment
    //

    /**
     * Checks if this grid contains the given object {@code obj}.
     *
     * @param obj The object to check for containment
     * @return {@code true} if at least one element of this grid is equal
     * to the provided object {@code obj}
     */
    boolean contains(@Nullable Object obj);

    /**
     * Checks if this grid contains multiple objects.
     *
     * @param i The iterable object to check for containment
     * @return {@code true} if this grid contains every element of the iterable object
     */
    boolean containsAll(@Nonnull Iterable<E> i);

    //
    // Getters
    //

    /**
     * Returns the element at the specified index.
     *
     * @param r The index of row to get
     * @param c The index of column to get
     * @return The element at the specified index
     * @throws IndexOutOfBoundsException When the index is out of bounds
     */
    E get(int r, int c) throws IndexOutOfBoundsException;

    /**
     * Returns the {@code i}th element of this grid.
     *
     * @param i The index of element to get
     * @return The {@code i}th element of this grid
     * @throws IndexOutOfBoundsException When the index {@code i} is out of bounds
     */
    E get(@Nonnull Int2 i) throws IndexOutOfBoundsException;

    //
    // Setters
    //

    /**
     * Sets the element at the specified index to the provided value {@code v}.
     *
     * @param r The index of row to set
     * @param c The index of column to set
     * @param v The value to set to
     * @throws IndexOutOfBoundsException When the index is out of bounds
     */
    void set(int r, int c, E v) throws IndexOutOfBoundsException;

    /**
     * Sets the {@code i}th element of this grid to the provided value {@code v}.
     *
     * @param i The index of element to set
     * @param v The value to set to
     * @throws IndexOutOfBoundsException When the index {@code i} is out of bounds
     */
    void set(@Nonnull Int2 i, E v) throws IndexOutOfBoundsException;

    /**
     * Fills this grid with the provided value {@code v}.
     *
     * @param v The value to fill this grid with
     */
    void fill(E v);

    //
    // Sub-operation
    //

    /**
     * Returns a sub-grid of this grid.
     * The return value will have a row count of {@code r2 - r1},
     * and a column count of {@code c2 - c1}.
     *
     * @param r1 The starting point's row index
     * @param c1 The starting point's column index
     * @param r2 The ending point's row index
     * @param c2 The ending point's column index
     * @return The sub-grid of the specified range
     * @throws IndexOutOfBoundsException When the range is out of bounds
     */
    @Nonnull
    Grid<E> get(int r1, int c1, int r2, int c2) throws IndexOutOfBoundsException;

    /**
     * Returns a sub-grid of this grid.
     * The return value will have a row count of {@code i2.x() - i1.x()},
     * and a column count of {@code i2.y() - i1.y()}.
     *
     * @param i1 The starting point's index
     * @param i2 The ending point's index
     * @return The sub-grid of the specified range
     * @throws IndexOutOfBoundsException When the range is out of bounds
     */
    @Nonnull
    Grid<E> get(@Nonnull Int2 i1, @Nonnull Int2 i2) throws IndexOutOfBoundsException;

    /**
     * Sets a sub-grid of this grid.
     *
     * @param r1 The starting point's row index
     * @param c1 The starting point's column index
     * @param r2 The ending point's row index
     * @param c2 The ending point's column index
     * @param g  The grid containing the values to assign
     * @throws IndexOutOfBoundsException When the range is out of bounds
     */
    void set(int r1, int c1, int r2, int c2, @Nonnull Grid<E> g) throws IndexOutOfBoundsException;

    /**
     * Sets a sub-grid of this grid.
     *
     * @param i1 The starting point's index
     * @param i2 The ending point's index
     * @param g  The grid containing the values to assign
     * @throws IndexOutOfBoundsException When the range is out of bounds
     */
    void set(@Nonnull Int2 i1, @Nonnull Int2 i2, @Nonnull Grid<E> g) throws IndexOutOfBoundsException;

    //
    // Transformation
    //

    /**
     * Applies the provided function {@code f} to every element of this grid,
     * then returns the resulting grid containing the return values.
     *
     * @param f The function to apply to each element of this grid
     * @return The resulting grid
     */
    @Nonnull
    Grid<E> transform(@Nonnull Function<? super E, E> f);

    /**
     * Returns a new grid where the rows and columns are inverted.
     * The values are mapped according to the function {@code f(p{x, y}) -> p{y, x}}.
     *
     * @return The transpose of this grid
     */
    @Nonnull
    Grid<E> transpose();

    /**
     * Returns a resized grid of which the components of this grid are mapped to.
     *
     * @param r The number of rows the new grid should have
     * @param c The number of columns the new grid should have
     * @return The resized grid
     */
    @Nonnull
    Grid<E> resize(int r, int c);

    /**
     * Returns a resized grid of which the components of this grid are mapped to.
     *
     * @param size The size of the new grid
     * @return The resized grid
     */
    @Nonnull
    Grid<E> resize(@Nonnull Int2 size);

    /**
     * Merges this grid with another grid of the same dimensions.
     * The merger function {@code f} is applied to each component, and the
     * resulting grid is returned.
     * <p>
     * For example, if a sum function {@code (x, y) -> x + y} was provided,
     * this would return the sum of the two grids of numbers.
     * This is just an example, and {@link civitas.celestis.math.matrix.Matrix matrices}
     * should be used for arithmetic operations.
     * </p>
     *
     * @param g The other grid to merge with
     * @param f The merger function to apply
     * @return The resulting grid
     * @throws IllegalArgumentException When the provided grid {@code g}'s dimensions do not
     *                                  match to this grid's dimensions
     */
    @Nonnull
    Grid<E> merge(@Nonnull Grid<E> g, @Nonnull BinaryOperator<E> f) throws IllegalArgumentException;

    //
    // Iteration
    //

    /**
     * Returns an iterator of all elements of this grid.
     *
     * @return An iterator of this grid's elements
     */
    @Override
    @Nonnull
    Iterator<E> iterator();

    //
    // Equality
    //

    /**
     * Checks for equality between this grid and the provided object {@code obj}.
     *
     * @param obj The object to compare to
     * @return {@code true} if the object is also a grid, and the dimensions and composition are equal
     */
    boolean equals(@Nullable Object obj);

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
