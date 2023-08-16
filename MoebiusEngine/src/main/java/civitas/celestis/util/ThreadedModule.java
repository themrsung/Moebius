package civitas.celestis.util;

/**
 * The superinterface for threaded modules.
 *
 * @see civitas.celestis.task.lifecycle.Scheduler Scheduler
 */
public interface ThreadedModule {
    /**
     * Starts this module, instructing it to start every thread this module controls.
     */
    void start();

    /**
     * Interrupts this module, instructing it to terminate every thread it controls
     * after the current iteration of this module has completed.
     */
    void interrupt();
}
