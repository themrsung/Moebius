package civitas.celestis.util;

import civitas.celestis.util.group.Pair;
import civitas.celestis.util.group.Singleton;
import civitas.celestis.util.group.Tuple;
import jakarta.annotation.Nonnull;

import java.util.*;
import java.util.function.Function;

/**
 * Represents a copyable object.
 *
 * @param <O> Type of object {@link Copyable#copy()} returns
 */
public interface Copyable<O> {
    //
    // Methods
    //

    /**
     * Returns a deep copy of this object.
     * Any changes made to the return value of this method will not be reflected in this instance.
     *
     * @return A deep copy of this object
     */
    @Nonnull
    O copy();

    //
    // Iterative Deep Copy
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
    static <E extends Copyable<F>, F> Collection<F> copy(@Nonnull Collection<E> original) {
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
    static <E extends Copyable<F>, F> List<F> copy(@Nonnull List<E> original) {
        final List<F> result = new ArrayList<>();

        for (final E e : original) {
            result.add(e.copy());
        }

        return result;
    }

    /**
     * Copies a set of object which extend the {@link Copyable} interface.
     *
     * @param original Original set
     * @param <E>      Type of element of the original set
     * @param <F>      Type of element of the returned set
     * @return Copied set
     */
    @Nonnull
    static <E extends Copyable<F>, F> Set<F> copy(@Nonnull Set<E> original) {
        final Set<F> result = new HashSet<>();

        for (final E e : original) {
            result.add(e.copy());
        }

        return result;
    }

    /**
     * Copies a map of objects which extend the {@link Copyable} interface.
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
    static <K, V extends Copyable<W>, W> Map<K, W> copy(@Nonnull Map<K, V> original) {
        final Map<K, W> result = new HashMap<>();
        original.forEach((k, v) -> result.put(k, v.copy()));
        return result;
    }

    /**
     * Copies a map of objects which extend the {@link Copyable} interface.
     * This also copies the keys using the provided function {@code keyMapper}.
     *
     * @param original  Original map
     * @param keyMapper Mapper to use to copy keys
     * @param <K>       Type of original key
     * @param <P>       Type of copied key
     * @param <V>       Type of value of the original map
     * @param <W>       Type of value of the returned map
     * @return Copied map
     */
    @Nonnull
    static <K, P, V extends Copyable<W>, W> Map<P, W> copy(@Nonnull Map<K, V> original, @Nonnull Function<K, P> keyMapper) {
        final Map<P, W> result = new HashMap<>();
        original.forEach((k, v) -> result.put(keyMapper.apply(k), v.copy()));
        return result;
    }

    /**
     * Copies a singleton of an object which extends the {@link Copyable} interface.
     *
     * @param original Original singleton
     * @param <T>      Type of object
     * @param <U>      Type of returned object
     * @return Copied singleton
     */
    @Nonnull
    static <T extends Copyable<U>, U> Singleton<U> copy(@Nonnull Singleton<T> original) {
        return new Singleton<>(original.value().copy());
    }

    /**
     * Copies a pair of objects which extend the {@link Copyable} interface.
     *
     * @param original Original pair
     * @param <T>      Type of object
     * @param <U>      Type of returned object
     * @return Copied pair
     */
    @Nonnull
    static <T extends Copyable<U>, U> Pair<U> copy(@Nonnull Pair<T> original) {
        return new Pair<>(original.a().copy(), original.b().copy());
    }

    /**
     * Copies a tuple of objects which extend the {@link Copyable} interface.
     *
     * @param original Original tuple
     * @param <T>      Type of object
     * @param <U>      Type of returned object
     * @return Copied tuple
     */
    @Nonnull
    static <T extends Copyable<U>, U> Tuple<U> copy(@Nonnull Tuple<T> original) {
        return new Tuple<>(original.a().copy(), original.b().copy(), original.c().copy());
    }
}
