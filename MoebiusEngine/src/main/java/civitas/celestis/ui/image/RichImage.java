package civitas.celestis.ui.image;

import civitas.celestis.graphics.color.RichColor;
import civitas.celestis.math.integer.IntMatrix;
import civitas.celestis.math.integer.IntVector2;
import civitas.celestis.math.vector.Vector2;
import civitas.celestis.ui.filter.ImageFilter;
import civitas.celestis.ui.shape.Shape;
import jakarta.annotation.Nonnull;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

/**
 * An image which natively supports the use of {@link RichColor}.
 */
public class RichImage extends BufferedImage {
    //
    // Constructors
    //

    /**
     * Creates a new image.
     *
     * @param width  The width of this image
     * @param height The height of this image
     */
    public RichImage(int width, int height) {
        super(width, height, TYPE_INT_RGB);
    }

    /**
     * Creates a new image.
     *
     * @param size The size of this image
     */
    public RichImage(@Nonnull IntVector2 size) {
        super(size.x(), size.y(), TYPE_INT_RGB);
    }

    /**
     * Creates a new image.
     * Note that this constructor can be very slow with large matrices.
     *
     * @param data The matrix containing the RGB integer values
     */
    public RichImage(@Nonnull IntMatrix data) {
        super(data.columns(), data.rows(), TYPE_INT_RGB);

        for (int r = 0; r < data.rows(); r++) {
            for (int c = 0; c < data.columns(); c++) {
                setRGB(r, c, data.get(r, c));
            }
        }
    }

    //
    // Getters
    //

    /**
     * Returns a point on this image.
     * The provided scale factor is applied to the width and height this image.
     *
     * @param x The X position of the point to get on a scale of {@code 0-1}
     * @param y The Y position of the point to get on a scale of {@code 0-1}
     * @return The requested position on this image
     */
    @Nonnull
    public IntVector2 getPoint(double x, double y) {
        return new IntVector2((int) (getWidth() * x), (int) (getHeight() * y));
    }

    /**
     * Returns a point on this image.
     * The provided scale factor is applied to the width and height this image.
     *
     * @param scale The vector containing the X and Y scales between {@code 0-1}
     * @return The requested position on this image
     */
    @Nonnull
    public IntVector2 getPoint(@Nonnull Vector2 scale) {
        return new IntVector2((int) (getWidth() * scale.x()), (int) (getHeight() * scale.y()));
    }

    /**
     * Returns the size of this image.
     *
     * @return The size of this image
     */
    @Nonnull
    public IntVector2 getSize() {
        return new IntVector2(getWidth(), getHeight());
    }

    /**
     * Returns the RGB hex integer of the given pixel.
     *
     * @param p The pixel of which to get the color of
     * @return The color of the given pixel
     */
    public int getRGB(@Nonnull IntVector2 p) {
        return getRGB(p.x(), p.y());
    }

    /**
     * Returns the color of the given pixel.
     *
     * @param x The X coordinate of the pixel to get
     * @param y The Y coordinate of the pixel to get
     * @return The color at the specified position
     */
    @Nonnull
    public RichColor getColor(int x, int y) {
        return new RichColor(getRGB(x, y));
    }

    /**
     * Returns the color at the given pixel.
     *
     * @param p The pixel of which to get the color of
     * @return The color of the given pixel
     */
    @Nonnull
    public RichColor getColor(@Nonnull IntVector2 p) {
        return new RichColor(getRGB(p.x(), p.y()));
    }

    //
    // Setters
    //

    /**
     * Sets the color of the given pixel.
     *
     * @param x     The X coordinate of the pixel to set
     * @param y     The Y coordinate of the pixel to set
     * @param color The color to set to
     */
    public void setColor(int x, int y, @Nonnull RichColor color) {
        setRGB(x, y, color.hexInt());
    }

    /**
     * Sets the color of the given pixel.
     *
     * @param p     The pixel of which to set the color of
     * @param color The color to set to
     */
    public void setColor(@Nonnull IntVector2 p, @Nonnull RichColor color) {
        setRGB(p.x(), p.y(), color.hexInt());
    }

    //
    // Methods
    //

    /**
     * Fills this image with the specified color.
     *
     * @param color The color to fill this image with
     */
    public void fill(@Nonnull RichColor color) {
        // Setup
        final Graphics graphics = getGraphics();
        graphics.setColor(color.awt());

        // Fill with color
        graphics.fillRect(0, 0, getWidth(), getHeight());
    }

    /**
     * Draws the borders of an object to this image.
     *
     * @param shape The shape to draw
     * @param color The color to draw with
     */
    public void draw(@Nonnull Shape shape, @Nonnull RichColor color) {
        // Setup
        final Graphics graphics = getGraphics();
        graphics.setColor(color.awt());

        // Create polygon object
        final Polygon polygon = new Polygon();
        for (final IntVector2 point : shape.getPoints()) {
            polygon.addPoint(point.x(), point.y());
        }

        // Draw polygon
        graphics.drawPolygon(polygon);
    }

    /**
     * Fills the shape with the given color on this image.
     *
     * @param shape The shape to fill
     * @param color The color to fill with
     */
    public void fill(@Nonnull Shape shape, @Nonnull RichColor color) {
        // Setup
        final Graphics graphics = getGraphics();
        graphics.setColor(color.awt());

        // Create polygon object
        final Polygon polygon = new Polygon();
        for (final IntVector2 point : shape.getPoints()) {
            polygon.addPoint(point.x(), point.y());
        }

        // Fill polygon
        graphics.fillPolygon(polygon);
    }

    /**
     * Applies given operator to every pixel of this image.
     *
     * @param operator The operator to apply to each pixel of this image
     */
    public void modify(@Nonnull UnaryOperator<RichColor> operator) {
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                setColor(x, y, operator.apply(getColor(x, y)));
            }
        }
    }

    /**
     * Applies given function to this image.
     * @param filter The function to apply
     */
    public void apply(@Nonnull ImageFilter filter) {
        filter.accept(this);
    }
}
