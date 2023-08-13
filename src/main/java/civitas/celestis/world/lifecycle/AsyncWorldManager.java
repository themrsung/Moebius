package civitas.celestis.world.lifecycle;

import civitas.celestis.util.collection.CircularQueue;
import civitas.celestis.util.string.RichString;
import civitas.celestis.world.World;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * An asynchronous implementation of {@link WorldManager} which has multiple threads.
 * This implementation is appropriate for applications where multiple worlds
 * will be ticked at one time. (e.g. server instances with multiple worlds)
 */
public final class AsyncWorldManager implements WorldManager {
    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        cores.forEach(SyncWorldManager::start);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void stop() {
        cores.forEach(SyncWorldManager::stop);
    }

    /**
     * {@inheritDoc}
     * @return A list of all worlds registered to this world manager
     */
    @Nonnull
    @Override
    public List<World> getWorlds() {
        final List<World> result = new ArrayList<>();

        for (final SyncWorldManager core : cores) {
            result.addAll(core.getWorlds());
        }

        return result;
    }

    /**
     * {@inheritDoc}
     * @param uniqueId The unique identifier of the world to get
     * @return The world of matching unique identifier
     * @throws NullPointerException When a world of matching unique ID cannot be found
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
     * @param world The world to add to this manager
     */
    @Override
    public void addWorld(@Nonnull World world) {
        nextCore().addWorld(world);
    }

    /**
     * {@inheritDoc}
     * @param world The world to remove from this manager
     */
    @Override
    public void removeWorld(@Nonnull World world) {
        cores.forEach(c -> c.removeWorld(world));
    }

    /**
     * Returns the next available core.
     * @return The next core
     */
    @Nonnull
    private SyncWorldManager nextCore() {
        final SyncWorldManager next = cores.next();

        if (next == null) {
            throw new RuntimeException("The AsyncWorldManager has run out of cores.");
        }

        return next;
    }

    /**
     * The queue of cores.
     * The number of cores can be adjusted to suit the application.
     */
    private final CircularQueue<SyncWorldManager> cores = CircularQueue.of(
            new SyncWorldManager(),
            new SyncWorldManager(),
            new SyncWorldManager(),
            new SyncWorldManager()
    );
}
