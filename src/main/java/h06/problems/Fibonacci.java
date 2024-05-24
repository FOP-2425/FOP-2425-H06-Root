package h06.problems;

import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

// man darf als Student davon ausgehen, dass nur für n>=0 gelöst werden muss
// Ich würde zuerst iterativ programmieren lassen da das gewohnt ist und anschließend rekursiv
public class Fibonacci {

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

    @StudentImplementationRequired
    public static int fibonacciRecursive(int n) {
        // Bedingung: Nur eine Zeile Code und Bedingungsoperator verwenden!
        // Gute Bedingung da man dann nicht unbedingt 1:1 aus Internet kopieren kann
        return n <= 1 ? n : fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }
}
