package civitas.celestis.util;

/**
 * A tickable object can be ticked at regular intervals.
 */
public interface Tickable {
    /**
     * Called every tick.
     *
     * @param delta The duration between the last tick and now in milliseconds
     */
    void tick(long delta);
}
