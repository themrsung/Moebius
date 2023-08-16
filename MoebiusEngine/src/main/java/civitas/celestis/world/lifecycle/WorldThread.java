package civitas.celestis.world.lifecycle;

import civitas.celestis.world.World;
import jakarta.annotation.Nonnull;

import java.util.*;

/**
 * A world manager thread. This can also be used directly as a single-core world manager.
 *
 * @param <W> The type of world to handle
 */
public class WorldThread<W extends World<?, ?, ?>> extends Thread implements WorldManager<W> {
    //
    // Thread Logic
    //

    /**
     * The logic of a world manager thread.
     *
     * @param worlds       The allocated list of worlds
     * @param times        The map of recent tick times
     * @param tickInterval The minimum tick interval
     * @param <W>          The type of world to handle
     * @return A new instance of {@link Runnable} containing the logic of a world manager thread
     */
    @Nonnull
    @SuppressWarnings("BusyWait")
    private static <W extends World<?, ?, ?>> Runnable getNewRunnable(@Nonnull List<W> worlds, @Nonnull Map<W, Long> times, long tickInterval) {
        return () -> {
            //
            // World Manager Thread
            // Last update: v0.3
            //

            // Infinitely loop until interrupted
            while (!Thread.interrupted()) {
                // Get current time
                final long tickStart = System.currentTimeMillis();

                // Iterate through copy of worlds to prevent concurrent modifications from crashing this thread
                for (final W w : List.copyOf(worlds)) {
                    // Get current time
                    final long t1 = System.currentTimeMillis();

                    // Get previous time (fallback to t1)
                    final long t2 = times.getOrDefault(w, t1);

                    // Calculate delta
                    final long d = t2 - t1;

                    // Respect tick interval
                    if (d < tickInterval) continue;

                    // Tick world
                    w.tick(d);
                }

                // Get current time
                final long tickEnd = System.currentTimeMillis();

                // Calculate the amount of time it took to execute this tick
                final long tickDuration = tickEnd - tickStart;

                // No sleep necessary
                if (tickDuration >= tickInterval) continue;

                // Calculate required sleep time
                final long sleepTime = tickInterval - tickDuration;

                // Sleep to respect the tick interval
                try {
                    Thread.sleep(sleepTime);
                } catch (final InterruptedException e) {
                    return; // Terminate thread if interrupted
                }
            }
        };
    }

    //
    // Constructors
    //

    /**
     * Creates a new world manager thread.
     *
     * @param name         The name of this thread
     * @param tickInterval The minimum tick interval
     */
    public WorldThread(@Nonnull String name, long tickInterval) {
        this(name, new ArrayList<>(), new HashMap<>(), tickInterval);
    }

    /**
     * Creates a new world manager thread.
     *
     * @param name         The name of this thread
     * @param worlds       The allocated list of worlds
     * @param times        The map of tick times
     * @param tickInterval The minimum tick interval
     */
    public WorldThread(@Nonnull String name, @Nonnull List<W> worlds, @Nonnull Map<W, Long> times, long tickInterval) {
        super(getNewRunnable(worlds, times, tickInterval), name);

        this.worlds = worlds; // DO NOT COPY the list; This needs to be a direct reference
        this.times = times;
        this.tickInterval = tickInterval;
    }

    //
    // Variables
    //

    /**
     * The list of worlds.
     */
    @Nonnull
    protected final List<W> worlds;

    /**
     * The map of the most recent tick times.
     */
    @Nonnull
    protected final Map<W, Long> times;

    /**
     * The tick interval. If ticking every world takes shorter than this interval,
     * the thread will sleep to ensure that the tick delta is at least this interval.
     */
    protected final long tickInterval;

    //
    // Methods
    //

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public List<W> getWorlds() {
        return List.copyOf(worlds);
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
        for (final W world : worlds) {
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
    public void addWorld(@Nonnull W world) {
        worlds.add(world);
        times.put(world, System.currentTimeMillis());
    }

    /**
     * {@inheritDoc}
     *
     * @param world The world to remove from this manager
     */
    @Override
    public void removeWorld(@Nonnull W world) {
        worlds.remove(world);
        times.remove(world);
    }
}
