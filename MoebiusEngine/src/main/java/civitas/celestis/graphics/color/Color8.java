package civitas.celestis.graphics.color;

import civitas.celestis.util.packing.Packable;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.awt.*;

/**
 * An 8-bit color.
 * The component values have a range of {@code 0-255}.
 *
 * @see SimpleColor
 * @see LinearColor
 */
public interface Color8 extends Packable {
    //
    // Static Utilities
    //

    /**
     * Converts a {@link Color8} to an AWT {@link Color}.
     *
     * @param color The color to convert
     * @return The converted color
     */
    @Nonnull
    static Color toAWT(@Nonnull Color8 color) {
        return new Color(color.red(), color.green(), color.blue(), color.alpha());
    }

    /**
     * Converts an AWT {@link Color} to a {@link Color8}.
     *
     * @param color The color to convert
     * @return The converted color
     */
    @Nonnull
    static Color8 fromAWT(@Nonnull Color color) {
        return new SimpleColor(hexToRgba(color.getRGB(), color.getAlpha()));
    }

    //
    // Parsing
    //

    /**
     * Deserializes a string into a color.
     *
     * @param input The input string to parse
     * @return The parsed color
     * @throws IllegalArgumentException When the format is invalid
     */
    @Nonnull
    static Color8 parseColor(@Nonnull String input) throws IllegalArgumentException {
        try {return SimpleColor.parseColor(input);} catch (final Exception ignored) {}
        try {return LinearColor.parseColor(input);} catch (final Exception ignored) {}

        throw new IllegalArgumentException("The string does not represent a color.");
    }

    //
    // Packing
    //

    /*
     * This section defines the standard encoding of a Color8.
     * RGBA components are encoded to 32 bits in their literal order.
     */

    /**
     * Packs four RGBA components into one {@code int}.
     *
     * @param r The red component
     * @param g The green component
     * @param b The blue component
     * @param a The alpha component
     * @return The packed value
     */
    static int packRGBA(int r, int g, int b, int a) {
        return ((r & 0xFF) << 24) |
                ((g & 0xFF) << 16) |
                ((b & 0xFF) << 8) |
                (a & 0xFF);
    }

    /**
     * Extracts the red component from a packed {@code int}.
     *
     * @param packed The packed {@code int}
     * @return The extracted component
     */
    static int unpackRed(int packed) {
        return (packed >> 24) & 0xFF;
    }

    /**
     * Extracts the green component from a packed {@code int}.
     *
     * @param packed The packed {@code int}
     * @return The extracted component
     */
    static int unpackGreen(int packed) {
        return (packed >> 16) & 0xFF;
    }

    /**
     * Extracts the blue component from a packed {@code int}.
     *
     * @param packed The packed {@code int}
     * @return The extracted component
     */
    static int unpackBlue(int packed) {
        return (packed >> 8) & 0xFF;
    }

    /**
     * Extracts the alpha component from a packed {@code int}.
     *
     * @param packed The packed {@code int}
     * @return The extracted component
     */
    static int unpackAlpha(int packed) {
        return packed & 0xFF;
    }

    /**
     * Packs three RGB components into one {@code int} using the standard RGB hex format.
     *
     * @param r The red component
     * @param g The green component
     * @param b The blue component
     * @return The packed value
     */
    static int packRGB(int r, int g, int b) {
        return (r << 16) | (g << 8) | b;
    }

    /**
     * Converts an RGBA encoded integer into an RGB hex integer.
     *
     * @param rgba The RGBA integer to convert
     * @return The RGB hex string of the input value
     */
    static int rgbaToHex(int rgba) {
        return (rgba >> 8) & 0xFFFFFF;
    }

    /**
     * Converts an RGB hex integer and alpha component into an RGBA encoded integer.
     *
     * @param rgbHex The RGB hex integer to convert
     * @param alpha  The alpha component value (0-255)
     * @return The RGBA encoded integer
     */
    static int hexToRgba(int rgbHex, int alpha) {
        return (rgbHex << 8) | (alpha & 0xFF);
    }

    //
    // Getters
    //

    /**
     * Returns the red component of this color.
     * The return value has a range of {@code 0-255}.
     *
     * @return The red component of this color
     */
    int red();

    /**
     * Returns the green component of this color.
     * The return value has a range of {@code 0-255}.
     *
     * @return The green component of this color
     */
    int green();

    /**
     * Returns the blue component of this color.
     * The return value has a range of {@code 0-255}.
     *
     * @return The blue component of this color
     */
    int blue();

    /**
     * Returns the alpha component of this color.
     * The return value has a range of {@code 0-255}.
     *
     * @return The alpha component of this color
     */
    int alpha();

    /**
     * Encodes this color into 32 bits which contain the RGBA component values.
     *
     * @return The encoded RGBA value
     * @see Color8#packRGBA(int, int, int, int)
     */
    int rgba();

    /**
     * Returns the RGB hex code of this color.
     *
     * @return The RGB hex code of this color
     */
    int hex();

    //
    // Equality
    //

    /**
     * Checks for equality between this color and the provided object {@code obj}.
     * <p>
     * Same instances of colors will be compared to their full storage precision.
     * For example, two instances of {@link LinearColor} will only be considered equal when
     * all {@code double} components are equal in accordance to the {@code ==} operator.
     * </p>
     * <p>
     * Between different instances, colors are considered equal when the 8 bit representation is equal,
     * regardless of the internal precision of the implementation.
     * </p>
     *
     * @param obj The object to compare to
     * @return {@code true} if the other object is also a color, and the RGBA components are equal
     */
    boolean equals(@Nullable Object obj);

    //
    // Serialization
    //

    /**
     * Serializes this color into a string.
     *
     * @return The string representation of this color
     */
    @Nonnull
    String toString();
}
