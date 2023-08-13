package civitas.celestis.world.lifecycle;

import civitas.celestis.util.string.RichString;
import civitas.celestis.world.World;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.List;
import java.util.UUID;

/**
 * The world manager.
 * World managers handle the lifecycle of worlds.
 * @see World
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
     * Returns a list of all worlds registered to this world manager.
     * @return A list of all worlds
     */
    @Nonnull
    List<World> getWorlds();

    /**
     * Gets a world by unique identifier.
     * @param uniqueId The unique identifier of the world to get
     * @return The world of matching unique identifier
     * @throws NullPointerException When a world of matching unique ID cannot be found
     */
    @Nonnull
    World getWorld(@Nonnull UUID uniqueId) throws NullPointerException;

    /**
     * Gets a world by name.
     * @param name The name of the world to get
     * @return The world if found, {@code null} if not
     */
    @Nullable
    World getWorld(@Nonnull String name);

    /**
     * Gets a world by display name.
     *
     * @param displayName The display name of the world to get
     * @return The world if found, {@code null} if not
     * @see RichString#equalsIgnoreFormat(RichString)
     */
    @Nullable
    World getWorld(@Nonnull RichString displayName);

    /**
     * Adds a world to this world manager.
     * @param world The world to add to this manager
     */
    void addWorld(@Nonnull World world);

    /**
     * Removes a world from this manager.
     * @param world The world to remove from this manager
     */
    void removeWorld(@Nonnull World world);
}
