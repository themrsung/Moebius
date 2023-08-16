package civitas.celestis.task.lifecycle;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A synchronous single-threaded scheduler.
 */
public class SyncScheduler extends SchedulerThread {
    /**
     * Creates a new synchronous scheduler.
     */
    public SyncScheduler() {
        super("SyncScheduler", new ArrayList<>(), new HashMap<>());
    }
}
