package civitas.celestis.math.vector;

import civitas.celestis.math.util.Numbers;
import jakarta.annotation.Nonnull;

import java.util.Arrays;
import java.util.function.UnaryOperator;

/**
 * A vector with no predefined length constraints.
 * While {@code VectorX} is still immutable, it can be constructed with any size.
 *
 * @param values The component scalars of this vector.
 */
public record VectorX(@Nonnull double... values) implements Vector {
    //
    // Constructors
    //

    /**
     * Creates a new vector from an array of component scalars.
     *
     * @param values Values to use
     */
    public VectorX(double... values) {
        this.values = new double[values.length];

        for (int i = 0; i < values.length; i++) {
            this.values[i] = Numbers.requireFinite(values[i]);
        }
    }

    //
    // Properties
    //

    /**
     * Returns the {@code i}th component of this vector.
     *
     * @param i Index of component to get
     * @return Component matching given index
     * @throws IndexOutOfBoundsException When the index {@code i >= length}
     */
    public double get(int i) throws IndexOutOfBoundsException {
        return values[i];
    }

    /**
     * {@inheritDoc}
     *
     * @return A copy of the values of this vector
     */
    @Nonnull
    @Override
    public double[] values() {
        return Arrays.copyOf(values, values.length);
    }

    /**
     * {@inheritDoc}
     *
     * @return The {@code length} property of {@link VectorX#values}
     */
    @Override
    public int length() {
        return values.length;
    }

    /**
     * {@inheritDoc}
     *
     * @return The square root of {@link VectorX#magnitude2()}
     */
    @Override
    public double magnitude() {
        return Math.sqrt(magnitude2());
    }

    /**
     * {@inheritDoc}
     *
     * @return The sum of all components' powers
     */
    @Override
    public double magnitude2() {
        double powerSums = 0;

        for (final double value : values) {
            powerSums += value * value;
        }

        return powerSums;
    }

    //
    // Arithmetic
    //

    /**
     * {@inheritDoc}
     *
     * @param s Scalar to add to this vector
     * @return A new vector where the scalar is added to all the components
     */
    @Nonnull
    @Override
    public Vector add(double s) {
        final double[] result = new double[values.length];

        for (int i = 0; i < values.length; i++) {
            result[i] = values[i] + s;
        }

        return Vector.of(result);
    }

    /**
     * {@inheritDoc}
     *
     * @param s Scalar to subtract from this vector
     * @return A new vector where the scalar is subtracted from all the components
     */
    @Nonnull
    @Override
    public Vector subtract(double s) {
        final double[] result = new double[values.length];

        for (int i = 0; i < values.length; i++) {
            result[i] = values[i] - s;
        }

        return Vector.of(result);
    }

    /**
     * {@inheritDoc}
     *
     * @param s Scalar to multiply with this vector
     * @return A new vector where the scalar is multiplied to all the components
     */
    @Nonnull
    @Override
    public Vector multiply(double s) {
        final double[] result = new double[values.length];

        for (int i = 0; i < values.length; i++) {
            result[i] = values[i] * s;
        }

        return Vector.of(result);
    }

    /**
     * {@inheritDoc}
     *
     * @param s Scalar to divide this vector by
     * @return A new vector where all the components are divided by the scalar
     * @throws ArithmeticException When the denominator {@code s == 0}
     */
    @Nonnull
    @Override
    public Vector divide(double s) throws ArithmeticException {
        if (s == 0) {
            throw new ArithmeticException("Cannot divide by zero.");
        }

        final double[] result = new double[values.length];

        for (int i = 0; i < values.length; i++) {
            result[i] = values[i] / s;
        }

        return Vector.of(result);
    }

    //
    // Utility
    //

    /**
     * {@inheritDoc}
     *
     * @param operator The operator to apply to all components of this vector
     * @return A new vector where the operator is applied to all of its components
     */
    @Nonnull
    @Override
    public Vector apply(@Nonnull UnaryOperator<Double> operator) {
        final double[] result = new double[values.length];

        for (int i = 0; i < values.length; i++) {
            result[i] = operator.apply(values[i]);
        }

        return Vector.of(result);
    }

    /**
     * {@inheritDoc}
     *
     * @return A new vector where all the components are negated
     */
    @Nonnull
    @Override
    public Vector negate() {
        return multiply(-1);
    }

    /**
     * {@inheritDoc}
     *
     * @return A new vector where all the components are normalized
     */
    @Nonnull
    @Override
    public Vector normalize() {
        try {
            return divide(magnitude());
        } catch (ArithmeticException e) {
            return Vector.of(new double[values.length]);
        }
    }

    //
    // Equality
    //

    /**
     * Checks for equality between {@code this} and the provided object {@code obj}.
     *
     * @param obj Object to compare this vector to
     * @return {@code true} if the object is an instance of {@link Vector}, and all the components are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Vector v)) return false;
        final double[] otherValues = v.values();

        if (otherValues.length != values.length) return false;
        for (int i = 0; i < values.length; i++) {
            if (otherValues[i] != values[i]) return false;
        }

        return true;
    }

    //
    // Serialization
    //


    /**
     * Parses a string into a {@link VectorX}.
     *
     * @param s String to parse into a vector
     * @return The parsed string
     * @throws NumberFormatException    When the format is invalid
     * @throws IllegalArgumentException When at least one of the components is non-finite
     */
    @Nonnull
    public static VectorX parseVector(@Nonnull String s) throws IllegalArgumentException {
        final String[] components = s.replaceAll("VectorX\\{values=|}|\\[|]", "").split(", ");
        final double[] values = new double[components.length];

        for (int i = 0; i < components.length; i++) {
            values[i] = Double.parseDouble(components[i]);
        }

        return new VectorX(values);
    }


    /**
     * Serializes this {@link VectorX} into a string.
     *
     * @return The string representation of this vector
     */
    @Override
    @Nonnull
    public String toString() {
        return "VectorX{" +
                "values=" + Arrays.toString(values) +
                '}';
    }
}
