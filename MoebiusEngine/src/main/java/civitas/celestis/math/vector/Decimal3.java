package civitas.celestis.math.vector;

import civitas.celestis.math.DecimalMath;
import civitas.celestis.math.Numbers;
import civitas.celestis.math.complex.DecimalQuaternion;
import civitas.celestis.util.tuple.Triple;
import civitas.celestis.util.tuple.Tuple;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * A three-dimensional vector which uses the type {@link BigDecimal}.
 */
public class Decimal3 extends Triple<BigDecimal> implements DecimalVector<Decimal3> {
    //
    // Constants
    //

    /**
     * A vector with no direction or magnitude. Represents origin.
     */
    public static final Decimal3 ZERO = new Decimal3(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);

    /**
     * The positive A unit vector.
     */
    public static final Decimal3 POSITIVE_A = new Decimal3(BigDecimal.ONE, BigDecimal.ZERO, BigDecimal.ZERO);

    /**
     * The positive B unit vector.
     */
    public static final Decimal3 POSITIVE_B = new Decimal3(BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.ZERO);

    /**
     * The positive C unit vector.
     */
    public static final Decimal3 POSITIVE_C = new Decimal3(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ONE);

    /**
     * The negative A unit vector.
     */
    public static final Decimal3 NEGATIVE_A = new Decimal3(BigDecimal.ONE.negate(), BigDecimal.ZERO, BigDecimal.ZERO);

    /**
     * The negative B unit vector.
     */
    public static final Decimal3 NEGATIVE_B = new Decimal3(BigDecimal.ZERO, BigDecimal.ONE.negate(), BigDecimal.ZERO);

    /**
     * The negative C unit vector.
     */
    public static final Decimal3 NEGATIVE_C = new Decimal3(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ONE.negate());

    //
    // Constructors
    //

    /**
     * Creates a new vector.
     *
     * @param a The A component of this vector
     * @param b The B component of this vector
     * @param c The C component of this vector
     */
    public Decimal3(@Nonnull BigDecimal a, @Nonnull BigDecimal b, @Nonnull BigDecimal c) {
        super(a, b, c);
    }

    /**
     * Creates a new vector.
     *
     * @param values An array containing the components of this vector in ABC order
     */
    public Decimal3(@Nonnull BigDecimal[] values) {
        super(values[0], values[1], values[2]);
    }

    /**
     * Creates a new vector.
     *
     * @param t The tuple of which to copy component values from
     */
    public Decimal3(@Nonnull Tuple<BigDecimal, ?> t) {
        super(t);
    }

    /**
     * Creates a new vector.
     *
     * @param t The triple of which to copy component values from
     */
    public Decimal3(@Nonnull Triple<BigDecimal> t) {
        super(t);
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     */
    public Decimal3(@Nonnull DecimalVector<?> v) {
        this(v.array());
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     */
    public Decimal3(@Nonnull Double3 v) {
        super(BigDecimal.valueOf(v.x), BigDecimal.valueOf(v.y), BigDecimal.valueOf(v.z));
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     */
    public Decimal3(@Nonnull Float3 v) {
        super(BigDecimal.valueOf(v.x), BigDecimal.valueOf(v.y), BigDecimal.valueOf(v.z));
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     */
    public Decimal3(@Nonnull Long3 v) {
        super(BigDecimal.valueOf(v.x), BigDecimal.valueOf(v.y), BigDecimal.valueOf(v.z));
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     */
    public Decimal3(@Nonnull Int3 v) {
        super(BigDecimal.valueOf(v.x), BigDecimal.valueOf(v.y), BigDecimal.valueOf(v.z));
    }

    //
    // Getters
    //

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public BigDecimal[] array() {
        return new BigDecimal[]{a, b, c};
    }

    //
    // Properties
    //

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public boolean isZero() {
        return a.equals(BigDecimal.ZERO) && b.equals(BigDecimal.ZERO) && c.equals(BigDecimal.ZERO);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public BigDecimal norm() {
        return norm2().sqrt(DecimalMath.RUNTIME_CONTEXT);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public BigDecimal norm2() {
        return a.multiply(a).add(b.multiply(b)).add(c.multiply(c));
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public BigDecimal normManhattan() {
        return a.abs().add(b.abs()).add(c.abs());
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
    public Decimal3 add(@Nonnull BigDecimal n) {
        return new Decimal3(a.add(n), b.add(n), c.add(n));
    }

    /**
     * {@inheritDoc}
     *
     * @param n The scalar to subtract from this vector
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Decimal3 subtract(@Nonnull BigDecimal n) {
        return new Decimal3(a.subtract(n), b.subtract(n), c.subtract(n));
    }

    /**
     * {@inheritDoc}
     *
     * @param n The scalar to multiply this vector by
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Decimal3 multiply(@Nonnull BigDecimal n) {
        return new Decimal3(a.multiply(n), b.multiply(n), c.multiply(n));
    }

    /**
     * {@inheritDoc}
     *
     * @param n The scalar to divide this vector by
     * @return {@inheritDoc}
     * @throws ArithmeticException When the denominator {@code n} is zero
     */
    @Nonnull
    @Override
    public Decimal3 divide(@Nonnull BigDecimal n) throws ArithmeticException {
        return new Decimal3(
                a.divide(n, DecimalMath.RUNTIME_CONTEXT),
                b.divide(n, DecimalMath.RUNTIME_CONTEXT),
                c.divide(n, DecimalMath.RUNTIME_CONTEXT)
        );
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to add to this vector
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Decimal3 add(@Nonnull Decimal3 v) {
        return new Decimal3(a.add(v.a), b.add(v.b), c.add(v.c));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to subtract from this vector
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Decimal3 subtract(@Nonnull Decimal3 v) {
        return new Decimal3(a.subtract(v.a), b.subtract(v.b), c.subtract(v.c));
    }

    /**
     * Returns the cross product between this vector and the provided vector {@code v}.
     *
     * @param v The vector to multiply this vector by
     * @return The cross product between the two vectors
     */
    @Nonnull
    public Decimal3 cross(@Nonnull Decimal3 v) {
        return new Decimal3(
                b.multiply(v.c).subtract(c.multiply(v.b)),
                c.multiply(v.a).subtract(a.multiply(v.c)),
                a.multiply(v.b).subtract(b.multiply(v.a))
        );
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the dot product between
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public BigDecimal dot(@Nonnull Decimal3 v) {
        return a.multiply(v.a).add(b.multiply(v.b)).add(c.multiply(v.c));
    }

    //
    // Transformation
    //

    /**
     * {@inheritDoc}
     *
     * @param f The function to apply to each component of this vector
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Decimal3 transform(@Nonnull Function<? super BigDecimal, BigDecimal> f) {
        return new Decimal3(f.apply(a), f.apply(b), f.apply(c));
    }

    //
    // Normalization
    //

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @throws ArithmeticException {@inheritDoc}
     */
    @Nonnull
    @Override
    public Decimal3 normalize() throws ArithmeticException {
        final BigDecimal n = norm();
        return new Decimal3(
                a.divide(n, DecimalMath.RUNTIME_CONTEXT),
                b.divide(n, DecimalMath.RUNTIME_CONTEXT),
                c.divide(n, DecimalMath.RUNTIME_CONTEXT)
        );
    }

    //
    // Negation
    //

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Decimal3 negate() {
        return new Decimal3(a.negate(), b.negate(), c.negate());
    }

    //
    // Distance
    //

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the distance to
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public BigDecimal distance(@Nonnull Decimal3 v) {
        final BigDecimal da = a.subtract(v.a);
        final BigDecimal db = b.subtract(v.b);
        final BigDecimal dc = c.subtract(v.c);

        return da.multiply(da).add(db.multiply(db)).add(dc.multiply(dc)).sqrt(DecimalMath.RUNTIME_CONTEXT);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the squared distance to
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public BigDecimal distance2(@Nonnull Decimal3 v) {
        final BigDecimal da = a.subtract(v.a);
        final BigDecimal db = b.subtract(v.b);
        final BigDecimal dc = c.subtract(v.c);

        return da.multiply(da).add(db.multiply(db)).add(dc.multiply(dc));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the distance to
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public BigDecimal distanceManhattan(@Nonnull Decimal3 v) {
        final BigDecimal da = a.subtract(v.a);
        final BigDecimal db = b.subtract(v.b);
        final BigDecimal dc = c.subtract(v.c);

        return da.abs().add(db.abs()).add(dc.abs());
    }

    //
    // Clamping
    //

    /**
     * {@inheritDoc}
     *
     * @param v The boundary vector of which to compare to
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Decimal3 min(@Nonnull Decimal3 v) {
        return new Decimal3(a.min(v.a), b.min(v.b), c.min(v.c));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The boundary vector of which to compare to
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Decimal3 max(@Nonnull Decimal3 v) {
        return new Decimal3(a.max(v.a), b.max(v.b), c.max(v.c));
    }

    /**
     * {@inheritDoc}
     *
     * @param min The minimum boundary vector
     * @param max The maximum boundary vector
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Decimal3 clamp(@Nonnull Decimal3 min, @Nonnull Decimal3 max) {
        return new Decimal3(
                DecimalMath.clamp(a, min.a, max.a),
                DecimalMath.clamp(b, min.b, max.b),
                DecimalMath.clamp(c, min.c, max.c)
        );
    }

    //
    // Rotation
    //

    /**
     * Converts this vector into a pure quaternion.
     * (a quaternion where the scalar part is {@code 0}, and the
     * vector part is populated with this vector).
     *
     * @return The pure quaternion representation of this vector
     */
    @Nonnull
    public DecimalQuaternion quaternion() {
        return new DecimalQuaternion(BigDecimal.ZERO, a, b, c);
    }

    /**
     * Rotates this vector by the provided rotation quaternion.
     *
     * @param rq The quaternion to rotate this vector by
     * @return The resulting vector
     */
    @Nonnull
    public Decimal3 rotate(@Nonnull DecimalQuaternion rq) {
        return rq.multiply(quaternion()).multiply(rq.conjugate()).vector();
    }

    //
    // Equality
    //

    /**
     * {@inheritDoc}
     *
     * @param obj The object to compare to
     * @return {@code true} if the object is either a vector or a tuple of length {@code 3},
     * and the component values are equal and are in the same order
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Decimal3 dv3) {
            return a.equals(dv3.a) && b.equals(dv3.b) && c.equals(dv3.c);
        }

        if (obj instanceof DecimalVector<?> dv) {
            final BigDecimal[] array = dv.array();
            return Arrays.equals(array(), array);
        }

        if (obj instanceof Vector<?, ?> v) {
            final List<BigDecimal> l1 = list();
            final List<? extends Number> l2 = v.list();

            if (l2.size() != 3) return false;

            for (int i = 0; i < 3; i++) {
                if (!Numbers.equals(l1.get(i), l2.get(i))) return false;
            }

            return true;
        }

        return super.equals(obj);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to compare to
     * @return {@inheritDoc}
     */
    @Override
    public boolean equals(@Nonnull Decimal3 v) {
        return a.equals(v.a) && b.equals(v.b) && c.equals(v.c);
    }

    //
    // Serialization
    //

    /**
     * Deserializes a string into a vector.
     *
     * @param input The input string to parse
     * @return The parsed vector
     * @throws NumberFormatException When the format is invalid
     */
    @Nonnull
    public static Decimal3 parseVector(@Nonnull String input) throws NumberFormatException {
        if (!input.startsWith("Vector{")) {
            throw new NumberFormatException("The provided string is not a vector.");
        }

        final String[] valueStrings = input.replaceAll("Vector\\{|}", "").split(", ");
        if (valueStrings.length != 3) {
            throw new NumberFormatException("The provided string does not have three components.");
        }

        final BigDecimal[] values = new BigDecimal[3];

        for (final String valueString : valueStrings) {
            final String[] split = valueString.split("=");
            if (split.length != 2) {
                throw new NumberFormatException("The format is invalid.");
            }

            values[switch (split[0]) {
                case "x" -> 0;
                case "y" -> 1;
                case "z" -> 2;
                default -> throw new NumberFormatException("The provided string has a non-XYZ component.");
            }] = new BigDecimal(split[1]);
        }

        return new Decimal3(values);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public String toString() {
        return "Vector{" +
                "x=" + a +
                ", y=" + b +
                ", z=" + c +
                '}';
    }
}
