package civitas.celestis.graphics.color;

import civitas.celestis.math.Numbers;
import civitas.celestis.math.vector.Vector;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.math.vector.Vector4;
import jakarta.annotation.Nonnull;

import java.awt.*;
import java.util.function.UnaryOperator;

/**
 * A color class with linear transition capability.
 * Color components are stored using {@code double} with a range of {@code 0-1}
 */
public class RichColor extends Vector4 {
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
     * A transparent color with the base color being white.
     */
    public static final RichColor TRANSPARENT_WHITE = new RichColor(255, 255, 255, 0);

    /**
     * A transparent color with the base color being black.
     */
    public static final RichColor TRANSPARENT_BLACK = new RichColor(0, 0, 0, 0);

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
    public static RichColor fromVector(@Nonnull Vector v) {
        if (v.length() != 4) {
            throw new IllegalArgumentException("The provided vector does not have a length of 4.");
        }

        return new RichColor(v.clamp(TRANSPARENT_BLACK, WHITE));
    }

    /**
     * Safely converts a vector to a color by clamping its values.
     *
     * @param v The vector to convert
     * @return The converted color
     */
    @Nonnull
    public static RichColor fromVector(@Nonnull Vector4 v) {
        return new RichColor(v.clamp(TRANSPARENT_BLACK, WHITE));
    }

    /**
     * Returns the simple average of given colors.
     *
     * @param colors The colors to average
     * @return The simple average of the provided colors
     */
    @Nonnull
    public static RichColor avg(@Nonnull RichColor... colors) {
        if (colors.length == 0) {
            return TRANSPARENT_BLACK; // {0, 0, 0, 0}
        }

        double r = 0;
        double g = 0;
        double b = 0;
        double a = 0;

        for (final RichColor color : colors) {
            r += color.x;
            g += color.y;
            b += color.z;
            a += color.w;
        }

        r /= colors.length;
        g /= colors.length;
        b /= colors.length;
        a /= colors.length;

        return new RichColor(r, g, b, a);
    }

    /**
     * Returns the weighted average of two colors.
     *
     * @param c1 The first color
     * @param c2 The second color
     * @param w1 The weight of the first color
     * @param w2 The weight of the second color
     * @return The weighted average of the two colors
     */
    @Nonnull
    public static RichColor avg(@Nonnull RichColor c1, @Nonnull RichColor c2, double w1, double w2) {
        final Vector4 v1 = c1.multiply(w1);
        final Vector4 v2 = c2.multiply(w2);

        return new RichColor(v1.add(v2).divide(w1 + w2));
    }

    /**
     * Returns the weighted average of given colors.
     *
     * @param colors  The colors to average
     * @param weights The weights of the colors
     * @return The weighted average of the given colors
     */
    @Nonnull
    public static RichColor avg(@Nonnull RichColor[] colors, @Nonnull double[] weights) {
        if (colors.length != weights.length) {
            throw new IllegalArgumentException("The length of colors and weights are different.");
        }

        double r = 0;
        double g = 0;
        double b = 0;
        double a = 0;

        for (int i = 0; i < colors.length; i++) {
            final Vector4 weighted = colors[i].multiply(weights[i]);

            r += weighted.x();
            g += weighted.y();
            b += weighted.z();
            a += weighted.w();
        }

        return new RichColor(new Vector4(a, r, g, b).divide(Numbers.sum(weights)));
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
     * All parameters must be within the range of {@code 0-255}.
     *
     * @param color An AWT {@link Color} object
     */
    public RichColor(@Nonnull Color color) {
        this(color.getAlpha(), color.getRed(), color.getBlue(), color.getGreen());
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
    public RichColor(@Nonnull Vector v) {
        super(v);
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
    public final Color color() {
        return new Color((int) Math.round(x), (int) Math.round(y), (int) Math.round(z), (int) Math.round(w));
    }

    /**
     * Returns an AWT {@link Color} built from this color.
     * Decimal places in the components are dropped for faster conversion.
     *
     * @return An AWT {@link Color} object
     */
    @Nonnull
    public final Color colorRaw() {
        return new Color((int) x, (int) y, (int) z, (int) w);
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
        return fromVector(super.subtract(c));
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
    public RichColor apply(@Nonnull UnaryOperator<Double> operator) {
        return new RichColor(super.apply(operator));
    }

    /**
     * Applies given operator to the RGB components of this color.
     *
     * @param operator The operator to apply on the RGB components
     * @return The resulting color
     */
    @Nonnull
    public RichColor applyRGB(@Nonnull UnaryOperator<Vector3> operator) {
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
            return divide(magnitude());
        } catch (final ArithmeticException e) {
            return ZERO; // A magnitude of zero means all components are zero
        }
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
