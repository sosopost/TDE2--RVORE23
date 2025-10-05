public class Arvore23 {

    class No {
        int chave1, chave2;
        No filhoEsq, filhoMeio, filhoDir;
        boolean temDuasChaves;

        No(int chave) {
            this.chave1 = chave;
            this.temDuasChaves = false;
            this.filhoEsq = this.filhoMeio = this.filhoDir = null;
        }
    }

    private No raiz;

    public Arvore23() {
        raiz = null;
    }

    public void inserir(int chave) {
        if (raiz == null) {
            raiz = new No(chave);
        } else {
            raiz = inserirRec(raiz, chave);
        }
    }

    private No inserirRec(No no, int chave) {
        if (no == null) {
            return new No(chave);
        }

        if (no.filhoEsq == null && no.filhoMeio == null && no.filhoDir == null) {
            return inserirNo(no, chave, null, null);
        }

        if (chave < no.chave1) {
            No novo = inserirRec(no.filhoEsq, chave);
            if (novo != no.filhoEsq) return inserirNo(no, novo.chave1, novo.filhoEsq, novo.filhoMeio);
        } else if (!no.temDuasChaves || chave < no.chave2) {
            No novo = inserirRec(no.filhoMeio, chave);
            if (novo != no.filhoMeio) return inserirNo(no, novo.chave1, novo.filhoEsq, novo.filhoMeio);
        } else {
            No novo = inserirRec(no.filhoDir, chave);
            if (novo != no.filhoDir) return inserirNo(no, novo.chave1, novo.filhoEsq, novo.filhoMeio);
        }

        return no;
    }

    private No inserirNo(No no, int chave, No filhoA, No filhoB) {
        if (!no.temDuasChaves) {
            if (chave < no.chave1) {
                no.chave2 = no.chave1;
                no.chave1 = chave;
                no.filhoDir = no.filhoMeio;
                no.filhoEsq = filhoA;
                no.filhoMeio = filhoB;
            } else {
                no.chave2 = chave;
                no.filhoMeio = filhoA;
                no.filhoDir = filhoB;
            }
            no.temDuasChaves = true;
            return no;
        } else {
            No novo = new No(no.chave2);

            if (chave < no.chave1) {
                novo.chave1 = no.chave2;
                novo.filhoEsq = new No(no.chave1);
                novo.filhoDir = new No(no.chave2);
                novo.filhoEsq.filhoEsq = filhoA;
                novo.filhoEsq.filhoMeio = filhoB;
            } else if (chave < no.chave2) {
                novo.chave1 = chave;
                novo.filhoEsq = new No(no.chave1);
                novo.filhoDir = new No(no.chave2);
            } else {
                novo.chave1 = no.chave2;
                novo.filhoEsq = new No(no.chave1);
                novo.filhoDir = new No(chave);
            }

            novo.temDuasChaves = false;
            return novo;
        }
    }

    public boolean buscar(int chave) {
        return buscarRec(raiz, chave);
    }

    private boolean buscarRec(No no, int chave) {
        if (no == null) return false;

        if (no.chave1 == chave || (no.temDuasChaves && no.chave2 == chave)) return true;

        if (chave < no.chave1) return buscarRec(no.filhoEsq, chave);
        else if (!no.temDuasChaves || chave < no.chave2) return buscarRec(no.filhoMeio, chave);
        else return buscarRec(no.filhoDir, chave);
    }

    public void imprimir() {
        imprimirRec(raiz);
        System.out.println();
    }

    private void imprimirRec(No no) {
        if (no != null) {
            imprimirRec(no.filhoEsq);
            System.out.print(no.chave1 + " ");
            imprimirRec(no.filhoMeio);
            if (no.temDuasChaves) {
                System.out.print(no.chave2 + " ");
                imprimirRec(no.filhoDir);
            }
        }
    }

    public static void main(String[] args) {
        Arvore23 arvore = new Arvore23();
        arvore.inserir(10);
        arvore.inserir(20);
        arvore.inserir(5);
        arvore.inserir(15);
        arvore.inserir(25);

        System.out.println("Árvore em ordem:");
        arvore.imprimir();

        System.out.println("Buscar 15: " + arvore.buscar(15));
        System.out.println("Buscar 30: " + arvore.buscar(30));
    }
}
