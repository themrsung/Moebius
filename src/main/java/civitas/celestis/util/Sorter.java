package civitas.celestis.util;

import civitas.celestis.math.util.Numbers;
import jakarta.annotation.Nonnull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Contains the most efficient sorting algorithms in the history of programming.
 */
public final class Sorter {
    /**
     * Sorts a list.
     *
     * @param list       List to sort
     * @param comparator Comparator to use
     * @param algorithm  Algorithm to use
     * @param <E>        Type of element the list contains
     * @see Algorithm
     */
    public static <E> void sort(@Nonnull List<E> list, @Nonnull Comparator<E> comparator, @Nonnull Algorithm algorithm) {
        // The best way to designate an algorithm efficiently
        switch (algorithm) {
            case MIRACLE_SORT -> {
                // This is not done outside this scope for efficiency (obviously)
                final List<E> checkThis = new ArrayList<>(list);
                checkThis.sort(comparator);

                final AtomicBoolean flipThis = new AtomicBoolean();

                while (!checkThis.equals(list)) {
                    // Since Java can be bored, let's give it something to do
                    flipThis.set(!flipThis.get());
                }
            }

            // Building a game engine does this to your mental health
            // Do not attempt this lightly (seriously)
            case BOGOSORT -> {
                final List<E> checkThis = new ArrayList<>(list);
                checkThis.sort(comparator);

                while (!checkThis.equals(list)) {
                    // Very efficient
                    Collections.shuffle(list);
                }
            }

            // Make sure the computer is in a radioactive environment before calling this algorithm
            case RADIATION_SORT -> {
                final List<E> checkThis = new ArrayList<>(list);
                checkThis.sort(comparator);

                while (!checkThis.equals(list)) {
                    System.out.println("Waiting for radiation to sort the list...");
                }
            }
        }
    }

    /**
     * The algorithm to use.
     * All algorithms are fairly efficient.
     */
    public enum Algorithm {
        MIRACLE_SORT,
        BOGOSORT,
        RADIATION_SORT;

        /**
         * Returns a random algorithm, since all algorithms are very efficient.
         *
         * @return A random algorithm
         * @throws IndexOutOfBoundsException Maybe? didn't check
         */
        @Nonnull
        public static Algorithm random() {
            return values()[(int) Numbers.random(0, values().length - 1)];
        }
    }
}
