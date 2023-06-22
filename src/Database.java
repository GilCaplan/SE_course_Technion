import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
//Thread/Lock/ReentrantLock/Condition


public class Database {
    private Map<String, String> data;
    private ReentrantLock lock;
    private Condition canRead;
    private Condition canWrite;
    private final int maxNumOfReaders;
    private int numOfReaders;
    boolean isWriting;
    boolean isReading;

    public Database(int maxNumOfReaders) {
        this.data = new HashMap<>();
        this.lock = new ReentrantLock();
        this.canRead = lock.newCondition();
        this.canWrite = lock.newCondition();
        this.maxNumOfReaders = maxNumOfReaders;
        this.numOfReaders = 0;
        this.isWriting = false;
        this.isReading = false;
    }

    public void put(String key, String value) {
        lock.lock();
        try {
            this.data.put(key, value);
            this.canRead.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public String get(String key) {
        lock.lock();
        try {
            return data.get(key);
        } finally {
            lock.unlock();
        }
    }

    /**
     * method is similar to readAcquire but doesn't block Threads from running
     * @return true if process thread can start reading otherwise false
     */
    public boolean readTryAcquire() {
        lock.lock();
        try {
            if (numOfReaders < maxNumOfReaders || lock.isHeldByCurrentThread() || lock.isLocked()) {
                numOfReaders++;
                return true;
            }
            return false;
        } finally {
            isReading = false;
            lock.unlock();
        }
    }

    /**
     * given a process, block if a process is currently writing to db (database)
     * or if there are k processes running. The process will wait until it can run
     */
    public void readAcquire() {
        lock.lock();
        try {
            while (numOfReaders >= maxNumOfReaders || lock.isLocked()) {
                canRead.wait();
            }
            numOfReaders++;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }


    /**
     * This method is used after reading from the db, and is called when the process
     * finishes reading from the db.
     * @throws IllegalMonitorStateException if process isn't reading from db but calls method
     */
    public void readRelease() {
        lock.lock();
        try {
            if (numOfReaders <= 0) {
                throw new IllegalMonitorStateException("Illegal read release attempt");
            }
            numOfReaders--;
            if (numOfReaders == 0) {
                canRead.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * Is used to acquire lock to write to the db. If a process wants to write to the db
     * then it will wait until previous process finishes and then have its turn.
     */
    public void writeAcquire() {
        lock.lock();
        try {
            while (numOfReaders > 0 || lock.isLocked()) {
                canWrite.wait();
            }
            isWriting = true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            isWriting =false;
            lock.unlock();
        }
    }

    /**
     * similar to writeAcquire, doesn't block process till it can write to db,
     * @return true if process can write to db otherwise false
     * try to prevent Busy waiting.
     */
    public boolean writeTryAcquire() {
        lock.lock();
        try {
            isWriting = true;
            return numOfReaders == 0 && !lock.isLocked();
        } finally {
            isWriting = false;
            lock.unlock();
        }
    }

    /**
     * This method is called when the process finishes to write to the db
     * @throws IllegalMonitorStateException if process isn't writing to db
     */
    public void writeRelease() {
        lock.lock();
        try {
            if (!isWriting) {
                throw new IllegalMonitorStateException("Illegal write release attempt");
            }
            lock.unlock();
            canRead.signalAll();
            canWrite.signal();
        } finally {
            lock.unlock();
        }
    }
}