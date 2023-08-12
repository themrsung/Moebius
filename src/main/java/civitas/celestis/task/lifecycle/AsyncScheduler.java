package civitas.celestis.task.lifecycle;

import civitas.celestis.task.Task;
import civitas.celestis.util.collection.CircularQueue;
import jakarta.annotation.Nonnull;

import java.util.Collection;

/**
 * An asynchronous scheduler with multiple threads.
 * <p>
 * While this scheduler is more resilient and can be more efficient for
 * large-scale projects, the synchronous scheduler can still be a valid choice
 * for smaller-scale projects.
 * </p>
 */
public final class AsyncScheduler implements Scheduler {
    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        cores.forEach(SchedulerCore::start);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop() {
        cores.forEach(SchedulerCore::stop);
    }

    /**
     * {@inheritDoc}
     *
     * @param task The task to register to this scheduler
     */
    @Override
    public void register(@Nonnull Task task) {
        nextCore().register(task);
    }

    /**
     * {@inheritDoc}
     *
     * @param tasks The collection of tasks to register synchronously
     */
    @Override
    public void registerSync(@Nonnull Collection<Task> tasks) {
        tasks.forEach(t -> nextCore().register(t));
    }

    /**
     * {@inheritDoc}
     *
     * @param tasks The collection of tasks to register asynchronously
     */
    @Override
    public void registerAsync(@Nonnull Collection<Task> tasks) {
        final SchedulerCore next = nextCore();
        tasks.forEach(next::register);
    }

    /**
     * {@inheritDoc}
     *
     * @param task The task to unregister from this scheduler
     */
    @Override
    public void unregister(@Nonnull Task task) {
        cores.forEach(c -> c.unregister(task));
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

    /**
     * Returns the next core.
     *
     * @return The next core
     */
    @Nonnull
    private SchedulerCore nextCore() {
        final SchedulerCore next = cores.next();

        if (next == null) {
            // This can only be reached if the queue of cores is empty, which cannot happen under normal circumstances
            throw new NullPointerException("The asynchronous scheduler has run out of cores.");
        }

        return next;
    }

    /**
     * The queue of scheduler cores.
     */
    private final CircularQueue<SchedulerCore> cores = CircularQueue.of(
            new SchedulerCore("AsyncScheduler-1"),
            new SchedulerCore("AsyncScheduler-2"),
            new SchedulerCore("AsyncScheduler-3"),
            new SchedulerCore("AsyncScheduler-4"),
            new SchedulerCore("AsyncScheduler-5"),
            new SchedulerCore("AsyncScheduler-6"),
            new SchedulerCore("AsyncScheduler-7"),
            new SchedulerCore("AsyncScheduler-8")
    );
}
