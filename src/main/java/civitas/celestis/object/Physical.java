package civitas.celestis.object;

import civitas.celestis.physics.profile.PhysicsProfile;
import civitas.celestis.physics.solid.Solid;
import jakarta.annotation.Nonnull;

/**
 * A physical object has a physical profile and can collide with other physical objects.
 */
public interface Physical extends Movable {
    //
    // Getters
    //

    /**
     * Gets the physical profile of this object.
     *
     * @return The physical profile of this object
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

    //
    // Setters
    //

    /**
     * Sets the physical profile of this object.
     *
     * @param physicsProfile The physical profile of this object
     */
    void setPhysicsProfile(@Nonnull PhysicsProfile physicsProfile);
}
