package h06;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIf;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static h06.TestConstants.TEST_ITERATIONS;

@DisabledIf("org.tudalgo.algoutils.tutor.general.Utils#isJagrRun()")
public class TestJsonGenerators {

    @Test
    public void generateLinearSearchDataSet() throws IOException {
        TestUtils.generateJsonTestData(
            (mapper, index, rnd) -> {
                boolean containsTarget = rnd.nextBoolean();
                int target = rnd.nextInt();
                List<Integer> arr = rnd.ints()
                    .distinct()
                    .filter(i -> i != target)
                    .limit(rnd.nextInt(10, 20))
                    .boxed()
                    .collect(Collectors.toList());
                int expectedIndex;
                if (containsTarget) {
                    expectedIndex = rnd.nextInt(arr.size());
                    arr.set(expectedIndex, target);
                } else {
                    expectedIndex = -1;
                }

                ArrayNode arrayNode = mapper.createArrayNode();
                arr.forEach(arrayNode::add);
                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("target", target);
                objectNode.set("arr", arrayNode);
                objectNode.put("expectedIndex", expectedIndex);

                return objectNode;
            },
            TEST_ITERATIONS,
            "LinearSearchDataSet.json"
        );
    }
}
