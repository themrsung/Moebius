package civitas.celestis;

import civitas.celestis.math.matrix.DoubleMatrix;
import civitas.celestis.math.vector.Double3;
import civitas.celestis.util.group.ArrayGrid;
import civitas.celestis.util.group.Grid;
import civitas.celestis.util.group.Group;
import civitas.celestis.util.tuple.Pair;

public class Tests {
    public static void main(String[] args) {
        final Grid<Double> grid = new ArrayGrid<>(3, 3);
        final Double3 vector = new Double3(3, 3, 3);
        final Pair<Double> pair = new Pair<>(3d, 0d);

        final Group<Double> copied1 = Group.copyOf(grid);
        final Group<Double> copied2 = Group.copyOf(vector);
        final Group<Double> copied3 = Group.copyOf(pair);

        System.out.println(copied1);
        System.out.println(copied2);
        System.out.println(copied3);
    }
}
