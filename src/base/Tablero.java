package base;

import base.enums.EstadoCasilla;

import java.util.ArrayList;
import java.util.Random;

public class Tablero {
    private int ancho;
    private int largo;
    private int cantMinas;
    private base.enums.EstadoCasilla[][] tablero;
    private int[][] guia;
    private ArrayList<Mina> listaMinas;
    private ArrayList<Guia> listaGuias;

    public Tablero(int medida) {
        this.ancho = medida;
        this.largo = medida;
        this.tablero = new base.enums.EstadoCasilla[ancho][largo];
        listaMinas = new ArrayList<>();
        listaGuias = new ArrayList<>();
        cantMinas = ((ancho * largo) / 64) * 10;
    }

    public void posicionarMinas() {
        ArrayList<Mina> listaMinas = new ArrayList<>();
        for (int i = 0; i < cantMinas; i++) {
            int x;
            int y;
            do {
                x = generarAleatorio(ancho - 1);
                y = generarAleatorio(largo - 1);
            } while (estaGuardadoEnLista(x, y, listaMinas));
            Mina mina = new Mina(x, y);
            ArrayList<Coordenada> listaPuntosCercanos = new ArrayList<>();
            listaPuntosCercanos.addAll(mina.encontrarCoordenadasVecinas(x, y, ancho, largo));
            mina.setCoordenadasVecinas(listaPuntosCercanos);
            listaMinas.add(mina);
        }
        this.listaMinas = listaMinas;
    }

    public void inicializarTablero() {
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < largo; j++) {
                tablero[i][j] = EstadoCasilla.OCULTA;
            }
        }
    }

    public void crearGuias() {
        ArrayList<Guia> listaValoresGuia = new ArrayList<>();
        for (Mina mina : listaMinas) {
            Coordenada posMina = mina.getCoordenada();
            listaValoresGuia.add(new Guia(posMina.getX(), posMina.getY(), 9));
            for (Coordenada posVecino : mina.getCoordenadasVecinas()) {
                if (!esUnaMina(posVecino.getX(), posVecino.getY())) {
                    int indice = getPosicionEnListaGuias(listaValoresGuia, posVecino.getX(), posVecino.getY());
                    if (indice != -1) {
                        int valorPrevio = listaValoresGuia.get(indice).getValor();
                        listaValoresGuia.get(indice).setValor(valorPrevio + 1);
                    } else {
                        listaValoresGuia.add(new Guia(posVecino.getX(), posVecino.getY(), 1));
                    }
                }
            }
        }
        listaGuias = listaValoresGuia;
        guia = new int[ancho][largo];
        for (Guia g : listaGuias) {
            guia[g.getCoordenada().getX()][g.getCoordenada().getY()] = g.getValor();
        }

    }

    public boolean esUnaMina(int x, int y) {
        for (Mina mina : listaMinas) {
            if (mina.getCoordenada().getX() == x && mina.getCoordenada().getY() == y) {
                return true;
            }
        }
        return false;
    }

    public int getPosicionEnListaGuias(ArrayList<Guia> listaGuias, int x, int y) {
        int cont = 0;
        for (int i = 0; i < listaGuias.size(); i++) {
            Coordenada temp = new Coordenada(listaGuias.get(i).getCoordenada());
            if (temp.getX() == x & temp.getY() == y) {
                return i;
            }
        }
        return -1;
    }

    public boolean estaGuardadoEnLista(int x, int y, ArrayList<Mina> listaMinas) {
        for (Mina mina : listaMinas) {
            Coordenada c = mina.getCoordenada();
            if (c.getX() == x && c.getY() == y) {
                return true;
            }
        }
        return false;
    }

    public int generarAleatorio(int limiteSuperior) {
        Random r = new Random();
        return r.nextInt(limiteSuperior);
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getLargo() {
        return largo;
    }

    public void setLargo(int largo) {
        this.largo = largo;
    }

    public int getCantMinas() {
        return cantMinas;
    }

    public void setCantMinas(int cantMinas) {
        this.cantMinas = cantMinas;
    }

    public base.enums.EstadoCasilla[][] getTablero() {
        return tablero;
    }

    public void setTablero(base.enums.EstadoCasilla[][] tablero) {
        this.tablero = tablero;
    }

    public int[][] getGuia() {
        return guia;
    }

    public void setGuia(int[][] guia) {
        this.guia = guia;
    }

    public ArrayList<Mina> getListaMinas() {
        return listaMinas;
    }

    public void setListaMinas(ArrayList<Mina> listaMinas) {
        this.listaMinas = listaMinas;
    }

    public ArrayList<Guia> getListaGuias() {
        return listaGuias;
    }

    public void setListaGuias(ArrayList<Guia> listaGuias) {
        this.listaGuias = listaGuias;
    }
}
