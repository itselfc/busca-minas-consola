package base;

import lombok.Getter;
import lombok.Setter;
import base.enums.Dificultad;
import base.enums.EstadoJuego;

public class Juego {
    @Getter @Setter
    private Tablero tablero;
    @Getter @Setter
    private int tiempo;
    @Getter @Setter
    private boolean gameover;

    public Juego(Dificultad dificultad) {
        tablero = new Tablero(dificultad.medida);
        tablero.posicionarMinas();
        tablero.crearGuias();
        tablero.inicializarTablero();
    }

    public EstadoJuego descubrirCasilla(int x, int y){
        return tablero.cambiarEstadoCasilla(x,y);
    }


}
