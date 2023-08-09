package civitas.celestis.math.util;

/**
 * Contains utility methods related to numbers.
 */
public final class Numbers {
    /**
     * Explicitly denotes that a given field requires a finite values as its input.
     *
     * @param value Value to check for finiteness
     * @return The value provided as the input parameter
     * @throws IllegalArgumentException When the given input is non-finite ({@link Double#isFinite(double)} return {@code false})
     */
    public static double requireFinite(double value) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("This field requires a finite value. The provided value was " + value + ".");
        }

        return value;
    }
}
