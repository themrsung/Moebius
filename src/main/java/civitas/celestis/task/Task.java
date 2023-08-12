package civitas.celestis.task;

/**
 * A task can be executed regularly by a scheduler.
 */
public interface Task {
    /**
     * Executes this task.
     *
     * @param delta Duration between the last execution and now in milliseconds
     */
    void execute(long delta);

    /**
     * Returns the interval of this task.
     *
     * @return The interval of this task in milliseconds
     */
    default long interval() {return DEFAULT_INTERVAL;}

    /**
     * The default interval of tasks.
     */
    long DEFAULT_INTERVAL = 10;
}
