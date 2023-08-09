package civitas.celestis.event;

/**
 * The execution priority of an event handler.
 */
public enum HandlerPriority {
    PREEMPTIVE,
    EARLY,
    NORMAL,
    LATE,
    PERMISSIVE
}
