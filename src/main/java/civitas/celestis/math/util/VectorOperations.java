package civitas.celestis.math.util;

import civitas.celestis.graphics.util.Position;
import civitas.celestis.graphics.util.Vertex;
import civitas.celestis.math.quaternion.Quaternion;
import civitas.celestis.math.vector.Vector;
import civitas.celestis.math.vector.Vector2;
import civitas.celestis.math.vector.Vector3;
import civitas.celestis.math.vector.Vector4;
import jakarta.annotation.Nonnull;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.UnaryOperator;

/**
 * Contains static vector operations.
 * These operations can be used to offload work to a static context.
 */
public final class VectorOperations {
    //
    // Magnitude
    //

    /**
     * Gets the magnitude of a vector.
     *
     * @param result Pointer to put the result in
     * @param v      Vector to get magnitude of
     */
    public static void magnitude(@Nonnull AtomicReference<Double> result, @Nonnull Vector v) {
        result.set(v.magnitude());
    }

    /**
     * Gets the squared magnitude of a vector.
     *
     * @param result Pointer to put the result in
     * @param v      Vector to get squared magnitude of
     */
    public static void magnitude2(@Nonnull AtomicReference<Double> result, @Nonnull Vector v) {
        result.set(v.magnitude2());
    }

    //
    // Scalar Addition
    //

    /**
     * Adds a vector and a scalar.
     *
     * @param result Pointer to put the result in
     * @param v      Vector to add
     * @param s      Scalar to add
     */
    public static void add(@Nonnull AtomicReference<Vector> result, @Nonnull Vector v, double s) {
        result.set(v.add(s));
    }

    /**
     * Adds a vector and a scalar.
     *
     * @param result Pointer to put the result in
     * @param v      Vector to add
     * @param s      Scalar to add
     */
    public static void add(@Nonnull AtomicReference<Vector2> result, @Nonnull Vector2 v, double s) {
        result.set(v.add(s));
    }

    /**
     * Adds a position and a scalar.
     *
     * @param result Pointer to put the result in
     * @param p      Position to add
     * @param s      Scalar to add
     */
    public static void add(@Nonnull AtomicReference<Position> result, @Nonnull Position p, double s) {
        result.set(p.add(s));
    }

    /**
     * Adds a vector and a scalar.
     *
     * @param result Pointer to put the result in
     * @param v      Vector to add
     * @param s      Scalar to add
     */
    public static void add(@Nonnull AtomicReference<Vector3> result, @Nonnull Vector3 v, double s) {
        result.set(v.add(s));
    }

    /**
     * Adds a vertex and a scalar.
     *
     * @param result Pointer to put the result in
     * @param v      Vertex to add
     * @param s      Scalar to add
     */
    public static void add(@Nonnull AtomicReference<Vertex> result, @Nonnull Vertex v, double s) {
        result.set(v.add(s));
    }

    /**
     * Adds a vector and a scalar.
     *
     * @param result Pointer to put the result in
     * @param v      Vector to add
     * @param s      Scalar to add
     */
    public static void add(@Nonnull AtomicReference<Vector4> result, @Nonnull Vector4 v, double s) {
        result.set(v.add(s));
    }

    /**
     * Adds a quaternion and a scalar.
     *
     * @param result Pointer to put the result in
     * @param q      Quaternion to add
     * @param s      Scalar to add
     */
    public static void add(@Nonnull AtomicReference<Quaternion> result, @Nonnull Quaternion q, double s) {
        result.set(q.add(s));
    }

    //
    // Scalar Subtraction
    //

    /**
     * Subtracts a scalar from a vector.
     *
     * @param result Pointer to put the result in
     * @param v      Vector to subtract from
     * @param s      Scalar to subtract by
     */
    public static void subtract(@Nonnull AtomicReference<Vector> result, @Nonnull Vector v, double s) {
        result.set(v.subtract(s));
    }

    /**
     * Subtracts a scalar from a vector.
     *
     * @param result Pointer to put the result in
     * @param v      Vector to subtract from
     * @param s      Scalar to subtract by
     */
    public static void subtract(@Nonnull AtomicReference<Vector2> result, @Nonnull Vector2 v, double s) {
        result.set(v.subtract(s));
    }

    /**
     * Subtracts a scalar from a position.
     *
     * @param result Pointer to put the result in
     * @param p      Position to subtract from
     * @param s      Scalar to subtract by
     */
    public static void subtract(@Nonnull AtomicReference<Position> result, @Nonnull Position p, double s) {
        result.set(p.subtract(s));
    }

    /**
     * Subtracts a scalar from a vector.
     *
     * @param result Pointer to put the result in
     * @param v      Vector to subtract from
     * @param s      Scalar to subtract by
     */
    public static void subtract(@Nonnull AtomicReference<Vector3> result, @Nonnull Vector3 v, double s) {
        result.set(v.subtract(s));
    }

    /**
     * Subtracts a scalar from a vertex.
     *
     * @param result Pointer to put the result in
     * @param v      Vertex to subtract from
     * @param s      Scalar to subtract by
     */
    public static void subtract(@Nonnull AtomicReference<Vertex> result, @Nonnull Vertex v, double s) {
        result.set(v.subtract(s));
    }

    /**
     * Subtracts a scalar from a vector.
     *
     * @param result Pointer to put the result in
     * @param v      Vector to subtract from
     * @param s      Scalar to subtract by
     */
    public static void subtract(@Nonnull AtomicReference<Vector4> result, @Nonnull Vector4 v, double s) {
        result.set(v.subtract(s));
    }

    /**
     * Subtracts a scalar from a quaternion.
     *
     * @param result Pointer to put the result in
     * @param q      Quaternion to subtract from
     * @param s      Scalar to subtract by
     */
    public static void subtract(@Nonnull AtomicReference<Quaternion> result, @Nonnull Quaternion q, double s) {
        result.set(q.subtract(s));
    }

    //
    // Scalar Multiplication
    //

    /**
     * Multiplies a vector by a scalar.
     *
     * @param result Pointer to put the result in
     * @param v      Vector to multiply
     * @param s      Scalar to multiply to
     */
    public static void multiply(@Nonnull AtomicReference<Vector> result, @Nonnull Vector v, double s) {
        result.set(v.multiply(s));
    }

    /**
     * Multiplies a vector by a scalar.
     *
     * @param result Pointer to put the result in
     * @param v      Vector to multiply
     * @param s      Scalar to multiply to
     */
    public static void multiply(@Nonnull AtomicReference<Vector2> result, @Nonnull Vector2 v, double s) {
        result.set(v.multiply(s));
    }

    /**
     * Multiplies a position by a scalar.
     *
     * @param result Pointer to put the result in
     * @param p      Position to multiply
     * @param s      Scalar to multiply to
     */
    public static void multiply(@Nonnull AtomicReference<Position> result, @Nonnull Position p, double s) {
        result.set(p.multiply(s));
    }

    /**
     * Multiplies a vector by a scalar.
     *
     * @param result Pointer to put the result in
     * @param v      Vector to multiply
     * @param s      Scalar to multiply to
     */
    public static void multiply(@Nonnull AtomicReference<Vector3> result, @Nonnull Vector3 v, double s) {
        result.set(v.multiply(s));
    }

    /**
     * Multiplies a vertex by a scalar.
     *
     * @param result Pointer to put the result in
     * @param v      Vertex to multiply
     * @param s      Scalar to multiply to
     */
    public static void multiply(@Nonnull AtomicReference<Vertex> result, @Nonnull Vertex v, double s) {
        result.set(v.multiply(s));
    }

    /**
     * Multiplies a vector by a scalar.
     *
     * @param result Pointer to put the result in
     * @param v      Vector to multiply
     * @param s      Scalar to multiply to
     */
    public static void multiply(@Nonnull AtomicReference<Vector4> result, @Nonnull Vector4 v, double s) {
        result.set(v.multiply(s));
    }

    /**
     * Multiplies a quaternion by a scalar.
     *
     * @param result Pointer to put the result in
     * @param q      Quaternion to multiply
     * @param s      Scalar to multiply to
     */
    public static void multiply(@Nonnull AtomicReference<Quaternion> result, @Nonnull Quaternion q, double s) {
        result.set(q.multiply(s));
    }

    //
    // Scalar Division
    //

    /**
     * Divides a vector by a scalar.
     *
     * @param result Pointer to put the result in
     * @param v      Vector to divide
     * @param s      Denominator to use
     */
    public static void divide(@Nonnull AtomicReference<Vector> result, @Nonnull Vector v, double s) {
        result.set(v.divide(s));
    }

    /**
     * Divides a vector by a scalar.
     *
     * @param result Pointer to put the result in
     * @param v      Vector to divide
     * @param s      Denominator to use
     */
    public static void divide(@Nonnull AtomicReference<Vector2> result, @Nonnull Vector2 v, double s) {
        result.set(v.divide(s));
    }

    /**
     * Divides a position by a scalar.
     *
     * @param result Pointer to put the result in
     * @param p      Position to divide
     * @param s      Denominator to use
     */
    public static void divide(@Nonnull AtomicReference<Position> result, @Nonnull Position p, double s) {
        result.set(p.divide(s));
    }

    /**
     * Divides a vector by a scalar.
     *
     * @param result Pointer to put the result in
     * @param v      Vector to divide
     * @param s      Denominator to use
     */
    public static void divide(@Nonnull AtomicReference<Vector3> result, @Nonnull Vector3 v, double s) {
        result.set(v.divide(s));
    }

    /**
     * Divides a vertex by a scalar.
     *
     * @param result Pointer to put the result in
     * @param v      Vertex to divide
     * @param s      Denominator to use
     */
    public static void divide(@Nonnull AtomicReference<Vertex> result, @Nonnull Vertex v, double s) {
        result.set(v.divide(s));
    }

    /**
     * Divides a vector by a scalar.
     *
     * @param result Pointer to put the result in
     * @param v      Vector to divide
     * @param s      Denominator to use
     */
    public static void divide(@Nonnull AtomicReference<Vector4> result, @Nonnull Vector4 v, double s) {
        result.set(v.divide(s));
    }

    /**
     * Divides a quaternion by a scalar.
     *
     * @param result Pointer to put the result in
     * @param q      Quaternion to divide
     * @param s      Denominator to use
     */
    public static void divide(@Nonnull AtomicReference<Quaternion> result, @Nonnull Quaternion q, double s) {
        result.set(q.divide(s));
    }

    //
    // Vector Addition
    //

    /**
     * Adds two vectors.
     *
     * @param result Pointer to put the result in
     * @param v1     The first vector of this operation
     * @param v2     The second vector of this operation
     */
    public static void add(@Nonnull AtomicReference<Vector2> result, @Nonnull Vector2 v1, @Nonnull Vector2 v2) {
        result.set(v1.add(v2));
    }

    /**
     * Adds two positions.
     *
     * @param result Pointer to put the result in
     * @param p1     The first position of this operation
     * @param p2     The second position (or vector) of this operation
     */
    public static void add(@Nonnull AtomicReference<Position> result, @Nonnull Position p1, @Nonnull Vector2 p2) {
        result.set(p1.add(p2));
    }

    /**
     * Adds two vectors.
     *
     * @param result Pointer to put the result in
     * @param v1     The first vector of this operation
     * @param v2     The second vector of this operation
     */
    public static void add(@Nonnull AtomicReference<Vector3> result, @Nonnull Vector3 v1, @Nonnull Vector3 v2) {
        result.set(v1.add(v2));
    }

    /**
     * Adds two vertices.
     *
     * @param result Pointer to put the result in
     * @param v1     The first vertex of this operation
     * @param v2     The second vertex (or vector) of this operation
     */
    public static void add(@Nonnull AtomicReference<Vertex> result, @Nonnull Vertex v1, @Nonnull Vector3 v2) {
        result.set(v1.add(v2));
    }

    /**
     * Adds two vectors.
     *
     * @param result Pointer to put the result in
     * @param v1     The first vector of this operation
     * @param v2     The second vector of this operation
     */
    public static void add(@Nonnull AtomicReference<Vector4> result, @Nonnull Vector4 v1, @Nonnull Vector4 v2) {
        result.set(v1.add(v2));
    }

    /**
     * Adds two quaternions.
     *
     * @param result Pointer to put the result in
     * @param q1     The first quaternion of this operation
     * @param q2     The second quaternion (or vector) of this operation
     */
    public static void add(@Nonnull AtomicReference<Quaternion> result, @Nonnull Quaternion q1, @Nonnull Vector4 q2) {
        result.set(q1.add(q2));
    }

    //
    // Vector Subtraction
    //

    /**
     * Subtracts two vectors.
     *
     * @param result Pointer to put the result in
     * @param v1     The first vector of this operation
     * @param v2     The second vector of this operation
     */
    public static void subtract(@Nonnull AtomicReference<Vector2> result, @Nonnull Vector2 v1, @Nonnull Vector2 v2) {
        result.set(v1.subtract(v2));
    }

    /**
     * Subtracts two positions.
     *
     * @param result Pointer to put the result in
     * @param p1     The first position of this operation
     * @param p2     The second position (or vector) of this operation
     */
    public static void subtract(@Nonnull AtomicReference<Position> result, @Nonnull Position p1, @Nonnull Vector2 p2) {
        result.set(p1.subtract(p2));
    }

    /**
     * Subtracts two vectors.
     *
     * @param result Pointer to put the result in
     * @param v1     The first vector of this operation
     * @param v2     The second vector of this operation
     */
    public static void subtract(@Nonnull AtomicReference<Vector3> result, @Nonnull Vector3 v1, @Nonnull Vector3 v2) {
        result.set(v1.subtract(v2));
    }

    /**
     * Subtracts two vertices.
     *
     * @param result Pointer to put the result in
     * @param v1     The first vertex of this operation
     * @param v2     The second vertex (or vector) of this operation
     */
    public static void subtract(@Nonnull AtomicReference<Vertex> result, @Nonnull Vertex v1, @Nonnull Vector3 v2) {
        result.set(v1.subtract(v2));
    }

    /**
     * Subtracts two vectors.
     *
     * @param result Pointer to put the result in
     * @param v1     The first vector of this operation
     * @param v2     The second vector of this operation
     */
    public static void subtract(@Nonnull AtomicReference<Vector4> result, @Nonnull Vector4 v1, @Nonnull Vector4 v2) {
        result.set(v1.subtract(v2));
    }

    /**
     * Subtracts two quaternions.
     *
     * @param result Pointer to put the result in
     * @param v1     The first quaternion of this operation
     * @param v2     The second quaternion (or vector) of this operation
     */
    public static void subtract(@Nonnull AtomicReference<Quaternion> result, @Nonnull Quaternion v1, @Nonnull Vector4 v2) {
        result.set(v1.subtract(v2));
    }

    //
    // Vector Multiplication
    //

    /**
     * Calculates the dot product of two vectors.
     *
     * @param result Pointer to put the result in
     * @param v1     The first vector of this operation
     * @param v2     The second vector of this operation
     */
    public static void dot(@Nonnull AtomicReference<Double> result, @Nonnull Vector2 v1, @Nonnull Vector2 v2) {
        result.set(v1.dot(v2));
    }

    /**
     * Calculates the dot product of two vectors.
     *
     * @param result Pointer to put the result in
     * @param v1     The first vector of this operation
     * @param v2     The second vector of this operation
     */
    public static void dot(@Nonnull AtomicReference<Double> result, @Nonnull Vector3 v1, @Nonnull Vector3 v2) {
        result.set(v1.dot(v2));
    }

    /**
     * Calculates the dot product of two vectors.
     *
     * @param result Pointer to put the result in
     * @param v1     The first vector of this operation
     * @param v2     The second vector of this operation
     */
    public static void dot(@Nonnull AtomicReference<Double> result, @Nonnull Vector4 v1, @Nonnull Vector4 v2) {
        result.set(v1.dot(v2));
    }

    /**
     * Calculates the cross product of two vectors.
     *
     * @param result Pointer to put the result in
     * @param v1     The left vector of this operation
     * @param v2     The right vector of this operation
     */
    public static void cross(@Nonnull AtomicReference<Vector3> result, @Nonnull Vector3 v1, @Nonnull Vector3 v2) {
        result.set(v1.cross(v2));
    }

    /**
     * Calculates the cross product of two vertices.
     *
     * @param result Pointer to put the result in
     * @param v1     The left vertex of this operation
     * @param v2     The right vertex (or a vector) of this operation
     */
    public static void cross(@Nonnull AtomicReference<Vertex> result, @Nonnull Vertex v1, @Nonnull Vector3 v2) {
        result.set(v1.cross(v2));
    }

    /**
     * Calculates the product of two vectors.
     *
     * @param result Pointer to put the result in
     * @param v1     The left vector of this operation
     * @param v2     The right vector of this operation
     */
    public static void multiply(@Nonnull AtomicReference<Vector2> result, @Nonnull Vector2 v1, @Nonnull Vector2 v2) {
        result.set(v1.multiply(v2));
    }

    /**
     * Calculates the product of two positions.
     *
     * @param result Pointer to put the result in
     * @param v1     The left position of this operation
     * @param v2     The right position (or a vector) of this operation
     */
    public static void multiply(@Nonnull AtomicReference<Position> result, @Nonnull Position v1, @Nonnull Vector2 v2) {
        result.set(v1.multiply(v2));
    }

    /**
     * Calculates the product of two quaternions.
     *
     * @param result Pointer to put the result in
     * @param q1     The left quaternion of this operation
     * @param q2     The right quaternion of this operation
     */
    public static void multiply(@Nonnull AtomicReference<Quaternion> result, @Nonnull Quaternion q1, @Nonnull Quaternion q2) {
        result.set(q1.multiply(q2));
    }

    //
    // Bulk Operations
    //

    /**
     * Calculates the sum of the given vectors.
     *
     * @param result  Pointer to put the result in
     * @param vectors The vectors to sum
     */
    public static void sum(@Nonnull AtomicReference<Vector2> result, @Nonnull Vector2... vectors) {
        result.set(Vector2.sum(vectors));
    }

    /**
     * Calculates the sum of the given positions.
     *
     * @param result    Pointer to put the result in
     * @param positions The positions to sum
     */
    public static void sum(@Nonnull AtomicReference<Position> result, @Nonnull Position... positions) {
        result.set(Position.sum(positions));
    }

    /**
     * Calculates the sum of the given vectors.
     *
     * @param result  Pointer to put the result in
     * @param vectors The vectors to sum
     */
    public static void sum(@Nonnull AtomicReference<Vector3> result, @Nonnull Vector3... vectors) {
        result.set(Vector3.sum(vectors));
    }

    /**
     * Calculates the sum of the given vertices.
     *
     * @param result   Pointer to put the result in
     * @param vertices The vertices to sum
     */
    public static void sum(@Nonnull AtomicReference<Vertex> result, @Nonnull Vertex... vertices) {
        result.set(Vertex.sum(vertices));
    }

    /**
     * Calculates the sum of the given vectors.
     *
     * @param result  Pointer to put the result in
     * @param vectors The vectors to sum
     */
    public static void sum(@Nonnull AtomicReference<Vector4> result, @Nonnull Vector4... vectors) {
        result.set(Vector4.sum(vectors));
    }

    /**
     * Calculates the average of the given vectors.
     *
     * @param result  Pointer to put the result in
     * @param vectors The vectors to average
     */
    public static void avg(@Nonnull AtomicReference<Vector2> result, @Nonnull Vector2... vectors) {
        result.set(Vector2.avg(vectors));
    }

    /**
     * Calculates the average of the given positions.
     *
     * @param result    Pointer to put the result in
     * @param positions The positions to average
     */
    public static void avg(@Nonnull AtomicReference<Position> result, @Nonnull Position... positions) {
        result.set(Position.avg(positions));
    }

    /**
     * Calculates the average of the given vectors.
     *
     * @param result  Pointer to put the result in
     * @param vectors The vectors to average
     */
    public static void avg(@Nonnull AtomicReference<Vector3> result, @Nonnull Vector3... vectors) {
        result.set(Vector3.avg(vectors));
    }

    /**
     * Calculates the average of the given vertices.
     *
     * @param result   Pointer to put the result in
     * @param vertices The vertices to average
     */
    public static void avg(@Nonnull AtomicReference<Vertex> result, @Nonnull Vertex... vertices) {
        result.set(Vertex.avg(vertices));
    }

    /**
     * Calculates the average of the given vectors.
     *
     * @param result  Pointer to put the result in
     * @param vectors The vectors to average
     */
    public static void avg(@Nonnull AtomicReference<Vector4> result, @Nonnull Vector4... vectors) {
        result.set(Vector4.avg(vectors));
    }

    //
    // Apply Operation
    //

    /**
     * Applies given operator to every component of the given vector.
     *
     * @param result   Pointer to put the result in
     * @param v        Vector of the operation
     * @param operator Operator to apply
     */
    public static void apply(@Nonnull AtomicReference<Vector> result, @Nonnull Vector v, @Nonnull UnaryOperator<Double> operator) {
        result.set(v.apply(operator));
    }

    /**
     * Applies given operator to every component of the given vector.
     *
     * @param result   Pointer to put the result in
     * @param v        Vector of the operation
     * @param operator Operator to apply
     */
    public static void apply(@Nonnull AtomicReference<Vector2> result, @Nonnull Vector2 v, @Nonnull UnaryOperator<Double> operator) {
        result.set(v.apply(operator));
    }

    /**
     * Applies given operator to every component of the given position.
     *
     * @param result   Pointer to put the result in
     * @param p        Position of the operation
     * @param operator Operator to apply
     */
    public static void apply(@Nonnull AtomicReference<Position> result, @Nonnull Position p, @Nonnull UnaryOperator<Double> operator) {
        result.set(p.apply(operator));
    }

    /**
     * Applies given operator to every component of the given vector.
     *
     * @param result   Pointer to put the result in
     * @param v        Vector of the operation
     * @param operator Operator to apply
     */
    public static void apply(@Nonnull AtomicReference<Vector3> result, @Nonnull Vector3 v, @Nonnull UnaryOperator<Double> operator) {
        result.set(v.apply(operator));
    }

    /**
     * Applies given operator to every component of the given vertex.
     *
     * @param result   Pointer to put the result in
     * @param v        Vertex of the operation
     * @param operator Operator to apply
     */
    public static void apply(@Nonnull AtomicReference<Vertex> result, @Nonnull Vertex v, @Nonnull UnaryOperator<Double> operator) {
        result.set(v.apply(operator));
    }

    /**
     * Applies given operator to every component of the given vector.
     *
     * @param result   Pointer to put the result in
     * @param v        Vector of the operation
     * @param operator Operator to apply
     */
    public static void apply(@Nonnull AtomicReference<Vector4> result, @Nonnull Vector4 v, @Nonnull UnaryOperator<Double> operator) {
        result.set(v.apply(operator));
    }

    //
    // Negation
    //

    /**
     * Negates a vector.
     *
     * @param result Pointer to put the result in
     * @param v      Vector to negate
     */
    public static void negate(@Nonnull AtomicReference<Vector> result, @Nonnull Vector v) {
        result.set(v.negate());
    }

    /**
     * Negates a vector.
     *
     * @param result Pointer to put the result in
     * @param v      Vector to negate
     */
    public static void negate(@Nonnull AtomicReference<Vector2> result, @Nonnull Vector2 v) {
        result.set(v.negate());
    }

    /**
     * Negates a position.
     *
     * @param result Pointer to put the result in
     * @param p      Position to negate
     */
    public static void negate(@Nonnull AtomicReference<Position> result, @Nonnull Position p) {
        result.set(p.negate());
    }

    /**
     * Negates a vector.
     *
     * @param result Pointer to put the result in
     * @param v      Vector to negate
     */
    public static void negate(@Nonnull AtomicReference<Vector3> result, @Nonnull Vector3 v) {
        result.set(v.negate());
    }

    /**
     * Negates a vertex.
     *
     * @param result Pointer to put the result in
     * @param v      Vertex to negate
     */
    public static void negate(@Nonnull AtomicReference<Vertex> result, @Nonnull Vertex v) {
        result.set(v.negate());
    }

    /**
     * Negates a vector.
     *
     * @param result Pointer to put the result in
     * @param v      Vector to negate
     */
    public static void negate(@Nonnull AtomicReference<Vector4> result, @Nonnull Vector4 v) {
        result.set(v.negate());
    }

    //
    // Normalization
    //

    /**
     * Normalizes the given vector.
     *
     * @param result Pointer to put the result in
     * @param v      Vector to normalize
     */
    public static void normalize(@Nonnull AtomicReference<Vector> result, @Nonnull Vector v) {
        result.set(v.normalize());
    }

    /**
     * Normalizes the given vector.
     *
     * @param result Pointer to put the result in
     * @param v      Vector to normalize
     */
    public static void normalize(@Nonnull AtomicReference<Vector2> result, @Nonnull Vector2 v) {
        result.set(v.normalize());
    }

    /**
     * Normalizes the given position.
     *
     * @param result Pointer to put the result in
     * @param p      Position to normalize
     */
    public static void normalize(@Nonnull AtomicReference<Position> result, @Nonnull Position p) {
        result.set(p.normalize());
    }

    /**
     * Normalizes the given vector.
     *
     * @param result Pointer to put the result in
     * @param v      Vector to normalize
     */
    public static void normalize(@Nonnull AtomicReference<Vector3> result, @Nonnull Vector3 v) {
        result.set(v.normalize());
    }

    /**
     * Normalizes the given vertex.
     *
     * @param result Pointer to put the result in
     * @param v      Vertex to normalize
     */
    public static void normalize(@Nonnull AtomicReference<Vertex> result, @Nonnull Vertex v) {
        result.set(v.normalize());
    }

    /**
     * Normalizes the given vector.
     *
     * @param result Pointer to put the result in
     * @param v      Vector to normalize
     */
    public static void normalize(@Nonnull AtomicReference<Vector4> result, @Nonnull Vector4 v) {
        result.set(v.normalize());
    }

    //
    // Bounding Operations
    //

    /**
     * Gets the maximum vector between two vectors.
     *
     * @param result Pointer to put the result in
     * @param v1     The first vector
     * @param v2     The second vector
     */
    public static void max(@Nonnull AtomicReference<Vector2> result, @Nonnull Vector2 v1, @Nonnull Vector2 v2) {
        result.set(v1.max(v2));
    }

    /**
     * Gets the maximum position between two positions.
     *
     * @param result Pointer to put the result in
     * @param p1     The first position
     * @param p2     The second position
     */
    public static void max(@Nonnull AtomicReference<Position> result, @Nonnull Position p1, @Nonnull Vector2 p2) {
        result.set(p1.max(p2));
    }

    /**
     * Gets the maximum vector between two vectors.
     *
     * @param result Pointer to put the result in
     * @param v1     The first vector
     * @param v2     The second vector
     */
    public static void max(@Nonnull AtomicReference<Vector3> result, @Nonnull Vector3 v1, @Nonnull Vector3 v2) {
        result.set(v1.max(v2));
    }

    /**
     * Gets the maximum vertex between two vertices.
     *
     * @param result Pointer to put the result in
     * @param v1     The first vertex
     * @param v2     The second vertex
     */
    public static void max(@Nonnull AtomicReference<Vertex> result, @Nonnull Vertex v1, @Nonnull Vector3 v2) {
        result.set(v1.max(v2));
    }

    /**
     * Gets the maximum vector between two vectors.
     *
     * @param result Pointer to put the result in
     * @param v1     The first vector
     * @param v2     The second vector
     */
    public static void max(@Nonnull AtomicReference<Vector4> result, @Nonnull Vector4 v1, @Nonnull Vector4 v2) {
        result.set(v1.max(v2));
    }

    /**
     * Gets the minimum vector between two vectors.
     *
     * @param result Pointer to put the result in
     * @param v1     The first vector
     * @param v2     The second vector
     */
    public static void min(@Nonnull AtomicReference<Vector2> result, @Nonnull Vector2 v1, @Nonnull Vector2 v2) {
        result.set(v1.min(v2));
    }

    /**
     * Gets the minimum position between two positions.
     *
     * @param result Pointer to put the result in
     * @param p1     The first position
     * @param p2     The second position
     */
    public static void min(@Nonnull AtomicReference<Position> result, @Nonnull Position p1, @Nonnull Vector2 p2) {
        result.set(p1.min(p2));
    }

    /**
     * Gets the minimum vector between two vectors.
     *
     * @param result Pointer to put the result in
     * @param v1     The first vector
     * @param v2     The second vector
     */
    public static void min(@Nonnull AtomicReference<Vector3> result, @Nonnull Vector3 v1, @Nonnull Vector3 v2) {
        result.set(v1.min(v2));
    }

    /**
     * Gets the minimum vertex between two vertices.
     *
     * @param result Pointer to put the result in
     * @param v1     The first vertex
     * @param v2     The second vertex
     */
    public static void min(@Nonnull AtomicReference<Vertex> result, @Nonnull Vertex v1, @Nonnull Vector3 v2) {
        result.set(v1.min(v2));
    }

    /**
     * Gets the minimum vector between two vectors.
     *
     * @param result Pointer to put the result in
     * @param v1     The first vector
     * @param v2     The second vector
     */
    public static void min(@Nonnull AtomicReference<Vector4> result, @Nonnull Vector4 v1, @Nonnull Vector4 v2) {
        result.set(v1.min(v2));
    }

    /**
     * Given a minimum and maximum boundary, this clamps the vector to respect the boundaries.
     *
     * @param result Pointer to put the result in
     * @param v      The vector to clamp
     * @param min    The minimum boundary
     * @param max    The maximum boundary
     */
    public static void clamp(@Nonnull AtomicReference<Vector2> result, @Nonnull Vector2 v, @Nonnull Vector2 min, @Nonnull Vector2 max) {
        result.set(v.clamp(min, max));
    }

    /**
     * Given a minimum and maximum boundary, this clamps the position to respect the boundaries.
     *
     * @param result Pointer to put the result in
     * @param p      The position to clamp
     * @param min    The minimum boundary
     * @param max    The maximum boundary
     */
    public static void clamp(@Nonnull AtomicReference<Position> result, @Nonnull Position p, @Nonnull Vector2 min, @Nonnull Vector2 max) {
        result.set(p.clamp(min, max));
    }

    /**
     * Given a minimum and maximum boundary, this clamps the vector to respect the boundaries.
     *
     * @param result Pointer to put the result in
     * @param v      The vector to clamp
     * @param min    The minimum boundary
     * @param max    The maximum boundary
     */
    public static void clamp(@Nonnull AtomicReference<Vector3> result, @Nonnull Vector3 v, @Nonnull Vector3 min, @Nonnull Vector3 max) {
        result.set(v.clamp(min, max));
    }

    /**
     * Given a minimum and maximum boundary, this clamps the vertex to respect the boundaries.
     *
     * @param result Pointer to put the result in
     * @param v      The vertex to clamp
     * @param min    The minimum boundary
     * @param max    The maximum boundary
     */
    public static void clamp(@Nonnull AtomicReference<Vertex> result, @Nonnull Vertex v, @Nonnull Vector3 min, @Nonnull Vector3 max) {
        result.set(v.clamp(min, max));
    }

    /**
     * Given a minimum and maximum boundary, this clamps the vector to respect the boundaries.
     *
     * @param result Pointer to put the result in
     * @param v      The vector to clamp
     * @param min    The minimum boundary
     * @param max    The maximum boundary
     */
    public static void clamp(@Nonnull AtomicReference<Vector4> result, @Nonnull Vector4 v, @Nonnull Vector4 min, @Nonnull Vector4 max) {
        result.set(v.clamp(min, max));
    }

    //
    // Distance Operations
    //

    /**
     * Calculates the distance between two vectors.
     *
     * @param result Pointer to put the result in
     * @param v1     The first vector
     * @param v2     The second vector
     */
    public static void distance(@Nonnull AtomicReference<Double> result, @Nonnull Vector2 v1, @Nonnull Vector2 v2) {
        result.set(v1.distance(v2));
    }

    /**
     * Calculates the distance between two vectors.
     *
     * @param result Pointer to put the result in
     * @param v1     The first vector
     * @param v2     The second vector
     */
    public static void distance(@Nonnull AtomicReference<Double> result, @Nonnull Vector3 v1, @Nonnull Vector3 v2) {
        result.set(v1.distance(v2));
    }

    /**
     * Calculates the distance between two vectors.
     *
     * @param result Pointer to put the result in
     * @param v1     The first vector
     * @param v2     The second vector
     */
    public static void distance(@Nonnull AtomicReference<Double> result, @Nonnull Vector4 v1, @Nonnull Vector4 v2) {
        result.set(v1.distance(v2));
    }

    /**
     * Calculates the squared distance between two vectors.
     *
     * @param result Pointer to put the result in
     * @param v1     The first vector
     * @param v2     The second vector
     */
    public static void distance2(@Nonnull AtomicReference<Double> result, @Nonnull Vector2 v1, @Nonnull Vector2 v2) {
        result.set(v1.distance2(v2));
    }

    /**
     * Calculates the squared distance between two vectors.
     *
     * @param result Pointer to put the result in
     * @param v1     The first vector
     * @param v2     The second vector
     */
    public static void distance2(@Nonnull AtomicReference<Double> result, @Nonnull Vector3 v1, @Nonnull Vector3 v2) {
        result.set(v1.distance2(v2));
    }

    /**
     * Calculates the squared distance between two vectors.
     *
     * @param result Pointer to put the result in
     * @param v1     The first vector
     * @param v2     The second vector
     */
    public static void distance2(@Nonnull AtomicReference<Double> result, @Nonnull Vector4 v1, @Nonnull Vector4 v2) {
        result.set(v1.distance2(v2));
    }

    //
    // Rotation
    //

    /**
     * Rotates a vector counter-clockwise.
     *
     * @param result  Pointer to put the result in
     * @param v       The vector to rotate
     * @param angRads Angle of rotation in radians
     */
    public static void rotate(@Nonnull AtomicReference<Vector2> result, @Nonnull Vector2 v, double angRads) {
        result.set(v.rotate(angRads));
    }


    /**
     * Rotates a position counter-clockwise.
     *
     * @param result  Pointer to put the result in
     * @param p       The position to rotate
     * @param angRads Angle of rotation in radians
     */
    public static void rotate(@Nonnull AtomicReference<Position> result, @Nonnull Position p, double angRads) {
        result.set(p.rotate(angRads));
    }

    /**
     * Rotates a vector.
     *
     * @param result Pointer to put the result in
     * @param v      The vector to rotate
     * @param rq     The rotation quaternion to apply
     */
    public static void rotate(@Nonnull AtomicReference<Vector3> result, @Nonnull Vector3 v, @Nonnull Quaternion rq) {
        result.set(v.rotate(rq));
    }

    /**
     * Rotates a vertex.
     *
     * @param result Pointer to put the result in
     * @param v      The vertex to rotate
     * @param rq     The rotation quaternion to apply
     */
    public static void rotate(@Nonnull AtomicReference<Vertex> result, @Nonnull Vertex v, @Nonnull Quaternion rq) {
        result.set(v.rotate(rq));
    }

    //
    // Vertex Operations
    //

    /**
     * Given three vertices of a surface, this calculates the normal vertex of the surface.
     *
     * @param result Pointer to put the result in
     * @param a      The first vertex
     * @param b      The second vertex
     * @param c      The third vertex
     * @see Vertex#normal(Vertex, Vertex, Vertex)
     */
    public static void normal(@Nonnull AtomicReference<Vertex> result, @Nonnull Vertex a, @Nonnull Vertex b, @Nonnull Vertex c) {
        result.set(Vertex.normal(a, b, c));
    }

    /**
     * Converts a vertex into a 2D position using the provided focal length.
     *
     * @param result      Pointer to put the result in
     * @param v           The vertex to calculate the point from
     * @param focalLength Focal length to use in conversion
     */
    public static void position(@Nonnull AtomicReference<Position> result, @Nonnull Vertex v, double focalLength) {
        result.set(v.position(focalLength));
    }

    //
    // Strict Equality
    //

    /**
     * Checks for exact equality between two vectors.
     *
     * @param result Pointer to put the result in
     * @param v1     The first vector
     * @param v2     The second vector
     */
    public static void strictEquals(@Nonnull AtomicBoolean result, @Nonnull Vector v1, @Nonnull Vector v2) {
        result.set(v1.equals(v2));
    }

    /**
     * Checks for loose equality between two vectors.
     *
     * @param result Pointer to put the result in
     * @param v1     The first vector
     * @param v2     The second vector
     * @see Numbers#equals(Vector, Vector)
     */
    public static void looseEquals(@Nonnull AtomicBoolean result, @Nonnull Vector v1, @Nonnull Vector v2) {
        result.set(Numbers.equals(v1, v2));
    }

    /**
     * Checks for loose equality between two vectors.
     *
     * @param result Pointer to put the result in
     * @param v1     The first vector
     * @param v2     The second vector
     * @see Numbers#equals(Vector2, Vector2)
     */
    public static void looseEquals(@Nonnull AtomicBoolean result, @Nonnull Vector2 v1, @Nonnull Vector2 v2) {
        result.set(Numbers.equals(v1, v2));
    }

    /**
     * Checks for loose equality between two vectors.
     *
     * @param result Pointer to put the result in
     * @param v1     The first vector
     * @param v2     The second vector
     * @see Numbers#equals(Vector3, Vector3)
     */
    public static void looseEquals(@Nonnull AtomicBoolean result, @Nonnull Vector3 v1, @Nonnull Vector3 v2) {
        result.set(Numbers.equals(v1, v2));
    }

    /**
     * Checks for loose equality between two vectors.
     *
     * @param result Pointer to put the result in
     * @param v1     The first vector
     * @param v2     The second vector
     * @see Numbers#equals(Vector4, Vector4)
     */
    public static void looseEquals(@Nonnull AtomicBoolean result, @Nonnull Vector4 v1, @Nonnull Vector4 v2) {
        result.set(Numbers.equals(v1, v2));
    }
}
