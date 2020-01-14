package base;

import java.util.ArrayList;

public class Guia {

    private Coordenada coordenada;
    private int valor;
    private boolean bandOculto;

    public Guia(int x,int y,int valor){
        coordenada=new Coordenada(x,y);
        this.valor=valor;
        bandOculto=true;
    }

    public Guia(int x,int y){
        coordenada=new Coordenada(x,y);
        bandOculto=true;
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(Coordenada coordenada) {
        this.coordenada = coordenada;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public boolean isBandOculto() {
        return bandOculto;
    }

    public void setBandOculto(boolean bandOculto) {
        this.bandOculto = bandOculto;
    }
    @Override
    public String toString() {
        return coordenada + "  V="+ valor;
    }
}
