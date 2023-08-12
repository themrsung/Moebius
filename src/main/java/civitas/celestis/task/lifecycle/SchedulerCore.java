package civitas.celestis.task.lifecycle;

import civitas.celestis.task.Task;
import jakarta.annotation.Nonnull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A scheduler core.
 */
public class SchedulerCore {
    /**
     * Starts this scheduler core.
     */
    public final void start() {
        thread.start();
    }

    /**
     * Stops this scheduler core.
     */
    public final void stop() {
        thread.interrupt();
    }

    /**
     * Registers a task to this scheduler core.
     *
     * @param task The task to register to this core
     */
    public final void register(@Nonnull Task task) {
        tasks.add(task);
        times.put(task, System.currentTimeMillis());
    }

    /**
     * Unregisters a task from this scheduler core.
     *
     * @param task The task to unregister from this core
     */
    public final void unregister(@Nonnull Task task) {
        tasks.remove(task);
        times.remove(task);
    }

    /**
     * Creates a new scheduler core.
     *
     * @param name The name of this core's thread
     */
    public SchedulerCore(@Nonnull String name) {
        this.thread = new Thread(() -> {
            while (!Thread.interrupted()) {
                for (final Task task : List.copyOf(tasks)) {
                    // Calculate delta
                    final long now = System.currentTimeMillis();
                    final long previous = times.getOrDefault(task, now);
                    final long delta = now - previous;

                    // Respect interval
                    if (delta < task.interval()) continue;

                    // Execute task
                    task.execute(delta);
                    times.put(task, now);
                }
            }
        }, name);
    }

    private final List<Task> tasks = new ArrayList<>();
    private final Map<Task, Long> times = new HashMap<>();
    private final Thread thread;
}
