package civitas.celestis.ui.event.component;

import civitas.celestis.ui.UIContainer;
import civitas.celestis.ui.component.UIComponent;
import jakarta.annotation.Nonnull;

/**
 * An event which is invoked when a component has been added to a container.
 */
public class ComponentAddedEvent extends ComponentEvent {
    /**
     * Creates a new component added event.
     *
     * @param container The container the component was added to
     * @param component The component which was added to the container
     */
    public ComponentAddedEvent(@Nonnull UIContainer container, @Nonnull UIComponent component) {
        super(container, component);
    }
}
