public class ProcessB extends Process {
    public ProcessB(int pid, Resource resource1, Resource resource2, int index) {
        super(pid, resource1, resource2, index);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000); // "Garante" que o SO escalone a outra thread para forçar o Deadlock
        } catch (InterruptedException e) {}

        // Execução com Deadlock
        this.getResource(resource2, Algorithms.OSTRICH);
        this.getResource(resource1, Algorithms.OSTRICH);

        // Execução sem Deadlock
        /*this.getResource(resource2, Algorithms.BANKER);
        this.getResource(resource1, Algorithms.BANKER);
        this.useResources();
        this.releaseResources();*/

        System.out.println(this.toString() + " finalizou");
    }
}