package civitas.celestis.world;

import civitas.celestis.object.BaseObject;
import civitas.celestis.util.Tickable;
import civitas.celestis.util.Unique;
import jakarta.annotation.Nonnull;

import java.util.List;
import java.util.UUID;

/**
 * A world represents an in-game level.
 */
public interface World extends Unique, Tickable {
    //
    // Lifecycle
    //

    /**
     * Initializes this world.
     */
    void initialize();

    //
    // Identification
    //

    /**
     * Gets the name of this world.
     *
     * @return The name of this world
     */
    @Nonnull
    String getName();

    //
    // Objects
    //

    /**
     * Gets a list of all objects in this world.
     *
     * @return List of objects in this world
     */
    @Nonnull
    List<BaseObject> getObjects();

    /**
     * Gets an object by unique identifier.
     *
     * @param uniqueId Unique ID of object to get
     * @return Object of matching unique ID
     * @throws NullPointerException When an object of matching unique identifier cannot be found
     */
    @Nonnull
    BaseObject getObject(@Nonnull UUID uniqueId) throws NullPointerException;

    /**
     * Adds an object to this world.
     *
     * @param object Object to add to this world
     */
    void addObject(@Nonnull BaseObject object);

    /**
     * Removes an object from this world.
     *
     * @param object Object to remove from this world
     */
    void removeObject(@Nonnull BaseObject object);
}
