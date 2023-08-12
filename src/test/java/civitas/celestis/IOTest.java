package civitas.celestis;

import civitas.celestis.graphics.color.RichColor;
import civitas.celestis.graphics.model.ColoredModel;
import civitas.celestis.graphics.model.Model;
import civitas.celestis.math.Interpolation;
import civitas.celestis.math.Numbers;
import de.javagl.obj.Obj;
import de.javagl.obj.ObjReader;

import java.io.*;

public class IOTest {
    public static void main(String[] args) {
        final File file = new File("src/main/resources/models/bc304/BC304Render.obj");
        final FileReader reader;
        final Obj obj;
        try {
            reader = new FileReader(file);
            obj = ObjReader.read(reader);

            reader.close();
        } catch (IOException e) {
            return;
        }

        final ColoredModel model = new ColoredModel(obj, RichColor.GRAY, 1);

        model.getFaces().forEach(f -> f.setColor(
                Interpolation.lerp(f.getColor(), RichColor.DARK_GRAY, Numbers.random(0, 0.2))
        ));

        final String fileName = "F304.model";

        try {
            final BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            final String[] lines = model.toString().split("\n");

            for (final String line : lines) {
                writer.write(line + "\n");
            }

            writer.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }

        final Model model2;

        try {
            final BufferedReader modelReader = new BufferedReader(new FileReader(fileName));
            final StringBuilder builder = new StringBuilder();
            modelReader.lines().forEach(l -> {
                builder.append(l);
                builder.append("\n");
            });

            final String string = builder.toString();
            model2 = ColoredModel.parseModel(string);

            modelReader.close();

        } catch (final IOException e) {
            e.printStackTrace();
            return;
        }

        System.out.println(model2);
    }
}
