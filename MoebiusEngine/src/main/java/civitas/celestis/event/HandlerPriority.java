package civitas.celestis.event;

/**
 * The priority of an event handler.
 *
 * @see EventHandler
 */
public enum HandlerPriority {
    PREEMPTIVE,
    LOW,
    MEDIUM,
    HIGH,
    PERMISSIVE;
}
