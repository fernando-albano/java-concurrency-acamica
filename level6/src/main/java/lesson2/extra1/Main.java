package lesson2.extra1;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Example of Merge-Sort using ParallelStream.
 */
public class Main {

    private static final boolean PARALLEL = false;
    private static final int ELEMENTS = 100000;
    private static final int ELEMENTS_TO_PROCESS = 10000;

    public static void main(String[] args) {

        int[] ints = new Random().ints(ELEMENTS).toArray();
        int arrays = ELEMENTS / ELEMENTS_TO_PROCESS;
        long nanos = System.nanoTime();

        IntStream stream = PARALLEL ? IntStream.range(0, arrays).parallel() : IntStream.range(0, arrays);
        int[] result = stream.mapToObj(i -> splitAndSort(ints, ELEMENTS_TO_PROCESS, i))
                .reduce(Sort::merge).get();

        nanos = (System.nanoTime() - nanos) / 1000000;
        checkCorrectOrdering(result);
        System.out.printf("Ordered %d elements, divided in %d arrays of %d elements.\n", ELEMENTS, arrays, ELEMENTS_TO_PROCESS);
        System.out.printf("Elements were %sprocessed in parallel.\n", PARALLEL ? "" : "not ");
        System.out.printf("Total time: %d ms.", nanos);
    }

    public static int[] splitAndSort(int[] ints, int size, int index) {
        int from = index * size;
        int to = (index + 1) * size;
        System.out.println(Thread.currentThread().getName() + " processes from " + from + " to " + to);

        int[] result = Arrays.copyOfRange(ints, from, to);
        return Sort.insertionSort(result);
    }

    public static void checkCorrectOrdering(int[] result) {
        for (int i=0; i<result.length-1; i++) {
            if (result[i] > result[i+1]) {
                System.out.printf("The ordering is not correct for %d and %d with values %d and %d.\n", i, i+1, result[i], result[i+1]);
            }
        }
    }
}
