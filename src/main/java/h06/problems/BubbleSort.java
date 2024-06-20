package h06.problems;

import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

public class BubbleSort {
    // TODO: Maybe we can leave away the n argument in the recursive methods since it is just the length of the array. But then it is not as close to the racket code as it is currently.
    /**
     * Sorts the given array using the bubble sort algorithm.
     *
     * @param array the array to sort
     */
    @StudentImplementationRequired
    public static void bubbleSortRecursive(int[] array) {
        bubbleSortRecursiveHelper(array, array.length);
    }

    /**
     * Helper method for bubbleSortRecursive.
     *
     * @param array the array to sort
     * @param n the length of the array
     */
    @StudentImplementationRequired
    private static void bubbleSortRecursiveHelper(int[] array, int n) {
        if (n == 1) {
            return;
        } else {
            bubblePass(array, 0, n);
            bubbleSortRecursiveHelper(array, n - 1);
        }
    }

    /**
     * Performs a single pass of the bubble sort algorithm.
     *
     * @param array the array to sort
     * @param i the current index
     * @param n the length of the array
     */
    @StudentImplementationRequired
    private static void bubblePass(int[] array, int i, int n) {
        if (i >= n - 1) {
            return;
        } else if (array[i] > array[i + 1]) {
            int temp = array[i];
            array[i] = array[i + 1];
            array[i + 1] = temp;
            bubblePass(array, i + 1, n);
        } else {
            bubblePass(array, i + 1, n);
        }
    }

    /**
     * Sorts the given array using the iterative bubble sort algorithm.
     *
     * @param array the array to sort
     */
   @StudentImplementationRequired
    public static void bubbleSortIterative(int[] array) {
        for (int i = array.length; i > 1; i--) {
            for (int j = 0; j < i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}
