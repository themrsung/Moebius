package civitas.celestis;

import civitas.celestis.util.collection.CircularQueue;

public class Test2 {
    public static void main(String[] args) {
        final CircularQueue<Double> loop = CircularQueue.of(
                1d,
                2d,
                3d
        );

        int i = 0;

        loop.forEach(System.out::println);
    }
}
