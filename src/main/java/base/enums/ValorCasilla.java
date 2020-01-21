package base.enums;

public enum ValorCasilla {
    MINA(9),
    VACIA(0),
    BANDERA(10);

    public final int valor;

    private ValorCasilla(int valor) {
        this.valor = valor;
    }
}
