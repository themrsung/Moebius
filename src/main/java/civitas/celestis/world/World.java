package civitas.celestis.world;

import civitas.celestis.object.BaseObject;
import civitas.celestis.object.Tickable;
import civitas.celestis.object.Unique;
import jakarta.annotation.Nonnull;

import java.util.List;
import java.util.UUID;

/**
 * A world can contain objects and have physical properties.
 */
public interface World extends Unique, Tickable {
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
     * @param uniqueId Unique identifier of the object to query
     * @return The object of specified unique identifier
     * @throws NullPointerException When an object of matching UUID cannot be found
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
