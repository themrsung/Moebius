package civitas.celestis;

import civitas.celestis.graphics.color.RichColor;
import civitas.celestis.graphics.model.ColoredModel;
import de.javagl.obj.Obj;
import de.javagl.obj.ObjReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Tests {
    public static void main(String[] args) {
        final File file = new File("src/main/resources/models/mcx/MCX.obj");
        final FileReader reader;
        final Obj obj;
        try {
            reader = new FileReader(file);
            obj = ObjReader.read(reader);
        } catch (IOException e) {
            return;
        }

        final ColoredModel model = new ColoredModel(obj, RichColor.GRAY, 1);
        System.out.println(ColoredModel.parseModel(model.toString()));
    }
}
