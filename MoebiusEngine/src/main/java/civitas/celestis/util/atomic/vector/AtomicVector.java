package civitas.celestis.util.atomic.vector;

import civitas.celestis.math.vector.Vector;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.function.UnaryOperator;

/**
 * A superinterface for atomic vector references.
 */
public interface AtomicVector<V extends Vector> {
    //
    // Methods copied from AtomicReference
    //

    /**
     * Returns a reference to the contained vector.
     *
     * @return The {@link V} reference
     */
    @Nullable
    V get();

    /**
     * Sets the value of this reference.
     *
     * @param v The value to set to
     */
    void set(V v);

    /**
     * Gets and updates the value of this reference.
     *
     * @param f The function to apply to the value of this reference
     * @return The previous value
     */
    V getAndUpdate(UnaryOperator<V> f);

    //
    // Getters
    //

    /**
     * Returns whether this reference points to {@code null}.
     *
     * @return {@code true} if the value of this reference is {@code null}
     */
    default boolean isNull() {
        return get() == null;
    }

    /**
     * Returns the value at the specified index.
     *
     * @param i The index of value to get
     * @return The value at the specified index
     * @throws NullPointerException      When the reference points to {@code null}
     * @throws IndexOutOfBoundsException When the index {@code i} is out of bounds
     */
    default double valueAt(int i) throws NullPointerException, IndexOutOfBoundsException {
        final V ref = get();
        if (ref == null) throw new NullPointerException("Reference points to null.");
        return ref.valueAt(i);
    }

    /**
     * Returns an array of component values of this vector.
     *
     * @return The array representation of this vector
     * @throws NullPointerException When the reference points to {@code null}
     */
    @Nonnull
    default double[] values() throws NullPointerException {
        final V ref = get();
        if (ref == null) throw new NullPointerException("Reference points to null.");
        return ref.values();
    }

    /**
     * Returns the magnitude of this vector.
     *
     * @return The magnitude of this vector
     * @throws NullPointerException When the reference points to {@code null}
     */
    default double magnitude() throws NullPointerException {
        final V ref = get();
        if (ref == null) throw new NullPointerException("Reference points to null.");
        return ref.magnitude();
    }

    /**
     * Returns the squared magnitude of this vector.
     *
     * @return The squared magnitude of this vector
     * @throws NullPointerException When the reference points to {@code null}
     */
    default double magnitude2() throws NullPointerException {
        final V ref = get();
        if (ref == null) throw new NullPointerException("Reference points to null.");
        return ref.magnitude2();
    }

    /**
     * Returns the length of this vector.
     *
     * @return The length of this vector
     * @throws NullPointerException When the reference points to {@code null}
     */
    default int length() throws NullPointerException {
        final V ref = get();
        if (ref == null) throw new NullPointerException("Reference points to null.");
        return ref.length();
    }

    //
    // Arithmetic
    //

    /**
     * Adds a scalar to this vector.
     *
     * @param s The scalar to add to this vector
     * @throws NullPointerException When the reference points to {@code null}
     */
    void add(double s) throws NullPointerException;

    /**
     * Subtracts a scalar from this vector.
     *
     * @param s The scalar to subtract from this vector
     * @throws NullPointerException When the reference points to {@code null}
     */
    void subtract(double s) throws NullPointerException;

    /**
     * Multiplies this vector by a scalar.
     *
     * @param s The scalar to multiply this vector by
     * @throws NullPointerException When the reference points to {@code null}
     */
    void multiply(double s) throws NullPointerException;

    /**
     * Divides this vector by a scalar.
     *
     * @param s The scalar to divide this vector by
     * @throws NullPointerException When the reference points to {@code null}
     * @throws ArithmeticException  When the denominator {@code s} is zero
     */
    void divide(double s) throws NullPointerException, ArithmeticException;

    /**
     * Adds a vector to this vector.
     *
     * @param v The vector to add to this vector
     * @throws NullPointerException When the reference points to {@code null}
     */
    void add(@Nonnull V v) throws NullPointerException;

    /**
     * Subtracts a vector from this vector.
     *
     * @param v The vector to subtract from this vector
     * @throws NullPointerException When the reference points to {@code null}
     */
    void subtract(@Nonnull V v) throws NullPointerException;

    /**
     * Multiplies this vector by the given vector.
     *
     * @param v The vector of which to multiply this vector with
     * @throws NullPointerException          When the reference points to {@code null}
     * @throws UnsupportedOperationException When the operation is unsupported with this type of vector
     */
    void multiply(@Nonnull V v) throws NullPointerException, UnsupportedOperationException;

    //
    // Clamping
    //

    /**
     * Performs the {@link Vector#min(Vector)} operation.
     *
     * @param v The vector to get the minimum between
     * @throws NullPointerException When the reference points to {@code null}
     */
    void min(@Nonnull V v) throws NullPointerException;

    /**
     * Performs the {@link Vector#max(Vector)} operation.
     *
     * @param v The vector to get the maximum between
     * @throws NullPointerException When the reference points to {@code null}
     */
    void max(@Nonnull V v) throws NullPointerException;

    /**
     * Performs the {@link Vector#clamp(Vector, Vector)} operation.
     *
     * @param min The minimum bounds to respect
     * @param max The maximum bounds to respect
     * @throws NullPointerException When the reference points to {@code null}
     */
    void clamp(@Nonnull V min, @Nonnull V max) throws NullPointerException;

    //
    // Utility
    //

    /**
     * Applies given operator to each component of this vector
     *
     * @param operator The operator to apply to each component of this vector
     * @throws NullPointerException When the reference points to {@code null}
     */
    void apply(@Nonnull UnaryOperator<Double> operator) throws NullPointerException;

    /**
     * Normalizes this vector.
     *
     * @throws NullPointerException          When the reference points to {@code null}
     * @throws UnsupportedOperationException When the magnitude of this vector is zero
     */
    void normalize() throws NullPointerException, UnsupportedOperationException;

    /**
     * Negates this vector.
     *
     * @throws NullPointerException When the reference points to {@code null}
     */
    void negate() throws NullPointerException;
}
