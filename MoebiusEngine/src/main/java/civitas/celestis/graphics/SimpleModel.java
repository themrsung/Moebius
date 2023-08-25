package civitas.celestis.graphics;

import civitas.celestis.math.vector.Vector2;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.util.array.FastArray;
import civitas.celestis.util.array.SafeArray;
import civitas.celestis.util.tuple.Tuple;
import de.javagl.obj.FloatTuple;
import de.javagl.obj.Obj;
import de.javagl.obj.ObjFace;
import jakarta.annotation.Nonnull;

public class SimpleModel implements Model {

    public SimpleModel(@Nonnull Obj obj, double scale) {
        final SafeArray<Vector3> vertices = new FastArray<>(obj.getNumVertices());
        final SafeArray<Vector3> normals = new FastArray<>(obj.getNumNormals());

        for (int i = 0; i < obj.getNumVertices(); i++) {
            final FloatTuple v = obj.getVertex(i);
            vertices.set(i, new Vector3(v.getZ(), v.getY(), v.getX()));
        }

        for (int i = 0; i < obj.getNumNormals(); i++) {
            final FloatTuple n = obj.getNormal(i);
            normals.set(i, new Vector3(n.getZ(), n.getY(), n.getX()));
        }

        this.vertices = vertices.mapToTuple(v -> v.multiply(scale));
        this.normals = vertices.mapToTuple(Vector3::normalize);

        final SafeArray<Vector2> uvs = new FastArray<>(obj.getNumTexCoords());

        for (int i = 0; i < obj.getNumTexCoords(); i++) {
            final FloatTuple uv = obj.getTexCoord(i);
            uvs.set(i, new Vector2(uv.get(0), uv.get(1)));
        }

        this.uvCoordinates = uvs.tuple();

        final SafeArray<Face> faces = new FastArray<>(obj.getNumFaces());

        for (int i = 0; i < obj.getNumFaces(); i++) {
            final ObjFace face = obj.getFace(i);

            final Vector3 v1 = vertices.get(face.getVertexIndex(0));
            final Vector3 v2 = vertices.get(face.getVertexIndex(1));
            final Vector3 v3 = vertices.get(face.getVertexIndex(2));

            faces.set(i, new Face(v1, v2, v3));
        }

        this.faces = faces.tuple();
    }

    protected final Tuple<Vector3> vertices, normals;
    protected final Tuple<Vector2> uvCoordinates;
    protected final Tuple<Face> faces;

    @Nonnull
    @Override
    public Tuple<Vector3> getVertices() {
        return vertices;
    }

    @Nonnull
    @Override
    public Vector3 getVertex(int i) throws IndexOutOfBoundsException {
        return vertices.get(i);
    }

    @Override
    public int getVertexCount() {
        return vertices.size();
    }

    @Nonnull
    @Override
    public Tuple<Vector3> getNormals() {
        return normals;
    }

    @Nonnull
    @Override
    public Vector3 getNormal(int i) throws IndexOutOfBoundsException {
        return normals.get(i);
    }

    @Nonnull
    @Override
    public Tuple<Vector2> getUVCoordinates() {
        return uvCoordinates;
    }

    @Nonnull
    @Override
    public Vector2 getUVCoordinate(int i) throws IndexOutOfBoundsException {
        return uvCoordinates.get(i);
    }

    @Override
    public int getUVCoordinateCount() {
        return uvCoordinates.size();
    }

    @Nonnull
    @Override
    public Tuple<Face> getFaces() {
        return faces;
    }

    @Nonnull
    @Override
    public Face getFace(int i) throws IndexOutOfBoundsException {
        return faces.get(i);
    }

    @Override
    public int getFaceCount() {
        return faces.size();
    }

    @Nonnull
    @Override
    public String toString() {
        return "SimpleModel{" +
                "vertices=" + vertices +
                ", normals=" + normals +
                ", uvCoordinates=" + uvCoordinates +
                ", faces=" + faces +
                '}';
    }
}
