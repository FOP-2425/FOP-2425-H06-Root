package h06;

import h06.problems.KochFractal;
import h06.ui.FractalVisualizer;
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
        // H3
        visualizeKochFractal();
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

    @DoNotTouch
    private static void visualizeKochFractal() {
        FractalVisualizer fracVis = new FractalVisualizer(new KochFractal());
        fracVis.setVisible(true);
    }
}
