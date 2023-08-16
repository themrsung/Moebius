package civitas.celestis;

import civitas.celestis.graphics.color.Color8;
import civitas.celestis.graphics.color.LinearColor;
import civitas.celestis.graphics.color.SimpleColor;
import civitas.celestis.math.Numbers;
import civitas.celestis.math.complex.Complex;

public class Tests {
    public static void main(String[] args) {
        final Complex c = new Complex(32, 1);

        System.out.println(c.multiply(c.reciprocal()));
    }
}
