package h06;

import h06.problems.Fractals;
import h06.ui.DrawInstruction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.tudalgo.algoutils.tutor.general.assertions.Context;
import org.tudalgo.algoutils.tutor.general.json.JsonParameterSet;
import org.tudalgo.algoutils.tutor.general.json.JsonParameterSetTest;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtMethod;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

import static h06.TestUtils.getCtMethod;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.*;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions4.assertIsNotIteratively;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions4.assertIsNotRecursively;

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

    @ParameterizedTest
    @JsonParameterSetTest("FractalsConcatDataSetBoth.json")
    public void testConcatenateLength(JsonParameterSet params) {
        Context context = params.toContext();
        DrawInstruction[] arr1 = toDrawInstructions(params.get("arr1"));
        DrawInstruction[] arr2 = toDrawInstructions(params.get("arr2"));
        DrawInstruction[] arrResult = callObject(() -> Fractals.concatenate(arr1, arr2), context, result ->
            "An exception occurred while invoking method concatenate");
        assertEquals(arr1.length + arr2.length, arrResult.length, context, result ->
            "The length of the array returned by method concatenate is incorrect");
    }

    @ParameterizedTest
    @JsonParameterSetTest("FractalsConcatDataSetFirst.json")
    public void testConcatenateFirstOnly(JsonParameterSet params) {
        Context context = params.toContext();
        DrawInstruction[] arr1 = toDrawInstructions(params.get("arr1"));
        DrawInstruction[] arr2 = toDrawInstructions(params.get("arr2"));
        DrawInstruction[] arrResult = callObject(() -> Fractals.concatenate(arr1, arr2), context, result ->
            "An exception occurred while invoking method concatenate");

        assertEquals(arr1.length, arrResult.length, context, result ->
            "The length of the array returned by method concatenate is incorrect");
        for (int i = 0; i < arr1.length; i++) {
            final int finalI = i;
            assertEquals(arr1[i], arrResult[i], context, result ->
                "Value at index %d of the returned array differs from expected value".formatted(finalI));
        }
    }

    @ParameterizedTest
    @JsonParameterSetTest("FractalsConcatDataSetSecond.json")
    public void testConcatenateSecondOnly(JsonParameterSet params) {
        Context context = params.toContext();
        DrawInstruction[] arr1 = toDrawInstructions(params.get("arr1"));
        DrawInstruction[] arr2 = toDrawInstructions(params.get("arr2"));
        DrawInstruction[] arrResult = callObject(() -> Fractals.concatenate(arr1, arr2), context, result ->
            "An exception occurred while invoking method concatenate");

        assertEquals(arr2.length, arrResult.length, context, result ->
            "The length of the array returned by method concatenate is incorrect");
        for (int i = 0; i < arr2.length; i++) {
            final int finalI = i;
            assertEquals(arr2[i], arrResult[i], context, result ->
                "Value at index %d of the returned array differs from expected value".formatted(finalI));
        }
    }

    @ParameterizedTest
    @JsonParameterSetTest("FractalsConcatDataSetBoth.json")
    public void testConcatenateBoth(JsonParameterSet params) {
        Context context = params.toContext();
        DrawInstruction[] arr1 = toDrawInstructions(params.get("arr1"));
        DrawInstruction[] arr2 = toDrawInstructions(params.get("arr2"));
        DrawInstruction[] arrResult = callObject(() -> Fractals.concatenate(arr1, arr2), context, result ->
            "An exception occurred while invoking method concatenate");

        assertEquals(arr1.length + arr2.length, arrResult.length, context, result ->
            "The length of the array returned by method concatenate is incorrect");
        for (int i = 0; i < arr1.length; i++) {
            final int finalI = i;
            assertEquals(arr1[i], arrResult[i], context, result ->
                "Value at index %d of the returned array differs from expected value".formatted(finalI));
        }
        for (int i = 0; i < arr2.length; i++) {
            final int finalI = i;
            assertEquals(arr2[i], arrResult[arr1.length + i], context, result ->
                "Value at index %d of the returned array differs from expected value".formatted(finalI));
        }
    }

    @Test
    public void testConcatenateVAnforderung() throws ReflectiveOperationException {
        CtMethod<?> concatenateCtMethod = getCtMethod(Fractals.class, "concatenate", DrawInstruction[].class, DrawInstruction[].class);
        assertIsNotRecursively(concatenateCtMethod, emptyContext(), result -> "Method concatenate is not iterative");

        Iterator<CtElement> concatenateIterator = concatenateCtMethod.getBody().descendantIterator();
        boolean calledArraycopy = false;
        while (!calledArraycopy && concatenateIterator.hasNext()) {
            if (concatenateIterator.next() instanceof CtInvocation<?> ctInvocation) {
                calledArraycopy = ctInvocation.toStringDebug().startsWith("java.lang.System.arraycopy(");
            }
        }
        assertFalse(calledArraycopy, emptyContext(), result -> "Method concatenate calls System.arraycopy");
    }

    private static DrawInstruction[] toDrawInstructions(List<String> drawInstructions) {
        DrawInstruction[] instructions = new DrawInstruction[drawInstructions.size()];
        for (int i = 0; i < instructions.length; i++) {
            instructions[i] = DrawInstruction.valueOf(drawInstructions.get(i));
        }
        return instructions;
    }
}
