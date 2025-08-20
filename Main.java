public class Main {
    public static void main(String[] args) {
        // Execução com possibilidade de Deadlock
        
        Resource resource1 = new Resource("Folha de Papel", 0);
        Resource resource2 = new Resource("Caneta", 1);

        ProcessA processA = new ProcessA(1234, resource1, resource2, 0);
        ProcessB processB = new ProcessB(5678, resource1, resource2, 1);

        new Thread(processA, processA.toString()).start();
        new Thread(processB, processB.toString()).start();

        // TODO: CRIAR CLASSE PARA ENCAPSULAR A LÓGICA DO ALGORITMO DO AVESTRUZ (SOMENTE PARA MELHORAR A LEITURA)
        // TODO: IMPLEMENTAR LÓGICA DOS BENCHMARKS ABAIXO
        // TODO: LIMPAR O CÓDIGO

        // Execução sem chance de Deadlock, apenas para medir performance
    }
}