package civitas.celestis.task.lifecycle;

import civitas.celestis.task.Task;
import civitas.celestis.util.collection.CircularQueue;
import jakarta.annotation.Nonnull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * A multi-core scheduler.
 */
public class AsyncScheduler implements Scheduler {
    //
    // Methods
    //

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        threads.forEach(SchedulerThread::start);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void interrupt() {
        threads.forEach(SchedulerThread::interrupt);
    }

    /**
     * {@inheritDoc}
     *
     * @param task The task to register to this scheduler
     */
    @Override
    public void register(@Nonnull Task task) {
        thread().register(task);
    }

    /**
     * {@inheritDoc}
     *
     * @param tasks The iterable object of tasks to register
     */
    @Override
    public void registerSync(@Nonnull Iterable<Task> tasks) {
        thread().registerSync(tasks);
    }

    /**
     * {@inheritDoc}
     *
     * @param tasks The iterable object of tasks to register
     */
    @Override
    public void registerAsync(@Nonnull Iterable<Task> tasks) {
        tasks.forEach(this::register);
    }

    /**
     * {@inheritDoc}
     *
     * @param task The task to unregister from this scheduler
     */
    @Override
    public void unregister(@Nonnull Task task) {
        threads.forEach(t -> t.unregister(task));
    }

    /**
     * {@inheritDoc}
     *
     * @param tasks The iterable object of tasks to unregister
     */
    @Override
    public void unregister(@Nonnull Iterable<Task> tasks) {
        threads.forEach(t -> t.unregister(tasks));
    }

    //
    // Internal
    //

    /**
     * Returns the next thread in the queue.
     *
     * @return The next thread in the queue
     */
    @Nonnull
    private SchedulerThread thread() {
        return Objects.requireNonNull(threads.next(), "Unknown error: the asynchronous scheduler has run out of cores.");
    }

    /**
     * The queue of 8 asynchronous scheduler cores.
     */
    @Nonnull
    private final CircularQueue<SchedulerThread> threads = CircularQueue.of(
            new SchedulerThread(new ArrayList<>(), new HashMap<>(), "AsyncScheduler-1"),
            new SchedulerThread(new ArrayList<>(), new HashMap<>(), "AsyncScheduler-2"),
            new SchedulerThread(new ArrayList<>(), new HashMap<>(), "AsyncScheduler-3"),
            new SchedulerThread(new ArrayList<>(), new HashMap<>(), "AsyncScheduler-4"),
            new SchedulerThread(new ArrayList<>(), new HashMap<>(), "AsyncScheduler-5"),
            new SchedulerThread(new ArrayList<>(), new HashMap<>(), "AsyncScheduler-6"),
            new SchedulerThread(new ArrayList<>(), new HashMap<>(), "AsyncScheduler-7"),
            new SchedulerThread(new ArrayList<>(), new HashMap<>(), "AsyncScheduler-8")
    );
}
