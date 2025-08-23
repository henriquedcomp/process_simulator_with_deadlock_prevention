// Processo criado para rodar os testes de performance
public class ProcessC extends Process {
    private final int iterations = 10000;
    private final int warmupIterations = 1000;

    public ProcessC(int pid, Resource resource1, Resource resource2, int index) {
        super(pid, resource1, resource2, index);
    }

    @Override
    public void run() {
        long totalTimeOstrich;
        long totalTimeBanker;
        long start;
        long end;

        // Aquecimento
        // Evita viés da JVM por questões de cache
        for(int i = 0; i < this.warmupIterations; i++) {
            this.getResource(this.resource1, Algorithms.OSTRICH);
            this.getResource(this.resource2, Algorithms.OSTRICH);
            this.useResources();
            this.resource1.release();
            this.resource2.release();

            this.getResource(resource1, Algorithms.BANKER);
            this.getResource(resource2, Algorithms.BANKER);
            this.useResources();
            this.releaseResources();
            RequestAnalyzer.resetAnalyzer();
        }

        // mede o tempo de execução utlizando o algoritmo do avestruz
        start = System.nanoTime();

        for(int i = 0; i < this.iterations; i++) {
            this.getResource(this.resource1, Algorithms.OSTRICH);
            this.getResource(this.resource2, Algorithms.OSTRICH);
            this.useResources();
            this.resource1.release();
            this.resource2.release();
        }

        end = System.nanoTime();

        totalTimeOstrich = end - start;

        // mede o tempo de execução utlizando o algoritmo do banqueiro
        start = System.nanoTime();

        for(int i = 0; i < this.iterations; i++) {
            this.getResource(resource1, Algorithms.BANKER);
            this.getResource(resource2, Algorithms.BANKER);
            this.useResources();
            this.releaseResources();
            RequestAnalyzer.resetAnalyzer();
        }

        end = System.nanoTime();

        totalTimeBanker = end - start;

        System.out.println("\n==== Resultados da Simulação ====");
        System.out.printf("Tempo de execução (Avestruz): %d ns\n", totalTimeOstrich);
        System.out.printf("Tempo de execução (Banqueiro): %d ns\n", totalTimeBanker);
    }
}