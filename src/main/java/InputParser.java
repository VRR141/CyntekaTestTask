import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputParser {

    private final String pathname;
    private List<String> first;
    private List<String> second;

    public InputParser(String pathname) {
        this.pathname = pathname;
    }

    public void parse() {
        initLists();
        File file = new File(pathname);
        if (!file.exists()) {
            throw new RuntimeException("file not found");
        }
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            while (in.ready()) {
                int t = 0;
                String tempFirst;
                while ((tempFirst = in.readLine()).equals("")){
                    continue;
                }
                if (tempFirst.length() == 1) {
                    try {
                        t = Integer.parseInt(tempFirst);
                    } catch (NumberFormatException e) {
                        continue;
                    }
                }
                for (int i = 0; i < t; i++) {
                    String temp1 = in.readLine();
                    if (temp1.equals("")) {
                        i--;
                        continue;
                    }
                    first.add(temp1);
                }
                String tempSecond;
                while ((tempSecond = in.readLine()).equals("")){
                    continue;
                }

                if (tempSecond.length() == 1) {
                    try {
                        t = Integer.parseInt(tempSecond);
                    } catch (NumberFormatException e) {
                        continue;
                    }
                }
                for (int i = 0; i < t; i++) {
                    String temp1 = in.readLine();
                    if (temp1.equals("")) {
                        i--;
                        continue;
                    }
                    second.add(temp1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initLists() {
        first = new ArrayList<>();
        second = new ArrayList<>();
    }

    public List<String> getFirst() {
        List<String> copy = new ArrayList<>(first);
        return copy;
    }

    public List<String> getSecond() {
        List<String> copy = new ArrayList<>(second);
        return copy;
    }

}
