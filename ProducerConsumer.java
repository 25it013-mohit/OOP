import java.util.Random;

class SharedBuffer {
    private final int[] buffer = new int[3];
    private int count = 0;
    private int in = 0;
    private int out = 0;

    public synchronized void produce(int item) throws InterruptedException {
        while (count == buffer.length) {
            wait();
        }

        buffer[in] = item;
        in = (in + 1) % buffer.length;
        count++;

        System.out.println(Thread.currentThread().getName()
                + " produced: " + item);

        notifyAll();
    }

    public synchronized int consume() throws InterruptedException {
        while (count == 0) {
            wait();
        }

        int item = buffer[out];
        out = (out + 1) % buffer.length;
        count--;

        System.out.println(Thread.currentThread().getName()
                + " consumed: " + item);

        notifyAll();
        return item;
    }
}

public class ProducerConsumer {
    public static void main(String[] args) {
        SharedBuffer buffer = new SharedBuffer();
        int items = 10;
        Random random = new Random();

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= items; i++) {
                    buffer.produce(i);
                    Thread.sleep(random.nextInt(300) + 100);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Producer");

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= items; i++) {
                    Thread.sleep(random.nextInt(300) + 100);
                    buffer.consume();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Consumer");

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("All items produced and consumed.");
    }
}