package civitas.celestis.graphics.color;

import civitas.celestis.math.Numbers;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.math.vector.Vector4;
import civitas.celestis.util.packing.Packable;
import jakarta.annotation.Nonnull;

import java.awt.*;
import java.util.function.UnaryOperator;

/**
 * An 8-bit color class with linear transition capability.
 * Color components are stored using {@code double} with a range of {@code 0-255}.
 * <p>
 * Colors can be packed into 64 bits by {@link RichColor#pack()}, and unpacked by calling the
 * {@link RichColor#unpack(long)}. Very little precision is lost in the process.
 * </p>
 */
public class RichColor extends Vector4 implements Packable {
    //
    // Constants
    //

    /**
     * The color white.
     */
    public static final RichColor WHITE = new RichColor(255, 255, 255);

    /**
     * The color light gray.
     */
    public static final RichColor LIGHT_GRAY = new RichColor(192, 192, 192);

    /**
     * The color gray.
     */
    public static final RichColor GRAY = new RichColor(128, 128, 128);

    /**
     * The color dark gray.
     */
    public static final RichColor DARK_GRAY = new RichColor(64, 64, 64);

    /**
     * The color black.
     */
    public static final RichColor BLACK = new RichColor(0, 0, 0);

    /**
     * The color red.
     */
    public static final RichColor RED = new RichColor(255, 0, 0);

    /**
     * The color pink.
     */
    public static final RichColor PINK = new RichColor(255, 175, 175);

    /**
     * The color orange.
     */
    public static final RichColor ORANGE = new RichColor(255, 200, 0);

    /**
     * The color yellow.
     */
    public static final RichColor YELLOW = new RichColor(255, 255, 0);

    /**
     * The color green.
     */
    public static final RichColor GREEN = new RichColor(0, 255, 0);

    /**
     * The color magenta.
     */
    public static final RichColor MAGENTA = new RichColor(255, 0, 255);

    /**
     * The color cyan.
     */
    public static final RichColor CYAN = new RichColor(0, 255, 255);

    /**
     * The color blue.
     */
    public static final RichColor BLUE = new RichColor(0, 0, 255);

    /**
     * The color gold.
     */
    public static final RichColor GOLD = new RichColor(218, 165, 32);

    /**
     * The color brown.
     */
    public static final RichColor BROWN = new RichColor(139, 69, 19);

    /**
     * A transparent color with the base color being white.
     */
    public static final RichColor TRANSPARENT_WHITE = new RichColor(255, 255, 255, 0);

    /**
     * A transparent color with the base color being black.
     */
    public static final RichColor TRANSPARENT_BLACK = new RichColor(0, 0, 0, 0);

    /**
     * Any value of alpha above this value will be considered opaque by the graphics engine.
     * Higher values will be more realistic, while lower values will offer better performance
     */
    public static final double TRANSLUCENT_THRESHOLD = 242.25;

    //
    // Static Utilities
    //

    /**
     * Safely converts a vector to a color by clamping its values.
     *
     * @param v The vector to convert
     * @return The converted color
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 4}
     */
    @Nonnull
    public static RichColor fromVector(@Nonnull Vector4 v) {
        return new RichColor(v.clamp(TRANSPARENT_BLACK, WHITE));
    }

    /**
     * Safely converts an RGB vector to a color by clamping its values.
     *
     * @param rgb   The RGB components to use
     * @param alpha The alpha component to use
     * @return The converted color
     */
    @Nonnull
    public static RichColor fromVector(@Nonnull Vector3 rgb, double alpha) {
        return new RichColor(
                rgb.clamp(new Vector3(0, 0, 0), new Vector3(255, 255, 255)),
                Numbers.clamp(alpha, 0, 255)
        );
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
    public RichColor(@Nonnull String hexString) {
        this(hexString, 255);
    }

    /**
     * Creates a new color.
     * The hex string must be in the format {@code #RRGGBB} or {@code RRGGBB}.
     * The alpha component must be within the range of {@code 0-255}.
     *
     * @param hexString The hex string to create this color from
     * @param alpha     The alpha component of this color
     */
    public RichColor(@Nonnull String hexString, double alpha) {
        super(rgbaFromHex(hexString, alpha));
        enforceComponentRange();
    }

    /**
     * Creates a new color.
     * The hex integer must be in the format {@code 0xRRGGBB}.
     *
     * @param hexInt The hex code as an integer
     */
    public RichColor(int hexInt) {
        this(hexInt, 255);
    }

    /**
     * Creates a new color.
     * The hex integer must be in the format {@code 0xRRGGBB}.
     * The alpha component must be within the range of {@code 0-255}.
     *
     * @param hexInt The hex code as an integer
     * @param alpha  The alpha component of this color
     */
    public RichColor(int hexInt, double alpha) {
        super(rgbaFromHex(hexInt, alpha));
        enforceComponentRange();
    }

    /**
     * Creates a new opaque color. The alpha will be {@code 255}.
     * All parameters must be within the range of {@code 0-255}.
     *
     * @param r The red component of this color
     * @param g The green component of this color
     * @param b The blue component of this color
     */
    public RichColor(double r, double g, double b) {
        this(r, g, b, 255);
    }

    /**
     * Creates a new color.
     * All parameters must be within the range of {@code 0-255}.
     *
     * @param r The red component of this color
     * @param g The green component of this color
     * @param b The blue component of this color
     * @param a The alpha component of this color
     */
    public RichColor(double r, double g, double b, double a) {
        super(a, r, g, b);
        enforceComponentRange();
    }

    /**
     * Creates a new color.
     *
     * @param color An AWT {@link Color} object
     */
    public RichColor(@Nonnull Color color) {
        super(color.getAlpha(), color.getRed(), color.getBlue(), color.getGreen());
    }

    /**
     * Creates a new color.
     * All parameters must be within the range of {@code 0-255}.
     *
     * @param rgb The RGB components of this color mapped in {@code x, y, z} respectively
     * @param a   The alpha component of this color
     */
    public RichColor(@Nonnull Vector3 rgb, double a) {
        this(rgb.x(), rgb.y(), rgb.z(), a);
    }

    /**
     * Creates a new color.
     * All parameters must be within the range of {@code 0-255}.
     *
     * @param values An array containing the color components
     */
    public RichColor(@Nonnull double[] values) {
        super(values);
        enforceComponentRange();
    }

    /**
     * Creates a new color.
     * All parameters must be within the range of {@code 0-255}.
     *
     * @param v The vector containing the color components
     */
    public RichColor(@Nonnull Vector4 v) {
        super(v);
        enforceComponentRange();
    }

    //
    // Randomization
    //

    /**
     * Returns a random opaque color.
     *
     * @return A random color with an alpha of {@code 255}
     */
    @Nonnull
    public static RichColor random() {
        return new RichColor(
                Numbers.random(0, 255),
                Numbers.random(0, 255),
                Numbers.random(0, 255)
        );
    }

    //
    // Packing
    //

    private static final long R_COMPONENT_SHIFT = 48;
    private static final long G_COMPONENT_SHIFT = 32;
    private static final long B_COMPONENT_SHIFT = 16;
    private static final double COMPONENT_PACK_FACTOR = (Math.pow(2, 16) - 1) / 255;
    private static final double COMPONENT_UNPACK_FACTOR = 1 / COMPONENT_PACK_FACTOR;

    /**
     * Unpacks the RGBA components from a packed {@code long}.
     *
     * @param packed The packed value
     * @return The unpacked color
     * @see RichColor#pack()
     */
    @Nonnull
    public static RichColor unpack(long packed) {
        return new RichColor(
                (packed & 0xFFFF) * COMPONENT_UNPACK_FACTOR,
                ((packed >> R_COMPONENT_SHIFT) & 0xFFFF) * COMPONENT_UNPACK_FACTOR,
                ((packed >> G_COMPONENT_SHIFT) & 0xFFFF) * COMPONENT_UNPACK_FACTOR,
                ((packed >> B_COMPONENT_SHIFT) & 0xFFFF) * COMPONENT_UNPACK_FACTOR
        );
    }

    /**
     * Packs the RGBA components into a single {@code long}.
     * <p>
     * The first 16 bits are used as an unsigned integer to represent the red component,
     * the next 16 bits are used in the same manner to represent the green component,
     * the next 16 bits for the blue, and the final 16 bits for the alpha component.
     * </p>
     *
     * @return The packed {@code long}
     * @see RichColor#unpack(long)
     */
    @Override
    public final long pack() {
        return (((long) (x * COMPONENT_PACK_FACTOR)) << R_COMPONENT_SHIFT)
                | (((long) (y * COMPONENT_PACK_FACTOR)) << G_COMPONENT_SHIFT)
                | (((long) (z * COMPONENT_PACK_FACTOR)) << B_COMPONENT_SHIFT)
                | ((long) (w * COMPONENT_PACK_FACTOR));
    }

    //
    // Internal
    //

    /**
     * Converts a hex string to an array of RGBA components.
     *
     * @param hexString The hex code string
     * @param alpha     The alpha component
     * @return The RGBA components in array form
     */
    @Nonnull
    private static double[] rgbaFromHex(@Nonnull String hexString, double alpha) {
        final String cleanHex = hexString.replace("#", "");
        if (cleanHex.length() != 6) {
            throw new IllegalArgumentException("Given string is not a hex code.");
        }

        final double[] rgba = new double[4];

        rgba[1] = Integer.parseInt(cleanHex.substring(0, 2), 16);
        rgba[2] = Integer.parseInt(cleanHex.substring(2, 4), 16);
        rgba[3] = Integer.parseInt(cleanHex.substring(4, 6), 16);
        rgba[0] = alpha;

        return rgba;
    }

    /**
     * Converts a hex integer to an array of RGBA components.
     *
     * @param hexInt The hex integer
     * @param alpha  The alpha component
     * @return The RGBA components in array form
     */
    @Nonnull
    private static double[] rgbaFromHex(int hexInt, double alpha) {
        final double[] rgba = new double[4];

        rgba[1] = (hexInt >> 16) & 0xFF;
        rgba[2] = (hexInt >> 8) & 0xFF;
        rgba[3] = hexInt & 0xFF;
        rgba[0] = alpha;

        return rgba;
    }

    /**
     * Internal method use to enforce the component range of {@code 0-255}.
     */
    private void enforceComponentRange() {
        Numbers.requireRange(w, 0, 255);
        Numbers.requireRange(x, 0, 255);
        Numbers.requireRange(y, 0, 255);
        Numbers.requireRange(z, 0, 255);
    }

    //
    // Getters
    //

    /**
     * Returns the red component of this color.
     *
     * @return The red component of this color
     */
    public final double red() {
        return x;
    }

    /**
     * Returns the green component of this color.
     *
     * @return The green component of this color
     */
    public final double green() {
        return y;
    }

    /**
     * Returns the blue component of this color.
     *
     * @return The blue component of this color
     */
    public final double blue() {
        return z;
    }

    /**
     * Returns the alpha component of this color.
     *
     * @return The alpha component of this color
     */
    public final double alpha() {
        return w;
    }

    /**
     * Returns the RGB components of this color.
     *
     * @return The RGB components of this color
     */
    @Nonnull
    public final Vector3 rgb() {
        return new Vector3(x, y, z);
    }

    /**
     * Returns the RGBA components of this color.
     *
     * @return The RGBA components of this color
     */
    @Nonnull
    public final Vector4 rgba() {
        return new Vector4(w, x, y, z);
    }

    /**
     * Returns an AWT {@link Color} built from this color.
     * Components are rounded to the nearest integer.
     *
     * @return An AWT {@link Color} object
     */
    @Nonnull
    public final Color awt() {
        return new Color((int) Math.round(x), (int) Math.round(y), (int) Math.round(z), (int) Math.round(w));
    }

    /**
     * Returns an AWT {@link Color} built from this color.
     * Decimal places in the components are dropped for faster conversion.
     *
     * @return An AWT {@link Color} object
     */
    @Nonnull
    public final Color awtRaw() {
        return new Color((int) x, (int) y, (int) z, (int) w);
    }

    //
    // Properties
    //

    /**
     * Returns whether this color is translucent.
     *
     * @return {@code true} if this color is translucent
     * @see RichColor#TRANSLUCENT_THRESHOLD
     */
    public boolean translucent() {
        return w <= TRANSLUCENT_THRESHOLD;
    }

    /**
     * Returns whether this color is opaque.
     *
     * @return {@code true} if this color is opaque
     * @see RichColor#TRANSLUCENT_THRESHOLD
     */
    public boolean opaque() {
        return w > TRANSLUCENT_THRESHOLD;
    }

    /**
     * Calculates the reflectiveness coefficient of this color.
     * This is calculated by normalizing the sum of the RGB components,
     * then multiplying it by the normalized alpha component.
     *
     * @return The reflectiveness coefficient of this color ({@code 0-1})
     */
    public double reflectiveness() {
        return ((x + y + z) / 765) * (w / 255);
    }

    /**
     * Returns the hex integer of this color.
     *
     * @return The hex integer of this color
     */
    public final int hexInt() {
        final int r = (int) Math.round(x);
        final int g = (int) Math.round(y);
        final int b = (int) Math.round(z);

        return (r << 16) | (g << 8) | b;
    }

    /**
     * Returns the hex string of this color.
     *
     * @return The hex string of this color
     */
    @Nonnull
    public final String hexString() {
        final int r = (int) Math.round(x);
        final int g = (int) Math.round(y);
        final int b = (int) Math.round(z);

        return String.format("#%02X%02X%02X", r, g, b);
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
    public RichColor add(@Nonnull RichColor c) {
        return fromVector(super.add(c));
    }

    /**
     * Subtracts a color from this color.
     *
     * @param c The color to subtract from this color
     * @return The resulting color
     */
    @Nonnull
    public RichColor subtract(@Nonnull RichColor c) {
        return new RichColor(super.subtract(c));
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
    public RichColor min(@Nonnull RichColor c) {
        return new RichColor(super.min(c));
    }

    /**
     * Returns the maximum color between this color and the provided color.
     *
     * @param c The color to get the maximum of
     * @return The maximum color
     */
    @Nonnull
    public RichColor max(@Nonnull RichColor c) {
        return new RichColor(super.max(c));
    }

    /**
     * Returns a clamped color of this color which respects the given boundaries.
     *
     * @param min The minimum allowed values
     * @param max The maximum allowed values
     * @return The clamped color
     */
    @Nonnull
    public RichColor clamp(@Nonnull RichColor min, @Nonnull RichColor max) {
        return new RichColor(super.clamp(min, max));
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
    public RichColor transform(@Nonnull UnaryOperator<Double> operator) {
        return new RichColor(super.transform(operator));
    }

    /**
     * Applies given operator to the RGB components of this color.
     *
     * @param operator The operator to apply on the RGB components
     * @return The resulting color
     */
    @Nonnull
    public RichColor transformRGB(@Nonnull UnaryOperator<Vector3> operator) {
        return new RichColor(operator.apply(rgb()), w);
    }

    /**
     * Normalizes this color to fit within the range of {@code 0-1}.
     * If the magnitude of this color is zero (the color is black and the alpha is zero),
     * this will return {@link Vector4#ZERO}.
     *
     * @return The normalized vector representing the RGBA components of this color
     */
    @Nonnull
    @Override
    public Vector4 normalize() {
        try {
            return divide(norm());
        } catch (final ArithmeticException e) {
            return ZERO; // A magnitude of zero means all components are zero
        }
    }

    /**
     * Returns the proximity index of this color and the provided color {@code c}.
     * This is calculated by normalizing the squared distance of the two colors.
     * <p>
     * An index of {@code 0} means the colors are equal, while an index of {@code 1}
     * means that the colors are completely different.
     * (most likely {@link RichColor#TRANSPARENT_BLACK} and {@link RichColor#WHITE})
     * </p>
     *
     * @param c The color to compare to
     * @return The proximity index of the two colors
     */
    public double proximity(@Nonnull RichColor c) {
        return distance2(c) / 260100.0; // The maximum squared distance two colors can have
    }

    /**
     * Returns the reverse of this color.
     * The RGB components are reversed by applying {@code 255 - value},
     * and the alpha component is preserved as-is.
     *
     * @return The reverse of this color
     */
    @Nonnull
    public RichColor reverse() {
        return new RichColor(255 - x, 255 - y, 255 - z, w);
    }

    //
    // Serialization
    //

    /**
     * Deserializes a string into a {@link RichColor}.
     *
     * @param input The input to deserialize
     * @return The parsed vector
     * @throws NumberFormatException    When the format is invalid
     * @throws IllegalArgumentException When at least one of the component scalars is non-finite
     */
    @Nonnull
    public static RichColor parseColor(@Nonnull String input) throws IllegalArgumentException {
        if (!input.startsWith("RichColor{")) {
            throw new NumberFormatException("The provided string does not represent a RichColor.");
        }

        final String cleanInput = input.replaceAll("RichColor\\{|}", "");
        final String[] valueStrings = cleanInput.split(", ");
        final double[] values = {Double.NaN, Double.NaN, Double.NaN, Double.NaN};

        for (final String valueString : valueStrings) {
            final String[] split = valueString.split("=");
            values[switch (split[0]) {
                case "a" -> 0;
                case "r" -> 1;
                case "g" -> 2;
                case "b" -> 3;
                default -> throw new NumberFormatException("The provided string does not represent a RichColor.");
            }] = Double.parseDouble(split[1]);
        }

        return new RichColor(values);
    }

    /**
     * {@inheritDoc}
     *
     * @return The string representation of this vector
     */
    @Override
    @Nonnull
    public String toString() {
        return "RichColor{" +
                "r=" + x +
                ", g=" + y +
                ", b=" + z +
                ", a=" + w +
                '}';
    }
}