package civitas.celestis;

import civitas.celestis.graphics.color.RichColor;

import javax.swing.*;

public class ColorView {
    public static void main(String[] args) {
        final JFrame frame = new JFrame();
        final SingleColorPanel panel = new SingleColorPanel();

        // This color will be displayed
        panel.color = RichColor.GOLD;

        frame.add(panel);

        frame.setSize(1000, 1000);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
