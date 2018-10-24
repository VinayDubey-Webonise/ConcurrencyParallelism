import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Fork {
    int forkId;
    boolean forkStatus;
    Lock lock = new ReentrantLock();

    public Fork(int forkId, boolean forkStatus) {
        this.forkId = forkId;
        this.forkStatus = forkStatus;
    }

    public boolean getForkStatus() {
        if (lock.tryLock()) {
            return true;
        } else {
            return false;
        }
    }

    public void removeLock() {
        lock.unlock();
    }
}
