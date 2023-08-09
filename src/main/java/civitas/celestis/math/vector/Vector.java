package civitas.celestis.math.vector;

import jakarta.annotation.Nonnull;

import java.io.Serializable;
import java.util.function.UnaryOperator;

/**
 * A one-dimensional array of numbers.
 */
public interface Vector extends Serializable {
    //
    // Factory
    //

    /**
     * Creates a new vector from an array of component scalars.
     * Note that this constructor is very inefficient, and using the discrete types' constructors
     * is faster when dealing with arrays of known sizes.
     *
     * @param values Values to use
     * @return Vector constructed from given values
     * @throws IllegalArgumentException When at least one of the provided component scalars is non-finite
     */
    @Nonnull
    static Vector of(@Nonnull double... values) {
        try {
            return new Vector2(values);
        } catch (IllegalArgumentException ignored) {}

        try {
            return new Vector3(values);
        } catch (IllegalArgumentException ignored) {}

        try {
            return new Vector4(values);
        } catch (IllegalArgumentException ignored) {}

        return new VectorX(values);
    }

    //
    // Properties
    //

    /**
     * Returns an array containing the component values of this vector.
     *
     * @return Array containing the components of this vector
     */
    @Nonnull
    double[] values();

    /**
     * Gets the length of this vector.
     *
     * @return The number of components in this vector
     */
    int length();

    /**
     * Gets the magnitude of this vector.
     *
     * @return The magnitude of this vector
     */
    double magnitude();

    /**
     * Gets the squared magnitude of this vector.
     *
     * @return The squared magnitude of this vector
     */
    double magnitude2();

    //
    // Arithmetic
    //

    /**
     * Adds a scalar to this vector.
     *
     * @param s Scalar to add to this vector
     * @return The resulting vector
     */
    @Nonnull
    Vector add(double s);

    /**
     * Subtracts a scalar from this vector.
     *
     * @param s Scalar to subtract from this vector
     * @return The resulting vector
     */
    @Nonnull
    Vector subtract(double s);

    /**
     * Multiplies this vector by a scalar.
     *
     * @param s Scalar to multiply with this vector
     * @return The resulting vector
     */
    @Nonnull
    Vector multiply(double s);

    /**
     * Divides this vector by a scalar.
     *
     * @param s Scalar to divide this vector by
     * @return The resulting vector
     * @throws ArithmeticException When the denominator is zero
     */
    @Nonnull
    Vector divide(double s) throws ArithmeticException;

    //
    // Utility
    //

    /**
     * Applies given operator to this vector, then returns the resulting vector.
     *
     * @param operator The operator to apply to all components of this vector
     * @return The resulting vector
     */
    @Nonnull
    Vector apply(@Nonnull UnaryOperator<Double> operator);

    /**
     * Returns the negated vector of this vector.
     *
     * @return The negated vector of this vector
     */
    @Nonnull
    Vector negate();

    /**
     * Returns a normalized vector of this vector.
     * If the magnitude of this vector is {@code 0}, the resulting vector will also have a magnitude of {@code 0}.
     *
     * @return The normalized vector of this vector
     */
    @Nonnull
    Vector normalize();
}
