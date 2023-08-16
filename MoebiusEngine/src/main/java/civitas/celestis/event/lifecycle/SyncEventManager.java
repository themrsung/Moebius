package civitas.celestis.event.lifecycle;

/**
 * A single-threaded synchronous event manager.
 *
 * @see EventManager
 */
public class SyncEventManager extends EventThread {
    /**
     * Creates a new synchronous event manager.
     */
    public SyncEventManager() {
        super("SyncEventManager");
    }
}
