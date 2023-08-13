package civitas.celestis.object;

import civitas.celestis.math.complex.Quaternion;
import civitas.celestis.math.vector.Vector3;
import jakarta.annotation.Nonnull;

/**
 * A placeable object has a physical location and rotation.
 */
public interface Placeable {
    //
    // Getters
    //

    /**
     * Returns the current location of this object.
     *
     * @return The location of this object
     */
    @Nonnull
    Vector3 getLocation();

    /**
     * Returns the rotation of this object.
     *
     * @return The rotation of this object
     */
    @Nonnull
    Quaternion getRotation();

    //
    // Setters
    //

    /**
     * Sets the location of this object.
     *
     * @param location The new location of this object
     */
    void setLocation(@Nonnull Vector3 location);

    /**
     * Sets the rotation of this object.
     *
     * @param rotation The new rotation of this object
     */
    void setRotation(@Nonnull Quaternion rotation);
}
