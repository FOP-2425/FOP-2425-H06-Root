package h06;

import org.sourcegrade.jagr.api.rubric.*;
import org.tudalgo.algoutils.tutor.general.json.JsonParameterSet;

import static org.tudalgo.algoutils.tutor.general.jagr.RubricUtils.criterion;

public class H06_RubricProvider implements RubricProvider {

    private static final Criterion H6_1_1 = Criterion.builder()
        .shortDescription("H6.1.1 | Fibonacci rekursiv")
        .minPoints(0)
        .maxPoints(2)
        .addChildCriteria(
            criterion(
                "Die Hilfsmethode doTheRecursion wurde korrekt implementiert.",
                JUnitTestRef.ofMethod(() -> FibonacciTest.class.getDeclaredMethod("testDoTheRecursion", int.class))
            ),
            criterion(
                "Die Methode fibonacciRecursiveDifferent ruft die Hilfsmethode auf und gibt die selben Werte wie fibonacciRecursiveClassic zurück.",
                JUnitTestRef.ofMethod(() -> FibonacciTest.class.getDeclaredMethod("testFibonacciRecursiveDifferent", int.class))
            ),
            criterion(
                "Verbindliche Anforderung nicht erfüllt",
                -2,
                JUnitTestRef.ofMethod(() -> FibonacciTest.class.getDeclaredMethod("testFibonacciRecursiveVAnforderung"))
            ) // Punktabzug wenn nicht erfüllt
        )
        .build();

    private static final Criterion H6_1_2 = Criterion.builder()
        .shortDescription("H6.1.2 | Fibonacci iterativ")
        .minPoints(0)
        .maxPoints(3)
        .addChildCriteria(
            criterion(
                "Die Methode fibonacciIterative gibt die selben Werte wie fibonacciRecursiveClassic zurück.",
                3,
                JUnitTestRef.ofMethod(() -> FibonacciTest.class.getDeclaredMethod("testFibonacciIterative", int.class))
            ),
            criterion(
                "Verbindliche Anforderung nicht erfüllt",
                -3,
                JUnitTestRef.ofMethod(() -> FibonacciTest.class.getDeclaredMethod("testFibonacciIterativeVAnforderung"))
            ) // Punktabzug wenn nicht erfüllt
        )
        .build();

    private static final Criterion H6_1 = Criterion.builder()
        .shortDescription("H6.1 | Fibonacci Zahlenfolge")
        .addChildCriteria(
            H6_1_1,
            H6_1_2
        )
        .build();

    private static final Criterion H6_2_1 = Criterion.builder()
        .shortDescription("H6.2.1 | Lineare Suche rekursiv")
        .minPoints(0)
        .maxPoints(4)
        .addChildCriteria(
            criterion(
                "Die Methode linearSearchRecursive gibt den korrekten Index zurück.",
                JUnitTestRef.ofMethod(() -> LinearSearchTest.class.getDeclaredMethod("testLinearSearchRecursive", JsonParameterSet.class))
            ),
            criterion(
                "Die Hilfsmethode wird mit den korrekten Parametern aufgerufen.",
                JUnitTestRef.ofMethod(() -> LinearSearchTest.class.getDeclaredMethod("testLinearSearchRecursiveHelperCall", JsonParameterSet.class))
            ),
            criterion(
                "Die Methode linearSearchRecursiveHelper gibt den korrekten Index zurück.",
                JUnitTestRef.ofMethod(() -> LinearSearchTest.class.getDeclaredMethod("testLinearSearchRecursiveHelper", JsonParameterSet.class))
            ),
            criterion(
                "Es wird -1 zurückgegeben, wenn das Element nicht gefunden wird.",
                JUnitTestRef.ofMethod(() -> LinearSearchTest.class.getDeclaredMethod("testLinearSearchRecursiveHelperTargetNotInArray", JsonParameterSet.class))
            ),
            criterion(
                "Verbindliche Anforderung nicht erfüllt",
                -4,
                JUnitTestRef.ofMethod(() -> LinearSearchTest.class.getDeclaredMethod("testLinearSearchRecursiveHelperVAnforderung"))
            ) // Punktabzug wenn nicht erfüllt
        )
        .build();

    private static final Criterion H6_2_2 = Criterion.builder()
        .shortDescription("H6.2.2 | Lineare Suche iterativ")
        .minPoints(0)
        .maxPoints(3)
        .addChildCriteria(
            criterion(
                "Die Methode linearSearchIterative gibt den korrekten Index zurück.",
                2,
                JUnitTestRef.ofMethod(() -> LinearSearchTest.class.getDeclaredMethod("testLinearSearchIterative", JsonParameterSet.class))
            ),
            criterion(
                "Es wird -1 zurückgegeben, wenn das Element nicht gefunden wird.",
                JUnitTestRef.ofMethod(() -> LinearSearchTest.class.getDeclaredMethod("testLinearSearchIterativeTargetNotInArray", JsonParameterSet.class))
            ),
            criterion(
                "Verbindliche Anforderung nicht erfüllt",
                -3,
                JUnitTestRef.ofMethod(() -> LinearSearchTest.class.getDeclaredMethod("testLinearSearchIterativeVAnforderung"))
            ) // Punktabzug wenn nicht erfüllt
        )
        .build();

    private static final Criterion H6_2 = Criterion.builder()
        .shortDescription("H6.2 | Lineare Suche")
        .addChildCriteria(
            H6_2_1,
            H6_2_2
        )
        .build();

    private static final Criterion H6_3_1 = Criterion.builder()
        .shortDescription("H6.3.1 | Funktion pow")
        .minPoints(0)
        .maxPoints(2)
        .addChildCriteria(
            criterion(
                "Die Methode pow gibt das korrekte Ergebnis für b = 0 zurück.",
                JUnitTestRef.ofMethod(() -> FractalsTest.class.getDeclaredMethod("testPowExponentZero", int.class))
            ),
            criterion(
                "Die Methode pow gibt das korrekte Ergebnis für b > 0 zurück.",
                JUnitTestRef.ofMethod(() -> FractalsTest.class.getDeclaredMethod("testPowExponentPositive", int.class))
            ),
            criterion(
                "Verbindliche Anforderung nicht erfüllt",
                -2,
                JUnitTestRef.ofMethod(() -> FractalsTest.class.getDeclaredMethod("testPowVAnforderung"))
            ) // Punktabzug wenn nicht erfüllt
        )
        .build();

    private static final Criterion H6_3_2 = Criterion.builder()
        .shortDescription("H6.3.2 | Funktion concatenate")
        .maxPoints(4)
        .addChildCriteria(
            criterion(
                "Die Länge des Ergebnisarrays ist die Summe der Längen der beiden Eingabearrays."
            ),
            criterion(
                "Das resultierende Array enthält nur die Elemente von arr1, wenn arr2.length == 0 ist."
            ),
            criterion(
                "Das resultierende Array enthält nur die Elemente von arr2, wenn arr1.length == 0 ist."
            ),
            criterion(
                "Das resultierende Array enthält alle Elemente von arr1 gefolgt von allen Elementen von arr2, wenn beide Arrays Elemente enthalten."
            ),
            criterion(
                "Verbindliche Anforderung nicht erfüllt",
                null,
                -4
            ) // Punktabzug wenn nicht erfüllt
        )
        .build();

    private static final Criterion H6_3_3 = Criterion.builder()
        .shortDescription("H6.3.3 | Funktion replaceAtIndex")
        .maxPoints(4)
        .addChildCriteria(
            criterion(
                "Die Länge des Ergebnisarrays ist die gleiche wie die des Eingabearrays."
            ),
            criterion(
                "Das Ergebnisarray enthält bis auf das Element an der angegebenen Stelle die gleichen Elemente wie das Eingabearray."
            ),
            criterion(
                "Das Ergebnisarray enthält das neue Element an der angegebenen Stelle."
            ),
            criterion(
                "Es wird ein neues Array zurückgegeben, das das Eingabearray nicht verändert."
            ),
            criterion(
                "Verbindliche Anforderung nicht erfüllt",
                null,
                -4
            ) // Punktabzug wenn nicht erfüllt
        )
        .build();

    private static final Criterion H6_3_4 = Criterion.builder()
        .shortDescription("H6.3.4 | Drachenkurve")
        .maxPoints(4)
        .addChildCriteria(
            criterion(
                "Die Methode dragonCurve gibt für n <= 0 das korrekte Ergebnis zurück."
            ),
            criterion(
                "Die Methode dragonCurve gibt für n == 1 das korrekte Ergebnis zurück."
            ),
            criterion(
                "Die Methode dragonCurve gibt für n > 1 das korrekte Ergebnis zurück.",
                null,
                2
            ),
            criterion(
                "Verbindliche Anforderung nicht erfüllt",
                null,
                -4
            ) // Punktabzug wenn nicht erfüllt
        )
        .build();

    private static final Criterion H6_3_5 = Criterion.builder()
        .shortDescription("H6.3.5 | Koch-Schneeflocke")
        .maxPoints(5)
        .addChildCriteria(
            criterion(
                "Die Methode kochSnowflake gibt für n <= 0 das korrekte Ergebnis zurück."
            ),
            criterion(
                "Die Methode kochSnowflake gibt für n == 1 das korrekte Ergebnis zurück."
            ),
            criterion(
                "Die Methode kochSnowflake gibt für n > 1 das korrekte Ergebnis zurück.",
                null,
                3
            )
        )
        .build();

    private static final Criterion H6_3_6 = Criterion.builder()
        .shortDescription("H6.3.6 | Visualisieren der Fraktale")
        .maxPoints(1)
        .addChildCriteria(
            criterion(
                "Ein Objekt vom Typ FractalViszalizer wird erstellt und bekommt die korrekten Parameter übergeben."
            )
        )
        .build();

    private static final Criterion H6_3 = Criterion.builder()
        .shortDescription("H6.3 | Fraktale")
        .addChildCriteria(
            H6_3_1,
            H6_3_2,
            H6_3_3,
            H6_3_4,
            H6_3_5,
            H6_3_6
        )
        .build();

    public static final Rubric RUBRIC = Rubric.builder()
        .title("H06 |  Racket zu Java: Rekursive Algorithmen für Fibonacci, Lineare Suche und Fraktale")
        .addChildCriteria(
            H6_1,
            H6_2,
            H6_3
        )
        .build();

    @Override
    public Rubric getRubric() {
        return RUBRIC;
    }
}
