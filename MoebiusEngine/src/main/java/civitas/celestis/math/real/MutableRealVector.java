package civitas.celestis.math.real;

import civitas.celestis.math.vector.MutableVector;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.UnaryOperator;

/**
 * A mutable vector class with an adjustable length.
 */
public class MutableRealVector implements RealVector {
    //
    // Constructors
    //

    /**
     * Creates a new vector.
     *
     * @param initialLength The initial length of this vector.
     */
    public MutableRealVector(int initialLength) {
        this.values = new RealNumber[initialLength];
        this.length = initialLength;

        Arrays.fill(values, RealNumber.ZERO);
    }

    /**
     * Creates a new vector.
     *
     * @param values The initial values of this vector
     */
    public MutableRealVector(@Nonnull RealNumber... values) {
        this.values = Arrays.copyOf(values, values.length);
        this.length = values.length;

        enforceNonNull();
    }

    /**
     * Creates a new vector.
     *
     * @param other The other vector to copy component values from
     */
    public MutableRealVector(@Nonnull RealVector other) {
        this.values = Arrays.copyOf(other.values(), other.length());
        this.length = other.length();
    }

    /**
     * Enforces the non-null constraint to all components.
     */
    private void enforceNonNull() {
        for (final RealNumber value : values) {
            Objects.requireNonNull(value);
        }
    }

    //
    // Variables
    //
    @Nonnull
    private RealNumber[] values;
    private int length;

    //
    // Getters
    //

    /**
     * {@inheritDoc}
     *
     * @param i Index of component to get
     * @return The component value at the given index
     * @throws IndexOutOfBoundsException When the index {@code i} is out of bounds
     */
    @Nonnull
    @Override
    public final RealNumber valueAt(int i) throws IndexOutOfBoundsException {
        if (i >= length) {
            throw new IndexOutOfBoundsException("Index " + i + " is out of bounds for this vector.");
        }

        return values[i];
    }

    /**
     * {@inheritDoc}
     *
     * @return A trimmed copy of the array of values this vector holds
     */
    @Nonnull
    @Override
    public final RealNumber[] values() {
        // This ensures that values.length == length
        return Arrays.copyOf(values, length);
    }

    //
    // Setters
    //

    /**
     * Sets the value at the specified index to the given value.
     *
     * @param i     The index to set the value of
     * @param value The value to set to
     * @throws IndexOutOfBoundsException When the index {@code i} is out of bounds ({@code i >= length})
     */
    public final void setValueAt(int i, @Nonnull RealNumber value) throws IndexOutOfBoundsException {
        values[i] = Objects.requireNonNull(value);
    }

    /**
     * Appends a component number to the end of this vector.
     *
     * @param value The value to append to this vector
     */
    public final void append(@Nonnull RealNumber value) {
        resize(length + 1);
        values[length++] = Objects.requireNonNull(value);
    }

    /**
     * Resizes this vector to hold {@code size} amount of values.
     *
     * @param size The new size of this vector
     */
    public final void resize(int size) {
        // Check for illegal input
        if (size < 0) {
            throw new IllegalArgumentException("Cannot resize a vector to a size of less than 0.");
        }

        // Resize array if necessary
        if (values.length < size) {
            this.values = Arrays.copyOf(values, size);
        }

        // Set length of this vector
        this.length = size;
    }

    /**
     * Trims this vector.
     * This reassigns {@link MutableRealVector#values} to a new array of length {@link MutableRealVector#length}.
     */
    public final void trimToLength() {
        this.values = Arrays.copyOf(values, length);
    }

    //
    // Properties
    //

    /**
     * {@inheritDoc}
     *
     * @return The collective square root of all components' square in this vector
     */
    @Nonnull
    @Override
    public RealNumber magnitude() {
        return magnitude2().sqrt();
    }

    /**
     * {@inheritDoc}
     *
     * @return The sum of all components' square in this vector
     */
    @Nonnull
    @Override
    public RealNumber magnitude2() {
        RealNumber sumOfSquares = RealNumber.ZERO;

        for (int i = 0; i < length; i++) {
            sumOfSquares = sumOfSquares.add(values[i].multiply(values[i]));
        }

        return sumOfSquares;
    }

    /**
     * {@inheritDoc}
     *
     * @return The current length of this vector
     */
    @Override
    public final int length() {
        return length;
    }

    //
    // Arithmetic
    //

    /**
     * {@inheritDoc}
     *
     * @param s Scalar to add to this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public MutableRealVector add(@Nonnull RealNumber s) {
        final MutableRealVector result = new MutableRealVector(length);

        for (int i = 0; i < length; i++) {
            result.values[i] = values[i].add(s);
        }

        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @param s Scalar to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public MutableRealVector subtract(@Nonnull RealNumber s) {
        final MutableRealVector result = new MutableRealVector(length);

        for (int i = 0; i < length; i++) {
            result.values[i] = values[i].subtract(s);
        }

        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @param s Scalar to multiply this vector by
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public MutableRealVector multiply(@Nonnull RealNumber s) {
        final MutableRealVector result = new MutableRealVector(length);

        for (int i = 0; i < length; i++) {
            result.values[i] = values[i].multiply(s);
        }

        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @param s Scalar to divide this vector by
     * @return The resulting vector
     * @throws ArithmeticException When the denominator {@code s} is zero
     */
    @Nonnull
    @Override
    public MutableRealVector divide(@Nonnull RealNumber s) throws ArithmeticException {
        final MutableRealVector result = new MutableRealVector(length);

        for (int i = 0; i < length; i++) {
            result.values[i] = values[i].divide(s);
        }

        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to add to this vector
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length
     *                                  is different from this vector's length
     */
    @Nonnull
    @Override
    public MutableRealVector add(@Nonnull RealVector v) throws IllegalArgumentException {
        if (length != v.length()) {
            throw new IllegalArgumentException("The vectors have a different length.");
        }

        final MutableRealVector result = new MutableRealVector(length);

        for (int i = 0; i < length; i++) {
            result.values[i] = values[i].add(v.valueAt(i));
        }

        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to subtract from this vector
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length
     *                                  is different from this vector's length
     */
    @Nonnull
    @Override
    public MutableRealVector subtract(@Nonnull RealVector v) throws IllegalArgumentException {
        if (length != v.length()) {
            throw new IllegalArgumentException("The vectors have a different length.");
        }

        final MutableRealVector result = new MutableRealVector(length);

        for (int i = 0; i < length; i++) {
            result.values[i] = values[i].subtract(v.valueAt(i));
        }

        return result;
    }

    /**
     * Multiplication is not supported for this vector by default.
     * <p>
     * While it is possible to perform multiplication if the length of the vectors is
     * either {@code 2} of {@code 4}, this is not implemented due to the performance overhead
     * caused by the mutability of this vector and the non-primitive nature of {@link RealNumber}.
     * </p>
     * <p>
     * Custom behavior can be implemented by extending this class.
     * </p>
     *
     * @param v The vector to multiply this vector by
     * @return This method cannot return a value
     * @throws UnsupportedOperationException Always throws an exception
     */
    @Nonnull
    @Override
    public MutableRealVector multiply(@Nonnull RealVector v) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Multiplication is not supported for this vector.");
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the dot product between
     * @return The dot product of the two vectors
     * @throws IllegalArgumentException When the provided vector {@code v}'s length
     *                                  is different from this vector's length
     */
    @Nonnull
    @Override
    public RealNumber dot(@Nonnull RealVector v) throws IllegalArgumentException {
        if (length != v.length()) {
            throw new IllegalArgumentException("The vectors have a different length.");
        }

        RealNumber result = RealNumber.ZERO;

        for (int i = 0; i < length; i++) {
            result = result.add(values[i].multiply(v.valueAt(i)));
        }

        return result;
    }

    //
    // Clamping
    //

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the minimum vector of
     * @return The minimum vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length
     *                                  is different from this vector's length
     */
    @Nonnull
    @Override
    public MutableRealVector min(@Nonnull RealVector v) throws IllegalArgumentException {
        if (length != v.length()) {
            throw new IllegalArgumentException("The vectors have a different length.");
        }

        final MutableRealVector result = new MutableRealVector(length);

        for (int i = 0; i < length; i++) {
            result.values[i] = values[i].min(v.valueAt(i));
        }

        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the maximum vector of
     * @return The maximum vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length
     *                                  is different from this vector's length
     */
    @Nonnull
    @Override
    public MutableRealVector max(@Nonnull RealVector v) throws IllegalArgumentException {
        if (length != v.length()) {
            throw new IllegalArgumentException("The vectors have a different length.");
        }

        final MutableRealVector result = new MutableRealVector(length);

        for (int i = 0; i < length; i++) {
            result.values[i] = values[i].max(v.valueAt(i));
        }

        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @param min The minimum allowed values
     * @param max The maximum allowed values
     * @return The clamped vector
     * @throws IllegalArgumentException When the provided vector {@code min} or {@code max}'s length
     *                                  is different from this vector's length
     */
    @Nonnull
    @Override
    public MutableRealVector clamp(@Nonnull RealVector min, @Nonnull RealVector max) throws IllegalArgumentException {
        if (length != min.length() || min.length() != max.length()) {
            throw new IllegalArgumentException("The vectors have a different length.");
        }

        final MutableRealVector result = new MutableRealVector(length);

        for (int i = 0; i < length; i++) {
            result.values[i] = values[i].clamp(min.valueAt(i), max.valueAt(i));
        }

        return result;
    }

    //
    // Utility
    //

    /**
     * {@inheritDoc}
     *
     * @param operator The operator to apply to each component of this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public MutableRealVector apply(@Nonnull UnaryOperator<RealNumber> operator) {
        final MutableRealVector result = new MutableRealVector(length);

        for (int i = 0; i < length; i++) {
            result.values[i] = Objects.requireNonNull(operator.apply(values[i]));
        }

        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @return The normalized vector
     * @throws UnsupportedOperationException When the magnitude of this vector is zero
     */
    @Nonnull
    @Override
    public MutableRealVector normalize() throws UnsupportedOperationException {
        try {
            return divide(magnitude());
        } catch (final ArithmeticException e) {
            throw new UnsupportedOperationException("Cannot derive a normalized vector of a vector with no magnitude.");
        }
    }

    /**
     * {@inheritDoc}
     *
     * @return The negation of this vector
     */
    @Nonnull
    @Override
    public MutableRealVector negate() {
        return multiply(new RealNumber(-1));
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the distance to
     * @return The distance between the two vectors
     * @throws IllegalArgumentException When the provided vector {@code min} or {@code max}'s length
     *                                  is different from this vector's length
     */
    @Nonnull
    @Override
    public RealNumber distance(@Nonnull RealVector v) throws IllegalArgumentException {
        return subtract(v).magnitude();
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the squared distance to
     * @return The squared distance between the two vectors
     * @throws IllegalArgumentException When the provided vector {@code min} or {@code max}'s length
     *                                  is different from this vector's length
     */
    @Nonnull
    @Override
    public RealNumber distance2(@Nonnull RealVector v) throws IllegalArgumentException {
        return subtract(v).magnitude2();
    }

    /**
     * {@inheritDoc}
     *
     * @return The {@link MutableVector} representation of this vector
     */
    @Nonnull
    @Override
    public MutableVector doubleValue() {
        final MutableVector result = new MutableVector(length);

        for (int i = 0; i < length; i++) {
            result.setValueAt(i, values[i].doubleValue());
        }

        return result;
    }

    //
    // Equality
    //

    /**
     * {@inheritDoc}
     *
     * @param obj The object to compare to
     * @return {@code true} if the given object is a vector, and the components and length are equal
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof RealVector v)) return false;
        if (length != v.length()) return false;

        for (int i = 0; i < length; i++) {
            if (!values[i].equals(v.valueAt(i))) return false;
        }

        return true;
    }

    //
    // Serialization
    //


    /**
     * Deserializes a string to parse a vector.
     *
     * @param input Input string to parse to a vector
     * @return The parsed vector
     * @throws IllegalArgumentException When at least one of the component scalars is non-finite
     * @throws NumberFormatException    When the string's format is invalid
     */
    @Nonnull
    public static MutableRealVector parseVector(@Nonnull String input) throws IllegalArgumentException {
        if (!input.startsWith("MutableRealVector{")) {
            throw new NumberFormatException("Given string does not represent a MutableRealVector.");
        }

        final String cleanInput = input.replaceAll("MutableRealVector\\{values=|}|\\[|]", "");
        final String[] valueStrings = cleanInput.split(", ");

        final RealNumber[] values = new RealNumber[valueStrings.length];

        for (int i = 0; i < valueStrings.length; i++) {
            values[i] = RealNumber.parseNumber(valueStrings[i]);
        }

        return new MutableRealVector(values);
    }

    /**
     * {@inheritDoc}
     *
     * @return The string representation of this vector
     */
    @Override
    @Nonnull
    public String toString() {
        return "MutableRealVector{" +
                "values=" + Arrays.toString(Arrays.copyOf(values, length)) +
                '}';
    }

}
