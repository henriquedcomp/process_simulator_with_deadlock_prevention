public class Process1 extends Thread implements IProcess {
    private final int pid;
    private final Resource resource1;
    private final Resource resource2;

    public Process1(int id, Resource resource1, Resource resource2) {
        super(String.valueOf(id));
        this.pid = id;
        this.resource1 = resource1;
        this.resource2 = resource2;
    }

    @Override
    public void run() {
        // Execução com Deadlock
        //resource1.get();

        // Execução sem Deadlock
        this.getResource(resource1);

        try {
            sleep(2000); // "Garante" que o SO escalone a outra thread para forçar o Deadlock
        } catch (InterruptedException e) {}

        // Execução com Deadlock
        //resource2.get();

        // Execução sem Deadlock
        this.getResource(resource2);
        this.releaseResources();

        System.out.println("Processo n° " + this.pid + " finalizou!");
    }

    @Override
    public int getPid() {
        return this.pid;
    }

    @Override 
    public void getResource(Resource resource) {
        synchronized(resource) {
            try {
                while(!BankersMonitor.getResource(this, resource)) {
                    resource.wait();  // Bloqueia a thread até que consiga obter o recurso
                    System.out.println("Processo acordou");
                }
            } catch (InterruptedException e) {}
        }
    }

    @Override
    public void releaseResources() {
        synchronized(this.resource1) {
            BankersMonitor.releaseResource(this, this.resource1);
            this.resource1.notifyAll();
        }

        synchronized(this.resource2) {
            BankersMonitor.releaseResource(this, this.resource2);
            this.resource2.notifyAll();
        }
    }
}