package civitas.celestis;

import civitas.celestis.math.vector.Double2;
import civitas.celestis.math.vector.Float2;
import civitas.celestis.math.vector.Long2;
import civitas.celestis.math.vector.Vector;

import java.util.Objects;

public class Tests {
    public static void main(String[] args) {
        final Vector<?, ?> v1 = new Long2(1, 2);
        final Vector<?, ?> v2 = new Float2(1, 2);

        System.out.println(v1.equals(v2));
    }
}
