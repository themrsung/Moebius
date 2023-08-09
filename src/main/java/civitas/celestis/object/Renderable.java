package civitas.celestis.object;

import civitas.celestis.graphics.model.Model;
import jakarta.annotation.Nonnull;

/**
 * A renderable object has a graphical model.
 */
public interface Renderable extends Movable {
    /**
     * Gets the graphical model of this object.
     *
     * @return The graphical model of this object
     */
    @Nonnull
    Model getModel();

    /**
     * Sets the graphical model of this object.
     *
     * @param model The graphical model of this object
     */
    void setModel(@Nonnull Model model);
}
