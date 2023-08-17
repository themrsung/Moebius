package civitas.celestis;

import civitas.celestis.math.DecimalMath;

import java.math.BigDecimal;

public class Test2 {
    public static void main(String[] args) {
        BigDecimal pi = DecimalMath.PI;
        BigDecimal r2 = DecimalMath.SQRT_2;

        System.out.println(pi);
        System.out.println(r2);

        System.out.println(pi.add(r2));
    }
}
