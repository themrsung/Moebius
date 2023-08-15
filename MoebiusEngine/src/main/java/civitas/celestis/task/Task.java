package civitas.celestis.task;

/**
 * A task can be scheduled to be executed in regular intervals.
 */
public interface Task {
    /**
     * Executes this task.
     *
     * @param delta The duration between the last execution and now in milliseconds
     */
    void execute(long delta);

    /**
     * Returns the interval of this task.
     *
     * @return The interval of this task in milliseconds
     */
    default long interval() {
        return DEFAULT_INTERVAL;
    }

    /**
     * The default interval of a task.
     */
    long DEFAULT_INTERVAL = 50;
}
