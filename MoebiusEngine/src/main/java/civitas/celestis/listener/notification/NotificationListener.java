package civitas.celestis.listener.notification;

import civitas.celestis.event.EventHandler;
import civitas.celestis.event.HandlerPriority;
import civitas.celestis.event.Listener;
import civitas.celestis.event.notification.NotificationEvent;
import jakarta.annotation.Nonnull;

import java.util.function.Consumer;

/**
 * An event listener which listens to notifications.
 *
 * @see NotificationEvent
 */
public final class NotificationListener implements Listener {
    //
    // Constructors
    //

    /**
     * Creates a new notification listener.
     * Notifications will be printed via {@link java.io.PrintStream#println(String) System.out.println()}.
     */
    public NotificationListener() {
        this.printer = System.out::println;
    }

    /**
     * Creates a new notification listener with a custom printer function.
     *
     * @param printer The function to call when this listener
     *                has received a notification
     */
    public NotificationListener(@Nonnull Consumer<String> printer) {
        this.printer = printer;
    }


    //
    // Handling
    //

    /**
     * Called when a notification event has been called to the event manager.
     *
     * @param event The event which was called
     */
    @EventHandler(priority = HandlerPriority.PREEMPTIVE)
    public void onNotified(@Nonnull NotificationEvent event) {
        printer.accept(event.getContent());
    }

    //
    // Variables
    //

    /**
     * The printer function to use to print notifications.
     */
    @Nonnull
    private final Consumer<String> printer;
}
