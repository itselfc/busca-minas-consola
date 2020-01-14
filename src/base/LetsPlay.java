package base;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class LetsPlay {

    public static void main(String[] args) {
        int ancho = 8;
        int largo = 8;
        Tablero tablero = new Tablero(ancho, largo);

        int cantMinas = tablero.getCantMinas();
        tablero.posicionarMinas();
        tablero.crearGuias();
int[][] guia=tablero.getGuia();
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < largo; j++) {
                System.out.print(" "+guia[i][j]+" ");
            }
            System.out.println("");
        }
    }

}
