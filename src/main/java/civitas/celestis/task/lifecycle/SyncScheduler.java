package civitas.celestis.task.lifecycle;

import civitas.celestis.task.Task;
import jakarta.annotation.Nonnull;

import java.util.Collection;

/**
 * A synchronous scheduler has only one core, and is not capable of asynchronous execution.
 */
public final class SyncScheduler implements Scheduler {
    @Override
    public void start() {
        core.start();
    }

    @Override
    public void stop() {
        core.stop();
    }

    @Override
    public void register(@Nonnull Task task) {
        core.register(task);
    }

    @Override
    public void registerSync(@Nonnull Collection<Task> tasks) {
        core.register(tasks);
    }

    @Override
    public void registerAsync(@Nonnull Collection<Task> tasks) {
        core.register(tasks);
    }

    @Override
    public void unregister(@Nonnull Task task) {
        core.unregister(task);
    }

    @Override
    public void unregister(@Nonnull Collection<Task> tasks) {
        core.unregister(tasks);
    }

    private final SchedulerCore core = new SchedulerCore("SyncScheduler");
}
