package civitas.celestis.task;

/**
 * Tasks can be regularly executed by a scheduler.
 * @see civitas.celestis.task.lifecycle.Scheduler Scheduler
 */
public interface Task {
    /**
     * Executes this task.
     *
     * @param delta The amount of time between the last execution and now in milliseconds
     */
    void execute(long delta);

    /**
     * The default interval of a task.
     */
    long DEFAULT_INTERVAL = 50;

    /**
     * Returns the interval of this task.
     *
     * @return The interval of this task in milliseconds
     */
    default long interval() {
        return DEFAULT_INTERVAL;
    }
}
