package civitas.celestis.task.lifecycle;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A single-core scheduler.
 */
public class SyncScheduler extends SchedulerThread {
    //
    // Constructors
    //

    /**
     * Creates a new scheduler.
     */
    public SyncScheduler() {
        super(new ArrayList<>(), new HashMap<>(), "SyncScheduler");
    }
}
