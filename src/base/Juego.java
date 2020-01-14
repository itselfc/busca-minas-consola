package base;

import base.Tablero;
import base.enums.Dificultad;

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

    public void imprimirGuia(){
        for (int i = 0; i < tablero.getAncho(); i++) {
            for (int j = 0; j < tablero.getLargo(); j++) {
                System.out.print(" " + tablero.getGuia()[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public void imprimirTablero(){
        for (int i = 0; i < tablero.getAncho(); i++) {
            for (int j = 0; j < tablero.getLargo(); j++) {
                System.out.print(" " + tablero.getTablero()[i][j].simbolo + " ");
            }
            System.out.println("");
        }
    }

}
