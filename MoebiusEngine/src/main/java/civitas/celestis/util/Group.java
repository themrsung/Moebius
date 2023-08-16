package civitas.celestis.util;

import civitas.celestis.math.vector.*;
import civitas.celestis.util.tuple.ArrayTuple;
import civitas.celestis.util.tuple.Pair;
import civitas.celestis.util.tuple.Quad;
import civitas.celestis.util.tuple.Triple;
import jakarta.annotation.Nonnull;

import java.util.Arrays;
import java.util.List;

/**
 * A group of elements.
 *
 * @param <E> The type of element to contain in this group
 */
public interface Group<E> {
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
     * Creates a new group of {@code double}s.
     *
     * @param values The values to contain
     * @return The constructed group
     */
    @Nonnull
    static Group<Double> of(@Nonnull double... values) {
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
    static Group<Float> of(@Nonnull float... values) {
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
    static Group<Long> of(@Nonnull long... values) {
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
    static Group<Integer> of(@Nonnull int... values) {
        return switch (values.length) {
            case 2 -> new Int2(values);
            case 3 -> new Int3(values);
            case 4 -> new Int4(values);
            default -> new ArrayTuple<>(Arrays.stream(values).boxed().toArray(Integer[]::new));
        };
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
}
