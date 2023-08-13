package civitas.celestis;

import civitas.celestis.graphics.color.RichColor;

import javax.swing.*;
import java.awt.*;

public class SingleColorPanel extends JPanel {

    public RichColor color = RichColor.WHITE;
    @Override
    public void paint(Graphics g) {
        g.setColor(color.awt());
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
