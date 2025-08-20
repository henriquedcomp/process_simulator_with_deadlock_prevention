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
        //resource1.get();

        // Execução sem Deadlock
        this.getResource(resource2);

        // Execução com Deadlock
        //resource2.get();  //

        // Execução sem Deadlock
        this.getResource(resource1);
        this.useResources();
        this.releaseResources();

        System.out.println(this.toString() + " finalizou");
    }
}