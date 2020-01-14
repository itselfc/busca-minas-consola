package base.enums;

public enum Dificultad {
    FACIL(8),
    INTERMEDIO(16),
    LEYENDA(32);

    public final int medida;

    private Dificultad(int medida) {
        this.medida = medida;
    }
}
