package civitas.celestis;


import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Experimental {
    public static void main(String[] args) {
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
