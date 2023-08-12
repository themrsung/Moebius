package civitas.celestis.task.lifecycle;

import civitas.celestis.task.Task;
import jakarta.annotation.Nonnull;

import java.util.Collection;

/**
 * A scheduler can execute tasks in regular intervals.
 *
 * @see Task
 * @see SyncScheduler
 * @see AsyncScheduler
 */
public interface Scheduler {
    /**
     * Starts executing the registered tasks.
     */
    void start();

    /**
     * Stops executing the registered tasks.
     */
    void stop();

    /**
     * Registers a task to this scheduler.
     *
     * @param task The task to register to this scheduler
     */
    void register(@Nonnull Task task);

    /**
     * Registers a collection of tasks synchronously to this scheduler.
     * This registers all tasks to a single thread.
     *
     * @param tasks The collection of tasks to register synchronously
     */
    void registerSync(@Nonnull Collection<Task> tasks);

    /**
     * Registers a collection of tasks asynchronously to this scheduler.
     * This distributes the tasks evenly to all threads.
     * <p>
     * Note that in the case of synchronous-only schedulers, this will be equal to calling
     * {@link Scheduler#registerSync(Collection)}.
     * </p>
     *
     * @param tasks The collection of tasks to register asynchronously
     */
    void registerAsync(@Nonnull Collection<Task> tasks);

    /**
     * Unregisters a task from this scheduler.
     *
     * @param task The task to unregister from this scheduler
     */
    void unregister(@Nonnull Task task);

    /**
     * Unregisters a collection of tasks from this scheduler.
     *
     * @param tasks The collection of tasks to unregister from this scheduler
     */
    void unregister(@Nonnull Collection<Task> tasks);
}
