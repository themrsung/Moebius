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

        final RichString rs1 = new RichString("Hello world!\n");
        final RichString rs2 = new RichString("Hello world2!");

        final RichText text = new RichText(rs1, rs2);

        System.out.println(RichText.parseText(text.toString()));
    }
}
