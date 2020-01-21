package base.enums;

public enum EstadoJuego {
    PERDIDO("Partida perdida"),
    CONTINUA("Partida en proceso"),
    GANADO("Partida ganada");

    public final String valor;


    EstadoJuego(String valor) {
     this.valor=valor
     ;
    }
}
