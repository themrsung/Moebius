package civitas.celestis.util.group;

import civitas.celestis.math.matrix.DoubleMatrix;
import civitas.celestis.math.matrix.FloatMatrix;
import civitas.celestis.math.matrix.IntMatrix;
import civitas.celestis.math.matrix.LongMatrix;
import civitas.celestis.math.vector.*;
import civitas.celestis.util.tuple.*;
import jakarta.annotation.Nonnull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * A group of elements.
 * <p>
 * While most groups are shallowly immutable, there are still mutable instances.
 * For example, {@link Grid}s are mutable by design since assigning every value
 * in the constructor of a large grid would be impractical and hard to use.
 * </p>
 *
 * @param <E> The type of element to contain in this group
 * @see Tuple
 * @see Pair
 * @see Triple
 * @see Quad
 * @see ArrayTuple
 * @see Vector
 * @see DoubleVector
 * @see FloatVector
 * @see LongVector
 * @see IntVector
 * @see Grid
 * @see civitas.celestis.math.matrix.Matrix Matrix
 */
public interface Group<E> extends Serializable {
    //
    // Factory
    //

    /**
     * Creates a group of objects.
     *
     * @param values The values to contain
     * @param <E>    The type of element to contain
     * @return The constructed group
     */
    @SafeVarargs
    @Nonnull
    static <E> Group<E> of(@Nonnull E... values) {
        return switch (values.length) {
            case 2 -> new Pair<>(values[0], values[1]);
            case 3 -> new Triple<>(values[0], values[1], values[2]);
            case 4 -> new Quad<>(values[0], values[1], values[2], values[3]);
            default -> new ArrayTuple<>(values);
        };
    }

    /**
     * Creates a new group of {@link BigDecimal}s.
     *
     * @param values The values to contain
     * @return The constructed group
     */
    @Nonnull
    static Group<BigDecimal> ofBigDecimal(@Nonnull BigDecimal... values) {
        return switch (values.length) {
            case 2 -> new Decimal2(values);
            case 3 -> new Decimal3(values);
            case 4 -> new Decimal4(values);
            default -> new ArrayTuple<>(values);
        };
    }

    /**
     * Creates a new group of {@link BigInteger}s.
     *
     * @param values The values to contain
     * @return The constructed group
     */
    @Nonnull
    static Group<BigInteger> ofBigDecimal(@Nonnull BigInteger... values) {
        return switch (values.length) {
            case 2 -> new Integer2(values);
            case 3 -> new Integer3(values);
            case 4 -> new Integer4(values);
            default -> new ArrayTuple<>(values);
        };
    }

    /**
     * Creates a new group of {@code double}s.
     *
     * @param values The values to contain
     * @return The constructed group
     */
    @Nonnull
    static Group<Double> ofDouble(@Nonnull double... values) {
        return switch (values.length) {
            case 2 -> new Double2(values);
            case 3 -> new Double3(values);
            case 4 -> new Double4(values);
            default -> new ArrayTuple<>(Arrays.stream(values).boxed().toArray(Double[]::new));
        };
    }

    /**
     * Creates a new group of {@code float}s.
     *
     * @param values The values to contain
     * @return The constructed group
     */
    @Nonnull
    static Group<Float> ofFloat(@Nonnull float... values) {
        return switch (values.length) {
            case 2 -> new Float2(values);
            case 3 -> new Float3(values);
            case 4 -> new Float4(values);
            default -> {
                final Float[] boxed = new Float[values.length];

                for (int i = 0; i < values.length; i++) {
                    boxed[i] = values[i];
                }

                yield new ArrayTuple<>(boxed);
            }
        };
    }

    /**
     * Creates a new group of {@code long}s.
     *
     * @param values The values to contain
     * @return The constructed group
     */
    @Nonnull
    static Group<Long> ofLong(@Nonnull long... values) {
        return switch (values.length) {
            case 2 -> new Long2(values);
            case 3 -> new Long3(values);
            case 4 -> new Long4(values);
            default -> new ArrayTuple<>(Arrays.stream(values).boxed().toArray(Long[]::new));
        };
    }

    /**
     * Creates a new group of {@code int}s.
     *
     * @param values The values to contain
     * @return The constructed group
     */
    @Nonnull
    static Group<Integer> ofInt(@Nonnull int... values) {
        return switch (values.length) {
            case 2 -> new Int2(values);
            case 3 -> new Int3(values);
            case 4 -> new Int4(values);
            default -> new ArrayTuple<>(Arrays.stream(values).boxed().toArray(Integer[]::new));
        };
    }

    /**
     * Creates a new group of objects.
     *
     * @param values The values to contain
     * @param <E>    The type of element to contain
     * @return The constructed group
     */
    @Nonnull
    static <E> Group<E> of(@Nonnull E[][] values) {
        return new ArrayGrid<>(values);
    }

    /**
     * Creates a new group of {@code double}s.
     *
     * @param values The values to contain
     * @return The constructed group
     */
    @Nonnull
    static Group<Double> of(@Nonnull double[][] values) {
        return new DoubleMatrix(values);
    }

    /**
     * Creates a new group of {@code float}s.
     *
     * @param values The values to contain
     * @return The constructed group
     */
    @Nonnull
    static Group<Float> of(@Nonnull float[][] values) {
        return new FloatMatrix(values);
    }

    /**
     * Creates a new group of {@code long}s.
     *
     * @param values The values to contain
     * @return The constructed group
     */
    @Nonnull
    static Group<Long> of(@Nonnull long[][] values) {
        return new LongMatrix(values);
    }

    /**
     * Creates a new group of {@code int}s.
     *
     * @param values The values to contain
     * @return The constructed group
     */
    @Nonnull
    static Group<Integer> of(@Nonnull int[][] values) {
        return new IntMatrix(values);
    }

    //
    // Copy
    //

    /**
     * Creates a new group by copying a collection's values.
     *
     * @param c   The collection of which to copy values from
     * @param <E> The type of element to contain
     * @return The copied group
     */
    @Nonnull
    static <E> Group<E> copyOf(@Nonnull Collection<E> c) {
        return switch (c.size()) {
            case 2 -> {
                final List<E> l = List.copyOf(c);
                yield new Pair<>(l.get(0), l.get(1));
            }

            case 3 -> {
                final List<E> l = List.copyOf(c);
                yield new Triple<>(l.get(0), l.get(1), l.get(2));
            }

            case 4 -> {
                final List<E> l = List.copyOf(c);
                yield new Quad<>(l.get(0), l.get(1), l.get(2), l.get(3));
            }

            default -> new ArrayTuple<>(c);
        };
    }

    /**
     * Creates a new group by copying an existing group.
     * Note that copying immutable groups is meaningless, and only takes up more memory.
     *
     * @param g   The group of which to copy values from
     * @param <E> The type of element to contain
     * @return The copied group
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    static <E> Group<E> copyOf(@Nonnull Group<E> g) {
        if (g instanceof Grid<E> grid) return new ArrayGrid<>(grid);

        if (g instanceof DecimalVector<?> dv) {
            try {return (Group<E>) new Decimal4(dv);} catch (final Exception ignored) {}
            try {return (Group<E>) new Decimal3(dv);} catch (final Exception ignored) {}
            try {return (Group<E>) new Decimal2(dv);} catch (final Exception ignored) {}
        }

        if (g instanceof IntegerVector<?> iv) {
            try {return (Group<E>) new Integer4(iv);} catch (final Exception ignored) {}
            try {return (Group<E>) new Integer3(iv);} catch (final Exception ignored) {}
            try {return (Group<E>) new Integer2(iv);} catch (final Exception ignored) {}
        }

        // Primitive vector constructors must be called in this specific order to prevent
        // inter-type copying and to prevent loss of precision.

        // 1. new Double3(Float3) will be harmless, while the other way around would lose precision.
        // 2. new Double2(Double4) will work, while new Double4(Double2) won't.

        if (g instanceof DoubleVector<?> dv) {
            try {return (Group<E>) new Double4(dv);} catch (final Exception ignored) {}
            try {return (Group<E>) new Double3(dv);} catch (final Exception ignored) {}
            try {return (Group<E>) new Double2(dv);} catch (final Exception ignored) {}
        }

        if (g instanceof FloatVector<?> fv) {
            try {return (Group<E>) new Float4(fv);} catch (final Exception ignored) {}
            try {return (Group<E>) new Float3(fv);} catch (final Exception ignored) {}
            try {return (Group<E>) new Float2(fv);} catch (final Exception ignored) {}
        }

        if (g instanceof LongVector<?, ?> lv) {
            try {return (Group<E>) new Long4(lv);} catch (final Exception ignored) {}
            try {return (Group<E>) new Long3(lv);} catch (final Exception ignored) {}
            try {return (Group<E>) new Long2(lv);} catch (final Exception ignored) {}
        }

        if (g instanceof IntVector<?, ?> iv) {
            try {return (Group<E>) new Int4(iv);} catch (final Exception ignored) {}
            try {return (Group<E>) new Int3(iv);} catch (final Exception ignored) {}
            try {return (Group<E>) new Int2(iv);} catch (final Exception ignored) {}
        }

        return new ArrayTuple<>(g.list());
    }

    //
    // List Conversion
    //

    /**
     * Converts this group into a list.
     *
     * @return The list representation of this group
     */
    @Nonnull
    List<E> list();

    //
    // Serialization
    //

    /**
     * Serializes this group into a string.
     *
     * @return The string representation of this group
     */
    @Nonnull
    String toString();
}
