package civitas.celestis.object.immovable;

import civitas.celestis.graphics.model.Model;
import civitas.celestis.math.quaternion.Quaternion;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.object.BaseObject;
import civitas.celestis.physics.profile.PhysicsProfile;
import jakarta.annotation.Nonnull;

import java.util.UUID;

/**
 * An immovable object's location and rotation cannot be changed after instantiation.
 * Although the acceleration and rate of rotation can be modified, they will do nothing.
 */
public class ImmovableObject extends BaseObject {
    public ImmovableObject(@Nonnull UUID uniqueId, @Nonnull Vector3 location, @Nonnull Quaternion rotation, @Nonnull PhysicsProfile physicsProfile, @Nonnull Model model) {
        super(uniqueId, location, rotation, physicsProfile, model);
    }

    @Override
    public void setLocation(@Nonnull Vector3 location) {}

    @Override
    public void setRotation(@Nonnull Quaternion rotation) {}

    @Override
    public void move(@Nonnull Vector3 amount) {}

    @Override
    public void rotate(@Nonnull Quaternion amount) {}
}
