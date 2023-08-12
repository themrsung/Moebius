package civitas.celestis.math.vector;

import civitas.celestis.math.Numbers;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.Arrays;
import java.util.function.UnaryOperator;

/**
 * A mutable vector class with an adjustable length.
 */
public class MutableVector implements Vector {
    //
    // Constructors
    //

    /**
     * Creates a new vector.
     *
     * @param initialLength The initial length of this vector.
     */
    public MutableVector(int initialLength) {
        this.values = new double[initialLength];
        this.length = initialLength;
    }

    /**
     * Creates a new vector.
     *
     * @param values The initial values of this vector
     */
    public MutableVector(@Nonnull double... values) {
        this.values = Arrays.copyOf(values, values.length);
        this.length = values.length;

        enforceNonFinite();
    }

    /**
     * Creates a new vector.
     *
     * @param other The other vector to copy component values from
     */
    public MutableVector(@Nonnull Vector other) {
        this.values = Arrays.copyOf(other.values(), other.length());
        this.length = other.length();

        enforceNonFinite();
    }

    /**
     * Enforces the non-finite constraint to all values of this vector.
     */
    private void enforceNonFinite() {
        for (final double value : values) {
            Numbers.requireFinite(value);
        }
    }

    //
    // Variables
    //
    @Nonnull
    private double[] values;
    private int length;

    //
    // Getters
    //

    /**
     * {@inheritDoc}
     *
     * @param i Index of component to get
     * @return The component value at the given index
     * @throws IndexOutOfBoundsException
     */
    @Override
    public final double valueAt(int i) throws IndexOutOfBoundsException {
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
    @Override
    public final double[] values() {
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
    public final void setValueAt(int i, double value) throws IndexOutOfBoundsException {
        values[i] = Numbers.requireFinite(value);
    }

    /**
     * Appends a component scalar to the end of this vector.
     *
     * @param value The value to append to this vector
     */
    public final void append(double value) {
        resize(length + 1);
        values[length++] = Numbers.requireFinite(value);
    }

    /**
     * Resizes this vector to hold
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
     * This reassigns {@link MutableVector#values} to a new array of length {@link MutableVector#length}.
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
    @Override
    public final double magnitude() {
        return Math.sqrt(magnitude2());
    }

    /**
     * {@inheritDoc}
     *
     * @return The sum of all components' square in this vector
     */
    @Override
    public final double magnitude2() {
        double sumOfSquares = 0;

        for (int i = 0; i < length; i++) {
            sumOfSquares += (values[i] * values[i]);
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
    public MutableVector add(double s) {
        final MutableVector result = new MutableVector(length);

        for (int i = 0; i < length; i++) {
            result.values[i] = Numbers.requireFinite(values[i] + s);
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
    public MutableVector subtract(double s) {
        final MutableVector result = new MutableVector(length);

        for (int i = 0; i < length; i++) {
            result.values[i] = Numbers.requireFinite(values[i] - s);
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
    public MutableVector multiply(double s) {
        final MutableVector result = new MutableVector(length);

        for (int i = 0; i < length; i++) {
            result.values[i] = Numbers.requireFinite(values[i] * s);
        }

        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @param s Scalar to divide this vector by
     * @return The resulting vector
     * @throws ArithmeticException When the denominator is zero
     */
    @Nonnull
    @Override
    public MutableVector divide(double s) throws ArithmeticException {
        if (s == 0) {
            throw new ArithmeticException("Cannot divide by zero.");
        }

        final MutableVector result = new MutableVector(length);

        for (int i = 0; i < length; i++) {
            result.values[i] = Numbers.requireFinite(values[i] / s);
        }

        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to add to this vector
     * @return The resulting vector
     * @throws IllegalArgumentException When the vector {@code v} has a different length from this vector
     */
    @Nonnull
    @Override
    public MutableVector add(@Nonnull Vector v) throws IllegalArgumentException {
        if (length != v.length()) {
            throw new IllegalArgumentException("Cannot perform addition between vectors of different length.");
        }

        final MutableVector result = new MutableVector(length);

        for (int i = 0; i < length; i++) {
            result.values[i] = values[i] + v.valueAt(i);
        }

        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to subtract from this vector
     * @return The resulting vector
     * @throws IllegalArgumentException When the vector {@code v} has a different length from this vector
     */
    @Nonnull
    @Override
    public MutableVector subtract(@Nonnull Vector v) throws IllegalArgumentException {
        if (length != v.length()) {
            throw new IllegalArgumentException("Cannot perform subtraction between vectors of different length.");
        }

        final MutableVector result = new MutableVector(length);

        for (int i = 0; i < length; i++) {
            result.values[i] = values[i] - v.valueAt(i);
        }

        return result;
    }

    /**
     * {@inheritDoc}
     * Vector multiplication of {@link MutableVector} is only supported when the length of the parameter vectors
     * is {@code 2} or {@code 4}.
     * The former case uses complex number multiplication, while the latter uses quaternion multiplication.
     *
     * @param v The vector to multiply this vector by
     * @return The resulting vector
     * @throws IllegalArgumentException      When the vector {@code v} has a different length from this vector
     * @throws UnsupportedOperationException When the vectors' length is not {@code 2} or {@code 4}
     */
    @Nonnull
    @Override
    public MutableVector multiply(@Nonnull Vector v) throws IllegalArgumentException, UnsupportedOperationException {
        if (length != v.length()) {
            throw new IllegalArgumentException("Cannot perform multiplication between vectors of different length.");
        }

        if (length != 2 && length != 4) {
            throw new UnsupportedOperationException("Multiplication between vectors of size " + length + " has no default definition.");
        }

        final MutableVector result = new MutableVector(length);

        switch (length) {
            // Complex number multiplication
            case 2 -> {
                final double x1 = values[0];
                final double y1 = values[1];

                final double x2 = v.valueAt(0);
                final double y2 = v.valueAt(1);

                result.setValueAt(0, x1 * x2 - y1 * y2);
                result.setValueAt(1, x1 * y2 + y1 * x2);
            }

            // Quaternion multiplication
            case 4 -> {
                final double w1 = values[0];
                final double x1 = values[1];
                final double y1 = values[2];
                final double z1 = values[3];

                final double w2 = v.valueAt(0);
                final double x2 = v.valueAt(1);
                final double y2 = v.valueAt(2);
                final double z2 = v.valueAt(3);

                result.setValueAt(0, w1 * w2 - x1 * x2 - y1 * y2 - z1 * z2);
                result.setValueAt(1, w1 * x2 + x1 * w2 + y1 * z2 - z1 * y2);
                result.setValueAt(2, w1 * y2 - x1 * z2 + y1 * w2 + z1 * x2);
                result.setValueAt(3, w1 * z2 + x1 * y2 - y1 * x2 + z1 * w2);
            }

            // This should not be reached under normal circumstances
            default -> throw new UnknownError();
        }

        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the dot product between
     * @return The sum of every components' product
     * @throws IllegalArgumentException When the vector {@code v} has a different length from this vector
     */
    @Override
    public double dot(@Nonnull Vector v) throws IllegalArgumentException {
        if (length != v.length()) {
            throw new IllegalArgumentException("Cannot perform a dot-product operation between vectors of different length.");
        }

        double result = 0;

        for (int i = 0; i < length; i++) {
            result += values[i] * v.valueAt(i);
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
     * @return The minimum vector between the two vectors
     * @throws IllegalArgumentException When the vector {@code v} has a different length from this vector
     */
    @Nonnull
    @Override
    public MutableVector min(@Nonnull Vector v) throws IllegalArgumentException {
        if (length != v.length()) {
            throw new IllegalArgumentException("Cannot perform a min operation between vectors of different length.");
        }

        final MutableVector result = new MutableVector(length);

        for (int i = 0; i < length; i++) {
            result.values[i] = Math.min(values[i], v.valueAt(i));
        }

        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the maximum vector of
     * @return The maximum vector between the two vectors
     * @throws IllegalArgumentException When the vector {@code v} has a different length from this vector
     */
    @Nonnull
    @Override
    public MutableVector max(@Nonnull Vector v) throws IllegalArgumentException {
        if (length != v.length()) {
            throw new IllegalArgumentException("Cannot perform a max operation between vectors of different length.");
        }

        final MutableVector result = new MutableVector(length);

        for (int i = 0; i < length; i++) {
            result.values[i] = Math.max(values[i], v.valueAt(i));
        }

        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @param min The minimum allowed values
     * @param max The maximum allowed values
     * @return The resulting vector
     * @throws IllegalArgumentException When either of the vectors {@code min} or {@code max} has a different length from this vector
     */
    @Nonnull
    @Override
    public MutableVector clamp(@Nonnull Vector min, @Nonnull Vector max) throws IllegalArgumentException {
        if (length != min.length() || length != max.length()) {
            throw new IllegalArgumentException("Cannot perform a clamp operation between vectors of different length.");
        }

        final MutableVector result = new MutableVector(length);

        for (int i = 0; i < length; i++) {
            result.values[i] = Math.max(Math.min(values[i], max.valueAt(i)), min.valueAt(i));
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
    public MutableVector apply(@Nonnull UnaryOperator<Double> operator) {
        final MutableVector result = new MutableVector(length);

        for (int i = 0; i < length; i++) {
            result.values[i] = Numbers.requireFinite(operator.apply(values[i]));
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
    public MutableVector normalize() throws UnsupportedOperationException {
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
    public MutableVector negate() {
        return multiply(-1);
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
        if (!(obj instanceof Vector v)) return false;
        if (length != v.length()) return false;

        for (int i = 0; i < length; i++) {
            if (values[i] != v.valueAt(i)) return false;
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
    public static MutableVector parseVector(@Nonnull String input) throws IllegalArgumentException {
        if (!input.startsWith("MutableVector{")) {
            throw new NumberFormatException("Given string does not represent a MutableVector.");
        }

        final String cleanInput = input.replaceAll("MutableVector\\{values=|}|\\[|]", "");
        final String[] valueStrings = cleanInput.split(", ");

        final double[] values = new double[valueStrings.length];

        for (int i = 0; i < valueStrings.length; i++) {
            values[i] = Double.parseDouble(valueStrings[i]);
        }

        return new MutableVector(values);
    }

    /**
     * {@inheritDoc}
     *
     * @return The string representation of this vector
     */
    @Override
    @Nonnull
    public String toString() {
        return "MutableVector{" +
                "values=" + Arrays.toString(Arrays.copyOf(values, length)) +
                '}';
    }
}
