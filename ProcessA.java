public class ProcessA extends Process {
    public ProcessA(int pid, Resource resource1, Resource resource2, int index) {
        super(pid, resource1, resource2, index);
    }

    @Override
    public void run() {
        // Execução com Deadlock
        //this.getResource(resource1, Algorithms.OSTRICH);

        // Execução sem Deadlock
        this.getResource(resource1, Algorithms.BANKER);

        try {
            Thread.sleep(2000); // "Garante" que o SO escalone a outra thread para forçar o Deadlock
        } catch (InterruptedException e) {}

        // Execução com Deadlock
        //this.getResource(resource2, Algorithms.OSTRICH);

        // Execução sem Deadlock
        this.getResource(resource2, Algorithms.BANKER);
        this.useResources();
        this.releaseResources();

        System.out.println(this.toString() + " finalizou");
    }
}