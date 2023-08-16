package civitas.celestis.math.matrix;

import civitas.celestis.util.group.Grid;
import jakarta.annotation.Nonnull;

/**
 * A grid of {@link Number}s.
 *
 * @param <N> The type of number to contain
 * @param <M> Itself (the parameter and return value of certain operations)
 */
public interface Matrix<N extends Number, M extends Matrix<N, M>> extends Grid<N> {
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
    M add(@Nonnull M m) throws ArithmeticException;

    /**
     * Subtracts another matrix from this matrix.
     *
     * @param m The matrix to subtract from this matrix
     * @return The resulting matrix
     * @throws ArithmeticException When the dimensions are different
     */
    @Nonnull
    M subtract(@Nonnull M m) throws ArithmeticException;

    /**
     * Multiplies this matrix by another matrix.
     *
     * @param m The matrix to multiply this matrix by
     * @return The resulting matrix
     * @throws ArithmeticException When the dimensions are incompatible
     */
    @Nonnull
    M multiply(@Nonnull M m) throws ArithmeticException;

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
