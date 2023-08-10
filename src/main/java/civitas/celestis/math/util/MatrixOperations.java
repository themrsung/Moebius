package civitas.celestis.math.util;

import civitas.celestis.graphics.util.Position;
import civitas.celestis.graphics.util.Vertex;
import civitas.celestis.math.matrix.Matrix;
import civitas.celestis.math.quaternion.Quaternion;
import civitas.celestis.math.vector.Vector;
import civitas.celestis.math.vector.Vector2;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.math.vector.Vector4;
import jakarta.annotation.Nonnull;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Contains static matrix operations.
 * These operations can be used to offload work to a static context.
 */
public final class MatrixOperations {
    //
    // Scalar Arithmetic
    //

    /**
     * Performs matrix-scalar addition.
     *
     * @param result Pointer to put the result in
     * @param m      Matrix of the operation
     * @param s      Scalar of the operation
     */
    public static void add(@Nonnull AtomicReference<Matrix> result, @Nonnull Matrix m, double s) {
        result.set(m.add(s));
    }

    /**
     * Performs matrix-scalar subtraction.
     *
     * @param result Pointer to put the result in
     * @param m      Matrix of the operation
     * @param s      Scalar of the operation
     */
    public static void subtract(@Nonnull AtomicReference<Matrix> result, @Nonnull Matrix m, double s) {
        result.set(m.subtract(s));
    }

    /**
     * Performs matrix-scalar multiplication.
     *
     * @param result Pointer to put the result in
     * @param m      Matrix of the operation
     * @param s      Scalar of the operation
     */
    public static void multiply(@Nonnull AtomicReference<Matrix> result, @Nonnull Matrix m, double s) {
        result.set(m.multiply(s));
    }

    /**
     * Performs matrix-scalar division.
     *
     * @param result Pointer to put the result in
     * @param m      Matrix of the operation
     * @param s      Scalar of the operation
     */
    public static void divide(@Nonnull AtomicReference<Matrix> result, @Nonnull Matrix m, double s) {
        result.set(m.divide(s));
    }

    //
    // Vector Arithmetic
    //

    /**
     * Performs matrix-vector multiplication.
     *
     * @param result Pointer to put the result in
     * @param m      Matrix of the operation
     * @param v      Vector of the operation
     */
    public static void multiply(@Nonnull AtomicReference<Vector> result, @Nonnull Matrix m, @Nonnull Vector v) {
        result.set(m.multiply(v));
    }

    /**
     * Performs matrix-vector multiplication.
     *
     * @param result Pointer to put the result in
     * @param m      Matrix of the operation
     * @param v      Vector of the operation
     */
    public static void multiply(@Nonnull AtomicReference<Vector2> result, @Nonnull Matrix m, @Nonnull Vector2 v) {
        // No need to check the instance for matching type
        result.set((Vector2) m.multiply(v));
    }

    /**
     * Performs matrix-position multiplication.
     *
     * @param result Pointer to put the result in
     * @param m      Matrix of the operation
     * @param v      Position of the operation
     */
    public static void multiply(@Nonnull AtomicReference<Position> result, @Nonnull Matrix m, @Nonnull Position v) {
        result.set(new Position((Vector2) m.multiply(v)));
    }

    /**
     * Performs matrix-vector multiplication.
     *
     * @param result Pointer to put the result in
     * @param m      Matrix of the operation
     * @param v      Vector of the operation
     */
    public static void multiply(@Nonnull AtomicReference<Vector3> result, @Nonnull Matrix m, @Nonnull Vector3 v) {
        // No need to check the instance for matching type
        result.set((Vector3) m.multiply(v));
    }

    /**
     * Performs matrix-vertex multiplication.
     *
     * @param result Pointer to put the result in
     * @param m      Matrix of the operation
     * @param v      Vertex of the operation
     */
    public static void multiply(@Nonnull AtomicReference<Vertex> result, @Nonnull Matrix m, @Nonnull Vertex v) {
        result.set(new Vertex((Vector3) m.multiply(v)));
    }

    /**
     * Performs matrix-vector multiplication.
     *
     * @param result Pointer to put the result in
     * @param m      Matrix of the operation
     * @param v      Vector of the operation
     */
    public static void multiply(@Nonnull AtomicReference<Vector4> result, @Nonnull Matrix m, @Nonnull Vector4 v) {
        // No need to check the instance for matching type
        result.set((Vector4) m.multiply(v));
    }

    /**
     * Performs matrix-quaternion multiplication.
     *
     * @param result Pointer to put the result in
     * @param m      Matrix of the operation
     * @param q      Quaternion of the operation
     */
    public static void multiply(@Nonnull AtomicReference<Quaternion> result, @Nonnull Matrix m, @Nonnull Quaternion q) {
        result.set(new Quaternion((Vector4) m.multiply(q)));
    }

    //
    // Matrix Arithmetic
    //

    /**
     * Adds two matrices together.
     *
     * @param result Pointer to put the result in
     * @param m1     The first matrix
     * @param m2     The second matrix
     */
    public static void add(@Nonnull AtomicReference<Matrix> result, @Nonnull Matrix m1, @Nonnull Matrix m2) {
        result.set(m1.add(m2));
    }

    /**
     * Subtracts two matrices.
     *
     * @param result Pointer to put the result in
     * @param m1     The first matrix
     * @param m2     The second matrix
     */
    public static void subtract(@Nonnull AtomicReference<Matrix> result, @Nonnull Matrix m1, @Nonnull Matrix m2) {
        result.set(m1.subtract(m2));
    }

    /**
     * Multiplies two matrices.
     *
     * @param result Pointer to put the result in
     * @param m1     The first matrix
     * @param m2     The second matrix
     */
    public static void multiply(@Nonnull AtomicReference<Matrix> result, @Nonnull Matrix m1, @Nonnull Matrix m2) {
        result.set(m1.multiply(m2));
    }

    //
    // Equality
    //

    /**
     * Checks for exact equality.
     *
     * @param result Pointer to put the result in
     * @param m1     The first matrix
     * @param m2     The second matrix
     */
    public static void strictEquals(@Nonnull AtomicBoolean result, @Nonnull Matrix m1, @Nonnull Matrix m2) {
        result.set(m1.equals(m2));
    }

    /**
     * Checks for loose equality.
     *
     * @param result Pointer to put the result in
     * @param m1     The first matrix
     * @param m2     The second matrix
     */
    public static void looseEquals(@Nonnull AtomicBoolean result, @Nonnull Matrix m1, @Nonnull Matrix m2) {
        result.set(Numbers.equals(m1, m2));
    }
}
