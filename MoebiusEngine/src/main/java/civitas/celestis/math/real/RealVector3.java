package civitas.celestis.math.real;

import civitas.celestis.math.vector.Vector3;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.Objects;
import java.util.function.UnaryOperator;

/**
 * An immutable three-dimensional vector which uses {@link RealNumber} as its components.
 */
public class RealVector3 implements RealVector {
    //
    // Constants
    //

    /**
     * A vector with no direction or magnitude. Represents origin.
     */
    public static final RealVector3 ZERO = new RealVector3(RealNumber.ZERO, RealNumber.ZERO, RealNumber.ZERO);

    /**
     * The minimum value a vector can have without being zero.
     */
    public static final RealVector3 MIN_VALUE = new RealVector3(RealNumber.MIN_VALUE, RealNumber.MIN_VALUE, RealNumber.MIN_VALUE);

    /**
     * The maximum value a vector can have without being infinite.
     */
    public static final RealVector3 MAX_VALUE = new RealVector3(RealNumber.MAX_VALUE, RealNumber.MAX_VALUE, RealNumber.MAX_VALUE);

    /**
     * The positive X unit vector.
     */
    public static final RealVector3 POSITIVE_X = new RealVector3(RealNumber.ONE, RealNumber.ZERO, RealNumber.ZERO);

    /**
     * The positive Y unit vector.
     */
    public static final RealVector3 POSITIVE_Y = new RealVector3(RealNumber.ZERO, RealNumber.ONE, RealNumber.ZERO);

    /**
     * The positive Z unit vector.
     */
    public static final RealVector3 POSITIVE_Z = new RealVector3(RealNumber.ZERO, RealNumber.ZERO, RealNumber.ONE);

    /**
     * The negative X unit vector.
     */
    public static final RealVector3 NEGATIVE_X = new RealVector3(RealNumber.ONE.negate(), RealNumber.ZERO, RealNumber.ZERO);

    /**
     * The negative Y unit vector.
     */
    public static final RealVector3 NEGATIVE_Y = new RealVector3(RealNumber.ZERO, RealNumber.ONE.negate(), RealNumber.ZERO);

    /**
     * The negative Z unit vector.
     */
    public static final RealVector3 NEGATIVE_Z = new RealVector3(RealNumber.ZERO, RealNumber.ZERO, RealNumber.ONE.negate());

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
    public RealVector3(double x, double y, double z) {
        this(new RealNumber(x), new RealNumber(y), new RealNumber(z));
    }

    /**
     * Creates a new vector.
     *
     * @param x The X component of this vector
     * @param y The Y component of this vector
     * @param z The Z component of this vector
     */
    public RealVector3(@Nonnull RealNumber x, @Nonnull RealNumber y, @Nonnull RealNumber z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Creates a new vector.
     *
     * @param values An array containing the components of this vector
     */
    public RealVector3(@Nonnull RealNumber[] values) {
        if (values.length != 3) {
            throw new IllegalArgumentException("The provided array does not have a length of 3.");
        }

        this.x = Objects.requireNonNull(values[0]);
        this.y = Objects.requireNonNull(values[1]);
        this.z = Objects.requireNonNull(values[2]);
    }

    /**
     * Creates a new vector.
     *
     * @param v The vector to copy values from
     */
    public RealVector3(@Nonnull RealVector v) {
        if (v.length() != 3) {
            throw new IllegalArgumentException("The provided vector does not have a length of 3.");
        }

        this.x = v.valueAt(0);
        this.y = v.valueAt(1);
        this.z = v.valueAt(2);
    }

    /**
     * Creates a new vector.
     *
     * @param other The vector to copy values from
     */
    public RealVector3(@Nonnull RealVector3 other) {
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
    }

    //
    // Variables
    //
    @Nonnull
    protected final RealNumber x, y, z;

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
            case 0 -> {return x;}
            case 1 -> {return y;}
            case 2 -> {return z;}
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
        return new RealNumber[]{x, y, z};
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
        return x.multiply(x).add(y.multiply(y)).add(z.multiply(z)).sqrt();
    }

    /**
     * {@inheritDoc}
     *
     * @return The squared magnitude of this vector
     */
    @Nonnull
    @Override
    public final RealNumber magnitude2() {
        return x.multiply(x).add(y.multiply(y)).add(z.multiply(z));
    }

    /**
     * {@inheritDoc}
     *
     * @return {@code 3}
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
    public RealVector3 add(@Nonnull RealNumber s) {
        return new RealVector3(x.add(s), y.add(s), z.add(s));
    }

    /**
     * {@inheritDoc}
     *
     * @param s Scalar to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public RealVector3 subtract(@Nonnull RealNumber s) {
        return new RealVector3(x.subtract(s), y.subtract(s), z.subtract(s));
    }

    /**
     * {@inheritDoc}
     *
     * @param s Scalar to multiply this vector by
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public RealVector3 multiply(@Nonnull RealNumber s) {
        return new RealVector3(x.multiply(s), y.multiply(s), z.multiply(s));
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
    public RealVector3 divide(@Nonnull RealNumber s) throws ArithmeticException {
        return new RealVector3(x.divide(s), y.divide(s), z.divide(s));
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
    public RealVector3 add(@Nonnull RealVector v) throws IllegalArgumentException {
        if (v.length() != 3) {
            throw new IllegalArgumentException("The given vector's length is different from this vector's length.");
        }

        return new RealVector3(x.add(v.valueAt(0)), y.add(v.valueAt(1)), z.add(v.valueAt(2)));
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
    public RealVector3 subtract(@Nonnull RealVector v) throws IllegalArgumentException {
        if (v.length() != 3) {
            throw new IllegalArgumentException("The given vector's length is different from this vector's length.");
        }

        return new RealVector3(x.subtract(v.valueAt(0)), y.subtract(v.valueAt(1)), z.subtract(v.valueAt(2)));
    }

    /**
     * Multiplication is not supported for this vector.
     *
     * @param v The vector to multiply this vector by
     * @return This method cannot return a value
     * @throws UnsupportedOperationException Always throws an exception
     */
    @Nonnull
    @Override
    public RealVector3 multiply(@Nonnull RealVector v) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Multiplication is not defined for this vector.");
    }

    /**
     * Returns ths cross product between this vector and the provided vector {@code v}.
     *
     * @param v The vector on the right of this operation
     * @return The cross product of the two vectors
     * @throws IllegalArgumentException When the provided vector {@code v}'s length
     *                                  is different from this vector's length
     */
    @Nonnull
    public RealVector3 cross(@Nonnull RealVector v) throws IllegalArgumentException {
        if (v.length() != 3) {
            throw new IllegalArgumentException("The given vector's length is different from this vector's length.");
        }

        return new RealVector3(
                y.multiply(v.valueAt(2)).subtract(z.multiply(v.valueAt(1))),
                z.multiply(v.valueAt(0)).subtract(x.multiply(v.valueAt(2))),
                x.multiply(v.valueAt(1)).subtract(y.multiply(v.valueAt(0)))
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
        if (v.length() != 3) {
            throw new IllegalArgumentException("The given vector's length is different from this vector's length.");
        }

        return x.multiply(v.valueAt(0)).add(y.multiply(v.valueAt(1))).add(z.valueAt(2));
    }

    /**
     * Adds another vector to this vector.
     *
     * @param v The vector to add to this vector
     * @return The resulting vector
     */
    @Nonnull
    public RealVector3 add(@Nonnull RealVector3 v) {
        return new RealVector3(x.add(v.x), y.add(v.y), z.add(v.z));
    }

    /**
     * Subtracts another vector from this vector.
     *
     * @param v The vector to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    public RealVector3 subtract(@Nonnull RealVector3 v) {
        return new RealVector3(x.subtract(v.x), y.subtract(v.y), z.subtract(v.z));
    }

    /**
     * Returns the cross product between this vector and the provided vector {@code v}.
     *
     * @param v The vector of which to get the cross product between
     * @return The cross product of the two vectors
     */
    @Nonnull
    public RealVector3 cross(@Nonnull RealVector3 v) {
        return new RealVector3(
                y.multiply(v.z).subtract(z.multiply(v.y)),
                z.multiply(v.x).subtract(x.multiply(v.z)),
                x.multiply(v.y).subtract(y.multiply(v.x))
        );
    }

    /**
     * Returns the dot product between this vector and the provided vector {@code v}.
     *
     * @param v The vector of which to get the dot product between
     * @return The dot product of the two vectors
     */
    @Nonnull
    public RealNumber dot(@Nonnull RealVector3 v) {
        return x.multiply(v.x).add(y.multiply(v.y)).add(z.multiply(v.z));
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
    public RealVector3 min(@Nonnull RealVector v) throws IllegalArgumentException {
        if (v.length() != 3) {
            throw new IllegalArgumentException("The given vector's length is different from this vector's length.");
        }

        return new RealVector3(x.min(v.valueAt(0)), y.min(v.valueAt(1)), z.min(v.valueAt(2)));
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
    public RealVector3 max(@Nonnull RealVector v) throws IllegalArgumentException {
        if (v.length() != 3) {
            throw new IllegalArgumentException("The given vector's length is different from this vector's length.");
        }

        return new RealVector3(x.max(v.valueAt(0)), y.max(v.valueAt(1)), z.max(v.valueAt(2)));
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
    public RealVector3 clamp(@Nonnull RealVector min, @Nonnull RealVector max) throws IllegalArgumentException {
        if (min.length() != 3 || max.length() != 3) {
            throw new IllegalArgumentException("The given vector's length is different from this vector's length.");
        }

        return new RealVector3(
                x.clamp(min.valueAt(0), max.valueAt(0)),
                y.clamp(min.valueAt(1), max.valueAt(1)),
                z.clamp(min.valueAt(2), max.valueAt(2))
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
    public RealVector3 apply(@Nonnull UnaryOperator<RealNumber> operator) {
        return new RealVector3(operator.apply(x), operator.apply(y), operator.apply(z));
    }

    /**
     * {@inheritDoc}
     *
     * @return The normalized vector of this vector
     * @throws UnsupportedOperationException When this vector's magnitude is zero
     */
    @Nonnull
    @Override
    public RealVector3 normalize() throws UnsupportedOperationException {
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
    public RealVector3 negate() {
        return new RealVector3(x.negate(), y.negate(), z.negate());
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
    public RealNumber distance(@Nonnull RealVector3 v) {
        final RealNumber dx = x.subtract(v.x);
        final RealNumber dy = y.subtract(v.y);
        final RealNumber dz = z.subtract(v.z);

        return dx.pow().add(dy.pow()).add(dz.pow()).sqrt();
    }

    /**
     * Returns the squared distance between this vector and the provided vector {@code v}.
     * @param v The vector to get the squared distance to
     * @return The squared distance between the two vectors
     */
    @Nonnull
    public RealNumber distance2(@Nonnull RealVector3 v) {
        final RealNumber dx = x.subtract(v.x);
        final RealNumber dy = y.subtract(v.y);
        final RealNumber dz = z.subtract(v.z);

        return dx.pow().add(dy.pow()).add(dz.pow());
    }

    /**
     * {@inheritDoc}
     *
     * @return The {@link Vector3} representation of this vector
     */
    @Nonnull
    @Override
    public Vector3 doubleValue() {
        return new Vector3(x.doubleValue(), y.doubleValue(), z.doubleValue());
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
        if (v.length() != 3) return false;
        return x.equals(v.valueAt(0)) && y.equals(v.valueAt(1)) && z.equals(v.valueAt(2));
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
    public static RealVector3 parseVector(@Nonnull String input) throws IllegalArgumentException {
        if (!input.startsWith("RealVector3{")) {
            throw new NumberFormatException("Given string does not represent a RealVector3");
        }

        final String cleanInput = input.replaceAll("RealVector3\\{|}", "");
        final String[] valueStrings = cleanInput.split(", ");
        final RealNumber[] values = new RealNumber[3];

        for (final String valueString : valueStrings) {
            final String[] splitByEquals = valueString.split("=");
            values[switch (splitByEquals[0]) {
                case "x" -> 0;
                case "y" -> 1;
                case "z" -> 2;
                default -> throw new NumberFormatException("Given string does not represent a RealVector3");
            }] = RealNumber.parseNumber(splitByEquals[1]);
        }

        return new RealVector3(values);
    }

    /**
     * {@inheritDoc}
     *
     * @return The string representation of this vector
     */
    @Override
    @Nonnull
    public String toString() {
        return "RealVector3{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
