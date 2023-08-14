package civitas.celestis;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Experimental {
    public static void main(String[] args) {
        doubleTest();
        decimalTest();
    }

    public static void doubleTest() {
        double a = 23.30232;
        double b = 55.61240;

        System.out.println(Math.pow(2, a) * b);
    }

    public static void decimalTest() {
        BigDecimal a = BigDecimal.valueOf(23.30232);
        BigDecimal b = BigDecimal.valueOf(55.61240);


    }

    public static BigDecimal exp(BigDecimal base, BigDecimal exp) {
        MathContext mc = new MathContext(10000, RoundingMode.HALF_UP);

return null;
    }
}
