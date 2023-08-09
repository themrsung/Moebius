package civitas.celestis.object;

import civitas.celestis.physics.profile.PhysicsProfile;
import civitas.celestis.physics.solid.Solid;
import jakarta.annotation.Nonnull;

/**
 * A physical object has a physical profile.
 */
public interface Physical extends Movable {
    /**
     * Gets the physics profile of this object.
     *
     * @return The physics profile of this object
     */
    @Nonnull
    PhysicsProfile getPhysicsProfile();

    /**
     * Gets the discrete solid of this object.
     *
     * @return The discrete solid of this object
     */
    @Nonnull
    Solid getSolid();

    /**
     * Sets the physics profile of this object.
     *
     * @param physicsProfile The physics profile of this object
     */
    void setPhysicsProfile(@Nonnull PhysicsProfile physicsProfile);
}
