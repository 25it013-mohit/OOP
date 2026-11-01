public class CinemaShow {
    private String title;
    private int SeatsAvailable;
    private final int capacity;
    private static int TotalBooked=0;

    public CinemaShow(String title, int capacity)
    {
        this.title = title;
        this.capacity = capacity;
        this.SeatsAvailable = capacity;
    }
    public CinemaShow(String title)
    {
        this(title,100);
    }
    public boolean book(int n)
    {
        if(n<=this.SeatsAvailable)
        {
            this.SeatsAvailable -= n;
            TotalBooked += n;
            return true;
        }
        return false;
    }
    public void cancel(int n)
    {
        this.SeatsAvailable += n;
        if(this.SeatsAvailable > this.capacity)
        {
            this.SeatsAvailable = this.capacity;
        }
    }
    public int getSeatsAvailable()
    {
        return this.SeatsAvailable;
    }
    public int getTotalBooked()
    {
        return TotalBooked;
    }

    static void main(String[] args) {
        CinemaShow movie = new CinemaShow("Avengers: Doomsday", 100);
        System.out.println("Show created with capacity: "+movie.getSeatsAvailable());
        System.out.println("\nBooking 40 seats");
        boolean b1 = movie.book(40);
        System.out.println("Result: "+b1 + " | Remaining: " + movie.getSeatsAvailable());
        System.out.println("\nBooking 70 seats");
        boolean b2 = movie.book(70);
        System.out.println("Result: " + b2 + " | Remaining: " + movie.getSeatsAvailable());
        System.out.println("\nCancelling 25 seats");
        movie.cancel(25);
        System.out.println("Remaining: " + movie.getSeatsAvailable());
        System.out.println("\nCancelling 30 seats");
        movie.cancel(30);
        System.out.println("\nRemaining: " + movie.getSeatsAvailable());
        System.out.println("\nTotal seats booked: " + movie.getTotalBooked());
    }
}