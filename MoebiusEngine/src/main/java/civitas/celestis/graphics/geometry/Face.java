package civitas.celestis.graphics.geometry;

import civitas.celestis.graphics.color.Color8;
import civitas.celestis.math.matrix.DoubleMatrix;
import civitas.celestis.math.tuple.Triple;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * A three-dimensional face.
 * Faces are used as the building block for 3D models.
 */
public class Face extends Triple<Vertex> {
    //
    // Constructors
    //

    /**
     * Creates a new face.
     *
     * @param a     The first vertex of this face
     * @param b     The second vertex of this face
     * @param c     The third vertex of this face
     * @param color The initial color of this face
     */
    public Face(@Nonnull Vertex a, @Nonnull Vertex b, @Nonnull Vertex c, @Nonnull Color8 color) {
        super(a, b, c);
        this.color = color;
    }

    /**
     * Creates a new face.
     *
     * @param f The face of which to copy properties from
     */
    public Face(@Nonnull Face f) {
        super(f);
        this.color = f.color;
    }

    //
    // Variables
    //

    /**
     * The color of this face.
     */
    @Nonnull
    protected Color8 color;

    //
    // Getters
    //

    /**
     * Returns the geometric centroid of this face.
     * This is a simple average of the three vertices of this face.
     *
     * @return The geometric centroid of this face
     */
    @Nonnull
    public Vertex getCenter() {
        final double sx = a.x() + b.x() + c.x();
        final double sy = a.y() + b.y() + c.y();
        final double sz = a.z() + b.z() + c.z();

        return new Vertex(sx / 3, sy / 3, sz / 3);
    }

    /**
     * Returns the normal vertex of this face.
     * The vertex is normalized to have a magnitude of {@code 1}.
     *
     * @return The normalized normal vertex
     */
    @Nonnull
    public Vertex getNormal() {
        final Vertex ab = b.subtract(a);
        final Vertex ac = c.subtract(a);

        return ab.cross(ac).normalize();
    }

    /**
     * Returns the current color of this face.
     *
     * @return The color of this face
     */
    @Nonnull
    public Color8 getColor() {
        return color;
    }

    //
    // Setters
    //

    /**
     * Sets the color of this face.
     *
     * @param color The new color to set to
     */
    public void setColor(@Nonnull Color8 color) {
        this.color = color;
    }

    //
    // Matrix Conversion
    //

    /**
     * Returns the matrix representation of this face.
     * Each row corresponds to a single vertex, and each column corresponds to an axis.
     * <p>
     * A face with the vertices {@code {a = (1, 2, 3), b = (4, 5, 6), c = (7, 8, 9)}}
     * would be mapped to a matrix as follows.<br><code>
     * 1, 2, 3<br>
     * 4, 5, 6<br>
     * 7, 8, 9
     * </code>
     * </p>
     *
     * @return The matrix representation of this face
     */
    @Nonnull
    public DoubleMatrix toMatrix() {
        return new DoubleMatrix(new double[][]{
                a.values(),
                b.values(),
                c.values()
        });
    }

    //
    // Equality
    //

    /**
     * Checks for equality between this face and the provided object {@code obj}.
     *
     * @param obj The object to compare to
     * @return {@code true} if the other object is also a face, and the properties are equal
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof Face f)) return false;
        return a.equals(f.a) && b.equals(f.b) && c.equals(f.c) && color.equals(f.color);
    }

    //
    // Serialization
    //

    /**
     * Serializes this face into a string.
     *
     * @return The string representation of this face
     */
    @Nonnull
    @Override
    public String toString() {
        return "Face{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", color=" + color +
                '}';
    }
}
