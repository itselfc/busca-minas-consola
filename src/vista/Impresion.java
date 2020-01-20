package vista;

import base.Juego;
import base.Tablero;

public class Impresion {

    private Tablero tablero;

    public Impresion(Juego juego){
        tablero=juego.getTablero();
    }

    public void mostrarGuia(){
        for (int i = 0; i < tablero.getAncho(); i++) {
            for (int j = 0; j < tablero.getLargo(); j++) {
                System.out.print(" " + tablero.getGuia()[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public void mostrarTablero(){
        for (int i = 0; i < tablero.getAncho(); i++) {
            for (int j = 0; j < tablero.getLargo(); j++) {
                System.out.print(" " + tablero.getTablero()[i][j] + " ");
            }
            System.out.println("");
        }
    }

}
