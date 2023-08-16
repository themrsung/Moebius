package civitas.celestis.task.lifecycle;

import civitas.celestis.task.Task;
import jakarta.annotation.Nonnull;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * A scheduler thread. This can also be used as a single-core scheduler.
 */
public class SchedulerThread extends Thread implements Scheduler {
    //
    // Thread Logic
    //

    /**
     * The scheduler thread logic.
     * @param tasks The list of allocated tasks
     * @param times The map of execution times
     * @return A new instance of {@link Runnable} containing
     * the logic of a scheduler thread
     */
    @Nonnull
    private static Runnable getNewRunnable(@Nonnull List<Task> tasks, @Nonnull Map<Task, Long> times) {
        return () -> {
            //
            // Scheduler Thread
            // Last update: v0.3
            //

            // Infinitely loop until interrupted
            while (!Thread.interrupted()) {

                // Iterate through copy of tasks to prevent concurrent modification from crashing this thread
                for (final Task task : List.copyOf(tasks)) {

                    // Retrieve the current system time
                    final long t2 = System.currentTimeMillis();

                    // Retrieve the previous execution time (fallback to t1 if not found)
                    final long t1 = times.getOrDefault(task, t2);

                    // Calculate delta
                    final long d = t2 - t1;

                    // Respect task's interval
                    if (d < task.interval()) continue;

                    // Execute task
                    task.execute(d);

                    // Keep time
                    times.put(task, t2);

                }
            }
        };
    }

    //
    // Constructors
    //

    /**
     * Creates a new scheduler thread.
     *
     * @param name  The name of this thread
     * @param tasks The list of tasks allocated to this thread
     * @param times The map of execution times of this thread
     */
    public SchedulerThread(@Nonnull String name, @Nonnull List<Task> tasks, @Nonnull Map<Task, Long> times) {
        super(getNewRunnable(tasks, times), name);
        this.tasks = tasks;
        this.times = times;
    }

    //
    // Variables
    //

    /**
     * The list of tasks allocated to this thread.
     */
    private final List<Task> tasks;

    /**
     * The map of the most recent execution times of the tasks.
     */
    private final Map<Task, Long> times;

    //
    // Methods
    //

    /**
     * {@inheritDoc}
     *
     * @param t The task to register to this scheduler
     */
    @Override
    public void register(@Nonnull Task t) {
        tasks.add(t);
        times.put(t, System.currentTimeMillis());
    }

    /**
     * {@inheritDoc}
     *
     * @param i {@inheritDoc}
     */
    @Override
    public void registerSync(@Nonnull Iterable<Task> i) {
        i.forEach(this::register);
    }

    /**
     * {@inheritDoc}
     * Since this is a single-threaded scheduler, this operation is equal to
     * {@link Scheduler#registerSync(Iterable)}.
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
        tasks.remove(t);
        times.remove(t);
    }

    /**
     * {@inheritDoc}
     *
     * @param i The iterable object containing the tasks to unregister
     */
    @Override
    public void unregister(@Nonnull Iterable<Task> i) {
        i.forEach(this::unregister);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Nonnull
    @Override
    public List<Task> getTasks() {
        return List.copyOf(tasks);
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
        try {
            return tasks.stream().filter(c::isInstance).map(c::cast).toList().get(0);
        } catch (final IndexOutOfBoundsException e) {
            throw new NoSuchElementException("Task of class " + c + " was not found in this thread.");
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param t The task to check for registration
     * @return {@inheritDoc}
     */
    @Override
    public boolean isRegistered(@Nonnull Task t) {
        return tasks.contains(t);
    }
}
