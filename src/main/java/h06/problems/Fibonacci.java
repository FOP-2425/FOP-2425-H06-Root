package h06.problems;

import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

// man darf als Student davon ausgehen, dass nur für n>=0 gelöst werden muss
// Ich würde zuerst iterativ programmieren lassen da das gewohnt ist und anschließend rekursiv
public class Fibonacci {

    @DoNotTouch
    public static int fibonacciRecursiveClassic(int n) {
        if ( n <= 1) {
            return n;
        } else {
            return fibonacciRecursiveClassic(n - 1) + fibonacciRecursiveClassic(n - 2);
        }
    }

    @StudentImplementationRequired
    public static int fibonacciIterative(int n) {
        int result = n;
        int twoBefore = 0;
        int oneBefore = 1;

        for (int i = 2; i <= n; i++) {
            result = oneBefore + twoBefore;
            twoBefore = oneBefore;
            oneBefore = result;
        }

        return result;
    }

    // Vllt. in Aufgabenstellung kurz erwähnen dass diese Implementierung effizienter ist als die naive rekursive
    // Nur als Motivation
    @StudentImplementationRequired
    public static int fibonacciRecursiveDifferent(int n) {
        return doTheRecursion(0, 1, n);
    }

    @StudentImplementationRequired
    private static int doTheRecursion(int a, int b, int n) {
        // Verbindliche Anforderung: Nur eine Zeile Code und Bedingungsoperator verwenden!
        return n <= 0 ? a : doTheRecursion(b, a+b, n-1);
    }
}
