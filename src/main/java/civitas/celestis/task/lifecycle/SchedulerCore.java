package civitas.celestis.task.lifecycle;

import civitas.celestis.task.Task;
import jakarta.annotation.Nonnull;

import java.util.*;

/**
 * The core of a scheduler.
 */
public final class SchedulerCore {
    /**
     * Registers a task to this core.
     *
     * @param t Task to register
     */
    public void register(@Nonnull Task t) {
        tasks.add(t);
        times.put(t, System.currentTimeMillis());
    }

    /**
     * Registers multiple tasks to this core.
     *
     * @param t Collection of tasks to register
     */
    public void register(@Nonnull Collection<Task> t) {
        for (final Task task : t) {
            register(task);
        }
    }

    /**
     * Unregisters a task from this core.
     *
     * @param t Task to unregister
     */
    public void unregister(@Nonnull Task t) {
        tasks.remove(t);
        times.remove(t);
    }

    /**
     * Unregisters multiple tasks from this core.
     *
     * @param t Collection of tasks to unregister
     */
    public void unregister(@Nonnull Collection<Task> t) {
        for (final Task task : t) {
            unregister(task);
        }
    }

    /**
     * Starts this scheduler core.
     */
    public void start() {
        thread.start();
    }

    /**
     * Stops this scheduler core.
     */
    public void stop() {
        thread.interrupt();
    }

    /**
     * Creates a new scheduler core with specified name.
     *
     * @param name Name of this core
     */
    public SchedulerCore(@Nonnull String name) {
        this.thread = new Thread(() -> {
            while (true) {
                if (Thread.interrupted()) return;

                for (final Task task : List.copyOf(tasks)) {
                    final long now = System.currentTimeMillis();
                    final long previous = times.getOrDefault(task, now);
                    final long delta = now - previous;

                    if (delta < task.interval()) continue;

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
