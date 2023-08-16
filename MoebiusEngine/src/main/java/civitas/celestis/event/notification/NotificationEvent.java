package civitas.celestis.event.notification;

import civitas.celestis.event.Event;
import civitas.celestis.event.Handleable;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * An event which notifies the console that a certain operation has been performed.
 *
 * @see civitas.celestis.listener.notification.NotificationListener NotificationListener
 */
public class NotificationEvent extends Event {
    //
    // Constructors
    //

    /**
     * Creates a new notification event with an empty message.
     */
    public NotificationEvent() {
        this.content = "";
    }

    /**
     * Creates a new notification event.
     *
     * @param content The content of the notification
     */
    public NotificationEvent(@Nonnull String content) {
        this.content = content;
    }

    /**
     * Creates a new notification event.
     *
     * @param content The content of the notification
     * @param cause   The cause of this notification
     */
    public NotificationEvent(@Nonnull String content, @Nullable Handleable cause) {
        super(cause);
        this.content = content;
    }


    //
    // Variables
    //

    /**
     * The content of this notification.
     */
    @Nonnull
    protected final String content;

    //
    // Getters
    //

    /**
     * Returns the content of this notification.
     *
     * @return The content of this notification
     */
    @Nonnull
    public String getContent() {
        return content;
    }
}
