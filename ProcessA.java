public class ProcessA extends Process {
    public ProcessA(int pid, Resource resource1, Resource resource2, int index) {
        super(pid, resource1, resource2, index);
    }

    @Override
    public void run() {
        // Execução com Deadlock
        //resource1.get();

        // Execução sem Deadlock
        this.getResource(resource1);

        try {
            Thread.sleep(2000); // "Garante" que o SO escalone a outra thread para forçar o Deadlock
        } catch (InterruptedException e) {}

        // Execução com Deadlock
        //resource2.get();

        // Execução sem Deadlock
        this.getResource(resource2);
        this.useResources();
        this.releaseResources();

        System.out.println(this.toString() + " finalizou");
    }
}