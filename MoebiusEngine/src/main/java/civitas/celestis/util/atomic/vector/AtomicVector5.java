package civitas.celestis.util.atomic.vector;

import civitas.celestis.math.vector.Vector5;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.UnaryOperator;

/**
 * An atomic reference to a {@link Vector5}.
 */
public class AtomicVector5 extends AtomicReference<Vector5> implements AtomicVector<Vector5> {
    //
    // Constructors
    //

    /**
     * Creates a new atomic vector.
     */
    public AtomicVector5() {
    }

    /**
     * Creates a new atomic vector.
     *
     * @param initialValue The initial value of this vector
     */
    public AtomicVector5(@Nullable Vector5 initialValue) {
        super(initialValue);
    }

    //
    // Getters
    //

    /**
     * Returns the V component of this vector.
     *
     * @return The V component of this vector
     * @throws NullPointerException When the reference points to {@code null}
     */
    public double v() throws NullPointerException {
        final Vector5 ref = get();
        if (ref == null) throw new NullPointerException("Reference points to null.");
        return ref.v();
    }

    /**
     * Returns the W component of this vector.
     *
     * @return The W component of this vector
     * @throws NullPointerException When the reference points to {@code null}
     */
    public double w() throws NullPointerException {
        final Vector5 ref = get();
        if (ref == null) throw new NullPointerException("Reference points to null.");
        return ref.w();
    }

    /**
     * Returns the X component of this vector.
     *
     * @return The X component of this vector
     * @throws NullPointerException When the reference points to {@code null}
     */
    public double x() throws NullPointerException {
        final Vector5 ref = get();
        if (ref == null) throw new NullPointerException("Reference points to null.");
        return ref.x();
    }

    /**
     * Returns the Y component of this vector.
     *
     * @return The Y component of this vector
     * @throws NullPointerException When the reference points to {@code null}
     */
    public double y() throws NullPointerException {
        final Vector5 ref = get();
        if (ref == null) throw new NullPointerException("Reference points to null.");
        return ref.y();
    }

    /**
     * Returns the Z component of this vector.
     *
     * @return The Z component of this vector
     * @throws NullPointerException When the reference points to {@code null}
     */
    public double z() throws NullPointerException {
        final Vector5 ref = get();
        if (ref == null) throw new NullPointerException("Reference points to null.");
        return ref.z();
    }

    //
    // Arithmetic
    //

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to add to this vector
     * @throws NullPointerException When the reference points to {@code null}
     */
    @Override
    public void add(double s) throws NullPointerException {
        final Vector5 ref = get();
        if (ref == null) throw new NullPointerException("Reference points to null.");
        set(ref.add(s));
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to subtract from this vector
     * @throws NullPointerException When the reference points to {@code null}
     */
    @Override
    public void subtract(double s) throws NullPointerException {
        final Vector5 ref = get();
        if (ref == null) throw new NullPointerException("Reference points to null.");
        set(ref.subtract(s));
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to multiply this vector by
     * @throws NullPointerException When the reference points to {@code null}
     */
    @Override
    public void multiply(double s) throws NullPointerException {
        final Vector5 ref = get();
        if (ref == null) throw new NullPointerException("Reference points to null.");
        set(ref.multiply(s));
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to divide this vector by
     * @throws NullPointerException When the reference points to {@code null}
     * @throws ArithmeticException  When the denominator {@code s} is zero
     */
    @Override
    public void divide(double s) throws NullPointerException, ArithmeticException {
        final Vector5 ref = get();
        if (ref == null) throw new NullPointerException("Reference points to null.");
        set(ref.divide(s));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to add to this vector
     * @throws NullPointerException When the reference points to {@code null}
     */
    @Override
    public void add(@Nonnull Vector5 v) throws NullPointerException {
        final Vector5 ref = get();
        if (ref == null) throw new NullPointerException("Reference points to null.");
        set(ref.add(v));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to subtract from this vector
     * @throws NullPointerException When the reference points to {@code null}
     */
    @Override
    public void subtract(@Nonnull Vector5 v) throws NullPointerException {
        final Vector5 ref = get();
        if (ref == null) throw new NullPointerException("Reference points to null.");
        set(ref.subtract(v));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector of which to multiply this vector with
     * @throws NullPointerException When the reference points to {@code null}
     */
    @Override
    public void multiply(@Nonnull Vector5 v) throws NullPointerException {
        final Vector5 ref = get();
        if (ref == null) throw new NullPointerException("Reference points to null.");
        set(ref.multiply(v));
    }

    //
    // Clamping
    //

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the minimum between
     * @throws NullPointerException When the reference points to {@code null}
     */
    @Override
    public void min(@Nonnull Vector5 v) throws NullPointerException {
        final Vector5 ref = get();
        if (ref == null) throw new NullPointerException("Reference points to null.");
        set(ref.min(v));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the maximum between
     * @throws NullPointerException When the reference points to {@code null}
     */
    @Override
    public void max(@Nonnull Vector5 v) throws NullPointerException {
        final Vector5 ref = get();
        if (ref == null) throw new NullPointerException("Reference points to null.");
        set(ref.max(v));
    }

    /**
     * {@inheritDoc}
     *
     * @param min The minimum bounds to respect
     * @param max The maximum bounds to respect
     * @throws NullPointerException When the reference points to {@code null}
     */
    @Override
    public void clamp(@Nonnull Vector5 min, @Nonnull Vector5 max) throws NullPointerException {
        final Vector5 ref = get();
        if (ref == null) throw new NullPointerException("Reference points to null.");
        set(ref.clamp(min, max));
    }

    //
    // Utility
    //

    /**
     * {@inheritDoc}
     *
     * @param operator The operator to apply to each component of this vector
     * @throws NullPointerException When the reference points to {@code null}
     */
    @Override
    public void apply(@Nonnull UnaryOperator<Double> operator) throws NullPointerException {
        final Vector5 ref = get();
        if (ref == null) throw new NullPointerException("Reference points to null.");
        set(ref.apply(operator));
    }

    /**
     * {@inheritDoc}
     *
     * @throws NullPointerException          When the reference points to {@code null}
     * @throws UnsupportedOperationException When the magnitude of this vector is zero
     */
    @Override
    public void normalize() throws NullPointerException, UnsupportedOperationException {
        final Vector5 ref = get();
        if (ref == null) throw new NullPointerException("Reference points to null.");
        set(ref.normalize());
    }

    /**
     * {@inheritDoc}
     *
     * @throws NullPointerException When the reference points to {@code null}
     */
    @Override
    public void negate() throws NullPointerException {
        final Vector5 ref = get();
        if (ref == null) throw new NullPointerException("Reference points to null.");
        set(ref.negate());
    }
}
