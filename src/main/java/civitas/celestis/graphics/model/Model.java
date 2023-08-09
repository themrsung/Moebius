package civitas.celestis.graphics.model;

import civitas.celestis.graphics.face.Face;
import civitas.celestis.graphics.util.GraphicsUtils;
import civitas.celestis.math.vector.Vector3;
import de.javagl.obj.Obj;
import de.javagl.obj.ObjFace;
import jakarta.annotation.Nonnull;

import java.util.Arrays;

/**
 * A 3D representation of an object.
 */
public class Model {
    //
    // Constructors
    //

    /**
     * Creates a new model.
     *
     * @param faces Array of faces of this model
     */
    public Model(@Nonnull Face[] faces) {
        this.faces = Arrays.copyOf(faces, faces.length);
    }

    /**
     * Creates a new model.
     *
     * @param obj Wavefront object to get faces from
     */
    public Model(@Nonnull Obj obj) {
        this.faces = new Face[obj.getNumFaces()];

        for (int i = 0; i < obj.getNumFaces(); i++) {
            final ObjFace face = obj.getFace(i);

            final Vector3 a = GraphicsUtils.objTupleToVector3(obj.getVertex(face.getVertexIndex(0)));
            final Vector3 b = GraphicsUtils.objTupleToVector3(obj.getVertex(face.getVertexIndex(1)));
            final Vector3 c = GraphicsUtils.objTupleToVector3(obj.getVertex(face.getVertexIndex(2)));

            faces[i] = new Face(a, b, c);
        }
    }

    //
    // Variables
    //

    @Nonnull
    private final Face[] faces;

    //
    // Getters
    //

    /**
     * Gets an array of all faces of this model.
     *
     * @return Array of all faces
     */
    @Nonnull
    public Face[] getFaces() {
        return Arrays.copyOf(faces, faces.length);
    }

    /**
     * Gets the face in the specified index.
     *
     * @param index Index of face to get
     * @return Face of specified index
     * @throws IndexOutOfBoundsException When the index is out of bounds
     */
    @Nonnull
    public Face getFace(int index) throws IndexOutOfBoundsException {
        return faces[index];
    }

    /**
     * Gets the number of faces in this model.
     *
     * @return The number of faces
     */
    public int getFaceCount() {
        return faces.length;
    }
}
