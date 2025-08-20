
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Resource {
    private final Lock lock = new ReentrantLock(); // lock para garantir exclusão mútua no acesso ao recurso
    private final String nome;
    private final int allocationMatrixIndex;

    public Resource(String nome, int allocationMatrixIndex) {
        this.nome = nome;
        this.allocationMatrixIndex = allocationMatrixIndex;
    }

    public void get() {
        lock.lock();
        System.out.println("Processo " + Thread.currentThread().getName() + " adquiriu " + this.nome);
    }

    public void release() {
        lock.unlock();
        System.out.println("Processo " + Thread.currentThread().getName() + " liberou " + this.nome);
    }

    public int getIndex() {
        return this.allocationMatrixIndex;
    }

    public String getNome() {
        return this.nome;
    }
}