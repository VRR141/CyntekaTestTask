import org.junit.Test;
import org.junit.jupiter.api.Disabled;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class InputParserTest {

    private InputParser inputParser;
    static final String pathname = "input.txt";

    @Disabled
    @Test
    public void inputParserTest(){
        List<String> firstExpected = Arrays.asList("гвоздь", "шуруп", "краска синяя", "ведро для воды");
        List<String> secondExpected = Arrays.asList("краска", "корыто для воды", "шуруп 3х1.5");

        inputParser = new InputParser(pathname);
        inputParser.parse();
        List<String> firstActual = inputParser.getFirst();
        List<String> secondActual = inputParser.getSecond();

        assertEquals(firstExpected, firstActual);
        assertEquals(secondExpected, secondActual);
    }
}
