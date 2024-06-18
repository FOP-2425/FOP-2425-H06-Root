package h06;

import h06.problems.BubbleSort;
import h06.problems.DragonCurve;
import h06.problems.InsertionSort;
import h06.ui.DragonCurveVisualizer;
import org.tudalgo.algoutils.student.annotation.DoNotTouch;

import java.util.Arrays;

import static h06.problems.Fibonacci.*;
import static h06.problems.LinearSearch.*;

/**
 * Main entry point in executing the program.
 */
public class Main {
    /**
     * Main entry point in executing the program.
     *
     * @param args program arguments, currently ignored
     */
    public static void main(String[] args) {
        // H1
        fibonacciTests();
        // H2
        linearSearchTests();
        bubbleSortTests();
        insertionSortTests();
        // H3
        visualizeDragonCurve();
    }

    @DoNotTouch
    private static void testHeader(String testName) {
        System.out.println("-----------------------------------");
        System.out.println("Running test: " + testName);
        System.out.println("-----------------------------------");

    }

    @DoNotTouch
    private static void fibonacciTests() {
        testHeader("Fibonacci");
        System.out.printf("| %3s | %18s | %20s | %15s |%n", "N", "Recursive: Classic", "Recursive: Different", "Iterative");

        for (int n = 0; n <= 15; n++) {
            System.out.printf("| %3d | %18d | %20d | %15d |%n", n, fibonacciRecursiveClassic(n), fibonacciRecursiveDifferent(n), fibonacciIterative(n));
        }
    }

    @DoNotTouch
    private static void linearSearchTests() {
        testHeader("Linear Search");
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.printf("Should find value %d at index %d%n", 5, 4);
        int target = 5;
        System.out.printf("Recursive: Index of %d in %s at index %d%n", target, Arrays.toString(arr), linearSearchRecursive(arr, target));
        System.out.printf("Iterative: Index of %d in %s at index %d%n", target, Arrays.toString(arr), linearSearchIterative(arr, target));

        target = 11;
        System.out.printf("%nShould not find value %d%n", target);
        System.out.printf("Recursive: Index of %d in %s: %d%n", target, Arrays.toString(arr), linearSearchRecursive(arr, target));
        System.out.printf("Iterative: Index of %d in %s: %d%n", target, Arrays.toString(arr), linearSearchIterative(arr, target));
    }

    private static void bubbleSortTests() {
        testHeader("Bubble Sort");
        int[] arr = {5, 2, 4, 6, 1, 3};
        System.out.printf("Recursive: %n");
        System.out.printf("Before: %s%n", Arrays.toString(arr));
        BubbleSort.bubbleSortRecursive(arr);
        System.out.printf("After: %s%n", Arrays.toString(arr));

        arr = new int[]{5, 2, 4, 6, 1, 3};
        System.out.printf("%nIterative: %n");
        System.out.printf("Before: %s%n", Arrays.toString(arr));
        BubbleSort.bubbleSortIterative(arr);
        System.out.printf("After: %s%n", Arrays.toString(arr));
    }

    private static void insertionSortTests() {
        testHeader("Insertion Sort");
        int[] arr = {5, 2, 4, 6, 1, 3};
        System.out.printf("Recursive: %n");
        System.out.printf("Before: %s%n", Arrays.toString(arr));
        InsertionSort.insertionSortRecursive(arr);
        System.out.printf("After: %s%n", Arrays.toString(arr));

        arr = new int[]{5, 2, 4, 6, 1, 3};
        System.out.printf("%nIterative: %n");
        System.out.printf("Before: %s%n", Arrays.toString(arr));
        InsertionSort.insertionSortIterative(arr);
        System.out.printf("After: %s%n", Arrays.toString(arr));
    }

    @DoNotTouch
    private static void visualizeDragonCurve() {
        String[] dragonCurve = DragonCurve.dragonCurve(14);
        //System.out.print(Arrays.toString(dragonCurve));

        DragonCurveVisualizer fracVis = new DragonCurveVisualizer(dragonCurve);
        fracVis.setVisible(true);
    }
}
