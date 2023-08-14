package civitas.celestis.math;

import jakarta.annotation.Nonnull;

/**
 * The sign of a number.
 */
public enum Sign {
    //
    // Enum Constants
    //

    /**
     * When the number is a negative floating point number divided by zero.
     */
    NEGATIVE_INFINITY(
            Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY,
            Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY,
            Long.MAX_VALUE, Long.MIN_VALUE, Integer.MAX_VALUE,
            Integer.MIN_VALUE, Short.MAX_VALUE, Short.MIN_VALUE,
            Byte.MAX_VALUE, Byte.MIN_VALUE
    ),

    /**
     * When the number is a finite negative number.
     */
    NEGATIVE(
            -Double.MAX_VALUE, -Double.MIN_VALUE,
            -Float.MAX_VALUE, -Float.MIN_VALUE,
            Long.MIN_VALUE, -1,
            Integer.MIN_VALUE, -1,
            Short.MIN_VALUE, (short) -1,
            Byte.MIN_VALUE, (byte) -1
    ),

    /**
     * When the number is zero.
     */
    ZERO(
            0d, 0d,
            0f, 0f,
            0L, 0L,
            0, 0,
            (short) 0, (short) 0,
            (byte) 0, (byte) 0
    ),

    /**
     * When the number is a floating point number which is not a number
     */
    NOT_A_NUMBER(
            Double.NaN, Double.NaN,
            Float.NaN, Float.NaN,
            Long.MAX_VALUE, Long.MIN_VALUE,
            Integer.MAX_VALUE, Integer.MIN_VALUE,
            Short.MAX_VALUE, Short.MIN_VALUE,
            Byte.MAX_VALUE, Byte.MIN_VALUE
    ),

    /**
     * When the number is a finite positive number.
     */
    POSITIVE(
            Double.MIN_VALUE, Double.MAX_VALUE,
            Float.MIN_VALUE, Float.MAX_VALUE,
            1, Long.MAX_VALUE,
            1, Integer.MAX_VALUE,
            (short) 1, Short.MAX_VALUE,
            (byte) 1, Byte.MAX_VALUE
    ),

    /**
     * When the number is a positive floating point number divided by zero.
     */
    POSITIVE_INFINITY(
            Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY,
            Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY,
            Long.MAX_VALUE, Long.MIN_VALUE,
            Integer.MAX_VALUE, Integer.MIN_VALUE,
            Short.MAX_VALUE, Short.MIN_VALUE,
            Byte.MAX_VALUE, Byte.MIN_VALUE
    );

    //
    // Factory
    //
    // These methods use a different algorithm from Numbers#sign(double),
    // and may be slower or faster depending on the application.
    //
    // Benchmark the two algorithms with your expected range of numbers before choosing
    // one for the final release version of your application.
    //

    /**
     * Returns the sign of the given value.
     *
     * @param v The value of which to get the sign of
     * @return The sign of {@code v}
     */
    @Nonnull
    public static Sign of(double v) {
        for (final Sign value : values()) {
            if (value.isInRange(v)) return value;
        }

        throw new UnknownError("Failed to get the sign of number: " + v);
    }

    /**
     * Returns the sign of the given value.
     *
     * @param v The value of which to get the sign of
     * @return The sign of {@code v}
     */
    @Nonnull
    public static Sign of(float v) {
        for (final Sign value : values()) {
            if (value.isInRange(v)) return value;
        }

        throw new UnknownError("Failed to get the sign of number: " + v);
    }

    /**
     * Returns the sign of the given value.
     *
     * @param v The value of which to get the sign of
     * @return The sign of {@code v}
     */
    @Nonnull
    public static Sign of(long v) {
        for (final Sign value : values()) {
            if (value.isInRange(v)) return value;
        }

        throw new UnknownError("Failed to get the sign of number: " + v);
    }

    /**
     * Returns the sign of the given value.
     *
     * @param v The value of which to get the sign of
     * @return The sign of {@code v}
     */
    @Nonnull
    public static Sign of(int v) {
        for (final Sign value : values()) {
            if (value.isInRange(v)) return value;
        }

        throw new UnknownError("Failed to get the sign of number: " + v);
    }

    /**
     * Returns the sign of the given value.
     *
     * @param v The value of which to get the sign of
     * @return The sign of {@code v}
     */
    @Nonnull
    public static Sign of(short v) {
        for (final Sign value : values()) {
            if (value.isInRange(v)) return value;
        }

        throw new UnknownError("Failed to get the sign of number: " + v);
    }

    /**
     * Returns the sign of the given value.
     *
     * @param v The value of which to get the sign of
     * @return The sign of {@code v}
     */
    @Nonnull
    public static Sign of(byte v) {
        for (final Sign value : values()) {
            if (value.isInRange(v)) return value;
        }

        throw new UnknownError("Failed to get the sign of number: " + v);
    }

    //
    // Constructors
    //

    /**
     * Creates a new sign.
     *
     * @param minDouble The minimum double
     * @param maxDouble The maximum double
     * @param minFloat  The minimum float
     * @param maxFloat  The maximum float
     * @param minLong   The minimum long
     * @param maxLong   The maximum long
     * @param minInt    The minimum int
     * @param maxInt    The maximum int
     * @param minShort  The minimum short
     * @param maxShort  The maximum short
     * @param minByte   The minimum byte
     * @param maxByte   The maximum byte
     */
    Sign(
            double minDouble,
            double maxDouble,
            float minFloat,
            float maxFloat,
            long minLong,
            long maxLong,
            int minInt,
            int maxInt,
            short minShort,
            short maxShort,
            byte minByte,
            byte maxByte
    ) {
        this.minDouble = minDouble;
        this.maxDouble = maxDouble;
        this.minFloat = minFloat;
        this.maxFloat = maxFloat;
        this.minLong = minLong;
        this.maxLong = maxLong;
        this.minInt = minInt;
        this.maxInt = maxInt;
        this.minShort = minShort;
        this.maxShort = maxShort;
        this.minByte = minByte;
        this.maxByte = maxByte;
    }

    //
    // Variables
    //

    private final double minDouble, maxDouble;
    private final float minFloat, maxFloat;
    private final long minLong, maxLong;
    private final int minInt, maxInt;
    private final short minShort, maxShort;
    private final byte minByte, maxByte;

    //
    // Methods
    //

    /**
     * Checks if given number has this sign.
     *
     * @param d The number to check
     * @return {@code true} if the number's sign is equal to {@code this}
     */
    public boolean isInRange(double d) {
        return Numbers.isInRange(d, minDouble, maxDouble);
    }

    /**
     * Checks if given number has this sign.
     *
     * @param f The number to check
     * @return {@code true} if the number's sign is equal to {@code this}
     */
    public boolean isInRange(float f) {
        return Numbers.isInRange(f, minFloat, maxFloat);
    }

    /**
     * Checks if given number has this sign.
     *
     * @param l The number to check
     * @return {@code true} if the number's sign is equal to {@code this}
     */
    public boolean isInRange(long l) {
        return Numbers.isInRange(l, minLong, maxLong);
    }

    /**
     * Checks if given number has this sign.
     *
     * @param i The number to check
     * @return {@code true} if the number's sign is equal to {@code this}
     */
    public boolean isInRange(int i) {
        return Numbers.isInRange(i, minInt, maxInt);
    }

    /**
     * Checks if given number has this sign.
     *
     * @param s The number to check
     * @return {@code true} if the number's sign is equal to {@code this}
     */
    public boolean isInRange(short s) {
        return Numbers.isInRange(s, minShort, maxShort);
    }

    /**
     * Checks if given number has this sign.
     *
     * @param b The number to check
     * @return {@code true} if the number's sign is equal to {@code this}
     */
    public boolean isInRange(byte b) {
        return Numbers.isInRange(b, minByte, maxByte);
    }

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
