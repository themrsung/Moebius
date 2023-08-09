package civitas.celestis.object;

import civitas.celestis.graphics.face.Face;
import civitas.celestis.graphics.model.Model;
import civitas.celestis.math.quaternion.Quaternion;
import civitas.celestis.math.util.Numbers;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.physics.profile.PhysicsProfile;
import civitas.celestis.physics.solid.Solid;
import civitas.celestis.util.Tickable;
import civitas.celestis.util.Unique;
import jakarta.annotation.Nonnull;

import java.util.IdentityHashMap;
import java.util.UUID;

/**
 * A basic object which can be placed in a world.
 */
public class BaseObject implements Unique, Movable, Renderable, Physical, Tickable {
    //
    // Constructors
    //

    /**
     * Creates a new object.
     *
     * @param uniqueId       Unique identifier of this object
     * @param location       Location of this object
     * @param rotation       Rotation of this object
     * @param physicsProfile Physical profile of this object
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
     * @param physicsProfile Physical profile of this object
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

    //
    // Variables
    //
    @Nonnull
    protected final UUID uniqueId;
    @Nonnull
    private Vector3 location, acceleration;
    @Nonnull
    private Quaternion rotation, rotationRate;
    @Nonnull
    protected PhysicsProfile physicsProfile;
    @Nonnull
    protected Model model;

    //
    // Tick
    //

    /**
     * {@inheritDoc}
     * @param delta Duration between the last tick and now in milliseconds
     */
    @Override
    public void tick(long delta) {
        final double seconds = delta / 1000d;

        // Applies acceleration and rate of rotation
        move(acceleration.multiply(seconds));
        rotate(rotationRate.scale(seconds));
    }


    //
    // Identification
    //

    /**
     * {@inheritDoc}
     *
     * @return The unique identifier of this object
     */
    @Override
    @Nonnull
    public UUID getUniqueId() {
        return uniqueId;
    }

    //
    // Movement
    //

    /**
     * {@inheritDoc}
     *
     * @return The location of this object
     */
    @Override
    @Nonnull
    public Vector3 getLocation() {
        return location;
    }

    /**
     * {@inheritDoc}
     *
     * @return The acceleration of this object
     */
    @Override
    @Nonnull
    public Vector3 getAcceleration() {
        return acceleration;
    }

    /**
     * {@inheritDoc}
     *
     * @return The rotation of this object
     */
    @Override
    @Nonnull
    public Quaternion getRotation() {
        return rotation;
    }

    /**
     * {@inheritDoc}
     *
     * @return The rate of rotation of this object
     */
    @Override
    @Nonnull
    public Quaternion getRotationRate() {
        return rotationRate;
    }

    /**
     * {@inheritDoc}
     *
     * @param location The location of this object
     */
    @Override
    public void setLocation(@Nonnull Vector3 location) {
        this.location = location;
    }

    /**
     * {@inheritDoc}
     *
     * @param acceleration The acceleration of this object
     */
    @Override
    public void setAcceleration(@Nonnull Vector3 acceleration) {
        this.acceleration = acceleration;
    }

    /**
     * {@inheritDoc}
     *
     * @param rotation The rotation of this object
     */
    @Override
    public void setRotation(@Nonnull Quaternion rotation) {
        this.rotation = rotation;
    }

    /**
     * {@inheritDoc}
     *
     * @param rotationRate The rate of rotation of this object
     */
    @Override
    public void setRotationRate(@Nonnull Quaternion rotationRate) {
        this.rotationRate = rotationRate;
    }

    /**
     * {@inheritDoc}
     *
     * @param amount The amount of movement to apply
     */
    @Override
    public void move(@Nonnull Vector3 amount) {
        this.location = location.add(amount);
    }

    /**
     * {@inheritDoc}
     *
     * @param amount The amount of acceleration to apply
     */
    @Override
    public void accelerate(@Nonnull Vector3 amount) {
        this.acceleration = acceleration.add(amount);
    }

    /**
     * {@inheritDoc}
     *
     * @param amount The amount of rotation to apply
     */
    @Override
    public void rotate(@Nonnull Quaternion amount) {
        this.rotation = amount.multiply(rotation);
    }

    /**
     * {@inheritDoc}
     *
     * @param amount The rotation to apply to the rate of rotation of this object
     */
    @Override
    public void rotateRate(@Nonnull Quaternion amount) {
        this.rotationRate = amount.multiply(rotationRate);
    }

    //
    // Physics
    //

    /**
     * {@inheritDoc}
     *
     * @return The physical profile of this object
     */
    @Override
    @Nonnull
    public PhysicsProfile getPhysicsProfile() {
        return physicsProfile;
    }

    /**
     * {@inheritDoc}
     *
     * @return The discrete solid of this object
     */
    @Nonnull
    @Override
    public Solid getSolid() {
        return physicsProfile.build(this);
    }

    /**
     * {@inheritDoc}
     *
     * @param physicsProfile The physical profile of this object
     */
    @Override
    public void setPhysicsProfile(@Nonnull PhysicsProfile physicsProfile) {
        this.physicsProfile = physicsProfile;
    }

    //
    // Graphics
    //

    /**
     * {@inheritDoc}
     *
     * @return The graphical model of this object
     */
    @Override
    @Nonnull
    public Model getModel() {
        return model;
    }

    /**
     * {@inheritDoc}
     *
     * @param model The model of this object
     */
    @Override
    public void setModel(@Nonnull Model model) {
        this.model = model;
    }

    //
    // Utility
    //

    /**
     * Checks if this object is moving, while accounting for the {@link Numbers#MARGIN_OF_SIGNIFICANCE}.
     * @return {@code true} if this object has movement
     */
    public boolean isMoving() {
        return Numbers.equals(acceleration, Vector3.ZERO);
    }

    /**
     * Checks if this object is rotating, while accounting for the {@link Numbers#MARGIN_OF_SIGNIFICANCE}.
     * @return {@code true} if this object is rotating
     */
    public boolean isRotating() {
        return Numbers.equals(rotationRate, Quaternion.IDENTITY);
    }

    /**
     * Gets the directional vector of this object.
     * If this object is not moving (which is derived from {@link BaseObject#isMoving()}),
     * this will return {@link Vector3#ZERO}.
     *
     * @return The directional vector of this object
     */
    @Nonnull
    public Vector3 getVector() {
        if (!isMoving()) return Vector3.ZERO;

        return Vector3.POSITIVE_Z.rotate(rotation);
    }

    /**
     * Gets the current velocity of this object.
     * @return The velocity of this object
     */
    public double getVelocity() {
        return acceleration.magnitude();
    }

    /**
     * Gets the squared velocity of this object.
     * @return The squared velocity of this object
     */
    public double getVelocity2() {
        return acceleration.magnitude2();
    }

    /**
     * Gets the negated acceleration of this object.
     * This is used to get the opposing direction of the heading of this object.
     * @return The negated acceleration of this object
     */
    @Nonnull
    public Vector3 getNegatedAcceleration() {
        return acceleration.negate();
    }

    /**
     * Gets the mass of this object.
     * @return The mass of this object
     */
    public double getMass() {
        return physicsProfile.getMass();
    }

    /**
     * Gets the volume of this object.
     * @return The volume of this object
     */
    public double getVolume() {
        return physicsProfile.getVolume();
    }

    /**
     * Gets the density of this object.
     * @return The density of this object
     */
    public double getDensity() {
        return physicsProfile.getDensity();
    }

    /**
     * Gets the current coefficient of drag of this object.
     * @return The coefficient of drag of this object
     */
    public double getDragCoefficient() {
        return getSolid().getDragCoefficient(getNegatedAcceleration());
    }

    /**
     * Gets the current cross-sectional area of this object.
     * @return The cross-sectional area of this object
     */
    public double getCrossSection() {
        return getSolid().getCrossSection(getNegatedAcceleration());
    }

    /**
     * Checks if this object overlaps another object.
     * @param other The object to compare to
     * @return {@code true} if the two objects overlap
     */
    public boolean overlaps(@Nonnull BaseObject other) {
        return getSolid().overlaps(other.getSolid());
    }

    /**
     * Gets an array of faces of this object.
     * Faces are offset to reflect the current location and rotation of this object.
     *
     * @return Array of this object's faces
     */
    @Nonnull
    public Face[] getFaces() {
        final Face[] faces = new Face[model.getFaceCount()];

        for (int i = 0; i < model.getFaceCount(); i++) {
            faces[i] = model.getFace(i).offset(location, rotation);
        }

        return faces;
    }
}
