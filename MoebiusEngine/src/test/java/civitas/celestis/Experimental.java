package civitas.celestis;

import civitas.celestis.math.fraction.Fraction;
import civitas.celestis.math.fraction.RealFraction;
import civitas.celestis.math.real.RealNumber;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.concurrent.atomic.AtomicBoolean;

public class Experimental {
    public static void main(String[] args) {

        RealNumber n = RealNumber.MIN_VALUE;

        BigInteger i = BigInteger.ZERO;
        while (n.equals(0)) {
            n = n.twoFold();
            i = i.add(BigInteger.ONE);

            System.out.println(n + " (" + n.doubleValue() + ")");
        }

        System.out.println("There are at least " + i + " values of RealNumber which are equal to 0.0d");
    }

    public static void doubleTest() {
        double a = 23.30232;
        double b = 2.5;

//        System.out.println(Math.pow(2, a) * b);
        System.out.println(Math.pow(a, b));

    }

    public static void decimalTest() {
//        BigDecimal a = BigDecimal.valueOf(23.30232);
//        BigDecimal b = BigDecimal.valueOf(55.61240);
        RealNumber a = new RealNumber(23.30232);
        RealNumber b = new RealNumber(2.5);
    }

    public static BigDecimal exp(BigDecimal base, BigDecimal exp) {
        MathContext mc = new MathContext(10000, RoundingMode.HALF_UP);

return null;
    }
}
