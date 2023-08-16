package civitas.celestis.math.complex;

import civitas.celestis.math.Numbers;
import civitas.celestis.math.vector.Double3;
import civitas.celestis.math.vector.Double4;
import civitas.celestis.math.vector.DoubleVector;
import jakarta.annotation.Nonnull;

/**
 * A quaternion is used to represent the rotation of a three-dimensional vector.
 */
public class Quaternion extends Double4 {
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
    public Quaternion(double s, @Nonnull Double3 v) {
        super(s, v.x(), v.y(), v.z());
    }

    /**
     * Creates a new quaternion.
     *
     * @param values An array containing the WXYZ components o this quaternion
     */
    public Quaternion(@Nonnull double[] values) {
        super(values);
    }

    /**
     * Creates a new quaternion.
     *
     * @param v The vector of which to copy component values from
     */
    public Quaternion(@Nonnull DoubleVector<?> v) {
        super(v);
    }

    /**
     * Creates a new quaternion.
     *
     * @param v The vector of which to copy component values from
     */
    public Quaternion(@Nonnull Double4 v) {
        super(v);
    }

    //
    // Getters
    //

    /**
     * Returns the scalar part of this quaternion.
     *
     * @return The scalar part of this quaternion
     */
    public final double scalar() {
        return w;
    }

    /**
     * Returns the vector part of this quaternion.
     *
     * @return The vector part of this quaternion
     */
    @Nonnull
    public final Double3 vector() {
        return new Double3(x, y, z);
    }

    //
    // Properties
    //

    /**
     * Returns the conjugate of this quaternion.
     *
     * @return The conjugate of this quaternion
     */
    @Nonnull
    public Quaternion conjugate() {
        return new Quaternion(w, -x, -y, -z);
    }

    /**
     * Returns the inverse of this quaternion.
     * Note that for rotation quaternions, using {@link Quaternion#conjugate()} is faster.
     *
     * @return The inverse of this quaternion
     */
    @Nonnull
    public Quaternion inverse() {
        final double n2 = norm2();
        if (n2 == 0) {
            return IDENTITY;
        }

        return conjugate().divide(n2);
    }

    //
    // Arithmetic
    //

    /**
     * Adds a scalar to this quaternion.
     *
     * @param s The scalar to add to this quaternion
     * @return The resulting quaternion
     */
    @Nonnull
    @Override
    public Quaternion add(double s) {
        return new Quaternion(w + s, x + s, y + s, z + s);
    }

    /**
     * Subtracts a scalar from this quaternion.
     *
     * @param s The scalar to subtract from this quaternion
     * @return The resulting quaternion
     */
    @Nonnull
    @Override
    public Quaternion subtract(double s) {
        return new Quaternion(w - s, x - s, y - s, z - s);
    }

    /**
     * Multiplies this quaternion by a scalar.
     *
     * @param s The scalar to multiply this quaternion by
     * @return The resulting quaternion
     */
    @Nonnull
    @Override
    public Quaternion multiply(double s) {
        return new Quaternion(w * s, x * s, y * s, z * s);
    }

    /**
     * Divides this quaternion by a scalar.
     *
     * @param s The scalar to divide this quaternion by
     * @return The resulting quaternion
     */
    @Nonnull
    @Override
    public Quaternion divide(double s) {
        return new Quaternion(w / s, x / s, y / s, z / s);
    }

    /**
     * Multiplies this quaternion by another quaternion.
     *
     * @param q The quaternion of which to multiply this quaternion by
     * @return The left-product of the two quaternions
     */
    @Nonnull
    public Quaternion multiply(@Nonnull Quaternion q) {
        return new Quaternion(
                w * q.w - x * q.x - y * q.y - z * q.z,
                w * q.x + x * q.w + y * q.z - z * q.y,
                w * q.y - x * q.z + y * q.w + z * q.x,
                w * q.z + x * q.y - y * q.x + z * q.w
        );
    }

    /**
     * Scales the rotation of this quaternion.
     * This will only work properly if this quaternion is a rotation quaternion.
     * (a quaternion with a Euclidean norm of {@code 1})
     *
     * @param s The scale factor to apply to the rotation
     * @return The scaled quaternion
     */
    @Nonnull
    public Quaternion scale(double s) {
        if (Math.abs(w() - 1) < Numbers.EPSILON) {
            return IDENTITY;
        }

        final double halfAngle = Math.acos(w());
        final double newHalfAngle = halfAngle * s;

        final double sinHalfAngle = Math.sin(halfAngle);
        final double sinNewHalfAngle = Math.sin(newHalfAngle);

        final double scaleFactor = sinNewHalfAngle / sinHalfAngle;

        return new Quaternion(
                Math.cos(newHalfAngle),
                x * scaleFactor,
                y * scaleFactor,
                z * scaleFactor
        );
    }

    //
    // Negation
    //

    /**
     * Negates this quaternion.
     *
     * @return The negation of this quaternion
     */
    @Nonnull
    @Override
    public Quaternion negate() {
        return new Quaternion(-w, -x, -y, -z);
    }


    //
    // Serialization
    //

    /**
     * Deserializes a string into a quaternion.
     *
     * @param input The input string to parse
     * @return The parsed quaternion
     * @throws NumberFormatException When the format is invalid
     */
    @Nonnull
    public static Quaternion parseQuaternion(@Nonnull String input) throws NumberFormatException {
        return new Quaternion(parseVector(input));
    }
}
