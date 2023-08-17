package civitas.celestis;

import civitas.celestis.math.vector.Decimal3;
import civitas.celestis.math.vector.Double3;
import civitas.celestis.util.tuple.Tuple;

import java.math.BigDecimal;

public class Test2 {
    public static void main(String[] args) {
        final Decimal3 d3 = new Decimal3((Tuple<BigDecimal, ?>) Decimal3.ZERO);

        System.out.println(d3);
    }
}
