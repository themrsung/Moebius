package civitas.celestis.object;

import civitas.celestis.math.quaternion.Quaternion;
import civitas.celestis.math.vector.Vector3;
import jakarta.annotation.Nonnull;

/**
 * A movable object can have a location and rotation.
 */
public interface Movable {
    //
    // Getters
    //

    /**
     * Gets the location of this object.
     *
     * @return The location of this object
     */
    @Nonnull
    Vector3 getLocation();

    /**
     * Gets the current acceleration of this object.
     *
     * @return The acceleration of this object
     */
    @Nonnull
    Vector3 getAcceleration();

    /**
     * Gets the rotation of this object.
     *
     * @return The rotation of this object
     */
    @Nonnull
    Quaternion getRotation();

    /**
     * Gets the rate of rotation of this object.
     *
     * @return The rate of rotation of this object
     */
    @Nonnull
    Quaternion getRotationRate();

    //
    // Setters
    //

    /**
     * Sets the location of this object.
     *
     * @param location The location of this object
     */
    void setLocation(@Nonnull Vector3 location);

    /**
     * Sets the acceleration of this object.
     *
     * @param acceleration The acceleration of this object
     */
    void setAcceleration(@Nonnull Vector3 acceleration);

    /**
     * Sets the rotation of this object.
     *
     * @param rotation The rotation of this object
     */
    void setRotation(@Nonnull Quaternion rotation);

    /**
     * Sets the rate of rotation of this object.
     *
     * @param rotationRate The rate of rotation of this object
     */
    void setRotationRate(@Nonnull Quaternion rotationRate);

    //
    // Modifiers
    //

    /**
     * Moves this object by given amount.
     *
     * @param amount Amount of movement to apply
     */
    void move(@Nonnull Vector3 amount);

    /**
     * Accelerates this object by given amount.
     *
     * @param amount Amount of acceleration to apply
     */
    void accelerate(@Nonnull Vector3 amount);

    /**
     * Rotates this object by given amount.
     *
     * @param amount Rotation to apply
     */
    void rotate(@Nonnull Quaternion amount);

    /**
     * Rotates the rate of rotation by given amount.
     *
     * @param amount Rate of rotation to apply
     */
    void rotateRate(@Nonnull Quaternion amount);
}
