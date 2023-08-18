package civitas.celestis.event;

import civitas.celestis.annotation.ApplicationCritical;

/**
 * The priority of an event handler.
 *
 * @see Handleable
 * @see EventHandler
 * @see Event
 * @see Listener
 */
public enum HandlerPriority {
    PREEMPTIVE,
    EARLY,
    MEDIUM,
    LATE,
    PERMISSIVE,

    /**
     * <b>THIS IS A RESERVED PRIORITY FOR TERMINATION OPERATIONS.</b><br>
     * <p>
     * This priority should only be used for event listeners which handle
     * the termination of an application after every event listener subscribing
     * to {@link civitas.celestis.event.notification.ApplicationStoppingEvent ApplicationStoppingEvent}.
     * (or any variation of the event)
     * </p>
     */
    @ApplicationCritical(lastUpdated = "0.3")
    TERMINATION
}
