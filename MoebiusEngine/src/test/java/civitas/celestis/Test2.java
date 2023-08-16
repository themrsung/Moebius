package civitas.celestis;

import civitas.celestis.math.Numbers;
import civitas.celestis.math.function.Graph;
import civitas.celestis.util.tuple.ArrayTuple;

public class Test2 {
    public static void main(String[] args) {
        final Graph<Long, Long> g = new Graph<>(Numbers::factorial);
        final ArrayTuple<Double> inputs = new ArrayTuple<>(
                1d,
                2d,
                3d,
                4d,
                5d,
                6d,
                7d,
                8d,
                9d,
                10d,
                11d,
                12d,
                13d,
                14d,
                15d
        );

        g.print(inputs.map(Double::longValue), System.out::println);
    }
}
