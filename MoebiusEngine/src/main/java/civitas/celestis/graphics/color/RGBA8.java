package civitas.celestis.graphics.color;

import civitas.celestis.math.integer.IntVector4;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.math.vector.Vector4;
import jakarta.annotation.Nonnull;

import java.awt.*;
import java.util.function.UnaryOperator;

/**
 * An 8-bit color with {@code double} RGBA components.
 */
public interface RGBA8 {
    //
    // Factory
    //

    /**
     * Creates a new RGBA 8-bit color.
     * All components must be within the range of {@code 0-255}.
     *
     * @param r The red component of the color
     * @param g The green component of the color
     * @param b The blue component of the color
     * @param a The alpha component of the color
     * @return The built color
     */
    @Nonnull
    static RGBA8 of(double r, double g, double b, double a) {
        return new RichColor(r, g, b, a);
    }

    /**
     * Deserializes a string into a color.
     *
     * @param input The input string to parse
     * @return The parsed color
     * @throws IllegalArgumentException When the format is invalid
     */
    @Nonnull
    static RGBA8 parseColor(@Nonnull String input) throws IllegalArgumentException {
        try {return RichColor.parseColor(input);} catch (final NumberFormatException ignored) {}
        throw new IllegalArgumentException("Given string is not a color.");
    }

    //
    // Getters
    //

    /**
     * Returns the red component of this color.
     *
     * @return The red component of this color
     */
    double red();

    /**
     * Returns the green component of this color.
     *
     * @return The green component of this color
     */
    double green();

    /**
     * Returns the blue component of this color.
     *
     * @return The blue component of this color
     */
    double blue();

    /**
     * Returns the alpha component of this color.
     *
     * @return The alpha component of this color
     */
    double alpha();

    /**
     * Returns the RGB components of this color.
     *
     * @return The RGB components of this color
     */
    @Nonnull
    Vector3 rgb();

    /**
     * Returns the RGBA components of this color.
     *
     * @return The RGBA components of this color
     */
    @Nonnull
    Vector4 rgba();

    /**
     * Returns the RGB components of this color, rounded to the nearest integer value.
     *
     * @return The rounded RGB components of this color
     */
    @Nonnull
    RGB8 rgbInt();

    /**
     * Returns the RGBA components of this color, rounded to the nearest integer value.
     *
     * @return The rounded RGBA components of this color
     */
    @Nonnull
    IntVector4 rgbaInt();

    /**
     * Returns an AWT {@link Color} built from this color.
     * Components are rounded to the nearest integer.
     *
     * @return An AWT {@link Color} object
     */
    @Nonnull
    Color awt();

    /**
     * Returns an AWT {@link Color} built from this color.
     * Decimal places in the components are dropped for faster conversion.
     *
     * @return An AWT {@link Color} object
     */
    @Nonnull
    Color awtRaw();

    //
    // Properties
    //

    /**
     * Returns whether this color is translucent.
     *
     * @return {@code true} if this color is translucent
     */
    boolean translucent();

    /**
     * Returns whether this color is opaque.
     *
     * @return {@code true} if this color is opaque
     */
    boolean opaque();

    /**
     * Calculates the reflectiveness coefficient of this color.
     * This is calculated by normalizing the sum of the RGB components,
     * then multiplying it by the normalized alpha component.
     *
     * @return The reflectiveness coefficient of this color ({@code 0-1})
     */
    double reflectiveness();

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

    /**
     * Applies given operator to every component of this color, then returns the resulting color.
     *
     * @param operator The operator to apply to each component of this color
     * @return The resulting color
     * @throws IllegalArgumentException When the return value of the function is illegal
     */
    @Nonnull
    RGBA8 apply(@Nonnull UnaryOperator<Double> operator) throws IllegalArgumentException;

    /**
     * Applies the given operator to the RGB components of this color, then returns the resulting color.
     *
     * @param operator The operator to apply to the RGB components of this color
     * @return The resulting color
     * @throws IllegalArgumentException When the return value of the function is illegal
     */
    @Nonnull
    RGBA8 applyRGB(@Nonnull UnaryOperator<Vector3> operator) throws IllegalArgumentException;

    /**
     * Normalizes this color to fit within the range of {@code 0-1}.
     * If the magnitude of this color is zero (the color is black and the alpha is zero),
     * this will return {@link Vector4#ZERO}.
     *
     * @return The normalized vector representing the RGBA components of this color
     */
    @Nonnull
    Vector4 normalize();

    /**
     * Returns the reverse of this color.
     * The RGB components are reversed by applying {@code 255 - value},
     * and the alpha component is preserved as-is.
     *
     * @return The reverse of this color
     */
    @Nonnull
    RGBA8 reverse();

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
