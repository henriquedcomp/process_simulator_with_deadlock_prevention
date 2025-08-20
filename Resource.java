import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Resource {
    private final Lock lock = new ReentrantLock(); // lock para garantir exclusão mútua no acesso ao recurso
    private final String name;
    private final int allocationMatrixIndex;

    public Resource(String name, int allocationMatrixIndex) {
        this.name = name;
        this.allocationMatrixIndex = allocationMatrixIndex;
    }

    public void get() {
        lock.lock();
        System.out.println(Thread.currentThread().getName() + " adquiriu " + this.name);
    }

    public void release() {
        lock.unlock();
        System.out.println(Thread.currentThread().getName() + " liberou " + this.name);
    }

    public int getIndex() {
        return this.allocationMatrixIndex;
    }

    public String getName() {
        return this.name;
    }
}