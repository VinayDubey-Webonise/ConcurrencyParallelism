import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Philosopher implements Runnable {

    private final Fork forkLeft;
    private Fork forkRight;
    static Lock lock = new ReentrantLock();

    Philosopher(Fork forkLeft, Fork forkRight) {
        this.forkLeft = forkLeft;
        this.forkRight = forkRight;
    }

    @Override
    public void run() {
        while (true) {
            carryTask();
            if (forkLeft.getForkStatus()) {
                Printer.show(Thread.currentThread().getName() + " has picked left fork");
                if (forkRight.getForkStatus()) {
                    Printer.show(Thread.currentThread().getName() + " has picked right fork");
                    Printer.show(Thread.currentThread().getName() + " is eating");
                    carryTask();
                    forkLeft.removeLock();
                    forkRight.removeLock();
                    Printer.show(Thread.currentThread().getName() + " Keeping both fork");
                } else {
                    forkLeft.removeLock();
                    Printer.show(Thread.currentThread().getName() + " Keeping left fork");
                }
            } else {
                forkLeft.removeLock();
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
