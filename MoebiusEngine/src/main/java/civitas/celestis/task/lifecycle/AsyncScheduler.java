package civitas.celestis.task.lifecycle;

import civitas.celestis.task.Task;
import civitas.celestis.util.collection.CircularQueue;
import jakarta.annotation.Nonnull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * An asynchronous scheduler with multiple threads.
 */
public class AsyncScheduler implements Scheduler {
    //
    // Constructors
    //

    /**
     * Creates a new asynchronous scheduler.
     */
    public AsyncScheduler() {

        //
        // The asynchronous scheduler currently has 8 cores.
        // Last update: v0.3
        //

        this.threads = CircularQueue.of(
                new SchedulerThread("AsyncScheduler-1", new ArrayList<>(), new HashMap<>()),
                new SchedulerThread("AsyncScheduler-2", new ArrayList<>(), new HashMap<>()),
                new SchedulerThread("AsyncScheduler-3", new ArrayList<>(), new HashMap<>()),
                new SchedulerThread("AsyncScheduler-4", new ArrayList<>(), new HashMap<>()),
                new SchedulerThread("AsyncScheduler-5", new ArrayList<>(), new HashMap<>()),
                new SchedulerThread("AsyncScheduler-6", new ArrayList<>(), new HashMap<>()),
                new SchedulerThread("AsyncScheduler-7", new ArrayList<>(), new HashMap<>()),
                new SchedulerThread("AsyncScheduler-8", new ArrayList<>(), new HashMap<>())
        );
    }

    //
    // Variables
    //

    /**
     * The circular queue of threads this scheduler controls.
     */
    private final CircularQueue<SchedulerThread> threads;

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
     * @param t The task to register to this scheduler
     */
    @Override
    @SuppressWarnings("ConstantConditions")
    public void register(@Nonnull Task t) {
        threads.next().register(t);
    }

    /**
     * {@inheritDoc}
     *
     * @param i {@inheritDoc}
     */
    @Override
    @SuppressWarnings("ConstantConditions")
    public void registerSync(@Nonnull Iterable<Task> i) {
        threads.next().registerSync(i);
    }

    /**
     * {@inheritDoc}
     *
     * @param i The iterable object containing the tasks to register
     */
    @Override
    public void registerAsync(@Nonnull Iterable<Task> i) {
        i.forEach(this::register);
    }

    /**
     * {@inheritDoc}
     *
     * @param t The task to unregister from this scheduler
     */
    @Override
    public void unregister(@Nonnull Task t) {
        threads.forEach(st -> st.unregister(t));
    }

    /**
     * {@inheritDoc}
     *
     * @param i The iterable object containing the tasks to unregister
     */
    @Override
    public void unregister(@Nonnull Iterable<Task> i) {
        threads.forEach(st -> st.unregister(i));
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public List<Task> getTasks() {
        final List<Task> temp = new ArrayList<>();
        threads.forEach(t -> temp.addAll(t.getTasks()));
        return temp;
    }

    /**
     * {@inheritDoc}
     *
     * @param c   The class of task to get
     * @param <T> {@inheritDoc}
     * @return {@inheritDoc}
     * @throws NoSuchElementException {@inheritDoc}
     */
    @Nonnull
    @Override
    public <T extends Task> T getTask(@Nonnull Class<T> c) throws NoSuchElementException {
        for (final SchedulerThread thread : threads) {
            try {
                return thread.getTask(c);
            } catch (final NoSuchElementException ignored) {}
        }

        throw new NoSuchElementException("Task of class " + c + " was not found in this scheduler.");
    }

    /**
     * {@inheritDoc}
     *
     * @param t The task to check for registration
     * @return {@inheritDoc}
     */
    @Override
    public boolean isRegistered(@Nonnull Task t) {
        for (final SchedulerThread thread : threads) {
            if (thread.isRegistered(t)) return true;
        }

        return false;
    }
}
