public class Main {
    public static void main(String[] args) {
        ArvoreBalanceadaRn arvore = new ArvoreBalanceadaRn();

        Reserva reserva1 = new Reserva(1, "Alice Silva", "V001", "2024-08-20T15:30:00");
        Reserva reserva2 = new Reserva(2, "João Pereira", "V001", "2024-08-20T14:00:00");
        Reserva reserva3 = new Reserva(3, "Maria Costa", "V001", "2024-08-20T15:00:00");
        Reserva reserva4 = new Reserva(4, "Carlos Souza", "V002", "2024-08-20T09:00:00");
        Reserva reserva5 = new Reserva(5, "Beatriz Lima", "V002", "2024-08-21T10:30:00");
        Reserva reserva6 = new Reserva(6, "Fernanda Oliveira", "V001", "2024-08-20T16:00:00");
        Reserva reserva7 = new Reserva(7, "Joaquim Ferreira", "V002", "2024-08-21T10:30:00");
        Reserva reserva8 = new Reserva(8, "Manoel Leão", "V001", "2024-08-20T16:00:00");
        Reserva reserva9 = new Reserva(9, "José Carlos", "V002", "2024-08-21T10:30:00");
        Reserva reserva10 = new Reserva(10, "André Mateus", "V001", "2024-08-20T16:00:00");

        // Adicionando Reservas
        arvore.inserirReserva(reserva1);
        arvore.inserirReserva(reserva2);
        arvore.inserirReserva(reserva3);
        arvore.inserirReserva(reserva4);
        arvore.inserirReserva(reserva5);
        arvore.inserirReserva(reserva6);
        arvore.inserirReserva(reserva7);
        arvore.inserirReserva(reserva8);
        arvore.inserirReserva(reserva9);
        arvore.inserirReserva(reserva10);
        
        // Exibe Árvore em Pré-Ordem
        System.out.println("\nÁrvore em pré-ordem:");
        arvore.imprimirPreOrdem();
        
        // Buscando Reservas
        arvore.buscarReserva(2);
        arvore.buscarReserva(999);
        
        // Listando Reservas Por Voo
        arvore.listarReservasPorVoo("V001");
        arvore.listarReservasPorVoo("V003");
        
        // Removendo Reserva
        arvore.removerReserva(9);
        arvore.removerReserva(10);
        arvore.removerReserva(4);       
        
        // Exibe Árvore em Pré-Ordem Pós Remoção
        System.out.println("\nÁrvore em pré-ordem após remoção:");
        arvore.imprimirPreOrdem();
        
        // Verificação de Balanceamento
        System.out.println("\nVerificação de balanceamento: " + 
        (arvore.verificarBalanceamento() ? "Balanceada" : "Desbalanceada"));

    }
}
