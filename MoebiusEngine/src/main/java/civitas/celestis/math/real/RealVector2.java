package civitas.celestis.math.real;

import civitas.celestis.math.vector.Vector2;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.Objects;
import java.util.function.UnaryOperator;

/**
 * An immutable three-dimensional vector which uses {@link RealNumber} as its components.
 */
public class RealVector2 implements RealVector {
    //
    // Constants
    //

    /**
     * A vector with no direction or magnitude. Represents origin.
     */
    public static final RealVector2 ZERO = new RealVector2(RealNumber.ZERO, RealNumber.ZERO);

    /**
     * The minimum value a vector can have without being zero.
     */
    public static final RealVector2 MIN_VALUE = new RealVector2(RealNumber.MIN_VALUE, RealNumber.MIN_VALUE);

    /**
     * The maximum value a vector can have without being infinite.
     */
    public static final RealVector2 MAX_VALUE = new RealVector2(RealNumber.MAX_VALUE, RealNumber.MAX_VALUE);

    /**
     * The positive X unit vector.
     */
    public static final RealVector2 POSITIVE_X = new RealVector2(RealNumber.ONE, RealNumber.ZERO);

    /**
     * The positive Y unit vector.
     */
    public static final RealVector2 POSITIVE_Y = new RealVector2(RealNumber.ZERO, RealNumber.ONE);

    /**
     * The negative X unit vector.
     */
    public static final RealVector2 NEGATIVE_X = new RealVector2(RealNumber.ONE.negate(), RealNumber.ZERO);

    /**
     * The negative Y unit vector.
     */
    public static final RealVector2 NEGATIVE_Y = new RealVector2(RealNumber.ZERO, RealNumber.ONE.negate());

    //
    // Constructors
    //

    /**
     * Creates a new vector.
     *
     * @param x The X component of this vector
     * @param y The Y component of this vector
     */
    public RealVector2(double x, double y) {
        this(new RealNumber(x), new RealNumber(y));
    }

    /**
     * Creates a new vector.
     *
     * @param x The X component of this vector
     * @param y The Y component of this vector
     */
    public RealVector2(@Nonnull RealNumber x, @Nonnull RealNumber y) {
        this.x = x;
        this.y = y;

    }

    /**
     * Creates a new vector.
     *
     * @param values An array containing the components of this vector
     */
    public RealVector2(@Nonnull RealNumber[] values) {
        if (values.length != 2) {
            throw new IllegalArgumentException("The provided array does not have a length of 2.");
        }

        this.x = Objects.requireNonNull(values[0]);
        this.y = Objects.requireNonNull(values[1]);
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector to copy values from
     */
    public RealVector2(@Nonnull RealVector v) {
        if (v.length() != 2) {
            throw new IllegalArgumentException("The provided vector does not have a length of 2.");
        }

        this.x = v.valueAt(0);
        this.y = v.valueAt(1);
    }

    /**
     * Creates a new vector.
     *
     * @param other The vector to copy values from
     */
    public RealVector2(@Nonnull RealVector2 other) {
        this.x = other.x;
        this.y = other.y;
    }

    //
    // Variables
    //
    @Nonnull
    protected final RealNumber x, y;

    //
    // Getters
    //

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
            case 0 -> {return x;}
            case 1 -> {return y;}
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
        return new RealNumber[]{x, y};
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
        return x.multiply(x).add(y.multiply(y)).sqrt();
    }

    /**
     * {@inheritDoc}
     *
     * @return The squared magnitude of this vector
     */
    @Nonnull
    @Override
    public final RealNumber magnitude2() {
        return x.multiply(x).add(y.multiply(y));
    }

    /**
     * {@inheritDoc}
     *
     * @return {@code 2}
     */
    @Override
    public final int length() {
        return 3;
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
    public RealVector2 add(@Nonnull RealNumber s) {
        return new RealVector2(x.add(s), y.add(s));
    }

    /**
     * {@inheritDoc}
     *
     * @param s Scalar to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public RealVector2 subtract(@Nonnull RealNumber s) {
        return new RealVector2(x.subtract(s), y.subtract(s));
    }

    /**
     * {@inheritDoc}
     *
     * @param s Scalar to multiply this vector by
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public RealVector2 multiply(@Nonnull RealNumber s) {
        return new RealVector2(x.multiply(s), y.multiply(s));
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
    public RealVector2 divide(@Nonnull RealNumber s) throws ArithmeticException {
        return new RealVector2(x.divide(s), y.divide(s));
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
    public RealVector2 add(@Nonnull RealVector v) throws IllegalArgumentException {
        if (v.length() != 2) {
            throw new IllegalArgumentException("The given vector's length is different from this vector's length.");
        }

        return new RealVector2(x.add(v.valueAt(0)), y.add(v.valueAt(1)));
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
    public RealVector2 subtract(@Nonnull RealVector v) throws IllegalArgumentException {
        if (v.length() != 2) {
            throw new IllegalArgumentException("The given vector's length is different from this vector's length.");
        }

        return new RealVector2(x.subtract(v.valueAt(0)), y.subtract(v.valueAt(1)));
    }

    /**
     * {@inheritDoc}
     * This implementation uses complex number multiplication to calculate the product.
     *
     * @param v The vector to multiply this vector by
     * @return The product of the two vectors
     * @throws IllegalArgumentException When the provided vector {@code v}'s length
     *                                  is different from this vector's length
     */
    @Nonnull
    @Override
    public RealVector2 multiply(@Nonnull RealVector v) throws IllegalArgumentException {
        if (v.length() != 2) {
            throw new IllegalArgumentException("The given vector's length is different from this vector's length.");
        }

        return new RealVector2(
                x.multiply(v.valueAt(0)).subtract(y.multiply(v.valueAt(1))),
                x.multiply(v.valueAt(1)).add(y.multiply(v.valueAt(0)))
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
        if (v.length() != 2) {
            throw new IllegalArgumentException("The given vector's length is different from this vector's length.");
        }

        return x.multiply(v.valueAt(0)).add(y.multiply(v.valueAt(1)));
    }

    /**
     * Adds another vector to this vector.
     *
     * @param v The vector to add to this vector
     * @return The resulting vector
     */
    @Nonnull
    public RealVector2 add(@Nonnull RealVector2 v) {
        return new RealVector2(x.add(v.x), y.add(v.y));
    }

    /**
     * Subtracts another vector from this vector.
     *
     * @param v The vector to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    public RealVector2 subtract(@Nonnull RealVector2 v) {
        return new RealVector2(x.subtract(v.x), y.subtract(v.y));
    }

    /**
     * Returns the dot product between this vector and the provided vector {@code v}.
     *
     * @param v The vector of which to get the dot product between
     * @return The dot product of the two vectors
     */
    @Nonnull
    public RealNumber dot(@Nonnull RealVector2 v) {
        return x.multiply(v.x).add(y.multiply(v.y));
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
    public RealVector2 min(@Nonnull RealVector v) throws IllegalArgumentException {
        if (v.length() != 2) {
            throw new IllegalArgumentException("The given vector's length is different from this vector's length.");
        }

        return new RealVector2(x.min(v.valueAt(0)), y.min(v.valueAt(1)));
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
    public RealVector2 max(@Nonnull RealVector v) throws IllegalArgumentException {
        if (v.length() != 2) {
            throw new IllegalArgumentException("The given vector's length is different from this vector's length.");
        }

        return new RealVector2(x.max(v.valueAt(0)), y.max(v.valueAt(1)));
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
    public RealVector2 clamp(@Nonnull RealVector min, @Nonnull RealVector max) throws IllegalArgumentException {
        if (min.length() != 2 || max.length() != 2) {
            throw new IllegalArgumentException("The given vector's length is different from this vector's length.");
        }

        return new RealVector2(
                x.clamp(min.valueAt(0), max.valueAt(0)),
                y.clamp(min.valueAt(1), max.valueAt(1))
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
    public RealVector2 apply(@Nonnull UnaryOperator<RealNumber> operator) {
        return new RealVector2(operator.apply(x), operator.apply(y));
    }

    /**
     * {@inheritDoc}
     *
     * @return The normalized vector of this vector
     * @throws UnsupportedOperationException When this vector's magnitude is zero
     */
    @Nonnull
    @Override
    public RealVector2 normalize() throws UnsupportedOperationException {
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
    public RealVector2 negate() {
        return new RealVector2(x.negate(), y.negate());
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
     * Returns the distance between this vector and the provided vector {@code v}.
     * @param v The vector to get the distance to
     * @return The distance between the two vectors
     */
    @Nonnull
    public RealNumber distance(@Nonnull RealVector2 v) {
        final RealNumber dx = x.subtract(v.x);
        final RealNumber dy = y.subtract(v.y);

        return dx.pow().add(dy.pow()).sqrt();
    }

    /**
     * Returns the squared distance between this vector and the provided vector {@code v}.
     * @param v The vector to get the squared distance to
     * @return The squared distance between the two vectors
     */
    @Nonnull
    public RealNumber distance2(@Nonnull RealVector2 v) {
        final RealNumber dx = x.subtract(v.x);
        final RealNumber dy = y.subtract(v.y);

        return dx.pow().add(dy.pow());
    }

    /**
     * {@inheritDoc}
     *
     * @return The {@link Vector2} representation of this vector
     */
    @Nonnull
    @Override
    public Vector2 doubleValue() {
        return new Vector2(x.doubleValue(), y.doubleValue());
    }

    /**
     * Rotates this vector counter-clockwise by given angle.
     *
     * @param angRads Angle in radians to rotate this vector by
     * @return The rotated vector
     */
    @Nonnull
    public RealVector2 rotate(double angRads) {
        return multiply(new RealVector2(Math.cos(angRads), Math.sin(angRads)));
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
        if (v.length() != 2) return false;
        return x.equals(v.valueAt(0)) && y.equals(v.valueAt(1));
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
    public static RealVector2 parseVector(@Nonnull String input) throws IllegalArgumentException {
        if (!input.startsWith("RealVector2{")) {
            throw new NumberFormatException("Given string does not represent a RealVector2");
        }

        final String cleanInput = input.replaceAll("RealVector2\\{|}", "");
        final String[] valueStrings = cleanInput.split(", ");
        final RealNumber[] values = new RealNumber[2];

        for (final String valueString : valueStrings) {
            final String[] splitByEquals = valueString.split("=");
            values[switch (splitByEquals[0]) {
                case "x" -> 0;
                case "y" -> 1;
                default -> throw new NumberFormatException("Given string does not represent a RealVector2");
            }] = RealNumber.parseNumber(splitByEquals[1]);
        }

        return new RealVector2(values);
    }

    /**
     * {@inheritDoc}
     *
     * @return The string representation of this vector
     */
    @Override
    @Nonnull
    public String toString() {
        return "RealVector2{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
