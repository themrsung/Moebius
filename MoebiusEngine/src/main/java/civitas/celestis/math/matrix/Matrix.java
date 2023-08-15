package civitas.celestis.math.matrix;

import civitas.celestis.math.Numeric;
import civitas.celestis.util.grid.Grid;
import jakarta.annotation.Nonnull;

import java.io.Serializable;

/**
 * A two-dimensional array of numbers.
 * <p>
 * Since both {@code double} and {@code long} have 64 bits,
 * this class can be used to contain two-dimensional data of up to 64 bits per element.
 * </p>
 *
 * @param <N> The type of number this matrix contains
 * @param <M> This matrix (the result of arithmetic operations)
 */
public interface Matrix<N extends Number, M extends Matrix<N, M>> extends Numeric, Grid<N, M>, Iterable<N>, Serializable {
    //
    // Arithmetic
    //

    /**
     * Adds another matrix to this matrix.
     *
     * @param m The matrix to add to this matrix
     * @return The resulting matrix
     * @throws ArithmeticException When the dimensions are different
     */
    @Nonnull
    M add(@Nonnull M m);

    /**
     * Subtracts another matrix from this matrix.
     *
     * @param m The matrix to subtract from this matrix
     * @return The resulting matrix
     * @throws ArithmeticException When the dimensions are different
     */
    @Nonnull
    M subtract(@Nonnull M m);

    /**
     * Multiplies this matrix by another matrix.
     *
     * @param m The matrix to multiply this matrix by
     * @return The resulting matrix
     * @throws ArithmeticException When the dimensions are different
     */
    @Nonnull
    M multiply(@Nonnull M m);

    //
    // Negation
    //

    /**
     * Negates this matrix, then returns the negated matrix.
     *
     * @return The negation of this matrix
     */
    @Nonnull
    M negate();
}
