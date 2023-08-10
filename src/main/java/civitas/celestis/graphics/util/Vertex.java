package civitas.celestis.graphics.util;

import civitas.celestis.math.quaternion.Quaternion;
import civitas.celestis.math.vector.Vector3;
import de.javagl.obj.FloatTuple;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.function.UnaryOperator;

/**
 * A specialized {@link Vector3} used in a graphical context.
 */
public final class Vertex extends Vector3 {
    //
    // Constants
    //

    /**
     * Represents origin.
     */
    public static final Vertex ZERO = new Vertex(0, 0, 0);

    /**
     * The directional unit vector facing positive X.
     */
    public static final Vertex RIGHT = new Vertex(1, 0, 0);

    /**
     * The directional unit vector facing positive Y.
     */
    public static final Vertex UP = new Vertex(0, 1, 0);

    /**
     * The directional unit vector facing positive Z.
     */
    public static final Vertex FRONT = new Vertex(0, 0, 1);

    /**
     * The directional unit vector facing negative X.
     */
    public static final Vertex LEFT = new Vertex(-1, 0, 0);

    /**
     * The directional unit vector facing negative Y.
     */
    public static final Vertex DOWN = new Vertex(0, -1, 0);

    /**
     * The directional unit vector facing negative Z.
     */
    public static final Vertex BACK = new Vertex(0, 0, -1);

    //
    // Constructors
    //

    /**
     * Creates a new vertex.
     *
     * @param x The X component of this vertex
     * @param y The Y component of this vertex
     * @param z The Z component of this vertex
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
     * Creates a new vertex from a {@link Vector3}.
     *
     * @param other The vector to copy values from
     */
    public Vertex(@Nonnull Vector3 other) {
        super(other);
    }

    /**
     * Creates a new vertex from a {@link FloatTuple}.
     * This does not perform any modifications to the values or axes.
     *
     * @param floatTuple The tuple to copy values from
     */
    public Vertex(@Nonnull FloatTuple floatTuple) {
        super(floatTuple.getX(), floatTuple.getY(), floatTuple.getZ());
    }

    //
    // Setters
    //

    /**
     * Changes only the X component of this vertex.
     *
     * @param x The new value to set the X component to
     * @return The resulting vertex
     */
    @Nonnull
    @Override
    public Vertex x(double x) {
        return new Vertex(super.x(x));
    }

    /**
     * Changes only the Y component of this vertex.
     *
     * @param y The new value to set the Y component to
     * @return The resulting vertex
     */
    @Nonnull
    @Override
    public Vertex y(double y) {
        return new Vertex(super.y(y));
    }

    /**
     * Changes only the Z component of this vertex.
     *
     * @param z The new value to set the Z component to
     * @return The resulting vertex
     */
    @Nonnull
    @Override
    public Vertex z(double z) {
        return new Vertex(super.z(z));
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
     * @param s Scalar to multiply with this vertex
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
     * @throws ArithmeticException When the denominator is zero
     */
    @Nonnull
    @Override
    public Vertex divide(double s) throws ArithmeticException {
        return new Vertex(super.divide(s));
    }

    /**
     * Adds a vector to this vertex.
     *
     * @param v Vector to add to this vertex
     * @return The resulting vertex
     */
    @Nonnull
    @Override
    public Vertex add(@Nonnull Vector3 v) {
        return new Vertex(super.add(v));
    }

    /**
     * Subtracts another vector from this vertex.
     *
     * @param v Vector to subtract from this vertex
     * @return The resulting vertex
     */
    @Nonnull
    @Override
    public Vertex subtract(@Nonnull Vector3 v) {
        return new Vertex(super.subtract(v));
    }

    /**
     * Returns the cross product of this vertex and a vector.
     *
     * @param v Vector to get the cross-product of
     * @return The cross product of the two vectors
     */
    @Nonnull
    @Override
    public Vertex cross(@Nonnull Vector3 v) {
        return new Vertex(super.cross(v));
    }

    //
    // Utility
    //

    /**
     * Given an array of vertices, this returns the sum of the vertices.
     *
     * @param vertices Vertices to sum
     * @return The sum of the given vertices
     */
    @Nonnull
    public static Vertex sum(@Nonnull Vertex... vertices) {
        return new Vertex(Vector3.sum(vertices));
    }

    /**
     * Given an array of vertices, this returns the average of the vectors.
     * This is equivalent to the geometric centroid of the vertices.
     *
     * @param vertices Vertices to average
     * @return The average of the given vertices
     */
    @Nonnull
    public static Vertex avg(@Nonnull Vertex... vertices) {
        return sum(vertices).divide(vertices.length);
    }

    /**
     * Given an array of vertices, this returns the maximum vertex.
     *
     * @param vertices Vertices to get maximum of
     * @return The maximum vertex of the given vertices
     */
    @Nonnull
    public static Vertex max(@Nonnull Vertex... vertices) {
        double x = -Double.MAX_VALUE;
        double y = -Double.MAX_VALUE;
        double z = -Double.MAX_VALUE;

        for (final Vertex vertex : vertices) {
            x = Math.max(x, vertex.x());
            y = Math.max(y, vertex.y());
            z = Math.max(z, vertex.z());
        }

        return new Vertex(x, y, z);
    }

    /**
     * Given an array of vertices, this returns the minimum vertex.
     *
     * @param vertices Vertices to get minimum of
     * @return The minimum vertex of the given vertices
     */
    @Nonnull
    public static Vertex min(@Nonnull Vertex... vertices) {
        double x = Double.MAX_VALUE;
        double y = Double.MAX_VALUE;
        double z = Double.MAX_VALUE;

        for (final Vertex vertex : vertices) {
            x = Math.min(x, vertex.x());
            y = Math.min(y, vertex.y());
            z = Math.min(z, vertex.z());
        }

        return new Vertex(x, y, z);
    }

    /**
     * Given three points which represent the vertices of a triangular surface, this calculates the surface normal.
     * This method assumes that the given coordinates are in counter-clockwise order.
     *
     * @param a The first vertex
     * @param b The second vertex
     * @param c The third vertex
     * @return The surface normal vertex
     */
    @Nonnull
    public static Vertex normal(@Nonnull Vertex a, @Nonnull Vertex b, @Nonnull Vertex c) {
        final Vertex ab = b.subtract(a);
        final Vertex ac = c.subtract(a);

        return ab.cross(ac);
    }

    /**
     * Applies given operator to all components of this vertex.
     *
     * @param operator The operator to apply to all components of this vertex
     * @return The resulting vertex
     */
    @Nonnull
    @Override
    public Vertex apply(@Nonnull UnaryOperator<Double> operator) {
        return new Vertex(super.apply(operator));
    }

    /**
     * Negates this vertex.
     *
     * @return The negated vertex
     */
    @Nonnull
    @Override
    public Vertex negate() {
        return new Vertex(super.negate());
    }

    /**
     * Negates the X coordinate of this vertex.
     *
     * @return The negated vertex
     */
    @Nonnull
    public Vertex negateX() {
        return new Vertex(-x(), y(), z());
    }

    /**
     * Negates the Y coordinate of this vertex.
     *
     * @return The negated vertex
     */
    @Nonnull
    public Vertex negateY() {
        return new Vertex(x(), -y(), z());
    }

    /**
     * Negates the Z coordinate of this vertex.
     *
     * @return The negated vertex
     */
    @Nonnull
    public Vertex negateZ() {
        return new Vertex(x(), y(), -z());
    }

    /**
     * Negates the X and Y coordinates of this vertex.
     *
     * @return The negated vertex
     */
    @Nonnull
    public Vertex negateXY() {
        return new Vertex(-x(), -y(), z());
    }

    /**
     * Negates the X and Z coordinates of this vertex.
     *
     * @return The negated vertex
     */
    @Nonnull
    public Vertex negateXZ() {
        return new Vertex(-x(), y(), -z());
    }

    /**
     * Negates the Y and Z coordinates of this vertex.
     *
     * @return The negated vertex
     */
    @Nonnull
    public Vertex negateYZ() {
        return new Vertex(x(), -y(), -z());
    }

    /**
     * Swaps the X and Y coordinates.
     *
     * @return The resulting vertex
     */
    @Nonnull
    public Vertex swapXY() {
        return new Vertex(y(), x(), z());
    }

    /**
     * Swaps the X and Z coordinates.
     *
     * @return The resulting vertex
     */
    @Nonnull
    public Vertex swapXZ() {
        return new Vertex(z(), y(), x());
    }

    /**
     * Swaps the Y and Z coordinates.
     *
     * @return The resulting vertex
     */
    @Nonnull
    public Vertex swapYZ() {
        return new Vertex(x(), z(), y());
    }

    /**
     * Normalizes this vertex.
     * Unlike {@link Vector3#normalize()}, the return value of this method is guaranteed to have a magnitude of {@code 1}.
     *
     * @return The normalized vertex
     * @throws ArithmeticException When the magnitude of this vertex is zero
     */
    @Nonnull
    @Override
    public Vertex normalize() {
        final double m = magnitude();
        if (m == 0) throw new ArithmeticException("Cannot normalize a vertex of zero magnitude.");

        return new Vertex(x() / m, y() / m, z() / m);
    }

    /**
     * Returns the maximum vertex between this vertex and the provided vector {@code v}.
     * This is calculated by applying {@link Math#max(double, double)} to all components.
     *
     * @param v Vector to get the maximum between
     * @return Maximum vertex of the two vectors
     */
    @Nonnull
    @Override
    public Vertex max(@Nonnull Vector3 v) {
        return new Vertex(super.max(v));
    }

    /**
     * Returns the minimum vertex between this vertex and the provided vector {@code v}.
     * This is calculated by applying {@link Math#min(double, double)} to all components.
     *
     * @param v Vector to get the minimum between
     * @return Minimum vertex of the two vectors
     */
    @Nonnull
    @Override
    public Vertex min(@Nonnull Vector3 v) {
        return new Vertex(super.min(v));
    }

    /**
     * Given a minimum and maximum allowed range, this returns a vertex which respects the given bounds.
     *
     * @param min Minimum allowed boundaries
     * @param max Maximum allowed boundaries
     * @return The clamped vertex
     */
    @Nonnull
    @Override
    public Vertex clamp(@Nonnull Vector3 min, @Nonnull Vector3 max) {
        return new Vertex(super.clamp(min, max));
    }

    /**
     * Transforms this vertex into a relative coordinate system.
     *
     * @param origin    The new origin of this vertex
     * @param rotation  The rotation to apply to this vertex
     * @param inflation The inflation to apply to this vertex
     * @return The transformed vertex
     */
    @Nonnull
    public Vertex transform(@Nonnull Vector3 origin, @Nonnull Quaternion rotation, double inflation) {
        // Component-wise arithmetic to minimize method calls
        final double x = (x() - origin.x()) * inflation;
        final double y = (y() - origin.y()) * inflation;
        final double z = (z() - origin.z()) * inflation;

        // Inlined calculation to minimize method calls
        final Quaternion result = rotation.multiply(new Quaternion(0, x, y, z)).multiply(rotation.conjugate());

        return new Vertex(result.x(), result.y(), result.z());
    }

    /**
     * Rotates this vertex by a rotation quaternion.
     *
     * @param rq The rotation quaternion to apply to this vertex
     * @return The rotated vertex
     */
    @Nonnull
    @Override
    public Vertex rotate(@Nonnull Quaternion rq) {
        return new Vertex(super.rotate(rq));
    }

    /**
     * Converts this vertex to a 2D position with the provided focal length.
     * <p>
     * This assumes that this vertex is already converted to a relative coordinate system,
     * the Z coordinate represents depth, and X and Y represent width and height respectively.
     * </p>
     *
     * @param focalLength The focal length to use in conversion
     * @return The converted position
     */
    @Nonnull
    public Position position(double focalLength) {
        final double focalLengthDenominator = focalLength + z();

        return new Position(
                (focalLength / focalLengthDenominator) * x(),
                (focalLength / focalLengthDenominator) * y()
        );
    }

    /**
     * Checks for equality between this vertex an object.
     *
     * @param obj Object to compare this vertex to
     * @return {@code true} if the other object is a vertex, and the values are equal
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Vertex v)) return false;
        return x() == v.x() && y() == v.y() && z() == v.z();
    }

    //
    // Serialization
    //

    /**
     * Parses a string into a {@link Vertex}.
     *
     * @param s String to parse into a vertex
     * @return The parsed string
     * @throws NumberFormatException    When the format is invalid
     * @throws IllegalArgumentException When at least one of the components is non-finite
     */
    @Nonnull
    public static Vertex parseVertex(@Nonnull String s) throws IllegalArgumentException {
        final String[] components = s.replaceAll("Vertex\\{|}", "").split(", ");
        final double[] values = new double[3];

        for (final String component : components) {
            final String[] split = component.split("=");
            if (split.length != 2) throw new NumberFormatException("The provided string does not represent a Vertex.");

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
     * Serializes this {@link Vertex} into a string.
     *
     * @return The string representation of this vertex
     */
    @Override
    @Nonnull
    public String toString() {
        return "Vertex{" +
                "x=" + x() +
                ", y=" + y() +
                ", z=" + z() +
                '}';
    }

}
