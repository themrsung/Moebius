package civitas.celestis.task.lifecycle;

/**
 * A synchronous single-threaded scheduler.
 */
public class SyncScheduler extends SchedulerThread {
    /**
     * Creates a new synchronous scheduler.
     */
    public SyncScheduler() {
        super("SyncScheduler");
    }
}
