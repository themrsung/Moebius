package civitas.celestis.math.vector;

import civitas.celestis.math.Numeric;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.io.Serializable;
import java.util.function.UnaryOperator;

/**
 * <p>
 * A multidimensional number which has a direction and magnitude.
 * This interface defines the contract of a vector.
 * </p>
 * <p>
 * Vectors can not only be used for spacial data, but can be used
 * for any application which require the collective use of multiple scalars as one object.
 * First-party examples include representing colors using a four-dimensional vector. (RGBA)
 * </p>
 * <p>
 * In order to ensure flawless arithmetic operation between vectors or other numbers,
 * the non-finite constraint is enforced in all vectors.
 * Non-finite values (e.g. {@link Double#NaN}, {@link Double#POSITIVE_INFINITY}, {@link Double#NEGATIVE_INFINITY})
 * will trigger an exception in the constructor.
 * </p>
 */
public interface Vector extends Numeric, Serializable {
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
    static Vector of(@Nonnull double... values) {
        return switch (values.length) {
            case 2 -> new Vector2(values);
            case 3 -> new Vector3(values);
            case 4 -> new Vector4(values);
            case 5 -> new Vector5(values);
            default -> new MutableVector(values);
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
    static Vector parseVector(@Nonnull String input) throws IllegalArgumentException {
        try {return Vector2.parseVector(input);} catch (final NumberFormatException ignored) {}
        try {return Vector3.parseVector(input);} catch (final NumberFormatException ignored) {}
        try {return Vector4.parseVector(input);} catch (final NumberFormatException ignored) {}
        try {return Vector5.parseVector(input);} catch (final NumberFormatException ignored) {}
        try {return MutableVector.parseVector(input);} catch (final NumberFormatException ignored) {}

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
    double valueAt(int i) throws IndexOutOfBoundsException;

    /**
     * Returns an array of values which represent the components of this vector.
     * Changes in the return value of this method will not be reflected in the original vector.
     *
     * @return The array representation of this vector
     */
    double[] values();

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
     * Adds a scalar to every component of this vector.
     *
     * @param s Scalar to add to this vector
     * @return The resulting vector
     */
    @Nonnull
    Vector add(double s);

    /**
     * Subtracts a scalar from every component of this vector.
     *
     * @param s Scalar to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    Vector subtract(double s);

    /**
     * Multiplies every component of this vector by given scalar.
     *
     * @param s Scalar to multiply this vector by
     * @return The resulting vector
     */
    @Nonnull
    Vector multiply(double s);

    /**
     * Divides every component of this vector by given scalar.
     *
     * @param s Scalar to divide this vector by
     * @return The resulting vector
     * @throws ArithmeticException When the denominator is zero
     */
    @Nonnull
    Vector divide(double s) throws ArithmeticException;

    /**
     * Adds another vector to this vector.
     *
     * @param v The vector to add to this vector
     * @return The resulting vector
     * @throws IllegalArgumentException When the given vector has a different length compared to this vector
     */
    @Nonnull
    Vector add(@Nonnull Vector v) throws IllegalArgumentException;

    /**
     * Subtracts this vector by another vector.
     *
     * @param v The vector to subtract from this vector
     * @return The resulting vector
     * @throws IllegalArgumentException When the given vector has a different length compared to this vector
     */
    @Nonnull
    Vector subtract(@Nonnull Vector v) throws IllegalArgumentException;

    /**
     * Multiplies this vector by another vector.
     *
     * @param v The vector to multiply this vector by
     * @return The resulting vector
     * @throws IllegalArgumentException      When the given vector has a different length compared to this vector
     * @throws UnsupportedOperationException When vector-multiplication has no default definition for this vector
     */
    @Nonnull
    Vector multiply(@Nonnull Vector v) throws IllegalArgumentException, UnsupportedOperationException;

    /**
     * Returns the dot product between this vector and another vector.
     *
     * @param v The vector to get the dot product between
     * @return The dot product of the two vectors
     * @throws IllegalArgumentException When the given vector has a different length compared to this vector
     */
    double dot(@Nonnull Vector v) throws IllegalArgumentException;

    //
    // Clamping
    //

    /**
     * Returns the minimum vector between this vector and the provided vector {@code v}.
     * This is achieved by applying {@link Math#min(double, double)} to each component of the two vectors.
     *
     * @param v The vector to get the minimum vector of
     * @return The resulting vector
     * @throws IllegalArgumentException When the given vector has a different length compared to this vector
     */
    @Nonnull
    Vector min(@Nonnull Vector v) throws IllegalArgumentException;

    /**
     * Returns the maximum vector between this vector and the provided vector {@code v}.
     * This is achieved by applying {@link Math#max(double, double)} to each component of the two vectors.
     *
     * @param v The vector to get the maximum vector of
     * @return The resulting vector
     * @throws IllegalArgumentException When the given vector has a different length compared to this vector
     */
    @Nonnull
    Vector max(@Nonnull Vector v) throws IllegalArgumentException;

    /**
     * Returns the clamped vector of this vector to respect the given boundaries of {@code min} and {@code max}.
     *
     * @param min The minimum allowed values
     * @param max The maximum allowed values
     * @return The resulting vector
     * @throws IllegalArgumentException When either of the given vectors have a different length compared to this vector
     */
    @Nonnull
    Vector clamp(@Nonnull Vector min, @Nonnull Vector max) throws IllegalArgumentException;

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
    Vector apply(@Nonnull UnaryOperator<Double> operator);

    /**
     * Normalizes this vector and returns the normalized vector.
     *
     * @return The normalized vector of this vector
     * @throws UnsupportedOperationException When the magnitude of this vector is exactly zero
     */
    @Nonnull
    Vector normalize() throws UnsupportedOperationException;

    /**
     * Returns the negated vector of this vector.
     *
     * @return The negation of this vector
     */
    @Nonnull
    Vector negate();

    /**
     * Returns the distance between this vector and the provided vector.
     *
     * @param v The vector to get the distance to
     * @return The distance between this vector and {@code v}
     * @throws IllegalArgumentException When the provided vector has a different length compared to this vector
     */
    double distance(@Nonnull Vector v) throws IllegalArgumentException;

    /**
     * Returns the squared distance between this vector and the provided vector.
     *
     * @param v The vector to get the squared distance to
     * @return The squared distance between this vector and {@code v}
     * @throws IllegalArgumentException When the provided vector has a different length compared to this vector
     */
    double distance2(@Nonnull Vector v) throws IllegalArgumentException;

    //
    // Equality
    //

    /**
     * Checks for equality between this vector and given object.
     * This operation ensures that the equality operator will work reliably between different instances of {@link Vector}.
     *
     * @param obj The object to compare to
     * @return {@code true} if the given object is an instance of {@link Vector}, and the components and length are equal
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
