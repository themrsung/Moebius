package civitas.celestis;

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
    }

    /**
     * Stops the Moebius engine.
     */
    public static void stop() {
        // Stop modules
    }

    //
    // Getters
    //

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
    private static final Logger logger = Logger.getLogger("Moebius");
}