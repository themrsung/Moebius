package civitas.celestis.util;

import jakarta.annotation.Nonnull;

import java.util.*;

/**
 * A utility class containing methods related to {@link Collection}.
 */
public final class CollectionUtils {
    //
    // Deep copy
    //

    /**
     * Copies a collection of objects which extend the {@link Copyable} interface.
     *
     * @param original Original collection
     * @param <E>      Type of element of the original collection
     * @param <F>      Type of element of the returned collection
     * @return Copied collection
     */
    @Nonnull
    public static <E extends Copyable<F>, F> Collection<F> copy(@Nonnull Collection<E> original) {
        final Collection<F> result = new ArrayList<>();

        for (final E e : original) {
            result.add(e.copy());
        }

        return result;
    }

    /**
     * Copies a list of objects which extend the {@link Copyable} interface.
     *
     * @param original Original list
     * @param <E>      Type of element of the original list
     * @param <F>      Type of element of the returned list
     * @return Copied list
     */
    @Nonnull
    public static <E extends Copyable<F>, F> List<F> copy(@Nonnull List<E> original) {
        final List<F> result = new ArrayList<>();

        for (final E e : original) {
            result.add(e.copy());
        }

        return result;
    }

    /**
     * Copies a map of object which extend the {@link Copyable} interface.
     * Note that if the key is not a primitive,
     * modifications in the keys of the resulting map will be reflected in the original map.
     *
     * @param original Original map
     * @param <K>      Type of key
     * @param <V>      Type of value of the original map
     * @param <W>      Type of value of the returned map
     * @return Copied map
     */
    @Nonnull
    public static <K, V extends Copyable<W>, W> Map<K, W> copy(@Nonnull Map<K, V> original) {
        final Map<K, W> result = new HashMap<>();
        original.forEach((k, v) -> result.put(k, v.copy()));
        return result;
    }
}
