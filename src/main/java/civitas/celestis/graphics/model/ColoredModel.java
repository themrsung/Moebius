package civitas.celestis.graphics.model;

import civitas.celestis.graphics.face.ColoredFace;
import civitas.celestis.graphics.util.GraphicsUtils;
import civitas.celestis.math.vector.Vector3;
import de.javagl.obj.FloatTuple;
import de.javagl.obj.Obj;
import de.javagl.obj.ObjFace;
import jakarta.annotation.Nonnull;

import java.awt.*;
import java.util.Arrays;

/**
 * A model constructed from colored faces.
 */
public class ColoredModel implements Model {
    /**
     * Creates a new colored model.
     *
     * @param faces Array of faces to use
     */
    public ColoredModel(@Nonnull ColoredFace[] faces) {
        this.faces = Arrays.copyOf(faces, faces.length);
    }

    /**
     * Creates a new colored model.
     *
     * @param obj          The Wavefront {@link Obj} object to use
     * @param initialColor The initial color of all faces
     * @param scale        The scale factor to apply to all faces
     */
    public ColoredModel(@Nonnull Obj obj, @Nonnull Color initialColor, double scale) {
        this.faces = new ColoredFace[obj.getNumFaces()];

        for (int i = 0; i < obj.getNumFaces(); i++) {
            final ObjFace face = obj.getFace(i);

            final FloatTuple t1 = obj.getVertex(face.getVertexIndex(0));
            final FloatTuple t2 = obj.getVertex(face.getVertexIndex(1));
            final FloatTuple t3 = obj.getVertex(face.getVertexIndex(2));

            final Vector3 v1 = GraphicsUtils.wavefrontTupleToVector3(t1).multiply(scale);
            final Vector3 v2 = GraphicsUtils.wavefrontTupleToVector3(t2).multiply(scale);
            final Vector3 v3 = GraphicsUtils.wavefrontTupleToVector3(t3).multiply(scale);

            faces[i] = new ColoredFace(v1, v2, v3, initialColor);
        }
    }

    @Nonnull
    private final ColoredFace[] faces;

    @Override
    @Nonnull
    public ColoredFace[] getFaces() {
        return faces; // Array not copied intentionally
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
    public ColoredModel copy() {
        final ColoredFace[] result = new ColoredFace[faces.length];

        for (int i = 0; i < faces.length; i++) {
            result[i] = faces[i].copy();
        }

        return new ColoredModel(result);
    }

    /**
     * Serializes this model into a string.
     *
     * @return The string representation of this model
     */
    @Override
    @Nonnull
    public String toString() {
        return "ColoredModel{" +
                "faces=" + Arrays.toString(faces) +
                '}';
    }
}
