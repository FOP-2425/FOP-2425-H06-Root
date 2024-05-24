package h06;

import h06.problems.KochFractal;
import h06.ui.FractalVisualizer;

import static h06.problems.Fibonacci.fibonacciIterative;
import static h06.problems.Fibonacci.fibonacciRecursive;

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
        System.out.printf("Fibonacci Sequence %n");
        System.out.printf("---------------------------------%n");
        System.out.printf("| %3s | %10s | %10s |%n", "N", "Iterative", "Recursive");

        for (int n = 0; n <= 15; n++) {
            System.out.printf("| %3d | %10d | %10d |%n", n, fibonacciIterative(n), fibonacciRecursive(n));
        }

        // H3
        FractalVisualizer fracVis = new FractalVisualizer(new KochFractal());
        fracVis.setVisible(true);
    }
}
