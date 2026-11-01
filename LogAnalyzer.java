import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class LogAnalyzer {
    public static void main(String[] args) {
        Path[] files = {
                Paths.get("log1.txt"),
                Paths.get("log2.txt"),
                Paths.get("log3.txt")
        };

        String keyword = "ERROR";
        int totalLines = 0;
        int matchingLines = 0;

        try {
            Files.write(files[0], List.of(
                    "Application started",
                    "ERROR: File not found",
                    "User logged in"
            ));

            Files.write(files[1], List.of(
                    "ERROR: Connection failed",
                    "Request processed",
                    "ERROR: Timeout occurred"
            ));

            Files.write(files[2], List.of(
                    "System running normally",
                    "User logged out",
                    "Application stopped"
            ));

            for (Path file : files) {
                BasicFileAttributes attributes =
                        Files.readAttributes(file, BasicFileAttributes.class);

                System.out.println("\nFile: " + file.getFileName());
                System.out.println("Size: " + attributes.size() + " bytes");

                try (var reader = Files.newBufferedReader(file)) {
                    String line;

                    while ((line = reader.readLine()) != null) {
                        totalLines++;

                        if (line.contains(keyword)) {
                            matchingLines++;
                            System.out.println("Match: " + line);
                        }
                    }
                }
            }

            System.out.println("\nTotal lines across all files: " + totalLines);
            System.out.println("Lines containing \"" + keyword + "\": "
                    + matchingLines);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}