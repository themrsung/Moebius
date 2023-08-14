package civitas.celestis.math.vector;

import civitas.celestis.math.Numbers;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.function.UnaryOperator;

/**
 * An immutable five-dimensional vector.
 */
public class Vector5 implements Vector {
    //
    // Constants
    //

    /**
     * Absolute zero. This vector represents origin.
     */
    public static final Vector5 ZERO = new Vector5(0, 0, 0, 0, 0);

    /**
     * The minimum positive value a vector can have without being zero.
     */
    public static final Vector5 MIN_VALUE = new Vector5(Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE);

    /**
     * The maximum positive value a vector can have without being infinite.
     */
    public static final Vector5 MAX_VALUE = new Vector5(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);

    /**
     * The positive V unit vector.
     */
    public static final Vector5 POSITIVE_V = new Vector5(1, 0, 0, 0, 0);

    /**
     * The positive W unit vector.
     */
    public static final Vector5 POSITIVE_W = new Vector5(0, 1, 0, 0, 0);

    /**
     * The positive X unit vector.
     */
    public static final Vector5 POSITIVE_X = new Vector5(0, 0, 1, 0, 0);

    /**
     * The positive Y unit vector.
     */
    public static final Vector5 POSITIVE_Y = new Vector5(0, 0, 0, 1, 0);

    /**
     * The positive Z unit vector.
     */
    public static final Vector5 POSITIVE_Z = new Vector5(0, 0, 0, 0, 1);

    /**
     * The negative V unit vector.
     */
    public static final Vector5 NEGATIVE_V = new Vector5(-1, 0, 0, 0, 0);

    /**
     * The negative W unit vector.
     */
    public static final Vector5 NEGATIVE_W = new Vector5(0, -1, 0, 0, 0);

    /**
     * The negative X unit vector.
     */
    public static final Vector5 NEGATIVE_X = new Vector5(0, 0, -1, 0, 0);

    /**
     * The negative Y unit vector.
     */
    public static final Vector5 NEGATIVE_Y = new Vector5(0, 0, 0, -1, 0);

    /**
     * The negative Z unit vector.
     */
    public static final Vector5 NEGATIVE_Z = new Vector5(0, 0, 0, 0, -1);

    //
    // Constructors
    //

    /**
     * Creates a new vector.
     *
     * @param v The V component of this vector
     * @param w The W component of this vector
     * @param x The X component of this vector
     * @param y The Y component of this vector
     * @param z The Z component of this vector
     */
    public Vector5(double v, double w, double x, double y, double z) {
        this.v = Numbers.requireFinite(v);
        this.w = Numbers.requireFinite(w);
        this.x = Numbers.requireFinite(x);
        this.y = Numbers.requireFinite(y);
        this.z = Numbers.requireFinite(z);
    }

    /**
     * Creates a new vector.
     *
     * @param values The array of component scalars to construct this vector from
     */
    public Vector5(@Nonnull double[] values) {
        if (values.length != 5) {
            throw new IllegalArgumentException("The provided array does not have a length of 5.");
        }

        this.v = Numbers.requireFinite(values[0]);
        this.w = Numbers.requireFinite(values[1]);
        this.x = Numbers.requireFinite(values[2]);
        this.y = Numbers.requireFinite(values[3]);
        this.z = Numbers.requireFinite(values[4]);
    }

    /**
     * Creates a new vector.
     *
     * @param other The vector to copy values from
     */
    public Vector5(@Nonnull Vector other) {
        if (other.length() != 5) {
            throw new IllegalArgumentException("The provided vector does not have a length of 5.");
        }

        this.v = other.valueAt(0);
        this.w = other.valueAt(1);
        this.x = other.valueAt(2);
        this.y = other.valueAt(3);
        this.z = other.valueAt(4);
    }

    /**
     * Creates a new vector.
     *
     * @param other The vector to copy values from
     */
    public Vector5(@Nonnull Vector5 other) {
        this.v = other.v;
        this.w = other.w;
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
    }

    //
    // Randomization
    //

    /**
     * Returns a random normalized unit vector.
     *
     * @return A random unit vector
     */
    @Nonnull
    public static Vector5 random() {
        return new Vector5(
                Numbers.random(-1, 1),
                Numbers.random(-1, 1),
                Numbers.random(-1, 1),
                Numbers.random(-1, 1),
                Numbers.random(-1, 1)
        ).normalize();
    }

    //
    // Variables
    //

    private final double v, w, x, y, z;

    //
    // Getters
    //

    /**
     * Returns the V component of this vector.
     *
     * @return The V component of this vector
     */
    public final double v() {
        return v;
    }

    /**
     * Returns the W component of this vector.
     *
     * @return The W component of this vector
     */
    public final double w() {
        return w;
    }

    /**
     * Returns the X component of this vector.
     *
     * @return The X component of this vector
     */
    public final double x() {
        return x;
    }

    /**
     * Returns the Y component of this vector.
     *
     * @return The Y component of this vector
     */
    public final double y() {
        return y;
    }

    /**
     * Returns the Z component of this vector.
     *
     * @return The Z component of this vector
     */
    public final double z() {
        return z;
    }

    /**
     * {@inheritDoc}
     *
     * @param i Index of component to get
     * @return The V, W, X, Y, Z component for {@code 0}, {@code 1}, {@code 2}, {@code 3}, {@code 4} respectively
     * @throws IndexOutOfBoundsException When the index is out of bounds {@code i < 0 || i > 4}
     */
    @Override
    public final double valueAt(int i) throws IndexOutOfBoundsException {
        switch (i) {
            case 0 -> {return v;}
            case 1 -> {return w;}
            case 2 -> {return x;}
            case 3 -> {return y;}
            case 4 -> {return z;}

            default -> throw new IndexOutOfBoundsException("Index " + i + " is out of bounds for this vector.");
        }
    }

    /**
     * {@inheritDoc}
     *
     * @return The array representation of this vector
     */
    @Override
    public final double[] values() {
        return new double[]{v, w, x, y, z};
    }

    //
    // Properties
    //

    /**
     * {@inheritDoc}
     *
     * @return The magnitude of this vector
     */
    @Override
    public final double magnitude() {
        return Math.sqrt(v * v + w * w + x * x + y * y + z * z);
    }

    /**
     * {@inheritDoc}
     *
     * @return The squared magnitude of this vector
     */
    @Override
    public final double magnitude2() {
        return v * v + w * w + x * x + y * y + z * z;
    }

    /**
     * Since a {@link Vector4} always has give components, this will return a constant value {@code 5}.
     *
     * @return {@code 5}
     */
    @Override
    public final int length() {
        return 5;
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
    public Vector5 add(double s) {
        return new Vector5(v + s, w + s, x + s, y + s, z + s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s Scalar to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public Vector5 subtract(double s) {
        return new Vector5(v - s, w - s, x - s, y - s, z - s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s Scalar to multiply this vector by
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public Vector5 multiply(double s) {
        return new Vector5(v * s, w * s, x * s, y * s, z * s);
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
    public Vector5 divide(double s) throws ArithmeticException {
        if (s == 0) {
            throw new ArithmeticException("Cannot divide by zero.");
        }

        return new Vector5(v / s, w / s, x / s, y / s, z / s);
    }

    /**
     * {@inheritDoc}
     *
     * @param vec The vector to add to this vector
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code vec}'s length is not {@code 5}
     */
    @Nonnull
    @Override
    public Vector5 add(@Nonnull Vector vec) throws IllegalArgumentException {
        if (vec.length() != 5) {
            throw new IllegalArgumentException("The provided vector does not have a length of 5.");
        }

        return new Vector5(
                v + vec.valueAt(0),
                w + vec.valueAt(1),
                x + vec.valueAt(2),
                y + vec.valueAt(3),
                z + vec.valueAt(4)
        );
    }

    /**
     * {@inheritDoc}
     *
     * @param vec The vector to subtract from this vector
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code vec}'s length is not {@code 5}
     */
    @Nonnull
    @Override
    public Vector5 subtract(@Nonnull Vector vec) throws IllegalArgumentException {
        if (vec.length() != 5) {
            throw new IllegalArgumentException("The provided vector does not have a length of 5.");
        }

        return new Vector5(
                v - vec.valueAt(0),
                w - vec.valueAt(1),
                x - vec.valueAt(2),
                y - vec.valueAt(3),
                z - vec.valueAt(4)
        );
    }

    /**
     * Since there is no default definition for multiplication of five-dimensional vectors,
     * this method will always throw an exception
     *
     * @param v The vector to multiply this vector by
     * @return This method cannot return a value
     * @throws UnsupportedOperationException Always throws an exception
     */
    @Nonnull
    @Override
    public Vector5 multiply(@Nonnull Vector v) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("There is no default definition of five-dimensional vector multiplication.");
    }

    /**
     * Returns the wedge-product between {@code this} and the provided vector {@code vec}.
     *
     * @param vec Vector to get the wedge-product of
     * @return The wedge-product of the two vectors
     * @throws IllegalArgumentException When the provided vector {@code vec}'s length is not {@code 5}
     */
    @Nonnull
    public Vector5 wedge(@Nonnull Vector vec) throws IllegalArgumentException {
        if (vec.length() != 5) {
            throw new IllegalArgumentException("The provided vector does not have a length of 5.");
        }

        return new Vector5(
                w * vec.valueAt(0) + v * vec.valueAt(1),
                x * vec.valueAt(0) + v * vec.valueAt(2),
                y * vec.valueAt(0) + v * vec.valueAt(3),
                z * vec.valueAt(0) + v * vec.valueAt(4),
                y * vec.valueAt(2) - x * vec.valueAt(3)
        );
    }

    /**
     * Returns the geometric product between {@code this} and the provided vector {@code vec}.
     *
     * @param vec Vector to get the geometric product of
     * @return The geometric product of the two vectors
     * @throws IllegalArgumentException When the provided vector {@code vec}'s length is not {@code 5}
     */
    @Nonnull
    public Vector5 geometric(@Nonnull Vector vec) throws IllegalArgumentException {
        if (vec.length() != 5) {
            throw new IllegalArgumentException("The provided vector does not have a length of 5.");
        }

        final Vector5 wedge = wedge(vec);

        return new Vector5(
                dot(vec) + wedge.v,
                wedge.w,
                wedge.x,
                wedge.y,
                wedge.z
        );
    }

    /**
     * {@inheritDoc}
     *
     * @param vec The vector to get the dot product between
     * @return The dot product of the two vectors
     * @throws IllegalArgumentException When the provided vector {@code vec}'s length is not {@code 5}
     */
    @Override
    public double dot(@Nonnull Vector vec) throws IllegalArgumentException {
        if (vec.length() != 5) {
            throw new IllegalArgumentException("The provided vector does not have a length of 5.");
        }

        return v * vec.valueAt(0) +
                w * vec.valueAt(1) +
                x * vec.valueAt(2) +
                y * vec.valueAt(3) +
                z * vec.valueAt(4);
    }

    /**
     * Adds another vector to this vector.
     *
     * @param vec The vector to add to this vector
     * @return The resulting vector
     */
    @Nonnull
    public Vector5 add(@Nonnull Vector5 vec) {
        return new Vector5(v + vec.v, w + vec.w, x + vec.x, y + vec.y, z + vec.z);
    }

    /**
     * Subtracts another vector from this vector.
     *
     * @param vec The vector to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    public Vector5 subtract(@Nonnull Vector5 vec) {
        return new Vector5(v - vec.v, w - vec.w, x - vec.x, y - vec.y, z - vec.z);
    }

    /**
     * Returns the wedge-product between {@code this} and the provided vector {@code vec}.
     *
     * @param vec Vector to get the wedge-product of
     * @return The wedge-product of the two vectors
     */
    @Nonnull
    public Vector5 wedge(@Nonnull Vector5 vec) {
        return new Vector5(
                w * vec.v + v * vec.w,
                x * vec.v + v * vec.x,
                y * vec.v + v * vec.y,
                z * vec.v + v * vec.z,
                y * vec.x - x * vec.y
        );
    }

    /**
     * Returns the geometric product between {@code this} and the provided vector {@code vec}.
     *
     * @param vec Vector to get the geometric product of
     * @return The geometric product of the two vectors
     */
    @Nonnull
    public Vector5 geometric(@Nonnull Vector5 vec) {
        final Vector5 wedge = wedge(vec);

        return new Vector5(
                dot(vec) + wedge.v,
                wedge.w,
                wedge.x,
                wedge.y,
                wedge.z
        );
    }

    /**
     * Returns the dot product between this vector and the provided vector {@code vec}.
     *
     * @param vec The vector to get the dot product between
     * @return The dot product of the two vectors
     */
    public double dot(@Nonnull Vector5 vec) {
        return v * vec.v + w * vec.w + x * vec.x + y * vec.y + z * vec.z;
    }

    //
    // Clamping
    //

    /**
     * {@inheritDoc}
     *
     * @param vec The vector to get the minimum vector of
     * @return The minimum vector of the two vectors
     * @throws IllegalArgumentException When the provided vector {@code vec}'s length is not {@code 5}
     */
    @Nonnull
    @Override
    public Vector4 min(@Nonnull Vector vec) throws IllegalArgumentException {
        if (vec.length() != 5) {
            throw new IllegalArgumentException("The provided vector does not have a length of 5.");
        }

        return new Vector4(
                Math.min(w, vec.valueAt(0)),
                Math.min(x, vec.valueAt(1)),
                Math.min(y, vec.valueAt(2)),
                Math.min(z, vec.valueAt(3))
        );
    }

    /**
     * {@inheritDoc}
     *
     * @param vec The vector to get the maximum vector of
     * @return The maximum vector of the two vectors
     * @throws IllegalArgumentException When the provided vector {@code vec}'s length is not {@code 5}
     */
    @Nonnull
    @Override
    public Vector4 max(@Nonnull Vector vec) throws IllegalArgumentException {
        if (vec.length() != 5) {
            throw new IllegalArgumentException("The provided vector does not have a length of 5.");
        }

        return new Vector4(
                Math.max(w, vec.valueAt(0)),
                Math.max(x, vec.valueAt(1)),
                Math.max(y, vec.valueAt(2)),
                Math.max(z, vec.valueAt(3))
        );
    }

    /**
     * {@inheritDoc}
     *
     * @param min The minimum allowed values
     * @param max The maximum allowed values
     * @return The clamped vector
     * @throws IllegalArgumentException When either of the provided vectors {@code min} or {@code max} does not have a length of {@code 5}
     */
    @Nonnull
    @Override
    public Vector5 clamp(@Nonnull Vector min, @Nonnull Vector max) throws IllegalArgumentException {
        if (min.length() != 5 || max.length() != 5) {
            throw new IllegalArgumentException("The provided vectors do not have a length of 5.");
        }

        return new Vector5(
                Math.max(Math.min(v, max.valueAt(0)), min.valueAt(0)),
                Math.max(Math.min(w, max.valueAt(1)), min.valueAt(1)),
                Math.max(Math.min(x, max.valueAt(2)), min.valueAt(2)),
                Math.max(Math.min(y, max.valueAt(3)), min.valueAt(3)),
                Math.max(Math.min(z, max.valueAt(4)), min.valueAt(4))
        );
    }

    /**
     * Returns the minimum vector between this vector and the provided vector {@code vec}.
     *
     * @param vec The vector to get the minimum vector of
     * @return The resulting vector
     */
    @Nonnull
    public Vector5 min(@Nonnull Vector5 vec) {
        return new Vector5(
                Math.min(v, vec.v),
                Math.min(w, vec.w),
                Math.min(x, vec.x),
                Math.min(y, vec.y),
                Math.min(z, vec.z)
        );
    }

    /**
     * Returns the maximum vector between this vector and the provided vector {@code vec}.
     *
     * @param vec The vector to get the maximum vector of
     * @return The resulting vector
     */
    @Nonnull
    public Vector5 max(@Nonnull Vector5 vec) {
        return new Vector5(
                Math.max(v, vec.v),
                Math.max(w, vec.w),
                Math.max(x, vec.x),
                Math.max(y, vec.y),
                Math.max(z, vec.z)
        );
    }

    /**
     * Returns the clamped vector of this vector to respect the given boundaries of {@code min} and {@code max}.
     *
     * @param min The minimum allowed values
     * @param max The maximum allowed values
     * @return The resulting vector
     */
    @Nonnull
    public Vector5 clamp(@Nonnull Vector5 min, @Nonnull Vector5 max) {
        return new Vector5(
                Math.max(Math.min(v, max.v), min.v),
                Math.max(Math.min(w, max.w), min.w),
                Math.max(Math.min(x, max.x), min.x),
                Math.max(Math.min(y, max.y), min.y),
                Math.max(Math.min(z, max.z), min.z)
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
    public Vector5 apply(@Nonnull UnaryOperator<Double> operator) {
        return new Vector5(
                operator.apply(v),
                operator.apply(w),
                operator.apply(x),
                operator.apply(y),
                operator.apply(z)
        );
    }

    /**
     * {@inheritDoc}
     *
     * @return The normalized vector
     * @throws UnsupportedOperationException When the magnitude of this vector is exactly zero
     */
    @Nonnull
    @Override
    public Vector5 normalize() throws UnsupportedOperationException {
        try {
            return divide(magnitude());
        } catch (final ArithmeticException e) {
            throw new UnsupportedOperationException("Cannot normalize a vector with no direction. (with a magnitude of zero)");
        }
    }

    /**
     * {@inheritDoc}
     *
     * @return The negation of this vector
     */
    @Nonnull
    @Override
    public Vector5 negate() {
        return new Vector5(-v, -w, -x, -y, -z);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the distance to
     * @return The distance between this vector and {@code v}
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 5}
     */
    @Override
    public double distance(@Nonnull Vector v) throws IllegalArgumentException {
        return subtract(v).magnitude();
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the squared distance to
     * @return The squared distance between this vector and {@code v}
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 5}
     */
    @Override
    public double distance2(@Nonnull Vector v) throws IllegalArgumentException {
        return subtract(v).magnitude2();
    }

    /**
     * Returns the distance between this vector and the provided vector.
     *
     * @param vec The vector to get the distance to
     * @return The distance between this vector and {@code v}
     */
    public double distance(@Nonnull Vector5 vec) {
        final double dv = v - vec.v;
        final double dw = w - vec.w;
        final double dx = x - vec.x;
        final double dy = y - vec.y;
        final double dz = z - vec.z;

        return Math.sqrt(dv * dv + dw * dw + dx * dx + dy * dy + dz * dz);
    }

    /**
     * Returns the squared distance between this vector and the provided vector.
     *
     * @param vec The vector to get the squared distance to
     * @return The squared distance between this vector and {@code v}
     */
    public double distance2(@Nonnull Vector5 vec) {
        final double dv = v - vec.v;
        final double dw = w - vec.w;
        final double dx = x - vec.x;
        final double dy = y - vec.y;
        final double dz = z - vec.z;

        return dv * dv + dw * dw + dx * dx + dy * dy + dz * dz;
    }

    //
    // Equality
    //

    /**
     * {@inheritDoc}
     *
     * @param obj The object to compare to
     * @return {@code true} if the given object is an instance of {@link Vector}, and the components and length are equal
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Vector vec)) return false;
        if (vec.length() != 5) return false;

        return v == vec.valueAt(0) &&
                w == vec.valueAt(1) &&
                x == vec.valueAt(2) &&
                y == vec.valueAt(3) &&
                z == vec.valueAt(4);
    }

    //
    // Serialization
    //

    /**
     * Deserializes a string into a {@link Vector5}.
     *
     * @param input The input to deserialize
     * @return The parsed vector
     * @throws NumberFormatException    When the format is invalid
     * @throws IllegalArgumentException When at least one of the component scalars is non-finite
     */
    @Nonnull
    public static Vector5 parseVector(@Nonnull String input) throws IllegalArgumentException {
        if (!input.startsWith("Vector5{")) {
            throw new NumberFormatException("The provided string does not represent a Vector5.");
        }

        final String cleanInput = input.replaceAll("Vector5\\{|}", "");
        final String[] valueStrings = cleanInput.split(", ");
        final double[] values = {Double.NaN, Double.NaN, Double.NaN, Double.NaN, Double.NaN};

        for (final String valueString : valueStrings) {
            final String[] split = valueString.split("=");
            values[switch (split[0]) {
                case "v" -> 0;
                case "w" -> 1;
                case "x" -> 2;
                case "y" -> 3;
                case "z" -> 4;
                default -> throw new NumberFormatException("The provided string does not represent a Vector5.");
            }] = Double.parseDouble(split[1]);
        }

        return new Vector5(values);
    }

    /**
     * {@inheritDoc}
     *
     * @return The string representation of this vector
     */
    @Override
    @Nonnull
    public String toString() {
        return "Vector5{" +
                "v=" + v +
                ", w=" + w +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
