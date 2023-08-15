package civitas.celestis.task.lifecycle;

import civitas.celestis.task.Task;
import jakarta.annotation.Nonnull;

import java.util.List;
import java.util.Map;

/**
 * A scheduler's thread.
 * Schedulers can have one or more threads depending on the implementation.
 * This can also directly be used as a single-core scheduler.
 */
public class SchedulerThread extends Thread implements Scheduler {
    //
    // Constructors
    //

    /**
     * Creates a new thread.
     *
     * @param tasks The list of tasks allocated to this thread
     * @param times The map of recent execution times of this thread
     * @param name  The name of this thread
     */
    public SchedulerThread(@Nonnull List<Task> tasks, @Nonnull Map<Task, Long> times, @Nonnull String name) {
        super(() -> {

            // Enter infinite loop until thread is interrupted
            while (!Thread.interrupted()) {

                // Iterate through copied list to prevent concurrent modification crashing the thread
                for (final Task task : List.copyOf(tasks)) {

                    // Calculate delta
                    final long now = System.currentTimeMillis();
                    final long previous = times.getOrDefault(task, now);
                    final long delta = now - previous;

                    // Respect interval
                    if (delta < task.interval()) continue;

                    // Execute task and keep time
                    task.execute(delta);
                    times.put(task, now);
                }
            }
        }, name);

        // Store references to list and map for modification
        this.tasks = tasks;
        this.times = times;
    }

    //
    // Variables
    //

    /**
     * The list of tasks allocated to this thread.
     */
    @Nonnull
    private final List<Task> tasks;

    /**
     * The map of the most recent execution times of this thread.
     */
    @Nonnull
    private final Map<Task, Long> times;

    //
    // Methods
    //

    /**
     * {@inheritDoc}
     *
     * @param task The task to register to this thread
     */
    @Override
    public void register(@Nonnull Task task) {
        tasks.add(task);
        times.put(task, System.currentTimeMillis());
    }

    /**
     * {@inheritDoc}
     *
     * @param tasks The iterable object of tasks to register
     */
    @Override
    public void registerSync(@Nonnull Iterable<Task> tasks) {
        tasks.forEach(this::register);
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
        tasks.remove(task);
        times.remove(task);
    }

    /**
     * {@inheritDoc}
     *
     * @param tasks The iterable object of tasks to unregister
     */
    @Override
    public void unregister(@Nonnull Iterable<Task> tasks) {
        tasks.forEach(this::unregister);
    }
}
