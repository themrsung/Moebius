package civitas.celestis.world.lifecycle;

import civitas.celestis.world.World;

/**
 * A single-threaded synchronous world manager.
 *
 * @param <W> The type of world to manage
 */
public class SyncWorldManager<W extends World<?, ?, ?>> extends WorldThread<W> {
    //
    // Constants
    //

    /**
     * The minimum tick interval of synchronous world managers.
     */
    private static final long TICK_INTERVAL = 50;

    //
    // Constructors
    //

    /**
     * Creates a new world manager.
     */
    public SyncWorldManager() {
        super("SyncWorldManager", TICK_INTERVAL);
    }

    /**
     * Creates a new world manager.
     *
     * @param tickInterval A customized minimum tick interval
     */
    public SyncWorldManager(long tickInterval) {
        super("SyncWorldManager", tickInterval);
    }
}
