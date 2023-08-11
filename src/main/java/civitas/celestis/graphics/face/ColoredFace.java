package civitas.celestis.graphics.face;

import civitas.celestis.math.quaternion.Quaternion;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.util.group.Tuple;
import jakarta.annotation.Nonnull;

import java.awt.*;
import java.util.function.UnaryOperator;

/**
 * A face with a single color component.
 */
public class ColoredFace implements Face {
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
    public ColoredFace(@Nonnull Vector3 a, @Nonnull Vector3 b, @Nonnull Vector3 c, @Nonnull Color color) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.color = color;
    }

    /**
     * Creates a new colored face from another.
     *
     * @param other The face to copy values from
     */
    public ColoredFace(@Nonnull Face other) {
        this.a = other.getA();
        this.b = other.getB();
        this.c = other.getC();
        this.color = other.getColor();
    }

    //
    // Variables
    //

    @Nonnull
    private final Vector3 a, b, c;
    @Nonnull
    private Color color;

    //
    // Geometry
    //

    @Override
    @Nonnull
    public Vector3 getA() {
        return a;
    }

    @Override
    @Nonnull
    public Vector3 getB() {
        return b;
    }

    @Override
    @Nonnull
    public Vector3 getC() {
        return c;
    }

    @Nonnull
    @Override
    public Tuple<Vector3> getVertices() {
        return new Tuple<>(a, b, c);
    }

    @Nonnull
    @Override
    public Vector3 getNormal() {
        final Vector3 ab = b.subtract(a);
        final Vector3 ac = c.subtract(a);

        return ab.cross(ac);
    }

    @Nonnull
    @Override
    public Vector3 getCentroid() {
        return Vector3.avg(a, b, c);
    }

    //
    // Graphics
    //


    @Override
    @Nonnull
    public Color getColor() {
        return color;
    }

    /**
     * Sets the color of this face.
     *
     * @param color The color of this face
     */
    public void setColor(@Nonnull Color color) {
        this.color = color;
    }

    @Override
    public double getAlpha() {
        return color.getAlpha() / 255d;
    }

    @Override
    public boolean isTranslucent() {
        return color.getAlpha() != 255;
    }

    //
    // Utility
    //

    @Nonnull
    @Override
    public ColoredFace apply(@Nonnull UnaryOperator<Vector3> operator) {
        return new ColoredFace(
                operator.apply(a),
                operator.apply(b),
                operator.apply(c),
                color
        );
    }

    @Nonnull
    @Override
    public ColoredFace inflate(double scale) {
        return new ColoredFace(
                a.multiply(scale),
                b.multiply(scale),
                c.multiply(scale),
                color
        );
    }

    @Nonnull
    @Override
    public ColoredFace translate(@Nonnull Vector3 origin) {
        return new ColoredFace(
                a.subtract(origin),
                b.subtract(origin),
                c.subtract(origin),
                color
        );
    }

    @Nonnull
    @Override
    public ColoredFace rotate(@Nonnull Quaternion rotation) {
        return new ColoredFace(
                a.rotate(rotation),
                b.rotate(rotation),
                c.rotate(rotation),
                color
        );
    }

    @Nonnull
    @Override
    public ColoredFace transform(@Nonnull Vector3 origin, @Nonnull Quaternion rotation, double scale) {
        return new ColoredFace(
                a.subtract(origin).rotate(rotation).multiply(scale),
                b.subtract(origin).rotate(rotation).multiply(scale),
                c.subtract(origin).rotate(rotation).multiply(scale),
                color
        );
    }

    @Nonnull
    @Override
    public ColoredFace copy() {
        return new ColoredFace(this);
    }

    /**
     * Checks for equality between this face and another object.
     *
     * @param obj The object to compare to
     * @return {@code true} if the object is a face, and all the components are exactly equal
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof ColoredFace face)) return false;
        return a.equals(face.a) && b.equals(face.b) && c.equals(face.c) && color.equals(face.color);
    }

    /**
     * Serializes this face into a string.
     *
     * @return The string representation of this face
     */
    @Override
    @Nonnull
    public String toString() {
        return "ColoredFace{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", color=" + color +
                '}';
    }
}
