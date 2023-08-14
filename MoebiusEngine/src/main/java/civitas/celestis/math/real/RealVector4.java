package civitas.celestis.math.real;

import civitas.celestis.math.vector.Vector4;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.Objects;
import java.util.function.UnaryOperator;

/**
 * An immutable three-dimensional vector which uses {@link RealNumber} as its components.
 */
public class RealVector4 implements RealVector {
    //
    // Constants
    //

    /**
     * A vector with no direction or magnitude. Represents origin.
     */
    public static final RealVector4 ZERO = new RealVector4(RealNumber.ZERO, RealNumber.ZERO, RealNumber.ZERO, RealNumber.ZERO);

    /**
     * The minimum value a vector can have without being zero.
     */
    public static final RealVector4 MIN_VALUE = new RealVector4(RealNumber.MIN_VALUE, RealNumber.MIN_VALUE, RealNumber.MIN_VALUE, RealNumber.MIN_VALUE);

    /**
     * The maximum value a vector can have without being infinite.
     */
    public static final RealVector4 MAX_VALUE = new RealVector4(RealNumber.MAX_VALUE, RealNumber.MAX_VALUE, RealNumber.MAX_VALUE, RealNumber.MAX_VALUE);

    /**
     * The positive W unit vector.
     */
    public static final RealVector4 POSITIVE_W = new RealVector4(RealNumber.ONE, RealNumber.ONE, RealNumber.ZERO, RealNumber.ZERO);

    /**
     * The positive X unit vector.
     */
    public static final RealVector4 POSITIVE_X = new RealVector4(RealNumber.ZERO, RealNumber.ONE, RealNumber.ZERO, RealNumber.ZERO);

    /**
     * The positive Y unit vector.
     */
    public static final RealVector4 POSITIVE_Y = new RealVector4(RealNumber.ZERO, RealNumber.ZERO, RealNumber.ONE, RealNumber.ZERO);

    /**
     * The positive Z unit vector.
     */
    public static final RealVector4 POSITIVE_Z = new RealVector4(RealNumber.ZERO, RealNumber.ZERO, RealNumber.ZERO, RealNumber.ONE);

    /**
     * The negative W unit vector.
     */
    public static final RealVector4 NEGATIVE_W = new RealVector4(RealNumber.ONE.negate(), RealNumber.ZERO, RealNumber.ZERO, RealNumber.ZERO);

    /**
     * The negative X unit vector.
     */
    public static final RealVector4 NEGATIVE_X = new RealVector4(RealNumber.ZERO, RealNumber.ONE.negate(), RealNumber.ZERO, RealNumber.ZERO);

    /**
     * The negative Y unit vector.
     */
    public static final RealVector4 NEGATIVE_Y = new RealVector4(RealNumber.ZERO, RealNumber.ZERO, RealNumber.ONE.negate(), RealNumber.ZERO);

    /**
     * The negative Z unit vector.
     */
    public static final RealVector4 NEGATIVE_Z = new RealVector4(RealNumber.ZERO, RealNumber.ZERO, RealNumber.ZERO, RealNumber.ONE.negate());

    //
    // Constructors
    //

    /**
     * Creates a new vector.
     *
     * @param x The X component of this vector
     * @param y The Y component of this vector
     * @param z The Z component of this vector
     */
    public RealVector4(double w, double x, double y, double z) {
        this(new RealNumber(w), new RealNumber(x), new RealNumber(y), new RealNumber(z));
    }

    /**
     * Creates a new vector.
     *
     * @param x The X component of this vector
     * @param y The Y component of this vector
     * @param z The Z component of this vector
     */
    public RealVector4(@Nonnull RealNumber w, @Nonnull RealNumber x, @Nonnull RealNumber y, @Nonnull RealNumber z) {
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Creates a new vector.
     *
     * @param values An array containing the components of this vector
     */
    public RealVector4(@Nonnull RealNumber[] values) {
        if (values.length != 4) {
            throw new IllegalArgumentException("The provided array does not have a length of 4.");
        }

        this.w = Objects.requireNonNull(values[0]);
        this.x = Objects.requireNonNull(values[1]);
        this.y = Objects.requireNonNull(values[2]);
        this.z = Objects.requireNonNull(values[3]);
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector to copy values from
     */
    public RealVector4(@Nonnull RealVector v) {
        if (v.length() != 4) {
            throw new IllegalArgumentException("The provided vector does not have a length of 4.");
        }

        this.w = v.valueAt(0);
        this.x = v.valueAt(1);
        this.y = v.valueAt(2);
        this.z = v.valueAt(3);
    }

    /**
     * Creates a new vector.
     *
     * @param other The vector to copy values from
     */
    public RealVector4(@Nonnull RealVector4 other) {
        this.w = other.w;
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
    }

    //
    // Variables
    //
    @Nonnull
    protected final RealNumber w, x, y, z;

    //
    // Getters
    //

    /**
     * Returns the W component of this vector.
     *
     * @return The W component of this vector
     */
    @Nonnull
    public final RealNumber w() {
        return w;
    }

    /**
     * Returns the X component of this vector.
     *
     * @return The X component of this vector
     */
    @Nonnull
    public final RealNumber x() {
        return x;
    }

    /**
     * Returns the Y component of this vector.
     *
     * @return The Y component of this vector
     */
    @Nonnull
    public final RealNumber y() {
        return y;
    }

    /**
     * Returns the Z component of this vector.
     *
     * @return The Z component of this vector
     */
    @Nonnull
    public final RealNumber z() {
        return z;
    }

    /**
     * {@inheritDoc}
     *
     * @param i Index of component to get
     * @return The value at the specified index
     * @throws IndexOutOfBoundsException When the index {@code i} is out of bounds
     */
    @Nonnull
    @Override
    public final RealNumber valueAt(int i) throws IndexOutOfBoundsException {
        switch (i) {
            case 0 -> {return w;}
            case 1 -> {return x;}
            case 2 -> {return y;}
            case 3 -> {return z;}
            default -> throw new IndexOutOfBoundsException("Index " + i + " is out of bounds for this vector.");
        }
    }

    /**
     * {@inheritDoc}
     *
     * @return The array representation of this vector
     */
    @Nonnull
    @Override
    public final RealNumber[] values() {
        return new RealNumber[]{w, x, y, z};
    }

    //
    // Properties
    //

    /**
     * {@inheritDoc}
     *
     * @return The magnitude of this vector
     */
    @Nonnull
    @Override
    public final RealNumber magnitude() {
        return w.multiply(w).add(x.multiply(x)).add(y.multiply(y)).add(z.multiply(z)).sqrt();
    }

    /**
     * {@inheritDoc}
     *
     * @return The squared magnitude of this vector
     */
    @Nonnull
    @Override
    public final RealNumber magnitude2() {
        return w.multiply(w).add(x.multiply(x)).add(y.multiply(y)).add(z.multiply(z));
    }

    /**
     * {@inheritDoc}
     *
     * @return {@code 4}
     */
    @Override
    public final int length() {
        return 4;
    }

    //
    // Arithmetic
    //

    /**
     * {@inheritDoc}
     *
     * @param s Scalar to add to this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public RealVector4 add(@Nonnull RealNumber s) {
        return new RealVector4(w.add(s), x.add(s), y.add(s), z.add(s));
    }

    /**
     * {@inheritDoc}
     *
     * @param s Scalar to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public RealVector4 subtract(@Nonnull RealNumber s) {
        return new RealVector4(w.subtract(s), x.subtract(s), y.subtract(s), z.subtract(s));
    }

    /**
     * {@inheritDoc}
     *
     * @param s Scalar to multiply this vector by
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public RealVector4 multiply(@Nonnull RealNumber s) {
        return new RealVector4(w.multiply(s), x.multiply(s), y.multiply(s), z.multiply(s));
    }

    /**
     * {@inheritDoc}
     *
     * @param s Scalar to divide this vector by
     * @return The resulting vector
     * @throws ArithmeticException When the denominator {@code s} is zero
     */
    @Nonnull
    @Override
    public RealVector4 divide(@Nonnull RealNumber s) throws ArithmeticException {
        return new RealVector4(w.divide(s), x.divide(s), y.divide(s), z.divide(s));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to add to this vector
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length
     *                                  is different from this vector's length
     */
    @Nonnull
    @Override
    public RealVector4 add(@Nonnull RealVector v) throws IllegalArgumentException {
        if (v.length() != 4) {
            throw new IllegalArgumentException("The given vector's length is different from this vector's length.");
        }

        return new RealVector4(w.add(v.valueAt(0)), x.add(v.valueAt(1)), y.add(v.valueAt(2)), z.add(v.valueAt(3)));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to subtract from this vector
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length
     *                                  is different from this vector's length
     */
    @Nonnull
    @Override
    public RealVector4 subtract(@Nonnull RealVector v) throws IllegalArgumentException {
        if (v.length() != 4) {
            throw new IllegalArgumentException("The given vector's length is different from this vector's length.");
        }

        return new RealVector4(
                w.subtract(v.valueAt(0)),
                x.subtract(v.valueAt(1)),
                y.subtract(v.valueAt(2)),
                z.subtract(v.valueAt(3))
        );
    }

    /**
     * {@inheritDoc}
     * This operation uses quaternion multiplication to calculate the result.
     *
     * @param v The vector to multiply this vector by
     * @return This product of the two vectors
     * @throws IllegalArgumentException When the provided vector {@code v}'s length
     *                                  is different from this vector's length
     */
    @Nonnull
    @Override
    public RealVector4 multiply(@Nonnull RealVector v) throws IllegalArgumentException {
        if (v.length() != 4) {
            throw new IllegalArgumentException("The given vector's length is different from this vector's length.");
        }

        return new RealVector4(
                w.multiply(v.valueAt(0)).subtract(x.multiply(v.valueAt(1))).subtract(y.multiply(v.valueAt(2))).subtract(z.multiply(v.valueAt(3))),
                w.multiply(v.valueAt(1)).add(x.multiply(v.valueAt(0))).add(y.multiply(v.valueAt(3))).subtract(z.multiply(v.valueAt(2))),
                w.multiply(v.valueAt(2)).subtract(x.multiply(v.valueAt(3))).add(y.multiply(v.valueAt(0))).add(z.multiply(v.valueAt(1))),
                w.multiply(v.valueAt(3)).add(x.multiply(v.valueAt(2))).subtract(y.multiply(v.valueAt(1))).add(z.multiply(v.valueAt(0)))
        );
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the dot product between
     * @return The dot product of the two vectors
     * @throws IllegalArgumentException When the provided vector {@code v}'s length
     *                                  is different from this vector's length
     */
    @Nonnull
    @Override
    public RealNumber dot(@Nonnull RealVector v) throws IllegalArgumentException {
        if (v.length() != 4) {
            throw new IllegalArgumentException("The given vector's length is different from this vector's length.");
        }

        return w.multiply(v.valueAt(0)).add(x.multiply(v.valueAt(0))).add(y.multiply(v.valueAt(1))).add(z.valueAt(2));
    }

    /**
     * Adds another vector to this vector.
     *
     * @param v The vector to add to this vector
     * @return The resulting vector
     */
    @Nonnull
    public RealVector4 add(@Nonnull RealVector4 v) {
        return new RealVector4(w.add(v.w), x.add(v.x), y.add(v.y), z.add(v.z));
    }

    /**
     * Subtracts another vector from this vector.
     *
     * @param v The vector to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    public RealVector4 subtract(@Nonnull RealVector4 v) {
        return new RealVector4(w.subtract(v.w), x.subtract(v.x), y.subtract(v.y), z.subtract(v.z));
    }

    /**
     * Multiplies this vector by the provided vector {@code v}.
     *
     * @param v The vector of which to multiply this vector by
     * @return The quaternion left-product of the two vectors
     */
    @Nonnull
    public RealVector4 multiply(@Nonnull RealVector4 v) {
        return new RealVector4(
                w.multiply(v.w).subtract(x.multiply(v.x)).subtract(y.multiply(v.y)).subtract(z.multiply(v.z)),
                w.multiply(v.x).add(x.multiply(v.w)).add(y.multiply(v.z)).subtract(z.multiply(v.y)),
                w.multiply(v.y).subtract(x.multiply(v.z)).add(y.multiply(v.w)).add(z.multiply(v.x)),
                w.multiply(v.z).add(x.multiply(v.y)).subtract(y.multiply(v.x)).add(z.multiply(v.w))
        );
    }

    /**
     * Returns the dot product between this vector and the provided vector {@code v}.
     *
     * @param v The vector of which to get the dot product between
     * @return The dot product of the two vectors
     */
    @Nonnull
    public RealNumber dot(@Nonnull RealVector4 v) {
        return w.multiply(v.w).add(x.multiply(v.x)).add(y.multiply(v.y)).add(z.multiply(v.z));
    }

    //
    // Clamping
    //

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the minimum vector of
     * @return The minimum vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length
     *                                  is different from this vector's length
     */
    @Nonnull
    @Override
    public RealVector4 min(@Nonnull RealVector v) throws IllegalArgumentException {
        if (v.length() != 4) {
            throw new IllegalArgumentException("The given vector's length is different from this vector's length.");
        }

        return new RealVector4(w.min(v.valueAt(0)), x.min(v.valueAt(1)), y.min(v.valueAt(2)), z.min(v.valueAt(3)));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the maximum vector of
     * @return The maximum vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length
     *                                  is different from this vector's length
     */
    @Nonnull
    @Override
    public RealVector4 max(@Nonnull RealVector v) throws IllegalArgumentException {
        if (v.length() != 4) {
            throw new IllegalArgumentException("The given vector's length is different from this vector's length.");
        }

        return new RealVector4(w.max(v.valueAt(0)), x.max(v.valueAt(1)), y.max(v.valueAt(2)), z.max(v.valueAt(3)));
    }

    /**
     * {@inheritDoc}
     *
     * @param min The minimum allowed values
     * @param max The maximum allowed values
     * @return The clamped vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length
     *                                  is different from this vector's length
     */
    @Nonnull
    @Override
    public RealVector4 clamp(@Nonnull RealVector min, @Nonnull RealVector max) throws IllegalArgumentException {
        if (min.length() != 4 || max.length() != 4) {
            throw new IllegalArgumentException("The given vector's length is different from this vector's length.");
        }

        return new RealVector4(
                w.clamp(min.valueAt(0), max.valueAt(0)),
                x.clamp(min.valueAt(1), max.valueAt(1)),
                y.clamp(min.valueAt(2), max.valueAt(2)),
                z.clamp(min.valueAt(3), max.valueAt(3))
        );
    }

    //
    // Utility
    //

    /**
     * {@inheritDoc}
     *
     * @param operator The operator to apply to each component of this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public RealVector4 apply(@Nonnull UnaryOperator<RealNumber> operator) {
        return new RealVector4(operator.apply(w), operator.apply(x), operator.apply(y), operator.apply(z));
    }

    /**
     * {@inheritDoc}
     *
     * @return The normalized vector of this vector
     * @throws UnsupportedOperationException When this vector's magnitude is zero
     */
    @Nonnull
    @Override
    public RealVector4 normalize() throws UnsupportedOperationException {
        try {
            return divide(magnitude());
        } catch (final ArithmeticException e) {
            throw new UnsupportedOperationException("Cannot normalize a vector with a magnitude of zero.");
        }
    }

    /**
     * {@inheritDoc}
     *
     * @return The negation of this vector
     */
    @Nonnull
    @Override
    public RealVector4 negate() {
        return new RealVector4(w.negate(), x.negate(), y.negate(), z.negate());
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the distance to
     * @return The distance between the two vectors
     * @throws IllegalArgumentException When the provided vector {@code v}'s length
     *                                  is different from this vector's length
     */
    @Nonnull
    @Override
    public RealNumber distance(@Nonnull RealVector v) throws IllegalArgumentException {
        return subtract(v).magnitude();
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the squared distance to
     * @return The squared distance between the two vectors
     * @throws IllegalArgumentException When the provided vector {@code v}'s length
     *                                  is different from this vector's length
     */
    @Nonnull
    @Override
    public RealNumber distance2(@Nonnull RealVector v) throws IllegalArgumentException {
        return subtract(v).magnitude2();
    }

    /**
     * {@inheritDoc}
     *
     * @return The {@link Vector4} representation of this vector
     */
    @Nonnull
    @Override
    public Vector4 doubleValue() {
        return new Vector4(w.doubleValue(), x.doubleValue(), y.doubleValue(), z.doubleValue());
    }

    //
    // Equality
    //

    /**
     * {@inheritDoc}
     *
     * @param obj The object to compare to
     * @return {@code true} if the object is an instance of {@link RealVector}, and the components and length are equal
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof RealVector v)) return false;
        if (v.length() != 4) return false;
        return w.equals(v.valueAt(0)) && x.equals(v.valueAt(1)) && y.equals(v.valueAt(2)) && z.equals(v.valueAt(3));
    }

    //
    // Serialization
    //

    /**
     * {@inheritDoc}
     *
     * @param input The input string to parse
     * @return The parsed vector
     * @throws IllegalArgumentException When at least one of the values is non-finite
     * @throws NumberFormatException    When the format is invalid
     */
    @Nonnull
    public static RealVector4 parseVector(@Nonnull String input) throws IllegalArgumentException {
        if (!input.startsWith("RealVector4{")) {
            throw new NumberFormatException("Given string does not represent a RealVector4");
        }

        final String cleanInput = input.replaceAll("RealVector4\\{|}", "");
        final String[] valueStrings = cleanInput.split(", ");
        final RealNumber[] values = new RealNumber[4];

        for (final String valueString : valueStrings) {
            final String[] splitByEquals = valueString.split("=");
            values[switch (splitByEquals[0]) {
                case "w" -> 0;
                case "x" -> 1;
                case "y" -> 2;
                case "z" -> 3;
                default -> throw new NumberFormatException("Given string does not represent a RealVector4");
            }] = RealNumber.parseNumber(splitByEquals[1]);
        }

        return new RealVector4(values);
    }

    /**
     * {@inheritDoc}
     *
     * @return The string representation of this vector
     */
    @Override
    @Nonnull
    public String toString() {
        return "RealVector4{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
