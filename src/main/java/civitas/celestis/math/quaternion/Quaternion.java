package civitas.celestis.math.quaternion;

import civitas.celestis.math.Numbers;
import civitas.celestis.math.vector.Vector;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.math.vector.Vector4;
import jakarta.annotation.Nonnull;

import java.util.function.UnaryOperator;

/**
 * A subclass of {@link Vector4}. Like {@code Vector4}, this is also immutable.
 * A quaternion is used to represent the rotation of a {@link Vector3}.
 */
public class Quaternion extends Vector4 {
    //
    // Constants
    //

    /**
     * The identity quaternion. In the context of rotation quaternions, this represents no rotation.
     */
    public static final Quaternion IDENTITY = new Quaternion(1, 0, 0, 0);

    //
    // Constructors
    //

    /**
     * Creates an identity quaternion.
     */
    public Quaternion() {
        super(IDENTITY);
    }

    /**
     * Creates a new quaternion.
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
     * Creates a new quaternion.
     *
     * @param s The scalar part of this quaternion
     * @param v The vector part of this quaternion
     */
    public Quaternion(double s, @Nonnull Vector3 v) {
        super(s, v.x(), v.y(), v.z());
    }

    /**
     * Creates a new quaternion.
     *
     * @param values The array of component scalars to construct this quaternion from
     */
    public Quaternion(@Nonnull double... values) {
        super(values);
    }

    /**
     * Creates a new quaternion.
     *
     * @param other The vector to copy values from
     */
    public Quaternion(@Nonnull Vector other) {
        super(other);
    }

    /**
     * Creates a new quaternion.
     *
     * @param other The vector to copy values from
     */
    public Quaternion(@Nonnull Vector4 other) {
        super(other);
    }

    //
    // Randomization
    //

    /**
     * Returns a random unit quaternion. (rotation quaternion)
     *
     * @return A random unit quaternion
     */
    @Nonnull
    public static Quaternion random() {
        return new Quaternion(
                Numbers.random(-1, 1),
                Numbers.random(-1, 1),
                Numbers.random(-1, 1),
                Numbers.random(-1, 1)
        ).normalize();
    }

    //
    // Properties
    //

    /**
     * Returns the vector part of this quaternion.
     *
     * @return A {@link Vector3} constructed from the components {@code x}, {@code y}, {@code z}.
     */
    @Nonnull
    public final Vector3 vector() {
        return new Vector3(x, y, z);
    }

    //
    // Axis/Angle
    //

    /**
     * Given that this quaternion is a rotation quaternion (a quaternion with a magnitude of {@code 1}),
     * this returns the axis of the rotation.
     *
     * @return The axis of the rotation
     */
    @Nonnull
    public Vector3 axis() {
        if (Math.abs(w) >= 1.0) {
            return Vector3.ZERO; // There is no rotation
        }

        final double factor = 1.0 / Math.sqrt(1 - w * w);
        return vector().multiply(factor).normalize();
    }

    /**
     * Given that this quaternion is a rotation quaternion (a quaternion with a magnitude of {@code 1}),
     * this returns the angle of the rotation in radians. The angle follows the right-hand-rule.
     *
     * @return The angle of the rotation
     */
    public double angle() {
        if (Math.abs(w()) > 1.0) {
            return 0; // There is no rotation
        }

        return 2 * Math.acos(w);
    }

    //
    // Euler Angles
    //

    /**
     * Given that this quaternion is a rotation quaternion (a quaternion with a magnitude of {@code 1}),
     * this returns the angle of pitch of this rotation in radians.
     * Pitch is defined as counter-clockwise rotation around the X axis.
     *
     * @return The angle of rotation along the X axis in radians
     */
    public double pitch() {
        final double sinp = 2 * (w * x + y * z);
        final double cosp = 1 - 2 * (x * x + y * y);
        return Math.atan2(sinp, cosp);
    }


    /**
     * Given that this quaternion is a rotation quaternion (a quaternion with a magnitude of {@code 1}),
     * this returns the angle of yaw of this rotation in radians.
     * Yaw is defined as counter-clockwise rotation around the Y axis.
     *
     * @return The angle of rotation along the Y axis in radians
     */
    public double yaw() {
        final double siny = 2 * (w * y - z * x);
        final double cosy = 1 - 2 * (y * y + z * z);
        return Math.atan2(siny, cosy);
    }

    /**
     * Given that this quaternion is a rotation quaternion (a quaternion with a magnitude of {@code 1}),
     * this returns the angle of roll of this rotation in radians.
     * Roll is defined as counter-clockwise rotation around the Z axis.
     *
     * @return The angle of rotation along the Z axis in radians
     */
    public double roll() {
        final double sinr = 2 * (w * z + x * y);
        final double cosr = 1 - 2 * (y * y + z * z);
        return Math.atan2(sinr, cosr);
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
     * Note that this will change the magnitude of this quaternion.
     * In order to scale rotation quaternions, use {@link Quaternion#scale(double)} instead.
     *
     * @param s Scalar to multiply this quaternion by
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
     * @param s Scalar to divide this vector by
     * @return The resulting quaternion
     * @throws ArithmeticException When the denominator {@code s} is zero
     */
    @Nonnull
    @Override
    public Quaternion divide(double s) throws ArithmeticException {
        return new Quaternion(super.divide(s));
    }

    /**
     * Adds a vector to this quaternion.
     *
     * @param v The vector to add to this quaternion
     * @return The resulting quaternion
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 4}
     */
    @Nonnull
    @Override
    public Quaternion add(@Nonnull Vector v) throws IllegalArgumentException {
        return new Quaternion(super.add(v));
    }

    /**
     * Subtracts a vector from this quaternion.
     *
     * @param v The vector to subtract from this quaternion
     * @return The resulting quaternion
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 4}
     */
    @Nonnull
    @Override
    public Quaternion subtract(@Nonnull Vector v) throws IllegalArgumentException {
        return new Quaternion(super.subtract(v));
    }

    /**
     * Multiplies this quaternion by a vector using quaternion left-multiplication.
     *
     * @param v The vector to multiply this quaternion by
     * @return The resulting left-product
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 4}
     */
    @Nonnull
    @Override
    public Quaternion multiply(@Nonnull Vector v) throws IllegalArgumentException {
        return new Quaternion(super.multiply(v));
    }

    /**
     * Adds a vector to this quaternion.
     *
     * @param v The vector to add to this quaternion
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
     * @param v The vector to subtract from this quaternion
     * @return The resulting quaternion
     */
    @Nonnull
    @Override
    public Quaternion subtract(@Nonnull Vector4 v) {
        return new Quaternion(super.subtract(v));
    }

    /**
     * Multiplies this quaternion by a vector using quaternion left-multiplication.
     *
     * @param v The vector to multiply this quaternion with
     * @return The resulting left-product
     */
    @Nonnull
    @Override
    public Quaternion multiply(@Nonnull Vector4 v) {
        return new Quaternion(super.multiply(v));
    }

    /**
     * Multiplies this quaternion by another quaternion using scalar-vector quaternion left-multiplication.
     *
     * @param q The quaternion to multiply this quaternion with
     * @return The resulting left-product
     */
    @Nonnull
    public Quaternion multiply(@Nonnull Quaternion q) {
        return new Quaternion(
                w * q.w - vector().dot(q.vector()),
                vector().cross(q.vector())
                        .add(vector().multiply(q.w))
                        .add(q.vector().multiply(w)));
    }

    /**
     * Given that this quaternion is a rotation quaternion (a quaternion with a magnitude of {@code 1}),
     * this scales the rotation of this quaternion by the provided factor {@code s}.
     *
     * @param s The scale factor to apply to this rotation quaternion
     * @return The scaled rotation quaternion
     */
    @Nonnull
    public Quaternion scale(double s) {
        // No need to scale identity quaternions
        if (w == 1) {
            return IDENTITY;
        }

        final double acos = Math.acos(w);
        return new Quaternion(Math.cos(acos * s), vector().divide(Math.sin(acos)).multiply(Math.sin(acos * 2)));
    }

    //
    // Clamping
    //

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the minimum vector of
     * @return The minimum quaternion
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 4}
     */
    @Nonnull
    @Override
    public Quaternion min(@Nonnull Vector v) throws IllegalArgumentException {
        return new Quaternion(super.min(v));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the maximum vector of
     * @return The maximum quaternion
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 4}
     */
    @Nonnull
    @Override
    public Quaternion max(@Nonnull Vector v) throws IllegalArgumentException {
        return new Quaternion(super.max(v));
    }

    /**
     * {@inheritDoc}
     *
     * @param min The minimum allowed values
     * @param max The maximum allowed values
     * @return The clamped quaternion
     * @throws IllegalArgumentException When the provided vectors {@code min} and {@code max}'s length is not {@code 4}
     */
    @Nonnull
    @Override
    public Quaternion clamp(@Nonnull Vector min, @Nonnull Vector max) throws IllegalArgumentException {
        return new Quaternion(super.clamp(min, max));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the minimum quaternion of
     * @return The minimum quaternion
     */
    @Nonnull
    @Override
    public Quaternion min(@Nonnull Vector4 v) {
        return new Quaternion(super.min(v));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the maximum quaternion of
     * @return The maximum quaternion
     */
    @Nonnull
    @Override
    public Quaternion max(@Nonnull Vector4 v) {
        return new Quaternion(super.max(v));
    }

    /**
     * {@inheritDoc}
     *
     * @param min The minimum allowed values
     * @param max The maximum allowed values
     * @return The clamped quaternion
     */
    @Nonnull
    @Override
    public Quaternion clamp(@Nonnull Vector4 min, @Nonnull Vector4 max) {
        return new Quaternion(super.clamp(min, max));
    }

    //
    // Utility
    //

    /**
     * Applies the given operator to all components of this quaternion.
     *
     * @param operator The operator to apply to each component of this quaternion
     * @return The resulting quaternion
     */
    @Nonnull
    @Override
    public Quaternion apply(@Nonnull UnaryOperator<Double> operator) {
        return new Quaternion(super.apply(operator));
    }

    /**
     * Normalizes this quaternion into a unit quaternion. (a rotation quaternion)
     * If normalization is not possible (the magnitude is zero), this will return {@link Quaternion#IDENTITY}.
     *
     * @return The normalized quaternion
     */
    @Nonnull
    @Override
    public Quaternion normalize() {
        try {
            return divide(magnitude());
        } catch (final ArithmeticException e) {
            return IDENTITY;
        }
    }

    /**
     * Negates this quaternion.
     *
     * @return The negation of this quaternion
     */
    @Nonnull
    @Override
    public Quaternion negate() {
        return new Quaternion(super.negate());
    }

    /**
     * Returns the conjugate of this quaternion.
     * For rotation quaternions, using this is preferred to {@link Quaternion#inverse()} as
     * it is less computationally intensive and for rotation quaternions, the inverse is equal to the conjugate.
     *
     * @return The conjugate of this quaternion
     */
    @Nonnull
    public Quaternion conjugate() {
        return new Quaternion(w, -x, -y, -z);
    }

    /**
     * Returns the inverse of this quaternion.
     *
     * @return The inverse of this quaternion
     * @throws UnsupportedOperationException When the quaternion's magnitude is exactly zero
     */
    @Nonnull
    public Quaternion inverse() throws UnsupportedOperationException {
        try {
            return divide(magnitude2());
        } catch (final ArithmeticException e) {
            throw new UnsupportedOperationException("An inverse cannot be derived from a quaternion with zero magnitude.");
        }
    }

    //
    // Serialization
    //


    /**
     * Deserializes a string into a {@link Quaternion}.
     *
     * @param input The input to deserialize
     * @return The parsed vector
     * @throws NumberFormatException    When the format is invalid
     * @throws IllegalArgumentException When at least one of the component scalars is non-finite
     */
    @Nonnull
    public static Quaternion parseQuaternion(@Nonnull String input) throws IllegalArgumentException {
        if (!input.startsWith("Quaternion{")) {
            throw new NumberFormatException("The provided string does not represent a Quaternion.");
        }

        final String cleanInput = input.replaceAll("Quaternion\\{|}", "");
        final String[] valueStrings = cleanInput.split(", ");
        final double[] values = {Double.NaN, Double.NaN, Double.NaN, Double.NaN};

        for (final String valueString : valueStrings) {
            final String[] split = valueString.split("=");
            values[switch (split[0]) {
                case "w" -> 0;
                case "x" -> 1;
                case "y" -> 2;
                case "z" -> 3;
                default -> throw new NumberFormatException("The provided string does not represent a Quaternion.");
            }] = Double.parseDouble(split[1]);
        }

        return new Quaternion(values);
    }

    /**
     * Serializes this quaternion into a string.
     *
     * @return The string representation of this quaternion
     */
    @Override
    @Nonnull
    public String toString() {
        return "Quaternion{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
