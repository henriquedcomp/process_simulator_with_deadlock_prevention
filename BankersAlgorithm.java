public class BankersAlgorithm {
    private final int[] available; //Quantidade disponível de cada recurso
    private final int[][] allocation; //Matriz de recursos alocados para um processo
    private final int[][] max; //Demanda máxima de recursos para cada processo
    private final int[][] need; // indica os recursos restantes para que o processo finalize a execução (Need = max - allocation)
    private final int numOfProcesses;
    private final int numOfResources;

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

                        count++;
                        finish[p] = true;
                        found = true;
                    }
                }
            }

            if (!found) {
                return false;
            }
        }

        return true;
    }
}