package civitas.celestis.world.lifecycle;

import civitas.celestis.world.World;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.List;
import java.util.UUID;

/**
 * A world manager handles the lifecycle of worlds.
 */
public interface WorldManager {
    /**
     * Starts ticking worlds.
     */
    void start();

    /**
     * Stops ticking worlds.
     */
    void stop();

    /**
     * Gets a list of all worlds currently present in this session.
     *
     * @return List of worlds of this session
     */
    @Nonnull
    List<World> getWorlds();

    /**
     * Gets a world by unique identifier.
     *
     * @param uniqueId Unique identifier of world to get
     * @return The world of matching unique ID
     * @throws NullPointerException When a world of matching unique identifier cannot be found
     */
    @Nonnull
    World getWorld(@Nonnull UUID uniqueId) throws NullPointerException;

    /**
     * Gets a world by name. This may return {@code null}.
     *
     * @param name Name of world to get
     * @return World of matching name if found, {@code null} if not
     */
    @Nullable
    World getWorld(@Nonnull String name);

    /**
     * Adds a world to this session.
     *
     * @param world World to add
     */
    void addWorld(@Nonnull World world);

    /**
     * Removes a world from this session.
     *
     * @param world World to remove
     */
    void removeWorld(@Nonnull World world);
}
