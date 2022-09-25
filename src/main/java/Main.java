import java.util.Arrays;

public class Main {

    static final String pathname = "input.txt";
    static final String pathnameOut = "output.txt";

    public static void main(String[] args) {
        InputParser inputParser = new InputParser(pathname);
        inputParser.parse();
        var first = inputParser.getFirst();
        var second = inputParser.getSecond();
        StringMatch stringMatch = new StringMatch();
        var matches = stringMatch.identifyMatches(first, second);
        OutputResult outputResult = new OutputResult(pathnameOut);
        outputResult.result(matches, first, second);
        if (outputResult.writeResult()) {
            System.out.println("done");
        }
    }


}
