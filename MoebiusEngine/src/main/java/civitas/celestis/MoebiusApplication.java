package civitas.celestis;

import civitas.celestis.annotation.ApplicationCritical;
import civitas.celestis.event.lifecycle.EventManager;
import civitas.celestis.event.lifecycle.SyncEventManager;
import civitas.celestis.event.notification.ApplicationStartedEvent;
import civitas.celestis.event.notification.ApplicationStoppingEvent;
import civitas.celestis.listener.application.ApplicationStateListener;
import civitas.celestis.listener.notification.NotificationListener;
import civitas.celestis.task.lifecycle.AsyncScheduler;
import civitas.celestis.task.lifecycle.Scheduler;
import civitas.celestis.world.lifecycle.SyncWorldManager;
import civitas.celestis.world.lifecycle.WorldManager;
import jakarta.annotation.Nonnull;

import java.util.logging.Logger;

/**
 * The main class of a Moebius application.
 */
public class MoebiusApplication {
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
        this.worldManager = new SyncWorldManager<>();
        this.logger = Logger.getLogger(name);
    }

    /**
     * Creates a new application.
     *
     * @param name         The name of this application
     * @param version      The version of this application
     * @param eventManager The event manager of this application
     * @param scheduler    The scheduler of this application
     * @param worldManager The world manager of this application
     * @param logger       The logger instance of this application
     */
    public MoebiusApplication(
            @Nonnull String name,
            @Nonnull String version,
            @Nonnull EventManager eventManager,
            @Nonnull Scheduler scheduler,
            @Nonnull WorldManager<?> worldManager,
            @Nonnull Logger logger
    ) {
        this.name = name;
        this.version = version;
        this.eventManager = eventManager;
        this.scheduler = scheduler;
        this.worldManager = worldManager;
        this.logger = logger;
    }

    //
    // Lifecycle
    //

    /**
     * Starts this application.
     * This should be overridden completely to customize the behavior of your application.
     */
    @ApplicationCritical(lastUpdated = "0.3")
    public void start() {
        // Register event listeners
        eventManager.register(new NotificationListener(logger::info));
        eventManager.register(new ApplicationStateListener());

        // Start the modules
        eventManager.start();
        scheduler.start();
        worldManager.start();

        // Notify classes that the application has started
        eventManager.call(new ApplicationStartedEvent(this, name + " " + version + " has started."));
    }

    /**
     * Stops this application.
     * This should be overridden completely to customize the behavior of your application.
     */
    @ApplicationCritical(lastUpdated = "0.3")
    public void stop() {
        // Notify classes that the application is about to stop
        eventManager.call(new ApplicationStoppingEvent(this, name + " " + version + " is stopping."));
    }

    /**
     * Fully terminates this application.
     * This is invoked by the {@link ApplicationStateListener}, and should not be directly called.
     */
    @ApplicationCritical(lastUpdated = "0.3")
    public void terminate() {
        // Interrupt the modules
        eventManager.interrupt();
        scheduler.interrupt();
        worldManager.interrupt();
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
     * Returns the world manager instance.
     *
     * @return The world manager instance
     * @see WorldManager
     */
    @Nonnull
    public WorldManager<?> getWorldManager() {
        // Override this method's signature to return your specific type of world manager,
        // then cast worldManager to your custom type by overriding this method.

        return worldManager;
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
     * The world manager instance.
     */
    @Nonnull
    protected final WorldManager<?> worldManager;

    /**
     * The logger instance.
     */
    @Nonnull
    protected final Logger logger;
}