package h06;

import h06.problems.Fractals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.tudalgo.algoutils.tutor.general.assertions.Context;

import static h06.TestUtils.getCtMethod;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.*;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions4.assertIsNotIteratively;

@TestForSubmission
public class FractalsTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9})
    public void testPowExponentZero(int a) {
        Context context = contextBuilder()
            .add("a", a)
            .add("b", 0)
            .build();
        int expected = 1;
        int actual = callObject(() -> Fractals.pow(a, 0), context, result ->
            "An exception occurred while invoking method pow");
        assertEquals(expected, actual, context, result -> "Method pow returned an incorrect value");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9})
    public void testPowExponentPositive(int a) {
        Context.Builder<?> contextBuilder = contextBuilder().add("a", a);
        for (int b = 1; b <= 5; b++) {
            Context context = contextBuilder
                .add("b", b)
                .build();
            final int finalB = b;
            int expected = (int) Math.pow(a, b);
            int actual = callObject(() -> Fractals.pow(a, finalB), context, result ->
                "An exception occurred while invoking method pow");
            assertEquals(expected, actual, context, result -> "Method pow returned an incorrect value");
        }
    }

    @Test
    public void testPowVAnforderung() {
        assertIsNotIteratively(getCtMethod(Fractals.class, "pow", int.class, int.class),
            emptyContext(),
            result -> "Method pow is not recursive");
    }
}
