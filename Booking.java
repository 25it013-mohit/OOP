class SeatBooking {
    private int seatsLeft = 5;
    public synchronized boolean book() {
        if (seatsLeft > 0) {
            seatsLeft--;
            return true;
        }
        return false;
    }

    public int getSeatsLeft() {
        return seatsLeft;
    }
}

class BookingThread extends Thread {
    private SeatBooking booking;
    private int successfulBookings = 0;

    BookingThread(SeatBooking booking) {
        this.booking = booking;
    }

    @Override
    public void run() {
        if (booking.book()) {
            successfulBookings++;
        }
    }

    public int getSuccessfulBookings() {
        return successfulBookings;
    }
}

public class Booking {
    public static void main(String[] args) throws InterruptedException {
        SeatBooking booking = new SeatBooking();
        int numberOfThreads = 10;
        BookingThread[] threads = new BookingThread[numberOfThreads];
        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new BookingThread(booking);
            threads[i].start();
        }
        for (int i = 0; i < numberOfThreads; i++) {
            threads[i].join();
        }
        int successful = 0;
        for (BookingThread thread : threads) {
            successful += thread.getSuccessfulBookings();
        }
        System.out.println("Successful bookings: " + successful);
        System.out.println("Seats left: " + booking.getSeatsLeft());
    }
}