package civitas.celestis.event.notification;

import civitas.celestis.event.Handleable;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * Called when a Moebius application is about to stop.
 */
public class ApplicationStoppingEvent extends NotificationEvent {
    //
    // Constructors
    //

    /**
     * Creates a new event with an empty message.
     */
    public ApplicationStoppingEvent() {
    }

    /**
     * Creates a new event.
     *
     * @param content The content of this event
     */
    public ApplicationStoppingEvent(@Nonnull String content) {
        super(content);
    }

    /**
     * Creates a new event.
     *
     * @param content The content of this event
     * @param cause   The cause of this event
     */
    public ApplicationStoppingEvent(@Nonnull String content, @Nullable Handleable cause) {
        super(content, cause);
    }
}
