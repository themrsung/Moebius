package civitas.celestis.math.vector;

import civitas.celestis.math.DecimalMath;
import civitas.celestis.math.Numbers;
import civitas.celestis.util.tuple.Pair;
import civitas.celestis.util.tuple.Tuple;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Decimal2 extends Pair<BigDecimal> implements DecimalVector<Decimal2> {
    //
    // Constants
    //

    /**
     * A vector with no direction or magnitude. Represents origin.
     */
    public static final Decimal2 ZERO = new Decimal2(BigDecimal.ZERO, BigDecimal.ZERO);

    /**
     * The positive A unit vector.
     */
    public static final Decimal2 POSITIVE_A = new Decimal2(BigDecimal.ONE, BigDecimal.ZERO);

    /**
     * The positive B unit vector.
     */
    public static final Decimal2 POSITIVE_B = new Decimal2(BigDecimal.ZERO, BigDecimal.ONE);

    /**
     * The negative A unit vector.
     */
    public static final Decimal2 NEGATIVE_A = new Decimal2(BigDecimal.ONE.negate(), BigDecimal.ZERO);

    /**
     * The negative B unit vector.
     */
    public static final Decimal2 NEGATIVE_B = new Decimal2(BigDecimal.ZERO, BigDecimal.ONE.negate());


    //
    // Constructors
    //

    /**
     * Creates a new vector.
     *
     * @param a The A component of this vector
     * @param b The B component of this vector
     */
    public Decimal2(@Nonnull BigDecimal a, @Nonnull BigDecimal b) {
        super(a, b);
    }

    /**
     * Creates a new vector.
     *
     * @param values An array containing the components of this vector in AB order
     */
    public Decimal2(@Nonnull BigDecimal[] values) {
        super(values[0], values[1]);
    }

    /**
     * Creates a new vector.
     *
     * @param t The tuple of which to copy component values from
     */
    public Decimal2(@Nonnull Tuple<BigDecimal, ?> t) {
        super(t);
    }

    /**
     * Creates a new vector.
     *
     * @param p The pair of which to copy component values from
     */
    public Decimal2(@Nonnull Pair<BigDecimal> p) {
        super(p);
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     */
    public Decimal2(@Nonnull DecimalVector<?> v) {
        super(v);
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     */
    public Decimal2(@Nonnull Double2 v) {
        super(BigDecimal.valueOf(v.x), BigDecimal.valueOf(v.y));
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     */
    public Decimal2(@Nonnull Float2 v) {
        super(BigDecimal.valueOf(v.x), BigDecimal.valueOf(v.y));
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     */
    public Decimal2(@Nonnull Long2 v) {
        super(BigDecimal.valueOf(v.x), BigDecimal.valueOf(v.y));
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     */
    public Decimal2(@Nonnull Int2 v) {
        super(BigDecimal.valueOf(v.x), BigDecimal.valueOf(v.y));
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
        return new BigDecimal[]{a, b};
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
        return a.equals(BigDecimal.ZERO) && b.equals(BigDecimal.ZERO);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public BigDecimal norm() {
        return a.multiply(a).add(b.multiply(b)).sqrt(DecimalMath.RUNTIME_CONTEXT);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public BigDecimal norm2() {
        return a.multiply(a).add(b.multiply(b));
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public BigDecimal normManhattan() {
        return a.abs().add(b.abs());
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
    public Decimal2 add(@Nonnull BigDecimal n) {
        return new Decimal2(a.add(n), b.add(n));
    }

    /**
     * {@inheritDoc}
     *
     * @param n The scalar to subtract from this vector
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Decimal2 subtract(@Nonnull BigDecimal n) {
        return new Decimal2(a.subtract(n), b.subtract(n));
    }

    /**
     * {@inheritDoc}
     *
     * @param n The scalar to multiply this vector by
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Decimal2 multiply(@Nonnull BigDecimal n) {
        return new Decimal2(a.multiply(n), b.multiply(n));
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
    public Decimal2 divide(@Nonnull BigDecimal n) throws ArithmeticException {
        return new Decimal2(a.divide(n, DecimalMath.RUNTIME_CONTEXT), b.divide(n, DecimalMath.RUNTIME_CONTEXT));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to add to this vector
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Decimal2 add(@Nonnull Decimal2 v) {
        return new Decimal2(a.add(v.a), b.add(v.b));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to subtract from this vector
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Decimal2 subtract(@Nonnull Decimal2 v) {
        return new Decimal2(a.subtract(v.a), b.subtract(v.b));
    }

    /**
     * Multiplies this vector by another vector using complex number multiplication.
     *
     * @param v The vector to multiply this vector by
     * @return The complex number product between the two vectors
     */
    @Nonnull
    public Decimal2 multiply(@Nonnull Decimal2 v) {
        return new Decimal2(
                a.multiply(v.a).subtract(b.multiply(v.b)),
                a.multiply(v.b).add(b.multiply(v.a))
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
    public BigDecimal dot(@Nonnull Decimal2 v) {
        return a.multiply(v.a).add(b.multiply(v.b));
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
    public Decimal2 transform(@Nonnull Function<? super BigDecimal, BigDecimal> f) {
        return new Decimal2(f.apply(a), f.apply(b));
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
    public Decimal2 normalize() {
        final BigDecimal n = norm();
        return new Decimal2(a.divide(n, DecimalMath.RUNTIME_CONTEXT), b.divide(n, DecimalMath.RUNTIME_CONTEXT));
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
    public Decimal2 negate() {
        return new Decimal2(a.negate(), b.negate());
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
    public BigDecimal distance(@Nonnull Decimal2 v) {
        final BigDecimal da = a.subtract(v.a);
        final BigDecimal db = b.subtract(v.b);

        return da.multiply(da).add(db.multiply(db)).sqrt(DecimalMath.RUNTIME_CONTEXT);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the squared distance to
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public BigDecimal distance2(@Nonnull Decimal2 v) {
        final BigDecimal da = a.subtract(v.a);
        final BigDecimal db = b.subtract(v.b);

        return da.multiply(da).add(db.multiply(db));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the distance to
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public BigDecimal distanceManhattan(@Nonnull Decimal2 v) {
        final BigDecimal da = a.subtract(v.a);
        final BigDecimal db = b.subtract(v.b);

        return da.abs().add(db.abs());
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
    public Decimal2 min(@Nonnull Decimal2 v) {
        return new Decimal2(a.min(v.a), b.min(v.b));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The boundary vector of which to compare to
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Decimal2 max(@Nonnull Decimal2 v) {
        return new Decimal2(a.max(v.a), b.max(v.b));
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
    public Decimal2 clamp(@Nonnull Decimal2 min, @Nonnull Decimal2 max) {
        return new Decimal2(DecimalMath.clamp(a, min.a, max.a), DecimalMath.clamp(b, min.b, max.b));
    }

    //
    // Rotation
    //

    /**
     * Rotates this vector counter-clockwise by given angle.
     *
     * @param angRads The angle of rotation to apply in radians
     */
    @Nonnull
    public Decimal2 rotate(double angRads) {
        final BigDecimal cos = BigDecimal.valueOf(Math.cos(angRads));
        final BigDecimal sin = BigDecimal.valueOf(Math.sin(angRads));

        return new Decimal2(
                cos.multiply(a).subtract(sin.multiply(b)),
                sin.multiply(a).add(cos.multiply(b))
        );
    }

    //
    // Equality
    //

    /**
     * {@inheritDoc}
     *
     * @param obj The object to compare to
     * @return {@code true} if the object is either a vector or a tuple of length {@code 2},
     * and the component values are equal and are in the same order
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Decimal2 dv2) {
            return a.equals(dv2.a) && b.equals(dv2.b);
        }

        if (obj instanceof DecimalVector<?> dv) {
            final BigDecimal[] array = dv.array();
            return Arrays.equals(array(), array);
        }

        if (obj instanceof Vector<?, ?> v) {
            final List<BigDecimal> l1 = list();
            final List<? extends Number> l2 = v.list();

            if (l2.size() != 2) return false;

            for (int i = 0; i < 2; i++) {
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
    public boolean equals(@Nonnull Decimal2 v) {
        return a.equals(v.a) && b.equals(v.b);
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
    public static Decimal2 parseVector(@Nonnull String input) throws NumberFormatException {
        if (!input.startsWith("Vector{")) {
            throw new NumberFormatException("The provided string is not a vector.");
        }

        final String[] valueStrings = input.replaceAll("Vector\\{|}", "").split(", ");
        if (valueStrings.length != 2) {
            throw new NumberFormatException("The provided string does not have teo components.");
        }

        final BigDecimal[] values = new BigDecimal[2];

        for (final String valueString : valueStrings) {
            final String[] split = valueString.split("=");
            if (split.length != 2) {
                throw new NumberFormatException("The format is invalid.");
            }

            values[switch (split[0]) {
                case "x" -> 0;
                case "y" -> 1;
                default -> throw new NumberFormatException("The provided string has a non-XY component.");
            }] = new BigDecimal(split[1]);
        }

        return new Decimal2(values);
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
                '}';
    }

}
