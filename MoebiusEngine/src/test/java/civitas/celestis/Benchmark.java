package civitas.celestis;

import civitas.celestis.graphics.color.RichColor;
import civitas.celestis.math.Interpolation;
import civitas.celestis.math.Numbers;
import civitas.celestis.math.Sign;
import civitas.celestis.math.real.RealNumber;

public class Benchmark {
    public static void main(String[] args) {
        final RichColor c1 = RichColor.TRANSPARENT_BLACK;
        final RichColor c2 = RichColor.WHITE;
        final long t1 = System.currentTimeMillis();
        // Start of benchmark

        for (int i = 0; i < 1000000; i++) {
            final Sign s = Numbers.sign(Numbers.random(0, 410));
        }

        // End of benchmark
        final long t2 = System.currentTimeMillis();

        System.out.println(t2 - t1);
    }
}
