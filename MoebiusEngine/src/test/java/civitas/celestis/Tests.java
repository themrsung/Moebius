package civitas.celestis;

import civitas.celestis.graphics.color.Color8;
import civitas.celestis.graphics.color.LinearColor;
import civitas.celestis.graphics.image.ColorMatrix;
import civitas.celestis.math.Interpolation;
import civitas.celestis.math.Numbers;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Tests {
    public static void main(String[] args) {
        final JFrame frame = new JFrame();
        final JPanel panel = new JPanel() {
            @Override
            public void paint(Graphics g) {
                final ColorMatrix image = new ColorMatrix(getHeight(), getWidth());
                final LinearColor base = Interpolation.lerp((Color8) LinearColor.WHITE, LinearColor.RED, (float) Math.random());
                for (int r = 0; r < image.rows(); r++) {
                    for (int c = 0; c < image.columns(); c++) {
                        final LinearColor random = new LinearColor(
                                Numbers.random(0f, 255f),
                                Numbers.random(0f, 255f),
                                Numbers.random(0f, 255f)
                        );

                        image.setColor(r, c, Interpolation.lerp((Color8) base, random, 0.2f));
                    }
                }

                final BufferedImage rendered = image.render();
                g.drawImage(rendered, 0, 0, null);
            }
        };

        frame.add(panel);
        frame.setSize(1920, 1080);
        frame.setVisible(true);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
