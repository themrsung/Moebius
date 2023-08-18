package civitas.celestis;

import civitas.celestis.annotation.ApplicationCritical;
import civitas.celestis.graphics.model.Model3;
import civitas.celestis.graphics.model.Models;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.io.IOException;

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

    /**
     * The 3D model of a BC-304 battlecruiser.
     */
    static final Model3 BC304;

    /**
     * The 3D model of a Sig MCX.
     */
    static final Model3 MCX;

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
    // Main
    //

    /**
     * The main method of this application.
     *
     * @param args The array of arguments
     */
    @ApplicationCritical(created = "0.3", lastUpdated = "0.3")
    public static void main(@Nonnull String[] args) {
        getApplication().start();
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
    // Methods
    //

    /**
     * Prints a message to the logger.
     *
     * @param message The message to log
     */
    public static void log(@Nonnull String message) {
        getApplication().logger.info(message);
    }

    /**
     * Prints a message to the logger.
     *
     * @param objects The objects to serialize and log
     */
    public static void log(@Nullable Object... objects) {
        final StringBuilder out = new StringBuilder();

        for (final Object object : objects) {
            out.append(object);
        }

        getApplication().logger.info(out.toString());
    }

    //
    // Static Operations
    //

    static {
        // Load models

        try {
            BC304 = Models.readWavefrontObjFile("MoebiusEngine/src/main/resources/models/bc304/BC304Render.obj");
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }

        try {
            MCX = Models.readWavefrontObjFile("MoebiusEngine/src/main/resources/models/mcx/MCX.obj");
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
}
