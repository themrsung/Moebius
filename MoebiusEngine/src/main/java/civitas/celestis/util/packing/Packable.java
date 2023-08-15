package civitas.celestis.util.packing;

/**
 * An object which can be packed into 64 bits with an acceptable loss of precision.
 */
public interface Packable {
    //
    // Methods
    //

    /**
     * Packs this object into 64 bits.
     * @return The packed object
     */
    long pack();
}
