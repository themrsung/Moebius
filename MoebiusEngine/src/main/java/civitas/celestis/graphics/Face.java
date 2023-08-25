package civitas.celestis.graphics;

import civitas.celestis.math.vector.Vector3;
import civitas.celestis.math.vector.Vectors;
import civitas.celestis.util.tuple.Tuple;
import jakarta.annotation.Nonnull;

import java.awt.*;

public class Face {
    public Face(@Nonnull Vector3 a, @Nonnull Vector3 b, @Nonnull Vector3 c) {
        this.a = a;
        this.b = b;
        this.c = c;

        final Vector3 ab = b.subtract(a);
        final Vector3 ac = c.subtract(a);

        this.normal = ab.cross(ac);

        this.color = Colors.DARK_GOLDEN_ROD;
    }

    @Nonnull
    protected final Vector3 a, b, c;

    @Nonnull
    protected transient final Vector3 normal;

    @Nonnull
    protected Color color;

    @Nonnull
    public Color getColor() {
        return color;
    }

    public void setColor(@Nonnull Color color) {
        this.color = color;
    }

    @Nonnull
    public Vector3 getA() {
        return a;
    }

    @Nonnull
    public Vector3 getB() {
        return b;
    }

    @Nonnull
    public Vector3 getC() {
        return c;
    }

    @Nonnull
    public Tuple<Vector3> getVertices() {
        return Tuple.of(a, b, c);
    }

    @Nonnull
    public Vector3 getCentroid() {
        return Vectors.avg(a, b, c);
    }

    @Nonnull
    public Vector3 getNormal() {
        return normal;
    }

    @Nonnull
    @Override
    public String toString() {
        return "Face{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", normal=" + normal +
                '}';
    }
}
