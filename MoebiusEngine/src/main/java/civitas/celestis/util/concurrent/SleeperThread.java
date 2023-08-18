package civitas.celestis.util.concurrent;

import civitas.celestis.annotation.ApplicationCritical;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * A thread where the sole purpose is to sleep for a specified amount of time,
 * then terminate after that time has passed.
 * Interruption of this thread will do nothing, as it is not designed to be interrupted.
 * <p>
 * This thread is useful to delay the termination of this application in order to
 * perform time-intensive asynchronous tasks which need to be finished before
 * fully terminating the application.
 * </p>
 */
public class SleeperThread extends Thread {
    //
    // Constants
    //

    /**
     * The action to terminate the JVM with exit code {@code 0}.
     */
    public static final Runnable TERMINATE_JVM = () -> System.exit(0);

    //
    // Static Methods
    //

    /**
     * Sleeps for the provided amount of time.
     * This allows the initiation of a sleep operation without halting the current thread.
     *
     * @param millis The amount of time to sleep in milliseconds
     * @return A reference to the newly created sleeper thread
     */
    @ApplicationCritical(created = "0.3", lastUpdated = "0.3")
    @Nonnull
    public static SleeperThread sleepForMillis(long millis) {
        final SleeperThread thread = new SleeperThread(millis);
        final Thread executor = new Thread(thread::start, "SleeperThreadExecutor");

        executor.start();
        return thread;
    }

    //
    // Constructors
    //

    /**
     * Creates a new sleeper thread.
     *
     * @param millis The amount of time to sleep in milliseconds
     */
    @ApplicationCritical(created = "0.3")
    public SleeperThread(long millis) {
        this(millis, "SleeperThread");
    }

    /**
     * Creates a new sleeper thread.
     *
     * @param millis The amount of time to sleep in milliseconds
     * @param name   The name of this thread
     */
    @ApplicationCritical(created = "0.3")
    public SleeperThread(long millis, @Nonnull String name) {
        this(millis, null, name);
    }

    /**
     * Creates a new sleeper thread.
     *
     * @param millis The amount of time to sleep in milliseconds
     * @param action The action to perform when the sleep has completed
     */
    @ApplicationCritical(created = "0.3")
    public SleeperThread(long millis, @Nullable Runnable action) {
        this(millis, action, "SleeperThread");
    }

    /**
     * Creates a new sleeper thread.
     *
     * @param millis The amount of time to sleep in milliseconds
     * @param action The action to perform when the sleep has completed
     * @param name   The name of this thread
     */
    @ApplicationCritical(created = "0.3", lastUpdated = "0.3")
    public SleeperThread(long millis, @Nullable Runnable action, @Nonnull String name) {
        super(() -> {

            try {
                Thread.sleep(millis);
            } catch (final InterruptedException ignored) {}

            if (action != null) {
                action.run();
            }

        }, name);
    }
}
