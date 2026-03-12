package ia.practica1.formal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

import ia.practica1.exceptions.SinSolucion;
import ia.practica1.heuristicas.Heuristica;

public class Mapa {
    
    /* ATRIBUTOS */
    
    private int rows;
    private int cols;
    private Carretera[][] mapa;
    private Estado inicial;
    private Estado destino;
    private Heuristica heuristica;
    private static int movs[][] = { 
        { 0, -1 },      // Izquierda
        { -1, 0 },      // Arriba    
        { 1, 0 },       // Abajo
        { 0, 1 }        // Derecha
    };

    /* CONSTRUCTOR */

    public Mapa ( int rows, int cols, String dataFilename, Estado inicial, Estado destino, Heuristica heuristica ) throws FileNotFoundException {
        this.rows = rows;
        this.cols = cols;
        this.mapa = new Carretera[rows][cols];
        this.inicial = inicial;
        this.destino = destino;
        this.heuristica = heuristica;
        this.heuristica.setDestino(this.destino);
        this.cargarMapa(dataFilename);
    }

    /* public Estado getInicial(){
        return this.inicial;
    }

    public Heuristica getHeuristica(){
        return this.heuristica;
    } */

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
        int i, j;
        for ( i = 0; i < this.rows; i++ ) {
            for ( j = 0; j < this.cols; j++ ) {
                System.out.print( this.mapa[i][j].getSimbolo() + " " );
            }
            System.out.println();
        }
    }
    
    public void mostrarMapaConCamino ( List<Estado> camino ) {
        int i, j;
        Estado estado = new Estado(0, 0, null);

        for ( i = 0; i < this.rows; i++ ) {
            for ( j = 0; j < this.cols; j++ ) {
                estado.setRow(i);
                estado.setCol(j);
                if ( camino.contains( estado ) ) {
                    System.out.print( this.mapa[i][j].getSimbolo() + " " );
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    private boolean dentroMapa ( int row, int col ) {
        return row >= 0 && row < this.rows && col >= 0 && col < this.cols;
    }

    public List<Estado> sucesores ( Estado estado ) {
        List<Estado> sucesores = new ArrayList<>();
        int i, col, row;

        if ( ! this.dentroMapa( estado.getRow(), estado.getCol() ) ) // Si fuera del mapa
            return sucesores;


        for ( i = 0; i < movs.length; i++ ) {

            row = estado.getRow() + movs[i][0]; // Nueva Fila
            col = estado.getCol() + movs[i][1]; // Nueva Columna

            // Si dentro del mapa Y no es casilla vacia
            if ( this.dentroMapa(row, col) && this.mapa[row][col] != Carretera.VACIO ) {
                sucesores.add(new Estado(row, col, this.mapa[row][col]));
            }
        }

        return sucesores;
    }
    
    private List<Estado> camino ( Estado estado ) {
        List<Estado> camino = new ArrayList<>();
        Estado temporal = estado;

        while ( temporal != null ) {
            camino.add( 0, temporal );
            temporal = temporal.getPadre();
        }

        return camino;
    }

    public Solucion bestFirst () throws SinSolucion {
        PriorityQueue<Estado> pends = new PriorityQueue<>( (e1,e2) -> {
            return Float.compare( e1.getHeuristica(), e2.getHeuristica() );
        });
        Set<Estado> trats = new HashSet<>();
        Estado actual;
        int niter = 0;
        
        /* Inicializar Cola Prioridad */
        this.inicial.setHeuristica( this.heuristica.evaluar( this.inicial ) );
        pends.add( this.inicial );

        /* Bucle de Búsqueda */
        while ( ! pends.isEmpty() ) {
            actual = pends.poll();

            if ( actual.equals( destino ) ) 
                return new Solucion ( this.camino( actual ), niter );
            
            for ( Estado sucesor : this.sucesores( actual ) ) {
                if ( ! trats.contains(sucesor) && ! pends.contains(sucesor) ) {
                    sucesor.setHeuristica( this.heuristica.evaluar( sucesor ) );
                    sucesor.setPadre( actual );
                    sucesor.setCoste( actual );
                    pends.add(sucesor);
                }
            }
            
            trats.add(actual);
            niter++;
        }

        throw new SinSolucion();
    }

    // Amb A* sempre trobarem la solució òptima quan el valor estimat del node
    // a l'estat final es igual o mes petit que el cost real.
    public Solucion AStar () throws SinSolucion {

        PriorityQueue<Estado> pendents = new PriorityQueue<>( (e1,e2) -> {
            return Double.compare( e1.getF(), e2.getF() );
        });

        Set<Estado> tractats = new HashSet<>();
        int niter = 0;
        double nouCost;
        Estado actual;

        /* Inicializar pendientes */
        this.inicial.setHeuristica( this.heuristica.coste( this.inicial ) );
        pendents.add( this.inicial );

        while( ! pendents.isEmpty() ){
            actual = pendents.poll();

            if ( actual.equals( this.destino ) )
                return new Solucion(this.camino(actual), niter);
            
            for ( Estado sucesor : this.sucesores(actual) ) { 
                if ( ! tractats.contains(sucesor) ) {

                    nouCost = actual.getCoste() + sucesor.getCarretera().getValor() + sucesor.difCarretera(actual);

                    if ( ! pendents.contains(sucesor) ) {
                        sucesor.setPadre(actual);
                        sucesor.setHeuristica(this.heuristica.coste(sucesor));
                        sucesor.setCoste(actual);
                        pendents.add(sucesor);
                    } 
                    else if ( nouCost < sucesor.getCoste() ) {
                        sucesor.setPadre(actual);
                        sucesor.setCoste(actual);
                        pendents.remove(sucesor);
                        pendents.add(sucesor);
                    }
                }
            }
            
            tractats.add(actual);
            niter++;
        }
        
        throw new SinSolucion();
    }

}
