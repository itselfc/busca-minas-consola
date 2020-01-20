package base.enums;

public enum EstadoJuego {
    PERDIDO(false,true,false),
    CONTINUA(true,true,false),
    GANADO(false,true,true);

    public final boolean bandSiguienteTurno;
    public final boolean bandTermino;
    public final boolean bandGanado;

    EstadoJuego(boolean bandSiguienteTurno, boolean bandTermino, boolean bandGanado) {
        this.bandSiguienteTurno = bandSiguienteTurno;
        this.bandTermino = bandTermino;
        this.bandGanado = bandGanado;
    }
}
