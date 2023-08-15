package civitas.celestis;

import civitas.celestis.math.complex.Complex;
import civitas.celestis.math.vector.Vector2;

public class ColorTest {
    public static void main(String[] args) {
        final Complex c = new Complex(242, 2390);

        System.out.println(c.pack());
        System.out.println(new Complex(Vector2.unpack(c.pack())));

    }
}
