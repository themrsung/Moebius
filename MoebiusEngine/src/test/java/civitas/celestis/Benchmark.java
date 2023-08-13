package civitas.celestis;

import civitas.celestis.graphics.color.RichColor;
import civitas.celestis.math.Interpolation;

public class Benchmark {
    public static void main(String[] args) {
        final RichColor c1 = RichColor.TRANSPARENT_BLACK;
        final RichColor c2 = RichColor.WHITE;

        final long t1 = System.currentTimeMillis();
        // Start of benchmark

        for (int i = 0; i < 1000000; i++) {
            Interpolation.lerp(c1, c2, 0.5);
        }

        // End of benchmark
        final long t2 = System.currentTimeMillis();

        System.out.println(t2 - t1);
    }
}
