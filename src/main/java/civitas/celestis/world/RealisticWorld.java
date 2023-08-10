package civitas.celestis.world;

import civitas.celestis.Moebius;
import civitas.celestis.event.object.ObjectsCollidedEvent;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.object.BaseObject;
import civitas.celestis.util.group.Group;
import civitas.celestis.util.group.Pair;
import jakarta.annotation.Nonnull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RealisticWorld implements World {
    //
    // Constructors
    //

    /**
     * Creates a new realistic world.
     *
     * @param uniqueId   Unique identifier of this world
     * @param name       Name of this world
     * @param gravity    Gravity vector of this world
     * @param airDensity Air density of this world
     */
    public RealisticWorld(
            @Nonnull UUID uniqueId,
            @Nonnull String name,
            @Nonnull Vector3 gravity,
            double airDensity
    ) {this(uniqueId, name, new ArrayList<>(), gravity, airDensity);}

    /**
     * Creates a new realistic world.
     *
     * @param uniqueId   Unique identifier of this world
     * @param name       Name of this world
     * @param objects    List of objects to contain in this world
     * @param gravity    Gravity vector of this world
     * @param airDensity Air density of this world
     */
    public RealisticWorld(
            @Nonnull UUID uniqueId,
            @Nonnull String name,
            @Nonnull List<BaseObject> objects,
            @Nonnull Vector3 gravity,
            double airDensity
    ) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.objects = new ArrayList<>(objects); // Copies list to ensure that list is mutable
        this.gravity = gravity;
        this.airDensity = airDensity;
    }

    //
    // Variables
    //

    @Nonnull
    protected final UUID uniqueId;
    @Nonnull
    protected final String name;
    @Nonnull
    protected final List<BaseObject> objects;
    @Nonnull
    protected Vector3 gravity;
    protected double airDensity;

    protected transient List<Pair<BaseObject>> overlaps = new ArrayList<>();

    //
    // Lifecycle
    //

    @Override
    public void initialize() {
        overlaps.clear();

        final List<Pair<BaseObject>> pairs = Group.pairsOf(objects);
        for (final Pair<BaseObject> pair : pairs) {
            final BaseObject o1 = pair.a();
            final BaseObject o2 = pair.b();

            if (o1.overlaps(o2)) overlaps.add(pair);
        }
    }


    //
    // Tick
    //

    @Override
    public void tick(long delta) {
        final double seconds = delta / 1000d;

        // Cache objects list for consistency
        final List<BaseObject> objects = getObjects();

        //
        // Handle collisions
        //

        // Obtain all possible pairs
        final List<Pair<BaseObject>> pairs = Group.pairsOf(objects);

        // Clear invalid cache
        overlaps.removeIf(p -> !pairs.contains(p));

        // Iterate through pairs
        for (final Pair<BaseObject> pair : pairs) {
            final BaseObject o1 = pair.a();
            final BaseObject o2 = pair.b();

            if (o1.overlaps(o2)) {
                if (!overlaps.contains(pair)) {
                    overlaps.add(pair);

                    // Call collision event
                    Moebius.getEventManager().call(new ObjectsCollidedEvent(pair));
                }
            } else {
                overlaps.remove(pair);
            }
        }

        // Scale gravity
        final Vector3 scaledGravity = gravity.multiply(seconds);

        // Iterate through objects
        for (final BaseObject object : objects) {
            // Apply gravitational acceleration
            object.accelerate(scaledGravity);

            //
            // Apply drag force
            //

            // Define variables
            double fluidDensity = airDensity;
            final double mass = object.getMass();
            final double dragCoefficient = object.getDragCoefficient();
            final double crossSection = object.getCrossSection();
            final double velocity2 = object.getVelocity2();
            final Vector3 acceleration = object.getAcceleration();

            // Calculate fluid density
            for (final Pair<BaseObject> overlap : overlaps) {
                if (!overlap.contains(object)) continue;
                final BaseObject other = overlap.other(object);

                fluidDensity = Math.max(fluidDensity, other.getDensity());
            }

            // Skip this step if fluid density <= 0
            if (fluidDensity > 0) {
                // Calculate drag force
                final double dragForceMagnitude = 0.5 * dragCoefficient * crossSection * fluidDensity * velocity2;

                // Safety check
                if (Double.isFinite(dragForceMagnitude)) {
                    final Vector3 dragForce = acceleration.normalize().multiply(-dragForceMagnitude / mass);

                    // Apply force to object
                    object.accelerate(dragForce);
                }
            }

            // Tick object
            object.tick(delta);
        }
    }


    //
    // Identification
    //

    @Override
    @Nonnull
    public UUID getUniqueId() {
        return uniqueId;
    }

    @Override
    @Nonnull
    public String getName() {
        return name;
    }

    //
    // Properties
    //

    /**
     * Gets the gravity of this world.
     *
     * @return The gravity of this world
     */
    @Nonnull
    public Vector3 getGravity() {
        return gravity;
    }

    /**
     * Gets the air density of this world.
     *
     * @return The air density of this world
     */
    public double getAirDensity() {
        return airDensity;
    }

    /**
     * Sets the gravity of this world.
     *
     * @param gravity The gravity of this world
     */
    public void setGravity(@Nonnull Vector3 gravity) {
        this.gravity = gravity;
    }

    /**
     * Sets the air density of this world.
     *
     * @param airDensity The air density of this world
     */
    public void setAirDensity(double airDensity) {
        this.airDensity = airDensity;
    }

    //
    // Objects
    //

    @Override
    @Nonnull
    public List<BaseObject> getObjects() {
        return new ArrayList<>(objects);
    }

    @Nonnull
    @Override
    public BaseObject getObject(@Nonnull UUID uniqueId) throws NullPointerException {
        for (final BaseObject object : getObjects()) {
            if (object.getUniqueId().equals(uniqueId)) return object;
        }

        throw new NullPointerException("Object of unique identifier " + uniqueId + " cannot be found.");
    }

    @Override
    public void addObject(@Nonnull BaseObject object) {
        objects.add(object);
    }

    @Override
    public void removeObject(@Nonnull BaseObject object) {
        objects.remove(object);
    }
}
