import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class FolderTreeReport {
    public static void main(String[] args) {
        Path folder = Paths.get("testfolder");
        Path report = Paths.get("folder_report.txt");

        try {
            Files.createDirectories(folder.resolve("documents"));
            Files.createDirectories(folder.resolve("images"));

            Files.writeString(folder.resolve("notes.txt"), "Sample notes");
            Files.writeString(folder.resolve("documents/report.txt"),
                    "Sample document");
            Files.writeString(folder.resolve("images/photo.txt"),
                    "Sample image file");

            DateTimeFormatter formatter = DateTimeFormatter
                    .ofPattern("dd-MM-yyyy HH:mm:ss")
                    .withZone(ZoneId.systemDefault());

            try (var writer = Files.newBufferedWriter(report);
                 var paths = Files.walk(folder)) {

                writer.write("FOLDER TREE REPORT\n");
                writer.write("==================\n\n");

                final long[] fileCount = {0};
                final long[] totalSize = {0};

                for (Path path : (Iterable<Path>) paths::iterator) {
                    if (Files.isRegularFile(path)) {
                        BasicFileAttributes attributes =
                                Files.readAttributes(
                                        path, BasicFileAttributes.class);

                        String name = folder.relativize(path).toString();
                        long size = attributes.size();
                        String modified = formatter.format(
                                attributes.lastModifiedTime().toInstant());

                        writer.write("Name: " + name + "\n");
                        writer.write("Size: " + size + " bytes\n");
                        writer.write("Modified: " + modified + "\n\n");

                        fileCount[0]++;
                        totalSize[0] += size;
                    }
                }

                writer.write("==================\n");
                writer.write("Total files: " + fileCount[0] + "\n");
                writer.write("Total size: " + totalSize[0] + " bytes\n");
            }

            System.out.println("Folder report generated successfully.");
            System.out.println("Report saved to: " + report.toAbsolutePath());

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}