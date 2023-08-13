package civitas.celestis.ui.component;

import civitas.celestis.ui.event.component.ComponentAddedEvent;
import civitas.celestis.ui.event.component.ComponentRemovedEvent;
import civitas.celestis.ui.image.RichImage;
import jakarta.annotation.Nonnull;

/**
 * A UI component. Components can be added to a frame.
 */
public interface UIComponent {
    /**
     * Renders this component to the output image.
     * The provided image is a raw reference to the frame's image.
     *
     * @param image The output image
     */
    void render(@Nonnull RichImage image);

    /**
     * Returns the Z index of this component.
     * Components with a higher Z index will be rendered last, thus taking priority.
     *
     * @return The Z index of this component
     */
    default int getZ() {return 0;}

    /**
     * Called when this component has been added to a container.
     *
     * @param event The component event
     */
    default void onComponentAdded(@Nonnull ComponentAddedEvent event) {}

    /**
     * Called when this component has been removed from a container.
     *
     * @param event The component event
     */
    default void onComponentRemoved(@Nonnull ComponentRemovedEvent event) {}
}
