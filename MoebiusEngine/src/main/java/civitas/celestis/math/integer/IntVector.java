package civitas.celestis.math.integer;

import civitas.celestis.math.vector.Vector;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.io.Serializable;
import java.util.function.UnaryOperator;

/**
 * <p>
 * A multidimensional integer which has a direction and magnitude.
 * This interface defines the contract of an integer-only vector.
 * </p>
 */
public interface IntVector extends Serializable {
    //
    // Factory
    //

    /**
     * Creates a new vector.
     *
     * @param values Values to create the vector from
     * @return The constructed vector
     */
    @Nonnull
    static IntVector of(@Nonnull int... values) {
        return switch (values.length) {
            case 2 -> new IntVector2(values);
            case 3 -> new IntVector3(values);
            case 4 -> new IntVector4(values);
            default -> new MutableIntVector(values);
        };
    }

    /**
     * Creates a new vector from a {@code double} vector.
     *
     * @param v {@link Vector} object to use
     * @return The constructed vector
     */
    @Nonnull
    static IntVector fromDouble(@Nonnull Vector v) {
        return switch (v.length()) {
            case 2 -> new IntVector2(v);
            case 3 -> new IntVector3(v);
            case 4 -> new IntVector4(v);
            default -> new MutableIntVector(v);
        };
    }

    /**
     * Deserializes a string into a vector.
     *
     * @param input Input string to parse
     * @return The parsed vector
     * @throws NumberFormatException When the string is not a vector
     */
    @Nonnull
    static IntVector parseVector(@Nonnull String input) throws NumberFormatException {
        try {return IntVector2.parseVector(input);} catch (final NumberFormatException ignored) {}
        try {return IntVector3.parseVector(input);} catch (final NumberFormatException ignored) {}
        try {return IntVector4.parseVector(input);} catch (final NumberFormatException ignored) {}
        try {return MutableIntVector.parseVector(input);} catch (final NumberFormatException ignored) {}
        throw new NumberFormatException("Given string is not a vector.");
    }

    //
    // Getters
    //

    /**
     * Returns the component at the specified index.
     *
     * @param i Index of component to get
     * @return The component at the given index
     * @throws IndexOutOfBoundsException If the index is out of bounds ({@code i >= length() || i < 0})
     */
    int valueAt(int i) throws IndexOutOfBoundsException;

    /**
     * Returns an array of values which represent the components of this vector.
     * Changes in the return value of this method will not be reflected in the original vector.
     *
     * @return The array representation of this vector
     */
    int[] values();

    //
    // Properties
    //

    /**
     * Returns the magnitude of this vector.
     *
     * @return The magnitude of this vector
     */
    double magnitude();

    /**
     * Returns the squared magnitude of this vector.
     *
     * @return The squared magnitude of this vector
     */
    double magnitude2();

    /**
     * Returns the length of this vector.
     *
     * @return The number of components this vector contains
     */
    int length();

    //
    // Arithmetic
    //

    /**
     * Adds a scalar to this vector.
     *
     * @param s The scalar to add to this vector
     * @return The resulting vector
     */
    @Nonnull
    IntVector add(int s);

    /**
     * Subtracts a scalar from this vector.
     *
     * @param s The scalar to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    IntVector subtract(int s);

    /**
     * Multiplies this vector by a scalar.
     *
     * @param s The scalar to multiply this vector by
     * @return The resulting vector
     */
    @Nonnull
    IntVector multiply(int s);

    /**
     * Divides this vector by a scalar.
     *
     * @param s The scalar to divide this vector by
     * @return The resulting vector
     * @throws ArithmeticException When the denominator {@code s} is zero
     */
    @Nonnull
    IntVector divide(int s) throws ArithmeticException;

    /**
     * Adds another vector to this vector.
     *
     * @param v The vector to add to this vector
     * @return The resulting vector
     * @throws IllegalArgumentException When the given vector has a different length compared to this vector
     */
    @Nonnull
    IntVector add(@Nonnull IntVector v) throws IllegalArgumentException;

    /**
     * Subtracts another vector from this vector.
     *
     * @param v The vector to subtract from this vector
     * @return The resulting vector
     * @throws IllegalArgumentException When the given vector has a different length compared to this vector
     */
    @Nonnull
    IntVector subtract(@Nonnull IntVector v) throws IllegalArgumentException;

    /**
     * Multiplies this vector by another vector.
     *
     * @param v The vector to multiply this vector by
     * @return The resulting vector
     * @throws IllegalArgumentException      When the given vector has a different length compared to this vector
     * @throws UnsupportedOperationException When there is no default multiplication operation defined for this vector
     */
    @Nonnull
    IntVector multiply(@Nonnull IntVector v) throws IllegalArgumentException, UnsupportedOperationException;

    /**
     * Returns the dot product between this vector and the provided vector {@code v}.
     *
     * @param v The vector to get the dot product between
     * @return The dot product of the two vectors
     * @throws IllegalArgumentException When the given vector has a different length compared to this vector
     */
    int dot(@Nonnull IntVector v) throws IllegalArgumentException;

    //
    // Clamping
    //

    /**
     * Returns the minimum vector between this vector and the provided vector {@code v}.
     *
     * @param v The vector to get the minimum vector of
     * @return The resulting vector
     * @throws IllegalArgumentException When the given vector has a different length compared to this vector
     */
    @Nonnull
    IntVector min(@Nonnull IntVector v) throws IllegalArgumentException;

    /**
     * Returns the maximum vector between this vector and the provided vector {@code v}.
     *
     * @param v The vector to get the maximum vector of
     * @return The resulting vector
     * @throws IllegalArgumentException When the given vector has a different length compared to this vector
     */
    @Nonnull
    IntVector max(@Nonnull IntVector v) throws IllegalArgumentException;

    /**
     * Returns the clamped vector of this vector to respect the given boundaries of {@code min} and {@code max}.
     *
     * @param min The minimum allowed values
     * @param max The maximum allowed values
     * @return The resulting vector
     * @throws IllegalArgumentException When either of the given vectors have a different length compared to this vector
     */
    @Nonnull
    IntVector clamp(@Nonnull IntVector min, @Nonnull IntVector max) throws IllegalArgumentException;

    //
    // Utility
    //

    /**
     * Applies given operator to all components of this vector, then returns the resulting vector.
     *
     * @param operator The operator to apply to each component of this vector
     * @return The resulting vector
     */
    @Nonnull
    IntVector apply(@Nonnull UnaryOperator<Integer> operator);

    /**
     * Returns the negated vector of this vector.
     *
     * @return The negation of this vector
     */
    @Nonnull
    IntVector negate();

    /**
     * Returns the distance between this vector and the provided vector.
     *
     * @param v The vector to get the distance to
     * @return The distance between this vector and {@code v}
     * @throws IllegalArgumentException When the provided vector has a different length from this vector
     */
    double distance(@Nonnull IntVector v) throws IllegalArgumentException;

    /**
     * Returns the squared distance between this vector and the provided vector.
     *
     * @param v The vector to get the squared distance to
     * @return The squared distance between this vector and {@code v}
     * @throws IllegalArgumentException When the provided vector has a different length from this vector
     */
    double distance2(@Nonnull IntVector v) throws IllegalArgumentException;

    /**
     * Converts this vector into a {@code double} vector.
     *
     * @return A {@link Vector} derived from {@code this}
     */
    @Nonnull
    Vector doubleValue();

    //
    // Equality
    //

    /**
     * Checks for equality between this vector and given object.
     * This operation ensures that the equality operator will work reliably between different instances of {@link IntVector}.
     *
     * @param obj The object to compare to
     * @return {@code true} if the given object is an instance of {@link IntVector}, and the components and lengths are equal
     */
    boolean equals(@Nullable Object obj);

    //
    // Serialization
    //

    /**
     * Serializes this vector into a string.
     *
     * @return The string representation of this vector
     */
    @Nonnull
    String toString();
}
