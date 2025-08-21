public class Main {
    public static void main(String[] args) {
        Resource resource1 = new Resource("Folha de Papel", 0);
        Resource resource2 = new Resource("Caneta", 1);

        // Execução com possibilidade de Deadlock
        // Comentar o trecho "Execução sem chance de Deadlock, apenas para medir performance" antes de
        // executar esse

        /*
        ProcessA processA = new ProcessA(1234, resource1, resource2, 0);
        ProcessB processB = new ProcessB(5678, resource1, resource2, 1);

        new Thread(processA, processA.toString()).start();
        new Thread(processB, processB.toString()).start();
        */

        // Execução sem chance de Deadlock, apenas para medir performance
        
        // Se for executar o benchmark, comentar o trecho "Execução com possibilidade de Deadlock"

        ProcessC processC = new ProcessC(0, resource1, resource2, 0);

        new Thread(processC, processC.toString()).start();
    }
}