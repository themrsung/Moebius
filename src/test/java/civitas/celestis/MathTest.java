package civitas.celestis;

import civitas.celestis.math.matrix.Matrix;
import civitas.celestis.math.util.MatrixOperations;
import civitas.celestis.math.util.VectorOperations;
import civitas.celestis.math.vector.Vector3;

import java.util.concurrent.atomic.AtomicReference;

public class MathTest {
    public static void main(String[] args) {
        final AtomicReference<Vector3> reference = new AtomicReference<>(null);
        final Vector3 v1 = new Vector3(1, 2, 3);
        final Matrix m1 = new Matrix(3, 3);
        m1.fill(Math.PI);

        MatrixOperations.multiply(reference, m1, v1);

        System.out.println(reference.get());
    }
}
