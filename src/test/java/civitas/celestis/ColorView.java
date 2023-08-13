package civitas.celestis;

import civitas.celestis.graphics.color.RichColor;
import civitas.celestis.math.Interpolation;

import javax.swing.*;

public class ColorView {
    public static void main(String[] args) {
        final JFrame frame = new JFrame();
        final SingleColorPanel panel = new SingleColorPanel();

        // This color will be displayed
        panel.color = Interpolation.lerp(RichColor.GOLD, RichColor.ORANGE, 0.5);
        System.out.println(panel.color);

        frame.add(panel);

        frame.setSize(1000, 1000);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
