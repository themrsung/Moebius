package civitas.celestis.physics.profile;

import civitas.celestis.object.Physical;
import civitas.celestis.physics.solid.Solid;
import jakarta.annotation.Nonnull;

/**
 * A profile used to build a discrete solid of an object.
 */
public interface PhysicsProfile {
    /**
     * Gets the volume of this physical profile.
     *
     * @return The volume of this physical profile
     */
    double getVolume();

    /**
     * Gets the surface area of this physical profile.
     *
     * @return The surface area of this physical profile
     */
    double getSurfaceArea();

    /**
     * Gets the mass of this physical profile.
     *
     * @return The mass of this physical profile
     */
    double getMass();

    /**
     * Gets the density of this physical profile.
     * If the volume is {@code 0}, this will return {@code 0}.
     *
     * @return The density of this physical profile
     */
    double getDensity();

    /**
     * Builds a discrete solid from this physical profile.
     *
     * @param object The object in possession of this physical profile
     * @return The built solid
     */
    @Nonnull
    Solid build(@Nonnull Physical object);
}
