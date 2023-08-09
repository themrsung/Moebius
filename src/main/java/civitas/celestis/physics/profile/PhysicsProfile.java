package civitas.celestis.physics.profile;

import civitas.celestis.object.Physical;
import civitas.celestis.physics.solid.Solid;
import jakarta.annotation.Nonnull;

/**
 * A profile for building a {@link Solid}.
 */
public interface PhysicsProfile {
    /**
     * Builds a solid from this profile.
     *
     * @param object The object in possession of this profile
     * @return Built solid
     */
    @Nonnull
    Solid build(@Nonnull Physical object);
}
