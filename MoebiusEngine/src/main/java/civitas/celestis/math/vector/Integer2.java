package civitas.celestis.math.vector;

import civitas.celestis.math.DecimalMath;
import civitas.celestis.math.IntegerMath;
import civitas.celestis.math.Numbers;
import civitas.celestis.util.tuple.Pair;
import civitas.celestis.util.tuple.Tuple;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Integer2 extends Pair<BigInteger> implements IntegerVector<Integer2> {
    //
    // Constants
    //

    /**
     * A vector with no direction or magnitude. Represents origin.
     */
    public static final Integer2 ZERO = new Integer2(BigInteger.ZERO, BigInteger.ZERO);

    /**
     * The positive A unit vector.
     */
    public static final Integer2 POSITIVE_A = new Integer2(BigInteger.ONE, BigInteger.ZERO);

    /**
     * The positive B unit vector.
     */
    public static final Integer2 POSITIVE_B = new Integer2(BigInteger.ZERO, BigInteger.ONE);

    /**
     * The negative A unit vector.
     */
    public static final Integer2 NEGATIVE_A = new Integer2(BigInteger.ONE.negate(), BigInteger.ZERO);

    /**
     * The negative B unit vector.
     */
    public static final Integer2 NEGATIVE_B = new Integer2(BigInteger.ZERO, BigInteger.ONE.negate());


    //
    // Constructors
    //

    /**
     * Creates a new vector.
     *
     * @param a The A component of this vector
     * @param b The B component of this vector
     */
    public Integer2(@Nonnull BigInteger a, @Nonnull BigInteger b) {
        super(a, b);
    }

    /**
     * Creates a new vector.
     *
     * @param values An array containing the components of this vector in AB order
     */
    public Integer2(@Nonnull BigInteger[] values) {
        super(values[0], values[1]);
    }

    /**
     * Creates a new vector.
     *
     * @param t The tuple of which to copy component values from
     */
    public Integer2(@Nonnull Tuple<BigInteger, ?> t) {
        super(t);
    }

    /**
     * Creates a new vector.
     *
     * @param p The pair of which to copy component values from
     */
    public Integer2(@Nonnull Pair<BigInteger> p) {
        super(p);
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     */
    public Integer2(@Nonnull IntegerVector<?> v) {
        super(v);
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     */
    public Integer2(@Nonnull Double2 v) {
        super(BigDecimal.valueOf(v.x).toBigInteger(), BigDecimal.valueOf(v.y).toBigInteger());
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     */
    public Integer2(@Nonnull Float2 v) {
        super(BigDecimal.valueOf(v.x).toBigInteger(), BigDecimal.valueOf(v.y).toBigInteger());
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     */
    public Integer2(@Nonnull Long2 v) {
        super(BigInteger.valueOf(v.x), BigInteger.valueOf(v.y));
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector of which to copy component values from
     */
    public Integer2(@Nonnull Int2 v) {
        super(BigInteger.valueOf(v.x), BigInteger.valueOf(v.y));
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
    public BigInteger[] array() {
        return new BigInteger[]{a, b};
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
        return a.equals(BigInteger.ZERO) && b.equals(BigInteger.ZERO);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public BigDecimal norm() {
        return new BigDecimal(a.multiply(a).add(b.multiply(b))).sqrt(DecimalMath.RUNTIME_CONTEXT);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public BigInteger norm2() {
        return a.multiply(a).add(b.multiply(b));
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public BigInteger normManhattan() {
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
    public Integer2 add(@Nonnull BigInteger n) {
        return new Integer2(a.add(n), b.add(n));
    }

    /**
     * {@inheritDoc}
     *
     * @param n The scalar to subtract from this vector
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Integer2 subtract(@Nonnull BigInteger n) {
        return new Integer2(a.subtract(n), b.subtract(n));
    }

    /**
     * {@inheritDoc}
     *
     * @param n The scalar to multiply this vector by
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Integer2 multiply(@Nonnull BigInteger n) {
        return new Integer2(a.multiply(n), b.multiply(n));
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
    public Integer2 divide(@Nonnull BigInteger n) throws ArithmeticException {
        return new Integer2(a.divide(n), b.divide(n));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to add to this vector
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Integer2 add(@Nonnull Integer2 v) {
        return new Integer2(a.add(v.a), b.add(v.b));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to subtract from this vector
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Integer2 subtract(@Nonnull Integer2 v) {
        return new Integer2(a.subtract(v.a), b.subtract(v.b));
    }

    /**
     * Multiplies this vector by another vector using complex number multiplication.
     *
     * @param v The vector to multiply this vector by
     * @return The complex number product between the two vectors
     */
    @Nonnull
    public Integer2 multiply(@Nonnull Integer2 v) {
        return new Integer2(
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
    public BigInteger dot(@Nonnull Integer2 v) {
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
    public Integer2 transform(@Nonnull Function<? super BigInteger, BigInteger> f) {
        return new Integer2(f.apply(a), f.apply(b));
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
        return new Decimal2(
                new BigDecimal(a).divide(n, DecimalMath.RUNTIME_CONTEXT),
                new BigDecimal(b).divide(n, DecimalMath.RUNTIME_CONTEXT)
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
    public Integer2 negate() {
        return new Integer2(a.negate(), b.negate());
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
    public BigDecimal distance(@Nonnull Integer2 v) {
        final BigDecimal da = new BigDecimal(a.subtract(v.a));
        final BigDecimal db = new BigDecimal(b.subtract(v.b));

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
    public BigInteger distance2(@Nonnull Integer2 v) {
        final BigInteger da = a.subtract(v.a);
        final BigInteger db = b.subtract(v.b);

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
    public BigInteger distanceManhattan(@Nonnull Integer2 v) {
        final BigInteger da = a.subtract(v.a);
        final BigInteger db = b.subtract(v.b);

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
    public Integer2 min(@Nonnull Integer2 v) {
        return new Integer2(a.min(v.a), b.min(v.b));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The boundary vector of which to compare to
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Integer2 max(@Nonnull Integer2 v) {
        return new Integer2(a.max(v.a), b.max(v.b));
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
    public Integer2 clamp(@Nonnull Integer2 min, @Nonnull Integer2 max) {
        return new Integer2(IntegerMath.clamp(a, min.a, max.a), IntegerMath.clamp(b, min.b, max.b));
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
    public Integer2 rotate(double angRads) {
        final BigDecimal da = new BigDecimal(a);
        final BigDecimal db = new BigDecimal(b);

        final BigDecimal cos = BigDecimal.valueOf(Math.cos(angRads));
        final BigDecimal sin = BigDecimal.valueOf(Math.sin(angRads));

        return new Integer2(
                cos.multiply(da).subtract(sin.multiply(db)).toBigInteger(),
                sin.multiply(da).add(cos.multiply(db)).toBigInteger()
        );
    }

    //
    // Primitive Conversion
    //

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Double2 toDouble() {
        return new Double2(a.doubleValue(), b.doubleValue());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Float2 toFloat() {
        return new Float2(a.floatValue(), b.floatValue());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Long2 toLong() {
        return new Long2(a.longValue(), b.longValue());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public Int2 toInt() {
        return new Int2(a.intValue(), b.intValue());
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
        if (obj instanceof Integer2 iv2) {
            return a.equals(iv2.a) && b.equals(iv2.b);
        }

        if (obj instanceof IntegerVector<?> iv) {
            final BigInteger[] array = iv.array();
            return Arrays.equals(array(), array);
        }

        if (obj instanceof Vector<?, ?> v) {
            final List<BigInteger> l1 = list();
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
    public boolean equals(@Nonnull Integer2 v) {
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
    public static Integer2 parseVector(@Nonnull String input) throws NumberFormatException {
        if (!input.startsWith("Vector{")) {
            throw new NumberFormatException("The provided string is not a vector.");
        }

        final String[] valueStrings = input.replaceAll("Vector\\{|}", "").split(", ");
        if (valueStrings.length != 2) {
            throw new NumberFormatException("The provided string does not have teo components.");
        }

        final BigInteger[] values = new BigInteger[2];

        for (final String valueString : valueStrings) {
            final String[] split = valueString.split("=");
            if (split.length != 2) {
                throw new NumberFormatException("The format is invalid.");
            }

            values[switch (split[0]) {
                case "x" -> 0;
                case "y" -> 1;
                default -> throw new NumberFormatException("The provided string has a non-XY component.");
            }] = new BigInteger(split[1]);
        }

        return new Integer2(values);
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
