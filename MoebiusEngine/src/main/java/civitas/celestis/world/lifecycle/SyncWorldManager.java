package civitas.celestis.world.lifecycle;

import civitas.celestis.Moebius;
import civitas.celestis.task.Task;
import civitas.celestis.util.string.RichString;
import civitas.celestis.world.World;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * A synchronous implementation of {@link WorldManager} which has only one thread.
 * This implementation is appropriate for applications where
 * very few worlds will be ticked at one time.
 */
public final class SyncWorldManager implements WorldManager {
    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        Moebius.getScheduler().register(task);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop() {
        Moebius.getScheduler().unregister(task);
    }

    /**
     * {@inheritDoc}
     *
     * @return A list of all worlds registered to this world manager
     */
    @Nonnull
    @Override
    public List<World> getWorlds() {
        return List.copyOf(worlds);
    }

    /**
     * {@inheritDoc}
     *
     * @param uniqueId The unique identifier of the world to get
     * @return The world of matching unique identifier
     * @throws NullPointerException When the world cannot be found
     */
    @Nonnull
    @Override
    public World getWorld(@Nonnull UUID uniqueId) throws NullPointerException {
        for (final World world : getWorlds()) {
            if (world.getUniqueId().equals(uniqueId)) return world;
        }

        throw new NullPointerException("World of unique identifier " + uniqueId + " cannot be found.");
    }

    /**
     * {@inheritDoc}
     *
     * @param name The name of the world to get
     * @return The world if found, {@code null} if not
     */
    @Nullable
    @Override
    public World getWorld(@Nonnull String name) {
        for (final World world : getWorlds()) {
            if (world.getName().equals(name)) return world;
        }

        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @param displayName The display name of the world to get
     * @return The world if found, {@code null} if not
     */
    @Nullable
    @Override
    public World getWorld(@Nonnull RichString displayName) {
        for (final World world : getWorlds()) {
            if (world.getDisplayName().equalsIgnoreFormat(displayName)) return world;
        }

        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @param world The world to add to this manager
     */
    @Override
    public void addWorld(@Nonnull World world) {
        worlds.add(world);
    }

    /**
     * {@inheritDoc}
     *
     * @param world The world to remove from this manager
     */
    @Override
    public void removeWorld(@Nonnull World world) {
        worlds.remove(world);
    }

    /**
     * The list of worlds.
     */
    private final List<World> worlds = new ArrayList<>();

    /**
     * The task responsible for ticking worlds.
     */
    private final Task task = delta -> {
        for (final World world : getWorlds()) {
            world.tick(delta);
        }
    };
}
