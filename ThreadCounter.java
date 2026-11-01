class Counter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

class CounterThread extends Thread {
    private Counter counter;
    private int times;

    CounterThread(Counter counter, int times) {
        this.counter = counter;
        this.times = times;
    }

    @Override
    public void run() {
        for (int i = 0; i < times; i++) {
            counter.increment();
        }
    }
}

public class ThreadCounter {
    public static void main(String[] args) throws InterruptedException {
        int numberOfThreads = 10;
        int incrementsPerThread = 100000;

        Counter counter = new Counter();
        Thread[] threads = new Thread[numberOfThreads];

        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new CounterThread(counter, incrementsPerThread);
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        int expected = numberOfThreads * incrementsPerThread;

        System.out.println("Expected count: " + expected);
        System.out.println("Actual count: " + counter.getCount());
    }
}