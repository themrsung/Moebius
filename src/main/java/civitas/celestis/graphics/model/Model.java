package civitas.celestis.graphics.model;

import civitas.celestis.graphics.face.Face;
import civitas.celestis.util.Copyable;
import jakarta.annotation.Nonnull;

/**
 * A 3D model which represents the graphical profile of an object.
 */
public interface Model extends Copyable<Model> {
    /**
     * Gets an array of all faces in this model.
     *
     * @return Array of faces of this model
     */
    @Nonnull
    Face[] getFaces();

    /**
     * Gets the face at the specified index.
     *
     * @param index Index of face to get
     * @return Face of specified index
     * @throws IndexOutOfBoundsException When the index is out of bounds
     */
    @Nonnull
    Face getFace(int index) throws IndexOutOfBoundsException;

    /**
     * Gets the number of faces this model has.
     *
     * @return The number of faces of this model
     */
    int getFaceCount();
}
