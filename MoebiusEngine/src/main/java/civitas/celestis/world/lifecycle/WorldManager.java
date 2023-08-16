package civitas.celestis.world.lifecycle;

import civitas.celestis.util.ThreadedModule;
import civitas.celestis.world.World;
import jakarta.annotation.Nonnull;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

/**
 * A world manager. Handles the lifecycle of worlds.
 *
 * @param <W> The type of world to handle
 * @see World
 * @see SyncWorldManager
 * @see AsyncWorldManager
 */
public interface WorldManager<W extends World<?, ?, ?>> extends ThreadedModule {
    //
    // Worlds
    //

    /**
     * Returns a list of every world currently registered to this manager.
     *
     * @return A list of all worlds in this manager
     */
    @Nonnull
    List<W> getWorlds();

    /**
     * Gets a world by its unique identifier.
     *
     * @param uniqueId The unique identifier of the world to get
     * @return The world of matching unique identifier
     * @throws NoSuchElementException When a world of matching unique identifier cannot be found
     */
    @Nonnull
    W getWorld(@Nonnull UUID uniqueId) throws NoSuchElementException;

    /**
     * Adds a world to this world manager.
     *
     * @param world The world to add to this manager
     */
    void addWorld(@Nonnull W world);

    /**
     * Removes a world from this world manager.
     *
     * @param world The world to remove from this manager
     */
    void removeWorld(@Nonnull W world);
}
