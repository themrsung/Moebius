package civitas.celestis.math.complex;

import jakarta.annotation.Nonnull;

/**
 * A builder class for building rotation quaternions.
 * <p><b>
 * Note that this is not a builder in accordance to the builder design pattern,
 * and building a quaternion using this class should not be done during runtime.
 * </b></p>
 */
public final class QuaternionBuilder {
    //
    // Constructors
    //

    /**
     * Starts the building sequence from the identity quaternion. (no rotation)
     */
    public QuaternionBuilder() {
        this(Quaternion.IDENTITY);
    }

    /**
     * Starts the building sequence from an existing quaternion.
     *
     * @param quaternion The quaternion to start from
     */
    public QuaternionBuilder(@Nonnull Quaternion quaternion) {
        this.quaternion = quaternion;
    }

    //
    // Variables
    //
    @Nonnull
    private Quaternion quaternion;

    //
    // Methods
    //

    /**
     * Adds rotation in the form of a rotation quaternion.
     *
     * @param rq The rotation quaternion to apply
     * @return {@code this}
     */
    @Nonnull
    public QuaternionBuilder rotate(@Nonnull Quaternion rq) {
        this.quaternion = rq.multiply(quaternion);
        return this;
    }

    /**
     * Adds pitch to this rotation.
     *
     * @param rads Angle of pitch in radians
     * @return {@code this}
     */
    @Nonnull
    public QuaternionBuilder pitch(double rads) {
        final double halfAngle = rads * 0.5;
        final double sinHalfAngle = Math.sin(halfAngle);
        final double cosHalfAngle = Math.cos(halfAngle);

        return rotate(new Quaternion(cosHalfAngle, sinHalfAngle, 0, 0));
    }

    /**
     * Adds yaw to this rotation.
     *
     * @param rads Angle of yaw in radians
     * @return {@code this}
     */
    @Nonnull
    public QuaternionBuilder yaw(double rads) {
        final double halfAngle = rads * 0.5;
        final double sinHalfAngle = Math.sin(halfAngle);
        final double cosHalfAngle = Math.cos(halfAngle);

        return rotate(new Quaternion(cosHalfAngle, 0, sinHalfAngle, 0));
    }

    /**
     * Adds roll to this rotation.
     *
     * @param rads Angle of roll in radians
     * @return {@code this}
     */
    @Nonnull
    public QuaternionBuilder roll(double rads) {
        final double halfAngle = rads * 0.5;
        final double sinHalfAngle = Math.sin(halfAngle);
        final double cosHalfAngle = Math.cos(halfAngle);

        return rotate(new Quaternion(cosHalfAngle, 0, 0, sinHalfAngle));
    }

    /**
     * Adds pitch to this rotation.
     *
     * @param deg Angle of pitch in degrees
     * @return {@code this}
     */
    @Nonnull
    public QuaternionBuilder pitchDeg(double deg) {
        return pitch(Math.toRadians(deg));
    }

    /**
     * Adds yaw to this rotation.
     *
     * @param deg Angle of yaw in degrees
     * @return {@code this}
     */
    @Nonnull
    public QuaternionBuilder yawDeg(double deg) {
        return yaw(Math.toRadians(deg));
    }

    /**
     * Adds roll to this rotation.
     *
     * @param deg Angle of roll in degrees
     * @return {@code this}
     */
    @Nonnull
    public QuaternionBuilder rollDeg(double deg) {
        return roll(Math.toRadians(deg));
    }

    //
    // Building
    //

    /**
     * Finalizes the building sequence and builds the quaternion.
     *
     * @return The built quaternion
     */
    @Nonnull
    public Quaternion build() {
        return quaternion;
    }
}
