package civitas.celestis.physics.profile;

import civitas.celestis.math.util.Numbers;
import civitas.celestis.object.Physical;
import civitas.celestis.physics.solid.Solid;
import civitas.celestis.physics.solid.Sphere;
import jakarta.annotation.Nonnull;

/**
 * A spherical physical profile.
 */
public class SphereProfile implements PhysicsProfile {
    /**
     * Creates a new spherical profile.
     *
     * @param radius The radius of this profile
     * @param mass   The mass of this profile
     */
    public SphereProfile(double radius, double mass) {
        this.radius = Numbers.requireFinite(radius);
        this.mass = Numbers.requireFinite(mass);
    }

    private final double radius, mass;

    /**
     * Gets the radius of this spherical profile.
     *
     * @return The radius of this spherical profile
     */
    public double getRadius() {
        return radius;
    }

    @Override
    public double getVolume() {
        return 4.0 / 3.0 * Math.PI * Math.pow(radius, 3);
    }

    @Override
    public double getSurfaceArea() {
        return 4.0 * Math.PI * radius * radius;
    }

    @Override
    public double getMass() {
        return mass;
    }

    @Override
    public double getDensity() {
        final double volume = getVolume();
        if (volume == 0) return 0;

        return mass / volume;
    }

    @Nonnull
    @Override
    public Sphere build(@Nonnull Physical object) {
        return new Sphere(object.getLocation(), radius);
    }
}
