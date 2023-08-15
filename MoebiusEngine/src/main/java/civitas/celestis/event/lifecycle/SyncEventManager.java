package civitas.celestis.event.lifecycle;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * A single-core event manager.
 */
public class SyncEventManager extends EventThread {
    //
    // Constructors
    //

    /**
     * Creates a new event manager.
     */
    public SyncEventManager() {
        super(new LinkedList<>(), new ArrayList<>(), "SyncEventManager");
    }
}
