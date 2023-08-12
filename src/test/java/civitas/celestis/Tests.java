package civitas.celestis;

import civitas.celestis.graphics.Interpolation;
import civitas.celestis.graphics.color.RichColor;
import civitas.celestis.math.Numbers;
import civitas.celestis.math.vector.Vector4;

import javax.swing.*;

public class Tests {
    public static void main(String[] args) {
        final RichColor c1 = RichColor.CYAN;
        final RichColor c2 = RichColor.PINK;

        final JFrame frame = new JFrame("test");
        final SingleColorPanel panel = new SingleColorPanel();

        frame.add(panel);
        frame.setSize(500, 500);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        System.out.println(c1);
        System.out.println(c2);

        final RichColor color = Interpolation.lerp(c1, c2, 0.2);
        System.out.println(color);

        panel.color = color;
        frame.repaint();
        System.out.println(panel.color.color());
    }
}
