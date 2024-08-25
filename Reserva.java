import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reserva {
    private int id;
    private String passageiro;
    private String voo;
    private LocalDateTime horario;

    public Reserva(int id, String nome, String voo, String horario) {
        this.id = id;
        this.passageiro = nome;
        this.voo = voo;
        this.horario = LocalDateTime.parse(horario, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
    
    public int getId() {
        return id;
    }

    public String getNome() {
        return passageiro;
    }

    public String getVoo() {
        return voo;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    @Override
    public String toString() {
        return String.format("ID = %d, Passageiro = '%s', Voo = '%s', Horário = %s", 
                id, passageiro, voo, horario);
    }
}