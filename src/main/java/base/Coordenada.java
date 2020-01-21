package base;

import lombok.Getter;
import lombok.Setter;

public class Coordenada {
    @Getter @Setter
    private int x;
    @Getter @Setter
    private int y;

    public Coordenada(int x,int y){
        this.x=x;
        this.y=y;
    }
    public Coordenada(Coordenada coordenada){
        x=coordenada.getX();
        y=coordenada.getY();
    }

    @Override
    public String toString() {
        return "C{" + x +
                "," + y +
                '}';
    }
}
