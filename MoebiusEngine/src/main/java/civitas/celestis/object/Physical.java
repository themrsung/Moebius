package civitas.celestis.object;

import civitas.celestis.physics.profile.PhysicsProfile;
import civitas.celestis.physics.solid.Solid;
import jakarta.annotation.Nonnull;

/**
 * A physical object has a physical profile and is subject to physical phenomena.
 */
public interface Physical {
    //
    // Getters
    //

    /**
     * Returns the physical profile of this object.
     *
     * @return The physical profile of this object
     */
    @Nonnull
    PhysicsProfile getPhysicsProfile();

    /**
     * Returns the mass of this object.
     *
     * @return The mass of this object
     */
    double getMass();

    /**
     * Returns the volume of this object.
     *
     * @return The volume of this object
     */
    double getVolume();

    /**
     * Returns the density of this object.
     *
     * @return The density of this object
     */
    double getDensity();

    /**
     * Returns the discrete solid of this object.
     *
     * @return The discrete solid of this object
     */
    @Nonnull
    Solid getSolid();

    /**
     * Checks if this object overlaps another object.
     *
     * @param other The object to compare to
     * @return {@code true} if the two objects overlap
     */
    boolean overlaps(@Nonnull Physical other);

    //
    // Setters
    //

    /**
     * Sets the physical profile of this object.
     *
     * @param profile The physical profile of this object
     */
    void setPhysicsProfile(@Nonnull PhysicsProfile profile);
}
