package civitas.celestis;

import civitas.celestis.annotation.ApplicationCritical;
import jakarta.annotation.Nonnull;

/**
 * A {@link MoebiusApplication} used for internal testing.
 * This also serves as an example on how to create Moebius applications.
 */
public class Moebius extends MoebiusApplication {
    //
    // Constants
    //

    /**
     * Returns a reference to this application.
     *
     * @return A reference to this application
     */
    @Nonnull
    public static Moebius getApplication() {
        return application;
    }

    /**
     * The only instance of this application which will ever be created.
     */
    private static final Moebius application = new Moebius();

    //
    // Constructors
    //

    /**
     * Private constructor to ensure that only one instance will be created.
     */
    private Moebius() {
        super("Moebius", "0.3");
    }

    //
    // Lifecycle
    //

    /**
     * The entry point of this application.
     */
    @Override
    @ApplicationCritical(created = "0.3", lastUpdated = "0.3")
    public void start() {
        super.start();
    }

    /**
     * Called to initialize the termination of this application.
     */
    @Override
    @ApplicationCritical(created = "0.3", lastUpdated = "0.3")
    public void stop() {
        super.stop();
    }

    /**
     * Called by the application state listener to full terminate this application.
     */
    @Override
    @ApplicationCritical(created = "0.3", lastUpdated = "0.3")
    public void terminate() {
        super.terminate();
    }

    //
    // Main
    //

    /**
     * The main method of this application.
     *
     * @param args The array of arguments
     */
    @ApplicationCritical
    public static void main(@Nonnull String[] args) {
        getApplication().start();
    }
}
