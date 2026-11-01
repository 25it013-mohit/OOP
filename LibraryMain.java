abstract class LibraryMedia {
    private final String title;
    private final String mediaId;

    public LibraryMedia(String mediaId, String title) {
        this.mediaId = mediaId;
        this.title = title;
    }

    public String getMediaId() {
        return mediaId;
    }

    public String getTitle() {
        return title;
    }

    public abstract double calculateLateFee(int daysOverdue);
}

class Book extends LibraryMedia {
    private static final double DAILY_RATE = 0.50;
    private static final double MAX_FEE = 10.00;

    public Book(String mediaId, String title) {
        super(mediaId, title);
    }

    @Override
    public double calculateLateFee(int daysOverdue) {
        if (daysOverdue <= 0) return 0.0;
        return Math.min(daysOverdue * DAILY_RATE, MAX_FEE);
    }
}

class MovieDVD extends LibraryMedia {
    private static final double DAILY_RATE = 1.50;
    private static final double FLAT_PENALTY = 3.00;

    public MovieDVD(String mediaId, String title) {
        super(mediaId, title);
    }

    @Override
    public double calculateLateFee(int daysOverdue) {
        if (daysOverdue <= 0) return 0.0;
        return FLAT_PENALTY + (daysOverdue * DAILY_RATE);
    }
}

class Magazine extends LibraryMedia {
    private static final double DAILY_RATE = 0.25;
    private static final int GRACE_PERIOD_DAYS = 2;

    public Magazine(String mediaId, String title) {
        super(mediaId, title);
    }

    @Override
    public double calculateLateFee(int daysOverdue) {
        if (daysOverdue <= GRACE_PERIOD_DAYS) return 0.0;
        return (daysOverdue - GRACE_PERIOD_DAYS) * DAILY_RATE;
    }
}

class MediaReturn {
    private final LibraryMedia media;
    private final int daysOverdue;

    public MediaReturn(LibraryMedia media, int daysOverdue) {
        this.media = media;
        this.daysOverdue = daysOverdue;
    }

    public LibraryMedia getMedia() {
        return media;
    }

    public int getDaysOverdue() {
        return daysOverdue;
    }

    public double computeFee() {
        return media.calculateLateFee(daysOverdue);
    }
}

public class LibraryMain {
    public static void main(String[] args) {
        MediaReturn[] returnedBatch = new MediaReturn[] {
                new MediaReturn(new Book("BK-101", "Clean Code"), 25),
                new MediaReturn(new MovieDVD("DVD-301", "Oppenheimer"), 4),
                new MediaReturn(new Magazine("MAG-501", "Wired Magazine"), 2),
                new MediaReturn(new Magazine("MAG-502", "National Geographic"), 6),
                new MediaReturn(new Book("BK-102", "Design Patterns"), 6)
        };

        double totalBatchFees = 0.0;

        System.out.printf("%-10s | %-24s | %-12s | %-12s%n", "ID", "Title", "Days Overdue", "Late Fee ($)");
        System.out.println("------------------------------------------------------------------");

        for (MediaReturn item : returnedBatch) {
            double fee = item.computeFee();
            totalBatchFees += fee;

            System.out.printf("%-10s | %-24s | %-12d | $%-11.2f%n",
                    item.getMedia().getMediaId(),
                    item.getMedia().getTitle(),
                    item.getDaysOverdue(),
                    fee
            );
        }

        System.out.println("------------------------------------------------------------------");
        System.out.printf("Total Batch Outstanding Fees: $%.2f%n", totalBatchFees);
    }
}