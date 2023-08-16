package civitas.celestis.object;

import civitas.celestis.util.Unique;
import jakarta.annotation.Nonnull;

import java.util.UUID;

/**
 * A movable object can be placed in a world, and have a location and rotation
 * of arbitrary data types {@link L} and {@link R}.
 *
 * @param <L> The datatype to use to represent location
 * @param <R> The datatype to use to represent rotation
 */
public interface Movable<L, R> extends Unique<UUID> {
    //
    // Properties
    //

    /**
     * Returns whether this object is in motion.
     *
     * @return {@code true} if the acceleration is non-zero
     */
    boolean isMoving();

    /**
     * Returns whether this object is rotating.
     *
     * @return {@code true} if the rate of rotation is non-identity
     */
    boolean isRotating();

    //
    // Getters
    //

    /**
     * Returns the current location of this object.
     *
     * @return The location of this object
     */
    @Nonnull
    L getLocation();

    /**
     * Returns the current acceleration of this object.
     *
     * @return The acceleration of this object
     */
    @Nonnull
    L getAcceleration();

    /**
     * Returns the current rotation of this object.
     *
     * @return The rotation of this object
     */
    @Nonnull
    R getRotation();

    /**
     * Returns the current rate of rotation of this object.
     *
     * @return The rate of rotation of this object
     */
    @Nonnull
    R getRotationRate();

    //
    // Setters
    //

    /**
     * Sets the location of this object.
     *
     * @param location The location of this object
     */
    void setLocation(@Nonnull L location);

    /**
     * Sets the acceleration of this object.
     *
     * @param acceleration The acceleration of this object
     */
    void setAcceleration(@Nonnull L acceleration);

    /**
     * Sets the rotation of this object.
     *
     * @param rotation The rotation of this object
     */
    void setRotation(@Nonnull R rotation);

    /**
     * Sets the rate of rotation of this object.
     *
     * @param rotationRate The rate of rotation of this object
     */
    void setRotationRate(@Nonnull R rotationRate);

    //
    // Modifiers
    //

    /**
     * Moves this object.
     *
     * @param amount The amount of movement to apply
     */
    void move(@Nonnull L amount);

    /**
     * Accelerates this object.
     *
     * @param amount The amount of acceleration to apply
     */
    void accelerate(@Nonnull L amount);

    /**
     * Rotates this object.
     *
     * @param amount The amount of rotation to apply
     */
    void rotate(@Nonnull R amount);

    /**
     * Changes the rate of rotation of this object.
     *
     * @param amount The amount of change to apply
     */
    void rotateRate(@Nonnull R amount);
}
