



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
    
    public static boolean getResource(IProcess process, Resource resource) {
        System.out.println("Processo " + process.getPid() + " está tentando pegar o " + resource.getNome());

        allocation[process.getPid()][resource.getIndex()] += 1;
        available[resource.getIndex()] -= 1;

        BankersAlgorithm banker = new BankersAlgorithm(allocation, max, available);

        //System.out.println("DEBUG:" + Arrays.deepToString(allocation));
        //System.out.println("DEBUG:" + Arrays.deepToString(max));
        //System.out.println("DEBUG:" + Arrays.toString(available));

        if(banker.isSafeState()) {
            resource.get();
            return true;
        }

        System.out.print("Processo " + process.getPid() + " não conseguiu pegar o " + resource.getNome());
        System.out.println("(Estado inseguro)");

        allocation[process.getPid()][resource.getIndex()] -= 1;
        available[resource.getIndex()] += 1;

        return false;
    }

    public static void releaseResource(IProcess process, Resource resource) {
        allocation[process.getPid()][resource.getIndex()] -= 1;
        max[process.getPid()][resource.getIndex()] -= 1;
        available[resource.getIndex()] += 1;
        resource.release();
    }
}