package civitas.celestis.object;

import civitas.celestis.math.complex.Quaternion;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.util.Unique;
import jakarta.annotation.Nonnull;

import java.util.UUID;

/**
 * A basic 3D object.
 */
public interface BaseObject extends Unique<UUID> {
    //
    // Getters
    //

    @Nonnull
    Vector3 getLocation();

    @Nonnull
    Vector3 getAcceleration();

    @Nonnull
    Quaternion getRotation();

    @Nonnull
    Quaternion getRotationRate();

    //
    // Setters
    //

    void setLocation(@Nonnull Vector3 location);

    void setAcceleration(@Nonnull Vector3 acceleration);

    void setRotation(@Nonnull Quaternion rotation);

    void setRotationRate(@Nonnull Quaternion rotationRate);

    //
    // Movement
    //

    void move(@Nonnull Vector3 amount);

    void accelerate(@Nonnull Vector3 amount);

    void rotate(@Nonnull Quaternion amount);

    void rotateRate(@Nonnull Quaternion amount);
}
