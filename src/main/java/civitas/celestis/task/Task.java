package civitas.celestis.task;

/**
 * A task can be scheduled to be executed on a regular basis.
 */
public interface Task {
    /**
     * Executes this task.
     *
     * @param delta Duration between the last execution and now in milliseconds
     */
    void execute(long delta);

    /**
     * The default interval of a task.
     */
    long DEFAULT_INTERVAL = 5;

    /**
     * Gets the interval of this task.
     *
     * @return Interval in milliseconds
     */
    default long interval() {return DEFAULT_INTERVAL;}
}
