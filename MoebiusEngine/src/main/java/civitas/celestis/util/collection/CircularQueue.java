package civitas.celestis.util.collection;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * A circular queue. Entries can be added or removed concurrently to this queue.
 *
 * @param <E> The type of element to contain in this queue
 */
public class CircularQueue<E> extends GroupableList<E> implements Queue<E> {
    //
    // Static Initializers
    //

    /**
     * Creates a new circular queue from a variable array of entries.
     *
     * @param entries The array of entries to contain
     * @param <E>     The type of element to contain
     * @return The constructed circular queue
     */
    @Nonnull
    @SafeVarargs
    public static <E> CircularQueue<E> of(@Nonnull E... entries) {
        return new CircularQueue<>(Arrays.asList(entries));
    }

    //
    // Constructors
    //

    /**
     * Creates a new circular queue.
     *
     * @param initialCapacity Initial capacity of this queue
     */
    public CircularQueue(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * Creates a new circular queue.
     */
    public CircularQueue() {
    }

    /**
     * Creates a new circular queue.
     *
     * @param c The collection of initial entries to contain
     */
    public CircularQueue(@Nonnull Collection<? extends E> c) {
        super(c);
    }

    /**
     * Creates a new circular queue.
     *
     * @param l The listable object of which to copy element values from
     */
    public CircularQueue(@Nonnull Listable<E> l) {
        super(l);
    }

    //
    // Iteration
    //

    /**
     * The current iterator of this queue.
     */
    private int i = 0;

    /**
     * Returns the next iterator of this queue, without any side effects.
     *
     * @return The next iterator of this queue
     */
    protected int getI() {
        if (i < size()) {
            return i;
        } else {
            return 0;
        }
    }

    /**
     * Returns the next iterator of this queue, then increments it.
     *
     * @return The next iterator of this queue
     */
    protected int getAndIncrementI() {
        if (i < size()) {
            return i++;
        } else {
            i = 0;
            return i;
        }
    }

    //
    // Getters
    //

    /**
     * Retrieves the next element of this queue, and increments the iterator.
     * Calling this method repetitively will continuously cycle the iterator,
     * resulting in different return values for each method call.
     *
     * @return The next element in this queue
     */
    @Nullable
    public E next() {
        return get(getAndIncrementI());
    }

    //
    // Methods from Queue
    //

    /**
     * Adds the element to this queue.
     * Since a circular queue has no limit to its size other than the limit of integers,
     * this will only fail if the resulting queue's size will trigger an integer overflow.
     *
     * @param e The element to add to this queue
     * @return The equivalent of {@link CircularQueue#add(Object)}
     */
    @Override
    public boolean offer(E e) {
        return add(e);
    }

    /**
     * {@inheritDoc}
     *
     * @return The head of this queue
     * @throws NoSuchElementException When the queue is empty
     */
    @Override
    @Nullable
    public E remove() {
        final E result = super.remove(getI());

        // Check of result is null
        if (result == null) {
            throw new NoSuchElementException("This queue is empty.");
        }
        // Increment the counter if an element was removed
        else {
            getAndIncrementI();
        }

        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @return The head of this queue
     */
    @Override
    @Nullable
    public E poll() {
        final E result = remove(getI());

        if (result != null) {
            getAndIncrementI();
        }

        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @return The head of this queue
     */
    @Override
    @Nullable
    public E element() {
        final E result = get(getAndIncrementI());

        // Check for null
        if (result == null) {
            throw new NoSuchElementException("This queue is empty.");
        }

        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @return The head of this queue
     */
    @Override
    @Nullable
    public E peek() {
        return get(getAndIncrementI());
    }
}