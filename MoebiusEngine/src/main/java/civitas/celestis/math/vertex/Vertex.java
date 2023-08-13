package civitas.celestis.math.vertex;

import civitas.celestis.math.Numbers;
import civitas.celestis.math.quaternion.Quaternion;
import civitas.celestis.math.vector.Vector;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.math.vector.VectorMapper;
import de.javagl.obj.FloatTuple;
import jakarta.annotation.Nonnull;

import java.util.function.UnaryOperator;

/**
 * A three-dimensional vector used to represent graphical data.
 * Like {@link Vector3}, this class is also immutable.
 */
public class Vertex extends Vector3 {
    //
    // Constants
    //

    /**
     * The vertex {{@code 0, 0, 0}}. Represents origin.
     */
    public static final Vertex ORIGIN = new Vertex(ZERO);

    /**
     * The positive X unit vertex.
     */
    public static final Vertex RIGHT = new Vertex(POSITIVE_X);

    /**
     * The positive Y unit vertex.
     */
    public static final Vertex UP = new Vertex(POSITIVE_Y);

    /**
     * The positive Z unit vertex.
     */
    public static final Vertex FRONT = new Vertex(POSITIVE_Z);

    /**
     * The negative X unit vertex.
     */
    public static final Vertex LEFT = new Vertex(NEGATIVE_X);

    /**
     * The negative Y unit vertex.
     */
    public static final Vertex DOWN = new Vertex(NEGATIVE_Y);

    /**
     * The negative Z unit vertex.
     */
    public static final Vertex BACK = new Vertex(NEGATIVE_Z);

    //
    // Static Utilities
    //

    /**
     * Converts a Wavefront {@link FloatTuple} to a Moebius {@link Vertex}.
     * This swaps the X and Z components to compensate for the differences in coordinate systems.
     *
     * @param in The input tuple
     * @return The converted vertex
     */
    @Nonnull
    public static Vertex fromObjTuple(@Nonnull FloatTuple in) {
        return VectorMapper.VERTEX_SWAP_X_Z.apply(new Vertex(in));
    }

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
    }

    /**
     * Creates a new vertex.
     *
     * @param values An array containing the values of this vertex
     */
    public Vertex(@Nonnull double[] values) {
        super(values);
    }

    /**
     * Creates a new vertex from a {@link FloatTuple}.
     *
     * @param objTuple The tuple to copy values from
     */
    public Vertex(@Nonnull FloatTuple objTuple) {
        super(objTuple.getX(), objTuple.getY(), objTuple.getZ());
    }

    /**
     * Creates a new vertex.
     *
     * @param other The vector to copy values from
     */
    public Vertex(@Nonnull Vector other) {
        super(other);
    }

    /**
     * Creates a new vertex.
     *
     * @param other The vector to copy values from
     */
    public Vertex(@Nonnull Vector3 other) {
        super(other);
    }

    //
    // Randomization
    //

    /**
     * Returns a random normalized unit vertex.
     *
     * @return A random unit vertex
     */
    @Nonnull
    public static Vertex random() {
        return new Vertex(Numbers.random(-1, 1), Numbers.random(-1, 1), Numbers.random(-1, 1)).normalize();
    }

    //
    // Arithmetic
    //

    /**
     * Adds a scalar to this vertex.
     *
     * @param s Scalar to add to this vertex
     * @return The resulting vertex
     */
    @Nonnull
    @Override
    public Vertex add(double s) {
        return new Vertex(super.add(s));
    }

    /**
     * Subtracts a scalar from this vertex.
     *
     * @param s Scalar to subtract from this vertex
     * @return The resulting vertex
     */
    @Nonnull
    @Override
    public Vertex subtract(double s) {
        return new Vertex(super.subtract(s));
    }

    /**
     * Multiplies this vertex by a scalar.
     *
     * @param s Scalar to multiply this vertex by
     * @return The resulting vertex
     */
    @Nonnull
    @Override
    public Vertex multiply(double s) {
        return new Vertex(super.multiply(s));
    }

    /**
     * Divides this vertex by a scalar.
     *
     * @param s Scalar to divide this vertex by
     * @return The resulting vertex
     * @throws ArithmeticException When the denominator {@code s} is zero
     */
    @Nonnull
    @Override
    public Vertex divide(double s) throws ArithmeticException {
        return new Vertex(super.divide(s));
    }

    /**
     * Adds a vector to this vertex
     *
     * @param v The vector to add to this vertex
     * @return The resulting vertex
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 3}
     */
    @Nonnull
    @Override
    public Vertex add(@Nonnull Vector v) throws IllegalArgumentException {
        return new Vertex(super.add(v));
    }

    /**
     * Subtracts a vector from this vertex.
     *
     * @param v The vector to subtract from this vertex
     * @return The resulting vertex
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 3}
     */
    @Nonnull
    @Override
    public Vertex subtract(@Nonnull Vector v) throws IllegalArgumentException {
        return new Vertex(super.subtract(v));
    }

    /**
     * This operation is not supported.
     * Use {@link Vertex#cross(Vector)} it {@link Vertex#dot(Vector)} instead.
     *
     * @param v The vector to multiply this vertex by
     * @return This method cannot return a value
     * @throws UnsupportedOperationException Always throws an exception
     */
    @Nonnull
    @Override
    public Vertex multiply(@Nonnull Vector v) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("The multiplication of vertices has no default definition.");
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector on the right of this operation
     * @return The cross product between this vertex and the provided vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 3}
     */
    @Nonnull
    @Override
    public Vertex cross(@Nonnull Vector v) throws IllegalArgumentException {
        return new Vertex(super.cross(v));
    }

    /**
     * Adds a vector to this vertex.
     *
     * @param v The vector to add to this vertex
     * @return The resulting vertex
     */
    @Nonnull
    @Override
    public Vertex add(@Nonnull Vector3 v) {
        return new Vertex(super.add(v));
    }

    /**
     * Subtracts a vector from this vertex.
     *
     * @param v The vector to subtract from this vertex
     * @return The resulting vertex
     */
    @Nonnull
    @Override
    public Vertex subtract(@Nonnull Vector3 v) {
        return new Vertex(super.subtract(v));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector on the right of this operation
     * @return The cross product between this vertex and the provided vector
     */
    @Nonnull
    @Override
    public Vertex cross(@Nonnull Vector3 v) {
        return new Vertex(super.cross(v));
    }

    //
    // Clamping
    //

    /**
     * Returns the minimum vertex.
     *
     * @param v The vector to get the minimum vertex of
     * @return The minimum vertex
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 3}
     */
    @Nonnull
    @Override
    public Vertex min(@Nonnull Vector v) throws IllegalArgumentException {
        return new Vertex(super.min(v));
    }

    /**
     * Returns the maximum vertex.
     *
     * @param v The vector to get the maximum vertex of
     * @return The maximum vertex
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is not {@code 3}
     */
    @Nonnull
    @Override
    public Vertex max(@Nonnull Vector v) throws IllegalArgumentException {
        return new Vertex(super.max(v));
    }

    /**
     * Clamps this vertex to respect the given boundaries.
     *
     * @param min The minimum allowed values
     * @param max The maximum allowed values
     * @return The clamped vertex
     * @throws IllegalArgumentException When the provided vector {@code min} or {@code max}'s length is not {@code 3}
     */
    @Nonnull
    @Override
    public Vertex clamp(@Nonnull Vector min, @Nonnull Vector max) throws IllegalArgumentException {
        return new Vertex(super.clamp(min, max));
    }

    /**
     * Returns the minimum vertex.
     *
     * @param v The vector to get the minimum vertex of
     * @return The minimum vertex
     */
    @Nonnull
    @Override
    public Vertex min(@Nonnull Vector3 v) {
        return new Vertex(super.min(v));
    }

    /**
     * Returns the maximum vertex.
     *
     * @param v The vector to get the maximum vertex of
     * @return The maximum vertex
     */
    @Nonnull
    @Override
    public Vertex max(@Nonnull Vector3 v) {
        return new Vertex(super.max(v));
    }

    /**
     * Clamps this vertex to respect the given boundaries
     *
     * @param min The minimum allowed values
     * @param max The maximum allowed values
     * @return The clamped vertex
     */
    @Nonnull
    @Override
    public Vertex clamp(@Nonnull Vector3 min, @Nonnull Vector3 max) {
        return new Vertex(super.clamp(min, max));
    }

    //
    // Utility
    //

    /**
     * Applies given operator to each coordinate of this vertex.
     *
     * @param operator The operator to apply to each component of this vertex
     * @return The resulting vertex
     */
    @Nonnull
    @Override
    public Vertex apply(@Nonnull UnaryOperator<Double> operator) {
        return new Vertex(super.apply(operator));
    }

    /**
     * Normalizes this vertex.
     *
     * @return The normalized vertex
     * @throws UnsupportedOperationException When the magnitude of this vertex is exactly {@code 1}
     */
    @Nonnull
    @Override
    public Vertex normalize() throws UnsupportedOperationException {
        return new Vertex(super.normalize());
    }

    /**
     * Negates this vertex.
     *
     * @return The negation of this vertex
     */
    @Nonnull
    @Override
    public Vertex negate() {
        return new Vertex(super.negate());
    }

    /**
     * Rotates this vertex by a rotation quaternion.
     *
     * @param rq The rotation quaternion to use
     * @return The rotated vertex
     */
    @Nonnull
    @Override
    public Vertex rotate(@Nonnull Quaternion rq) {
        return new Vertex(super.rotate(rq));
    }

    /**
     * Transforms this vertex to a relative coordinate system,
     * and adds inflation to better represents its size in a graphical context.
     *
     * @param origin   The new origin of this vertex
     * @param rotation The rotation to apply to this vertex
     * @param scale    The scale factor of the inflation
     * @return The transformed vertex
     */
    @Nonnull
    public Vertex transform(@Nonnull Vector3 origin, @Nonnull Quaternion rotation, double scale) {
        final double scaledX = (x - origin.x()) * scale;
        final double scaledY = (y - origin.y()) * scale;
        final double scaledZ = (z - origin.z()) * scale;

        return new Vertex(scaledX, scaledY, scaledZ).rotate(rotation);
    }

    /**
     * Transforms this vertex to an absolute coordinate system.
     * This is used to translate local coordinates into absolute coordinates.
     *
     * @param offset   The offset of the local coordinate system's origin relative to absolute zero
     * @param rotation The rotation to apply before translating
     * @return The absolute coordinates of this vertex
     */
    @Nonnull
    public Vertex absolute(@Nonnull Vector3 offset, @Nonnull Quaternion rotation) {
        return rotate(rotation).add(offset);
    }

    //
    // Serialization
    //

    /**
     * Deserializes a string into a {@link Vertex}.
     *
     * @param input The input to deserialize
     * @return The parsed vertex
     * @throws NumberFormatException    When the format is invalid
     * @throws IllegalArgumentException When at least one of the component scalars is non-finite
     */
    @Nonnull
    public static Vertex parseVertex(@Nonnull String input) throws IllegalArgumentException {
        if (!input.startsWith("Vertex{")) {
            throw new NumberFormatException("The provided string does not represent a Vertex.");
        }

        final String cleanInput = input.replaceAll("Vertex\\{|}", "");
        final String[] valueStrings = cleanInput.split(", ");
        final double[] values = {Double.NaN, Double.NaN, Double.NaN};

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
