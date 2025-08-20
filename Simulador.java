import java.util.*;

class Processo {
    int id;
    int maxNeed;
    int allocated;
    int need;

    public Processo(int id, int maxNeed) {
        this.id = id;
        this.maxNeed = maxNeed;
        this.allocated = 0;
        this.need = maxNeed;
    }
}

class Sistema {
    int recursosDisp;
    List<Processo> processos = new ArrayList<>();

    public Sistema(int recursosTotais) {
        this.recursosDisp = recursosTotais;
    }

    public void adicionarProcesso(Processo p) {
        processos.add(p);
    }

    // Algoritmo do Banqueiro
    public boolean solicitarBanqueiro(Processo p, int qtd) {
        if (qtd > p.need || qtd > recursosDisp) {
            return false;
        }

        // Concessão temporária
        recursosDisp -= qtd;
        p.allocated += qtd;
        p.need -= qtd;

        if (estadoSeguro()) {
            return true;
        } else {
            // Reversão
            recursosDisp += qtd;
            p.allocated -= qtd;
            p.need += qtd;
            return false;
        }
    }

    // Verificação de estado seguro
    private boolean estadoSeguro() {
        int work = recursosDisp;
        boolean[] finish = new boolean[processos.size()];

        boolean found;
        do {
            found = false;
            for (int i = 0; i < processos.size(); i++) {
                Processo p = processos.get(i);
                if (!finish[i] && p.need <= work) {
                    work += p.allocated;
                    finish[i] = true;
                    found = true;
                }
            }
        } while (found);

        // Se todos puderem terminar, estado seguro
        for (boolean f : finish) {
            if (!f) return false;
        }
        return true;
    }

    // Algoritmo do Avestruz
    public boolean solicitarAvestruz(Processo p, int qtd) {
        if (qtd <= p.need && qtd <= recursosDisp) {
            recursosDisp -= qtd;
            p.allocated += qtd;
            p.need -= qtd;
            return true;
        }
        return false;
    }
}

public class Simulador {
    public static void main(String[] args) {
        int nProcessos = 5;
        int recursosTotais = 10;
        int nRequisicoes = 500;

        Sistema sistemaB = new Sistema(recursosTotais);
        Sistema sistemaA = new Sistema(recursosTotais);

        Random rand = new Random();

        // Criar processos
        for (int i = 0; i < nProcessos; i++) {
            int maxNeed = rand.nextInt(recursosTotais / 2) + 1;
            sistemaB.adicionarProcesso(new Processo(i, maxNeed));
            sistemaA.adicionarProcesso(new Processo(i, maxNeed));
        }

        long tempoTotalBanqueiro = 0;
        long tempoTotalAvestruz = 0;
        int deadlocksAvestruz = 0;

        for (int r = 0; r < nRequisicoes; r++) {
            int procIdx = rand.nextInt(nProcessos);
            int qtd = rand.nextInt(recursosTotais / 2) + 1;

            Processo pB = sistemaB.processos.get(procIdx);
            Processo pA = sistemaA.processos.get(procIdx);

            // Banqueiro
            long ini = System.nanoTime();
            sistemaB.solicitarBanqueiro(pB, qtd);
            long fim = System.nanoTime();
            tempoTotalBanqueiro += (fim - ini);

            // Avestruz
            ini = System.nanoTime();
            boolean sucesso = sistemaA.solicitarAvestruz(pA, qtd);
            fim = System.nanoTime();
            tempoTotalAvestruz += (fim - ini);

            if (!sucesso) {
                // Verificação simples de deadlock
                boolean precisa = false;
                for (Processo p : sistemaA.processos) {
                    if (p.need > 0) {
                        precisa = true;
                        break;
                    }
                }
                if (sistemaA.recursosDisp == 0 && precisa) {
                    deadlocksAvestruz++;
                }
            }
        }

        double tempoMedioBanqueiro = tempoTotalBanqueiro / (double) nRequisicoes;
        double tempoMedioAvestruz = tempoTotalAvestruz / (double) nRequisicoes;

        System.out.println("==== Resultados da Simulação ====");
        System.out.printf("Tempo médio por requisição (Banqueiro): %.2f ns\n", tempoMedioBanqueiro);
        System.out.printf("Tempo médio por requisição (Avestruz): %.2f ns\n", tempoMedioAvestruz);
        System.out.println("Deadlocks detectados (Avestruz): " + deadlocksAvestruz);
    }
}