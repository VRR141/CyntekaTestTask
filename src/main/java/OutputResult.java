import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OutputResult {

    private final String pathname;
    private final double percentForMerge = 0.55;

    public List<String> getResult() {
        List<String> copy = new ArrayList<>(result);
        return copy;
    }

    private List<String> result;

    public OutputResult(String pathname) {
        this.pathname = pathname;
    }

    public void initFile() {
        File file = new File(pathname);
        if (file.exists()) {
            System.out.println("Do you want overwrite: " + file + " y/n");
            Scanner scanner = new Scanner(System.in);
            if (scanner.nextLine().equals("n")) {
                throw new RuntimeException("canceled");
            }
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void result(double[][] matches, List<String> first, List<String> second) {
        result = new ArrayList<>();
        if (first.size() == 1 && second.size() == 1){
            result.add(first.get(0).concat(":").concat(second.get(0)));
            return;
        }

        if (first.size() >= second.size()) {
            for (int i = 0; i < first.size(); i++) {
                String temp = first.get(i);
                int position = 0;
                double max = matches[i][0];
                for (int j = 0; j < matches[i].length; j++) {
                    if (matches[i][j] - max > 0.01) {
                        max = matches[i][j];
                        position = j;
                    }
                }
                if (max - percentForMerge > 0.01) {
                    result.add(temp.concat(":").concat(second.get(position)));
                } else {
                    result.add(temp.concat(":").concat("?"));
                }
            }
            return;
        }

        if (first.size() < second.size()){
            List<Integer> havePair = new ArrayList<>();
            for (int i = 0; i < first.size(); i++) {
                String temp = first.get(i);
                int position = 0;
                double max = matches[i][0];
                for (int j = 0; j < matches[i].length; j++) {
                    if (matches[i][j] - max > 0.01) {
                        max = matches[i][j];
                        position = j;
                    }
                }
                if (max - percentForMerge > 0.01) {
                    result.add(temp.concat(":").concat(second.get(position)));
                } else {
                    result.add(temp.concat(":").concat("?"));
                }
                havePair.add(position);
            }
            for (int i = 0; i < second.size(); i++){
                if (!havePair.contains(i)){
                    result.add(second.get(i).concat(":").concat("?"));
                }
            }
        }
    }

    public boolean writeResult() {
        initFile();
        try (BufferedWriter out = new BufferedWriter(new FileWriter(pathname))) {
            for (String s : result) {
                out.write(s + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

}
