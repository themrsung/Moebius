package civitas.celestis.object;

import civitas.celestis.graphics.model.Model;
import civitas.celestis.math.quaternion.Quaternion;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.physics.profile.PhysicsProfile;
import jakarta.annotation.Nonnull;

import java.util.UUID;

/**
 * An immovable object's location and rotation cannot be changed after instantiation.
 * While immovable objects still have acceleration and rate of rotation,
 * they will only affect the counterparty object of a collision.
 */
public class ImmovableObject extends BaseObject {
    /**
     * Creates a new immovable object.
     *
     * @param uniqueId       Unique identifier of this object
     * @param location       Location of this object
     * @param rotation       Rotation of this object
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
     * Does nothing.
     *
     * @param location The location of this object
     */
    @Override
    public void setLocation(@Nonnull Vector3 location) {}

    /**
     * Does nothing.
     *
     * @param rotation The rotation of this object
     */
    @Override
    public void setRotation(@Nonnull Quaternion rotation) {}

    /**
     * Does nothing.
     *
     * @param amount The amount of movement to apply
     */
    @Override
    public void move(@Nonnull Vector3 amount) {}

    /**
     * Does nothing.
     *
     * @param amount The amount of rotation to apply
     */
    @Override
    public void rotate(@Nonnull Quaternion amount) {}
}
