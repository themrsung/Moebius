package civitas.celestis;

import civitas.celestis.annotation.application.ApplicationCritical;
import civitas.celestis.event.lifecycle.EventManager;
import civitas.celestis.task.lifecycle.Scheduler;
import jakarta.annotation.Nonnull;

/**
 * A Moebius application.
 */
public class MoebiusApplication extends Application {
    //
    // Constructors
    //

    /**
     * Creates a new Moebius application.
     * @param name The name of this application
     * @param version The version of this application
     */
    public MoebiusApplication(@Nonnull String name, @Nonnull String version) {
        super(name, version);
    }

    /**
     * Creates a new Moebius application.
     * @param name The name of this application
     * @param version The version of this application
     * @param eventManager The event manager instance of this application
     * @param scheduler The scheduler instance of this application
     */
    protected MoebiusApplication(
            @Nonnull String name,
            @Nonnull String version,
            @Nonnull @ApplicationCritical EventManager eventManager,
            @Nonnull @ApplicationCritical Scheduler scheduler
    ) {
        super(name, version, eventManager, scheduler);
    }

    //
    // Lifecycle
    //

    /*
     * All versioning is done using Moebius versions.
     */

    /**
     * {@inheritDoc}
     */
    @Override
    @ApplicationCritical(created = "0.3", lastUpdated = "0.3")
    public void start() {
        super.start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @ApplicationCritical(created = "0.3", lastUpdated = "0.3")
    public void stop() {
        super.stop();
    }

    /**
     * {@inheritDoc}
     * @param exitCode The exit code to send to {@link System#exit(int)}
     */
    @Override
    @ApplicationCritical(created = "0.3", lastUpdated = "0.3")
    public void terminate(int exitCode) {
        super.terminate(exitCode);
    }
}
