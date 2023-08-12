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
        if (value < min || value > max) {
            throw new IllegalArgumentException("This field required the value to be within range " + min + "-" + max + ".");
        }

        return value;
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
}
