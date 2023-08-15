package civitas.celestis.graphics.color;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * A simple immutable color, which is not easy to manipulate.
 * This class is designed for spacial efficiency.
 * <p>
 * This implementation has 8 bits of precision for both storage and output.
 * </p>
 */
public class SimpleColor implements Color8 {
    //
    // Constants
    //
    /**
     * The color white.
     */
    public static final SimpleColor WHITE = new SimpleColor(255, 255, 255, 255);

    /**
     * The color red.
     */
    public static final SimpleColor RED = new SimpleColor(255, 0, 0, 255);

    /**
     * The color green.
     */
    public static final SimpleColor GREEN = new SimpleColor(0, 255, 0, 255);

    /**
     * The color blue.
     */
    public static final SimpleColor BLUE = new SimpleColor(0, 0, 255, 255);

    /**
     * The color cyan.
     */
    public static final SimpleColor CYAN = new SimpleColor(0, 255, 255, 255);

    /**
     * The color magenta.
     */
    public static final SimpleColor MAGENTA = new SimpleColor(255, 0, 255, 255);

    /**
     * The color yellow.
     */
    public static final SimpleColor YELLOW = new SimpleColor(255, 255, 0, 255);

    /**
     * The color black.
     */
    public static final SimpleColor BLACK = new SimpleColor(0, 0, 0, 255);

    //
    // Constructors
    //

    /**
     * Creates a new color by assigning the raw RGBA value.
     * For the encoding format, see {@link Color8#packRGBA(int, int, int, int)}.
     * <p><b>
     * Note that this does not use the same encoding as standard RGB hex codes.
     * </b></p>
     *
     * @param rgba The raw RGBA value of this color
     * @see Color8#packRGBA(int, int, int, int)
     * @see Color8#unpackRed(int)
     * @see Color8#unpackGreen(int)
     * @see Color8#unpackBlue(int)
     * @see Color8#unpackAlpha(int)
     */
    public SimpleColor(int rgba) {
        this.rgba = rgba;
    }

    /**
     * Creates a new color.
     * All components must be within the range of {@code 0-255}.
     *
     * @param r The red component of this color
     * @param g The green component of this color
     * @param b The blue component of this color
     * @param a The alpha component of this color
     */
    public SimpleColor(int r, int g, int b, int a) {
        this.rgba = Color8.packRGBA(r, g, b, a);
    }

    /**
     * Creates a new color.
     *
     * @param c The color of which to copy component values from
     */
    public SimpleColor(@Nonnull Color8 c) {
        this.rgba = c.rgba();
    }

    //
    // Hex Conversion
    //

    /**
     * Creates a color from an RGB hex code.
     * The alpha component will be {@code 255}. (opaque)
     *
     * @param hex The RGB hex code
     * @return The converted color
     */
    @Nonnull
    public static SimpleColor fromHex(int hex) {
        return new SimpleColor(Color8.hexToRgba(hex, 255));
    }

    /**
     * Creates a color from an RGB hex code.
     *
     * @param hex   The RGB hex code
     * @param alpha The alpha component value
     * @return The converted color
     */
    @Nonnull
    public static SimpleColor fromHex(int hex, int alpha) {
        return new SimpleColor(Color8.hexToRgba(hex, alpha));
    }

    //
    // Packing
    //

    /**
     * Packs this color into a {@code long}.
     * This is only implemented to respect the contract of {@link Color8},
     * and packing this color into a {@code long} will only increase its size.
     *
     * @return The {@code long} representation of this color
     */
    @Override
    public long pack() {
        return rgba;
    }

    //
    // Variables
    //

    /**
     * The RGBA components packed into 32 bits.
     */
    private final int rgba;

    //
    // Getters
    //

    /**
     * {@inheritDoc}
     *
     * @return The RGBA components of this color packed into 32 bits
     */
    @Override
    public int rgba() {
        return rgba;
    }

    /**
     * {@inheritDoc}
     *
     * @return The red component of this color
     */
    @Override
    public int red() {
        return Color8.unpackRed(rgba);
    }

    /**
     * {@inheritDoc}
     *
     * @return The green component of this color
     */
    @Override
    public int green() {
        return Color8.unpackGreen(rgba);
    }

    /**
     * {@inheritDoc}
     *
     * @return The blue component of this color
     */
    @Override
    public int blue() {
        return Color8.unpackBlue(rgba);
    }

    /**
     * {@inheritDoc}
     *
     * @return The alpha component of this color
     */
    @Override
    public int alpha() {
        return Color8.unpackAlpha(rgba);
    }

    /**
     * {@inheritDoc}
     *
     * @return The RGB hex code of this color
     */
    @Override
    public int hex() {
        return Color8.rgbaToHex(rgba);
    }

    //
    // Equality
    //

    /**
     * {@inheritDoc}
     *
     * @param obj The object to compare to
     * @return {@code true} if the object is also a color, and the RGBA components are equal
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof Color8 c)) return false;
        return rgba == c.rgba();
    }

    //
    // Serialization
    //

    /**
     * Deserializes a string into a color.
     *
     * @param input The string to parse
     * @return The parsed string
     * @throws IllegalArgumentException When the format is invalid
     */
    @Nonnull
    public static SimpleColor parseColor(@Nonnull String input) throws IllegalArgumentException {
        if (!input.startsWith("Color{")) {
            throw new IllegalArgumentException("The provided string does not represent a SimpleColor.");
        }

        final String cleanInput = input.replaceAll("Color\\{rgba=|}", "");
        return new SimpleColor(Integer.parseInt(cleanInput));
    }

    /**
     * {@inheritDoc}
     *
     * @return The string representation of this color
     */
    @Override
    @Nonnull
    public String toString() {
        return "Color{rgba=" + rgba + "}";
    }
}
