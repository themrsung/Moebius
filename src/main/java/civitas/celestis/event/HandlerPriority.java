package civitas.celestis.event;

/**
 * The priority of an {@link EventHandler}.
 * <p>
 * While lower priority handlers are guaranteed to be called before
 * higher priority handlers, there is no set order between handlers of the
 * same priority.
 * </p>
 *
 * @see EventHandler
 */
public enum HandlerPriority {
    PREEMPTIVE,
    EARLY,
    NORMAL,
    LATE,
    PERMISSIVE
}
