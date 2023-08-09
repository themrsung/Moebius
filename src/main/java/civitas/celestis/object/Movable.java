package civitas.celestis.object;

import civitas.celestis.math.quaternion.Quaternion;
import civitas.celestis.math.vector.Vector3;
import jakarta.annotation.Nonnull;

/**
 * A movable object has properties related to movement, and can be moved by accessor methods.
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
     * Gets the acceleration of this object.
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
    // Methods
    //

    /**
     * Moves this object by given amount.
     *
     * @param amount The amount of movement to apply
     */
    void move(@Nonnull Vector3 amount);

    /**
     * Accelerates this object by given amount.
     *
     * @param amount The amount of acceleration to apply
     */
    void accelerate(@Nonnull Vector3 amount);

    /**
     * Rotates this object by given amount.
     *
     * @param amount The amount of rotation to apply
     */
    void rotate(@Nonnull Quaternion amount);

    /**
     * Modifies the rate of rotation of this object.
     *
     * @param amount The rotation to apply to the rate of rotation of this object
     */
    void rotateRate(@Nonnull Quaternion amount);
}
