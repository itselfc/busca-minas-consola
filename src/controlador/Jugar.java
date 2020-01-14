package controlador;
import base.Juego;
import base.Mina;
import base.enums.*;

public class Jugar {
 private Juego juego;


    public static void main(String[] args) {
        Dificultad nivel= Dificultad.FACIL;
        Jugar play=new Jugar(nivel);



    }

    public Jugar(Dificultad nivel){
        Juego juego=new Juego(nivel);
        juego.imprimirGuia();
        juego.imprimirTablero();
    }


}
