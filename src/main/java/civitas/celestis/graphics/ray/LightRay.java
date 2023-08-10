package civitas.celestis.graphics.ray;

import civitas.celestis.math.vector.Vector3;
import jakarta.annotation.Nonnull;

import java.awt.*;

/**
 * A light ray has an intensity and a color.
 */
public class LightRay implements Ray {
    /**
     * Creates a new light ray.
     *
     * @param origin    Origin of this ray
     * @param direction Direction of this ray
     * @param intensity Intensity of this ray
     */
    public LightRay(@Nonnull Vector3 origin, @Nonnull Vector3 direction, double intensity) {this(origin, direction, Color.WHITE, intensity);}

    /**
     * Creates a new light ray.
     *
     * @param origin    Origin of this ray
     * @param direction Direction of this ray
     * @param color     Color of this ray
     * @param intensity Intensity of this ray
     */
    public LightRay(@Nonnull Vector3 origin, @Nonnull Vector3 direction, @Nonnull Color color, double intensity) {
        this.origin = origin;
        this.direction = direction;
        this.color = color;
        this.intensity = intensity;
    }

    @Nonnull
    private final Vector3 origin, direction;
    @Nonnull
    private final Color color;
    private final double intensity;

    @Override
    @Nonnull
    public Vector3 getOrigin() {
        return origin;
    }

    @Override
    @Nonnull
    public Vector3 getDirection() {
        return direction;
    }

    @Nonnull
    @Override
    public Vector3 getDestination(double t) {
        return origin.add(direction.multiply(t));
    }

    /**
     * Gets the color of this ray.
     *
     * @return Color of this ray
     */
    @Nonnull
    public Color getColor() {
        return color;
    }

    /**
     * Gets the intensity of this ray.
     *
     * @return The intensity of this ray
     */
    public double getIntensity() {
        return intensity;
    }
}
