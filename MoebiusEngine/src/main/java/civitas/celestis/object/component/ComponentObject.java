package civitas.celestis.object.component;

import civitas.celestis.graphics.model.Model;
import civitas.celestis.math.quaternion.Quaternion;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.object.BaseObject;
import civitas.celestis.physics.profile.PhysicsProfile;
import jakarta.annotation.Nonnull;

import java.util.List;
import java.util.UUID;

/**
 * An object which is also an object component.
 * These objects can be used to created child objects of other objects.
 */
public class ComponentObject extends BaseObject implements ObjectComponent {
    /**
     * Creates a new component object.
     *
     * @param uniqueId       The unique identifier of this object
     * @param location       The location of this object
     * @param rotation       The rotation of this object
     * @param physicsProfile The physical profile of this object
     * @param model          The graphical model of this object
     */
    public ComponentObject(
            @Nonnull UUID uniqueId,
            @Nonnull Vector3 location,
            @Nonnull Quaternion rotation,
            @Nonnull PhysicsProfile physicsProfile,
            @Nonnull Model model
    ) {
        super(uniqueId, location, rotation, physicsProfile, model);
    }

    /**
     * Creates a new component object.
     *
     * @param uniqueId       The unique identifier of this object
     * @param location       The location of this object
     * @param acceleration   The acceleration of this object
     * @param rotation       The rotation of this object
     * @param rotationRate   The rate of rotation of this object
     * @param physicsProfile The physical profile of this object
     * @param model          The graphical model of this object
     * @param components     The list of initial components of this object
     */
    public ComponentObject(
            @Nonnull UUID uniqueId,
            @Nonnull Vector3 location,
            @Nonnull Vector3 acceleration,
            @Nonnull Quaternion rotation,
            @Nonnull Quaternion rotationRate,
            @Nonnull PhysicsProfile physicsProfile,
            @Nonnull Model model,
            @Nonnull List<ObjectComponent> components
    ) {
        super(uniqueId, location, acceleration, rotation, rotationRate, physicsProfile, model, components);
    }

    /**
     * {@inheritDoc}
     * <p>
     * If this object was added to itself as a component,
     * this will immediately remove itself from the list of components.
     * This ensures that a component object will not be added to itself.
     * </p>
     *
     * @param parent The parent object this component was added to
     */
    @Override
    public void onAdded(@Nonnull BaseObject parent) {
        if (equals(parent)) parent.removeComponent(this);
    }
}
