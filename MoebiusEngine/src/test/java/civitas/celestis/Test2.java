package civitas.celestis;

import civitas.celestis.graphics.model.Model32;
import civitas.celestis.graphics.model.Model64;
import de.javagl.obj.Obj;
import de.javagl.obj.ObjReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Test2 {
    public static void main(String[] args) {
        final Obj obj;

        try {
            obj = ObjReader.read(new FileReader(BC304));
        } catch (final IOException e) {
            e.printStackTrace();
            return;
        }

        final Model32 model = new Model32(obj);

        System.out.println(model);
    }

    ///////////////////////////////////////////////////

    static final File BC304 = new File("MoebiusEngine/src/main/resources/models/bc304/BC304Render.obj");
    static final File MCX = new File("MoebiusEngine/src/main/resources/models/mcx/MCX.obj");


    ///////////////////////////////////////////////////

}