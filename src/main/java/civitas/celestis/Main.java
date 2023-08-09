package civitas.celestis;

import civitas.celestis.math.quaternion.Quaternion;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        final Quaternion q = Quaternion.builder().yawDegrees(25).build();

        System.out.println(q);
    }
}