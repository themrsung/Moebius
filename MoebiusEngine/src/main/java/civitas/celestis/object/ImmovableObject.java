package civitas.celestis.object;

import civitas.celestis.graphics.model.Model;
import civitas.celestis.math.complex.Quaternion;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.object.component.ObjectComponent;
import civitas.celestis.physics.profile.PhysicsProfile;
import jakarta.annotation.Nonnull;

import java.util.List;
import java.util.UUID;

/**
 * An immovable object.
 * The location and rotation of an immovable object cannot be changed after its inception.
 */
public class ImmovableObject extends BaseObject {
    /**
     * Creates a new immovable object.
     *
     * @param uniqueId       The unique identifier of this object
     * @param location       The location of this object
     * @param rotation       The rotation of this object
     * @param physicsProfile The physical profile of this object
     * @param model          The graphical model of this object
     */
    public ImmovableObject(
            @Nonnull UUID uniqueId,
            @Nonnull Vector3 location,
            @Nonnull Quaternion rotation,
            @Nonnull PhysicsProfile physicsProfile,
            @Nonnull Model model
    ) {
        super(uniqueId, location, rotation, physicsProfile, model);
    }

    /**
     * Creates a new immovable object.
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
    public ImmovableObject(
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

    //
    // Methods
    //

    /**
     * Since an immovable object's location cannot be changed, this method does nothing.
     *
     * @param location The new location of this object
     */
    @Override
    public final void setLocation(@Nonnull Vector3 location) {}

    /**
     * Since an immovable object's rotation cannot be changed, this method does nothing.
     *
     * @param rotation The new rotation of this object
     */
    @Override
    public final void setRotation(@Nonnull Quaternion rotation) {}

    /**
     * Since an immovable object's location cannot be changed, this method does nothing.
     *
     * @param amount The amount of movement to apply
     */
    @Override
    public final void move(@Nonnull Vector3 amount) {}

    /**
     * Since an immovable object's rotation cannot be changed, this method does nothing.
     *
     * @param amount The amount of rotation to apply
     */
    @Override
    public final void rotate(@Nonnull Quaternion amount) {}
}
