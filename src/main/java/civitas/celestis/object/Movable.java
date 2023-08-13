package civitas.celestis.object;

import civitas.celestis.math.quaternion.Quaternion;
import civitas.celestis.math.vector.Vector3;
import jakarta.annotation.Nonnull;

/**
 * A movable object can be placed in a simulated world,
 * and can be moved by the Moebius engine.
 */
public interface Movable {
    //
    // Properties
    //

    /**
     * Returns the current location of this object.
     *
     * @return The location of this object
     */
    @Nonnull
    Vector3 getLocation();

    /**
     * Returns the current acceleration of this object.
     *
     * @return The acceleration of this object
     */
    @Nonnull
    Vector3 getAcceleration();

    /**
     * Returns the rotation of this object.
     *
     * @return The rotation of this object
     */
    @Nonnull
    Quaternion getRotation();

    /**
     * Returns the rate of rotation of this object.
     *
     * @return The rate of rotation of this object
     */
    @Nonnull
    Quaternion getRotationRate();

    /**
     * Returns the current velocity of this object.
     *
     * @return The velocity of this object
     */
    double getVelocity();

    /**
     * Returns the current squared velocity of this object.
     *
     * @return The squared velocity of this object
     */
    double getVelocity2();

    /**
     * Returns whether this object is currently moving.
     * This accounts for floating point imprecision of the acceleration vector.
     *
     * @return {@code true} if this object's acceleration is not zero
     */
    boolean isMoving();

    /**
     * Returns whether this object is currently rotating.
     * This accounts for floating point imprecision of the rate of rotation.
     *
     * @return {@code true} if this object's rate of rotation is
     * effectively equal to the identity quaternion
     */
    boolean isRotating();

    //
    // Setters
    //

    /**
     * Sets the location of this object.
     *
     * @param location The new location of this object
     */
    void setLocation(@Nonnull Vector3 location);

    /**
     * Sets the acceleration of this object.
     *
     * @param acceleration The new acceleration of this object
     */
    void setAcceleration(@Nonnull Vector3 acceleration);

    /**
     * Sets the rotation of this object.
     *
     * @param rotation The new rotation of this object
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
     * Moves this object by the specified amount.
     *
     * @param amount The amount of movement to apply
     */
    void move(@Nonnull Vector3 amount);

    /**
     * Accelerates this object by the specified amount.
     *
     * @param amount The amount of acceleration to apply
     */
    void accelerate(@Nonnull Vector3 amount);

    /**
     * Rotates this object by the specified amount.
     *
     * @param amount The amount of rotation to apply
     */
    void rotate(@Nonnull Quaternion amount);

    /**
     * Rotates the rate of rotation by the specified amount.
     *
     * @param amount The amount of rotation to apply to the rate of rotation
     */
    void rotateRate(@Nonnull Quaternion amount);


}
