package base;

import base.enums.EstadoCasilla;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

public class Mina {
    @Getter @Setter
    private Coordenada coordenada;
    @Getter @Setter
    private ArrayList<Coordenada> coordenadasVecinas;
    @Getter @Setter
    private base.enums.EstadoCasilla estado;

    public Mina(int x, int y) {
        coordenada = new Coordenada(x, y);
        coordenadasVecinas = new ArrayList<Coordenada>();
        estado = EstadoCasilla.OCULTA;
    }

    public ArrayList<Coordenada> encontrarCoordenadasVecinas(int x, int y, int ancho, int largo) {
        ArrayList<Coordenada> listaPuntosCercanos = new ArrayList<Coordenada>();
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if ((i >= 0 && i <= ancho) && (j >= 0 && j <= largo)) {
                    listaPuntosCercanos.add(new Coordenada(i, j));
                }
            }
        }
        return listaPuntosCercanos;
    }

}
