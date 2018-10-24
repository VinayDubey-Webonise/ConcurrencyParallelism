public class Philosopher implements Runnable {

    private final Fork forkLeft;
    private Fork forkRight;

    Philosopher(Fork forkLeft, Fork forkRight) {
        this.forkLeft = forkLeft;
        this.forkRight = forkRight;
    }

    @Override
    public void run() {
        while (true) {
            carryTask();
            synchronized (forkLeft) {
                Printer.show(Thread.currentThread().getName() + " has picked left fork");
                carryTask();
                synchronized (forkRight) {
                    Printer.show(Thread.currentThread().getName() + " has picked right fork");
                    carryTask();
                }
            }
            Printer.show(Thread.currentThread().getName() + " Thinking");
        }
    }

    private void carryTask() {
        try {
            Thread.sleep(((int) (Math.random() * 100)));
        } catch (InterruptedException e) {
            Printer.show("Error: " + e);
        }
    }
}
