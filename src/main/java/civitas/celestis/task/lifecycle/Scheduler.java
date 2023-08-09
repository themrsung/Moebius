package civitas.celestis.task.lifecycle;

import civitas.celestis.task.Task;
import jakarta.annotation.Nonnull;

import java.util.Collection;

/**
 * A scheduler handles the lifecycle of tasks.
 *
 * @see Task
 */
public interface Scheduler {
    /**
     * Starts executing tasks.
     */
    void start();

    /**
     * Stops executing tasks.
     */
    void stop();

    /**
     * Registers a task to this scheduler.
     *
     * @param task Task to register
     */
    void register(@Nonnull Task task);

    /**
     * Registers multiple tasks to a single core.
     *
     * @param tasks Collection of tasks to register synchronously
     */
    void registerSync(@Nonnull Collection<Task> tasks);

    /**
     * Registers multiple tasks across multiple cores.
     *
     * @param tasks Collection of tasks to register asynchronously
     */
    void registerAsync(@Nonnull Collection<Task> tasks);

    /**
     * Unregisters a task from this scheduler.
     *
     * @param task Task to unregister
     */
    void unregister(@Nonnull Task task);

    /**
     * Unregisters multiple tasks from this scheduler.
     *
     * @param tasks Collection of tasks to unregister
     */
    void unregister(@Nonnull Collection<Task> tasks);
}
