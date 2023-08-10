package civitas.celestis.graphics.util;

import jakarta.annotation.Nonnull;

import java.awt.*;

/**
 * Contains utility methods related to AWT colors.
 */
public final class Colors {
    //
    // Constants
    //

    public static final Color BLACK = Color.BLACK;
    public static final Color WHITE = Color.WHITE;
    public static final Color TRANSPARENT = new Color(0, 0, 0, 0);

    public static final Color RED = Color.RED;
    public static final Color BLUE = Color.BLUE;
    public static final Color GREEN = Color.GREEN;

    public static final Color LIGHT_GRAY = Color.LIGHT_GRAY;
    public static final Color GRAY = Color.GRAY;
    public static final Color DARK_GRAY = Color.DARK_GRAY;

    public static final Color PINK = Color.PINK;
    public static final Color CYAN = Color.CYAN;
    public static final Color MAGENTA = Color.MAGENTA;
    public static final Color ORANGE = Color.ORANGE;
    public static final Color YELLOW = Color.YELLOW;
    public static final Color GOLD = new Color(246, 205, 51);

    //
    // Mixing
    //

    /**
     * Calculates the weighted average of two colors.
     *
     * @param c1 The first color
     * @param c2 The second color
     * @param w1 The first color's weight
     * @param w2 The second color's weight
     * @return The weighted average of the two colors
     */
    @Nonnull
    public static Color average(@Nonnull Color c1, @Nonnull Color c2, double w1, double w2) {
        final double r1 = c1.getRed();
        final double g1 = c1.getGreen();
        final double b1 = c1.getBlue();
        final double a1 = c1.getAlpha();

        final double r2 = c2.getRed();
        final double g2 = c2.getGreen();
        final double b2 = c2.getBlue();
        final double a2 = c2.getAlpha();

        final double rs = r1 * w1 + r2 * w2;
        final double gs = g1 * w1 + g2 * w2;
        final double bs = b1 * w1 + b2 * w2;
        final double as = a1 * w1 + a2 * w2;

        final double ws = w1 + w2;

        final double rd = rs / ws;
        final double gd = gs / ws;
        final double bd = bs / ws;
        final double ad = as / ws;

        final int r = (int) Math.round(rd);
        final int g = (int) Math.round(gd);
        final int b = (int) Math.round(bd);
        final int a = (int) Math.round(ad);

        return new Color(r, g, b, a);
    }

    /**
     * Calculates the simple average of two colors.
     *
     * @param c1 The first color
     * @param c2 The second color
     * @return The simple average of the two colors
     */
    @Nonnull
    public static Color average(@Nonnull Color c1, @Nonnull Color c2) {
        final double r1 = c1.getRed();
        final double g1 = c1.getGreen();
        final double b1 = c1.getBlue();
        final double a1 = c1.getAlpha();

        final double r2 = c2.getRed();
        final double g2 = c2.getGreen();
        final double b2 = c2.getBlue();
        final double a2 = c2.getAlpha();

        final int r = (int) ((r1 + r2) / 2);
        final int g = (int) ((g1 + g2) / 2);
        final int b = (int) ((b1 + b2) / 2);
        final int a = (int) ((a1 + a2) / 2);

        return new Color(r, g, b, a);
    }
}
