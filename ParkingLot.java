public class ParkingLot {
    private int twoWheelers = 0;
    private int fourWheelers = 0;
    private final int twoCap;
    private final int fourCap;
    private static long revenue = 0;
    public ParkingLot(int twoCap, int fourCap) {
        this.twoCap = twoCap;
        this.fourCap = fourCap;
    }
    public void park(String type) {
        if (type.equalsIgnoreCase("two")) {
            if (this.twoWheelers < this.twoCap) {
                this.twoWheelers++;
                revenue += 20;
                System.out.println("Parked a two-wheeler successfully.");
            } else {
                System.out.println("parking full for two wheeler");
            }
        } else if (type.equalsIgnoreCase("four")) {
            if (this.fourWheelers < this.fourCap) {
                this.fourWheelers++;
                revenue += 40;
                System.out.println("Parked a four-wheeler successfully.");
            } else {
                System.out.println("parking full for four wheeler");
            }
        } else {
            System.out.println("Invalid vehicle type.");
        }
    }
    public void leave(String type) {
        if (type.equalsIgnoreCase("two")) {
            if (this.twoWheelers > 0) {
                this.twoWheelers--;
                System.out.println("A two-wheeler left the lot.");
            } else {
                System.out.println("No two-wheelers are currently parked.");
            }
        } else if (type.equalsIgnoreCase("four")) {
            if (this.fourWheelers > 0) {
                this.fourWheelers--;
                System.out.println("A four-wheeler left the lot.");
            } else {
                System.out.println("No four-wheelers are currently parked.");
            }
        } else {
            System.out.println("Invalid vehicle type.");
        }
    }
    public int getTwoWheelers() { return this.twoWheelers; }
    public int getFourWheelers() { return this.fourWheelers; }
    public static long getRevenue() { return revenue; }
    public static void main(String[] args) {
        ParkingLot lot = new ParkingLot(2, 1);
        lot.park("two");
        lot.park("two");
        lot.park("two");
        lot.park("four");
        lot.park("four");
        lot.leave("two");
        lot.park("two");
        lot.leave("four");
        lot.leave("four");
        System.out.println("Current Two-Wheelers Parked: " + lot.getTwoWheelers());
        System.out.println("Current Four-Wheelers Parked: " + lot.getFourWheelers());
        System.out.println("Total Revenue Generated: " + ParkingLot.getRevenue());
    }
}