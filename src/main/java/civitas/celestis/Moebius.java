package civitas.celestis;

import civitas.celestis.event.lifecycle.EventManager;
import civitas.celestis.event.lifecycle.SyncEventManager;
import civitas.celestis.listener.object.ObjectsCollidedListener;
import civitas.celestis.task.lifecycle.AsyncScheduler;
import civitas.celestis.task.lifecycle.Scheduler;
import civitas.celestis.world.lifecycle.SyncWorldManager;
import civitas.celestis.world.lifecycle.WorldManager;
import jakarta.annotation.Nonnull;

import java.util.List;
import java.util.logging.Logger;

/**
 * The main class of Moebius.
 */
public final class Moebius {
    //
    // Lifecycle
    //

    /**
     * Starts the Moebius engine.
     */
    public static void start() {
        // Register listeners
        registerListeners();

        // Start modules
        scheduler.start();
        eventManager.start();
        worldManager.start();
    }

    /**
     * Stops the Moebius engine.
     */
    public static void stop() {
        // Stop modules
        scheduler.stop();
        eventManager.stop();
        worldManager.stop();
    }

    //
    // Getters
    //

    /**
     * Gets the scheduler instance of Moebius.
     *
     * @return Scheduler instance
     */
    @Nonnull
    public static Scheduler getScheduler() {
        return scheduler;
    }

    /**
     * Gets the event manager instance of Moebius.
     *
     * @return Event manager instance
     */
    @Nonnull
    public static EventManager getEventManager() {
        return eventManager;
    }

    /**
     * Gets the world manager instance of Moebius.
     * @return World manager instance
     */
    @Nonnull
    public static WorldManager getWorldManager() {
        return worldManager;
    }

    /**
     * Gets the logger instance of Moebius.
     *
     * @return Logger instance
     */
    @Nonnull
    public static Logger getLogger() {
        return logger;
    }

    //
    // Definitions
    //

    private static final Scheduler scheduler = new AsyncScheduler();
    private static final EventManager eventManager = new SyncEventManager();
    private static final WorldManager worldManager = new SyncWorldManager();
    private static final Logger logger = Logger.getLogger("Moebius");

    //
    // Internal
    //

    /**
     * Registers first-party listeners to the event manager.
     */
    private static void registerListeners() {
        eventManager.register(List.of(
                new ObjectsCollidedListener()
        ));
    }
}