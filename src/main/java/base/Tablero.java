package base;

import base.enums.*;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.Random;

public class Tablero {
    @Getter @Setter
    private int ancho;
    @Getter @Setter
    private int largo;
    @Getter @Setter
    private int cantMinas;
    @Getter @Setter
    private String[][] tablero;
    @Getter @Setter
    private EstadoCasilla[][] tableroEstados;
    @Getter @Setter
    private int[][] guia;
    @Getter @Setter
    private ArrayList<Mina> listaMinas;
    @Getter @Setter
    private ArrayList<Guia> listaGuias;

    public Tablero(int medida) {
        this.ancho = medida;
        this.largo = medida;
        this.tableroEstados = new base.enums.EstadoCasilla[ancho][largo];
        listaMinas = new ArrayList<Mina>();
        listaGuias = new ArrayList<Guia>();
        cantMinas = ((ancho * largo) / 64) * 10;
    }

    public EstadoJuego cambiarEstadoCasilla(int x, int y) {
        EstadoCasilla estadoCasilla = tableroEstados[x][y];
        if (estadoCasilla == EstadoCasilla.OCULTA) {
            int valor = guia[x][y];
            if (valor == ValorCasilla.VACIA.valor) {
                ArrayList<Coordenada> listaCoordenadasEspaciosVacios = buscarPosicionesEspacioVacio(x, y);
                for (Coordenada coordenada : listaCoordenadasEspaciosVacios) {
                    int i = coordenada.getX();
                    int j = coordenada.getY();
                    tablero[i][j] = EstadoCasilla.DESCUBIERTA.simbolo;
                }
            }
            if (valor == ValorCasilla.MINA.valor) {
                tablero[x][y] = EstadoCasilla.EXPLOTADA.simbolo;
                return EstadoJuego.PERDIDO;
            }
            if (valor == ValorCasilla.BANDERA.valor) {
                tablero[x][y] = EstadoCasilla.DESCUBIERTA.simbolo;
                return EstadoJuego.GANADO;
            }
            tablero[x][y] = EstadoCasilla.DESCUBIERTA.simbolo;
            return EstadoJuego.CONTINUA;
        }
        if (!hayCasillasOcultas()) {
            return EstadoJuego.GANADO;
        }
        return EstadoJuego.CONTINUA;
    }

    public boolean hayCasillasOcultas() {

        for (int x = 0; x < ancho; x++) {
            for (int y = 0; y < largo; y++) {
                if (tablero[x][y].equalsIgnoreCase(EstadoCasilla.OCULTA.simbolo)) {
                    return true;
                }
            }
        }

        return false;
    }

    public ArrayList<Coordenada> buscarPosicionesEspacioVacio(int x, int y) {
        ArrayList<Coordenada> listaEspera = new ArrayList<Coordenada>();
        ArrayList<Coordenada> listaRevisados = new ArrayList<Coordenada>();
        ArrayList<Coordenada> listaGuardado = new ArrayList<Coordenada>();

        Boolean bandContinua = true;
        Coordenada coordenada = new Coordenada(x, y);
        do {
            int i = coordenada.getX();
            int j = coordenada.getY();
            //Buscar arriba
            if (i - 1 >= 0 && guia[i - 1][j] == ValorCasilla.VACIA.valor) {
                Coordenada aux = new Coordenada(i - 1, j);
                if (!estaCoordenadaEnLista(listaRevisados, aux)) {
                    listaRevisados.add(aux);
                    listaEspera.add(aux);
                    listaGuardado.add(aux);
                }
            }
            //Buscar abajo
            if (i + 1 < largo && guia[i + 1][j] == ValorCasilla.VACIA.valor) {
                Coordenada aux = new Coordenada(i + 1, j);
                if (!estaCoordenadaEnLista(listaRevisados, aux)) {
                    listaRevisados.add(aux);
                    listaEspera.add(aux);
                    listaGuardado.add(aux);
                }
            }
            //Buscar hacia la derecha
            if (j + 1 < ancho && guia[i][j + 1] == ValorCasilla.VACIA.valor) {
                Coordenada aux = new Coordenada(i, j + 1);
                if (!estaCoordenadaEnLista(listaRevisados, aux)) {
                    listaRevisados.add(aux);
                    listaEspera.add(aux);
                    listaGuardado.add(aux);
                }
                //Buscar hacia la izquierda
            }
            if (j - 1 >= 0 && guia[i][j - 1] == ValorCasilla.VACIA.valor) {
                Coordenada aux = new Coordenada(i, j - 1);
                if (!estaCoordenadaEnLista(listaRevisados, aux)) {
                    listaRevisados.add(aux);
                    listaEspera.add(aux);
                    listaGuardado.add(aux);
                }
            }
            if (listaEspera.size() > 0) {
                coordenada = listaEspera.get(0);
                listaEspera.remove(0);
            } else {
                bandContinua = false;
            }
        } while (bandContinua);
        return listaGuardado;
    }

    public boolean estaCoordenadaEnLista(ArrayList<Coordenada> lista, Coordenada coordenada) {
        for (Coordenada coord : lista) {
            if (coord.getX() == coordenada.getX() && coord.getY() == coordenada.getY()) {
                return true;
            }
        }
        return false;
    }

    public void posicionarMinas() {
        ArrayList<Mina> listaMinas = new ArrayList<Mina>();
        for (int i = 0; i < cantMinas; i++) {
            int x;
            int y;
            do {
                x = generarAleatorio(ancho - 1);
                y = generarAleatorio(largo - 1);
            } while (estaGuardadoEnLista(x, y, listaMinas));
            Mina mina = new Mina(x, y);
            ArrayList<Coordenada> listaPuntosCercanos = new ArrayList<Coordenada>();
            listaPuntosCercanos.addAll(mina.encontrarCoordenadasVecinas(x, y, ancho, largo));
            mina.setCoordenadasVecinas(listaPuntosCercanos);
            listaMinas.add(mina);
        }
        this.listaMinas = listaMinas;
    }

    public void inicializarTablero() {
        tablero = new String[ancho][largo];
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < largo; j++) {
                tableroEstados[i][j] = EstadoCasilla.OCULTA;
                tablero[i][j] = EstadoCasilla.OCULTA.simbolo;

            }
        }

    }

    public void crearGuias() {
        ArrayList<Guia> listaValoresGuia = new ArrayList<Guia>();
        for (Mina mina : listaMinas) {
            Coordenada posMina = mina.getCoordenada();
            listaValoresGuia.add(new Guia(posMina.getX(), posMina.getY(), ValorCasilla.MINA.valor));
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

}
