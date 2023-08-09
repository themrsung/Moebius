package civitas.celestis.object;

import civitas.celestis.graphics.model.Model;
import jakarta.annotation.Nonnull;

/**
 * A renderable object has a model as its property, and can be rendered to a scene.
 */
public interface Renderable extends Movable {
    //
    // Getters
    //

    /**
     * Gets the model of this object.
     *
     * @return The model of this object
     */
    @Nonnull
    Model getModel();

    //
    // Setters
    //

    /**
     * Sets the model of this object.
     *
     * @param model The model of this object
     */
    void setModel(@Nonnull Model model);
}
