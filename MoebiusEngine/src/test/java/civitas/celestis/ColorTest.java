package civitas.celestis;

import civitas.celestis.graphics.color.RichColor;

public class ColorTest {
    public static void main(String[] args) {
        final RichColor c = RichColor.random();

        System.out.println(c);
        System.out.println(c.pack());
        System.out.println(new RichColor(c.pack()));
    }
}
