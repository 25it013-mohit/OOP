import java.util.*;

public class WordFrequency {
    public static void main(String[] args) {
        String paragraph = "Java is easy and Java is powerful. "
                + "Java is popular and easy to learn.";

        Map<String, Integer> frequency = new HashMap<>();

        String[] words = paragraph.toLowerCase()
                .replaceAll("[^a-z0-9\\s]", "")
                .split("\\s+");

        for (String word : words) {
            frequency.put(word, frequency.getOrDefault(word, 0) + 1);
        }

        List<Map.Entry<String, Integer>> entries =
                new ArrayList<>(frequency.entrySet());

        entries.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> a,
                               Map.Entry<String, Integer> b) {
                int result = b.getValue().compareTo(a.getValue());

                if (result == 0) {
                    return a.getKey().compareTo(b.getKey());
                }

                return result;
            }
        });

        System.out.println("Word Frequencies (Highest First):");

        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}