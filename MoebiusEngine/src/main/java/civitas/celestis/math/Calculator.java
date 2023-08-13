package civitas.celestis.math;

import civitas.celestis.math.fraction.Fraction;
import jakarta.annotation.Nonnull;

import java.util.function.UnaryOperator;

/**
 * A mathematically accurate calculator.
 */
public final class Calculator {
    //
    // Arithmetic
    //

    /**
     * Adds two values.
     *
     * @param v1 The first value
     * @param v2 The second values
     * @return The sum of the values
     */
    public static double add(double v1, double v2) {
        return v1 + v2;
    }

    /**
     * Subtracts {@code v2} from {@code v1}.
     *
     * @param v1 The first value
     * @param v2 The second value
     * @return THe difference of the two values
     */
    public static double subtract(double v1, double v2) {
        return v1 - v2;
    }

    /**
     * Multiplies two values.
     *
     * @param v1 The first value
     * @param v2 The second value
     * @return The product of the two values
     */
    public static double multiply(double v1, double v2) {
        return v1 * v2;
    }

    /**
     * Divides {@code v1} by {@code v2}.
     *
     * @param v1 The first value
     * @param v2 The second value
     * @return The fractional representation of the result
     */
    @Nonnull
    public static Fraction divide(double v1, double v2) {
        return new Fraction(v1, v2);
    }

    //
    // Derivatives
    //

    /**
     * Returns the derivative function of the provided function {@code f}.
     * Note that this is an approximation of the derivative.
     * This method uses {@link Numbers#EPSILON} as the scalar parameter.
     *
     * @param f The function to get the derivative of
     * @return The derivative function
     */
    @Nonnull
    public static UnaryOperator<Double> derivative(@Nonnull UnaryOperator<Double> f) {
        return derivative(f, Numbers.EPSILON);
    }

    /**
     * Returns the derivative function of the provided function {@code f}.
     * Note that this is an approximation of the derivative.
     *
     * @param f The function to get the derivative of
     * @param h A small value of which to calculate the derivative by
     * @return The derivative function
     */
    @Nonnull
    public static UnaryOperator<Double> derivative(@Nonnull UnaryOperator<Double> f, double h) {
        return x -> (f.apply(x + h) - f.apply(x)) / h;
    }

    //
    // Newton's method
    //

    /**
     * Given a function {@code f}, this returns the root of the function which is closest to the initial guess
     * using the Newton's method to estimate its root.
     * <p><b>
     * Note that this method has no limitations in the number of recursions, and in certain cases
     * can end up in an infinite loop.
     * </b></p>
     *
     * @param f            The function to solve
     * @param initialGuess The initial guess
     * @return The root of the function {@code f} closest to the initial guess
     */
    public static double root(@Nonnull UnaryOperator<Double> f, double initialGuess) {
        // Get the derivative
        final UnaryOperator<Double> d = derivative(f);

        // Declare a local variable to use
        double x = initialGuess;

        // Infinitely apply Newton's method
        while (!Numbers.equals(f.apply(x), 0)) {
            x = x - f.apply(x) / d.apply(x);
        }

        return x;
    }

    /**
     * Given a function {@code f}, this returns the root of the function which is closest to the initial guess
     * using the Newton's method to estimate its root.
     *
     * @param f            The function to solve
     * @param initialGuess The initial guess
     * @param limit        The maximum number of iterations allowed
     * @return The root of the function {@code f} closest to the initial guess
     */
    public static double root(@Nonnull UnaryOperator<Double> f, double initialGuess, int limit) {
        // Get the derivative
        final UnaryOperator<Double> d = derivative(f);

        // Declare a local variable to use
        double x = initialGuess;

        // The number of remaining iterations
        int i = limit;

        // Apply Newton's method for min(necessary, limit) times
        while (!Numbers.equals(f.apply(x), 0) && i > 0) {
            x = x - f.apply(x) / d.apply(x);
            i--;
        }

        return x;
    }
}
