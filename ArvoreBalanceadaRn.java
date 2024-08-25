import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ArvoreBalanceadaRn {
    private static final boolean VERMELHO = true;
    private static final boolean PRETO = false;

    private class No {
        Reserva reserva;
        No esquerda, direita;
        boolean cor;

        No(Reserva reserva) {
            this.reserva = reserva;
            this.cor = VERMELHO;
        }
    }

    private No raiz;

    private boolean isVermelho(No no) {
        return no != null && no.cor == VERMELHO;
    }

    private No rodarEsquerda(No no) {
        No temporario = no.direita;
        no.direita = temporario.esquerda;
        temporario.esquerda = no;
        temporario.cor = no.cor;
        no.cor = VERMELHO;
        return temporario;
    }

    private No rodarDireita(No no) {
        No temporario = no.esquerda;
        no.esquerda = temporario.direita;
        temporario.direita = no;
        temporario.cor = no.cor;
        no.cor = VERMELHO;
        return temporario;
    }

    private void inverterCores(No no) {
        no.cor = VERMELHO;
        no.esquerda.cor = PRETO;
        no.direita.cor = PRETO;
    }

    public void inserirReserva(Reserva reserva) {
        raiz = inserir(raiz, reserva);
        raiz.cor = PRETO;
        System.out.println("Reserva inserida com sucesso: ID = " + reserva.getId() + 
                ", Passageiro = " + reserva.getNome() + 
                ", Voo = " + reserva.getVoo() + 
                ", Horário = " + reserva.getHorario());
    }

    private No inserir(No no, Reserva reserva) {
        if (no == null) {
            return new No(reserva);
        }

        if (reserva.getId() < no.reserva.getId()) {
            no.esquerda = inserir(no.esquerda, reserva);
        } else if (reserva.getId() > no.reserva.getId()) {
            no.direita = inserir(no.direita, reserva);
        } else {
            no.reserva = reserva;
        }

        if (isVermelho(no.direita) && !isVermelho(no.esquerda)) no = rodarEsquerda(no);
        if (isVermelho(no.esquerda) && isVermelho(no.esquerda.esquerda)) no = rodarDireita(no);
        if (isVermelho(no.esquerda) && isVermelho(no.direita)) inverterCores(no);

        return no;
    }

    public Reserva buscarReserva(int id, boolean exibeMsg) {
        No no = raiz;
        while (no != null) {
            if (id < no.reserva.getId()) {
                no = no.esquerda;
            } else if (id > no.reserva.getId()) {
                no = no.direita;
            } else {
            	if (exibeMsg) {
                System.out.println("\nReserva encontrada: ID = " + no.reserva.getId() + 
                                   ", Passageiro = " + no.reserva.getNome() + 
                                   ", Voo = " + no.reserva.getVoo() + 
                                   ", Horário = " + no.reserva.getHorario());
            	}
                return no.reserva;
            }
        }
        if (exibeMsg) {
        System.out.println("\nReserva não encontrada: ID = " + id);
        }
        return null;
    }

    public void listarReservasPorVoo(String voo) {
        List<Reserva> reservas = new ArrayList<>();
        coletarReservas(raiz, voo, reservas);

        if (!reservas.isEmpty()) {
            System.out.println("\nReservas para o voo " + voo + ":");
            Collections.sort(reservas, Comparator.comparing(Reserva::getHorario));
            int[] cont = {1};
            for (Reserva reserva : reservas) {
                System.out.println(cont[0] + ". " + reserva);
                cont[0]++;
            }
        } else {
            System.out.println("\nNenhuma reserva encontrada para o voo " + voo);
        }
    }

    private void coletarReservas(No no, String voo, List<Reserva> reservas) {
        if (no == null) return;

        coletarReservas(no.esquerda, voo, reservas);

        if (no.reserva.getVoo().equals(voo)) {
            reservas.add(no.reserva);
        }

        coletarReservas(no.direita, voo, reservas);
    }

    public void imprimirPreOrdem() {
        imprimirPreOrdem(raiz);
    }

    private void imprimirPreOrdem(No no) {
        if (no == null) return;
        System.out.println(no.reserva);
        imprimirPreOrdem(no.esquerda);
        imprimirPreOrdem(no.direita);
    }

    public void removerReserva(int id) {
        if (buscarReserva(id, false) != null) {
            raiz = remover(raiz, id);
            if (raiz != null) raiz.cor = PRETO;
            System.out.println("\nReserva removida com sucesso: ID = " + id);
        } else {
            System.out.println("\nReserva não encontrada: ID = " + id);
        }
    }

    public Reserva buscarReserva(int id) {
        return buscarReserva(id, true);
    }
    
    private No remover(No no, int id) {
        if (id < no.reserva.getId()) {
            if (no.esquerda != null) {
                no.esquerda = remover(no.esquerda, id);
            }
        } else if (id > no.reserva.getId()) {
            if (no.direita != null) {
                no.direita = remover(no.direita, id);
            }
        } else {
            if (no.direita == null) return no.esquerda;
            if (no.esquerda == null) return no.direita;

            No temp = no;
            no = min(temp.direita);
            no.direita = removerMin(temp.direita);
            no.esquerda = temp.esquerda;
        }
        return balancer(no);
    }

    private No removerMin(No no) {
        if (no.esquerda == null) return null;
        no.esquerda = removerMin(no.esquerda);
        return balancer(no);
    }

    private No min(No no) {
        while (no.esquerda != null) no = no.esquerda;
        return no;
    }

    private No balancer(No no) {
        if (isVermelho(no.direita)) no = rodarEsquerda(no);
        if (isVermelho(no.esquerda) && isVermelho(no.esquerda.esquerda)) no = rodarDireita(no);
        if (isVermelho(no.esquerda) && isVermelho(no.direita)) inverterCores(no);
        return no;
    }

    public boolean verificarBalanceamento() {
        return verificarBalanceamento(raiz) != -1;
    }

    private int verificarBalanceamento(No no) {
        if (no == null) return 0;
        int alturaEsquerda = verificarBalanceamento(no.esquerda);
        int alturaDireita = verificarBalanceamento(no.direita);

        if (alturaEsquerda == -1 || alturaDireita == -1 || alturaEsquerda != alturaDireita) {
            return -1;
        }

        if (isVermelho(no) && (isVermelho(no.esquerda) || isVermelho(no.direita))) {
            return -1;
        }

        return alturaEsquerda + (isVermelho(no) ? 0 : 1);
    }
}
