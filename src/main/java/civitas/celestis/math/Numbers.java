package civitas.celestis.math;

import civitas.celestis.exception.math.NotIntSafeException;
import civitas.celestis.math.integer.*;
import civitas.celestis.math.matrix.Matrix;
import civitas.celestis.math.quaternion.Quaternion;
import civitas.celestis.math.vector.*;
import jakarta.annotation.Nonnull;

/**
 * Contains utility methods for numbers.
 */
public final class Numbers {
    //
    // Constraints
    //

    /**
     * Explicitly denotes that a given {@code double} must be finite.
     * This is determined by the static method {@link Double#isFinite(double)}.
     * If the value is finite, this will simply pass through the value to the return value.
     * If not, this will throw an {@link IllegalArgumentException}.
     *
     * @param value Value to validate
     * @return The value given as the parameter
     * @throws IllegalArgumentException When the given field is non-finite
     */
    public static double requireFinite(double value) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("This field requires a finite value as an input. The provided value was " + value + ".");
        }

        return value;
    }

    /**
     * Explicitly denotes that a given {@code double} must be within the range {@code min} to {@code max}.
     * If the value meets the requirements, this will simply pass through the value to the return value.
     * If not, this will throw an {@link IllegalArgumentException}.
     *
     * @param value Value to validate
     * @param min   The minimum acceptable value
     * @param max   The maximum acceptable value
     * @return The value given as the parameter
     * @throws IllegalArgumentException When {@code value > max || value < min}
     */
    public static double requireRange(double value, double min, double max) {
        if (!isInRange(value, min, max)) {
            throw new IllegalArgumentException("This field requires the value to be within range " + min + "-" + max + ".");
        }

        return value;
    }

    //
    // Range Tests
    //

    /**
     * Checks if a scalar is within the specified range.
     *
     * @param value The value to check
     * @param min   The minimum acceptable value
     * @param max   The maximum acceptable value
     * @return {@code true} if the value obeys the bounds
     */
    public static boolean isInRange(double value, double min, double max) {
        return value >= min && value <= max;
    }

    /**
     * Checks if a vector is within the specified range.
     *
     * @param value The value to check
     * @param min   The minimum acceptable value
     * @param max   The maximum acceptable value
     * @return {@code true} if the value obeys the bounds
     */
    public static boolean isInRange(@Nonnull Vector value, @Nonnull Vector min, @Nonnull Vector max) {
        if (value.length() != min.length() || min.length() != max.length()) {
            throw new ArithmeticException("Cannot test range for vectors with different lengths.");
        }

        for (int i = 0; i < value.length(); i++) {
            if (!isInRange(value.valueAt(i), min.valueAt(i), max.valueAt(i))) return false;
        }

        return true;
    }

    /**
     * Checks if a vector is within the specified range.
     *
     * @param value The value to check
     * @param min   The minimum acceptable value
     * @param max   The maximum acceptable value
     * @return {@code true} if the value obeys the bounds
     */
    public static boolean isInRange(@Nonnull Vector2 value, @Nonnull Vector2 min, @Nonnull Vector2 max) {
        return isInRange(value.x(), min.x(), max.x()) && isInRange(value.y(), min.y(), max.y());
    }

    /**
     * Checks if a vector is within the specified range.
     *
     * @param value The value to check
     * @param min   The minimum acceptable value
     * @param max   The maximum acceptable value
     * @return {@code true} if the value obeys the bounds
     */
    public static boolean isInRange(@Nonnull Vector3 value, @Nonnull Vector3 min, @Nonnull Vector3 max) {
        return isInRange(value.x(), min.x(), max.x()) &&
                isInRange(value.y(), min.y(), max.y()) &&
                isInRange(value.z(), min.z(), max.z());
    }

    /**
     * Checks if a vector is within the specified range.
     *
     * @param value The value to check
     * @param min   The minimum acceptable value
     * @param max   The maximum acceptable value
     * @return {@code true} if the value obeys the bounds
     */
    public static boolean isInRange(@Nonnull Vector4 value, @Nonnull Vector4 min, @Nonnull Vector4 max) {
        return isInRange(value.w(), min.w(), max.w()) &&
                isInRange(value.x(), min.x(), max.x()) &&
                isInRange(value.y(), min.y(), max.y()) &&
                isInRange(value.z(), min.z(), max.z());
    }

    //
    // Randomization
    //

    /**
     * Returns a random {@code double} between the range of {@code min} and {@code max}
     *
     * @param min The minimum acceptable value
     * @param max The maximum acceptable value
     * @return A random value between {@code min} and {@code max}
     */
    public static double random(double min, double max) {
        return Math.random() * max - min;
    }

    //
    // Type Casting
    //

    /**
     * Checks if a {@code double} can be safely cast to an {@code int} without any overflows.
     *
     * @param value Value to check
     * @return {@code true} if {@code (int) value} will not result in an overflow
     */
    public static boolean isIntSafe(double value) {
        // Adds margin of 1 for additional safety
        return value > -Integer.MAX_VALUE && value < Integer.MAX_VALUE;
    }

    /**
     * Checks if a {@link Vector} can be safely cast to an {@link IntVector} without any overflows.
     *
     * @param v Value to check
     * @return {@code true} if the vector is int-safe
     */
    public static boolean isIntSafe(@Nonnull Vector v) {
        for (int i = 0; i < v.length(); i++) {
            if (!isIntSafe(v.valueAt(i))) return false;
        }

        return true;
    }

    /**
     * Safely casts a {@code double} to an {@code int}.
     *
     * @param value The value to cast
     * @return The integer value of the {@code value}
     * @throws NotIntSafeException When the value is not int-safe (determined by {@link Numbers#isIntSafe(double)})
     */
    public static double safeCastToInt(double value) throws NotIntSafeException {
        if (!isIntSafe(value)) {
            throw new NotIntSafeException("The value " + value + " cannot be safely cast to an int.");
        }

        return (int) value;
    }

    /**
     * Safely casts a {@code double} vector to an {@code int} vector.
     *
     * @param value The value to cast
     * @return The integer vector of the provided vector
     * @throws NotIntSafeException When the value is not int-safe (determined by {@link Numbers#isIntSafe(Vector)})
     */
    @Nonnull
    public static IntVector safeCastToInt(@Nonnull Vector value) throws NotIntSafeException {
        if (!isIntSafe(value)) {
            throw new NotIntSafeException("The provided vector cannot be safely cast to an int vector.");
        }

        return IntVector.fromDouble(value);
    }

    /**
     * Safely casts a {@code double} vector to an {@code int} vector.
     *
     * @param value The value to cast
     * @return The integer vector of the provided vector
     * @throws NotIntSafeException When the value is not int-safe (determined by {@link Numbers#isIntSafe(Vector)})
     */
    @Nonnull
    public static IntVector2 safeCastToInt(@Nonnull Vector2 value) throws NotIntSafeException {
        if (!isIntSafe(value)) {
            throw new NotIntSafeException("The provided vector cannot be safely cast to an int vector.");
        }

        return new IntVector2(value);
    }

    /**
     * Safely casts a {@code double} vector to an {@code int} vector.
     *
     * @param value The value to cast
     * @return The integer vector of the provided vector
     * @throws NotIntSafeException When the value is not int-safe (determined by {@link Numbers#isIntSafe(Vector)})
     */
    @Nonnull
    public static IntVector3 safeCastToInt(@Nonnull Vector3 value) throws NotIntSafeException {
        if (!isIntSafe(value)) {
            throw new NotIntSafeException("The provided vector cannot be safely cast to an int vector.");
        }

        return new IntVector3(value);
    }

    /**
     * Safely casts a {@code double} vector to an {@code int} vector.
     *
     * @param value The value to cast
     * @return The integer vector of the provided vector
     * @throws NotIntSafeException When the value is not int-safe (determined by {@link Numbers#isIntSafe(Vector)})
     */
    @Nonnull
    public static IntVector4 safeCastToInt(@Nonnull Vector4 value) throws NotIntSafeException {
        if (!isIntSafe(value)) {
            throw new NotIntSafeException("The provided vector cannot be safely cast to an int vector.");
        }

        return new IntVector4(value);
    }

    //
    // Deep Copy
    //
    // Note that copying immutable vectors is meaningless.
    //

    /**
     * Deep copies a vector.
     *
     * @param v The vector to copy
     * @return The copied vector
     */
    @Nonnull
    public static Vector copy(@Nonnull Vector v) {
        return Vector.of(v.values());
    }

    /**
     * Deep copies a vector.
     *
     * @param v The vector to copy
     * @return The copied vector
     */
    @Nonnull
    public static MutableVector copy(@Nonnull MutableVector v) {
        return new MutableVector(v.values());
    }

    /**
     * Deep copies a vector.
     *
     * @param v The vector to copy
     * @return The copied vector
     */
    @Nonnull
    public static IntVector copy(@Nonnull IntVector v) {
        return IntVector.of(v.values());
    }

    /**
     * Deep copies a vector.
     *
     * @param v The vector to copy
     * @return The copied vector
     */
    @Nonnull
    public static MutableIntVector copy(@Nonnull MutableIntVector v) {
        return new MutableIntVector(v.values());
    }

    /**
     * Deep copies a matrix.
     *
     * @param m The matrix to copy
     * @return The copied matrix
     */
    @Nonnull
    public static Matrix copy(@Nonnull Matrix m) {
        final Matrix result = new Matrix(m.rows(), m.columns());

        for (int r = 0; r < m.rows(); r++) {
            for (int c = 0; c < m.rows(); c++) {
                result.set(r, c, m.get(r, c));
            }
        }

        return result;
    }

    //
    // Sum
    //

    /**
     * Returns the total of the provided values.
     *
     * @param values The values to sum
     * @return The sum of the provided values
     */
    public static double sum(@Nonnull double... values) {
        double sum = 0;

        for (final double value : values) {
            sum += value;
        }

        return sum;
    }

    /**
     * Returns the sum of the provided vectors.
     * This is the equivalent of chaining {@link Vector2#add(Vector2)}, but is faster due to the lack of
     * re-instantiation every iteration.
     *
     * @param values The vectors to sum
     * @return The sum of the provided vectors
     */
    @Nonnull
    public static Vector2 sum(@Nonnull Vector2... values) {
        double x = 0;
        double y = 0;

        for (final Vector2 value : values) {
            x += value.x();
            y += value.y();
        }

        return new Vector2(x, y);
    }

    /**
     * Returns the sum of the provided vectors.
     * This is the equivalent of chaining {@link Vector3#add(Vector3)}, but is faster due to the lack of
     * re-instantiation every iteration.
     *
     * @param values The vectors to sum
     * @return The sum of the provided vectors
     */
    @Nonnull
    public static Vector3 sum(@Nonnull Vector3... values) {
        double x = 0;
        double y = 0;
        double z = 0;

        for (final Vector3 value : values) {
            x += value.x();
            y += value.y();
            z += value.z();
        }

        return new Vector3(x, y, z);
    }

    /**
     * Returns the sum of the provided vectors.
     * This is the equivalent of chaining {@link Vector4#add(Vector4)}, but is faster due to the lack of
     * re-instantiation every iteration.
     *
     * @param values The vectors to sum
     * @return The sum of the provided vectors
     */
    @Nonnull
    public static Vector4 sum(@Nonnull Vector4... values) {
        double w = 0;
        double x = 0;
        double y = 0;
        double z = 0;

        for (final Vector4 value : values) {
            w += value.w();
            x += value.x();
            y += value.y();
            z += value.z();
        }

        return new Quaternion(w, x, y, z);
    }

    /**
     * Returns the sum of the provided vectors.
     * This is the equivalent of chaining {@link IntVector2#add(IntVector2)}, but is faster due to the lack of
     * re-instantiation every iteration.
     *
     * @param values The vectors to sum
     * @return The sum of the provided vectors
     */
    @Nonnull
    public static IntVector2 sum(@Nonnull IntVector2... values) {
        int x = 0;
        int y = 0;

        for (final IntVector2 value : values) {
            x += value.x();
            y += value.y();
        }

        return new IntVector2(x, y);
    }

    /**
     * Returns the sum of the provided vectors.
     * This is the equivalent of chaining {@link IntVector3#add(IntVector3)}, but is faster due to the lack of
     * re-instantiation every iteration.
     *
     * @param values The vectors to sum
     * @return The sum of the provided vectors
     */
    @Nonnull
    public static IntVector3 sum(@Nonnull IntVector3... values) {
        int x = 0;
        int y = 0;
        int z = 0;

        for (final IntVector3 value : values) {
            x += value.x();
            y += value.y();
            z += value.z();
        }

        return new IntVector3(x, y, z);
    }

    /**
     * Returns the sum of the provided vectors.
     * This is the equivalent of chaining {@link IntVector4#add(IntVector4)}, but is faster due to the lack of
     * re-instantiation every iteration.
     *
     * @param values The vectors to sum
     * @return The sum of the provided vectors
     */
    @Nonnull
    public static IntVector4 sum(@Nonnull IntVector4... values) {
        int w = 0;
        int x = 0;
        int y = 0;
        int z = 0;

        for (final IntVector4 value : values) {
            w += value.w();
            x += value.x();
            y += value.y();
            z += value.z();
        }

        return new IntVector4(w, x, y, z);
    }

    //
    // Average
    //

    /**
     * Returns the average of the provided values.
     *
     * @param values The values to average
     * @return The average of the values
     */
    public static double avg(@Nonnull double... values) {
        if (values.length == 0) {
            return 0;
        }

        return sum(values) / values.length;
    }

    /**
     * Returns the average of the provided vectors.
     * This is equivalent to the geometric centroid of the vectors.
     *
     * @param values The vectors to average
     * @return The average (geometric centroid) of ths provided vectors
     */
    @Nonnull
    public static Vector2 avg(@Nonnull Vector2... values) {
        return sum(values).divide(values.length);
    }

    /**
     * Returns the average of the provided vectors.
     * This is equivalent to the geometric centroid of the vectors.
     *
     * @param values The vectors to average
     * @return The average (geometric centroid) of ths provided vectors
     */
    @Nonnull
    public static Vector3 avg(@Nonnull Vector3... values) {
        return sum(values).divide(values.length);
    }

    /**
     * Returns the average of the provided vectors.
     * This is equivalent to the geometric centroid of the vectors.
     *
     * @param values The vectors to average
     * @return The average (geometric centroid) of ths provided vectors
     */
    @Nonnull
    public static Vector4 avg(@Nonnull Vector4... values) {
        return sum(values).divide(values.length);
    }

    /**
     * Returns the average of the provided vectors.
     * This is equivalent to the geometric centroid of the vectors.
     *
     * @param values The vectors to average
     * @return The average (geometric centroid) of ths provided vectors
     */
    @Nonnull
    public static IntVector2 avg(@Nonnull IntVector2... values) {
        return sum(values).divide(values.length);
    }

    /**
     * Returns the average of the provided vectors.
     * This is equivalent to the geometric centroid of the vectors.
     *
     * @param values The vectors to average
     * @return The average (geometric centroid) of ths provided vectors
     */
    @Nonnull
    public static IntVector3 avg(@Nonnull IntVector3... values) {
        return sum(values).divide(values.length);
    }

    /**
     * Returns the average of the provided vectors.
     * This is equivalent to the geometric centroid of the vectors.
     *
     * @param values The vectors to average
     * @return The average (geometric centroid) of ths provided vectors
     */
    @Nonnull
    public static IntVector4 avg(@Nonnull IntVector4... values) {
        return sum(values).divide(values.length);
    }

    //
    // Min
    //

    /**
     * Returns the minimum of the provided values.
     *
     * @param values The values to get the minimum of
     * @return The minimum of the provided values
     */
    public static double min(@Nonnull double... values) {
        double min = Double.MAX_VALUE;

        for (final double value : values) {
            min = Math.min(min, value);
        }

        return min;
    }

    /**
     * Returns the minimum vector of the provided vectors.
     *
     * @param values The vectors to get the minimum of
     * @return The minimum of the provided vectors
     */
    @Nonnull
    public static Vector2 min(@Nonnull Vector2... values) {
        double x = Double.MAX_VALUE;
        double y = Double.MAX_VALUE;

        for (final Vector2 value : values) {
            x = Math.min(x, value.x());
            y = Math.min(y, value.y());
        }

        return new Vector2(x, y);
    }

    /**
     * Returns the minimum vector of the provided vectors.
     *
     * @param values The vectors to get the minimum of
     * @return The minimum of the provided vectors
     */
    @Nonnull
    public static Vector3 min(@Nonnull Vector3... values) {
        double x = Double.MAX_VALUE;
        double y = Double.MAX_VALUE;
        double z = Double.MAX_VALUE;

        for (final Vector3 value : values) {
            x = Math.min(x, value.x());
            y = Math.min(y, value.y());
            z = Math.min(z, value.z());
        }

        return new Vector3(x, y, z);
    }

    /**
     * Returns the minimum vector of the provided vectors.
     *
     * @param values The vectors to get the minimum of
     * @return The minimum of the provided vectors
     */
    @Nonnull
    public static Vector4 min(@Nonnull Vector4... values) {
        double w = Double.MAX_VALUE;
        double x = Double.MAX_VALUE;
        double y = Double.MAX_VALUE;
        double z = Double.MAX_VALUE;

        for (final Vector4 value : values) {
            w = Math.min(w, value.w());
            x = Math.min(x, value.x());
            y = Math.min(y, value.y());
            z = Math.min(z, value.z());
        }

        return new Vector4(w, x, y, z);
    }

    /**
     * Returns the minimum vector of the provided vectors.
     *
     * @param values The vectors to get the minimum of
     * @return The minimum of the provided vectors
     */
    @Nonnull
    public static IntVector2 min(@Nonnull IntVector2... values) {
        int x = Integer.MAX_VALUE;
        int y = Integer.MAX_VALUE;

        for (final IntVector2 value : values) {
            x = Math.min(x, value.x());
            y = Math.min(y, value.y());
        }

        return new IntVector2(x, y);
    }

    /**
     * Returns the minimum vector of the provided vectors.
     *
     * @param values The vectors to get the minimum of
     * @return The minimum of the provided vectors
     */
    @Nonnull
    public static IntVector3 min(@Nonnull IntVector3... values) {
        int x = Integer.MAX_VALUE;
        int y = Integer.MAX_VALUE;
        int z = Integer.MAX_VALUE;

        for (final IntVector3 value : values) {
            x = Math.min(x, value.x());
            y = Math.min(y, value.y());
            z = Math.min(z, value.z());
        }

        return new IntVector3(x, y, z);
    }

    /**
     * Returns the minimum vector of the provided vectors.
     *
     * @param values The vectors to get the minimum of
     * @return The minimum of the provided vectors
     */
    @Nonnull
    public static IntVector4 min(@Nonnull IntVector4... values) {
        int w = Integer.MAX_VALUE;
        int x = Integer.MAX_VALUE;
        int y = Integer.MAX_VALUE;
        int z = Integer.MAX_VALUE;

        for (final IntVector4 value : values) {
            w = Math.min(w, value.w());
            x = Math.min(x, value.x());
            y = Math.min(y, value.y());
            z = Math.min(z, value.z());
        }

        return new IntVector4(w, x, y, z);
    }

    //
    // Max
    //

    /**
     * Returns the maximum of the provided values.
     *
     * @param values The values to get the maximum of
     * @return The maximum of the provided values
     */
    public static double max(@Nonnull double... values) {
        double max = -Double.MAX_VALUE;

        for (final double value : values) {
            max = Math.max(max, value);
        }

        return max;
    }

    /**
     * Returns the maximum vector of the provided vectors.
     *
     * @param values The vectors to get the maximum of
     * @return The maximum of the provided vectors
     */
    @Nonnull
    public static Vector2 max(@Nonnull Vector2... values) {
        double x = -Double.MAX_VALUE;
        double y = -Double.MAX_VALUE;

        for (final Vector2 value : values) {
            x = Math.max(x, value.x());
            y = Math.max(y, value.y());
        }

        return new Vector2(x, y);
    }

    /**
     * Returns the maximum vector of the provided vectors.
     *
     * @param values The vectors to get the maximum of
     * @return The maximum of the provided vectors
     */
    @Nonnull
    public static Vector3 max(@Nonnull Vector3... values) {
        double x = -Double.MAX_VALUE;
        double y = -Double.MAX_VALUE;
        double z = -Double.MAX_VALUE;

        for (final Vector3 value : values) {
            x = Math.max(x, value.x());
            y = Math.max(y, value.y());
            z = Math.max(z, value.z());
        }

        return new Vector3(x, y, z);
    }

    /**
     * Returns the maximum vector of the provided vectors.
     *
     * @param values The vectors to get the maximum of
     * @return The maximum of the provided vectors
     */
    @Nonnull
    public static Vector4 max(@Nonnull Vector4... values) {
        double w = -Double.MAX_VALUE;
        double x = -Double.MAX_VALUE;
        double y = -Double.MAX_VALUE;
        double z = -Double.MAX_VALUE;

        for (final Vector4 value : values) {
            w = Math.max(w, value.w());
            x = Math.max(x, value.x());
            y = Math.max(y, value.y());
            z = Math.max(z, value.z());
        }

        return new Vector4(w, x, y, z);
    }

    /**
     * Returns the maximum vector of the provided vectors.
     *
     * @param values The vectors to get the minimum of
     * @return The maximum of the provided vectors
     */
    @Nonnull
    public static IntVector2 max(@Nonnull IntVector2... values) {
        int x = -Integer.MAX_VALUE;
        int y = -Integer.MAX_VALUE;

        for (final IntVector2 value : values) {
            x = Math.max(x, value.x());
            y = Math.max(y, value.y());
        }

        return new IntVector2(x, y);
    }

    /**
     * Returns the maximum vector of the provided vectors.
     *
     * @param values The vectors to get the minimum of
     * @return The maximum of the provided vectors
     */
    @Nonnull
    public static IntVector3 max(@Nonnull IntVector3... values) {
        int x = -Integer.MAX_VALUE;
        int y = -Integer.MAX_VALUE;
        int z = -Integer.MAX_VALUE;

        for (final IntVector3 value : values) {
            x = Math.max(x, value.x());
            y = Math.max(y, value.y());
            z = Math.max(z, value.z());
        }

        return new IntVector3(x, y, z);
    }

    /**
     * Returns the maximum vector of the provided vectors.
     *
     * @param values The vectors to get the minimum of
     * @return The maximum of the provided vectors
     */
    @Nonnull
    public static IntVector4 max(@Nonnull IntVector4... values) {
        int w = -Integer.MAX_VALUE;
        int x = -Integer.MAX_VALUE;
        int y = -Integer.MAX_VALUE;
        int z = -Integer.MAX_VALUE;

        for (final IntVector4 value : values) {
            w = Math.max(w, value.w());
            x = Math.max(x, value.x());
            y = Math.max(y, value.y());
            z = Math.max(z, value.z());
        }

        return new IntVector4(w, x, y, z);
    }

    //
    // Clamping
    //

    /**
     * Clamps the value {@code v} to respect the boundaries of {@code min} and {@code max}.
     *
     * @param v   The value to clamp
     * @param min The minimum acceptable value
     * @param max The maximum acceptable value
     * @return The clamped value
     */
    public static double clamp(double v, double min, double max) {
        return Math.max(Math.min(v, max), min);
    }

    //
    // Weighted Average
    //

    /**
     * Returns the weighted average of two numbers.
     *
     * @param v1 The first number
     * @param v2 The second number
     * @param w1 The weight of the first number
     * @param w2 The weight of the second number
     * @return The weighted average
     */
    public static double avgw(double v1, double v2, double w1, double w2) {
        final double denominator = w1 + w2;
        if (denominator == 0) {
            throw new ArithmeticException("Cannot divide by zero.");
        }

        return (v1 * w1 + v2 * w2) / denominator;
    }

    /**
     * Returns the weighted average of three numbers.
     *
     * @param v1 The first number
     * @param v2 The second number
     * @param v3 The third number
     * @param w1 The weight of the first number
     * @param w2 The weight of the second number
     * @param w3 The weight of the third number
     * @return The weighted average
     */
    public static double avgw(double v1, double v2, double v3, double w1, double w2, double w3) {
        final double denominator = w1 + w2 + w3;
        if (denominator == 0) {
            throw new ArithmeticException("Cannot divide by zero.");
        }

        return (v1 * w1 + v2 * w2 + v3 * w3) / denominator;
    }

    /**
     * Returns the weighted average of the given numbers.
     *
     * @param values  The values to average
     * @param weights The weights of the values to average
     * @return The weighted average
     */
    public static double avgw(@Nonnull double[] values, @Nonnull double[] weights) {
        if (values.length != weights.length) {
            throw new IllegalArgumentException("The values and weights have a different length.");
        }

        final double denominator = Numbers.sum(weights);
        if (denominator == 0) {
            throw new ArithmeticException("Cannot divide by zero.");
        }

        double sum = 0;

        for (int i = 0; i < values.length; i++) {
            sum += (values[i] * weights[i]);
        }

        return sum / denominator;
    }

    /**
     * Returns the weighted average of two vectors.
     *
     * @param v1 The first vector
     * @param v2 The second vector
     * @param w1 The weight of the first vector
     * @param w2 The weight of the second vector
     * @return The weighted average of the two vectors
     */
    @Nonnull
    public static Vector avgw(@Nonnull Vector v1, @Nonnull Vector v2, double w1, double w2) {
        if (v1.length() != v2.length()) {
            throw new IllegalArgumentException("Cannot calculate the weighted average of vectors with different lengths.");
        }

        return v1.multiply(w1).add(v2.multiply(w2)).divide(w1 + w2);
    }

    /**
     * Returns the weighted average of three vectors.
     *
     * @param v1 The first vector
     * @param v2 The second vector
     * @param v3 The third vector
     * @param w1 The weight of the first vector
     * @param w2 The weight of the second vector
     * @param w3 The weight of the third vector
     * @return The weighted average of the three vectors
     */
    @Nonnull
    public static Vector avgw(@Nonnull Vector v1, @Nonnull Vector v2, @Nonnull Vector v3, double w1, double w2, double w3) {
        if (v1.length() != v2.length() || v2.length() != v3.length()) {
            throw new IllegalArgumentException("Cannot calculate the weighted average of vectors with different lengths.");
        }

        return v1.multiply(w1).add(v2.multiply(w2)).add(v3.multiply(w3)).divide(w1 + w2 + w3);
    }

    /**
     * Returns the weighted average of two vectors.
     *
     * @param v1 The first vector
     * @param v2 The second vector
     * @param w1 The weight of the first vector
     * @param w2 The weight of the second vector
     * @return The weighted average of the two vectors
     */
    @Nonnull
    public static Vector2 avgw(@Nonnull Vector2 v1, @Nonnull Vector2 v2, double w1, double w2) {
        return v1.multiply(w1).add(v2.multiply(w2)).divide(w1 + w2);
    }

    /**
     * Returns the weighted average of three vectors.
     *
     * @param v1 The first vector
     * @param v2 The second vector
     * @param v3 The third vector
     * @param w1 The weight of the first vector
     * @param w2 The weight of the second vector
     * @param w3 The weight of the third vector
     * @return The weighted average of the three vectors
     */
    @Nonnull
    public static Vector2 avgw(@Nonnull Vector2 v1, @Nonnull Vector2 v2, @Nonnull Vector2 v3, double w1, double w2, double w3) {
        return v1.multiply(w1).add(v2.multiply(w2)).add(v3.multiply(w3)).divide(w1 + w2 + w3);
    }

    /**
     * Returns the weighted average of the given vectors.
     *
     * @param values  The vectors to average
     * @param weights The weights of the vectors to average
     * @return The weighted average
     */
    @Nonnull
    public static Vector2 avgw(@Nonnull Vector2[] values, @Nonnull double[] weights) {
        if (values.length != weights.length) {
            throw new IllegalArgumentException("The length of the values and weights are different.");
        }

        final Vector2[] weighted = new Vector2[values.length];
        for (int i = 0; i < values.length; i++) {
            weighted[i] = values[i].multiply(weights[i]);
        }

        return sum(weighted).divide(sum(weights));
    }

    /**
     * Returns the weighted average of two vectors.
     *
     * @param v1 The first vector
     * @param v2 The second vector
     * @param w1 The weight of the first vector
     * @param w2 The weight of the second vector
     * @return The weighted average of the two vectors
     */
    @Nonnull
    public static Vector3 avgw(@Nonnull Vector3 v1, @Nonnull Vector3 v2, double w1, double w2) {
        return v1.multiply(w1).add(v2.multiply(w2)).divide(w1 + w2);
    }

    /**
     * Returns the weighted average of three vectors.
     *
     * @param v1 The first vector
     * @param v2 The second vector
     * @param v3 The third vector
     * @param w1 The weight of the first vector
     * @param w2 The weight of the second vector
     * @param w3 The weight of the third vector
     * @return The weighted average of the three vectors
     */
    @Nonnull
    public static Vector3 avgw(@Nonnull Vector3 v1, @Nonnull Vector3 v2, @Nonnull Vector3 v3, double w1, double w2, double w3) {
        return v1.multiply(w1).add(v2.multiply(w2)).add(v3.multiply(w3)).divide(w1 + w2 + w3);
    }

    /**
     * Returns the weighted average of the given vectors.
     *
     * @param values  The vectors to average
     * @param weights The weights of the vectors to average
     * @return The weighted average
     */
    @Nonnull
    public static Vector3 avgw(@Nonnull Vector3[] values, @Nonnull double[] weights) {
        if (values.length != weights.length) {
            throw new IllegalArgumentException("The length of the values and weights are different.");
        }

        final Vector3[] weighted = new Vector3[values.length];
        for (int i = 0; i < values.length; i++) {
            weighted[i] = values[i].multiply(weights[i]);
        }

        return sum(weighted).divide(sum(weights));
    }

    /**
     * Returns the weighted average of two vectors.
     *
     * @param v1 The first vector
     * @param v2 The second vector
     * @param w1 The weight of the first vector
     * @param w2 The weight of the second vector
     * @return The weighted average of the two vectors
     */
    @Nonnull
    public static Vector4 avgw(@Nonnull Vector4 v1, @Nonnull Vector4 v2, double w1, double w2) {
        return v1.multiply(w1).add(v2.multiply(w2)).divide(w1 + w2);
    }

    /**
     * Returns the weighted average of three vectors.
     *
     * @param v1 The first vector
     * @param v2 The second vector
     * @param v3 The third vector
     * @param w1 The weight of the first vector
     * @param w2 The weight of the second vector
     * @param w3 The weight of the third vector
     * @return The weighted average of the three vectors
     */
    @Nonnull
    public static Vector4 avgw(@Nonnull Vector4 v1, @Nonnull Vector4 v2, @Nonnull Vector4 v3, double w1, double w2, double w3) {
        return v1.multiply(w1).add(v2.multiply(w2)).add(v3.multiply(w3)).divide(w1 + w2 + w3);
    }

    /**
     * Returns the weighted average of the given vectors.
     *
     * @param values  The vectors to average
     * @param weights The weights of the vectors to average
     * @return The weighted average
     */
    @Nonnull
    public static Vector4 avgw(@Nonnull Vector4[] values, @Nonnull double[] weights) {
        if (values.length != weights.length) {
            throw new IllegalArgumentException("The length of the values and weights are different.");
        }

        final Vector4[] weighted = new Vector4[values.length];
        for (int i = 0; i < values.length; i++) {
            weighted[i] = values[i].multiply(weights[i]);
        }

        return sum(weighted).divide(sum(weights));
    }

}
