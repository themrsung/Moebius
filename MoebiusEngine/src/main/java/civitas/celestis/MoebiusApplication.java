package civitas.celestis;

import civitas.celestis.event.lifecycle.EventManager;
import civitas.celestis.event.lifecycle.SyncEventManager;
import civitas.celestis.event.notification.ApplicationStartedEvent;
import civitas.celestis.event.notification.ApplicationStoppingEvent;
import civitas.celestis.listener.notification.NotificationListener;
import civitas.celestis.task.lifecycle.AsyncScheduler;
import civitas.celestis.task.lifecycle.Scheduler;
import jakarta.annotation.Nonnull;

import java.util.logging.Logger;

/**
 * The main class of a Moebius application.
 */
public class MoebiusApplication {
    //
    // Constants
    //

    /**
     * An application used for internal testing.
     */
    public static final MoebiusApplication TEST_APP = new MoebiusApplication("Moebius", "0.3");

    //
    // Constructors
    //

    /**
     * Creates a new application.
     *
     * @param name    The name of this application
     * @param version The version of this application
     */
    public MoebiusApplication(@Nonnull String name, @Nonnull String version) {
        this.name = name;
        this.version = version;

        this.eventManager = new SyncEventManager();
        this.scheduler = new AsyncScheduler();
        this.logger = Logger.getLogger(name);
    }

    /**
     * Creates a new application.
     *
     * @param name         The name of this application
     * @param version      The version of this application
     * @param eventManager The event manager of this application
     * @param scheduler    The scheduler of this application
     * @param logger       The logger instance of this application
     */
    public MoebiusApplication(
            @Nonnull String name,
            @Nonnull String version,
            @Nonnull EventManager eventManager,
            @Nonnull Scheduler scheduler,
            @Nonnull Logger logger
    ) {
        this.name = name;
        this.version = version;
        this.eventManager = eventManager;
        this.scheduler = scheduler;
        this.logger = logger;
    }

    //
    // Lifecycle
    //

    /**
     * Starts this application.
     * This should be overridden completely to customize the behavior of your application.
     */
    public void start() {
        // Register event listeners
        eventManager.register(new NotificationListener(logger::info));

        // Start the modules
        eventManager.start();
        scheduler.start();

        // Notify classes that the application has started
        eventManager.call(new ApplicationStartedEvent(name + " " + version + " has started."));
    }

    /**
     * Stops this application.
     * This should be overridden completely to customize the behavior of your application.
     */
    public void stop() {
        // Notify classes that the application is about to stop
        eventManager.call(new ApplicationStoppingEvent(name + " " + version + " is stopping."));

        // Give the listeners time to process the stopping event
        try {
            Thread.sleep(1000);
        } catch (final InterruptedException ignored) {}

        // Interrupt the modules
        eventManager.interrupt();
        scheduler.interrupt();
    }

    //
    // Getters
    //

    /**
     * Returns the name of this application.
     *
     * @return The name of this application
     */
    @Nonnull
    public String getName() {
        return name;
    }

    /**
     * Returns the version of this application.
     *
     * @return The version of this application
     */
    @Nonnull
    public String getVersion() {
        return version;
    }

    /**
     * Returns the event manager instance.
     *
     * @return The event manager instance
     * @see EventManager
     */
    @Nonnull
    public EventManager getEventManager() {
        return eventManager;
    }

    /**
     * Returns the scheduler instance.
     *
     * @return The scheduler instance
     * @see Scheduler
     */
    @Nonnull
    public Scheduler getScheduler() {
        return scheduler;
    }

    /**
     * Returns the logger instance.
     *
     * @return The logger instance
     * @see Logger
     */
    @Nonnull
    public Logger getLogger() {
        return logger;
    }

    //
    // Properties
    //

    /**
     * The name of this application.
     */
    @Nonnull
    protected final String name;

    /**
     * The version of this application.
     */
    @Nonnull
    protected final String version;

    //
    // Modules
    //

    /**
     * The event manager instance.
     */
    @Nonnull
    protected final EventManager eventManager;

    /**
     * The scheduler instance.
     */
    @Nonnull
    protected final Scheduler scheduler;

    /**
     * The logger instance.
     */
    @Nonnull
    protected final Logger logger;
}
