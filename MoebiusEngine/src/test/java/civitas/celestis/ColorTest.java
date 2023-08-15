package civitas.celestis;

import civitas.celestis.graphics.color.Color8;
import civitas.celestis.graphics.color.LinearColor;

public class ColorTest {
    public static void main(String[] args) {
        final LinearColor color = LinearColor.CYAN;
        System.out.println(color);
        System.out.println(new LinearColor(Color8.fromAWT(Color8.toAWT(color))));
    }
}
