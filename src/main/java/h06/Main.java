package h06;

import h06.problems.KochFractal;
import h06.ui.FractalVisualizer;

import static h06.problems.Fibonacci.*;

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
        System.out.printf("---------------------------------%n");
        System.out.printf("Computing the Fibonacci Sequence %n");
        System.out.printf("---------------------------------%n");
        System.out.printf("| %3s | %18s | %20s | %15s |%n", "N", "Recursive: Classic", "Recursive: Different", "Iterative");

        for (int n = 0; n <= 15; n++) {
            System.out.printf("| %3d | %18d | %20d | %15d |%n", n, fibonacciRecursiveClassic(n), fibonacciRecursiveDifferent(n), fibonacciIterative(n));
        }

        // H3
        FractalVisualizer fracVis = new FractalVisualizer(new KochFractal());
        //fracVis.setVisible(true);
    }
}
