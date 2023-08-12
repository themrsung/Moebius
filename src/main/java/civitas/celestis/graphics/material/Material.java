package civitas.celestis.graphics.material;

import jakarta.annotation.Nonnull;

/**
 * The material of a surface.
 */
public enum Material {
    //
    // Constants
    //

    // Special

    /**
     * A special material with no reflectiveness and {@code 0} elasticity
     */
    NULL(0, 0),

    // Metals

    /**
     * Titanium. A very hard metal.
     */
    TITANIUM(Constants.METAL_REFLECTIVENESS, 0.95),

    /**
     * Hard steel.
     */
    STEEL(Constants.METAL_REFLECTIVENESS, 0.9),

    /**
     * Aluminum. Softer than steel.
     */
    ALUMINUM(Constants.METAL_REFLECTIVENESS, 0.7),

    /**
     * Copper. Softer than aluminum.
     */
    COPPER(Constants.METAL_REFLECTIVENESS, 0.5),

    /**
     * Gold. A very soft metal.
     */
    GOLD(Constants.METAL_REFLECTIVENESS, 0.3);

    //
    // Constructors
    //

    /**
     * Creates a new material.
     * @param reflectiveness The reflectiveness coefficient of this material
     * @param elasticity The elasticity coefficient of this material
     */
    Material(double reflectiveness, double elasticity) {
        this.reflectiveness = reflectiveness;
        this.elasticity = elasticity;
    }

    //
    // Variables
    //

    private final double reflectiveness;
    private final double elasticity;

    //
    // Properties
    //

    /**
     * Returns the reflectiveness coefficient of this material.
     * @return The reflectiveness coefficient of this material
     */
    public double reflectiveness() {
        return reflectiveness;
    }

    /**
     * Returns the elasticity coefficient of this material.
     * @return The elasticity coefficient of this material
     */
    public double elasticity() {
        return elasticity;
    }

    /**
     * Calculates the restitution coefficient when colliding with the given material.
     * @param other The material this material has collided with
     * @return The restitution coefficient of the collision
     */
    public double restitution(@Nonnull Material other) {
        return elasticity * other.elasticity;
    }

    /**
     * A static nested class which houses the constants for materials.
     */
    private static class Constants {
        /**
         * The reflectiveness coefficient of metals.
         */
        private static final double METAL_REFLECTIVENESS = 10;
    }
}
