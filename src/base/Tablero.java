package base;

import base.enums.*;

import java.util.ArrayList;
import java.util.Random;

public class Tablero {
    private int ancho;
    private int largo;
    private int cantMinas;
    private String[][] tablero;
    private EstadoCasilla[][] tableroEstados;
    private int[][] guia;
    private ArrayList<Mina> listaMinas;
    private ArrayList<Guia> listaGuias;

    public Tablero(int medida) {
        this.ancho = medida;
        this.largo = medida;
        this.tableroEstados = new base.enums.EstadoCasilla[ancho][largo];
        listaMinas = new ArrayList<>();
        listaGuias = new ArrayList<>();
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
        return EstadoJuego.CONTINUA;

    }

    public ArrayList<Coordenada> buscarPosicionesEspacioVacio(int x, int y) {
        ArrayList<Coordenada> listaCoordenadas = new ArrayList<>();

        //La búsqueda de posiciones divide el tablero en 4 cuadrantes en donde la coordenada dada es el la que los divide.
        // 1er cuadrante, C(x,y) limita la esquina inferior derecha del cuadrante.
        // 2do cuadrante, C(x,y) limita la esquina inferior izquierda del cuadrante.
        // 3er cuadrante, C(x,y) limita la esquina superior derecha del cuadrante.
        // 4to cuadrante, C(x,y) limita la esquina superior derecha del cuadrante.
        // Por medio de ciclos se buscan todas posiciones a partir del punto hasta llegar a una guia diferente de 0;

        //Busqueda en primer cuadrante.
        for (int i = x; i >= 0; i--) {
            if (guia[i][y] != ValorCasilla.VACIA.valor) {
                break;
            }
            for (int j = y; j >= 0; j--) {
                Coordenada coordenada = new Coordenada(i, j);
                if (guia[i][j] == ValorCasilla.VACIA.valor) {
                    System.out.println("1"+ coordenada);

                    listaCoordenadas.add(coordenada);
                } else {
                    break;
                }
            }

        }

        //Busqueda en segundo cuadrante. Agregas +1 a X para buscar en la siguiente linea vertical.
        for (int i = x+1; i <= ancho; i++) {
            if (guia[i][y] != ValorCasilla.VACIA.valor) {
                break;
            }
            for (int j = y; j >= 0; j--) {
                Coordenada coordenada = new Coordenada(i, j);
                if (guia[i][j] == ValorCasilla.VACIA.valor) {
                    System.out.println("2"+ coordenada);

                    listaCoordenadas.add(coordenada);
                } else {
                    break;
                }
            }
        }

        //Busqueda en tercer cuadrante. Agregamos +1 a Y para buscar en la siguiente linea horizontal.
        for (int i = x; i >= 0; i--) {
            if ((y+1)<=largo && guia[i][y+1] != ValorCasilla.VACIA.valor) {
                break;
            }
            for (int j = y+1; j <= largo; j++) {
                Coordenada coordenada = new Coordenada(i, j);
                if (guia[i][j] == ValorCasilla.VACIA.valor) {
                    System.out.println("3"+ coordenada);

                    listaCoordenadas.add(coordenada);
                } else {
                    break;
                }
            }
        }


        //Busqueda en cuarto cuadrante. Agregas +1 a X para buscar en la siguiente linea vertical.
        for (int i = x+1; i <= ancho; i++) {
            if (guia[i][y] != ValorCasilla.VACIA.valor) {
                break;
            }
            for (int j = y+1; j <= largo; j++) {
                Coordenada coordenada = new Coordenada(i, j);
                if (guia[i][j] == ValorCasilla.VACIA.valor) {
                    listaCoordenadas.add(coordenada);
                    System.out.println("4"+ coordenada);
                } else {
                    break;
                }
            }
        }

        return listaCoordenadas;
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
        tablero = new String[ancho][largo];
        for (int i = 0; i < ancho; i++) {
            for (int j = 0; j < largo; j++) {
                tableroEstados[i][j] = EstadoCasilla.OCULTA;
                tablero[i][j] = EstadoCasilla.OCULTA.simbolo;

            }
        }

    }

    public void crearGuias() {
        ArrayList<Guia> listaValoresGuia = new ArrayList<>();
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

    public String[][] getTablero() {
        return tablero;
    }

    public void setTablero(String[][] tablero) {
        this.tablero = tablero;
    }

    public EstadoCasilla[][] getTableroEstados() {
        return tableroEstados;
    }

    public void setTableroEstados(EstadoCasilla[][] tableroEstados) {
        this.tableroEstados = tableroEstados;
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
