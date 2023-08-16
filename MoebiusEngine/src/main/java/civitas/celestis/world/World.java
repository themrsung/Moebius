package civitas.celestis.world;

import civitas.celestis.object.Movable;
import civitas.celestis.util.Nameable;
import civitas.celestis.util.Unique;
import jakarta.annotation.Nonnull;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.function.Predicate;

/**
 * Represents an in-game world.
 *
 * @param <L> The datatype used to represent location in this world
 * @param <R> The datatype used to represent rotation in this world
 * @param <M> The type of movable object this world contains
 */
public interface World<L, R, M extends Movable<L, R>> extends Iterable<M>, Unique<UUID>, Nameable<String> {
    //
    // Objects
    //

    /**
     * Returns whether the provided object is currently present in this world.
     *
     * @param object The object to check for containment
     * @return {@code true} if this world contains the provided object
     */
    boolean contains(@Nonnull M object);

    /**
     * Returns a list of all objects currently present in this world.
     *
     * @return A copied list of all objects in this world
     */
    @Nonnull
    List<M> getObjects();

    /**
     * Returns a list of objects matching the provided filter.
     *
     * @param filter The filter to apply to the list of objects
     * @return A filtered list of objects
     */
    @Nonnull
    List<M> getObjects(@Nonnull Predicate<M> filter);

    /**
     * Returns a list of objects first filtered by class, then cast to the class.
     *
     * @param objectClass The class of which to filter by, then cast to
     * @param <N>         The type of object to filter by and cast to
     * @return The filtered and cast list of objects
     */
    @Nonnull
    <N extends M> List<N> getObjects(@Nonnull Class<N> objectClass);

    /**
     * Gets an object by its unique identifier.
     *
     * @param uniqueId The unique identifier of the object to get
     * @return The object of matching unique identifier
     * @throws NoSuchElementException When an object of matching unique ID cannot be found
     */
    @Nonnull
    M getObject(@Nonnull UUID uniqueId) throws NoSuchElementException;

    /**
     * Returns an iterator of all objects currently present in this world.
     *
     * @return An iterator of every object of this world
     */
    @Override
    @Nonnull
    Iterator<M> iterator();

    /**
     * Adds an object to this world.
     *
     * @param object The object to add to this world
     */
    void addObject(@Nonnull M object);

    /**
     * Adds multiple objects to this world.
     *
     * @param objects The iterable object containing the references of the objects to add
     */
    void addObjects(@Nonnull Iterable<M> objects);

    /**
     * Removes an object from this world.
     *
     * @param object The object to remove from this world
     */
    void removeObject(@Nonnull M object);

    /**
     * Removes multiple objects from this world.
     *
     * @param objects The iterable object containing the references of the objects to remove
     */
    void removeObjects(@Nonnull Iterable<M> objects);
}
