package civitas.celestis.graphics.color;

import jakarta.annotation.Nonnull;

import java.awt.*;
import java.util.function.UnaryOperator;

/**
 * An 8-bit color with integer RGB components.
 */
public interface RGB8 {
    //
    // Factory
    //

    /**
     * Creates a new RGB 8-bit color.
     * All components must be within the range of {@code 0-255}.
     *
     * @param r The red component of the color
     * @param g The green component of the color
     * @param b The blue component of the color
     * @return The built color
     */
    @Nonnull
    static RGB8 of(int r, int g, int b) {
        return new RGBColor(r, g, b);
    }

    /**
     * Deserializes a string into a color.
     *
     * @param input The input string to parse
     * @return The parsed color
     * @throws IllegalArgumentException When the format is invalid
     */
    @Nonnull
    static RGB8 parseColor(@Nonnull String input) throws IllegalArgumentException {
        try {return RGBColor.parseColor(input);} catch (final NumberFormatException ignored) {}
        throw new IllegalArgumentException("Given string is not a color.");
    }

    //
    // Getters
    //

    /**
     * Returns the red component of this color.
     *
     * @return The red component of this color ({@code 0-255})
     */
    int red();

    /**
     * Returns the blue component of this color.
     *
     * @return The blue component of this color ({@code 0-255})
     */
    int blue();

    /**
     * Returns the green component of this color.
     *
     * @return The green component of this color ({@code 0-255})
     */
    int green();

    /**
     * Returns an AWT {@link Color} built from this color.
     *
     * @return An AWT {@link Color} object
     */
    @Nonnull
    Color awt();

    //
    // Properties
    //

    /**
     * Returns the hex integer of this color.
     *
     * @return The hex integer of this color
     */
    int hexInt();

    /**
     * Returns the hex string of this color.
     *
     * @return The hex string of this color
     */
    @Nonnull
    String hexString();

    //
    // Utility
    //

    /**
     * Applies given operator to all components of this color, then returns the resulting color.
     *
     * @param operator The operator to apply to each component of this color
     * @return The resulting color
     * @throws IllegalArgumentException When the operator returns an illegal value
     */
    @Nonnull
    RGB8 apply(@Nonnull UnaryOperator<Integer> operator) throws IllegalArgumentException;

    /**
     * Returns the reverse of this color.
     * The RGB components are reversed by applying {@code 255 - value}.
     *
     * @return The reverse of this color
     */
    @Nonnull
    RGB8 reverse();

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
