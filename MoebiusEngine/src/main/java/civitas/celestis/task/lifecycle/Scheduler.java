package civitas.celestis.task.lifecycle;

import civitas.celestis.task.Task;
import civitas.celestis.util.ThreadedModule;
import jakarta.annotation.Nonnull;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * A scheduler can execute tasks according to the task's interval.
 *
 * @see Task
 * @see ThreadedModule
 * @see SyncScheduler
 * @see AsyncScheduler
 */
public interface Scheduler extends ThreadedModule {
    //
    // Registration
    //

    /**
     * Registers a task to this scheduler.
     *
     * @param t The task to register to this scheduler
     */
    void register(@Nonnull Task t);

    /**
     * Registers multiple tasks to this scheduler synchronously,
     * registering all tasks to a single thread.
     *
     * @param i The iterable object containing the tasks to register
     */
    void registerSync(@Nonnull Iterable<Task> i);

    /**
     * Registers multiple tasks to this scheduler asynchronously,
     * evenly distributing the tasks across each thread of this scheduler.
     *
     * @param i The iterable object containing the tasks to register
     */
    void registerAsync(@Nonnull Iterable<Task> i);

    /**
     * Unregisters a task from this scheduler.
     *
     * @param t The task to unregister from this scheduler
     */
    void unregister(@Nonnull Task t);

    /**
     * Unregisters multiple tasks from this scheduler.
     *
     * @param i The iterable object containing the tasks to unregister
     */
    void unregister(@Nonnull Iterable<Task> i);

    //
    // Getters
    //

    /**
     * Returns a list containing every task currently registered to this scheduler.
     *
     * @return A copied unmodifiable list containing every task of this scheduler
     */
    @Nonnull
    List<Task> getTasks();

    /**
     * Searches for a task by the class of the task,
     * then returns the first instance of the task found.
     *
     * @param c   The class of task to get
     * @param <T> The type of task to get
     * @return The first task instance of the given class {@code c}
     * @throws NoSuchElementException When a task of matching class cannot be found
     */
    @Nonnull
    <T extends Task> T getTask(@Nonnull Class<T> c) throws NoSuchElementException;

    /**
     * Returns whether the given task instance is currently registered to this scheduler.
     *
     * @param t The task to check for registration
     * @return {@code true} if the task is currently registered to this scheduler
     */
    boolean isRegistered(@Nonnull Task t);
}
