import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class OutputResultTest {

    private OutputResult outputResult;

    @Disabled
    @Test
    public void outputResultTest(){
        List<String> expected = Arrays.asList("гвоздь:?", "шуруп:шуруп 3х1.5", "краска синяя:краска", "ведро для воды:корыто для воды");

        outputResult = new OutputResult( "output.txt");

        InputParser inputParser = Mockito.mock(InputParser.class);
        Mockito.when(inputParser.getFirst()).thenReturn(Arrays.asList("гвоздь", "шуруп", "краска синяя", "ведро для воды"));
        Mockito.when(inputParser.getSecond()).thenReturn(Arrays.asList("краска", "корыто для воды", "шуруп 3х1.5"));
        var first = inputParser.getFirst();
        var second = inputParser.getSecond();

        StringMatch stringMatch = Mockito.mock(StringMatch.class);
        double[][] matches = {
                {0.0, 0.49, 0.0},
                {0.46, 0.42, 0.9},
                {0.92, 0.53, 0.45},
                {0.41, 0.75, 0.44}
        };
        Mockito.when(stringMatch.identifyMatches(first, second)).thenReturn(matches);

        outputResult.result(matches, first, second);
        var actual = outputResult.getResult();

        Assertions.assertEquals(expected, actual);
    }
}
