package civitas.celestis.math.util;

import civitas.celestis.math.matrix.Matrix;
import civitas.celestis.math.vector.*;
import jakarta.annotation.Nonnull;

/**
 * Contains utility methods related to numbers.
 */
public final class Numbers {
    //
    // Constraints
    //

    /**
     * Explicitly denotes that a given field requires a finite value as its input.
     *
     * @param value Value to check for finiteness
     * @return The value provided as the input parameter
     * @throws IllegalArgumentException When the given input is non-finite ({@link Double#isFinite(double)} return {@code false})
     */
    public static double requireFinite(double value) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("This field requires a finite value. The provided value was " + value + ".");
        }

        return value;
    }

    /**
     * Explicitly denotes that a given field requires a non-negative value as its input.
     *
     * @param value Value to check for non-negativeness
     * @return The value provided as the input parameter
     * @throws IllegalArgumentException When the given input is non-negative ({@code value < 0})
     */
    public static double requireNonNegative(double value) {
        if (value < 0) {
            throw new IllegalArgumentException("This field requires a non-negative value. The provided value was " + value + ".");
        }

        return value;
    }

    /**
     * Explicitly denotes that a given field requires a positive value as its input.
     *
     * @param value Value to check for positiveness
     * @return The value provided as the input parameter
     * @throws IllegalArgumentException When the given input is non-positive ({@code value <= 0})
     */
    public static double requirePositive(double value) {
        if (value <= 0) {
            throw new IllegalArgumentException("This field requires a positive value. The provided value was " + value + ".");
        }

        return value;
    }

    //
    // Randomization
    //

    /**
     * Returns a random {@code double} between the provided boundaries.
     *
     * @param min Minimum acceptable value
     * @param max Maximum acceptable value
     * @return A random value between {@code min} and {@code max}
     */
    public static double random(double min, double max) {
        return Math.random() * (max - min) + min;
    }

    //
    // Casting
    //

    /**
     * Safely casts a {@code double} to an {@code int}.
     * The resulting value is guaranteed to represent the double as accurately as possible.
     *
     * @param value Value to represent as an integer
     * @return The integer representation of the {@code double}
     * @throws UnsupportedOperationException When the operation will result in an underflow or an overflow
     */
    public static int safeCastToInt(double value) {
        // Adds margin of 1 to the comparison for safety
        if (value > Integer.MAX_VALUE || value < -Integer.MAX_VALUE) {
            throw new UnsupportedOperationException("The provided value is not representable by an integer.");
        }

        return (int) value;
    }

    //
    // Algorithms
    //

    /**
     * Given an array of {@code double}s, this returns the sum of the values.
     *
     * @param values Values to sum
     * @return The sum of the given values
     */
    public static double sum(double... values) {
        double sum = 0;

        for (final double value : values) {
            sum += value;
        }

        return sum;
    }

    /**
     * Given an array of {@code double}s, this returns the average of the values.
     *
     * @param values Values to average
     * @return The average of the given values
     */
    public static double avg(double... values) {
        return divide(sum(values), values.length);
    }

    /**
     * Given an array of {@code double}s, this returns the maximum value.
     *
     * @param values Values to max
     * @return The maximum value
     */
    public static double max(double... values) {
        double max = -Double.MAX_VALUE;

        for (final double value : values) {
            max = Math.max(max, value);
        }

        return max;
    }

    /**
     * Given an array of {@code double}s, this returns the minimum value.
     *
     * @param values Values to mim
     * @return The minimum value
     */
    public static double min(double... values) {
        double min = Double.MAX_VALUE;

        for (final double value : values) {
            min = Math.min(min, value);
        }

        return min;
    }

    /**
     * Given a minimum and maximum allowed range, this returns the clamped value.
     *
     * @param value Value to clamp
     * @param min   Minimum allowed value
     * @param max   Maximum allowed value
     * @return The clamped value
     */
    public static double clamp(double value, double min, double max) {
        return Math.max(Math.min(value, max), min);
    }

    /**
     * Performs floating point division while triggering an arithmetic exception.
     *
     * @param numerator   Numerator of the calculation
     * @param denominator Denominator of the calculation
     * @return The result of the division
     * @throws ArithmeticException When the denominator is zero
     */
    public static double divide(double numerator, double denominator) throws ArithmeticException {
        if (denominator == 0) throw new ArithmeticException("Cannot divide by zero.");

        return numerator / denominator;
    }

    /**
     * If the given value is an integer (which is determined by {@link Numbers#isInteger(double)}),
     * this corrects the given value to the nearest integer.
     *
     * @param value Value to correct to nearest integer
     * @return Corrected value is the value is an integer, the original value if not
     */
    public static double correct(double value) {
        if (isInteger(value)) {
            return (double) (long) value;
        }

        return value;
    }

    /**
     * Checks if given value {@code n} is an integer, while accounting for the {@link Numbers#MARGIN_OF_SIGNIFICANCE}.
     *
     * @param n The number to check
     * @return {@code true} if the number can be considered an integer
     */
    public static boolean isInteger(double n) {
        return equals(n, Math.round(n));
    }

    /**
     * Rounds a number while retaining an arbitrary number of decimal points.
     *
     * @param n      Number to round
     * @param digits Number of digits to retain
     * @return The rounded value
     */
    public static double round(double n, int digits) {
        if (digits == 0) {
            return Math.round(n);
        }

        final double factor = Math.pow(10, digits);

        return Math.round(n * factor) / factor;
    }

    /**
     * Floors a number while retaining an arbitrary number of decimal points.
     *
     * @param n      Number to round
     * @param digits Number of digits to retain
     * @return The floored value
     */
    public static double floor(double n, int digits) {
        if (digits == 0) {
            return Math.floor(n);
        }

        final double factor = Math.pow(10, digits);

        return Math.floor(n * factor) / factor;
    }

    /**
     * Returns the ceiling of a number while retaining an arbitrary number of decimal points.
     *
     * @param n      Number to ceil
     * @param digits Number of digits to retain
     * @return The ceiling of the number
     */
    public static double ceil(double n, int digits) {
        if (digits == 0) {
            return Math.ceil(n);
        }

        final double factor = Math.pow(10, digits);

        return Math.ceil(n * factor) / factor;
    }

    /**
     * Calculates the factorial of the provided input {@code n}.
     * Note that this requires a non-negative input.
     *
     * @param n The number to get the factorial of
     * @return The factorial of {@code n}
     */
    public static double factorial(double n) {
        // Estimate factorial using the Gamma function
        return gamma(requireNonNegative(n) + 1);
    }

    /**
     * Calculates the factorial of the provided input {@code n}.
     * Note that this requires a non-negative input.
     *
     * @param n The number to get the factorial of
     * @return The factorial of {@code n}
     */
    public static long factorial(long n) {
        // Use factorial table if possible
        if (requireNonNegative(n) < FACTORIALS.length) {
            return FACTORIALS[(int) n];
        }

        // Numbers above the threshold will result in overflows
        if (n > FACTORIAL_CALCULATION_THRESHOLD) {
            throw new ArithmeticException("Given input will trigger an integer overflow. Use factorial(double) instead.");
        }

        // This fallback algorithm will not be reached if the default settings are kept intact

        // Calculate factorial through recursion
        return n * factorial(n - 1);
    }

    /**
     * Calculates the factorial of the provided input {@code n}.
     * Note that this requires a non-negative input.
     *
     * @param n The number to get the factorial of
     * @return The factorial of {@code n}
     */
    public static long factorial(int n) {
        // Use factorial table if possible
        if (requireNonNegative(n) < FACTORIALS.length) {
            return FACTORIALS[n];
        }

        // Delegate to factorial(long)
        return factorial((long) n);
    }

    // Factorial table
    private static final long[] FACTORIALS = {
            1,
            1,
            2,
            6,
            24,
            120,
            720,
            5040,
            40320,
            362880,
            3628800,
            39916800,
            479001600,
            6227020800L,
            87178291200L,
            1307674368000L,
            20922789888000L,
            355687428096000L,
            6402373705728000L,
            121645100408832000L,
            2432902008176640000L
    };

    // The maximum input of the factorial function this class will attempt to calculate
    private static final int FACTORIAL_CALCULATION_THRESHOLD = FACTORIALS.length - 1;

    // Lanczos parameters
    private static final double LANCZOS_G = 7;
    private static final double[] LANCZOS_COEFFICIENTS = {
            0.99999999999980993,
            676.5203681218851,
            -1259.1392167224028,
            771.32342877765313,
            -176.61502916214059,
            12.507343278686905,
            -0.13857109526572012,
            9.9843695780195716e-6,
            1.5056327351493116e-7
    };

    /**
     * Returns the gamma of the given input {@code n}.
     *
     * @param n The value to get the gamma of
     * @return The gamma function value of {@code n}
     */
    public static double gamma(double n) {
        // Copy value for manipulation
        double x = n;

        if (x <= 0.5) {
            return Math.PI / (Math.sin(Math.PI * x) * gamma(1 - x));
        }

        x -= 1;
        double result = LANCZOS_COEFFICIENTS[0];
        for (int i = 1; i < LANCZOS_COEFFICIENTS.length; i++) {
            result += LANCZOS_COEFFICIENTS[i] / (x + i);
        }

        double t = x + LANCZOS_G + 0.5;
        return Math.sqrt(2 * Math.PI) * Math.pow(t, x + 0.5) * Math.exp(-t) * result;
    }

    //
    // Loose Comparison
    //

    /**
     * The margin of significance. Any differences below this value will be considered zero.
     */
    public static final double MARGIN_OF_SIGNIFICANCE = 1e-6;

    private static final double VECTOR2_COMPONENT_MARGIN = MARGIN_OF_SIGNIFICANCE / 2;
    private static final double VECTOR3_COMPONENT_MARGIN = MARGIN_OF_SIGNIFICANCE / 3;
    private static final double VECTOR4_COMPONENT_MARGIN = MARGIN_OF_SIGNIFICANCE / 4;
    private static final double VECTOR5_COMPONENT_MARGIN = MARGIN_OF_SIGNIFICANCE / 5;

    /**
     * Checks for equality between two {@code double}s while accounting for the {@link Numbers#MARGIN_OF_SIGNIFICANCE}.
     *
     * @param d1 The first double to compare
     * @param d2 The second double to compare
     * @return {@code true} if the two doubles are considered equal
     */
    public static boolean equals(double d1, double d2) {
        return Math.abs(d1 - d2) < MARGIN_OF_SIGNIFICANCE;
    }

    /**
     * Checks for equality between two {@link Vector}s while accounting for the {@link Numbers#MARGIN_OF_SIGNIFICANCE}.
     *
     * @param v1 The first vector to compare
     * @param v2 The second vector to compare
     * @return {@code true} if the two vectors are considered equal
     */
    public static boolean equals(@Nonnull Vector v1, @Nonnull Vector v2) {
        if (v1.length() != v2.length()) return false;

        final double componentMargin = MARGIN_OF_SIGNIFICANCE / v1.length();
        final double[] a1 = v1.values();
        final double[] a2 = v2.values();

        for (int i = 0; i < v1.length(); i++) {
            if (Math.abs(a1[i] - a2[i]) >= componentMargin) return false;
        }

        return true;
    }

    /**
     * Checks for equality between two {@link Vector2}s while accounting for the {@link Numbers#MARGIN_OF_SIGNIFICANCE}.
     *
     * @param v1 The first vector to compare
     * @param v2 The second vector to compare
     * @return {@code true} if the two vectors are considered equal
     */
    public static boolean equals(@Nonnull Vector2 v1, @Nonnull Vector2 v2) {
        return Math.abs(v1.x() - v2.x()) < VECTOR2_COMPONENT_MARGIN &&
                Math.abs(v1.y() - v2.y()) < VECTOR2_COMPONENT_MARGIN;
    }

    /**
     * Checks for equality between two {@link Vector3}s while accounting for the {@link Numbers#MARGIN_OF_SIGNIFICANCE}.
     *
     * @param v1 The first vector to compare
     * @param v2 The second vector to compare
     * @return {@code true} if the two vectors are considered equal
     */
    public static boolean equals(@Nonnull Vector3 v1, @Nonnull Vector3 v2) {
        return Math.abs(v1.x() - v2.x()) < VECTOR3_COMPONENT_MARGIN &&
                Math.abs(v1.y() - v2.y()) < VECTOR3_COMPONENT_MARGIN &&
                Math.abs(v1.z() - v2.z()) < VECTOR3_COMPONENT_MARGIN;
    }

    /**
     * Checks for equality between two {@link Vector4}s while accounting for the {@link Numbers#MARGIN_OF_SIGNIFICANCE}.
     *
     * @param v1 The first vector to compare
     * @param v2 The second vector to compare
     * @return {@code true} if the two vectors are considered equal
     */
    public static boolean equals(@Nonnull Vector4 v1, @Nonnull Vector4 v2) {
        return Math.abs(v1.w() - v2.w()) < VECTOR4_COMPONENT_MARGIN &&
                Math.abs(v1.x() - v2.x()) < VECTOR4_COMPONENT_MARGIN &&
                Math.abs(v1.y() - v2.y()) < VECTOR4_COMPONENT_MARGIN &&
                Math.abs(v1.z() - v2.z()) < VECTOR4_COMPONENT_MARGIN;
    }

    /**
     * Checks for equality between two {@link Vector5}s while accounting for the {@link Numbers#MARGIN_OF_SIGNIFICANCE}.
     *
     * @param v1 The first vector to compare
     * @param v2 The second vector to compare
     * @return {@code true} if the two vectors are considered equal
     */
    public static boolean equals(@Nonnull Vector5 v1, @Nonnull Vector5 v2) {
        return Math.abs(v1.v() - v2.v()) < VECTOR5_COMPONENT_MARGIN &&
                Math.abs(v1.w() - v2.w()) < VECTOR5_COMPONENT_MARGIN &&
                Math.abs(v1.x() - v2.x()) < VECTOR5_COMPONENT_MARGIN &&
                Math.abs(v1.y() - v2.y()) < VECTOR5_COMPONENT_MARGIN &&
                Math.abs(v1.z() - v2.z()) < VECTOR5_COMPONENT_MARGIN;
    }

    /**
     * Checks for equality between two {@link Matrix} instances while accounting for the {@link Numbers#MARGIN_OF_SIGNIFICANCE}.
     *
     * @param m1 The first matrix to compare
     * @param m2 The second matrix to compare
     * @return {@code true} if the two matrices are considered equal
     */
    public static boolean equals(@Nonnull Matrix m1, @Nonnull Matrix m2) {
        if (m1.rows() != m2.rows() || m1.columns() != m2.columns()) return false;

        for (int r = 0; r < m1.rows(); r++) {
            for (int c = 0; c < m2.columns(); c++) {
                if (!equals(m1.get(r, c), m2.get(r, c))) return false;
            }
        }

        return true;
    }

    /**
     * Compares two {@code double}s while accounting for the {@link Numbers#MARGIN_OF_SIGNIFICANCE}.
     *
     * @param d1 The first double to compare
     * @param d2 The second double to compare
     * @return {@code 0} if the two doubles are considered equal, {@code 1} if the first is larger,
     * {@code -1} if the second is larger
     */
    public static int compare(double d1, double d2) {
        if (equals(d1, d2)) return 0;
        return d1 > d2 ? 1 : -1;
    }

    /**
     * Checks if two vectors are facing the same direction.
     * This accounts for small errors using the {@link Numbers#MARGIN_OF_SIGNIFICANCE}.
     *
     * @param v1 The first vector to compare
     * @param v2 The second vector to compare
     * @return {@code true} if the dot product of the two vectors are equal to or smaller than the threshold
     */
    public static boolean isParallel(@Nonnull Vector2 v1, @Nonnull Vector2 v2) {
        final Vector2 n1 = v1.normalize();
        final Vector2 n2 = v2.normalize();

        return equals(Math.pow(n1.dot(n2), 2), 1);
    }

    /**
     * Checks if two vectors are facing the same direction.
     * This accounts for small errors using the {@link Numbers#MARGIN_OF_SIGNIFICANCE}.
     *
     * @param v1 The first vector to compare
     * @param v2 The second vector to compare
     * @return {@code true} if the dot product of the two vectors are equal to or smaller than the threshold
     */
    public static boolean isParallel(@Nonnull Vector3 v1, @Nonnull Vector3 v2) {
        final Vector3 n1 = v1.normalize();
        final Vector3 n2 = v2.normalize();

        return equals(Math.pow(n1.dot(n2), 2), 1);
    }

    /**
     * Checks if two vectors are facing the same direction.
     * This accounts for small errors using the {@link Numbers#MARGIN_OF_SIGNIFICANCE}.
     *
     * @param v1 The first vector to compare
     * @param v2 The second vector to compare
     * @return {@code true} if the dot product of the two vectors are equal to or smaller than the threshold
     */
    public static boolean isParallel(@Nonnull Vector4 v1, @Nonnull Vector4 v2) {
        final Vector4 n1 = v1.normalize();
        final Vector4 n2 = v2.normalize();

        return equals(Math.pow(n1.dot(n2), 2), 1);
    }

    /**
     * Checks if two vectors are facing the same direction.
     * This accounts for small errors using the {@link Numbers#MARGIN_OF_SIGNIFICANCE}.
     *
     * @param v1 The first vector to compare
     * @param v2 The second vector to compare
     * @return {@code true} if the dot product of the two vectors are equal to or smaller than the threshold
     */
    public static boolean isParallel(@Nonnull Vector5 v1, @Nonnull Vector5 v2) {
        final Vector5 n1 = v1.normalize();
        final Vector5 n2 = v2.normalize();

        return equals(Math.pow(n1.dot(n2), 2), 1);
    }
}
