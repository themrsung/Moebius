package civitas.celestis;

import civitas.celestis.math.Numbers;
import civitas.celestis.math.vector.Float2;
import civitas.celestis.math.vector.Vector2;

public class ColorTest {
    public static void main(String[] args) {
        final Float2 f = new Float2(new Vector2(Numbers.random(23, 2094), Numbers.random(24, 24902)));

        System.out.println(f);
        System.out.println(Float2.unpack(f.pack()));
    }
}
