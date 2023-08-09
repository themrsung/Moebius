package civitas.celestis.math.quaternion;

import civitas.celestis.math.vector.Vector3;
import civitas.celestis.math.vector.Vector4;
import jakarta.annotation.Nonnull;

/**
 * A quaternion is used to represent the rotation of a {@link Vector3}.
 */
public final class Quaternion extends Vector4 {
    //
    // Constants
    //

    /**
     * The identity quaternion. This represents no rotation.
     */
    public static final Quaternion IDENTITY = new Quaternion(1, 0, 0, 0);

    //
    // Constructors
    //

    /**
     * Returns a new builder instance.
     *
     * @return A new builder instance
     */
    @Nonnull
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Creates a new quaternion from four component scalars.
     *
     * @param w The W component of this quaternion
     * @param x The X component of this quaternion
     * @param y The Y component of this quaternion
     * @param z The Z component of this quaternion
     */
    public Quaternion(double w, double x, double y, double z) {
        super(w, x, y, z);
    }

    /**
     * Creates a new quaternion from a scalar and a vector.
     *
     * @param s Scalar part of this quaternion
     * @param v Vector part of this quaternion
     */
    public Quaternion(double s, @Nonnull Vector3 v) {
        super(s, v.x(), v.y(), v.z());
    }

    /**
     * Creates a new quaternion from an array of component scalars.
     * The length of the array must be equal to {@code 4}.
     *
     * @param values Array of values to use to construct this quaternion
     */
    public Quaternion(@Nonnull double[] values) {
        super(values);
    }

    /**
     * Creates a new quaternion by copying the values of a {@link Vector4}. (or another quaternion)
     *
     * @param other Vector to copy the values of
     */
    public Quaternion(@Nonnull Vector4 other) {
        super(other);
    }

    //
    // Properties
    //

    /**
     * Returns the vector part of this quaternion.
     *
     * @return The vector part of this quaternion
     */
    @Nonnull
    public Vector3 vector() {
        return new Vector3(x(), y(), z());
    }


    /**
     * Calculates the pitch angle (rotation around the X-axis) from this Quaternion.
     *
     * @return The pitch angle in radians
     */
    public double pitch() {
        final double sinp = 2 * (w() * x() + y() * z());
        final double cosp = 1 - 2 * (x() * x() + y() * y());
        return Math.atan2(sinp, cosp);
    }

    /**
     * Calculates the yaw angle (rotation around the Y-axis) from this Quaternion.
     *
     * @return The yaw angle in radians
     */
    public double yaw() {
        final double sinp = 2 * (w() * y() - z() * x());
        final double cosp = 1 - 2 * (y() * y() + z() * z());
        return Math.atan2(sinp, cosp);
    }

    /**
     * Calculates the roll angle (rotation around the Z-axis) from this Quaternion.
     *
     * @return The roll angle in radians
     */
    public double roll() {
        final double sinr = 2 * (w() * z() + x() * y());
        final double cosr = 1 - 2 * (y() * y() + z() * z());
        return Math.atan2(sinr, cosr);
    }

    /**
     * Calculates the axis of rotation represented by this quaternion.
     * If this quaternion represents no rotation, this returns {@link Vector3#ZERO}.
     *
     * @return The axis of rotation represented by this quaternion
     */
    @Nonnull
    public Vector3 axis() {
        if (Math.abs(w()) >= 1.0) {
            return Vector3.ZERO;
        }

        final double factor = 1.0 / Math.sqrt(1 - w() * w());
        return new Vector3(x() * factor, y() * factor, z() * factor).normalize();
    }

    /**
     * Calculates the angle in radians of rotation represented by this quaternion.
     * If this quaternion represents no rotation, this returns {@code 0}.
     *
     * @return The angle in radians of rotation represented by this quaternion
     */
    public double angle() {
        if (Math.abs(w()) >= 1.0) {
            return 0;
        }

        return 2 * Math.acos(w());
    }

    //
    // Arithmetic
    //

    /**
     * Adds a scalar to this quaternion.
     *
     * @param s Scalar to add to this quaternion
     * @return The resulting quaternion
     */
    @Nonnull
    @Override
    public Quaternion add(double s) {
        return new Quaternion(super.add(s));
    }

    /**
     * Subtracts a scalar from this quaternion.
     *
     * @param s Scalar to subtract from this quaternion
     * @return The resulting quaternion
     */
    @Nonnull
    @Override
    public Quaternion subtract(double s) {
        return new Quaternion(super.subtract(s));
    }

    /**
     * Multiplies this quaternion by a scalar.
     *
     * @param s Scalar to multiply with this quaternion
     * @return The resulting quaternion
     */
    @Nonnull
    @Override
    public Quaternion multiply(double s) {
        return new Quaternion(super.multiply(s));
    }

    /**
     * Divides this quaternion by a scalar.
     *
     * @param s Scalar to divide this quaternion by
     * @return The resulting quaternion
     * @throws ArithmeticException When the denominator {@code s == 0}
     */
    @Nonnull
    @Override
    public Quaternion divide(double s) throws ArithmeticException {
        return new Quaternion(super.divide(s));
    }

    /**
     * Adds a vector to this quaternion.
     *
     * @param v Vector to add to this quaternion
     * @return The resulting quaternion
     */
    @Nonnull
    @Override
    public Quaternion add(@Nonnull Vector4 v) {
        return new Quaternion(super.add(v));
    }

    /**
     * Subtracts a vector from this quaternion.
     *
     * @param v Vector to subtract from this quaternion
     * @return The resulting quaternion
     */
    @Nonnull
    @Override
    public Quaternion subtract(@Nonnull Vector4 v) {
        return new Quaternion(super.subtract(v));
    }

    /**
     * Given this quaternion is a rotation quaternion (a quaternion with a magnitude of {@code 1}),
     * this scales the rotation of the quaternion by the provided scalar.
     *
     * @param s Scalar to scale this quaternion by
     * @return Scaled quaternion
     */
    @Nonnull
    public Quaternion scale(double s) {
        // No need to scale identity quaternions
        if (w() == 1) return IDENTITY;

        final double acos = Math.acos(w());
        return new Quaternion(
                Math.cos(acos * s),
                vector().divide(Math.sin(acos)).multiply(Math.sin(acos * s))
        );
    }

    /**
     * Multiplies this quaternion by another quaternion.
     * Note that quaternion multiplication is non-commutative. (the order of the operation matters)
     * This is an operation where {@code this} is on the left, and {@code q} is on the right.
     *
     * @param q The quaternion to multiply this quaternion by
     * @return The left-product of the two quaternions
     */
    @Nonnull
    public Quaternion multiply(@Nonnull Quaternion q) {
        final double s = w() * q.w() - vector().dot(q.vector());
        final Vector3 v = vector().cross(q.vector())
                .add(vector().multiply(q.w()))
                .add(q.vector().multiply(w()));

        return new Quaternion(s, v);
    }

    //
    // Utility
    //

    /**
     * Returns the conjugate of this quaternion.
     *
     * @return The conjugate of this quaternion
     */
    @Nonnull
    public Quaternion conjugate() {
        return new Quaternion(w(), -x(), -y(), -z());
    }

    /**
     * Returns the inverse of this quaternion.
     *
     * @return The inverse of this quaternion
     * @throws UnsupportedOperationException When the magnitude of {@code this} is equal to {@code 0}
     */
    @Nonnull
    public Quaternion inverse() throws UnsupportedOperationException {
        final double m2 = magnitude2();
        if (m2 == 0) {
            throw new UnsupportedOperationException("An inverse cannot be derived from a quaternion with zero magnitude.");
        }

        return divide(m2);
    }

    /**
     * Converts this quaternion into a builder for modification.
     *
     * @return Builder constructed from this quaternion
     */
    @Nonnull
    public Builder toBuilder() {
        return new Builder(this);
    }

    //
    // Serialization
    //

    /**
     * Parses a string into a {@link Quaternion}.
     *
     * @param input String to parse into a quaternion
     * @return The parsed string
     * @throws NumberFormatException    When the format is invalid
     * @throws IllegalArgumentException When at least one of ths components is non-finite
     */
    @Nonnull
    public static Quaternion parseQuaternion(@Nonnull String input) throws IllegalArgumentException {
        return new Quaternion(Vector4.parseVector(input.replaceAll("Quaternion", "Vector4")));
    }

    /**
     * Serializes this quaternion into a string.
     *
     * @return The string representation of this quaternion
     */
    @Nonnull
    @Override
    public String toString() {
        return super.toString().replaceAll("Vector4", "Quaternion");
    }

    //
    // Builder
    //

    /**
     * Constructs a new quaternion from a {@link Builder}.
     *
     * @param builder Builder to use
     */
    private Quaternion(@Nonnull Builder builder) {
        this(builder.w, builder.x, builder.y, builder.z);
    }

    /**
     * The builder class for {@link Quaternion}.
     */
    public static final class Builder {
        /**
         * Initializes the building sequence.
         */
        private Builder() {
            this.w = 1;
            this.x = 0;
            this.y = 0;
            this.z = 0;
        }

        /**
         * Initializes the building sequence.
         *
         * @param q Quaternion to use as an initial state
         */
        private Builder(@Nonnull Quaternion q) {
            this.w = q.w();
            this.x = q.x();
            this.y = q.y();
            this.z = q.z();
        }

        private double w, x, y, z;

        /**
         * Sets the W component of this quaternion.
         *
         * @param w The W component to set to
         * @return {@code this}
         */
        @Nonnull
        public Builder w(double w) {
            this.w = w;
            return this;
        }

        /**
         * Sets the X component of this quaternion.
         *
         * @param x The X component to set to
         * @return {@code this}
         */
        @Nonnull
        public Builder x(double x) {
            this.x = x;
            return this;
        }

        /**
         * Sets the Y component of this quaternion.
         *
         * @param y The Y component to set to
         * @return {@code this}
         */
        @Nonnull
        public Builder y(double y) {
            this.y = y;
            return this;
        }

        /**
         * Sets the Z component of this quaternion.
         *
         * @param z The Z component to set to
         * @return {@code this}
         */
        @Nonnull
        public Builder z(double z) {
            this.z = z;
            return this;
        }

        /**
         * Sets the vector part of this quaternion.
         *
         * @param x The X component to set to
         * @param y The Y component to set to
         * @param z The Z component to set to
         * @return {@code this}
         */
        @Nonnull
        public Builder xyz(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
            return this;
        }

        /**
         * Sets the vector part of this quaternion.
         *
         * @param v The vector to use
         * @return {@code this}
         */
        @Nonnull
        public Builder xyz(@Nonnull Vector3 v) {
            this.x = v.x();
            this.y = v.y();
            this.z = v.z();
            return this;
        }

        /**
         * Rotates this quaternion by given rotation.
         *
         * @param rq Rotation to apply to this builder instance
         * @return {@code this}
         */
        @Nonnull
        public Builder rotate(@Nonnull Quaternion rq) {
            final Quaternion current = build();
            final Quaternion result = rq.multiply(current);

            this.w = result.w();
            this.x = result.x();
            this.y = result.y();
            this.z = result.z();

            return this;
        }

        /**
         * Adds pitch (counter-clockwise rotation along positive X) to this rotation.
         *
         * @param rads Angle of pitch in radians
         * @return {@code this}
         */
        @Nonnull
        public Builder pitch(double rads) {
            final double halfAngle = rads * 0.5;
            final double sinHalfAngle = Math.sin(halfAngle);
            final double cosHalfAngle = Math.cos(halfAngle);

            final Quaternion current = build();
            final Quaternion input = new Quaternion(cosHalfAngle, sinHalfAngle, 0, 0);
            final Quaternion result = input.multiply(current);

            this.w = result.w();
            this.x = result.x();
            this.y = result.y();
            this.z = result.z();

            return this;
        }

        /**
         * Adds yaw (counter-clockwise rotation along positive Y) to this rotation.
         *
         * @param rads Angle of yaw in radians
         * @return {@code this}
         */
        @Nonnull
        public Builder yaw(double rads) {
            final double halfAngle = rads * 0.5;
            final double sinHalfAngle = Math.sin(halfAngle);
            final double cosHalfAngle = Math.cos(halfAngle);

            final Quaternion current = build();
            final Quaternion input = new Quaternion(cosHalfAngle, 0, sinHalfAngle, 0);
            final Quaternion result = input.multiply(current);

            this.w = result.w();
            this.x = result.x();
            this.y = result.y();
            this.z = result.z();

            return this;
        }

        /**
         * Adds roll (counter-clockwise rotation along positive Z) to this rotation.
         *
         * @param rads Angle of roll in radians
         * @return {@code this}
         */
        @Nonnull
        public Builder roll(double rads) {
            final double halfAngle = rads * 0.5;
            final double sinHalfAngle = Math.sin(halfAngle);
            final double cosHalfAngle = Math.cos(halfAngle);

            final Quaternion current = build();
            final Quaternion input = new Quaternion(cosHalfAngle, 0, 0, sinHalfAngle);
            final Quaternion result = input.multiply(current);

            this.w = result.w();
            this.x = result.x();
            this.y = result.y();
            this.z = result.z();

            return this;
        }

        /**
         * Adds pitch to this rotation.
         *
         * @param deg Angle of pitch in degrees
         * @return {@code this}
         */
        @Nonnull
        public Builder pitchDegrees(double deg) {
            return pitch(Math.toRadians(deg));
        }

        /**
         * Adds yaw to this rotation.
         *
         * @param deg Angle of yaw in degrees
         * @return {@code this}
         */
        @Nonnull
        public Builder yawDegrees(double deg) {
            return yaw(Math.toRadians(deg));
        }

        /**
         * Adds roll to this rotation.
         *
         * @param deg Angle of roll in degrees
         * @return {@code this}
         */
        @Nonnull
        public Builder rollDegrees(double deg) {
            return roll(Math.toRadians(deg));
        }

        /**
         * Finalizes the building sequence and builds the quaternion.
         *
         * @return Built quaternion
         */
        @Nonnull
        public Quaternion build() {
            return new Quaternion(this);
        }
    }
}
