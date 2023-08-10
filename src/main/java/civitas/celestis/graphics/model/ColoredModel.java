package civitas.celestis.graphics.model;

import civitas.celestis.graphics.face.ColoredFace;
import civitas.celestis.graphics.util.Vertex;
import de.javagl.obj.Obj;
import de.javagl.obj.ObjFace;
import jakarta.annotation.Nonnull;

import java.awt.*;

/**
 * A model which uses colored faces.
 */
public class ColoredModel implements Model {
    /**
     * Creates a new colored model.
     *
     * @param faces Array of faces to use
     */
    public ColoredModel(@Nonnull ColoredFace[] faces) {
        this.faces = new ColoredFace[faces.length];

        for (int i = 0; i < faces.length; i++) {
            this.faces[i] = faces[i].copy();
        }
    }

    /**
     * Creates a new colored model from an {@link Obj} object.
     *
     * @param obj          Object data to use
     * @param initialColor Initial color component of all faces
     */
    public ColoredModel(@Nonnull Obj obj, @Nonnull Color initialColor) {
        final int numVertices = obj.getNumVertices();
        final int numFaces = obj.getNumFaces();

        //
        // Intermediate step of copying all vertices to ensure the re-use of same vertices across faces
        // This is only possible because Vertex is immutable
        //

        final Vertex[] vertices = new Vertex[numVertices];

        for (int i = 0; i < numVertices; i++) {
            vertices[i] = new Vertex(obj.getVertex(i)).swapXZ(); // Swaps X and Z coordinates for accurate representation
        }

        this.faces = new ColoredFace[numFaces];

        for (int i = 0; i < numFaces; i++) {
            final ObjFace face = obj.getFace(0);

            final Vertex a = vertices[face.getVertexIndex(0)];
            final Vertex b = vertices[face.getVertexIndex(1)];
            final Vertex c = vertices[face.getVertexIndex(2)];

            faces[i] = new ColoredFace(a, b, c, initialColor);
        }
    }

    @Nonnull
    private final ColoredFace[] faces;

    @Override
    @Nonnull
    public ColoredFace[] getFaces() {
        return faces;
    }

    @Nonnull
    @Override
    public ColoredFace getFace(int index) throws IndexOutOfBoundsException {
        return faces[index];
    }

    @Override
    public int getFaceCount() {
        return faces.length;
    }

    @Nonnull
    @Override
    public Model copy() {
        final ColoredFace[] copiedFaces = new ColoredFace[faces.length];

        for (int i = 0; i < faces.length; i++) {
            copiedFaces[i] = faces[i].copy();
        }

        return new ColoredModel(copiedFaces);
    }
}
