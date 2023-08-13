package civitas.celestis.object.component;

import civitas.celestis.object.BaseObject;
import civitas.celestis.util.Tickable;
import jakarta.annotation.Nonnull;

/**
 * A component of an object.
 * <p>
 * Objects can have multiple components to implement custom behavior without the need
 * to implement a new independent object class.
 * </p>
 * <p>
 * Since there is only one undefined method ({@link ObjectComponent#tick(long)}), an
 * instance of {@link ObjectComponent} can be declared with a lambda expression.
 * </p>
 */
public interface ObjectComponent extends Tickable {
    /**
     * Called when this component has been added to an object.
     *
     * @param parent The parent object this component was added to
     */
    default void onAdded(@Nonnull BaseObject parent) {}

    /**
     * Called when this component has been removed from an object.
     *
     * @param parent The parent object this component was removed from
     */
    default void onRemoved(@Nonnull BaseObject parent) {}
}
