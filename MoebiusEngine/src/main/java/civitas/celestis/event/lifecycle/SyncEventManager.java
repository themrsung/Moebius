package civitas.celestis.event.lifecycle;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * A single-threaded synchronous event manager.
 */
public class SyncEventManager extends EventThread {
    /**
     * Creates a new synchronous event manager.
     */
    public SyncEventManager() {
        super("SyncEventManager", new LinkedList<>(), new ArrayList<>());
    }
}
