package h06.problems;

import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

public class InsertionSort {
    /**
     * Sorts the given array using the recursive insertion sort algorithm.
     *
     * @param array the array to sort
     */
    @StudentImplementationRequired
    public static void insertionSortRecursive(int[] array) {
        insertionSortRecursiveHelper(array, array.length);
    }

    /**
     * Helper method for insertionSortRecursive.
     *
     * @param array the array to sort
     * @param n the length of the unsorted part of the array
     */
    @StudentImplementationRequired
    private static void insertionSortRecursiveHelper(int[] array, int n) {
        if (n <= 1) {
            return;
        } else {
            insertRecursive(array, n - 1);
            insertionSortRecursiveHelper(array, n - 1);
        }
    }

    /**
     * Inserts the element at index n into the correct position in the array.
     *
     * @param array the array to sort
     * @param n the index of the element to insert
     */
    @StudentImplementationRequired
    private static void insertRecursive(int[] array, int n) {
        if (n <= 0 || array[n - 1] <= array[n]) {
            return;
        } else {
            int temp = array[n];
            array[n] = array[n - 1];
            array[n - 1] = temp;
            insertRecursive(array, n - 1);
        }
    }

    /**
     * Sorts the given array using the iterative insertion sort algorithm.
     *
     * @param array the array to sort
     */
    @StudentImplementationRequired
    public static void insertionSortIterative(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;

            // Move elements of array[0..i-1], that are greater than key
            // to one position ahead of their current position
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }
}
