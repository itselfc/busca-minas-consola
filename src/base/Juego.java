package base;

import base.Tablero;
import base.enums.Dificultad;
import base.enums.EstadoJuego;

public class Juego {
    private Tablero tablero;
    private int tiempo;
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

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public boolean isGameover() {
        return gameover;
    }

    public void setGameover(boolean gameover) {
        this.gameover = gameover;
    }
}
