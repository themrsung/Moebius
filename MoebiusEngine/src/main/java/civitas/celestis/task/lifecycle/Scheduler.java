package civitas.celestis.task.lifecycle;

import civitas.celestis.task.Task;
import jakarta.annotation.Nonnull;

/**
 * A scheduler. Schedulers can execute tasks according to the tasks' intervals.
 *
 * @see Task
 */
public interface Scheduler {
    /**
     * Starts this scheduler, instructing it to start every thread.
     */
    void start();

    /**
     * Interrupts this scheduler, instructing it
     * to terminate after it has finished the current iteration.
     */
    void interrupt();

    /**
     * Registers a task to this scheduler.
     *
     * @param task The task to register to this scheduler
     */
    void register(@Nonnull Task task);

    /**
     * Registers multiple tasks synchronously to this scheduler.
     * All tasks will be registered to a single thread.
     *
     * @param tasks The iterable object of tasks to register
     */
    void registerSync(@Nonnull Iterable<Task> tasks);

    /**
     * Registers multiple tasks asynchronously to this scheduler.
     * The tasks will be evenly distributed across all threads of this scheduler.
     *
     * @param tasks The iterable object of tasks to register
     */
    void registerAsync(@Nonnull Iterable<Task> tasks);

    /**
     * Unregisters a task from this scheduler.
     *
     * @param task The task to unregister from this scheduler
     */
    void unregister(@Nonnull Task task);

    /**
     * Unregisters multiple tasks from this scheduler.
     *
     * @param tasks The iterable object of tasks to unregister
     */
    void unregister(@Nonnull Iterable<Task> tasks);
}
