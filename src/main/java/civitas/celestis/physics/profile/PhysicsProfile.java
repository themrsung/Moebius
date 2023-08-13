package civitas.celestis.physics.profile;

import civitas.celestis.object.Physical;
import civitas.celestis.physics.solid.Solid;
import jakarta.annotation.Nonnull;

/**
 * The physical profile of an object.
 * This object contains data used to build a discrete solid of an object,
 * as well as other physical data such as mass or volume.
 */
public interface PhysicsProfile {
    /**
     * Returns the mass of this profile.
     *
     * @return The mass of this profile
     */
    double getMass();

    /**
     * Returns the volume of this profile.
     *
     * @return The volume of this profile
     */
    double getVolume();

    /**
     * Returns the density of this profile.
     * If the volume is zero, this will return {@code 0}.
     *
     * @return The density of this profile
     */
    default double getDensity() {
        final double volume = getVolume();
        if (volume == 0) return 0;

        return getMass() / volume;
    }

    /**
     * Builds the discrete solid of this physical profile.
     *
     * @param parent The parent object which is in possession of this profile.
     * @return The built discrete solid
     */
    @Nonnull
    Solid build(@Nonnull Physical parent);
}
