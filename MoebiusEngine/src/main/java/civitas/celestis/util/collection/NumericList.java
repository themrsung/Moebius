package civitas.celestis.util.collection;

import civitas.celestis.math.Sign;
import jakarta.annotation.Nonnull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * A list of numbers.
 *
 * @param <N> The type of number this list should hold
 */
public class NumericList<N extends Number> extends ArrayList<N> implements GroupableCollection<N> {
    //
    // Static Initializers
    //

    /**
     * Creates a new numeric list from an array of values.
     *
     * @param values The values to contain in the list
     * @param <N>    The type of number to contain
     * @return A numeric list containing the provided values
     */
    @Nonnull
    @SafeVarargs
    public static <N extends Number> NumericList<N> of(@Nonnull N... values) {
        return new NumericList<>(Arrays.asList(values));
    }

    /**
     * Creates a new numeric list from an array of values of the type {@code double}.
     *
     * @param values The values to contain in the list
     * @return A numeric list containing the provided values
     */
    public static NumericList<Double> ofDouble(@Nonnull double... values) {
        return new NumericList<>(Arrays.stream(values).boxed().toList());
    }

    /**
     * Creates a new numeric list from an array of values of the type {@code long}.
     *
     * @param values The values to contain in the list
     * @return A numeric list containing the provided values
     */
    public static NumericList<Long> ofLong(@Nonnull long... values) {
        return new NumericList<>(Arrays.stream(values).boxed().toList());
    }

    //
    // Constructors
    //

    /**
     * Creates a new list of numbers.
     *
     * @param initialCapacity The initial capacity of this list
     */
    public NumericList(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * Creates a new empty list of numbers.
     */
    public NumericList() {
    }

    /**
     * Creates a new list of numbers.
     *
     * @param c The collection of which to copy elements from
     */
    public NumericList(@Nonnull Collection<? extends N> c) {
        super(c);
    }

    /**
     * Creates a new list of numbers.
     *
     * @param l The listable object of which to copy element values from
     */
    public NumericList(@Nonnull Listable<N> l) {
        super(l.list());
    }

    //
    // Properties
    //

    /**
     * Returns a list populated with the signs of this list's component values,
     * mapped to their own indexes.
     *
     * @return A list containing the signs of this list's values
     */
    @Nonnull
    public List<Sign> getSigns() {
        final List<Sign> signs = new ArrayList<>();

        for (final N n : this) {
            signs.add(Sign.ofNumber(n));
        }

        return signs;
    }
}
