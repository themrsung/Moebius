package civitas.celestis.math;

import jakarta.annotation.Nonnull;

/**
 * The sign of a number.
 */
public enum Sign {
    /**
     * When the number is a negative floating point number divided by zero.
     */
    NEGATIVE_INFINITY,

    /**
     * When the number is a finite negative number.
     */
    NEGATIVE,

    /**
     * When the number is zero.
     */
    ZERO,

    /**
     * When the number is a floating point number which is not a number
     */
    NOT_A_NUMBER,

    /**
     * When the number is a finite positive number.
     */
    POSITIVE,

    /**
     * When the number is a positive floating point number divided by zero.
     */
    POSITIVE_INFINITY;

    /**
     * Checks if this sign is finite.
     *
     * @return {@code true} if this sign is finite
     */
    public boolean finite() {
        return switch (this) {
            case POSITIVE, ZERO, NEGATIVE -> true;
            default -> false;
        };
    }

    /**
     * Checks if this sign is {@link Sign#NOT_A_NUMBER}.
     *
     * @return {@code true} if {@code this == Sign.NOT_A_NUMBER}
     */
    public boolean nan() {
        return this == NOT_A_NUMBER;
    }

    /**
     * Returns the sign of the resulting number when multiplied together.
     *
     * @param other The other number's sign
     * @return The resulting number's sign
     */
    @Nonnull
    public Sign multiply(@Nonnull Sign other) {
        return switch (this) {
            case POSITIVE_INFINITY -> POSITIVE_INFINITY;

            case POSITIVE -> switch (other) {
                case POSITIVE_INFINITY -> POSITIVE_INFINITY;
                case POSITIVE -> POSITIVE;
                case ZERO -> ZERO;
                case NEGATIVE -> NEGATIVE;
                case NEGATIVE_INFINITY -> NEGATIVE_INFINITY;
                case NOT_A_NUMBER -> NOT_A_NUMBER;
            };

            case ZERO -> switch (other) {
                case POSITIVE_INFINITY -> POSITIVE_INFINITY;
                case NEGATIVE_INFINITY -> NEGATIVE_INFINITY;
                case NOT_A_NUMBER -> NOT_A_NUMBER;
                default -> ZERO;
            };

            case NEGATIVE -> switch (other) {
                case POSITIVE_INFINITY -> NEGATIVE_INFINITY;
                case POSITIVE -> NEGATIVE;
                case ZERO -> ZERO;
                case NEGATIVE -> POSITIVE;
                case NEGATIVE_INFINITY -> POSITIVE_INFINITY;
                case NOT_A_NUMBER -> NOT_A_NUMBER;
            };

            case NEGATIVE_INFINITY -> NEGATIVE_INFINITY;

            case NOT_A_NUMBER -> NOT_A_NUMBER;
        };
    }
}
