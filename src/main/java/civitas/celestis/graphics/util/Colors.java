package civitas.celestis.graphics.util;

import jakarta.annotation.Nonnull;

import java.awt.*;

/**
 * Contains utility methods related to AWT colors.
 */
public final class Colors {
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

}
