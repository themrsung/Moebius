package civitas.celestis.math.integer;

import civitas.celestis.math.vector.MutableVector;
import civitas.celestis.math.vector.Vector;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.Arrays;
import java.util.function.UnaryOperator;

/**
 * A mutable vector class with an adjustable length.
 */
public class MutableIntVector implements IntVector {
    //
    // Constructors
    //

    /**
     * Creates a new vector.
     *
     * @param initialLength The initial length of this vector
     */
    public MutableIntVector(int initialLength) {
        this.values = new int[initialLength];
        this.length = initialLength;
    }

    /**
     * Creates a new vector.
     *
     * @param values The initial values of this vector
     */
    public MutableIntVector(@Nonnull int... values) {
        this.values = Arrays.copyOf(values, values.length);
        this.length = values.length;
    }

    /**
     * Creates a new vector.
     *
     * @param v The {@link Vector} to copy values from
     */
    public MutableIntVector(@Nonnull Vector v) {
        this.values = new int[v.length()];
        this.length = v.length();

        for (int i = 0; i < length; i++) {
            this.values[i] = (int) v.valueAt(i);
        }
    }

    /**
     * Creates a new vector.
     *
     * @param other The other vector to copy component values from
     */
    public MutableIntVector(@Nonnull IntVector other) {
        this.values = Arrays.copyOf(other.values(), other.length());
        this.length = other.length();
    }

    //
    // Variables
    //
    @Nonnull
    private int[] values;
    private int length;

    //
    // Getters
    //

    /**
     * {@inheritDoc}
     *
     * @param i Index of component to get
     * @return The value of given index
     * @throws IndexOutOfBoundsException When the index is out of bounds
     */
    @Override
    public final int valueAt(int i) throws IndexOutOfBoundsException {
        if (i >= length) {
            throw new IndexOutOfBoundsException("Index " + i + " is out of bounds for this vector.");
        }

        return values[i];
    }

    /**
     * {@inheritDoc}
     *
     * @return The array representation of this vector
     */
    @Override
    public final int[] values() {
        // This ensures that values.length == length
        return Arrays.copyOf(values, length);
    }

    //
    // Setters
    //

    /**
     * Sets the value of given index.
     *
     * @param i     Index to set
     * @param value Value to set to
     * @throws IndexOutOfBoundsException When the index is out of bounds
     */
    public void setValueAt(int i, int value) throws IndexOutOfBoundsException {
        values[i] = value;
    }

    /**
     * Appends the value to the end of this vector.
     *
     * @param value Value to append
     */
    public void append(int value) {
        resize(length + 1);
        values[length++] = value;
    }

    /**
     * Resizes this vector.
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
     * Trims this vector so that the length of {@link MutableIntVector#values} is equal to {@link MutableIntVector#length}.
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
     * @return The magnitude of this vector
     */
    @Override
    public final double magnitude() {
        return Math.sqrt(magnitude2());
    }

    /**
     * {@inheritDoc}
     *
     * @return The squared magnitude of this vector
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
     * @return The number of components in this vector
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
     * @param s The scalar to add to this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public MutableIntVector add(int s) {
        final MutableIntVector result = new MutableIntVector(length);

        for (int i = 0; i < length; i++) {
            result.values[i] = values[i] + s;
        }

        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public MutableIntVector subtract(int s) {
        final MutableIntVector result = new MutableIntVector(length);

        for (int i = 0; i < length; i++) {
            result.values[i] = values[i] - s;
        }

        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to multiply this vector by
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public MutableIntVector multiply(int s) {
        final MutableIntVector result = new MutableIntVector(length);

        for (int i = 0; i < length; i++) {
            result.values[i] = values[i] * s;
        }

        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @param s The scalar to divide this vector by
     * @return The resulting vector
     * @throws ArithmeticException When the denominator {@code s} is zero
     */
    @Nonnull
    @Override
    public MutableIntVector divide(int s) throws ArithmeticException {
        final MutableIntVector result = new MutableIntVector(length);

        for (int i = 0; i < length; i++) {
            result.values[i] = values[i] / s;
        }

        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to add to this vector
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length if different from this vector's length
     */
    @Nonnull
    @Override
    public MutableIntVector add(@Nonnull IntVector v) throws IllegalArgumentException {
        if (length != v.length()) {
            throw new IllegalArgumentException("Cannot perform addition between vectors of different length.");
        }

        final MutableIntVector result = new MutableIntVector(length);

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
     * @throws IllegalArgumentException When the provided vector {@code v}'s length if different from this vector's length
     */
    @Nonnull
    @Override
    public MutableIntVector subtract(@Nonnull IntVector v) throws IllegalArgumentException {
        if (length != v.length()) {
            throw new IllegalArgumentException("Cannot perform subtraction between vectors of different length.");
        }

        final MutableIntVector result = new MutableIntVector(length);

        for (int i = 0; i < length; i++) {
            result.values[i] = values[i] - v.valueAt(i);
        }

        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to multiply this vector by
     * @return This method cannot return a value
     * @throws UnsupportedOperationException Always throws an exception
     */
    @Nonnull
    @Override
    public MutableIntVector multiply(@Nonnull IntVector v) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("There is no default multiplication operation defined for MutableVector.");
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the dot product between
     * @return The dot product of the two vectors
     * @throws IllegalArgumentException When the given vector's length is different from this vector's length
     */
    @Override
    public int dot(@Nonnull IntVector v) throws IllegalArgumentException {
        if (length != v.length()) {
            throw new IllegalArgumentException("The given vector has a different length.");
        }

        int sum = 0;

        for (int i = 0; i < length; i++) {
            sum += values[i] * v.valueAt(i);
        }

        return sum;
    }

    //
    // Clamping
    //


    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the minimum vector of
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is different from this vector's length
     */
    @Nonnull
    @Override
    public MutableIntVector min(@Nonnull IntVector v) throws IllegalArgumentException {
        if (length != v.length()) {
            throw new IllegalArgumentException("Cannot perform a min operation between vectors of different length.");
        }

        final MutableIntVector result = new MutableIntVector(length);

        for (int i = 0; i < length; i++) {
            result.values[i] = Math.min(values[i], v.valueAt(i));
        }

        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the maximum vector of
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is different from this vector's length
     */
    @Nonnull
    @Override
    public MutableIntVector max(@Nonnull IntVector v) throws IllegalArgumentException {
        if (length != v.length()) {
            throw new IllegalArgumentException("Cannot perform a max operation between vectors of different length.");
        }

        final MutableIntVector result = new MutableIntVector(length);

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
     * @throws IllegalArgumentException When the provided vector {@code min} or {@code max}'s length is
     *                                  different from this vector's length
     */
    @Nonnull
    @Override
    public MutableIntVector clamp(@Nonnull IntVector min, @Nonnull IntVector max) throws IllegalArgumentException {
        if (length != min.length() || length != max.length()) {
            throw new IllegalArgumentException("Cannot perform a clamp operation between vectors of different length.");
        }

        final MutableIntVector result = new MutableIntVector(length);

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
    public MutableIntVector apply(@Nonnull UnaryOperator<Integer> operator) {
        final MutableIntVector result = new MutableIntVector(length);

        for (int i = 0; i < length; i++) {
            result.values[i] = operator.apply(values[i]);
        }

        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @return The negation of this vector
     */
    @Nonnull
    @Override
    public MutableIntVector negate() {
        return multiply(-1);
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the distance to
     * @return The distance between this vector and {@code v}
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is different from this vector's length
     */
    @Override
    public double distance(@Nonnull IntVector v) throws IllegalArgumentException {
        return subtract(v).magnitude();
    }

    /**
     * {@inheritDoc}
     *
     * @param v The vector to get the squared distance to
     * @return The squared distance between this vector and {@code v}
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is different from this vector's length
     */
    @Override
    public double distance2(@Nonnull IntVector v) throws IllegalArgumentException {
        return subtract(v).magnitude2();
    }

    /**
     * {@inheritDoc}
     *
     * @return A {@link MutableVector} derived from {@code this}
     */
    @Nonnull
    @Override
    public MutableVector toDouble() {
        final double[] doubleValues = new double[length];

        for (int i = 0; i < length; i++) {
            doubleValues[i] = values[i];
        }

        return new MutableVector(doubleValues);
    }

    //
    // Equality
    //

    /**
     * {@inheritDoc}
     *
     * @param obj The object to compare to
     * @return {@code true} if the object is a vector, and the components and length are equal
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof IntVector v)) return false;
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
     * @throws NumberFormatException When the string's format is invalid
     */
    @Nonnull
    public static MutableIntVector parseVector(@Nonnull String input) throws NumberFormatException {
        if (!input.startsWith("MutableIntVector{")) {
            throw new NumberFormatException("Given string does not represent a MutableIntVector.");
        }

        final String cleanInput = input.replaceAll("MutableIntVector\\{values=|}|\\[|]", "");
        final String[] valueStrings = cleanInput.split(", ");

        final int[] values = new int[valueStrings.length];

        for (int i = 0; i < valueStrings.length; i++) {
            values[i] = Integer.parseInt(valueStrings[i]);
        }

        return new MutableIntVector(values);
    }

    /**
     * {@inheritDoc}
     *
     * @return The string representation of this vector
     */
    @Override
    @Nonnull
    public String toString() {
        return "MutableIntVector{" +
                "values=" + Arrays.toString(Arrays.copyOf(values, length)) +
                '}';
    }
}
