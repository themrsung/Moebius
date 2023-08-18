package civitas.celestis.listener.application;

import civitas.celestis.event.EventHandler;
import civitas.celestis.event.HandlerPriority;
import civitas.celestis.event.Listener;
import civitas.celestis.event.notification.ApplicationStoppingEvent;
import jakarta.annotation.Nonnull;

/**
 * A listener which handles the lifecycle of a Moebius application.
 */
public class ApplicationStateListener implements Listener {
    /**
     * Called after every other listener has finished processing this event.
     *
     * @param event The application stopping event
     */
    @EventHandler(priority = HandlerPriority.TERMINATION)
    public void onApplicationStopping(@Nonnull ApplicationStoppingEvent event) {
        event.getApplication().terminate();
    }
}
