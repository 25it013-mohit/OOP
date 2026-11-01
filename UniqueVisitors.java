import java.util.*;

public class UniqueVisitors {
    public static void main(String[] args) {
        List<String> loginNames = Arrays.asList(
                "Rahul", "Priya", "Amit", "Rahul",
                "Neha", "Priya", "Karan", "Amit"
        );

        HashSet<String> uniqueUsers = new HashSet<>(loginNames);

        LinkedHashSet<String> orderedUsers = new LinkedHashSet<>(loginNames);

        System.out.println("Unique visitors count: " + uniqueUsers.size());

        System.out.println("First three distinct names:");

        int count = 0;
        for (String name : orderedUsers) {
            if (count == 3) {
                break;
            }

            System.out.println(name);
            count++;
        }
    }
}