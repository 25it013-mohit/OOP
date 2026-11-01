class DatabaseConnection implements AutoCloseable {

    public DatabaseConnection() {
        System.out.println("Database connection opened");
    }

    @Override
    public void close() {
        System.out.println("Database connection closed");
    }
}

public class Main {
    public static void main(String[] args) {

        try (DatabaseConnection db = new DatabaseConnection()) {

            System.out.println("Using database...");
            throw new RuntimeException("Something went wrong!");

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}