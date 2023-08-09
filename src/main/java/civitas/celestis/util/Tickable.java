package civitas.celestis.util;

/**
 * A tickable object can be ticked on a regular basis.
 */
public interface Tickable {
    /**
     * Called every tick.
     *
     * @param delta Duration between the last tick and now in milliseconds
     */
    void tick(long delta);
}
