package civitas.celestis;

import civitas.celestis.math.vector.Double3;
import civitas.celestis.util.array.SafeArray;

import java.io.File;

public class Test2 {
    public static void main(String[] args) {

        final SafeArray<Double> array = SafeArray.boxDoubles(23, 4220, 32, 3, 42, 2, 3, 24, 2, 3, 3);

        System.out.println(Double3.POSITIVE_X.safeArray());

    }

    ///////////////////////////////////////////////////

    static final File BC304 = new File("MoebiusEngine/src/main/resources/models/bc304/BC304Render.obj");
    static final File MCX = new File("MoebiusEngine/src/main/resources/models/mcx/MCX.obj");


    ///////////////////////////////////////////////////

}