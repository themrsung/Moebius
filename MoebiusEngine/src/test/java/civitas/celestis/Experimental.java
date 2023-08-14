package civitas.celestis;

import civitas.celestis.graphics.color.RGBColor;
import civitas.celestis.math.Numbers;
import civitas.celestis.math.matrix.IntMatrix;
import civitas.celestis.math.real.RealNumber;

public class Experimental {
    public static void main(String[] args) {
        RealNumber n = RealNumber.MIN_VALUE;

        for (int i = 0; i < 100; i++) {
            System.out.println(i + ". " + n.toReadableString());
            n = n.add(RealNumber.MIN_VALUE);
        }
    }
}
