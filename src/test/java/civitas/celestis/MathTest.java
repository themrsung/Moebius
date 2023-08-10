package civitas.celestis;

import civitas.celestis.math.matrix.Matrix;
import civitas.celestis.math.util.Numbers;

public class MathTest {
    public static void main(String[] args) {
        final Matrix m1 = new Matrix(3, 3);
        m1.fill(10);

        final Matrix m2 = m1.resize(5, 1);
        for (int i = 0; i < 100; i++) {
            System.out.println(Numbers.factorial(-1));
        }
    }
}
