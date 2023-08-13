package civitas.celestis;

import civitas.celestis.graphics.color.DeepColor;
import civitas.celestis.graphics.color.RichColor;

public class Printer {
    public static void main(String[] args) {
        final DeepColor c10 = DeepColor.random();

        for (int i = 0; i < 1000; i++) {
            System.out.println(DeepColor.random().to8Bit());
        }

    }
}
