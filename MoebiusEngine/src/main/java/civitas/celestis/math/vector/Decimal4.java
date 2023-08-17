package civitas.celestis.math.vector;

import civitas.celestis.math.DecimalMath;
import civitas.celestis.math.Numbers;
import civitas.celestis.util.tuple.Quad;
import civitas.celestis.util.tuple.Tuple;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Decimal4 extends Quad<BigDecimal> implements DecimalVector<Decimal4> {
    //
    // Constants
    //

    /**
     * A vector with no direction or magnitude. Represents origin.
     */
    public static final Decimal4 ZERO = new Decimal4(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);

    /**
     * The positive A unit vector.
     */
    public static final Decimal4 POSITIVE_A = new Decimal4(BigDecimal.ONE, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);

    /**
     * The positive B unit vector.
     */
    public static final Decimal4 POSITIVE_B = new Decimal4(BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.ZERO, BigDecimal.ZERO);

    /**
     * The positive C unit vector.
     */
    public static final Decimal4 POSITIVE_C = new Decimal4(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.ZERO);

    /**
     * The positive D unit vector.
     */
    public static final Decimal4 POSITIVE_D = new Decimal4(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ONE);

    /**
     * The negative A unit vector.
     */
    public static final Decimal4 NEGATIVE_A = new Decimal4(BigDecimal.ONE.negate(), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);

    /**
     * The negative B unit vector.
     */
    public static final Decimal4 NEGATIVE_B = new Decimal4(BigDecimal.ZERO, BigDecimal.ONE.negate(), BigDecimal.ZERO, BigDecimal.ZERO);

    /**
     * The negative C unit vector.
     */
    public static final Decimal4 NEGATIVE_C = new Decimal4(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ONE.negate(), BigDecimal.ZERO);

    /**
     * The negative D unit vector.
     */
    public static final Decimal4 NEGATIVE_D = new Decimal4(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ONE.negate());

    //
    // Constructors
    //

    /**
     * Creates a new vector.
     *
     * @param a The A component of this vector
     * @param b The B component of this vector
     * @param c The C component of this vector
     * @param d The D component of this vector
     */
    public Decimal4(@Nonnull BigDecimal a, @Nonnull BigDecimal b, @Nonnull BigDecimal c, @Nonnull BigDecimal d) {
        super(a, b, c, d);
    }

    /**
     * Creates a new vector.
     *
     * @param values An array containing the components of this vector in ABCD order
     */
    public Decimal4(@Nonnull BigDecimal[] values) {
        super(values[0], values[1], values[2], values[3]);
    }

    /**
     * Creates a new vector.
     *
     * @param t The tuple of which to copy component values from
     */
    public Decimal4(@Nonnull Tuple<BigDecimal, ?> t) {
        super(t);
    }

    /**
     * Creates a new vector.
     *
     * @param q The quad of which to copy component values from
     */
    public Decimal4(@Nonnull Quad<BigDecimal> q) {
        super(q);
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     */
    public Decimal4(@Nonnull Double4 v) {
        super(BigDecimal.valueOf(v.w), BigDecimal.valueOf(v.x), BigDecimal.valueOf(v.y), BigDecimal.valueOf(v.z));
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     */
    public Decimal4(@Nonnull Float4 v) {
        super(BigDecimal.valueOf(v.w), BigDecimal.valueOf(v.x), BigDecimal.valueOf(v.y), BigDecimal.valueOf(v.z));
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     */
    public Decimal4(@Nonnull Long4 v) {
        super(BigDecimal.valueOf(v.w), BigDecimal.valueOf(v.x), BigDecimal.valueOf(v.y), BigDecimal.valueOf(v.z));
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     */
    public Decimal4(@Nonnull Int4 v) {
        super(BigDecimal.valueOf(v.w), BigDecimal.valueOf(v.x), BigDecimal.valueOf(v.y), BigDecimal.valueOf(v.z));
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
        return new BigDecimal[]{a, b, c, d};
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
        return a.equals(BigDecimal.ZERO) &&
                b.equals(BigDecimal.ZERO) &&
                c.equals(BigDecimal.ZERO) &&
                d.equals(BigDecimal.ZERO);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public BigDecimal norm() {
        return a.multiply(a).add(b.multiply(b)).add(c.multiply(c)).add(d.multiply(d))
                .sqrt(DecimalMath.RUNTIME_CONTEXT);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public BigDecimal norm2() {
        return a.multiply(a).add(b.multiply(b)).add(c.multiply(c)).add(d.multiply(d));
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public BigDecimal normManhattan() {
        return a.abs().add(b.abs()).add(c.abs()).add(d.abs());
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
    public Decimal4 add(@Nonnull BigDecimal n) {
        return new Decimal4(a.add(n), b.add(n), c.add(n), d.add(n));
    }

    /**
     * {@inheritDoc}
     *
     * @param n The scalar to subtract from this vector
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Decimal4 subtract(@Nonnull BigDecimal n) {
        return new Decimal4(a.subtract(n), b.subtract(n), c.subtract(n), d.subtract(n));
    }

    /**
     * {@inheritDoc}
     *
     * @param n The scalar to multiply this vector by
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Decimal4 multiply(@Nonnull BigDecimal n) {
        return new Decimal4(a.multiply(n), b.multiply(n), c.multiply(n), d.multiply(n));
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
    public Decimal4 divide(@Nonnull BigDecimal n) throws ArithmeticException {
        return new Decimal4(
                a.divide(n, DecimalMath.RUNTIME_CONTEXT),
                b.divide(n, DecimalMath.RUNTIME_CONTEXT),
                c.divide(n, DecimalMath.RUNTIME_CONTEXT),
                d.divide(n, DecimalMath.RUNTIME_CONTEXT)
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
    public Decimal4 add(@Nonnull Decimal4 v) {
        return new Decimal4(a.add(v.a), b.add(v.b), c.add(v.c), d.add(v.d));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to subtract from this vector
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Decimal4 subtract(@Nonnull Decimal4 v) {
        return new Decimal4(a.subtract(v.a), b.subtract(v.b), c.subtract(v.c), d.subtract(v.c));
    }


    /**
     * Multiplies this vector by another vector using quaternion multiplication.
     *
     * @param v The vector to multiply this vector by
     * @return The quaternion left-product of the two vectors
     */
    @Nonnull
    public Decimal4 multiply(@Nonnull Decimal4 v) {
        return new Decimal4(
                a.multiply(v.a).subtract(b.multiply(v.b)).subtract(c.multiply(v.c)).subtract(d.multiply(v.d)),
                a.multiply(v.b).add(b.multiply(v.a)).add(c.multiply(v.d)).subtract(d.multiply(v.c)),
                a.multiply(v.c).subtract(b.multiply(v.d)).add(c.multiply(v.a)).add(d.multiply(v.b)),
                a.multiply(v.d).add(b.multiply(v.c)).subtract(c.multiply(v.b)).add(d.multiply(v.a))
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
    public BigDecimal dot(@Nonnull Decimal4 v) {
        return a.multiply(v.a).add(b.multiply(v.b)).add(c.multiply(v.c)).add(d.multiply(v.d));
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
    public Decimal4 transform(@Nonnull Function<? super BigDecimal, BigDecimal> f) {
        return new Decimal4(f.apply(a), f.apply(b), f.apply(c), f.apply(d));
    }

    //
    // Normalization
    //

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Decimal4 normalize() {
        final BigDecimal n = norm();
        return new Decimal4(
                a.divide(n, DecimalMath.RUNTIME_CONTEXT),
                b.divide(n, DecimalMath.RUNTIME_CONTEXT),
                c.divide(n, DecimalMath.RUNTIME_CONTEXT),
                d.divide(n, DecimalMath.RUNTIME_CONTEXT)
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
    public Decimal4 negate() {
        return new Decimal4(a.negate(), b.negate(), c.negate(), d.negate());
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
    public BigDecimal distance(@Nonnull Decimal4 v) {
        final BigDecimal da = a.subtract(v.a);
        final BigDecimal db = b.subtract(v.b);
        final BigDecimal dc = c.subtract(v.c);
        final BigDecimal dd = d.subtract(v.d);

        return da.multiply(da).add(db.multiply(db)).add(dc.multiply(dc)).add(dd.multiply(dd))
                .sqrt(DecimalMath.RUNTIME_CONTEXT);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the squared distance to
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public BigDecimal distance2(@Nonnull Decimal4 v) {
        final BigDecimal da = a.subtract(v.a);
        final BigDecimal db = b.subtract(v.b);
        final BigDecimal dc = c.subtract(v.c);
        final BigDecimal dd = d.subtract(v.d);

        return da.multiply(da).add(db.multiply(db)).add(dc.multiply(dc)).add(dd.multiply(dd));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the distance to
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public BigDecimal distanceManhattan(@Nonnull Decimal4 v) {
        final BigDecimal da = a.subtract(v.a);
        final BigDecimal db = b.subtract(v.b);
        final BigDecimal dc = c.subtract(v.c);
        final BigDecimal dd = d.subtract(v.d);

        return da.abs().add(db.abs()).add(dc.abs()).add(dd.abs());
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
    public Decimal4 min(@Nonnull Decimal4 v) {
        return new Decimal4(a.min(v.a), b.min(v.b), c.min(v.c), d.min(v.d));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The boundary vector of which to compare to
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Decimal4 max(@Nonnull Decimal4 v) {
        return new Decimal4(a.max(v.a), b.max(v.b), c.max(v.c), d.max(v.d));
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
    public Decimal4 clamp(@Nonnull Decimal4 min, @Nonnull Decimal4 max) {
        return new Decimal4(
                DecimalMath.clamp(a, min.a, max.a),
                DecimalMath.clamp(b, min.b, max.b),
                DecimalMath.clamp(c, min.c, max.c),
                DecimalMath.clamp(d, min.d, max.d)
        );
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
        if (obj instanceof Decimal4 dv4) {
            return a.equals(dv4.a) && b.equals(dv4.b) && c.equals(dv4.c) && d.equals(dv4.d);
        }

        if (obj instanceof DecimalVector<?> dv) {
            final BigDecimal[] array = dv.array();
            return Arrays.equals(array(), array);
        }

        if (obj instanceof Vector<?, ?> v) {
            final List<BigDecimal> l1 = list();
            final List<? extends Number> l2 = v.list();

            if (l2.size() != 4) return false;

            for (int i = 0; i < 4; i++) {
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
    public boolean equals(@Nonnull Decimal4 v) {
        return a.equals(v.a) && b.equals(v.b) && c.equals(v.c) && d.equals(v.d);
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
    public static Decimal4 parseVector(@Nonnull String input) throws NumberFormatException {
        if (!input.startsWith("Vector{")) {
            throw new NumberFormatException("The provided string is not a vector.");
        }

        final String[] valueStrings = input.replaceAll("Vector\\{|}", "").split(", ");
        if (valueStrings.length != 4) {
            throw new NumberFormatException("The provided string does not have three components.");
        }

        final BigDecimal[] values = new BigDecimal[4];

        for (final String valueString : valueStrings) {
            final String[] split = valueString.split("=");
            if (split.length != 2) {
                throw new NumberFormatException("The format is invalid.");
            }

            values[switch (split[0]) {
                case "w" -> 0;
                case "x" -> 1;
                case "y" -> 2;
                case "z" -> 3;
                default -> throw new NumberFormatException("The provided string has a non-XYZ component.");
            }] = new BigDecimal(split[1]);
        }

        return new Decimal4(values);
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
                "w=" + a +
                ", x=" + b +
                ", y=" + c +
                ", z=" + d +
                '}';
    }
}
