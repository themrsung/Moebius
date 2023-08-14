package civitas.celestis;

import civitas.celestis.graphics.color.RGBColor;
import civitas.celestis.math.Numbers;
import civitas.celestis.math.matrix.IntMatrix;

public class Experimental {
    public static void main(String[] args) {
        final IntMatrix img = new IntMatrix(1920, 1080);

        System.out.println(Numbers.isInRange(0, 0d, 255d));

        for (int r = 0; r < img.rows(); r++) {
            for (int c = 0; c < img.columns(); c++) {
                img.set(r, c, img.get(r, c) + (int) Numbers.random(0, new RGBColor(255, 255, 255).hexInt()));
            }
        }

        System.out.println(img);
    }
}
