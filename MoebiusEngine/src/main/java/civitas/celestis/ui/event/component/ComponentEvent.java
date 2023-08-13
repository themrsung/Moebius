package civitas.celestis.ui.event.component;

import civitas.celestis.ui.UIContainer;
import civitas.celestis.ui.UIFrame;
import civitas.celestis.ui.component.UIComponent;
import civitas.celestis.ui.event.UIEvent;
import jakarta.annotation.Nonnull;

/**
 * An event involving a container and a component.
 */
public abstract class ComponentEvent implements UIEvent {
    /**
     * Creates a new component event.
     * @param container The container of this event
     * @param component The component of this event
     */
    public ComponentEvent(@Nonnull UIContainer container, @Nonnull UIComponent component) {
        this.container = container;
        this.component = component;
    }

    @Nonnull
    private final UIContainer container;
    @Nonnull
    private final UIComponent component;

    /**
     * Returns the container of this event.
     * @return The container of this event
     */
    @Nonnull
    public UIContainer getContainer() {
        return container;
    }

    /**
     * Returns the frame the container is placed in.
     * @return The frame of this event
     */
    @Nonnull
    public UIFrame getFrame() {
        return container.getParent();
    }

    /**
     * Returns the component of this event.
     * @return The component of this event
     */
    @Nonnull
    public UIComponent getComponent() {
        return component;
    }
}
