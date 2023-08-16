package civitas.celestis.graphics.color;

import civitas.celestis.math.vector.*;
import jakarta.annotation.Nonnull;

import java.awt.*;

/**
 * An 8-bit color with 32 bits of precision.
 *
 * @see Color8
 */
public class LinearColor extends Float4 implements Color8 {
    //
    // Constructors
    //

    /**
     * Creates a new color by providing the 64-bit RGBA representation.
     *
     * @param rgba64 The 64-bit RGBA representation of this color
     * @see Color8#pack64(float, float, float, float)
     * @see Color8#unpack64(long)
     */
    public LinearColor(long rgba64) {
        super(Color8.unpack64(rgba64));
    }

    /**
     * Creates a new color by assigning the RGBA components.
     * All components should respect the range of {@code 0-255}.
     * The alpha component will be assigned to {@code 255}. (opaque)
     *
     * @param r The red component of this color
     * @param g The green component of this color
     * @param b The blue component of this color
     */
    public LinearColor(float r, float g, float b) {
        this(r, g, b, 255);
    }

    /**
     * Creates a new color by assigning the RGBA components.
     * All components should respect the range of {@code 0-255}.
     *
     * @param r The red component of this color
     * @param g The green component of this color
     * @param b The blue component of this color
     * @param a The alpha component of this color
     */
    public LinearColor(float r, float g, float b, float a) {
        super(r, g, b, a);
    }

    /**
     * Creates a new color.
     *
     * @param values An array containing the component values in RGBA order
     */
    public LinearColor(@Nonnull float[] values) {
        super(values);
    }

    /**
     * Creates a new color.
     * All components should respect the range of {@code 0-255}.
     *
     * @param v The vector of which to copy component values from
     */
    public LinearColor(@Nonnull FloatVector<?> v) {
        super(v);
    }

    /**
     * Creates a new color.
     * All components should respect the range of {@code 0-255}.
     *
     * @param v The vector of which to copy component values from
     */
    public LinearColor(@Nonnull Float4 v) {
        super(v);
    }

    /**
     * Creates a new color.
     * All components should respect the range of {@code 0-255}.
     *
     * @param v The vector of which to copy component values from
     */
    public LinearColor(@Nonnull Double4 v) {
        super(v);
    }

    /**
     * Creates a new color.
     * All components should respect the range of {@code 0-255}.
     *
     * @param v The vector of which to copy component values from
     */
    public LinearColor(@Nonnull Long4 v) {
        super(v);
    }

    /**
     * Creates a new color.
     * All components should respect the range of {@code 0-255}.
     *
     * @param v The vector of which to copy component values from
     */
    public LinearColor(@Nonnull Int4 v) {
        super(v);
    }

    //
    // Getters
    //

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int red() {
        return (int) w;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int green() {
        return (int) x;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int blue() {
        return (int) y;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int alpha() {
        return (int) z;
    }

    //
    // Packing
    //

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int pack24() {
        return Color8.pack24((int) w, (int) x, (int) y);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public int pack32() {
        return Color8.pack32((int) w, (int) x, (int) y, (int) z);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public long pack64() {
        return Color8.pack64(w, x, y, z);
    }

    //
    // Conversion
    //

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Color awt32() {
        return new Color(pack32(), true);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Color awt24() {
        return new Color(pack24());
    }

    //
    // Serialization
    //

    /**
     * Deserializes a string into a {@link LinearColor}.
     *
     * @param input The input string to parse
     * @return The parsed color
     * @throws IllegalArgumentException When the format is invalid
     */
    @Nonnull
    public static LinearColor parseColor(@Nonnull String input) throws IllegalArgumentException {
        if (!input.startsWith("LinearColor{")) {
            throw new IllegalArgumentException("The provided string is not a LinearColor.");
        }

        final String[] valueStrings = input.replaceAll("LinearColor\\{|}", "").split(", ");
        if (valueStrings.length != 4) {
            throw new IllegalArgumentException("The provided string does not have four components.");
        }

        final float[] values = new float[4];

        for (final String valueString : valueStrings) {
            final String[] split = valueString.split("=");
            if (split.length != 2) {
                throw new IllegalArgumentException("The format is invalid.");
            }

            values[switch (split[0]) {
                case "r" -> 0;
                case "g" -> 1;
                case "b" -> 2;
                case "a" -> 3;
                default -> throw new IllegalArgumentException("The provided string has a non-RGBA component.");
            }] = Float.parseFloat(split[1]);
        }

        return new LinearColor(values);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public String toString() {
        return "LinearColor{" +
                "r=" + w +
                ", g=" + x +
                ", b=" + y +
                ", a=" + z +
                '}';
    }
}
