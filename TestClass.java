class InvalidQuantityException extends Exception {
    public InvalidQuantityException(String message) {
        super(message);
    }
}
class OutOfStockException extends Exception {
    private final int shortfall;

    public OutOfStockException(String item, int shortfall) {
        super("Not enough stock for '" + item + "'. Shortfall: " + shortfall);
        this.shortfall = shortfall;
    }

    public int getShortfall() {
        return shortfall;
    }
}
class Warehouse {
    private int laptopStock = 10;
    private int mouseStock = 5;
    public void issue(String item, int qty) throws InvalidQuantityException, OutOfStockException {
        if (qty <= 0) {
            throw new InvalidQuantityException("Requested quantity (" + qty + ") must be greater than zero.");
        }

        int available = 0;
        switch (item) {
            case "Laptop":
                available = laptopStock;
                break;
            case "Mouse":
                available = mouseStock;
                break;
            default:
                available = 0;
                break;
        }

        if (available < qty) {
            int shortfall = qty - available;
            throw new OutOfStockException(item, shortfall);
        }
        switch (item) {
            case "Laptop": laptopStock -= qty; break;
            case "Mouse":  mouseStock -= qty; break;
        }

        System.out.println("SUCCESS: Issued " + qty + " units of '" + item + "'.");
    }
}

public class TestClass {
    private static void processRequest(Warehouse warehouse, int requestNum, String item, int qty) {
        System.out.println("\nProcessing Request #" + requestNum + ": [Item: " + item + ", Qty: " + qty + "]");
        try {
            warehouse.issue(item, qty);
        } catch (InvalidQuantityException e) {
            System.err.println("Quantity Error: " + e.getMessage());
        } catch (OutOfStockException e) {
            System.err.println("Inventory Error: " + e.getMessage() + " (Shortfall detected: " + e.getShortfall() + ")");
        }
    }

    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();

        System.out.println("--- Starting Batch Stock Processing ---");
        processRequest(warehouse, 1, "Laptop", 3);
        processRequest(warehouse, 2, "Mouse", -2);
        processRequest(warehouse, 3, "Laptop", 12);
        processRequest(warehouse, 4, "Keyboard", 1);
        processRequest(warehouse, 5, "Mouse", 4);

        System.out.println("\n--- Batch Processing Completed ---");
    }
}
