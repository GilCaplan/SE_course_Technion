import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Database {
    private final Map<String, String> data;
    private final ReentrantLock lock;
    private final Condition canRead;
    private final Condition canWrite;
    private final int maxNumOfReaders;
    private int numOfReaders;
    boolean isWriting;

    public Database(int maxNumOfReaders) {
        this.data = new HashMap<>();
        this.lock = new ReentrantLock();
        this.canRead = lock.newCondition();
        this.canWrite = lock.newCondition();
        this.maxNumOfReaders = maxNumOfReaders;
        this.numOfReaders = 0;
        this.isWriting = false;
    }

    public void put(String key, String value) {
        this.data.put(key, value);
    }

    public String get(String key) {
        return data.get(key);
    }

    /**
     * method is similar to readAcquire but doesn't block Threads from running
     * @return true if process thread can start reading otherwise false
     */
    public boolean readTryAcquire() {
        lock.lock();
        try {
            if (isWriting || numOfReaders >= maxNumOfReaders) {
                return false;
            }
            numOfReaders++;
            return true;
        } finally {
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
            while (isWriting || numOfReaders >= maxNumOfReaders) {
                canRead.await();
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
            if (numOfReaders < maxNumOfReaders) {
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
            while (isWriting || numOfReaders > 0) {
                canWrite.await();
            }
            isWriting = true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
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
            if (isWriting || numOfReaders > 0)
                return false;
            isWriting = true;
            return true;
        } finally {
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
            isWriting = false;
            numOfReaders = 0;
            canRead.signalAll();
            canWrite.signal();
        } finally {
            lock.unlock();
        }
    }
}