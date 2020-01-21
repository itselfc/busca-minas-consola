package base;

import base.enums.Dificultad;
import base.enums.EstadoCasilla;

import java.util.ArrayList;

public class Mina {
    private Coordenada coordenada;
    private ArrayList<Coordenada> coordenadasVecinas;
    private base.enums.EstadoCasilla estado;

    public Mina(int x, int y) {
        coordenada = new Coordenada(x, y);
        coordenadasVecinas = new ArrayList<>();
        estado = EstadoCasilla.OCULTA;
    }

    public ArrayList<Coordenada> encontrarCoordenadasVecinas(int x, int y, int ancho, int largo) {
        ArrayList<Coordenada> listaPuntosCercanos = new ArrayList<>();
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if ((i >= 0 && i <= ancho) && (j >= 0 && j <= largo)) {
                    listaPuntosCercanos.add(new Coordenada(i, j));
                }
            }
        }
        return listaPuntosCercanos;
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(Coordenada coordenada) {
        this.coordenada = coordenada;
    }

    public ArrayList<Coordenada> getCoordenadasVecinas() {
        return coordenadasVecinas;
    }

    public void setCoordenadasVecinas(ArrayList<Coordenada> coordenadasVecinas) {
        this.coordenadasVecinas = coordenadasVecinas;
    }

    public EstadoCasilla getEstado() {
        return estado;
    }

    public void setEstado(EstadoCasilla estado) {
        this.estado = estado;
    }
}
