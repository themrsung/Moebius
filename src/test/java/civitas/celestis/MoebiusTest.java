package civitas.celestis;

import de.javagl.obj.Obj;
import de.javagl.obj.ObjReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MoebiusTest {
    public static void main(String[] args) {
        final File file = new File("src/main/resources/models/bc304/BC304Render.obj");
        final FileReader reader;
        final Obj obj;

        try {
            reader = new FileReader(file);
            obj = ObjReader.read(reader);
        } catch (IOException e) {
            return;
        }
    }
}
