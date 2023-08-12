package civitas.celestis;

import civitas.celestis.graphics.color.RichColor;
import civitas.celestis.math.vector.Vector4;

public class Tests {
    public static void main(String[] args) {
        final RichColor c1 = RichColor.random();
        final RichColor c2 = RichColor.random();

        System.out.println(c1 + " / " + c2);

        System.out.println(c1.distance(c2));
    }
}
