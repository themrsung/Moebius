package civitas.celestis.util.atomic;

import civitas.celestis.math.Numbers;

import java.util.concurrent.atomic.AtomicReference;

/**
 * An atomic reference to a {@code double}.
 */
public class AtomicDouble extends AtomicReference<Double> {
    //
    // Constructors
    //

    /**
     * Creates a new atomic double.
     *
     * @param initialValue The initial value
     */
    public AtomicDouble(double initialValue) {
        super(initialValue);
    }

    /**
     * Creates a new atomic double.
     */
    public AtomicDouble() {
        super(0.0);
    }

    //
    // Arithmetic
    //

    /**
     * Adds a scalar to this double.
     *
     * @param s The scalar to add to this double
     */
    public void add(double s) {
        set(get() + s);
    }

    /**
     * Subtracts a scalar from this double.
     *
     * @param s The scalar to subtract from this double
     */
    public void subtract(double s) {
        set(get() - s);
    }

    /**
     * Multiplies this double by a scalar.
     *
     * @param s The scalar of which to multiply this double with
     */
    public void multiply(double s) {
        set(get() * s);
    }

    /**
     * Divides this double by a scalar.
     * This will not invoke an {@link ArithmeticException}.
     *
     * @param s The scalar to divide this double by
     */
    public void divide(double s) {
        set(get() / s);
    }

    /**
     * Raises this double to the {@code e}th power.
     *
     * @param e The exponent to raise to
     */
    public void pow(double e) {
        set(Math.pow(get(), e));
    }

    /**
     * Square roots this double.
     */
    public void sqrt() {
        set(Math.sqrt(get()));
    }

    //
    // Clamping
    //

    /**
     * Sets this double to the minimum of {@code this} and {@code s}.
     *
     * @param s The reference scalar
     */
    public void min(double s) {
        set(Math.min(get(), s));
    }

    /**
     * Sets this double to the maximum of {@code this} and {@code s}.
     *
     * @param s The reference scalar
     */
    public void max(double s) {
        set(Math.max(get(), s));
    }

    /**
     * Clamps this double to respect the given boundaries.
     *
     * @param min The minimum allowed value
     * @param max The maximum allowed value
     */
    public void clamp(double min, double max) {
        set(Numbers.clamp(get(), min, max));
    }
}
