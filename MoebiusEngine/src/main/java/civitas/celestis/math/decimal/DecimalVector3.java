package civitas.celestis.math.decimal;

import jakarta.annotation.Nonnull;

import java.math.BigDecimal;

/**
 * An immutable three-dimensional {@link DecimalVector}.
 */
public /* unfinished */ abstract class DecimalVector3 implements DecimalVector {
    //
    // Constructors
    //

    /**
     * Creates a new vector.
     * @param x The X component of this vector
     * @param y The Y component of this vector
     * @param z The Z component of this vector
     */
    public DecimalVector3(@Nonnull BigDecimal x, @Nonnull BigDecimal y, @Nonnull BigDecimal z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    //
    // Variables
    //
    @Nonnull
    private final BigDecimal x, y, z;

    //
    // Getters
    //

    @Nonnull
    public final BigDecimal x() {
        return x;
    }

    @Nonnull
    public final BigDecimal y() {
        return y;
    }

    @Nonnull
    public final BigDecimal z() {
        return z;
    }
}
