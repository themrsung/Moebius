package civitas.celestis.math.matrix;

import civitas.celestis.math.Numbers;
import civitas.celestis.math.integer.IntVector2;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.Arrays;
import java.util.Iterator;

/**
 * A two-dimensional array of scalars. Matrices can be used for various purposes.
 * A matrix cannot be resized after creation. Resizing requires re-instantiation.
 */
public class Matrix implements Iterable<Double> {
    //
    // Constructors
    //

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
     * @param values The values to contain in this matrix.
     */
    public Matrix(@Nonnull double[][] values) {
        if (values.length < 1) {
            this.rows = 0;
            this.columns = 0;
        } else {
            this.rows = values.length;
            this.columns = values[0].length;
        }

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

    @Nonnull
    private final double[][] values;
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
    public double[] values() {
        final double[] valueArray = new double[length()];

        for (int r = 0; r < rows; r++) {
            if (columns >= 0) System.arraycopy(values[r], 0, valueArray, r * columns + 0, columns);
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
    public double get(int r, int c) throws IndexOutOfBoundsException {
        return values[r][c];
    }

    /**
     * Gets a value at the specified position.
     *
     * @param i The index of the value
     * @return The value at the position
     * @throws IndexOutOfBoundsException When the index is out of bounds
     */
    public double get(@Nonnull IntVector2 i) throws IndexOutOfBoundsException {
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
    public void set(int r, int c, double v) throws IndexOutOfBoundsException {
        values[r][c] = Numbers.requireFinite(v);
    }

    /**
     * Assigns a value to the specified position.
     *
     * @param i The index of the position
     * @param v The value to assign
     * @throws IndexOutOfBoundsException When the index is out of bounds
     */
    public void set(@Nonnull IntVector2 i, double v) throws IndexOutOfBoundsException {
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
    public Iterator<Double> iterator() {
        return Arrays.stream(values()).iterator();
    }


    //
    // Equality
    //

    /**
     * Checks for equality between this matrix and the specified object {@code obj}.
     *
     * @param obj The object to compare to
     * @return {@code true} if the object is an instance of {@link Matrix} and the dimensions and values are equal
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
