package civitas.celestis;

import civitas.celestis.math.Numbers;
import civitas.celestis.math.Numeric;
import civitas.celestis.math.fraction.Fraction;
import civitas.celestis.math.real.RealNumber;

public class Printer {
    public static void main(String[] args) {
        final RealNumber n1 = new RealNumber(3.14);
        final Fraction f = new Fraction(3.14);

        System.out.println(n1.equals(f));
    }
}
