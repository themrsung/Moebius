package civitas.celestis.graphics.color;

import civitas.celestis.math.Numbers;
import civitas.celestis.math.integer.IntVector;
import civitas.celestis.math.integer.IntVector3;
import civitas.celestis.math.natural.NaturalVector3;
import civitas.celestis.math.vector.Vector;
import civitas.celestis.math.vector.Vector3;
import jakarta.annotation.Nonnull;

import java.awt.*;
import java.util.function.UnaryOperator;

/**
 * An 8-bit color which only has discrete integer RGB components.
 */
public class RGBColor extends NaturalVector3 {
    //
    // Constants
    //

    /**
     * The color white.
     */
    public static final RGBColor WHITE = new RGBColor(255, 255, 255);

    /**
     * The color light gray.
     */
    public static final RGBColor LIGHT_GRAY = RichColor.LIGHT_GRAY.rgbInt();

    /**
     * The color gray.
     */
    public static final RGBColor GRAY = RichColor.GRAY.rgbInt();

    /**
     * The color dark gray.
     */
    public static final RGBColor DARK_GRAY = RichColor.DARK_GRAY.rgbInt();

    /**
     * The color black.
     */
    public static final RGBColor BLACK = new RGBColor(0, 0, 0);

    /**
     * The color red.
     */
    public static final RGBColor RED = new RGBColor(255, 0, 0);

    /**
     * The color pink.
     */
    public static final RGBColor PINK = RichColor.PINK.rgbInt();

    /**
     * The color orange.
     */
    public static final RGBColor ORANGE = RichColor.ORANGE.rgbInt();

    /**
     * The color yellow.
     */
    public static final RGBColor YELLOW = new RGBColor(255, 255, 0);

    /**
     * The color green.
     */
    public static final RGBColor GREEN = new RGBColor(0, 255, 0);

    /**
     * The color magenta.
     */
    public static final RGBColor MAGENTA = new RGBColor(255, 0, 255);

    /**
     * The color cyan.
     */
    public static final RGBColor CYAN = new RGBColor(0, 255, 255);

    /**
     * The color blue.
     */
    public static final RGBColor BLUE = new RGBColor(0, 0, 255);

    /**
     * The color gold.
     */
    public static final RGBColor GOLD = RichColor.GOLD.rgbInt();

    /**
     * The color brown.
     */
    public static final RGBColor BROWN = RichColor.BROWN.rgbInt();

    //
    // Static Utilities
    //

    /**
     * Safely converts a vector to a color by clamping its values.
     *
     * @param v The vector to convert
     * @return The converted color
     */
    @Nonnull
    public static RGBColor fromVector(@Nonnull IntVector3 v) {
        return new RGBColor(v.clamp(BLACK, WHITE));
    }

    //
    // Constructors
    //

    /**
     * Creates a new color.
     * The hex string must be in the format {@code #RRGGBB} or {@code RRGGBB}.
     *
     * @param hexString The hex string to create this color from
     */
    public RGBColor(@Nonnull String hexString) {
        super(rgbFromHex(hexString));
        enforceComponentRange();
    }

    /**
     * Creates a new color.
     * The hex integer must be in the format {@code 0xRRGGBB}.
     *
     * @param hexInt The hex code as an integer
     */
    public RGBColor(int hexInt) {
        super(rgbFromHex(hexInt));
        enforceComponentRange();
    }

    /**
     * Creates a new color.
     * All components must be within the range of {@code 0-255}.
     *
     * @param r The red component of this color
     * @param g The green component of this color
     * @param b The blue component of this color
     */
    public RGBColor(int r, int g, int b) {
        super(r, g, b);
        enforceComponentRange();
    }

    /**
     * Creates a new color.
     * All components must be within the range of {@code 0-255}.
     *
     * @param values An array containing the RGB components of this color
     */
    public RGBColor(@Nonnull int[] values) {
        super(values);
        enforceComponentRange();
    }

    /**
     * Creates a new color.
     *
     * @param color An AWT {@link Color} object
     */
    public RGBColor(@Nonnull Color color) {
        super(color.getRed(), color.getGreen(), color.getBlue());
    }

    /**
     * Creates a new vector.
     * All components must be within the range of {@code 0-255}.
     *
     * @param v The vector containing the RGB components of this color
     */
    public RGBColor(@Nonnull Vector v) {
        super(v);
        enforceComponentRange();
    }

    /**
     * Creates a new color.
     * All components must be within the range of {@code 0-255}.
     *
     * @param v The vector containing the RGB components of this color
     */
    public RGBColor(@Nonnull Vector3 v) {
        super(v);
        enforceComponentRange();
    }

    /**
     * Creates a new color.
     * All components must be within the range of {@code 0-255}.
     *
     * @param other The vector containing the RGB components of this color
     */
    public RGBColor(@Nonnull IntVector other) {
        super(other);
        enforceComponentRange();
    }

    /**
     * Creates a new color.
     * All components must be within the range of {@code 0-255}.
     *
     * @param other The vector containing the RGB components of this color
     */
    public RGBColor(@Nonnull IntVector3 other) {
        super(other);
        enforceComponentRange();
    }

    /**
     * Internal method to enforce the component range of {@code 0-255}.
     */
    private void enforceComponentRange() {
        Numbers.requireRange(x, 0, 255);
        Numbers.requireRange(y, 0, 255);
        Numbers.requireRange(z, 0, 255);
    }

    //
    // Randomization
    //

    /**
     * Returns a random color.
     *
     * @return A random color
     */
    @Nonnull
    public static RGBColor random() {
        return new RGBColor(
                (int) Numbers.random(0, 255),
                (int) Numbers.random(0, 255),
                (int) Numbers.random(0, 255)
        );
    }

    //
    // Internal
    //

    /**
     * Converts a hex string to an array of RGB components.
     *
     * @param hexString The hex code string
     * @return The RGB components in array form
     */
    @Nonnull
    private static int[] rgbFromHex(@Nonnull String hexString) {
        final String cleanHex = hexString.replace("#", "");
        if (cleanHex.length() != 6) {
            throw new IllegalArgumentException("Given string is not a hex code.");
        }

        final int[] rgb = new int[3];

        rgb[0] = Integer.parseInt(cleanHex.substring(0, 2), 16);
        rgb[1] = Integer.parseInt(cleanHex.substring(2, 4), 16);
        rgb[2] = Integer.parseInt(cleanHex.substring(4, 6), 16);

        return rgb;
    }

    /**
     * Converts a hex integer to an array of RGB components.
     *
     * @param hexInt The hex integer
     * @return The RGB components in array form
     */
    @Nonnull
    private static int[] rgbFromHex(int hexInt) {
        final int[] rgb = new int[3];

        rgb[0] = (hexInt >> 16) & 0xFF;
        rgb[1] = (hexInt >> 8) & 0xFF;
        rgb[2] = hexInt & 0xFF;

        return rgb;
    }

    //
    // Getters
    //

    /**
     * Returns the red component of this color.
     *
     * @return The red component of this color ({@code 0-255})
     */
    public final int red() {
        return x;
    }

    /**
     * Returns the green component of this color.
     *
     * @return The green component of this color ({@code 0-255})
     */
    public final int green() {
        return y;
    }

    /**
     * Returns the blue component of this color.
     *
     * @return The blue component of this color ({@code 0-255})
     */
    public final int blue() {
        return z;
    }

    /**
     * Returns an AWT {@link Color} built from this color.
     *
     * @return An AWT {@link Color} object
     */
    @Nonnull
    public Color awt() {
        return new Color(x, y, z);
    }

    //
    // Properties
    //

    /**
     * Returns the hex integer of this color.
     *
     * @return The hex integer of this color
     */
    public final int hexInt() {
        return (x << 16) | (y << 8) | z;
    }

    /**
     * Returns the hex string of this color.
     *
     * @return The hex string of this color
     */
    @Nonnull
    public final String hexString() {
        return String.format("#%02X%02X%02X", x, y, z);
    }

    //
    // Arithmetic
    //

    /**
     * Adds a color to this color.
     *
     * @param c The color to add to this color
     * @return The resulting color
     */
    @Nonnull
    public RGBColor add(@Nonnull RGBColor c) {
        return fromVector(super.add(c));
    }

    /**
     * Subtracts a color from this color.
     *
     * @param c The color to subtract from this color
     * @return The resulting color
     */
    @Nonnull
    public RGBColor subtract(@Nonnull RGBColor c) {
        return new RGBColor(super.subtract(c));
    }

    //
    // Clamping
    //

    /**
     * Returns the minimum color between this color and the provided color.
     *
     * @param c The color to get the minimum of
     * @return The minimum color
     */
    @Nonnull
    public RGBColor min(@Nonnull RGBColor c) {
        return new RGBColor(super.min(c));
    }

    /**
     * Returns the maximum color between this color and the provided color.
     *
     * @param c The color to get the maximum of
     * @return The maximum color
     */
    @Nonnull
    public RGBColor max(@Nonnull RGBColor c) {
        return new RGBColor(super.max(c));
    }

    /**
     * Returns a clamped color of this color which respects the given boundaries.
     *
     * @param min The minimum allowed values
     * @param max The maximum allowed values
     * @return The clamped color
     */
    @Nonnull
    public RGBColor clamp(@Nonnull RGBColor min, @Nonnull RGBColor max) {
        return new RGBColor(super.clamp(min, max));
    }

    //
    // Utility
    //

    /**
     * Applies given operator to all components of this color, then returns the resulting color.
     *
     * @param operator The operator to apply to each component of this color
     * @return The resulting color
     */
    @Nonnull
    @Override
    public RGBColor apply(@Nonnull UnaryOperator<Integer> operator) {
        return new RGBColor(super.apply(operator));
    }


    /**
     * Returns the proximity index of this color and the provided color {@code c}.
     * This is calculated by normalizing the squared distance of the two colors.
     * <p>
     * An index of {@code 0} means the colors are equal, while an index of {@code 1}
     * means that the colors are completely different.
     * (most likely {@link RGBColor#BLACK} and {@link RGBColor#WHITE})
     * </p>
     *
     * @param c The color to compare to
     * @return The proximity index of the two colors
     */
    public double proximity(@Nonnull RGBColor c) {
        return distance2(c) / 260100.0; // The maximum squared distance two colors can have
    }

    /**
     * Returns the reverse of this color.
     * The RGB components are reversed by applying {@code 255 - value}.
     *
     * @return The reverse of this color
     */
    @Nonnull
    public RGBColor reverse() {
        return new RGBColor(255 - x, 255 - y, 255 - z);
    }

    //
    // Serialization
    //

    /**
     * Deserializes a string into a vector.
     *
     * @param input Input string to parse
     * @return The parsed vector
     * @throws NumberFormatException When the format is invalid
     */
    @Nonnull
    public static RGBColor parseColor(@Nonnull String input) throws NumberFormatException {
        if (!input.startsWith("RGBColor{")) {
            throw new NumberFormatException("The provided string does not represent a RGBColor.");
        }

        final String cleanInput = input.replaceAll("RGBColor\\{|}", "");
        final String[] valueStrings = cleanInput.split(", ");
        final int[] values = new int[3];

        for (final String valueString : valueStrings) {
            final String[] split = valueString.split("=");
            values[switch (split[0]) {
                case "r" -> 0;
                case "g" -> 1;
                case "b" -> 2;
                default -> throw new NumberFormatException("The provided string does not represent a RGBColor.");
            }] = Integer.parseInt(split[1]);
        }

        return new RGBColor(values);
    }

    /**
     * {@inheritDoc}
     *
     * @return The string representation of this vector
     */
    @Override
    @Nonnull
    public String toString() {
        return "RGBColor{" +
                "r=" + x +
                ", g=" + y +
                ", b=" + z +
                '}';
    }
}
