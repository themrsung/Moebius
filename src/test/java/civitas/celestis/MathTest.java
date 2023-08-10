package civitas.celestis;

import civitas.celestis.math.util.VectorOperations;
import civitas.celestis.math.vector.Vector3;

import java.util.concurrent.atomic.AtomicReference;

public class MathTest {
    public static void main(String[] args) {
        final AtomicReference<Vector3> result = new AtomicReference<>(Vector3.ZERO);
        final Vector3 v = new Vector3(1, 1, 1);

        VectorOperations.add(result, v, 1);

        System.out.println(result.get());
    }
}
