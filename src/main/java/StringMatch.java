import java.util.Arrays;
import java.util.List;

public class StringMatch {

    private double getDistance(CharSequence first, CharSequence second) {
        double DEFAULT_SCALING_FACTOR = 0.1;
        if (first != null && second != null) {
            int[] mtp = matches(first, second);
            double m = (double) mtp[0];
            if (m == 0.0) {
                return 0.0;
            } else {
                double j = (m / (double) first.length() + m / (double) second.length() + (m - (double) mtp[1]) / m) / 3.0;
                double jw = j < 0.7 ? j : j + Math.min(0.1, 1.0 / (double) mtp[3]) * (double) mtp[2] * (1.0 - j);
                return (double) Math.round(jw * 100.0) / 100.0;
            }
        } else {
            throw new IllegalArgumentException("Strings must not be null");
        }
    }

    private int[] matches(CharSequence first, CharSequence second) {
        CharSequence max;
        CharSequence min;
        if (first.length() > second.length()) {
            max = first;
            min = second;
        } else {
            max = second;
            min = first;
        }

        int range = Math.max(max.length() / 2 - 1, 0);
        int[] matchIndexes = new int[min.length()];
        Arrays.fill(matchIndexes, -1);
        boolean[] matchFlags = new boolean[max.length()];
        int matches = 0;

        int transpositions;
        int prefix;
        for (int mi = 0; mi < min.length(); ++mi) {
            char c1 = min.charAt(mi);
            transpositions = Math.max(mi - range, 0);

            for (prefix = Math.min(mi + range + 1, max.length()); transpositions < prefix; ++transpositions) {
                if (!matchFlags[transpositions] && c1 == max.charAt(transpositions)) {
                    matchIndexes[mi] = transpositions;
                    matchFlags[transpositions] = true;
                    ++matches;
                    break;
                }
            }
        }

        char[] ms1 = new char[matches];
        char[] ms2 = new char[matches];
        transpositions = 0;

        for (prefix = 0; transpositions < min.length(); ++transpositions) {
            if (matchIndexes[transpositions] != -1) {
                ms1[prefix] = min.charAt(transpositions);
                ++prefix;
            }
        }

        transpositions = 0;

        for (prefix = 0; transpositions < max.length(); ++transpositions) {
            if (matchFlags[transpositions]) {
                ms2[prefix] = max.charAt(transpositions);
                ++prefix;
            }
        }

        transpositions = 0;

        for (prefix = 0; prefix < ms1.length; ++prefix) {
            if (ms1[prefix] != ms2[prefix]) {
                ++transpositions;
            }
        }

        prefix = 0;

        for (int mi = 0; mi < min.length() && first.charAt(mi) == second.charAt(mi); ++mi) {
            ++prefix;
        }

        return new int[]{matches, transpositions / 2, prefix, max.length()};
    }

    public double[][] identifyMatches(List<String> first, List<String> second) {
        double[][] percentMatcher = new double[first.size()][second.size()];
        for (int i = 0; i < first.size(); i++) {
            for (int j = 0; j < second.size(); j++) {
                double d = getDistance(first.get(i), second.get(j));
                percentMatcher[i][j] = d;
            }
        }
        return percentMatcher;
    }


}