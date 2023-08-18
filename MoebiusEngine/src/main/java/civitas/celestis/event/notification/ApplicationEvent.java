package civitas.celestis.event.notification;

import civitas.celestis.MoebiusApplication;
import civitas.celestis.event.Handleable;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * An event related to a Moebius application.
 */
public abstract class ApplicationEvent extends NotificationEvent {
    //
    // Constructors
    //

    /**
     * Creates a new event with an empty message.
     *
     * @param application The application calling this event
     */
    public ApplicationEvent(@Nonnull MoebiusApplication application) {
        this.application = application;
    }

    /**
     * Creates a new event.
     *
     * @param application The application calling this event
     * @param content     The content of this event
     */
    public ApplicationEvent(@Nonnull MoebiusApplication application, @Nonnull String content) {
        super(content);
        this.application = application;
    }

    /**
     * Creates a new event.
     *
     * @param application The application calling this event
     * @param content     The content of this event
     * @param cause       The cause of this event
     */
    public ApplicationEvent(
            @Nonnull MoebiusApplication application,
            @Nonnull String content,
            @Nullable Handleable cause) {
        super(content, cause);
        this.application = application;
    }

    //
    // Variables
    //

    /**
     * The application instance this event has been called by.
     */
    @Nonnull
    protected final MoebiusApplication application;

    //
    // Getters
    //

    /**
     * Returns the application instance this event has been called by.
     *
     * @return The application instance this event has been called by
     */
    @Nonnull
    public MoebiusApplication getApplication() {
        return application;
    }
}
