package civitas.celestis;

import civitas.celestis.math.decimal.MutableDecimalVector;

import java.math.BigDecimal;

public class DecimalTest {
    public static void main(String[] args) {
        final MutableDecimalVector v1 = new MutableDecimalVector(BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.TWO);
        System.out.println(MutableDecimalVector.parseVector(v1.toString()));
    }
}
