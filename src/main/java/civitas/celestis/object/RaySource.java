package civitas.celestis.object;

import civitas.celestis.graphics.ray.Ray;
import jakarta.annotation.Nonnull;

import java.util.Collection;

/**
 * An object which emits rays.
 */
public interface RaySource {
    /**
     * Generates the rays this object emits for this frame.
     *
     * @return Collection of rays emitted by this object
     */
    @Nonnull
    Collection<Ray> generateRays();
}
