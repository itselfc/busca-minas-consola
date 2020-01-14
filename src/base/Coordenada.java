package base;

import java.util.ArrayList;

public class Coordenada {

    private int x;
    private int y;

    public Coordenada(int x,int y){
        this.x=x;
        this.y=y;
    }
    public Coordenada(Coordenada coordenada){
        x=coordenada.getX();
        y=coordenada.getY();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "C{" + x +
                "," + y +
                '}';
    }
}
