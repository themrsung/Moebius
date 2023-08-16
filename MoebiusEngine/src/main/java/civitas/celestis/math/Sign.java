package civitas.celestis.math;

import civitas.celestis.util.data.Packable8;
import jakarta.annotation.Nonnull;

/**
 * The sign of a number.
 */
public enum Sign implements Packable8 {
    //
    // The order here matters; Do not change it.
    //
    // Sorting by this enum should give you a quasi-accurate representation of the dataset's order.
    //

    /**
     * When the value is either {@link Double#POSITIVE_INFINITY} ot {@link Float#POSITIVE_INFINITY}.
     */
    NEGATIVE_INFINITY,

    /**
     * When the value is below zero.
     */
    NEGATIVE,

    /**
     * When the value is zero.
     */
    ZERO,

    /**
     * When the value is above zero.
     */
    POSITIVE,

    /**
     * When the value is either {@link Double#POSITIVE_INFINITY} or {@link Float#POSITIVE_INFINITY}.
     */
    POSITIVE_INFINITY,

    /**
     * When the value is either {@link Double#NaN} or {@link Float#NaN}.
     */
    NaN;

    //
    // Factory
    //

    /**
     * Returns the sign of the provided value {@code v}.
     *
     * @param v The value of which to get the sign of
     * @return The sign of the value {@code v}
     */
    @Nonnull
    public static Sign of(double v) {
        if (Double.isNaN(v)) return NaN;
        if (v == Double.POSITIVE_INFINITY) return POSITIVE_INFINITY;
        if (v == Double.NEGATIVE_INFINITY) return NEGATIVE_INFINITY;

        return v == 0 ? ZERO : (v > 0 ? POSITIVE : NEGATIVE);
    }

    /**
     * Returns the sign of the provided value {@code v}.
     *
     * @param v The value of which to get the sign of
     * @return The sign of the value {@code v}
     */
    @Nonnull
    public static Sign of(float v) {
        if (Float.isNaN(v)) return NaN;
        if (v == Float.POSITIVE_INFINITY) return POSITIVE_INFINITY;
        if (v == Float.NEGATIVE_INFINITY) return NEGATIVE_INFINITY;

        return v == 0 ? ZERO : (v > 0 ? POSITIVE : NEGATIVE);
    }

    /**
     * Returns the sign of the provided value {@code v}.
     *
     * @param v The value of which to get the sign of
     * @return The sign of the value {@code v}
     */
    @Nonnull
    public static Sign of(long v) {
        return v == 0 ? ZERO : (v > 0 ? POSITIVE : NEGATIVE);
    }

    /**
     * Returns the sign of the provided value {@code v}.
     *
     * @param v The value of which to get the sign of
     * @return The sign of the value {@code v}
     */
    @Nonnull
    public static Sign of(int v) {
        return v == 0 ? ZERO : (v > 0 ? POSITIVE : NEGATIVE);
    }

    /**
     * Returns the sign of the provided value {@code v}.
     *
     * @param v The value of which to get the sign of
     * @return The sign of the value {@code v}
     */
    @Nonnull
    public static Sign of(short v) {
        return v == 0 ? ZERO : (v > 0 ? POSITIVE : NEGATIVE);
    }

    /**
     * Returns the sign of the provided value {@code v}.
     *
     * @param v The value of which to get the sign of
     * @return The sign of the value {@code v}
     */
    @Nonnull
    public static Sign of(byte v) {
        return v == 0 ? ZERO : (v > 0 ? POSITIVE : NEGATIVE);
    }

    //
    // Properties
    //

    /**
     * Returns whether this sign is not a number.
     *
     * @return {@code true} if this sign is {@link Sign#NaN}
     */
    public boolean isNaN() {
        return this == NaN;
    }

    /**
     * Returns whether this sign is finite.
     *
     * @return {@code true} if this sign is finite
     */
    public boolean isFinite() {
        return this != POSITIVE_INFINITY && this != NEGATIVE_INFINITY && this != NaN;
    }

    /**
     * Returns whether this sign is infinite.
     * Note that this returns {@code false} for {@link Sign#NaN}.
     *
     * @return {@code true} if this sign is infinite
     */
    public boolean isInfinite() {
        return this == POSITIVE_INFINITY || this == NEGATIVE_INFINITY;
    }

    //
    // Packing
    //

    /**
     * {@inheritDoc}
     * <p>
     * When the data is invalid, this will fallback to {@link Sign#NaN} instead
     * of throwing an exception.
     * </p>
     *
     * @param sign8 The packed 8-bit representation of this sign
     * @return The unpacked sign
     */
    @Nonnull
    public static Sign unpack8(byte sign8) {
        try {
            return values()[sign8];
        } catch (final IndexOutOfBoundsException e) {
            return NaN;
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * This simply casts the constant ordinal (obtainable by {@link Sign#ordinal()}) to a {@code byte}.
     * </p>
     *
     * @return {@inheritDoc}
     */
    @Override
    public byte pack8() {
        return (byte) ordinal();
    }
}
