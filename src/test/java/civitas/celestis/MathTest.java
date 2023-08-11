package civitas.celestis;

import civitas.celestis.graphics.util.BoundingBox;
import civitas.celestis.math.matrix.Matrix;
import civitas.celestis.math.util.Numbers;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.math.vector.Vector5;

import java.util.concurrent.atomic.AtomicReference;

public class MathTest {
    public static void main(String[] args) {
        final BoundingBox box = new BoundingBox(Vector3.POSITIVE_MAX, Vector3.NEGATIVE_MAX);

        final Vector3 v = Vector3.random();
        System.out.println(box.contains(v));

        for (int i = 0; i < 10000; i++) {
            System.out.println(v.cross(Vector3.random()));
        }
    }
}
