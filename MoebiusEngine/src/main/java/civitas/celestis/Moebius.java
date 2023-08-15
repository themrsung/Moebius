package civitas.celestis;

import civitas.celestis.event.lifecycle.EventManager;
import civitas.celestis.event.lifecycle.SyncEventManager;
import civitas.celestis.task.lifecycle.AsyncScheduler;
import civitas.celestis.task.lifecycle.Scheduler;
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
    }

    /**
     * Stops the Moebius engine.
     */
    public static void stop() {
        // Stop modules
        eventManager.interrupt();
        scheduler.interrupt();
    }

    //
    // Getters
    //

    /**
     * Returns the event manager instance.
     *
     * @return The event manager instance
     */
    @Nonnull
    public static EventManager getEventManager() {
        return eventManager;
    }

    /**
     * Returns the scheduler instance.
     *
     * @return The scheduler instance
     */
    @Nonnull
    public static Scheduler getScheduler() {
        return scheduler;
    }

    /**
     * Returns the logger instance.
     *
     * @return The logger instance
     */
    @Nonnull
    public static Logger getLogger() {
        return logger;
    }

    //
    // Modules
    //

    private static final EventManager eventManager = new SyncEventManager();
    private static final Scheduler scheduler = new AsyncScheduler();
    private static final Logger logger = Logger.getLogger("Moebius");
}
