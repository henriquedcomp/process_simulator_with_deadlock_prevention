public abstract class Process implements Runnable {
    protected final int pid;
    protected final int index;
    protected final Resource resource1;
    protected final Resource resource2;

    public Process(int pid, Resource resource1, Resource resource2, int index) {
        this.pid = pid;
        this.resource1 = resource1;
        this.resource2 = resource2;
        this.index = index;
    }

    @Override
    public abstract void run();

    public int getPid() {
        return this.pid;
    }

    public int getIndex() {
        return this.index;
    }

    @Override
    public String toString() {
        return "Processo #" + this.pid;
    }

    public void useResources() {
        System.out.println(this.toString() + " utilizou os recursos para escrever");
    }

    public void getResource(Resource resource) {
        synchronized(resource) {
            try {
                while(!BankersMonitor.getResource(this, resource)) {
                    resource.wait();  // Bloqueia a thread até que consiga obter o recurso
                }
            } catch (InterruptedException e) {}
        }
    }

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