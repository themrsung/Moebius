package civitas.celestis.event;

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
    PERMISSIVE
}
