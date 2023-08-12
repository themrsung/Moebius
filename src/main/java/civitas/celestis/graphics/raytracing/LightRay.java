package civitas.celestis.graphics.raytracing;

import civitas.celestis.graphics.color.RichColor;
import civitas.celestis.math.vector.Vector3;
import jakarta.annotation.Nonnull;

/**
 * A ray of light.
 * Light rays have a color and intensity.
 */
public class LightRay implements Ray {
    //
    // Constructors
    //

    /**
     * Creates a new light ray.
     * The color will be set to {@link RichColor#WHITE}.
     *
     * @param origin    The origin of this ray
     * @param direction The direction of this ray
     * @param intensity The intensity of this ray
     */
    public LightRay(@Nonnull Vector3 origin, @Nonnull Vector3 direction, double intensity) {this(origin, direction, RichColor.WHITE, intensity);}

    /**
     * Creates a new light ray.
     *
     * @param origin    The origin of this ray
     * @param direction The direction of this ray
     * @param color     The color of this ray
     * @param intensity The intensity of this ray
     */
    public LightRay(@Nonnull Vector3 origin, @Nonnull Vector3 direction, @Nonnull RichColor color, double intensity) {
        this.origin = origin;
        this.direction = direction;
        this.color = color;
        this.intensity = intensity;
    }

    /**
     * Creates a new light ray.
     *
     * @param other Other ray to copy properties from
     */
    public LightRay(@Nonnull LightRay other) {
        this.origin = other.origin;
        this.direction = other.direction;
        this.color = other.color;
        this.intensity = other.intensity;
    }

    //
    // Variables
    //

    @Nonnull
    protected final Vector3 origin, direction;
    @Nonnull
    protected final RichColor color;
    protected final double intensity;

    //
    // Getters
    //

    /**
     * {@inheritDoc}
     *
     * @return The origin of this ray
     */
    @Nonnull
    @Override
    public final Vector3 origin() {
        return origin;
    }

    /**
     * {@inheritDoc}
     *
     * @return The direction of this ray
     */
    @Nonnull
    @Override
    public final Vector3 direction() {
        return direction;
    }

    /**
     * {@inheritDoc}
     *
     * @param t The distance parameter
     * @return The destination of this ray
     */
    @Nonnull
    @Override
    public final Vector3 destination(double t) {
        return origin.add(direction.multiply(t));
    }

    /**
     * Returns the color of this ray.
     *
     * @return The color of this ray
     */
    @Nonnull
    public final RichColor color() {
        return color;
    }

    /**
     * Returns the intensity of this ray.
     *
     * @return The intensity of this ray
     */
    public final double intensity() {
        return intensity;
    }
}
