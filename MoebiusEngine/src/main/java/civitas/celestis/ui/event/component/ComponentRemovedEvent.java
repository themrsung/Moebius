package civitas.celestis.ui.event.component;

import civitas.celestis.ui.UIContainer;
import civitas.celestis.ui.component.UIComponent;
import jakarta.annotation.Nonnull;

/**
 * An event which is invoked when a component has been removed from a container.
 */
public class ComponentRemovedEvent extends ComponentEvent {
    /**
     * Creates a new component removed event.
     * @param container The container the component was removed from
     * @param component The component which was removed from the container
     */
    public ComponentRemovedEvent(@Nonnull UIContainer container, @Nonnull UIComponent component) {
        super(container, component);
    }
}
