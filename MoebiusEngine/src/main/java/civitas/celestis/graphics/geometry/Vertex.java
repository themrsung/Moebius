package civitas.celestis.graphics.geometry;

import civitas.celestis.math.Numbers;
import civitas.celestis.math.complex.Quaternion;
import civitas.celestis.math.vector.Float3;
import civitas.celestis.math.vector.Int3;
import civitas.celestis.math.vector.Long3;
import civitas.celestis.math.vector.Vector3;
import jakarta.annotation.Nonnull;

import java.util.function.UnaryOperator;

/**
 * A point within a 3D coordinate system.
 * The coordinates are represented as {@code double} in the notation X, Y, and Z.
 * <p>
 * The X, Y, Z axes represent width, height, depth respectively.
 * Positive X faces the right, positive Y faces upwards, and positive Z faces forwards.
 * </p>
 * <p>
 * Due to {@link Vertex} inheriting {@link Vector3}, all arithmetic operations
 * can be performed with a vector as the input parameter. Thus, other subclasses of
 * {@link Vector3} are also compatible with a vertex. Vertices are mainly used in a graphical context.
 * </p>
 * <p>
 * Unlike its parent class, {@link Vertex} does not allow the use of non-finite values.
 * Non-finite values include {@link Double#NaN}, {@link Double#POSITIVE_INFINITY}, and {@link Double#NEGATIVE_INFINITY}.
 * </p>
 */
public class Vertex extends Vector3 {
    //
    // Constants
    //

    /**
     * Absolute zero. This vector represents origin.
     */
    public static final Vertex ORIGIN = new Vertex(0, 0, 0);

    /**
     * The positive X unit vector.
     */
    public static final Vertex RIGHT = new Vertex(1, 0, 0);

    /**
     * The positive Y unit vector.
     */
    public static final Vertex UP = new Vertex(0, 1, 0);

    /**
     * The positive Z unit vector.
     */
    public static final Vertex FRONT = new Vertex(0, 0, 1);

    /**
     * The negative X unit vector.
     */
    public static final Vertex LEFT = new Vertex(-1, 0, 0);

    /**
     * The negative Y unit vector.
     */
    public static final Vertex DOWN = new Vertex(0, -1, 0);

    /**
     * The negative Z unit vector.
     */
    public static final Vertex BACK = new Vertex(0, 0, -1);
    //
    // Constructors
    //

    /**
     * Creates a new vertex.
     *
     * @param x The X coordinate of this vertex
     * @param y The Y coordinate of this vertex
     * @param z The Z coordinate of this vertex
     */
    public Vertex(double x, double y, double z) {
        super(x, y, z);
        enforceNonFinite();
    }

    /**
     * Creates a new vertex.
     *
     * @param values The XYZ component values in array form
     */
    public Vertex(@Nonnull double[] values) {
        super(values);
        enforceNonFinite();
    }

    /**
     * Creates a new vertex.
     *
     * @param v The vector of which to copy values from
     */
    public Vertex(@Nonnull Vector3 v) {
        super(v);
        enforceNonFinite();
    }

    /**
     * Creates a new vertex.
     *
     * @param v The vector of which to copy values from
     */
    public Vertex(@Nonnull Float3 v) {
        super(v);
        enforceNonFinite();
    }

    /**
     * Creates a new vertex.
     *
     * @param v The vector of which to copy values from
     */
    public Vertex(@Nonnull Long3 v) {
        super(v);
    }

    /**
     * Creates a new vertex.
     *
     * @param v The vector of which to copy values from
     */
    public Vertex(@Nonnull Int3 v) {
        super(v);
    }

    /**
     * Called to enforce the non-finite constraint.
     */
    protected void enforceNonFinite() {
        Numbers.requireFinite(x + y + z);
    }

    //
    // Arithmetic
    //

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to add to this vertex
     * @return The resulting vertex
     */
    @Nonnull
    @Override
    public Vertex add(double s) {
        return new Vertex(x + s, y + s, z + s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to subtract from this vertex
     * @return The resulting vertex
     */
    @Nonnull
    @Override
    public Vertex subtract(double s) {
        return new Vertex(x - s, y - s, z - s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to multiply to this vertex
     * @return The resulting vertex
     */
    @Nonnull
    @Override
    public Vertex multiply(double s) {
        return new Vertex(x * s, y * s, z * s);
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to divide this vertex by
     * @return The resulting vertex
     * @throws ArithmeticException When the denominator {@code s} is zero
     */
    @Nonnull
    @Override
    public Vertex divide(double s) throws ArithmeticException {
        if (s == 0) {
            throw new ArithmeticException("Cannot divide by zero.");
        }

        return new Vertex(x / s, y / s, z / s);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to add to this vertex
     * @return The resulting vertex
     */
    @Nonnull
    @Override
    public Vertex add(@Nonnull Vector3 v) {
        return new Vertex(x + v.x(), y + v.y(), z + v.z());
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to subtract from this vertex
     * @return The resulting vertex
     */
    @Nonnull
    @Override
    public Vertex subtract(@Nonnull Vector3 v) {
        return new Vertex(x - v.x(), y - v.y(), z - v.z());
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to get the cross product between
     * @return The resulting vertex
     */
    @Nonnull
    @Override
    public Vertex cross(@Nonnull Vector3 v) {
        final double vx = v.x();
        final double vy = v.y();
        final double vz = v.z();

        return new Vertex(y * vz - z * vy, z * vx - x * vz, x * vy - y * vx);
    }

    //
    // Normalization
    //

    /**
     * Normalizes this vertex.
     * If this vertex cannot be normalized (the magnitude is zero),
     * this will return {@link Vertex#ORIGIN} instead of failing.
     *
     * @return The normalized vertex of this vertex
     */
    @Nonnull
    @Override
    public Vertex normalize() {
        final double n = norm();
        if (n == 0) return ORIGIN;
        return new Vertex(x / n, y / n, z / n);
    }

    /**
     * Negates this vertex.
     * This flips this vertex around all axes.
     *
     * @return The negation of this vertex
     */
    @Nonnull
    @Override
    public Vertex negate() {
        return new Vertex(-x, -y, -z);
    }

    //
    // Transformation
    //

    /**
     * {@inheritDoc}
     *
     * @param transformer The function to apply to each component of this vertex
     * @return The resulting vertex
     */
    @Nonnull
    @Override
    public Vertex transform(@Nonnull UnaryOperator<Double> transformer) {
        return new Vertex(transformer.apply(x), transformer.apply(y), transformer.apply(z));
    }

    //
    // Clamping
    //

    /**
     * {@inheritDoc}
     *
     * @param v The vector to compare to
     * @return The minimum vertex
     */
    @Nonnull
    @Override
    public Vertex min(@Nonnull Vector3 v) {
        return new Vertex(Math.min(x, v.x()), Math.min(y, v.y()), Math.min(z, v.z()));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to compare to
     * @return The maximum vertex
     */
    @Nonnull
    @Override
    public Vertex max(@Nonnull Vector3 v) {
        return new Vertex(Math.max(x, v.x()), Math.max(y, v.y()), Math.max(z, v.z()));
    }

    /**
     * {@inheritDoc}
     *
     * @param min The minimum allowed vertex value
     * @param max The maximum allowed vertex value
     * @return The clamped vertex
     */
    @Nonnull
    @Override
    public Vertex clamp(@Nonnull Vector3 min, @Nonnull Vector3 max) {
        return new Vertex(
                Numbers.clamp(x, min.x(), max.x()),
                Numbers.clamp(y, min.y(), max.y()),
                Numbers.clamp(z, min.z(), max.z())
        );
    }

    //
    // Rotation
    //

    /**
     * {@inheritDoc}
     *
     * @param rq The rotation quaternion to apply
     * @return The rotated vertex
     */
    @Nonnull
    @Override
    public Vertex rotate(@Nonnull Quaternion rq) {
        return new Vertex(super.rotate(rq));
    }

    //
    // Serialization
    //

    /**
     * Deserializes a string into a {@link Vertex}.
     *
     * @param input The input to deserialize
     * @return The parsed vertex
     * @throws NumberFormatException When the format is invalid
     */
    @Nonnull
    public static Vertex parseVector(@Nonnull String input) throws NumberFormatException {
        if (!input.startsWith("Vertex{")) {
            throw new NumberFormatException("The provided string does not represent a Vertex.");
        }

        final String cleanInput = input.replaceAll("Vertex\\{|}", "");
        final String[] valueStrings = cleanInput.split(", ");
        final double[] values = new double[3];

        for (final String valueString : valueStrings) {
            final String[] split = valueString.split("=");
            values[switch (split[0]) {
                case "x" -> 0;
                case "y" -> 1;
                case "z" -> 2;
                default -> throw new NumberFormatException("The provided string does not represent a Vertex.");
            }] = Double.parseDouble(split[1]);
        }

        return new Vertex(values);
    }

    /**
     * {@inheritDoc}
     *
     * @return The string representation of this vertex
     */
    @Override
    @Nonnull
    public String toString() {
        return "Vertex{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
