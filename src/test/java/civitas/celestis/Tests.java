package civitas.celestis;

import civitas.celestis.graphics.color.RichColor;
import civitas.celestis.graphics.model.ColoredModel;
import civitas.celestis.math.Numbers;
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

        final RichColor c1 = RichColor.PINK;
        final RichColor c2 = RichColor.ORANGE;
        final RichColor c3 = RichColor.GOLD;

        System.out.println(c1.reflectiveness());
        System.out.println(c2.reflectiveness());
        System.out.println(c3.reflectiveness());

        System.out.println(Numbers.scale(0.95, 0, 1, 0, 255));
    }
}
