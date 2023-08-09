package civitas.celestis.math.util;

import civitas.celestis.math.matrix.Matrix;
import civitas.celestis.math.vector.Vector;
import civitas.celestis.math.vector.Vector2;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.math.vector.Vector4;
import jakarta.annotation.Nonnull;

/**
 * Contains utility methods related to numbers.
 */
public final class Numbers {
    //
    // Constraints
    //

    /**
     * Explicitly denotes that a given field requires a finite values as its input.
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
}
