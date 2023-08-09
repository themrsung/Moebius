package civitas.celestis;

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
        scheduler.start();
    }

    /**
     * Stops the Moebius engine.
     */
    public static void stop() {
        // Stop modules
        scheduler.stop();
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
    private static final Logger logger = Logger.getLogger("Moebius");
}