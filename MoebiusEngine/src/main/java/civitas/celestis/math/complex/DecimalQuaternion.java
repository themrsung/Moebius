package civitas.celestis.math.complex;

import civitas.celestis.math.DecimalMath;
import civitas.celestis.math.vector.Decimal3;
import civitas.celestis.math.vector.Decimal4;
import civitas.celestis.util.tuple.Quad;
import civitas.celestis.util.tuple.Tuple;
import jakarta.annotation.Nonnull;

import java.math.BigDecimal;

/**
 * A quaternion is used to represent the rotation of a three-dimensional vector.
 */
public class DecimalQuaternion extends Decimal4 {
    //
    // Constants
    //

    /**
     * The identity quaternion. This represents no rotation.
     */
    public static final DecimalQuaternion IDENTITY =
            new DecimalQuaternion(BigDecimal.ONE, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);

    //
    // Constructors
    //

    /**
     * Creates a new quaternion.
     *
     * @param a The A component of this quaternion
     * @param b The B component of this quaternion
     * @param c The C component of this quaternion
     * @param d The D component of this quaternion
     */
    public DecimalQuaternion(
            @Nonnull BigDecimal a,
            @Nonnull BigDecimal b,
            @Nonnull BigDecimal c,
            @Nonnull BigDecimal d
    ) {
        super(a, b, c, d);
    }

    /**
     * Creates a new quaternion.
     *
     * @param s The scalar part of this quaternion
     * @param v The vector part of this quaternion
     */
    public DecimalQuaternion(@Nonnull BigDecimal s, @Nonnull Decimal3 v) {
        super(s, v.getA(), v.getB(), v.getC());
    }

    /**
     * Creates a new quaternion.
     *
     * @param values An array containing the ABCD components of this quaternion
     */
    public DecimalQuaternion(@Nonnull BigDecimal[] values) {
        super(values);
    }

    /**
     * Creates a new quaternion.
     *
     * @param q The quad of which to copy component values from
     */
    public DecimalQuaternion(@Nonnull Quad<BigDecimal> q) {
        super(q);
    }

    /**
     * Creates a new quaternion.
     *
     * @param v The vector of which to copy component values from
     */
    public DecimalQuaternion(@Nonnull Decimal4 v) {
        super((Tuple<BigDecimal, ?>) v);
    }

    //
    // Getters
    //

    /**
     * Returns the scalar part of this quaternion.
     *
     * @return The scalar part of this quaternion
     */
    @Nonnull
    public final BigDecimal scalar() {
        return a;
    }

    /**
     * Returns the vector part of this quaternion.
     *
     * @return The vector part of this quaternion
     */
    @Nonnull
    public final Decimal3 vector() {
        return new Decimal3(b, c, d);
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
    public DecimalQuaternion conjugate() {
        return new DecimalQuaternion(a, b.negate(), c.negate(), d.negate());
    }

    /**
     * Returns the inverse of this quaternion.
     * Note that for rotation quaternions, using {@link Quaternion#conjugate()} is faster.
     *
     * @return The inverse of this quaternion
     */
    @Nonnull
    public DecimalQuaternion inverse() {
        final BigDecimal n2 = norm2();
        if (n2.equals(BigDecimal.ZERO)) {

            //
            // Unlike primitive quaternion types, this throws an exception
            // instead of using identity as the fallback value.
            //
            // Decimal quaternions are designed in favor of mathematical accuracy over ease of use.
            //

            throw new ArithmeticException("Cannot derive the inverse of a quaternion where the squared Euclidean norm is zero.");
        }

        return conjugate().divide(n2);
    }

    //
    // Arithmetic
    //

    /**
     * {@inheritDoc}
     *
     * @param n The scalar to add to this vector
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public DecimalQuaternion add(@Nonnull BigDecimal n) {
        return new DecimalQuaternion(a.add(n), b.add(n), c.add(n), d.add(n));
    }

    /**
     * {@inheritDoc}
     *
     * @param n The scalar to subtract from this vector
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public DecimalQuaternion subtract(@Nonnull BigDecimal n) {
        return new DecimalQuaternion(a.subtract(n), b.subtract(n), c.subtract(n), d.subtract(n));
    }

    /**
     * {@inheritDoc}
     *
     * @param n The scalar to multiply this vector by
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public DecimalQuaternion multiply(@Nonnull BigDecimal n) {
        return new DecimalQuaternion(a.multiply(n), b.multiply(n), c.multiply(n), d.multiply(n));
    }

    /**
     * {@inheritDoc}
     *
     * @param n The scalar to divide this vector by
     * @return {@inheritDoc}
     * @throws ArithmeticException {@inheritDoc}
     */
    @Nonnull
    @Override
    public DecimalQuaternion divide(@Nonnull BigDecimal n) throws ArithmeticException {
        return new DecimalQuaternion(
                a.divide(n, DecimalMath.RUNTIME_CONTEXT),
                b.divide(n, DecimalMath.RUNTIME_CONTEXT),
                c.divide(n, DecimalMath.RUNTIME_CONTEXT),
                d.divide(n, DecimalMath.RUNTIME_CONTEXT)
        );
    }

    /**
     * Multiplies this quaternion by another quaternion.
     *
     * @param q The quaternion of which to multiply this quaternion by
     * @return The left-product of the two quaternions
     */
    @Nonnull
    public DecimalQuaternion multiply(@Nonnull DecimalQuaternion q) {
        return new DecimalQuaternion(
                a.multiply(q.a).subtract(b.multiply(q.b)).subtract(c.multiply(q.c)).subtract(d.multiply(q.d)),
                a.multiply(q.b).add(b.multiply(q.a)).add(c.multiply(q.d)).subtract(d.multiply(q.c)),
                a.multiply(q.c).subtract(b.multiply(q.d)).add(c.multiply(q.a)).add(d.multiply(q.b)),
                a.multiply(q.d).add(b.multiply(q.c)).subtract(c.multiply(q.b)).add(d.multiply(q.a))
        );
    }


    /**
     * Scales the rotation of this quaternion.
     * This will only work properly if this quaternion is a rotation quaternion.
     * (a quaternion with a Euclidean norm of {@code 1})
     * <br>
     *
     * @param s The scale factor to apply to the rotation
     * @return The scaled quaternion
     */
    @Nonnull
    public DecimalQuaternion scale(double s) {
        if (a.equals(BigDecimal.ONE)) {
            return IDENTITY;
        }

        //
        // The following code will not work for non-rotation quaternions.
        // The type double does not have enough significant bits to represent the
        // theoretically infinite range of BigDecimal types.
        //

        final double halfAngle = Math.acos(a.doubleValue());
        final double newHalfAngle = halfAngle * s;

        final double sinHalfAngle = Math.sin(halfAngle);
        final double sinNewHalfAngle = Math.sin(newHalfAngle);

        final BigDecimal scaleFactor = BigDecimal.valueOf(sinNewHalfAngle / sinHalfAngle);

        return new DecimalQuaternion(
                BigDecimal.valueOf(Math.cos(newHalfAngle)),
                b.multiply(scaleFactor),
                c.multiply(scaleFactor),
                d.multiply(scaleFactor)
        );
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
    public static DecimalQuaternion parseQuaternion(@Nonnull String input) throws NumberFormatException {
        return new DecimalQuaternion(parseVector(input));
    }
}
