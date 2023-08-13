package civitas.celestis.graphics.color;

import civitas.celestis.math.Numbers;
import civitas.celestis.math.integer.IntVector3;
import civitas.celestis.math.integer.IntVector4;
import civitas.celestis.math.vector.Vector;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.math.vector.Vector4;
import jakarta.annotation.Nonnull;

import java.awt.*;
import java.util.function.UnaryOperator;

/**
 * A 10-bit color class with linear transition capability.
 * <p>
 * Since {@link RichColor} already uses {@code double} to store the RGBA components,
 * using 10-bit colors has no significant advantage over 8-bit colors when it comes to precision.
 * <b>This class should only be used to support 10-bit capable hardware.</b>
 * </p>
 * <p><b>
 * While {@link DeepColor} and {@link RichColor} can be used in the same context
 * due to both classes inheriting {@link Vector4},
 * doing so will result in errors as they use a different scale.
 * </b></p>
 * <p>
 * Color components are stored using {@code double} with a range of {@code 0-1023}.
 * 10-bit colors do not support the conversion to and from hex codes.
 * </p>
 */
public class DeepColor extends Vector4 {
    //
    // Constants
    //

    /**
     * The color white.
     */
    public static final DeepColor WHITE = new DeepColor(1023, 1023, 1023);

    // Transparent black must be at the top of the list, since from8Bit() requires it to be initialized

    /**
     * A transparent color with the base color being black.
     */
    public static final DeepColor TRANSPARENT_BLACK = new DeepColor(0, 0, 0, 0);

    /**
     * The color light gray.
     */
    public static final DeepColor LIGHT_GRAY = from8Bit(RichColor.LIGHT_GRAY);

    /**
     * The color gray.
     */
    public static final DeepColor GRAY = from8Bit(RichColor.GRAY);

    /**
     * The color dark gray.
     */
    public static final DeepColor DARK_GRAY = from8Bit(RichColor.DARK_GRAY);

    /**
     * The color black.
     */
    public static final DeepColor BLACK = new DeepColor(0, 0, 0);

    /**
     * The color red.
     */
    public static final DeepColor RED = new DeepColor(1023, 0, 0);

    /**
     * The color pink.
     */
    public static final DeepColor PINK = from8Bit(RichColor.PINK);

    /**
     * The color orange.
     */
    public static final DeepColor ORANGE = from8Bit(RichColor.ORANGE);

    /**
     * The color yellow.
     */
    public static final DeepColor YELLOW = new DeepColor(1023, 1023, 0);

    /**
     * The color green.
     */
    public static final DeepColor GREEN = new DeepColor(0, 1023, 0);

    /**
     * The color magenta.
     */
    public static final DeepColor MAGENTA = new DeepColor(1023, 0, 1023);

    /**
     * The color cyan.
     */
    public static final DeepColor CYAN = new DeepColor(0, 1023, 1023);

    /**
     * The color blue.
     */
    public static final DeepColor BLUE = new DeepColor(0, 0, 1023);

    /**
     * The color gold.
     */
    public static final DeepColor GOLD = from8Bit(RichColor.GOLD);

    /**
     * The color brown.
     */
    public static final DeepColor BROWN = from8Bit(RichColor.BROWN);

    /**
     * A transparent color with the base color being white.
     */
    public static final DeepColor TRANSPARENT_WHITE = new DeepColor(1023, 1023, 1023, 0);

    /**
     * Any value of alpha above this value will be considered opaque by the graphics engine.
     * Higher values will be more realistic, while lower values will offer better performance
     */
    public static final double TRANSLUCENT_THRESHOLD = 971.85;

    //
    // Static Utilities
    //

    /**
     * Converts an 8-bit {@link RichColor} to a 10-bit {@link DeepColor}.
     *
     * @param c The color to convert
     * @return The converted color
     */
    @Nonnull
    public static DeepColor from8Bit(@Nonnull RichColor c) {
        return new DeepColor(Numbers.scale(
                c,
                RichColor.TRANSPARENT_BLACK,
                RichColor.WHITE,
                DeepColor.TRANSPARENT_BLACK,
                DeepColor.WHITE)
        );
    }

    /**
     * Safely converts a vector to a color by clamping its values.
     *
     * @param v The vector to convert
     * @return The converted color
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 4}
     */
    @Nonnull
    public static DeepColor fromVector(@Nonnull Vector v) {
        if (v.length() != 4) {
            throw new IllegalArgumentException("The provided vector does not have a length of 4.");
        }

        return new DeepColor(v.clamp(TRANSPARENT_BLACK, WHITE));
    }

    /**
     * Safely converts a vector to a color by clamping its values.
     *
     * @param v The vector to convert
     * @return The converted color
     */
    @Nonnull
    public static DeepColor fromVector(@Nonnull Vector4 v) {
        return new DeepColor(v.clamp(TRANSPARENT_BLACK, WHITE));
    }

    /**
     * Safely converts an RGB vector to a color by clamping its values.
     *
     * @param rgb   The RGB components to use
     * @param alpha The alpha component to use
     * @return The converted color
     */
    @Nonnull
    public static DeepColor fromVector(@Nonnull Vector3 rgb, double alpha) {
        return new DeepColor(
                rgb.clamp(new Vector3(0, 0, 0), new Vector3(1023, 1023, 1023)),
                Numbers.clamp(alpha, 0, 1023)
        );
    }

    /**
     * Checks for equality between two colors while accounting for differences in decimal places.
     * This rounds the RGBA components, then checks them for equality.
     *
     * @param c1 The first color to compare
     * @param c2 The second color to compare
     * @return {@code true} if the colors are effectively equal
     */
    public static boolean equals(@Nonnull DeepColor c1, @Nonnull DeepColor c2) {
        return c1.rgbaInt().equals(c2.rgbaInt());
    }

    /**
     * Returns the simple average of given colors.
     *
     * @param colors The colors to average
     * @return The simple average of the provided colors
     */
    @Nonnull
    public static DeepColor avg(@Nonnull DeepColor... colors) {
        if (colors.length == 0) {
            return TRANSPARENT_BLACK; // {0, 0, 0, 0}
        }

        double r = 0;
        double g = 0;
        double b = 0;
        double a = 0;

        for (final DeepColor color : colors) {
            r += color.x;
            g += color.y;
            b += color.z;
            a += color.w;
        }

        r /= colors.length;
        g /= colors.length;
        b /= colors.length;
        a /= colors.length;

        return new DeepColor(r, g, b, a);
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
    public static DeepColor avgw(@Nonnull DeepColor c1, @Nonnull DeepColor c2, double w1, double w2) {
        final Vector4 v1 = c1.multiply(w1);
        final Vector4 v2 = c2.multiply(w2);

        return new DeepColor(v1.add(v2).divide(w1 + w2));
    }

    /**
     * Returns the weighted average of given colors.
     *
     * @param colors  The colors to average
     * @param weights The weights of the colors
     * @return The weighted average of the given colors
     */
    @Nonnull
    public static DeepColor avgw(@Nonnull DeepColor[] colors, @Nonnull double[] weights) {
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

        return new DeepColor(new Vector4(a, r, g, b).divide(Numbers.sum(weights)));
    }

    //
    // Constructors
    //

    /**
     * Creates a new opaque color. The alpha will be {@code 1023}.
     * All parameters must be within the range of {@code 0-1023}.
     *
     * @param r The red component of this color
     * @param g The green component of this color
     * @param b The blue component of this color
     */
    public DeepColor(double r, double g, double b) {
        this(r, g, b, 1023);
    }

    /**
     * Creates a new color.
     * All parameters must be within the range of {@code 0-1023}.
     *
     * @param r The red component of this color
     * @param g The green component of this color
     * @param b The blue component of this color
     * @param a The alpha component of this color
     */
    public DeepColor(double r, double g, double b, double a) {
        super(a, r, g, b);
        enforceComponentRange();
    }

    /**
     * Creates a new color.
     *
     * @param color An AWT {@link Color} object
     */
    public DeepColor(@Nonnull Color color) {
        super(
                Numbers.scale(color.getAlpha(), 0, 255, 0, 1023),
                Numbers.scale(color.getRed(), 0, 255, 0, 1023),
                Numbers.scale(color.getGreen(), 0, 255, 0, 1023),
                Numbers.scale(color.getBlue(), 0, 255, 0, 1023)
        );
    }

    /**
     * Creates a new color.
     *
     * @param color An {@link RGBColor} object
     */
    public DeepColor(@Nonnull RGBColor color) {
        super(from8Bit(new RichColor(color)));
    }

    /**
     * Creates a new color.
     * All parameters must be within the range of {@code 0-255}.
     *
     * @param rgb The RGB components of this color mapped in {@code x, y, z} respectively
     * @param a   The alpha component of this color
     */
    public DeepColor(@Nonnull Vector3 rgb, double a) {
        this(rgb.x(), rgb.y(), rgb.z(), a);
    }

    /**
     * Creates a new color.
     * All parameters must be within the range of {@code 0-255}.
     *
     * @param values An array containing the color components
     */
    public DeepColor(@Nonnull double[] values) {
        super(values);
        enforceComponentRange();
    }

    /**
     * Creates a new color.
     * All parameters must be within the range of {@code 0-255}.
     *
     * @param v The vector containing the color components
     */
    public DeepColor(@Nonnull Vector v) {
        super(v);
        enforceComponentRange();
    }

    /**
     * Creates a new color.
     * All parameters must be within the range of {@code 0-255}.
     *
     * @param v The vector containing the color components
     */
    public DeepColor(@Nonnull Vector4 v) {
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
    public static DeepColor random() {
        return new DeepColor(
                Numbers.random(0, 1023),
                Numbers.random(0, 1023),
                Numbers.random(0, 1023)
        );
    }

    //
    // Internal
    //

    /**
     * Internal method use to enforce the component range of {@code 0-255}.
     */
    private void enforceComponentRange() {
        Numbers.requireRange(w, 0, 1023);
        Numbers.requireRange(x, 0, 1023);
        Numbers.requireRange(y, 0, 1023);
        Numbers.requireRange(z, 0, 1023);
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
     * Returns the RGB components of this color, rounded to the nearest integer value.
     *
     * @return The rounded RGB components of this color
     */
    @Nonnull
    public final IntVector3 rgbInt() {
        return new IntVector3((int) Math.round(x), (int) Math.round(y), (int) Math.round(z));
    }

    /**
     * Returns the RGBA components of this color, rounded to the nearest integer value.
     *
     * @return The rounded RGBA components of this color
     */
    @Nonnull
    public final IntVector4 rgbaInt() {
        return new IntVector4((int) Math.round(w), (int) Math.round(x), (int) Math.round(y), (int) Math.round(z));
    }

    /**
     * Returns an AWT {@link Color} built from this color.
     * Components are rounded to the nearest integer.
     *
     * @return An AWT {@link Color} object
     */
    @Nonnull
    public final Color awt() {
        return new Color(
                (int) Math.round(Numbers.scale(x, 0, 1023, 0, 255)),
                (int) Math.round(Numbers.scale(y, 0, 1023, 0, 255)),
                (int) Math.round(Numbers.scale(z, 0, 1023, 0, 255)),
                (int) Math.round(Numbers.scale(w, 0, 1023, 0, 255))
        );
    }

    /**
     * Returns an AWT {@link Color} built from this color.
     * Decimal places in the components are dropped for faster conversion.
     *
     * @return An AWT {@link Color} object
     */
    @Nonnull
    public final Color awtRaw() {
        return new Color(
                (int) Numbers.scale(x, 0, 1023, 0, 255),
                (int) Numbers.scale(y, 0, 1023, 0, 255),
                (int) Numbers.scale(z, 0, 1023, 0, 255),
                (int) Numbers.scale(w, 0, 1023, 0, 255)
        );
    }

    /**
     * Converts this color into an 8-bit color.
     *
     * @return The 8-bit representation of this color
     */
    @Nonnull
    public final RichColor to8Bit() {
        return new RichColor(Numbers.scale(
                this,
                DeepColor.TRANSPARENT_BLACK,
                DeepColor.WHITE,
                RichColor.TRANSPARENT_BLACK,
                RichColor.WHITE
        ));
    }

    //
    // Properties
    //

    /**
     * Returns whether this color is translucent.
     *
     * @return {@code true} if this color is translucent
     * @see DeepColor#TRANSLUCENT_THRESHOLD
     */
    public boolean translucent() {
        return w <= TRANSLUCENT_THRESHOLD;
    }

    /**
     * Returns whether this color is opaque.
     *
     * @return {@code true} if this color is opaque
     * @see DeepColor#TRANSLUCENT_THRESHOLD
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
        return ((x + y + z) / 3069) * (w / 1023);
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
    public DeepColor add(@Nonnull DeepColor c) {
        return fromVector(super.add(c));
    }

    /**
     * Subtracts a color from this color.
     *
     * @param c The color to subtract from this color
     * @return The resulting color
     */
    @Nonnull
    public DeepColor subtract(@Nonnull DeepColor c) {
        return new DeepColor(super.subtract(c));
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
    public DeepColor min(@Nonnull DeepColor c) {
        return new DeepColor(super.min(c));
    }

    /**
     * Returns the maximum color between this color and the provided color.
     *
     * @param c The color to get the maximum of
     * @return The maximum color
     */
    @Nonnull
    public DeepColor max(@Nonnull DeepColor c) {
        return new DeepColor(super.max(c));
    }

    /**
     * Returns a clamped color of this color which respects the given boundaries.
     *
     * @param min The minimum allowed values
     * @param max The maximum allowed values
     * @return The clamped color
     */
    @Nonnull
    public DeepColor clamp(@Nonnull DeepColor min, @Nonnull DeepColor max) {
        return new DeepColor(super.clamp(min, max));
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
    public DeepColor apply(@Nonnull UnaryOperator<Double> operator) {
        return new DeepColor(super.apply(operator));
    }

    /**
     * Applies given operator to the RGB components of this color.
     *
     * @param operator The operator to apply on the RGB components
     * @return The resulting color
     */
    @Nonnull
    public DeepColor applyRGB(@Nonnull UnaryOperator<Vector3> operator) {
        return new DeepColor(operator.apply(rgb()), w);
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

    /**
     * Returns the proximity index of this color and the provided color {@code c}.
     * This is calculated by normalizing the squared distance of the two colors.
     * <p>
     * An index of {@code 0} means the colors are equal, while an index of {@code 1}
     * means that the colors are completely different.
     * (most likely {@link DeepColor#TRANSPARENT_BLACK} and {@link DeepColor#WHITE})
     * </p>
     *
     * @param c The color to compare to
     * @return The proximity index of the two colors
     */
    public double proximity(@Nonnull DeepColor c) {
        return distance2(c) / 4186116.0; // The maximum squared distance two colors can have
    }

    /**
     * Returns the reverse of this color.
     * The RGB components are reversed by applying {@code 255 - value},
     * and the alpha component is preserved as-is.
     *
     * @return The reverse of this color
     */
    @Nonnull
    public DeepColor reverse() {
        return new DeepColor(1023 - x, 1023 - y, 1023 - z, w);
    }

    //
    // Serialization
    //

    /**
     * Deserializes a string into a {@link DeepColor}.
     *
     * @param input The input to deserialize
     * @return The parsed vector
     * @throws NumberFormatException    When the format is invalid
     * @throws IllegalArgumentException When at least one of the component scalars is non-finite
     */
    @Nonnull
    public static DeepColor parseColor(@Nonnull String input) throws IllegalArgumentException {
        if (!input.startsWith("DeepColor{")) {
            throw new NumberFormatException("The provided string does not represent a DeepColor.");
        }

        final String cleanInput = input.replaceAll("DeepColor\\{|}", "");
        final String[] valueStrings = cleanInput.split(", ");
        final double[] values = {Double.NaN, Double.NaN, Double.NaN, Double.NaN};

        for (final String valueString : valueStrings) {
            final String[] split = valueString.split("=");
            values[switch (split[0]) {
                case "a" -> 0;
                case "r" -> 1;
                case "g" -> 2;
                case "b" -> 3;
                default -> throw new NumberFormatException("The provided string does not represent a DeepColor.");
            }] = Double.parseDouble(split[1]);
        }

        return new DeepColor(values);
    }

    /**
     * {@inheritDoc}
     *
     * @return The string representation of this vector
     */
    @Override
    @Nonnull
    public String toString() {
        return "DeepColor{" +
                "r=" + x +
                ", g=" + y +
                ", b=" + z +
                ", a=" + w +
                '}';
    }
}
