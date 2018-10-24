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

    public Fork getForkStatus() {
        if (lock.tryLock()) {
            setLock();
            return this;
        } else {
            return null;
        }
    }

    public void setLock() {
        lock.lock();
    }

    public void removeLock() {
        lock.unlock();
    }
}
