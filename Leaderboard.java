import java.util.*;

public class Leaderboard {
    public static void main(String[] args) {
        Map<String, Integer> scores = new HashMap<>();

        scores.put("Rahul", 850);
        scores.put("Priya", 920);
        scores.put("Amit", 780);
        scores.put("Neha", 920);
        scores.put("Karan", 870);
        scores.put("Riya", 800);

        int topN = 3;

        List<Map.Entry<String, Integer>> players =
                new ArrayList<>(scores.entrySet());

        players.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> p1,
                               Map.Entry<String, Integer> p2) {
                int scoreComparison =
                        p2.getValue().compareTo(p1.getValue());

                if (scoreComparison == 0) {
                    return p1.getKey().compareTo(p2.getKey());
                }

                return scoreComparison;
            }
        });

        System.out.println("Top " + topN + " Players:");
        System.out.println("--------------------");

        for (int i = 0; i < Math.min(topN, players.size()); i++) {
            Map.Entry<String, Integer> player = players.get(i);

            System.out.println((i + 1) + ". "
                    + player.getKey() + " - " + player.getValue());
        }
    }
}