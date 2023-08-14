package civitas.celestis;


import Jama.Matrix;
import civitas.celestis.graphics.color.RichColor;
import civitas.celestis.math.Interpolation;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Experimental {
    public static void main(String[] args) {
        final RichColor color = Interpolation.lerp(RichColor.GOLD, RichColor.random(), 0.1);
        System.out.println(color);
        System.out.println(color.pack());
        System.out.println(new RichColor(color.pack()));

    }

    public static void doubleTest() {
        double a = 23.30232;
        double b = 2.5;

//        System.out.println(Math.pow(2, a) * b);
        System.out.println(Math.pow(a, b));

    }

    public static void decimalTest() {
    }

    public static BigDecimal exp(BigDecimal base, BigDecimal exp) {
        MathContext mc = new MathContext(10000, RoundingMode.HALF_UP);

        return null;
    }
}
