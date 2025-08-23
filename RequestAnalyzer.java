public class RequestAnalyzer {
    // Faz o papel do SO de decidir se entrega ou não um recurso a um processo
    // baseia-se no conceito de estado seguro e algoritmo do banqueiro

    // matriz de alocação de recursos
    private final static int[][] allocation = {
        {0, 0},
        {0, 0}
    };
    
    // matriz com informações da necessidade dos processos por recursos
    private static int[][] max = {
        {1, 1},
        {1, 1}
    };
    
    // recursos disponíveis
    private final static int[] available = {1, 1};
    
    // verifica se o fornecimento do recurso ao processo iria gerar estado inseguro
    // fornece o recurso em caso negativo
    public static boolean getResource(Process process, Resource resource) {
        System.out.println(process.toString() + " está tentando pegar " + resource.getName());

        // simula o fornecimento do recurso
        allocation[process.getIndex()][resource.getIndex()] += 1;
        available[resource.getIndex()] -= 1;

        BankersAlgorithm banker = new BankersAlgorithm(allocation, max, available);

        if(banker.isSafeState()) {
            resource.get(); //fornece de fato o recurso
            return true;
        }

        System.out.print(process.toString() + " não conseguiu pegar " + resource.getName());
        System.out.println("(Estado inseguro)");

        //desfaz a simulação
        allocation[process.getIndex()][resource.getIndex()] -= 1;
        available[resource.getIndex()] += 1;

        return false;
    }

    // ajusta as estruturas de dados ao liberar recursos e libera o lock do recurso
    public static void releaseResource(Process process, Resource resource) {
        allocation[process.getIndex()][resource.getIndex()] -= 1;
        max[process.getIndex()][resource.getIndex()] -= 1;
        available[resource.getIndex()] += 1;
        resource.release();
    }

    // reseta a matriz que informa a necessidade dos processos por recursos
    // uso no benchmark, para permitir várias iterações adquirindo e liberando os recursos
    public static void resetAnalyzer() {
        int[][] newMax = {
            {1, 1},
            {1, 1}
        };

        max = newMax;
    }
}