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

            case MERGE_SORT -> {
                mergeSort(list, comparator);
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
        RADIATION_SORT,
        /**
         * Not recommended. This algorithm is occasionally useful.
         */
        MERGE_SORT;

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


    //
    // Inefficient copypasta code provided by ChatGPT.
    //

    private static <E> void mergeSort(List<E> list, Comparator<E> comparator) {
        mergeSortRecursive(list, comparator, 0, list.size() - 1);
    }

    private static <E> void mergeSortRecursive(List<E> list, Comparator<E> comparator, int left, int right) {
        if (left < right) {
            int middle = left + (right - left) / 2;
            mergeSortRecursive(list, comparator, left, middle);
            mergeSortRecursive(list, comparator, middle + 1, right);
            merge(list, comparator, left, middle, right);
        }
    }

    private static <E> void merge(List<E> list, Comparator<E> comparator, int left, int middle, int right) {
        int n1 = middle - left + 1;
        int n2 = right - middle;

        List<E> leftList = new ArrayList<>(list.subList(left, left + n1));
        List<E> rightList = new ArrayList<>(list.subList(middle + 1, middle + 1 + n2));

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (comparator.compare(leftList.get(i), rightList.get(j)) <= 0) {
                list.set(k++, leftList.get(i++));
            } else {
                list.set(k++, rightList.get(j++));
            }
        }

        while (i < n1) {
            list.set(k++, leftList.get(i++));
        }

        while (j < n2) {
            list.set(k++, rightList.get(j++));
        }
    }
}
