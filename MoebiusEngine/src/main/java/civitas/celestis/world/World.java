package civitas.celestis.world;

import civitas.celestis.object.BaseObject;
import civitas.celestis.util.Tickable;
import civitas.celestis.util.Unique;
import civitas.celestis.util.string.RichString;
import jakarta.annotation.Nonnull;

import java.util.List;
import java.util.UUID;

/**
 * An in-game world.
 * Worlds can have objects and other properties.
 */
public interface World extends Unique, Tickable {
    //
    // Identification
    //

    /**
     * Returns the name of this world.
     *
     * @return The name of this world
     */
    @Nonnull
    String getName();

    /**
     * Returns the display name of this world.
     *
     * @return The display name of this world
     */
    @Nonnull
    RichString getDisplayName();

    //
    // Objects
    //

    /**
     * Returns a list of all objects directly contained in this world.
     * This does not include child objects.
     *
     * @return A list of all objects directly contained in this world
     */
    @Nonnull
    List<BaseObject> getObjects();

    /**
     * Returns a list of all objects contained directly or indirectly in this world.
     * This list includes child objects.
     *
     * @return A list of all objects contained in this world
     */
    @Nonnull
    List<BaseObject> getAllObjects();

    /**
     * Gets an object by unique identifier.
     * This method will retrieve child objects as well as directly contained objects.
     *
     * @param uniqueId The unique identifier of the object to get
     * @return The object of matching unique identifier
     * @throws NullPointerException When an object of matching unique ID cannot be found
     */
    @Nonnull
    BaseObject getObject(@Nonnull UUID uniqueId) throws NullPointerException;

    /**
     * Adds an object to this world.
     *
     * @param object The object to add to this world
     */
    void addObject(@Nonnull BaseObject object);

    /**
     * Removes an object from this world.
     *
     * @param object The object to remove from this world
     */
    void removeObject(@Nonnull BaseObject object);
}
