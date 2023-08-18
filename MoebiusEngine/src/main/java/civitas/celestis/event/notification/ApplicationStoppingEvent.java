package civitas.celestis.event.notification;

import civitas.celestis.MoebiusApplication;
import civitas.celestis.event.Handleable;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * Called when a Moebius application is about to stop.
 */
public class ApplicationStoppingEvent extends ApplicationEvent {
    //
    // Constructors
    //

    /**
     * Creates a new event.
     *
     * @param application The application calling this event
     */
    public ApplicationStoppingEvent(@Nonnull MoebiusApplication application) {
        super(application);
    }

    /**
     * Creates a new event.
     *
     * @param application The application calling this event
     * @param content     The content of this event
     */
    public ApplicationStoppingEvent(@Nonnull MoebiusApplication application, @Nonnull String content) {
        super(application, content);
    }

    /**
     * Creates a new event.
     *
     * @param application The application calling this event
     * @param content     The content of this event
     * @param cause       The cause of this event
     */
    public ApplicationStoppingEvent(@Nonnull MoebiusApplication application, @Nonnull String content, @Nullable Handleable cause) {
        super(application, content, cause);
    }
}
