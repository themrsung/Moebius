package civitas.celestis;

import civitas.celestis.graphics.color.RichColor;
import civitas.celestis.graphics.model.ColoredModel;
import civitas.celestis.math.Numbers;
import civitas.celestis.util.string.RichString;
import civitas.celestis.util.string.RichText;
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

        final RichColor color = RichColor.random();
        System.out.println(color.magnitude());
        System.out.println(color.magnitude2());
    }
}
