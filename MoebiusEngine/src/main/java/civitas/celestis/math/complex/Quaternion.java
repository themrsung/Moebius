package civitas.celestis.math.complex;

import civitas.celestis.math.vector.Vector3;
import civitas.celestis.math.vector.Vector4;
import jakarta.annotation.Nonnull;

/**
 * An immutable quaternion class. Mainly used to represent the rotation of a {@link Vector3}.
 */
public class Quaternion extends Vector4 {
    //
    // Constants
    //

    /**
     * A quaternion where all components are zero.
     */
    public static final Quaternion ZERO = new Quaternion(0, 0, 0, 0);

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
     * @param values An array of length {@code 3} which contains the component os this quaternion in WXYZ order
     */
    public Quaternion(@Nonnull double[] values) {
        super(values);
    }

    public Quaternion(@Nonnull Vector4 v) {
        super(v);
    }

    //
    // Properties
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
    public final Vector3 vector() {
        return new Vector3(x, y, z);
    }

    /**
     * Returns the conjugate of this quaternion.
     *
     * @return The conjugate of this quaternion
     */
    @Nonnull
    public final Quaternion conjugate() {
        return new Quaternion(w, -x, -y, -z);
    }

    /**
     * Returns the inverse of this quaternion.
     *
     * @return The inverse of this quaternion
     */
    @Nonnull
    public final Quaternion inverse() {
        final double n2 = norm2();
        if (n2 == 0) {
            throw new ArithmeticException("An inverse cannot be derived. This quaternion has zero magnitude.");
        } else if (n2 == 1) {
            return conjugate(); // For rotation quaternions, identity == conjugate
        }

        return new Quaternion(w / n2, -x / n2, -y / n2, -z / n2);
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
     * @param s The scalar to multiply to this quaternion
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
     * Performs quaternion multiplication where this quaternion is on the left,
     * and the provided quaternion {@code q} is on the right.
     *
     * @param q The quaternion on the right of this operation
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

    //
    // Normalization
    //

    /**
     * Normalizes this quaternion into a unit quaternion.
     * When the magnitude is zero, this returns the identity quaternion.
     *
     * @return The normalized quaternion of this quaternion
     */
    @Nonnull
    @Override
    public Quaternion normalize() {
        try {
            return new Quaternion(super.normalize());
        } catch (final ArithmeticException e) {
            return IDENTITY;
        }
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
                "w=" + w +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
