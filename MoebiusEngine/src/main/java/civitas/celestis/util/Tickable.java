package civitas.celestis.util;

/**
 * An object which can be notified every tick.
 */
public interface Tickable {
    /**
     * Called every tick.
     *
     * @param delta The duration between the last tick and now in milliseconds
     */
    void tick(long delta);
}
