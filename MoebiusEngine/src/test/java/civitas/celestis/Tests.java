package civitas.celestis;

import civitas.celestis.math.matrix.DoubleMatrix;
import civitas.celestis.util.group.ArrayGrid;
import civitas.celestis.util.group.Grid;

public class Tests {
    public static void main(String[] args) {
        final Grid<Double> grid = new ArrayGrid<>(3, 3);
        grid.fill(23d);
        final DoubleMatrix matrix = new DoubleMatrix(grid);

        System.out.println(matrix.merge(grid, (x, y) -> Math.pow(x, 2)));
    }
}
