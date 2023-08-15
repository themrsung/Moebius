package civitas.celestis;

import civitas.celestis.graphics.color.RichColor;
import civitas.celestis.math.Numbers;
import civitas.celestis.math.matrix.DoubleMatrix;
import civitas.celestis.math.matrix.LongMatrix;
import civitas.celestis.math.matrix.Matrix;

public class ColorTest {
    public static void main(String[] args) {
        final LongMatrix img = new LongMatrix(10, 10);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                img.set(i, j, RichColor.random().pack());
            }
        }

        System.out.println(img);
    }
}
