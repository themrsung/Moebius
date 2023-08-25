package civitas.celestis.graphics;

import civitas.celestis.math.vector.Vector2;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.util.tuple.Tuple;
import de.javagl.obj.ObjReader;
import jakarta.annotation.Nonnull;

import java.io.FileReader;
import java.io.IOException;

public interface Model {
    @Nonnull
    static Model loadModel(@Nonnull String path, double scale) throws IOException {
        return new SimpleModel(ObjReader.read(new FileReader(path)), scale);
    }

    @Nonnull
    Tuple<? extends Vector3> getVertices();
    @Nonnull
    Vector3 getVertex(int i) throws IndexOutOfBoundsException;
    int getVertexCount();


    @Nonnull
    Tuple<? extends Vector3> getNormals();
    @Nonnull
    Vector3 getNormal(int i) throws IndexOutOfBoundsException;


    @Nonnull
    Tuple<? extends Vector2> getUVCoordinates();
    @Nonnull
    Vector2 getUVCoordinate(int i) throws IndexOutOfBoundsException;
    int getUVCoordinateCount();


    @Nonnull
    Tuple<? extends Face> getFaces();
    @Nonnull
    Face getFace(int i) throws IndexOutOfBoundsException;
    int getFaceCount();
}
