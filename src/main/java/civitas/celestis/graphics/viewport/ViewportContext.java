package civitas.celestis.graphics.viewport;

import civitas.celestis.math.quaternion.Quaternion;
import civitas.celestis.math.vector.Vector3;
import jakarta.annotation.Nonnull;

import java.util.Objects;

/**
 * The contextual data required to render a scene.
 */
public final class ViewportContext {
    @Nonnull
    private final Vector3 location;
    @Nonnull
    private final Quaternion rotation;
    private final double inflation;
    private final double focalLength;

    /**
     * @param location    Location of the viewport
     * @param rotation    Rotation of the viewport
     * @param inflation   Inflation of objects (to better represent their size)
     * @param focalLength Focal length of the viewport
     */
    public ViewportContext(
            @Nonnull Vector3 location,
            @Nonnull Quaternion rotation,
            double inflation,
            double focalLength
    ) {
        this.location = location;
        this.rotation = rotation;
        this.inflation = inflation;
        this.focalLength = focalLength;
    }

    /**
     * Gets the default configuration of a viewport.
     *
     * @return The default configuration of a viewport
     */
    @Nonnull
    public static ViewportContext getDefaultContext() {
        return new ViewportContext(Vector3.ZERO, Quaternion.IDENTITY, 10, 250);
    }

    @Nonnull
    public Vector3 location() {return location;}

    @Nonnull
    public Quaternion rotation() {return rotation;}

    @Nonnull
    public ViewportContext location(@Nonnull Vector3 location) {
        return new ViewportContext(location, rotation, inflation, focalLength);
    }

    @Nonnull
    public ViewportContext rotation(@Nonnull Quaternion rotation) {
        return new ViewportContext(location, rotation, inflation, focalLength);
    }

    public double inflation() {return inflation;}

    public double focalLength() {return focalLength;}

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ViewportContext) obj;
        return Objects.equals(this.location, that.location) &&
                Objects.equals(this.rotation, that.rotation) &&
                Double.doubleToLongBits(this.inflation) == Double.doubleToLongBits(that.inflation) &&
                Double.doubleToLongBits(this.focalLength) == Double.doubleToLongBits(that.focalLength);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, rotation, inflation, focalLength);
    }

    @Override
    public String toString() {
        return "ViewportContext[" +
                "location=" + location + ", " +
                "rotation=" + rotation + ", " +
                "inflation=" + inflation + ", " +
                "focalLength=" + focalLength + ']';
    }

}
