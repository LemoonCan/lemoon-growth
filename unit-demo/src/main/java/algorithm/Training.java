package algorithm;


import java.util.*;

/**
 * @author lee
 * @since 2024/11/13
 */
public class Training {
    public static void main(String[] args) {
        Training training = new Training();
        System.out.println(training.findSubstring("a", new String[]{"a","a"}));
    }

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        int x = words[0].length(), y = words.length;

        for (int i = 0; i < x && i + x * y <= s.length(); i++) {
            Map<String, Integer> diff = new HashMap<>();
            for (String word : words) {
                diff.put(word, diff.getOrDefault(word, 0) + 1);
            }

            for (int j = 0; j < y; j++) {
                String word = s.substring(i + j * x, i + (j + 1) * x);
                diff.put(word, diff.getOrDefault(word, 0) - 1);
                if (diff.get(word) == 0) {
                    diff.remove(word);
                }
            }

            for (int k = i; k + x * y <= s.length(); k += x) {
                if (k != i) {
                    String skip = s.substring(k - x, k);
                    diff.put(skip, diff.getOrDefault(skip, 0) + 1);
                    if (diff.get(skip) == 0) {
                        diff.remove(skip);
                    }

                    String move = s.substring(k + (y - 1) * x, k + y * x);
                    diff.put(move, diff.getOrDefault(move, 0) - 1);
                    if (diff.get(move) == 0) {
                        diff.remove(move);
                    }
                }
                if (diff.isEmpty()) {
                    result.add(k);
                }
            }

        }
        return result;
    }
}
