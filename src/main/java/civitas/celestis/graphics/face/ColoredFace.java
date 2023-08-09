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
     * The reflectiveness and translucency is derived from the color component.
     *
     * @param a     The first point of this face
     * @param b     The second point of this face
     * @param c     The third point of this face
     * @param color The initial color of this face
     */
    public ColoredFace(
            @Nonnull Vector3 a,
            @Nonnull Vector3 b,
            @Nonnull Vector3 c,
            @Nonnull Color color
    ) {
        this(
                a,
                b,
                c,
                color,
                (double) (color.getRed() + color.getGreen() + color.getBlue()) / (255 * 3),
                color.getAlpha() != 255
        );
    }

    /**
     * Creates a new colored face.
     *
     * @param a              The first point of this face
     * @param b              The second point of this face
     * @param c              The third point of this face
     * @param color          The initial color of this face
     * @param reflectiveness The reflectiveness factor of this face
     * @param translucent    Whether this face is translucent
     */
    public ColoredFace(
            @Nonnull Vector3 a,
            @Nonnull Vector3 b,
            @Nonnull Vector3 c,
            @Nonnull Color color,
            double reflectiveness,
            boolean translucent
    ) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.color = color;
        this.reflectiveness = reflectiveness;
        this.translucent = translucent;

        this.normal = b.subtract(a).cross(c.subtract(a));
    }

    /**
     * Creates a new colored face.
     *
     * @param points         A tuple containing the points of this face
     * @param color          The initial color of this face
     * @param reflectiveness The reflectiveness factor of this face
     * @param translucent    Whether this face is translucent
     */
    public ColoredFace(@Nonnull Tuple<Vector3> points, @Nonnull Color color, double reflectiveness, boolean translucent) {
        this.a = points.a();
        this.b = points.b();
        this.c = points.c();
        this.color = color;
        this.reflectiveness = reflectiveness;
        this.translucent = translucent;

        this.normal = b.subtract(a).cross(c.subtract(a));
    }

    /**
     * Creates a new colored face from another.
     *
     * @param other Colored face to copy values from
     */
    public ColoredFace(@Nonnull ColoredFace other) {
        this.a = other.a;
        this.b = other.b;
        this.c = other.c;
        this.normal = other.normal;
        this.color = other.color;
        this.reflectiveness = other.reflectiveness;
        this.translucent = other.translucent;
    }

    /**
     * Protected all-args constructor.
     *
     * @param a              The first point of this face
     * @param b              The second point of this face
     * @param c              The third point of this face
     * @param normal         The surface normal of this face
     * @param color          The color of this face
     * @param reflectiveness The reflectiveness of this face
     * @param translucent    Whether this face is translucent
     */
    protected ColoredFace(@Nonnull Vector3 a, @Nonnull Vector3 b, @Nonnull Vector3 c, @Nonnull Vector3 normal, @Nonnull Color color, double reflectiveness, boolean translucent) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.normal = normal;
        this.color = color;
        this.reflectiveness = reflectiveness;
        this.translucent = translucent;
    }

    //
    // Variables
    //

    @Nonnull
    private final Vector3 a, b, c, normal;
    @Nonnull
    private Color color;
    private double reflectiveness;
    private boolean translucent;

    //
    // Properties
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
    public Tuple<Vector3> getPoints() {
        return new Tuple<>(a, b, c);
    }

    @Override
    @Nonnull
    public Vector3 getNormal() {
        return normal;
    }

    @Nonnull
    @Override
    public Vector3 getCentroid() {
        return a.add(b).add(c).divide(3);
    }

    /**
     * Gets the current color of this face.
     *
     * @return The color of this face
     */
    @Nonnull
    public Color getColor() {
        return color;
    }

    @Override
    public double getReflectiveness() {
        return reflectiveness;
    }

    @Override
    public boolean isTranslucent() {
        return translucent;
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
    public void setReflectiveness(double reflectiveness) {
        this.reflectiveness = reflectiveness;
    }

    @Override
    public void setTranslucent(boolean translucent) {
        this.translucent = translucent;
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
                color,
                reflectiveness,
                translucent
        );
    }

    @Nonnull
    @Override
    public ColoredFace inflate(double scale) {
        return new ColoredFace(
                a.multiply(scale),
                b.multiply(scale),
                c.multiply(scale),
                normal.multiply(scale),
                color,
                reflectiveness,
                translucent
        );
    }

    @Nonnull
    @Override
    public ColoredFace translate(@Nonnull Vector3 origin) {
        return new ColoredFace(
                a.subtract(origin),
                b.subtract(origin),
                c.subtract(origin),
                color,
                reflectiveness,
                translucent
        );
    }

    @Nonnull
    @Override
    public ColoredFace rotate(@Nonnull Quaternion rotation) {
        return new ColoredFace(
                a.rotate(rotation),
                b.rotate(rotation),
                c.rotate(rotation),
                normal.rotate(rotation),
                color,
                reflectiveness,
                translucent
        );
    }

    @Nonnull
    @Override
    public ColoredFace transform(@Nonnull Vector3 origin, @Nonnull Quaternion rotation) {
        return new ColoredFace(
                a.subtract(origin).rotate(rotation),
                b.subtract(origin).rotate(rotation),
                c.subtract(origin).rotate(rotation),
                color,
                reflectiveness,
                translucent
        );
    }

    @Nonnull
    @Override
    public ColoredFace transform(@Nonnull Vector3 origin, @Nonnull Quaternion rotation, double scale) {
        return new ColoredFace(
                a.subtract(origin).rotate(rotation).multiply(scale),
                b.subtract(origin).rotate(rotation).multiply(scale),
                c.subtract(origin).rotate(rotation).multiply(scale),
                color,
                reflectiveness,
                translucent
        );
    }

    @Nonnull
    @Override
    public ColoredFace offset(@Nonnull Vector3 offset, @Nonnull Quaternion rotation) {
        return new ColoredFace(
                a.rotate(rotation).add(offset),
                b.rotate(rotation).add(offset),
                c.rotate(rotation).add(offset),
                color,
                reflectiveness,
                translucent
        );
    }

    @Nonnull
    @Override
    public ColoredFace copy() {
        return new ColoredFace(this);
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
                ", normal=" + normal +
                ", color=" + color +
                ", reflectiveness=" + reflectiveness +
                ", translucent=" + translucent +
                '}';
    }
}
