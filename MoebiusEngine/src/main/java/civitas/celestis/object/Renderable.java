package civitas.celestis.object;

import civitas.celestis.graphics.face.Face;
import civitas.celestis.graphics.model.Model;
import jakarta.annotation.Nonnull;

import java.util.List;

/**
 * A renderable object has a graphical model which can be rendered.
 */
public interface Renderable extends Placeable {
    /**
     * Returns the graphical model of this object.
     *
     * @return The graphical model of this object
     */
    @Nonnull
    Model getModel();

    /**
     * Returns a list of faces which are mapped to absolute coordinates
     * in relation to this object's location and rotation.
     *
     * @return A list of faces of this object mapped to absolute coordinates
     */
    @Nonnull
    List<? extends Face> getAbsoluteFaces();

    /**
     * Sets the graphical model of this object.
     *
     * @param model The model to set to
     */
    void setModel(@Nonnull Model model);
}
