package civitas.celestis.task.lifecycle;

import civitas.celestis.task.Task;
import civitas.celestis.util.counter.Repeater;
import jakarta.annotation.Nonnull;

import java.util.Collection;

/**
 * A scheduler capable of asynchronous task execution.
 */
public final class AsyncScheduler implements Scheduler {
    @Override
    public void start() {
        for (final SchedulerCore core : cores) {
            core.start();
        }
    }

    @Override
    public void stop() {
        for (final SchedulerCore core : cores) {
            core.stop();
        }
    }

    @Override
    public void register(@Nonnull Task task) {
        nextCore().register(task);
    }

    @Override
    public void registerSync(@Nonnull Collection<Task> tasks) {
        nextCore().register(tasks);
    }

    @Override
    public void registerAsync(@Nonnull Collection<Task> tasks) {
        for (final Task task : tasks) {
            nextCore().register(task);
        }
    }

    @Override
    public void unregister(@Nonnull Task task) {
        for (final SchedulerCore core : cores) {
            core.unregister(task);
        }
    }

    @Override
    public void unregister(@Nonnull Collection<Task> tasks) {
        for (final SchedulerCore core : cores) {
            core.unregister(tasks);
        }
    }

    private SchedulerCore nextCore() {
        return cores[distributor.next()];
    }

    private final SchedulerCore[] cores = {
            new SchedulerCore("AsyncScheduler-1"),
            new SchedulerCore("AsyncScheduler-2"),
            new SchedulerCore("AsyncScheduler-3"),
            new SchedulerCore("AsyncScheduler-4"),
            new SchedulerCore("AsyncScheduler-5"),
            new SchedulerCore("AsyncScheduler-6"),
            new SchedulerCore("AsyncScheduler-7"),
            new SchedulerCore("AsyncScheduler-8")
    };
    private final Repeater distributor = new Repeater(cores.length);
}
