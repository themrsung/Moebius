package civitas.celestis;

import civitas.celestis.math.vector.Double3;
import civitas.celestis.util.group.ArrayGrid;
import civitas.celestis.util.group.Grid;
import civitas.celestis.util.tuple.QuaternaryQuad;
import civitas.celestis.util.tuple.Triple;

public class Test2 {
    public static void main(String[] args) {
        final QuaternaryQuad<Grid<Double>, Double3, Triple<Double>, Double> quad = new QuaternaryQuad<>(
                new ArrayGrid<>(2, 2),
                Double3.ZERO,
                new Triple<>(0d, 0d, 0d),
                3d
        );


    }
}
