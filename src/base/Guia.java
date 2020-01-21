package base;

import java.util.ArrayList;

public class Guia {

    private Coordenada coordenada;
    private int valor;

    public Guia(int x, int y, int valor) {
        coordenada = new Coordenada(x, y);
        this.valor = valor;
    }

    public Guia(int x, int y) {
        coordenada = new Coordenada(x, y);
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(Coordenada coordenada) {
        this.coordenada = coordenada;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return coordenada + "  V=" + valor;
    }
}
