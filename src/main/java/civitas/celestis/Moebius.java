package civitas.celestis;

import civitas.celestis.event.lifecycle.EventManager;
import civitas.celestis.event.lifecycle.SingleEventManager;
import civitas.celestis.task.lifecycle.AsyncScheduler;
import civitas.celestis.task.lifecycle.Scheduler;
import civitas.celestis.world.lifecycle.SyncWorldManager;
import civitas.celestis.world.lifecycle.WorldManager;
import jakarta.annotation.Nonnull;

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
        // Start modules
        eventManager.start();
        scheduler.start();
        worldManager.start();
    }

    /**
     * Stops the Moebius engine.
     */
    public static void stop() {
        // Stop modules
        eventManager.stop();
        scheduler.stop();
        worldManager.stop();
    }

    //
    // Getters
    //


    /**
     * Returns the event manager instance of Moebius.
     *
     * @return The event manager instance
     */
    @Nonnull
    public static EventManager getEventManager() {
        return eventManager;
    }

    /**
     * Returns the scheduler instance of Moebius.
     *
     * @return The scheduler instance
     */
    @Nonnull
    public static Scheduler getScheduler() {
        return scheduler;
    }

    /**
     * Returns the world manager instance.
     * @return The world manager instance
     */
    @Nonnull
    public static WorldManager getWorldManager() {
        return worldManager;
    }

    /**
     * Returns the logger instance of Moebius.
     *
     * @return The logger instance
     */
    @Nonnull
    public static Logger getLogger() {
        return logger;
    }

    //
    // Definitions
    //
    private static final EventManager eventManager = new SingleEventManager();
    private static final Scheduler scheduler = new AsyncScheduler();
    private static final WorldManager worldManager = new SyncWorldManager();
    private static final Logger logger = Logger.getLogger("Moebius");
}