package civitas.celestis.object;

import civitas.celestis.graphics.model.Model;
import civitas.celestis.math.quaternion.Quaternion;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.physics.profile.PhysicsProfile;
import civitas.celestis.physics.solid.Solid;
import jakarta.annotation.Nonnull;

import java.util.UUID;

/**
 * A basic object capable of being placed in worlds.
 */
public class BaseObject implements Unique, Renderable, Physical, Tickable {
    /**
     * Creates a new object.
     *
     * @param uniqueId       Unique identifier of this object
     * @param location       Location of this object
     * @param rotation       Rotation of this object
     * @param physicsProfile Solid profile of this object
     * @param model          Graphical model of this object
     */
    public BaseObject(
            @Nonnull UUID uniqueId,
            @Nonnull Vector3 location,
            @Nonnull Quaternion rotation,
            @Nonnull PhysicsProfile physicsProfile,
            @Nonnull Model model
    ) {
        this(uniqueId, location, Vector3.ZERO, rotation, Quaternion.IDENTITY, physicsProfile, model);
    }

    /**
     * Creates a new object.
     *
     * @param uniqueId       Unique identifier of this object
     * @param location       Location of this object
     * @param acceleration   Acceleration of this object
     * @param rotation       Rotation of this object
     * @param rotationRate   Rate of rotation of this object
     * @param physicsProfile Solid profile of this object
     * @param model          Graphical model of this object
     */
    public BaseObject(
            @Nonnull UUID uniqueId,
            @Nonnull Vector3 location,
            @Nonnull Vector3 acceleration,
            @Nonnull Quaternion rotation,
            @Nonnull Quaternion rotationRate,
            @Nonnull PhysicsProfile physicsProfile,
            @Nonnull Model model
    ) {
        this.uniqueId = uniqueId;
        this.location = location;
        this.acceleration = acceleration;
        this.rotation = rotation;
        this.rotationRate = rotationRate;
        this.physicsProfile = physicsProfile;
        this.model = model;
    }

    @Nonnull
    protected final UUID uniqueId;
    @Nonnull
    protected Vector3 location, acceleration;
    @Nonnull
    protected Quaternion rotation, rotationRate;
    @Nonnull
    protected PhysicsProfile physicsProfile;
    @Nonnull
    protected Model model;

    /**
     * Handles the movement and rotation of this object.
     *
     * @param delta Duration between the last tick and now in milliseconds
     */
    @Override
    public void tick(long delta) {
        final double seconds = delta / 1000d;

        move(acceleration.multiply(seconds));
        rotate(rotationRate.scale(seconds));
    }

    @Override
    @Nonnull
    public UUID getUniqueId() {
        return uniqueId;
    }

    @Override
    @Nonnull
    public Vector3 getLocation() {
        return location;
    }

    @Override
    @Nonnull
    public Vector3 getAcceleration() {
        return acceleration;
    }

    @Override
    @Nonnull
    public Quaternion getRotation() {
        return rotation;
    }

    @Override
    @Nonnull
    public Quaternion getRotationRate() {
        return rotationRate;
    }

    @Override
    @Nonnull
    public PhysicsProfile getPhysicsProfile() {
        return physicsProfile;
    }

    @Nonnull
    @Override
    public Solid getSolid() {
        return physicsProfile.build(this);
    }

    @Override
    @Nonnull
    public Model getModel() {
        return model;
    }

    @Override
    public void setLocation(@Nonnull Vector3 location) {
        this.location = location;
    }

    @Override
    public void setAcceleration(@Nonnull Vector3 acceleration) {
        this.acceleration = acceleration;
    }

    @Override
    public void setRotation(@Nonnull Quaternion rotation) {
        this.rotation = rotation;
    }

    @Override
    public void setRotationRate(@Nonnull Quaternion rotationRate) {
        this.rotationRate = rotationRate;
    }

    @Override
    public void setPhysicsProfile(@Nonnull PhysicsProfile physicsProfile) {
        this.physicsProfile = physicsProfile;
    }

    @Override
    public void setModel(@Nonnull Model model) {
        this.model = model;
    }

    @Override
    public void move(@Nonnull Vector3 amount) {
        this.location = location.add(amount);
    }

    @Override
    public void accelerate(@Nonnull Vector3 amount) {
        this.acceleration = acceleration.add(amount);
    }

    @Override
    public void rotate(@Nonnull Quaternion amount) {
        this.rotation = amount.multiply(rotation);
    }

    @Override
    public void rotateRate(@Nonnull Quaternion amount) {
        this.rotationRate = amount.multiply(rotationRate);
    }
}
