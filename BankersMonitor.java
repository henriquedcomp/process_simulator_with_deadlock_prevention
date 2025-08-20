public class BankersMonitor {
    // Faz o papel do SO de decidir se entrega ou não um recurso a um processo
    // baseia-no no conceito de estado seguro e algoritmo do banqueiro

    private final static int[][] allocation = {
        {0, 0},
        {0, 0}
    };
    
    private final static int[][] max = {
        {1, 1},
        {1, 1}
    };
    
    private final static int[] available = {1, 1};
    
    public static boolean getResource(Process process, Resource resource) {
        System.out.println(process.toString() + " está tentando pegar " + resource.getName());

        allocation[process.getIndex()][resource.getIndex()] += 1;
        available[resource.getIndex()] -= 1;

        BankersAlgorithm banker = new BankersAlgorithm(allocation, max, available);

        if(banker.isSafeState()) {
            resource.get();
            return true;
        }

        System.out.print(process.toString() + " não conseguiu pegar " + resource.getName());
        System.out.println("(Estado inseguro)");

        allocation[process.getIndex()][resource.getIndex()] -= 1;
        available[resource.getIndex()] += 1;

        return false;
    }

    public static void releaseResource(Process process, Resource resource) {
        allocation[process.getIndex()][resource.getIndex()] -= 1;
        max[process.getIndex()][resource.getIndex()] -= 1;
        available[resource.getIndex()] += 1;
        resource.release();
    }
}