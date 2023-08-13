package civitas.celestis.math.decimal;

import civitas.celestis.math.vector.MutableVector;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;

/**
 * A mutable implementation of {@link DecimalVector}.
 * Values can be reassigned after instantiation.
 * The length of this vector can also be changed.
 */
public class MutableDecimalVector implements DecimalVector {
    //
    // Constructors
    //

    /**
     * Creates a new vector.
     * @param initialLength The initial length of this vector
     */
    public MutableDecimalVector(int initialLength) {
        this.values = new ArrayList<>(initialLength);
    }

    /**
     * Creates a new vector.
     * @param values The values to use as the components of this vector
     */
    public MutableDecimalVector(@Nonnull BigDecimal... values) {
        this.values = Arrays.asList(values);
    }

    //
    // Variables
    //

    @Nonnull
    private final List<BigDecimal> values;

    //
    // Getters
    //

    /**
     * {@inheritDoc}
     * @param i Index of component to get
     * @return The value at the specified index
     * @throws IndexOutOfBoundsException When the index {@code i} is out of bounds
     */
    @Nonnull
    @Override
    public BigDecimal valueAt(int i) throws IndexOutOfBoundsException {
        return values.get(i);
    }

    /**
     * {@inheritDoc}
     * @return The list of values
     */
    @Nonnull
    @Override
    public List<BigDecimal> values() {
        return List.copyOf(values);
    }

    //
    // Setters
    //

    /**
     * Sets the {@code i}th value of this vector.
     * @param i The index of value to set
     * @param value The value to set to
     * @throws IndexOutOfBoundsException When the index {@code i} is out of bounds
     */
    public void setValueAt(int i, @Nonnull BigDecimal value) throws IndexOutOfBoundsException {
        values.set(i, value);
    }

    /**
     * Appends given value to the end of this vector.
     * @param value The value to append
     */
    public void append(@Nonnull BigDecimal value) {
        values.add(value);
    }

    //
    // Properties
    //

    /**
     * {@inheritDoc}
     * @param context The mathematical context to use in the square root operation
     *
     * @return The magnitude of this vector
     */
    @Nonnull
    @Override
    public BigDecimal magnitude(@Nonnull MathContext context) {
        return magnitude2().sqrt(context);
    }

    /**
     * {@inheritDoc}
     * @return The squared magnitude of this vector
     */
    @Nonnull
    @Override
    public BigDecimal magnitude2() {
        BigDecimal sumOfSquares = BigDecimal.ZERO;

        for (final BigDecimal value : values) {
            sumOfSquares = sumOfSquares.add(value.multiply(value));
        }

        return sumOfSquares;
    }

    /**
     * {@inheritDoc}
     * @return The length of this vector
     */
    @Override
    public int length() {
        return values.size();
    }

    //
    // Arithmetic
    //

    /**
     * {@inheritDoc}
     * @param s The scalar to add to this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public MutableDecimalVector add(@Nonnull BigDecimal s) {
        final MutableDecimalVector result = new MutableDecimalVector(length());
        values.forEach(v -> result.append(v.add(s)));
        return result;
    }

    /**
     * {@inheritDoc}
     * @param s The scalar to subtract this vector by
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public MutableDecimalVector subtract(@Nonnull BigDecimal s) {
        final MutableDecimalVector result = new MutableDecimalVector(length());
        values.forEach(v -> result.append(v.subtract(s)));
        return result;
    }

    /**
     * {@inheritDoc}
     * @param s The scalar to multiply this vector by
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public MutableDecimalVector multiply(@Nonnull BigDecimal s) {
        final MutableDecimalVector result = new MutableDecimalVector(length());
        values.forEach(v -> result.append(v.multiply(s)));
        return result;
    }

    /**
     * {@inheritDoc}
     * @param s The scalar to divide this vector by
     * @param context The mathematical context to use in the division operation
     * @return The resulting vector
     * @throws ArithmeticException When the denominator {@code s} is zero
     */
    @Nonnull
    @Override
    public MutableDecimalVector divide(@Nonnull BigDecimal s, @Nonnull MathContext context) throws ArithmeticException {
        final MutableDecimalVector result = new MutableDecimalVector(length());
        values.forEach(v -> result.append(v.divide(s, context)));
        return result;
    }

    /**
     * {@inheritDoc}
     * @param v The vector to add to this vector
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is different from this vector
     */
    @Nonnull
    @Override
    public MutableDecimalVector add(@Nonnull DecimalVector v) throws IllegalArgumentException {
        if (length() != v.length()) {
            throw new IllegalArgumentException("The provided vector has a different length from this vector.");
        }

        final MutableDecimalVector result = new MutableDecimalVector(length());

        for (int i = 0; i < length(); i++) {
            result.append(values.get(i).add(v.valueAt(i)));
        }

        return result;
    }

    /**
     * {@inheritDoc}
     * @param v The vector to subtract from this vector
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is different from this vector
     */
    @Nonnull
    @Override
    public MutableDecimalVector subtract(@Nonnull DecimalVector v) throws IllegalArgumentException {
        if (length() != v.length()) {
            throw new IllegalArgumentException("The provided vector has a different length from this vector.");
        }

        final MutableDecimalVector result = new MutableDecimalVector(length());

        for (int i = 0; i < length(); i++) {
            result.append(values.get(i).subtract(v.valueAt(i)));
        }

        return result;
    }

    /**
     * Since there is no default multiplication defined for a mutable vector,
     * this will always throw an exception.
     * @param v The vector to multiply this vector by
     * @return This method cannot return a value
     * @throws UnsupportedOperationException Always throws an exception
     */
    @Nonnull
    @Override
    public DecimalVector multiply(@Nonnull DecimalVector v) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Mutable vectors have no default definition of multiplication.");
    }

    /**
     * {@inheritDoc}
     * @param v The vector to get the dot product between
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is different from this vector
     */
    @Nonnull
    @Override
    public BigDecimal dot(@Nonnull DecimalVector v) throws IllegalArgumentException {
        if (length() != v.length()) {
            throw new IllegalArgumentException("The provided vector has a different length from this vector.");
        }

        BigDecimal sumOfProducts = BigDecimal.ZERO;

        for (int i = 0; i < length(); i++) {
            sumOfProducts = sumOfProducts.add(values.get(i).multiply(v.valueAt(i)));
        }

        return sumOfProducts;
    }

    //
    // Clamping
    //

    /**
     * {@inheritDoc}
     * @param v The vector to get the minimum vector of
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is different from this vector
     */
    @Nonnull
    @Override
    public MutableDecimalVector min(@Nonnull DecimalVector v) throws IllegalArgumentException {
        if (length() != v.length()) {
            throw new IllegalArgumentException("The provided vector has a different length from this vector.");
        }

        final MutableDecimalVector result = new MutableDecimalVector(length());

        for (int i = 0; i < length(); i++) {
            result.append(values.get(i).min(v.valueAt(i)));
        }

        return result;
    }

    /**
     * {@inheritDoc}
     * @param v The vector to get the maximum vector of
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is different from this vector
     */
    @Nonnull
    @Override
    public MutableDecimalVector max(@Nonnull DecimalVector v) throws IllegalArgumentException {
        if (length() != v.length()) {
            throw new IllegalArgumentException("The provided vector has a different length from this vector.");
        }

        final MutableDecimalVector result = new MutableDecimalVector(length());

        for (int i = 0; i < length(); i++) {
            result.append(values.get(i).max(v.valueAt(i)));
        }

        return result;
    }

    /**
     * {@inheritDoc}
     * @param min The minimum allowed values
     * @param max The minimum allowed values
     * @return The resulting vector
     * @throws IllegalArgumentException When the provided vector {@code min} or {@code max}'s
     * length is different from this vector
     */
    @Nonnull
    @Override
    public MutableDecimalVector clamp(@Nonnull DecimalVector min, @Nonnull DecimalVector max) throws IllegalArgumentException {
        if (length() != min.length() || min.length() != max.length()) {
            throw new IllegalArgumentException("The provided vectors have a different length from this vector.");
        }

        final MutableDecimalVector result = new MutableDecimalVector(length());

        for (int i = 0; i < length(); i++) {
            result.append(values.get(i).min(max.valueAt(i)).min(min.valueAt(i)));
        }

        return result;
    }

    //
    // Utility
    //

    /**
     * {@inheritDoc}
     * @param operator The operator to apply to each component of this vector
     * @return The resulting vector
     */
    @Nonnull
    @Override
    public MutableDecimalVector apply(@Nonnull UnaryOperator<BigDecimal> operator) {
        final MutableDecimalVector result = new MutableDecimalVector(length());
        values.forEach(v -> result.append(operator.apply(v)));
        return result;
    }

    /**
     * {@inheritDoc}
     * @param context The mathematical context to use for square root and division
     * @return The normalized vector
     * @throws UnsupportedOperationException When the magnitude of this vector is exactly zero
     */
    @Nonnull
    @Override
    public MutableDecimalVector normalize(@Nonnull MathContext context) throws UnsupportedOperationException {
        final BigDecimal m = magnitude(context);
        try {
            return divide(m, context);
        } catch (final ArithmeticException e) {
            throw new UnsupportedOperationException("Cannot normalize a vector with no direction.");
        }
    }

    /**
     * {@inheritDoc}
     * @return The negated vector
     */
    @Nonnull
    @Override
    public MutableDecimalVector negate() {
        final MutableDecimalVector result = new MutableDecimalVector(length());
        values.forEach(v -> result.append(v.negate()));
        return result;
    }

    /**
     * {@inheritDoc}
     * @param v The vector to get the distance to
     * @param context The mathematical context to use when calculating the magnitude of the difference vector
     * @return The distance between this vector and {@code v}
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is different from this vector
     */
    @Nonnull
    @Override
    public BigDecimal distance(@Nonnull DecimalVector v, @Nonnull MathContext context) throws IllegalArgumentException {
        return subtract(v).magnitude(context);
    }

    /**
     * {@inheritDoc}
     * @param v The vector to get the squared distance to
     * @return The squared distance between this vector and {@code v}
     * @throws IllegalArgumentException When the provided vector {@code v}'s length is different from this vector
     */
    @Nonnull
    @Override
    public BigDecimal distance2(@Nonnull DecimalVector v) throws IllegalArgumentException {
        return subtract(v).magnitude2();
    }

    /**
     * {@inheritDoc}
     * @return The {@code double} value of this vector
     */
    @Nonnull
    @Override
    public MutableVector doubleValue() {
        final MutableVector result = new MutableVector(length());

        for (int i = 0; i < length(); i++) {
            result.setValueAt(i, values.get(i).doubleValue());
        }

        return result;
    }

    //
    // Equality
    //

    /**
     * {@inheritDoc}
     * @param obj The object to compare to
     * @return {@code true} if the object is a vector, and the components and length are equal
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof DecimalVector v)) return false;
        if (length() != v.length()) return false;

        for (int i = 0; i < length(); i++) {
            if (!values.get(i).equals(v.valueAt(i))) return false;
        }

        return true;
    }

    //
    // Serialization
    //

    /**
     * Deserializes a string into a vector.
     * @param input The input string to parse
     * @return The parsed vector
     * @throws NumberFormatException When the format is invalid
     */
    @Nonnull
    public static MutableDecimalVector parseVector(@Nonnull String input) throws NumberFormatException {
        if (!input.startsWith("MutableDecimalVector{")) {
            throw new NumberFormatException("Given string does not represent a MutableDecimalVector.");
        }

        final String cleanInput = input.replaceAll("MutableDecimalVector\\{values=|}|\\[|]", "");
        final String[] valueStrings = cleanInput.split(", ");

        final BigDecimal[] values = new BigDecimal[valueStrings.length];

        for (int i = 0; i < valueStrings.length; i++) {
            values[i] = new BigDecimal(valueStrings[i]);
        }

        return new MutableDecimalVector(values);
    }

    /**
     * {@inheritDoc}
     * @return The string representation of this vector
     */
    @Override
    @Nonnull
    public String toString() {
        return "MutableDecimalVector{" +
                "values=" + values +
                '}';
    }
}
