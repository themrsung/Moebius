package civitas.celestis.ui;

import civitas.celestis.ui.component.UIComponent;
import civitas.celestis.ui.event.component.ComponentAddedEvent;
import civitas.celestis.ui.event.component.ComponentRemovedEvent;
import jakarta.annotation.Nonnull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * A container class which handles the lifecycle of UI components.
 */
public class UIContainer {
    //
    // Constructors
    //

    /**
     * Creates a new empty UI container.
     *
     * @param parent The parent frame of this container
     */
    public UIContainer(@Nonnull UIFrame parent) {
        this.parent = parent;
        this.components = new ArrayList<>();
    }

    /**
     * Creates a new UI container with a predefined list of components.
     *
     * @param parent     The parent frame of this container
     * @param components The list of components to construct this container with
     */
    public UIContainer(@Nonnull UIFrame parent, @Nonnull List<UIComponent> components) {
        this.parent = parent;
        this.components = new ArrayList<>(components);
    }

    //
    // Variables
    //
    @Nonnull
    protected final UIFrame parent;
    @Nonnull
    private final List<UIComponent> components;

    //
    // Methods
    //

    /**
     * Triggers a re-render of all components in this container.
     */
    public void render() {
        parent.repaint();
    }

    //
    // Getters
    //

    /**
     * Returns a reference to the parent frame of this container.
     *
     * @return A reference to the parent frame of this container
     */
    @Nonnull
    public final UIFrame getParent() {
        return parent;
    }

    /**
     * Returns a list of components currently in this container.
     *
     * @return The list of components in this container
     */
    @Nonnull
    public final List<UIComponent> getComponents() {
        return List.copyOf(components);
    }

    /**
     * Returns the {@code i}th component of this container.
     *
     * @param i The index of the component to get
     * @return The component at the specified index
     * @throws IndexOutOfBoundsException When the index {@code i} is out of bounds
     */
    @Nonnull
    public final UIComponent getComponent(int i) throws IndexOutOfBoundsException {
        return components.get(i);
    }

    /**
     * Checks if this container contains the given component.
     *
     * @param component The component to check for
     * @return {@code true} if the component is contained within this container
     */
    public final boolean contains(@Nonnull UIComponent component) {
        return components.contains(component);
    }

    //
    // Setters
    //

    /**
     * Adds a component to this container.
     *
     * @param component The component to add to this container
     */
    public final void addComponent(@Nonnull UIComponent component) {
        components.add(component);

        // Declare event
        final ComponentAddedEvent event = new ComponentAddedEvent(this, component);

        // Notify observers
        component.onComponentAdded(event);
        parent.onComponentAdded(event);

        // Preemptively sort components by Z index
        components.sort(Comparator.comparing(UIComponent::getZ));
    }

    /**
     * Removes a component from this container.
     *
     * @param component The component to remove from this container
     */
    public final void removeComponent(@Nonnull UIComponent component) {
        if (!components.remove(component)) return;

        // Declare event
        final ComponentRemovedEvent event = new ComponentRemovedEvent(this, component);

        // Notify observers
        component.onComponentRemoved(event);
        parent.onComponentRemoved(event);
    }
}
