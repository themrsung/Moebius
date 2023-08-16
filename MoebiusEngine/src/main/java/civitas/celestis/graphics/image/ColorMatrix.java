package civitas.celestis.graphics.image;

import civitas.celestis.graphics.color.Color8;
import civitas.celestis.graphics.color.LinearColor;
import civitas.celestis.math.matrix.LongMatrix;
import civitas.celestis.math.vector.Int2;
import civitas.celestis.util.group.Grid;
import jakarta.annotation.Nonnull;

import java.awt.image.BufferedImage;

/**
 * A matrix of colors.
 * <p>
 * Color matrices allocate 64 bits per pixel to represent the color of that pixel.
 * Colors are encoded into the type {@code long} using {@link Color8#pack64()}.
 * </p>
 */
public class ColorMatrix extends LongMatrix {
    //
    // Constructors
    //

    /**
     * Creates a new matrix.
     *
     * @param r The number of rows this matrix has (the height)
     * @param c The number of columns this matrix has (the width)
     */
    public ColorMatrix(int r, int c) {
        super(r, c);
    }

    /**
     * Creates a new matrix.
     *
     * @param values The two-dimensional array containing the 64-bit
     *               RGBA representations of the pixel values
     */
    public ColorMatrix(@Nonnull long[][] values) {
        super(values);
    }

    /**
     * Creates a new matrix.
     *
     * @param size The dimensions of this matrix ({@code x = height, y = width})
     */
    public ColorMatrix(@Nonnull Int2 size) {
        super(size);
    }

    /**
     * Creates a new matrix.
     *
     * @param g The grid containing the 64-bit RGBA colors
     */
    public ColorMatrix(@Nonnull Grid<? extends Number> g) {
        super(g);
    }

    //
    // Getters
    //

    /**
     * Returns the color of the specified pixel.
     *
     * @param r The index of the pixel's row
     * @param c The index of the column's row
     * @return The color of the specified pixel
     * @throws IndexOutOfBoundsException When the index is out of bounds
     */
    @Nonnull
    public LinearColor getColor(int r, int c) throws IndexOutOfBoundsException {
        return new LinearColor(values[r][c]);
    }

    /**
     * Returns the color of the specified pixel.
     *
     * @param i The index of the pixel
     * @return The color of the specified pixel
     * @throws IndexOutOfBoundsException When the index is out of bounds
     */
    @Nonnull
    public LinearColor getColor(@Nonnull Int2 i) throws IndexOutOfBoundsException {
        return new LinearColor(values[i.x()][i.y()]);
    }

    //
    // Setters
    //

    /**
     * Sets the color of the specified pixel.
     *
     * @param r The index of the pixel's row
     * @param c The index of the pixel's column
     * @param v The color to set to
     * @throws IndexOutOfBoundsException When the index is out of bounds
     */
    public void setColor(int r, int c, @Nonnull Color8 v) throws IndexOutOfBoundsException {
        values[r][c] = v.pack64();
    }

    /**
     * Sets the color of the specified pixel.
     *
     * @param i The index of the pixel
     * @param v The color to set to
     * @throws IndexOutOfBoundsException When the index is out of bounds
     */
    public void setColor(@Nonnull Int2 i, @Nonnull Color8 v) throws IndexOutOfBoundsException {
        values[i.x()][i.y()] = v.pack64();
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
    public ColorMatrix get(int r1, int c1, int r2, int c2) throws IndexOutOfBoundsException {
        final ColorMatrix result = new ColorMatrix(r2 - r1, c2 - c1);

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
    public ColorMatrix get(@Nonnull Int2 i1, @Nonnull Int2 i2) throws IndexOutOfBoundsException {
        final int r1 = i1.x();
        final int c1 = i1.y();
        final int r2 = i2.x();
        final int c2 = i2.y();

        final ColorMatrix result = new ColorMatrix(r2 - r1, c2 - c1);

        for (int r = r1; r < r2; r++) {
            if (c2 - c1 >= 0) System.arraycopy(values[r], c1, result.values[r - r1], 0, c2 - c1);
        }

        return result;
    }

    //
    // Rendering
    //

    /**
     * Renders this matrix into an AWT {@link BufferedImage}.
     *
     * @return The rendered image
     */
    @Nonnull
    public BufferedImage render() {
        final BufferedImage matrix = new BufferedImage(columns, rows, BufferedImage.TYPE_INT_ARGB);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                matrix.setRGB(c, r, Color8.convert64to32(values[r][c]));
            }
        }

        return matrix;
    }
}
