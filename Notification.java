public class Notification {
    public interface Notifier {
        void send(String message);
    }
    public interface Urgent {}

    public static void main(String[] args) {
        String message = "System update scheduled for midnight.";
        Notifier[] notifiers = new Notifier[] {
                msg -> System.out.println("[Standard Email] " + msg),
                (Notifier & Urgent) msg -> System.out.println("[URGENT SMS] " + msg),
                (Notifier & Urgent) msg -> System.out.println("[URGENT Push Notification] " + msg),
                msg -> System.out.println("[Standard Slack] " + msg)
        };
        broadcast(notifiers, message);
    }

    public static void broadcast(Notifier[] notifiers, String message) {
        for (Notifier notifier : notifiers) {
            if (notifier instanceof Urgent) {
                notifier.send(message);
                notifier.send(message);
            } else {
                notifier.send(message);
            }
        }
    }
}