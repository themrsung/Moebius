package civitas.celestis;

import civitas.celestis.graphics.color.Color8;
import civitas.celestis.graphics.color.LinearColor;
import civitas.celestis.graphics.color.SimpleColor;

public class Tests {
    public static void main(String[] args) {
        final Color8 color = new LinearColor(2, 3, 3, 4);
        System.out.println(LinearColor.parseColor(color.toString()));

        final SimpleColor c2 = SimpleColor.RED;

        System.out.println(SimpleColor.parseColor(c2.toString()).toReadableString());
        System.out.println(Double.isFinite(Double.NaN));
    }
}
