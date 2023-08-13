package civitas.celestis.task.lifecycle;

import civitas.celestis.task.Task;
import jakarta.annotation.Nonnull;

import java.util.Collection;

/**
 * A synchronous scheduler with a single thread.
 * <p>
 * While this scheduler is less resilient and can be less efficient
 * compared to asynchronous implementations in large-scale projects,
 * this can still be a valid choice for smaller-scale projects.
 * </p>
 */
public final class SyncScheduler extends SchedulerCore implements Scheduler {
    /**
     * Creates a new synchronous scheduler.
     */
    public SyncScheduler() {
        super("SyncScheduler");
    }

    /**
     * {@inheritDoc}
     *
     * @param tasks The collection of tasks to register synchronously
     */
    @Override
    public void registerSync(@Nonnull Collection<Task> tasks) {
        tasks.forEach(this::register);
    }

    /**
     * {@inheritDoc}
     *
     * @param tasks The collection of tasks to register asynchronously
     */
    @Override
    public void registerAsync(@Nonnull Collection<Task> tasks) {
        tasks.forEach(this::register);
    }

    /**
     * {@inheritDoc}
     *
     * @param tasks The collection of tasks to unregister from this scheduler
     */
    @Override
    public void unregister(@Nonnull Collection<Task> tasks) {
        tasks.forEach(this::unregister);
    }
}
