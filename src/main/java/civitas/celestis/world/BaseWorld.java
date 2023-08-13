package civitas.celestis.world;

import civitas.celestis.object.BaseObject;
import civitas.celestis.object.component.ComponentObject;
import civitas.celestis.util.string.RichString;
import jakarta.annotation.Nonnull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * A basic world.
 */
public class BaseWorld implements World {
    //
    // Constructors
    //

    /**
     * Creates a new world.
     *
     * @param uniqueId The unique identifier of this world
     * @param name     The name of this world
     */
    public BaseWorld(
            @Nonnull UUID uniqueId,
            @Nonnull String name
    ) {
        this(uniqueId, name, new RichString(name), new ArrayList<>());
    }

    /**
     * Creates a new world.
     *
     * @param uniqueId    The unique identifier of this world
     * @param displayName The display name of this world
     */
    public BaseWorld(
            @Nonnull UUID uniqueId,
            @Nonnull RichString displayName
    ) {
        this(uniqueId, displayName.content(), displayName, new ArrayList<>());
    }

    /**
     * Creates a new world.
     *
     * @param uniqueId The unique identifier of this world
     * @param name     The name of this world
     * @param objects  The list of initial objects in this world
     */
    public BaseWorld(
            @Nonnull UUID uniqueId,
            @Nonnull String name,
            @Nonnull List<BaseObject> objects
    ) {
        this(uniqueId, name, new RichString(name), objects);
    }

    /**
     * Creates a new world.
     *
     * @param uniqueId    The unique identifier of this world
     * @param displayName The display name of this world
     * @param objects     The list of initial objects in this world
     */
    public BaseWorld(
            @Nonnull UUID uniqueId,
            @Nonnull RichString displayName,
            @Nonnull List<BaseObject> objects
    ) {
        this(uniqueId, displayName.content(), displayName, objects);
    }

    /**
     * Creates a new world.
     * @param uniqueId The unique identifier of this world
     * @param name The name of this world
     * @param displayName The display name of this world
     * @param objects The list of initial objects in this world
     */
    public BaseWorld(
            @Nonnull UUID uniqueId,
            @Nonnull String name,
            @Nonnull RichString displayName,
            @Nonnull List<BaseObject> objects
    ) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.displayName = displayName;
        this.objects = new ArrayList<>(objects);
    }

    //
    // Variables
    //
    @Nonnull
    private final UUID uniqueId;
    @Nonnull
    private final String name;
    @Nonnull
    private final RichString displayName;
    @Nonnull
    private final List<BaseObject> objects;

    //
    // Tick
    //

    /**
     * {@inheritDoc}
     * This ticks every object directly contained in this world.
     * Recursive ticking of child objects should be handled in the parent object.
     *
     * @param delta The duration between the last tick and now in milliseconds
     */
    @Override
    public void tick(long delta) {
        for (final BaseObject object : getObjects()) {
            object.tick(delta);
        }
    }

    //
    // Identification
    //

    /**
     * {@inheritDoc}
     * @return The unique identifier of this world
     */
    @Override
    @Nonnull
    public UUID getUniqueId() {
        return uniqueId;
    }

    /**
     * {@inheritDoc}
     * @return The name of this world
     */
    @Override
    @Nonnull
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     * @return The display name of this world
     */
    @Override
    @Nonnull
    public RichString getDisplayName() {
        return displayName;
    }

    //
    // Objects
    //

    /**
     * {@inheritDoc}
     * @return A list of objects in this world
     */
    @Override
    @Nonnull
    public List<BaseObject> getObjects() {
        return objects;
    }

    /**
     * {@inheritDoc}
     * @return A list of all objects including child objects of objects in this world
     */
    @Nonnull
    @Override
    public List<BaseObject> getAllObjects() {
        final List<BaseObject> result = new ArrayList<>(objects);

        for (final BaseObject object : getObjects()) {
            addChildObjects(result, object);
        }

        return result;
    }

    /**
     * Recursively adds all child objects of given object to the list {@code result}.
     * @param result The list to add object references to
     * @param parentObject The parent object to search
     */
    private static void addChildObjects(@Nonnull List<BaseObject> result, @Nonnull BaseObject parentObject) {
        for (final ComponentObject component : parentObject.getComponents(ComponentObject.class)) {
            addChildObjects(result, component);
        }
    }

    /**
     * {@inheritDoc}
     * @param uniqueId The unique identifier of the object to get
     * @return The object of matching unique ID
     * @throws NullPointerException When the object cannot be found
     */
    @Nonnull
    @Override
    public BaseObject getObject(@Nonnull UUID uniqueId) throws NullPointerException {
        for (final BaseObject object : getObjects()) {
            if (object.getUniqueId().equals(uniqueId)) return object;
        }

        throw new NullPointerException("Object of unique identifier " + uniqueId + " cannot be found.");
    }

    /**
     * {@inheritDoc}
     * @param object The object to add to this world
     */
    @Override
    public void addObject(@Nonnull BaseObject object) {
        objects.add(object);
    }

    /**
     * {@inheritDoc}
     * @param object The object to remove from this world
     */
    @Override
    public void removeObject(@Nonnull BaseObject object) {
        objects.remove(object);
    }
}
