package ia.practica1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

import ia.practica1.exceptions.SinSolucion;

public class Mapa {

    /* ATRIBUTOS */

    private int rows;
    private int cols;
    private Carretera[][] mapa;
    private Estado estadoInicial;
    private Estado estadoFinal;

    /* CONSTRUCTOR */

    public Mapa ( int rows, int cols, String dataFilename ) throws FileNotFoundException {
        this.rows = rows;
        this.cols = cols;
        this.mapa = new Carretera[rows][cols];
        this.cargarMapa(dataFilename);
    }
    
    public Mapa ( int rows, int cols, String dataFilename, Estado estadoInicial, Estado estadoFinal ) throws FileNotFoundException {
        this.rows = rows;
        this.cols = cols;
        this.mapa = new Carretera[rows][cols];
        this.estadoInicial = estadoInicial;
        this.estadoFinal = estadoFinal;
        this.cargarMapa(dataFilename);
    }

    /* MÉTODOS */

    private void cargarMapa ( String dataFilename ) throws FileNotFoundException {
        Scanner scanner;
        Carretera carretera;
        int i,j;

        scanner = new Scanner ( new File( dataFilename ) );
        i = 0;
        while ( scanner.hasNextLine() ) {
            j = 0;
            for ( String str : scanner.nextLine().split(",") ) {
                switch ( str.charAt(0) ) {
                    case 'A': carretera = Carretera.AUTOVIA;  break;
                    case 'N': carretera = Carretera.NACIONAL; break;
                    case 'C': carretera = Carretera.COMARCAL; break;
                    default:  carretera = Carretera.VACIO;
                }
                this.mapa[i][j] = carretera;
                j++;
            }
            i++;
        }

        scanner.close();        
    }

    public void mostrarMapa () {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                System.out.print(this.mapa[i][j].getSimbolo() + " ");
            }
            System.out.println();
        }
    }

    public List<Estado> sucesores ( Estado estado ) {
        List<Estado> sucesores = new ArrayList<>();

        for ( Operador operador : this.operadores(estado) ) {
            sucesores.add( this.aplicar(estado, operador) );
        }

        return sucesores;
    }
    
    public List<Operador> operadores ( Estado estado ) {
        List<Operador> operadores = new ArrayList<>();
        int i, x, y;
        
        y = estado.getRow();
        x = estado.getCol();

        if ( y < 0 || y >= this.rows || x < 0 || x >= this.cols ) { // Si fuera del mapa
            return null;
        }

        int movs[][] = {
            {0,-1},     // Izquierda
            {-1,0},     // Arriba
            {1,0},      // Abajo            
            {0,1}       // Derecha    
        };

        for ( i = 0; i < movs.length; i++ ) {
            
            y = estado.getRow() + movs[i][0];   // Nueva Fila
            x = estado.getCol() + movs[i][1];   // Nueva Columna
            
            if ( y >= 0 && y < this.rows && x >= 0 && x < this.cols ) {     // Si no fuera del mapa
                if ( this.mapa[y][x] != Carretera.VACIO ) {                 // Y no es casilla vacia
                    operadores.add( new Operador(movs[i][0], movs[i][1], this.mapa[y][x]));
                }
            }
        }

        return operadores;
    }

    // Lanzar excepción de operador no aplicable
    private Estado aplicar ( Estado estado, Operador operador ) {
        int x, y;
        
        y = estado.getRow() + operador.getDRow();   // Nueva Fila
        x = estado.getCol() + operador.getDCol();   // Nueva Columna

        if ( y < 0 || y >= this.rows || x < 0 || x >= this.cols ) { // Si fuera del mapa
            return null;
        }

        return new Estado( y, x, operador.getNCarretera() );
    }

    public List<Estado> bestFirst () throws SinSolucion {
        PriorityQueue<Estado> pends = new PriorityQueue<>( (e1,e2) -> {
            return Float.compare( e1.getH(), e2.getH() );
        });
        List<Estado> solucion = null, camino = null;
        Set<Estado> trats = new HashSet<>();
        boolean encontrado = false;
        Estado actual;
        
        /* Inicializar Cola Prioridad */
        this.estadoInicial.setH(1);
        pends.add( this.estadoInicial );

        /* Bucle de Búsqueda */
        while ( ! encontrado && ! pends.isEmpty() ) {
            actual = pends.poll();

            if ( actual.equals( estadoFinal ) ) {
                encontrado = true;
                solucion = actual.getCamino();
            } else {
                for ( Estado sucesor : this.sucesores(actual) ) {
                    if ( ! trats.contains(sucesor) && ! pends.contains(sucesor)) {
                        camino = actual.getCamino();
                        camino.add(sucesor);
                        sucesor.setCamino( camino );
                        sucesor.setH(1);
                        pends.add(sucesor);
                    }
                }
                trats.add(actual);
            }
        }

        if ( encontrado ) {
            return solucion;
        }
        throw new SinSolucion();
    }
}
