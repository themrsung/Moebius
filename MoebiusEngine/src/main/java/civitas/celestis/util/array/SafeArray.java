package civitas.celestis.util.array;

import civitas.celestis.util.collection.Listable;
import civitas.celestis.util.group.Group;
import civitas.celestis.util.group.Groupable;
import civitas.celestis.util.tuple.ArrayTuple;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * A lightweight type-safe array.
 * <p>
 * This should be used as an intermediary data structure in the same manner
 * {@link Stream}s are used in. Since type-safe arrays can be easily converted to
 * other data types such as {@link Group}, {@link Collection}, or {@link List},
 * converting to one of those data structures is recommended.
 * </p>
 *
 * @param <E> The type of element to contain
 */
public class SafeArray<E> implements Listable<E>, Groupable<E>, Iterable<E> {
    //
    // Static Initializers
    //

    /**
     * Creates a new array representing the provided primitive array.
     * Changes in the primitive array will be reflected to the return value, and vice versa.
     *
     * @param values The primitive array of values to represent
     * @param <E>    The type of element to contain
     * @return The type-safe array representing the provided values
     */
    @Nonnull
    @SafeVarargs
    public static <E> SafeArray<E> of(@Nonnull E... values) {
        return new SafeArray<>(values);
    }

    /**
     * Creates a new array from a {@link Group} of objects.
     *
     * @param g   The group of which to copy values from
     * @param <E> The type of element to contain
     * @return An array representation of the group
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    public static <E> SafeArray<E> fromGroup(@Nonnull Group<E> g) {
        return new SafeArray<>((E[]) g.list().toArray(Object[]::new));
    }

    /**
     * Creates a new array from a {@link Collection} of objects.
     * Changes in the collection will be reflected to the return value, and vice versa.
     *
     * @param c   The collection of which to copy values from
     * @param <E> The type of element to contain
     * @return An array representation of the collection
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    public static <E> SafeArray<E> fromCollection(@Nonnull Collection<E> c) {
        return new SafeArray<>((E[]) c.toArray(Object[]::new));
    }

    /**
     * Returns a shallow copy of the provided array {@code a}.
     *
     * @param a   The array of which to perform the shallow copy of
     * @param <E> The type of element to contain
     * @return A shallow copy of the provided array
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    public static <E> SafeArray<E> copyOf(@Nonnull E[] a) {
        return new SafeArray<>((E[]) Arrays.stream(a).toArray(Object[]::new));
    }

    /**
     * Returns a shallow copy of the provided safe array {@code a}.
     *
     * @param a   The array of which to perform the shallow copy of
     * @param <E> The type of element to contain
     * @return A shallow copy of the provided array
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    public static <E> SafeArray<E> copyOf(@Nonnull SafeArray<E> a) {
        return new SafeArray<>((E[]) Arrays.stream(a.values).toArray(Object[]::new));
    }

    //
    // Boxing
    //

    /**
     * Boxes a primitive array of {@code double}s.
     *
     * @param values The values to box
     * @return The boxed array of {@link Double}s
     */
    @Nonnull
    public static SafeArray<Double> boxDoubles(@Nonnull double... values) {
        return new SafeArray<>(Arrays.stream(values).boxed().toArray(Double[]::new));
    }

    /**
     * Boxes a primitive array of {@code float}s.
     *
     * @param values The values to box
     * @return The unboxed array of {@link Float}s
     */
    @Nonnull
    public static SafeArray<Float> boxFloats(@Nonnull float... values) {
        final Float[] boxed = new Float[values.length];

        for (int i = 0; i < values.length; i++) {
            boxed[i] = values[i];
        }

        return new SafeArray<>(boxed);
    }

    /**
     * Boxes a primitive array of {@code long}s.
     *
     * @param values The values to box
     * @return The boxed array of {@link Long}s
     */
    @Nonnull
    public static SafeArray<Long> boxLongs(@Nonnull long... values) {
        return new SafeArray<>(Arrays.stream(values).boxed().toArray(Long[]::new));
    }

    /**
     * Boxes a primitive array of {@code int}s.
     *
     * @param values The values to box
     * @return The boxed array of {@link Integer}s
     */
    @Nonnull
    public static SafeArray<Integer> boxIntegers(@Nonnull int... values) {
        return new SafeArray<>(Arrays.stream(values).boxed().toArray(Integer[]::new));
    }

    /**
     * Unboxes a safe array of {@link Double}s.
     *
     * @param array The array to unbox
     * @return The unboxed primitive array of {@code double}s
     */
    @Nonnull
    public static double[] unboxDoubles(@Nonnull SafeArray<Double> array) {
        final double[] unboxed = new double[array.values.length];

        for (int i = 0; i < array.values.length; i++) {
            unboxed[i] = array.values[i];
        }

        return unboxed;
    }

    /**
     * Unboxes a safe array of {@link Float}s.
     *
     * @param array The array to unbox
     * @return The unboxed primitive array of {@code float}s
     */
    @Nonnull
    public static float[] unboxFloats(@Nonnull SafeArray<Float> array) {
        final float[] unboxed = new float[array.values.length];

        for (int i = 0; i < array.values.length; i++) {
            unboxed[i] = array.values[i];
        }

        return unboxed;
    }

    /**
     * Unboxes a safe array of {@link Long}s.
     *
     * @param array The array to unbox
     * @return The unboxed primitive array of {@code long}s
     */
    @Nonnull
    public static long[] unboxLongs(@Nonnull SafeArray<Long> array) {
        final long[] unboxed = new long[array.values.length];

        for (int i = 0; i < array.values.length; i++) {
            unboxed[i] = array.values[i];
        }

        return unboxed;
    }

    /**
     * Unboxes a safe array of {@link Integer}s.
     *
     * @param array The array to unbox
     * @return The unboxed primitive array of {@code int}s
     */
    @Nonnull
    public static int[] unboxIntegers(@Nonnull SafeArray<Integer> array) {
        final int[] unboxed = new int[array.values.length];

        for (int i = 0; i < array.values.length; i++) {
            unboxed[i] = array.values[i];
        }

        return unboxed;
    }

    //
    // Constructors
    //

    /**
     * Creates a new array.
     *
     * @param length The length of this array
     */
    @SuppressWarnings("unchecked")
    public SafeArray(int length) {
        this.values = (E[]) new Object[length];
    }

    /**
     * Creates a new array.
     *
     * @param values The values to contain in this array
     */
    public SafeArray(@Nonnull E[] values) {
        this.values = values;
    }

    //
    // Variables
    //

    /**
     * The internal primitive array.
     */
    @Nonnull
    protected final E[] values;

    //
    // Containment
    //

    /**
     * Checks if this array contains the provided object {@code obj}.
     *
     * @param obj The object of which to check for containment
     * @return {@code true} if this array contains the provided object {@code obj}
     */
    public boolean contains(@Nullable Object obj) {
        for (final E value : values) {
            if (Objects.equals(value, obj)) return true;
        }

        return false;
    }

    /**
     * Checks if this array contains multiple objects.
     *
     * @param i The iterable object to check for containment
     * @return {@code true} if this array contains every element of the iterable object
     */
    public boolean containsAll(@Nonnull Iterable<?> i) {
        for (final Object o : i) {
            if (!contains(o)) return false;
        }

        return true;
    }

    //
    // Getters
    //

    /**
     * Returns the {@code i}th element of this array.
     *
     * @param i The index of element to get
     * @return The {@code i}th element of this array
     * @throws IndexOutOfBoundsException When the index {@code i} is out of bounds
     */
    public E get(int i) throws IndexOutOfBoundsException {
        return values[i];
    }

    //
    // Setters
    //

    /**
     * Sets the {@code i}th element of this array.
     *
     * @param i The index of element to set
     * @param v The value to set the element to
     * @throws IndexOutOfBoundsException When the index {@code i} is out of bounds
     */
    public void set(int i, E v) throws IndexOutOfBoundsException {
        values[i] = v;
    }

    //
    // Bulk Operation
    //

    /**
     * Fills this array with the provided value {@code v}.
     *
     * @param v The value to fill this array with
     */
    public void fill(E v) {
        Arrays.fill(values, v);
    }

    /**
     * Replaces the first instance of the old value to the new value.
     *
     * @param oldValue The value to replace
     * @param newValue The value to replace to
     */
    public void replaceFirst(E oldValue, E newValue) {
        for (int i = 0; i < values.length; i++) {
            if (Objects.equals(values[i], oldValue)) {
                values[i] = newValue;
                break;
            }
        }
    }

    /**
     * Replaces all instances of the old value to the new value.
     *
     * @param oldValue The value to replace
     * @param newValue The value to replace to
     */
    public void replaceAll(E oldValue, E newValue) {
        for (int i = 0; i < values.length; i++) {
            if (Objects.equals(values[i], oldValue)) {
                values[i] = newValue;
            }
        }
    }

    //
    // Sub-operation
    //

    /**
     * Returns a sub-array of the provided range.
     *
     * @param i1 The starting index of the sub-array
     * @param i2 The ending index of the sub-array
     * @return The sub-array of the provided range
     * @throws IndexOutOfBoundsException When the range is out of bounds
     */
    @Nonnull
    public SafeArray<E> subArray(int i1, int i2) throws IndexOutOfBoundsException {
        final SafeArray<E> result = new SafeArray<>(i2 - i1);
        if (i2 - i1 >= 0) System.arraycopy(values, i1, result.values, 0, i2 - i1);
        return result;
    }

    //
    // Resizing
    //

    /**
     * Returns a resized array with the elements of this array.
     * If the new length is greater than this array's length, the oversized part
     * will be populated with {@code null}.
     *
     * @param newLength The new length to resize to
     * @return A resized array
     */
    @Nonnull
    public SafeArray<E> resize(int newLength) {
        final SafeArray<E> result = new SafeArray<>(newLength);
        final int minLength = Math.min(values.length, newLength);

        if (minLength >= 0) System.arraycopy(values, 0, result.values, 0, minLength);

        return result;
    }

    //
    // Transformation
    //

    /**
     * Applies the provided function {@code f} to all elements of this array,
     * then returns the transformed array.
     *
     * @param f The function to apply to each element of this array
     * @return The transformed array
     */
    @Nonnull
    public SafeArray<E> transform(@Nonnull Function<? super E, E> f) {
        final SafeArray<E> result = new SafeArray<>(values.length);

        for (int i = 0; i < values.length; i++) {
            result.values[i] = f.apply(values[i]);
        }

        return result;
    }

    /**
     * Applies the provided mapper function {@code f} to all elements of this array,
     * then returns the mapped array.
     *
     * @param f   The mapper function to apply to each element of this group
     * @param <F> The type of which to map this array to
     * @return The mapped array
     */
    @Nonnull
    public <F extends E> SafeArray<F> map(@Nonnull Function<? super E, F> f) {
        final SafeArray<F> result = new SafeArray<>(values.length);

        for (int i = 0; i < values.length; i++) {
            result.values[i] = f.apply(values[i]);
        }

        return result;
    }

    //
    // List Conversion
    //

    /**
     * Converts this array into a list.
     * Changes in the list will be reflected to this array.
     *
     * @return The list representation of this array
     */
    @Nonnull
    @Override
    public List<E> list() {
        return Arrays.asList(values);
    }

    //
    // Group Conversion
    //

    /**
     * Converts this array into a group.
     *
     * @return The group representation of this array
     */
    @Nonnull
    @Override
    @SuppressWarnings("unchecked")
    public ArrayTuple<E> group() {
        return new ArrayTuple<>((E[]) Arrays.stream(values).toArray(Object[]::new));
    }

    //
    // Stream Conversion
    //

    /**
     * Returns a stream of the values of this array.
     *
     * @return A stream of the values of this array
     */
    @Nonnull
    public Stream<E> stream() {
        return Arrays.stream(values);
    }

    //
    // Type-safe Conversion
    //

    /**
     * This is already a type-safe array, and thus requires no conversion.
     * This method will simply return a reference to this instance itself.
     * @return {@code this}
     */
    @Nonnull
    @Override
    public SafeArray<E> safeArray() {
        return this;
    }


    //
    // Iteration
    //

    /**
     * Returns an iterator of all elements of this array.
     *
     * @return An iterator of all elements of this array
     */
    @Override
    public Iterator<E> iterator() {
        return Arrays.stream(values).iterator();
    }

    //
    // Equality
    //

    /**
     * Checks for equality between this array and the provided object {@code obj}.
     *
     * @param obj The object to compare to
     * @return {@code true} if the object is also a type-safe array,
     * and the composition and order are equal
     */
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof SafeArray<?> a)) return false;
        return Arrays.equals(values, a.values);
    }

    /**
     * Checks for equality between this array and a primitive array of the same type.
     *
     * @param a The array to compare to
     * @return {@code true} if the composition and order are equal
     */
    public boolean equals(@Nonnull E[] a) {
        return Arrays.equals(values, a);
    }

    //
    // Serialization
    //

    /**
     * Serializes this array into a string.
     *
     * @return The string representation of this array
     */
    @Override
    @Nonnull
    public String toString() {
        return Arrays.toString(values);
    }
}
