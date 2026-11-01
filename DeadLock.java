class DeadlockDemo {
    private static final Object lockA = new Object();
    private static final Object lockB = new Object();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Demonstrating Deadlock:");

        Thread t1 = new Thread(() -> {
            synchronized (lockA) {
                System.out.println("Thread 1 acquired Lock A");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }

                System.out.println("Thread 1 waiting for Lock B");

                synchronized (lockB) {
                    System.out.println("Thread 1 acquired Lock B");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lockB) {
                System.out.println("Thread 2 acquired Lock B");

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }

                System.out.println("Thread 2 waiting for Lock A");

                synchronized (lockA) {
                    System.out.println("Thread 2 acquired Lock A");
                }
            }
        });

        t1.start();
        t2.start();

        t1.join(500);
        t2.join(500);

        if (t1.isAlive() && t2.isAlive()) {
            System.out.println("Deadlock detected!");
        }

        System.out.println("\nDemonstrating Deadlock Prevention:");

        Thread t3 = new Thread(() -> performTask("Thread 3"));
        Thread t4 = new Thread(() -> performTask("Thread 4"));

        t3.start();
        t4.start();

        t3.join();
        t4.join();

        System.out.println("Both threads completed successfully.");
    }

    private static void performTask(String name) {
        synchronized (lockA) {
            System.out.println(name + " acquired Lock A");

            synchronized (lockB) {
                System.out.println(name + " acquired Lock B");
                System.out.println(name + " completed task");
            }
        }
    }
}