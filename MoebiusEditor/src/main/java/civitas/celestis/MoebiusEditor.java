package civitas.celestis;

import civitas.celestis.task.lifecycle.AsyncScheduler;
import civitas.celestis.task.lifecycle.Scheduler;
import civitas.celestis.task.lifecycle.SyncScheduler;
import jakarta.annotation.Nonnull;

import java.util.logging.Logger;

/**
 * The main class of the Moebius engine's editor.
 */
public final class MoebiusEditor {
    //
    // Main
    //

    /**
     * The main method of the editor.
     * @param args The array of provided arguments
     */
    public static void main(@Nonnull String[] args) {
        start();

    }

    //
    // Lifecycle
    //

    /**
     * Starts the editor.
     */
    public static void start() {
        System.out.println("Hello world!");
    }

    /**
     * Stops the editor.
     */
    public static void stop() {

    }

    //
    // Getters
    //

    /**
     * Returns the scheduler instance.
     * @return The scheduler instance
     */
    @Nonnull
    public static Scheduler getScheduler() {
        return scheduler;
    }

    /**
     * Returns the logger instance.
     * @return The logger instance
     */
    @Nonnull
    public static Logger getLogger() {
        return logger;
    }

    //
    // Definitions
    //

    private static final Scheduler scheduler = new SyncScheduler();
    private static final Logger logger = Logger.getLogger("MoebiusEditor");
}