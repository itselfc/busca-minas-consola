package base;

import lombok.Getter;
import lombok.Setter;

public class Guia {
    @Getter @Setter
    private Coordenada coordenada;
    @Getter @Setter
    private int valor;

    public Guia(int x, int y, int valor) {
        coordenada = new Coordenada(x, y);
        this.valor = valor;
    }

    public Guia(int x, int y) {
        coordenada = new Coordenada(x, y);
    }

    @Override
    public String toString() {
        return coordenada + "  V=" + valor;
    }
}
