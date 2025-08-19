
public class BankersAlgorithm {
    private int[] available; //Quantidade disponível de cada recurso
    private int[][] allocation; //Matriz de recursos alocados para um processo
    private int[][] max; //Demanda máxima de recursos para cada processo
    private int[][] need; // indica os recursos restantes para que o processo finalize a execução (Need = max - allocation)
    private int numOfProcesses;
    private int numOfResources;


    public BankersAlgorithm(int[][] allocation, int[][] max, int[] available) {
        this.allocation = allocation;
        this.max = max;
        this.available = available;
        this.numOfProcesses = allocation.length;
        this.numOfResources = available.length;
        this.need = new int[numOfProcesses][numOfResources];
        calculateNeed();
    }

    private void calculateNeed() {
        for (int i = 0; i < numOfProcesses; i++) {
            for (int j = 0; j < numOfResources; j++) {
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }
    }

    //Verifica se existe pelo menos uma ordem para que não haja deadlock
    public boolean isSafeState() {
        int[] work = new int[numOfResources];
        System.arraycopy(available, 0, work, 0, numOfResources);

        //array para guardar o estado de execução dos processos
        boolean[] finish = new boolean[numOfProcesses];
        int[] safeSequence = new int[numOfProcesses];
        //quantidade de processos concluídos na sequência segura
        int count = 0;

        while (count < numOfProcesses) {
            boolean found = false;
            for (int p = 0; p < numOfProcesses; p++) {
                if (!finish[p]) {
                    int j;
                    for (j = 0; j < numOfResources; j++) {
                        //Verifica se a necessidade é maior que a quantidade de recurso disponível
                        if (need[p][j] > work[j]) {
                            break;
                        }
                    }

                    if (j == numOfResources) {
                        for (int k = 0; k < numOfResources; k++) {
                            work[k] += allocation[p][k];
                        }
                        safeSequence[count++] = p;
                        finish[p] = true;
                        found = true;
                    }
                }
            }

            if (!found) {
                System.out.println("O sistema não está em um estado seguro.");
                return false;
            }
        }

        System.out.print("O sistema está em um estado seguro. Sequência segura: ");
        for (int i = 0; i < numOfProcesses; i++) {
            System.out.print("P" + safeSequence[i] + (i == numOfProcesses - 1 ? "" : " -> "));
        }
        System.out.println();
        return true;
    }

    public static void main(String[] args) {
        int[][] allocation = {
            {0, 1, 0},
            {2, 0, 0},
            {3, 0, 2},
            {2, 1, 1},
            {0, 0, 2}
        };
        
        int[][] max = {
            {7, 5, 3},
            {3, 2, 2},
            {9, 0, 2},
            {2, 2, 2},
            {4, 3, 3}
        };
        
        // Exemplo de estado seguro
        // int[] available = {3, 3, 2};

        // Exemplo de estado inseguro
        int[] available = {1,1,0};

        BankersAlgorithm banker = new BankersAlgorithm(allocation, max, available);
        
        banker.isSafeState();
    }

    
}