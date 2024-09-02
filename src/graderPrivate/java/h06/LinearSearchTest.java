package h06;

import h06.problems.LinearSearch;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.tudalgo.algoutils.tutor.general.SpoonUtils;
import org.tudalgo.algoutils.tutor.general.assertions.Context;
import org.tudalgo.algoutils.tutor.general.json.JsonParameterSet;
import org.tudalgo.algoutils.tutor.general.json.JsonParameterSetTest;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtParameter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.*;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions4.assertIsNotIteratively;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions4.assertIsNotRecursively;

@TestForSubmission
public class LinearSearchTest {

    private int[] arr;
    private int target;
    private int expectedIndex;
    private Context baseContext;

    public void setup(JsonParameterSet params) {
        List<Integer> arrParam = params.get("arr");
        arr = new int[arrParam.size()];
        for (int i = 0; i < arrParam.size(); i++) {
            arr[i] = arrParam.get(i);
        }
        target = params.get("target");
        expectedIndex = params.get("expectedIndex");
        baseContext = params.toContext("expectedIndex");
    }

    @ParameterizedTest
    @JsonParameterSetTest("LinearSearchDataSet.json")
    public void testLinearSearchRecursive(JsonParameterSet params) {
        setup(params);
        int actual = callObject(() -> LinearSearch.linearSearchRecursive(arr, target), baseContext, result ->
            "An exception occurred while invoking method linearSearchRecursive");
        assertEquals(expectedIndex, actual, baseContext, result -> "Method linearSearchRecursive returned an incorrect value");
    }

    @ParameterizedTest
    @JsonParameterSetTest("LinearSearchDataSet.json")
    public void testLinearSearchRecursiveHelperCall(JsonParameterSet params) throws ReflectiveOperationException {
        setup(params);
        Method linearSearchRecursiveHelperMethod = LinearSearch.class.getDeclaredMethod("linearSearchRecursiveHelper", int[].class, int.class, int.class);
        List<Map<String, ?>> args = new ArrayList<>();
        Answer<?> answer = invocation -> {
            if (invocation.getMethod().equals(linearSearchRecursiveHelperMethod)) {
                args.add(Map.of(
                    "arr", invocation.getArgument(0),
                    "target", invocation.getArgument(1),
                    "index", invocation.getArgument(2)
                ));
                return -1;
            } else {
                return invocation.callRealMethod();
            }
        };

        try (MockedStatic<LinearSearch> ignored = Mockito.mockStatic(LinearSearch.class, answer)) {
            call(() -> LinearSearch.linearSearchRecursive(arr, target), baseContext, result ->
                "An exception occurred while invoking method linearSearchRecursive");
        }

        assertEquals(1, args.size(), baseContext, result ->
            "Method linearSearchRecursive did not invoke linearSearchRecursiveHelper exactly once");
        Map<String, ?> invocation = args.get(0);
        assertSame(arr, invocation.get("arr"), baseContext, result ->
            "Method linearSearchRecursive did not invoke linearSearchRecursiveHelper with parameter arr");
        assertEquals(target, invocation.get("target"), baseContext, result ->
            "Method linearSearchRecursive did not invoke linearSearchRecursiveHelper with parameter target");
        assertEquals(0, invocation.get("index"), baseContext, result ->
            "Method linearSearchRecursive did not invoke linearSearchRecursiveHelper with index = 0");
    }

    @ParameterizedTest
    @JsonParameterSetTest("LinearSearchDataSet.json")
    public void testLinearSearchRecursiveHelper(JsonParameterSet params) {
        setup(params);
        int actual = callObject(() -> LinearSearch.linearSearchRecursiveHelper(arr, target, 0), baseContext, result ->
            "An exception occurred while invoking method linearSearchRecursiveHelper");
        assertEquals(expectedIndex, actual, baseContext, result -> "Method linearSearchRecursiveHelper returned an incorrect value");
    }

    @ParameterizedTest
    @JsonParameterSetTest("LinearSearchDataSet.json")
    public void testLinearSearchRecursiveHelperTargetNotInArray(JsonParameterSet params) {
        if (params.getInt("expectedIndex") != -1) {
            System.out.println("Test ignored. Value target exists in arr");
            return;
        }

        setup(params);
        int actual = callObject(() -> LinearSearch.linearSearchRecursiveHelper(arr, target, 0), baseContext, result ->
            "An exception occurred while invoking method linearSearchRecursiveHelper");
        assertEquals(-1, actual, baseContext, result -> "Method linearSearchRecursiveHelper returned an incorrect value");
    }

    @Test
    public void testLinearSearchRecursiveHelperVAnforderung() {
        assertIsNotIteratively(getCtMethod("linearSearchRecursiveHelper", int[].class, int.class, int.class),
            emptyContext(),
            result -> "Method linearSearchRecursiveHelper is not recursive");
    }

    @ParameterizedTest
    @JsonParameterSetTest("LinearSearchDataSet.json")
    public void testLinearSearchIterative(JsonParameterSet params) {
        setup(params);
        int actual = callObject(() -> LinearSearch.linearSearchIterative(arr, target), baseContext, result ->
            "An exception occurred while invoking method linearSearchIterative");
        assertEquals(expectedIndex, actual, baseContext, result -> "Method linearSearchIterative returned an incorrect value");
    }

    @ParameterizedTest
    @JsonParameterSetTest("LinearSearchDataSet.json")
    public void testLinearSearchIterativeTargetNotInArray(JsonParameterSet params) {
        if (params.getInt("expectedIndex") != -1) {
            System.out.println("Test ignored. Value target exists in arr");
            return;
        }

        setup(params);
        int actual = callObject(() -> LinearSearch.linearSearchIterative(arr, target), baseContext, result ->
            "An exception occurred while invoking method linearSearchIterative");
        assertEquals(-1, actual, baseContext, result -> "Method linearSearchIterative returned an incorrect value");
    }

    @Test
    public void testLinearSearchIterativeVAnforderung() {
        assertIsNotRecursively(getCtMethod("linearSearchIterative", int[].class, int.class),
            emptyContext(),
            result -> "Method linearSearchIterative is not recursive");
    }

    private static CtMethod<?> getCtMethod(String methodName, Class<?>... paramTypes) {
        return SpoonUtils.getType(LinearSearch.class.getName())
            .getMethodsByName(methodName)
            .stream()
            .filter(ctMethod -> {
                List<CtParameter<?>> parameters = ctMethod.getParameters();
                boolean result = parameters.size() == paramTypes.length;
                for (int i = 0; result && i < parameters.size(); i++) {
                    result = parameters.get(i).getType().getQualifiedName().equals(paramTypes[i].getTypeName());
                }
                return result;
            })
            .findAny()
            .orElseThrow();
    }
}