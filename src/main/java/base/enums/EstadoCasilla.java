package base.enums;

public enum EstadoCasilla {
    OCULTA("-"),
    DESCUBIERTA("O"),
    EXPLOTADA("X");

    public final String simbolo;

    private EstadoCasilla(String simbolo) {
        this.simbolo = simbolo;
    }
}
