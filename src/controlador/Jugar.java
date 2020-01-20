package controlador;

import base.Juego;
import base.Mina;
import base.enums.*;
import vista.Impresion;

import java.util.Scanner;

public class Jugar {
    private Juego juego;
    private Impresion impresora;

    public static void main(String[] args) {
        Dificultad nivel = Dificultad.FACIL;
        Jugar play = new Jugar(nivel);
        play.impresora.mostrarGuia();
        play.impresora.mostrarTablero();
        EstadoJuego estado = EstadoJuego.CONTINUA;
        do {
            Scanner in = new Scanner(System.in);
            System.out.println("X:");
            int x = in.nextInt();
            System.out.println("Y:");
            int y = in.nextInt();
            estado = play.juego.descubrirCasilla(x, y);
            play.impresora.mostrarGuia();
            play.impresora.mostrarTablero();
        } while (estado == EstadoJuego.CONTINUA);

    }

    public Jugar(Dificultad nivel) {
        juego = new Juego(nivel);
        impresora = new Impresion(juego);

    }


}
