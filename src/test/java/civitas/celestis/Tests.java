package civitas.celestis;

import civitas.celestis.math.vector.Vector3;

public class Tests {
    public static void main(String[] args) {
        final Vector3 v1 = new Vector3(1, 2, 30);
        System.out.println(Vector3.parseVector(v1.toString()));
        System.out.println(1 / 0L);
    }
}
