package civitas.celestis;

import civitas.celestis.math.real.MutableRealVector;
import civitas.celestis.math.real.RealNumber;

public class Printer {
    public static void main(String[] args) {
        final MutableRealVector v = new MutableRealVector(RealNumber.ONE, RealNumber.TWO, RealNumber.THREE);

        System.out.println(MutableRealVector.parseVector(v.toString()));
        System.out.println(new RealNumber(2.34, 12.320).toReadableString());
    }
}
