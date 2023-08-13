package civitas.celestis.object;

import civitas.celestis.graphics.face.Face;
import civitas.celestis.graphics.model.Model;
import civitas.celestis.math.Numbers;
import civitas.celestis.math.complex.Quaternion;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.object.component.ObjectComponent;
import civitas.celestis.physics.profile.PhysicsProfile;
import civitas.celestis.physics.solid.Solid;
import civitas.celestis.util.Tickable;
import civitas.celestis.util.Unique;
import jakarta.annotation.Nonnull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The base class for objects in Moebius.
 */
public class BaseObject implements Unique, Movable, Physical, Renderable, Tickable {
    //
    // Constructors
    //

    /**
     * Creates a new object.
     *
     * @param uniqueId       The unique identifier of this object.
     * @param location       The location of this object
     * @param rotation       The rotation of this object
     * @param physicsProfile The physical profile of this object
     * @param model          The graphical model of this object
     */
    public BaseObject(
            @Nonnull UUID uniqueId,
            @Nonnull Vector3 location,
            @Nonnull Quaternion rotation,
            @Nonnull PhysicsProfile physicsProfile,
            @Nonnull Model model
    ) {
        this(uniqueId, location, Vector3.ZERO, rotation, Quaternion.IDENTITY, physicsProfile, model, new ArrayList<>());
    }

    /**
     * Creates a new object.
     *
     * @param uniqueId       The unique identifier of this object.
     * @param location       The location of this object
     * @param acceleration   The acceleration of this object
     * @param rotation       The rotation of this object
     * @param rotationRate   The rate of rotation of this object
     * @param physicsProfile The physical profile of this object
     * @param model          The graphical model of this object
     * @param components     The list of initial object components
     */
    public BaseObject(
            @Nonnull UUID uniqueId,
            @Nonnull Vector3 location,
            @Nonnull Vector3 acceleration,
            @Nonnull Quaternion rotation,
            @Nonnull Quaternion rotationRate,
            @Nonnull PhysicsProfile physicsProfile,
            @Nonnull Model model,
            @Nonnull List<ObjectComponent> components
    ) {
        this.uniqueId = uniqueId;
        this.location = location;
        this.acceleration = acceleration;
        this.rotation = rotation;
        this.rotationRate = rotationRate;
        this.physicsProfile = physicsProfile;
        this.model = model;
        this.components = new ArrayList<>(components);
    }


    //
    // Variables
    //

    @Nonnull
    private final UUID uniqueId;
    @Nonnull
    private Vector3 location, acceleration;
    @Nonnull
    private Quaternion rotation, rotationRate;
    @Nonnull
    private PhysicsProfile physicsProfile;
    @Nonnull
    private Model model;
    @Nonnull
    private final List<ObjectComponent> components;

    //
    // Components
    //

    /**
     * Returns a list of components currently registered to this object.
     *
     * @return The list of components this object has
     */
    @Nonnull
    public final List<ObjectComponent> getComponents() {
        return List.copyOf(components);
    }

    /**
     * Gets a list of components by class.
     *
     * @param compClass The class of the component
     * @param <C>       The type of component to get
     * @return A list of all instances of components of matching class
     */
    @Nonnull
    public final <C extends ObjectComponent> List<C> getComponents(@Nonnull Class<C> compClass) {
        return components.stream().filter(compClass::isInstance).map(compClass::cast).toList();
    }

    /**
     * Gets a component by class.
     *
     * @param compClass The class of the component
     * @param <C>       The type of component to get
     * @return The first instance of the component of matching class
     * @throws NullPointerException When the component cannot be found
     */
    @Nonnull
    public final <C extends ObjectComponent> C getComponent(@Nonnull Class<C> compClass) throws NullPointerException {
        for (final ObjectComponent component : getComponents()) {
            if (compClass.isInstance(component)) return compClass.cast(component);
        }

        throw new NullPointerException("Component of class " + compClass.getName() + " cannot be found.");
    }

    /**
     * Adds a component to this object.
     *
     * @param component The component to add to this object
     */
    public final <C extends ObjectComponent> void addComponent(@Nonnull C component) {
        components.add(component);
        component.onAdded(this);
    }

    /**
     * Removes a component from this object.
     *
     * @param component The component to remove from this object
     */
    public final <C extends ObjectComponent> void removeComponent(@Nonnull C component) {
        components.remove(component);
        component.onRemoved(this);
    }

    /**
     * Clears the list of components in this object.
     * This unregisters every component
     */
    public final void clearComponents() {
        components.forEach(c -> c.onRemoved(this));
        components.clear();
    }

    //
    // Ticking
    //

    /**
     * {@inheritDoc}
     * Accelerates and rotates this object in respect to the acceleration and rate of rotation
     *
     * @param delta The duration between the last tick and now in milliseconds
     */
    @Override
    public void tick(long delta) {
        final double seconds = delta / 1000d;

        // Applies acceleration and rate of rotation
        move(acceleration.multiply(seconds));
        rotate(rotationRate.scale(seconds));

        // Tick components
        for (final ObjectComponent component : getComponents()) {
            component.tick(delta);
        }
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
     * @return The current location of this object
     */
    @Override
    @Nonnull
    public Vector3 getLocation() {
        return location;
    }

    /**
     * {@inheritDoc}
     *
     * @return The current acceleration of this object
     */
    @Override
    @Nonnull
    public Vector3 getAcceleration() {
        return acceleration;
    }

    /**
     * {@inheritDoc}
     *
     * @return The current rotation of this object
     */
    @Override
    @Nonnull
    public Quaternion getRotation() {
        return rotation;
    }

    /**
     * {@inheritDoc}
     *
     * @return The current rate of rotation of this object
     */
    @Override
    @Nonnull
    public Quaternion getRotationRate() {
        return rotationRate;
    }

    /**
     * {@inheritDoc}
     *
     * @return The current velocity of this object
     */
    @Override
    public double getVelocity() {
        return acceleration.magnitude();
    }

    /**
     * {@inheritDoc}
     *
     * @return The current squared velocity of this object
     */
    @Override
    public double getVelocity2() {
        return acceleration.magnitude2();
    }

    /**
     * {@inheritDoc}
     *
     * @return {@code true} if this object is currently moving
     */
    @Override
    public boolean isMoving() {
        return Numbers.equals(acceleration, Vector3.ZERO);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@code true} if this object is currently rotating
     */
    @Override
    public boolean isRotating() {
        return Numbers.equals(rotationRate, Quaternion.IDENTITY);
    }

    /**
     * {@inheritDoc}
     *
     * @return The current direction of this object
     */
    @Nonnull
    @Override
    public Vector3 getDirection() {
        if (isMoving()) {
            return Vector3.POSITIVE_Z.rotate(rotation);
        } else {
            return Vector3.ZERO;
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param location The new location of this object
     */
    @Override
    public void setLocation(@Nonnull Vector3 location) {
        this.location = location;
    }

    /**
     * {@inheritDoc}
     *
     * @param acceleration The new acceleration of this object
     */
    @Override
    public void setAcceleration(@Nonnull Vector3 acceleration) {
        this.acceleration = acceleration;
    }

    /**
     * {@inheritDoc}
     *
     * @param rotation The new rotation of this object
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
     * @param amount The amount of rotation to apply to the rate of rotation
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
     * @return The mass of this object
     */
    @Override
    public double getMass() {
        return physicsProfile.getMass();
    }

    /**
     * {@inheritDoc}
     *
     * @return The volume of this object
     */
    @Override
    public double getVolume() {
        return physicsProfile.getVolume();
    }

    /**
     * {@inheritDoc}
     *
     * @return The density of this object
     */
    @Override
    public double getDensity() {
        return physicsProfile.getDensity();
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
     * @param other The object to compare to
     * @return {@code true} if the two objects overlap
     */
    @Override
    public boolean overlaps(@Nonnull Physical other) {
        return getSolid().overlaps(other.getSolid());
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
     * @return A list of faces of this object which are mapped to absolute coordinates
     */
    @Nonnull
    @Override
    public List<? extends Face> getAbsoluteFaces() {
        return model.getFaces().stream()
                .map(f -> f.apply(v -> v.absolute(location, rotation)))
                .toList();
    }

    /**
     * {@inheritDoc}
     *
     * @param model The model to set to
     */
    @Override
    public void setModel(@Nonnull Model model) {
        this.model = model;
    }
}
