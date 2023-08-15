package civitas.celestis.graphics.color;

import civitas.celestis.math.Numbers;
import civitas.celestis.math.vector.Float4;
import civitas.celestis.math.vector.Int4;
import civitas.celestis.math.vector.Long4;
import civitas.celestis.math.vector.Vector4;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * An immutable color capable of linear transition and easy manipulation.
 * This class is designed for linear transition, as decimal places can be
 * represented due to the use of {@code float} for each component.
 * <p>
 * This implementation has 32 bits of precision for storage and manipulation,
 * and 8 bits of precision for output.
 * </p>
 */
public class LinearColor extends Float4 implements Color8 {
    //
    // Constants
    //

    public static final LinearColor WHITE = new LinearColor(255, 255, 255, 255);
    public static final LinearColor RED = new LinearColor(255, 0, 0, 255);
    public static final LinearColor GREEN = new LinearColor(0, 255, 0, 255);
    public static final LinearColor BLUE = new LinearColor(0, 0, 255, 255);
    public static final LinearColor CYAN = new LinearColor(0, 255, 255, 255);
    public static final LinearColor MAGENTA = new LinearColor(255, 0, 255, 255);
    public static final LinearColor YELLOW = new LinearColor(255, 255, 0, 255);
    public static final LinearColor BLACK = new LinearColor(0, 0, 0, 255);
    public static final LinearColor TRANSPARENT_BLACK = new LinearColor(0, 0, 0, 0);

    //
    // Constructors
    //

    /**
     * Creates a new opaque color.
     *
     * @param r The red component of this color
     * @param g The green component of this color
     * @param b The blue component of this color
     */
    public LinearColor(float r, float g, float b) {
        super(r, g, b, 255);
        enforceComponentRange();
    }

    /**
     * Creates a new color.
     *
     * @param r The red component of this color
     * @param g The green component of this color
     * @param b The blue component of this color
     * @param a The alpha component of this color
     */
    public LinearColor(float r, float g, float b, float a) {
        super(r, g, b, a);
        enforceComponentRange();
    }

    /**
     * Creates a new color.
     *
     * @param values An array containing the component values
     */
    public LinearColor(@Nonnull float[] values) {
        super(values);
        enforceComponentRange();
    }

    /**
     * Creates a new color.
     * Copying a {@link LinearColor} through this constructor will drop the
     * decimal places of the RGBA components. (it is also slower than {@link LinearColor#LinearColor(LinearColor)})
     *
     * @param c The color of which to copy component values from
     */
    public LinearColor(@Nonnull Color8 c) {
        super(c.red(), c.green(), c.blue(), c.alpha());
        enforceComponentRange();
    }

    /**
     * Creates a new color.
     *
     * @param other The color of which to copy component values from
     */
    public LinearColor(@Nonnull LinearColor other) {
        super(other);
    }

    /**
     * Creates a new color.
     *
     * @param v The vector of which to copy component values from
     */
    public LinearColor(@Nonnull Float4 v) {
        super(v);
        enforceComponentRange();
    }

    /**
     * Creates a new color.
     *
     * @param v The vector of which to copy component values from
     */
    public LinearColor(@Nonnull Vector4 v) {
        super(v);
        enforceComponentRange();
    }

    /**
     * Creates a new color.
     *
     * @param v The vector of which to copy component values from
     */
    public LinearColor(@Nonnull Long4 v) {
        super(v);
        enforceComponentRange();
    }

    /**
     * Creates a new color.
     *
     * @param v The vector of which to copy component values from
     */
    public LinearColor(@Nonnull Int4 v) {
        super(v);
        enforceComponentRange();
    }

    /**
     * Called to enforce the component range.
     */
    protected void enforceComponentRange() {
        Numbers.requireRange(w, 0, 255);
        Numbers.requireRange(x, 0, 255);
        Numbers.requireRange(y, 0, 255);
        Numbers.requireRange(z, 0, 255);
    }

    //
    // Packing
    //

    protected static final long R_COMPONENT_SHIFT = 48;
    protected static final long G_COMPONENT_SHIFT = 32;
    protected static final long B_COMPONENT_SHIFT = 16;
    protected static final float COMPONENT_PACK_FACTOR = (float) (Math.pow(2, 16) - 1) / 255;
    protected static final float COMPONENT_UNPACK_FACTOR = 1 / COMPONENT_PACK_FACTOR;

    /**
     * Unpacks a 64-bit {@code long} into a {@link LinearColor}.
     *
     * @param packed The packed {@code long}
     * @return The unpacked color
     * @see LinearColor#pack()
     */
    @Nonnull
    public static LinearColor unpack(long packed) {
        return new LinearColor(
                ((packed >> R_COMPONENT_SHIFT) & 0xFFFF) * COMPONENT_UNPACK_FACTOR,
                ((packed >> G_COMPONENT_SHIFT) & 0xFFFF) * COMPONENT_UNPACK_FACTOR,
                ((packed >> B_COMPONENT_SHIFT) & 0xFFFF) * COMPONENT_UNPACK_FACTOR,
                (packed & 0xFFFF) * COMPONENT_UNPACK_FACTOR
        );
    }

    /**
     * Packs this color into a 64 bit {@code long}.
     *
     * @return The packed color
     * @see LinearColor#unpack(long)
     */
    @Override
    public long pack() {
        return (((long) (w * COMPONENT_PACK_FACTOR)) << R_COMPONENT_SHIFT)
                | (((long) (x * COMPONENT_PACK_FACTOR)) << G_COMPONENT_SHIFT)
                | (((long) (y * COMPONENT_PACK_FACTOR)) << B_COMPONENT_SHIFT)
                | ((long) (z * COMPONENT_PACK_FACTOR));
    }

    //
    // Getters
    //

    /**
     * {@inheritDoc}
     *
     * @return The red component of this color ({@code 0-255})
     */
    @Override
    public int red() {
        return (int) w;
    }

    /**
     * {@inheritDoc}
     *
     * @return The green component of this color ({@code 0-255})
     */
    @Override
    public int green() {
        return (int) x;
    }

    /**
     * {@inheritDoc}
     *
     * @return The blue component of this color ({@code 0-255})
     */
    @Override
    public int blue() {
        return (int) y;
    }

    /**
     * {@inheritDoc}
     *
     * @return The alpha component of this color ({@code 0-255})
     */
    @Override
    public int alpha() {
        return (int) z;
    }

    /**
     * {@inheritDoc}
     *
     * @return The 32 bit RGBA representation of this color
     */
    @Override
    public int rgba() {
        return Color8.packRGBA((int) w, (int) x, (int) y, (int) z);
    }

    /**
     * {@inheritDoc}
     *
     * @return The hex code of this color
     */
    @Override
    public int hex() {
        return Color8.packRGB((int) w, (int) x, (int) y);
    }

    //
    // Equality
    //

    /**
     * {@inheritDoc}
     *
     * @param obj The object to compare to
     * @return {@code true} if the object is also a color, and the values are considered equal
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null) return false;

        if (obj instanceof LinearColor lc) {
            return w == lc.w &&
                    x == lc.x &&
                    y == lc.y &&
                    z == lc.z;
        }

        if (obj instanceof Color8 c) return rgba() == c.rgba();

        return true;
    }

    //
    // Serialization
    //

    /**
     * Deserializes a string into a {@link LinearColor}.
     *
     * @param input The input to deserialize
     * @return The parsed vector
     * @throws NumberFormatException When the format is invalid
     */
    @Nonnull
    public static LinearColor parseColor(@Nonnull String input) throws IllegalArgumentException {
        if (!input.startsWith("LinearColor{")) {
            throw new NumberFormatException("The provided string does not represent a LinearColor.");
        }

        final String cleanInput = input.replaceAll("LinearColor\\{|}", "");
        final String[] valueStrings = cleanInput.split(", ");
        final float[] values = new float[4];

        for (final String valueString : valueStrings) {
            final String[] split = valueString.split("=");
            values[switch (split[0]) {
                case "r" -> 0;
                case "g" -> 1;
                case "b" -> 2;
                case "a" -> 3;
                default -> throw new NumberFormatException("The provided string does not represent a LinearColor.");
            }] = Float.parseFloat(split[1]);
        }

        return new LinearColor(values);
    }

    /**
     * {@inheritDoc}
     *
     * @return The string representation of this color
     */
    @Override
    @Nonnull
    public String toString() {
        return "LinearColor{" +
                "r=" + w +
                ", g=" + x +
                ", b=" + y +
                ", a=" + z +
                '}';
    }
}
