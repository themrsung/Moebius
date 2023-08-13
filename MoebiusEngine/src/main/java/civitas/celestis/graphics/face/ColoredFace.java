package civitas.celestis.graphics.face;

import civitas.celestis.graphics.color.RichColor;
import civitas.celestis.graphics.raytracing.Ray;
import civitas.celestis.math.Geometry;
import civitas.celestis.math.Numbers;
import civitas.celestis.math.vector.Vector;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.math.vertex.Vertex;
import civitas.celestis.util.alphabetical.AlphabeticalTuple;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.function.UnaryOperator;

/**
 * A face with a single color component.
 */
public class ColoredFace extends AlphabeticalTuple<Vertex> implements Face {
    //
    // Constructors
    //

    /**
     * Creates a new colored face.
     *
     * @param a     The first vertex of this face
     * @param b     The second vertex of this face
     * @param c     The third vertex of this face
     * @param color The initial color of this face
     */
    public ColoredFace(@Nonnull Vertex a, @Nonnull Vertex b, @Nonnull Vertex c, @Nonnull RichColor color) {
        super(a, b, c);
        this.color = color;
    }

    /**
     * Creates a new colored face.
     *
     * @param other The face to copy values from
     */
    public ColoredFace(@Nonnull ColoredFace other) {
        super(other);
        this.color = other.color;
    }

    //
    // Variables
    //

    @Nonnull
    private RichColor color;

    //
    // Getters
    //

    /**
     * Returns the current color of this face.
     *
     * @return The color of this face
     */
    @Nonnull
    public RichColor getColor() {
        return color;
    }

    /**
     * Sets the color of this face.
     *
     * @param color The color of this face
     */
    public void setColor(@Nonnull RichColor color) {
        this.color = color;
    }

    /**
     * {@inheritDoc}
     *
     * @return The surface normal vector of this face
     */
    @Nonnull
    @Override
    public Vector3 getNormal() {
        final Vector3 ab = b.subtract(a);
        final Vector3 ac = c.subtract(a);

        return ab.cross(ac);
    }

    /**
     * {@inheritDoc}
     *
     * @return The geometric centroid of this face
     */
    @Nonnull
    @Override
    public Vector3 getCentroid() {
        return Numbers.avg(a, b, c);
    }

    /**
     * Returns an array containing the vertices of this face.
     *
     * @return An array of the vertices of this face
     */
    @Nonnull
    @Override
    public Vertex[] toArray() {
        return new Vertex[]{a, b, c};
    }

    //
    // Methods
    //

    /**
     * {@inheritDoc}
     *
     * @param incidentRay The incident ray
     * @return The point of intersection if found, {@code null} if not
     */
    @Nullable
    @Override
    public Vector3 getIntersection(@Nonnull Ray incidentRay) {
        return Geometry.intersection(this, incidentRay);
    }

    /**
     * {@inheritDoc}
     *
     * @param incidentVector The incident directional vector
     * @return The reflection vector
     */
    @Nonnull
    @Override
    public Vector3 getReflectionVector(@Nonnull Vector3 incidentVector) {
        return Geometry.reflect(incidentVector, getNormal());
    }

    //
    // Utility
    //

    /**
     * {@inheritDoc}
     *
     * @param operator The operator to apply to each vertex of this face
     * @return The resulting face
     */
    @Nonnull
    @Override
    public ColoredFace apply(@Nonnull UnaryOperator<Vertex> operator) {
        return new ColoredFace(
                operator.apply(a),
                operator.apply(b),
                operator.apply(c),
                color
        );
    }

    //
    // Serialization
    //

    /**
     * Deserializes a string into a {@link ColoredFace}.
     *
     * @param input The input string to parse
     * @return The parsed face
     * @throws IllegalArgumentException When the format is invalid
     */
    @Nonnull
    public static ColoredFace parseFace(@Nonnull String input) throws IllegalArgumentException {
        if (!input.startsWith("ColoredFace{")) {
            throw new IllegalArgumentException("Given string does not represent a ColoredFace.");
        }

        final String[] lines = input.split("\n");

        final Vector[] components = new Vector[4];

        for (final String line : lines) {
            final String[] splitByEquals = line.split("=", 2);
            if (splitByEquals.length != 2) continue;

            switch (splitByEquals[0].trim()) {
                case "a" -> components[0] = Vertex.parseVertex(splitByEquals[1]);
                case "b" -> components[1] = Vertex.parseVertex(splitByEquals[1]);
                case "c" -> components[2] = Vertex.parseVertex(splitByEquals[1]);
                case "color" -> components[3] = RichColor.parseColor(splitByEquals[1]);
            }
        }

        return new ColoredFace(
                (Vertex) components[0],
                (Vertex) components[1],
                (Vertex) components[2],
                (RichColor) components[3]
        );
    }

    //
    // Serialization
    //

    /**
     * {@inheritDoc}
     *
     * @return The string representation of this face
     */
    @Override
    @Nonnull
    public String toString() {
        return "ColoredFace{\n" +
                "  a=" + a + "\n" +
                "  b=" + b + "\n" +
                "  c=" + c + "\n" +
                "  color=" + color + "\n" +
                '}';
    }
}
