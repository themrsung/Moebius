package civitas.celestis.math.real;

import civitas.celestis.math.Numeric;
import civitas.celestis.math.vector.Vector;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.io.Serializable;
import java.util.function.UnaryOperator;

/**
 * <p>
 * A multidimensional number which has a direction and magnitude.
 * This interface defines the contract of a {@link RealNumber} vector.
 * </p>
 */
public interface RealVector extends Numeric, Serializable {
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
    static RealVector of(@Nonnull RealNumber... values) {
        return switch (values.length) {
            case 2 -> new RealVector2(values);
            case 3 -> new RealVector3(values);
            case 4 -> new RealVector4(values);
            default -> new MutableRealVector(values);
        };
    }

    /**
     * Deserializes a string into a vector.
     *
     * @param input Input string to parse
     * @return The parsed vector
     * @throws IllegalArgumentException When at least one of the components is non-finite
     * @throws NumberFormatException    When the format is invalid
     */
    @Nonnull
    static RealVector parseVector(@Nonnull String input) throws IllegalArgumentException {
        try {return RealVector2.parseVector(input);} catch (final Exception ignored) {}
        try {return RealVector3.parseVector(input);} catch (final Exception ignored) {}
        try {return RealVector4.parseVector(input);} catch (final Exception ignored) {}
        try {return MutableRealVector.parseVector(input);} catch (final Exception ignored) {}

        throw new IllegalArgumentException("Given string is not a vector.");
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
    @Nonnull
    RealNumber valueAt(int i) throws IndexOutOfBoundsException;

    /**
     * Returns an array of values which represent the components of this vector.
     * Changes in the return value of this method will not be reflected in the original vector.
     *
     * @return The array representation of this vector
     */
    @Nonnull
    RealNumber[] values();

    //
    // Properties
    //

    /**
     * Returns the magnitude of this vector.
     *
     * @return The magnitude of this vector
     */
    @Nonnull
    RealNumber magnitude();

    /**
     * Returns the squared magnitude of this vector.
     *
     * @return The squared magnitude of this vector
     */
    @Nonnull
    RealNumber magnitude2();

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
     * Adds a scalar to every component of this vector.
     *
     * @param s Scalar to add to this vector
     * @return The resulting vector
     */
    @Nonnull
    RealVector add(@Nonnull RealNumber s);

    /**
     * Subtracts a scalar from every component of this vector.
     *
     * @param s Scalar to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    RealVector subtract(@Nonnull RealNumber s);

    /**
     * Multiplies every component of this vector by given scalar.
     *
     * @param s Scalar to multiply this vector by
     * @return The resulting vector
     */
    @Nonnull
    RealVector multiply(@Nonnull RealNumber s);

    /**
     * Divides every component of this vector by given scalar.
     *
     * @param s Scalar to divide this vector by
     * @return The resulting vector
     * @throws ArithmeticException When the denominator is zero
     */
    @Nonnull
    RealVector divide(@Nonnull RealNumber s) throws ArithmeticException;

    /**
     * Adds another vector to this vector.
     *
     * @param v The vector to add to this vector
     * @return The resulting vector
     * @throws IllegalArgumentException When the given vector has a different length compared to this vector
     */
    @Nonnull
    RealVector add(@Nonnull RealVector v) throws IllegalArgumentException;

    /**
     * Subtracts this vector by another vector.
     *
     * @param v The vector to subtract from this vector
     * @return The resulting vector
     * @throws IllegalArgumentException When the given vector has a different length compared to this vector
     */
    @Nonnull
    RealVector subtract(@Nonnull RealVector v) throws IllegalArgumentException;

    /**
     * Multiplies this vector by another vector.
     *
     * @param v The vector to multiply this vector by
     * @return The resulting vector
     * @throws IllegalArgumentException      When the given vector has a different length compared to this vector
     * @throws UnsupportedOperationException When vector-multiplication has no default definition for this vector
     */
    @Nonnull
    RealVector multiply(@Nonnull RealVector v) throws IllegalArgumentException, UnsupportedOperationException;

    /**
     * Returns the dot product between this vector and another vector.
     *
     * @param v The vector to get the dot product between
     * @return The dot product of the two vectors
     * @throws IllegalArgumentException When the given vector has a different length compared to this vector
     */
    @Nonnull
    RealNumber dot(@Nonnull RealVector v) throws IllegalArgumentException;

    //
    // Clamping
    //

    /**
     * Returns the minimum vector between this vector and the provided vector {@code v}.
     * This is achieved by applying {@link RealNumber#min(RealNumber)} to each component of the two vectors.
     *
     * @param v The vector to get the minimum vector of
     * @return The resulting vector
     * @throws IllegalArgumentException When the given vector has a different length compared to this vector
     */
    @Nonnull
    RealVector min(@Nonnull RealVector v) throws IllegalArgumentException;

    /**
     * Returns the maximum vector between this vector and the provided vector {@code v}.
     * This is achieved by applying {@link RealNumber#max(RealNumber)} to each component of the two vectors.
     *
     * @param v The vector to get the maximum vector of
     * @return The resulting vector
     * @throws IllegalArgumentException When the given vector has a different length compared to this vector
     */
    @Nonnull
    RealVector max(@Nonnull RealVector v) throws IllegalArgumentException;

    /**
     * Returns the clamped vector of this vector to respect the given boundaries of {@code min} and {@code max}.
     *
     * @param min The minimum allowed values
     * @param max The maximum allowed values
     * @return The resulting vector
     * @throws IllegalArgumentException When either of the given vectors have a different length compared to this vector
     */
    @Nonnull
    RealVector clamp(@Nonnull RealVector min, @Nonnull RealVector max) throws IllegalArgumentException;

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
    RealVector apply(@Nonnull UnaryOperator<RealNumber> operator);

    /**
     * Normalizes this vector and returns the normalized vector.
     *
     * @return The normalized vector of this vector
     * @throws UnsupportedOperationException When the magnitude of this vector is exactly zero
     */
    @Nonnull
    RealVector normalize() throws UnsupportedOperationException;

    /**
     * Returns the negated vector of this vector.
     *
     * @return The negation of this vector
     */
    @Nonnull
    RealVector negate();

    /**
     * Returns the distance between this vector and the provided vector.
     *
     * @param v The vector to get the distance to
     * @return The distance between this vector and {@code v}
     * @throws IllegalArgumentException When the provided vector has a different length compared to this vector
     */
    @Nonnull
    RealNumber distance(@Nonnull RealVector v) throws IllegalArgumentException;


    /**
     * Returns the squared distance between this vector and the provided vector.
     *
     * @param v The vector to get the squared distance to
     * @return The squared distance between this vector and {@code v}
     * @throws IllegalArgumentException When the provided vector has a different length compared to this vector
     */
    @Nonnull
    RealNumber distance2(@Nonnull RealVector v) throws IllegalArgumentException;

    /**
     * Returns the {@code double} vector of this vector.
     *
     * @return The {@code double} representation of this vector
     */
    @Nonnull
    Vector doubleValue();

    //
    // Equality
    //

    /**
     * Checks for equality between this vector and given object.
     * This operation ensures that the equality operator will work reliably between different instances of {@link RealVector}.
     *
     * @param obj The object to compare to
     * @return {@code true} if the given object is an instance of {@link RealVector}, and the components and length are equal
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
