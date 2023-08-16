package civitas.celestis.world.lifecycle;

import civitas.celestis.util.collection.CircularQueue;
import civitas.celestis.world.World;
import jakarta.annotation.Nonnull;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

/**
 * An asynchronous world manger.
 *
 * @param <W> The type of world to manager
 * @see WorldManager
 */
public class AsyncWorldManager<W extends World<?, ?, ?>> implements WorldManager<W> {
    //
    // Constants
    //

    /**
     * The minimum tick interval.
     */
    private static final long TICK_INTERVAL = 50;

    //
    // Constructors
    //

    /**
     * Creates a new asynchronous world manager.
     */
    public AsyncWorldManager() {

        //
        // The asynchronous world manager currently has 4 cores by default.
        // Last update: v0.3
        //

        this.threads = CircularQueue.of(
                new WorldThread<>("AsyncWorldManager-1", TICK_INTERVAL),
                new WorldThread<>("AsyncWorldManager-2", TICK_INTERVAL),
                new WorldThread<>("AsyncWorldManager-3", TICK_INTERVAL),
                new WorldThread<>("AsyncWorldManager-4", TICK_INTERVAL)
        );
    }

    /**
     * Creates a new asynchronous world manager.
     *
     * @param n The number of threads to construct
     */
    public AsyncWorldManager(int n) {
        this.threads = new CircularQueue<>();

        for (int i = 0; i < n; i++) {
            threads.add(new WorldThread<>("AsyncWorldManager-" + (i + 1), TICK_INTERVAL));
        }
    }

    /**
     * Creates a new asynchronous world manager.
     *
     * @param n            The number of threads to construct
     * @param tickInterval A customized minimum tick interval
     */
    public AsyncWorldManager(int n, long tickInterval) {
        this.threads = new CircularQueue<>();

        for (int i = 0; i < n; i++) {
            threads.add(new WorldThread<>("AsyncWorldManager-" + (i + 1), tickInterval));
        }
    }

    //
    // Variables
    //

    /**
     * The circular queue of threads this world manager controls.
     */
    private final CircularQueue<WorldThread<W>> threads;

    //
    // Methods
    //

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        threads.forEach(WorldThread::start);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void interrupt() {
        threads.forEach(WorldThread::interrupt);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public List<W> getWorlds() {
        final List<W> worlds = new ArrayList<>();
        threads.forEach(t -> worlds.addAll(t.getWorlds()));
        return worlds;
    }

    /**
     * {@inheritDoc}
     *
     * @param uniqueId The unique identifier of the world to get
     * @return {@inheritDoc}
     * @throws NoSuchElementException {@inheritDoc}
     */
    @Nonnull
    @Override
    public W getWorld(@Nonnull UUID uniqueId) throws NoSuchElementException {
        for (final W world : getWorlds()) {
            if (world.getUniqueId().equals(uniqueId)) return world;
        }

        throw new NoSuchElementException("World of unique identifier " + uniqueId + " cannot be found.");
    }

    /**
     * {@inheritDoc}
     *
     * @param world The world to add to this manager
     */
    @Override
    @SuppressWarnings("ConstantConditions")
    public void addWorld(@Nonnull W world) {
        threads.next().addWorld(world);
    }

    /**
     * {@inheritDoc}
     *
     * @param world The world to remove from this manager
     */
    @Override
    public void removeWorld(@Nonnull W world) {
        threads.forEach(t -> t.removeWorld(world));
    }
}
